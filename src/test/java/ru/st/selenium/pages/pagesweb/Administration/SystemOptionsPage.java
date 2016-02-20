package ru.st.selenium.pages.pagesweb.Administration;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.st.selenium.utils.ElementUtil.selectingSecondAdjustmentPosition;

/**
 * Настройки системы
 */
public class SystemOptionsPage extends BasePage {


    /**
     * tab - Задачи
     */
    @FindBy(xpath = "(//a//span[text()][ancestor::a[contains(@id,'tab')]])[4]")
    private SelenideElement tabTasks;

    /**
     * Опция - Уделение себя из задач
     */
    @FindBy(xpath = "//*[@id='allowDeleteHimself-inputEl']")
    private SelenideElement allowToDeleteFromTasks;

    /**
     * Сохранить
     */
    @FindBy(xpath = "//a[contains(@id,'button')][ancestor::span[contains(@id,'panel')]]//span[2]")
    private SelenideElement save;

    /**
     * Клик alert "Ok"
     */
    @FindBy(xpath = "//*[@id='button-1005-btnIconEl']")
    private SelenideElement clicAlertOk;


    /**
     * Проверка Загрузки страницы - ожидание вкладок - Лицензии, Интерфейс, Задачи, Документы и пр..
     */
    @Step("Проверяем отображение элементов (вкладок) на странице - Настройки системы")
    public SystemOptionsPage ensurePageLoaded() {
        $$(By.xpath("//a//span[text()][ancestor::a[contains(@id,'tab')]]")).shouldBe(CollectionCondition.size(7));
        return this;
    }

    /**
     * Выбор опции - Удаление себя из задач == Да
     *
     * @return SystemOptionsPage
     */
    @Step("Выбор опции - Удаление себя из задач == Да")
    public SystemOptionsPage selectAllowToDeleteOneSelfFromTasks() {

        String initialValue = "Нет";

        ensurePageLoaded();
        tabTasks.click();
        String value = allowToDeleteFromTasks.val();
        if (value != null && initialValue.equals(value)) {
            allowToDeleteFromTasks.click();
            selectingSecondAdjustmentPosition();
            save.click();
            checkingMessagesConfirmationOfTheObject($(By.xpath("//div[count(div)=3]/div[2]//div[contains(@id,'messagebox') and (@data-errorqtip)]")),
                    "Изменения сохранены", clicAlertOk);
        }

        return this;
    }


}
