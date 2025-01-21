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
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

@Epic("Тесты расчёта платежа по кредиту для недопустимой и допустимой суммы")
public class TestCountKredit extends  BaseTest {
private MainPage mainPage;
    private KreditCalculatorPage kreditCalculatorPage;

    private static Stream<Arguments> randomSmallKreditSumProvider() { // Генератор случайного числа для недопустимой суммы от 20000 до 5000000
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
    }

    @ParameterizedTest
@ValueSource(strings = {"20000", "5000000"})
    @Description("Анализ негативных граничных значений для рассчёта суммы платежа для крупных кредитов на 7 лет")
    @DisplayName("Анализ негативных граничных значений для рассчёта суммы платежа для крупных кредитов на 7 лет")
    public  void negativeTestCountKreditfor7years(String smallSum) {
        mainPage.clickInterseptElement();
        mainPage.goCalculatorLink();
        kreditCalculatorPage.clearKreditSumInput();
        kreditCalculatorPage.enterKreditSum(smallSum);
        kreditCalculatorPage.changeKreditTime();
      String  expectedConfirmationText = "Это срок для крупных кредитов";
        kreditCalculatorPage.checkConfirmationTextAboutBigKreditSum(expectedConfirmationText);
            }

    @ParameterizedTest
@MethodSource("randomSmallKreditSumProvider")
    @Description("Тест рассчёта кредита на 7 лет для недопустимой суммы от 20000 до 5000000. На вход подаётся случайное число в данном диапазоне")
    @DisplayName("Тест рассчёта платежа по кредиту на 7 лет для недопустимой суммы. На вход подаётся случайное число от 20000 до 5000000")
    public void negativeTestCountKreditfor7yearsRandomNumber(String randomSum, String expectedConfirmationText) {
        mainPage.clickInterseptElement();
        mainPage.goCalculatorLink();
        kreditCalculatorPage.clearKreditSumInput();
        kreditCalculatorPage.enterKreditSum(randomSum);
        kreditCalculatorPage.changeKreditTime();
        kreditCalculatorPage.checkConfirmationTextAboutBigKreditSum(expectedConfirmationText);
    }

    @ParameterizedTest
    @CsvSource({"5000001, '85 349 ₽'", "15000000, '256 048 ₽'"})
    @Description("Тест рассчёта платежа по кредиту на 7 лет для допустимой суммы")
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