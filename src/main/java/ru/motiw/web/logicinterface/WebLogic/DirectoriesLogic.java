package ru.motiw.web.logicinterface.WebLogic;

import ru.motiw.web.model.Administration.Directories.Directories;

/**
 * Справочники
 */
public interface DirectoriesLogic {

    void addDirectories(Directories directories);

    void editDirectories(Directories directories);

    void removeAnDirectories(Directories directories);


}
