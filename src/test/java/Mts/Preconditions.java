package Mts;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Preconditions {
    private String mainPageUrl = "https://www.mtsbank.ru/";
private long timeout = 40000; // ожидание для элементов в тесте

    @Step("Открыть браузер Chrome в полноэкранном режиме и установить ожидание в тесте на {this.timeout} мили секунд")
    public  void openChrome() {
        WebDriverManager.chromedriver().setup();
        browser = "chrome";
        Configuration.webdriverLogsEnabled = true;
        Configuration.headless = true;
                Configuration.browserSize = "1920x1080"; // открытие браузера в полноэкранном режиме
        Configuration.timeout = timeout; //
        System.setProperty("chromeoptions.args", "\"--no-sandbox\",\"--disable-dev-shm-usage\",\"--remote-debugging-port=9222\",\"--headless\"");
    }
    @Step("Перейти на страницу: {this.mainPageUrl}")
    public void goToUrl() {
        open(mainPageUrl);
    }
}
