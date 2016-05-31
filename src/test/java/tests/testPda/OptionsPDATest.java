package tests.testPda;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagespda.InternalPagePDA;
import ru.motiw.web.elements.elementspagespda.LoginPagePDA;
import ru.motiw.web.elements.elementspagespda.OptionsPagePDA;
import ru.motiw.web.elements.elementspagespda.Task.EditTaskPagePDA;
import ru.motiw.web.elements.elementspagespda.Task.NewTaskPagePDA;
import ru.motiw.web.elements.elementspagespda.Task.TaskPagePDA;
import tests.data.BaseTest;
import tests.data.system.ModuleTaskCaseTest;
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
@Features("Настройки (PDA)")
@Title("Проверка работу настроек в PDA-интерфейсе")
public class OptionsPDATest extends ModuleTaskCaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Title("Аттачминг файлов в задаче")
    @Description("Проверяем аттачминг файлов в форме созданной задачи (Лента действий)")
    @Test(dataProvider = "objectDataTaskPDA", priority = 1)
    public void verifyAttachmentFileInTheTask(Task task) throws Exception {
        LoginPagePDA loginPagePDA = Selenide.open(BasePage.PDA_PAGE_URL, LoginPagePDA.class);
        loginPagePDA.loginAsAdmin(BaseTest.ADMIN);
        InternalPagePDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
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
        taskForm.addAttachFiles(BaseTest.randomString(15)); // Аттачим файлы

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }


}

