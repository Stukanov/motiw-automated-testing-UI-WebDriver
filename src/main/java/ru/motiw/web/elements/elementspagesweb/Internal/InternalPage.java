package ru.motiw.web.elements.elementspagesweb.Internal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.elements.elementspagesweb.Administration.*;
import ru.motiw.web.elements.elementspagesweb.Administration.Users.DepartmentElements;
import ru.motiw.web.logicinterface.WebLogic.BaseInternalLogic;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.DictionaryEditorPage;
import ru.motiw.web.elements.elementspagesweb.DocflowAdministration.GridDocRegisterCardsPage;
import ru.motiw.web.elements.elementspagesweb.Documents.NewDocumentPage;
import ru.motiw.web.elements.elementspagesweb.Options.PwdPage;
import ru.motiw.web.elements.elementspagesweb.Tasks.UnionTasks.UnionTasksElements;
import ru.motiw.web.steps.Administration.SearchSystemPageSteps;
import ru.motiw.web.steps.Administration.Users.UsersPageSteps;
import ru.motiw.web.steps.Tasks.UnionMessageNewPageSteps;
import ru.motiw.web.steps.Tasks.UnionTasksPageSteps;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static ru.motiw.utils.ChecksUtil.isElementPresent;

/**
 * Внутренняя страница системы (ОМ - Основное меню)
 */
public class InternalPage extends BasePage implements BaseInternalLogic {

    UnionTasksElements unionTasksElements = page(UnionTasksElements.class);
    UnionTasksPageSteps unionTasksPageSteps = new UnionTasksPageSteps();

    /*
     * Ссылки на все пункты меню
     */
    @FindBy(xpath = "//div[@id='left-panel'][ancestor::div[@id='menu']]//div[contains(@class,'menu-point') and not(@class='menu-point-hidden')]//div[@class='caption']")
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
    private SelenideElement libMenu;

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
    private SelenideElement menuAdministration;

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
    private SelenideElement menuDirectories;

    /*
     * Инструменты/Администрирование/Типы задач
     */
    @FindBy(id = "L_MENU_ADMIN_TYPE_TASK-menupoint")
    private SelenideElement menuTypeTask;

    /*
     * Инструменты/Администрирование/Настройки системы
     */
    @FindBy(css = "#x-menu-el-L_MENU_SYSTEMOPTIONS-menupoint")
    private SelenideElement menuSystemOptions;



    /*
     * =======================================================================================================АДМИНИСТРИРОВАНИЕ ДО
     */

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


    /**
     * Поиск объекта в гриде
     *
     * @param serachstring передаваемый шаблон поиска
     * @return DepartmentElements
     */
    public InternalPage searchFacilityOnTheGrid(String serachstring) {
        search.clear();
        search.setValue(serachstring);
        search.pressEnter();
        sleep(1000);
        return this;
    }

    /**
     * Метод 1-х уровневой навигации
     *
     * @param firstclick передаваемый первый клик на элемент меню
     */
    private void oneTierNavigation(SelenideElement firstclick) {
        getFrameTop();
        firstclick.click();
        getFrameFlow();
    }

    /**
     * Метод 2-х уровневой навигации
     *
     * @param firstclick  передаваемый первый клик на элемент меню
     * @param secondclick вторая навигация на элемент меню
     */
    private void twoTierNavigation(SelenideElement firstclick, SelenideElement secondclick) {
        getFrameTop();
        firstclick.click();
        secondclick.click();
        getFrameFlow();
    }

    /**
     * Метод 3-х уровневой навигации
     *
     * @param firstclick  передаваемый первый клик на элемент меню
     * @param secondclick вторая навигация на элемент меню
     * @param thirdclick  третий клик на эдемент меню
     */
    private void threeTierNavigation(SelenideElement firstclick, SelenideElement secondclick, SelenideElement thirdclick) {
        getFrameTop();
        firstclick.click();
        actions().moveToElement(secondclick).perform();
        $(thirdclick).shouldBe(Condition.visible);
        actions().moveToElement(thirdclick).perform();
        thirdclick.click();
        getFrameFlow();
    }

    /**
     * Переход в Задачи/Создать задачу
     */
    public UnionMessageNewPageSteps goToUnionMessageNew() {
        twoTierNavigation(menuTask, createTask);
        return page(UnionMessageNewPageSteps.class);
    }

    /**
     * Переход в Задачи/Задачи
     */
    public UnionTasksPageSteps goToUnionTasks() {
        twoTierNavigation(menuTask, tasks);
        return page(UnionTasksPageSteps.class);
    }

    /**
     * Переходим в раздел - Библиотека
     * @return LibraryPage
     */
    public LibraryPage goToLibrary() {
        oneTierNavigation(libMenu);
        return page(LibraryPage.class);
    }

    /**
     * Переход в меню - Администрирование ДО/Регистрационные карточки документов
     */
    public GridDocRegisterCardsPage goToGridDocRegisterCards() {
        threeTierNavigation(instrMenu, docAdministrationMenu, registerCardsMenu);
        return page(GridDocRegisterCardsPage.class);

    }

    /**
     * Переход в меню - Администрирование ДО/Редактор словарей
     */
    public DictionaryEditorPage goToDictionaryEditor() {
        threeTierNavigation(instrMenu, docAdministrationMenu, dictionaryEditorMenu);
        return page(DictionaryEditorPage.class);
    }

    /**
     * Переход в меню - Администрирование/Справочники
     */
    public TaskTypeListObjectPage goToDirectories() {
        threeTierNavigation(instrMenu, menuAdministration, menuDirectories);
        return page(TaskTypeListObjectPage.class);

    }

    /**
     * Переход в меню - Администрирование/Типы таблиц
     */
    public TaskTypeListObjectPage goToTypesOfTables() {
        threeTierNavigation(instrMenu, menuAdministration, menuTables);
        return page(TaskTypeListObjectPage.class);

    }

    /**
     * Переход в меню - Администрирование/Типы таблиц
     */
    public TaskTypeListObjectPage goToTaskTypes() {
        threeTierNavigation(instrMenu, menuAdministration, menuTypeTask);
        return page(TaskTypeListObjectPage.class);

    }

    /**
     * Документы/Создать документ
     */
    public NewDocumentPage goToNewDocument() {
        twoTierNavigation(menuDocument, createDoc);
        return page(NewDocumentPage.class);
    }

    /**
     * Переход в меню - Администрирование/Подразделения
     */
    public DepartmentElements goToDepartments() {
        threeTierNavigation(instrMenu, menuAdministration, menuUsers);
        return page(DepartmentElements.class);
    }

    /**
     * Инициализация - Администрирование/Подразделения (Пользователи)
     */
    public UsersPageSteps initializationUsersPage() {
        return page(UsersPageSteps.class);
    }

    /**
     * Администрирование - Информация о системе
     */
    public SystemInformationPage goToSystemInfo() {
        threeTierNavigation(instrMenu, menuAdministration, tester);
        return page(SystemInformationPage.class);
    }

    /**
     * Переходим - Поисковая система
     */
    public SearchSystemPageSteps goToSearchSystemPage() {
        threeTierNavigation(instrMenu, menuAdministration, searchSystem);
        return page(SearchSystemPageSteps.class);
    }

    /**
     * Переходим - Настройки системы
     */
    public SystemOptionsPage goToSystemOptionsPage() {
        threeTierNavigation(instrMenu, menuAdministration, menuSystemOptions);
        return page(SystemOptionsPage.class);
    }

    /**
     * Выход из системы
     */
    @Override
    public void logout() {
        getFrameTop();
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
        menuElements.shouldHaveSize(8);
        return !menuElements.isEmpty();
    }

    /**
     * Проверяем, что текущий пользователь Workflow. Отсутствует п.м. Поиск документов и Документы
     *
     * @return InternalPage
     */
    public InternalPage checkUserWorkflow(int countPanelGrouping) {
        getFrameTop();
        $(By.xpath("//*[@id='doc-search']/a")).shouldNotBe(Condition.visible);
        menuDocument.shouldNotBe(Condition.visible);
        goToUnionTasks();
        unionTasksPageSteps.selectTheGroupInTheGridForUserComplete(unionTasksElements.getPanelGroupingTasks(), countPanelGrouping);
        return this;
    }

    /**
     * Проверяем, что текущий пользователь Docflow. Отсутствует п.м. Создать задачу
     *
     * @return InternalPage
     */
    public InternalPage checkUserDocflow() {
        getFrameTop();
        menuTask.click();
        assertFalse(isElementPresent(By.xpath("//*[@id='L_INFORMER_CREATETASK-menupoint']")));
        return this;

    }

    /**
     * Переход в меню - Настройки/Мои реквизиты
     */
    public PwdPage goToPwd() {
        threeTierNavigation(instrMenu, menuSettings, menuMyOptions);
        return page(PwdPage.class);
    }


    /**
     * Метод проверки истенности загрузки внутренней стр.
     */
    public boolean isPageLoadedInternal() {
        getFrameTop();
        try {
            List<SelenideElement> elements = new ArrayList<>();
            for (SelenideElement selenideElement : $$(menuElements)) {
                elements.add(selenideElement);
            }
            elements.get(0).shouldBe(visible).shouldHave(text("Мотив"));
            elements.get(1).shouldBe(visible).shouldHave(text("Задачи"));

            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }


}
