package ru.motiw.web.model.Administration.FieldsObject;


import ru.motiw.web.model.Administration.Directories.DirectoriesField;
import ru.motiw.web.model.Administration.Directories.Directories;

/**
 * Модель объекта системы - Тип поля "Множественная ссылка на справочник"
 */
public class TypeListFieldsMultiDirectory extends ParentFieldsObject {

    private String nameDirectory;
    private String nameDirectoryField;
    private Directories directories;
    private DirectoriesField field;
    private DirectoriesField linkedWithField;
    private String displayNameTemplate;


    /**
     * Название спр-ка
     *
     * @return
     */
    public String getDirectoryName() {
        return nameDirectory;
    }

    public TypeListFieldsMultiDirectory setDirectoryName(String nameDirectory) {
        this.nameDirectory = nameDirectory;
        return this;
    }

    /**
     * Название поля спр-ка
     *
     * @return
     */
    public String getNameDirectoryField() {
        return nameDirectoryField;
    }

    public TypeListFieldsMultiDirectory setNameDirectoryField(String nameDirectoryField) {
        this.nameDirectoryField = nameDirectoryField;
        return this;
    }

    /**
     * Справочник
     *
     * @return
     */
    public Directories getDirectories() {
        return directories;
    }

    public TypeListFieldsMultiDirectory setDirectories(Directories directories) {
        this.directories = directories;
        return this;
    }

    /**
     * Поле спр-ка
     *
     * @return
     */
    public DirectoriesField getFieldDirectory() {
        return field;
    }

    public TypeListFieldsMultiDirectory setFieldDirectory(DirectoriesField field) {
        this.field = field;
        return this;
    }

    /**
     * Связан с полем
     *
     * @return
     */
    public DirectoriesField getLinkedWithField() {
        return linkedWithField;
    }

    public TypeListFieldsMultiDirectory setLinkedWithField(DirectoriesField linkedWithField) {
        this.linkedWithField = linkedWithField;
        return this;
    }

    /**
     * Шаблон отображения
     *
     * @return
     */
    public String getDisplayNameTemplate() {
        return displayNameTemplate;
    }

    public TypeListFieldsMultiDirectory setDisplayNameTemplate(String displayNameTemplate) {
        this.displayNameTemplate = displayNameTemplate;
        return this;
    }


}
