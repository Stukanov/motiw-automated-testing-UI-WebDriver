package tests.testPda;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagesweb.Administration.SystemOptionsPage;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.elements.elementspagesweb.Login.LoginPage;
import ru.motiw.web.elements.elementspagespda.InternalPagePDA;
import ru.motiw.web.elements.elementspagespda.LoginPagePDA;
import ru.motiw.web.elements.elementspagespda.Task.NewTaskPagePDA;
import ru.motiw.web.elements.elementspagespda.Task.TaskPagePDA;
import ru.motiw.web.elements.elementspagespda.Task.TasksReportsPagePDA;
import tests.data.system.ModuleTaskCaseTest;
import tests.listeners.ScreenShotOnFailListener;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.elements.elementspagespda.Task.EditTaskPagePDA;
import ru.motiw.web.steps.Tasks.UnionTasksPageSteps;
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
@Features("Создать задачу (PDA)")
@Title("Проверка создания Задач в PDA-интерфейсе")
public class CreateTaskPDATest extends ModuleTaskCaseTest {

    // Атрибуты задачи для редактирования задачи
    Task editTask = getRandomObjectTask();

    // Папка
    Folder[] folder = getRandomArrayFolders();


    @Test(priority = 1)
    public void aPreconditionForFurtherVerification() {
        LoginPage loginPage = Selenide.open(BasePage.WEB_PAGE_URL, LoginPage.class);

        loginPage.loginAs(ADMIN);

        InternalPage internalPage = loginPage.initializedInsidePage(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------- Задачи/Задачи
        UnionTasksPageSteps unionTasksPageSteps = internalPage.goToUnionTasks();
        unionTasksPageSteps.beforeAddFolder(19);
        // добавить Папку - для фильтрации созданных задач
        unionTasksPageSteps.addFolders(new Folder[]{folder[0].setNameFolder("wD_Smart_Box " + randomString(4)).setUseFilter(true).setFilterField("Начало").setChooseRelativeValue(true)
                .setSharedFolder(false).setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false)});

        //---------------------------------------------------------------- Настройки системы
        SystemOptionsPage systemOptionsPage = internalPage.goToSystemOptionsPage();
        // Выбор опции - Секретная задача == Да
        systemOptionsPage.selectCreatingASecretTask();
        // Выбор опции - Удаление себя из задач == Да
        systemOptionsPage.selectAllowToDeleteOneSelfFromTasks();

        internalPage.logout();
        assertTrue(loginPage.isNotLoggedIn());
    }


    @Severity(SeverityLevel.BLOCKER)
    @Title("Создание задачи")
    @Description("Проверяем создание задачи с набором атрибутов")
    @Test(priority = 2, dataProvider = "objectDataTaskPDA")
    public void verifyCreateTaskPDA(Task task) throws Exception {
        LoginPagePDA loginPagePDA = open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        // Авторизация
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
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

        //----------------------------------------------------------------ГРИД - Задачи
        TasksReportsPagePDA tasksReportsPagePDA = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи
        tasksReportsPagePDA.checkDisplayTaskGrid(task, folder[0]); // Проверяем отображение созданной задачи в гриде Задач
        internalPagePDA.logout(); // Выход из системы

        assertTrue(loginPagePDA.isNotLoggedInPDA());
    }


    @Severity(SeverityLevel.BLOCKER)
    @Title("Редактирование задачи")
    @Description("Проверяем редактирование задачи с набором новых атрибутов")
    @Test(priority = 3, dataProvider = "objectDataTaskPDA")
    public void checkEditingTaskPDA(Task task) throws Exception {
        // Авторизация
        LoginPagePDA loginPagePDA = open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        loginPagePDA.loginAsAdmin(ADMIN);

        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
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

        internalPagePDA.goToHome(); // Домашняя стр-ца

        TasksReportsPagePDA tasksReportsPagePDA = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи
        tasksReportsPagePDA.checkDisplayTaskGrid(task, folder[0]); // Проверяем отображение созданной задачи в гриде Задач
        tasksReportsPagePDA.openTaskInGrid(task); // открываем форму в гриде задач

        //----------------------------------------------------------------ФОРМА - Задачи - Атрибуты

        taskForm.openFormEditTask(task, EMPLOYEE_ADMIN); // открываем форму редактирования атрибутов задачи
        editTaskPagePDA.editAttributesOfTasks(editTask); // редактируем атрибуты задачи
        taskForm.saveActionsInTheTape(randomString(15)); // добавляем пользовательский текст в задачу и проверяем его сохранение
        editTaskPagePDA.editWorkingGroupInTask(EMPLOYEE_ADMIN); // редактируем РГ задачи (удаляем пользователей)

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }

    @Severity(SeverityLevel.CRITICAL)
    @Title("Завершение задачи")
    @Description("Проверяем завершение задачи (отправка в Архив)")
    @Test(priority = 4, dataProvider = "objectDataTaskPDA")
    public void verifyCompletionOfTheTaskPDA(Task task) throws Exception {
        LoginPagePDA loginPagePDA = open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
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

        taskForm.openShapeCreatedTask(task); // Открываем форму созданной задачу
        assertTrue(taskForm.resultsDisplayButtons()); // Проверяем отображения кнопок в форме задачи
        internalPagePDA.goToHome();
        TasksReportsPagePDA tasksReportsPagePDA = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи
        taskForm.closeTask(task, randomString(15), folder[0]); // Закрываем задачу (отправляем в архив)

        internalPagePDA.goToHome(); // Возвращаемся домой (внутренняя страница)
        internalPagePDA.goToTaskReports(); // переходим в грид задач

        tasksReportsPagePDA.checkDisappearTaskInGrid(task);

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());


    }


}
