package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkPositiveLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productPage.getTitle(),
                "Products",
                "Сообщение об ошибке не соответствует");
    }

    @Test
    public void checkLoginWithEmptyPassword(){
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не соответствует");//когда пишем ассерты, всегда указываем сообщение
        //Этим мы заменили длинный код который был указан ниже
        /*
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        Assert.assertEquals(title, "Epic sadface: Password is required");

         */
    }

    @Test
    public void checkLoginWithEmptyLogin(){
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required",
                "Сообщение об ошибке не соответствует");
    }

    @Test
    public void checkLoginWithEmptyName(){
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Сообщение об ошибке не соответствует");
    }
}
