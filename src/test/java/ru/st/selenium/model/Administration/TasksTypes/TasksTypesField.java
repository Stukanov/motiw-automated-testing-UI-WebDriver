package ru.st.selenium.model.Administration.TasksTypes;

import ru.st.selenium.model.Administration.FieldsObject.ParentFieldsObject;

/**
 * Модель объекта системы - поля "Типы задачи"
 */
public class TasksTypesField {
    private String fieldName;
    private String fieldID;
    private ObligatoryFieldTypeTask obligatoryFieldTypeTask;
    private boolean hideInSearch;
    private ParentFieldsObject field;


    /**
     * Название поля
     *
     * @return
     */
    public String getFieldName() {
        return fieldName;
    }

    public TasksTypesField setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    /**
     * Идентификатор поля
     *
     * @return
     */
    public String getFieldID() {
        return fieldID;
    }

    public TasksTypesField setFieldID(String fieldID) {
        this.fieldID = fieldID;
        return this;
    }

    /**
     * Тип поля
     *
     * @return
     */
    public ParentFieldsObject getFieldType() {
        return field;
    }

    public TasksTypesField setFieldType(ParentFieldsObject field) {
        this.field = field;
        return this;
    }

    /**
     * Обязательность поля
     *
     * @return
     */
    public ObligatoryFieldTypeTask getObligatory() {
        return obligatoryFieldTypeTask;
    }

    public TasksTypesField setObligatory(ObligatoryFieldTypeTask obligatoryFieldTypeTask) {
        this.obligatoryFieldTypeTask = obligatoryFieldTypeTask;
        return this;
    }

    /**
     * Скрывать при поиске?
     *
     * @return
     */
    public boolean getIsHideInSearch() {
        return hideInSearch;
    }

    public TasksTypesField setIsHideInSearch(boolean hideInSearch) {
        this.hideInSearch = hideInSearch;
        return this;
    }


}
