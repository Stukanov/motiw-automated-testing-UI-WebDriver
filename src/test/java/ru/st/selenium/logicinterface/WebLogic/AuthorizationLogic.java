package ru.st.selenium.logicinterface.WebLogic;

import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.pageselementsweb.Login.LoginPage;

/**
 * Основные операции в Системе
 */
public interface AuthorizationLogic {

    LoginPage loginAs(Employee user);

    boolean isLoggedIn();

    boolean isNotLoggedIn();

    boolean newUserIsLoggedInAs(Employee user);

    boolean checkTheSystemFolderMappingUserLibrary(Employee user);

    boolean hasMenuUserComplete();


}
