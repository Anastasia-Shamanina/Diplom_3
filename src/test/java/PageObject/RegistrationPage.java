package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {  // конструктор класса
        this.driver = driver;
    }

    // Ссылка "Зарегистрироваться"
    private By registrationLink = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");

    @Step("Нажать на ссылку 'Зарегистрироваться'")
    public void clickRegistrationLink() {
        driver.findElement(registrationLink).click();
    }

    // Поле Имя
    private By fieldRegistrationName = By.xpath("//fieldset[1]/div/div/input");

    @Step("Ввести данны в поле 'Имя'")
    public void setRegistrationName(String registrationName) {
        driver.findElement(fieldRegistrationName).sendKeys(registrationName);
    }

    // Поле email
    private By fieldRegistrationEmail = By.xpath("//fieldset[2]/div/div/input");

    @Step("Ввести данны в поле 'Эмейл'")
    public void setRegistrationEmail(String registrationEmail) {
        driver.findElement(fieldRegistrationEmail).sendKeys(registrationEmail);
    }

    // Поле Пароль
    private By fieldRegistrationPassword = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");

    @Step("Ввести данны в поле 'Пароль'")
    public void setRegistrationPassword(String registrationPassword) {
        driver.findElement(fieldRegistrationPassword).sendKeys(registrationPassword);
    }

    // Кнопка "Зарегистрироваться"
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");

    @Step("Нажать на кнопку 'Зарегистрироваться'")
    public void clickRegistrationButton() {

        driver.findElement(registrationButton).click();
    }

    //Объединяем методы для регистрации аккаунта
    @Step("Регистрируемся")
    public void registration(String registrationName, String registrationEmail, String registrationPassword) {
        setRegistrationName(registrationName);
        setRegistrationEmail(registrationEmail);
        setRegistrationPassword(registrationPassword);
        clickRegistrationButton();
    }

    // Появление окна входа после регистрации
    private By loginWindow = By.className("Auth_login__3hAey");

    @Step("Проверяем появление окна входа")
    public void displayedLoginWindow() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(loginWindow));
    }

    // Ошибка регистрации при неверном пароле
    private By passwordError = By.xpath(".//p[@class='input__error text_type_main-default' and text()='Некорректный пароль']");

    @Step("Проверяем появление пуша об ошибке")
    public void displayedPasswordError() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(passwordError));
    }

    // Лого Stellar Burgers
    private By logo = By.className("AppHeader_header__logo__2D0X2");
    public void clickLogo() {
        driver.findElement(logo).click();
    }
}
