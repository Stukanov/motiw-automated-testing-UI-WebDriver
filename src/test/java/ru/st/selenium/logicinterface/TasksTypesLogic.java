package ru.st.selenium.logicinterface;

import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;

/**
 * Типы задач
 */
public interface TasksTypesLogic {

    void addTasksTypes(TasksTypes tasksTypes);

    void editTasksTypes(TasksTypes tasksTypes);

    void removeAnTasksTypes(TasksTypes tasksTypes);


}
