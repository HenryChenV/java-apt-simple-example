Java APT Simple Example
=======================

This is a simple example to demonstrate the capabilities of the APT (Annotations Processor Tool)
It will create a class with a main entry point that will call the annotated method.

Project Structure
-----------------

The project is divided in 4 modules:
 {root}							The main project that will compile the 3 sub projects
 {root}/annotation 				The project that defines the Annotation Type
 {root}/annotationProcessor		The project that defines the Annotation Processor. It also has the Plugin.xml definition to run inside Eclipse
 {root}/example 				The project that make use of the annotation and will trigger in compilation the execution of the Annotation Processor

Project requirements
--------------------
 - JDK 1.6+ 
 - Maven 3+
 - Command-Line commands required: mvn, java

Project execution
-----------------
 - Clone the project or download the zip file
 - > mvn clean install
 - > java -cp example/target/java-apt-simple-example-example-0.0.1-SNAPSHOT.jar com.gustavoalberola.java.apt.annotation.AnnotatedClassWrappedClass