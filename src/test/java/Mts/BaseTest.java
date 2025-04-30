package Mts;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.io.OutputStream;
import java.nio.file.*;
import java.util.Properties;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract class BaseTest {
    @BeforeAll
    @Step("Собрать и записать информацию об окружении")
    public static void createEnvironmentFile() {
        try {
            WebDriverManager.chromedriver().setup();
            Configuration.browser = "chrome";
            Configuration.headless = true;
            open("about:blank");
            String os = System.getProperty("os.name");
            String browser = Configuration.browser;
            String browserVersion = ((RemoteWebDriver) getWebDriver())
                    .getCapabilities().getBrowserVersion();

            Properties props = new Properties();
            props.setProperty("OS", os);
            props.setProperty("Browser", browser);
            props.setProperty("Browser.Version", browserVersion);

            Path allureResults = Paths.get("allure-results");
            if (!Files.exists(allureResults)) {
                Files.createDirectories(allureResults);
            }
            try (OutputStream out = Files.newOutputStream(allureResults.resolve("environment.properties"))) {
                props.store(out, "Allure environment properties");
            }
            closeWebDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    @Step("Перейти на главную страницу https://www.mtsbank.ru/")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserSize = "1920x1080"; // открытие браузера в полноэкранном режиме
        Configuration.headless = true;
        Configuration.pageLoadTimeout = 70000;
        Configuration.timeout = 60000;
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
