package ru.motiw.web.logicinterface.WebLogic;


import ru.motiw.web.model.Administration.Directories.Directories;

/**
 * Форма редактирования Справочника
 */
public interface DirectoriesEditFormLogic {


    void addSettingsAndFieldDirectories(Directories directories);

    void editFieldDirectories(Directories directories);

    void removeAnFieldDirectories(Directories directories);


}
