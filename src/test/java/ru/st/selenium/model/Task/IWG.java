package ru.st.selenium.model.Task;

import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.Users.Employee;

/**
 * Модель объекта системы - Изолированная рабочая группа
 */
public class IWG {


    private String name;
    private TaskTemplate taskTemplate;
    private TasksTypes tasksTypes;
    private boolean isSystemActionsInParentTask;
    private Employee[] resppersons;
    private Employee[] workers;
    private Employee[] controller;


    /**
     * Название ИРГ
     */
    public String getNameIWG() {
        return name;
    }

    public IWG setNameIWG(String name) {
        this.name = name;
        return this;
    }

    /**
     * Шаблон задачи
     *
     * @return
     */
    public TaskTemplate getTaskTemplate() {
        return taskTemplate;
    }

    public IWG setTaskTemplate(TaskTemplate taskTemplate) {
        this.taskTemplate = taskTemplate;
        return this;
    }

    /**
     * Тип задачи
     */
    public TasksTypes getTasksTypes() {
        return tasksTypes;
    }

    public IWG setTasksTypes(TasksTypes tasksTypes) {
        this.tasksTypes = tasksTypes;
        return this;
    }

    /**
     * Системные действия транслируются в родительскую задачу?
     */
    public boolean getIsSystemActionsInParentTask() {
        return isSystemActionsInParentTask;
    }

    public IWG setIsSystemActionsInParentTask(boolean isSystemActionsInParentTask) {
        this.isSystemActionsInParentTask = isSystemActionsInParentTask;
        return this;
    }

    /**
     * Ответственные руководители
     */
    public Employee[] getRespPersons() {
        return resppersons;
    }

    public IWG setRespPersons(Employee[] resppersons) {
        this.resppersons = resppersons;
        return this;
    }

    /**
     * Исполнители
     */
    public Employee[] getWorkers() {
        return workers;
    }

    public IWG setWorkers(Employee[] workers) {
        this.workers = workers;
        return this;
    }

    /**
     * Контролеры
     */
    public Employee[] getControllers() {
        return controller;
    }

    public IWG setControllers(Employee[] controller) {
        this.controller = controller;
        return this;
    }

}
