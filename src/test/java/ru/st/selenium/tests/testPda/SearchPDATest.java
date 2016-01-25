package ru.st.selenium.tests.testPda;

import com.codeborne.selenide.testng.TextReport;
import ru.st.selenium.pages.pagespda.*;
import ru.st.selenium.tests.data.Retry;
import ru.st.selenium.tests.data.system.ModuleTaskTestCase;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.pages.Page;
import ru.st.selenium.tests.listeners.TestListener;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class, TestListener.class})
@Features("Поиск")
@Title("Проверка поиск объектов системы (SOLR)")
public class SearchPDATest extends ModuleTaskTestCase {

    @Severity(SeverityLevel.CRITICAL)
    @Title("Поиск объектов (Расширенный поиск)")
    @Description("Проверяем расширенный поиск проинициализированных объектов системы - Контакты, Задачи, Документы и пр..")
    @Test(priority = 1, retryAnalyzer = Retry.class)
    public void verifySearch() throws Exception {
        LoginPagePDA loginPagePDA = open(Page.PDA_PAGE_URL, LoginPagePDA.class);

        // Авторизация
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Task; Today; Document)",
                internalPagePDA.hasMenuUserComplete());

        /*  TODO поиск задачи через SOLR
        NewTaskPagePDA newTaskPage = internalPagePDA.goToCreateTask();  // Инициализируем стр. формы создание задачи и переходим на нее

        //----------------------------------------------------------------ФОРМА - создания Задачи

        newTaskPage.creatingTask(task);
        EditTaskPagePDA editTaskPage = newTaskPage.goToPreview(); // Инициализируем стр. формы предпросмотра задачи и переходим на нее

        //----------------------------------------------------------------ФОРМА - Предпросмотр создания задачи

        editTaskPage.inputValidationFormTask(task); // Проверяем отображение значений в форме предпросмотра создания задачи

        //----------------------------------------------------------------ФОРМА - Задачи

        TaskPagePDA taskForm = editTaskPage.goToTask(); // Инициализируем стр. формы - Созданной задачи и переходим на нее
        taskForm.openShapeCreatedTask(task); // Открываем созданную задачу
        assertTrue(taskForm.resultsDisplayButtons()); // Проверяем отображения кнопок в форме задачи
        internalPagePDA.goToHome();
        TasksReportsPagePDA tasksReportsPage = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи
        tasksReportsPage.checkDisplayTaskGrid(task); // Проверяем отображение созданной задачи в гриде Задач

        internalPagePDA.goToHome();

        //----------------------------------------------------------------ГРИД - ПОИСК

        SearchPagePDA searchPagePDA = internalPagePDA.goToSearch(); // Переходим в раздел Поиска
        searchPagePDA.searchTask(task); // Производим поиск задачи по - Названию */

        SearchPagePDA searchPagePDA = internalPagePDA.goToSearch(); // Переходим в раздел Поиска
        searchPagePDA.searchContact(EMPLOYEE_ADMIN); // проверяем поиск Контакта пользователя по Фамилии


        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());
    }


}


