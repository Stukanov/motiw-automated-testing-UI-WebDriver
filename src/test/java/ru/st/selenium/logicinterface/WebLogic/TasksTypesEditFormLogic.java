package ru.st.selenium.logicinterface.WebLogic;

import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;

/**
 * Форма редактирования - Типы задач
 */
public interface TasksTypesEditFormLogic {

    void addSettingsAndFieldTasksTypes(TasksTypes tasksTypes);

    void editFieldTasksTypes(TasksTypes tasksTypes);

    void removeAnFieldTasksTypes(TasksTypes tasksTypes);

}
