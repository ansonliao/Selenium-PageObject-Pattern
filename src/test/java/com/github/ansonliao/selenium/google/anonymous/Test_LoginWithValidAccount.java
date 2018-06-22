package com.github.ansonliao.selenium.google.anonymous;

import com.github.ansonliao.selenium.annotations.Description;
import com.github.ansonliao.selenium.annotations.URL;
import com.github.ansonliao.selenium.annotations.browser.Chrome;
import com.github.ansonliao.selenium.annotations.browser.Firefox;
import com.github.ansonliao.selenium.annotations.browser.PhantomJs;
import com.github.ansonliao.selenium.google.pages.LoginPage;
import com.github.ansonliao.selenium.google.pages.SecurePage;
import com.github.ansonliao.selenium.google.utils.CredentialUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.github.ansonliao.selenium.factory.WDManager.getDriver;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

/**
 * @author ansonliao
 */
@Chrome
public class Test_LoginWithValidAccount {
    private static final String REMOTE_URL = "http://the-internet.herokuapp.com/login";

    @Test(groups = {"@BVT"})
    @Firefox
    @Description("Login with valid account information should be passed")
    public void f1() {
        getDriver().get(REMOTE_URL);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.inputUserName(CredentialUtils.getUserName());
        loginPage.inputPassword(CredentialUtils.getPassword());
        SecurePage securePage = loginPage.clickLoginButton();
        assertThat(securePage.getDriver().getTitle().toLowerCase(),
                containsString("internet"));
    }

    @Test(groups = {"@SMOKE"}, dataProvider = "test1")
    @PhantomJs
    @Description("Demo for fail test case")
    public void f2(String userName, String pwd) {
        getDriver().get(REMOTE_URL);
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
