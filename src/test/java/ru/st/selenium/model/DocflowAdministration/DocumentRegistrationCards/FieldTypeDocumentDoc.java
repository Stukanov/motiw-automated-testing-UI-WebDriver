package ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Модель объекта системы - Тип поля документа "Документ"
 */
public class FieldTypeDocumentDoc extends ParentFieldTypeDoc {

    private String displayNameTemplate;
    private boolean searchSimiliarDocuments;
    private String searchRules;


    /**
     * Шаблон отображения
     *
     * @return
     */
    public String getDisplayNameTemplate() {
        return displayNameTemplate;
    }

    public FieldTypeDocumentDoc setDisplayNameTemplate(String displayNameTemplate) {
        this.displayNameTemplate = displayNameTemplate;
        return this;
    }

    /**
     * Искать похожие документы
     *
     * @return
     */
    public boolean getSearchSimiliarDocuments() {
        return searchSimiliarDocuments;
    }

    public FieldTypeDocumentDoc setSearchSimiliarDocuments(boolean searchSimiliarDocuments) {
        this.searchSimiliarDocuments = searchSimiliarDocuments;
        return this;
    }

    /**
     * Правила поиска
     *
     * @return
     */
    public String getSearchRules() {
        return searchRules;
    }

    public FieldTypeDocumentDoc setSearchRules(String searchRules) {
        this.searchRules = searchRules;
        return this;
    }


}
