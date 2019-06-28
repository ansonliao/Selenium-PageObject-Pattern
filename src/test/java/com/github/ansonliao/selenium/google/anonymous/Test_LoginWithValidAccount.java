package com.github.ansonliao.selenium.google.anonymous;

import com.automation.remarks.video.annotations.Video;
import com.github.ansonliao.selenium.annotations.Description;
import com.github.ansonliao.selenium.annotations.URL;
import com.github.ansonliao.selenium.annotations.browser.Chrome;
import com.github.ansonliao.selenium.annotations.browser.Firefox;
import com.github.ansonliao.selenium.google.pages.LoginPage;
import com.github.ansonliao.selenium.google.pages.SecurePage;
import com.github.ansonliao.selenium.google.utils.CredentialUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.github.ansonliao.selenium.factory.WDManager.getDriver;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.testng.Assert.assertTrue;

/**
 * @author ansonliao
 */
@Chrome
@Description("Test login page function")
public class Test_LoginWithValidAccount {

    @Test(groups = {"@BVT"})
    @Firefox
    @URL("http://the-internet.herokuapp.com/login")
    @Description("Login with valid account information should be passed")
    @Video
    public void loginWithCorrectLoginInfo() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.inputUserName(CredentialUtils.getUserName());
        loginPage.inputPassword(CredentialUtils.getPassword());
        SecurePage securePage = loginPage.clickLoginButton();
        assertThat(securePage.getDriver().getTitle().toLowerCase())
                .contains("internet");
    }

    @Test(groups = {"@SMOKE"}, dataProvider = "test1")
    @URL("http://the-internet.herokuapp.com/login")
    @Description("Demo for fail test case")
    @Video
    public void loginWithWrongLoginInfo(String userName, String pwd) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.inputUserName(userName);
        loginPage.inputPassword(pwd);
        assertTrue(false);
    }

    @DataProvider(name = "test1")
    public Object[][] userName() {
        return new Object[][]{
                {"abc", "123"},
                {"efg", "456"}};
    }
}
