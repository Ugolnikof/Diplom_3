package pages;

import configure.EnvConfig;
import io.qameta.allure.Step;
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

    @Step("open browser")
    public MainPage open() {
        driver.get(EnvConfig.BASE_URL);
        return this;
    }

    @Step("click on button enter to account")
    public void clickOnButtonEnterToAccount() {
        driver.findElement(buttonEnterToAccount).click();
    }

    @Step("get access token from local storage")
    public String getAccessTokenFromLocalStorage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(headerCreateBurger));

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        return localStorage.getItem("accessToken");
    }

    @Step("check login successfully")
    public MainPage checkLoginSuccessfully() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));

        assertTrue(driver.findElement(createOrderButton).isDisplayed());
        return this;
    }

    @Step("click on personal account")
    public void clickOnPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("click on logo")
    public MainPage clickOnLogo() {
        driver.findElement(logo).click();
        return this;
    }

    @Step("check switch to main page")
    public void checkSwitchToMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(headerCreateBurger));

        assertTrue(driver.findElement(headerCreateBurger).isDisplayed());
    }

    @Step("click on constructor")
    public MainPage clickOnConstructor() {
        driver.findElement(constructor).click();
        return this;
    }

    @Step("click on sauce")
    public MainPage clickOnSauce() {
        driver.findElement(sauce).click();
        return this;
    }

    @Step("check sauce is selected")
    public MainPage checkSauceIsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(sauce));

        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                        .until(ExpectedConditions.attributeContains(sauce, "class", "current")));
        return this;
    }

    @Step("click on filling")
    public MainPage clickOnFilling() {
        driver.findElement(filling).click();
        return this;
    }

    @Step("check filling is selected")
    public void checkFillingIsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(filling));

        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(filling, "class", "current")));
    }
}
