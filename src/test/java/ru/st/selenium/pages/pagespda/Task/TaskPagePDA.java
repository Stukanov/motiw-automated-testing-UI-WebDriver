package ru.st.selenium.pages.pagespda.Task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.model.Tasks.Folder;
import ru.st.selenium.model.Tasks.Task;
import ru.st.selenium.model.Administration.Users.Employee;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.AssertJUnit.assertTrue;

/*
 * Страница - Форма задачи (Лента действий)
 */
public class TaskPagePDA extends TasksReportsPagePDA {

    public static final String FILE = "src/test/java/../resources/attachfiles/hello_world.txt";
    public static final String FILE1 = "src/test/java/../resources/attachfiles/Договор аренды.doc";
    public static final String SUBMIT_BUTTON_ADD_FILE = "input[name='myfile1']";

    /*
    Кнопки в форме "Обычной задач"
     */
    @FindBy(xpath = "//*[@id='buttonDopMenu2']/div//div")
    private ElementsCollection buttonMenu;

    /*
     Сохранить
     */
    @FindBy(css = "div.save_button")
    private SelenideElement save;

    /*
     Завершить выполнение
     */
    @FindBy(css = "div.close_button")
    private SelenideElement completeTask;

    /*
     Лента действий
     */
    @FindBy(css = "#text")
    private SelenideElement action;

    /*
     Добавить файл
     */
    @FindBy(xpath = "//a[@onclick='addFile();']//span[2]")
    private SelenideElement addFile;

    /*
     Добавить файл
     */
    @FindBy(css = "input[name='myfile1']")
    private SelenideElement uploadFile;


    /**
     * Проверяем отображение формы созданной задачи
     *
     * @param task return values of attributes of the task
     */
    public TaskPagePDA openShapeCreatedTask(Task task) {
        $(By.cssSelector("div.save_button")).shouldBe(Condition.present);
        $(By.xpath("//a[contains(text(),'" + task.getTaskName() + "')][ancestor::ul[@class='ui-listview']]")).shouldHave(Condition.visible);
        return this;
    }

    /**
     * Проверяем отображениия кнопок в форме задачи
     *
     */
    public boolean resultsDisplayButtons() {
        buttonMenu.shouldHaveSize(5); // проверяем, что отображается 5 кнопок в форме задачи (Сохранить; Завершить выполнение; Play; Pause; Stop)
        return !buttonMenu.isEmpty();
    }

    /**
     * Открываем форму редактирования задачи (Атрибуты задачи)
     *
     * @param task return values of attributes of the task
     */
    public EditTaskPagePDA openFormEditTask(Task task, Employee user) {
        $(By.xpath("//a[contains(text(),'" + task.getTaskName() + "')][ancestor::ul[@class='ui-listview']]")).click();
        save.shouldBe(Condition.visible);
        $(By.xpath("//span[@name='autor']//a[contains(text(),'" + user.getLastName() + "')]")).shouldBe(Condition.visible);
        return page(EditTaskPagePDA.class);
    }


    /**
     * Добавляем текст в ленту действий
     *
     * @param textAction input text for feed action tasks
     * @return TaskPagePDA
     */
    public TaskPagePDA saveActionsInTheTape(String textAction) {
        $(By.xpath("(//div[@class='menu-line']//a/li)[2]")).click();
        int n = 5;
        while (n > 0) {
            action.setValue(textAction);
            save.click();
            $(By.xpath("//ul[@class='ui-listview']//div/span[text()='" + textAction + "']")).shouldBe(Condition.visible);
            n--;
        }
        return this;
    }

    /**
     * Закрываем задачу
     *
     * @param task return values of attributes of the task
     * @param textAction input text for feed action tasks
     * @param folderTask
     * @return TaskPagePDA
     */
    public TaskPagePDA closeTask(Task task, String textAction, Folder folderTask) {
        checkDisplayTaskGrid(task, folderTask); // Проверяем отображение созданной задачи в гриде Задач
        openTaskInGrid(task); // открываем форму задачи в гриде Задач
        action.setValue(textAction); // пишем действие
        completeTask.click(); // Завершить выполнение
        $(By.xpath("//ul[@class='ui-listview']//div/span[text()='" + textAction + "']")).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Аттачминг файлов в форме задачи
     *
     * @param textAction input text for feed action tasks
     * @return TaskPagePDA
     */
    public TaskPagePDA addAttachFiles(String textAction) {
        for (int i = 0; i < 2; i++) {
            addFile.click();
            File file = $(By.cssSelector(SUBMIT_BUTTON_ADD_FILE))
                    .uploadFile(new File(FILE));
            action.setValue(textAction);
            save.click();
            $(By.xpath("//div[@class='message-file-container'][text()='Файлы:']")).shouldHave(Condition.visible);
            $(By.xpath("//ul[@class='ui-listview']//div/span[text()='" + textAction + "']")).shouldBe(Condition.visible);
            assertTrue(file.exists());
            assertTrue(file.getPath().replace(File.separatorChar, '/').endsWith("src/test/resources/attachfiles/hello_world.txt"));
        }
        for (int a = 0; a < 2; a++) {
            addFile.click();
            File file1 = $(By.cssSelector(SUBMIT_BUTTON_ADD_FILE))
                    .uploadFile(new File(FILE1));
            action.setValue(textAction);
            save.click();
            $(By.xpath("//div[@class='message-file-container'][text()='Файлы:']")).shouldHave(Condition.visible);
            $(By.xpath("//ul[@class='ui-listview']//div/span[text()='" + textAction + "']")).shouldBe(Condition.visible);
            assertTrue(file1.exists());
            assertTrue(file1.getPath().replace(File.separatorChar, '/').endsWith("src/test/resources/attachfiles/Договор аренды.doc"));
        }
        return this;
    }


}
