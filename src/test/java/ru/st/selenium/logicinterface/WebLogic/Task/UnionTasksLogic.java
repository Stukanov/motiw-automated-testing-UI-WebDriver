package ru.st.selenium.logicinterface.WebLogic.Task;

import com.codeborne.selenide.SelenideElement;
import ru.st.selenium.model.Task.Folder;
import ru.st.selenium.model.Task.Task;

/**
 * грид - Задачи/Задачи
 */
public interface UnionTasksLogic {


    void openAnExistingTask(Task task, Folder folder);

    void selectTheGroupInTheGrid(SelenideElement panelGrouping, SelenideElement grouping);

}
