package ru.st.selenium.pages.pagesweb.Internal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.WebLogic.BaseInternalLogic;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Administration.*;
import ru.st.selenium.pages.pagesweb.DocflowAdministration.DictionaryEditorPage;
import ru.st.selenium.pages.pagesweb.DocflowAdministration.GridDocRegisterCardsPage;
import ru.st.selenium.pages.pagesweb.Documents.NewDocumentPage;
import ru.st.selenium.pages.pagesweb.Options.PwdPage;
import ru.st.selenium.pages.pagesweb.Tasks.UnionMessageNewPage;
import ru.st.selenium.pages.pagesweb.Tasks.UnionTasksPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Внутренняя страница системы (ОМ - Основное меню)
 */
public class InternalPage extends Page implements BaseInternalLogic {

    /*
     * Ссылки на все пункты меню
     */
    @FindBy(xpath = "//div[@id='left-panel'][ancestor::div[@id='menu']]/div[not(@id='menu-button-more' and @class='menu-point-hidden')]")
    private ElementsCollection menuElements;

    /*
     * =====================================================================================================ЗАДАЧИ
     */

    /*
    Основное меню Задачи
     */
    @FindBy(id = "task")
    private SelenideElement menuTask;

    /*
     * Задачи/Задачи
     */
    @FindBy(id = "L_MENU_UNIONTASKS-menupoint")
    private SelenideElement tasks;

    /*
     * Задачи/Создать задачу
     */
    @FindBy(id = "L_INFORMER_CREATETASK-menupoint")
    private SelenideElement createTask;

    /*
    * =====================================================================================================ДОКУМЕНТЫ
    */
    @FindBy(css = "#doc")
    private SelenideElement menuDocument;

    /*
     * Документы/Создать документ
     */
    @FindBy(css = "#L_DOCUMENT_NEW-menupoint")
    private SelenideElement createDoc;

    /*
     * Календарь
     */
    @FindBy(id = "col")
    private SelenideElement CalMenu;

    /*
     * Библиотека
     */
    @FindBy(id = "lib")
    private SelenideElement LibMenu;

    /*
     * Инструменты
     */
    @FindBy(id = "instr")
    private SelenideElement instrMenu;

    /*
     * =======================================================================================================АДМИНИСТРИРОВАНИЕ
     */
    /*
     Администирование
     */
    @FindBy(id = "L_MENU_ADMINISTRATION-menupoint")
    private SelenideElement administration;

    /*
     * Инструменты/Администрирование/Пользователи
     */
    @FindBy(id = "L_MENU_USERADMIN-menupoint")
    private SelenideElement menuUsers;

    /*
     * Инструменты/Администрирование/Типы таблиц
     */
    @FindBy(id = "L_MENU_TABLES-menupoint")
    private SelenideElement menuTables;

    /*
     * Инструменты/Администрирование/Справочники
     */
    @FindBy(id = "L_MENU_ADMIN_DICTIONARY-menupoint")
    private SelenideElement directories;

    /*
     * Инструменты/Администрирование/Типы задач
     */
    @FindBy(id = "L_MENU_ADMIN_TYPE_TASK-menupoint")
    private SelenideElement typeTask;

    /*
     * Администрирование ДО
     */
    @FindBy(id = "L_DOCPROCESSING-menupoint")
    private SelenideElement docAdministrationMenu;

    /*
     * Регистрационные карточки документов
     */
    @FindBy(id = "L_AU_DOC_CARDS-menupoint")
    private SelenideElement registerCardsMenu;

    /*
     * Редактор маршрутных схем
     */
    @FindBy(id = "L_MENU_ROUTESCHEME_EDITOR-menupoint")
    private SelenideElement rsEditorMenu;

    /*
     * Счетчики документов
     */
    @FindBy(id = "L_AU_DOCUMENT_COUNTERS-menupoint")
    private SelenideElement docCountersMenu;

    /*
     * Редактор словарей
     */
    @FindBy(id = "L_GLOBAL_DICTIONARY_EDITOR-menupoint")
    private SelenideElement dictionaryEditorMenu;

    /*
     Выход из Системы
     */
    @FindBy(css = "#btn_logout")
    private SelenideElement Logout;

    /*
     * Фрейм
     */
    @FindBy(id = "flow")
    private SelenideElement fremFlow;

    /*
     * Строка поиска
     */
    @FindBy(css = "#searchQueryEdit")
    private SelenideElement search;

    /*
    ================================================================================================НАСТРОЙКИ
     */

    /*
     * Настройки
     */
    @FindBy(id = "L_AU_MENU_SETTINGS-menupoint")
    private SelenideElement menuSettings;

    /*
     * Мои реквизиты
     */
    @FindBy(id = "L_MENU_MYOPTIONS-menupoint")
    private SelenideElement menuMyOptions;

    /*
    * Информация о системе
     */
    @FindBy(id = "L_MENU_SISTEM_INFORMATION-menupoint")
    private SelenideElement tester;

    /*
     * Поисковая система
     */
    @FindBy(id = "L_SEARCH_ADMIN-menupoint")
    private SelenideElement searchSystem;


    /*
     * Уходим во фрейм объекта
     */
    public void goToFremFlow() {
        getWebDriver().switchTo().frame(fremFlow);
    }


    /**
     * Поиск
     *
     * @param serachstring
     * @return CreateDepartmentPage
     */
    public InternalPage searchField(String serachstring) {
        search.clear();
        search.setValue(serachstring);
        search.pressEnter();
        return this;
    }

    /**
     * Пользователяская API для эмуляции сложных пользовательских действий
     * (клавиатуры и мыши)
     */
    Actions action = new Actions(getWebDriver());

    /**
     * Метод 2-х уровневой навигации
     *
     * @param firstclick
     * @param secondclick
     */
    private void menuClicker(SelenideElement firstclick, SelenideElement secondclick) {
        goToTopFrem();
        firstclick.click();
        secondclick.click();
        goToFremFlow();
    }

    /**
     * Метод 3-х уровневой навигации
     *
     * @param firstclick
     * @param secondclick
     * @param thirdclick
     */
    private void subMenuClicker(SelenideElement firstclick, SelenideElement secondclick, SelenideElement thirdclick) {
        goToTopFrem();
        firstclick.click();
        action.moveToElement(secondclick).perform();
        $(thirdclick).shouldBe(Condition.visible);
        action.moveToElement(thirdclick).perform();
        thirdclick.click();
        goToFremFlow();
    }

    /**
     * Переход в Задачи/Создать задачу
     */
    public UnionMessageNewPage goToUnionMessageNew() {
        menuClicker(menuTask, createTask);
        return page(UnionMessageNewPage.class);
    }

    /**
     * Переход в Задачи/Задачи
     */
    public UnionTasksPage goToUnionTasks() {
        menuClicker(menuTask, tasks);
        return page(UnionTasksPage.class);
    }

    /**
     * Переход в меню - Администрирование ДО/Регистрационные карточки документов
     */
    public GridDocRegisterCardsPage goToGridDocRegisterCards() {
        subMenuClicker(instrMenu, docAdministrationMenu, registerCardsMenu);
        return page(GridDocRegisterCardsPage.class);

    }

    /**
     * Переход в меню - Администрирование ДО/Редактор словарей
     */
    public DictionaryEditorPage goToDictionaryEditor() {
        subMenuClicker(instrMenu, docAdministrationMenu, dictionaryEditorMenu);
        return page(DictionaryEditorPage.class);
    }

    /**
     * Переход в меню - Администрирование/Справочники
     */
    public TaskTypeListObjectPage goToDirectories() {
        subMenuClicker(instrMenu, administration, directories);
        return page(TaskTypeListObjectPage.class);

    }

    /**
     * Переход в меню - Администрирование/Типы таблиц
     */
    public TaskTypeListObjectPage goToTypesOfTables() {
        subMenuClicker(instrMenu, administration, menuTables);
        return page(TaskTypeListObjectPage.class);

    }

    /**
     * Переход в меню - Администрирование/Типы таблиц
     */
    public TaskTypeListObjectPage goToTaskTypes() {
        subMenuClicker(instrMenu, administration, typeTask);
        return page(TaskTypeListObjectPage.class);

    }

    /**
     * Документы/Создать документ
     */
    public NewDocumentPage goToNewDocument() {
        menuClicker(menuDocument, createDoc);
        return page(NewDocumentPage.class);
    }

    /**
     * Переход в меню - Администрирование/Подразделения
     */
    public CreateDepartmentPage goToDepartments() {
        subMenuClicker(instrMenu, administration, menuUsers);
        return page(CreateDepartmentPage.class);
    }

    /**
     * Инициализация - Администрирование/Подразделения (Пользователи)
     */
    public CreateUsersPage initializationUsersPage() {
        return page(CreateUsersPage.class);
    }

    /**
     * Администрирование - Информация о системе
     */
    public SystemInformationPage goToSystemInfo() {
        subMenuClicker(instrMenu, administration, tester);
        return page(SystemInformationPage.class);
    }

    /**
     * Переходим - Поисковая система
     */
    public SearchAdminPage goToSearchSystemPage() {
        subMenuClicker(instrMenu, administration, searchSystem);
        return page(SearchAdminPage.class);

    }

    /**
     * Выход из системы
     */
    @Override
    public void logout() {
        goToTopFrem();
        $(Logout).shouldBe(Condition.visible).click();
        $(By.cssSelector("#login")).shouldBe(Condition.visible);
        $(By.cssSelector("#pass")).shouldBe(Condition.visible);

    }

    /**
     * Проверяем отображение меню на внутренней странице
     *
     * @return information about the number of the menu on the main page
     */
    @Override
    public boolean hasMenuUserComplete() {
        $(By.xpath("//div[@id='menu']//div[@id='panel_2']")).waitUntil(Condition.present, 15000);
        menuElements.shouldHaveSize(8);
        return !menuElements.isEmpty();
    }

    /**
     * Проверяем, что текущий пользователь Workflow. Отсутствует п.м. Поиск документов и Документы
     *
     * @return InternalPage
     */
    public InternalPage checkUserWorkflow() {
        goToTopFrem();
        $(By.xpath("//*[@id='doc-search']/a")).shouldNotBe(Condition.visible);
        menuDocument.shouldNotBe(Condition.visible);
        return this;

    }

    /**
     * Проверяем, что текущий пользователь Docflow. Отсутствует п.м. Создать задачу
     *
     * @return InternalPage
     */
    public InternalPage checkUserDocflow() {
        goToTopFrem();
        menuTask.click();
        assertFalse(isElementPresent(By.xpath("//*[@id='L_INFORMER_CREATETASK-menupoint']")));
        return this;

    }


    /**
     * Переход в меню - Настройки/Мои реквизиты
     */
    public PwdPage goToPwd() {
        subMenuClicker(instrMenu, menuSettings, menuMyOptions);
        return page(PwdPage.class);
    }


    /**
     * Ожидания появления объектов ОМ - Сообщение; -Календарь; -Библиотека
     */
    public InternalPage ensurePageLoaded() {
        $(By.id("mes")).shouldBe(Condition.present);
        $(By.id("col")).shouldBe(Condition.present);
        $(By.id("lib")).shouldBe(Condition.visible);
        return this;
    }

}
