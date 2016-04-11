package ru.st.selenium.pages.pagespda.Task;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.model.Tasks.Task;
import ru.st.selenium.model.Administration.Users.Employee;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/*
 * Форма задачи (Атрибуты задачи)
 */
public class EditTaskPagePDA extends NewTaskPagePDA {

    /*
     * кнопка Создать задачу
     */
    @FindBy(css = "input[name='next3']")
    private SelenideElement createTask;

    /*
     * Ссылка - Перейти к задаче
     */
    @FindBy(xpath = "//a[contains(@href, '/task/')]")
    private SelenideElement goToTask;

    /*
     * Сохранить
     */
    @FindBy(css = "div.save_button")
    private SelenideElement save;

    /*
     * Ссылка на задачу в форме редактирования задачи
     */
    @FindBy(xpath = "(//div[@class='menu-line']//a/li)[2]")
    private SelenideElement linkTaskReturnMainForm;


    /**
     * Проверка введенных данный в предпросмотре формы создания задачи
     *
     * @param task return values of attributes of the task
     * @return EditTaskPagePDA page
     */
    public EditTaskPagePDA inputValidationFormTask(Task task) {
        $(By.xpath("//form[@id='data_value']//li[2]//span[@style][text()='" + task.getTaskName() + "']"))
                .shouldBe(visible); // Название задачи
        $(By.xpath("//form[@id='data_value']//li[3]//span[@style][text()='" + task.getDescription() + "']"))
                .shouldBe(visible); // Описание задачи
        $(By.xpath("//form[@id='data_value']//li[9]//span[@style][contains(text(),'" + task.getDateEnd() + "')]"))
                .shouldBe(visible); // Окончание задачи
        saveNewTask();
        return this;
    }

    /**
     * Создать (нажатие кнопки - Создать)
     *
     * @return EditTaskPagePDA
     */
    public EditTaskPagePDA saveNewTask() {
        createTask.click();
        $(By.xpath("//a[contains(@href, '/task/')]")).waitUntil(Condition.appear, 4);
        return this;
    }

    /**
     * Перейти к форме задачи (Лента действий)
     *
     * @return TaskPagePDA
     */
    public TaskPagePDA goToTask() {
        goToTask.click();
        return page(TaskPagePDA.class);
    }

    /**
     * Сохранить изменения по задаче
     *
     * @return EditTaskPagePDA
     */
    public EditTaskPagePDA saveChangesToTask() {
        save.click();
        return this;
    }

    /**
     * Редактирование атрибутов задачи
     *
     * @return EditTaskPagePDA
     */
    public EditTaskPagePDA editAttributesOfTasks(Task editTask) {
        setTaskName(editTask.getTaskName()) // Название задачи
                .setTasksDescription(editTask.getDescription()) // Описание задачи
                .setDateEnd(editTask.getDateEnd()); // Дата окончания задачи
        rangeOfValuesF​romTheCheckbox(editTask.getIsImportant(), importantTask); // признак - Важная задача
        // TODO - Добавить проверку - Установка настройки - СЕКРЕТНАЯ задача в настройках системы!!!
        rangeOfValuesF​romTheCheckbox(editTask.getIsSecret(), privateTask); // признак - Секретная задача
        saveChangesToTask();
        sleep(1500);
        checkTheAttributesAreSaved(editTask); // проверяем отображение изменений (системное действие) в ленте действий
        verifyAttributesOfTask(editTask); // проверяем отображение новых значений в полях задачи
        return this;
    }

    /**
     * Проверяем отображение новых значений в полях задачи
     *
     * @return EditTaskPagePDA
     */
    public EditTaskPagePDA verifyAttributesOfTask(Task editTask) {
        goToTask.click();
        $(By.xpath("//input[@id='input_prj_t' and @name='task_name']"))
                .waitUntil(hasValue(" " + editTask.getTaskName() + " "), 10000); // Название задачи
        $(By.xpath("//textarea[@id='task_description']"))
                .shouldHave(value(" " + editTask.getDescription() + " ")); // Описание задачи
        return this;
    }

    /**
     * Редактирование РГ (рабочая группа) задачи
     *
     * @param employee return values user details
     * @return EditTaskPagePDA
     */
    public EditTaskPagePDA editWorkingGroupInTask(Employee employee) {
        goToTask.click();
        // Удаляем - Контролеры задачи
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'cg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]")).click();
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'cg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]"))
                .shouldNotBe(visible);
        // Удаляем - Ответственные руководители
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'rg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]")).click();
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'rg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]"))
                .shouldNotBe(visible);
        // Удаляем - Исполнители
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'wg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]")).click();
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'wg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]"))
                .shouldNotBe(visible);
        checkWorkingGroupInTaskAreSaved(employee); // проверяем формирование системных действий об удалении пользователей в ленте действий задачи
        return this;
    }

    /**
     * Проверяем сохраненные изменения в ленте действий задачи
     *
     * @param editTask post in action
     * @return TaskPagePDA
     */
    public TaskPagePDA checkTheAttributesAreSaved(Task editTask) {
        linkTaskReturnMainForm.click();
        $(By.xpath("//div[@id='mainblock']//ul[@class='ui-listview']//div//font[text()='" + editTask.getTaskName() + "']"))
                .shouldHave(Condition.exactText(" " + editTask.getTaskName() + " "));
        return page(TaskPagePDA.class);
    }

    /**
     * Проверяем сохраненные изменения в ленте действий задачи
     *
     * @param employee
     * @return TaskPagePDA
     */
    public TaskPagePDA checkWorkingGroupInTaskAreSaved(Employee employee) {
        linkTaskReturnMainForm.click();
        $$(By.xpath("//span[contains(text(),'Из задачи удален') and contains(text(),'" + employee.getLastName() + "')]"))
                .shouldHave(CollectionCondition.size(3));
        return page(TaskPagePDA.class);
    }


}
