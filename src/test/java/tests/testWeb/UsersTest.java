package tests.testWeb;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.web.elements.elementspagesweb.Administration.Users.DepartmentElements;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Administration.Users.Module;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.elements.elementspagesweb.Login.LoginPage;
import ru.motiw.web.elements.elementspagesweb.Login.RestorePasswordPage;
import ru.motiw.web.steps.Administration.Users.UsersPageSteps;
import tests.listeners.ScreenShotOnFailListener;
import tests.data.system.ModuleAdministrationObjectCaseTest;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Пользователи (Web)")
@Title("Пользователи и Подразделения")
public class UsersTest extends ModuleAdministrationObjectCaseTest {

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
            .setNeedsPasswordChange(false)
            .setDepartment(departmentUser); // Передаем подразделение того пользователя, к-го собираемся отредактировать

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


    @Severity(SeverityLevel.CRITICAL)
    @Title("Проверяем работу объекта - Подразделения")
    @Description("Проверяем создание объекта Подразделение с разным уровнем подчиненности. Редактирование/Удаление подразделений " +
            "и изменения вложенности иерархии Подразделений")
    @Test(priority = 1)
    public void verifyCreatingAndRemovalDepartments() throws Exception {
        LoginPage loginPage = Selenide.open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Инициализируем страницу и переходим на нее - Администрирование/Пользователи
        DepartmentElements departmentElements = internalPage.goToDepartments();

        departmentElements.beforeAdd();
        departmentElements.createDepartment(department1);
        departmentElements.createDepartment(department2);
        /*
         * Создаем подразделение А
         * определяем для подразделения А, родительское подразделение Б, ИНАЧЕ создается, как НЕ дочернее
         * т.е. если есть в памяти - .setParentDepartment (Название родительского под-ия), подразделение создается как Дочернее
         */
        departmentElements.createDepartment(department2_1
                .setParentDepartment(department2));
        departmentElements.createDepartment(department2_1_1
                .setParentDepartment(department2_1));
        departmentElements.dndSavePermissions(department2_1_1, department1);
        departmentElements.createDepartment(editDepartment
                .setParentDepartment(department1));
        departmentElements.editDepartments(editDepartment, department3);
        departmentElements.createDepartment(department2_2
                .setParentDepartment(department2_1));

        /*
         проверяем - удаление ранеее созданных Подразделений
         */
        departmentElements.deleteDepartment(department1);
        departmentElements.deleteDepartment(department2);
        departmentElements.deleteDepartment(department2_1);
        departmentElements.deleteDepartment(department2_1_1);
        departmentElements.deleteDepartment(department3);
        departmentElements.deleteDepartment(department2_2);

        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

    }

    @Severity(SeverityLevel.CRITICAL)
    @Title("Проверяем работу объекта - Пользователи")
    @Description("Проверяем создание, редактирование и пользователей и псевдонимов системы. Верификация под созданными" +
            " пользователями")
    @Test(priority = 2)
    public void verifyCreatingUsers() throws Exception {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Создаем подразделения для пользователей
        DepartmentElements departmentElements = internalPage.goToDepartments();
        departmentElements.beforeAdd();
        departmentElements.createDepartment(departmentUser);
        departmentElements.createDepartment(departmentUser1);

        // Инициализируем страницу - Администрирование/Пользователи
        UsersPageSteps usersPage = internalPage.initializationUsersPage();

        usersPage.beforeAdd();
        usersPage.createUser(user1); // Создание пользователя
        usersPage.createUser(user2);
        usersPage.editUser(user2, editUser);

        usersPage.createUser(changepass);
        usersPage.createUser(workflow); // Создание пользователя с правом на модуль "WORKFLOW"
        usersPage.createUser(docflow); // Создание пользователя с правом на модуль "DOCFLOW"

        /*
         * Проверка создания псевдонима пользователя
         */
        usersPage.createAndCheckAliasForDep(user1, departmentUser1);

        internalPage.logout(); // Выход из системы
        assertTrue(loginPage.isNotLoggedIn()); // Проверка того, что пользователь разлогинен

        /*
         * Верификация авторизации - под вновь созданными пользователями
         * user1 - пользователь 1; editUser - пользователь user2 (отредактированный пользователь)
         */
        loginPage.loginAs(user1);
        assertTrue(loginPage.newUserIsLoggedInAs(user1));
        assertTrue(loginPage.checkTheSystemFolderMappingUserLibrary(user1)); // проверяем отображение системной папки Библиотека пользователя
        internalPage.logout(); // Выход из системы
        assertTrue(loginPage.isNotLoggedIn()); // Проверка того, что пользователь разлогинен

        loginPage.loginAs(editUser);
        assertTrue(loginPage.newUserIsLoggedInAs(editUser));
        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

        loginPage.loginAs(changepass);
        RestorePasswordPage restorePasswordPage = usersPage.initializationRestorePasswordPage();
        restorePasswordPage.passwordChange(changepass);
        assertTrue(loginPage.newUserIsLoggedInAs(changepass));
        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

        loginPage.loginAs(docflow);
        assertTrue(loginPage.newUserIsLoggedInAs(docflow));
        usersPage.initializationInternalPage().checkUserDocflow();
        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

        loginPage.loginAs(workflow);
        assertTrue(loginPage.newUserIsLoggedInAs(workflow));
        usersPage.initializationInternalPage().checkUserWorkflow(17);

        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

    }


    @Severity(SeverityLevel.CRITICAL)
    @Title("Удаление пользователей")
    @Description("Проверяем Удаление ранее созданных пользователей и подразделений из Системы")
    @Test(dependsOnMethods = "verifyCreatingUsers")
    public void verifyRemovalUsers() throws Exception {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Инициализируем страницу и переходим на нее - Администрирование/Пользователи
        DepartmentElements userPage = internalPage.goToDepartments();
        userPage.beforeAdd();

        // Инициализируем страницу - Администрирование/Пользователи
        UsersPageSteps usersPage = internalPage.initializationUsersPage();
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

