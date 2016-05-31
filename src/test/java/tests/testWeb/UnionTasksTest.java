package tests.testWeb;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Administration.Users.Module;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagesweb.Administration.Users.DepartmentElements;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.elements.elementspagesweb.Login.LoginPage;
import ru.motiw.web.steps.Administration.Users.UsersElementsSteps;
import ru.motiw.web.steps.Tasks.UnionTasksPageSteps;
import tests.data.BaseTest;
import tests.data.system.ModuleTaskCaseTest;
import tests.listeners.ScreenShotOnFailListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Задачи / Задачи (Web)")
@Title("Создание папок, работа с Задачами")
public class UnionTasksTest extends ModuleTaskCaseTest {

    // Масси объекта - Папка
    Folder[] folder = getRandomArrayFolders();

    /*
      * Инициализация переменных - Подразделение
      */
    Department department = getRandomDepartment();

    /*
     * Инициализация переменных - Пользователь
     */
    Employee user = getRandomEmployer()
            .setNeedsPasswordChange(true) // сбросить признак - "Сменить пароль при следующем входе" (true - изменяем значение; false - оставляем без изменений)
            .setDepartment(department) // определяем пользователя в подразделение
    .setModule(Module.COMPLETE);

    @Severity(SeverityLevel.CRITICAL)
    @Title("Проверяем работу объекта - Папка")
    @Description("Проверяем создание, редактирование и удаление объекта - Папка. Верификация под созданными")
    @Test(priority = 1)
    public void verifyCreateFolder() {
        LoginPage loginPage = Selenide.open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(BaseTest.ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------- Задачи/Задачи
        UnionTasksPageSteps unionTasksPageSteps = internalPage.goToUnionTasks();
        unionTasksPageSteps.beforeAddFolder(19);
        // Добавляем Папки(/у)
        unionTasksPageSteps.addFolders(new Folder[]{
                folder[0].setNameFolder("wD_Smart_Box " + BaseTest.randomString(4)).setUseFilter(true).setFilterField("Начало").setChooseRelativeValue(true)
                        .setSharedFolder(false).setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false),
                folder[1].setNameFolder("Обычная папка " + BaseTest.randomString(4)).setUseFilter(false).setParentFolder(folder[0]).setSharedFolder(false)
                        .setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false),
                // Общая папка
                folder[2].setNameFolder("Общая папка " + BaseTest.randomString(4)).setUseFilter(false).setSharedFolder(true)
                        .setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false),
                // Добавить всем
                folder[3].setNameFolder("Общая папка + Добавить всем " + BaseTest.randomString(4)).setUseFilter(false).setParentFolder(folder[1])
                        .setSharedFolder(false).setAddSharedFolderForAll(true).setAddSharedFolderForNewUsers(false),
                // Добавить для новых пользователей
                folder[4].setNameFolder("Общая папка + Добавлять для нов. польз. " + BaseTest.randomString(4)).setUseFilter(false).setSharedFolder(true)
                        .setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(true),

        });

        internalPage.logout();
        Assert.assertTrue(loginPage.isNotLoggedIn());

        // TODO 0001 - Редактирование Папки + Удаление чистка после теста!!!
    }

    @Severity(SeverityLevel.NORMAL)
    @Title("Проверяем отображение ОП (общая папка) у пользователей")
    @Description("Проверяем верификацию созданных ОП (Общая папка) - работа опций папки - Добавить всем; Добавлять для новых пользователей ")
    @Test(dependsOnMethods = { "verifyCreateFolder" })
    public void verifyDisplaySharedFolderTheUser() {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(BaseTest.ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Создаем подразделения для пользователей
        DepartmentElements departmentElements = internalPage.goToDepartments();
        departmentElements.beforeAdd();
        departmentElements.createDepartment(department);

        // Инициализируем страницу - Администрирование/Пользователи
        UsersElementsSteps usersPage = internalPage.initializationUsersPage();

        usersPage.beforeAdd();
        usersPage.createUser(user); // Создание пользователя

        // Выход из системы
        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn()); // Проверка того, что пользователь разлогинен

        /*
         * Верификация папки - ОП (общая папка)- под вновь созданными пользователями
         */
        loginPage.loginAs(user);

        //---------------------------------------------------------------- Задачи/Задачи
        UnionTasksPageSteps unionTasksPageSteps = internalPage.goToUnionTasks();

        unionTasksPageSteps.checkTheMapASharedFolderFromTheNewlyCreatedUser(folder[4], 19);

        internalPage.logout(); // Выход из системы
        assertTrue(loginPage.isNotLoggedIn()); // Проверка того, что пользователь разлогинен

        // Авторищируемся вновь под ADMIN - Удаляем Подразделение и Пользователя
        loginPage.loginAs(BaseTest.ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Создаем подразделения для пользователей
        internalPage.goToDepartments();

        departmentElements.beforeAdd();

        // Инициализируем страницу - Администрирование/Пользователи
        usersPage.deleteUser(user); // Пользователь и Подразделение
        departmentElements.deleteDepartment(department);

        // Выход из Системы
        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());

    }

}
