package tests.testWeb;

import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.elements.elementspagesweb.Login.LoginPage;
import ru.motiw.web.steps.Administration.SearchSystemPageSteps;
import tests.data.BaseTest;
import tests.data.system.ModuleTaskCaseTest;
import tests.listeners.ScreenShotOnFailListener;
import ru.motiw.web.elements.elementspagesweb.Administration.SystemInformationPage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Features("Информация о системе И Поисковая система (Web)")
@Title("Проверяем разделы Информация о системе И Поисковая система на наличие ошибок")
@Listeners({ScreenShotOnFailListener.class, TextReport.class, VideoListener.class })
public class SystemInformationTest extends ModuleTaskCaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Title("Проверка отсутствия незапущенных служб")
    @Description("Проверка на отсутствия незапущенных служб (красные элементы на странице)")
    @Video(name = "Индикация работоспособности служб")
    @Test(priority = 1)
    public void verifyNotRedSystemInfo() throws Exception {
        LoginPage loginPage = Selenide.open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(BaseTest.ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Переход в раздел Администрирование/Информация о системе
        SystemInformationPage systemInfoPage = internalPage.goToSystemInfo();
        assertTrue(systemInfoPage.checkingOfSystemServices()); // Проверка, что все службы запущены (нет красных)

        // Выход из системы
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());

    }

    @Severity(SeverityLevel.CRITICAL)
    @Title("Проверяем наличие ошибок в индексах Поисковой системы")
    @Description("Проверяем отсутствие ошибок в Поисковой системе над конкретным объектом")
    @Video(name = "Ошибки в индексах - Поискова система")
    @Test(priority = 2)
    public void verifyNotIndexingErrors() throws Exception {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(BaseTest.ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Переход в раздел Администрирование/Поисковая система
        SearchSystemPageSteps searchSystemPageSteps = internalPage.goToSearchSystemPage();
        assertTrue("The absence of errors in the search indexes", searchSystemPageSteps.checkNotIndexingErrors()); // Проверяем отсутствие ошибок в индексах

        // Выход из системы
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());
    }


}