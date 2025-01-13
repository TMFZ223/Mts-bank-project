package Mts;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private SelenideElement kreditLink = $x("//a[@href='/chastnim-licam/krediti/']"); // Ссылка кредиты
    private SelenideElement calculatorLink = $x("//a[@href='/factory/krediti/kreditnyj-kalkulyator/']"); // Ссылка на кредитный калькулятор
    private SelenideElement offices = $x("//a[@href='/ofisi-i-bankomati/']"); // Ссылка на список оффисов и банкоматов
    private SelenideElement interseptElement = $x("//button[.//div[@data-testid='text' and text()='Да, верно']]"); // Элемент, который может перехватить нажатие другого элемента

    @Step("Кликнуть по перехватывающему элементу")
    public void clickInterseptElement() {
        if (interseptElement.exists()) {
            interseptElement.hover().click();
        }
    }

    @Step("Перейти на страницу кредитов")
    public void goKreditLink() {
        kreditLink.hover().click();
    }

    @Step("Перейти по ссылке кредитного калькулятора")
    public void goCalculatorLink() {
        calculatorLink.scrollIntoView("{block: 'center'}");
        calculatorLink.shouldHave(Condition.visible).click();
    }

    @Step("Перейти на страницу офисов и банкоматов")
    public void goOfficeListLink() {
        offices.scrollIntoView("{block: 'center'}");
        offices.shouldHave(Condition.visible).click();
    }

}