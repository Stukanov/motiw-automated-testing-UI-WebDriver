package ru.st.selenium.model.Administration.TypesOfTables;

/**
 * Модель объекта системы - Типы таблиц (Администрирование/Типы таблиц)
 */
public class TypesOfTables {

    private String nameTableType;
    private TypesOfTablesField[] typesOfTablesFields;


    public TypesOfTables(String nameTableType) {
        this.nameTableType = nameTableType;
    }

    /**
     * Название - Типы таблиц
     */
    public String getTableTypeName() {
        return nameTableType;
    }

    public TypesOfTables setTableTypeName(String nameTableName) {
        this.nameTableType = nameTableName;
        return this;
    }

    /**
     * Поля - массив объектов - поля объекта "Типы таблиц"
     */
    public TypesOfTablesField[] getTypesOfTablesFields() {
        return typesOfTablesFields;
    }

    public TypesOfTables setTypesOfTablesFields(TypesOfTablesField[] typesOfTablesFields) {
        this.typesOfTablesFields = typesOfTablesFields;
        return this;
    }

}
