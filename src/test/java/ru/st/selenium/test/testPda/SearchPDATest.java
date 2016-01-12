package ru.st.selenium.test.testPda;

import com.codeborne.selenide.testng.TextReport;
import ru.st.selenium.pages.pagespda.LoginPagePDA;
import ru.st.selenium.pages.pagespda.SearchPagePDA;
import ru.st.selenium.test.data.TestRetryAnalyzer;
import ru.st.selenium.test.data.system.ModuleTaskTestCase;
import ru.st.selenium.test.listeners.RetryListener;
import ru.st.selenium.test.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.pages.pagespda.InternalPagePDA;
import ru.st.selenium.pages.Page;
import ru.st.selenium.test.listeners.alluretestng.retrylistener.RetryListenerAllure;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

/**
 * раздел - Поиск
 */
@Listeners({ScreenShotOnFailListener.class, TextReport.class, RetryListenerAllure.class, RetryListener.class})
public class SearchPDATest extends ModuleTaskTestCase {



    /**
     * проверка - Посик в системе - SOLR
     */
    @Test(priority = 1, retryAnalyzer = TestRetryAnalyzer.class)
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

       newTaskPage.createTask(task);
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
        //TODO Добавить выбор фильтрации - КОНТАКТЫ - НЕРАБОТАЕТ СЕЙЧАС!!!
        searchPagePDA.searchContact(EMPLOYEE_ADMIN); // проверяем поиск Контакта пользователя по Фамилии


        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());
    }


}


