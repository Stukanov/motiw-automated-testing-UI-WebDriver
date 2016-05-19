package ru.motiw.web.logicinterface.WebLogic;

import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;

/**
 * Форма редактирования - Типы задач
 */
public interface TasksTypesEditFormLogic {

    void addSettingsAndFieldTasksTypes(TasksTypes tasksTypes);

    void editFieldTasksTypes(TasksTypes tasksTypes);

    void removeAnFieldTasksTypes(TasksTypes tasksTypes);

}
