package ru.st.selenium.tests.testWeb;

import com.codeborne.selenide.testng.SoftAsserts;
import com.codeborne.selenide.testng.TextReport;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.Tasks.Folder;
import ru.st.selenium.model.Tasks.Task;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pageselementsweb.Administration.CreateDepartmentPage;
import ru.st.selenium.pages.pageselementsweb.Administration.CreateUsersPage;
import ru.st.selenium.pages.pageselementsweb.Internal.InternalPage;
import ru.st.selenium.pages.pageselementsweb.Login.LoginPage;
import ru.st.selenium.pagesteps.Tasks.UnionMessageNewPageSteps;
import ru.st.selenium.pagesteps.Tasks.UnionMessagePageSteps;
import ru.st.selenium.pagesteps.Tasks.UnionTasksPageSteps;
import ru.st.selenium.tests.data.system.ModuleTaskCaseTest;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class, SoftAsserts.class})
@Features("Создать задачу (Web)")
@Title("Проверка создания задач в Web-интерфейсе")
public class CreateTaskTest extends ModuleTaskCaseTest {

    // Папка
    Folder[] folder = getRandomArrayFolders();

    @Severity(SeverityLevel.BLOCKER)
    @Title("Создание смарт-папки с ")
    @Description("Проверяем создание задачи с набором атрибутов")
    @Test(priority = 1)
    public void aPreconditionForFurtherVerification() {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; TasksElements; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------- Задачи/Задачи
        UnionTasksPageSteps unionTasksPageSteps = internalPage.goToUnionTasks();
        unionTasksPageSteps.beforeAddFolder(19);
        // Добавляем Папки(/у)
        unionTasksPageSteps.addFolders(new Folder[]{folder[0].setNameFolder("wD_Smart_Box " + randomString(4)).setUseFilter(true).setFilterField("Начало").setChooseRelativeValue(true)
                .setSharedFolder(false).setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false)});

        internalPage.logout();
        Assert.assertTrue(loginPage.isNotLoggedIn());
    }

    /**
     * Проверка создания обычной задачи
     *
     * @param department    атрибуты пользователя
     * @param author        атрибуты пользователя Автор
     * @param resppers      атрибуты пользователя Ответственный руководитель
     * @param controller    атрибуты пользователя Контролер
     * @param worker        атрибуты пользователя Исполнитель
     * @param IWGWorker     атрибуты пользователя Исполнитель задачи ИРГ
     * @param IWGResppers   атрибуты пользователя ОР задачи ИРГ
     * @param IWGСontroller атрибуты пользователя
     * @param task          атрибуты - значения задачи
     */
    @Severity(SeverityLevel.BLOCKER)
    @Title("Создание задачи")
    @Description("Проверяем создание задачи с набором атрибутов")
    @Test(priority = 2, dataProvider = "objectDataTask")
    public void verifyCreateTask(Department department, Employee[] author, Employee[] resppers, Employee[] controller, Employee[] worker,
                                 Employee[] IWGWorker, Employee[] IWGResppers, Employee[] IWGСontroller, Task task) {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; TasksElements; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Создаем подразделения для пользователей
        CreateDepartmentPage createDepartmentPage = internalPage.goToDepartments();
        createDepartmentPage.beforeAdd();
        createDepartmentPage.createDepartment(department);

        // Инициализируем страницу - Администрирование/Пользователи
        CreateUsersPage createUsersPage = internalPage.initializationUsersPage();
        // Авторы задачи
        createUsersPage.createUser(author[0].setDepartment(department));
        createUsersPage.createUser(author[1].setDepartment(department));
        // Ответственные руководители задачи
        createUsersPage.createUser(resppers[0].setDepartment(department));
        // Контролеры задачи
        createUsersPage.createUser(controller[0].setDepartment(department));
        // Исполнители задачи
        createUsersPage.createUser(worker[0].setDepartment(department));
        createUsersPage.createUser(worker[1].setDepartment(department));

        // Инициализация и переход на страницу - Задачи/Создать задачу
        UnionMessageNewPageSteps unionMessageNewPageSteps = internalPage.goToUnionMessageNew();
        unionMessageNewPageSteps.creatingTask(task);

        /*
         Проверяем отображение созданной задачи в гриде.
         Инициализация и переход на страницу - Задачи/Создать задачу
          */
        UnionTasksPageSteps unionTasksPageSteps = internalPage.goToUnionTasks();
        unionTasksPageSteps.openAnExistingTaskInFolder(task, folder[0]);

        UnionMessagePageSteps unionMessagePageSteps = unionTasksPageSteps.initializationUnionMessagePage();
        unionMessagePageSteps.verifyCreateTask(task);

        // Выход
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());

    }

    /**
     * Проверяем создание задачи типа ИРГ
     *
     * @param department    подразделение, в к-е будет добавлятсья пользователь
     * @param author        Авторы задачи
     * @param resppers      Ответственные руководители задачи
     * @param controller    Контролеры задачи
     * @param worker        Исполнители задачи
     * @param IWGWorker     Исполнители задачи ИРГ
     * @param IWGResppers   ОР задачи ИРГ
     * @param IWGСontroller Контролеры задачи
     * @param task          Задача со всеми её параметрами
     */
    @Severity(SeverityLevel.BLOCKER)
    @Title("Создание задачи типа ИРГ")
    @Description("Проверяем создание задачи ИРГ с набором атрибутов")
    @Test(priority = 3, dataProvider = "objectDataTask")
    public void verifyCreateIWGTask(Department department, Employee[] author, Employee[] resppers, Employee[] controller, Employee[] worker,
                                    Employee[] IWGWorker, Employee[] IWGResppers, Employee[] IWGСontroller, Task task) {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; TasksElements; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Создаем подразделения для пользователей
        CreateDepartmentPage createDepartmentPage = internalPage.goToDepartments();
        createDepartmentPage.beforeAdd();
        createDepartmentPage.createDepartment(department);

        // Инициализируем страницу - Администрирование/Пользователи
        CreateUsersPage createUsersPage = internalPage.initializationUsersPage();

        // Ответственные руководители задачи
        createUsersPage.createUser(resppers[0].setDepartment(department));

        // Исполнители задачи ИРГ
        createUsersPage.createUser(IWGWorker[0].setDepartment(department));
        createUsersPage.createUser(IWGWorker[1].setDepartment(department));
        createUsersPage.createUser(IWGWorker[2].setDepartment(department));
        // Ответственные руководители задачи ИРГ
        createUsersPage.createUser(IWGResppers[0].setDepartment(department));
        createUsersPage.createUser(IWGResppers[1].setDepartment(department));
        // Контролеры задачи ИРГ
        createUsersPage.createUser(IWGСontroller[0].setDepartment(department));
        createUsersPage.createUser(IWGСontroller[1].setDepartment(department));

        // Инициализация и переход на страницу - Задачи/Создать задачу
        UnionMessageNewPageSteps unionMessageNewPageSteps = internalPage.goToUnionMessageNew();
        unionMessageNewPageSteps.creatingTaskWithTheTaskOfIWG(task);

        /*
         Проверяем отображение созданной задачи в гриде.
         Инициализация и переход на страницу - Задачи/Создать задачу
          */
        UnionTasksPageSteps unionTasksPageSteps = internalPage.goToUnionTasks();
        unionTasksPageSteps.openAnExistingTaskInFolder(task, folder[0]);

        UnionMessagePageSteps unionMessagePageSteps = unionTasksPageSteps.initializationUnionMessagePage();
        unionMessagePageSteps.verifyCreateTask(task);

        // Выход
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Title("Создание задачи с КТ (контрольные точки)")
    @Description("Проверяем создание задачи c набором Контрольных точек")
    @Test(priority = 4, dataProvider = "objectDataTask")
    public void checkTheCreationOfATaskCheckpoints(Department department, Employee[] author, Employee[] resppers, Employee[] controller, Employee[] worker,
                                                   Employee[] IWGWorker, Employee[] IWGResppers, Employee[] IWGСontroller, Task task) {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; TasksElements; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Инициализация и переход на страницу - Задачи/Создать задачу
        UnionMessageNewPageSteps unionMessageNewPageSteps = internalPage.goToUnionMessageNew();
        unionMessageNewPageSteps.creationOfATaskCheckpoints(task);

        /*
         Проверяем отображение созданной задачи в гриде.
         Инициализация и переход на страницу - Задачи/Создать задачу
          */
        UnionTasksPageSteps unionTasksPageSteps = internalPage.goToUnionTasks();
        unionTasksPageSteps.openExistingTaskInTheFolderThroughTheSearch(task, folder[0]);

        UnionMessagePageSteps unionMessagePageSteps = unionTasksPageSteps.initializationUnionMessagePage();
        unionMessagePageSteps.verifyCreateTask(task);

        // Выход
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());
    }


}

