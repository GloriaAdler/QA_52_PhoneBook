package phoneBook;

import phoneBook.data.UserData;
import phoneBook.model.User;
import phoneBook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {


    @BeforeMethod
    public void preCondition() {
        if (app.getUserHelper().isSignOutButtonPresent()) {
            logger.info("User already logged in. Sign out...");
            app.getUserHelper().clickOnSignOutButton();
        } else {
            logger.info("LOGIN link is present. No need to Sign Out");
        }
    }

    @Test(invocationCount = 1)
    public void loginExistedUserPositiveTest() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail("gloria2025@gmail.com");
        app.getUserHelper().typePassword("Password@1");
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin();
    }
    @Test(invocationCount = 1)
    public void loginExistedUserPositiveTest3() {
        app.getUserHelper().clickOnLoginLink()
                .typeEmail("gloria2025@gmail.com")
                .typePassword("Password@1")
                .clickOnLoginButton()
                .checkLogin();
    }

    @Test
    public void loginExistedDataUserPositiveTest() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail(UserData.VALID_EMAIL);
        app.getUserHelper().typePassword(UserData.VALID_PASSWORD);
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin();
    }

    @Test
    public void loginWOEmailNegativeTest() {
        app.getUserHelper().clickOnLoginLink();
        // fillInLoginForm("Password@1");
        app.getUserHelper().fillInLoginForm(new User()
                //.setEmail("gloria2025@gmail.com")
                .setPassword(UserData.VALID_PASSWORD));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getUserHelper().isAlertPresent());
    }

    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviders.class)
    public void loginExistedUserDataProviderPositiveTest(String email, String password) {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail(email);
        app.getUserHelper().typePassword(password);
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin();
    }

    @AfterMethod
    public void postCondition() {
        //app.getUserHelper().clickOnSignOutButton();
    }
}
