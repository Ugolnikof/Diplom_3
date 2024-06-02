package pages;

import configure.EnvConfig;
import io.qameta.allure.Step;
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
public class LoginPage {
    private final WebDriver driver;
    private final By fieldEmail = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By fieldPassword = By.xpath(".//label[text()='Пароль']/parent::div/input");
    private final By linkToAuthorize = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By buttonEnter = By.xpath(".//button[text()='Войти']");
    private final By linkToRecoveryPassword = By.xpath(".//a[text()='Восстановить пароль']");

    @Step("click on link to authorize")
    public void clickOnLinkToAuthorize() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmail));

        driver.findElement(linkToAuthorize).click();
    }

    @Step("check register successfully")
    public void checkRegisterSuccessfully() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonEnter));

        assertTrue(driver.findElement(buttonEnter).isDisplayed());
    }

    @Step("click on button enter")
    public void clickOnButtonEnter() {
        driver.findElement(buttonEnter).click();
    }

    @Step("enter password")
    public LoginPage enterPassword(User user) {
        driver.findElement(fieldPassword).sendKeys(user.getPassword());
        return this;
    }

    @Step("enter email")
    public LoginPage enterEmail(User user) {
        driver.findElement(fieldEmail).sendKeys(user.getEmail());
        return this;
    }

    @Step("click on link to recover password")
    public void clickOnLinkToRecoverPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(linkToRecoveryPassword));

        driver.findElement(linkToRecoveryPassword).click();
    }

    @Step("check logout successfully")
    public void checkLogoutSuccessfully() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonEnter));

        assertTrue(driver.findElement(buttonEnter).isDisplayed());
    }
}
