package ru.st.selenium.tests.testPda;

import com.codeborne.selenide.Selenide;

import com.codeborne.selenide.testng.TextReport;
import ru.st.selenium.model.Task.Task;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagespda.*;
import ru.st.selenium.pages.pagespda.Task.EditTaskPagePDA;
import ru.st.selenium.pages.pagespda.Task.NewTaskPagePDA;
import ru.st.selenium.pages.pagespda.Task.TaskPagePDA;
import ru.st.selenium.pages.pagespda.Task.TasksReportsPagePDA;
import ru.st.selenium.tests.data.system.ModuleTaskTestCase;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Создать задачу (PDA)")
@Title("Проверка создания Задач в PDA-интерфейсе")
public class CreateTaskPDATest extends ModuleTaskTestCase {

    Task editTask = getRandomObjectTask();

    @Severity(SeverityLevel.BLOCKER)
    @Title("Создание задачи (PDA)")
    @Description("Проверяем создание задачи с набором атрибутов")
    @Test(priority = 1, dataProvider = "objectDataTaskPDA")
    public void verifyCreateTaskPDA(Task task) throws Exception {
        LoginPagePDA loginPagePDA = open(Page.PDA_PAGE_URL, LoginPagePDA.class);
        // Авторизация
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

        //----------------------------------------------------------------ГРИД - Задачи
        // TODO дописать переход в папку если Задачи в папке а не в корне!!! частно из-за этого падают тесты
        TasksReportsPagePDA tasksReportsPagePDA = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи
        tasksReportsPagePDA.checkDisplayTaskGrid(task); // Проверяем отображение созданной задачи в гриде Задач
        internalPagePDA.logout(); // Выход из системы

        assertTrue(loginPagePDA.isNotLoggedInPDA());
    }

    /**
     * проверка - Редактирование задачи
     * TODO - добавить До запуска метода установку зн-ия - Удаление себя из задач == Да
     */
    @Severity(SeverityLevel.BLOCKER)
    @Title("Редактирование задачи")
    @Description("Проверяем редактирование задачи с набором новых атрибутов")
    @Test(priority = 2, dataProvider = "objectDataTaskPDA")
    public void checkEditingTaskPDA(Task task) throws Exception {
        LoginPagePDA loginPagePDA = open(Page.PDA_PAGE_URL, LoginPagePDA.class);

        // Авторизация
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

        internalPagePDA.goToHome(); // Домашняя стр-ца

        TasksReportsPagePDA tasksReportsPagePDA = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи

        tasksReportsPagePDA.checkDisplayTaskGrid(task); // Проверяем отображение созданной задачи в гриде Задач
        tasksReportsPagePDA.openTaskInGrid(task); // открываем форму в гриде задач

        //----------------------------------------------------------------ФОРМА - Задачи - Атрибуты

        taskForm.openFormEditTask(task, EMPLOYEE_ADMIN); // открываем форму редактирования атрибутов задачи
        editTaskPagePDA.editAttributesOfTasks(editTask); // редактируем атрибуты задачи
        taskForm.saveActionsInTheTape(randomString(15)); // добавляем пользовательский текст в задачу и проверяем его сохранение
        editTaskPagePDA.editWorkingGroupInTask(EMPLOYEE_ADMIN); // редактируем РГ задачи (удаляем пользователей)

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }

    /**
     * проверка - Закрытие задачи (Отправка в архив)
     */
    @Test(priority = 3, dataProvider = "objectDataTaskPDA")
    public void verifyCompletionOfTheTaskPDA(Task task) throws Exception {
        LoginPagePDA loginPagePDA = Selenide.open(Page.PDA_PAGE_URL, LoginPagePDA.class);

        // Авторизация
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

        taskForm.openShapeCreatedTask(task); // Открываем форму созданной задачу
        assertTrue(taskForm.resultsDisplayButtons()); // Проверяем отображения кнопок в форме задачи

        internalPagePDA.goToHome();

        TasksReportsPagePDA tasksReportsPagePDA = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи

        taskForm.closeTask(task, randomString(15)); // Закрываем задачу (отправляем в архив)

        internalPagePDA.goToHome(); // Возвращаемся домой (внутренняя страница)
        internalPagePDA.goToTaskReports(); // переходим в грид задач

        tasksReportsPagePDA.checkDisappearTaskInGrid(task);

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());


    }


}
