package com.github.ansonliao.selenium.google.internal;

import com.aventstack.extentreports.Status;
import com.github.ansonliao.selenium.annotations.PageName;
import com.github.ansonliao.selenium.google.Interrupt.Pause;
import com.github.ansonliao.selenium.report.factory.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class CommonSeleniumActions {
    protected static Logger logger = LoggerFactory.getLogger(CommonSeleniumActions.class);
    private WebDriver driver;
    private String pageName;
    private Actions action;
    private Robot robot;
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
        Pause.byMillisecondWithNoLog(200);
        ExtentTestManager.getExtentTest().log(
                Status.INFO,
                String.format(
                        "%s: Click",
                        this.generateExtentTestLogMsgPrefix(
                                this.getPageName(), element.getName())));
    }

    public void type(TypifiedElement element, String value) {
        element.getWrappedElement().sendKeys(new CharSequence[]{value});
        Pause.byMillisecondWithNoLog(200);
        ExtentTestManager.getExtentTest().log(
                Status.INFO,
                String.format(
                        "%s: Type text: %s",
                        this.generateExtentTestLogMsgPrefix(this.getPageName(),
                                element.getName()), value));
    }

    public void clearText(TypifiedElement element) {
        element.getWrappedElement().clear();
        ExtentTestManager.getExtentTest().log(
                Status.INFO,
                String.format(
                        "%s: Clear Text",
                        this.generateExtentTestLogMsgPrefix(
                                this.getPageName(), element.getName())));
    }

    public void pressESC() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        ExtentTestManager.getExtentTest().log(
                Status.INFO,
                String.format("Press ESC key."));
    }

    public WebDriver openUrl(String url) {
        this.driver.get(url);
        ExtentTestManager.getExtentTest()
                .log(Status.INFO, this.withBoldHTML("Test Start"));
        ExtentTestManager.getExtentTest()
                .log(Status.INFO, this.withBoldHTML("Open URL: ") + url);
        return this.driver;
    }

    public String getPageName() {
        if (this.pageName == null
                && this.getClass().isAnnotationPresent(PageName.class)) {
            this.pageName = ((PageName) this.getClass().getAnnotation(PageName.class))
                    .value().trim();
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
            return elementName != null && !elementName.trim().isEmpty()
                    ? "[Element: " + elementName + "] "
                    : "";
        }
    }
}
