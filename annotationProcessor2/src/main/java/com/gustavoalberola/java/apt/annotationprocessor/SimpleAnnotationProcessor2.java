package com.gustavoalberola.java.apt.annotationprocessor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SupportedAnnotationTypes(value = { "com.gustavoalberola.java.apt.annotation.SimpleAnnotation" })
public class SimpleAnnotationProcessor2 extends AbstractProcessor {

	static final private Log LOGGER = LogFactory.getLog(SimpleAnnotationProcessor2.class);
		
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		processingEnv.getMessager().printMessage(Kind.NOTE, "Annotation processor executed!");

		for (TypeElement annotationElement : annotations) {
			
			LOGGER.info("APT Tool Example 2nd Processor");			
		}
		
		return true;
	}
}
