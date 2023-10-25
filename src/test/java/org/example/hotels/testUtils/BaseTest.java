package org.example.hotels.testUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import test.listeners.BaseListener;

import java.io.IOException;
import java.io.InputStream;

@Listeners(BaseListener.class)
abstract public class BaseTest {

    private WebDriver driver = null;
    public static final Boolean CLEAR_COOKIES_AND_STORAGE = Boolean.parseBoolean(
            System.getProperty("clearCookies", "true")
    );
    public static final String browserName = System.getProperty("browser", "chrome");
    private final String env = System.getProperty("env", "qa");

    @BeforeMethod(alwaysRun = true)
    public void setupSuite() throws IOException {
        readConfig();
        setUpBrowser();

        driver.get(System.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void testDown() {
        clearCookiesAndLocalStorage();
        driver.close();
        driver.quit();
    }

    private void readConfig() throws IOException {
        String fileName = env + ".properties";
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
        System.getProperties()
                .load(inputStream);
    }

    public void setUpBrowser() {
        System.out.println("Browser Name is: " + browserName);
        if (browserName.equalsIgnoreCase("chrome")) {
            setupClass();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            throw new IllegalStateException("No browser name supplied");
        }
    }

    public WebDriver getDriver() {//чтоб никто не переписывал браузер
        return driver;
    }

    public String getEnvironment() {
        return env;
    }

    public void clearCookiesAndLocalStorage() {
        if (CLEAR_COOKIES_AND_STORAGE) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

}