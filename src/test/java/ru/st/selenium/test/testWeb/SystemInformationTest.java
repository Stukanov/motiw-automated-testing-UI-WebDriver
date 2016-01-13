package ru.st.selenium.test.testWeb;


import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Administration.SearchAdminPage;
import ru.st.selenium.pages.pagesweb.Administration.SystemInformationPage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.test.data.TestRetryAnalyzer;
import ru.st.selenium.test.data.system.ModuleTaskTestCase;
import ru.st.selenium.test.listeners.RetryListener;
import ru.st.selenium.test.listeners.ScreenShotOnFailListener;
import ru.st.selenium.test.listeners.alluretestng.retrylistener.RetryListenerAllure;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Информация о системе И Поисковая система
 */
@Listeners({ScreenShotOnFailListener.class, TextReport.class, RetryListenerAllure.class, RetryListener.class})
public class SystemInformationTest extends ModuleTaskTestCase {


    /*
      * Проверка отсутствия незапущенных служб (красные элементы на странице)
      */
    @Test(priority = 1, retryAnalyzer = TestRetryAnalyzer.class)
    public void verifyNotRedSystemInfo() throws Exception {
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
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

    /*
      * Проверяем отсутствие ошибок в поисковой системе над конкретным объектом
      */
    @Test(priority = 2, retryAnalyzer = TestRetryAnalyzer.class)
    public void verifyNotIndexingErrors() throws Exception {
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
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