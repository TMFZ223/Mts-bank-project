package Mts;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class KreditCalculatorPage {
    private SelenideElement sumKreditInput = $x("//input[@data-testid='input-slider']"); // Поле для ввода суммы кредита
    private SelenideElement kreditTime = $("span[id='7'] > div"); // Срок кредита (7 лет)
    private SelenideElement monthPayElement = $x("//hundefined[@data-testid='heading']"); // элемент ежемесячного платежа
    private SelenideElement confirmationAboutBigKreditSum = $x("//div[@class='styled__SmartText-n9vm43-0 jGftlt']"); // предупреждение о том, что сумма предназначена для крупных кредитов

    @Step("Очистить поле суммы кредита")
    public void clearKreditSumInput() {
        sumKreditInput.scrollIntoView("{block: 'center'}");
        sumKreditInput.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    @Step("ввести в поле суммы кредита значение {sum}")
    public void enterKreditSum(String sum) {
        sumKreditInput.shouldBe(Condition.visible).setValue(String.valueOf(sum));
    }

    @Step("Убедиться, что в поле для ввода суммы кредита введено значение {value}")
    public void checkSumInputValue(String value) {
        sumKreditInput.shouldHave(Condition.value(value));
    }

    @Step("Выбрать срок кредита 7 лет")
    public void changeKreditTime() {
        kreditTime.scrollIntoView("{block: 'center'}");
        kreditTime.shouldBe(Condition.enabled).click();
    }

    @Step("Убедиться, что текст предупреждения: {confirmationText}")
    public void checkConfirmationTextAboutBigKreditSum(String confirmationText) {
        confirmationAboutBigKreditSum.shouldHave(Condition.text(confirmationText));
    }

    @Step("Убедиться, что ежемесячный платёж по кредиту составляет {monthPay}")
    public void checkMonthPay(String monthPay) {
        monthPayElement.scrollIntoView("{block: 'center'}");
        monthPayElement.shouldBe(Condition.visible);
        monthPayElement.shouldHave(Condition.text(monthPay));
    }
}