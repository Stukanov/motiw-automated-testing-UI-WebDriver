package ru.st.selenium.logicinterface.WebLogic;

import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;

/**
 * РКД
 */
public interface DocRegisterCardsLogic {

    void addDocRegisterCards();

    void editDocRegisterCards(DocRegisterCards directories);

    void removeAnDocRegisterCards(DocRegisterCards directories);

}
