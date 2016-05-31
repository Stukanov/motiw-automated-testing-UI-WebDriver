package ru.motiw.web.steps.Administration.Users;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.motiw.web.elements.elementspagesweb.Administration.Users.DepartmentElements;
import ru.motiw.web.elements.elementspagesweb.Administration.Users.UsersElements;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.elements.elementspagesweb.Login.RestorePasswordPage;
import ru.motiw.web.logicinterface.WebLogic.UsersLogic;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Administration.Users.Module;
import ru.motiw.web.model.Administration.Users.Status;

import static com.codeborne.selenide.Condition.present;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertTrue;
import static ru.motiw.utils.ChecksUtil.isElementPresent;

/**
 * Пользователи
 */
public class UsersPageSteps extends DepartmentElements implements UsersLogic {

    private UsersElements usersElements = page(UsersElements.class);

    /**
     * Кнопка - Добавить пользователя
     */
    public UsersPageSteps buttonAddUser() {
        usersElements.getButtonAddUser().click();
        return this;
    }

    /**
     * Общий метод заполнения поля в форме реквизитов пользователя
     *
     * @param field передаваемая поле (елемент) - в форме реквизитов пользователя
     * @param text  передаваемая - текстовое зн-ие для ввода
     * @return UsersPageStep
     */
    public UsersPageSteps setEntryField(SelenideElement field, String text) {
        field.click();
        field.clear();
        field.sendKeys(text);
        return this;
    }

    /**
     * Выбор пола пользователя
     * @param isMan передаваемое зн-ие, если true == Мужчина
     * @return UsersPageSteps
     */
    public UsersPageSteps clickSex(boolean isMan) {
        if (isMan) {
            usersElements.getCheckMan().click();
        } else {
            usersElements.getCheckWoman().click();
        }
        return this;
    }


    /**
     * Имя пользователя
     *
     * @param userName передаваемое Имя пользователя
     */
    public UsersPageSteps setNameUserLogin(String userName) {
        usersElements.getClickNameUserLogin().click();
        usersElements.getVisibleEditor().clear();
        usersElements.getVisibleEditor().sendKeys(userName);
        return this;
    }

    /**
     * Пароль
     *
     * @param passwordUser передаваемый - пароль - пользователя
     * @return CreateUserPage
     */
    public UsersPageSteps setPasswordUser(String passwordUser) {
        usersElements.getPasswordUser().click();
        usersElements.getVisibleEditor().clear();
        usersElements.getVisibleEditor().sendKeys(passwordUser);
        String confPass = Keys.chord(Keys.ENTER);
        usersElements.getVisibleEditor().sendKeys(confPass);
        return this;
    }

    /**
     * Подтверждение пароля
     *
     * @param confirmationPassword передаваемое значение - подтверждение пароля - пользователя
     * @return CreateUserPage
     */
    public UsersPageSteps setConfirmationPassword(String confirmationPassword) {
        usersElements.getСonfirmationPassword().click();
        usersElements.getVisibleEditor().clear();
        usersElements.getVisibleEditor().sendKeys(confirmationPassword);
        return this;
    }

    /**
     * Статус - Начальник
     *
     * @return CreateUserPage
     */
    public UsersPageSteps setStatus(Status status) {
        usersElements.getStatus().click();
        if (status == Status.BOSS) {
            usersElements.getVisibleEditor().sendKeys(Keys.chord(Keys
                    .ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));
        } else if (status == Status.CLERK) {
            usersElements.getVisibleEditor().sendKeys(Keys.chord(Keys
                    .ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));
        }
        return this;
    }

    /**
     * Заполнение строковых полей в форме реквизитов пользователя
     *
     * @param clickField локатор элемента поля - выбор поля
     * @param field      действия производимые над выбранным полем (очистить, заполнить зн-ие)
     * @param valueField
     */
    public void rangeOfFieldsAndFillingInDetails(SelenideElement clickField, SelenideElement field, String valueField) {
        clickField.click();
        field.clear();
        field.sendKeys(valueField);
    }

    /**
     * Сменить пароль при следующем входе
     *
     * @return CreateUserPage
     */
    public UsersPageSteps clickNeedPasswordChange(boolean needsPassChange) {
        if (needsPassChange) {
            usersElements.getNeedPasswordChange().click();
        }
        return this;
    }

    /**
     * Нажатие кнопки - Сохранить пользователя
     *
     * @return CreateUserPage
     */
    public UsersPageSteps saveUser() {
        usersElements.getSave().click();
        waitForMask();
        return this;
    }

    /**
     * Удалить пользователя
     *
     * @return UsersPageSteps
     */
    public UsersPageSteps removeUser() {
        usersElements.getRemoveUser().click();
        return this;
    }

    /**
     * Проверка создания пользователей:
     */
    public UsersPageSteps verifyCreateUser(Employee user) {
        $(By.xpath("//tbody[contains(@id,'gridview')]/tr//a[text()='"
                + user.getLastName() + " " + user.getName() + " "
                + user.getPatronymic() + "']")).waitUntil(present, 15000);
        return this;
    }

    /**
     * Выбор пользователя в гриде
     *
     * @param user передаваемые реквизиты пользователя
     * @return UsersPageSteps
     */
    public UsersPageSteps clickUserByName(Employee user) {
        $(By.xpath("//a[contains (text(), '" + user.getLastName() + "')]")).shouldBe(visible);
        $(By.xpath("//a[contains (text(), '" + user.getLastName()
                + "')]/parent::div/parent::td/preceding-sibling::td"))
                .click();
        return this;
    }

    /**
     * Выбор пользователя (открытие формы редактирования пользователя) в гриде
     *
     * @param user передаваемые реквизиты пользователя
     * @return UsersPageSteps
     */
    public UsersPageSteps clickEditUserFormByName(Employee user) {
        $(By
                .xpath("//a[contains (text(), '" + user.getLastName() + "')]")).shouldBe(visible);
        $(By.xpath("//a[contains(@onclick,'openEditUserForm')][contains(text(),'" + user.getLastName() + "')]"))
                .click();
        return this;
    }

    /**
     * Создать псевдоним
     */
    public UsersPageSteps createAnAlias() {
        usersElements.getCreateAnalias().click();
        return this;

    }

    /**
     * В окне добавления псевдонима в подразделение ищем нужный департамент
     */
    public UsersPageSteps searchDepartmentForAlias(Department department) {
        getFrameAlias();
        $(usersElements.getFieldSearchDepForAlias()).shouldBe(visible);
        usersElements.getFieldSearchDepForAlias().click();
        usersElements.getFieldSearchDepForAlias().sendKeys(department.getDepartmentName());
        usersElements.getButtonSearchDepForAlias().click();
        return this;
    }

    /**
     * Кликаем чекбокс найденного департамента
     */
    public UsersPageSteps clickFoundDep() {
        usersElements.getSelectedCheckBox().click();
        return this;
    }

    /**
     * Кликаем кнопку сохранить в окне добавления псевдонима
     */
    public UsersPageSteps clickButtonSaveAlias() {
        usersElements.getButtonSaveAlias().click();
        return this;
    }

    /**
     * Проверка наличия на странице псевдонима для подразделения
     */
    public UsersPageSteps assertHasAlias(Employee user, Department department) {
        assertTrue(isElementPresent(By.xpath("/*//*[contains (text(), '" + user.getLastName() + " " + user.getName() + " "
                + user.getPatronymic() + " (для задач по " + department.getDepartmentName() + ")')]")));
        return this;
    }

    /**
     * Метод устновки прав на модуль
     *
     * @param module параметр установки прав на определенные модули системы
     */
    public UsersPageSteps selModule(Module module) {
        if (module == Module.DOCFLOW) {
            usersElements.getWorkflow().click();
        }
        if (module == Module.WORKFLOW) {
            usersElements.getDocflow().click();
        }
        return this;
    }


    /**
     * Проверяем и ожидаем, что на странице имеется соответствующий элемент:
     * -Кнопка - Добавить пользователя; -Кнопка - Удалить пользователя.
     */
    public UsersPageSteps ensurePageLoaded() {
        $(By.xpath("(//a[contains(@id,'button-')])[4]")).shouldBe(present);
        $(By.xpath("(//a[contains(@id,'button-')])[5]")).shouldBe(present);
        return this;
    }


    /**
     * Инициализация стр. - Проверка смены пароля пользователя
     */
    public RestorePasswordPage initializationRestorePasswordPage() {
        return page(RestorePasswordPage.class);
    }

    /**
     * Метод помогающий подготовить интерфейс к добавлению пользователей
     * (Проверка загрузки страницы, раскрытие всех элементов дерева подразделений)
     */
    @Override
    public void beforeAdd() {
        ensurePageLoaded()
                .unwrapAllNodes(); // Раскрытие дочерних подразделений
    }

    /**
     * Метод удаления пользователей
     */
    @Override
    public void deleteUser(Employee user) {
        getFrameTop();
        initializationInternalPage().searchFacilityOnTheGrid(user.getLastName());
        goToFremDepartmentPage().waitForMask();
        clickUserByName(user); // выбираем пользователя в гриде
        removeUser(); // Нажимаем удалить пользователя
        confirmDeletionUser(user);
    }

    /**
     * Подтверждение удаления пользователя
     *
     * @param user
     */
    public DepartmentElements confirmDeletionUser(Employee user) {
        checkingMessagesConfirmationOfTheObject(getExpectedMessageTextToDialog,
                "Вы уверены, что хотите удалить пользователя " + user.getLastName() + " "
                        + user.getName() + " " + user.getPatronymic() + "" + "?", oKRemoveDelete);
        waitForMask();
        $(By.xpath("//tbody[contains(@id,'gridview')]/tr//a[text()='"
                + user.getLastName() + " " + user.getName() + " "
                + user.getPatronymic() + "']")).shouldNotBe(visible);
        return this;
    }

    /**
     * Инициализация класса - Внутреняя страница
     */
    public InternalPage initializationInternalPage() {
        return page(InternalPage.class);
    }

    /**
     * Создание псевдонима пользователя, методу передается пользователь,
     * которому будет создан псевдоним и подразделение в котором будет создан псевдоним
     */
    @Override
    public void createAndCheckAliasForDep(Employee user, Department department) {
        getFrameTop();
        initializationInternalPage().searchFacilityOnTheGrid(user.getLastName());
        goToFremDepartmentPage();
        waitForMask();
        clickUserByName(user)
                .createAnAlias()
                .searchDepartmentForAlias(department)
                .clickFoundDep()
                .clickButtonSaveAlias();
        checkIsAlias(user, department);
    }

    /**
     * Проверяем наличие псевдонима в Подразделении. Методу передается
     * пользователь оригинал, и подразделение для которого нужно проверить наличие псевдонима
     * данного пользователя
     *
     * @param user       атрибуты пользователя
     * @param department атрибуты подразделения
     */
    public void checkIsAlias(Employee user, Department department) {
        getFrameTop();
        initializationInternalPage().searchFacilityOnTheGrid(user.getLastName());
        goToFremDepartmentPage();
        waitForMask();
        assertHasAlias(user, department);
    }

    /**
     * Метод Добавления пользователей. В качестве параметра передается объект
     * создаваемого пользователя и подразделение, в которое он будет помещен
     *
     * @param user атрибуты пользователя
     */
    @Override
    public void createUser(Employee user) {
        selectTheParentUnit(user.getDepartment()); // Выбираем подразделение
        buttonAddUser(). // Добавить пользователя
                getFrameEditUser(); // Переходим в iframe формы добавления пользователя
        setEntryField(usersElements.getLastName(), user.getLastName()) // Фамилия
                .setEntryField(usersElements.getName(), user.getName()) // Имя
                .setEntryField(usersElements.getPatronymic(), user.getPatronymic()) // Отчество
                .clickSex(user.getIsMan())
                .setEntryField(usersElements.getBirthDateField(), user.getBirthDate()) // Дата рождения
                .setEntryField(usersElements.getJobTitle(), user.getJobTitle()) // Должность
                .setNameUserLogin(user.getLoginName())
                .setPasswordUser(user.getPassword())
                .setConfirmationPassword(user.getСonfirmationPassword())
                .setStatus(user.getStatus())
                .selModule(user.getModule());
        rangeOfFieldsAndFillingInDetails(usersElements.getAdditionalNumber(), usersElements.getVisibleEditor(),
                user.getAdditionalNumber()); // Доп. номер
        rangeOfFieldsAndFillingInDetails(usersElements.getUserForcedSorting(), usersElements.getVisibleEditor(),
                user.getUserForcedSorting()); // Порядок пользователя при принудительной сортировке
        clickNeedPasswordChange(user.getNeedsPasswordChange())
                .saveUser()
                .getFrameTop();
        getFrameFlow();
        ensurePageLoaded().verifyCreateUser(user);
    }

    /**
     * Метод Редактирования пользователей. В качестве параметра передается объект
     * созданного пользователя и его подразделение, в котором он был создан
     *
     * @param user     атрибуты пользователя
     * @param editUser атрибуты нового пользователя
     */
    @Override
    public void editUser(Employee user, Employee editUser) {
        selectTheParentUnit(user.getDepartment());
        clickEditUserFormByName(user); // выбираем пользователя в гриде для редактирования
        getFrameEditUser(); // Переходим в iframe формы добавления пользователя

        setEntryField(usersElements.getLastName(), editUser.getLastName()) // Фамилия
                .setEntryField(usersElements.getName(), editUser.getName()) // Имя
                .setEntryField(usersElements.getPatronymic(), editUser.getPatronymic()) // Отчество
                .clickSex(editUser.getIsMan())
                .setEntryField(usersElements.getBirthDateField(), editUser.getBirthDate()) // Дата рождения
                .setEntryField(usersElements.getJobTitle(), editUser.getJobTitle()) // Должность
                .setNameUserLogin(editUser.getLoginName())
                .setPasswordUser(editUser.getPassword())
                .setConfirmationPassword(editUser.getСonfirmationPassword())
                .setStatus(editUser.getStatus())
                .selModule(editUser.getModule());
        rangeOfFieldsAndFillingInDetails(usersElements.getAdditionalNumber(), usersElements.getVisibleEditor(),
                editUser.getAdditionalNumber()); // Доп. номер
        rangeOfFieldsAndFillingInDetails(usersElements.getUserForcedSorting(), usersElements.getVisibleEditor(),
                editUser.getUserForcedSorting()); // Порядок пользователя при принудительной сортировке
        clickNeedPasswordChange(editUser.getNeedsPasswordChange())
                .saveUser()
                .getFrameTop();
        getFrameFlow();
        ensurePageLoaded().verifyCreateUser(editUser);
    }
}
