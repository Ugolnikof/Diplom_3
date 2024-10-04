import driverrule.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import user.User;
import user.UserChecks;
import user.UserProperties;

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
    @DisplayName("register User")
    public void registerUser() {
        WebDriver driver = driverRule.getDriver();

        // Создание пользователя
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnLinkToAuthorize();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterName(user)
                .enterEmail(user)
                .enterPassword(user)
                .clickOnButtonToRegister();

        loginPage.checkRegisterSuccessfully();


        // Логин пользователя
        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        token = mainPage.getAccessTokenFromLocalStorage();
    }

    @Test
    @DisplayName("register User with short password")
    public void registerUserWithShortPassword() {
        // укорачиваю пароль
        String newPassword = user.getPassword().substring(0, 5);
        user.setPassword(newPassword);

        WebDriver driver = driverRule.getDriver();

        // Создание пользователя
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnLinkToAuthorize();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterName(user)
                .enterEmail(user)
                .enterPassword(user)
                .clickOnButtonToRegister()
                .checkErrorInvalidPassword();
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
