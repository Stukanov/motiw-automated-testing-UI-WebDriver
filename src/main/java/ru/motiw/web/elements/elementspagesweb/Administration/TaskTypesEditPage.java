package ru.motiw.web.elements.elementspagesweb.Administration;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.motiw.web.model.Administration.FieldsObject.*;
import ru.motiw.web.model.Administration.TasksTypes.ObligatoryFieldTypeTask;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.logicinterface.WebLogic.TasksTypesEditFormLogic;
import ru.motiw.web.model.CorrectionMethod;
import ru.motiw.web.model.OpenFilesForEdit;
import ru.motiw.web.model.ShiftDirection;
import ru.motiw.web.model.Administration.TasksTypes.ComputeModeNumerator;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypesField;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.motiw.utils.ElementUtil.selectingFourthlyAdjustmentPosition;
import static ru.motiw.utils.ElementUtil.selectingSecondAdjustmentPosition;
import static ru.motiw.utils.ElementUtil.selectingThirdAdjustmentPosition;
import static ru.motiw.utils.WaitUtil.waitForPageUntilElementIsVisible;
import static ru.motiw.web.model.ShiftDirection.DATE_DOES_NOT_MOVE;
import static ru.motiw.web.model.ShiftDirection.DATE_MOVES_BACK;
import static ru.motiw.web.model.ShiftDirection.DATE_MOVES_FORWARD;

/**
 * Форма редактирования - Типы Задач
 */
public class TaskTypesEditPage extends TaskTypeListObjectPage implements TasksTypesEditFormLogic {


    /*
     * Вкладка - Настройки
     *
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[1]")
    private SelenideElement settingsTab;

    /*
     * Вкладка - Поля
     *
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[2]")
    private SelenideElement fieldsTab;

    /*
     * Вкладка - Обработчики
     *
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[3]")
    private SelenideElement handlersTab;

    /*
     * Вкладка - Настройки почтовых уведомлений И Настройки закладок (только для объектов - Справочники И Типы таблиц)
     *
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[4]")
    private SelenideElement handlersAndMailNotifySettingsTab;

    /*
     * Вкладка - Оценки и доклады
     *
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[5]")
    private SelenideElement estimationsAndReportsTab;

    /*
     * Вкладка - Доступ
     *
     */
    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[6]")
    private SelenideElement accessTab;

    /*
     *  ЭЛЕМЕНТЫ - Форма грида редактирования полей
     */
    /*
     * Кнопка - Добавить Поле
     */
    @FindBy(xpath = "//a/span[contains(@id, 'button-10')]")
    private SelenideElement clickOkAndAddFieldTypesObgect;

    /*
     * Нажать кнопку Сохранить объект
     */
    @FindBy(xpath = "//span[@id='button-1010-btnIconEl']")
    private SelenideElement saveObject;

    /*
     *  ЭЛЕМЕНТЫ - Форма добавления полей
     */
    /*
     * Ввод Названия поля
     */
    @FindBy(css = "#dialog_form_name-inputEl")
    private SelenideElement nameField;

    /**
     * Ввод Идентификатора поля
     */
    @FindBy(css = "#dialog_form_identifier-inputEl")
    private SelenideElement nameIdentifier;

    /*
     * Выбор Типа поля
     */
    @FindBy(css = "#dialog_form_type-inputEl")
    private SelenideElement typeField;

    /*
     * Обязательное поле
     */
    @FindBy(css = "#dialog_form_isnecessary-inputEl")
    private SelenideElement obligatoryField;

    /*
     * Скрывать при поиске
     */
    @FindBy(css = "#dialog_form_ishideinsearch-inputEl")
    private SelenideElement isHideInSearch;

    /*
     * Поле - "Шаблон нумератора:"
     */
    @FindBy(css = "#dialog_form_numerator_template-inputEl")
    private SelenideElement selNumeratorTemp;

    /*
     * Режим вычисления
     */
    @FindBy(css = "#dialog_form_numerator_compute_mode-inputEl")
    private SelenideElement calculatMode;

    /*
     * "Выбор из списка" для поля типа "Строка"
     */
    @FindBy(css = "#dialog_form_isselect-inputEl")
    private SelenideElement selectionFromList;

    /*
     * Поле - "Список значений" для поля типа "Строка"
     */
    @FindBy(css = "#dialog_form_listval-inputEl")
    private SelenideElement fieldListVal;

    /*
     * Клик alert "Ok"
     */
    @FindBy(xpath = "//*[@id='button-1005-btnIconEl']")
    private SelenideElement clickAlertOk;

    /*
     * Сохранить
     */
    @FindBy(xpath = "//span[contains(@id,'-btnIconEl')] [ancestor::div[contains(@id,'window-')]]")
    private SelenideElement saveField;

    //---------------------------------------------------------------------------------------------------- Выбор типов полей из выпадающего списка:

    /*
     * Строка
     */
    @FindBy(xpath = "//li[text()='Строка']")
    private SelenideElement typeFieldString;

    /*
     * Текст
     */
    @FindBy(xpath = "//li[text()='Текст']")
    private SelenideElement typeFieldText;

    /*
     * Целое
     */
    @FindBy(xpath = "//li[text()='Целое']")
    private SelenideElement typeFieldInteger;

    /*
     * Вещественное
     */
    @FindBy(xpath = "//li[text()='Вещественное']")
    private SelenideElement typeFieldDouble;

    /*
     * Дата
     */
    @FindBy(xpath = "//li[text()='Дата']")
    private SelenideElement typeFieldData;

    /*
     * Cсылка на справочник
     */
    @FindBy(xpath = "//li[text()='Cсылка на справочник']")
    private SelenideElement typeFieldReferenceToTheDictionary;

    /*
     * Клик "Справочник:"
     */
    @FindBy(css = "#dialog_form_dictionary-inputEl")
    private SelenideElement clickFieldDictionary;

    /*
     * Клик "Поле:"
     */
    @FindBy(css = "#dialog_form_field-inputEl")
    private SelenideElement selectField;

    /*
     * Шаблон отображения (Справочник/Таблица)
     */
    @FindBy(id = "dialog_form_dictionary_template-inputEl")
    private SelenideElement dicTemplate;

    /*
     * Вычислить новые значения для сохраненных полей? - при вводе Шаблона отображения
     */
    @FindBy(xpath = "//div[contains(@id,'messagebox')]//div[count(a)=4]/a[2]//span[1]")
    private SelenideElement calculatNewValues;

    /*
     * Множественная ссылка на справочник
     */
    @FindBy(xpath = "//li[text()='Множественная ссылка на справочник']")
    private SelenideElement typeFieldMultipleReferenceToTheDictionary;

    /*
     * Логический
     */
    @FindBy(xpath = "//li[text()='Логический']")
    private SelenideElement typeFieldBoolean;

    /*
     * Ссылка на библиотеку
     */
    @FindBy(xpath = "//li[text()='Ссылка на библиотеку']")
    private SelenideElement typeFieldLibrarylink;

    /*
     * Cсылка на задачу
     */
    @FindBy(xpath = "//li[text()='Cсылка на задачу']")
    private SelenideElement typeFieldReferenceTask;

    /*
     * Нумератор
     */
    @FindBy(xpath = "//li[text()='Нумератор']")
    private SelenideElement typeFieldNumerator;

    /*
     * Ссылка на объект
     */
    @FindBy(xpath = "//li[text()='Ссылка на объект']")
    private SelenideElement typeFieldLinkObject;

    /*
     * Таблица
     */
    @FindBy(xpath = "//li[text()='Таблица']")
    private SelenideElement typeFieldTable;

    /*
     * Клик поле "Таблица:"
     */
    @FindBy(css = "#dialog_form_table-inputEl")
    private SelenideElement fieldTable;

    /*
     * Подразделение
     */
    @FindBy(xpath = "//li[text()='Подразделение']")
    private SelenideElement typeFieldDepartment;

    /*
     * Сотрудник
     */
    @FindBy(xpath = "//li[text()='Сотрудник']")
    private SelenideElement typeFieldEmployee;


    //--------------------------------------------------------------------Вкладка - НАСТРОЙКИ---------------------------------------------------------------------------------

    /**
     * Направление смещения даты при попадании на нерабочее время:
     * Дата не меняется
     */
    @FindBy(xpath = "//input[@id='direction-no-inputEl']")
    private SelenideElement clickDateDoesNotMove;


    /**
     * Направление смещения даты при попадании на нерабочее время
     * Дата сдвигается вперед
     */
    @FindBy(xpath = "//input[@id='direction-forward-inputEl']")
    private SelenideElement clickDateMovesForward;

    /**
     * Направление смещения даты при попадании на нерабочее время
     * Дата сдвигается назад
     */
    @FindBy(xpath = "//input[@id='direction-back-inputEl']")
    private SelenideElement clickDateMovesBack;

    /**
     * Использовать ЭЦП
     */
    @FindBy(xpath = "(//input[contains(@id,'checkbox')] [ancestor::div[contains(@id,'tabpanel')]])[1]")
    private SelenideElement useECP;

    /**
     * Корректировка даты
     * Способ корректировки:
     * выбор поля - Способ корректировки (активация списка значений)
     */
    @FindBy(css = "#type_correction-inputEl")
    private SelenideElement typeCorrection;

    /**
     * Способ корректировки:
     * Не корректировать
     */
    @FindBy(xpath = "//ul[@class='x-list-plain']/li[1]")
    private SelenideElement doNotAdjust;

    /**
     * Способ корректировки:
     * Установить время
     */
    @FindBy(xpath = "//ul[@class='x-list-plain']/li[2]")
    private SelenideElement setTime;

    /**
     * Способ корректировки:
     * Сместить в рабочем интервале
     */
    @FindBy(xpath = "//ul[@class='x-list-plain']/li[3]")
    private SelenideElement offsetInTheInterval;

    /**
     * Способ корректировки:
     * Сместить в периоде
     */
    @FindBy(xpath = "//ul[@class='x-list-plain']/li[4]")
    private SelenideElement offsetInThePeriod;

    /**
     * Запретить изменение типа для созданной задачи
     */
    @FindBy(xpath = "(//input[contains(@id,'checkbox')] [ancestor::div[contains(@id,'tabpanel')]])[2]")
    private SelenideElement typeChangeDisabled;

    /**
     * Создавать подзадачи ИРГ только родительского типа
     */
    @FindBy(xpath = "(//input[contains(@id,'checkbox')] [ancestor::div[contains(@id,'tabpanel')]])[3]")
    private SelenideElement sameTypeIWG;

    /**
     * Запретить закрытие задач с неготовыми контрольными точками
     */
    @FindBy(xpath = "(//input[contains(@id,'checkbox')] [ancestor::div[contains(@id,'tabpanel')]])[4]")
    private SelenideElement closingTasksWithNotReadyCheckpoints;

    /**
     * Открывать файлы для редактирования
     */
    @FindBy(css = "#files_edit-inputEl")
    private SelenideElement filesEdit;

    /**
     * Прикреплять файлы:
     * Лента действий
     */
    @FindBy(css = "#actiontape_attachments-inputEl")
    private SelenideElement attachFilesActionLine;

    /**
     * Прикреплять файлы:
     * Описание
     */
    @FindBy(css = "#description_attachments-inputEl")
    private SelenideElement attachFilesDescription;


    /**
     * МЕТОДЫ--------------------------------------------------------------------------------------------------------------------------------------------
     */
    //-----------------------------------------------------------------------------Вкладка - НАСТРОЙКИ---------------------------------------------------

    /**
     * Выбор вкладки - Настройки (Options)
     *
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage clickSettingsTab() {
        settingsTab.click();
        return this;
    }

    /**
     * Направление смещения даты при попадании на нерабочее время
     */
    public TaskTypesEditPage directionOfDisplacementOfTheDate(ShiftDirection shiftdirection) {
        if (shiftdirection == DATE_MOVES_BACK) {
            clickDateMovesBack.click();
        } else if (shiftdirection == DATE_DOES_NOT_MOVE) {
            clickDateDoesNotMove.click();
        } else if (shiftdirection == DATE_MOVES_FORWARD) {
            clickDateMovesForward.click();
        }

        return this;
    }

    /**
     * Использовать ECP
     *
     * @param useSignature передаем параметр использования ЭЦП
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage selUseECP(boolean useSignature) {
        if (useSignature) {
            useECP.click();
        }

        return this;
    }

    /**
     * Корректировка даты
     *
     * @param dateCorrection
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage selDateCorrection(CorrectionMethod dateCorrection) {
        if (dateCorrection == CorrectionMethod.SET_TIME) {
            typeCorrection.click();
            selectingSecondAdjustmentPosition();
        } else if (dateCorrection == CorrectionMethod.OFFSET_IN_THE_INTERVAL) {
            typeCorrection.click();
            selectingThirdAdjustmentPosition();
        } else if (dateCorrection == CorrectionMethod.OFFSET_IN_THE_PERIOD) {
            typeCorrection.click();
            selectingFourthlyAdjustmentPosition();
        }

        return this;
    }

    /**
     * Запретить изменение типа для созданной задачи
     */
    public TaskTypesEditPage selIsTaskTypeChangeDisabled(boolean taskTypeChangeDisabled) {
        if (taskTypeChangeDisabled) {
            typeChangeDisabled.click();
        }
        return this;
    }

    /**
     * Создавать подзадачи ИРГ только родительского типа
     */
    public TaskTypesEditPage selOnlyTheSameTypeIWG(boolean onlyTheSameTypeIWG) {
        if (onlyTheSameTypeIWG) {
            sameTypeIWG.click();
        }
        return this;
    }

    /**
     * Запретить закрытие задач с неготовыми контрольными точками
     */
    public TaskTypesEditPage selIsCloseTaskWithNotReadyCheckpointsDisabled(boolean notReadyCheckpointsDisabled) {
        if (notReadyCheckpointsDisabled) {
            closingTasksWithNotReadyCheckpoints.click();
        }
        return this;
    }

    /**
     * Открывать файлы для редактирования
     *
     * @param filesForEdit
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage selOpenFilesForEdit(OpenFilesForEdit filesForEdit) {
        if (filesForEdit == OpenFilesForEdit.YES) {
            filesEdit.click();
            selectingThirdAdjustmentPosition();
        } else if (filesForEdit == OpenFilesForEdit.NO) {
            filesEdit.click();
            selectingSecondAdjustmentPosition();
        }
        return this;
    }

    /**
     * Прикреплять файлы:
     * Лента действий
     *
     * @param actionLine
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage selIsAttachFilesToActionLine(boolean actionLine) {
        if (actionLine) {
            attachFilesActionLine.click();
        }
        return this;
    }

    /**
     * Прикреплять файлы:
     * Описание
     *
     * @param description
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage selIsAttachFilesToDescription(boolean description) {
        if (description) {
            attachFilesDescription.click();
        }
        return this;
    }

    //------------------------------------------------------------------------------------------------------------Вкладка - ПОЛЯ --------------------------------------------------

    /**
     * Выбор вкладки - Поля (Fields)
     *
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage clickFieldsTab() {
        fieldsTab.click();
        return this;
    }

    /**
     * Клик кнопка - Добавить поле
     *
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage addField() {
        clickOkAndAddFieldTypesObgect.click();
        return this;
    }

    /**
     * Поле - Название поля
     *
     * @param text
     * @return TypesOfTablesEditPage
     */
    public TaskTypesEditPage adNameField(String text) {
        nameField.clear();
        nameField.sendKeys(text);
        return this;
    }

    /**
     * Идентификатор поля
     *
     * @param identifier
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage adFieldID(String identifier) {
        nameIdentifier.clear();
        nameIdentifier.sendKeys(identifier);
        return this;
    }

    /**
     * Выбор Типа поля
     *
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage selTypeField() {
        typeField.click();
        return this;
    }

    /**
     * Обязательность поля
     *
     * @param oblField
     * @return
     */
    public TaskTypesEditPage selObligatoryField(ObligatoryFieldTypeTask oblField) {
        if (oblField == ObligatoryFieldTypeTask.OBLIGATORY_ON_CREATE) {
            obligatoryField.click();
            selectingSecondAdjustmentPosition();
        } else if (oblField == ObligatoryFieldTypeTask.OBLIGATORY_ON_CLOSE) {
            obligatoryField.click();
            selectingThirdAdjustmentPosition();
        }
        return this;
    }

    /**
     * Скрывать при поиске
     *
     * @return TypesOfTablesEditPage
     */
    public TaskTypesEditPage selIsHideInSearch(boolean hideInSearch) {
        if (hideInSearch) {
            isHideInSearch.click();
            selectingSecondAdjustmentPosition();
        }
        return this;
    }

    /**
     * Выбор из списка
     *
     * @return TypesOfTablesEditPage
     */
    public TaskTypesEditPage selFromList(boolean fromList) {
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
    public TaskTypesEditPage selListVal(String listVal) {
        fieldListVal.click();
        fieldListVal.sendKeys(listVal);
        return this;
    }

    /**
     * Сохранить поле объекта
     *
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage clickSaveField() {
        saveField.click();
        clickYesCalculatNewValues(); /* исключение - срабатывает для полей типа Справочник при вводе - Шаблона отображения
        - alert - Вычислить новые значения для сохраненных полей */
        return this;
    }

    /**
     * Сохранить объект
     *
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage clickSaveObject() {
        saveObject.click();
        checkingMessagesConfirmationOfTheObject($(By.xpath("//div[count(div)=3]/div[2]//div[contains(@id,'messagebox') and (@data-errorqtip)]")),
                "Изменения сохранены", clickAlertOk);
        return this;
    }

    //------------------------------------------------------------------------------------------Выбор Типов полей из выпадающего списка:-------------------------------------

    /**
     * Выбор типа поля объекта
     *
     * @param typeOfField передаваемое значение типа поля
     * @return TypesOfTablesEditPage
     */
    public TaskTypesEditPage selectTheTypeOfField(SelenideElement typeOfField) {
        typeOfField.click();
        return this;
    }

    /**
     * Выбор поля - Справочник (в форме редактирования поля - Сслыка и Множественная ссылка)
     *
     * @return
     */
    public TaskTypesEditPage selDirectory() {
        clickFieldDictionary.click();
        return this;
    }

    /**
     * Выбор поля - Поле (в форме редактирования поля - Сслыка и Множественная ссылка)
     *
     * @return TaskTypesEditPag
     */
    public TaskTypesEditPage selField() {
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
    public TaskTypesEditPage chooseDirectory(String directoryName) {
        clickFieldDictionary.click();
        $(By.xpath("//*[text()='" + directoryName + "']")).click();
        return this;
    }

    /**
     * Выбор поля ср-ка из списка ("Поле:" справочника/типы таблицы)
     *
     * @param directoryFieldName
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage chooseFieldDirectoryAndTable(String directoryFieldName) {
        sleep(500);
        selectField.click();
        $(By.xpath("//*[text()='" + directoryFieldName + "']")).click();
        return this;
    }

    /**
     * Шаблон отображения
     *
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage selDirectoryTemplate(String directoryTemplate) {
        dicTemplate.clear();
        dicTemplate.sendKeys(directoryTemplate);
        return this;
    }

    /**
     * Вычислить новые значения для сохраненных полей? - при вводе Шаблона отображения
     */
    public boolean clickYesCalculatNewValues() {
        try {
            (new WebDriverWait(getWebDriver(), 0, 50))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath("//div[contains(@id,'messagebox')]//div[count(a)=4]/a[2]//span[1]")));
            calculatNewValues.click();
            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }


    /**
     * Ввод Шаблона нумератора
     *
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage selNumeratorTemplate(String numeratorTemplate) {
        selNumeratorTemp.clear();
        selNumeratorTemp.sendKeys(numeratorTemplate);
        return this;
    }

    /**
     * Режим вычисления поля типа Нумертаор
     *
     * @param calculationMode
     * @return
     */
    public TaskTypesEditPage selCalculationMode(ComputeModeNumerator calculationMode) {
        if (calculationMode == ComputeModeNumerator.DONTCOMPUTE) {
            calculatMode.click();
            selectingSecondAdjustmentPosition();
        }
        return this;
    }

    /**
     * Выбор Таблица из списка
     *
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage chooseTable(String tableName) {
        fieldTable.click();
        $(By.xpath("//*[text()='" + tableName + "']")).click();
        return this;
    }

    /**
     * Проверяем отображение в гриде полей
     *
     * @return TaskTypesEditPage
     */
    public TaskTypesEditPage verifyFieldInGrid(String fieldName) {
        waitForPageUntilElementIsVisible(
                By.xpath("//table[contains(@id,'gridview')]//td[1]/div[text()='" + fieldName + "']"),
                5000);
        return this;
    }


    /**
     * ----------------------------------------------------------------------------Метод добавления всех типов полей--------------------------------
     *
     * @param fieldsTaskTypes
     * @return
     */
    public TaskTypesEditPage addAllFieldsTaskTypes(TasksTypesField[] fieldsTaskTypes) {
        if (fieldsTaskTypes == null) {
            return null;
        } else
            outer:
                    for (TasksTypesField fieldTaskTypes : fieldsTaskTypes) {
                        addField(); // Добавить поле
                        adNameField(fieldTaskTypes.getFieldName()); // заполняем Название поля документа из массива
                        adFieldID(fieldTaskTypes.getFieldID()); // заполняем Идентификатор поля из массива
                        selTypeField(); // Выбор поля - Тип поля

                        // 1. СТРОКА
                        if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsString) {
                            selectTheTypeOfField(typeFieldString);
                            TypeListFieldsString fieldString = (TypeListFieldsString) fieldTaskTypes.getFieldType();
                            if (fieldString.getIsListChoice()) {
                                selFromList(fieldString.getIsListChoice()); // Выбор из списка
                                selListVal(fieldString.getValuesList()); // Список значений
                            } else if (!fieldString.getIsListChoice()) {
                                selListVal(fieldString.getValuesList()); // Список значений
                            }
                            // 2. ТЕКСТ
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsText) {
                            selectTheTypeOfField(typeFieldText);
                            TypeListFieldsText fieldText = (TypeListFieldsText) fieldTaskTypes.getFieldType();
                            // 3. ЦЕЛОЕ
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsInt) {
                            selectTheTypeOfField(typeFieldInteger);
                            TypeListFieldsInt fieldInt = (TypeListFieldsInt) fieldTaskTypes.getFieldType();
                            // 4. ВЕЩЕСТВЕННОЕ
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsDouble) {
                            selectTheTypeOfField(typeFieldDouble);
                            TypeListFieldsDouble fieldsDouble = (TypeListFieldsDouble) fieldTaskTypes.getFieldType();
                            // 5. ДАТА
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsDate) {
                            selectTheTypeOfField(typeFieldData);
                            TypeListFieldsDate fieldsDate = (TypeListFieldsDate) fieldTaskTypes.getFieldType();
                            // 6. СПРАВОЧНИК
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsDirectory) {
                            selectTheTypeOfField(typeFieldReferenceToTheDictionary);
                            TypeListFieldsDirectory fieldsDir = (TypeListFieldsDirectory) fieldTaskTypes.getFieldType();
                            chooseDirectory(fieldsDir.getDirectories().getDirectoryName()); // Выбор спр-ка
                            chooseFieldDirectoryAndTable(fieldsDir.getFieldDirectory().getFieldName()); // Выбор поля спр-ка
                            selDirectoryTemplate(fieldsDir.getDisplayNameTemplate()); // Шаблон отображения
                            // 7. МНОЖЕСТВЕННАЯ ССЫЛКА НА СПР-К
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsMultiDirectory) {
                            selectTheTypeOfField(typeFieldMultipleReferenceToTheDictionary);
                            TypeListFieldsMultiDirectory fieldsMultiDir = (TypeListFieldsMultiDirectory) fieldTaskTypes.getFieldType();
                            selDirectory(); // Выбор поля Спр-к
                            $(By.xpath("//*[text()='" + fieldsMultiDir.getDirectoryName() + "']")).click(); // Выбор справочника
                            selField(); // Выбор поля Поле
                            $(By.xpath("//li[text()='" + fieldsMultiDir.getNameDirectoryField() + "']")).click(); // Выбор поля справочника
                            selDirectoryTemplate(fieldsMultiDir.getDisplayNameTemplate()); // Шаблон отображения
                            // 8. ЛОГИЧЕСКОЕ
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsBoolean) {
                            selectTheTypeOfField(typeFieldBoolean);
                            TypeListFieldsBoolean fieldsDate = (TypeListFieldsBoolean) fieldTaskTypes.getFieldType();
                            // 9. ССЫЛКА НА БИБЛИОТЕКУ
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsLibraryLink) {
                            selectTheTypeOfField(typeFieldLibrarylink);
                            TypeListFieldsLibraryLink fieldsLibraryLink = (TypeListFieldsLibraryLink) fieldTaskTypes.getFieldType();
                            // 10. ССЫЛКА НА ЗАДАЧУ
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsReferenceTask) {
                            selectTheTypeOfField(typeFieldReferenceTask);
                            TypeListFieldsReferenceTask fieldsReferenceTask = (TypeListFieldsReferenceTask) fieldTaskTypes.getFieldType();
                            // 11. НУМЕРАТОР
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsNumerator) {
                            selectTheTypeOfField(typeFieldNumerator);
                            TypeListFieldsNumerator fieldsNumerator = (TypeListFieldsNumerator) fieldTaskTypes.getFieldType();
                            selNumeratorTemplate(fieldsNumerator.getNumeratorTemplate()); // Шаблон нумератора
                            selCalculationMode(fieldsNumerator.getComputeMode()); // Режим вычисления
                            // 12. ССЫЛКА НА ОБЪЕКТ
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsObjectLink) {
                            selectTheTypeOfField(typeFieldLinkObject);
                            TypeListFieldsObjectLink fieldsObjectLink = (TypeListFieldsObjectLink) fieldTaskTypes.getFieldType();
                            // 13. ТАБЛИЦА
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsTable) {
                            selectTheTypeOfField(typeFieldTable);
                            TypeListFieldsTable fieldsTable = (TypeListFieldsTable) fieldTaskTypes.getFieldType();
                            chooseTable(fieldsTable.getTypesOfTables().getTableTypeName()); // Выбор объекта - Таблица, из списка
                            chooseFieldDirectoryAndTable(fieldsTable.getFieldTable().getFieldName()); // Выбор поля объекта - Таблица, из списка
                            selDirectoryTemplate(fieldsTable.getNumeratorTemplate()); // Шаблон отображения
                            // 14. ПОДРАЗДЕЛЕНИЕ
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsDepartment) {
                            selectTheTypeOfField(typeFieldDepartment);
                            TypeListFieldsDepartment fieldsDepartment = (TypeListFieldsDepartment) fieldTaskTypes.getFieldType();
                            // 15. СТРУДНИК
                        } else if (fieldTaskTypes.getFieldType() instanceof TypeListFieldsEmployee) {
                            selectTheTypeOfField(typeFieldEmployee);
                            TypeListFieldsEmployee fieldsEmployee = (TypeListFieldsEmployee) fieldTaskTypes.getFieldType();
                        }

                        selObligatoryField(fieldTaskTypes.getObligatory()); // Обязательность поля
                        selIsHideInSearch(fieldTaskTypes.getIsHideInSearch()); // Скрывать при поиске
                        clickSaveField(); // Сохранить поле
                        verifyFieldInGrid(fieldTaskTypes.getFieldName()); // Проверяем отображение добавленного поля в гриде

                    }

        return this;
    }

    /**
     * Добавление полей Типа задач
     *
     * @param tasksTypes передаваемые атрибуты (настройки) объекта Типы задач
     */
    @Override
    public void addSettingsAndFieldTasksTypes(TasksTypes tasksTypes) {
        checkDisplayedTabsInTheShapeOfAnObject(By.xpath("//a/ancestor::div[contains(@id,'tabbar')]//a"), 7,
                By.xpath("//a/ancestor::div[contains(@id,'tabbar')]//a//span[text()]"), new String[]{"Настройки", "Поля", "Обработчики", "Настройки почтовых уведомлений",
                        "Оценки и доклады", "Доступ", "Форматы"});
        clickFieldsTab() // Вкладка - Поля
                .addAllFieldsTaskTypes(tasksTypes.getTasksTypesFields()) // Добавление полей

                .clickSettingsTab() // Вкладка - Настройки
                .directionOfDisplacementOfTheDate(tasksTypes.getTaskTypeShiftDirection()) // Направление смещения при попадании на нерабочее время
                .selUseECP(tasksTypes.getUseECP()) // Использовать ЭЦП

                .selDateCorrection(tasksTypes.getTaskTypeCorrectionMethod()) // Корректировка даты

                .selIsTaskTypeChangeDisabled(tasksTypes.getIsTaskTypeChangeDisabled()) // Запретить изменение типа для созданной задачи
                .selOnlyTheSameTypeIWG(tasksTypes.getOnlyTheSameTypeIWG()) // Создавать подзадачи ИРГ только родительского типа
                .selIsCloseTaskWithNotReadyCheckpointsDisabled(tasksTypes.getIsCloseTaskWithNotReadyCheckpointsDisabled()) // Запретить закрытие задач с неготовыми контрольными точками

                .selOpenFilesForEdit(tasksTypes.getOpenFilesForEdit()) // Открывать файлы для редактирования
                .selIsAttachFilesToActionLine(tasksTypes.getIsAttachFilesToActionLine()) // Добавлять файлы в ленту действий
                .selIsAttachFilesToDescription(tasksTypes.getIsAttachFilesToDecription()) // Добавлять файлы в описание

                .clickSaveObject() // Сохранить объект

                .verifyCreateObject(tasksTypes.getTaskTypeName()); // Проверяем отображение созданного объекта в гриде

    }

    @Override
    public void editFieldTasksTypes(TasksTypes tasksTypes) {

    }

    @Override
    public void removeAnFieldTasksTypes(TasksTypes tasksTypes) {

    }
}