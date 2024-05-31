package pages;

import configure.EnvConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@Data
@AllArgsConstructor
public class LoginPage {
    private final WebDriver driver;
    private final By fieldEmail = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By linkToAuthorize = By.xpath(".//a[contains(@class,'Auth_link')]");
    private final By buttonEnter = By.xpath(".//button[text()='Войти']");

    public void clickOnLinkToAuthorize() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(fieldEmail));

        driver.findElement(linkToAuthorize).click();
    }

    public void checkRegisterSuccessfully() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonEnter));

        assertTrue(driver.findElement(buttonEnter).isDisplayed());
    }

    public void clickOnButtonEnter() {
        driver.findElement(buttonEnter).click();
    }
}
