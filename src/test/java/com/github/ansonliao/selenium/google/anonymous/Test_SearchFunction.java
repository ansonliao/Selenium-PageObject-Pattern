package com.github.ansonliao.selenium.google.anonymous;

import com.github.ansonliao.selenium.annotations.Description;
import com.github.ansonliao.selenium.annotations.URL;
import com.github.ansonliao.selenium.annotations.browser.Chrome;
import com.github.ansonliao.selenium.google.pages.SearchPage;
import com.github.ansonliao.selenium.google.pages.SearchResultPage;
import com.github.ansonliao.selenium.internal.UserBaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class Test_SearchFunction extends UserBaseTest {

    /**
     * @author ansonliao
     */
    @Test(groups = {"@Regression"})
    @Chrome
    @Description("Test valid searching string")
    @URL("https://www.google.com")
    public void searchWithValidInput() {
        openUrl(getUrl());
        new WebDriverWait(getDriver(), 10)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                        .equals("complete"));
        SearchPage searchPage = new SearchPage(getDriver()).waitForPageLoaded();
        searchPage.clearSearchInput();
        searchPage.typeSearchString("Selenium");
        searchPage.getAction().sendKeys(Keys.ESCAPE).perform();
        SearchResultPage resultPage = searchPage.clickSearchButton();
        assertThat(resultPage.getDriver().getTitle().toLowerCase(), containsString("selenium"));
    }
}
