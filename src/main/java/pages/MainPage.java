package pages;

import configure.EnvConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@Data
@AllArgsConstructor
public class MainPage {
    private final WebDriver driver;
    private final By buttonEnterToAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By headerCreateBurger = By.xpath(".//h1[text()='Соберите бургер']");
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']/parent::a");

    public MainPage open() {
        driver.get(EnvConfig.BASE_URL);

        return this;
    }

    public void clickOnButtonEnterToAccount() {
        driver.findElement(buttonEnterToAccount).click();
    }

    public String getAccessTokenFromLocalStorage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(headerCreateBurger));

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        return localStorage.getItem("accessToken");
    }

    public void checkLoginSuccessfully() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));

        assertTrue(driver.findElement(createOrderButton).isDisplayed());

    }

    public void clickOnPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }
}
