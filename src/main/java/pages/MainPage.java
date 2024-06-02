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
    private final By logo = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]");
    private final By constructor = By.xpath(".//p[contains(@class, 'AppHeader_header__linkText')]");
    private final By sauce = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By filling = By.xpath(".//span[text()='Начинки']/parent::div");

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

    public MainPage checkLoginSuccessfully() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));

        assertTrue(driver.findElement(createOrderButton).isDisplayed());
        return this;
    }

    public void clickOnPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }

    public MainPage clickOnLogo() {
        driver.findElement(logo).click();
        return this;
    }

    public void checkSwitchToMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(headerCreateBurger));

        assertTrue(driver.findElement(headerCreateBurger).isDisplayed());
    }

    public MainPage clickOnConstructor() {
        driver.findElement(constructor).click();
        return this;
    }

    public MainPage clickOnSauce() {
        driver.findElement(sauce).click();
        return this;
    }

    public MainPage checkSauceIsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(sauce));

        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                        .until(ExpectedConditions.attributeContains(sauce, "class", "current")));

        return this;
    }

    public MainPage clickOnFilling() {
        driver.findElement(filling).click();
        return this;
    }

    public void checkFillingIsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(filling));

        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(filling, "class", "current")));
    }
}
