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
public class PersonalAccount {
    private final WebDriver driver;
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    public PersonalAccount checkSwitchToAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));

        assertTrue(driver.findElement(exitButton).isDisplayed());
        return this;
    }

    public void exitFromAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));

        driver.findElement(exitButton).click();
    }
}
