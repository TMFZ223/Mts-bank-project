package Mts;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Epic("Тестирование формы кредитного калькулятора")
public class TestKreditCalculatorForm {
    private  String expectedSum;
    private  MainPage mainPage;
    private KreditCalculatorPage kreditCalculatorPage;
    private Preconditions preconditions;
    private PostConditions postConditions;

    public TestKreditCalculatorForm() {
        this.mainPage = new MainPage();
        this.kreditCalculatorPage = new KreditCalculatorPage();
        this.preconditions = new Preconditions();
        this.postConditions = new PostConditions();
    }

    @BeforeEach
    public void setUp() {
        preconditions.openChrome();
        preconditions.goToUrl();
    }

    @AfterEach
    public void tearDown() {
        postConditions.clearCookies();
        postConditions.clearLocalStorage();
        postConditions.closeBrowser();
    }

    @ParameterizedTest
    @CsvSource({"20000, '20 000 ₽'", "'fbJco', ''", "'йцшьЪэу', ''", "'.#$`~%^', ''"})
    @Description("Обработка различных значений суммы кредита")
    public void testAnalysisOfThedifferentsValues(String myKreditValue, String expectedSum) {
        mainPage.clickInterseptElement();
        mainPage.goCalculatorLink();
        kreditCalculatorPage.clearKreditSumInput();
        kreditCalculatorPage.enterKreditSum(myKreditValue);
            kreditCalculatorPage.checkSumInputValue(expectedSum);
    }
}