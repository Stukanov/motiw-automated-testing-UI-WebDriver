package ru.st.selenium.logicinterface;

import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;

/**
 * Пользователи
 */
public interface UsersLogic {


    void beforeAdd();

    void deleteUser(Employee user);

    void createAndCheckAliasForDep(Employee user, Department department);

    void createUser(Employee user);

    void editUser(Employee editUser, Employee user);
}
