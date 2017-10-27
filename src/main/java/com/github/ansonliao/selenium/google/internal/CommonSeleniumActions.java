package com.github.ansonliao.selenium.google.internal;

import com.aventstack.extentreports.Status;
import com.github.ansonliao.selenium.annotations.PageName;
import com.github.ansonliao.selenium.internal.interrupt.Sleep;
import com.github.ansonliao.selenium.report.factory.ExtentTestManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class CommonSeleniumActions {
    protected static Logger logger = Logger.getLogger(com.github.ansonliao.selenium.internal.CommonSeleniumAction.class);
    private WebDriver driver;
    private String pageName;
    private Actions action;
    private WebDriverWait wait;

    public CommonSeleniumActions(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElement(WebElement element) {
        wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(TypifiedElement element) {
        element.getWrappedElement().click();
        Sleep.byMillisecondWithNoLog(200);
        ExtentTestManager.getExtentTest().log(Status.INFO, String.format("%s: Click", this.generateExtentTestLogMsgPrefix(this.getPageName(), element.getName())));
    }

    public void type(TypifiedElement element, String value) {
        element.getWrappedElement().sendKeys(new CharSequence[]{value});
        Sleep.byMillisecondWithNoLog(200);
        ExtentTestManager.getExtentTest().log(Status.INFO, String.format("%s: Type text: %s", this.generateExtentTestLogMsgPrefix(this.getPageName(), element.getName()), value));
    }

    public void clearText(TypifiedElement element) {
        element.getWrappedElement().clear();
        ExtentTestManager.getExtentTest().log(Status.INFO, String.format("%s: Clear Text", this.generateExtentTestLogMsgPrefix(this.getPageName(), element.getName())));
    }

    public WebDriver openUrl(String url) {
        this.driver.get(url);
        ExtentTestManager.getExtentTest().log(Status.INFO, this.withBoldHTML("Test Start"));
        ExtentTestManager.getExtentTest().log(Status.INFO, this.withBoldHTML("Open URL: ") + url);
        return this.driver;
    }

    public String getPageName() {
        if (this.pageName == null && this.getClass().isAnnotationPresent(PageName.class)) {
            this.pageName = ((PageName)this.getClass().getAnnotation(PageName.class)).value().trim();
        }

        return this.pageName;
    }

    public Actions getAction() {
        if (this.action == null) {
            this.action = new Actions(this.driver);
        }

        return this.action;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public String withBoldHTML(String s) {
        return !s.trim().isEmpty() ? "<b>" + s + "</b>" : "";
    }

    public String generateExtentTestLogMsgPrefix(String pageName, String elementName) {
        if (pageName != null && !pageName.trim().isEmpty()) {
            String log = "[" + pageName.trim();
            if (elementName != null && !elementName.trim().isEmpty()) {
                log = log + " --> " + elementName + "] ";
                return log;
            } else {
                return "[Page: " + pageName.trim() + "] ";
            }
        } else {
            return elementName != null && !elementName.trim().isEmpty() ? "[Element: " + elementName + "] " : "";
        }
    }
}
