import configure.EnvConfig;
import driverRule.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import user.User;
import user.UserChecks;
import user.UserProperties;

import java.time.Duration;

import static org.junit.Assert.*;

public class RegisterUserTest {
    String token;
    User user;
    private final UserProperties userProperties = new UserProperties();
    private final UserChecks userChecks = new UserChecks();

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    @DisplayName("create new User")
    public void createUser() {
        user = User.getRandomUser();
    }

    @Test
    public void registerUser() {
        WebDriver driver = driverRule.getDriver();
        // Создание пользователя
        driver.get(EnvConfig.BASE_URL);

        driver.findElement(By.xpath(".//button[text()='Войти в аккаунт']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//label[text()='Email']/parent::div/input")));

        driver.findElement(By.xpath(".//a[contains(@class,'Auth_link')]")).click();

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//label[text()='Имя']/parent::div/input")));

        // ввести Имя
        driver.findElements(By.xpath(".//label[text()='Имя']/parent::div/input")).get(0).sendKeys(user.getName());

        // ввести Email
        driver.findElement(By.xpath(".//label[text()='Email']/parent::div/input")).sendKeys(user.getEmail());

        // ввести Пароль
        driver.findElement(By.xpath(".//label[text()='Пароль']/parent::div/input")).sendKeys(user.getPassword());

        driver.findElement(By.xpath(".//button[text()='Зарегистрироваться']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Войти']")));

        assertTrue(driver.findElement(By.xpath(".//button[text()='Войти']")).isDisplayed());


        // Логин пользователя
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Войти']")));
        // ввести Email
        driver.findElement(By.xpath(".//label[text()='Email']/parent::div/input")).sendKeys(user.getEmail());

        // ввести Пароль
        driver.findElement(By.xpath(".//label[text()='Пароль']/parent::div/input")).sendKeys(user.getPassword());

        driver.findElement(By.xpath(".//button[text()='Войти']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//h1[text()='Соберите бургер']")));

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        token = localStorage.getItem("accessToken");
    }

    @After
    @DisplayName("delete User")
    public void deleteUser() {
        if (token != null) {
            ValidatableResponse response = userProperties.deleteExistingUser(token);
            userChecks.deleteSuccessfully(response);
        }
    }

}
