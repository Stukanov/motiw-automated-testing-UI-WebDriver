package ru.motiw.web.logicinterface.WebLogic;

import ru.motiw.web.model.Administration.TypesOfTables.TypesOfTables;

/**
 * Форма редактирования Типы таблиц
 */
public interface TypesOfTablesEditFormLogic {

    void addSettingsAndFieldTypesOfTables(TypesOfTables typesOfTables);

    void editFieldTypesOfTables(TypesOfTables typesOfTables);

    void removeAnFieldTypesOfTables(TypesOfTables typesOfTables);


}
