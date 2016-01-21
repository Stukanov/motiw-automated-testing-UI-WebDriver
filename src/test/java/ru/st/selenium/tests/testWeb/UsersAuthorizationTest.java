package ru.st.selenium.tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.tests.data.TestBase;
import ru.st.selenium.tests.data.TestRetryAnalyzer;
import ru.st.selenium.tests.listeners.RetryListener;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import ru.st.selenium.tests.listeners.alluretestng.retrylistener.RetryListenerAllure;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class, RetryListenerAllure.class, RetryListener.class})
/**
 * Раздел - Стр. авторизации
 */
public class UsersAuthorizationTest extends TestBase {

    // Проверка - Авторизация не прошла - fail password
    @Test(priority = 1, dataProvider = "verifyFailAuthorizationWeb", retryAnalyzer = TestRetryAnalyzer.class)
    public void notSuccessfulAuthorization(Employee user) throws Exception {
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(user);
        assertTrue("Log in to the system fails", loginPage.isNotLoggedIn());
    }


    // Метод - проверка авторизации, валидность логина/пароля и выход из системы
    @Test(priority = 2, retryAnalyzer = TestRetryAnalyzer.class)
    public void loginSuccess() throws Exception {
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        assertTrue(loginPage.isLoggedInAs(ADMIN)); // Проверяем, что залогинен именно тот пользователь, к-й входил в систему
        // Выход из системы
        loginPage.initializedInsidePage().logout();
        assertTrue(loginPage.isNotLoggedIn());
    }

}