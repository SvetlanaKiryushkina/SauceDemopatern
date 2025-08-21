package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;

public class CartTestNew extends BaseTest {

    @Test(testName = "Проверка, что товар находится в корзине")
    @Description("Проверка, что товар находится в корзине")
    @Owner("Светлана")
    @Feature("Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Тимофей")
    public void checkCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productPage.addToCart("Sauce Labs Backpack");
        productPage.clickToCart();
        CartPage cartPage = new CartPage(driver);
        String productName = cartPage.getProductNameFromCart(0);
        Assert.assertEquals(productName,
                "Sauce Labs Backpack",
                "Товар отсутствует в корзине");
    }

    @Test(testName = "Проверка перехода на страницу Checkout")
    @Description("Проверка, что товар находится в корзине")
    @Owner("Светлана")
    @Feature("Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Тимофей")
    public void checkClickCheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productPage.addToCart("Sauce Labs Backpack");
        productPage.clickToCart();
        cartPage.clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertEquals(checkoutPage.getPageTitle(), "Checkout: Your Information",
                "Пользователь не перешёл на страницу Checkout");
    }

    @Test(testName = "Проверка количества товаров в корзине после добавления")
    @Description("Проверка количества товаров в корзине после добавления")
    @Owner("Светлана")
    @Feature("Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Тимофей")
    public void checkClickRemove() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productPage.addToCart("Sauce Labs Backpack");
        productPage.addToCart("Sauce Labs Bike Light");
        productPage.clickToCart();
        Assert.assertEquals(cartPage.getItemsCount(), 2, "Количество не соответствует");
    }
}
