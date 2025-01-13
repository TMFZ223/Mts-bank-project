package Mts;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;

public class ContinueKreditFormPage {
    private SelenideElement smsCodeConfirmationElement = $x("//div[@class='styled__SmartText-n9vm43-0 bUwfLc']");

    @Step("убедиться, что отображается сообщение: {confirmationAboutSmsCode}")
    public  void checkSmsConfirmationText(String confirmationAboutSmsCode) {
        smsCodeConfirmationElement.scrollIntoView("{block: 'center'}");
        smsCodeConfirmationElement.shouldBe(Condition.visible);
        smsCodeConfirmationElement.shouldBe(Condition.text(confirmationAboutSmsCode));
    }
}