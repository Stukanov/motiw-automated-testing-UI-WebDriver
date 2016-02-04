package ru.st.selenium.logicinterface.WebLogic;


import ru.st.selenium.model.Administration.Directories.Directories;

/**
 * Форма редактирования Справочника
 */
public interface DirectoriesEditFormLogic {


    void addSettingsAndFieldDirectories(Directories directories);

    void editFieldDirectories(Directories directories);

    void removeAnFieldDirectories(Directories directories);


}
