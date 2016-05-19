package ru.motiw.web.model.Administration.Directories;

import ru.motiw.web.model.OpenFilesForEdit;
import ru.motiw.web.model.Administration.FieldsObject.ParentFieldsObject;

/**
 * Модель объекта системы - Поля объекта "Справочник"
 */
public class DirectoriesField {

    private String fieldName;
    private String fieldID;
    private boolean dictionaryObligatory;
    private boolean isUniqueField;
    private ParentFieldsObject field;
    private OpenFilesForEdit openFilesForEdit;


    /**
     * Название поля
     *
     */
    public String getFieldName() {
        return fieldName;
    }

    public DirectoriesField setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    /**
     * Идентификатор поля
     *
     */
    public String getFieldID() {
        return fieldID;
    }

    public DirectoriesField setFieldID(String fieldID) {
        this.fieldID = fieldID;
        return this;
    }

    /**
     * Тип поля
     *
     */
    public ParentFieldsObject getFieldType() {
        return field;
    }

    public DirectoriesField setFieldType(ParentFieldsObject field) {
        this.field = field;
        return this;
    }

    /**
     * Обязательность поля
     */
    public boolean getObligatory() {
        return dictionaryObligatory;
    }

    public DirectoriesField setObligatory(boolean dictionaryObligatory) {
        this.dictionaryObligatory = dictionaryObligatory;
        return this;
    }

    /**
     * Уникальное поле
     *
     */
    public boolean getIsUniqueField() {
        return isUniqueField;
    }

    public DirectoriesField setIsUniqueField(boolean isUniqueField) {

        this.isUniqueField = isUniqueField;
        return this;
    }

    /**
     * Разрешить редактирование файлов для поля типа "Файл"
     *
     */
    public OpenFilesForEdit getOpenFilesForEdit() {
        return openFilesForEdit;
    }

    public DirectoriesField setOpenFilesForEdit(OpenFilesForEdit openFilesForEdit) {
        this.openFilesForEdit = openFilesForEdit;
        return this;
    }


}
