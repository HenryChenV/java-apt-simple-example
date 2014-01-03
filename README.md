# Java APT Simple Example
This is a simple example to demonstrate the capabilities of the APT (Annotations Processor Tool)
It will create a class with a main entry point that will call the annotated method.

## Project Structure
The project is divided in 5 modules

 - __*{project_home}*__	The main project that will compile the 3 sub projects
 - __*{project_home}*/annotation__ The project that defines the Annotation Type
 - __*{project_home}*/annotationProcessor__ The project that defines the Annotation Processor. It also has the Plugin.xml definition to run inside Eclipse
 - __*{project_home}*/annotationProcessor2__ A dummy copy of the Annotation Processor. This is used to demostrate the issues with autodiscovery 
 - __*{project_home}*/example__ The project that make use of the annotation and will trigger in compilation the execution of the Annotation Processor

## Project requirements
 - JDK 1.6+ 
 - Maven 3+
 - Command-Line commands required: mvn, java

## Project execution 1
 - Clone the project or download the zip file
 - > mvn clean install
 - > java -cp example/target/java-apt-simple-example-example-0.0.1-SNAPSHOT.jar com.gustavoalberola.java.apt.annotation.AnnotatedClassWrappedClass

Because the Annotation Process autodiscovery tries to find an Annotation Processor for the Annotation _@SimpleAnnotation_, it looks in the class path, and the first match is _annotationProcesor_.
The _process_ operation is returning _true_, which means that the AP is claiming possesion over the annotation, and java will not look forward for other AP. Meaning that _annotationProcessor2_ is never executed.

## Project execution 2
 - Clone the project or download the zip file
 - > mvn -P connector-certification clean install

We are executing a _maven profile_ in which we pass an specific AP as compile argument (_-processor_). This will skip the process of _AP Auto Discovery_ an instead, execute the defined AP, resulting in the execution of the AP skipped in the execution 1.