package ru.st.selenium.test.testWeb;


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
import ru.st.selenium.test.data.TestRetryAnalyzer;
import ru.st.selenium.test.data.system.ModuleTaskTestCase;
import ru.st.selenium.test.listeners.RetryListener;
import ru.st.selenium.test.listeners.ScreenShotOnFailListener;
import ru.st.selenium.test.listeners.alluretestng.retrylistener.RetryListenerAllure;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class, RetryListenerAllure.class, RetryListener.class})
/**
 * Задачи
 */
public class CreateTaskTest extends ModuleTaskTestCase {

    /**
     * Проверка создания задачи
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
     * @throws Exception
     */
    @Test(priority = 1, dataProvider = "objectDataTask", retryAnalyzer = TestRetryAnalyzer.class)
    public void createTaskTest(Department department, Employee[] author, Employee[] resppers, Employee[] controller, Employee[] worker,
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
        createUsersPage.createUser(resppers[1].setDepartment(department));
        // Контролеры задачи
        createUsersPage.createUser(controller[0].setDepartment(department));
        // Исполнители задачи
        createUsersPage.createUser(worker[0].setDepartment(department));
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
        unionMessageNewPage.createTask(task);

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
