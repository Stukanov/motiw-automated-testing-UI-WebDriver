package ru.st.selenium.tests.testWeb;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.model.Administration.Directories.Directories;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.st.selenium.model.Document.Document;
import ru.st.selenium.pages.Page;
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
import ru.st.selenium.tests.data.system.ModuleDocflowAdministrationObjectTestCase;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;


import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Создать документ (Web)")
@Title("Проверка создания документа в Web-интерфейсе")
public class CreateDocumentTest extends ModuleDocflowAdministrationObjectTestCase {

    @Severity(SeverityLevel.CRITICAL)
    @Title("Проверяем создание документа")
    @Description("Проверяем создание документа с набором заполняемых полей")
    @Test(priority = 1, dataProvider = "objectDataDRC")
    public void CreateDocument(Department[] departments, Employee[] employees, Directories directories, TasksTypes tasksTypes, DictionaryEditor dictionaryEditor,
                               DocRegisterCards registerCards, Document document) throws Exception {
        LoginPage loginPage = open(Page.WEB_PAGE_URL, LoginPage.class);
        loginPage.loginAs(ADMIN);
        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPage.isLoggedIn());

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
