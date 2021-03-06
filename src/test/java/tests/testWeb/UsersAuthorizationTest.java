package tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.elements.elementspagesweb.Login.LoginPage;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.elements.BasePage;
import tests.data.BaseTest;
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
@Features("Авторизация (Web)")
@Title("Авторизация в систему Web-интерфейс")
public class UsersAuthorizationTest extends BaseTest {

    @Severity(SeverityLevel.BLOCKER)
    @Title("Невалидная авторизация")
    @Description("Пользователь авторизируется в систему под невалидными учетными данными. Авторизация в систему" +
            "не проходит")
    @Test(priority = 1, dataProvider = "verifyFailAuthorizationWeb")
    public void notSuccessfulAuthorization(Employee user) throws Exception {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(user);
        assertTrue("Log in to the system fails", loginPage.isNotLoggedIn());
    }

    @Severity(SeverityLevel.BLOCKER)
    @Title("Валидная авторизация")
    @Description("Пользователь авторизируется в систему под валидными учетными данными")
    @Test(priority = 2)
    public void successfulAuthorization() throws Exception {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());
        // Выход из системы
        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());
    }



}