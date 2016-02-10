package mj;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hdhamee on 1/6/16.
 */
public class TestScanner {
    private static final int  // token codes
            none      = 0,

            ident     = 1,
            number    = 2,
            charCon   = 3,

            plus      = 4,
            minus     = 5,
            times     = 6,
            slash     = 7,
            rem       = 8,
            eql       = 9,
            neq       = 10,
            lss       = 11,
            leq       = 12,
            gtr       = 13,
            geq       = 14,
            assign    = 15,
            semicolon = 16,
            comma     = 17,
            period    = 18,
            lpar      = 19,
            rpar      = 20,
            lbrack    = 21,
            rbrack    = 22,
            lbrace    = 23,
            rbrace    = 24,

            class_    = 25,
            else_     = 26,
            final_    = 27,
            if_       = 28,
            new_      = 29,
            print_    = 30,
            program_  = 31,
            read_     = 32,
            return_   = 33,
            void_     = 34,
            while_    = 35,

            eof       = 36;

    private static String[] tokenName = {
            // error token-codes
            "none",

            // token classes token-codes
            "ident ",
            "number ",
            "charCon ",

            // operators and special characters token-codes
            "plus",
            "minus",
            "times",
            "slash",
            "rem",
            "eql",
            "neq",
            "lss",
            "leq",
            "gtr",
            "geq",
            "assign",
            "semicolon",
            "comma",
            "period",
            "lpar",
            "rpar",
            "lbrack",
            "rbrack",
            "lbrace",
            "rbrace",

            // keywords token-codes
            "class_",
            "else_",
            "final_",
            "if_",
            "new_",
            "print_",
            "program_",
            "read_",
            "return_",
            "void_",
            "while_",

            // end of line token-codes
            "eof"
    };

    // Main method of the scanner tester
    public static void main(String[] args) {
        Token t;
        String source = "/home/hdhamee/contributions/CoderLab/Compiler_Construction/MJ/src/test/resource/sample.mj";
        //String source = "/home/hdhamee/contributions/CoderLab/Compiler_Construction/MJ/src/test/resource/sample2.mj";
        //String source = "/home/hdhamee/contributions/CoderLab/Compiler_Construction/MJ/src/test/resource/BuggyScannerInput.mj";
        if (source != null) {
            try {
                Scanner.init(new InputStreamReader(new FileInputStream(source)));
                do {
                    t = Scanner.next();
                    System.out.print("line " + t.line + ", col " + t.col + ": " + tokenName[t.kind]);
                    switch (t.kind) {
                        case ident:   System.out.println(t.string); break;
                        case number:  System.out.println(t.val); break;
                        default: System.out.println(); break;
                    }
                } while (t.kind != eof);
            } catch (IOException e) {
                System.out.println("-- cannot open input file " + source + " " + e.getLocalizedMessage());
            }
        } else System.out.println("-- synopsis: java MJ.TestScanner <inputfileName>");
    }
}
