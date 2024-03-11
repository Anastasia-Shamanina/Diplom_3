import pages.LoginButtonsAtPage;
import browser.Browser;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import methods.BaseHttpClient;
import methods.UserMethods;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.junit4.DisplayName;
import pojo.User;

import static constants.Url.URL_BURGERS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class LoginTest extends Browser {

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
        loginButtonsAtPage.displayedHeader();
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт'")
    @Description("Вход по кнопке 'Войти в аккаунт' -> 'Войти' (кнопка в форме входа)")
    public void loginTestButtonLoginInAccount() {
        LoginButtonsAtPage objLoginButtonsAtPage = new LoginButtonsAtPage(driver);
        objLoginButtonsAtPage.clickLoginInAccountButton();
        objLoginButtonsAtPage.login(email, password);
        objLoginButtonsAtPage.clickLoginButton();
        String textSection = objLoginButtonsAtPage.displayedCreateOrderButton();
        assertEquals("Оформить заказ", textSection);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Вход по кнопке 'Войти в аккаунт' -> 'Восстановить пароль' -> кнопка 'Войти'")
    public void loginTestButtonRestorePassword() {
        LoginButtonsAtPage objLoginButtonsAtPage = new LoginButtonsAtPage(driver);
        objLoginButtonsAtPage.clickLoginInAccountButton();
        objLoginButtonsAtPage.clickRestorePasswordButton();
        objLoginButtonsAtPage.clickRestoreLoginButton();
        objLoginButtonsAtPage.login(email, password);
        objLoginButtonsAtPage.clickLoginButton();
        String textSection = objLoginButtonsAtPage.displayedCreateOrderButton();
        assertEquals("Оформить заказ", textSection);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Вход по кнопке 'Войти в аккаунт' -> 'Зарегистрироваться' -> кнопка 'Войти'")
    public void loginTestButtonLoginInRegistrationForm() {
        LoginButtonsAtPage objLoginButtonsAtPage = new LoginButtonsAtPage(driver);
        objLoginButtonsAtPage.clickLoginInAccountButton();
        objLoginButtonsAtPage.clickRegistrationLink();
        objLoginButtonsAtPage.clickRegistrationLoginButton();
        objLoginButtonsAtPage.login(email, password);
        objLoginButtonsAtPage.clickLoginButton();
        String textSection = objLoginButtonsAtPage.displayedCreateOrderButton();
        assertEquals("Оформить заказ", textSection);
    }


    @Test
    @DisplayName("Вход по кнопке 'Личный кабинет'")
    @Description("Вход по кнопке 'Личный кабинет' -> 'Войти' (кнопка в форме входа)")
    public void loginTestButtonPersonalAccount() {
        LoginButtonsAtPage objLoginButtonsAtPage = new LoginButtonsAtPage(driver);
        objLoginButtonsAtPage.clickPersonalAccountButton();
        objLoginButtonsAtPage.login(email, password);
        objLoginButtonsAtPage.clickLoginButton();
        String textSection = objLoginButtonsAtPage.displayedCreateOrderButton();
        assertEquals("Оформить заказ", textSection);
    }

    @After
    public void closeOut() {
        driver.quit();

        // Удаляем аккаунт через API
        UserMethods userMethods = new UserMethods();
        userMethods.deleteUser(accessToken);
    }
}
