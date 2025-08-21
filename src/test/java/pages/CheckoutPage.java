package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By TITLE = By.xpath("//*[text()='Checkout: Your Information']");

    public CheckoutPage(WebDriver driver) {

        super(driver);
    }

    public String getPageTitle() {
        return driver.findElement(TITLE).getText();
    }
}
