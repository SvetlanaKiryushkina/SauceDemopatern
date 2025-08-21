package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By BUTTON_CHECKOUT = By.xpath("//*[@id='checkout']");
    private final String NAME_PRODUCT = "//*[@class='cart_item']//*[text()='%s']";
    private final By BUTTON_CONTINUE = By.xpath("//*[@id='continue-shopping']");
    private final By BUTTON_REMOVE = By.xpath("//*[@class='cart_item' and .//*[text()='%s']]//" +
            "button[contains(text(),'Remove')]");
    private final By PRODUCT_ITEMS = By.className("cart_item");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {//переход на страницу корзины
        driver.get(BASE_URL + "cart.html");
    }

    @Step("Нажатие на кнопку Continue Shopping")
    public void clickContinue() {//нажать на кнопку "Вернуться к товарам"
        driver.findElement(BUTTON_CONTINUE).click();
    }

    @Step("Нажатие на кнопку Checkout")
    public void clickCheckout() {
        driver.findElement(BUTTON_CHECKOUT).click();
    }

    @Step("Удалить товар из корзины, нажав на кнопку Remove")
    public void clickRemove(String productName) {//кликнуть на кнопку remove
        driver.findElement(BUTTON_REMOVE).click();
    }

    //Первый метод проверки наличия товара в корзине
    public boolean isProductInCart(String product) {
        return driver.findElement(By.xpath(String.format(NAME_PRODUCT, product))).isDisplayed();
    }

    //Второй метод проверки наличия товара в корзине при помощи индекса
    @Step("Проверяем, что товар находится в корзине")
    public String getProductNameFromCart(int index) {//получаем имя товара
        return driver.findElements(By.cssSelector(".inventory_item_name"))
                .get(index)
                .getText();
    }

    //Третий альтернативный метод проверки наличия товара в корзине
    public ArrayList<String> getProductsName() {
        List<WebElement> allProductsElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProductsElements) {
            names.add(product.getText());
        }
        return names;//перечень всех имен находящихся в коллекции
    }

    @Step("Получить количество товаров в корзине")
    public int getItemsCount() {
        return driver.findElements(PRODUCT_ITEMS).size();
    }
}
