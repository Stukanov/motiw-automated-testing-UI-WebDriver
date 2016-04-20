package ru.st.selenium.pages.pagesweb.Tasks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.WebLogic.Task.FolderLogic;
import ru.st.selenium.logicinterface.WebLogic.Task.UnionTasksLogic;
import ru.st.selenium.model.Tasks.Folder;
import ru.st.selenium.model.Tasks.Task;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pagesteps.TaskSteps.UnionMessagePageSteps;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import static ru.st.selenium.utils.ChecksUtil.isElementPresent;
import static ru.st.selenium.utils.WindowsUtil.NewWindowOpen;

/**
 * Шаги - отчет - грид задач - ЗАДАЧИ / ЗАДАЧИ
 */
public class UnionTasksPageSteps extends BasePage implements UnionTasksLogic, FolderLogic {


    /*
     * Панель списка группировок на ПУГЗ (панель управления группировкой задач)
     */
    @FindBy(xpath = "//tr[@class='x-toolbar-left-row'][ancestor::div[@id='tree_folders_wrapper']]//input")
    private SelenideElement panelGroupingTasks;

    /*
     * ------------------------------------------------------------------------------------------------------группировка - Папка
     */
    @FindBy(xpath = "(//div[contains(@id,'ext-gen') and contains(@style,'visibility: visible')]//div[contains(@class,'x-combo-list-item')])[14]")
    private SelenideElement groupingFolder;

    /*
     * Коллекция иерархий папок (Папки в гриде)
     */
    @FindBy(xpath = "//div[@id='tree_folders']//div[contains(@id,'extdd')]//a//span")
    private ElementsCollection folderInTheGroup;

    /*
     * Добавить - Папка
     */
    @FindBy(xpath = "(//div[@id='importfolder' and contains(@style,'visibility: visible')]//span)[1]")
    private SelenideElement addFolder;

    /*
     * Редактировать - Папка
     */
    @FindBy(xpath = "(//div[@id='importfolder' and contains(@style,'visibility: visible')]//span)[1]")
    private SelenideElement editFolder;

    /*
     * Использовать фильтр
     */
    @FindBy(css = "#tfolder_is_smart-displayEl")
    private SelenideElement checkUseFilter;

    /*
     * Общая папка
     */
    @FindBy(css = "#tfolder_is_shared-displayEl")
    private SelenideElement checkFolderSharedFilter;

    /*
     * Добавить всем
     */
    @FindBy(css = "#tfolder_addforall-displayEl")
    private SelenideElement checkFolderAddForAll;

    /*
     * Добавлять для новых пользователей
     */
    @FindBy(css = "#tfolder_fornewuser-displayEl")
    private SelenideElement checkAddSharedFolderForNewUsers;

    /*
     * ОК (Подтверждение изменений в форме редактирования папки)
     */
    @FindBy(xpath = "(//*[contains(@id,'btnIconEl') and contains(@id,'button')])[2]/..")
    private SelenideElement saveСhangesInTheCustomFolder;

    /*
     * Элемент отображения дочерних узлов папок
     */
    @FindBy(xpath = "//img[contains(@class,'x-tree-ec-icon') and contains(@class,'plus')]")
    private SelenideElement plusSubsites;

    /*
     * Название папки
     */
    @FindBy(xpath = "//input[contains(@id,'nameedit') and @type='text']")
    private SelenideElement folderName;


    /**
     * Проверка загрузки страницы
     */
    public UnionTasksPageSteps ensurePageLoaded() {
        $(By.xpath("//div[@id='tree_folders_wrapper']")).shouldBe(present); // Панель ПУГЗ
        $(By.xpath("//td[@class='x-toolbar-left'][ancestor::div[contains(@id,'grid-repuniontasks')]]"))
                .shouldBe(present); // Панель Навигации по гриду
        return this;
    }

    /**
     * Поиск задачи
     */
    public UnionTasksPageSteps findTask(String taskName) {
        initializationInternalPage().searchFacilityOnTheGrid(taskName);
        return this;
    }

    /**
     * Ожидание маски грида - Задачи/Задачи
     */
    public UnionTasksPageSteps waitForMask() {
        $(By.xpath("//div[contains(@id,'ext-gen') and @class='ext-el-mask']")).shouldNotBe(present, visible);
        sleep(500);
        return this;
    }

    /**
     * Открытие формы задачи в гриде по гриппировке Папка через поиск по гриду
     * Задачи/Задачи
     *
     * @param task   Название задачи
     * @param folder папка для поиска задачи в гриде
     */
    @Override
    public void openExistingTaskInTheFolderThroughTheSearch(Task task, Folder folder) {
        waitForMask();
        ensurePageLoaded();
        $(By.xpath("//li[@class='x-tree-node']//li//b[contains(text(),'" + parseNameFolder(folder.getNameFolder())[0] + "')]")).click();
        getFrameTop();
        findTask(task.getTaskName());
        getFrameFlow();
        waitForMask();
        $$(By.xpath("//*[@class='x-grid3-body']/div//td//a[contains(@href,'/user/unionmessage') and (text()='" + task.getTaskName() + "')]"))
                .first().shouldBe(visible);
        $(By.xpath("//a[text()='" + task.getTaskName() + "']")).sendKeys(NewWindowOpen()); // Открытие найденой задачи в новом окне
    }

    /**
     * Открытие формы задачи в гриде по гриппировке Папка
     *
     * @param task   Название задачи
     * @param folder папка для поиска задачи в гриде
     */
    @Override
    public void openAnExistingTaskInFolder(Task task, Folder folder) {
        waitForMask();
        ensurePageLoaded();
        $(By.xpath("//li[@class='x-tree-node']//li//b[contains(text(),'" + parseNameFolder(folder.getNameFolder())[0] + "')]")).click();
        waitForMask();
        $$(By.xpath("//*[@class='x-grid3-body']/div//td//a[contains(@href,'/user/unionmessage') and (text()='" + task.getTaskName() + "')]"))
                .first().shouldBe(visible);
        $(By.xpath("//a[text()='" + task.getTaskName() + "']")).sendKeys(NewWindowOpen()); // Открытие найденой задачи в новом окне
    }


    /**
     * Инициализируем форму задачи
     */
    public UnionMessagePageSteps initializationUnionMessagePage() {
        return page(UnionMessagePageSteps.class);
    }

    /**
     * Инициализируем внутренюю стр. (InternalPage)
     */
    public InternalPage initializationInternalPage() {
        return page(InternalPage.class);
    }

    /**
     * Вводим имя папки
     *
     * @param nameFolder зн-ие для формирования имени папки
     */
    public UnionTasksPageSteps selFolderName(String nameFolder) {
        folderName.clear();
        folderName.setValue(nameFolder);
        return this;
    }

    /**
     * Метод помогающий подготовить интерфейс к взаимодействию с объектом - Папка
     * (Проверка загрузки страницы, раскрытие всех элементов дерева подразделений)
     */
    public void beforeAddFolder() {
        ensurePageLoaded();
        selectTheGroupInTheGrid(panelGroupingTasks, groupingFolder);
        unwrapAllNodesFolder();
        folderInTheGroup.first().shouldBe(present);
    }

    /**
     * Развернем все ветви папок (группировка - Папка)
     */
    public UnionTasksPageSteps unwrapAllNodesFolder() {
        try {
            while (isElementPresent(By
                    .xpath("//img[contains(@class,'x-tree-ec-icon') and contains(@class,'plus')]")))
                plusSubsites.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Выбираем группировку на ПУГЗ (панель управления группировкой задач
     * )
     *
     * @param panelGrouping элемент панели ПУГЗ
     * @param grouping      выбираемая группировка
     */
    @Override
    public void selectTheGroupInTheGrid(SelenideElement panelGrouping, SelenideElement grouping) {
        waitForMask();
        panelGrouping.click();
        $$(By.xpath("//div[contains(@id,'ext-gen') and contains(@style,'visibility: visible')]//div[contains(@class,'x-combo-list-item')]"))
                .shouldHaveSize(19); // проверяем, что ПУГЗ имеет 19 значений группировок
        // TODO 0004 - добавить проверку для пользователя WORKFLOW - у него 17 пунктов
        grouping.click(); // выбрать группировка - Папка
        waitForMask();
        $(By.xpath("//div[contains(@id,'extdd')]//img[2]")).isImage();

    }

    /**
     * Выбираем папку в иерархии папок по Имени
     *
     * @param folder атрибуты папки
     * @return
     */
    public void selectTheParentFolder(Folder folder) {
        $(By.xpath("//div[@id='tree_folders']//div[contains(@id,'extdd')]//a//span/b[contains(text(),'"
                + parseNameFolder(folder.getNameFolder())[0] + "')]")).shouldBe(visible, present).click();
        waitForMask();
        sleep(1000);
        $(By.xpath("//div[@id='tree_folders']//div[contains(@id,'extdd')]//a//span/b[contains(text(),'"
                + parseNameFolder(folder.getNameFolder())[0] + "')]")).shouldBe(visible, present).contextClick();
    }

    /**
     * Добавление объекта - Папка
     *
     * @param folders кол-во передаваемых папок с атрибутами (настройки СП (смарт-папки); ОП (общие папки)..)
     */
    @Override
    public void addFolders(Folder[] folders) {
        if (folders != null) {
            for (Folder folder : folders) {
                if (folder.getParentFolder() != null) {
                    selectTheParentFolder(folder.getParentFolder()); // Выбираем родительскую папку папку и выводим КМ для взаимодействия с папкой
                } else {
                    waitForMask();
                    sleep(1000);
                    folderInTheGroup.first().contextClick();
                }
                addFolder.click(); // Добавить папку
                getFrameFormFolder(); // уходим во фрейм окна - Редактирование папки
                selFolderName(folder.getNameFolder());
                if (folder.isUseFilter() & folder.isChooseRelativeValue()) {
                    checkUseFilter.click();
                    setTheConditionOfFiltration(folder.getFilterField(), folder.isChooseRelativeValue());
                }
                if (folder.isSharedFolder()) checkFolderSharedFilter.click();
                if (folder.isAddSharedFolderForAll()) checkFolderAddForAll.click();
                if (folder.isAddSharedFolderForNewUsers()) checkAddSharedFolderForNewUsers.click();

                saveСhangesInTheCustomFolder.click();
                getFrameTop();
                getFrameFlow();
                checkDisplayCreateAFolderInTheGrid(folder.getNameFolder(), folder.isUseFilter()); // Проверяем созданные Папки

            }
        }
    }

    /**
     * Формируем условие фильтра - Начало (относительное значение == Сегодня)
     *
     * @param field                передаваемое навание поля для формирования условия фильтрации
     * @param relativeImportanceOf относительное зн-ие для условия папки
     */
    public UnionTasksPageSteps setTheConditionOfFiltration(String field, boolean relativeImportanceOf) {
        $(By.xpath("//table[contains(@id,'treeview')][2]//td[1]//span")).click();
        // Выбираем поле для фильтрации
        $(By.xpath("//div[@id='sffieldcombochooser']//input")).clear();
        $(By.xpath("//div[@id='sffieldcombochooser']//input")).setValue(field);
        $(By.xpath("//table[contains(@id,'treeview')][2]//td[3]//div")).click();   // Выбор поля - Значение
        if (relativeImportanceOf) {
            $(By.xpath("//input[contains(@id,'checkbox') and (@type='button') and (@role='checkbox')]")).click();
        }
        return this;
    }

    /**
     * Проверяем отображение созданных папок на Панели управления группировкой задач (ПУГЗ)
     *
     * @param folder     передаем название папки
     * @param useAFilter передаем параметр установленного зн-ия - Использовать фильтр
     */
    public void checkDisplayCreateAFolderInTheGrid(String folder, boolean useAFilter) {
        if (useAFilter) {
            $(By.xpath("//div[@id='tree_folders']//div[contains(@id,'extdd')]//a//span/b[contains(text(),'"
                    + parseNameFolder(folder)[0] + "')]")).waitUntil(visible, 10000);
            $(By.xpath("//img[contains(@src,'smart')]/..//a//b[contains(text(),'" +
                    parseNameFolder(folder)[0] + "')]//../../../../div//img[2]")).waitUntil(visible, 10000).isImage();
        } else
            $(By.xpath("//div[@id='tree_folders']//div[contains(@id,'extdd')]//a//span/b[contains(text(),'" + parseNameFolder(folder)[0] + "')]"))
                    .waitUntil(visible, 10000);

    }

    public void checkTheMapASharedFolderFromTheNewlyCreatedUser(Folder folder) {
        beforeAddFolder();
        checkDisplayCreateAFolderInTheGrid(folder.getNameFolder(), folder.isUseFilter()); // Проверяем созданные Папки
    }

    /**
     * Метод удаляет (0/0/0) в названии папки
     * -при необходимости можно дабивать split();
     *
     * @param folder передаваемое значение названия папки (имя целиком со счетчиками)
     */
    private String[] parseNameFolder(String folder) {
        if (folder == null)
            return null;
        String[] splitNameFolderGrid;
        String nameFolderParse = $(By.xpath("//div[@id='tree_folders']//div[contains(@id,'extdd')]//a//span/b[contains(text(),'"
                + folder + "')]"))
                .getText();
        splitNameFolderGrid = nameFolderParse.split("[(/0)]");
        return splitNameFolderGrid;
    }

    /**
     * Редактирование папки
     */
    @Override
    public void editFolder(Folder folder) {

    }

    /**
     * Удаление папки
     */
    @Override
    public void deleteFolder(Folder folder) {

    }
}