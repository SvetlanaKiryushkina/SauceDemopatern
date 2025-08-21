package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By USERNAME_INPUT = By.id("user-name");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы LoginPage")
    public LoginPage open() {
        driver.get(BASE_URL);
        //takeScreenshot(driver);//добавляем для создания скриншота, но целесообразно делать
        // только при падении теста
        return this;
    }

    @Step("Проверка полной загрузки страницы")
    public LoginPage isPageOpened (){
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));//явное ожидание, т.е после перехода
        //на страницу, ожидаем пока загрузится кнопка логин
        return this;
    }

    @Step("Вход в магазин с именем пользователя: '{user}' и пароль: '{password}'")
    public ProductPage login (String user, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
