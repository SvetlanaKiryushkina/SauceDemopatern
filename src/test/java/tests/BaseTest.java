package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.ProductPage;

import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductPage productPage;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        //options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
