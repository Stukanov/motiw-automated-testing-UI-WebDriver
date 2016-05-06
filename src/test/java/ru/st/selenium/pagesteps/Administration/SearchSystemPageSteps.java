package ru.st.selenium.pagesteps.Administration;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.WebLogic.SearchSystemLogic;
import ru.st.selenium.pages.BasePage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница - Поисковая система
 */
public class SearchSystemPageSteps extends BasePage implements SearchSystemLogic {

    /*
    Массив элементов всех объектов в колонке "Ошибок индексирования"
     */
    @FindBy(xpath = "//*[@id='indexingInfo']//td[6]")
    private ElementsCollection elementsCollectionIndexingErrors;

    /*
    Массив элементов - ссылки на ошибки индексирования в колонке "Ошибок индексирования"
     */
    @FindBy(xpath = "//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")
    private SelenideElement indexingErrors;


    /**
     * Проверка Загрузки страницы - ожидание наличия информации о индексировании
     */
    public SearchSystemPageSteps ensurePageLoaded() {
        $(By.xpath("//*[@id='indexingInfo']")).shouldBe(Condition.visible);
        $$(By.xpath("//table//tr")).shouldBe(CollectionCondition.size(13));
        return this;
    }

    /**
     * Проверяем отсутствие ошибок в индексах для конкретных объектов системы
     */
    @Override
    public boolean checkNotIndexingErrors() {
        ensurePageLoaded();
        try {
            List<SelenideElement> elementsIndexing = new ArrayList<>();
            for (SelenideElement selenideElement : elementsCollectionIndexingErrors) {
                elementsIndexing.add(selenideElement);
            }
            elementsIndexing.get(1).find(By.xpath("//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")).shouldNotBe(visible); // Действия
            elementsIndexing.get(2).find(By.xpath("//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")).shouldNotBe(visible); // Задачи
            elementsIndexing.get(4).find(By.xpath("/*//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")).shouldNotBe(visible);  // Атрибуты задачи
            elementsIndexing.get(5).find(By.xpath("/*//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")).shouldNotBe(visible); // Документы
            elementsIndexing.get(8).find(By.xpath("/*//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")).shouldNotBe(visible); // Руководства
            elementsIndexing.get(9).find(By.xpath("/*//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")).shouldNotBe(visible); // Контакты
            elementsIndexing.get(10).find(By.xpath("/*//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")).shouldNotBe(visible); // Справочники
            elementsIndexing.get(11).find(By.xpath("/*//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")).shouldNotBe(visible); // Проекты
            elementsIndexing.get(12).find(By.xpath("/*//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")).shouldNotBe(visible); // Права пользователей
            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }
}
