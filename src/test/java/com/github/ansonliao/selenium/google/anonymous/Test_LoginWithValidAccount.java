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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@Chrome
public class Test_LoginWithValidAccount extends UserBaseTest {

    /**
     * @author ansonliao
     */
    @Test
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
}
