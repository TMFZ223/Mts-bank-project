package Mts;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class Preconditions {
    private String mainPageUrl = "https://www.mtsbank.ru/";
private long timeout = 40000; // ожидание для элементов в тесте

    @Step("Открыть браузер Chrome в полноэкранном режиме и установить ожидание в тесте на {this.timeout} мили секунд")
    public  void openChrome() {
        browser = "chrome";
        Configuration.browserSize = "1920x1080"; // открытие браузера в полноэкранном режиме
        Configuration.timeout = timeout; //
    }
    @Step("Перейти на страницу: {this.mainPageUrl}")
    public void goToUrl() {
        open(mainPageUrl);
    }
}