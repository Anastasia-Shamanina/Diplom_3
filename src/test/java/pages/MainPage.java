package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {  // конструктор класса
        this.driver = driver;
    }

    public boolean isVisible = true;

    // Кнопка "Личный кабинет"
    private By personalAccountButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");

    @Step("Нажимаем на кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    // Кнопка "Конструктор"
    private By constructorButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']");

    @Step("Нажимаем на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    // Отображается текст формы "Соберите бургер"
    private By textConstructorSection = By.xpath(".//h1[text()='Соберите бургер']");

    @Step("Извлекаем текст из элемента 'Соберите бургер'")
    public String displayedTextConstructorSection() {
        return driver.findElement(textConstructorSection).getText();
    }


    //Раздел "Булки"
    private By bunSectionButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Булки']");

    @Step("Извлекаем текст из элемента 'Булки'")
    public void clickBunSectionButton() {
        driver.findElement(bunSectionButton).click();
    }

    //Раздел "Соусы"
    private By saucesSectionButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Соусы']");

    @Step("Извлекаем текст из элемента 'Соусы'")
    public void clickSaucesSectionButton() {
        driver.findElement(saucesSectionButton).click();
    }

    //Раздел "Начинки"
    private By fillingSectionButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Начинки']");

    @Step("Извлекаем текст из элемента 'Начинки'")
    public void clickFillinпSectionButton() {
        driver.findElement(fillingSectionButton).click();
    }

    // Получить текст текущего раздела
    @Step("Извлекаем текст из активного раздела в конструкторе")
    public String getTextSection() {
        return driver.findElement(currentSection).getText();
    }

    private By currentSection = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");

    // Лого Stellar Burgers
    private By logo = By.xpath("//div/a[@href='/']");

    @Step("Нажимаем на 'Лого'")
    public void clickLogo() {
        driver.findElement(logo).click();
    }
}
