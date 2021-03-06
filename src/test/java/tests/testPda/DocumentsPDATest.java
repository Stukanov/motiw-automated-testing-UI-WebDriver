package tests.testPda;

import com.codeborne.selenide.testng.TextReport;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.elements.elementspagesweb.Administration.Users.DepartmentElements;
import ru.motiw.web.elements.elementspagesweb.Administration.TaskTypeListObjectPage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.FormDocRegisterCardsEditPage;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.elements.elementspagesweb.Login.LoginPage;
import ru.motiw.web.elements.elementspagespda.DocumentsPagePDA;
import ru.motiw.web.elements.elementspagespda.LoginPagePDA;
import ru.motiw.web.steps.Administration.Users.UsersPageSteps;
import tests.data.system.ModuleDocflowAdministrationObjectCaseTest;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagespda.InternalPagePDA;
import ru.motiw.web.elements.elementspagesweb.Administration.DirectoriesEditFormPage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.DictionaryEditorPage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.GridDocRegisterCardsPage;
import ru.motiw.web.elements.elementspagesweb.Documents.NewDocumentPage;

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
import static org.testng.Assert.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Документы (PDA)")
@Title("Проверка раздела Документы в PDA-интерфейсе")
public class DocumentsPDATest extends ModuleDocflowAdministrationObjectCaseTest {


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
        DepartmentElements departmentElements = internalPage.goToDepartments();

        departmentElements.beforeAdd();
        departmentElements.createDepartment(departments[0]);
        departmentElements.createDepartment(departments[1]);

        // Инициализируем страницу - Администрирование/Пользователи (пользователи)
        UsersPageSteps usersPage = internalPage.initializationUsersPage();

        usersPage.beforeAdd();

        usersPage.createUser(employees[0].setDepartment(departments[0]));
        usersPage.createUser(employees[1].setDepartment(departments[1]));
        usersPage.createUser(employees[2].setDepartment(departments[1]));
        usersPage.createUser(employees[3].setDepartment(departments[0]));

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
        LoginPagePDA loginPagePDA = open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        // Авторизация
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
                internalPagePDA.hasMenuUserComplete());

        DocumentsPagePDA documentsPagePDA = internalPagePDA.goToDocuments();
        // Проверяем отображение отчетов "контролирования"
        documentsPagePDA.checkMapGridsDocuments();
        // Проверяем отображение созданного документа в гриде
        documentsPagePDA.checkTheDisplayOfTheDocumentGrid(registerCards, document);

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());


    }



}
