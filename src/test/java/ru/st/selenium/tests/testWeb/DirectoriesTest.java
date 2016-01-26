package ru.st.selenium.tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.model.Administration.Directories.Directories;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Administration.DirectoriesEditFormPage;
import ru.st.selenium.pages.pagesweb.Administration.TaskTypeListObjectPage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.tests.data.Retry;
import ru.st.selenium.tests.data.system.ModuleAdministrationObjectTestCase;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import ru.st.selenium.tests.listeners.TestListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;


import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class, TestListener.class})
@Features("Справочник")
@Title("Проверка создания Справочник в Web-интерфейсе")
public class DirectoriesTest extends ModuleAdministrationObjectTestCase {


    @Severity(SeverityLevel.CRITICAL)
    @Title("Создание Справочника с полным набором полей")
    @Description("Проверяем создание объекта Справочник со всеми типами полей")
    @Test(priority = 1, dataProvider = "objectDataDirectories", retryAnalyzer = Retry.class)
    public void createDirectories(Directories directories) throws Exception {
        // Авторизация
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        // Переход в раздел Администрирование/Справочники
        TaskTypeListObjectPage directoriesPage = internalPage.goToDirectories();

        // Добавляем объект - Справочник
        directoriesPage.addDirectories(directories);

        // переходим в форму редактирования Справочника
        DirectoriesEditFormPage directoriesEditPage = directoriesPage.goToDirectoriesEditPage();

        // Добавляем настройки И поля спр-ка
        directoriesEditPage.addFieldDirectories(directories);

        // Выход из системы
        internalPage.logout();

        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());

        /**
         * Проверяем удаление объекта - Справочник
         */
        loginPage.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        // Переход в раздел Администрирование/Справочники
        internalPage.goToDirectories();

        // Удаляем объект - Справочник
        directoriesPage.removeAnDirectories(directories);

        internalPage.logout();

        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());
    }

}
