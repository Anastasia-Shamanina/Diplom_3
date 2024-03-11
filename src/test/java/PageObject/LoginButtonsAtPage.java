package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

// Локаторы для кнопок входа на главной странице, странице входа, личного кабинета, в формах восстановления пароля и регистрации

public class LoginButtonsAtPage {
    private WebDriver driver;

    public LoginButtonsAtPage(WebDriver driver) {  // конструктор класса
        this.driver = driver;
    }

    public boolean isVisible = true;

    // Кнопка "Войти в аккаунт"
    private By loginInAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    @Step("Нажать на кнопку 'Войти в аккаунт'")
    public void clickLoginInAccountButton() {
        driver.findElement(loginInAccountButton).click();
    }

    // Кнопка "Личный кабинет"
    private By personalAccountButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");

    @Step("Нажать на кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    // Кнопка "Войти"
    private By loginButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']");

    @Step("Нажать на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Ссылка "Зарегистрироваться"
    private By registrationLink = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");

    @Step("Нажать на ссылку 'Зарегистрироваться'")
    public void clickRegistrationLink() {
        driver.findElement(registrationLink).click();
    }

    // Кнопка "Войти" в форме регистрации
    private By registrationLoginButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Войти']");

    @Step("Нажать на кнопку 'Войти' в форме регистрации")
    public void clickRegistrationLoginButton() {
        driver.findElement(registrationLoginButton).click();
    }

    // Кнопка "Восстановить пароль"
    private By restorePasswordButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");
    @Step("Нажать на кнопку 'Восстановить пароль'")
    public void clickRestorePasswordButton() {
        driver.findElement(restorePasswordButton).click();
    }

    // Кнопка "Войти" в форме восстановления пароля
    private By restoreLoginButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Войти']");

    @Step("Нажать на кнопку 'Войти' в форме восстановления пароля")
    public void clickRestoreLoginButton() {
        driver.findElement(restoreLoginButton).click();
    }

    // Поле ввода email
    private By fieldEmail = By.xpath("//fieldset[1]/div/div/input");

    @Step("Ввести эмейл")
    public void setEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    // Поле ввода пароля
    private By fieldPassword = By.xpath("//fieldset[2]/div/div/input");

    @Step("Ввести пароль")
    public void setPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    //Объединяем методы для входа в аккаунт
    @Step("Вход в аккаунт")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    // Отображается кнопка "Оформить заказ"
    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    @Step("Извлекаем текст из элемента 'Оформить заказ'")
    public String displayedCreateOrderButton() {
        return driver.findElement(createOrderButton).getText();
    }

    // Форма входа
    private By loginForm = By.xpath(".//h2[text()='Вход']");

    @Step("Проверяем, что появилась форма входа")
    public void displayedLoginForm() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(loginForm));
    }
}
