package ru.st.selenium.pages.pagesweb.Administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.WebLogic.DirectoriesLogic;
import ru.st.selenium.logicinterface.WebLogic.TasksTypesLogic;
import ru.st.selenium.logicinterface.WebLogic.TypesOfTablesLogic;
import ru.st.selenium.model.Administration.Directories.Directories;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.TypesOfTables.TypesOfTables;
import ru.st.selenium.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Основной грид разделов - Типы Таблиц/Типы задач/Справочники, раздела Администрирование
 */
public class TaskTypeListObjectPage extends BasePage implements DirectoriesLogic, TypesOfTablesLogic, TasksTypesLogic {

    /*
     * ОК - Подтверждение добавления объекта
     */
    @FindBy(xpath = "//*[contains (@class, 'message-box')]//a[1]")
    private SelenideElement clickOkAndAddFieldTypesTable;

    /*
     * Поле ввода Названия объекта
     */
    @FindBy(xpath = "//input")
    private SelenideElement nameObject;

    /*
     * Добавить
     */
    @FindBy(xpath = "//div[contains (@class, 'bottom')]//a[1]//span[contains (@class, 'icon')]")
    private SelenideElement clickAddTypesObject;

    /*
     * Редактировать
     */
    @FindBy(xpath = "//div[contains (@class, 'bottom')]//a[2]//span[contains (@class, 'icon')]")
    private SelenideElement clickEditTypesObject;

    /*
     * Удалить
     */
    @FindBy(xpath = "//div[contains (@class, 'bottom')]//a[3]//span[contains (@class, 'icon')]")
    private SelenideElement clickDelTypesObject;

    /*
     * Кнопка - Да, в диалоговом окне при удалении объекта
     */
    @FindBy(xpath = "//div[count(a)=4]/a[2]//span[position()=2]")
    private SelenideElement confirmationButtonObjectDeletion;

    /*
     * Кнопка - Нет, в диалоговом окне при удалении объекта
     */
    @FindBy(xpath = "//div[count(a)=4]/a[3]//span[position()=2]")
    private SelenideElement cancelButtonObjectDeletion;


    /**
     * Кнопка: Добавить объект - в гриде - Типы Таблиц/Типы задач/Справочники
     */
    public TaskTypeListObjectPage addTaskTypesObject() {
        clickAddTypesObject.click();
        // Ждем появления формы ввода Названия Типы таблицы
        $(clickOkAndAddFieldTypesTable).shouldBe(Condition.present);
        return this;
    }

    /**
     * Вводим Название объекта и нажимаем кнопку ОК для сохранения
     *
     * @param text
     * @return TaskTypeListObjectPage
     */
    public TaskTypeListObjectPage valueNameObject(String text) {
        $(nameObject).shouldBe(Condition.visible).setValue(text);
        return this;
    }

    /**
     * Проверка созданного объекта в гриде tasktypelist
     *
     * @return TaskTypeListObjectPage
     */
    public TaskTypeListObjectPage verifyCreateObject(String ObjectName) {
        // TODO проблема скроллинга - не ищет в DOM когда очень много объектов!
        $(By.xpath("//*[text()='" + ObjectName + "'][ancestor::table]")).scrollTo();
        $(By.xpath("//*[text()='" + ObjectName + "'][ancestor::table]")).shouldBe(Condition.visible);
        return this;
    }


    //----------------------------------------------------------------------------------------СПРАВОЧНИКИ

    /**
     * Добавление нового спр-ка (Администрирование / Справочники)
     *
     * @param directories
     */
    @Override
    public void addDirectories(Directories directories) {
        ensurePageLoaded();
        addTaskTypesObject(); // Добавить объект (кнопка - Добавить)
        valueNameObject(directories.getDirectoryName()); // Название Спр-ка
        clickOkAndAddFieldTypesTable.click(); // Сохранение (подтверждение) объекта
    }

    @Override
    public void editDirectories(Directories directories) {

    }

    /**
     * Удаление объекта Справочник из системы
     *
     * @param directories
     */
    @Override
    public void removeAnDirectories(Directories directories) {
        ensurePageLoaded();
        $(By.xpath("//*[contains(text(),'" + directories.getDirectoryName() + "')][ancestor::table]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(),'" + directories.getDirectoryName() + "')][ancestor::table]/../../img[1]")).click();
        clickDelTypesObject.click();
        checkingMessagesConfirmationOfTheObject($(By.xpath("//div[contains(@id,'messagebox')]/ancestor::div[contains(@id,'container')]//div[text()]")),
                "Вы действительно хотите удалить справочник \"" + directories.getDirectoryName() + "" + "\"?",
                confirmationButtonObjectDeletion);
        $(By.xpath("//*[contains(text(),'" + directories.getDirectoryName() + "')][ancestor::table]/..")).shouldNotBe(Condition.visible);

    }

    /**
     * Инициализация формы редактирования - Справочники
     */
    public DirectoriesEditFormPage goToDirectoriesEditPage() {
        return page(DirectoriesEditFormPage.class);
    }

    //----------------------------------------------------------------------------------------ТИПЫ ТАБЛИЦ

    /**
     * Добавление объекта Типы таблиц (Администрирование / Типы таблиц)
     *
     * @param typesOfTables передаём строковое зн-ие (Имя объекта)
     */
    @Override
    public void addTypesOfTables(TypesOfTables typesOfTables) {
        ensurePageLoaded();
        addTaskTypesObject(); // Добавить объект (кнопка - Добавить)
        valueNameObject(typesOfTables.getTableTypeName()); // Название Типа таблицы
        clickOkAndAddFieldTypesTable.click(); // Сохранение (подтверждение) объекта
    }

    @Override
    public void editTypesOfTables(TypesOfTables typesOfTables) {

    }

    /**
     * Удаление объекта Типы таблиц из системы
     *
     * @param typesOfTables передаём строковое зн-ие (Имя объекта)
     */
    @Override
    public void removeTypesOfTables(TypesOfTables typesOfTables) {
        ensurePageLoaded();
        $(By.xpath("//*[contains(text(),'" + typesOfTables.getTableTypeName() + "')][ancestor::table]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(),'" + typesOfTables.getTableTypeName() + "')][ancestor::table]/../span")).click();
        clickDelTypesObject.click();
        checkingMessagesConfirmationOfTheObject($(By.xpath("//div[contains(@id,'messagebox')]/ancestor::div[contains(@id,'container')]//div[text()]")),
                "Вы действительно хотите удалить таблицу \"" + typesOfTables.getTableTypeName() + "" + "\"?",
                confirmationButtonObjectDeletion);
        $(By.xpath("//*[contains(text(),'" + typesOfTables.getTableTypeName() + "')][ancestor::table]/..")).shouldNotBe(Condition.visible);

    }

    /**
     * Инициализация формы редактирования - Типы таблиц
     */
    public TypesOfTablesEditPage goToTypesOfTablesEditPage() {
        return page(TypesOfTablesEditPage.class);
    }

    //----------------------------------------------------------------------------------------ТИПЫ ЗАДАЧ

    /**
     * Добавление нового спр-ка (Администрирование / Типы задач)
     *
     * @param tasksTypes передаём строковое зн-ие (Имя объекта)
     */
    @Override
    public void addTasksTypes(TasksTypes tasksTypes) {
        ensurePageLoaded();
        addTaskTypesObject(); // Добавить объект (кнопка - Добавить)
        valueNameObject(tasksTypes.getTaskTypeName()); // Название Типа таблицы
        clickOkAndAddFieldTypesTable.click(); // Сохранение (подтверждение) объекта
    }

    @Override
    public void editTasksTypes(TasksTypes tasksTypes) {

    }

    /**
     * Удаление объекта - Тип задачи - из системы
     *
     * @param tasksTypes передаём строковое зн-ие (Имя объекта)
     */
    @Override
    public void removeAnTasksTypes(TasksTypes tasksTypes) {
        ensurePageLoaded();
        $(By.xpath("//*[contains(text(),'" + tasksTypes.getTaskTypeName() + "')][ancestor::table]")).shouldBe(Condition.visible);
        $(By.xpath("//*[contains(text(),'" + tasksTypes.getTaskTypeName() + "')][ancestor::table]/../span")).click();
        clickDelTypesObject.click();
        checkingMessagesConfirmationOfTheObject($(By.xpath("//div[contains(@id,'messagebox')]/ancestor::div[contains(@id,'container')]//div[text()]")),
                "Вы действительно хотите удалить тип задачи \"" + tasksTypes.getTaskTypeName() + "" + "\"?",
                confirmationButtonObjectDeletion);
        $(By.xpath("//*[contains(text(),'" + tasksTypes.getTaskTypeName() + "')][ancestor::table]/..")).shouldNotBe(Condition.visible);
    }

    /**
     * Инициализация формы редактирования - Типы задач
     */
    public TaskTypesEditPage goToTaskTypesEditPage() {
        return page(TaskTypesEditPage.class);
    }


    /**
     * Ожидание появления элемента(\ов) в гридах - Типы таблиц/Типы
     * задач/Справочники - Кнопка - Добавить; - Кнопка - Редактировать
     */
    public TaskTypeListObjectPage ensurePageLoaded() {
        $(clickAddTypesObject).shouldBe(Condition.present);
        $(clickEditTypesObject).shouldBe(Condition.visible);
        return this;
    }
}
