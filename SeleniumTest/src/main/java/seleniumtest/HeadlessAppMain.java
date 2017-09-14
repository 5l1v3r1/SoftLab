package seleniumtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by hdhamee on 9/14/17.
 */
public class HeadlessAppMain {

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        // Define chrome options
        ChromeOptions options = new ChromeOptions();
        options.setBinary(new File("/usr/bin/chromium-browser"));
        options.addArguments("--disable-web-security");
        options.addArguments("--headless");

        //Set chrome capability
        DesiredCapabilities chromeCapability = new DesiredCapabilities().chrome();
        chromeCapability.setCapability(ChromeOptions.CAPABILITY, options);

        // Create a new instance of the Firefox driver
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:1111"),chromeCapability);

        // URL to be browsed
        String url = "http://mehikmat.github.io/";

        //Launch the Online Store Website
        driver.get(url);

        System.out.println("Title: "+driver.getTitle());

        // wait for 5 seconds
        Thread.sleep(5000);

        //Closing browser
        driver.close();
    }
}
