package com.github.ansonliao.selenium.google.pages;

import com.github.ansonliao.selenium.google.expectations.Expectations;
import com.github.ansonliao.selenium.google.objects.SearchResultPO;
import com.github.ansonliao.selenium.internal.CommonSeleniumAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class SearchResultPage extends CommonSeleniumAction {
    public SearchResultPO po = new SearchResultPO();

    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(
                new HtmlElementLocatorFactory(driver)), po);
    }

    public SearchResultPage waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOf(po.searchInput));
        return this;
    }
}
