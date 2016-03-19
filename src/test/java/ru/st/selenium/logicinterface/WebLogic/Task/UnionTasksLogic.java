package ru.st.selenium.logicinterface.WebLogic.Task;

import com.codeborne.selenide.SelenideElement;
import ru.st.selenium.model.Tasks.Folder;
import ru.st.selenium.model.Tasks.Task;

/**
 * грид - Задачи/Задачи
 */
public interface UnionTasksLogic {


    void openExistingTaskInTheFolderThroughTheSearch(Task task, Folder folder);

    void openAnExistingTaskInFolder (Task task, Folder folder);

    void selectTheGroupInTheGrid(SelenideElement panelGrouping, SelenideElement grouping);

}
