package org.example.hotels.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterPage extends BasePage {
    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='show-matched-text']")
    private WebElement showOptions;

    @FindBy(xpath = "//span[@class='checkbox__title']")
    private WebElement togle;

    @FindBy(xpath = "//button[text()='Снять фильтры']")
    private WebElement takeOfFilters;

    @FindBy(xpath = "//*[@id='view-filters']//span[text()='Назад']")
    private WebElement returnMainPage;

    public FilterPage clickTakeOfFilters() {

        if (takeOfFilters.isDisplayed()) {
            takeOfFilters.click();
        } else {
            returnMainPage.click();
        }
        return new FilterPage(getDriver());
    }

    public MainPage clickReturnMainPage() {
        returnMainPage.click();
        return new MainPage(getDriver());
    }

}
