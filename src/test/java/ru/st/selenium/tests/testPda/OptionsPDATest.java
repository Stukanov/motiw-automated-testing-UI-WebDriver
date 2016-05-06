package ru.st.selenium.tests.testPda;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.BeforeClass;
import ru.st.selenium.model.Tasks.Task;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pagespda.Task.EditTaskPagePDA;
import ru.st.selenium.pages.pagespda.Task.NewTaskPagePDA;
import ru.st.selenium.pages.pagespda.Task.TaskPagePDA;
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

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Features("Настройки (PDA)")
@Title("Проверка работу настроек в PDA-интерфейсе")
public class OptionsPDATest extends ModuleTaskCaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Title("Аттачминг файлов в задаче")
    @Description("Проверяем аттачминг файлов в форме созданной задачи (Лента действий)")
    @Test(dataProvider = "objectDataTaskPDA", priority = 1)
    public void verifyAttachmentFileInTheTask(Task task) throws Exception {
        LoginPagePDA loginPagePDA = open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (TasksElements; Create TasksElements; Today; Document)",
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

