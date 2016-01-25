package ru.st.selenium.tests.testPda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.TextReport;
import ru.st.selenium.pages.pagespda.InternalPagePDA;
import ru.st.selenium.pages.pagespda.LoginPagePDA;
import ru.st.selenium.tests.data.Retry;
import ru.st.selenium.tests.data.system.ModuleTaskTestCase;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.pages.Page;
import ru.st.selenium.tests.listeners.TestListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;
import static com.codeborne.selenide.Selenide.open;


@Listeners({ScreenShotOnFailListener.class, TextReport.class, TestListener.class})
@Features("Авторизация")
@Title("Авторизация в систему PDA")
@Description("Проверка авторизации корневого пользователя системы с массивом данных")
/**
 * Раздел - Стр. авторизации
 */
public class UsersAuthorizationPDATest extends ModuleTaskTestCase {

    @Severity(SeverityLevel.BLOCKER)
    @Title("Валидная авторизация")
    @Description("Пользователь авторизируется в систему под валидными учетными данными")
    @Test(priority = 3, retryAnalyzer = Retry.class)
    public void verifyLoginSuccess() throws Exception {
        LoginPagePDA loginPagePDA = open(Page.PDA_PAGE_URL, LoginPagePDA.class);
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Проверяем отображение п.м. системы
        assertThat("Check that the displayed menu item 4 (Tasks; Create Task; Today; Document)",
                internalPagePDA.hasMenuUserComplete());
        internalPagePDA.logout(); // Выход из системы
    }


    @Severity(SeverityLevel.BLOCKER)
    @Title("Невалидная авторизация")
    @Description("Пользователь авторизируется в систему под невалидными учетными данными. Авторизация в систему" +
            "не проходит")
    @Test(priority = 1, dataProvider = "verifyFailAuthorization", retryAnalyzer = Retry.class)
    public void verifyFailAuthorization(String login, String pass) throws Exception {
        LoginPagePDA loginPagePDA = open(Page.PDA_PAGE_URL, LoginPagePDA.class);
        loginPagePDA.loginAs(login, pass);
        assertTrue(loginPagePDA.isNotLoggedInPDA());
        $(By.cssSelector("#error")).shouldBe(Condition.exactText("Доступ запрещен"));

    }

    @Severity(SeverityLevel.BLOCKER)
    @Title("Невалидная авторизация")
    @Description("Пользователь авторизируется в систему под невалидными учетными данными. Авторизация в систему" +
            "не проходит")
    @Test(priority = 2, dataProvider = "secondVerifyFailAuthorization", retryAnalyzer = Retry.class)
    public void secondVerifyFailAuthorization(String login, String pass) throws Exception {
        LoginPagePDA loginPagePDA = open(Page.PDA_PAGE_URL, LoginPagePDA.class);
        loginPagePDA.loginAs(login, pass);
        assertTrue(loginPagePDA.isNotLoggedInPDA());
    }


}