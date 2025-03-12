package Mts;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class KreditCalculatorPage {
    private SelenideElement sumKreditInput = $x("//input[@data-testid='input-slider']"); // Поле для суммы кредита
    private SelenideElement sumSlider = $x("//div[@role='slider']"); // Ползунок, где отображается сумма кредита
    private SelenideElement kreditTime = $("span[id='7'] > div"); // Срок кредита (7 лет)
    private SelenideElement closeConfirmationModelWindowElement = $x("//button[@class='Wrapper-sc-165360ae-1 cToGcJ']"); // Элемент для закрытия модального окна с предупреждением о сроке для суммы крупного кредита
    private SelenideElement monthPayElement = $x("//hundefined[@data-testid='heading']"); // элемент ежемесячного платежа
    private SelenideElement confirmationAboutBigKreditSum = $x("//div[@class='styled__SmartText-n9vm43-0 jGftlt']"); // предупреждение о том, что сумма предназначена для крупных кредитов

    @Step("Очистить поле суммы кредита")
    public void clearKreditSumInput() {
        sumKreditInput.scrollIntoView("{block: 'center'}");
        sumKreditInput.shouldBe(Condition.visible).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    @Step("ввести в поле суммы кредита значение {sum}")
    public void enterKreditSum(String sum) {
        sumKreditInput.setValue(String.valueOf(sum));
    }

    @Step("Убедиться, что в поле для ввода суммы кредита введено значение {value}")
    public void checkSumInputValue(String value) {
        sumKreditInput.shouldHave(Condition.value(value));
    }

    @Step("Убедиться, что текущее значение ползунка {sum}")
    public void checkCurrentSliderValue(String sum) {
        sumSlider.scrollIntoView("{block: 'center'}");
        sumSlider.shouldHave(Condition.attribute("aria-valuenow", sum));
    }

    @Step("Выбрать срок кредита 7 лет")
    public void changeKreditTime() {
        kreditTime.scrollIntoView("{block: 'center'}");
        kreditTime.shouldBe(Condition.visible).click();
    }

    @Step("Убедиться, что текст предупреждения: {confirmationText}")
    public void checkConfirmationTextAboutBigKreditSum(String confirmationText) {
        confirmationAboutBigKreditSum.shouldHave(Condition.text(confirmationText));
    }

    @Step("Закрыть модальное окно")
    public void closeModelWindow() {
        closeConfirmationModelWindowElement.scrollIntoView("{block: 'center'}");
        closeConfirmationModelWindowElement.click();
    }

    @Step("Убедиться, что элемент предупреждения исчез")
    public void checkDisappearConfirmation() {
        confirmationAboutBigKreditSum.shouldBe(Condition.disappear);
    }

    @Step("Убедиться, что ежемесячный платёж по кредиту составляет {monthPay}")
    public void checkMonthPay(String monthPay) {
        monthPayElement.scrollIntoView("{block: 'center'}");
        monthPayElement.shouldBe(Condition.visible);
        monthPayElement.shouldHave(Condition.text(monthPay));
    }
}
