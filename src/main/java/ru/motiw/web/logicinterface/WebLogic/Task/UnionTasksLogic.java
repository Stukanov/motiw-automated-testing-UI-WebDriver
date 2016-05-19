package ru.motiw.web.logicinterface.WebLogic.Task;

import com.codeborne.selenide.SelenideElement;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;

/**
 * грид - Задачи/Задачи
 */
public interface UnionTasksLogic {


    void openExistingTaskInTheFolderThroughTheSearch(Task task, Folder folder);

    void openAnExistingTaskInFolder (Task task, Folder folder);

    void selectTheGroupInTheGridForUserComplete(SelenideElement panelGrouping, int countPanelGrouping);

}
