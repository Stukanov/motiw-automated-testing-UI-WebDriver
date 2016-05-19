package ru.motiw.web.logicinterface.WebLogic;

import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;

/**
 * Типы задач
 */
public interface TasksTypesLogic {

    void addTasksTypes(TasksTypes tasksTypes);

    void editTasksTypes(TasksTypes tasksTypes);

    void removeAnTasksTypes(TasksTypes tasksTypes);


}
