package ru.st.selenium.pages.pagesweb.Administration;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.WebLogic.TypesOfTablesEditFormLogic;
import ru.st.selenium.model.Administration.FieldsObject.*;
import ru.st.selenium.model.Administration.TypesOfTables.TypesOfTables;
import ru.st.selenium.model.OpenFilesForEdit;
import ru.st.selenium.model.Administration.TypesOfTables.TypesOfTablesField;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static ru.st.selenium.utils.WaitUtil.waitForPageUntilElementIsVisible;

/**
 * Форма редактирования - Типы таблиц
 */
public class TypesOfTablesEditPage extends TaskTypeListObjectPage implements TypesOfTablesEditFormLogic {


    /**
     * Вкладка - Настройки
     *
     * @FindBy
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[1]")
    private SelenideElement settingsTab;

    /**
     * Способ отображения
     * Иерархический
     */
    @FindBy(xpath = "(//tr[contains(@id,'radiofield')]//input)[1]")
    private SelenideElement rbHierarchical;

    /**
     * Способ отображения
     * Линейный
     */
    @FindBy(xpath = "(//tr[contains(@id,'radiofield')]//input)[2]")
    private SelenideElement rbFlat;

    /**
     * Вкладка - Поля
     *
     * @FindBy
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[2]")
    private SelenideElement fieldsTab;

    /**
     * Вкладка - Обработчики
     *
     * @FindBy
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[3]")
    private SelenideElement handlersTab;

    /**
     * Вкладка - Настройки почтовых уведомлений И Настройки закладок (только для объектов - Справочники И Типы таблиц)
     *
     * @FindBy
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[4]")
    private SelenideElement handlersAndMailNotifySettingsTab;

    /**
     *  ЭЛЕМЕНТЫ - Форма грида редактирования полей
     */
    /**
     * Кнопка - Добавить Поле
     */
    @FindBy(xpath = "//a/span[contains(@id, 'button-10')]")
    private SelenideElement addField;

    /**
     * Нажать кнопку Сохранить объект
     */
    @FindBy(xpath = "//span[@id='button-1010-btnIconEl']")
    private SelenideElement saveObject;


    //-------------------------------------------------------------------------------------- ЭЛЕМЕНТЫ - Форма добавления полей----------------------------------------------------------------

    /**
     * Ввод Названия поля
     */
    @FindBy(id = "dialog_form_name-inputEl")
    private SelenideElement nameField;

    /**
     * Ввод Идентификатора поля
     */
    @FindBy(id = "dialog_form_identifier-inputEl")
    private SelenideElement nameIdentifier;

    /**
     * Выбор Тип поля
     */
    @FindBy(id = "dialog_form_type-inputEl")
    private SelenideElement typeField;

    /**
     * Обязательное поле
     */
    @FindBy(id = "dialog_form_isnecessary-inputEl")
    private SelenideElement obligatoryField;

    /**
     * Клик "Поле:"
     */
    @FindBy(id = "dialog_form_field-inputEl")
    private SelenideElement selectField;

    /**
     * "Выбор из списка" для поля типа "Строка"
     */
    @FindBy(id = "dialog_form_isselect-inputEl")
    private SelenideElement selectionFromList;

    /**
     * Поле - "Список значений" для поля типа "Строка"
     */
    @FindBy(id = "dialog_form_listval-inputEl")
    private SelenideElement fieldListVal;

    /**
     * Клик alert "Ok"
     */
    @FindBy(xpath = "//*[@id='button-1005-btnIconEl']")
    private SelenideElement clicAlertOk;

    /**
     * Сохранить поле
     */
    @FindBy(xpath = "//span[contains(@id,'-btnIconEl')] [ancestor::div[contains(@id,'window-')]]")
    private SelenideElement saveField;


    //------------------------------------------------------------------------------------ Выбор типов полей из выпадающего списка--------------------------------------------------------------

    /**
     * Строка
     */
    @FindBy(xpath = "//li[text()='Строка']")
    private SelenideElement typeFieldString;

    /**
     * Текст
     */
    @FindBy(xpath = "//li[text()='Текст']")
    private SelenideElement typeFieldText;

    /**
     * Целое
     */
    @FindBy(xpath = "//li[text()='Целое']")
    private SelenideElement typeFieldInteger;

    /**
     * Ссылка на объект
     */
    @FindBy(id = "dialog_form_ref_type-inputEl")
    private SelenideElement linkObject;

    /**
     * Вещественное
     */
    @FindBy(xpath = "//li[text()='Вещественное']")
    private SelenideElement typeFieldDouble;

    /**
     * Дата
     */
    @FindBy(xpath = "//li[text()='Дата']")
    private SelenideElement typeFieldData;

    /**
     * Файл
     */
    @FindBy(xpath = "//li[text()='Файл']")
    private SelenideElement typeFieldFile;

    /**
     * Открывать файлы для редактирования
     */
    @FindBy(css = "#dialog_form_files_edit-inputEl")
    private SelenideElement openFileForEdit;

    /**
     * Cсылка на справочник
     */
    @FindBy(xpath = "//li[text()='Cсылка на справочник']")
    private SelenideElement typeFieldReferenceToTheDictionary;

    /**
     * клик в поле "Справочник:"
     */
    @FindBy(css = "#dialog_form_dictionary-inputEl")
    private SelenideElement clickFieldDictionary;

    /**
     * Множественная ссылка на справочник
     */
    @FindBy(xpath = "//li[text()='Множественная ссылка на справочник']")
    private SelenideElement typeFieldMultipleReferenceToTheDictionary;

    /**
     * Логический
     */
    @FindBy(xpath = "//li[text()='Логический']")
    private SelenideElement typeFieldBoolean;

    /**
     * Телефон
     */
    @FindBy(xpath = "//li[text()='Телефон']")
    private SelenideElement typeFieldPhone;

    /**
     * Email
     */
    @FindBy(xpath = "//li[text()='Email']")
    private SelenideElement typeFieldEmail;

    /**
     * Изображение
     */
    @FindBy(xpath = "//li[text()='Изображение']")
    private SelenideElement typeFieldImage;

    /**
     * Цвет
     */
    @FindBy(xpath = "//li[text()='Цвет']")
    private SelenideElement typeFieldColor;

    /**
     * Подразделение
     */
    @FindBy(xpath = "//li[text()='Подразделение']")
    private SelenideElement typeFieldDepartment;

    //-------------------------------------------------------------------------------------вкладка - НАСТРОЙКИ-------------------------------------------------------

    /**
     * Выбор вкладки - Настройки
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage clickSettingsTab() {
        settingsTab.click();
        return this;
    }

    /**
     * Выбор вкладки - Поля
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage clickFieldsTab() {
        fieldsTab.click();
        return this;
    }

    /**
     * Способ отображения
     */
    public TypesOfTablesEditPage selMappingDevice(boolean flat) {
        if (flat) {
            rbFlat.click();
        } else if (!flat) {
            rbHierarchical.click();
        }
        return this;
    }

    //--------------------------------------------------------------------------------------Выбор Типов полей из выпадающего списка-------------------------------------

    /**
     * СТРОКА
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldString() {
        typeFieldString.click();
        return this;
    }

    /**
     * ТЕКСТ
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldText() {
        typeFieldText.click();
        return this;
    }

    /**
     * ЦЕЛОЕ
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldInteger() {
        typeFieldInteger.click();
        return this;
    }

    /**
     * Ссылка на объект
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selLinkObject(boolean linkObj) {
        if (linkObj) {
            linkObject.click();
            selectingSecondAdjustmentPosition();
        }
        return this;
    }

    /**
     * ВЕЩЕСТВЕННОЕ
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldDouble() {
        typeFieldDouble.click();
        return this;
    }

    /**
     * ДАТА
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldDate() {
        typeFieldData.click();
        return this;
    }

    /**
     * ФАЙЛ
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldFile() {
        typeFieldFile.click();
        return this;
    }

    /**
     * выбор поля - Открывать файлы для редактирования
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selFieldOpenFileForEdit() {
        openFileForEdit.click();
        return this;
    }

    /**
     * выбор значения поля - Открывать файлы для редактирования
     */
    public TypesOfTablesEditPage selOpenFilesForEdit(OpenFilesForEdit filesForEdit) {
        selFieldOpenFileForEdit(); // выюбираем поле - Открывать файлы для редактирования
        if (filesForEdit == OpenFilesForEdit.NULL) {
        } else if (filesForEdit == OpenFilesForEdit.NO) {
            selectingSecondAdjustmentPosition();
        } else if (filesForEdit == OpenFilesForEdit.YES) {
            selectingThirdAdjustmentPosition();
        }
        return this;
    }

    /**
     * ССЫЛКА НА СПРАВОЧНИК
     *
     * @return DirectoryEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldDirectory() {
        typeFieldReferenceToTheDictionary.click();
        return this;
    }

    /**
     * Клик в поле "Справочник:"
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selFieldDirectory() {
        clickFieldDictionary.click();
        return this;
    }

    /**
     * Клик в поле "Поле:" справочника
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectField() {
        sleep(300);
        selectField.click();
        return this;
    }

    /**
     * Выбор спр-ка из списка Спр-ов (поле - Справочник)
     *
     * @param directoryName
     * @return TaskTypesEditPage
     */
    public TypesOfTablesEditPage chooseDirectory(String directoryName) {
        clickFieldDictionary.click();
        $(By.xpath("//*[text()='" + directoryName + "']")).click();
        return this;
    }

    /**
     * Выбор поля ср-ка из списка ("Поле:" справочника)
     *
     * @param directoryFieldName
     * @return TaskTypesEditPage
     */
    public TypesOfTablesEditPage chooseFieldDirectoryAndTable(String directoryFieldName) {
        sleep(300);
        selectField.click();
        scrollToAndClick("//*[text()='" + directoryFieldName + "']");
        return this;
    }


    /**
     * МНОЖЕСТВЕННАЯ ССЫЛКА
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldMultipleDictionary() {
        typeFieldMultipleReferenceToTheDictionary.click();
        return this;
    }

    /**
     * ЛОГИЧЕСКОЕ
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldBoolean() {
        typeFieldBoolean.click();
        return this;
    }

    /**
     * ТЕЛЕФОН
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldPhone() {
        typeFieldPhone.click();
        return this;
    }

    /**
     * EMAIL
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldEmail() {
        typeFieldEmail.click();
        return this;
    }

    /**
     * ИЗОБРАЖЕНИЕ
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldImage() {
        typeFieldImage.click();
        return this;
    }

    /**
     * ЦВЕТ
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selectTypeFieldColor() {
        typeFieldColor.click();
        return this;
    }

    /**
     * ПОДРАЗДЕЛЕНИЕ
     *
     * @return DirectoriesEditFormPage
     */
    public TypesOfTablesEditPage selectTypeFieldDepartment() {
        typeFieldDepartment.click();
        return this;
    }

    /**
     * Клик кнопка - Добавить поле
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage addFieldTypesOfTables() {
        addField.click();
        return this;
    }

    /**
     * Поле - Название поля
     *
     * @param text
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage adNameField(String text) {
        nameField.clear();
        nameField.sendKeys(text);
        return this;
    }

    /**
     * Идентификатор поля
     *
     * @param identifier
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage adFieldID(String identifier) {
        nameIdentifier.clear();
        nameIdentifier.sendKeys(identifier);
        return this;
    }

    /**
     * Выбор Типа поля
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selTypeField() {
        typeField.click();
        return this;
    }

    /**
     * Обязательное поле
     *
     * @param oblField
     * @return
     */
    public TypesOfTablesEditPage selObligatoryField(boolean oblField) {
        if (oblField) {
            obligatoryField.click();
            selectingSecondAdjustmentPosition();
        }
        return this;
    }

    /**
     * Выбор из списка
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selFromList(boolean fromList) {
        if (fromList) {
            selectionFromList.click();
            selectingSecondAdjustmentPosition();
        }
        return this;
    }

    /**
     * Список значений
     *
     * @param listVal
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage selListVal(String listVal) {
        fieldListVal.click();
        fieldListVal.sendKeys(listVal);
        return this;
    }

    /**
     * Сохранить поле объекта
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage clickSaveField() {
        saveField.click();
        return this;
    }

    /**
     * Проверяем отображение в гриде полей
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage verifyFieldInGrid(String fieldName) {
        waitForPageUntilElementIsVisible(
                By.xpath("//table[contains(@id,'gridview')]//td[1]/div[text()='" + fieldName + "']"),
                5000);
        return this;
    }

    /**
     * Сохранить объект
     *
     * @return TypesOfTablesEditPage
     */
    public TypesOfTablesEditPage clickSaveObject() {
        saveObject.click();
        checkingMessagesConfirmationOfTheObject($(By.xpath("//div[count(div)=3]/div[2]//div[contains(@id,'messagebox') and (@data-errorqtip)]")),
                "Изменения сохранены", clicAlertOk);
        return this;
    }

    /**
     * Метод добавления всех типов полей - Типы таблиц
     *
     * @param fieldsTypesOfTables массив полей объекта - Типы таблиц, с предопределенными настройками к полям
     * @return DirectoriesEditFormPage
     */
    public TypesOfTablesEditPage addAllFieldsTypesOfTables(TypesOfTablesField[] fieldsTypesOfTables) {
        if (fieldsTypesOfTables == null) {
            return null;
        } else
            outer:
                    for (TypesOfTablesField fieldTypesOfTables : fieldsTypesOfTables) {
                        addFieldTypesOfTables(); // Добавить поле
                        adNameField(fieldTypesOfTables.getFieldName()); // заполняем Название поля документа из массива
                        adFieldID(fieldTypesOfTables.getFieldID()); // заполняем Идентификатор поля из массива
                        selTypeField(); // Выбор поля - Тип поля
                        // 1. СТРОКА
                        if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsString) {
                            selectTypeFieldString();
                            TypeListFieldsString fieldString = (TypeListFieldsString) fieldTypesOfTables.getFieldType();
                            if (fieldString.getIsListChoice()) {
                                selFromList(fieldString.getIsListChoice()); // Выбор из списка
                                selListVal(fieldString.getValuesList()); // Список значений

                            } else if (!fieldString.getIsListChoice()) {
                                selListVal(fieldString.getValuesList()); // Список значений
                            }
                            // 2. ТЕКСТ
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsText) {
                            selectTypeFieldText();
                            TypeListFieldsText fieldText = (TypeListFieldsText) fieldTypesOfTables.getFieldType();
                            // 3. ЦЕЛОЕ
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsInt) {
                            selectTypeFieldInteger();
                            TypeListFieldsInt fieldInt = (TypeListFieldsInt) fieldTypesOfTables.getFieldType();
                            if (fieldInt.getObjectLink()) {
                                selLinkObject(fieldInt.getObjectLink()); // Ссылка на объект
                            }
                            // 4. ВЕЩЕСТВЕННОЕ
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsDouble) {
                            selectTypeFieldDouble();
                            TypeListFieldsDouble fieldsDouble = (TypeListFieldsDouble) fieldTypesOfTables.getFieldType();
                            // 5. ДАТА
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsDate) {
                            selectTypeFieldDate();
                            TypeListFieldsDate fieldsDate = (TypeListFieldsDate) fieldTypesOfTables.getFieldType();
                            // 6. ФАЙЛ
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsFile) {
                            selectTypeFieldFile();
                            TypeListFieldsFile fieldsFile = (TypeListFieldsFile) fieldTypesOfTables.getFieldType();
                            if (fieldsFile.getOpenFilesForEdit() == OpenFilesForEdit.YES || fieldsFile.getOpenFilesForEdit() == OpenFilesForEdit.NO) {
                                selOpenFilesForEdit(fieldsFile.getOpenFilesForEdit());
                            }
                            // 7. СПРАВОЧНИК
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsDirectory) {
                            selectTypeFieldDirectory();
                            TypeListFieldsDirectory fieldsDir = (TypeListFieldsDirectory) fieldTypesOfTables.getFieldType();
                            selFieldDirectory(); // Выбор поля: Спр-к
                            scrollToAndClick("//*[text()='" + fieldsDir.getDirectoryName() + "']"); // выбор - Спр-ка из списка справочников
                            selectField(); // Выбор поля: Поля
                            scrollToAndClick("//li[text()='" + fieldsDir.getNameDirectoryField() + "']"); // выбор поля спр-ка
                            // 8. МНОЖЕСТВЕННАЯ ССЫЛКА НА СПР-К
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsMultiDirectory) {
                            selectTypeFieldMultipleDictionary();
                            TypeListFieldsMultiDirectory fieldsMultiDir = (TypeListFieldsMultiDirectory) fieldTypesOfTables.getFieldType();
                            chooseDirectory(fieldsMultiDir.getDirectories().getDirectoryName()); // Выбор спр-ка
                            chooseFieldDirectoryAndTable(fieldsMultiDir.getFieldDirectory().getFieldName()); // Выбор поля спр-ка
                            // 8. ЛОГИЧЕСКОЕ
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsBoolean) {
                            selectTypeFieldBoolean();
                            TypeListFieldsBoolean fieldsDate = (TypeListFieldsBoolean) fieldTypesOfTables.getFieldType();
                            // 9. ТЕЛЕФОН
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsPhone) {
                            selectTypeFieldPhone();
                            TypeListFieldsPhone fieldsPhone = (TypeListFieldsPhone) fieldTypesOfTables.getFieldType();
                            // 10. EMAIL
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsEmail) {
                            selectTypeFieldEmail();
                            TypeListFieldsEmail fieldsEmail = (TypeListFieldsEmail) fieldTypesOfTables.getFieldType();
                            // 11. ИЗОБРАЖЕНИЕ
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsImage) {
                            selectTypeFieldImage();
                            TypeListFieldsImage fieldsImage = (TypeListFieldsImage) fieldTypesOfTables.getFieldType();
                            // 12. ЦВЕТ
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsColor) {
                            selectTypeFieldColor();
                            TypeListFieldsColor fieldsColor = (TypeListFieldsColor) fieldTypesOfTables.getFieldType();
                            // 13. ПОДРАЗДЕЛЕНИЕ
                        } else if (fieldTypesOfTables.getFieldType() instanceof TypeListFieldsDepartment) {
                            selectTypeFieldDepartment();
                            TypeListFieldsDepartment fieldsDepartment = (TypeListFieldsDepartment) fieldTypesOfTables.getFieldType();
                        }


                        selObligatoryField(fieldTypesOfTables.getObligatory()); // Обязательное поле
                        clickSaveField(); // Сохранить поле
                        verifyFieldInGrid(fieldTypesOfTables.getFieldName()); // Проверяем отображение добавленного поля в гриде

                    }


        return this;
    }


    /**
     * Ожидание появления элементов на вкладке - Поля
     */
    public TypesOfTablesEditPage waitingElementsTabFieldTypesOfTab() {
        $(By.xpath("//a/span[contains(@id, 'button-10')]")).shouldBe(Condition.present);
        $(By.id("bEditField-btnIconEl")).shouldBe(Condition.visible);
        $(By.id("bDeleteField-btnIconEl")).shouldBe(Condition.visible);
        return this;
    }


    /**
     * Добавление полей Типа таблицы
     *
     * @param typesOfTables
     */
    @Override
    public void addSettingsAndFieldTypesOfTables(TypesOfTables typesOfTables) {
        $$(By.xpath("//div[count(a)=4]/a//text()//..")).shouldBe(CollectionCondition.size(4)); // проверка отображения вкладок в форме редактирования Спр-ка
        clickFieldsTab() // Выбираем вкладку Поля
                .waitingElementsTabFieldTypesOfTab() // Ожидаем появление элементов на вкладке Поля
                .addAllFieldsTypesOfTables(typesOfTables.getTypesOfTablesFields()) // Добавление всех типов полей объекта
                .clickSettingsTab() // выбор вкладки Настройки
                .clickSaveObject() // Сохранить объект
                .verifyCreateObject(typesOfTables.getTableTypeName()); // Проверяем отображение созданного объекта в гриде
    }

    @Override
    public void editFieldTypesOfTables(TypesOfTables typesOfTables) {

    }

    @Override
    public void removeAnFieldTypesOfTables(TypesOfTables typesOfTables) {

    }
}