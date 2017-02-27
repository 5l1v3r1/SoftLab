import java.util.List;

/**
 * @author Hikmat Dhamee
 * @email hikmatdhamee@gmail.com
 */
public class WebCrawlerTest {

    static public void main(String[] args) throws Exception {

        SimpleWebCrawler webCrawler = new SimpleWebCrawler("http://facebookcom", 2);

        for (List<String> ls : webCrawler.crawledData) {
            String url = ls.get(0);
            String text = ls.get(1);

            System.out.println("URL: " + url + " content: " + text);
        }
    }
}