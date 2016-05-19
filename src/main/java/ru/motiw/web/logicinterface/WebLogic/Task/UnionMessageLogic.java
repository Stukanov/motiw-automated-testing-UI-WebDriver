package ru.motiw.web.logicinterface.WebLogic.Task;

import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.Tasks.UnionMessagePageSteps;

/**
 * Форма задачи
 */
public interface UnionMessageLogic {

    UnionMessagePageSteps verifyCreateTask(Task task);

}
