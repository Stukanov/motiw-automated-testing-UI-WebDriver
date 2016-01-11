package ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Модель объекта системы - Тип поля документа "Сотрудник"
 */
public class FieldTypeEmployeeDoc extends ParentFieldTypeDoc {

    private boolean defaultValue;
    private boolean editionAvailableWhileCreation;
    private boolean documentSuperviser;
    private boolean forInformation;


    /**
     * Значение по умолчанию; true == Текущий пользователь; false == Нет
     *
     */
    public boolean getDefaultValue() {
        return defaultValue;
    }

    public FieldTypeEmployeeDoc setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Изменяемое при создании
     *
     */
    public boolean getEditionAvailableWhileCreation() {
        return editionAvailableWhileCreation;
    }

    public FieldTypeEmployeeDoc setEditionAvailableWhileCreation(boolean editionAvailableWhileCreation) {
        this.editionAvailableWhileCreation = editionAvailableWhileCreation;
        return this;
    }

    /**
     * Контролер документа
     *
     */
    public boolean getDocumentSuperviser() {
        return documentSuperviser;
    }

    public FieldTypeEmployeeDoc setDocumentSuperviser(boolean documentSuperviser) {
        this.documentSuperviser = documentSuperviser;
        return this;
    }

    /**
     * Для сведения
     *
     */
    public boolean getForInformation() {
        return forInformation;
    }

    public FieldTypeEmployeeDoc setForInformation(boolean forInformation) {
        this.forInformation = forInformation;
        return this;
    }


}
