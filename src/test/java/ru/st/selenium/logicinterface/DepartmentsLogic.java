package ru.st.selenium.logicinterface;

import ru.st.selenium.model.Administration.Users.Department;

/**
 * Подразделения
 */
public interface DepartmentsLogic {

    void beforeAdd();

    void createDepartment(Department departments);

    void editDepartments(Department editedDepartment, Department department);

    void deleteDepartment(Department departments);

    void dndSavePermissions(Department source, Department target);


}
