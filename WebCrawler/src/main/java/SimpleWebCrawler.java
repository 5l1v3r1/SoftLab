import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Hikmat Dhamee
 * @email hikmatdhamee@gmail.com
 */
public class SimpleWebCrawler {
    public static final String VALID_URL = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
    public static List<List<String>> crawledData = new ArrayList<List<String>>();

    public SimpleWebCrawler(String seedUrl, int maxVisitCount) throws Exception {
        if(seedUrl.matches(VALID_URL)){
            System.out.println("Malformed seed url! " + seedUrl);
            return;
        }
        List<String> urls = new ArrayList<String>();
        Set<String> visitedUrls = new HashSet<String>();
        int currentVisitCount = 0;

        urls.add(seedUrl);

        while (currentVisitCount < maxVisitCount && !urls.isEmpty()) {
            String url = urls.remove(0);

            if (url.matches(VALID_URL) && !visitedUrls.contains(url)){
                visitedUrls.add(url);

                Document doc = Jsoup.connect(url).get();

                currentVisitCount++;

                String text = doc.text();

                // Skip any pages where text on doc is identical to existing
                // doc (e.g., http://example.com and http://exaple.com/index.html
                boolean process = true;
                for (List<String> ls : crawledData) {
                    if (text.equals(ls.get(1))) {
                        process = false;
                        break;
                    }
                }

                if (process) {
                    Elements inPageLinks = doc.select("a[href]");

                    for (Element element :inPageLinks) {
                        String link = element.attr("abs:href");
                        urls.add(link);
                    }

                    List<String> ls = new ArrayList<String>(2);
                    ls.add(url);
                    ls.add(text);
                    crawledData.add(ls);
                }
            }
        }
    }
}
