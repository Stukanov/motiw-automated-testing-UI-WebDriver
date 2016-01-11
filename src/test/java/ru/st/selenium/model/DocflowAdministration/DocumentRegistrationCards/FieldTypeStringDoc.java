package ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards;


import ru.st.selenium.model.Administration.Directories.Directories;

/**
 * Модель объекта системы - Тип поля документа "Строка"
 */
public class FieldTypeStringDoc extends ParentFieldTypeDoc {

    private String nameDirectory;
    private Directories directories;
    private String directoryTemplate;
    private boolean selectOnlyFromDictionary;
    private String fieldLength;


    /**
     * Название спр-ка - в поле типа "Строка"
     *
     */
    public String getDirectoryName() {
        return nameDirectory;
    }

    public FieldTypeStringDoc setDirectoryName(String nameDirectory) {
        this.nameDirectory = nameDirectory;
        return this;
    }

    /**
     * Справочник
     *
     */
    public Directories getDirectoryForFieldString() {
        return directories;
    }

    public FieldTypeStringDoc setDirectoryForFieldString(Directories directories) {
        this.directories = directories;
        return this;
    }

    /**
     * Шаблон справочника
     *
     */
    public String getDirectoryTemplate() {
        return directoryTemplate;
    }

    public FieldTypeStringDoc setDirectoryTemplate(String directoryTemplate) {
        this.directoryTemplate = directoryTemplate;
        return this;
    }

    /**
     * Выбор только из справочника; true == Нет; false == Да
     *
     */
    public boolean getSelectOnlyFromDictionary() {
        return selectOnlyFromDictionary;
    }

    public FieldTypeStringDoc setSelectOnlyFromDictionary(boolean selectOnlyFromDictionary) {
        this.selectOnlyFromDictionary = selectOnlyFromDictionary;
        return this;
    }

    /**
     * Длина поля
     *
     */
    public String getFieldLength() {
        return fieldLength;
    }

    public FieldTypeStringDoc setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
        return this;
    }


}
