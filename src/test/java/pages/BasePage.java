package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public final String BASE_URL = "https://www.saucedemo.com/";
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /*JavascriptExecutor используется:
   -когда нужен скроллинг
   -в какие-то поля перекрытые например банером нужно ввести
   -нажать на кнопку которая перекрыта банером
    */
    public void clickJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;//обьявили джава executor
        js.executeScript("argument[0].click();", element);
    }
    //можно еще закинуть скроллинг

    //выполняет команду, которая позволяет понять что страница прогружена(дожидается полной загрузки страницы)
    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
    }

    public void waitElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
