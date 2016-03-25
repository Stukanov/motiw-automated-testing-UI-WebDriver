package ru.st.selenium.tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pagesweb.Administration.SearchAdminPage;
import ru.st.selenium.pages.pagesweb.Administration.SystemInformationPage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.tests.data.system.ModuleTaskCaseTest;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Features("Информация о системе И Поисковая система (Web)")
@Title("Проверяем разделы Информация о системе И Поисковая система на наличие ошибок")
@Listeners({ScreenShotOnFailListener.class, TextReport.class})
public class SystemInformationTest extends ModuleTaskCaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Title("Проверка отсутствия незапущенных служб")
    @Description("Проверка на отсутствия незапущенных служб (красные элементы на странице)")
    @Test(priority = 1)
    public void verifyNotRedSystemInfo() throws Exception {
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Переход в раздел Администрирование/Информация о системе
        SystemInformationPage systemInfoPage = internalPage.goToSystemInfo();
        systemInfoPage.checkingOfSystemServices(); // Проверка, что все службы запущены (нет красных)

        // Выход из системы
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());

    }

    @Severity(SeverityLevel.CRITICAL)
    @Title("Проверяем наличие ошибок в индексах Поисковой системы")
    @Description("Проверяем отсутствие ошибок в Поисковой системе над конкретным объектом")
    @Test(priority = 2)
    public void verifyNotIndexingErrors() throws Exception {
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        // Переход в раздел Администрирование/Поисковая система
        SearchAdminPage searchAdminPage = internalPage.goToSearchSystemPage();
        searchAdminPage.checkNotIndexingErrors(); // Проверяем отсутствие ошибок в индексах

        // Выход из системы
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());
    }



}