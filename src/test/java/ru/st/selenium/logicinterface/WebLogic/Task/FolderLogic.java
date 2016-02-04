package ru.st.selenium.logicinterface.WebLogic.Task;

import ru.st.selenium.model.Task.Folder;

/**
 * Папка
 */
public interface FolderLogic {

    void addFolders(Folder[] folders);

    void editFolder(Folder folder);

    void deleteFolder(Folder folder);


}
