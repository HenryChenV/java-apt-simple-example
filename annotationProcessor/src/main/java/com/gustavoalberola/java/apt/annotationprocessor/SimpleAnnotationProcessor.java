package com.gustavoalberola.java.apt.annotationprocessor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes(value = {"com.gustavoalberola.java.apt.annotation.SimpleAnnotation"})
public class SimpleAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		processingEnv.getMessager().printMessage(Kind.NOTE, "Annotation processor executed!");
		JavaFileObject jfo = null;
		try {
			jfo = processingEnv.getFiler().createSourceFile("annotations.MyGeneratedClass");						
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(jfo.openWriter());
			
			bw.append("package ");
			bw.append("annotations;");
			bw.newLine();
			bw.append("public class MyGeneratedClass {}");
			bw.newLine();
			
			bw.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				bw.close();
			} catch (IOException e) {}
		}
		
		return true;		
	}

}
