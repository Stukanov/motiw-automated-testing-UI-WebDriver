package ru.st.selenium.pages.pagesweb.Tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.Task.UnionTasksLogic;
import ru.st.selenium.model.Task.Task;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Страница - Задачи/Задачи
 */
public class UnionTasksPage extends Page implements UnionTasksLogic {


    /**
     * Основной фрейм
     */
    @FindBy(id = "flow")
    private SelenideElement Frame;

    /**
     * Первая запись грида
     */
    @FindBy(xpath = "//*[@class='x-grid3-body']/div[1]")
    private SelenideElement firstRecord;


    /**
     * Переход в фрейм
     */
    public UnionTasksPage goToFrame() {
        getWebDriver().switchTo().frame(Frame);
        return this;
    }

    /**
     * Проверка загрузки страницы
     */
    public UnionTasksPage ensurePageLoaded() {
        $(firstRecord).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Поиск задачи
     */
    public UnionTasksPage findTask(String taskName) {
        initializationInternalPage().searchField(taskName);
        return this;
    }

    /**
     * Ожидание маски раздела
     */
    public UnionTasksPage waitForMask() {
        $(By.xpath("//*[@class='ext-el-mask']")).shouldNotBe(Condition.visible);
        return this;
    }

    /**
     * Открытие найденой задачи в новом окне
     */
    public UnionTasksPage openTask(String taskName) {
        $(By.xpath("//a[text()='" + taskName + "']")).sendKeys(NewWindowOpen);
        return this;
    }

    /**
     * Открытие формы задачи в гриде - Задачи/Задачи
     */
    @Override
    public void openTask(Task task) {
        ensurePageLoaded();
        goToTopFrem();
        findTask(task.getTaskName())
                .goToFrame()
                .waitForMask()
                .openTask(task.getTaskName());
    }

    /**
     * Инициализируем форму задачи
     * @return
     */
    public UnionMessagePage initializationUnionMessagePage(){
        return page(UnionMessagePage.class);
    }

    /**
     * Инициализируем внутренюю стр. (InternalPage)
     * @return
     */
    public InternalPage initializationInternalPage(){
        return page(InternalPage.class);
    }

}