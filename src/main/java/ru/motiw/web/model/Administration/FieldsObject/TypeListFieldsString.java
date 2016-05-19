package ru.motiw.web.model.Administration.FieldsObject;

/**
 * Модель объекта системы - Тип поля "Строка"
 */
public class TypeListFieldsString extends ParentFieldsObject {

    private boolean listChoice;
    private String valuesList;


    /**
     * Нужен ли выбор из списка
     *
     * @return
     */

    public boolean getIsListChoice() {
        return listChoice;
    }

    public TypeListFieldsString setIsListChoice(boolean listChoice) {
        this.listChoice = listChoice;
        return this;
    }

    /**
     * Поле ввода значений для выбора из списка (Список значений)
     *
     * @return
     */
    public String getValuesList() {
        return valuesList;
    }

    public TypeListFieldsString setValuesList(String valuesList) {
        this.valuesList = valuesList;
        return this;
    }

}

