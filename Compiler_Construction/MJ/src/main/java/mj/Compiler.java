package mj;

import mj.parser.Parser;
import mj.scanner.Scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hdhamee on 8/25/16.
 */
public class Compiler {
    public static void main(String[] args) {
        if (args.length < 2) {
           System.out.println("-- synopsis: java mj.Compiler <inputfileName>");
        }
        // for idea case take input from command line
        String inFile = "data/sample.mj";
        String outFile = "data/sample.obj";
        try {
            Scanner scanner = new Scanner(new FileInputStream(inFile));
            Parser parser = new Parser(scanner);
            parser.parse();
            System.out.println(parser.errors + " errors found.");
            if (parser.errors == 0) {
                try {
                    parser.code.write(new FileOutputStream(
                            objFileName(outFile)));
                } catch (IOException ex) {
                    System.err.println("Error writing output file");
                    ex.printStackTrace();
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(inFile + " not found, exiting...");
        }
    }

    private static String objFileName(String s) {
        int i = s.lastIndexOf('.');
        if (i < 0) {
            return s + ".obj";
        } else {
            return s.substring(0, i) + ".obj";
        }
    }
}
