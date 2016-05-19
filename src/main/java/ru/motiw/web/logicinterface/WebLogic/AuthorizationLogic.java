package ru.motiw.web.logicinterface.WebLogic;

import ru.motiw.web.elements.elementsweb.Login.LoginPage;
import ru.motiw.web.model.Administration.Users.Employee;

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
