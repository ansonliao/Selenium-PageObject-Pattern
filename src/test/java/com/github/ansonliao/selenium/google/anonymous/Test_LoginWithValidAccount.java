package com.github.ansonliao.selenium.google.anonymous;

import com.github.ansonliao.selenium.annotations.Description;
import com.github.ansonliao.selenium.annotations.URL;
import com.github.ansonliao.selenium.annotations.browser.Chrome;
import com.github.ansonliao.selenium.annotations.browser.Firefox;
import com.github.ansonliao.selenium.google.pages.LoginPage;
import com.github.ansonliao.selenium.google.pages.SecurePage;
import com.github.ansonliao.selenium.google.utils.CredentialUtils;
import com.github.ansonliao.selenium.internal.UserBaseTest;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

/**
 * @author ansonliao
 */
@Chrome
public class Test_LoginWithValidAccount extends UserBaseTest {

    @Test(groups = {"@BVT"})
    @Firefox
    @Description("Login with valid account information should be passed")
    @URL("http://the-internet.herokuapp.com/login")
    public void f1() {
        openUrl(getUrl());
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.inputUserName(CredentialUtils.getUserName());
        loginPage.inputPassword(CredentialUtils.getPassword());
        SecurePage securePage = loginPage.clickLoginButton();
        assertThat(securePage.getDriver().getTitle().toLowerCase(),
                containsString("internet"));
    }

    @Test(groups = {"@SMOKE"})
    @Description("Demo for fail test case")
    @URL("http://the-internet.herokuapp.com/login")
    public void f2() {
        LoginPage loginPage = new LoginPage(getDriver());
        assertTrue(false);
    }
}
