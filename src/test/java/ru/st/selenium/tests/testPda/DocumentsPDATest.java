package ru.st.selenium.tests.testPda;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.st.selenium.model.Administration.Directories.Directories;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.*;
import ru.st.selenium.model.Document.Document;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pagespda.DocumentsPagePDA;
import ru.st.selenium.pages.pagespda.InternalPagePDA;
import ru.st.selenium.pages.pagespda.LoginPagePDA;
import ru.st.selenium.pages.pagesweb.Administration.CreateDepartmentPage;
import ru.st.selenium.pages.pagesweb.Administration.CreateUsersPage;
import ru.st.selenium.pages.pagesweb.Administration.DirectoriesEditFormPage;
import ru.st.selenium.pages.pagesweb.Administration.TaskTypeListObjectPage;
import ru.st.selenium.pages.pagesweb.DocflowAdministration.DictionaryEditorPage;
import ru.st.selenium.pages.pagesweb.DocflowAdministration.FormDocRegisterCardsEditPage;
import ru.st.selenium.pages.pagesweb.DocflowAdministration.GridDocRegisterCardsPage;
import ru.st.selenium.pages.pagesweb.Documents.NewDocumentPage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.tests.data.system.ModuleDocflowAdministrationObjectCaseTest;

import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
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
@Features("Документы (PDA)")
@Title("Проверка раздела Документы в PDA-интерфейсе")
public class DocumentsPDATest extends ModuleDocflowAdministrationObjectCaseTest {

    @BeforeClass
    public static LoginPagePDA openUrlStartBrowser() {
        open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        return page(LoginPagePDA.class);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Title("Проверяем  отображение документа в гриде документа")
    @Description("Проверяем создание РКД с набором атрибутов, а также отображение его в отчете контролирования")
    @Test(priority = 1, dataProvider = "objectDataDRC")
    public void checkWorkDocumentsPDA(Department[] departments, Employee[] employees, Directories directories, TasksTypes tasksTypes, DictionaryEditor dictionaryEditor,
                                      DocRegisterCards registerCards, Document document) throws Exception {

        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);

        loginPage.loginAs(ADMIN);

        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------------------------Пользователи и Подразделения
        // Инициализируем страницу и переходим на нее - Администрирование/Пользователи (Подразделения)
        CreateDepartmentPage createDepartmentPage = internalPage.goToDepartments();

        createDepartmentPage.beforeAdd();
        createDepartmentPage.createDepartment(departments[0]);
        createDepartmentPage.createDepartment(departments[1]);

        // Инициализируем страницу - Администрирование/Пользователи (пользователи)
        CreateUsersPage createUsersPage = internalPage.initializationUsersPage();

        createUsersPage.beforeAdd();

        createUsersPage.createUser(employees[0].setDepartment(departments[0]));
        createUsersPage.createUser(employees[1].setDepartment(departments[1]));
        createUsersPage.createUser(employees[2].setDepartment(departments[1]));
        createUsersPage.createUser(employees[3].setDepartment(departments[0]));

        //-----------------------------------------------------------------------------------Редактор словарей
        // Переход в раздел - Администрирование ДО/Редактор словарей
        DictionaryEditorPage dictionaryEditorPage = internalPage.goToDictionaryEditor();
        dictionaryEditorPage.addDictionaryEditor(dictionaryEditor);

        //----------------------------------------------------------------------Справочники
        // Переход в раздел Администрирование/Справочники
        TaskTypeListObjectPage directoriesPage = internalPage.goToDirectories();
        // добавляем объект - Справочник
        directoriesPage.addDirectories(directories);
        // переходим в форму редактирования Справочника
        DirectoriesEditFormPage directoriesEditPage = directoriesPage.goToDirectoriesEditPage();
        // Добавляем настройки И поля спр-ка
        directoriesEditPage.addSettingsAndFieldDirectories(directories);

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

        //-------------------------------------------------------------------------------Создать документ
        NewDocumentPage newDocumentPage = internalPage.goToNewDocument();
        newDocumentPage.createDocument(document);


        internalPage.logout(); // Выход из системы
        assertTrue(loginPage.isNotLoggedIn());

        /**
         * Проверяем отображение документов в гриде документов (отчет Контролирования)
         */
        LoginPagePDA loginPagePDA = openUrlStartBrowser();

        // Авторизация
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Task; Today; Document)",
                internalPagePDA.hasMenuUserComplete());

        DocumentsPagePDA documentsPagePDA = internalPagePDA.goToDocuments();
        // Проверяем отображение отчетов "контролирования"
        documentsPagePDA.checkMapGridsDocuments();
        // Проверяем отображение созданного документа в гриде
        documentsPagePDA.checkTheDisplayOfTheDocumentGrid(registerCards);

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());


    }



}
