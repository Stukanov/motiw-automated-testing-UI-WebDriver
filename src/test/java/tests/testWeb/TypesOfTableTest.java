package tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.web.elements.elementsweb.Administration.TaskTypeListObjectPage;
import ru.motiw.web.elements.elementsweb.Internal.InternalPage;
import ru.motiw.web.elements.elementsweb.Login.LoginPage;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.TypesOfTables.TypesOfTables;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementsweb.Administration.DirectoriesEditFormPage;
import ru.motiw.web.elements.elementsweb.Administration.TypesOfTablesEditPage;
import tests.data.system.ModuleAdministrationObjectCaseTest;
import tests.listeners.ScreenShotOnFailListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Типы таблиц (Web)")
@Title("Проверка создания Типа таблиц в Web-интерфейсе")
public class TypesOfTableTest extends ModuleAdministrationObjectCaseTest {


    @Severity(SeverityLevel.CRITICAL)
    @Title("Создание Типа таблицы с полным набором полей")
    @Description("Проверяем создание объекта Типа таблицы со всеми типами полей")
    @Test(priority = 1, dataProvider = "objectDataTypesOfTable")
    public void createTypesOfTable(Directories directories, TypesOfTables typesOfTables) throws Exception {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; TasksElements; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //------------------------------------------------ Администрирование/Справочники
        // Переход в раздел Администрирование/Справочники
        TaskTypeListObjectPage directoriesPage = internalPage.goToDirectories();
        // Добавляем объект - Справочник
        directoriesPage.addDirectories(directories);
        // переходим в форму редактирования Справочника
        DirectoriesEditFormPage directoriesEditPage = directoriesPage.goToDirectoriesEditPage();
        // Добавляем настройки И поля спр-ка
        directoriesEditPage.addSettingsAndFieldDirectories(directories);

        //------------------------------------------------- Администрирование/Типы таблиц
        TaskTypeListObjectPage typesOfTablesPage = internalPage.goToTypesOfTables();
        typesOfTablesPage.addTypesOfTables(typesOfTables);
        // переходим в форму редактирования - Типы таблицы
        TypesOfTablesEditPage typesOfTablesEditPage = typesOfTablesPage.goToTypesOfTablesEditPage();
        // Добавляем настройки и поля Типы таблицы
        typesOfTablesEditPage.addSettingsAndFieldTypesOfTables(typesOfTables);

        /*
         * Проверяем удаление объекта - Типы таблиц
         */

        //------------------------------------------------- Удаляем - Типы таблиц
        typesOfTablesPage.removeTypesOfTables(typesOfTables);

        //------------------------------------------------- Удаляем - Справочники
        internalPage.goToDirectories();
        // Удаляем объект - Справочник
        directoriesPage.removeAnDirectories(directories);

        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());


    }



}
