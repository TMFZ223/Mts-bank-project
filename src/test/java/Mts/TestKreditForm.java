package Mts;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@Epic("Тестирование формы оформления кредита наличными онлайн")
public class TestKreditForm extends BaseTest {
    private String clientFio = "Сидоров Константин Егорович";
    private String clientBirdthdate = "24.01.2000";
    private  String clientPhoneNumber = "915 220-29-38";
    private  MainPage mainPage;
    private  KreditPage kreditPage;
    private KreditFormPage kreditFormPage;
    private ContinueKreditFormPage continueKreditFormPage;
    private String expectedMessage;
private  String aboutSmsCodeText = "Код подтверждения отправлен на номер +7 " + clientPhoneNumber;

    public TestKreditForm() {
        this.mainPage = new MainPage();
        this.kreditPage = new KreditPage();
        this.kreditFormPage = new KreditFormPage();
        this.continueKreditFormPage = new ContinueKreditFormPage();
    }

    @Test
    @Description("Позетивный тест с вводом валидных данных для оформления кредита наличными онлайн")
    @DisplayName("Позетивный тест с вводом валидных данных для оформления кредита наличными онлайн")
    public  void  positiveTestKreditForm() {
        mainPage.clickInterseptElement();
        mainPage.goKreditLink();
        kreditPage.GoloanCalcLink();
        kreditFormPage.enterClientFio(clientFio);
        kreditFormPage.enterBirthdate(clientBirdthdate);
        kreditFormPage.enterClientPhoneNumber(clientPhoneNumber);
        kreditFormPage.setAllowProcessingConditionsCheckbox();
        kreditFormPage.clickNextButton();
        continueKreditFormPage.checkSmsConfirmationText(aboutSmsCodeText);
    }

    @ParameterizedTest
    @CsvSource({"'02.02.2005', 'Возраст клиента должен быть не менее 20 лет'", "'02.01.1954', 'Возраст клиента должен быть не более 70 лет'", "'', 'Обязательное поле'"})
    @Description("Анализ негативных граничных значений даты рождения при оформлении кредита наличными онлайн")
    @DisplayName("Анализ негативных граничных значений даты рождения при оформлении кредита наличными онлайн")
    public  void testAnalysisOfNegativeBoundaryValuesOfDateOfBirth(String negativeClientBirdthdateValue, String expectedMessage) {
        mainPage.clickInterseptElement();
        mainPage.goKreditLink();
        kreditPage.GoloanCalcLink();
        kreditFormPage.enterClientFio(clientFio);
        kreditFormPage.enterBirthdate(negativeClientBirdthdateValue);
        kreditFormPage.enterClientPhoneNumber(clientPhoneNumber);
                kreditFormPage.setAllowProcessingConditionsCheckbox();
        kreditFormPage.clickNextButton();
                        kreditFormPage.checkConfirmationMessage(expectedMessage);
    }

    @ParameterizedTest
    @CsvSource({"'915 303-45-1', 'Введите верный номер телефона'", "'', 'Обязательное поле'"})
    @Description("Ввод короткого и пустого значения мобильного телефона")
    @DisplayName("Ввод короткого и пустого значения мобильного телефона")
    public void testEnterShortAndEmptyPhoneNumberValue(String negativeClientPhoneNumberValue, String expectedMessage) {
        mainPage.clickInterseptElement();
        mainPage.goKreditLink();
        kreditPage.GoloanCalcLink();
        kreditFormPage.enterClientFio(clientFio);
        kreditFormPage.enterBirthdate(clientBirdthdate);
        kreditFormPage.enterClientPhoneNumber(negativeClientPhoneNumberValue);
        kreditFormPage.setAllowProcessingConditionsCheckbox();
        kreditFormPage.clickNextButton();
        kreditFormPage.checkConfirmationMessage(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"915 303-45-123", "abf ddn-qr-qc", "яэо лдщ-йц-цх", "№; @&*-!`-~("})
    @Description("Ввод длинного значения и других недопустимых значений в поле мобильного телефона  ")
    @DisplayName("Ввод длинного значения и других недопустимых значений в поле мобильного телефона  ")
    public void testEnterLongAndInvalidPhoneNumberValues(String negativeClientPhoneNumberValue) {
        mainPage.clickInterseptElement();
        mainPage.goKreditLink();
        kreditPage.GoloanCalcLink();
        kreditFormPage.enterClientPhoneNumber(negativeClientPhoneNumberValue);
        String expectedPhoneNumberValue;
        if (negativeClientPhoneNumberValue.equals("915 303-45-123")) {
            expectedPhoneNumberValue = "+7 915 303-45-12";
        } else {
            expectedPhoneNumberValue = "+7 ";
        }
        kreditFormPage.checkPhoneNumber(expectedPhoneNumberValue);
            }

    @Test
    @Description("Оформление заявки без согласия с условиями рассмотрения")
    @DisplayName("Оформление заявки без согласия с условиями рассмотрения")
        public  void testWithoutAllowPreconditions() {
        mainPage.clickInterseptElement();
        mainPage.goKreditLink();
        kreditPage.GoloanCalcLink();
        kreditFormPage.enterClientFio(clientFio);
        kreditFormPage.enterBirthdate(clientBirdthdate);
        kreditFormPage.enterClientPhoneNumber(clientPhoneNumber);
        kreditFormPage.clickNextButton();
        expectedMessage = "Для подачи заявки необходимо дать согласие на этот пункт";
        kreditFormPage.checkConfirmationMessage(expectedMessage);
    }
}