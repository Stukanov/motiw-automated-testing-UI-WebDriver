package tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.elements.elementspagesweb.Administration.Users.DepartmentElements;
import ru.motiw.web.elements.elementspagesweb.Administration.TaskTypeListObjectPage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.FormDocRegisterCardsEditPage;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.elements.elementspagesweb.Login.LoginPage;
import ru.motiw.web.steps.Administration.Users.UsersElementsSteps;
import tests.data.system.ModuleDocflowAdministrationObjectCaseTest;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagesweb.Administration.DirectoriesEditFormPage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.DictionaryEditorPage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.GridDocRegisterCardsPage;
import ru.motiw.web.elements.elementspagesweb.Documents.NewDocumentPage;
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
@Features("Создать документ (Web)")
@Title("Проверка создания документа в Web-интерфейсе")
public class CreateDocumentTest extends ModuleDocflowAdministrationObjectCaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Title("Проверяем создание документа")
    @Description("Проверяем создание документа с набором заполняемых полей")
    @Test(priority = 1, dataProvider = "objectDataDRC")
    public void CreateDocument(Department[] departments, Employee[] employees, Directories directories, TasksTypes tasksTypes, DictionaryEditor dictionaryEditor,
                               DocRegisterCards registerCards, Document document) throws Exception {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

        //---------------------------------------------------------------------------------Пользователи и Подразделения
        // Инициализируем страницу и переходим на нее - Администрирование/Пользователи (Подразделения)
        DepartmentElements departmentElements = internalPage.goToDepartments();

        departmentElements.beforeAdd();
        departmentElements.createDepartment(departments[0]);
        departmentElements.createDepartment(departments[1]);

        // Инициализируем страницу - Администрирование/Пользователи (пользователи)
        UsersElementsSteps usersPage = internalPage.initializationUsersPage();

        usersPage.beforeAdd();

        usersPage.createUser(employees[0].setDepartment(departments[0]));
        usersPage.createUser(employees[1].setDepartment(departments[1]));
        usersPage.createUser(employees[2].setDepartment(departments[1]));
        usersPage.createUser(employees[3].setDepartment(departments[0]));

        //---------------------------------------------------------------------------------Словарь

        // Переход в раздел Администрирование ДО/Редактор словарей
        DictionaryEditorPage dictionaryEditorPage = internalPage.goToDictionaryEditor();
        // Добавляем элементы словаря
        dictionaryEditorPage.addDictionaryEditor(dictionaryEditor);

        //---------------------------------------------------------------------------------Справочник
        // Переход в раздел Администрирование/Справочники
        TaskTypeListObjectPage directoriesPage = internalPage.goToDirectories();
        // Добавляем объект - Справочник
        directoriesPage.addDirectories(directories);
        // переходим в форму редактирования Справочника
        DirectoriesEditFormPage directoriesEditPage = directoriesPage.goToDirectoriesEditPage();
        // Добавляем настройки И поля спр-ка
        directoriesEditPage.addSettingsAndFieldDirectories(directories);

        //---------------------------------------------------------------------------------РКД
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

        // Выход из системы
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPage.isNotLoggedIn());

    }



}
