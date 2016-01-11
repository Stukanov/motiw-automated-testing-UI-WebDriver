package ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Модель объекта системы - Тип поля документа "Дата"
 */
public class FieldTypeDateDoc extends ParentFieldTypeDoc {

    private boolean defaultValue;
    private boolean editionAvailableWhileCreation;


    /**
     * Значение по умолчанию
     *
     * @return FieldTypeDateDoc
     */
    public boolean getDefaultValue() {
        return defaultValue;
    }

    public FieldTypeDateDoc setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Изменяемое при создании
     *
     * @return FieldTypeDateDoc
     */
    public boolean getEditionAvailableWhileCreation() {
        return editionAvailableWhileCreation;
    }

    public FieldTypeDateDoc setEditionAvailableWhileCreation(boolean editionAvailableWhileCreation) {
        this.editionAvailableWhileCreation = editionAvailableWhileCreation;
        return this;
    }


}
