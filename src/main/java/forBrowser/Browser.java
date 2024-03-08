package forBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import constants.*;
public class Browser {

    public static WebDriver getWebDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/anastasiashamanina/WebDriver/bin/chromedriver");
                return new ChromeDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "/Users/anastasiashamanina/WebDriver/bin2/chromedriver");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                return new ChromeDriver(options);
            default:
                throw new RuntimeException("Unknown name browser");
        }
    }
}

