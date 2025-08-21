package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTestLecture extends BaseTest {

    //разметка теста,указывается в скобках после @Test, такие как: Наименование testName, приоритизация priority,
    // группа для запуска groups, зависимость от другого теста для запуска dependsOnMethods,
    // количество раз запуска, выключение теста enabled,

    @Test(testName = "Проверка входа в магазин с позитивными данными")//указав testName в скобках при выполнении
    // тестов указывается читаемое название самого теста в run
    @Description("Проверка входа в магазин с позитивными данными")
    @Owner("Светлана")
    @Link()//анатация, которая добавляет ссылку например на аналитику
    @Feature("fsf")
    @Story("frsd")
    @Severity(SeverityLevel.CRITICAL)//уровень нашего теста
    @Lead("Иван Иванов")//указываем нашего лида
    @TmsLink("SD_01")//
    @Issue("SD_01/1")//Ссылка на багрепорт
    public void checkPositiveLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productPage.getTitle(),
                "Products",
                "Сообщение об ошибке не соответствует");
    }

    @Test(enabled = false)//можно выключить конкретный тест, написав в скобках enabled = false,
    // затем изменить на true или лучше вообще стереть
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

    @Test (testName = "Проверка логина с пустым паролем", priority = 1, retryAnalyzer = Retry.class)//по умолчанию
    // тесты запускаются в алфавином порядке названия теста, или если есть testNAme то в его алфавитном порядке.
    // Но если указать через запятую приорити то тесты будут запускаться по его номер
    /*
    retryAnalyzer - точечное указание того, что бы тестовый метод запускался несколько раз. Вынесли это в xml
     */
    public void checkLoginWithEmptyLogin(){
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is require",
                "Сообщение об ошибке не соответствует");
    }

    @Test(groups = "Login Test", dependsOnMethods = "checkLoginWithEmptyLogin")// groups можно сгруппировать тесты по
    // какой-нибудь своей логике и потом запускать в соответствии с группой.
    // Командой dependsOnMethods вешаем зависимость, что пока не выполнится успешно тест, имя которого мы указали,
    // наш тест не запускается
    public void checkLoginWithEmptyName(){
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Сообщение об ошибке не соответствует");
    }

    //Параметризированные тесты
    //Можно писать тесты каждый по отдельности, но лучше их параметризировать, например для пачки негативных тестов,
    // или тесты которые похожи по своим параметрам, например как предыдущие три теста.
    // Для этого используем анатацию @DataProvider

    @DataProvider(name = "Проверка логина с негативными данными")
    public Object[][] loginData() {  //этот метод возвращает двухмерный массив объектов, который
        // является источником данных для теста. И что бы связать этот метод с тестом нужно указать анaтацию @DataProvider
        return new Object[][] {
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    //пишем тест с тремя параметрами и используем в нем метод выше
    @Test(dataProvider = "Проверка логина с негативными данными")//для связки теста с методом указываем в скобках название анатации
    public void paramNegativeTest(String user, String password, String expectedErrorMessage){
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                expectedErrorMessage,
                "Сообщение об ошибке не соответствует");
    }
}
