import PageObject.LoginButtonsAtPage;
import PageObject.MainPage;
import PageObject.PersonalAccount;
import browser.Browser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import methods.BaseHttpClient;
import methods.UserMethods;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.junit4.DisplayName;
import pojo.User;

import static constants.Url.URL_BURGERS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

// Тесты на Переход в личный кабинет и Переход из личного кабинета в конструктор
public class FromPersonalAccountToConstructorTest extends Browser {


    private String email = "ivanovanastia_6@gmail.com";
    private String password = "123456";
    private String name = "Настя";
    String accessToken;

    @Before
    public void openSite() {
        RestAssured.requestSpecification = BaseHttpClient.baseRequestSpec();

        // Создаем аккаунт через API и логинимся
        User user = new User(email, password, name);
        UserMethods userMethods = new UserMethods();
        userMethods.createUser(user)
                .then().assertThat().body("success", equalTo(true))
                .and()
                .statusCode(200);

        Response responseAccessToken = userMethods.loginUser(user);
        responseAccessToken.then().assertThat().body("accessToken", notNullValue())
                .and()
                .statusCode(200);

        this.accessToken = responseAccessToken.body().jsonPath().getString("accessToken");

        driver.get(URL_BURGERS);

        // Ожидание пока не появится хэдер
        LoginButtonsAtPage loginButtonsAtPage = new LoginButtonsAtPage(driver);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("AppHeader_header__logo__2D0X2")));
        driver.manage().window().maximize();

        // Авторизация
        LoginButtonsAtPage objLoginButtonsAtPage = new LoginButtonsAtPage(driver);
        objLoginButtonsAtPage.clickLoginInAccountButton();
        objLoginButtonsAtPage.login(email, password);
        objLoginButtonsAtPage.clickLoginButton();
    }

    @Test
    @DisplayName("Переход по кнопке 'Конструктор'")
    public void transitionToConctructorTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickPersonalAccountButton();
        objMainPage.clickConstructorButton();
        String textSection = objMainPage.displayedTextConstructorSection();
        assertEquals("Соберите бургер", textSection);
    }

    @Test
    @DisplayName("Переход по лого")
    public void transitionToLogoTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickPersonalAccountButton();
        objMainPage.clickLogo();
        String textSection = objMainPage.displayedTextConstructorSection();
        assertEquals("Соберите бургер", textSection);
    }

    @Test
    @DisplayName("Переход по кнопке 'Личный кабинет'")
    public void transitionToPersonalAccountTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickConstructorButton();
        objMainPage.clickPersonalAccountButton();
        PersonalAccount objPersonalAccount = new PersonalAccount(driver);
        String textSection = objPersonalAccount.displayedProfileSection();
        assertEquals("Профиль", textSection);
    }

    @After
    public void closeOut() {
        driver.quit();

        // Удаляем аккаунт через API
        UserMethods userMethods = new UserMethods();
        userMethods.deleteUser(accessToken);
    }
}
