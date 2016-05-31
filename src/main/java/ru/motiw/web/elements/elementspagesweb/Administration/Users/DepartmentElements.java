package ru.motiw.web.elements.elementspagesweb.Administration.Users;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.motiw.web.logicinterface.WebLogic.DepartmentsLogic;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.elements.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.motiw.utils.ChecksUtil.isElementPresent;

/**
 * Страница - Администрирование/Пользователи (Подразделения)
 */
public class DepartmentElements extends BasePage implements DepartmentsLogic {

    /**
     * Строка поиска
     */
    @FindBy(id = "searchQueryEdit")
    private SelenideElement search;

    /**
     * Выбрать корневое подразделение
     */

    @FindBy(xpath = "//tr[@data-recordid='1']/td/div/span")
    private SelenideElement rootDep;

    /**
     * Свернутый элемент дерева подразделений
     */
    @FindBy(xpath = "//tr[not(contains(@class, 'expanded'))]//img[contains (@class, 'expander')]")
    private SelenideElement closedNode;

    /**
     * Добавить подразделение
     */
    @FindBy(xpath = "(//div[contains(@id,'panel-')]//a[1])[1]")
    private SelenideElement buttonAddDep;

    /**
     * Кнопка - Редактировать подразделение
     */
    @FindBy(xpath = "(//div[contains(@id,'panel-')]//a[2])[1]")
    private SelenideElement buttonEditDep;

    /**
     * Кнопка - Удаление подразделения
     */
    @FindBy(xpath = "(//div[contains(@id,'panel-')]//a[3])[1]")
    private SelenideElement buttonRemoveDep;

    /**
     * Наименование подразделения
     */
    @FindBy(id = "dep_name-inputEl")
    private SelenideElement nameDep;

    /**
     * Условный номер
     */
    @FindBy(id = "add_number-inputEl")
    private SelenideElement conditionalNumber;

    /**
     * Значение счетчика
     */
    @FindBy(id = "counter-inputEl")
    private SelenideElement counter;

    /**
     * Шаблон нумератора
     */
    @FindBy(id = "reset_date-inputEl")
    private SelenideElement resetDate;

    /**
     * Дата обнуления
     */
    @FindBy(id = "numerator_template-inputEl")
    private SelenideElement numeratorTemplate;

    /**
     * Сохранить подразделение
     */
    @FindBy(xpath = "(//span[contains(@id,'button') and @class='x-btn-wrap'] [ancestor::div[@id='editDepWin']])[1]/span/span[last()]")
    private SelenideElement saveDepartment;

    /**
     * Отменить сохранение подразделения
     */
    @FindBy(xpath = "(//span[contains(@id,'button') and @class='x-btn-wrap'] [ancestor::div[@id='editDepWin']])[2]/span/span[last()]")
    private SelenideElement cancellationOfCreatingDivisions;

    /**
     * Маска при клике подразделений
     */
    @FindBy(xpath = "//*[contains (@class, 'x-mask')]")
    private SelenideElement maskDepartment;

    /**
     * Кнопка - Да, в форме предупреждений - Вы уверены, что хотите удалить подразделение? или Сохранить дополнительные полномочия пользователей?
     */
    @FindBy(xpath = "//*[contains (@class, 'message')][contains (@class, 'closable')]/div[contains (@id, 'toolbar')]//a[2]//span[contains (@class, 'icon')]")
    private SelenideElement savePermissionYes;


    /**
     * Кнопка Ок - в диалоге подтверждения удаления подразделения
     */
    @FindBy(xpath = "//span[contains(@id,'-btnWrap')]//span[text()='Да']/../span[contains(@id,'-btnIconEl')]")
    protected SelenideElement oKRemoveDelete;

    /**
     * Комбик для раскрытия подразделений
     */
    @FindBy(xpath = "//tr[not(contains(@class, 'expanded'))]/td/div/img[contains (@class, 'expander')]")
    private SelenideElement disclosureComboUnitsUser;

    /**
     * Элемент - Сообщение в диалоге при ДнД/Удалении - Подразделение
     */
    @FindBy(xpath = "//div[count(div)=3]/div[2]//div[contains(@id,'messagebox') and (@data-errorqtip)]")
    protected SelenideElement getExpectedMessageTextToDialog;

    /**
     * Основной фрейм
     */
    @FindBy(id = "flow")
    private SelenideElement Frem;

    /**
     * Уходим во фрейм - Администрирование/Подразделения
     *
     * @return DepartmentElements
     */
    public DepartmentElements goToFremDepartmentPage() {
        getWebDriver().switchTo().frame(Frem);
        return this;
    }

    /**
     * Развернем все ветви дерева подразделений
     *
     * @return DepartmentElements
     */
    public DepartmentElements unwrapAllNodes() {
        try {
            while (isElementPresent(By
                    .xpath("//tr[not(contains(@class, 'expanded'))]//img[contains (@class, 'expander')]")))
                closedNode.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }


    /**
     * Выбрать корневое Подразделение для взаимодействия с элементом
     *
     * @return DepartmentElements
     */
    public DepartmentElements clickRootDep() {
        rootDep.click();
        waitForMask();
        return this;
    }

    /**
     * Добавить Подразделение
     *
     * @return DepartmentElements
     */
    public DepartmentElements buttonAddDep() {
        buttonAddDep.click();
        return this;
    }

    /**
     * Удаление Подразделения
     *
     * @return DepartmentElements
     */
    public DepartmentElements clickButtonRemoveDep() {
        buttonRemoveDep.click();
        return this;
    }

    /**
     * Редактировать Подразделение
     *
     * @return DepartmentElements
     */
    public DepartmentElements clickbuttonEditDep() {
        buttonEditDep.click();
        return this;
    }

    /**
     * Название Подразделения
     *
     * @param text input text
     * @return DepartmentElements
     */
    public DepartmentElements setNameDep(String text) {
        nameDep.clear();
        nameDep.setValue(text);
        return this;
    }

    /**
     * Условный номер подразделения
     *
     * @param text input text
     * @return DepartmentElements
     */
    public DepartmentElements setConditionalNumber(String text) {
        conditionalNumber.clear();
        conditionalNumber.setValue(text);
        return this;
    }

    /**
     * Значение счетчика подразделения
     *
     * @param text input text
     * @return DepartmentElements
     */
    public DepartmentElements setCounter(String text) {
        counter.clear();
        counter.setValue(text);
        return this;
    }

    /**
     * Дата обнуления счетчика подразделения
     *
     * @param text input text
     * @return DepartmentElements
     */
    public DepartmentElements setResetDate(String text) {
        resetDate.clear();
        resetDate.setValue(text);
        return this;
    }

    /**
     * Дата обнуления счетчика подразделения
     *
     * @param text input text
     * @return DepartmentElements
     */
    public DepartmentElements setNumeratorTemplate(String text) {
        numeratorTemplate.clear();
        numeratorTemplate.setValue(text);
        return this;
    }

    /**
     * Сохранение подразделения
     *
     * @return DepartmentElements
     */
    public DepartmentElements clickSaveDep() {
        saveDepartment.click();
        return this;
    }


    /**
     * Проверка отображения созданного подразделения
     *
     * @return DepartmentElements
     */
    public DepartmentElements verifyCreateDepartment(String DepName) {
        $(By.xpath("//*[text()='" + DepName + "'] [ancestor::*[contains(@id,'treeview')]]")).waitUntil(Condition.visible, 10000);
        return this;

    }

    /**
     * Проверка удаления подразделения. Подразделение не должно отображаться в гриде после удаления
     *
     * @return DepartmentElements
     */
    public DepartmentElements verifyRemoveDepartment(String DepName) {
        $(By.xpath("//*[text()='" + DepName + "'] [ancestor::*[contains(@id,'treeview')]]")).waitUntil(Condition.disappears, 10000);
        return this;

    }

    /**
     * После каждого клика на подразделение повисает маска, данный метод должен
     * использовваться после каждого клика по подразделению для ожидания
     * невидимости маски
     *
     * @return DepartmentElements
     */
    public DepartmentElements waitForMask() {
        sleep(400);
        $(By.xpath("//*[contains (@class, 'x-mask')]")).waitUntil(Condition.disappear, 30000);
        return this;
    }

    /**
     * Метод ДнД подразделения
     *
     * @return DepartmentElements
     */
    public DepartmentElements DnDDepartment(Department source, Department target) {
        SelenideElement sourceElement = $(By
                .xpath("//span[contains(text(),'" + source.getDepartmentName()
                        + "')] [ancestor::tbody[contains(@id,'treeview')]]"));
        SelenideElement targetElement = $(By
                .xpath("//span[contains(text(),'" + target.getDepartmentName()
                        + "')] [ancestor::tbody[contains(@id,'treeview')]]"));
        sourceElement.click();
        Actions builder = new Actions(getWebDriver());
        Action drag = builder.clickAndHold(sourceElement).build();
        Action drop = builder.moveToElement(targetElement)
                .release(targetElement).build();
        drag.perform();
        waitForMask();
        drop.perform();
        return this;
    }

    /**
     * Метод ДнД для удаления подразделений
     *
     * @return DepartmentElements
     */
    public DepartmentElements dndFirstChildToRoot(Department department) {
        $(By.xpath("//*[contains (@id, 'messagebox')][contains (@class, 'closable')]/div[contains (@id, 'toolbar')]//a[1]//span[2]"))
                .click();
        SelenideElement child = $(By.xpath("//span[contains(text(),'"
                + department.getDepartmentName()
                + "')] [ancestor::tbody[contains(@id,'treeview')]]/parent::div/parent::td/parent::tr/following-sibling::tr/td/div/span"));
        child.click();
        Actions builder = new Actions(getWebDriver());
        Action drag = builder.clickAndHold(child).build();
        Action drop = builder.moveToElement(rootDep).release(rootDep).build();
        drag.perform();
        waitForMask();
        drop.perform();
        checkingMessagesConfirmationOfTheObject(getExpectedMessageTextToDialog,
                "Сохранить дополнительные полномочия пользователей?", savePermissionYes);
        return this;
    }

    /**
     * Выбираем подразделение по имени
     */
    public DepartmentElements selectTheParentUnit(Department department) {
        waitForMask();
        $(By.xpath("//span[contains(text(),'" + department.getDepartmentName()
                + "')] [ancestor::tbody[contains(@id,'treeview')]]"))
                .click();
        return this;
    }

    /**
     * Логическая проверка, есть ли у подразделения дочерние подразделение,
     * производится только, после клика кнопки - Удалить подразделение
     */
    public boolean hasChild() {
        try {
            (new WebDriverWait(getWebDriver(), 0, 50))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath("//*[contains (@id, 'messagebox')][contains (@class, 'closable')]/div[contains (@id, 'toolbar')]//a[1]")));
            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }

    /**
     * Проверяем и ожидаем, что на странице имеется соответствующий элемент: -
     * Главное подразделение - в фрейме иерархии подразделений; - Кнопка -
     * Добавить подразделение
     */
    public DepartmentElements ensurePageLoaded() {
        $(By.xpath("//tr[@data-recordid='1']/td/div/span/ancestor::div[contains(@class,'treecolumn')]")).waitUntil(Condition.visible, 8000);
        rootDep.shouldBe(Condition.visible);
        return this;
    }

    /**
     * Метод помогающий подготовить интерфейс к добавлению подразделений
     * (Проверка загрузки страницы, раскрытие всех элементов дерева подразделений)
     */
    @Override
    public void beforeAdd() {
        ensurePageLoaded().unwrapAllNodes();
        clickRootDep(); // выбираем корневое подразделение
    }


    /**
     * Создание объекта - Подразделение
     *
     * @param department передаваемые атрибуты подразделения
     */
    @Step("Создаем пользовательское подразделение")
    @Override
    public void createDepartment(Department department) {
        if (department.getParentDepartment() != null) {
            selectTheParentUnit(department.getParentDepartment()); // Выбираем подразделение
        }
        buttonAddDep(); // Добавить подразделение - Подразделение
        setNameDep(department.getDepartmentName()) // Название подразделения
                .setConditionalNumber(department.getConditionalNumber()) // Условный номер
                .setCounter(department.getCounter()) // Счетчик
                .setResetDate(department.getResetDate()) // Дата обнуления счетчика
                .setNumeratorTemplate(department.getNumeratorTemplate()) // Шаблон нумератора
                .clickSaveDep() // Сохранить подразделение
                .verifyCreateDepartment(department.getDepartmentName()); // Проверяем отображение элемента в гриде

    }

    /**
     * Подтверждение удаления подразделения
     *
     * @param department передаваемое Название подразделения
     */
    public DepartmentElements confirmDeletionDepartment(Department department) {
        checkingMessagesConfirmationOfTheObject(getExpectedMessageTextToDialog,
                "Вы уверены, что хотите удалить подразделение " + department.getDepartmentName() + "" + "?", oKRemoveDelete);
        waitForMask();
        return this;
    }

    /**
     * Метод редактирования имеющегося подразделения
     *
     * @param editedDepartment передаваемые атрибуты полей редактируемого подразделения
     * @param department       передаваемые атрибуты начального подразделения
     */
    @Step("Редактируем пользовательское Подразделение")
    @Override
    public void editDepartments(Department editedDepartment, Department department) {
        // TODO Добавить проверку заполнения полей (см. selenide - InputFieldTest)
        selectTheParentUnit(editedDepartment)
                .clickbuttonEditDep().setNameDep(department.getDepartmentName())
                .setConditionalNumber(department.getConditionalNumber())
                .setCounter(department.getCounter())
                .setResetDate(department.getResetDate())
                .setNumeratorTemplate(department.getNumeratorTemplate())
                .clickSaveDep()
                .verifyCreateDepartment(department.getDepartmentName());
    }

    /**
     * Удаление подразделений, удаляет подраздделения, даже, когда у удаляемого
     * подразделения есть вложенные
     */
    @Step("Удаляем пользовательское Подразделение")
    @Override
    public void deleteDepartment(Department department) {
        ensurePageLoaded()
                .selectTheParentUnit(department) // Выбираем текущее подразделение в гриде
                .clickButtonRemoveDep(); // Удалить подразделение
        confirmDeletionDepartment(department);
        while (hasChild()) { // Пока есть подразделение потомок
            dndFirstChildToRoot(department) // Днд первое подразделение потомок в корень
                    .selectTheParentUnit(department) // Выбираем текущее подразделение в гриде
                    .clickButtonRemoveDep(); // Удалить подразделение
            confirmDeletionDepartment(department);
        }
        verifyRemoveDepartment(department.getDepartmentName());
    }


    /**
     * ДнД подразделения с сохранением дополнительных полномочий
     */
    @Step("ДнД Подразделения пользователя")
    @Override
    public void dndSavePermissions(Department source, Department target) {
        DnDDepartment(source, target).checkingMessagesConfirmationOfTheObject(getExpectedMessageTextToDialog,
                "Сохранить дополнительные полномочия пользователей?", savePermissionYes);
        waitForMask();

    }


}