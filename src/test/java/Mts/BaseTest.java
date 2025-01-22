package Mts;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

abstract class BaseTest {

    @BeforeEach
    @Step("Перейти на главную страницу https://www.mtsbank.ru/")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        browser = "chrome";
        Configuration.webdriverLogsEnabled = true;
        Configuration.headless = true;
                Configuration.browserSize = "1920x1080"; // открытие браузера в полноэкранном режиме
        Configuration.timeout = 30000;
        open("https://www.mtsbank.ru/");
    }

    @AfterEach
    @Step("Очистить Куки, локальное хранилище и закрыть браузер")
    public void tearDown() {
                clearBrowserCookies();
        clearBrowserLocalStorage();
closeWebDriver();
    }
}
