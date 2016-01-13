package ru.st.selenium.test.testWeb;


import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.Administration.Users.Module;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Administration.CreateDepartmentPage;
import ru.st.selenium.pages.pagesweb.Administration.CreateUsersPage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.pages.pagesweb.Login.RestorePasswordPage;
import ru.st.selenium.test.data.TestRetryAnalyzer;
import ru.st.selenium.test.data.system.ModuleAdministrationObjectTestCase;
import ru.st.selenium.test.listeners.RetryListener;
import ru.st.selenium.test.listeners.ScreenShotOnFailListener;
import ru.st.selenium.test.listeners.alluretestng.retrylistener.RetryListenerAllure;


import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class, RetryListenerAllure.class, RetryListener.class})
/**
 * Пользователи
 */
public class UsersTest extends ModuleAdministrationObjectTestCase {


    /*
      * Инициализация переменных - Подразделение
      */
    Department department1 = getRandomDepartment();
    Department department2 = getRandomDepartment();
    Department department2_1 = getRandomDepartment();
    Department department2_1_1 = getRandomDepartment();
    Department editDepartment = getRandomDepartment();
    Department department3 = getRandomDepartment();
    Department department2_2 = getRandomDepartment();

    /*
    * Инициализация переменных - Подразделение для создания пользователей
    */
    Department departmentUser = getRandomDepartment();
    Department departmentUser1 = getRandomDepartment();

    /*
     * Инициализация переменных - Пользователь
     */
    Employee user1 = getRandomEmployer()
            .setNeedsPasswordChange(true) // сбросить признак - "Сменить пароль при следующем входе" (true - изменяем значение; false - оставляем без изменений)
            .setDepartment(departmentUser); // определяем пользователя в подразделение

    Employee user2 = getRandomEmployer()
            .setModule(Module.COMPLETE) // Право на модуль
            .setNeedsPasswordChange(true) // Сменить пароль при следующем входе (true - снимаем признак)
            .setDepartment(departmentUser);

    Employee editUser = getRandomEmployer()
            .setNeedsPasswordChange(false)
            .setModule(Module.COMPLETE)
            .setNeedsPasswordChange(false) // Проверяем редактирование пользователя
            .setDepartment(departmentUser); // передаем подразделения того пользователя, к-го собираемся отредактировать

    Employee changepass = getRandomEmployer()
            .setNeedsPasswordChange(false) // Сменить пароль при следующем входе (false - оставляем признак без изменеиня)
            .setDepartment(departmentUser1);

    Employee workflow = getRandomEmployer()
            .setLastName("WORKFLOW " + randomString(10))
            .setNeedsPasswordChange(true)
            .setModule(Module.WORKFLOW)
            .setDepartment(departmentUser1);

    Employee docflow = getRandomEmployer()
            .setLastName("DOCFLOW " + randomString(10))
            .setNeedsPasswordChange(true)
            .setModule(Module.DOCFLOW)
            .setDepartment(departmentUser1);


    /*
     * проверяем - создание Подразделений
     */
    @Test(priority = 1, retryAnalyzer = TestRetryAnalyzer.class)
    public void verifyCreatingAndRemovalDepartments() throws Exception {
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Инициализируем страницу и переходим на нее - Администрирование/Пользователи
        CreateDepartmentPage createDepartmentPage = internalPage.goToDepartments();

        createDepartmentPage.beforeAdd();
        createDepartmentPage.createDepartment(department1);
        createDepartmentPage.createDepartment(department2);
        /**
         * Создаем подразделение А
         * определяем для подразделения А, родительское подразделение Б, ИНАЧЕ создается, как НЕ дочернее
         * т.е. если есть в памяти - .setParentDepartment (Название родительского под-ия), подразделение создается как Дочернее
         */
        createDepartmentPage.createDepartment(department2_1
                .setParentDepartment(department2));
        createDepartmentPage.createDepartment(department2_1_1
                .setParentDepartment(department2_1));
        createDepartmentPage.dndSavePermissions(department2_1_1, department1);
        createDepartmentPage.createDepartment(editDepartment
                .setParentDepartment(department1));
        createDepartmentPage.editDepartments(editDepartment, department3);
        createDepartmentPage.createDepartment(department2_2
                .setParentDepartment(department2_1));

        /**
         проверяем - удаление ранеее созданных Подразделений
         */
        createDepartmentPage.deleteDepartment(department1);
        createDepartmentPage.deleteDepartment(department2);
        createDepartmentPage.deleteDepartment(department2_1);
        createDepartmentPage.deleteDepartment(department2_1_1);
        createDepartmentPage.deleteDepartment(department3);
        createDepartmentPage.deleteDepartment(department2_2);

        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

    }

    // Проверка - Создание - Пользователи
    @Test(priority = 2, retryAnalyzer = TestRetryAnalyzer.class)
    public void verifyCreatingUsers() throws Exception {
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Создаем подразделения для пользователей
        CreateDepartmentPage createDepartmentPage = internalPage.goToDepartments();
        createDepartmentPage.beforeAdd();
        createDepartmentPage.createDepartment(departmentUser);
        createDepartmentPage.createDepartment(departmentUser1);

        // Инициализируем страницу - Администрирование/Пользователи
        CreateUsersPage createUsersPage = internalPage.initializationUsersPage();

        createUsersPage.beforeAdd();
        createUsersPage.createUser(user1); // Создание пользователя
        createUsersPage.createUser(user2);
        createUsersPage.editUser(editUser, user2);

        createUsersPage.createUser(changepass);
        createUsersPage.createUser(workflow); // Создание пользователя с правом на модуль "WORKFLOW"
        createUsersPage.createUser(docflow); // Создание пользователя с правом на модуль "DOCFLOW"

        /**
         * Проверка создания псевдонима пользователя
         */
        createUsersPage.createAndCheckAliasForDep(user1, departmentUser1);

        internalPage.logout(); // Выход из системы
        assertTrue(loginPage.isNotLoggedIn()); // Проверка того, что пользователь разлогинен

        /**
         * Верификация авторизации - под вновь созданными пользователями
         * user1 - пользователь 1; editUser - пользователь user2 (отредактированный пользователь)
         */
        loginPage.loginAs(user1);
        assertTrue(loginPage.isLoggedInAs(user1));
        internalPage.logout(); // Выход из системы
        assertTrue(loginPage.isNotLoggedIn()); // Проверка того, что пользователь разлогинен

        loginPage.loginAs(editUser);
        assertTrue(loginPage.isLoggedInAs(editUser));
        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

        loginPage.loginAs(changepass);
        RestorePasswordPage restorePasswordPage = createUsersPage.initializationRestorePasswordPage();
        restorePasswordPage.passwordChange(changepass);
        assertTrue(loginPage.isLoggedInAs(changepass));
        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

        loginPage.loginAs(docflow);
        assertTrue(loginPage.isLoggedInAs(docflow));
        createUsersPage.initializationInternalPage().checkUserDocflow();
        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

        loginPage.loginAs(workflow);
        assertTrue(loginPage.isLoggedInAs(workflow));
        createUsersPage.initializationInternalPage().checkUserWorkflow();

        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

    }


    // Проверяем Удаление ранее созданных пользователей и подразделений из Системы
    @Test(dependsOnMethods = "verifyCreatingUsers")
    public void verifyRemovalUsers() throws Exception {

        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Инициализируем страницу и переходим на нее - Администрирование/Пользователи
        CreateDepartmentPage userPage = internalPage.goToDepartments();
        userPage.beforeAdd();

        // Инициализируем страницу - Администрирование/Пользователи
        CreateUsersPage usersPage = internalPage.initializationUsersPage();
        usersPage.deleteUser(user1);
        usersPage.deleteUser(editUser);
        usersPage.deleteUser(changepass);
        usersPage.deleteUser(docflow);
        usersPage.deleteUser(workflow);

        usersPage.deleteDepartment(departmentUser);
        usersPage.deleteDepartment(departmentUser1);

        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());


    }

}

