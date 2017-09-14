package seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by hdhamee on 9/14/17.
 */
public class ChromeAppMain {

    public static void main(String[] args) throws InterruptedException {
        // set chrome web driver server path
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");

        // Create firefox browser options,like executable path
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("/usr/bin/chromium-browser");

        // Create a new instance of the Firefox driver
        WebDriver driver = new ChromeDriver(chromeOptions);

        // URL to be browsed
        String url = "http://mehikmat.github.io/";

        //Launch the Online Store Website
        driver.get(url);

        // Storing Title name in the String variable
        String title = driver.getTitle();

        // Storing Title length in the Int variable
        int titleLength = driver.getTitle().length();

        // Printing Title & Title length in the Console window
        System.out.println("Title of the page is : " + title);
        System.out.println("Length of the title is : "+ titleLength);

        // Storing URL in String variable
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals(url)){
            System.out.println("Verification Successful - The correct Url is opened.");
        }else{
            System.out.println("Verification Failed - An incorrect Url is opened.");
            //In case of Fail, you like to print the actual and expected URL for the record purpose
            System.out.println("Actual URL is : " + actualUrl);
            System.out.println("Expected URL is : " + url);
        }

        // Storing Page Source in String variable
        String pageSource = driver.getPageSource();

        // Storing Page Source length in Int variable
        int pageSourceLength = pageSource.length();

        // Printing length of the Page Source on console
        System.out.println("Total length of the Pgae Source is : " + pageSourceLength);

        // do some clicks on website
        WebElement webElement = driver.findElement(By.xpath(".//*[@class='profile-summary']/div/a"));
        //webElement.click(); OR
        webElement.sendKeys(Keys.ENTER);

        // wait for 5 seconds
        Thread.sleep(5000);

        //Test again
        driver.get("http://hikmatdhamee.com.np");

        //Closing browser
        driver.close();
    }
}
