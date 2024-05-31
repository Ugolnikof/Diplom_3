import driverRule.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import user.User;

public class RegisterUserWithShortPasswordTest {
    User user;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    @DisplayName("create new User")
    public void createUser() {
        user = User.getRandomUserWithShortPassword();
    }

    @Test
    public void registerUserWithShortPassword() {
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

}
