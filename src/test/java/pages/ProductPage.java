package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    private final By TITLE = By.className("title");
    private final String ADD_TO_CART_PATTERN = "//*[text() ='%s']/ancestor::div[@class='inventory_item']" +
            "//button[text()='Add to cart']";

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void open() {
        driver.get(BASE_URL + "inventory.html");//переход на страницу товаров
    }

    @Step("Добавление товара с именем: '{product}' в корзину")
    public void addToCart(String product) {//добавление товара в корзину
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
    }

    @Step("Нажатие на кнопку корзина")
    public void clickToCart() {//клик по иконке корзины
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[]"))); //нужная вещь.
        // Т.е ожидаем, что нужная страница открылась
    }
}
