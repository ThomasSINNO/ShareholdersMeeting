Group:
- Nom1 Prenom1
- Nom2 Prenom2

Copyright (C) 2016
Contact: Denis.Conan[at]telecom-sudparis.eu

License: See file LICENSE.txt

================================================================================

Software prerequisites:
-----------------------
	1. Java Version >= 8.0
	   (http://www.oracle.com/technetwork/java/javase/overview/index.html)
	2. Maven Version >= 3.0.4
	   (http://maven.apache.org/)

Shell variables to set:
-----------------------
	1. JAVA_HOME to your Java SDK
$
export JAVA_HOME=<the root directory of your Java installation>
echo $JAVA_HOME

Compilation and installation:
-----------------------------
	To compile and install the modules, execute the following command.
	Use the options -Dmaven.test.skip=true and -Dmaven.javadoc.skip=true
	if you do not want to execute the JUnit tests or to generate the
	JavaDoc files, so that the compilation is much more rapid.
$
mvn install
$

Execution:
-----------------------------
	To launch HelloWorld, execute the following command.
$
mvn exec:java
$

In Eclipse:
-----------
	To load the project in Eclipse, either use the maven plugin or
	create the Eclipse project using the following command:
$
mvn eclipse:clean eclipse:eclipse
$
