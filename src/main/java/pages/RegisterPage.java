package pages;

import configure.EnvConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import user.User;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@Data
@AllArgsConstructor
public class RegisterPage {
    private final WebDriver driver;
    private final By fieldPassword = By.xpath(".//label[text()='Пароль']/parent::div/input");
    private final By fieldEmail = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By fieldName = By.xpath(".//label[text()='Имя']/parent::div/input");
    private final By buttonToRegister = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorMessageWrongPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private final By redBorderFieldEmail = By.xpath(".//div[contains(@class, 'input_status_error')]");
    private final By linkToEnterToAccount = By.xpath(".//a[text()='Войти']");

    public RegisterPage enterPassword(User user) {
        driver.findElement(fieldPassword).sendKeys(user.getPassword());
        return this;
    }

    public RegisterPage enterEmail(User user) {
        driver.findElement(fieldEmail).sendKeys(user.getEmail());
        return this;
    }

    public RegisterPage enterName(User user) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(fieldName));

        driver.findElement(fieldName).sendKeys(user.getName());
        return this;
    }

    public RegisterPage clickOnButtonToRegister() {
        driver.findElement(buttonToRegister).click();
        return this;
    }

    public void checkErrorInvalidPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessageWrongPassword));

        assertTrue(driver.findElement(errorMessageWrongPassword).isDisplayed());
        assertTrue(driver.findElement(redBorderFieldEmail).isDisplayed());
    }

    public void clickOnLinkToEnterAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(linkToEnterToAccount));

        driver.findElement(linkToEnterToAccount).click();
    }
}
