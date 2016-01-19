package ru.st.selenium.test.data.system;

import org.testng.annotations.DataProvider;
import ru.st.selenium.model.AccessRights;
import ru.st.selenium.model.Administration.Directories.Directories;
import ru.st.selenium.model.Administration.Directories.DirectoriesField;
import ru.st.selenium.model.Administration.FieldsObject.*;
import ru.st.selenium.model.Administration.TasksTypes.ObligatoryFieldTypeTask;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypesField;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.CorrectionMethod;
import ru.st.selenium.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.st.selenium.model.DocflowAdministration.DictionaryEditor.DictionaryEditorField;
import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.*;
import ru.st.selenium.model.Document.Document;
import ru.st.selenium.model.OpenFilesForEdit;
import ru.st.selenium.model.ShiftDirection;

/**
 * Данные раздела - Администрирование ДО
 */
public class ModuleDocflowAdministrationObjectTestCase extends ModuleAdministrationObjectTestCase {

    //---Администрирование/Администрирование ДО----------------------------------------------------------
    //-----Редактор словарей----------------------------------------------------------

    /**
     * Метод создания полностью случайного объекта - "Редактор словарей"
     */
    public DictionaryEditor getRandomDictionaryEditor() {
        DictionaryEditor dictionEditor = new DictionaryEditor("wD_Словарь " + randomString(15), /* Название словаря*/ randomEnum(AccessRights.class) /* Уровень доступа*/,
                (new DictionaryEditorField[]{

                        new DictionaryEditorField()
                                .setDictionaryEditorElement(randomString(15)) // Название элемента словаря
                                .setDescriptionDictionItem(randomString(80) + "\n" + randomString(15)), // Описание элемента словаря

                        new DictionaryEditorField()
                                .setDictionaryEditorElement(randomString(15)) // Название элемента словаря
                                .setDescriptionDictionItem(randomString(15) + "\n" + randomString(50)), // Описание элемента словаря

                        new DictionaryEditorField()
                                .setDictionaryEditorElement(randomString(15)) // Название элемента словаря
                                .setDescriptionDictionItem(randomString(30) + "\n" + randomString(15)), // Описание элемента словаря

                }));

        return dictionEditor;
    }

    //---Администрирование/Администрирование ДО----------------------------------------------------------
    //-----Регистрационные карточки документа----------------------------------------------------------

    /**
     * Параметризация - Инициализируем модель - Регистрационная карточка документа (со всеми надстройками)
     *
     * @return массив параметров объектов системы
     */
    @DataProvider
    public Object[][] objectDataDRC() {

        //---------------------------------------------------------------------------------------------------------- Инициализируем объект - Подразделение и Пользователь
        Department[] department = new Department[]{getRandomDepartment(), getRandomDepartment()};

        Employee[] employee = new Employee[]{getRandomEmployer(), getRandomEmployer(), getRandomEmployer(), getRandomEmployer()};


        //----------------------------------------------------------------------------------------------------Инициализация поля объекта и объект - Справочник
        /*
         1. СТРОКА (Выбор из списка == Да; Уникальное; Обязательное)
          */
        DirectoriesField fieldStringIsListChoiceDirectory = new DirectoriesField()
                .setFieldName("Строка (Выбор из списка == Да; Обяз.) " + randomString(10))
                .setFieldID("STRING" + randomIdentifier(5))
                .setObligatory(true) // Обязательное поле
                .setIsUniqueField(true) // Уникальное
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(true) // Выбор из списка
                        .setValuesList(randomString(10) + "\n" + randomString(10) + "\n" + randomString(10)));
        /*
         2. ТЕКСТ
          */
        DirectoriesField fieldTextDirectory = new DirectoriesField()
                .setFieldName("Текст " + randomString(10))
                .setFieldID("TEXT" + randomIdentifier(5))
                .setFieldType(new TypeListFieldsText());

        // Будешь плохо кодить, придет Java и съест твою память

        /*
         3. ЦЕЛОЕ
          */
        DirectoriesField fieldIntDirectory = new DirectoriesField()
                .setFieldName("Целое " + randomString(10))
                .setFieldID("INTEGER" + randomIdentifier(5))
                .setFieldType(new TypeListFieldsInt());


        Directories directories = new Directories("wD_Справочник_for_DRC " + randomString(10)) /* Название справочника*/
                // Вкладка - Настройки
                .setShareRecords(true) // Общедоступность записей
                .setAccessToRecords(true) // Настройка доступа к записям
                .setMappingDevice(true) // Способ отображения - Линейный ли? true - да; false - иерархический
                .setSearchSettings(true) // true - поиск записей через SOLR; false - поиск записей через БД
                .setDirectoriesFields(new DirectoriesField[]{fieldStringIsListChoiceDirectory, fieldTextDirectory, fieldIntDirectory});

        //----------------------------------------------------------------------------------------------------Инициализация поля объекта и объект - Типы задач
        // 1. СТРОКА (Выбор из списка == Да; Обязательное)
        TasksTypesField fieldStringIsListChoiceTasksTypes = new TasksTypesField()
                .setFieldName("Строка (Выбор из списка == Да; Обязательное) " + randomString(5))
                .setFieldID("STRING" + randomIdentifier(5))
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(true) // Выбор из списка
                        .setValuesList(randomString(10) + "\n" + randomString(10) + "\n" + randomString(10))) // Список значений
                .setObligatory(ObligatoryFieldTypeTask.OBLIGATORY_ON_CREATE) // Обязательное при создании
                .setIsHideInSearch(true);

        // 2. ТЕКСТ (Скрывать при поиске)
        TasksTypesField fieldTextTasksTypes = new TasksTypesField()
                .setFieldName("Текст " + randomString(10))
                .setFieldID("TEXT" + randomIdentifier(5))
                .setFieldType(new TypeListFieldsText())
                .setIsHideInSearch(true); // Скрывать при поиске

        // 3. ЦЕЛОЕ
        TasksTypesField fieldIntTasksTypes = new TasksTypesField()
                .setFieldName("Целое " + randomString(10))
                .setFieldID("INTEGER" + randomIdentifier(5))
                .setFieldType(new TypeListFieldsInt());

        // 4. ВЕЩЕСТВЕННОЕ
        TasksTypesField fieldFloatTasksTypes = new TasksTypesField()
                .setFieldName("Вещественное " + randomString(10))
                .setFieldID("FLOAT" + randomIdentifier(5))
                .setFieldType(new TypeListFieldsDouble());

        // 5. ДАТА
        TasksTypesField fieldDateTasksTypes = new TasksTypesField()
                .setFieldName("Дата " + randomString(10))
                .setFieldID("DATE" + randomIdentifier(5))
                .setFieldType(new TypeListFieldsDate());

        // Инициализация объекта - Типы задач с настройками
        TasksTypes tasksTypes = new TasksTypes("wD_Тип задачи " + randomString(20))

                // Направление смещения даты при попадании на нерабочее время
                .setTaskTypeShiftDirection(ShiftDirection.DATE_MOVES_BACK) // Дата сдвигается назад

                // Корректировка даты
                .setTaskTypeCorrectionMethod(CorrectionMethod.SET_TIME)

                .setIsTaskTypeChangeDisabled(true) // Запретить изменение типа для созданной задачи
                .setOnlyTheSameTypeIWG(true) // Создавать подзадачи ИРГ только родительского типа
                .setIsCloseTaskWithNotReadyCheckpointsDisabled(true) // Запретить закрытие задач с неготовыми контрольными точками
                .setOpenFilesForEdit(OpenFilesForEdit.YES) // Открывать файлы для редактирования
                // Прикреплять файлы:
                .setIsAttachFilesToActionLine(false) // Лента действий (true - есть сигнал, значит производим клик - снимаем настройку; false - оставляем без изменения)
                .setIsAttachFilesToDecription(false) // Описание

                // Поля типа
                .setTasksTypesFields(new TasksTypesField[]
                        {fieldStringIsListChoiceTasksTypes, fieldTextTasksTypes, fieldIntTasksTypes,
                                fieldFloatTasksTypes, fieldDateTasksTypes});

        //----------------------------------------------------------------------------------------------------Инициализация объекта - Словарь
        DictionaryEditorField elementOne = new DictionaryEditorField()
                .setDictionaryEditorElement(randomString(10))
                .setDescriptionDictionItem(randomString(15) + "\n" + randomString(30));

        DictionaryEditorField elementTwo = new DictionaryEditorField()
                .setDictionaryEditorElement(randomString(10))
                .setDescriptionDictionItem(randomString(50) + "\n" + randomString(30));

        DictionaryEditorField elementThree = new DictionaryEditorField()
                .setDictionaryEditorElement(randomString(10))
                .setDescriptionDictionItem(randomString(30) + "\n" + randomString(30));

        DictionaryEditor dictionaryEditor = getRandomDictionaryEditor()
                .setDictionaryEditorFields(new DictionaryEditorField[]{elementOne, elementTwo,
                        elementThree});
        /*
         ---------------------------------------------------------------------------------------------------------Инициализация полей объекта - Документ
         */
        /*
         1. ЧИСЛО
         */
        DocRegisterCardsField fieldNumber = new DocRegisterCardsField()
                .setFieldNameDoc("Число " + randomString(5)) // Имя поля документа
                .setFieldIdentifierDoc("NUMBER" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeNumberDoc())
                .setEditableField(true) // Обязательное при редактировании (true == Да; false == Нет)
                .setObligatoryFieldDoc(ObligatoryFieldDocument.REQUIRED_WHEN_CREATION); // Обязательное поле == Обязательное при создании

        /*
          2. ДАТА
         */
        DocRegisterCardsField fieldDate = new DocRegisterCardsField()
                .setFieldNameDoc("Дата " + randomString(5)) // Имя поля документа
                .setFieldIdentifierDoc("DATE" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeDateDoc()
                        .setDefaultValue(true) // Значение по умолчанию (true == Текущая дата; false == Нет)
                        .setEditionAvailableWhileCreation(true)) // Изменяемое при создании
                .setEditableField(true);

        /*
          3. СТРОКА (Уникальное == Да)
         */
        DocRegisterCardsField fieldUniqueString = new DocRegisterCardsField()
                .setFieldNameDoc("Строка (Уникальное) " + randomString(5)) // Имя поля документа
                .setFieldIdentifierDoc("STRING" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeStringDoc()
                        .setFieldLength(randomInt(999))) // Длина поля
                .setEditableField(true)
                .setUniqueField(true); // Уникальное поле

        /*
          3.1 СТРОКА; Выбор только из спр-ка == Да
         */
        DocRegisterCardsField fieldStringOnlyYesDirectory = new DocRegisterCardsField()
                .setFieldNameDoc("Строка (Выбор из спр-ка == Да) " + randomString(5)) // Имя поля документа
                .setFieldIdentifierDoc("STONLYESDIR" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeStringDoc()
                        .setSelectOnlyFromDictionary(true) // Выбор только из справочника (true == Да; false == Нет)
                        .setDirectoryName("Номенклатура дел")
                        .setDirectoryTemplate("{Код дела}, " + "{Наименование раздела} " + randomString(15))
                        .setFieldLength(randomInt(999))) // Длина поля
                .setEditableField(true);

        /*
          3.2 СТРОКА; Выбор только из спр-ка == Нет
         */
        DocRegisterCardsField fieldStringOnlyNoDirectory = new DocRegisterCardsField()
                .setFieldNameDoc("Строка (Выбор из спр-ка == Нет) " + randomString(5)) // Имя поля документа
                .setFieldIdentifierDoc("STONLNODIR" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeStringDoc()
                        .setSelectOnlyFromDictionary(false) // Выбор только из справочника (true == Да; false == Нет)
                        .setDirectoryName("Номенклатура дел")
                        .setDirectoryTemplate("{Код дела}, " + "{Наименование раздела} " + randomString(15))
                        .setFieldLength(randomInt(999))) // Длина поля
                .setEditableField(true);

        /*
          4. ТЕКСТ
         */
        DocRegisterCardsField fieldText = new DocRegisterCardsField()
                .setFieldNameDoc("Текст " + randomString(5))
                .setFieldIdentifierDoc("TEXT" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeTextDoc())
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)

        /*
          4.1. ТЕКСТ; Выбор только из спр-ка == Да
         */
        DocRegisterCardsField fieldTextOnlyYesDirectory = new DocRegisterCardsField()
                .setFieldNameDoc("Текст (Выбор из спр-ка == Да) " + randomString(5))
                .setFieldIdentifierDoc("TEXONLYYESDIR" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeTextDoc()
                        .setSelectOnlyFromDictionary(true)
                        .setDirectoryName("Номенклатура дел")
                        .setDirectoryTemplate("{Код дела}, " + "{Наименование раздела} " + randomString(15)))
                .setEditableField(true) // Обязательное при редактировании (true == Да; false == Нет)
                .setHideInTablesField(true); // Скрывать в таблицах

        /*
          5. СЛОВАРЬ
         */
        DocRegisterCardsField fieldDictionary = new DocRegisterCardsField()
                .setFieldNameDoc("Словарь " + randomString(5))
                .setFieldIdentifierDoc("DICTIONARY" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeDictionaryDoc()
                        .setDictionaryEditor(dictionaryEditor)) // Выбор проинициализированный объект - Словарь
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)

        /*
          6. ПОДРАЗДЕЛЕНИЕ
         */
        DocRegisterCardsField fieldDepartment = new DocRegisterCardsField()
                .setFieldNameDoc("Подразделение " + randomString(5))
                .setFieldIdentifierDoc("DEPARTMENT" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeDepartmentDoc())
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)

        /*
          7. СОТРУДНИК
         */
        DocRegisterCardsField fieldEmployee = new DocRegisterCardsField()
                .setFieldNameDoc("Сотрудник " + randomString(5))
                .setFieldIdentifierDoc("EMPLOYEE" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeEmployeeDoc())
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)

        /*
          7.1. СОТРУДНИК (Контролер типа == Да; Текущий пользователь == Да)
         */
        DocRegisterCardsField fieldEmployeeSuperviserAndDefaultValue = new DocRegisterCardsField()
                .setFieldNameDoc("Сотрудник (Контролер типа; Текущий пользователь) " + randomString(5))
                .setFieldIdentifierDoc("EMPDEFAULTVALUY" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeEmployeeDoc()
                        .setDefaultValue(true) // Значение по умолчанию == Текущий пользователь
                        .setDocumentSuperviser(true)) // Контролер документа == Да
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)

        /*
          7.2. СОТРУДНИК (Для сведения == Да)
         */
        DocRegisterCardsField fieldEmployeeForInformation = new DocRegisterCardsField()
                .setFieldNameDoc("Сотрудник (Для сведения) " + randomString(5))
                .setFieldIdentifierDoc("EMFORINFORMATION" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeEmployeeDoc()
                        .setForInformation(true)) // Для сведения == Да
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)

        /*
          8. ДОКУМЕНТ
         */
        DocRegisterCardsField fieldDocument = new DocRegisterCardsField()
                .setFieldNameDoc("Документ " + randomString(5))
                .setFieldIdentifierDoc("DOCUMENT" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeDocumentDoc()
                        .setDisplayNameTemplate("{" + fieldUniqueString.getFieldIdentifierDoc() + "}; " + "{" + randomIdentifier(10) + "}; " + randomString(10)))
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)

        /*
          8.1. ДОКУМЕНТ (Правила поиска)
         */
        DocRegisterCardsField fieldDocumentSearchRules = new DocRegisterCardsField()
                .setFieldNameDoc("Документ (Правила поиска) " + randomString(5))
                .setFieldIdentifierDoc("DOCSEARCHRUL" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeDocumentDoc()
                        .setDisplayNameTemplate("{" + randomIdentifier(10) + "}; " + "{" + randomIdentifier(10) + "}; " + randomString(10))
                        .setSearchSimiliarDocuments(true) // Искать похожие документы
                        .setSearchRules("DOCUMENT_STATE" + "=" + "0;")) // Правила поиска
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)

        /*
          9. НУМЕРАТОР
         */
        DocRegisterCardsField fieldNumerator = new DocRegisterCardsField()
                .setFieldNameDoc("Нумератор " + randomString(5))
                .setFieldIdentifierDoc("NUMERATOR" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeNumeratorDoc()
                        .setNumeratorTemplateDoc("{" + fieldUniqueString.getFieldIdentifierDoc() + "}-{counter}-{counter(" + fieldDepartment.getFieldIdentifierDoc() + ",%04d)}-[8]-{DD}.{YYYY} "
                                + randomString(10))
                        .setEditionAvailableWhileCreation(true)) // Изменяемое при создании
                .setObligatoryFieldDoc(ObligatoryFieldDocument.REQUIRED_WHEN_CREATION) // Обязательное поле == Обязательное при создании
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)

        /*
         10. СПРАВОЧНИК (Одна запись)
         */
        DocRegisterCardsField fieldDirectory = new DocRegisterCardsField()
                .setFieldNameDoc("Справочник " + randomString(5))
                .setFieldIdentifierDoc("DIRECTORY" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeDirectoryDoc()
                        .setNameDirectoryDoc("Номенклатура дел")
                        .setDirectoryTemplate("{Код дела}, " + "{Наименование раздела} " + randomString(15))
                        .setDirectoryEntriesSelection(true)) // Одна запись
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)

    /*
          11. МН. СПРАВОЧНИК (Несколько записей)
     */
        DocRegisterCardsField fieldMultiDirectory = new DocRegisterCardsField()
                .setFieldNameDoc("Множественный справочник " + randomString(5))
                .setFieldIdentifierDoc("DIRMULTI" + randomIdentifier(5)) // Идентификатор поля
                .setFieldTypeDoc(new FieldTypeDirectoryDoc()
                        .setDirectoryDoc(directories) // Задаем проинициализированный спр-к
                        .setDirectoryTemplate("{" + fieldStringIsListChoiceDirectory.getFieldName() + "}" + ";"
                                + "{" + fieldTextDirectory.getFieldName() + "}" + ";" + "{" + fieldIntDirectory.getFieldName() + "}" + "; " + randomString(15))
                        .setDirectoryEntriesSelection(false)) // Выбор записей спр-ка; true == Одна запись; false == Несколько записей
                .setEditableField(true); // Обязательное при редактировании (true == Да; false == Нет)*/

        // Инициализация РКД и её настроек
        DocRegisterCards registerCards = new DocRegisterCards("wD_Тестовая карточка " + randomString(20))

                // Статус документа
                .setDocumentStatesOnReview("На рассмотрении " + randomString(20)) // - На рассмотрении
                .setDocumentStatesReviewed("Рассмотрен " + randomString(20)) // - Рассмотрен
                .setDocumentStatesOnApproval("На подписании " + randomString(20)) // - На подписании
                .setDocumentStatesOnExecution("На исполнении " + randomString(20)) // - На исполнении
                .setDocumentStatesInArchive("В архиве " + randomString(20)) // - В архиве

                .setDisplayNameTemplate("{" + fieldNumerator.getFieldIdentifierDoc() + "}, " + "{" + fieldUniqueString.getFieldIdentifierDoc() + "} "
                        + randomString(15)) // Шаблон отображения

                // Направление смещения при попадании на нерабочее время
                .setDocRegisterCardsShiftDirection(ShiftDirection.DATE_MOVES_FORWARD) // Дата сдвигается назад

                // Настройки по умолчанию при отправке документа на доработку:
                .setAtFirstRevisionScheme(false) // Возврат на доработку с начала текущей схемы
                .setForCompletionInTighterPoint(false) // Возврат на доработку в ту же точку рассмотрения
                .setOnCompletionTheNewScheme(true) // Возврат на доработку с новой схемой

                .setOpenFilesForEditDoc(OpenFilesForEdit.YES) // Открывать файлы для редактирования

                .setAutoСalculationNumeratorFields(OpenFilesForEdit.YES) // Автоматическое вычисление полей-нумераторов

                .setAccessDoc(AccessRights.AVAILABLETOALL) // выбираем права Доступа к РКД

                // Изменение признака "Окончательная версия"
                .setDocAuthorFinalVersionFiles(SettingsFinalVersion.NO) // Автор документа
                .setUserWithEditRightFinalVersionFiles(SettingsFinalVersion.NULL) // Пользователь с правами редактирования
                .setDocTypeControllerFinalVersionFiles(SettingsFinalVersion.NO) // Контролер типа документа

                // Редактирование своих документов
                .setEditionOwnDocumentsOnReview(EditionOwnDocuments.USER_RIGHT_EDIT_THEIR_DOCUMENTS) //  - На рассмотрении
                .setEditionOwnDocumentsOnExecution(EditionOwnDocuments.YES) // - На исполнении
                .setEditionOwnDocumentsInArchive(EditionOwnDocuments.NO) // - В архиве

                // Доступ к разделам документа при просмотре/редактировании
                .setAccessToSectionsDocumentRoute(false) // - Маршрут
                .setAccessToSectionsDocumentFiles(false) // - Файлы
                .setAccessToSectionsDocumentResolution(false) // - Резолюции
                .setAccessToSectionsDocumentLog(false) // - Журнал

                .setCreationOfLinkedDocuments(CreationOfLinkedDocuments.USERS_WITH_RIGHT) // Создание связанных документов

                .setCheckBoxUseAllPossibleRoutes(true) // Использовать все возможные маршруты

                // Типы полей документа
                .setDocRegisterCardsFields(new DocRegisterCardsField[]{fieldNumber, fieldDate, fieldUniqueString, fieldStringOnlyYesDirectory,
                        fieldStringOnlyNoDirectory, fieldText, fieldTextOnlyYesDirectory, fieldDictionary, fieldDepartment, fieldEmployee,
                        fieldEmployeeSuperviserAndDefaultValue, fieldEmployeeForInformation, fieldDocument, fieldDocumentSearchRules, fieldNumerator, fieldDirectory})

                // Копирование полей при создании задачи
                .setCopyingFieldsWhenCreatingATask(fieldStringIsListChoiceTasksTypes.getFieldID() + "=" + fieldUniqueString.getFieldIdentifierDoc() + ";" + " " + fieldTextTasksTypes.getFieldID() + "="
                        + fieldText.getFieldIdentifierDoc() + ";" + " " + fieldIntTasksTypes.getFieldID() + "=" + fieldNumber.getFieldIdentifierDoc() + ";" + " " + fieldDateTasksTypes.getFieldID() + "=" + fieldDate.getFieldIdentifierDoc() + ";")

                // TODO доработать инициализацию полей для трансляции пользователей
                // Поля документа, содержащие...:
                .setAuthorsObjectives("AVTOR") // авторов задач
                .setControllersOfTasks("TASKSUPERVISORS") // контролеров задач
                .setDecisionMakersOfTasks("EXECUTIVEMANAGERS") // ответственных руководителей задач
                .setExecutorsOfTasks("PERFORMERS"); // исполнителей задач

        //----------------------------------------------------------------------------------------------------------- Инициализация Документа
        Document document = new Document()

                .setDocumentType(registerCards) // Тип документа
                .setDateRegistration(randomDateTime()) // Дата регистрации
                .setProject(getRandomProject()) // Инициализируем проект документа

                // Осуществляем заполнение (наполнение) полей документа через массив
                .setDocumentFields(new DocRegisterCardsField[]{
                                (DocRegisterCardsField) fieldNumber.setValueField(randomInt(9999)),

                                (DocRegisterCardsField) fieldDate.setValueField(randomDateTime()),

                                (DocRegisterCardsField) fieldUniqueString.setValueField(randomString(50)),
                                (DocRegisterCardsField) fieldStringOnlyNoDirectory.setValueField(randomString(25)),

                                (DocRegisterCardsField) fieldText.setValueField(randomString(200)),

                                (DocRegisterCardsField) fieldDictionary.setValueDictionaryEditor(elementOne),

                                (DocRegisterCardsField) fieldDepartment.setValueDepartment(new Department[]{department[0], department[1]}),

                                (DocRegisterCardsField) fieldEmployee.setValueEmployee(new Employee[]{employee[0], employee[1]}),
                                (DocRegisterCardsField) fieldEmployeeForInformation.setValueEmployee(new Employee[]{employee[2], employee[3]})
                        }
                )
                .setRouteScheme("ООА");

        return new Object[][]{

                {
                        //-------------------------------------------пременная объекта - ПОДРАЗДЕЛЕНИЕ
                        department,
                        //-------------------------------------------пременная объекта - ПОЛЬЗОВАТЕЛЬ
                        employee,
                        //-------------------------------------------пременнаяобъекта - СПРАВОЧНИКИ
                        directories,
                        //-------------------------------------------пременная объекта - ТИПЫ ЗАДАЧ
                        tasksTypes,
                        //-------------------------------------------пременная объекта - СЛОВАРЬ
                        dictionaryEditor,
                        //-------------------------------------------пременная объекта - РКД
                        registerCards,
                        //-------------------------------------------пременная объекта - ДОКУМЕНТ
                        document


                }
        };

    }

}
