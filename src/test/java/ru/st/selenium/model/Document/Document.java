package ru.st.selenium.model.Document;

import ru.st.selenium.model.DocflowAdministration.DictionaryEditor.DictionaryEditorField;
import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCardsField;
import ru.st.selenium.model.Tasks.Project;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;

/**
 * Модель объекта - Создать документа (форма создания документа)
 */
public class Document {


    private String nameDocumentType;
    private DocRegisterCards documentType;
    private DocRegisterCardsField[] docFields;
    private Employee documentAuthor;
    private String dateRegistration;
    private String dateOfTerm;
    private Project project;
    private Department[] valueDepartment;
    private Employee[] valueEmployee;
    private String valueField;
    private DictionaryEditorField valueDictionaryEditor;
    private String routeScheme;


    /**
     * Название Типа Документа
     *
     */
    public String getNameDocumentType() {
        return nameDocumentType;
    }

    public Document setNameDocumentType(String nameDocumentType) {
        this.nameDocumentType = nameDocumentType;
        return this;
    }

    /**
     * Тип Документа
     *
     */
    public DocRegisterCards getDocumentType() {
        return documentType;
    }

    public Document setDocumentType(DocRegisterCards documentType) {
        this.documentType = documentType;
        return this;
    }

    /**
     * Системное поле - Автор
     */
    public Employee getDocumentAuthor() {
        return documentAuthor;
    }

    public Document setDocumentAuthor(Employee documentAuthor) {
        this.documentAuthor = documentAuthor;
        return this;
    }

    /**
     * Системное поле - Дата регистрации
     */
    public String getDateRegistration() {
        return dateRegistration;
    }

    public Document setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
        return this;
    }

    /**
     * Системное поле - Срок исполнения
     */
    public String getDateOfTerm() {
        return dateOfTerm;
    }

    public Document setDateOfTerm(String dateOfTerm) {
        this.dateOfTerm = dateOfTerm;
        return this;
    }

    /**
     * Проект документа
     *
     */
    public Project getProject() {
        return project;
    }

    public Document setProject(Project project) {
        this.project = project;
        return this;
    }

    /**
     * Поля Типа документа
     *
     * @return
     */
    public DocRegisterCardsField[] getDocumentFields() {
        return docFields;
    }

    public Document setDocumentFields(DocRegisterCardsField[] docFields) {
        this.docFields = docFields;
        return this;
    }

    /**
     * Значение для поля типа - "Словарь"
     *
     */
    public DictionaryEditorField getValueDictionaryEditor() {
        return valueDictionaryEditor;
    }

    public Document setValueDictionaryEditor(DictionaryEditorField valueDictionaryEditor) {
        this.valueDictionaryEditor = valueDictionaryEditor;
        return this;
    }

    /**
     * Значение для поля типа - "Подразделение"
     *
     */
    public Department[] getValueDepartment() {
        return valueDepartment;
    }

    public Document setValueDepartment(Department[] valueDepartment) {
        this.valueDepartment = valueDepartment;
        return this;
    }

    /**
     * Значение для поля типа - "Сотрудник"
     *
     */
    public Employee[] getValueEmployee() {
        return valueEmployee;
    }

    public Document setValueEmployee(Employee[] valueEmployee) {
        this.valueEmployee = valueEmployee;
        return this;
    }

    /**
     * Значения поля документа
     *
     */
    public String getValueField() {
        return valueField;
    }

    public Document setValueField(String valueField) {
        this.valueField = valueField;
        return this;
    }

    /**
     * Маршрутная схема
     *
     */
    public String getRouteScheme() {
        return routeScheme;
    }

    public Document setRouteScheme(String routeScheme) {
        this.routeScheme = routeScheme;
        return this;
    }

}
