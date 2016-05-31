package tests.testWeb;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagesweb.Administration.DirectoriesEditFormPage;
import ru.motiw.web.elements.elementspagesweb.Administration.TaskTypeListObjectPage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.FormDocRegisterCardsEditPage;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.elements.elementspagesweb.Login.LoginPage;
import tests.data.system.ModuleDocflowAdministrationObjectCaseTest;
import tests.listeners.ScreenShotOnFailListener;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.DictionaryEditorPage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.GridDocRegisterCardsPage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("РКД (Регистрационная карточка документа) (Web)")
@Title("Проверка создания Регистрационная карточка документа в Web-интерфейсе")
public class DocumentRegistrationCardsTest extends ModuleDocflowAdministrationObjectCaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Title("Создание РКД с полным набором полей и надстройками")
    @Description("Создание объекта Регистрационная карточка документа со всеми типами полей")
    @Test(priority = 1, dataProvider = "objectDataDRC")
    public void verifyCreateRegCardDocumentAllFields(Department[] departments, Employee[] employees, Directories directories, TasksTypes tasksTypes, DictionaryEditor dictionaryEditor,
                                                     DocRegisterCards registerCards, Document document) throws Exception {
        LoginPage loginPage = Selenide.open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице


        //----------------------------------------------------------------------Справочники
        // Переход в раздел Администрирование/Справочники
        TaskTypeListObjectPage directoriesPage = internalPage.goToDirectories();

        // добавляем объект - Справочник
        directoriesPage.addDirectories(directories);

        // переходим в форму редактирования Справочника
        DirectoriesEditFormPage directoriesEditPage = directoriesPage.goToDirectoriesEditPage();

        // Добавляем настройки И поля спр-ка
        directoriesEditPage.addSettingsAndFieldDirectories(directories);

        //-----------------------------------------------------------------------------------Редактор словарей
        // Переход в раздел - Администрирование ДО/Редактор словарей
        DictionaryEditorPage dictionaryEditorPage = internalPage.goToDictionaryEditor();
        dictionaryEditorPage.addDictionaryEditor(dictionaryEditor);

        //-----------------------------------------------------------------------------------РКД
        // Переход в раздел Администрирование ДО/Регистрационные карточки документов
        GridDocRegisterCardsPage gridDocRegisterCardsPage = internalPage.goToGridDocRegisterCards();
        // Добавить РКД
        gridDocRegisterCardsPage.addDocRegisterCards();
        // Добавление РКД с проинициализированными объектами
        FormDocRegisterCardsEditPage formDocRegisterCardsEditPage = gridDocRegisterCardsPage.goToDocRegisterCardsEditPage();
        // Добавление полей РКД
        formDocRegisterCardsEditPage.addFieldsDocRegisterCards(registerCards);
        // Добавление настроек РКД
        formDocRegisterCardsEditPage.addSettingsDocRegisterCards(registerCards);
        // Сохранение настроек РКД
        formDocRegisterCardsEditPage.saveAllChangesInDoc(registerCards);

        internalPage.logout(); // Выход из системы
        assertTrue(loginPage.isNotLoggedIn());
    }


}