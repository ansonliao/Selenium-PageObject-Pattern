package com.github.ansonliao.selenium.google.pages;

import com.github.ansonliao.selenium.google.internal.CommonSeleniumActions;
import com.github.ansonliao.selenium.google.objects.LoginPO;
import com.github.ansonliao.selenium.google.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class LoginPage extends CommonSeleniumActions {
    LoginPO po = new LoginPO();

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(
                new HtmlElementLocatorFactory(driver)), po);

        PageUtils.waitForPageLoaded(driver);
    }

    public LoginPage inputUserName(String value) {
        clearUserName();
        type(po.userNameInput, value);
        return this;
    }

    public LoginPage clearUserName() {
        clearText(po.userNameInput);
        return this;
    }

    public LoginPage inputPassword(String value) {
        clearPassword();
        type(po.passwordInput, value);
        return this;
    }

    public LoginPage clearPassword() {
        clearText(po.passwordInput);
        return this;
    }

    public SecurePage clickLoginButton() {
        click(po.loginBtn);
        return new SecurePage(getDriver());
    }


}
