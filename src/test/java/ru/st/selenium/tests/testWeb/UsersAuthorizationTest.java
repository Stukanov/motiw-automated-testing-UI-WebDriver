package ru.st.selenium.tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.tests.data.TestBase;
import ru.st.selenium.tests.data.Retry;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import ru.st.selenium.tests.listeners.TestListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class, TestListener.class})
@Features("Авторизация")
@Title("Авторизация в систему PDA")
@Description("Проверка авторизации корневого пользователя системы с массивом данных")
public class UsersAuthorizationTest extends TestBase {

    @Severity(SeverityLevel.BLOCKER)
    @Title("Невалидная авторизация")
    @Description("Пользователь авторизируется в систему под невалидными учетными данными. Авторизация в систему" +
            "не проходит")
    @Test(priority = 1, dataProvider = "verifyFailAuthorizationWeb", retryAnalyzer = Retry.class)
    public void notSuccessfulAuthorization(Employee user) throws Exception {
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(user);
        assertTrue("Log in to the system fails", loginPage.isNotLoggedIn());
    }

    @Severity(SeverityLevel.BLOCKER)
    @Title("Валидная авторизация")
    @Description("Пользователь авторизируется в систему под валидными учетными данными")
    @Test(priority = 2, retryAnalyzer = Retry.class)
    public void loginSuccess() throws Exception {
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        assertTrue(loginPage.isLoggedInAs(ADMIN)); // Проверяем, что залогинен именно тот пользователь, к-й входил в систему
        // Выход из системы
        loginPage.initializedInsidePage().logout();
        assertTrue(loginPage.isNotLoggedIn());
    }

}