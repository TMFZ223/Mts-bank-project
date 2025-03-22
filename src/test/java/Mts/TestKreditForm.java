package Mts;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Epic("Тестирование формы оформления кредита наличными онлайн")
public class TestKreditForm extends BaseTest {
    private List<String> clientNames = Arrays.asList("Сидоров Константин Егорович", "Карнилов Владимир Павлович", "Жигулин Даниил Кирилович", "Самохин Андрей Степанович", "Японцев Виталий Альбертович", "Суслопаров Сергей Николаевич", "Прихотько Степан Валентинович", "Балабанов Владимир Николаевич", "ларинов Пётр Михайлович", "Гера Дана Дмитриевна", "Миронова Анастасия Алексеевна", "Малевский Александр Олегович", "Самойлов Савелий Юрьевич", "Коренев Алексей Русланович");
    private List<String> phones = Arrays.asList("915 220-29-38", "916 237-12-54", "915 461-24-29", "916 894-38-29", "910 280-37-21", "915 303-43-01", "925 111-08-97", "917 236-00-09", "985 235-85-85", "915 130-00-00", "906 001-00-00", "988 149-77-76", "918 118-18-18");
        private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private MainPage mainPage;
    private KreditPage kreditPage;
    private KreditFormPage kreditFormPage;
    private ContinueKreditFormPage continueKreditFormPage;
    private String expectedMessage;

    private static Stream<Object[]> negativeDateProvider() {
        LocalDate oldDate = LocalDate.now().withYear(1954);
        LocalDate smallDate = LocalDate.now().plusDays(1).withYear(2005);
        String emptyValue = "";
        return Stream.of(
                new Object[]{formatter.format(oldDate), "Возраст клиента должен быть не более 70 лет"},
                new Object[]{formatter.format(smallDate), "Возраст клиента должен быть не менее 20 лет"},
                new Object[]{emptyValue, "Обязательное поле"}
        );
    }

    public TestKreditForm() {
        this.mainPage = new MainPage();
        this.kreditPage = new KreditPage();
        this.kreditFormPage = new KreditFormPage();
        this.continueKreditFormPage = new ContinueKreditFormPage();
    }

    @Test
    @Description("Позетивный тест с вводом валидных данных для оформления кредита наличными онлайн")
    @DisplayName("Позетивный тест с вводом валидных данных для оформления кредита наличными онлайн")
    public void positiveTestKreditForm() {
        mainPage.clickInterseptElement();
        mainPage.goKreditLink();
        kreditPage.GoloanCalcLink();
        Random random = new Random();
        String randomClientName = clientNames.get(random.nextInt(clientNames.size()));
        kreditFormPage.enterClientFio(randomClientName);
        LocalDate startDate = LocalDate.now().plusDays(1).withYear(1955);
        LocalDate endDate = LocalDate.now().withYear(2005);
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        // Генерируем случайное число в диапазоне [startEpochDay, endEpochDay]
        long diff = endEpochDay - startEpochDay + 1; // +1, чтобы включить конец диапазона
        long offset = (long) (random.nextDouble() * diff);
        long randomEpochDay = startEpochDay + offset;

        // Превращаем обратно в LocalDate
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        String formattedDate = randomDate.format(formatter);
        kreditFormPage.enterBirthdate(formattedDate);
        String randomPhone = phones.get(random.nextInt(phones.size()));
        kreditFormPage.enterClientPhoneNumber(randomPhone);
        kreditFormPage.setAllowProcessingConditionsCheckbox();
        kreditFormPage.clickNextButton();
                String aboutSmsCodeText = "Код подтверждения отправлен на номер +7 " + randomPhone;
        continueKreditFormPage.checkSmsConfirmationText(aboutSmsCodeText);
    }

    @ParameterizedTest
    @MethodSource("negativeDateProvider")
    @Description("Анализ негативных граничных значений даты рождения при оформлении кредита наличными онлайн")
    @DisplayName("Анализ негативных граничных значений даты рождения при оформлении кредита наличными онлайн")
    public void testAnalysisOfNegativeBoundaryValuesOfDateOfBirth(String negativeClientBirdthdateValue, String expectedNegativeMessage) {
        mainPage.clickInterseptElement();
        mainPage.goKreditLink();
        kreditPage.GoloanCalcLink();
        Random random = new Random();
        String randomClientName = clientNames.get(random.nextInt(clientNames.size()));
        kreditFormPage.enterClientFio(randomClientName);
        kreditFormPage.enterBirthdate(negativeClientBirdthdateValue);
        String randomPhone = phones.get(random.nextInt(phones.size()));
        kreditFormPage.enterClientPhoneNumber(randomPhone);
        kreditFormPage.setAllowProcessingConditionsCheckbox();
        kreditFormPage.clickNextButton();
        kreditFormPage.checkConfirmationMessage(expectedNegativeMessage);
    }

    @ParameterizedTest
    @CsvSource({"'915 303-45-1', 'Введите верный номер телефона'", "'', 'Обязательное поле'"})
    @Description("Ввод короткого и пустого значения мобильного телефона")
    @DisplayName("Ввод короткого и пустого значения мобильного телефона")
    public void testEnterShortAndEmptyPhoneNumberValue(String negativeClientPhoneNumberValue, String expectedMessage) {
        mainPage.clickInterseptElement();
        mainPage.goKreditLink();
        kreditPage.GoloanCalcLink();
        Random random = new Random();
        String randomClientName = clientNames.get(random.nextInt(clientNames.size()));
        kreditFormPage.enterClientFio(randomClientName);
        LocalDate startDate = LocalDate.now().plusDays(1).withYear(1955);
        LocalDate endDate = LocalDate.now().withYear(2005);
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        // Генерируем случайное число в диапазоне [startEpochDay, endEpochDay]
        long diff = endEpochDay - startEpochDay + 1; // +1, чтобы включить конец диапазона
        long offset = (long) (random.nextDouble() * diff);
        long randomEpochDay = startEpochDay + offset;

        // Превращаем обратно в LocalDate
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        String formattedDate = randomDate.format(formatter);
        kreditFormPage.enterBirthdate(formattedDate);
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
    public void testWithoutAllowPreconditions() {
        mainPage.clickInterseptElement();
        mainPage.goKreditLink();
        kreditPage.GoloanCalcLink();
                Random random = new Random();
        String randomClientName = clientNames.get(random.nextInt(clientNames.size()));
        kreditFormPage.enterClientFio(randomClientName);
        LocalDate startDate = LocalDate.now().plusDays(1).withYear(1955);
        LocalDate endDate = LocalDate.now().withYear(2005);
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        // Генерируем случайное число в диапазоне [startEpochDay, endEpochDay]
        long diff = endEpochDay - startEpochDay + 1; // +1, чтобы включить конец диапазона
        long offset = (long) (random.nextDouble() * diff);
        long randomEpochDay = startEpochDay + offset;

        // Превращаем обратно в LocalDate
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        String formattedDate = randomDate.format(formatter);
        kreditFormPage.enterBirthdate(formattedDate);
        String randomPhone = phones.get(random.nextInt(phones.size()));
        kreditFormPage.enterClientPhoneNumber(randomPhone);
        kreditFormPage.clickNextButton();
        expectedMessage = "Для подачи заявки необходимо дать согласие на этот пункт";
        kreditFormPage.checkConfirmationMessage(expectedMessage);
    }
}
