package ru.st.selenium.model.Administration.FieldsObject;


import ru.st.selenium.model.Administration.TasksTypes.ComputeModeNumerator;

/**
 * Модель объекта системы - Тип поля "Нумератор"
 */
public class TypeListFieldsNumerator extends ParentFieldsObject {

    private String numeratorTemplate;
    private ComputeModeNumerator computeMode;


    /**
     * Шаблон нумератора
     *
     * @return
     */
    public String getNumeratorTemplate() {
        return numeratorTemplate;
    }

    public TypeListFieldsNumerator setNumeratorTemplate(
            String numeratorTemplate) {
        this.numeratorTemplate = numeratorTemplate;
        return this;
    }

    /**
     * Режим вычисления
     *
     * @return
     */
    public ComputeModeNumerator getComputeMode() {
        return computeMode;
    }

    public TypeListFieldsNumerator setComputeMode(
            ComputeModeNumerator computeMode) {
        this.computeMode = computeMode;
        return this;
    }


}
