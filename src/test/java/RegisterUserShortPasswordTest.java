import driverrule.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import user.User;

@RunWith(Parameterized.class)
public class RegisterUserShortPasswordTest {
    User user;

    private final int passLength;

    public RegisterUserShortPasswordTest(int passLength) {
        this.passLength = passLength;
    }

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    @DisplayName("create new User")
    public void createUser() {
        user = User.getRandomUser();
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {4},
                {5}
        };
    }

    @Test
    @DisplayName("register User with short password")
    public void registerUserWithShortPassword() {
        // укорачиваю пароль
        String newPassword = user.getPassword().substring(0, passLength);
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

}
