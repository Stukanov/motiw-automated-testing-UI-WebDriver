package ru.st.selenium.pages.pagesweb.Tasks;

import ru.st.selenium.model.Task.Action;
import ru.st.selenium.model.Task.Checkpoint;
import ru.st.selenium.model.Task.IWG;
import ru.st.selenium.model.Task.Project;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.Page;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Форма (редактирования) задачи
 */
public class UnionMessagePage extends Page {


    public UnionMessagePage switchToWindow() {
        /*String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		driver.close();
		   for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		          driver.switchTo().window(windowHandle);
		          }
		        } */
        return this;
    }

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

    public UnionMessagePage switchBack() {
        getWebDriver().switchTo().window(getWebDriver().getWindowHandle());
        return this;
    }


}