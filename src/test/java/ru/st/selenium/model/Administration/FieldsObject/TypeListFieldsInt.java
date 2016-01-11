package ru.st.selenium.model.Administration.FieldsObject;

/**
 * Модель объекта системы - Тип поля "Целое"
 */

public class TypeListFieldsInt extends ParentFieldsObject {

    private boolean objectLink;
    private boolean formatNumber;


    /**
     * Ссылка на объект; true == Задача; false == Нет
     *
     * @return
     */
    public boolean getObjectLink() {
        return objectLink;
    }

    public TypeListFieldsInt setObjectLink(boolean objectLink) {
        this.objectLink = objectLink;
        return this;
    }

    /**
     * Формат; true == Другой формат; false == Значение по умолчанию
     *
     * @return
     */
    public boolean getFormatNumber() {
        return formatNumber;
    }

    public TypeListFieldsInt setFormatNumber(boolean formatNumber) {
        this.formatNumber = formatNumber;
        return this;
    }

}
