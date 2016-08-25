package mj;

import junit.framework.TestCase;
import mj.scanner.Scanner;
import mj.scanner.Token;

import java.io.ByteArrayInputStream;

/**
 * Created by hdhamee on 1/6/16. *
 * cloned from https://github.com/VivienBarousse/MicroJava
 */
public class TestScanner extends TestCase {
    public void testNextKeywords() {

        String program = "class else final if new print program read return void while";
        Scanner scanner = new Scanner(new ByteArrayInputStream(program.getBytes()));

        assertEquals(scanner.next().kind, Token.CLASS);
        assertEquals(scanner.next().kind, Token.ELSE);
        assertEquals(scanner.next().kind, Token.FINAL);
        assertEquals(scanner.next().kind, Token.IF);
        assertEquals(scanner.next().kind, Token.NEW);
        assertEquals(scanner.next().kind, Token.PRINT);
        assertEquals(scanner.next().kind, Token.PROGRAM);
        assertEquals(scanner.next().kind, Token.READ);
        assertEquals(scanner.next().kind, Token.RETURN);
        assertEquals(scanner.next().kind, Token.VOID);
        assertEquals(scanner.next().kind, Token.WHILE);
        assertEquals(scanner.next().kind, Token.EOF);
    }

    public void testIdentifiers() {
        String program = "foo bar baz";
        Scanner scanner = new Scanner(new ByteArrayInputStream(program.getBytes()));

        Token token;
        token = scanner.next();
        assertEquals(token.kind, Token.IDENT);
        assertEquals(token.string, "foo");

        token = scanner.next();
        assertEquals(token.kind, Token.IDENT);
        assertEquals(token.string, "bar");

        token = scanner.next();
        assertEquals(token.kind, Token.IDENT);
        assertEquals(token.string, "baz");

        assertEquals(scanner.next().kind, Token.EOF);
    }

    public void testNumbers() {
        String program = "42 12 1337";
        Scanner scanner = new Scanner(new ByteArrayInputStream(program.getBytes()));

        Token token;

        token = scanner.next();
        assertEquals(token.kind, Token.NUMBER);
        assertEquals(token.value, 42);

        token = scanner.next();
        assertEquals(token.kind, Token.NUMBER);
        assertEquals(token.value, 12);

        token = scanner.next();
        assertEquals(token.kind, Token.NUMBER);
        assertEquals(token.value, 1337);

        assertEquals(scanner.next().kind, Token.EOF);
    }

    public void testCharConstants() {
        String program = "'a'";
        Scanner scanner = new Scanner(new ByteArrayInputStream(program.getBytes()));

        Token token = scanner.next();
        assertEquals(token.kind, Token.CHAR_CONST);
        assertEquals(token.value, 'a');

        assertEquals(scanner.next().kind, Token.EOF);
    }

    public void testCharConstantsEscaped() {
        String program = "'\\'' '\\n' '\\t'";
        Scanner scanner = new Scanner(new ByteArrayInputStream(program.getBytes()));

        Token token;

        token = scanner.next();
        assertEquals(token.kind, Token.CHAR_CONST);
        assertEquals(token.value, '\'');

        token = scanner.next();
        assertEquals(token.kind, Token.CHAR_CONST);
        assertEquals(token.value, '\n');

        token = scanner.next();
        assertEquals(token.kind, Token.CHAR_CONST);
        assertEquals(token.value, '\t');

        assertEquals(scanner.next().kind, Token.EOF);
    }

    public void testOperators() {
        String program = "+ - * / % = == != < <= > >=";
        Scanner scanner = new Scanner(new ByteArrayInputStream(program.getBytes()));

        assertEquals(scanner.next().kind, Token.PLUS);
        assertEquals(scanner.next().kind, Token.MINUS);
        assertEquals(scanner.next().kind, Token.TIMES);
        assertEquals(scanner.next().kind, Token.SLASH);
        assertEquals(scanner.next().kind, Token.REM);
        assertEquals(scanner.next().kind, Token.ASSIGN);
        assertEquals(scanner.next().kind, Token.EQL);
        assertEquals(scanner.next().kind, Token.NEQ);
        assertEquals(scanner.next().kind, Token.LESS);
        assertEquals(scanner.next().kind, Token.LEQ);
        assertEquals(scanner.next().kind, Token.GTR);
        assertEquals(scanner.next().kind, Token.GEQ);
        assertEquals(scanner.next().kind, Token.EOF);
    }

    public void testSeparators() {
        String program = "; . ,";
        Scanner scanner = new Scanner(new ByteArrayInputStream(program.getBytes()));

        assertEquals(scanner.next().kind, Token.SEMICOLON);
        assertEquals(scanner.next().kind, Token.PERIOD);
        assertEquals(scanner.next().kind, Token.COMMA);
        assertEquals(scanner.next().kind, Token.EOF);
    }

    public void testBrackets() {
        String program = "(){}[]";
        Scanner scanner = new Scanner(new ByteArrayInputStream(program.getBytes()));

        assertEquals(scanner.next().kind, Token.LPAR);
        assertEquals(scanner.next().kind, Token.RPAR);
        assertEquals(scanner.next().kind, Token.LBRACE);
        assertEquals(scanner.next().kind, Token.RBRACE);
        assertEquals(scanner.next().kind, Token.LBRACK);
        assertEquals(scanner.next().kind, Token.RBRACK);
        assertEquals(scanner.next().kind, Token.EOF);
    }

    public void testComments() {
        String program = "program // program";
        Scanner scanner = new Scanner(new ByteArrayInputStream(program.getBytes()));

        assertEquals(scanner.next().kind, Token.PROGRAM);
        assertEquals(scanner.next().kind, Token.EOF);
    }
}
