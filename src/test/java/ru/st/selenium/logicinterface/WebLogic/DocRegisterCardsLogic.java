package ru.st.selenium.logicinterface.WebLogic;

import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.st.selenium.pages.pagesweb.DocflowAdministration.FormDocRegisterCardsEditPage;

/**
 * РКД
 */
public interface DocRegisterCardsLogic {

    void addDocRegisterCards();

    void editDocRegisterCards(DocRegisterCards directories);

    void removeAnDocRegisterCards(DocRegisterCards directories);

}
