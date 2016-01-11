package ru.st.selenium.model.Administration.FieldsObject;

/**
 * Модель объекта системы - Тип поля "Вещественное"
 */
public class TypeListFieldsDouble extends ParentFieldsObject {

    private boolean formatNumber;


    /**
     * Формат; true == Другой формат; false == Значение по умолчанию
     *
     * @return
     */
    public boolean getFormatNumber() {
        return formatNumber;
    }

    public TypeListFieldsDouble setFormatNumber(boolean formatNumber) {
        this.formatNumber = formatNumber;
        return this;
    }


}
