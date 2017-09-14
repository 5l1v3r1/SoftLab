package seleniumtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Created by hdhamee on 9/14/17.
 */
public class FirefoxAppMain {

    public static void main(String[] args) throws InterruptedException {
        // Create firefox browser options,like executable path
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary("/opt/firefox/firefox");

        // Create a new instance of the Firefox driver
        WebDriver driver = new FirefoxDriver(firefoxOptions);

        //Launch the Online Store Website
        driver.get("http://www.store.demoqa.com");

        // Print a Log In message to the screen
        System.out.println("Successfully opened the website www.Store.Demoqa.com");

        //Wait for 5 Sec
        Thread.sleep(10000);

        // Close the driver
        driver.quit();
    }
}
