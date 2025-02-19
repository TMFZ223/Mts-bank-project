package Mts;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;

public class ContinueKreditFormPage {
    private SelenideElement smsCodeConfirmationElement = $x("//div[@class='styled__SmartText-sc-bd556393-0 kNbGij']");

    @Step("убедиться, что отображается сообщение: {confirmationAboutSmsCode}")
    public  void checkSmsConfirmationText(String confirmationAboutSmsCode) {
        smsCodeConfirmationElement.scrollIntoView("{block: 'center'}");
        smsCodeConfirmationElement.shouldBe(Condition.visible);
        smsCodeConfirmationElement.shouldBe(Condition.text(confirmationAboutSmsCode));
    }
}
