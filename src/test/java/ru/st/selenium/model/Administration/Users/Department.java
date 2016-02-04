package ru.st.selenium.model.Administration.Users;

/**
 * Модель объекта системы - Подразделение
 */
public class Department {

    private Department parent;
    private String depname;
    private String conditionalnumber;
    private String counter;
    private String resetdate;
    private String numeratortemplate;

    /*
     * Наименование родительского подразделения
     */
    public Department getParentDepartment() {
        return parent;
    }

    public Department setParentDepartment(Department parent) {
        this.parent = parent;
        return this;
    }

    /*
     * Название подразделения
     */
    public String getDepName() {
        return depname;
    }

    public Department setDepName(String depname) {
        this.depname = depname;
        return this;
    }

    /*
     * Условный номер
     */
    public String getConditionalNumber() {
        return conditionalnumber;
    }

    public Department setConditionalNumber(String conditionalnumber) {
        this.conditionalnumber = conditionalnumber;
        return this;
    }

    /*
     * Счетчик
     */
    public String getCounter() {
        return counter;
    }

    public Department setCounter(String counter) {
        this.counter = counter;
        return this;
    }

    /*
     * Дата сброса счетчика
     */
    public String getResetDate() {
        return resetdate;
    }

    public Department setResetDate(String resetdate) {
        this.resetdate = resetdate;
        return this;
    }

    /*
     * Шаблон нумератора
     */
    public String getNumeratorTemplate() {
        return numeratortemplate;
    }

    public Department setNumeratorTemplate(String numeratortemplate) {
        this.numeratortemplate = numeratortemplate;
        return this;
    }


}
