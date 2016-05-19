package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;


import ru.motiw.web.model.Document.Document;

/**
 * Модель объекта системы - Поля Документа
 */
public class DocRegisterCardsField extends Document {

    private String fieldNameDoc;
    private String fieldIDDoc;
    private ObligatoryFieldDocument obligatoryFieldDocument;
    private ParentFieldTypeDoc fieldTypeDoc;
    private boolean editableField;
    private boolean uniqueField;
    private boolean hideForCreationField;
    private boolean hideInTablesField;
    private boolean hideForSearchField;
    private boolean hideInСardField;
    private boolean usedToCreateTheLinkedDocument;

    /**
     * Название поля документа
     *
     * @return DocRegisterCardsField
     */
    public String getFieldNameDoc() {
        return fieldNameDoc;
    }

    public DocRegisterCardsField setFieldNameDoc(String fieldNameDoc) {
        this.fieldNameDoc = fieldNameDoc;
        return this;
    }

    /**
     * Идентификатор поля документа
     *
     * @return DocRegisterCardsField
     */
    public String getFieldIdentifierDoc() {
        return fieldIDDoc;
    }

    public DocRegisterCardsField setFieldIdentifierDoc(String fieldIDDoc) {
        this.fieldIDDoc = fieldIDDoc;
        return this;
    }

    /**
     * Тип поля документа
     *
     * @return DocRegisterCardsField
     */
    public ParentFieldTypeDoc getFieldTypeDoc() {
        return fieldTypeDoc;
    }

    public DocRegisterCardsField setFieldTypeDoc(ParentFieldTypeDoc fieldTypeDoc) {
        this.fieldTypeDoc = fieldTypeDoc;
        return this;
    }

    /**
     * Изменяемое при редактировании
     *
     * @return DocRegisterCardsField
     */
    public boolean getEditableField() {
        return editableField;
    }

    public DocRegisterCardsField setEditableField(boolean editableField) {
        this.editableField = editableField;
        return this;
    }

    /**
     * Обязательность поля документа
     *
     * @return DocRegisterCardsField
     */
    public ObligatoryFieldDocument getObligatoryFieldDoc() {
        return obligatoryFieldDocument;
    }

    public DocRegisterCardsField setObligatoryFieldDoc(ObligatoryFieldDocument obligatoryFieldTypeTask) {
        this.obligatoryFieldDocument = obligatoryFieldTypeTask;
        return this;
    }

    /**
     * Уникальность поля
     *
     * @return DocRegisterCardsField
     */
    public boolean getUniqueField() {
        return uniqueField;
    }

    public DocRegisterCardsField setUniqueField(boolean uniqueField) {
        this.uniqueField = uniqueField;
        return this;
    }

    /**
     * Скрывать при создании
     *
     * @return DocRegisterCardsField
     */
    public boolean getHideForCreationField() {
        return hideForCreationField;
    }

    public DocRegisterCardsField setHideForCreationField(boolean hideForCreationField) {
        this.hideForCreationField = hideForCreationField;
        return this;
    }

    /**
     * Скрывать в таблицах
     *
     * @return DocRegisterCardsField
     */
    public boolean getHideInTablesField() {
        return hideInTablesField;
    }

    public DocRegisterCardsField setHideInTablesField(boolean hideInTablesField) {
        this.hideInTablesField = hideInTablesField;
        return this;
    }


    /**
     * Скрывать при поиске
     *
     * @return DocRegisterCardsField
     */
    public boolean getHideForSearchField() {
        return hideForSearchField;
    }

    public DocRegisterCardsField setHideForSearchField(boolean hideForSearchField) {
        this.hideForSearchField = hideForSearchField;
        return this;
    }

    /**
     * Скрывать в карточке
     *
     * @return DocRegisterCardsField
     */
    public boolean getHideInСardField() {
        return hideInСardField;
    }

    public DocRegisterCardsField setHideInСardField(boolean hideInСardField) {
        this.hideInСardField = hideInСardField;
        return this;
    }

    /**
     * Использовать при создании связанного документа
     *
     * @return DocRegisterCardsField
     */
    public boolean getUsedToCreateTheLinkedDocument() {
        return usedToCreateTheLinkedDocument;
    }

    public DocRegisterCardsField setUsedToCreateTheLinkedDocument(boolean usedToCreateTheLinkedDocument) {
        this.usedToCreateTheLinkedDocument = usedToCreateTheLinkedDocument;
        return this;
    }



}
