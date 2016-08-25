package mj.runtime;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hdhamee on 8/25/16.
 *
 *
 // This class can be used to decode MicroJava object files.
 // Synopsis: java mj.Decode <filename>.obj
 */
public class Decode {

    public static void main(String[] arg) {
        if (arg.length == 0)
            System.out.println("-- no filename specified");
        else {
            try {
                InputStream s = new FileInputStream(arg[0]);
                byte[] code = new byte[3000];
                int len = s.read(code);
                Decoder.decode(code, 14, len);
            } catch (IOException e) {
                System.out.println("-- could not open file " + arg[0]);
            }
        }
    }
}
