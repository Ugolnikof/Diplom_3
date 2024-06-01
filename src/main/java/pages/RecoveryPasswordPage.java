package pages;

import configure.EnvConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Data
@AllArgsConstructor
public class RecoveryPasswordPage {
    private final WebDriver driver;
    private final By linkToEnterToAccount = By.xpath(".//a[text()='Войти']");

    public void clickOnLinkToEnterAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(linkToEnterToAccount));

        driver.findElement(linkToEnterToAccount).click();
    }
}
