package ru.st.selenium.pages.pagesweb.Administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.model.Administration.Directories.DirectoriesField;
import ru.st.selenium.model.Administration.FieldsObject.*;
import ru.st.selenium.model.OpenFilesForEdit;

import static com.codeborne.selenide.Selenide.$;

/**
 * Форма редактирования - Справочники
 */
public class DirectoryEditPage extends TaskTypeListObjectPage {


    /**
     * Вкладка - Настройки
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[1]")
    private SelenideElement settingsTab;

    /**
     * Общедоступность записей
     */
    @FindBy(xpath = "//input[@id='quotient-inputEl']")
    private SelenideElement recordsAccessibility;

    /**
     * Настройка доступа к записям
     */
    @FindBy(css = "#visible_quotient-inputEl")
    private SelenideElement recordAccessSetting;

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
     * Настройки поиска
     * Использовать поисковую систему
     */
    @FindBy(xpath = "(//input[contains(@id,'search_type')])[1]")
    private SelenideElement useSearchSystem;

    /**
     * Настройки поиска
     * Использовать БД
     */
    @FindBy(xpath = "(//input[contains(@id,'search_type')])[2]")
    private SelenideElement useSearchBD;

    /**
     * Вкладка - Поля
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[2]")
    private SelenideElement fieldsTab;

    /**
     * Вкладка - Обработчики
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[3]")
    private SelenideElement handlersTab;

    /**
     * Настройки закладок
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
    private SelenideElement addFieldDirectory;

    /**
     * Нажать кнопку Сохранить объект
     */
    @FindBy(xpath = "//span[@id='button-1010-btnIconEl']")
    private SelenideElement saveObject;


    //-------------------------------------------------------------------------------------- ЭЛЕМЕНТЫ - Форма добавления полей----------------------------------------------------------------

    /**
     * Ввод Названия поля
     */
    @FindBy(css = "#dialog_form_name-inputEl")
    private SelenideElement nameField;

    /**
     * Ввод Идентификатора поля
     */
    @FindBy(css = "#dialog_form_identifier-inputEl")
    private SelenideElement nameIdentifier;

    /**
     * Выбор Тип поля
     */
    @FindBy(css = "#dialog_form_type-inputEl")
    private SelenideElement typeField;

    /**
     * Обязательное поле
     */
    @FindBy(css = "#dialog_form_isnecessary-inputEl")
    private SelenideElement obligatoryField;

    /**
     * Уникальное поле
     */
    @FindBy(css = "#dialog_form_is_unique-inputEl")
    private SelenideElement uniqueField;

    /**
     * Клик "Поле:"
     */
    @FindBy(css = "#dialog_form_field-inputEl")
    private SelenideElement selectField;

    /**
     * "Выбор из списка" для поля типа "Строка"
     */
    @FindBy(css = "#dialog_form_isselect-inputEl")
    private SelenideElement selectionFromList;

    /**
     * Поле - "Список значений" для поля типа "Строка"
     */
    @FindBy(css = "#dialog_form_listval-inputEl")
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
    @FindBy(id = "dialog_form_files_edit-inputEl")
    private SelenideElement openFileForEdit;

    /**
     * Cсылка на справочник
     */
    @FindBy(xpath = "//li[text()='Cсылка на справочник']")
    private SelenideElement typeFieldReferenceToTheDictionary;

    /**
     * поле "Справочник:"
     */
    @FindBy(id = "dialog_form_dictionary-inputEl")
    private SelenideElement typeDictionary;

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
     * Вложенный справочник
     */
    @FindBy(xpath = "//li[text()='Вложенный справочник']")
    private SelenideElement typeFieldEnclosedDirectory;


    //-------------------------------------------------------------------------------------вкладка - НАСТРОЙКИ-------------------------------------------------------

    /**
     * Выбор вкладки - Настройки
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage clickSettingsTab() {
        settingsTab.click();
        return this;
    }

    /**
     * Выбор вкладки - Поля
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage clickFieldsTab() {
        fieldsTab.click();
        return this;
    }

    /**
     * Общедоступность записей
     *
     * @param shareRecords
     * @return
     */
    public DirectoryEditPage selShareRecords(boolean shareRecords) {
        if (shareRecords) {
            this.recordsAccessibility.click();
        }
        return this;
    }

    /**
     * Настройка доступа к записям
     *
     * @param recAccessSetting
     * @return
     */
    public DirectoryEditPage selRecordAccessSetting(boolean recAccessSetting) {
        if (recAccessSetting) {
            this.recordAccessSetting.click();
        }
        return this;
    }

    /**
     * Способ отображения
     */
    public DirectoryEditPage selMappingDevice(boolean flat) {
        if (flat) {
            rbFlat.click();
        } else if (!flat) {
            rbHierarchical.click();
        }
        return this;
    }

    /**
     * Настройка поиска
     */
    public DirectoryEditPage selSearchSettings(boolean searchSystem) {
        if (searchSystem) {
            useSearchSystem.click();
        } else if (!searchSystem) {
            useSearchBD.click();
        }

        return this;
    }

    //------------------------------------------------------------------Форма редактирования полей-----------------------------------------------------

    /**
     * Выбор Типа поля
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selTypeField() {
        typeField.click();
        return this;
    }

    /**
     * Клик кнопка - Добавить поле
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage clickAddFieldDirectory() {
        addFieldDirectory.click();
        return this;
    }

    /**
     * Поле - Название поля
     *
     * @param text
     * @return DirectoryEditPage
     */
    public DirectoryEditPage adNameField(String text) {
        nameField.clear();
        nameField.sendKeys(text);
        return this;
    }

    /**
     * Идентификатор поля
     *
     * @param identifier
     * @return DirectoryEditPage
     */
    public DirectoryEditPage adFieldID(String identifier) {
        nameIdentifier.clear();
        nameIdentifier.sendKeys(identifier);
        return this;
    }

    /**
     * Обязательное поле
     *
     * @param oblField
     * @return
     */
    public DirectoryEditPage selObligatoryField(boolean oblField) {
        if (oblField) {
            obligatoryField.click();
            selectingSecondAdjustmentPosition();
        }
        return this;
    }

    /**
     * Уникальное поле
     *
     * @param uniqField
     * @return
     */
    public DirectoryEditPage selUniqueField(boolean uniqField) {
        if (uniqField) {
            uniqueField.click();
            selectingSecondAdjustmentPosition();
        }
        return this;
    }

    /**
     * Выбор из списка
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selFromList(boolean fromList) {
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
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selListVal(String listVal) {
        fieldListVal.click();
        fieldListVal.sendKeys(listVal);
        return this;
    }

    /**
     * Сохранить поле объекта
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage clickSaveField() {
        saveField.click();
        return this;
    }

    /**
     * Сохранить объект
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage clickSaveObject() {
        saveObject.click();
        clicAlertOk();
        return this;
    }

    /**
     * Проверяем отображение в гриде полей
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage verifyFieldInGrid(String fieldName) {
        waitForPageUntilElementIsVisible(
                By.xpath("//table[contains(@id,'gridview')]//td[1]/div[text()='" + fieldName + "']"),
                5000);
        return this;
    }

    //-----------------------------------------------------------------------------------------Выбор Типов полей из выпадающего списка-------------------------------------

    /**
     * СТРОКА
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldString() {
        typeFieldString.click();
        return this;
    }

    /**
     * ТЕКСТ
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldText() {
        typeFieldText.click();
        return this;
    }

    /**
     * ЦЕЛОЕ
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldInteger() {
        typeFieldInteger.click();
        return this;
    }

    /**
     * ВЕЩЕСТВЕННОЕ
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldDouble() {
        typeFieldDouble.click();
        return this;
    }

    /**
     * ДАТА
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldDate() {
        typeFieldData.click();
        return this;
    }

    /**
     * ФАЙЛ
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldFile() {
        typeFieldFile.click();
        return this;
    }

    /**
     * выбор поля - Открывать файлы для редактирования
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selFieldOpenFileForEdit() {
        openFileForEdit.click();
        return this;
    }

    /**
     * выбор значения поля - Открывать файлы для редактирования
     */
    public DirectoryEditPage selOpenFilesForEdit(OpenFilesForEdit filesForEdit) {
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
    public DirectoryEditPage selectTypeFieldDirectory() {
        typeFieldReferenceToTheDictionary.click();
        return this;
    }

    /**
     * Клик в поле "Справочник:"
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selFieldDirectory() {
        typeDictionary.click();
        return this;
    }

    /**
     * Клик в поле "Поле:" справочника
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectField() {
        waitMillisecond(0.3);
        selectField.click();
        return this;
    }

    /**
     * МНОЖЕСТВЕННАЯ ССЫЛКА
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldMultipleDictionary() {
        typeFieldMultipleReferenceToTheDictionary.click();
        return this;
    }

    /**
     * ЛОГИЧЕСКОЕ
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldBoolean() {
        typeFieldBoolean.click();
        return this;
    }

    /**
     * ТЕЛЕФОН
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldPhone() {
        typeFieldPhone.click();
        return this;
    }

    /**
     * EMAIL
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldEmail() {
        typeFieldEmail.click();
        return this;
    }

    /**
     * ИЗОБРАЖЕНИЕ
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldImage() {
        typeFieldImage.click();
        return this;
    }

    /**
     * ЦВЕТ
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldColor() {
        typeFieldColor.click();
        return this;
    }

    /**
     * ВЛОЖЕННЫЙ СПРАВОЧНИК
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage selectTypeFieldEnclosedDirectory() {
        typeFieldEnclosedDirectory.click();
        return this;
    }

    /**
     * Ожидание появления alert об успешном сохранении - Изменения сохранены;
     * Клик alert
     *
     * @return DirectoryEditPage
     */
    public DirectoryEditPage clicAlertOk() {
        waitForPageUntilElementIsVisible(
                By.xpath("//td[contains(@id,'messagebox')]/div[text()='Изменения сохранены']"), 5000);
        clicAlertOk.click();
        return this;
    }

    /**
     * ----------------------------------------------------------------------------Метод добавления всех типов полей------------------------------------
     *
     * @param fieldsDirectory
     * @return
     */
    public DirectoryEditPage addAllFieldsDirectory(DirectoriesField[] fieldsDirectory) {
        if (fieldsDirectory == null) {
            return null;
        } else
            outer:
                    for (DirectoriesField fieldDirectory : fieldsDirectory) {
                        clickAddFieldDirectory(); // Добавить поле
                        adNameField(fieldDirectory.getFieldName()); // заполняем Название поля документа из массива
                        adFieldID(fieldDirectory.getFieldID()); // заполняем Идентификатор поля из массива
                        selTypeField(); // Выбор поля - Тип поля

                        // 1. СТРОКА
                        if (fieldDirectory.getFieldType() instanceof TypeListFieldsString) {
                            selectTypeFieldString();
                            TypeListFieldsString fieldString = (TypeListFieldsString) fieldDirectory.getFieldType();
                            if (fieldString.getIsListChoice()) {
                                selFromList(fieldString.getIsListChoice()); // Выбор из списка
                                selListVal(fieldString.getValuesList()); // Список значений
                            } else if (!fieldString.getIsListChoice()) {
                                selListVal(fieldString.getValuesList()); // Список значений
                            }
                            // 2. ТЕКСТ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsText) {
                            selectTypeFieldText();
                            TypeListFieldsText fieldText = (TypeListFieldsText) fieldDirectory.getFieldType();
                            // 3. ЦЕЛОЕ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsInt) {
                            selectTypeFieldInteger();
                            TypeListFieldsInt fieldInt = (TypeListFieldsInt) fieldDirectory.getFieldType();
                            // 4. ВЕЩЕСТВЕННОЕ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsDouble) {
                            selectTypeFieldDouble();
                            TypeListFieldsDouble fieldsDouble = (TypeListFieldsDouble) fieldDirectory.getFieldType();
                            // 5. ДАТА
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsDate) {
                            selectTypeFieldDate();
                            TypeListFieldsDate fieldsDate = (TypeListFieldsDate) fieldDirectory.getFieldType();
                            // 6. ФАЙЛ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsFile) {
                            selectTypeFieldFile();
                            TypeListFieldsFile fieldsFile = (TypeListFieldsFile) fieldDirectory.getFieldType();
                            if (fieldsFile.getOpenFilesForEdit() == OpenFilesForEdit.YES || fieldsFile.getOpenFilesForEdit() == OpenFilesForEdit.NO) {
                                selOpenFilesForEdit(fieldsFile.getOpenFilesForEdit());
                            }
                            // 7. СПРАВОЧНИК
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsDirectory) {
                            selectTypeFieldDirectory();
                            TypeListFieldsDirectory fieldsDir = (TypeListFieldsDirectory) fieldDirectory.getFieldType();
                            selFieldDirectory(); // Выбор поля Спр-к
                            $(By.xpath("//*[text()='" + fieldsDir.getDirectoryName() + "']")).click(); // Выбор справочника
                            selectField();
                            $(By.xpath("//li[text()='" + fieldsDir.getNameDirectoryField() + "']")).click(); // Выбор поля справочника
                            // 8. МНОЖЕСТВЕННАЯ ССЫЛКА НА СПР-К
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsMultiDirectory) {
                            selectTypeFieldMultipleDictionary();
                            TypeListFieldsMultiDirectory fieldsMultiDir = (TypeListFieldsMultiDirectory) fieldDirectory.getFieldType();
                            selFieldDirectory(); // Выбор поля Спр-к
                            $(By.xpath("//*[text()='" + fieldsMultiDir.getDirectoryName() + "']")).click();
                            selectField();
                            pressEnter();
                            // 8. ЛОГИЧЕСКОЕ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsBoolean) {
                            selectTypeFieldBoolean();
                            TypeListFieldsBoolean fieldsDate = (TypeListFieldsBoolean) fieldDirectory.getFieldType();
                            // 9. ТЕЛЕФОН
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsPhone) {
                            selectTypeFieldPhone();
                            TypeListFieldsPhone fieldsPhone = (TypeListFieldsPhone) fieldDirectory.getFieldType();
                            // 10. EMAIL
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsEmail) {
                            selectTypeFieldEmail();
                            TypeListFieldsEmail fieldsEmail = (TypeListFieldsEmail) fieldDirectory.getFieldType();
                            // 11. ИЗОБРАЖЕНИЕ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsImage) {
                            selectTypeFieldImage();
                            TypeListFieldsImage fieldsImage = (TypeListFieldsImage) fieldDirectory.getFieldType();
                            // 12. ЦВЕТ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsColor) {
                            selectTypeFieldColor();
                            TypeListFieldsColor fieldsColor = (TypeListFieldsColor) fieldDirectory.getFieldType();
                            // 14. ВЛОЖЕННЫЙ СПРАВОЧНИК
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsEnclosedDirectory) {
                            selectTypeFieldEnclosedDirectory();
                            TypeListFieldsEnclosedDirectory fieldsEnclosedDirectory = (TypeListFieldsEnclosedDirectory) fieldDirectory.getFieldType();
                            selFieldDirectory(); // Выбор поля Спр-к
                            $(By.xpath("//*[text()='" + fieldsEnclosedDirectory.getDirectoryName() + "']")).click();
                            waitMillisecond(0.2);
                        }

                        selObligatoryField(fieldDirectory.getObligatory()); // Обязательное поле
                        selUniqueField(fieldDirectory.getIsUniqueField()); // Уникальное поле

                        clickSaveField(); // Сохранить поле

                        verifyFieldInGrid(fieldDirectory.getFieldName()); // Проверяем отображение добавленного поля в гриде

                    }

        return this;
    }


    /**
     * Ожидание появления элементов на вкладке - Поля
     */
    public DirectoryEditPage waitingElementsTabField() {
        $(By
                .xpath("//a/span[contains(@id, 'button-10')]")).shouldBe(Condition.present);
        $(By.id("bEditField-btnIconEl")).shouldBe(Condition.visible);
        $(By.id("bDeleteField-btnIconEl")).shouldBe(Condition.visible);
        return this;
    }


}