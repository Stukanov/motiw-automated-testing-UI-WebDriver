package ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards;


import ru.st.selenium.model.Administration.Directories.Directories;

/**
 * Модель объекта системы - Тип поля документа "Справочник"
 */
public class FieldTypeDirectoryDoc extends ParentFieldTypeDoc {

    private String nameDirectory;
    private Directories directories;
    private String directoryTemplate;
    private boolean directoryEntriesSelection;


    /**
     * Название спр-ка
     *
     * @return
     */
    public String getNameDirectoryDoc() {
        return nameDirectory;
    }

    public FieldTypeDirectoryDoc setNameDirectoryDoc(String nameDirectory) {
        this.nameDirectory = nameDirectory;
        return this;
    }

    /**
     * Справочник
     *
     * @return
     */
    public Directories getDirectoryDoc() {
        return directories;
    }

    public FieldTypeDirectoryDoc setDirectoryDoc(Directories directories) {
        this.directories = directories;
        return this;
    }

    /**
     * Шаблон справочника
     *
     * @return FieldTypeStringDoc
     */
    public String getDirectoryTemplate() {
        return directoryTemplate;
    }

    public FieldTypeDirectoryDoc setDirectoryTemplate(String directoryTemplate) {
        this.directoryTemplate = directoryTemplate;
        return this;
    }

    /**
     * Выбор записей спр-ка; true == Одна запись; false == Несколько записей
     *
     * @return
     */
    public boolean getDirectoryEntriesSelection() {
        return directoryEntriesSelection;
    }

    public FieldTypeDirectoryDoc setDirectoryEntriesSelection(boolean directoryEntriesSelection) {
        this.directoryEntriesSelection = directoryEntriesSelection;
        return this;
    }


}
