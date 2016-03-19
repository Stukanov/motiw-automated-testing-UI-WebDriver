package ru.st.selenium.tests.data.system;

import org.testng.annotations.DataProvider;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Period;
import ru.st.selenium.model.Tasks.*;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;

import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.tests.data.BaseTest;

/**
 * Общие данные для работы - инициализация
 */
public abstract class ModuleTaskCaseTest extends BaseTest {


    //----------------------------------------------------------------------Задачи

    /**
     * Метод создания полностью случайного объекта - "Задача" for PDA
     */
    public Task getRandomObjectTask() {
        return new Task()
                .setTaskName(randomString(15) + " " + randomString(30))
                .setDescription(randomString(100) + "\n " + randomString(100) + "\n " + randomString(100))
                .setDateEnd(tomorrowDate())
                .setIsImportant(randomBoolean())
                .setIsSecret(randomBoolean())
                .setIsWithReport(randomBoolean());
    }

    /**
     * Параметризация - Инициализируем модель - Задача for PDA
     */
    @DataProvider
    public Object[][] objectDataTaskPDA() {
        return new Object[][]{

                {new Task().setTaskName(randomString(15) + " " + randomString(30))
                        .setDescription(randomString(100) + "\n " + randomString(100) + "\n " + randomString(100))
                        .setDateEnd(tomorrowDate())
                        .setAuthors(new Employee[]{EMPLOYEE_ADMIN})
                        .setControllers(new Employee[]{EMPLOYEE_ADMIN})
                        .setExecutiveManagers(new Employee[]{EMPLOYEE_ADMIN})
                        .setWorkers(new Employee[]{EMPLOYEE_ADMIN})
                        .setIsSecret(true) // Секретная задача
                        .setIsWithReport(false) // C докладом
                        .setIsImportant(true)}, // Важная задача
        };
    }


    //-----Задачи/Создать задачу/Проекты----------------------------------------------------------

    /**
     * Метод создания полностью случайного объекта - "Проект"
     */
    public Project getRandomProject() {
        return new Project()
                .setDescription(randomString(80))
                .setNameProject(randomString(80))
                .setСlient(randomString(80))
                .setEndDate(randomDateTime());
    }

    /**
     * Метод создания полностью случайного объекта - "Задача" for Web
     */
    public Task getRandomTask() {
        return new Task()
                .setTaskName(randomString(80))
                .setTasktype(new TasksTypes("Обычный"))
                .setDescription(randomString(500))
                .setDateEnd(tomorrowDate())
                .setIsForReview(randomBoolean())
                .setIsImportant(randomBoolean())
                .setIsSecret(randomBoolean())
                .setIsWithReport(randomBoolean())
                .setProject(getRandomProject());
    }

    /**
     * Метод создания полностью случайного объекта - "КТ"
     */
    public Checkpoint getRandomCheckpoint() {
        return new Checkpoint()
                .setDate(randomDateTime())
                .setDescription(randomString(100))
                .setIsReady(randomBoolean())
                .setLinkedTo(randomEnum(LinkedTo.class))
                .setName(randomString(80))
                .setOffset(randomInt(10))
                .setPeriod(randomEnum(Period.class));
    }

    /**
     * Метод создания полностью случайного объекта - "ИРГ"
     */
    public IWG getRandomIWG() {
        return new IWG()
                .setIsSystemActionsInParentTask(randomBoolean())
                .setNameIWG(randomString(80))
                .setTasksTypes(new TasksTypes("Обычный"));
    }

    /**
     * Параметризация - Инициализируем модель - Задача for Web
     */
    @DataProvider
    public Object[][] objectDataTask() {

        //-------------------------------------------------------------------------------------------------------Инициализация объекта - Подразделение
        Department department = getRandomDepartment();
        //-------------------------------------------------------------------------------------------------------Инициализация объекта - Пользователи
        // Авторы задачи
        Employee[] author = new Employee[]{getRandomEmployer().setLastName("Автор задачи " + randomString(5)),
                getRandomEmployer().setLastName("Автор1 задачи " + randomString(5))};
        // Ответственный руководители
        Employee[] executiveManagers = new Employee[]{getRandomEmployer().setLastName("ОР задачи " + randomString(5))};
        // Контролеры
        Employee[] controller = new Employee[]{getRandomEmployer().setLastName("Контролер задачи " + randomString(5))};
        // Исполнители
        Employee[] worker = new Employee[]{getRandomEmployer().setLastName("Исполнитель задачи " + randomString(5)),
                getRandomEmployer().setLastName("Исполнитель1 задачи " + randomString(5))};

        // Исполнители для задачи типа ИРГ
        Employee[] IWGWorker = new Employee[]{getRandomEmployer().setLastName("Исп. ИРГ " + randomString(5)),
                getRandomEmployer().setLastName("Исп.1 ИРГ " + randomString(5)),
                getRandomEmployer().setLastName("Исп.2 ИРГ " + randomString(5))};
        // Ответственный руководители для задачи типа ИРГ
        Employee[] IWGResppers = new Employee[]{getRandomEmployer().setLastName("ОР ИРГ " + randomString(5)),
                getRandomEmployer().setLastName("ОР1 ИРГ " + randomString(5))};
        // Контролеры для задачи типа ИРГ
        Employee[] IWGСontroller = new Employee[]{getRandomEmployer().setLastName("Контролер ИРГ " + randomString(5)),
                getRandomEmployer().setLastName("Контролер ИРГ1 " + randomString(5))};

        //--------------------------------------------------------------------------------------------------Инициализация объекта - Контрольная точка
        Checkpoint[] checkPoint = new Checkpoint[]{getRandomCheckpoint().setIsReady(true).setLinkedTo(LinkedTo.NULL),
                getRandomCheckpoint().setIsReady(false).setLinkedTo(LinkedTo.NULL)};
        //---------------------------------------------------------------------------------------------------Инициализация объекта - задача типа ИРГ
        IWG[] iwg = new IWG[]{getRandomIWG().setNameIWG("ИРГ (трансляция сис. действий из ИРГ) " + randomString(5))
                .setIsSystemActionsInParentTask(true), // транслировать системные действия из ИРГ в родительскую задачу
                getRandomIWG()
                        .setNameIWG("ИРГ1 " + randomString(5))
                        .setIsSystemActionsInParentTask(false),
                getRandomIWG()
                        .setNameIWG("ИРГ3 " + randomString(5))
                        .setIsSystemActionsInParentTask(false)};
        //--------------------------------------------------------------------------------------------------Инициализация объекта - Задача (с атрибутами)
        Task task = getRandomTask()
                .setAuthors(new Employee[]{author[0], author[1]})
                .setControllers(new Employee[]{controller[0]})
                .setWorkers(new Employee[]{worker[0], worker[1]})
                .setExecutiveManagers(new Employee[]{executiveManagers[0]})
                .setCheckpoints(new Checkpoint[]{checkPoint[0], checkPoint[1]})
                .setIWG(new IWG[]{
                        iwg[0]
                                .setRespPersons(new Employee[]{IWGResppers[0], IWGResppers[1]})
                                .setWorkers(new Employee[]{IWGWorker[0], IWGWorker[1], IWGWorker[2]})
                                .setControllers(new Employee[]{IWGСontroller[0]}),
                        iwg[1]
                                .setRespPersons(new Employee[]{IWGResppers[0]})
                                .setWorkers(new Employee[]{IWGWorker[0]})
                                .setControllers(new Employee[]{IWGСontroller[0], IWGСontroller[1]}),
                        iwg[2]
                                .setRespPersons(new Employee[]{IWGResppers[0]})
                                .setWorkers(new Employee[]{IWGWorker[0]})});

        return new Object[][]{

                {
                        department,
                        author,
                        executiveManagers,
                        controller,
                        worker,
                        IWGWorker,
                        IWGResppers,
                        IWGСontroller,
                        task,

                }
        };
    }


    /**
     * Метод создания полностью случайного объект - "Папка"
     *
     * @return folder c атрибутами полей объекта - Папка
     */
    public Folder getRandomFolder() {
        return new Folder()
                .setNameFolder("wD_Box " + randomString(10)) // Зн-ие НЕ изменять - используется в проверке - checkDisplayCreateAFolderInTheGrid()
                .setUseFilter(randomBoolean())
                .setChooseRelativeValue(randomBoolean())
                .setSharedFolder(randomBoolean()) // Общая папка
                .setAddSharedFolderForAll(randomBoolean()) // Добавить всем
                .setAddSharedFolderForNewUsers(randomBoolean());
    }

    /**
     * Метод создания полностью случайного массива объектов - "Папка"
     *
     * @return folders с атрибутами полей объекта - Папка
     */
    public Folder[] getRandomArrayFolders() {
        return new Folder[]{
                new Folder()
                        .setNameFolder("wD_Box " + randomString(10)) // Зн-ие НЕ изменять - используется в проверке - checkDisplayCreateAFolderInTheGrid()
                        .setUseFilter(randomBoolean())
                        .setChooseRelativeValue(randomBoolean())
                        .setSharedFolder(randomBoolean())
                        .setAddSharedFolderForAll(randomBoolean())
                        .setAddSharedFolderForNewUsers(randomBoolean()),

                getRandomFolder()};
    }

}






