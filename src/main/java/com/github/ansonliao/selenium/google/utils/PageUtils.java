package com.github.ansonliao.selenium.google.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class PageUtils {

    public synchronized static void waitForPageLoaded(WebDriver driver) {
        FluentWait wait = new FluentWait(driver)
                .pollingEvery(Duration.ofMillis(500))
                .withTimeout(Duration.ofSeconds(10));
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete");
            }
        };

        wait.until(pageLoadCondition);
        // wait.until(d -> ((JavascriptExecutor) d).executeScript(js).equals(result));
    }

    public synchronized static boolean verifyTextPresented(
            WebDriver driver, String value) {
        return getPageSource(driver).contains(value);
    }

    public synchronized static String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public synchronized static String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }
}
