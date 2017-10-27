package com.github.ansonliao.selenium.google.expectations;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;

public class Expectations {

    public static ExpectedCondition<Boolean> visibleOfElement(WebElement e) {
        return new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver webDriver) {
                try {
                    return Boolean.valueOf(e.isDisplayed());
                } catch (NoSuchElementException e1) {
                    return Boolean.FALSE;
                } catch (StaleElementReferenceException e2) {
                    return Boolean.FALSE;
                }
            }
        };
    }
}
