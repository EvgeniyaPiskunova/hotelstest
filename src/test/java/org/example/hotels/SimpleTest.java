package org.example.hotels;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.example.hotels.page.MainPage;
import org.example.hotels.testUtils.BaseTest;

import static org.testng.Assert.assertTrue;

public class SimpleTest extends BaseTest {
    @Owner("Zhenya")
    @Link(name = "link", url = "https://m.101hotels.com/main/cities/sankt-peterburg")
    @Description("filter")
    @Test(groups = {"prod", "stg", "qa"})
    public void clickIconFilter() {
        boolean product = new MainPage(getDriver())
                .clickFilters()
                .showOptions();
        assertTrue(product);
    }

    @Owner("Zhenya")
    @Link(name = "link", url = "https://m.101hotels.com/main/cities/sankt-peterburg")
    @Description("filter")
    @Test(groups = {"prod", "stg", "qa"})
    public void clickButtonFilter() {

        boolean showAmountFilters = new MainPage(getDriver())
                .clickFilters()
                .clickTakeOfFilters()
                .clickAllOptionsFilters(1)
                .findByNumber(1);
        assertTrue(showAmountFilters);
    }
}