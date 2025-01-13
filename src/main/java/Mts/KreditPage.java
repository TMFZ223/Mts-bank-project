package Mts;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class KreditPage {
    private SelenideElement loanCalcLink = $x("//a[@href='/chastnim-licam/krediti/credit-all/?scroll=loanCalc']"); // Ссылка на оформление кредита наличными

    @Step("Перейти по ссылке оформления кредита наличными")
    public  void  GoloanCalcLink() {
        loanCalcLink.scrollIntoView("{block: 'center'}");
        loanCalcLink.shouldHave(Condition.visible).click();
    }
}
