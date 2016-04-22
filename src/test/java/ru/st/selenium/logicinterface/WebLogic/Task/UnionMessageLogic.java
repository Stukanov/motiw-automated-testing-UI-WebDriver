package ru.st.selenium.logicinterface.WebLogic.Task;

import ru.st.selenium.model.Tasks.Task;
import ru.st.selenium.pagesteps.Tasks.UnionMessagePageSteps;

/**
 * Форма задачи
 */
public interface UnionMessageLogic {

    UnionMessagePageSteps verifyCreateTask(Task task);

}
