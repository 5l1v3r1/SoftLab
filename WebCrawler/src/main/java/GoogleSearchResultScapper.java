import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by hdhamee on 3/27/17.
 */
public class GoogleSearchResultScapper {
    public static void main (String args[])
    {
        Document doc;
        try{
            doc =  Jsoup.connect("https://www.google.com/search?q=deerwalk&*").userAgent("Ubuntu Chromium/41.0.2272.76 Chrome/41.0.2272.76 Safari/537.36").ignoreHttpErrors(true).timeout(0).get();
            //Traverse the results
            System.out.println("size: " +doc.select("div.g").size());
            for (Element result : doc.select("div.g")){

                System.out.println(result.getAllElements().select("cite").text() + " ~~~ " + result.select("h3.r a").text() + "~~~" + result.getElementsByClass("st").text());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
