package ru.motiw.web.elements.elementspagespda;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagespda.Task.NewTaskPagePDA;
import ru.motiw.web.elements.elementspagespda.Task.TasksReportsPagePDA;
import ru.motiw.web.logicinterface.WebLogic.BaseInternalLogic;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Condition.*;


/*
 * Импорт для использования методов самого Selenium, в т.ч. объект WebDriver.
 * Дальше можно спокойно использовать метод getWebDriver(), который возвращает объект WebDriver.
 * пример, getWebDriver().findElement(By.id("username"));
 */
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Внутреняя страница системы
 */
public class InternalPagePDA extends BasePage implements BaseInternalLogic {

    /*
     * Ссылки на все пункты меню
     */
    @FindBy(xpath = "//a[@class='ui-link-inherit']")
    private ElementsCollection menuElements;

    /*
     * Выход из системы
     */
    @FindBy(xpath = "//a[contains(@href, '/logout/')]")
    private SelenideElement logout;

    /*
     * Задачи/Задачи
     */
    @FindBy(xpath = "//a[@href='/tasksreports/']")
    private SelenideElement menuTaskReports;

    /*
     * Создать задачу
     */
    @FindBy(xpath = "//a[contains(@href, '/edittask/newtask')]")
    private SelenideElement createTask;

    /*
     * Помощь
     */
    @FindBy(xpath = "//li[@class='help-but']/a")
    private SelenideElement helpHtml;

    /*
     * Настройки
     */
    @FindBy(xpath = "//li[@class='option-but']/a")
    private SelenideElement options;

    /*
     * Сегодня
     */
    @FindBy(xpath = "//a[contains(@href, '/today/')]")
    private SelenideElement today;

    /*
     * Документы
     */
    @FindBy(xpath = "//a[contains(@href, '/documents/')]")
    private SelenideElement documents;

    /*
     * Домой
     */
    @FindBy(xpath = "//a/img[contains(@src,'home')]")
    private SelenideElement home;

    /*
     * Поле - Поиск
     */
    @FindBy(xpath = "//input[@name='search']")
    private SelenideElement search;


    /**
     * Домой (возврат на основную стр-цу)
     */
    public BasePage goToHome() {
        home.click();
        return this;
    }


    /**
     * Переходим в форму - Создать задачу
     *
     * @return NewTaskPagePDA results page instance
     */
    public NewTaskPagePDA goToCreateTask() {
        createTask.click();
        $(By.xpath("//input[contains(@class,'button') and @name='next2']")).shouldHave(visible);
        return page(NewTaskPagePDA.class);
    }

    /**
     * Переходим в форму - Помощь
     *
     * @return HelpHtmlPagePDA results page instance
     */
    public HelpHtmlPagePDA goToHelpHtml() {
        helpHtml.click();
        $(By.xpath("//div[@id='mainblock']/ul/li[1]/div[@class='save_button']")).shouldHave(visible);
        return page(HelpHtmlPagePDA.class);
    }

    /**
     * Переходим в грид - Задачи
     *
     * @return TasksReportsPagePDA results page instance
     */
    public TasksReportsPagePDA goToTaskReports() {
        menuTaskReports.click();
        $(By.xpath("//div[@id='mainblock']//a//span[text()]")).shouldBe(visible);
        return page(TasksReportsPagePDA.class);
    }

    /**
     * Переходим в грид - Настройки
     *
     * @return OptionsPagePDA results page instance
     */
    public OptionsPagePDA goToOptions() {
        options.click();
        $(By.xpath("(//input[@type='submit'])[2]")).shouldBe(present);
        return page(OptionsPagePDA.class);
    }

    /**
     * Переходим в грид - Сегодня
     *
     * @return Today results page instance
     */
    public TodayPagePDA goToToday() {
        today.click();
        $(By.xpath("//div[@id='headertop']//ul/a[2]/li")).shouldBe(present);
        return page(TodayPagePDA.class);
    }

    /**
     * Переходим в грид - Документы
     *
     * @return DocumentsPagePDA
     */
    public DocumentsPagePDA goToDocuments() {
        documents.click();
        $$(By.xpath("//div[@class='ui-navbar ui-navbar-noicons']//li")).shouldBe(size(3));
        return page(DocumentsPagePDA.class);
    }

    /**
     * Переходим в грид - Поиск
     *
     * @return results instance page Search
     */
    public SearchPagePDA goToSearch() {
        search.pressEnter();
        $(By.xpath("//div[@id='b_filter_dialog']/img")).shouldBe(present, visible);
        $(By.xpath("//img[@class='menu_help_image']")).shouldBe(present, visible);
        return page(SearchPagePDA.class);
    }

    /**
     * Проверяем отображение меню на внутренней странице
     *
     * @return information about the number of the menu on the main page
     */
    @Override
    public boolean hasMenuUserComplete() {
        // TODO - Добавить проверку если Страртовать С - установлена НЕ раздел задачи
        menuElements.shouldHaveSize(4);
        return !menuElements.isEmpty();
    }

    /**
     * Универсальный выход из системы (где бы ненаходился пользователь)
     *
     * @return LoginPagePDA
     */
    @Override
    public void logout() {
        try {
            (new WebDriverWait(getWebDriver(), 0, 50))
                    .until(ExpectedConditions.presenceOfElementLocated(By
                            .xpath("//a[contains(@href, '/logout/')]"))).click();
        } catch (WebDriverException e) {
            goToHome();
            logout.waitUntil(appears, 4);
            logout.click();
        }
        $("#center>form>div>img").shouldBe(visible);
        $(By.cssSelector("#login")).shouldHave(appears);
        $(By.cssSelector("#pass")).shouldHave(appears);
        $(By.cssSelector("input[name='logon']")).getCssValue("Вход");
    }
}
