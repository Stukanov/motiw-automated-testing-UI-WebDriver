package ru.st.selenium.tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.Task.Task;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Administration.CreateDepartmentPage;
import ru.st.selenium.pages.pagesweb.Administration.CreateUsersPage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.pages.pagesweb.Tasks.UnionMessageNewPage;
import ru.st.selenium.pages.pagesweb.Tasks.UnionMessagePage;
import ru.st.selenium.pages.pagesweb.Tasks.UnionTasksPage;
import ru.st.selenium.tests.data.system.ModuleTaskTestCase;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Создать задачу")
@Title("Проверка создания задач в Web-интерфейсе")
public class CreateTaskTest extends ModuleTaskTestCase {

    /**
     * Проверка создания обычной задачи
     *
     * @param department
     * @param author
     * @param resppers
     * @param controller
     * @param worker
     * @param IWGWorker
     * @param IWGResppers
     * @param IWGСontroller
     * @param task
     */
    @Severity(SeverityLevel.BLOCKER)
    @Title("Создание задачи")
    @Description("Проверяем создание задачи с набором атрибутов")
    @Test(priority = 1, dataProvider = "objectDataTask")
    public void verifyCreateTaskTest(Department department, Employee[] author, Employee[] resppers, Employee[] controller, Employee[] worker,
                               Employee[] IWGWorker, Employee[] IWGResppers, Employee[] IWGСontroller, Task task) throws Exception {

        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
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
        UnionMessageNewPage unionMessageNewPage = internalPage.goToUnionMessageNew();
        unionMessageNewPage.creatingTask(task);

        /*
         Проверяем отображение созданной задачи в гриде.
         Инициализация и переход на страницу - Задачи/Создать задачу
          */
        UnionTasksPage unionTasksPage = internalPage.goToUnionTasks();
        unionTasksPage.openTask(task);

        UnionMessagePage unionMessagePage = unionTasksPage.initializationUnionMessagePage();
        unionMessagePage.verifyCreateTask(task);

        // Выход
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());

    }

    /**
     * Проверяем создание задачи типа ИРГ
     * @param department подразделение, в к-е будет добавлятсья пользователь
     *
     * @param author Авторы задачи
     * @param resppers Ответственные руководители задачи
     * @param controller Контролеры задачи
     * @param worker Исполнители задачи
     *
     * @param IWGWorker Исполнители задачи ИРГ
     * @param IWGResppers ОР задачи ИРГ
     * @param IWGСontroller Контролеры задачи
     *
     * @param task Задача со всеми её параметрами
     */
    @Severity(SeverityLevel.BLOCKER)
    @Title("Создание задачи типа ИРГ")
    @Description("Проверяем создание задачи ИРГ с набором атрибутов")
    @Test(priority = 2, dataProvider = "objectDataTask")
    public void verifyCreateIWGTask(Department department, Employee[] author, Employee[] resppers, Employee[] controller, Employee[] worker,
                               Employee[] IWGWorker, Employee[] IWGResppers, Employee[] IWGСontroller, Task task) throws Exception {

        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
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
        UnionMessageNewPage unionMessageNewPage = internalPage.goToUnionMessageNew();
        unionMessageNewPage.creatingTaskWithTheTaskOfIWG(task);

        /*
         Проверяем отображение созданной задачи в гриде.
         Инициализация и переход на страницу - Задачи/Создать задачу
          */
        UnionTasksPage unionTasksPage = internalPage.goToUnionTasks();
        unionTasksPage.openTask(task);

        UnionMessagePage unionMessagePage = unionTasksPage.initializationUnionMessagePage();
        unionMessagePage.verifyCreateTask(task);

        // Выход
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Title("Создание задачи с КТ (контрольные точки)")
    @Description("Проверяем создание задачи c набором Контрольных точек")
    @Test(priority = 2, dataProvider = "objectDataTask")
    public void checkTheCreationOfATaskCheckpoints(Department department, Employee[] author, Employee[] resppers, Employee[] controller, Employee[] worker,
                                    Employee[] IWGWorker, Employee[] IWGResppers, Employee[] IWGСontroller, Task task) throws Exception {

        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Инициализация и переход на страницу - Задачи/Создать задачу
        UnionMessageNewPage unionMessageNewPage = internalPage.goToUnionMessageNew();
        unionMessageNewPage.creationOfATaskCheckpoints(task);

        /*
         Проверяем отображение созданной задачи в гриде.
         Инициализация и переход на страницу - Задачи/Создать задачу
          */
        UnionTasksPage unionTasksPage = internalPage.goToUnionTasks();
        unionTasksPage.openTask(task);

        UnionMessagePage unionMessagePage = unionTasksPage.initializationUnionMessagePage();
        unionMessagePage.verifyCreateTask(task);

        // Выход
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());
    }
}
