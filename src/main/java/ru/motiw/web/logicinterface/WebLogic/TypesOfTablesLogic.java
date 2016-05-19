package ru.motiw.web.logicinterface.WebLogic;


import ru.motiw.web.model.Administration.TypesOfTables.TypesOfTables;

/**
 * Типы таблиц
 */
public interface TypesOfTablesLogic {


    void addTypesOfTables(TypesOfTables typesOfTables);

    void editTypesOfTables(TypesOfTables typesOfTables);

    void removeTypesOfTables(TypesOfTables typesOfTables);

}
