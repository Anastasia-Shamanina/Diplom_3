import PageObject.LoginButtonsAtPage;
import PageObject.PersonalAccount;
import forBrowser.Browser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.junit4.DisplayName;
import static constants.Url.URL_BURGERS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LogoutTest {
    private WebDriver driver;

    private String email = "ivanovanastia_6@gmail.com";
    private String password = "123456";
    private String name = "Настя";
    String accessToken;

    @Before
    public void openSite() {
        RestAssured.baseURI = URL_BURGERS; // для api

        // Создаем аккаунт через API и логинимся
        User user = new User(email, password, name);
        user.createUser()
                .then().assertThat().body("success", equalTo(true))
                .and()
                .statusCode(200);

        Response responseAccessToken = user.loginUser();
        responseAccessToken.then().assertThat().body("accessToken", notNullValue())
                .and()
                .statusCode(200);

        this.accessToken = responseAccessToken.body().jsonPath().getString("accessToken");

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
    @DisplayName("Выход из аккаунта")
    public void logoutTest() {
        LoginButtonsAtPage objLoginButtonsAtPage = new LoginButtonsAtPage(driver);
        objLoginButtonsAtPage.clickLoginInAccountButton();
        objLoginButtonsAtPage.login(email, password);
        objLoginButtonsAtPage.clickLoginButton();
        objLoginButtonsAtPage.clickPersonalAccountButton();

        PersonalAccount objPersonalAccount = new PersonalAccount(driver);
        objPersonalAccount.clickExitButton();

        objLoginButtonsAtPage.displayedLoginForm();
    }

    @After
    public void closeOut() {
        driver.quit();

        // Удаляем аккаунт через API
        User user = new User(email, password, name);
        user.deleteUser(accessToken);
    }

}
