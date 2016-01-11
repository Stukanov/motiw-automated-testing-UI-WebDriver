package ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards;


import ru.st.selenium.model.Administration.Directories.Directories;

/**
 * Модель объекта системы - Тип поля документа "Текст"
 */
public class FieldTypeTextDoc extends ParentFieldTypeDoc {

    private String nameDirectory;
    private Directories directories;
    private String directoryTemplate;
    private boolean selectOnlyFromDictionary;


    /**
     * Название спр-ка - в поле типа "Текст"
     *
     */
    public String getDirectoryName() {
        return nameDirectory;
    }

    public FieldTypeTextDoc setDirectoryName(String nameDirectory) {
        this.nameDirectory = nameDirectory;
        return this;
    }

    /**
     * Справочник
     *
     */
    public Directories getDirectoryForFieldText() {
        return directories;
    }

    public FieldTypeTextDoc setDirectoryForFieldText(Directories directories) {
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

    public FieldTypeTextDoc setDirectoryTemplate(String directoryTemplate) {
        this.directoryTemplate = directoryTemplate;
        return this;
    }

    /**
     * Выбор только из справочника
     *
     */
    public boolean getSelectOnlyFromDictionary() {
        return selectOnlyFromDictionary;
    }

    public FieldTypeTextDoc setSelectOnlyFromDictionary(boolean selectOnlyFromDictionary) {
        this.selectOnlyFromDictionary = selectOnlyFromDictionary;
        return this;
    }


}
