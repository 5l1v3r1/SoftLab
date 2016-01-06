package mj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by hdhamee on 1/6/16.
 */
public class Scanner {
    private static final char eofCh = '\u0080';
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
    private static final String key[] = { // sorted list of keywords
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
        nextCh();
    }

    //---------- Return next input token
    public static Token next() {
        while (ch <= ' ') nextCh(); // skip blanks, tabs, eols
        Token t = new Token(); t.line = line; t.col = col;
        switch (ch) {
            case 'a': case 'b':  case 'z': case 'A': case 'B':  case 'Z':
                readName(t); break;
            case '0': case '1':  case '9':
                readNumber(t); break;
            case ';': nextCh(); t.kind = semicolon; break;
            case '.': nextCh(); t.kind = period; break;
            case eofCh: t.kind = eof; break; // no nextCh() any more

            case '=': nextCh();
                if (ch == '=') { nextCh(); t.kind = eql; } else t.kind = assign;
                break;

            case '/': nextCh();
                if (ch == '/') {
                    do nextCh(); while (ch != '\n' && ch != eofCh);
                    t = next(); // call scanner recursively
                } else t.kind = slash;
                break;
            default: nextCh(); t.kind = none; break;
        }
        return t;
    } // ch holds the next character that is still unprocessed

    // todo: template impl
    private static void readNumber(Token t) {
        StringBuilder b = new StringBuilder();
        b.append(ch);
        do {
            try {
                ch = (char) in.read();
                col++;
                pos++;
                b.append(ch);
                if (ch == eol) {
                    line++;
                    col = 0;
                    break;
                } else if(ch == '\uffff'){
                    ch = eofCh;
                    break;
                }
                else if (Character.isWhitespace(ch)){
                    col++;
                    break;
                }
            } catch (IOException e) {
                ch = eofCh;
            }
        }while (true);
        t.string = b.toString();
        t.kind = 1;
    }
    // todo; this is sample impl
    private static void readName(Token t) {
        StringBuilder b = new StringBuilder();
        b.append(ch);
        do {
            try {
                ch = (char) in.read();
                col++;
                pos++;
                b.append(ch);
                if (ch == eol) {
                    line++;
                    col = 0;
                    break;
                } else if(ch == '\uffff'){
                    ch = eofCh;
                    break;
                }
                else if (Character.isWhitespace(ch)){
                    col++;
                    break;
                }
            } catch (IOException e) {
                ch = eofCh;
            }
        }while (true);
        t.string = b.toString();
        t.kind = 1;
    }
}
