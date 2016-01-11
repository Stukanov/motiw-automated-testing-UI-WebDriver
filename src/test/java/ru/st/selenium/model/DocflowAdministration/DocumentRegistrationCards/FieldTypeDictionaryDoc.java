package ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards;


import ru.st.selenium.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;

/**
 * Модель объекта системы - Тип поля документа "Словарь"
 */
public class FieldTypeDictionaryDoc extends ParentFieldTypeDoc {

    private DictionaryEditor dictionaryEditor;


    /**
     * Словарь
     *
     * @return
     */
    public DictionaryEditor getDictionaryEditor() {
        return dictionaryEditor;
    }

    public FieldTypeDictionaryDoc setDictionaryEditor(DictionaryEditor dictionaryEditor) {
        this.dictionaryEditor = dictionaryEditor;
        return this;
    }

}
