package ru.motiw.web.logicinterface.WebLogic;

import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;

/**
 * РКД
 */
public interface DocRegisterCardsLogic {

    void addDocRegisterCards();

    void editDocRegisterCards(DocRegisterCards directories);

    void removeAnDocRegisterCards(DocRegisterCards directories);

}
