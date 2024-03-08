package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class PersonalAccount {
    private WebDriver driver;
    public boolean isVisible = true;

    public PersonalAccount(WebDriver driver) {  // конструктор класса
        this.driver = driver;
    }

    // Кнопка "Выход"
    private By exitButton = By.xpath("//*[text()='Выход']");

    public void clickExitButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton)).click();

    } //после выхода отображается форма входа


    // Раздел "Профиль"
    private By profileSection = By.xpath(".//a[text()='Профиль']");
    private boolean isDisplayed(By profileSection) {
        return true;
    }
    public void displayedProfileSection() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(profileSection));
        assertEquals(isVisible, isDisplayed(profileSection));
    }
}
