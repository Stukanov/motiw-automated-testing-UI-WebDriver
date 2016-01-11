package ru.st.selenium.pages.pagespda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.model.Task.Task;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.Page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/*
 Страница - Создать задачу
 */
public class NewTaskPagePDA extends Page {

    /*
     Название задачи
     */
    @FindBy(xpath = "//input[@id='input_prj_t' and @name='task_name']")
    private SelenideElement tasksName;

    /*
     Описание задачи
     */
    @FindBy(xpath = "//textarea[@id='task_description']")
    private SelenideElement tasksDescription;

    /*
     Окончание задачи
     */
    @FindBy(xpath = "//input[@id='mes_date_end']")
    private SelenideElement dateEnd;

    /*
     Авторы
     */
    @FindBy(xpath = "//input[@id='ag']")
    private SelenideElement inputFieldAuthors;

    /*
     Контролеры задачи
     */
    @FindBy(xpath = "//input[@id='cg']")
    private SelenideElement inputFieldTaskSupervisors;

    /*
     Ответственные руководители
     */
    @FindBy(xpath = "//input[@id='rg']")
    private SelenideElement inputFieldExecutiveManagers;

    /*
     Исполнители
     */
    @FindBy(xpath = "//input[@id='wg']")
    private SelenideElement inputFieldPerformers;

    /*
     Секретная задача
     */
    @FindBy(xpath = "//div[@class='ui-checkbox']//input[@id='secret']/..//span[2]")
    private SelenideElement privateTask;

    /*
     C докладом
     */
    @FindBy(xpath = "//div[@class='ui-checkbox']//input[@id='withreport2']/..//span[2]")
    private SelenideElement reportRequired;

    /*
     Важная задача
     */
    @FindBy(xpath = "//div[@class='ui-checkbox']//input[@id='taskhigh']/..//span[2]")
    private SelenideElement importantTask;

    /*
     Просмотр
     */
    @FindBy(css = "input[name='next2']")
    private SelenideElement view;

    /*
     Постановщик задачи
     */
    @FindBy(xpath = "//a [ancestor::span[@name='autor']]")
    private SelenideElement directorOfTheTask;

    /**
     * Название задачи
     *
     * @param nameTasks name task for input
     * @return page NewTaskPag
     */
    public NewTaskPagePDA setTaskName(String nameTasks) {
        tasksName.clear();
        tasksName.setValue(nameTasks);
        return this;
    }

    /**
     * Описание задачи
     *
     * @param descriptionTasks description task for input
     * @return page NewTaskPag
     */
    public NewTaskPagePDA setTasksDescription(String descriptionTasks) {
        tasksDescription.clear();
        tasksDescription.setValue(descriptionTasks);
        return this;
    }

    /**
     * Окончание задачи
     *
     *  @param end date end for task
     *
     * @return
     */
    public NewTaskPagePDA setDateEnd(String end) {
        dateEnd.clear();
        dateEnd.setValue(end);
        return this;
    }

    /**
     * Производим заполнение поля - Авторы задачи
     *
     * @param authors
     * @return
     */
    public NewTaskPagePDA setAuthors(Employee authors[]) {
        if (authors == null)
            return this;
        else
            for (Employee author : authors) {
                inputFieldAuthors.setValue(author.getLastName());
                $(By.xpath("//ul[contains(@style,' display: block')]//a[contains(text(),'" + author.getLastName() + "')]"))
                        .shouldBe(Condition.visible);
                $(By.xpath("//ul[contains(@style,' display: block')]//a[contains(text(),'" + author.getLastName() + "')]")).click();
            }
        return this;
    }

    /**
     * Производим заполнение поля - Контролеры задачи
     *
     * @param taskSupervisors
     * @return
     */
    public NewTaskPagePDA setTaskSupervisors(Employee taskSupervisors[]) {
        if (taskSupervisors == null)
            return this;
        else
            for (Employee taskSupervisor : taskSupervisors) {
                inputFieldTaskSupervisors.clear();
                inputFieldTaskSupervisors.setValue(taskSupervisor.getLastName());
                $(By.xpath("//ul[contains(@style,' display: block')]//a[contains(text(),'" + taskSupervisor.getLastName() + "')]"))
                        .shouldBe(Condition.visible);
                $(By.xpath("//ul[contains(@style,' display: block')]//a[contains(text(),'" + taskSupervisor.getLastName() + "')]")).click();
            }
        return this;
    }

    /**
     * Производим заполнение поля - Ответственные руководители
     *
     * @param exeManagers
     * @return
     */
    public NewTaskPagePDA setExecutiveManagers(Employee exeManagers[]) {
        if (exeManagers == null) {
            return this;
        } else
            for (Employee exeManager : exeManagers) {
                inputFieldExecutiveManagers.clear();
                inputFieldExecutiveManagers.setValue(exeManager.getLastName());
                $(By.xpath("//ul[contains(@style,' display: block')]//a[contains(text(),'" + exeManager.getLastName() + "')]"))
                        .shouldBe(Condition.visible);
                $(By.xpath("//ul[contains(@style,' display: block')]//a[contains(text(),'" + exeManager.getLastName() + "')]")).click();
            }
        return this;
    }

    /**
     * Производим заполнение поля - Исполнители
     *
     * @param performers
     * @return
     */
    public NewTaskPagePDA setPerformers(Employee performers[]) {
        if (performers == null) {
            return this;
        } else
            for (Employee performer : performers) {
                inputFieldPerformers.clear();
                inputFieldPerformers.setValue(performer.getLastName());
                $(By.xpath("//ul[contains(@style,' display: block')]//a[contains(text(),'" + performer.getLastName() + "')]"))
                        .shouldBe(Condition.visible);
                $(By.xpath("//ul[contains(@style,' display: block')]//a[contains(text(),'" + performer.getLastName() + "')]")).click();
            }
        return this;
    }

    /**
     * Секретная задача
     *
     * @param privTask
     * @return
     */
    public NewTaskPagePDA setPrivateTask(boolean privTask) {
        if (privTask) {
            privateTask.click();
        }
        return this;
    }

    /**
     * C докладом
     *
     * @param reportReq
     * @return
     */
    public NewTaskPagePDA setReportRequired(boolean reportReq) {
        if (reportReq) {
            reportRequired.click();
        }
        return this;
    }

    /**
     * Важная задача
     *
     * @param impTask
     * @return
     */
    public NewTaskPagePDA setImportantTask(boolean impTask) {
        if (impTask) {
            importantTask.click();
        }
        return this;
    }

    /**
     * Просмотр (предсоздание задачи)
     *
     * @return
     */
    public EditTaskPagePDA goToPreview() {
        view.click();
        $(By.cssSelector("input[name='next3']")).waitUntil(Condition.appear, 4);
        return page(EditTaskPagePDA.class);
    }

    /**
     * Создание новой задачи
     */
    public void createTask(Task task) {
        setTaskName(task.getTaskName()) // Название задачи
                .setTasksDescription(task.getDescription()) // Описание задачи
                .setTaskSupervisors(task.getControllers()) // вводим - Контролеры задачи
                .setExecutiveManagers(task.getExecutiveManagers()) // вводим - Ответственные руковдители
                .setPerformers(task.getWorkers()) // вводим - Исполнители
                .setDateEnd(task.getDateEnd()) // Дата окончания задачи
                .setImportantTask(task.getIsImportant()) // признак - Важная задача
                .setReportRequired(task.getIsWithReport()) // признак - С доклаом
                .setPrivateTask(task.getIsSecret()); // признак - Секретная задача


    }


}
