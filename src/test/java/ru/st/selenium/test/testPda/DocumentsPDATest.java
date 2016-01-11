package ru.st.selenium.test.testPda;

import com.codeborne.selenide.testng.TextReport;
import ru.st.selenium.model.Administration.Directories.Directories;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.*;
import ru.st.selenium.model.Document.Document;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagespda.DocumentsPagePDA;
import ru.st.selenium.pages.pagespda.InternalPagePDA;
import ru.st.selenium.pages.pagespda.LoginPagePDA;
import ru.st.selenium.pages.pagesweb.Administration.DirectoriesEditFormPage;
import ru.st.selenium.pages.pagesweb.Administration.TaskTypeListObjectPage;
import ru.st.selenium.pages.pagesweb.DocflowAdministration.DictionaryEditorPage;
import ru.st.selenium.pages.pagesweb.DocflowAdministration.FormDocRegisterCardsEditPage;
import ru.st.selenium.pages.pagesweb.DocflowAdministration.GridDocRegisterCardsPage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.test.data.TestRetryAnalyzer;
import ru.st.selenium.test.data.system.ModuleDocflowAdministrationObjectTestCase;

import ru.st.selenium.test.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

/**
 * Раздел - Документы
 */
@Listeners({ScreenShotOnFailListener.class, TextReport.class})
public class DocumentsPDATest extends ModuleDocflowAdministrationObjectTestCase {

    /**
     * Создаем РКД для проверки работы объекта Документ
     */
    @Test(priority = 1, dataProvider = "objectDataDRC", retryAnalyzer = TestRetryAnalyzer.class)
    public void verifyCreateRegCardDocumentAllFields(Department[] departments, Employee[] employees, Directories directories, TasksTypes tasksTypes, DictionaryEditor dictionaryEditor,
                                                     DocRegisterCards registerCards, Document document) throws Exception {

        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);

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
        directoriesEditPage.addFieldDirectories(directories);

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

    /**
     * проверка - Отображение грида документа
     */
    @Test(priority = 2, retryAnalyzer = TestRetryAnalyzer.class)
    public void checkMapGridOfDocuments() throws Exception {
        LoginPagePDA loginPagePDA = open(Page.PDA_PAGE_URL, LoginPagePDA.class);

        // Авторизация
        loginPagePDA.loginAsAdmin(ADMIN);

        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Task; Today; Document)",
                internalPagePDA.hasMenuUserComplete());

        DocumentsPagePDA documentsPagePDA = internalPagePDA.goToDocuments();

        documentsPagePDA.checkMapGridsDocuments(); //TODO 1 - Реализовать - проверка созданного документа в гриде PDA; -проверка отображение заполненных зн-ий в ПДА

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());


    }

}
