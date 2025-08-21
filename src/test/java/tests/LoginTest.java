package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Проверка входа в магазин с валидными данными",
            description = "Проверка входа в магазин с позитивными данными",
            groups = {"Smoke"}, priority = 1)
    @Description("Проверка входа в магазин с позитивными данными")
    @Owner("Светлана")
    @Feature("Authorization")
    @Severity(SeverityLevel.CRITICAL)//уровень нашего теста
    @Lead("Тимофей")
    public void checkPositiveLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productPage.getTitle(),
                "Products",
                "Сообщение об ошибке не соответствует");
    }

    @Test(enabled = false)
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не соответствует");
    }

    @Test(testName = "Проверка входа в магазин с пустым логином",
            description = "Проверка входа в магазин с пустым логином", groups = "{Smoke}", priority = 2)
    @Description("Проверка входа в магазин с пустым логином")
    @Owner("Светлана")
    @Feature("Authorization")
    @Severity(SeverityLevel.CRITICAL)//уровень нашего теста
    @Lead("Тимофей")
    public void checkLoginWithEmptyLogin() {
        loginPage.open();
        loginPage.login("", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не соответствует");
    }

    @DataProvider(name = "Проверка логина с негативными данными")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(testName = "Проверка логина с негативными данными",
            dataProvider = "Проверка логина с негативными данными",
            description = "Проверка логина с негативными данными")
    @Description("Проверка логина с негативными данными")
    @Owner("Светлана")
    @Feature("Authorization")
    @Severity(SeverityLevel.CRITICAL)//уровень нашего теста
    @Lead("Тимофей")
    public void paramNegativeTest(String user, String password, String expectedErrorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                expectedErrorMessage,
                "Сообщение об ошибке не соответствует");
    }
}
