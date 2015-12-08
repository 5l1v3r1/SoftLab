package jninative;

/**
 * ====================JNI with C================
 * <p/>
 * Step 1: Write a Java Class that uses C Codes - HelloJNI.java
 * <p/>
 * Step 2: Create the C/C++ Header file - HelloJNI.h
 * <p/>
 * Step 3: C Implementation of HelloJNI.h - the HelloJNI.c
 *
 * Step 4: Create a so file and set in LD_LIBRARY_PATH or java.library.path
 *
 * Step5: Set VM Option -Djava.library.path=../native_keyword/jni/HelloJNI.so
 *
 * Step6: Run program
 */
public class HelloJNI {
    /**
     *  Load JNI native library at runtime
     *  hello.dll (Windows) or libhello.so (Unixes)
     *  This library shall be included in Java's library path
     *  (kept in Java system variable java.library.path)
     *  CMD param: -Djava.library.path=path_to_lib instead of -classpath=path-to-libs
     */
    static {
        System.out.println(">>>>:" + System.getProperty("java.library.path"));
        System.loadLibrary("hellojni");
    }

    /**
     * Declare a jni native method sayHello() that receives nothing and returns void
     * and which denotes that this method is implemented in another language
     */
    private native void sayHello();

    // Test Driver
    public static void main(String[] args) {
        new HelloJNI().sayHello();  // invoke the jninative method
    }
}


