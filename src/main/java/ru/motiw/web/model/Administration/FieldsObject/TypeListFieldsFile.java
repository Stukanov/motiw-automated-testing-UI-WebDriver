package ru.motiw.web.model.Administration.FieldsObject;


import ru.motiw.web.model.OpenFilesForEdit;

/**
 * Тип поля документа - Файл
 */
public class TypeListFieldsFile extends ParentFieldsObject {

    private OpenFilesForEdit openFilesForEdit;

    /**
     * Открывать файлы для редактирования
     *
     * @return
     */
    public OpenFilesForEdit getOpenFilesForEdit() {
        return openFilesForEdit;
    }

    public TypeListFieldsFile setOpenFilesForEdit(OpenFilesForEdit openFilesForEdit) {
        this.openFilesForEdit = openFilesForEdit;
        return this;
    }


}
