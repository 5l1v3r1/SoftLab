package demo.spellchecker;

import org.apache.lucene.analysis.hunspell.HunspellDictionary;
import org.apache.lucene.analysis.hunspell.HunspellStemmer;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Hikmat Dhamee
 * @email hikmatdhamee@gmail.com
 */
public class HunsSpellCheckerApp {

    public static void main(String[] args) throws IOException, ParseException {

        HunspellDictionary dictionary =  new HunspellDictionary(new FileInputStream(new File("meta/en_US.aff")), new FileInputStream(new File("meta/en_US.dic")), Version.LUCENE_36);
        HunspellStemmer hstem = new HunspellStemmer(dictionary);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input word to be checked: ");
        String word = scanner.nextLine();

        System.out.println("MissSpelled: "+ hstem.stem(word).isEmpty());
    }
}
