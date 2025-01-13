package Mts;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

@Epic("Тесты расчёта кредита")
public class TestCountKredit {
private MainPage mainPage;
    private KreditCalculatorPage kreditCalculatorPage;
    private Preconditions preconditions;
    private PostConditions postConditions;

    private  static Stream<Arguments> randomSmallKreditSumProvider() {
        int minSum = 20000;
        int maxSum = 5000000;
        // Генерация чисел в заданном диапазоне, включая верхнюю границу
        Random random = new Random();
        int randomNumber = minSum + random.nextInt(maxSum - minSum + 1);
        String randomSum = String.valueOf(randomNumber);
        String expectedConfirmationText = "Это срок для крупных кредитов";
        return Stream.of(Arguments.of(randomSum, expectedConfirmationText));
    }
    public TestCountKredit() {
        this.mainPage = new MainPage();
        this.kreditCalculatorPage = new KreditCalculatorPage();
        this.preconditions = new Preconditions();
        this.postConditions = new PostConditions();
    }

    @BeforeEach
    public void setUp() {
        preconditions.openChrome();
        preconditions.goToUrl();
    }

    @AfterEach
    public void tearDown() {
        postConditions.clearCookies();
        postConditions.clearLocalStorage();
        postConditions.closeBrowser();
    }

    @ParameterizedTest
@MethodSource("randomSmallKreditSumProvider")
    @Description("Тест рассчёта кредита на 7 лет для недопустимой суммы от 20000 до 5000000")
    @DisplayName("Тест рассчёта кредита на 7 лет для недопустимой суммы от 20000 до 5000000")
    public void negativeTestCountKreditfor7years(String randomSum, String expectedConfirmationText) {
        mainPage.clickInterseptElement();
        mainPage.goCalculatorLink();
        kreditCalculatorPage.clearKreditSumInput();
        kreditCalculatorPage.enterKreditSum(randomSum);
        kreditCalculatorPage.changeKreditTime();
        kreditCalculatorPage.checkConfirmationTextAboutBigKreditSum(expectedConfirmationText);
    }

    @ParameterizedTest
    @CsvSource({"5000001, '85 349 ₽'"})
    @Description("Тест рассчёта кредита на 7 лет для допустимой суммы")
    @DisplayName("Тест рассчёта кредита на 7 лет для допустимой суммы")
    public void positiveTestCountKreditFor7Years(String normalKreditSum, String expectedMonthPay) {
        mainPage.clickInterseptElement();
        mainPage.goCalculatorLink();
        kreditCalculatorPage.clearKreditSumInput();
        kreditCalculatorPage.enterKreditSum(normalKreditSum);
        Selenide.sleep(10000);
        kreditCalculatorPage.changeKreditTime();
        kreditCalculatorPage.checkMonthPay(expectedMonthPay);
    }
}