package ru.motiw.web.model.Administration.FieldsObject;


import ru.motiw.web.model.Administration.Directories.DirectoriesField;
import ru.motiw.web.model.Administration.Directories.Directories;

/**
 * Модель объекта системы - Тип поля "Ссылка на cправочник"
 */
public class TypeListFieldsDirectory extends ParentFieldsObject {

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

    public TypeListFieldsDirectory setDirectoryName(String nameDirectory) {
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

    public TypeListFieldsDirectory setNameDirectoryField(String nameDirectoryField) {
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

    public TypeListFieldsDirectory setDirectories(Directories directories) {
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

    public TypeListFieldsDirectory setFieldDirectory(DirectoriesField field) {
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

    public TypeListFieldsDirectory setLinkedWithField(DirectoriesField linkedWithField) {
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

    public TypeListFieldsDirectory setDisplayNameTemplate(String displayNameTemplate) {
        this.displayNameTemplate = displayNameTemplate;
        return this;
    }



}
