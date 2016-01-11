package ru.st.selenium.logicinterface;

import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.pagesweb.Login.LoginPage;

/**
 * Основные операции в Системе
 */
public interface AuthorizationLogic {

    LoginPage loginAs(Employee user);

    boolean isLoggedIn();

    boolean isNotLoggedIn();

    boolean isLoggedInAs(Employee user);

    boolean hasMenuUserComplete();


}
