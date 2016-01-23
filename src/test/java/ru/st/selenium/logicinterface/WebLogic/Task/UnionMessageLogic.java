package ru.st.selenium.logicinterface.WebLogic.Task;

import ru.st.selenium.model.Task.Task;
import ru.st.selenium.pages.pagesweb.Tasks.UnionMessagePage;

/**
 * Форма задачи
 */
public interface UnionMessageLogic {

    UnionMessagePage verifyCreateTask(Task task);

}
