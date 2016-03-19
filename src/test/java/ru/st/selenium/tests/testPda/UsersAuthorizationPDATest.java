package ru.st.selenium.tests.testPda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pagespda.InternalPagePDA;
import ru.st.selenium.pages.pagespda.LoginPagePDA;
import ru.st.selenium.tests.data.system.ModuleTaskCaseTest;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Авторизация (PDA)")
@Title("Авторизация в систему PDA")
@Description("Проверка авторизации корневого пользователя системы с массивом данных")
public class UsersAuthorizationPDATest extends ModuleTaskCaseTest {

    @BeforeClass
    public static LoginPagePDA openUrlStartBrowser() {
        open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        return page(LoginPagePDA.class);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Title("Валидная авторизация")
    @Description("Пользователь авторизируется в систему под валидными учетными данными")
    @Test(priority = 3)
    public void verifyLoginSuccess() throws Exception {
        LoginPagePDA loginPagePDA = openUrlStartBrowser();
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Проверяем отображение п.м. системы
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
                internalPagePDA.hasMenuUserComplete());
        internalPagePDA.logout(); // Выход из системы
    }


    @Severity(SeverityLevel.BLOCKER)
    @Title("Невалидная авторизация")
    @Description("Пользователь авторизируется в систему под невалидными учетными данными. Авторизация в систему" +
            "не проходит")
    @Test(priority = 1, dataProvider = "verifyFailAuthorization")
    public void verifyFailAuthorization(String login, String pass) throws Exception {
        LoginPagePDA loginPagePDA = openUrlStartBrowser();
        loginPagePDA.loginAs(login, pass);
        assertTrue(loginPagePDA.isNotLoggedInPDA());
        $(By.cssSelector("#error")).shouldBe(Condition.exactText("Доступ запрещен"));

    }

    @Severity(SeverityLevel.BLOCKER)
    @Title("Невалидная авторизация")
    @Description("Пользователь авторизируется в систему под невалидными учетными данными. Авторизация в систему" +
            "не проходит")
    @Test(priority = 2, dataProvider = "secondVerifyFailAuthorization")
    public void secondVerifyFailAuthorization(String login, String pass) throws Exception {
        LoginPagePDA loginPagePDA = openUrlStartBrowser();
        loginPagePDA.loginAs(login, pass);
        assertTrue(loginPagePDA.isNotLoggedInPDA());
    }


}