package hk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hdhamee on 3/27/17.
 */
public class LawyerScrapper {
    public static final String ROOT_URL = "https://www.msbar.org/lawyer-directory.aspx";

    public static void main (String args[]) {
        // speficy the pages to be scrapped
        final String pages = "A-B"; // to be taken from command line

        // specify the fields to be scrapped
        final List<String> fields = Arrays.asList("Name:","Email:","Phone:"); // to be taken from command line

        // output file name
        String outputFileName = "LawyerInfo.csv"; // to be taken from command line

        // for avoiding javax.net.ssl.SSLProtocolException: handshake alert:  unrecognized_name
        System.setProperty("jsse.enableSNIExtension", "false");

        // WARNING: do it only if security isn't important, otherwise you have
        // to follow this advices: http://stackoverflow.com/a/7745706/1363265
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
            public X509Certificate[] getAcceptedIssuers(){return null;}
            public void checkClientTrusted(X509Certificate[] certs, String authType){}
            public void checkServerTrusted(X509Certificate[] certs, String authType){}
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        try{
            // init a file to write final result
            File file = new File(outputFileName);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            //CRAWL: get the page html
            Document doc =  Jsoup.connect(ROOT_URL)
                    .data("type","7")
                    .data("term","B")
                    .userAgent("Ubuntu Chromium/41.0.2272.76 Chrome/41.0.2272.76 Safari/537.36")
                    .ignoreHttpErrors(true)
                    .timeout(0)
                    .maxBodySize(20*1024*1024)
                    .get();

            //SCRAP: Traverse the results: LAYOUT:>  Name;Admit Date;Mailing Address;Phone;Email;Status
            for (Element lawyer : doc.select("section.LawyerInformation")){
                Elements lawyerInfo = lawyer.select("div");
                Map<String,String> kv = new HashMap<String, String>();

                for(int i=0; i<lawyerInfo.size(); i=i+2) {

                    String field = lawyerInfo.get(i).text();
                    String value = lawyerInfo.get(i+1).text();

                    if(fields.contains(field)) kv.put(field,value);
                }

                // extract all the fields
                StringBuilder row = new StringBuilder();
                String delim = "";
                for (String field: fields) {
                    row.append(delim+kv.getOrDefault(field,""));
                    delim = ";";
                }

                // write a lawyer information
                bos.write( (row.toString() + "\n").getBytes());
            }

            // closing file
            bos.flush();
            bos.close();
            fos.flush();
            fos.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Exception caught: " + e.getLocalizedMessage());
        }
    }
}
