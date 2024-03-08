import PageObject.LoginButtonsAtPage;
import forBrowser.Browser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import PageObject.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

import static constants.Url.URL_BURGERS;
import static org.hamcrest.Matchers.notNullValue;

public class RegistrationTest {

    private WebDriver driver;

    private String email = "ivanovanastia_6@gmail.com";
    private String password = "123456";
    private String passwordIncorrect = "123";
    private String name = "Настя";
    String accessToken;

    @Before
    public void openSite() {
        RestAssured.baseURI = URL_BURGERS; // для api

        driver = Browser.getWebDriver("chrome");
      /*  driver = Browser.getWebDriver("yandex");*/
        driver.get(URL_BURGERS);

        // Ожидание пока не появится хэдер
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("AppHeader_header__logo__2D0X2")));
        driver.manage().window().maximize();
    }

    // Тест с корректным паролем (6 цифер)
    @Test
    @DisplayName("Регистрация с корректным паролем")
    @Description("Пароль должен быть =>6 символов")
    public void registrationTestPositiv() {
        LoginButtonsAtPage objLoginButtonsAtPage = new LoginButtonsAtPage(driver);
        objLoginButtonsAtPage.clickPersonalAccountButton();

        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.clickRegistrationLink();
        objRegistrationPage.registration(name, email, password);
        objRegistrationPage.displayedLoginWindow();

        // Удаляем аккаунт через API
        User user = new User(email, password, name);
        Response responseAccessToken = user.loginUser();
        responseAccessToken.then().assertThat().body("accessToken", notNullValue())
                .and()
                .statusCode(200);
        this.accessToken = responseAccessToken.body().jsonPath().getString("accessToken");
        user.deleteUser(accessToken);
    }

    // Тест с не корректным паролем (менее 6 цифер)
    @Test
    @DisplayName("Регистрация не корректным паролем")
    @Description("Пароль менее 6 символов")
    public void registrationTestNegativ() {
        LoginButtonsAtPage objLoginButtonsAtPage = new LoginButtonsAtPage(driver);
        objLoginButtonsAtPage.clickPersonalAccountButton();

        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.clickRegistrationLink();
        objRegistrationPage.registration(name, email, passwordIncorrect);
        objRegistrationPage.displayedPasswordError();
    }

    @After
    public void closeOut() {
        driver.quit();
    }
}

