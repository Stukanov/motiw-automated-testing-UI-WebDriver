package ru.st.selenium.tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.tests.data.TestBase;
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

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Авторизация (Web)")
@Title("Авторизация в систему Web-интерфейс")
public class UsersAuthorizationTest extends TestBase {

    @BeforeClass
    public static LoginPage beforeTest() {
        open(Page.WEB_PAGE_URL, LoginPage.class);
        return page(LoginPage.class);
    }


    @Severity(SeverityLevel.BLOCKER)
    @Title("Невалидная авторизация")
    @Description("Пользователь авторизируется в систему под невалидными учетными данными. Авторизация в систему" +
            "не проходит")
    @Test(priority = 1, dataProvider = "verifyFailAuthorizationWeb")
    public static void notSuccessfulAuthorization(Employee user) throws Exception {
        LoginPage loginPage = beforeTest();
        loginPage.loginAs(user);
        assertTrue("Log in to the system fails", loginPage.isNotLoggedIn());
    }

    @Severity(SeverityLevel.BLOCKER)
    @Title("Валидная авторизация")
    @Description("Пользователь авторизируется в систему под валидными учетными данными")
    @Test(priority = 2)
    public void loginSuccess() throws Exception {
        LoginPage loginPage = beforeTest();
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());
        assertTrue(loginPage.isLoggedInAs(ADMIN)); // Проверяем, что залогинен именно тот пользователь, к-й входил в систему
        // Выход из системы
        loginPage.initializedInsidePage().logout();
        assertTrue(loginPage.isNotLoggedIn());
    }

    @AfterClass
    public static void afterTest() {
        close();
    }

}