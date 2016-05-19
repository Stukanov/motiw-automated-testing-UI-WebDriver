package ru.motiw.web.logicinterface.WebLogic;

import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;

/**
 * Редактор словарей
 */
public interface DictionaryEditorLogic {

    void addDictionaryEditor(DictionaryEditor directoriesEditor);

    void editDictionaryEditor(DictionaryEditor directoriesEditor);

    void removeAnDictionaryEditor(DictionaryEditor directoriesEditor);
}
