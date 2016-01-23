package ru.st.selenium.tests.testPda;

import com.codeborne.selenide.testng.TextReport;
import ru.st.selenium.model.Task.Task;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagespda.Task.EditTaskPagePDA;
import ru.st.selenium.pages.pagespda.Task.NewTaskPagePDA;
import ru.st.selenium.pages.pagespda.Task.TaskPagePDA;
import ru.st.selenium.tests.data.TestRetryAnalyzer;
import ru.st.selenium.tests.data.system.ModuleTaskTestCase;
import ru.st.selenium.tests.listeners.RetryListener;
import ru.st.selenium.tests.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.st.selenium.pages.pagespda.*;
import ru.st.selenium.tests.listeners.alluretestng.retrylistener.RetryListenerAllure;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

/**
 * Раздел - Настройки
 */
@Listeners({ScreenShotOnFailListener.class, TextReport.class, RetryListenerAllure.class, RetryListener.class})
public class OptionsPDATest extends ModuleTaskTestCase {

    /**
     * проверка - Аттачминг файлов в форме задачи
     */
    @Test(dataProvider = "objectDataTaskPDA", priority = 1, retryAnalyzer = TestRetryAnalyzer.class)
    public void verifyAttachmentFileInTheTask(Task task) throws Exception {
        LoginPagePDA loginPagePDA = open(Page.PDA_PAGE_URL, LoginPagePDA.class);
        // Авторизация
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Task; Today; Document)",
                internalPagePDA.hasMenuUserComplete());


        // Инициализируем стр. формы Настройки и переходим на нее
        OptionsPagePDA optionsPagePDA = internalPagePDA.goToOptions();
        optionsPagePDA.selAttachFiles(true); // устанавливаем признак - возможность прикрепления файлов
        internalPagePDA.goToHome(); // уходим домой

        // Инициализируем стр. формы создание задачи и переходим на нее
        NewTaskPagePDA newTaskPagePDA = internalPagePDA.goToCreateTask();

        //----------------------------------------------------------------ФОРМА - создания Задачи
        newTaskPagePDA.creatingTask(task);
        EditTaskPagePDA editTaskPagePDA = newTaskPagePDA.goToPreview(); // Инициализируем стр. формы предпросмотра задачи и переходим на нее

        //----------------------------------------------------------------ФОРМА - Предпросмотр создания задачи

        editTaskPagePDA.inputValidationFormTask(task); // Проверяем отображение значений в форме предпросмотра создания задачи

        //----------------------------------------------------------------ФОРМА - Задачи

        TaskPagePDA taskForm = editTaskPagePDA.goToTask(); // Инициализируем стр. формы - Созданной задачи и переходим на нее
        taskForm.openShapeCreatedTask(task); // Открываем форму созданной задачи
        assertTrue(taskForm.resultsDisplayButtons()); // Проверяем отображения кнопок в форме задачи
        taskForm.addAttachFiles(randomString(15)); // Аттачим файлы

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }


}

