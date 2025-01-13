package Mts;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MapPage {
    private SelenideElement filtersButton = $x("//div[@class='styled__Container-sc-1m80q5x-0 dbbrBq']/div[1]/div[1]"); // Элемент фильтров для объектов на карте
    private ElementsCollection filtersList = $$x("//label[input[@name='defaultName']]//div[@type='checkbox']"); // список фильтров
    private SelenideElement listButton = $x("//div[@data-testid='tabsbar']//button[2]"); // Кнопка показа списком
    private  ElementsCollection objects = $$x("//div[@class=\"styled__SmartText-n9vm43-0 gWGfyf styled__TextWithOverflow-sc-egp5sf-3 bxtWxp\"]"); // элементы, показывающие тип объекта

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