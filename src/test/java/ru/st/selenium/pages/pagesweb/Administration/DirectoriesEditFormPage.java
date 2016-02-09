package ru.st.selenium.pages.pagesweb.Administration;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.WebLogic.DirectoriesEditFormLogic;
import ru.st.selenium.model.Administration.Directories.Directories;
import ru.st.selenium.model.Administration.Directories.DirectoriesField;
import ru.st.selenium.model.Administration.FieldsObject.*;
import ru.st.selenium.model.OpenFilesForEdit;

import static com.codeborne.selenide.Selenide.*;


/**
 * Форма редактирования объекта - Справочники
 */
public class DirectoriesEditFormPage extends TaskTypeListObjectPage implements DirectoriesEditFormLogic {

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

    /**
     * Нажать кнопку ОК при сохранении объекта
     */
    @FindBy(xpath = "//div[count(div)=3]/div[3]//div[count(a)=4]/a[1]//span[position()=2]")
    private SelenideElement clickOkAlert;


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
     * Сохранить поле
     */
    @FindBy(xpath = "(//span[contains(@id,'-btnIconEl')] [ancestor::div[contains(@id,'toolbar-') and contains(@class,'x-toolbar')]])[9]")
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
     * Ссылка на справочник
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

    /**
     * Подразделение
     */
    @FindBy(xpath = "//li[text()='Подразделение']")
    private SelenideElement typeFieldDepartment;


    //-------------------------------------------------------------------------------------вкладка - НАСТРОЙКИ-------------------------------------------------------

    /**
     * Выбор вкладки - Настройки
     *
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage clickSettingsTab() {
        $(settingsTab).shouldBe(Condition.present).click();
        return this;
    }

    /**
     * Выбор вкладки - Поля
     *
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage clickFieldsTab() {
        $(fieldsTab).shouldBe(Condition.present).click();
        return this;
    }

    /**
     * Общедоступность записей
     *
     * @param shareRecords
     * @return
     */
    public DirectoriesEditFormPage selShareRecords(boolean shareRecords) {
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
    public DirectoriesEditFormPage selRecordAccessSetting(boolean recAccessSetting) {
        if (recAccessSetting) {
            this.recordAccessSetting.click();
        }
        return this;
    }

    /**
     * Способ отображения
     */
    public DirectoriesEditFormPage selMappingDevice(boolean flat) {
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
    public DirectoriesEditFormPage selSearchSettings(boolean searchSystem) {
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
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage selTypeField() {
        typeField.click();
        return this;
    }

    /**
     * Клик кнопка - Добавить поле
     *
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage clickAddFieldDirectory() {
        addFieldDirectory.click();
        return this;
    }

    /**
     * Поле - Название поля
     *
     * @param text
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage adNameField(String text) {
        nameField.clear();
        nameField.setValue(text);
        return this;
    }

    /**
     * Идентификатор поля
     *
     * @param identifier
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage adFieldID(String identifier) {
        nameIdentifier.clear();
        nameIdentifier.setValue(identifier);
        return this;
    }

    /**
     * Обязательное поле
     *
     * @param oblField
     * @return
     */
    public DirectoriesEditFormPage selObligatoryField(boolean oblField) {
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
    public DirectoriesEditFormPage selUniqueField(boolean uniqField) {
        if (uniqField) {
            uniqueField.click();
            selectingSecondAdjustmentPosition();
        }
        return this;
    }

    /**
     * Выбор из списка
     *
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage selFromList(boolean fromList) {
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
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage selListVal(String listVal) {
        fieldListVal.click();
        fieldListVal.setValue(listVal);
        return this;
    }

    /**
     * Сохранить поле объекта
     *
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage clickSaveField() {
        saveField.click();
        return this;
    }

    /**
     * Сохранить объект
     *
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage clickSaveObject() {
        saveObject.click();
        checkingMessagesConfirmationOfTheObject($(By.xpath("//div[count(div)=3]/div[2]//div[contains(@id,'messagebox') and (@data-errorqtip)]")),
                "Изменения сохранены", clickOkAlert);
        return this;
    }

    /**
     * Проверяем отображение в гриде полей
     *
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage verifyFieldInGrid(String fieldName) {
        $(By.xpath("//table[contains(@id,'gridview')]//td[1]/div[text()='" + fieldName + "']")).shouldBe(Condition.visible);
        return this;
    }

    //-----------------------------------------------------------------------------------------Выбор Типов полей из выпадающего списка-------------------------------------

    /**
     * Выбор типа поля объекта
     *
     * @param typeOfField передаваемое значение типа поля
     * @return FormDocRegisterCardsEditPage
     */
    public DirectoriesEditFormPage selectTheTypeOfField(SelenideElement typeOfField) {
        typeOfField.click();
        return this;
    }

    /**
     * выбор поля - Открывать файлы для редактирования
     *
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage selFieldOpenFileForEdit() {
        openFileForEdit.click();
        return this;
    }

    /**
     * выбор значения поля - Открывать файлы для редактирования
     */
    public DirectoriesEditFormPage selOpenFilesForEdit(OpenFilesForEdit filesForEdit) {
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
     * Клик в поле "Справочник:"
     *
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage selFieldDirectory() {
        typeDictionary.click();
        return this;
    }

    /**
     * Клик в поле "Поле:" справочника
     *
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage selectField() {
        sleep(300);
        $(selectField).click();
        return this;
    }


    /**
     * Метод добавления всех типов полей - Справочник
     *
     * @param fieldsDirectory массив полей объекта - Справочник, с предопределенными настройками к полям
     * @return DirectoriesEditFormPage
     */
    public DirectoriesEditFormPage addAllFieldsDirectory(DirectoriesField[] fieldsDirectory) {
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
                            selectTheTypeOfField(typeFieldString);
                            TypeListFieldsString fieldString = (TypeListFieldsString) fieldDirectory.getFieldType();
                            if (fieldString.getIsListChoice()) {
                                selFromList(fieldString.getIsListChoice()); // Выбор из списка
                                selListVal(fieldString.getValuesList()); // Список значений
                            } else if (!fieldString.getIsListChoice()) {
                                selListVal(fieldString.getValuesList()); // Список значений
                            }
                            // 2. ТЕКСТ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsText) {
                            selectTheTypeOfField(typeFieldText);
                            TypeListFieldsText fieldText = (TypeListFieldsText) fieldDirectory.getFieldType();
                            // 3. ЦЕЛОЕ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsInt) {
                            selectTheTypeOfField(typeFieldInteger);
                            TypeListFieldsInt fieldInt = (TypeListFieldsInt) fieldDirectory.getFieldType();
                            // 4. ВЕЩЕСТВЕННОЕ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsDouble) {
                            selectTheTypeOfField(typeFieldDouble);
                            TypeListFieldsDouble fieldsDouble = (TypeListFieldsDouble) fieldDirectory.getFieldType();
                            // 5. ДАТА
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsDate) {
                            selectTheTypeOfField(typeFieldData);
                            TypeListFieldsDate fieldsDate = (TypeListFieldsDate) fieldDirectory.getFieldType();
                            // 6. ФАЙЛ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsFile) {
                            selectTheTypeOfField(typeFieldFile);
                            TypeListFieldsFile fieldsFile = (TypeListFieldsFile) fieldDirectory.getFieldType();
                            if (fieldsFile.getOpenFilesForEdit() == OpenFilesForEdit.YES || fieldsFile.getOpenFilesForEdit() == OpenFilesForEdit.NO) {
                                selOpenFilesForEdit(fieldsFile.getOpenFilesForEdit());
                            }
                            // 7. СПРАВОЧНИК
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsDirectory) {
                            selectTheTypeOfField(typeFieldReferenceToTheDictionary);
                            TypeListFieldsDirectory fieldsDir = (TypeListFieldsDirectory) fieldDirectory.getFieldType();
                            selFieldDirectory(); // Выбор поля: Спр-к
                            scrollToAndClick("//*[text()='" + fieldsDir.getDirectoryName() + "']"); // выбор - Спр-ка из списка справочников
                            selectField(); // Выбор поля: Поля
                            scrollToAndClick("//li[text()='" + fieldsDir.getNameDirectoryField() + "']"); // выбор поля спр-ка
                            // 8. МНОЖЕСТВЕННАЯ ССЫЛКА НА СПР-К
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsMultiDirectory) {
                            selectTheTypeOfField(typeFieldMultipleReferenceToTheDictionary);
                            TypeListFieldsMultiDirectory fieldsMultiDir = (TypeListFieldsMultiDirectory) fieldDirectory.getFieldType();
                            selFieldDirectory();
                            scrollToAndClick("//*[text()='" + fieldsMultiDir.getDirectoryName() + "']");
                            selectField();
                            scrollToAndClick("//li[text()='" + fieldsMultiDir.getNameDirectoryField() + "']");
                            // 8. ЛОГИЧЕСКОЕ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsBoolean) {
                            selectTheTypeOfField(typeFieldBoolean);
                            TypeListFieldsBoolean fieldsDate = (TypeListFieldsBoolean) fieldDirectory.getFieldType();
                            // 9. ТЕЛЕФОН
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsPhone) {
                            selectTheTypeOfField(typeFieldPhone);
                            TypeListFieldsPhone fieldsPhone = (TypeListFieldsPhone) fieldDirectory.getFieldType();
                            // 10. EMAIL
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsEmail) {
                            selectTheTypeOfField(typeFieldEmail);
                            TypeListFieldsEmail fieldsEmail = (TypeListFieldsEmail) fieldDirectory.getFieldType();
                            // 11. ИЗОБРАЖЕНИЕ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsImage) {
                            selectTheTypeOfField(typeFieldImage);
                            TypeListFieldsImage fieldsImage = (TypeListFieldsImage) fieldDirectory.getFieldType();
                            // 12. ЦВЕТ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsColor) {
                            selectTheTypeOfField(typeFieldColor);
                            TypeListFieldsColor fieldsColor = (TypeListFieldsColor) fieldDirectory.getFieldType();
                            // 14. ВЛОЖЕННЫЙ СПРАВОЧНИК
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsEnclosedDirectory) {
                            selectTheTypeOfField(typeFieldEnclosedDirectory);
                            TypeListFieldsEnclosedDirectory fieldsEnclosedDirectory = (TypeListFieldsEnclosedDirectory) fieldDirectory.getFieldType();
                            selFieldDirectory(); // Выбор поля Спр-к
                            scrollToAndClick("//*[text()='" + fieldsEnclosedDirectory.getDirectoryName() + "']");
                            sleep(500);
                            // 15. ПОДРАЗДЕЛЕНИЕ
                        } else if (fieldDirectory.getFieldType() instanceof TypeListFieldsDepartment) {
                            selectTheTypeOfField(typeFieldDepartment);
                            TypeListFieldsDepartment fieldsDepartment = (TypeListFieldsDepartment) fieldDirectory.getFieldType();
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
    public DirectoriesEditFormPage waitingElementsTabField() {
        $(By.xpath("//a/span[contains(@id, 'button-10')]")).shouldBe(Condition.present);
        $(By.id("bEditField-btnIconEl")).shouldBe(Condition.present);
        $(By.id("bDeleteField-btnIconEl")).shouldBe(Condition.present);
        return this;
    }

    /**
     * Добавление полей Справочника
     *
     * @param directories
     */
    @Override
    public void addSettingsAndFieldDirectories(Directories directories) {
        $$(By.xpath("//div[count(a)=4]/a//text()//..")).shouldBe(CollectionCondition.size(4)); // проверка отображения вкладок в форме редактирования Спр-ка
        clickFieldsTab(); // Выбираем вкладку Поля
        waitingElementsTabField() // Ожидаем появления элементов на вкладке "Поля"
                .addAllFieldsDirectory(directories.getDirectoriesFields()) // Добавление типов полей
                .clickSettingsTab() // выбор вкладки Настройки
                .selShareRecords(directories.getShareRecords()) // Общедоступность записей
                .selRecordAccessSetting(directories.getAccessToRecords()) // Настройка доступа к записям
                .selMappingDevice(directories.getMappingDevice()) // Способ отображения
                .selSearchSettings(directories.getSearchSettings()) // Настройка поиска
                .clickSaveObject() // Сохранить объект
                .verifyCreateObject(directories.getDirectoryName()); // Проверяем отображение сохраненного объекта в гриде Спр-ов
    }


    @Override
    public void editFieldDirectories(Directories directories) {

    }

    @Override
    public void removeAnFieldDirectories(Directories directories) {

    }


}
