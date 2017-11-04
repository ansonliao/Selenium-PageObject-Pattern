package com.github.ansonliao.selenium.expectedcondition;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import javax.annotation.Nullable;

public class MyExpectedConditions {

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

    public static ExpectedCondition<Boolean> invisibleOfElement(WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    return Boolean.valueOf(!element.isDisplayed());
                } catch (NoSuchElementException var3) {
                    return Boolean.valueOf(true);
                } catch (StaleElementReferenceException var4) {
                    return Boolean.valueOf(true);
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibleOfElement(TypifiedElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    return Boolean.valueOf(!element.getWrappedElement().isDisplayed());
                } catch (NoSuchElementException var3) {
                    return Boolean.valueOf(true);
                } catch (StaleElementReferenceException var4) {
                    return Boolean.valueOf(true);
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> textPresented(String text) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getPageSource().contains(text);
            }
        };
    }
}
