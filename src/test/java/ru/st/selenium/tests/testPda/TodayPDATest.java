package ru.st.selenium.tests.testPda;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.st.selenium.model.Task.Folder;
import ru.st.selenium.model.Task.Task;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pagespda.Task.EditTaskPagePDA;
import ru.st.selenium.pages.pagespda.Task.NewTaskPagePDA;
import ru.st.selenium.pages.pagespda.Task.TaskPagePDA;
import ru.st.selenium.pages.pagespda.Task.TasksReportsPagePDA;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;
import ru.st.selenium.pages.pagesweb.Tasks.UnionTasksPage;
import ru.st.selenium.tests.data.system.ModuleTaskCaseTest;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.pages.pagespda.*;
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
@Features("Сегодня (PDA)")
public class TodayPDATest extends ModuleTaskCaseTest {

    @BeforeClass
    public static LoginPagePDA openUrlStartBrowser() {
        open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        return page(LoginPagePDA.class);
    }

    /*
      Инициализируем модель - Задача #2 (атрибуты и лента для редактирования)
     */
    Task editTask = getRandomObjectTask();

    /*
     Инициализируем текст для Ленты действий задачи
     */
    String textActions = randomString(15);

    // Папка
    Folder[] folder = getRandomArrayFolders();

    @Test(priority = 1)
    public void createFolderForTasks() {
        LoginPage loginPage = open(BasePage.WEB_PAGE_URL, LoginPage.class);

        loginPage.loginAs(ADMIN);

        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------- Задачи/Задачи
        UnionTasksPage unionTasksPage = internalPage.goToUnionTasks();
        // Добавляем Папки
        unionTasksPage.addFolders(new Folder[]{folder[0].setNameFolder("wD_Smart_Box " + randomString(4)).setUseFilter(true)});

        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());
    }

    @Severity(SeverityLevel.NORMAL)
    @Title("Отображение информации в разделе Сегодня")
    @Description("Проверяем отображение сохраненной информации в разедел - Сегодня")
    @Test(dataProvider = "objectDataTaskPDA", priority = 1)
    public void verifyInfoToday(Task task) throws Exception {
        LoginPagePDA loginPagePDA = openUrlStartBrowser();
        loginPagePDA.loginAsAdmin(ADMIN);

        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Task; Today; Document)",
                internalPagePDA.hasMenuUserComplete());

        // Инициализируем стр. формы создание задачи и переходим на нее
        NewTaskPagePDA newTaskPagePDA = internalPagePDA.goToCreateTask();

        //----------------------------------------------------------------ФОРМА - создания Задачи
        newTaskPagePDA.creatingTask(task);
        EditTaskPagePDA editTaskPagePDA = newTaskPagePDA.goToPreview(); // Инициализируем стр. формы предпросмотра задачи и переходим на нее

        //----------------------------------------------------------------ФОРМА - Предпросмотр создания задачи
        editTaskPagePDA.inputValidationFormTask(task); // Проверяем отображение значений в форме предпросмотра создания задачи

        //----------------------------------------------------------------ФОРМА - Задачи
        TaskPagePDA taskForm = editTaskPagePDA.goToTask(); // Инициализируем стр. формы - Созданной задачи и переходим на нее
        taskForm.openShapeCreatedTask(task); // Открываем созданную задачу
        assertTrue(taskForm.resultsDisplayButtons()); // Проверяем отображения кнопок в форме задачи
        internalPagePDA.goToHome();
        TasksReportsPagePDA tasksReportsPagePDA = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи
        tasksReportsPagePDA.checkDisplayTaskGrid(task, folder[0]); // Проверяем отображение созданной задачи в гриде Задач
        tasksReportsPagePDA.openTaskInGrid(task); // открываем форму в гриде задач

        //----------------------------------------------------------------ФОРМА - Задачи - Атрибуты

        taskForm.openFormEditTask(task, EMPLOYEE_ADMIN); // открываем форму редактирования атрибутов задачи
        editTaskPagePDA.editAttributesOfTasks(editTask); // редактируем задачу
        taskForm.saveActionsInTheTape(textActions); // добавляем пользовательский текст в задачу и проверяем его сохранение
        internalPagePDA.goToHome();
        TodayPagePDA todayPagePDA = internalPagePDA.goToToday(); // Переходим на стр.
        todayPagePDA.verifyInformationDisplaySectionToday(textActions);

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }

    @AfterClass
    public static void afterTest() {
        close();
    }


}
