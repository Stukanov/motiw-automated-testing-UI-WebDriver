package tests.testWeb;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementsweb.Administration.DirectoriesEditFormPage;
import ru.motiw.web.elements.elementsweb.Administration.TaskTypeListObjectPage;
import ru.motiw.web.elements.elementsweb.Internal.InternalPage;
import ru.motiw.web.elements.elementsweb.Login.LoginPage;
import tests.data.system.ModuleAdministrationObjectCaseTest;
import tests.listeners.CustomSoftAsserts.CustomSoftAsserts;
import tests.listeners.CustomTextReport.CustomTextReport;
import tests.listeners.ScreenShotOnFailListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;


@Listeners({ScreenShotOnFailListener.class, CustomTextReport.class, CustomSoftAsserts.class})
@Features("Справочник (Web)")
@Title("Проверка создания Справочник в Web-интерфейсе")
public class DirectoriesTest extends ModuleAdministrationObjectCaseTest {



    @Severity(SeverityLevel.CRITICAL)
    @Title("Создание Справочника с полным набором полей")
    @Description("Проверяем создание объекта Справочник со всеми типами полей")
    @Test(priority = 1, dataProvider = "objectDataDirectories")
    public void createDirectories(Directories directories) throws Exception {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        // Авторизация
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; TasksElements; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        // Переход в раздел Администрирование/Справочники
        TaskTypeListObjectPage directoriesPage = internalPage.goToDirectories();
        // Добавляем объект - Справочник
        directoriesPage.addDirectories(directories);
        // переходим в форму редактирования Справочника
        DirectoriesEditFormPage directoriesEditPage = directoriesPage.goToDirectoriesEditPage();
        // Добавляем настройки И поля спр-ка
        directoriesEditPage.addSettingsAndFieldDirectories(directories);

        //-------------------------------------------------------Удаляем / Справочники
        directoriesPage.removeAnDirectories(directories);

        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());
    }


}
