package Mts;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Epic("Попарное тестирование фильтров офисов и банкоматов")
public class TestMap extends  BaseTest {
    private MainPage mainPage;
    private MapPage mapPage;

    public TestMap() {
        this.mainPage = new MainPage();
        this.mapPage = new MapPage();
    }

    @ParameterizedTest
    @CsvSource({"8, 9, 10, 11, 'Офис'", "7, 9, 10, 11, 'Магазин'", "7, 8, 10, 11, 'Банкомат'", "7, 8, 9, 11, 'Терминал'", "7, 8, 9, 10, 'Банкомат'"})
    @Description("Попарное тестирование фильтров (Экспериментальный вариант)")
    @DisplayName("Попарное тестирование фильтров (Экспериментальный вариант)")
    public void testWorkingNowObjects(int filter1, int filter2, int filter3, int filter4, String expectedObjectType) {
        /*
        Экспериментальный вариант демонстрации техники попарного тестирования.
        Поумолчанию активен фильтр Работает сейчас, а также в фильтре тип объекта отмечены все фильтры.
        В параметризованном тесте сначала происходит клик на фильтры магазины, банкоматы, терминалы, офисы партнёрских банкоматов. Остаётся пара фильтров: работает сейчас, офисы.
       Затем происходит клик на фильтры офисы, банкоматы, терминалы, офисы партнёрских банкоматов. Остаётся пара фильтров: работает сейчас, магазины.
       Затем происходит клик на фильтры офисы, магазины, терминалы, офисы партнёрских банкоматов. Остаётся пара фильтров: работает сейчас, банкоматы.
       Затем происходит клик на фильтры офисы, магазины, банкоматы, офисы партнёрских банкоматов. Остаётся пара фильтров: работает сейчас, терминалы.
       И наконец происходит клик на фильтры офисы, магазины, банкоматы, терминалы. Остаётся пара фильтров: работает сейчас, офисы партнёрских банкоматов.
       При условии, что пара фильтров активна происходит просмотр списка найденных объектов, где проверяется что это за объект (либо офис, либо магазин, либо банкомат, либо терминал либо банкомат партнёра)
         */
        mainPage.clickInterseptElement();
        mainPage.goOfficeListLink();
        mapPage.clickFiltersButton();
        mapPage.changeFilters(filter1, filter2, filter3, filter4);
        mapPage.clickListButton();
        mapPage.checkTypeObject(expectedObjectType);
    }
}