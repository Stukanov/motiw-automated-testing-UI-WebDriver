package ru.st.selenium.pages.pagesweb.Administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.WebLogic.UsersLogic;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.Administration.Users.Module;
import ru.st.selenium.model.Administration.Users.Status;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Login.RestorePasswordPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertTrue;
import static ru.st.selenium.model.Administration.Users.Module.*;
import static ru.st.selenium.model.Administration.Users.Status.*;

/**
 * Страница - Администрирование/Пользователи ч.2 (Пользователи)
 */
public class CreateUsersPage extends CreateDepartmentPage implements UsersLogic {


    /**
     * Чекбокс - Workflow
     */
    @FindBy(xpath = "//input[@id='workflow']")
    private SelenideElement workflowCheckBox;

    /**
     * Поле ввода
     */
    @FindBy(xpath = "//*[contains (@class, 'x-editor')][contains (@style, 'visible')]//input")
    private SelenideElement visibleEditor;

    /**
     * Чекбокс - Docflow
     */
    @FindBy(xpath = "//input[@id='docflow']")
    private SelenideElement docflowCheckBox;

    /**
     * Кнопка сохранить окна добавления псевдонима в подразделение
     */
    @FindBy(xpath = "(//*[contains (@class, 'footer')]//button)[1]")
    private SelenideElement buttonSaveAlias;

    /**
     * Кнопка поиска окна добавления псевдонима в подразделение
     */
    @FindBy(xpath = "//*[contains (@class, 'button_s')]")
    private SelenideElement buttonSearchDepForAlias;

    /**
     * Поле поиска окна добавления псевдонима в подразделение
     */
    @FindBy(xpath = "//*[@id='searchField']")
    private SelenideElement fieldSearchDepForAlias;

    /**
     * Чебокс подсвеченного узла окна добавления псевдонима в подразделение
     */
    @FindBy(xpath = "//*[contains (@class, 'selected')][contains (@class, 'tree-node')]/*[@type='checkbox'] ")
    private SelenideElement selectedCheckBox;

    /**
     * Кнопка - Добавить пользователя
     */
    @FindBy(xpath = "(//body[@id='createuser']/div[3]//a[contains(@id,'button-')])[1]")
    private SelenideElement buttonAddUser;

    /**
     * Кнопка - Удалить пользователя
     */
    @FindBy(xpath = "(//body[@id='createuser']/div[3]//a[contains(@id,'button-')])[2]")
    private SelenideElement buttonDelUser;

    /**
     * Кнопка - Создать псевдоним
     */
    @FindBy(xpath = "(//body[@id='createuser']/div[3]//a[contains(@id,'button-')])[4]")
    private SelenideElement buttonCreateAlias;

    /**
     * Фамилия
     */
    @FindBy(xpath = "//tr/td[2]/input")
    private SelenideElement lastNameField;

    /**
     * Имя
     */
    @FindBy(xpath = "//tr/td[3]/input")
    private SelenideElement nameField;

    /**
     * Отчество
     */
    @FindBy(xpath = "//tr/td[4]/input")
    private SelenideElement patronymicField;

    /**
     * Женщина
     */
    @FindBy(xpath = "//input[@type='radio' and @name='sex']/.. /../div[2]/input")
    private SelenideElement clickRadioButtonWoman;

    /**
     * Мужчина
     */
    @FindBy(xpath = "(//input[@type='radio' and @name='sex']/.. /../div/input)[1]")
    private SelenideElement clickRadioButtonMan;

    /**
     * Дата рождения поле
     */
    @FindBy(xpath = "//*[contains (@class, 'date')]/../input")
    private SelenideElement birthDateField;

    /**
     * Дата рождения
     */
    @FindBy(xpath = "//img[contains(@class,'x-form-trigger x-form-date-trigger')]")
    private SelenideElement clickDate;

    @FindBy(xpath = "//table[@class='x-date-inner']/tbody/tr[1]//span")
    private SelenideElement setDate;

    /**
     * Должность
     */
    @FindBy(xpath = "//*[contains (@class, 'bwrap')]/*[contains (@class, 'layout')]/div/input")
    private SelenideElement jobTitle;

    /**
     * Имя пользователя (login)
     */
    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[2]//div)[2]")
    private SelenideElement clickNameUserLogin;

    /**
     * Пароль
     */
    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[3]//div)[2]")
    private SelenideElement clickFieldPassword;

    /**
     * Подтверждение пароля
     */
    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[4]//div)[2]")
    private SelenideElement clickСonfirmationPassword;

    /**
     * Статус
     */
    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[5]//div)[2]")
    private SelenideElement clickStatus;

    /**
     * Дополнительный номер
     */
    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[6]//div)[2]")
    private SelenideElement clickAdditionalNumber;

    /**
     * Порядок пользователя при принудительной сортировке
     */
    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[7]//div)[2]")
    private SelenideElement clickUserForcedSorting;

    /**
     * Сменить пароль при следующем входе
     */
    @FindBy(id = "needpasswordchange_-1")
    private SelenideElement clickNeedPasswordChange;

    /**
     * Страна
     */
    @FindBy(xpath = "//div[contains(@id,'ext-')]/div[11]//td[2]")
    private SelenideElement clickСountry;

    /**
     * Почтовый код
     */
    @FindBy(xpath = "//div[contains(@id,'ext-')]/div[12]//td[2]")
    private SelenideElement clickPostalCode;

    /**
     * Область
     */
    @FindBy(xpath = "//div[contains(@id,'ext-')]/div[13]//td[2]")
    private SelenideElement clickRegion;

    /**
     * Адрес
     */
    @FindBy(xpath = "//div[contains(@id,'ext-')]/div[15]//td[2]")
    private SelenideElement clickAddress;

    /**
     * Рабочий телефон
     */
    @FindBy(xpath = "//div[contains(@id,'ext-')]/div[17]//td[2]")
    private SelenideElement clickJobIphone;

    /**
     * Домашний телефон
     */
    @FindBy(xpath = "//div[contains(@id,'ext-')]/div[18]//td[2]")
    private SelenideElement clickHomeIphone;

    /**
     * email
     */
    @FindBy(xpath = "//div[contains(@id,'ext-')]/div[20]//td[2]")
    private SelenideElement clickEmail;

    /**
     * Компания
     */
    @FindBy(xpath = "//div[contains(@id,'ext-')]/div[26]//td[2]")
    private SelenideElement clickCompany;

    /**
     * Сохранить пользователя
     */
    @FindBy(xpath = "(//table[3]/../table[1]//button)[1]")
    private SelenideElement clickButtonSave;

    /**
     * ФРЕЙМЫ:
     */

    /**
     * Фрейм окна добавления псевдонима в подразделение
     */
    @FindBy(xpath = "//*[contains (@src, 'selectdep')]")
    private SelenideElement addAliasFrame;

    /**
     * Фрейм редактирование атрибутов пользователя
     */
    @FindBy(xpath = "//iframe[contains(@id,'component-')]")
    private SelenideElement fremEditUser;

    /**
     * Фрейм редактирования изображения пользователя
     */
    @FindBy(xpath = "//iframe[@class='cke_wysiwyg_frame cke_reset']")
    private SelenideElement ckeWysiwygFrame;

    /**
     * Основной фрейм
     */
    @FindBy(id = "flow")
    private SelenideElement Frem;

    /**
     * Фрейм редактирования атрибутов пользователя
     *
     * @return CreateUsersPage
     */
    public CreateUsersPage gotoFremEditUser() {
        getWebDriver().switchTo().frame(fremEditUser);
        return this;
    }

    /**
     * Фрейм редактирования изображения пользователя
     *
     * @return
     */
    public CreateUsersPage CkeWysiwygFrame() {
        getWebDriver().switchTo().frame(ckeWysiwygFrame);
        return this;
    }

    /**
     * Уходим во фрейм - Администрирование/Пользователи
     *
     * @return CreateUsersPage
     */
    public CreateUsersPage goToFremUsersPage() {
        getWebDriver().switchTo().frame(Frem);
        return this;
    }

    /**
     * Добавить пользователя
     *
     * @return CreateUserPage
     */
    public CreateUsersPage buttonAddUser() {
        buttonAddUser.click();
        return this;
    }

    /**
     * Фамилия
     *
     * @param text передаваемая - Фамилия - пользователя
     * @return CreateUserPage
     */
    public CreateUsersPage setLastNameField(String text) {
        lastNameField.click();
        lastNameField.clear();
        lastNameField.sendKeys(text);
        return this;
    }

    /**
     * Имя
     *
     * @param text передаваемое - Имя - пользователя
     * @return CreateUserPage
     */
    public CreateUsersPage setNameField(String text) {
        nameField.click();
        nameField.clear();
        nameField.sendKeys(text);
        return this;
    }

    /**
     * Отчество
     *
     * @param text передаваемая - Отчество - пользователя
     * @return CreateUsersPage
     */
    public CreateUsersPage setPatronymicField(String text) {
        patronymicField.click();
        patronymicField.clear();
        patronymicField.sendKeys(text);
        return this;
    }

    /**
     * Пол
     *
     * @return CreateUsersPage
     */
    public CreateUsersPage clickSex(boolean isMan) {
        if (isMan) {
            clickRadioButtonMan.click();
        } else {
            clickRadioButtonWoman.click();
        }
        return this;
    }

    /**
     * Дата рождения
     *
     * @return CreateUsersPage
     */
    public CreateUsersPage setDateOfBirth(String date) {
        birthDateField.click();
        birthDateField.clear();
        birthDateField.sendKeys(date);
        return this;
    }

    /**
     * Должность
     *
     * @param text
     * @return CreateUsersPage
     */
    public CreateUsersPage setJobTitle(String text) {
        jobTitle.click();
        jobTitle.clear();
        jobTitle.sendKeys(text);
        return this;
    }

    /**
     * Имя пользователя (login)
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setNameUserLogin(String text) {
        clickNameUserLogin.click();
        visibleEditor.clear();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Пароль
     *
     * @param text передаваемый - пароль - пользователя
     * @return CreateUserPage
     */
    public CreateUsersPage setPasswordUser(String text) {
        clickFieldPassword.click();
        visibleEditor.clear();
        visibleEditor.sendKeys(text);
        String confPass = Keys.chord(Keys.ENTER);
        visibleEditor.sendKeys(confPass);
        return this;
    }

    /**
     * Подтверждение пароля
     *
     * @param text передаваемое - подтверждение пароля - пользователя
     * @return CreateUserPage
     */
    public CreateUsersPage setСonfirmationPassword(String text) {
        clickСonfirmationPassword.click();
        visibleEditor.clear();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Статус - Начальник
     *
     * @return CreateUserPage
     */
    public CreateUsersPage setStatus(Status status) {
        clickStatus.click();
        if (status == BOSS) {
            visibleEditor.sendKeys(Keys.chord(Keys
                    .ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));
        } else if (status == CLERK) {
            visibleEditor.sendKeys(Keys.chord(Keys
                    .ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));
        }
        return this;
    }

    /**
     * Статус - Делопроизводитель
     */
    public CreateUsersPage setStatusClerk() {
        clickStatus.click();
        String selectStatus = Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN,
                Keys.ARROW_DOWN, Keys.ENTER);
        visibleEditor.sendKeys(selectStatus);
        return this;
    }

    /**
     * Дополнительный номер
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setAdditionalNumber(String text) {
        clickAdditionalNumber.click();
        visibleEditor.clear();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Порядок пользователя при принудительной сортировке
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setUserForcedSorting(String text) {
        clickUserForcedSorting.click();
        visibleEditor.clear();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Сменить пароль при следующем входе
     *
     * @return CreateUserPage
     */
    public CreateUsersPage clickNeedPasswordChange(boolean needsPassChange) {
        if (needsPassChange) {
            clickNeedPasswordChange.click();
        }
        return this;
    }

    /**
     * Страна
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setCountry(String text) {
        clickСountry.click();
        visibleEditor.clear();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Почтовый код
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setPostalCode(String text) {
        clickPostalCode.click();
        visibleEditor.clear();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Область
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setRegion(String text) {
        clickRegion.click();
        visibleEditor.clear();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Адрес - улица, дом, кв.
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setAddress(String text) {
        clickAddress.click();
        visibleEditor.clear();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Рабочий телефон
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setWorkIphone(String text) {
        clickJobIphone.click();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Домашний телефон
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setHomeIphone(String text) {
        clickHomeIphone.click();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * email
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setEmail(String text) {
        clickEmail.click();
        visibleEditor.clear();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Компания
     *
     * @param text
     * @return CreateUserPage
     */
    public CreateUsersPage setCompany(String text) {
        clickCompany.click();
        visibleEditor.sendKeys(text);
        return this;
    }

    /**
     * Нажатие кнопки - Сохранить пользователя
     *
     * @return CreateUserPage
     */
    public CreateUsersPage clickButtonSave() {
        clickButtonSave.click();
        waitForMask();
        return this;
    }

    /**
     * Удалить пользователя
     *
     * @return CreateUsersPage
     */
    public CreateUsersPage buttonDelUser() {
        buttonDelUser.click();
        return this;
    }

    /**
     * Проверка создания пользователей:
     */
    public CreateUsersPage verifyCreateUser(Employee User) {
        $(By.xpath("//tbody[contains(@id,'gridview')]/tr//a[text()='"
                + User.getLastName() + " " + User.getName() + " "
                + User.getPatronymic() + "']")).shouldBe(Condition.present);
        return this;
    }

    /**
     * Выбор пользователя в гриде
     *
     * @param user
     * @return CreateUsersPage
     */
    public CreateUsersPage clickUserByName(Employee user) {
        $(By.xpath("//a[contains (text(), '" + user.getLastName() + "')]")).shouldBe(Condition.visible);
        $(By.xpath("//a[contains (text(), '" + user.getLastName()
                + "')]/parent::div/parent::td/preceding-sibling::td"))
                .click();
        return this;
    }

    /**
     * Выбор пользователя (открытие формы редактирования) в гриде
     *
     * @param user
     * @return CreateUsersPage
     */
    public CreateUsersPage clickEditUserFormByName(Employee user) {
        $(By
                .xpath("//a[contains (text(), '" + user.getLastName() + "')]")).shouldBe(Condition.visible);
        $(By.xpath("//a[contains(@onclick,'openEditUserForm')][contains(text(),'" + user.getLastName() + "')]"))
                .click();
        return this;
    }

    /**
     * Кликнуть кнопку - Создать псевдоним
     */
    public CreateUsersPage clickButtonCreateAlias() {
        buttonCreateAlias.click();
        return this;

    }

    /**
     * В окне добавления псевдонима в подразделение ищем нужный департамент
     */
    public CreateUsersPage serarchDepForAlias(Department department) {
        getWebDriver().switchTo().frame(addAliasFrame);
        $(fieldSearchDepForAlias).shouldBe(Condition.visible);
        fieldSearchDepForAlias.click();
        fieldSearchDepForAlias.sendKeys(department.getDepName());
        buttonSearchDepForAlias.click();
        return this;
    }

    /**
     * Кликаем чекбокс найденного департамента
     */
    public CreateUsersPage clickFoundDep() {
        selectedCheckBox.click();
        return this;

    }

    /**
     * Кликаем кнопку сохранить в окне добавления псевдонима
     */
    public CreateUsersPage clickButtonSaveAlias() {
        buttonSaveAlias.click();
        return this;

    }

    /**
     * Проверка наличия на странице псевдонима для подразделения
     */
    public CreateUsersPage assertHasAlias(Employee user, Department department) {
        assertTrue(isElementPresent(By.xpath("/*//*[contains (text(), '" + user.getLastName() + " " + user.getName() + " "
                + user.getPatronymic() + " (для задач по " + department.getDepName() + ")')]")));
        return this;
    }

    /**
     * Метод устновки прав на модуль
     *
     * @param module
     * @return
     */
    public CreateUsersPage selModule(Module module) {
        if (module == DOCFLOW) {
            workflowCheckBox.click();
        }
        if (module == WORKFLOW) {
            docflowCheckBox.click();
        }
        return this;
    }


    /**
     * Проверяем и ожидаем, что на странице имеется соответствующий элемент:
     * -Кнопка - Добавить пользователя; -Кнопка - Удалить пользователя.
     */
    public CreateUsersPage ensurePageLoaded() {
        $(By.xpath("(//a[contains(@id,'button-')])[4]")).shouldBe(Condition.present);
        $(By.xpath("(//a[contains(@id,'button-')])[5]")).shouldBe(Condition.present);
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
        goToTopFrem();
        initializationInternalPage().searchFacilityOnTheGrid(user.getLastName());
        goToFremDepartmentPage().waitForMask();
        clickUserByName(user); // выбираем пользователя в гриде
        buttonDelUser(); // Нажимаем удалить пользователя
        confirmDeletionUser(user);
    }

    /**
     * Подтверждение удаления пользователя
     *
     * @param user
     */
    public CreateDepartmentPage confirmDeletionUser(Employee user) {
        checkingMessagesConfirmationOfTheObject(getExpectedMessageTextToDialog,
                "Вы уверены, что хотите удалить пользователя " + user.getLastName() + " "
                        + user.getName() + " " + user.getPatronymic() + "" + "?", oKRemoveDelete);
        waitForMask();
        return this;
    }

    /**
     * Инициализация класса - Внутреняя страница
     *
     * @return
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
        goToTopFrem();
        initializationInternalPage().searchFacilityOnTheGrid(user.getLastName());
        goToFremDepartmentPage();
        waitForMask();
        clickUserByName(user)
                .clickButtonCreateAlias()
                .serarchDepForAlias(department)
                .clickFoundDep()
                .clickButtonSaveAlias();
        checkIsAlias(user, department);
    }

    /**
     * Проверка наличия псевдонима в подразделении. Методу передается
     * пользователь оригинал, и подразделение для которого нужно проверить наличие псевдонима
     * данного пользователя
     */
    public void checkIsAlias(Employee user, Department department) {
        goToTopFrem();
        initializationInternalPage().searchFacilityOnTheGrid(user.getLastName());
        goToFremDepartmentPage();
        waitForMask();
        assertHasAlias(user, department);
    }

    /**
     * Метод Добавления пользователей. В качестве параметра передается объект
     * создаваемого пользователя и подразделение, в которое он будет помещен
     */
    @Override
    public void createUser(Employee user) {
        selectTheParentUnit(user.getDepartment());
        buttonAddUser() // Добавить пользователя
                .gotoFremEditUser() // Переходим в iframe формы добавления пользователя
                .setLastNameField(user.getLastName()) // ФИО
                .setNameField(user.getName()) // Имя
                .setPatronymicField(user.getPatronymic()) // Отчество
                .clickSex(user.getIsMan())
                .setDateOfBirth(user.getBirthDate())
                .setJobTitle(user.getJobTitle())
                .setNameUserLogin(user.getLoginName())
                .setPasswordUser(user.getPassword())
                .setСonfirmationPassword(user.getСonfirmationPassword())
                .setStatus(user.getStatus())
                .selModule(user.getModule())
                .setAdditionalNumber(user.getAdditionalNumber())
                .setUserForcedSorting(user.getUserForcedSorting())
                .clickNeedPasswordChange(user.getNeedsPasswordChange())
                .clickButtonSave()

                .goToTopFrem();
        goToFremUsersPage()
                .ensurePageLoaded().verifyCreateUser(user);
    }

    /**
     * Метод Редактирования пользователей. В качестве параметра передается объект
     * созданного пользователя и его подразделение, в котором он был создан
     */
    @Override
    public void editUser(Employee editUser, Employee user) {
        selectTheParentUnit(user.getDepartment());
        clickEditUserFormByName(user)// выбираем пользователя в гриде для редактирования
                .gotoFremEditUser() // Переходим в iframe формы добавления пользователя
                .setLastNameField(editUser.getLastName())
                .setNameField(editUser.getName())
                .setPatronymicField(editUser.getPatronymic())
                .clickSex(editUser.getIsMan())
                .setDateOfBirth(editUser.getBirthDate())
                .setJobTitle(editUser.getJobTitle())
                .setNameUserLogin(editUser.getLoginName())
                .setPasswordUser(editUser.getPassword())
                .setСonfirmationPassword(editUser.getСonfirmationPassword())
                .setStatus(editUser.getStatus())
                .selModule(editUser.getModule())
                .setAdditionalNumber(editUser.getAdditionalNumber())
                .setUserForcedSorting(editUser.getUserForcedSorting())
                .clickNeedPasswordChange(editUser.getNeedsPasswordChange())
                .clickButtonSave()

                .goToTopFrem();
        goToFremUsersPage()
                .ensurePageLoaded().verifyCreateUser(editUser);
    }
}
