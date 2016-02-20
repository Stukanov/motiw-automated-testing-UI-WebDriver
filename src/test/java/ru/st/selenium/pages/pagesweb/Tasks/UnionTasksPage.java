package ru.st.selenium.pages.pagesweb.Tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.WebLogic.Task.FolderLogic;
import ru.st.selenium.logicinterface.WebLogic.Task.UnionTasksLogic;
import ru.st.selenium.model.Task.Folder;
import ru.st.selenium.model.Task.Task;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;

import static com.codeborne.selenide.Selenide.*;

import static ru.st.selenium.utils.ChecksUtil.isElementPresent;
import static ru.st.selenium.utils.WindowsUtil.NewWindowOpen;

/**
 * Страница - Задачи/Задачи
 */
public class UnionTasksPage extends BasePage implements UnionTasksLogic, FolderLogic {


    /*
     * Основной фрейм
     */
    @FindBy(id = "flow")
    private SelenideElement frame;

    /*
     * Фрейм - форма редактирования Папки
     */
    @FindBy(xpath = "//iframe[contains(@id,'ext-comp') and contains(@src,'/user/smart_folder')]")
    private SelenideElement frameFilterWindow;

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
     * Иерархия папок (папки в гриде)
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
    @FindBy(css = "#tfolder_is_smart-inputEl")
    private SelenideElement checkUseFilter;

    /*
     * ОК (Подтверждение изменений в форме редактирования папки)
     */
    @FindBy(xpath = "(//*[contains(@id,'btnIconEl') and contains(@id,'button')])[2]")
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
     * Переход в фрейм
     */
    public UnionTasksPage goToFrame() {
        switchTo().frame(frame);
        return this;
    }

    /**
     * Переход в фрейм - форма редактирования Папки
     */
    public UnionTasksPage goToFrameFormFolder() {
        switchTo().frame(frameFilterWindow);
        return this;
    }

    /**
     * Проверка загрузки страницы
     */
    public UnionTasksPage ensurePageLoaded() {
        $(By.xpath("//div[@id='tree_folders_wrapper']")).shouldBe(Condition.present); // Панель ПУГЗ
        $(By.xpath("//td[@class='x-toolbar-left'][ancestor::div[contains(@id,'grid-repuniontasks')]]"))
                .shouldBe(Condition.present); // Панель Навигации по гриду

        return this;
    }

    /**
     * Поиск задачи
     */
    public UnionTasksPage findTask(String taskName) {
        initializationInternalPage().searchFacilityOnTheGrid(taskName);
        return this;
    }

    /**
     * Ожидание маски грида - Задачи/Задачи
     */
    public UnionTasksPage waitForMask() {
        $(By.xpath("//div[contains(@id,'ext-gen') and @class='ext-el-mask']")).shouldNotBe(Condition.present);
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
        $(By.xpath("//li[@class='x-tree-node']//li//b[contains(text(),'" + parseNameFolder(folder.getNameFolder()) + "')]")).click();
        goToTopFrem();
        findTask(task.getTaskName());
        goToFrame();
        waitForMask();
        $$(By.xpath("//*[@class='x-grid3-body']/div//td//a[contains(@href,'/user/unionmessage') and (text()='" + task.getTaskName() + "')]"))
                .first().shouldBe(Condition.visible);
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
        $(By.xpath("//li[@class='x-tree-node']//li//b[contains(text(),'" + parseNameFolder(folder.getNameFolder()) + "')]")).click();
        waitForMask();
        $$(By.xpath("//*[@class='x-grid3-body']/div//td//a[contains(@href,'/user/unionmessage') and (text()='" + task.getTaskName() + "')]"))
                .first().shouldBe(Condition.visible);
        $(By.xpath("//a[text()='" + task.getTaskName() + "']")).sendKeys(NewWindowOpen()); // Открытие найденой задачи в новом окне
    }


    /**
     * Инициализируем форму задачи
     */
    public UnionMessagePage initializationUnionMessagePage() {
        return page(UnionMessagePage.class);
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
    public UnionTasksPage selFolderName(String nameFolder) {
        folderName.clear();
        folderName.setValue(nameFolder);
        return this;
    }

    /**
     * Развернем все ветви папок (группировка - Папка)
     */
    public UnionTasksPage unwrapAllNodesFolder() {
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
        ensurePageLoaded();
        waitForMask();
        panelGrouping.click();
        $$(By.xpath("//div[contains(@id,'ext-gen') and contains(@style,'visibility: visible')]//div[contains(@class,'x-combo-list-item')]"))
                .shouldHaveSize(19); // проверяем, что ПУГЗ имеет 19 значений группировок
        grouping.click(); // выбрать группировка - Папка
        waitForMask();
    }

    /**
     * Добавление объекта - Папка
     *
     * @param folders кол-во передаваемых папок с атрибутами (настройки СП (смарт-папки); ОП (общие папки)..)
     */
    @Override
    public void addFolders(Folder[] folders) {
        selectTheGroupInTheGrid(panelGroupingTasks, groupingFolder);
        unwrapAllNodesFolder();
        folderInTheGroup.first().shouldBe(Condition.present);
        if (folders != null) {
            for (Folder folder : folders) {
                sleep(1000);
                waitForMask();
                folderInTheGroup.first().contextClick();
                waitForMask();
                addFolder.click();
                goToFrameFormFolder();
                selFolderName(folder.getNameFolder());
                if (folder.isUseFilter()) {
                    setTheConditionOfFiltration("Начало", "Относительное значение - Сегодня");
                }
                saveСhangesInTheCustomFolder.click();
                goToTopFrem();
                goToFrame();
                checkDisplayCreateAFolderInTheGrid(folder.getNameFolder()); // Проверяем создание папки в гриде

                //TODO PARENT (создание иерархических папок)!!!!
            }
        }
    }

    /**
     * Формируем условие фильтра - Начало (относительное значение == Сегодня)
     *
     * @param field                передаваемое навание поля для формирования условия фильтрации
     * @param relativeImportanceOf относительное зн-ие для условия папки
     */
    public UnionTasksPage setTheConditionOfFiltration(String field, String relativeImportanceOf) {
        checkUseFilter.click();
        $(By.xpath("//*[contains(@id,'ext-gen')][text()]")).shouldBe(Condition.visible);
        $(By.xpath("//tr[2]/td/div/span")).click();
        // Выбираем поле для фильтрации
        $(By.xpath("//div[@id='sffieldcombochooser']//input")).clear();
        $(By.xpath("//div[@id='sffieldcombochooser']//input")).setValue(field);
        $(By.xpath("//tr[contains(@id,'treeview')][2]/td[3]/div")).click();   // Выбор поля - Значение
        if (field.equals("Начало") & (relativeImportanceOf.equals("Относительное значение - Сегодня"))) {
            $(By.xpath("//input[contains(@id,'checkbox') and (@type='button') and (@role='checkbox')]")).click();
        } else {
            $(By.xpath("//tr[contains(@id,'treeview')][2]/td[3]/div")).click();
            $(By.xpath("//input[contains(@id,'textfield') and (@role='textbox')]")).setValue(field); // поле ввода - Значение
        }
        return this;
    }

    /**
     * Проверяем отображение созданной папки в гриде папок
     */
    public UnionTasksPage checkDisplayCreateAFolderInTheGrid(String folder) {
        $(By.xpath("//div[@id='tree_folders']//div[contains(@id,'extdd')]//a//span/b[contains(text(),'" + parseNameFolder(folder) + "')]")).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Метод удаляет (0/0/0) в названии папки
     * -при необходимости можно дабивать split();
     *
     * @param folder передаваемое значение названия папки (имя целиком со счетчиками)
     */
    private String parseNameFolder(String folder) {
        String nameFolderParse = $(By.xpath("//div[@id='tree_folders']//div[contains(@id,'extdd')]//a//span/b[contains(text(),'" + folder + "')]"))
                .getText();
        nameFolderParse = nameFolderParse.substring(0, 17);
        return nameFolderParse;
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