package ru.st.selenium.tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.model.Administration.Directories.Directories;
import ru.st.selenium.model.Administration.TypesOfTables.TypesOfTables;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Administration.DirectoriesEditFormPage;
import ru.st.selenium.pages.pagesweb.Administration.TaskTypeListObjectPage;
import ru.st.selenium.pages.pagesweb.Administration.TypesOfTablesEditPage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.tests.data.system.ModuleAdministrationObjectTestCase;
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
@Features("Типы таблиц")
@Title("Проверка создания Типа таблиц в Web-интерфейсе")
public class TypesOfTableTest extends ModuleAdministrationObjectTestCase {


    @Severity(SeverityLevel.CRITICAL)
    @Title("Создание Типа таблицы с полным набором полей")
    @Description("Проверяем создание объекта Типа таблицы со всеми типами полей")
    @Test(priority = 1, dataProvider = "objectDataTypesOfTable")
    public void createTypesOfTable(Directories directories, TypesOfTables typesOfTables) throws Exception {

        // Авторизация
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //------------------------------------------------ Администрирование/Справочники
        // Переход в раздел Администрирование/Справочники
        TaskTypeListObjectPage directoriesPage = internalPage.goToDirectories();
        // Добавляем объект - Справочник
        directoriesPage.addDirectories(directories);
        // переходим в форму редактирования Справочника
        DirectoriesEditFormPage directoriesEditPage = directoriesPage.goToDirectoriesEditPage();
        // Добавляем настройки И поля спр-ка
        directoriesEditPage.addFieldDirectories(directories);

        //------------------------------------------------- Администрирование/Типы таблиц
        TaskTypeListObjectPage typesOfTablesPage = internalPage.goToTypesOfTables();
        typesOfTablesPage.addTypesOfTables(typesOfTables);
        // переходим в форму редактирования - Типы таблицы
        TypesOfTablesEditPage typesOfTablesEditPage = typesOfTablesPage.goToTypesOfTablesEditPage();
        // Добавляем настройки и поля Типы таблицы
        typesOfTablesEditPage.addFieldTypesOfTables(typesOfTables);

        // Разлогиниться
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());

        /**
         * Проверяем удаление объекта - Типы таблиц
         */
        loginPage.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //------------------------------------------------- Администрирование/Типы таблиц
        internalPage.goToTypesOfTables();
        typesOfTablesPage.removeTypesOfTables(typesOfTables);

        //------------------------------------------------- Администрирование/Справочники
        internalPage.goToDirectories();
        // Удаляем объект - Справочник
        directoriesPage.removeAnDirectories(directories);

        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());


    }


}
