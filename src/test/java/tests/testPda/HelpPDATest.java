package tests.testPda;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.pagespda.HelpHtmlPagePDA;
import ru.motiw.web.elements.pagespda.InternalPagePDA;
import ru.motiw.web.elements.pagespda.LoginPagePDA;
import tests.data.system.ModuleTaskCaseTest;
import tests.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Помощь (PDA)")
public class HelpPDATest extends ModuleTaskCaseTest {


    @Severity(SeverityLevel.MINOR)
    @Title("Проверяем отображение элементов помощи")
    @Description("Проверяем наличие элементов и расшифровку Кнопок (элементов) на странице помощи")
    @Test(priority = 1)
    public void verifyElementsHelp() throws Exception {
        LoginPagePDA loginPagePDA = Selenide.open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (TasksElements; Create TasksElements; Today; Document)",
                internalPagePDA.hasMenuUserComplete());

        // Инициализируем стр. формы создание задачи и переходим на нее
        HelpHtmlPagePDA helpPage = internalPagePDA.goToHelpHtml();
        helpPage.checkPresenceElementsOfAid(); // Проверяем общее количество элементов помощи
        helpPage.visibleElementsTextHelp(); // Проверяем отображение текста в элементах помощи
        assertEquals(19, helpPage.results().size()); // проверяем кол-во элементов на стр-це ппомочи

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }

}
