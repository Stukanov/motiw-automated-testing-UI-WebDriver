package tests.data.system;

import org.testng.annotations.DataProvider;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.Directories.DirectoriesField;
import ru.motiw.web.model.Administration.FieldsObject.*;
import ru.motiw.web.model.Administration.TasksTypes.ComputeModeNumerator;
import ru.motiw.web.model.Administration.TasksTypes.ObligatoryFieldTypeTask;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.TypesOfTables.TypesOfTables;
import ru.motiw.web.model.Administration.TypesOfTables.TypesOfTablesField;
import ru.motiw.web.model.CorrectionMethod;
import ru.motiw.web.model.OpenFilesForEdit;
import tests.data.BaseTest;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypesField;
import ru.motiw.web.model.ShiftDirection;

/**
 * Данные раздела - Администрирование
 */
public abstract class ModuleAdministrationObjectCaseTest extends ModuleTaskCaseTest {


    //---Администрирование----------------------------------------------------------
    //-----Справочники----------------------------------------------------------

    /**
     * Параметризация - Инициализируем модель - Справочник (со всеми надстройками)
     */
    @DataProvider
    public Object[][] objectDataDirectories() {

        // 1. СТРОКА (Выбор из списка == Да; Уникальное; Обязательное)
        DirectoriesField fieldStringIsListChoice = new DirectoriesField()
                .setFieldName("Строка (Выбор из списка == Да; Обяз.) " + BaseTest.randomString(10))
                .setFieldID("STRING" + BaseTest.randomIdentifier(5))
                .setObligatory(true) // Обязательное поле
                .setIsUniqueField(true) // Уникальное
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(true) // Выбор из списка
                        .setValuesList(BaseTest.randomString(10) + "\n" + BaseTest.randomString(10) + "\n" + BaseTest.randomString(10))); // Список значений

        // 1.1. СТРОКА (Выбор из списка == Нет)
        DirectoriesField fieldStringIsNotListChoice = new DirectoriesField()
                .setFieldName("Строка (Выбор из списка == Нет) " + BaseTest.randomString(10))
                .setFieldID("STRNOTLIST" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(false) // Выбор из списка
                        .setValuesList(BaseTest.randomString(10) + "\n" + BaseTest.randomString(10) + "\n" + BaseTest.randomString(10))); // Список значений

        // 2. ТЕКСТ
        DirectoriesField fieldText = new DirectoriesField()
                .setFieldName("Текст " + BaseTest.randomString(10))
                .setFieldID("TEXT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsText());

        // 3. ЦЕЛОЕ
        DirectoriesField fieldInt = new DirectoriesField()
                .setFieldName("Целое " + BaseTest.randomString(10))
                .setFieldID("INTEGER" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsInt());

        // 4. ВЕЩЕСТВЕННОЕ
        DirectoriesField fieldFloat = new DirectoriesField()
                .setFieldName("Вещественное " + BaseTest.randomString(10))
                .setFieldID("FLOAT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDouble());

        // 5. ДАТА
        DirectoriesField fieldDate = new DirectoriesField()
                .setFieldName("Дата " + BaseTest.randomString(10))
                .setFieldID("DATE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDate());

        // 6. ФАЙЛ (Редактирование == Да)
        DirectoriesField fieldFileEdit = new DirectoriesField()
                .setFieldName("Файл (Редактирование == Да) " + BaseTest.randomString(10))
                .setFieldID("FILEDIT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsFile()
                        .setOpenFilesForEdit(OpenFilesForEdit.YES)); // Редактирование файлов - Да

        // 6.1. ФАЙЛ (Редактирование == Нет)
        DirectoriesField fieldFile = new DirectoriesField()
                .setFieldName("Файл (Редактирование == Нет) " + BaseTest.randomString(10))
                .setFieldID("FILE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsFile()
                        .setOpenFilesForEdit(OpenFilesForEdit.NO)); // Редактирование файлов - Нет

        // 7. ССЫЛКА НА СПР-К
        DirectoriesField fieldDirectory = new DirectoriesField()
                .setFieldName("Ссылка на справочник " + BaseTest.randomString(10))
                .setFieldID("DIRECTORY" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDirectory()
                        .setDirectoryName("Адресная книга")
                        .setNameDirectoryField("Фамилия"));

        // 8. МНОЖЕСТВЕННАЯ ССЫЛКА НА СПР-К TODO 0002- добвать проинициализированный пользовательский спр-к
        DirectoriesField fieldMultiDirectory = new DirectoriesField()
                .setFieldName("Множественная ссылка на справочник " + BaseTest.randomString(5))
                .setFieldID("DIRMULTI" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsMultiDirectory()
                        .setDirectoryName("Банк")
                        .setNameDirectoryField("Название"));

        // 9. ЛОГИЧЕСКИЙ
        DirectoriesField fieldBoolean = new DirectoriesField()
                .setFieldName("Логический " + BaseTest.randomString(5))
                .setFieldID("BOOLEAN" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsBoolean());

        // 10. ТЕЛЕФОН
        DirectoriesField fieldPhone = new DirectoriesField()
                .setFieldName("Телефон " + BaseTest.randomString(5))
                .setFieldID("PHONE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsPhone());

        // 11. EMAIL
        DirectoriesField fieldEmail = new DirectoriesField()
                .setFieldName("Email " + BaseTest.randomString(5))
                .setFieldID("EMAIL" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsEmail());

        // 12. ИЗОБРАЖЕНИЕ
        DirectoriesField fieldImage = new DirectoriesField()
                .setFieldName("Изображение " + BaseTest.randomString(5))
                .setFieldID("IMAGE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsImage());

        // 13. ЦВЕТ
        DirectoriesField fieldColor = new DirectoriesField()
                .setFieldName("Цвет " + BaseTest.randomString(5))
                .setFieldID("COLOR" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsColor())
                .setIsUniqueField(true); // Уникальное

        // 14. ВЛОЖЕННЫЙ СПРАВОЧНИК
        DirectoriesField fieldEnclosedDirectory = new DirectoriesField()
                .setFieldName("Вложенный спр-к " + BaseTest.randomString(5))
                .setFieldID("ENCLOSEDDIRECTORY" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsEnclosedDirectory()
                        .setDirectoryName("Банк"));

        // 15. ПОДРАЗДЕЛЕНИЕ
        DirectoriesField fieldDepartment = new DirectoriesField()
                .setFieldName("Подразделение " + BaseTest.randomString(5))
                .setFieldID("DEPARTMENT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDepartment());

        // Инициализируем объект - Справочник
        Directories directories = new Directories("wD_Справочник " + BaseTest.randomString(10))

                // Вкладка - Настройки
                .setShareRecords(true) // Общедоступность записей
                .setAccessToRecords(true) // Настройка доступа к записям
                .setMappingDevice(true) // Способ отображения - Линейный ли? true - да; false - иерархический
                .setSearchSettings(true) // true - поиск записей через SOLR; false - поиск записей через БД
                .setDirectoriesFields(new DirectoriesField[]{fieldStringIsListChoice, fieldStringIsNotListChoice, fieldText, fieldInt, fieldFloat, fieldDate, fieldFileEdit,
                        fieldFile, fieldDirectory, fieldMultiDirectory, fieldBoolean, fieldPhone, fieldEmail, fieldImage, fieldColor, fieldEnclosedDirectory, fieldDepartment});

        return new Object[][]{

                {
                        //--------------------------------------------------------------------Инициализация объекта - СПРАВОЧНИКИ
                        directories,
                }
        };

    }

    //---Администрирование----------------------------------------------------------
    //-----Типы задач----------------------------------------------------------

    /**
     * Параметризация - Инициализируем модель - Типы задач (со всеми надстройками)
     */
    @DataProvider
    public Object[][] objectDataTasksTypes() {

        //------------------------------------------------------------------------Инициализация полей и настроек объекта - СПРАВОЧНИКИ
        // 1. СТРОКА (Выбор из списка == Да; Уникальное; Обязательное)
        DirectoriesField fieldStringIsListChoiceDirectories = new DirectoriesField()
                .setFieldName("Строка (Выбор из списка == Да; Обяз.) " + BaseTest.randomString(10))
                .setFieldID("STRING" + BaseTest.randomIdentifier(5))
                .setObligatory(true) // Обязательное поле
                .setIsUniqueField(true) // Уникальное
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(true) // Выбор из списка
                        .setValuesList(BaseTest.randomString(10) + "\n" + BaseTest.randomString(10) + "\n" + BaseTest.randomString(10))); // Список значений

        // 2. ТЕКСТ
        DirectoriesField fieldTextDirectory = new DirectoriesField()
                .setFieldName("Текст " + BaseTest.randomString(10))
                .setFieldID("TEXT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsText());

        // 3. ЦЕЛОЕ
        DirectoriesField fieldIntDirectory = new DirectoriesField()
                .setFieldName("Целое " + BaseTest.randomString(10))
                .setFieldID("INTEGER" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsInt());

        // Инициализируем объект - Справочник
        Directories directories = new Directories("wD_Directories_for_TasksTypes " + BaseTest.randomString(10))

                // Вкладка - Настройки
                .setShareRecords(true) // Общедоступность записей
                .setAccessToRecords(true) // Настройка доступа к записям
                .setMappingDevice(true) // Способ отображения - Линейный ли? true - да; false - иерархический
                .setSearchSettings(true) // true - поиск записей через SOLR; false - поиск записей через БД
                .setDirectoriesFields(new DirectoriesField[]{fieldStringIsListChoiceDirectories, fieldTextDirectory, fieldIntDirectory});


        //--------------------------------------------------------------------------Инициализация полей и настроек объекта - ТИПЫ ТАБЛИЦ

        // 1. СТРОКА (Выбор из списка == Да; Обязательное)
        TypesOfTablesField fieldStringTypesTable = new TypesOfTablesField()
                .setFieldName("Строка (Выбор из списка == Да) " + BaseTest.randomString(10))
                .setFieldID("STRING" + BaseTest.randomIdentifier(5))
                .setObligatory(true) // Обязательное поле
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(true) // Выбор из списка
                        .setValuesList(BaseTest.randomString(10) + "\n" + BaseTest.randomString(10) + "\n" + BaseTest.randomString(10))); // Список значений

        // 2. ТЕКСТ
        TypesOfTablesField fieldTextTypesTable = new TypesOfTablesField()
                .setFieldName("Текст " + BaseTest.randomString(10))
                .setFieldID("TEXT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsText());

        // 3. ЦЕЛОЕ (Ссылка на объект == Задача)
        TypesOfTablesField fieldIntLinkObjectTypesTable = new TypesOfTablesField()
                .setFieldName("Целое (Ссылка на объект == Задача) " + BaseTest.randomString(10))
                .setFieldID("INTEGER" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsInt()
                        .setObjectLink(true)); // Ссылка на объект


        // Инициализация объекта - Типы таблиц
        TypesOfTables typesOfTables = new TypesOfTables("wD_TypesOfTables_for_TasksTypes " + BaseTest.randomString(10))
                .setTypesOfTablesFields(new TypesOfTablesField[]{fieldStringTypesTable, fieldTextTypesTable, fieldIntLinkObjectTypesTable});


        //---------------------------------------------------------------------------------------------------------Инициализация полей и объекта - ТИПЫ ЗАДАЧ

        // 1. СТРОКА (Выбор из списка == Да)
        TasksTypesField fieldStringIsListChoice = new TasksTypesField()
                .setFieldName("Строка (Выбор из списка == Да; Обязательное) " + BaseTest.randomString(5))
                .setFieldID("STRING" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(true) // Выбор из списка
                        .setValuesList(BaseTest.randomString(10) + "\n" + BaseTest.randomString(10) + "\n" + BaseTest.randomString(10))) // Список значений
                .setObligatory(ObligatoryFieldTypeTask.OBLIGATORY_ON_CREATE) // Обязательное при создании
                .setIsHideInSearch(true);

        // 1.1. СТРОКА (Выбор из списка == Нет; Обязательное при завершении)
        TasksTypesField fieldStringIsNotListChoice = new TasksTypesField()
                .setFieldName("Строка (Выбор из списка == Нет; Обяз. при завершении) " + BaseTest.randomString(5))
                .setFieldID("STRNOTLIST" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(false) // Выбор из списка
                        .setValuesList(BaseTest.randomString(10) + "\n" + BaseTest.randomString(10) + "\n" + BaseTest.randomString(10))) // Список значений
                .setObligatory(ObligatoryFieldTypeTask.OBLIGATORY_ON_CLOSE); // Обязательное при завершении

        // 2. ТЕКСТ (Скрывать при поиске)
        TasksTypesField fieldText = new TasksTypesField()
                .setFieldName("Текст " + BaseTest.randomString(10))
                .setFieldID("TEXT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsText())
                .setIsHideInSearch(true); // Скрывать при поиске

        // 3. ЦЕЛОЕ
        TasksTypesField fieldInt = new TasksTypesField()
                .setFieldName("Целое " + BaseTest.randomString(10))
                .setFieldID("INTEGER" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsInt());

        // 4. ВЕЩЕСТВЕННОЕ
        TasksTypesField fieldFloat = new TasksTypesField()
                .setFieldName("Вещественное " + BaseTest.randomString(10))
                .setFieldID("FLOAT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDouble());

        // 5. ДАТА
        TasksTypesField fieldDate = new TasksTypesField()
                .setFieldName("Дата " + BaseTest.randomString(10))
                .setFieldID("DATE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDate());

        // 6. ССЫЛКА НА СПР-К
        TasksTypesField fieldDirectory = new TasksTypesField()
                .setFieldName("Ссылка на справочник " + BaseTest.randomString(10))
                .setFieldID("DIRECTORY" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDirectory()
                        .setDirectories(directories) // Выбираем проинициализированный объект - Справочник
                        .setFieldDirectory(fieldStringIsListChoiceDirectories)  // Выбираем проинициализированный объект - поле Справочника
                        .setDisplayNameTemplate("{STRING}-обычный текст-" + "{" + BaseTest.randomIdentifier(5) + "}"));

        // 7. МНОЖЕСТВЕННАЯ ССЫЛКА НА СПР-К
        TasksTypesField fieldMultiDirectory = new TasksTypesField()
                .setFieldName("Множественная ссылка на справочник " + BaseTest.randomString(5))
                .setFieldID("DIRMULTI" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsMultiDirectory()
                        .setDirectoryName("Банк")
                        .setNameDirectoryField("Адрес")
                        .setDisplayNameTemplate("{STRING}-обычный текст-" + "{" + BaseTest.randomIdentifier(5) + "}"));

        // 8. ЛОГИЧЕСКИЙ
        TasksTypesField fieldBoolean = new TasksTypesField()
                .setFieldName("Логический " + BaseTest.randomString(5))
                .setFieldID("BOOLEAN" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsBoolean());

        // 9. ССЫЛКА НА БИБЛИОТЕКУ
        TasksTypesField libraryLink = new TasksTypesField()
                .setFieldName("Ссылка на библиотеку " + BaseTest.randomString(5))
                .setFieldID("LIBLINK" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsLibraryLink());

        // 10. ССЫЛКА НА ЗАДАЧУ
        TasksTypesField fieldReferenceToTheTask = new TasksTypesField()
                .setFieldName("Ссылка на задачу " + BaseTest.randomString(5))
                .setFieldID("REFTHETASK" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsReferenceTask());

        // 11. НУМЕРАТОР
        TasksTypesField fieldNumerator = new TasksTypesField()
                .setFieldName("Нумератор " + BaseTest.randomString(5))
                .setFieldID("NUMERATOR" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsNumerator()
                        .setNumeratorTemplate("{counter}-{" + fieldStringIsNotListChoice.getFieldID() + "}-{DD}.{YYYY} "
                                + BaseTest.randomString(15)) // Шаблон нумератора
                        .setComputeMode(ComputeModeNumerator.WHEN_CREATING_TASK)); // Режим вычисления - При создании задачи

        // 12. ССЫЛКА НА ОБЪЕКТ
        TasksTypesField fieldObjectLink = new TasksTypesField()
                .setFieldName("Ссылка на объект " + BaseTest.randomString(5))
                .setFieldID("OBJECTLINK" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsObjectLink());

        // 13. ТАБЛИЦА
        TasksTypesField fieldTable = new TasksTypesField()
                .setFieldName("Таблица " + BaseTest.randomString(5))
                .setFieldID("TABLE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsTable()
                        .setTypesOfTables(typesOfTables)
                        .setFieldTable(fieldStringTypesTable)
                        .setNumeratorTemplate("{STRING}-обычный текст-" + "{" + BaseTest.randomIdentifier(5) + "}")); // Шаблон отображения

        // 14. ПОДРАЗДЕЛЕНИЕ
        TasksTypesField fieldDepartment = new TasksTypesField()
                .setFieldName("Подразделение " + BaseTest.randomString(5))
                .setFieldID("DEPARTMENT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDepartment());

        // 15. СОТРУДНИК
        TasksTypesField fieldEmployee = new TasksTypesField()
                .setFieldName("Сотрудник " + BaseTest.randomString(5))
                .setFieldID("EMPLOYEE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsEmployee());


        // Инициализация объекта - Типы задач с настройками
        TasksTypes tasksTypes = new TasksTypes("wD_Тип задачи " + BaseTest.randomString(20))

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
                        {fieldStringIsListChoice, fieldStringIsNotListChoice, fieldText, fieldInt,
                                fieldFloat, fieldDate, fieldDirectory, fieldMultiDirectory, fieldBoolean, libraryLink, fieldReferenceToTheTask,
                                fieldNumerator, fieldObjectLink, fieldTable, fieldDepartment, fieldEmployee});

        return new Object[][]{

                {
                        // Справочник
                        directories,
                        // Типы таблиц
                        typesOfTables,
                        // Типы задач
                        tasksTypes,
                }
        };


    }

    //---Администрирование----------------------------------------------------------
    //-----Типы таблиц----------------------------------------------------------

    /**
     * Параметризация - Инициализируем модель - Типы таблиц (со всеми надстройками)
     */
    @DataProvider
    public Object[][] objectDataTypesOfTable() {

        //---------------------------------------------------------------------------------------------------------Инициализация полей и объекта - СПРАВОЧНИКИ

        // 1. СТРОКА (Выбор из списка == Да; Уникальное; Обязательное)
        DirectoriesField fieldStringIsListChoiceDirectories = new DirectoriesField()
                .setFieldName("Строка (Выбор из списка == Да; Обяз.) " + BaseTest.randomString(10))
                .setFieldID("STRING" + BaseTest.randomIdentifier(5))
                .setObligatory(true) // Обязательное поле
                .setIsUniqueField(true) // Уникальное
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(true) // Выбор из списка
                        .setValuesList(BaseTest.randomString(10) + "\n" + BaseTest.randomString(10) + "\n" + BaseTest.randomString(10))); // Список значений

        // 2. ТЕКСТ
        DirectoriesField fieldTextDirectories = new DirectoriesField()
                .setFieldName("Текст " + BaseTest.randomString(10))
                .setFieldID("TEXT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsText());

        // 3. ЦЕЛОЕ
        DirectoriesField fieldIntDirectories = new DirectoriesField()
                .setFieldName("Целое " + BaseTest.randomString(10))
                .setFieldID("INTEGER" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsInt());

        // Инициализируем объект - Справочник
        Directories directories = new Directories("wD_Directories_for_TypesOfTable " + BaseTest.randomString(10))

                // Вкладка - Настройки
                .setShareRecords(true) // Общедоступность записей
                .setAccessToRecords(true) // Настройка доступа к записям
                .setMappingDevice(true) // Способ отображения - Линейный ли? true - да; false - иерархический
                .setSearchSettings(true) // true - поиск записей через SOLR; false - поиск записей через БД
                .setDirectoriesFields(new DirectoriesField[]{fieldStringIsListChoiceDirectories, fieldTextDirectories, fieldIntDirectories});

        //---------------------------------------------------------------------------------------------------------Инициализация полей и настроек объекта - ТИПЫ ТАБЛИЦ

        // 1. СТРОКА (Выбор из списка == Да; Обязательное)
        TypesOfTablesField fieldStringIsListChoice = new TypesOfTablesField()
                .setFieldName("Строка (Выбор из списка == Да; Обязательное) " + BaseTest.randomString(5))
                .setFieldID("STRING" + BaseTest.randomIdentifier(5))
                .setObligatory(true) // Обязательное поле
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(true) // Выбор из списка
                        .setValuesList(BaseTest.randomString(10) + "\n" + BaseTest.randomString(10) + "\n" + BaseTest.randomString(10))); // Список значений

        // 1.1. СТРОКА (Выбор из списка == Нет)
        TypesOfTablesField fieldStringIsNotListChoice = new TypesOfTablesField()
                .setFieldName("Строка (Выбор из списка == Нет) " + BaseTest.randomString(10))
                .setFieldID("STRING" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsString()
                        .setIsListChoice(false) // Выбор из списка
                        .setValuesList(BaseTest.randomString(10) + "\n" + BaseTest.randomString(10) + "\n" + BaseTest.randomString(10))); // Список значений

        // 2. ТЕКСТ
        TypesOfTablesField fieldText = new TypesOfTablesField()
                .setFieldName("Текст " + BaseTest.randomString(10))
                .setFieldID("TEXT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsText());

        // 3. ЦЕЛОЕ (Ссылка на объект == Задача)
        TypesOfTablesField fieldIntLinkObject = new TypesOfTablesField()
                .setFieldName("Целое (Ссылка на объект == Задача) " + BaseTest.randomString(10))
                .setFieldID("INTEGER" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsInt()
                        .setObjectLink(true)); // Ссылка на объект

        // 3.1. ЦЕЛОЕ (Ссылка на объект == Нет)
        TypesOfTablesField fieldInt = new TypesOfTablesField()
                .setFieldName("Целое (Ссылка на объект == Нет) " + BaseTest.randomString(10))
                .setFieldID("INTEGER" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsInt()
                        .setObjectLink(false));

        // 4. ВЕЩЕСТВЕННОЕ
        TypesOfTablesField fieldFloat = new TypesOfTablesField()
                .setFieldName("Вещественное " + BaseTest.randomString(10))
                .setFieldID("FLOAT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDouble());

        // 5. ДАТА
        TypesOfTablesField fieldDate = new TypesOfTablesField()
                .setFieldName("Дата " + BaseTest.randomString(10))
                .setFieldID("DATE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDate());

        // 6. ФАЙЛ (Редактирование == Да)
        TypesOfTablesField fieldFileEdit = new TypesOfTablesField()
                .setFieldName("Файл (Редактирование == Да) " + BaseTest.randomString(10))
                .setObligatory(true)
                .setFieldID("FILEDIT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsFile()
                        .setOpenFilesForEdit(OpenFilesForEdit.YES)); // Редактирование файлов - Да

        // 6.1. ФАЙЛ (Редактирование == Нет)
        TypesOfTablesField fieldFile = new TypesOfTablesField()
                .setFieldName("Файл (Редактирование == Нет) " + BaseTest.randomString(10))
                .setFieldID("FILE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsFile()
                        .setOpenFilesForEdit(OpenFilesForEdit.NO)); // Редактирование файлов - Нет

        // 7. ССЫЛКА НА СПР-К
        TypesOfTablesField fieldDirectory = new TypesOfTablesField()
                .setFieldName("Ссылка на справочник " + BaseTest.randomString(10))
                .setFieldID("DIRECTORY" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDirectory()
                        .setDirectoryName("Адресная книга")
                        .setNameDirectoryField("Имя"));

        // 8. МНОЖЕСТВЕННАЯ ССЫЛКА НА СПР-К
        TypesOfTablesField fieldMultiDirectory = new TypesOfTablesField()
                .setFieldName("Множественная ссылка на справочник " + BaseTest.randomString(5))
                .setFieldID("DIRMULTI" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsMultiDirectory()
                        .setDirectories(directories)
                        .setFieldDirectory(fieldStringIsListChoiceDirectories));

        // 9. ЛОГИЧЕСКИЙ
        TypesOfTablesField fieldBoolean = new TypesOfTablesField()
                .setFieldName("Логический " + BaseTest.randomString(5))
                .setFieldID("BOOLEAN" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsBoolean());

        // 10. ТЕЛЕФОН
        TypesOfTablesField fieldPhone = new TypesOfTablesField()
                .setFieldName("Телефон " + BaseTest.randomString(5))
                .setFieldID("PHONE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsPhone());

        // 11. EMAIL
        TypesOfTablesField fieldEmail = new TypesOfTablesField()
                .setFieldName("Email " + BaseTest.randomString(5))
                .setFieldID("EMAIL" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsEmail());

        // 12. ИЗОБРАЖЕНИЕ
        TypesOfTablesField fieldImage = new TypesOfTablesField()
                .setFieldName("Изображение " + BaseTest.randomString(5))
                .setFieldID("IMAGE" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsImage());

        // 13. ЦВЕТ
        TypesOfTablesField fieldColor = new TypesOfTablesField()
                .setFieldName("Цвет " + BaseTest.randomString(5))
                .setFieldID("COLOR" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsColor());

        // 14. ПОДРАЗДЕЛЕНИЕ
        TypesOfTablesField fieldDepartment = new TypesOfTablesField()
                .setFieldName("Подразделение " + BaseTest.randomString(5))
                .setFieldID("DEPARTMENT" + BaseTest.randomIdentifier(5))
                .setFieldType(new TypeListFieldsDepartment());


        // Инициализируем объект - Типы таблиц
        TypesOfTables typesOfTables = new TypesOfTables("wD_Типы таблиц " + BaseTest.randomString(10))

                // Вкладка - Настройки
                .setTypesOfTablesFields(new TypesOfTablesField[]{fieldStringIsListChoice, fieldStringIsNotListChoice, fieldText, fieldIntLinkObject,
                        fieldInt, fieldFloat, fieldDate, fieldFileEdit, fieldFile, fieldDirectory, fieldMultiDirectory, fieldBoolean, fieldPhone,
                        fieldEmail, fieldImage, fieldColor, fieldDepartment});

        return new Object[][]{
                {
                        // Справочник
                        directories,
                        // Типы таблиц
                        typesOfTables,
                }
        };


    }

}
