package ru.st.selenium.test.testPda;


import com.codeborne.selenide.testng.TextReport;
import ru.st.selenium.pages.pagespda.HelpHtmlPagePDA;
import ru.st.selenium.pages.pagespda.InternalPagePDA;
import ru.st.selenium.pages.pagespda.LoginPagePDA;
import ru.st.selenium.test.data.TestRetryAnalyzer;
import ru.st.selenium.test.data.system.ModuleTaskTestCase;
import ru.st.selenium.test.listeners.RetryListener;
import ru.st.selenium.test.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.pages.Page;
import ru.st.selenium.test.listeners.alluretestng.retrylistener.RetryListenerAllure;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class, RetryListenerAllure.class, RetryListener.class})
@Features("Раздел - Помощь")
public class HelpPDATest extends ModuleTaskTestCase {

    @Severity(SeverityLevel.MINOR)
    @Description("Проверяем наличие элементов помощи на странице")
    @Test(priority = 1, retryAnalyzer = TestRetryAnalyzer.class)
    public void verifyElementsHelp() throws Exception {
        LoginPagePDA loginPagePDA = open(Page.PDA_PAGE_URL, LoginPagePDA.class);

        // Авторизация
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Task; Today; Document)",
                internalPagePDA.hasMenuUserComplete());

        // Инициализируем стр. формы создание задачи и переходим на нее
        HelpHtmlPagePDA helpPage = internalPagePDA.goToHelpHtml();
        helpPage.checkPresenceElementsOfAid(); // Проверяем общее количество элементов помощи
        helpPage.visibleElementsTextHelp(); // Проверяем отображение текста в элементах помощи
        assertEquals(19, helpPage.results().size()); // проверяем кол-во элементов на стр-це ппомочи
        makeScreenshot();

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }

}
