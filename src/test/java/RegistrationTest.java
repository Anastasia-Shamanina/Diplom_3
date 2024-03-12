import pages.LoginButtonsAtPage;
import browser.Browser;
import io.restassured.response.Response;
import methods.UserMethods;
import org.junit.After;
import pages.RegistrationPage;
import org.junit.Test;
import org.junit.Before;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import pojo.User;

import static constants.Url.URL_BURGERS;
import static org.hamcrest.Matchers.notNullValue;

public class RegistrationTest extends Browser {

    private String email = "ivanovanastia_6@gmail.com";
    private String password = "123456";
    private String passwordIncorrect = "123";
    private String name = "Настя";
    String accessToken;

    @Before
    public void openSite() {
        driver.get(URL_BURGERS);

        // Ожидание пока не появится хэдер
        LoginButtonsAtPage loginButtonsAtPage = new LoginButtonsAtPage(driver);
        loginButtonsAtPage.displayedHeader();
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
        UserMethods userMethods = new UserMethods();
        Response responseAccessToken = userMethods.loginUser(user);
        responseAccessToken.then().assertThat().body("accessToken", notNullValue())
                .and()
                .statusCode(200);
        this.accessToken = responseAccessToken.body().jsonPath().getString("accessToken");
        userMethods.deleteUser(accessToken);
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

