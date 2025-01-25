package Mts;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class KreditFormPage {
    private SelenideElement fioInput = $x("//input[@name='clientFio']"); // Поле для ввода фамилии имени и отчества клиента
    private SelenideElement birthDateInput = $x("//input[@name='birthDate']"); // Поле для ввода даты рождения клиента
    private SelenideElement phoneNumberInput = $x("//input[@name='phoneNumber']"); // Поле для ввода номера телефона клиента
    private SelenideElement allowProcessingConditionsCheckbox = $x("//label[input[@name='allowProcessingConditions']]//div[@type='checkbox']"); // Флажок согласия с условиями рассмотрения заявки оформления кредита
    private SelenideElement nextButton = $x("//button[.//div[text()=\'Далее\']]");
    private SelenideElement confirmationElement = $x("//div[@class='Wrapper-sc-e7da0d4e-0 bDySCX HelperText-sc-20190104-0 bWSIEw']"); // Элемент предупреждающий о несоответствии каких-то критериев оформления кредита

    @Step("Ввести в поле ФИО значение {fio}")
    public void enterClientFio(String fio) {
        fioInput.shouldBe(Condition.visible).scrollTo();
        fioInput.setValue(fio);
    }

    @Step("Ввести в поле дата рождения значение {birthdate}")
    public void enterBirthdate(String birthdate) {
        birthDateInput.scrollTo().hover().setValue(birthdate);
    }

    @Step("Ввести в поле мобильного телефона значение  {phone}")
    public void enterClientPhoneNumber(String phone) {
        phoneNumberInput.scrollTo().hover().setValue(phone);
    }

    @Step("Отметить флажок согласия с условиями рассмотрения заявки")
    public void setAllowProcessingConditionsCheckbox() {
        allowProcessingConditionsCheckbox.scrollIntoView("{block: 'center'}");
        allowProcessingConditionsCheckbox.shouldBe(Condition.clickable).click();
    }

    @Step("Нажать на кнопку далее")
    public void clickNextButton() {
        nextButton.scrollIntoView("{block: 'center'}");
        nextButton.shouldBe(Condition.clickable).click();
    }

    @Step("Убедиться, что отображается сообщение: {message}")
    public void checkConfirmationMessage(String message) {
        confirmationElement.scrollTo().hover();
        confirmationElement.shouldHave(Condition.text(message));
    }

    @Step("Убедиться, что в поле для ввода мобильного телефона введено значение {phone}")
    public void checkPhoneNumber(String phone) {
        phoneNumberInput.shouldHave(Condition.value(phone));
    }
}