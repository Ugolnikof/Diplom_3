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
import pages.RecoveryPasswordPage;
import pages.RegisterPage;
import user.User;
import user.UserChecks;
import user.UserProperties;

public class AuthorizationUserTest {
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
        ValidatableResponse response = userProperties.createNewUser(user);
        token = User.getToken(response);
    }

    @Test
    @DisplayName("authorize User by button on mane page")
    public void authorizeUserByButtonOnManePage() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        mainPage.checkLoginSuccessfully();
    }

    @Test
    @DisplayName("authorize User from personal account")
    public void authorizeUserFromPersonalAccount() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        mainPage.checkLoginSuccessfully();
    }

    @Test
    @DisplayName("authorize User from register page")
    public void authorizeUserFromRegisterPage() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnLinkToAuthorize();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickOnLinkToEnterAccount();

        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        mainPage.checkLoginSuccessfully();
    }

    @Test
    @DisplayName("authorize User from password recovery page")
    public void authorizeUserFromPasswordRecoveryPage() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnLinkToRecoverPassword();

        RecoveryPasswordPage recoveryPasswordPage = new RecoveryPasswordPage(driver);
        recoveryPasswordPage.clickOnLinkToEnterAccount();

        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        mainPage.checkLoginSuccessfully();
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
