#!/usr/bin/env bash
#script to generate c header file for java class which uses native method

$JAVA_HOME/bin/javac src/jninative/HelloJNI.java
cd src
$JAVA_HOME/bin/javah -d ../jni jninative.HelloJNI