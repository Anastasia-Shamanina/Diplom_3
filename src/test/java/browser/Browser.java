package browser;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Browser {
    protected WebDriver driver;
    public static final String PROPERTIES = "src/main/resources/config.properties";
    private static String browser;


    @Before
    public void getWebDriver(){
        FileInputStream fileInputStream; // Объявление переменной fileInputStream типа FileInputStream
        Properties properties = new Properties(); // Создание объекта Properties для работы с файлом настроек
        try {
            fileInputStream = new FileInputStream(PROPERTIES); // Читаем файл настроек
            properties.load(fileInputStream); // Загрузка файла настроек в объект Properties
            browser = properties.getProperty("browser"); // Получение значения параметра "browser" из файла настроек
        } catch (IOException e) {
            e.printStackTrace(); // Исключение при ошибке
        }
        selectBrowser(); // Выбор браузера на основе полученных настроек
    }

    public void selectBrowser() {
        if ("chrome".equals(browser))
            setUpChrome();
        else
            setUpYandex();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void setUpChrome() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
    public void setUpYandex() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver2");
        options.setBinary("/Users/anastasiashamanina/Desktop/Yandex 2.app/Contents/MacOS/Yandex");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
   /* public void setUpYandex() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }*/
}

