package ru.st.selenium.pages.pagesweb.DocflowAdministration;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.WebLogic.DictionaryEditorLogic;
import ru.st.selenium.model.AccessRights;
import ru.st.selenium.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.st.selenium.model.DocflowAdministration.DictionaryEditor.DictionaryEditorField;
import ru.st.selenium.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница - Администрирование ДО/Редактор словарей
 */
public class DictionaryEditorPage extends BasePage implements DictionaryEditorLogic {


    /**
     * Основной фрейм
     */
    @FindBy(id = "flow")
    private SelenideElement Frem;

    /**
     * Список объектов - Словарь
     */
    @FindBy(xpath = "//table[contains(@id,'treeview')]")
    private ElementsCollection tableGridTreeView;

    /**
     * Добавить словарь
     */
    @FindBy(xpath = "(//a//preceding-sibling::span)[1]")
    private SelenideElement addDictionaryEditor;

    /**
     * Редактировать словарь
     */

    @FindBy(xpath = "(//a//preceding-sibling::span)[2]")
    private SelenideElement editDictionaryEditor;

    /**
     * Удалить словарь
     */
    @FindBy(xpath = "(//a//preceding-sibling::span)[3]")
    private SelenideElement delDictionaryEditor;

    /**
     * Название словаря
     */
    @FindBy(id = "dict_name-inputEl")
    private SelenideElement nameDictionaryEditor;


    //----------------------------------------------------------------------------ПРАВА--------------------------------------------------------------------------------


    /**
     * Отображение - Общедоступная
     *
     * @FindBy
     */
    @FindBy(xpath = "(//tr[count(td)=3]//td)[1]//span[@data-ref]")
    private SelenideElement clickAccessAvailableToAll;

    /**
     * Отображение - Только для чтения
     *
     * @FindBy
     */
    @FindBy(xpath = "(//tr[count(td)=3]//td)[2]//span[@data-ref]")
    private SelenideElement clickReadOnly;

    /**
     * Отображение - Личный словарь
     *
     * @FindBy
     */
    @FindBy(xpath = "(//tr[count(td)=3]//td)[3]//span[@data-ref]")
    private SelenideElement clickPersonal;

    /**
     * Доабвить значение словаря
     */

    @FindBy(xpath = "//span[@id='bAddWord-btnEl']")
    private SelenideElement addDictionaryEditorItem;

    /**
     * Редактировать значения словаря
     */

    @FindBy(xpath = "//span[@id='bEditWord-btnWrap']")
    private SelenideElement editDictionItem;

    /**
     * Удалить значение словаря
     */

    @FindBy(xpath = "//span[@id='bDeleteWord']")
    private SelenideElement delDictionItem;

    /**
     * Переместить вверх значение словаря
     */

    @FindBy(xpath = "//span[@id='bUpWord']")
    private SelenideElement upDictionItem;

    /**
     * Переместить вниз значение словаря
     */

    @FindBy(xpath = "//span[@id='bDownWord']")
    private SelenideElement downDictionItem;

    /**
     * Название элемента словаря
     */
    @FindBy(xpath = "//input[@id='name_word-inputEl']")
    private SelenideElement nameDictionaryEditorItem;

    /**
     * Описание элемента словаря
     */
    @FindBy(xpath = "//textarea[@id='value_word-inputEl']")
    private SelenideElement descriptionDictionaryEditorItem;

    /**
     * Сохранить элемента словаря
     */
    @FindBy(xpath = "(//span[contains(@id,'button')])[8]")
    private SelenideElement saveDictionaryEditorItem;

    /**
     * Отменить сохранения элемента словаря
     */
    @FindBy(xpath = "(//span[contains(@id,'button')])[7]")
    private SelenideElement cancelDictionaryEditorItem;

    /**
     * Сохранить изменения по объекту Словарь
     */
    @FindBy(xpath = "(//span[contains(@id,'bSave-')]/span[last()])[2]")
    private SelenideElement saveChanges;

    /**
     * Сохранить как
     */
    @FindBy(xpath = "//span[@id='bSaveAs-btnIconEl']")
    private SelenideElement saveAsChanges;

    /**
     * Вернуться без сохранения
     */
    @FindBy(xpath = "//div[count(a)=3 and contains(@id,'toolbar')]/a[3]//preceding-sibling::span")
    private SelenideElement backWithoutSaving;

    /**
     * Клик Добавить объект - Редактор словарей
     */
    public DictionaryEditorPage addDictionaryEditor() {
        addDictionaryEditor.click();
        $(nameDictionaryEditor).shouldBe(Condition.present);
        return this;
    }

    /**
     * Вводим название - Словаря
     */
    public DictionaryEditorPage setNameDictionaryEditor(String nameDictionText) {
        nameDictionaryEditor.clear();
        nameDictionaryEditor.setValue(nameDictionText);
        return this;
    }

    /**
     * Метод выбора уровня доступа к РКД (Регистрационная карточка документа)
     */
    public DictionaryEditorPage setRadioButtAccess(AccessRights accessRights) {
        if (accessRights == AccessRights.AVAILABLETOALL) {
            clickAccessAvailableToAll.click();
        } else if (accessRights == AccessRights.PERSONAL) {
            clickPersonal.click();
        } else if (accessRights == AccessRights.READONLY) {
            clickReadOnly.click();
        }
        return this;
    }

    /**
     * Клик Добавить элемент словаря
     */
    public DictionaryEditorPage addDictionaryEditorItem() {
        addDictionaryEditorItem.click();
        $(nameDictionaryEditorItem).shouldBe(Condition.present);
        return this;
    }

    /**
     * Вводим название и описание Элемента словаря - Словаря
     */
    public DictionaryEditorPage addDictionItemFields(DictionaryEditorField[] dictionItem) {
        int countElement = 1;
        if (dictionItem == null) {
            return this;
        } else
            outer:
                    for (DictionaryEditorField aDictionItem : dictionItem) {
                        addDictionaryEditorItem(); // Доавть элемент словаря
                        nameDictionaryEditorItem.clear();
                        nameDictionaryEditorItem.setValue(aDictionItem.getDictionaryEditorElement()); // Название элемента словаря
                        descriptionDictionaryEditorItem.clear();
                        descriptionDictionaryEditorItem.setValue(aDictionItem.getDescriptionElement()); // Описание элемента словаря
                        saveDictionaryEditorItem();
                        // Проверям отображение элемента словаря в гриде Словаря
                        $(By.xpath("(//div[contains(@id,'dict_words-body')]//td[1]/div)[" + countElement + "]")).shouldBe(Condition.exactText("" + aDictionItem.getDictionaryEditorElement() + ""));
                        $(By.xpath("(//div[contains(@id,'dict_words-body')]//td[2]/div)[" + countElement + "]")).shouldBe(Condition.exactText("" + aDictionItem.getDescriptionElement() + ""));
                        countElement++;
                    }
        return this;
    }

    /**
     * Сохранить элемент словаря
     *
     * @return DictionaryEditorPage
     */
    public DictionaryEditorPage saveDictionaryEditorItem() {
        saveDictionaryEditorItem.click();
        return this;
    }

    /**
     * Сохранить все изменения
     *
     * @return DictionaryEditorPage
     */
    public DictionaryEditorPage saveDictionaryEditor() {
        $(saveChanges).shouldBe(Condition.visible);
        saveChanges.click();
        return this;
    }

    /**
     * Проверяем, что созданный объект сохранился и отображается в гриде - Редактор словарей
     *
     * @return DictionaryEditorPage
     */
    public DictionaryEditorPage verifyDictionaryEditor(String dictionaryEditor) {
        $(By.xpath("//table//span[contains(@class,'x-tree-node-text') and contains(text(),'" + dictionaryEditor + "')]")).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Проверяем и ожидаем, что на странице имеется соответствующий элемент: -
     * Кнопки - Добавить и Редактировать в гриде - Редактор словарей
     */
    public DictionaryEditorPage ensurePageLoaded() {
        $(By.xpath("(//a//preceding-sibling::span)[1]")).shouldBe(Condition.present);
        $(By.xpath("(//a//preceding-sibling::span)[2]")).shouldBe(Condition.present);
        return this;
    }


    /**
     * Добавить Словарь
     *
     * @param directoriesEditor
     */
    @Override
    public void addDictionaryEditor(DictionaryEditor directoriesEditor) {
        ensurePageLoaded();
        addDictionaryEditor()
                .setNameDictionaryEditor(directoriesEditor.getDictionaryEditorName()) // Название словаря
                .setRadioButtAccess(directoriesEditor.getAccessDiction()) // Определяем уровень доступа к объекту
                .addDictionItemFields(directoriesEditor.getDictionaryEditorFields()) // Добавляем элементы словаря
                .saveDictionaryEditor() // Сохранить все изменения
                .verifyDictionaryEditor(directoriesEditor.getDictionaryEditorName()); // Проверяем создание Словаря
    }

    @Override
    public void editDictionaryEditor(DictionaryEditor directoriesEditor) {

    }

    @Override
    public void removeAnDictionaryEditor(DictionaryEditor directoriesEditor) {

    }
}

