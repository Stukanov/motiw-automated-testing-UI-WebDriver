package ru.st.selenium.logicinterface;

import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;

/**
 * Форма редактирования - Типы задач
 */
public interface TasksTypesEditFormLogic {

    void addFieldTasksTypes(TasksTypes tasksTypes);

    void editFieldTasksTypes(TasksTypes tasksTypes);

    void removeAnFieldTasksTypes(TasksTypes tasksTypes);

}
