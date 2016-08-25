package mj;

import junit.framework.TestCase;
import mj.parser.Parser;
import mj.scanner.Scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by hdhamee on 1/11/16.
 */
public class TestParser extends TestCase {

    public void testParser(){
        String inFile = "data/sample.mj";
        try {
            Scanner scanner = new Scanner(new FileInputStream(inFile));
            Parser parser = new Parser(scanner);
            parser.parse();

            System.out.println(parser.errors + " errors found.");

        } catch (FileNotFoundException ex) {
            System.err.println(inFile + " not found, exiting...");
        }
    }
}
