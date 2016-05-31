package tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import tests.data.system.ModuleDocflowAdministrationObjectCaseTest;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.DictionaryEditorPage;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.elements.elementspagesweb.Login.LoginPage;
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
@Features("Редактор словарей (Web)")
@Title("Проверка создания Редактор словарей в Web-интерфейсе")
public class DictionaryEditorTest extends ModuleDocflowAdministrationObjectCaseTest {

    // Инициализируем объект - Редактор словарей
    DictionaryEditor dictionaryEditor = getRandomDictionaryEditor();

    @Severity(SeverityLevel.CRITICAL)
    @Title("Ссоздание объекта Редактор словарей")
    @Description("Создание объекта Редактор словарей с набором элементов")
    @Test(priority = 1)
    public void createDictionaryEditor() throws Exception {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        //---------------------------------------------------------------------------------Словарь

        // Переход в раздел Администрирование ДО/Редактор словарей
        DictionaryEditorPage dictionaryEditorPage = internalPage.goToDictionaryEditor();
        // Добавляем элементы словаря
        dictionaryEditorPage.addDictionaryEditor(dictionaryEditor);

        // Выход
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());
    }


}