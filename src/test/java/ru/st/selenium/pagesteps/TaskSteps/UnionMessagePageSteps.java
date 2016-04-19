package ru.st.selenium.pagesteps.TaskSteps;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.st.selenium.logicinterface.WebLogic.Task.UnionMessageLogic;
import ru.st.selenium.model.Tasks.*;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.st.selenium.utils.WindowsUtil.newWindowForm;

/**
 * Форма (редактирования) задачи
 */
public class UnionMessagePageSteps extends BasePage implements UnionMessageLogic {


    public UnionMessagePageSteps verifyEnd(String end) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyProject(Project project) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyTaskDescription(String description) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyBegin(String begin) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyImportance(boolean isImportant) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyAuthors(Employee[] authors) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyControllers(Employee[] controllers) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyWorkers(Employee[] workers) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyResppersons(Employee[] resppersons) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyTaskType(TasksTypes tasktype) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyIWG(IWG[] iwg) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyCheckpoints(Checkpoint[] checkpoints) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyReport(boolean isWithReport) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifySecret(boolean isSecret) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyReview(boolean isForReview) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps postAction(Action[] actions) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps verifyAction() {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePageSteps closeWindow() {
        getWebDriver().close();
        return this;
    }

    /**
     * Проверяем создание задачи
     *
     * @param valueTask
     */
    @Override
    public UnionMessagePageSteps verifyCreateTask(Task valueTask) {
        /*
         Window PopUp. Store your parent window
         */
        String parentWindowHandler = getWebDriver().getWindowHandle();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.xpath("//body[@id='unionmessage']//li//span[text()='Действия']"))));
        ensurePageLoaded();
        if (valueTask == null) {
            return null;
        } else
            verifyEnd(valueTask.getDateEnd())
                    .verifyProject(valueTask.getProject())
                    .verifyTaskDescription(valueTask.getDescription())
                    .verifyBegin(valueTask.getDateBegin())
                    .verifyImportance(valueTask.getIsImportant())
                    .verifyAuthors(valueTask.getAuthors())
                    .verifyControllers(valueTask.getControllers())
                    .verifyWorkers(valueTask.getWorkers())
                    .verifyResppersons(valueTask.getExecutiveManagers())
                    .verifyTaskType(valueTask.getTasktype())
                    .verifyIWG(valueTask.getIWG())
                   // TODO вынести проверки -  .verifyCheckpoints(valueTask.getCheckpoints()) и .verifyIWG(valueTask.getIWG()) в отдельные - для соот.  отдельных методов
                    .verifyCheckpoints(valueTask.getCheckpoints())
                    .verifyReport(valueTask.getIsWithReport())
                    .verifySecret(valueTask.getIsSecret())
                    .verifyReview(valueTask.getIsForReview())
                    .postAction(valueTask.getActions())
                    .verifyAction();

        closeWindow();
        getWebDriver().switchTo().window(parentWindowHandler);  // Switch back to parent window
        return this;
    }


    /**
     * Проверка загрузки формы задачи
     */
    public UnionMessagePageSteps ensurePageLoaded() {
        $(By.xpath("//body[@id='unionmessage']//li//span[text()='Действия']")).shouldBe(Condition.visible);
        $(By.xpath("//body[@id='unionmessage']//li//span[text()='Описание']")).shouldBe(Condition.visible);
        $(By.xpath("//body[@id='unionmessage']//li//span[text()='Файлы']")).shouldBe(Condition.visible);
        $(By.xpath("//body[@id='unionmessage']//li//span[text()='Планирование']")).shouldBe(Condition.visible);
        $(By.xpath("//body[@id='unionmessage']//li//span[text()='События']")).shouldBe(Condition.visible);
        $(By.xpath("//body[@id='unionmessage']//li//span[text()='Контакты']")).shouldBe(Condition.visible);
        $(By.xpath("//body[@id='unionmessage']//li//span[text()='Журнал']")).shouldBe(Condition.visible);
        $(By.xpath("//body[@id='unionmessage']//li//span[text()='Дополнительно']")).shouldBe(Condition.visible);
        return this;
    }
}