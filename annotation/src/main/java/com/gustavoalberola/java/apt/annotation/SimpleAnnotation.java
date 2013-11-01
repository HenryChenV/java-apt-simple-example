package com.gustavoalberola.java.apt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(value = {ElementType.METHOD})
public @interface SimpleAnnotation {
	public enum Types {
		SIMPLE,
		MEDIUM,
		COMPLEX;
	}
	
	Types type() default Types.SIMPLE; 
}