package Mts;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;

public class PostConditions {
    @Step("Очистить Куки")
    public void clearCookies() {
        clearBrowserCookies();
    }

    @Step("Очистить локальное хранилище")
    public void clearLocalStorage() {
        clearBrowserLocalStorage();
    }

    @Step("Закрыть браузер")
    public void closeBrowser() {
        closeWebDriver();
    }
}