package ru.st.selenium.tests.testPda;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.st.selenium.pages.pagespda.*;
import ru.st.selenium.tests.data.system.ModuleTaskCaseTest;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.pages.BasePage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Поиск (PDA)")
@Title("Проверка поиск объектов системы (SOLR)")
public class SearchPDATest extends ModuleTaskCaseTest {

    @BeforeClass
    public static LoginPagePDA openUrlStartBrowser() {
        open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        return page(LoginPagePDA.class);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Title("Поиск объектов (Контакты)")
    @Description("Проверяем расширенный поиск, проинициализированных объектов системы - Контакты")
    @Test(priority = 1)
    public void verifySearchContact() throws Exception {
        LoginPagePDA loginPagePDA = openUrlStartBrowser();
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
                internalPagePDA.hasMenuUserComplete());
        SearchPagePDA searchPagePDA = internalPagePDA.goToSearch(); // Переходим в раздел Поиска
        searchPagePDA.searchContact(EMPLOYEE_ADMIN); // проверяем поиск Контакта пользователя по Фамилии

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());
    }


}


