package com.github.ansonliao.selenium.google.pages;

import com.github.ansonliao.selenium.annotations.PageName;
import com.github.ansonliao.selenium.google.internal.CommonSeleniumActions;
import com.github.ansonliao.selenium.google.objects.SecurePO;
import com.github.ansonliao.selenium.google.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

@PageName("Secure Page")
public class SecurePage extends CommonSeleniumActions {
    public SecurePO po = new SecurePO();

    public SecurePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(
                new HtmlElementLocatorFactory(driver)), po);
        PageUtils.waitForPageLoaded(driver);
    }

    public LoginPage clickLogoutButton() {
        click(po.logoutBtn);
        return new LoginPage(getDriver());
    }
}
