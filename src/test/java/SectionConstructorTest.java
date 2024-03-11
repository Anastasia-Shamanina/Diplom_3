import PageObject.LoginButtonsAtPage;
import PageObject.MainPage;
import browser.Browser;
import io.qameta.allure.Description;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.junit4.DisplayName;

import static constants.Url.URL_BURGERS;
import static org.junit.Assert.assertEquals;

public class SectionConstructorTest extends Browser {

    @Before
    public void openSite() {
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
        String textSection = objMainPage.getTextSection();
        assertEquals("Булки", textSection);
    }

    @Test
    @DisplayName("Переход к разделу: 'Соусы'")
    @Description("В разделе 'Конструктор' переход к разделу: 'Соусы'")
    public void saucesSectionTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickSaucesSectionButton();
        String textSection = objMainPage.getTextSection();
        assertEquals("Соусы", textSection);
    }

    @Test
    @DisplayName("Переход к разделу: 'Начинки'")
    @Description("В разделе 'Конструктор' переход к разделу: 'Начинки'")
    public void fillingSectionTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickFillinпSectionButton();
        String textSection = objMainPage.getTextSection();
        assertEquals("Начинки", textSection);
    }

    @After
    public void closeOut() {
        driver.quit();
    }
}
