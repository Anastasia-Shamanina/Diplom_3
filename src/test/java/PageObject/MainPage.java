package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver) {  // конструктор класса
        this.driver = driver;
    }

    public boolean isVisible = true;

    // Кнопка "Личный кабинет"
    private By personalAccountButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    };

    // Кнопка "Конструктор"
    private By constructorButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']");
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    };

    // Отображается текст формы "Соберите бургер"
    private By textConstructorSection = By.xpath(".//h1[text()='Соберите бургер']");
    private boolean isDisplayed (By textConstructorSection) {
        return true;
    }
    public void displayedTextConstructorSection() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textConstructorSection));
        assertEquals(isVisible, isDisplayed(textConstructorSection));
    }

    //Раздел "Булки"
    private By bunSectionButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Булки']");
    public void clickBunSectionButton() {
        driver.findElement(bunSectionButton).click();
    };

    // Отображается текст раздела "Булки"
    private By textBunSection = By.xpath(".//h2[text()='Булки']");
    private boolean isDisplayed3 (By textBunSection) {
        return true;
    }
    public void displayedTextBunSection() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textBunSection));
        assertEquals(isVisible, isDisplayed3(textBunSection));
    }

    //Раздел "Соусы"
    private By saucesSectionButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Соусы']");
    public void clickSaucesSectionButton() {
        driver.findElement(saucesSectionButton).click();
    };

    // Отображается текст раздела "Соусы"
    private By textSaucesSection = By.xpath(".//h2[text()='Соусы']");
    private boolean isDisplayed2 (By textSaucesSection) {
        return true;
    }
    public void displayedTextSaucesSection() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textSaucesSection));
        assertEquals(isVisible, isDisplayed2(textSaucesSection));
    }

    //Раздел "Начинки"
    private By fillingSectionButton = By.xpath(".//span[@class='text text_type_main-default' and text()='Начинки']");
    public void clickFillinпSectionButton() {
        driver.findElement(fillingSectionButton).click();
    };

    // Отображается текст раздела "Начинки"
    private By textFillingSection = By.xpath(".//h2[text()='Начинки']");
    private boolean isDisplayed1(By textFillingSection) {
        return true;
    }
    public void displayedTextFillingSection() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(textFillingSection));
        assertEquals(isVisible, isDisplayed1(textFillingSection));
    }

    // Лого Stellar Burgers
    private By logo = By.xpath("//div/a[@href='/']");
    public void clickLogo() {
        driver.findElement(logo).click();
    }
}
