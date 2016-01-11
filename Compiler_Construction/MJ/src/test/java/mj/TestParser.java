package mj;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hdhamee on 1/11/16.
 */
public class TestParser {
    // Main method of the parser tester
    public static void main(String args[]) {
        Token t;
        String source = "/home/hdhamee/contributions/CoderLab/Compiler_Construction/MJ/src/test/resource/sample.mj";
        if (source != null) {
            try {
                Scanner.init(new InputStreamReader(new FileInputStream(source)));
                Parser.parse();
                System.out.println(Parser.errors + " errors detected");
            } catch (IOException e) {
                System.out.println("-- cannot open input file " + source);
            }
        } else System.out.println("-- synopsis: java MJ.TestParser <inputfileName>");
    }


}
