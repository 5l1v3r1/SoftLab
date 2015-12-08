#!/usr/bin/env bash

# Define and Set environment variable JAVA_HOME to JDK installed directory
# I recommend that you set JAVA_HOME permanently, via "Control Panel" ⇒ "System" ⇒ "Environment Variables"
#JAVA_HOME=$JAVA_HOME

#  In Windows, you can refer a environment variable by adding % prefix and suffix
#export $JAVA_HOME

# Compile HellJNI.c into shared library hello.dll
cd jni
#gcc -c -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/win32" -shared -o hello.so HelloJNI.c // for windows

#// Compile-only with -c flag. Output is HElloJNI.o
gcc -c -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux" -fPIC -c HelloJNI.c
#// Link into shared library "HelloJNI.so"
gcc -shared -Wl,-soname,libhellojni.so -o libhellojni.so HelloJNI.o

#check compiled function in so file
nm libhellojni.so | grep say
cdir=`pwd`
#put directory containing so file not the so file itself
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$cdir/jni