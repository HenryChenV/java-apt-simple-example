package com.gustavoalberola.java.apt.example;

import com.gustavoalberola.java.apt.annotation.SimpleAnnotation;

public class AnnotatedClass {
	
	static public void main(String[] args) {
		(new AnnotatedClass()).foo();
	}
	
	@SimpleAnnotation
	public void foo() {
		System.out.println("Im the foo master");		
	}
}
