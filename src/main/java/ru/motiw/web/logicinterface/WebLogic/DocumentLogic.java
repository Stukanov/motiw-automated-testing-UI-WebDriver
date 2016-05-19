package ru.motiw.web.logicinterface.WebLogic;

import ru.motiw.web.model.Document.Document;

/**
 * Создать документ
 */
public interface DocumentLogic {


    void createDocument(Document newDocument);

    void createWithResolution();


}
