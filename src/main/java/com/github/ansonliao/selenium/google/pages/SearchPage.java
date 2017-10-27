package com.github.ansonliao.selenium.google.pages;

import com.github.ansonliao.selenium.annotations.PageName;
import com.github.ansonliao.selenium.google.objects.SearchPO;
import com.github.ansonliao.selenium.internal.CommonSeleniumAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

@PageName("Search Home Page")
public class SearchPage extends CommonSeleniumAction {
    public SearchPO po = new SearchPO();

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(
                new HtmlElementLocatorFactory(driver)), po);
    }

    public SearchPage waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOf(po.searchInput));
        return this;
    }

    public SearchPage clearSearchInput() {
        clearText(po.searchInput);
        return this;
    }

    public SearchPage typeSearchString(String value) {
        type(po.searchInput, value);
        return this;
    }

    public SearchResultPage clickSearchButton() {
        click(po.searchBtn);
        return new SearchResultPage(getDriver()).waitForPageLoaded();
    }


}
