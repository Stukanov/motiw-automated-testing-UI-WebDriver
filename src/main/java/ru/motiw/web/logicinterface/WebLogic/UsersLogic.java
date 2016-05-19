package ru.motiw.web.logicinterface.WebLogic;

import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;

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
