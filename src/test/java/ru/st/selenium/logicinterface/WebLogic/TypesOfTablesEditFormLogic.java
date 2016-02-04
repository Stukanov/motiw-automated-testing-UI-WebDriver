package ru.st.selenium.logicinterface.WebLogic;

import ru.st.selenium.model.Administration.TypesOfTables.TypesOfTables;

/**
 * Форма редактирования Типы таблиц
 */
public interface TypesOfTablesEditFormLogic {

    void addSettingsAndFieldTypesOfTables(TypesOfTables typesOfTables);

    void editFieldTypesOfTables(TypesOfTables typesOfTables);

    void removeAnFieldTypesOfTables(TypesOfTables typesOfTables);


}
