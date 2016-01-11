package ru.st.selenium.test.data.system;

import org.testng.annotations.DataProvider;
import ru.st.selenium.model.Period;
import ru.st.selenium.model.Task.*;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;

import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.test.data.TestBase;


/**
 * Общие данные для работы - инициализация
 */
public abstract class BaseObjectTestCase extends TestBase {


    //----------------------------------------------------------------Авторизация

    /**
     * Инициализация входных данных для Логин и Пароль
     */
    @DataProvider(name = "verifyFailAuthorization")
    public Object[][] firstNotSuccessfulAuthorizationDataProvider() {
        return new Object[][]{
                {"fail", "admin"},
                {"admin", "fail"},
                {"fail", "fail"},
                {"admin", ""}
        };
    }

    @DataProvider(name = "secondVerifyFailAuthorization")
    public Object[][] secondNotSuccessfulAuthorizationDataProvider() {
        return new Object[][]{
                {"", "admin"},
                {"", ""}
        };
    }

    /**
     * Инициализация входных данных для Логин и Пароль
     */
    @DataProvider(name = "verifyFailAuthorizationWeb")
    public static Object[][] notSuccessfulAuthorizationDataProvider() {
        return new Object[][]{
                {FAIL_ADMIN},
                {ADMIN_FAIL},
                {FAIL_FAIL},
                {ADMIN_NULL},
                {NULL_ADMIN},
                {NULL_NULL}
        };
    }

    //----------------------------------------------------------------------Задачи

    /**
     * Метод создания полностью случайного объекта - "Задача" for PDA
     */
    public Task getRandomObjectTask() {
        Task task = new Task()
                .setTaskName(randomString(15) + " " + randomString(30))
                .setDescription(randomString(100) + "\n " + randomString(100) + "\n " + randomString(100))
                .setDateEnd(tomorrowDate())
                .setIsImportant(randomBoolean())
                .setIsSecret(randomBoolean())
                .setIsWithReport(randomBoolean());
        return task;
    }

    /**
     * Параметризация - Инициализируем модель - Задача for PDA
     */
    @DataProvider
    public Object[][] objectDataTask() {
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
        Project project = new Project()
                .setDescription(randomString(80))
                .setNameProject(randomString(80))
                .setСlient(randomString(80))
                .setEndDate(randomDateTime());
        return project;
    }

    /**
     * Метод создания полностью случайного объекта - "Задача" for Web
     */
    public Task getRandomTask() {
        Task task = new Task()
                .setTaskName(randomString(80))
                .setTasktype(new TasksTypes("Обычный"))
                .setDescription(randomString(500))
                .setDateEnd(tomorrowDate())
                .setIsForReview(randomBoolean())
                .setIsImportant(randomBoolean())
                .setIsSecret(randomBoolean())
                .setIsWithReport(randomBoolean())
                .setProject(getRandomProject());
        return task;
    }

    /**
     * Метод создания полностью случайного объекта - "КТ"
     */
    public Checkpoint getRandomCheckpoint() {
        Checkpoint checkpoint = new Checkpoint()
                .setDate(randomDateTime())
                .setDescription(randomString(100))
                .setIsReady(randomBoolean())
                .setLinkedTo(randomEnum(LinkedTo.class))
                .setName(randomString(80))
                .setOffset(randomInt(10))
                .setPeriod(randomEnum(Period.class));
        return checkpoint;
    }

    /**
     * Метод создания полностью случайного объекта - "ИРГ"
     */
    public IWG getRandomIWG() {
        IWG iwg = new IWG()
                .setIsSystemActionsInParentTask(randomBoolean())
                .setNameIWG(randomString(80))
                .setTasksTypes(new TasksTypes("Обычный"));
        return iwg;
    }


}
