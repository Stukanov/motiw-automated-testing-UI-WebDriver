package ru.st.selenium.model.Administration.FieldsObject;

import ru.st.selenium.model.Administration.TypesOfTables.RightsEditingTableEntries;
import ru.st.selenium.model.Administration.TypesOfTables.TypesOfTables;
import ru.st.selenium.model.Administration.TypesOfTables.TypesOfTablesField;

/**
 * Модель объекта системы - Тип поля "Таблица"
 */
public class TypeListFieldsTable extends ParentFieldsObject {


    private String nameTypesOfTables;
    private String nameTypesOfTablesField;
    private TypesOfTables typesOfTables;
    private TypesOfTablesField fieldTable;
    private String numeratorTemplate;

    private boolean rightsAddingAuthors;
    private boolean rightsAddingTaskSupervisors;
    private boolean rightsAddingExecutiveManagers;
    private boolean rightsAddingPerformers;

    private RightsEditingTableEntries rightsEditAuthors;
    private RightsEditingTableEntries rightsEditTaskSupervisors;
    private RightsEditingTableEntries rightsEditExecutiveManagers;
    private RightsEditingTableEntries rightsEditPerformers;

    private RightsEditingTableEntries rightsDeletionAuthors;
    private RightsEditingTableEntries rightsDeletionTaskSupervisors;
    private RightsEditingTableEntries rightsDeletionExecutiveManagers;
    private RightsEditingTableEntries rightsDeletionPerformers;


    /**
     * Название таблицы
     *
     * @return
     */
    public String getTypesOfTablesName() {
        return nameTypesOfTables;
    }

    public TypeListFieldsTable setTypesOfTablesName(String nameTypesOfTables) {
        this.nameTypesOfTables = nameTypesOfTables;
        return this;
    }

    /**
     * Название поля таблицы
     *
     * @return
     */
    public String getNameTypesOfTablesField() {
        return nameTypesOfTablesField;
    }

    public TypeListFieldsTable setNameTypesOfTablesField(String nameTypesOfTablesField) {
        this.nameTypesOfTablesField = nameTypesOfTablesField;
        return this;
    }

    /**
     * Таблица
     *
     * @return
     */
    public TypesOfTables getTypesOfTables() {
        return typesOfTables;
    }

    public TypeListFieldsTable setTypesOfTables(TypesOfTables typesOfTables) {
        this.typesOfTables = typesOfTables;
        return this;
    }

    /**
     * Поле таблицы
     *
     * @return
     */
    public TypesOfTablesField getFieldTable() {
        return fieldTable;
    }

    public TypeListFieldsTable setFieldTable(TypesOfTablesField fieldTable) {
        this.fieldTable = fieldTable;
        return this;
    }

    /**
     * Шаблон отображения
     *
     * @return
     */
    public String getNumeratorTemplate() {
        return numeratorTemplate;
    }

    public TypeListFieldsTable setNumeratorTemplate(
            String numeratorTemplate) {
        this.numeratorTemplate = numeratorTemplate;
        return this;
    }

    /**
     * Права - Добавление
     * Авторы
     *
     * @return
     */
    public boolean getRightsAddingAuthors() {
        return rightsAddingAuthors;
    }

    public TypeListFieldsTable setRightsAddingAuthors(boolean rightsAddingAuthors) {
        this.rightsAddingAuthors = rightsAddingAuthors;
        return this;
    }

    /**
     * Права - Добавление
     * Контролеры задачи
     *
     * @return
     */
    public boolean getRightsAddingTaskSupervisors() {
        return rightsAddingTaskSupervisors;
    }

    public TypeListFieldsTable setRightsAddingTaskSupervisors(boolean rightsAddingTaskSupervisors) {
        this.rightsAddingTaskSupervisors = rightsAddingTaskSupervisors;
        return this;
    }

    /**
     * Права - Добавление
     * Ответственные руководители
     *
     * @return
     */
    public boolean getRightsAddingExecutiveManagers() {
        return rightsAddingExecutiveManagers;
    }

    public TypeListFieldsTable setRightsAddingExecutiveManagers(boolean rightsAddingExecutiveManagers) {
        this.rightsAddingExecutiveManagers = rightsAddingExecutiveManagers;
        return this;
    }

    /**
     * Права - Добавление
     * Исполнители
     *
     * @return
     */
    public boolean getRightsAddingPerformers() {
        return rightsAddingPerformers;
    }

    public TypeListFieldsTable setRightsAddingPerformers(boolean rightsAddingPerformers) {
        this.rightsAddingPerformers = rightsAddingPerformers;
        return this;
    }

    /**
     * Права - Редактирование
     * Авторы
     *
     * @return
     */
    public RightsEditingTableEntries getRightsEditAuthors() {
        return rightsEditAuthors;
    }

    public TypeListFieldsTable setRightsEditAuthors(RightsEditingTableEntries rightsEditAuthors) {
        this.rightsEditAuthors = rightsEditAuthors;
        return this;
    }

    /**
     * Права - Редактирование
     * Контролеры задачи
     *
     * @return
     */
    public RightsEditingTableEntries getRightsEditTaskSupervisors() {
        return rightsEditTaskSupervisors;
    }

    public TypeListFieldsTable setRightsEditTaskSupervisors(RightsEditingTableEntries rightsEditTaskSupervisors) {
        this.rightsEditTaskSupervisors = rightsEditTaskSupervisors;
        return this;
    }

    /**
     * Права - Редактирование
     * Ответственные руководители
     *
     * @return
     */
    public RightsEditingTableEntries getRightsEditExecutiveManagers() {
        return rightsEditExecutiveManagers;
    }

    public TypeListFieldsTable setRightsEditExecutiveManagers(RightsEditingTableEntries rightsEditExecutiveManagers) {
        this.rightsEditExecutiveManagers = rightsEditExecutiveManagers;
        return this;
    }

    /**
     * Права - Редактирование
     * Исполнители
     *
     * @return
     */
    public RightsEditingTableEntries getRightsEditPerformers() {
        return rightsEditPerformers;
    }

    public TypeListFieldsTable setRightsEditPerformers(RightsEditingTableEntries rightsEditPerformers) {
        this.rightsEditPerformers = rightsEditPerformers;
        return this;
    }

    /**
     * Права - Удаление
     * Авторы
     *
     * @return
     */
    public RightsEditingTableEntries getRightsDeletionAuthors() {
        return rightsDeletionAuthors;
    }

    public TypeListFieldsTable setRightsDeletionAuthors(RightsEditingTableEntries rightsDeletionAuthors) {
        this.rightsDeletionAuthors = rightsDeletionAuthors;
        return this;
    }

    /**
     * Права - Удаление
     * Контролеры задачи
     *
     * @return
     */
    public RightsEditingTableEntries getRightsDeletionTaskSupervisors() {
        return rightsDeletionTaskSupervisors;
    }

    public TypeListFieldsTable setRightsDeletionTaskSupervisors(RightsEditingTableEntries rightsDeletionTaskSupervisors) {
        this.rightsDeletionTaskSupervisors = rightsDeletionTaskSupervisors;
        return this;
    }

    /**
     * Права - Удаление
     * Ответственные руководители
     *
     * @return
     */
    public RightsEditingTableEntries getRightsDeletionExecutiveManagers() {
        return rightsDeletionExecutiveManagers;
    }

    public TypeListFieldsTable setRightsDeletionExecutiveManagers(RightsEditingTableEntries rightsDeletionExecutiveManagers) {
        this.rightsDeletionExecutiveManagers = rightsDeletionExecutiveManagers;
        return this;
    }

    /**
     * Права - Удаление
     * Исполнители
     *
     * @return
     */
    public RightsEditingTableEntries getRightsDeletionPerformers() {
        return rightsDeletionPerformers;
    }

    public TypeListFieldsTable setRightsDeletionPerformers(RightsEditingTableEntries rightsDeletionPerformers) {
        this.rightsDeletionPerformers = rightsDeletionPerformers;
        return this;
    }


}
