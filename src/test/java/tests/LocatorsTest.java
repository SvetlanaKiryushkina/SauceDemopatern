package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorsTest extends BaseTest {

    @Test
    public void checkLocators() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name"));
        driver.findElement(By.name("password"));
        driver.findElement(By.className("error-message-container"));
        driver.findElement(By.tagName("div"));
        //логинимся
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[value=Login]")).click();
        //поиск по тексту
        driver.findElement(By.linkText("Sauce Labs Bolt T-Shirt"));//linktext - ищет только в теге <a>
        driver.findElement(By.partialLinkText("Labs"));//partiallinktext - частичный текст только в теге <a>
        //XPath
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));//поиск по атрибуту
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));//по тексту
        driver.findElement(By.xpath("//div[contains(text(),'Sauce')]"));//по части текста
        driver.findElement(By.xpath("//img[contains(@src,'static')]"));//по части атрибуту
        driver.findElement(By.xpath("//button[text()='Add to cart' and @id='add-to-cart-sauce-labs-onesie']"));
        //оси
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']/parent::div"));//родитель
        driver.findElement(By.xpath("//*[text()='Sauce Labs Backpack']//ancestor::div"));//поднимается
        // к любому потомку
        driver.findElement(By.xpath("//div[@class='inventory_list']//descendant::div"));//обращается/спускается
        //к любому потомку на любом уровне
        //все элементы которые следуют
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']//following::button[1]"));
        //css
        driver.findElement(By.cssSelector(".primary_header"));//пишется c . т.к это класс
        driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));//если класс в несколько
        // слов, то между словами указывается .
        driver.findElement(By.cssSelector(".pricebar .inventory_item_price"));//вложенный класс тоже
        // указывается через точку, но обязательно пробел
        driver.findElement(By.cssSelector("#item_2_title_link"));
        driver.findElement(By.cssSelector("noscript"));//обращение по тегу. Указываем только название тега
        driver.findElement(By.cssSelector("div.inventory_item_label"));//обращение по тегу и по классу
        //поиск по атрибуту
        driver.findElement(By.cssSelector("[type=button]"));
        driver.findElement(By.cssSelector("button[class~=btn]"));// первое слово из нескольких
        driver.findElement(By.cssSelector("meta[name|='theme']"));//не просто через тире, а еще это слово должно быть первым
        driver.findElement(By.cssSelector("a[id$='_link']"));//поиск по концу строки
        driver.findElement(By.cssSelector("a[id*='img']"));//поиск по вхождению подстроки внутри значения
    }
}
