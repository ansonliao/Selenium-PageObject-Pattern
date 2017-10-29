package com.github.ansonliao.selenium.google.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class PageUtils {

    public synchronized static void waitForPageLoaded(WebDriver driver) {
        FluentWait wait = new FluentWait(driver)
                .withTimeout(5, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS);

        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState")
                .equals("complete"));

    }
}
