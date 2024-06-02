import driverRule.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccount;
import user.User;
import user.UserChecks;
import user.UserProperties;

public class NavigationTest {
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
    @DisplayName("go to personal account")
    public void goToPersonalAccount() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        mainPage.checkLoginSuccessfully()
                .clickOnPersonalAccount();

        PersonalAccount personalAccount = new PersonalAccount(driver);
        personalAccount.checkSwitchToAccount();
    }

    @Test
    @DisplayName("go to main page by click on logo")
    public void goToMainPageByClickLogo() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        mainPage.checkLoginSuccessfully()
                .clickOnPersonalAccount();

        PersonalAccount personalAccount = new PersonalAccount(driver);
        personalAccount.checkSwitchToAccount();

        mainPage.clickOnLogo().
                checkSwitchToMainPage();
    }

    @Test
    @DisplayName("go to main page by click on constructor")
    public void goToMainPageByClickConstructor() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        mainPage.checkLoginSuccessfully()
                .clickOnPersonalAccount();

        PersonalAccount personalAccount = new PersonalAccount(driver);
        personalAccount.checkSwitchToAccount();

        mainPage.clickOnConstructor()
                .checkSwitchToMainPage();
    }

    @Test
    @DisplayName("exit from account")
    public void exitFromAccount() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnButtonEnterToAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(user)
                .enterPassword(user)
                .clickOnButtonEnter();

        mainPage.checkLoginSuccessfully()
                .clickOnPersonalAccount();

        PersonalAccount personalAccount = new PersonalAccount(driver);
        personalAccount.checkSwitchToAccount()
                .exitFromAccount();

        loginPage.checkLogoutSuccessfully();
    }

    @Test
    @DisplayName("switching between burger sections")
    public void switchingBetweenBurgerSections() {
        WebDriver driver = driverRule.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnSauce()
                .checkSauceIsSelected()
                .clickOnFilling()
                .checkFillingIsSelected();
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
