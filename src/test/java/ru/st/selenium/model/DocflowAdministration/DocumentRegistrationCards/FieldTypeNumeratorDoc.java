package ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Модель объекта системы - Тип поля документа "Нумератор"
 */
public class FieldTypeNumeratorDoc extends ParentFieldTypeDoc {

    private String numeratorTemplateDoc;
    private boolean editionAvailableWhileCreation;

    /**
     * Поле - Шаблон нумератора документа
     *
     */
    public String getNumeratorTemplateDoc() {
        return numeratorTemplateDoc;
    }

    public FieldTypeNumeratorDoc setNumeratorTemplateDoc(
            String numeratorTemplateDoc) {
        this.numeratorTemplateDoc = numeratorTemplateDoc;
        return this;
    }

    /**
     * Изменяемое при создании
     *
     */
    public boolean getEditionAvailableWhileCreation() {
        return editionAvailableWhileCreation;
    }

    public FieldTypeNumeratorDoc setEditionAvailableWhileCreation(boolean editionAvailableWhileCreation) {
        this.editionAvailableWhileCreation = editionAvailableWhileCreation;
        return this;
    }

}
