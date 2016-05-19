package ru.motiw.web.logicinterface.WebLogic.Task;

import ru.motiw.web.model.Tasks.Folder;

/**
 * Папка
 */
public interface FolderLogic {

    void addFolders(Folder[] folders);

    void editFolder(Folder folder);

    void deleteFolder(Folder folder);


}
