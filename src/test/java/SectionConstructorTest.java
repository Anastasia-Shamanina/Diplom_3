import PageObject.LoginButtonsAtPage;
import PageObject.MainPage;
import forBrowser.Browser;
import io.qameta.allure.Description;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.junit4.DisplayName;

import static constants.Url.URL_BURGERS;

public class SectionConstructorTest {
    private WebDriver driver;

    @Before
    public void openSite() {
        driver = Browser.getWebDriver("chrome");
        /*driver = Browser.getWebDriver("yandex");*/
        driver.get(URL_BURGERS);

        // Ожидание пока не появится хэдер
        LoginButtonsAtPage loginButtonsAtPage = new LoginButtonsAtPage(driver);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("AppHeader_header__logo__2D0X2")));
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Переход к разделу: 'Булки'")
    @Description("В разделе 'Конструктор' переход к разделу: 'Булки' без входа в аккаунт ")
    public void bunSectionTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickSaucesSectionButton(); //дефолтно открывается раздел булки, сначала сделан переход на другой раздел
        objMainPage.clickBunSectionButton();
        objMainPage.displayedTextBunSection();
    }

    @Test
    @DisplayName("Переход к разделу: 'Соусы'")
    @Description("В разделе 'Конструктор' переход к разделу: 'Соусы'")
    public void saucesSectionTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickSaucesSectionButton();
        objMainPage.displayedTextSaucesSection();
    }

    @Test
    @DisplayName("Переход к разделу: 'Начинки'")
    @Description("В разделе 'Конструктор' переход к разделу: 'Начинки'")
    public void fillingSectionTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickFillinпSectionButton();
        objMainPage.displayedTextFillingSection();
    }

    @After
    public void closeOut() {
        driver.quit();
    }
}
