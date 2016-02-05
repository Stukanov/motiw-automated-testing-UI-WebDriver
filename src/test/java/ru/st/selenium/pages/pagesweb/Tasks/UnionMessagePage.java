package ru.st.selenium.pages.pagesweb.Tasks;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.st.selenium.logicinterface.WebLogic.Task.UnionMessageLogic;
import ru.st.selenium.model.Task.*;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.Page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Форма (редактирования) задачи
 */
public class UnionMessagePage extends Page implements UnionMessageLogic {


    public UnionMessagePage verifyEnd(String end) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyProject(Project project) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyTaskDescription(String description) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyBegin(String begin) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyImportance(boolean isImportant) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyAuthors(Employee[] authors) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyControllers(Employee[] controllers) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyWorkers(Employee[] workers) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyResppersons(Employee[] resppersons) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyTaskType(TasksTypes tasktype) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyIWG(IWG[] iwg) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyCheckpoints(Checkpoint[] checkpoints) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyReport(boolean isWithReport) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifySecret(boolean isSecret) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyReview(boolean isForReview) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage postAction(Action[] actions) {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage verifyAction() {
        // TODO Auto-generated method stub
        return this;
    }

    public UnionMessagePage closeWindow() {
        getWebDriver().close();
        return this;
    }

    /**
     * Проверяем создание задачи
     *
     * @param valueTask
     */
    @Override
    public UnionMessagePage verifyCreateTask(Task valueTask) {
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
    public UnionMessagePage ensurePageLoaded() {
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