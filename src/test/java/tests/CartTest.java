package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Random;

public class CartTest extends BaseTest {

    @Test
    public void checkCart() {
        /*
        a. Залогиниться
        b. Добавить товар в корзину
        c. Перейти в корзину
        d. Проверить (assertEquals) стоимость товара и его имя в корзине
         */
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://www.saucedemo.com/");
        //логинимся
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[value=Login]")).click();
        //проверяем, что мы залогинились
        String title = driver.findElement(By.xpath("//*[@id='header_container']" +
                "/div[2]/span")).getText();
        softAssert.assertEquals(title, "Products");
        //выбираем товар
        //запрашиваем используя List все товары
        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        // Выбрать рандомный товар
        Random rand = new Random();
        WebElement randomProduct = products.get(rand.nextInt(products.size()));

        // Запрашиваю название и цену выбранного товара, что бы потом сравнить то что отображается в корзине
        String productName = randomProduct.findElement(By.className("inventory_item_name")).getText();
        String productPrice = randomProduct.findElement(By.className("inventory_item_price")).getText();

        // Добавляю выбранный рандомный товар в корзину
        WebElement addButton = randomProduct.findElement(By.xpath("//*[text()='Add to cart']"));
        addButton.click();

        // Переходим в корзину и проверяю, что открылась страница именно корзины
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a")).click();
        String cart = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        softAssert.assertEquals(cart, "Your Cart");

        // Запрашиваю название и цену товара в корзине
        WebElement cartItem = driver.findElement(By.className("cart_item"));
        String cartProductName = cartItem.findElement(By.className("inventory_item_name")).getText();
        String cartProductPrice = cartItem.findElement(By.className("inventory_item_price")).getText();

        // сравниваю название и цену, ту которая в корзине и ту которую узнавала ранее
        softAssert.assertEquals("Название товара не совпадает!", productName, cartProductName);
        softAssert.assertEquals("Стоимость товара не совпадает!", productPrice, cartProductPrice);
        //softAssert.assertAll();
    }
}
