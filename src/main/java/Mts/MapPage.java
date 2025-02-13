package Mts;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MapPage {
    private SelenideElement filtersButton = $x("//div[@class='styled__MainRow-sc-c547a2c6-1 bmrlcA']"); // Элемент фильтров для объектов на карте
    private ElementsCollection filtersList = $$x("//label[input[@name='defaultName']]//div[@type='checkbox']"); // список фильтров
    private SelenideElement listButton = $x("//button[@class='TabItem-sc-284dbe6b-4 kuAyes']"); // Кнопка показа списком
    private  ElementsCollection objects = $$x("//div[@class='styled__MainDataTextContainer-sc-d623ef82-2 coNzXP']"); // элементы, показывающие тип объекта

    @Step("Кликнуть по фильтрам")
    public  void clickFiltersButton() {
        filtersButton.scrollIntoView("{block: 'center'}");
        filtersButton.shouldBe(Condition.visible).click();
    }

    @Step("Обработать необходимые флажки с фильтрами")
    public  void  changeFilters(int... indices) {
        for (int index : indices) {
            if (index >= 0 && index < filtersList.size()) {
                filtersList.get(index).click(); // Отмечаем флажок по индексу
            } else {
                System.out.println("Индекс " + index + "Недопустим в данной коллекции элементов");
            }
        }
        filtersButton.click();
    }

    @Step("Кликнуть на кнопку для отображения списком")
    public  void clickListButton() {
        listButton.scrollIntoView("{block: 'center'}");
        listButton.shouldBe(Condition.visible).click();
    }
    @Step("Убедиться, что отображается тип объекта {type}")
    public  void checkTypeObject(String type) {
objects.shouldBe(CollectionCondition.sizeGreaterThan(0));
for (SelenideElement element: objects) {
    element.scrollIntoView("{block: 'center'}");
    element.shouldHave(Condition.partialText(type));
}
    }
}
