package mj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

/**
 * Created by hdhamee on 1/6/16.
 */
public class Scanner {
    private static final char eofCh = '\u0080'; // character that is returned at the end of the file
    private static final char eol = '\n';
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
    private static final String key[] = {
            // sorted list of keywords
            "class", "else", "final", "if", "new", "print",
            "program", "read", "return", "void", "while"
    };
    private static final int keyVal[] = {
            class_, else_, final_, if_, new_, print_,
            program_, read_, return_, void_, while_
    };

    private static char ch;			// lookahead character
    public  static int col;			// current column
    public  static int line;		// current line
    private static int pos;			// current position from start of source file
    private static Reader in;  	// source file reader
    private static char[] lex;	// current lexeme (token string)
    private static HashMap<String,Integer> symbols = new HashMap<String, Integer>();

    //----- ch = next input character
    private static void nextCh() {
        try {
            ch = (char)in.read(); col++; pos++;
            if (ch == eol) {line++; col = 0;}
            else if (ch == '\uffff') ch = eofCh;
        } catch (IOException e) {
            ch = eofCh;
        }
    }

    //--------- Initialize scanner
    public static void init(Reader r) {
        in = new BufferedReader(r);
        lex = new char[64];
        line = 1; col = 0;
        // put symbol codes in symbol table
        symbols.put("none",0);
        symbols.put("ident",1);
        symbols.put("number",2);
        symbols.put("charCon",3);
        symbols.put("plus",4);
        symbols.put("minus",5);
        symbols.put("times",6);
        symbols.put("slash",7);
        symbols.put("rem",8);
        symbols.put("eql",9);
        symbols.put("neq",10);
        symbols.put("lss",11);
        symbols.put("leq",12);
        symbols.put("gtr",13);
        symbols.put("geq",14);
        symbols.put("assign",15);
        symbols.put("semicolon",16);
        symbols.put("comma",17);
        symbols.put("period",18);
        symbols.put("lpar",19);
        symbols.put("rpar",20);
        symbols.put("lbrack",21);
        symbols.put("rbrack",22);
        symbols.put("lbrace",23);
        symbols.put("rbrace",24);
        symbols.put("class_",25);
        symbols.put("else_",26);
        symbols.put("final_",27);
        symbols.put("if_",28);
        symbols.put("new_",29);
        symbols.put("print_",30);
        symbols.put("program_",31);
        symbols.put("read_",32);
        symbols.put("return_",33);
        symbols.put("void_",34);
        symbols.put("while_",35);
        symbols.put("eof",36);
        nextCh();// reads the first character into ch and increments col to 1
    }

    //---------- Return next input token
    public static Token next() {
        while (ch <= ' ' ) nextCh(); // skip blanks, tabs, eols
        Token t = new Token(); t.line = line; t.col = col;
        switch (ch) {
            case 'a':case 'b': case 'c':case 'd':case 'e':case 'f':case 'g':case 'h':case 'i':case 'j':case 'k':case 'l':case 'm':
            case 'n':case 'o':case 'p':case 'q':case 'r':case 's':case 't':case 'u':case 'v':case 'w':case 'x':case 'y': case 'z':
            case 'A':case 'B':case 'C':case 'D':case 'E':case 'F':case 'G':case 'H':case 'I':case 'J':case 'K':case 'L':case 'M':
            case 'N':case 'O':case 'P':case 'Q':case 'R':case 'S':case 'T':case 'U':case 'V':case 'W':case 'X':case 'Y':case 'Z':
                readName(t);
                break;
            case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
                readNumber(t);
                break;
            case ';':
                nextCh();
                t.kind = semicolon;
                break;
            case '.':
                nextCh();
                t.kind = period;
                break;
            case eofCh:
                t.kind = eof;
                break; // no nextCh() any more
            case '=':
                nextCh();
                if (ch == '=') {
                    nextCh();
                    t.kind = eql;
                } else
                    t.kind = assign;
                break;
            case '/': nextCh();
                if (ch == '/') {
                    do
                        nextCh();
                    while (ch != '\n' && ch != eofCh);
                    t = next(); // call scanner recursively
                } else
                    t.kind = slash;
                break;
            default:
                nextCh();
                t.kind = none;
                break;
        }
        return t;
    } // ch holds the next character that is still unprocessed

    private static void readName(Token t) {
        String ret = "";
        while ((ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') && ch !=eofCh) {
            ret += ch;
            try {
                ch = (char) in.read();
            } catch (IOException e) {
                ch = eofCh;
            }
        }
        t.kind = tokenCode(ret);
        t.string = ret;
    }

    private static void readNumber(Token t) {
        String num = "";
        int radix = 10;
        while ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f')  || (ch >= 'A' && ch <= 'F') && ch != eofCh) {
            num += ch;
            try {
                ch = (char) in.read();
            } catch (IOException e) {
                ch = eofCh;
            }
        }
        if (ch == 'h' || ch == 'H') {
            radix = 16;
            try {
                ch = (char) in.read();
            } catch (IOException e) {
                ch = eofCh;
            }
        }
        Integer value = Integer.parseInt(num, radix);
        t.kind = number;
        t.val = value;
    }

    private static int tokenCode(String ret) {
        String key = ret+"_";
        int v = ident;
        if (symbols.containsKey(ret)){
            v = symbols.get(ret);
        }
        if (symbols.containsKey(key)){
            v = symbols.get(key);
        }
        return v;
    }
}
