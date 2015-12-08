#include <jni.h> // The header "jni.h" is available under the "<JAVA_HOME>\include" and "<JAVA_HOME>\include\win32" directories
#include <stdio.h>
#include "jninative_HelloJNI.h"

// Implementation of native method sayHello() of HelloJNI class
JNIEXPORT void JNICALL Java_jninative_HelloJNI_sayHello(JNIEnv *env, jobject thisObj) {
   printf("Welcome to JNI! You are meeting the grandfather language, the C, a big boss :) \n");
   return;
}