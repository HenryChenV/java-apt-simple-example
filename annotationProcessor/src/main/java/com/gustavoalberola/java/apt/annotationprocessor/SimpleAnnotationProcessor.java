package com.gustavoalberola.java.apt.annotationprocessor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes(value = { "com.gustavoalberola.java.apt.annotation.SimpleAnnotation" })
public class SimpleAnnotationProcessor extends AbstractProcessor {

	static private final String CLASS_SUFFIX = "WrappedClass";
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		processingEnv.getMessager().printMessage(Kind.NOTE, "Annotation processor executed!");

		for (TypeElement annotationElement : annotations) {
			PackageElement packageElement = (PackageElement) annotationElement.getEnclosingElement();
			String pckName = packageElement.getQualifiedName().toString();
			
			
			Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(annotationElement);
			
			for (Element implementatorElement : elementsAnnotatedWith) {
				
				if (implementatorElement.getKind() == ElementKind.METHOD) {
					Element classElement = implementatorElement.getEnclosingElement();
					createClass(classElement, pckName);
				} else if (implementatorElement.getKind() == ElementKind.CLASS) {
					createClass(implementatorElement, pckName);
				}
				
				
			}
		}
		
		return true;
	}

	private void createClass(Element implementatorElement, String pckName) {
		TypeElement classElement = (TypeElement) implementatorElement;
		String classSimpleName = classElement.getSimpleName().toString();
		JavaFileObject jfo = null;
		
		try {
			jfo = processingEnv.getFiler().createSourceFile(String.format("%s.%s%s", pckName , classSimpleName, CLASS_SUFFIX));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(jfo.openWriter());

			bw.append("package ");
			bw.append(pckName + ";");
			bw.newLine();
			bw.append(String.format("public class %s%s {}", classSimpleName, CLASS_SUFFIX));
			bw.newLine();

			bw.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
			}
		}
	}
}
