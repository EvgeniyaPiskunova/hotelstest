package org.example.hotels.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"fast_filters\"]/div[2]/div[1]")
    private WebElement filter;

    @FindBy(xpath = "//div[@class='widget-button-title']")
    private WebElement buttonsMenu;
    @FindBy(xpath = "//span[@class='show-matched-text']")
    private WebElement showOptions;

    @FindBy(xpath = "//button[text()='Снять фильтры']")
    private WebElement takeOfFilters;

    @FindBy(css = "[class='icon icon-big-arrow-left']")
    private WebElement arrowLeft;

    @FindBy(xpath = "//*[@id='view-filters']//span[text()='Назад']")
    private WebElement returnMainPage;

    public MainPage clickFilters() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(filter));
        element.click();
        return new MainPage(getDriver());
    }

    public MainPage clickAllOptionsFilters(int numberOfListElements) {
        List<WebElement> elementList = getDriver().findElements(By.cssSelector("[class='widget-button-container ui-widget search-widget fast-filter']"));
        for (int i = 0; i < numberOfListElements; i++) {
            elementList.get(i).click();
        }
        return new MainPage(getDriver());
    }

    public boolean showOptions() {
        return showOptions.isDisplayed();
    }

    public MainPage clickTakeOfFilters() {

        if (takeOfFilters.isDisplayed()) {
            takeOfFilters.click();
        } else {
            clickReturnMainPage();
        }
        return new MainPage(getDriver());
    }

    public MainPage clickReturnMainPage() {
        returnMainPage.click();
        return new MainPage(getDriver());
    }

    public MainPage clickArrowLeft() {
        arrowLeft.click();
        return new MainPage(getDriver());
    }

    private static String getXpath(int n) {
        return "//*[@id='fast_filters']/div[2]/div[1]/div/div/div[2][text()='" + n + "']";
    }

    public boolean findByNumber(int n) {
        waitElementVisible(getDriver().findElement(By.xpath(getXpath(n))));
        return getDriver().findElement(By.xpath(getXpath(n))).isEnabled();
    }

}
