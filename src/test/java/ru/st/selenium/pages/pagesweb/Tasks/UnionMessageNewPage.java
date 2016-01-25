package ru.st.selenium.pages.pagesweb.Tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.st.selenium.logicinterface.WebLogic.Task.UnionMessageNewLogic;
import ru.st.selenium.model.Task.Checkpoint;
import ru.st.selenium.model.Task.IWG;
import ru.st.selenium.model.Task.Project;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.Task.Task;
import ru.st.selenium.pages.Page;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertTrue;

/**
 * Страница - Задачи/Создать задачу
 */
public class UnionMessageNewPage extends Page implements UnionMessageNewLogic {

    /**
     * Кнопка выбора проекта
     */
    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]")
    private SelenideElement selectProject;

    /**
     * Кнопка Ок в окне оповещения о созданной задаче
     */
    @FindBy(xpath = "//*[contains (@class, 'x-window-plain')]//button")
    private SelenideElement buttonTaskSavedOK;

    /**
     * Кнопка создания нового проекта
     */
    @FindBy(xpath = "//a[contains (@href, 'newproject')]/img")
    private SelenideElement newProject;

    /**
     * Кнопка редактирования описания
     */
    @FindBy(xpath = "//*[contains (@onclick, 'Edit')]")
    private SelenideElement description;

    /**
     * Кнопка сохранения описания
     */
    @FindBy(xpath = "//*[contains (@class,'window-noborder')][contains (@style,'visible')]//td[contains (@class,'cell')][1]")
    private SelenideElement buttonSaveDescription;

    /**
     * Кнопка добавления ИРГ
     */
    @FindBy(xpath = "//*[@id='btnAddIWG']//button")
    private SelenideElement buttonAddIWG;

    /**
     * Область редактирования описания
     */
    @FindBy(css = "body")
    private SelenideElement ckeBody;

    /**
     * Кнопка редактирования авторов
     */
    @FindBy(xpath = "//*[contains (@onclick, 'author')]")
    private SelenideElement authors;

    /**
     * Кнопка редактирования контролеров
     */
    @FindBy(xpath = "//*[contains (@onclick, 'controller')]")
    private SelenideElement controllers;

    /**
     * Кнопка редактирования ответственных руководителей
     */
    @FindBy(xpath = "//*[contains (@onclick, 'respperson')]")
    private SelenideElement resppersons;

    /**
     * Кнопка редактирования исполнителей
     */
    @FindBy(xpath = "//*[contains (@onclick, 'worker')]")
    private SelenideElement workers;

    /**
     * Кнопка Сохранить и создать новую задачу
     */
    @FindBy(xpath = "(//*[contains (@type, 'button')])[1]")
    private SelenideElement buttonSaveCreateTask;

    /**
     * Кнопка Сохранить задачу
     */
    @FindBy(xpath = "(//*[contains (@type, 'button')])[2]")
    private SelenideElement buttonCreateTask;

    /**
     * Поле Название задачи
     */
    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[1]//*[contains (@class, 'col-value')]")
    private SelenideElement clickTaskName;

    /**
     * Номер задачи
     */
    @FindBy(xpath = "//div[@id='numerator']")
    private SelenideElement taskNumber;

    /**
     * Поле приоритета
     */
    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[4]//*[contains (@class, 'col-value')]")
    private SelenideElement priority;

    /**
     * Поле начало
     */
    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[5]//*[contains (@class, 'col-value')]")
    private SelenideElement beginField;

    /**
     * Поле окончание
     */
    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[6]//*[contains (@class, 'col-value')]")
    private SelenideElement endField;

    /**
     * Поле тип задачи
     */
    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[13]//*[contains (@class, 'col-value')]")
    private SelenideElement fieldTaskType;

    /**
     * Поле авторы
     */
    @FindBy(xpath = "//*[contains (@onclick, 'author')]/../../../td[2]/div")
    private SelenideElement authorsField;

    /**
     * Поле контролеры
     */
    @FindBy(xpath = "//*[contains (@onclick, 'controller')]/../../../td[2]/div")
    private SelenideElement сontrollersField;

    /**
     * Поле ответственные руководители
     */
    @FindBy(xpath = "//*[contains (@onclick, 'respperson')]/../../../td[2]/div")
    private SelenideElement executiveManagersField;

    /**
     * Поле исполнители
     */
    @FindBy(xpath = "//*[contains (@onclick, 'worker')]/../../../td[2]/div")
    private SelenideElement workersField;

    /**
     * Поле ввода для любого поля задачи
     */
    @FindBy(xpath = "//*[contains (@class, 'x-editor')][contains (@style, 'visible')]//input")
    private SelenideElement editorField;

    //-------------------------------------------------------------------------------------------------------------Форма - ИРГ
    /**
     * Поле названия ИРГ
     */
    @FindBy(xpath = "//input[contains (@id, 'iwgname')]")
    private SelenideElement inputIwgName;

    /**
     * Поле шаблона ИРГ
     */
    @FindBy(xpath = "//input[contains (@id, 'tasktemplate')]")
    private SelenideElement inputIwgTaskTemplate;

    /**
     * Поле типа задачи ИРГ
     */
    @FindBy(xpath = "//input[contains (@id, 'tasktype')]")
    private SelenideElement inputIwgTaskType;

    /**
     * Чекбокс сист. действия в родительской задаче
     */
    @FindBy(xpath = "//input[contains (@id, 'sysacts')]")
    private SelenideElement inputIwgSysActionsInParentTask;

    /**
     * Добавить - ИРГ - ОР задачи
     */
    @FindBy(xpath = "(//span[contains(@id,'button')][ancestor::div[contains(@id,'grid')]]/span/span[2])[1]")
    private SelenideElement buttonIwgAddRespPerson;

    /**
     * Добавить - ИРГ -  Исполнитель задачи
     */
    @FindBy(xpath = "(//span[contains(@id,'button')][ancestor::div[contains(@id,'grid')]]/span/span[2])[2]")
    private SelenideElement buttonIwgAddWorker;

    /**
     * Добавить - ИРГ - Контролер задачи
     */
    @FindBy(xpath = "(//span[contains(@id,'button')][ancestor::div[contains(@id,'grid')]]/span/span[2])[3]")
    private SelenideElement buttonIwgAddController;

    /**
     * Кнопка Сохранить
     */
    @FindBy(xpath = "(//span[contains(@id,'button')][ancestor::div[contains(@id,'panel') and contains(@class,'x-plain bottom-panel')]]/span/span[2])[1]")
    private SelenideElement buttonIwgSave;

    /**
     * Кнопка Отмена
     */
    @FindBy(xpath = "(//span[contains(@id,'button')][ancestor::div[contains(@id,'panel') and contains(@class,'x-plain bottom-panel')]]/span/span[2])[2]")
    private SelenideElement buttonIwgCancel;

    //----------------------------------------------------------------------------------------------------------Окно выбора пользователей

    /**
     * Поле поиска пользователя
     *
     * @FindBy
     */
    @FindBy(css = "#SearchEdit")
    private SelenideElement userSearchField;

    /**
     * кнопка доб пользователя
     */
    @FindBy(xpath = "//img[contains (@onclick, 'check')]")
    private SelenideElement userAddButton;

    /**
     * кнопка сохранить
     */
    @FindBy(xpath = "//*[@id='btn_save']//button")
    private SelenideElement userSaveButton;

    //------------------------------------------------------------------------------------------------------------Форма проекта

    /**
     * Поле ввода для  поля проекта
     */
    @FindBy(xpath = "//*[contains (@class, 'x-editor')][not(contains (@style, 'none'))]//input")
    private SelenideElement editorFieldProject;

    /**
     * Поле текста для проекта
     */
    @FindBy(xpath = "//textarea")
    private SelenideElement editorTextProject;

    /**
     * Поле проект
     */
    @FindBy(xpath = "//tr[1]/td[2]/div")
    private SelenideElement projectField;

    /**
     * Описание проекта
     */
    @FindBy(xpath = "//tr[2]/td[2]/div")
    private SelenideElement projectDescription;

    /**
     * Заказчик проекта
     */
    @FindBy(xpath = "//tr[4]/td[2]/div")
    private SelenideElement projectClient;

    /**
     * Окончание проекта
     */
    @FindBy(xpath = "//tr[6]/td[2]/div")
    private SelenideElement projectEnd;

    /**
     * Сохранить проект
     */
    @FindBy(xpath = "//*[contains (@class, 'footer')]//a[3]/../a[1]//span[2]")
    private SelenideElement projectSave;

    //------------------------------------------------------------------------------------------------------------Форма задачи
    /**
     * Обычная задача
     */
    @FindBy(xpath = "//*[contains (@class, 'inner')]/*[contains (@class, 'combo-list')][1]")
    private SelenideElement simpleTask;

    /**
     * Важная задача
     */
    @FindBy(xpath = "//*[contains (@class, 'inner')]/*[contains (@class, 'combo-list')][2]")
    private SelenideElement importantTask;

    /**
     * Вкладка Планирование
     */
    @FindBy(xpath = "//li[contains (@id, 'planning')]//span[contains (@class, 'strip')]")
    private SelenideElement planningTab;

    /**
     * Вкладка Дополнительно
     */
    @FindBy(xpath = "//li[contains (@id, 'additional')]//span[contains (@class, 'strip')]")
    private SelenideElement additionalTab;

    /**
     * Чекбокс с докладом
     */
    @FindBy(xpath = "//*[contains (@id, 'iswithreport')]")
    private SelenideElement checkboxWithReport;

    /**
     * Чекбокс секретная задача
     */
    @FindBy(xpath = "//*[contains (@id, 'issecret')]")
    private SelenideElement checkboxSecretTask;

    /**
     * Чекбокс только для ознакомления
     */
    @FindBy(xpath = "//*[contains (@id, 'only_for_view')]")
    private SelenideElement checkboxOnlyForView;

    /**
     * Кнопка добавить КТ
     */
    @FindBy(xpath = "//table[contains (@id, 'AddCP')]//button")
    private SelenideElement buttonAddCheckpoint;

    /**
     * Чекбокс "готово" первой КТ грида
     */
    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//td//div[contains (@class, 'check')]")
    private SelenideElement checkboxReadyFirst;

    /**
     * Поле Дата первой КТ грида
     */
    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//*[contains (@class, 'scroller')]//*[contains (@class, 'row')][1]//td[2]")
    private SelenideElement checkpointDateField;

    /**
     * Поле Привязка первой КТ грида
     */
    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//*[contains (@class, 'scroller')]//*[contains (@class, 'row')][1]//td[3]")
    private SelenideElement checkpointLinkedField;

    /**
     * Поле смещение первой КТ грида
     */
    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//*[contains (@class, 'scroller')]//*[contains (@class, 'row')][1]//td[4]")
    private SelenideElement checkpointOffsetField;

    /**
     * Поле период первой КТ грида
     */
    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//*[contains (@class, 'scroller')]//*[contains (@class, 'row')][1]//td[5]")
    private SelenideElement checkpointPeriodField;

    /**
     * Поле название первой КТ грида
     */
    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//*[contains (@class, 'scroller')]//*[contains (@class, 'row')][1]//td[6]")
    private SelenideElement checkpointNameField;

    /**
     * Кнопка описание первой КТ грида
     */
    @FindBy(xpath = "(//img[contains (@onclick, 'Description')])[1]")
    private SelenideElement checkpointDescriptionField;

    /**
     * Первый видимый элеиент комбобокса
     */
    @FindBy(xpath = "//div[contains (@class, 'combo')][contains (@style, 'visibility: visible')]/div/div[1]")
    private SelenideElement firstVisibleCombo;

    /**
     * Второй видимый элеиент комбобокса
     */
    @FindBy(xpath = "//div[contains (@class, 'combo')][contains (@style, 'visibility: visible')]/div/div[2]")
    private SelenideElement secondVisibleCombo;

    /**
     * Третий видимый элеиент комбобокса
     */
    @FindBy(xpath = "//div[contains (@class, 'combo')][contains (@style, 'visibility: visible')]/div/div[3]")
    private SelenideElement thirdVisibleCombo;

    /**
     * Четвертый видимый элеиент комбобокса
     */
    @FindBy(xpath = "//div[contains (@class, 'combo')][contains (@style, 'visibility: visible')]/div/div[4]")
    private SelenideElement fourthVisibleCombo;

    /**
     * Пятый видимый элеиент комбобокса
     */
    @FindBy(xpath = "//div[contains (@class, 'combo')][contains (@style, 'visibility: visible')]/div/div[5]")
    private SelenideElement fifthVisibleCombo;

    /**
     * ----------------------------------------------------------------------------------------------------------------ФРЕЙМЫ:
     */

    /**
     * Фрейм создания проекта
     */
    @FindBy(xpath = "//*[contains (@src, 'project')]")
    private SelenideElement projectFrame;

    /**
     * Основной фрейм
     */
    @FindBy(id = "flow")
    private SelenideElement Frame;

    /**
     * Фрейм ввода текста описания
     */
    @FindBy(xpath = "//*[@class='cke_wysiwyg_frame cke_reset']")
    private SelenideElement descriptionFrame;

    /**
     * Фрейм выбора пользвателя
     */
    @FindBy(id = "SelectUser")
    private SelenideElement selectUserFrame;

    /**
     * Фрейм создания ирг
     */
    @FindBy(xpath = "//*[contains (@src, 'editiwg')]")
    private SelenideElement iwgFrame;

    //------------------------------------------------------------------------------------------------------------------Методы

    /**
     * Переход в фрейм
     */
    public UnionMessageNewPage gotoFrame() {
        getWebDriver().switchTo().frame(Frame);
        return this;
    }

    /**
     * Переход в фрейм формы редактирования/создания ИРГ
     */
    public UnionMessageNewPage gotoIWGFrame() {
        getWebDriver().switchTo().frame(iwgFrame);
        return this;
    }

    /**
     * Ожидание маски быстрого поиска при вводе шаблона поиска
     */
    public UnionMessageNewPage waitForLivesearchMask() {
        waitMillisecond(0.7);
        $(By.xpath("//*[contains (@class, 'loading-indicator')]")).shouldNotBe(Condition.visible);
        ;
        return this;
    }

    /**
     * Ожидание маски задачи
     */
    public UnionMessageNewPage waitForTaskMask() {
        waitMillisecond(0.5);
        $(By.xpath("//*[contains (@class, 'ext-el-mask')]")).shouldNotBe(Condition.visible);
        return this;
    }

    /**
     * Ожидание маски проекта
     */
    public UnionMessageNewPage waitForProjectMask() {
        waitMillisecond(0.3);
        $(By.xpath("//*[contains (@class, 'x-mask x-mask-fixed')]")).shouldNotBe(Condition.visible);
        return this;
    }

    /**
     * Добавление пользователей в роль задачи, через livesearch - Поиск по фамилии
     *
     * @param employees
     * @param fieldCustomRole
     * @param valueField
     */
    protected void choiceUsersThroughTheSearchLiveSurname(Employee[] employees, SelenideElement fieldCustomRole, SelenideElement valueField) {
        if (employees != null) {
            for (Employee employee : employees) {
                $(fieldCustomRole).shouldNotBe(Condition.disabled);
                fieldCustomRole.click();
                valueField.setValue(employee.getLastName());
                $(By.xpath("//div[contains (@style, 'visible')]//*[contains (text(), '" + employee
                        .getLastName() + "')]")).shouldBe(Condition.visible);
                $(By.xpath("//div[contains (@style, 'visible')]//*[contains (text(), '" + employee.getLastName() + "')]")).click();
                waitForTaskMask();
            }
        }
    }

    /**
     * Добавление Пользователей (ОР, Исполнители и т.д..) через livesearch, ввод SPACE и поиск пользователя из выпадающего списка
     * (контекстное меню)
     *
     * @param employees
     * @param fieldCustomRole
     * @param valueField
     */
    protected void choiceUsersThroughTheSearchLiveForSpace(Employee[] employees, SelenideElement fieldCustomRole, SelenideElement valueField) {
        if (employees != null) {
            for (Employee employee : employees) {
                $(fieldCustomRole).shouldBe(Condition.visible);
                fieldCustomRole.click();
                valueField.sendKeys(Keys.SPACE);
                waitForLivesearchMask();
                $(By.xpath("//div[contains (@style, 'visible')]//*[contains (text(), '" + employee.getLastName() + "')]")).click();
                waitForTaskMask();
            }
        }
    }

    /**
     * Добавление названия задачи
     */
    public UnionMessageNewPage setTaskName(String taskName) {
        clickTaskName.click();
        editorField.setValue(taskName);
        return this;
    }

    /**
     * Клик сохранить задачу - ожидание маски
     */
    public UnionMessageNewPage clickSaveTask() {
        buttonCreateTask.click();
        waitForTaskMask();
        return this;
    }

    /**
     * Проверка что появилось окно и ссылка на созданную задачу
     */
    public UnionMessageNewPage assertWindowTaskCreated() {
        $(By.xpath("//a[contains (@href, '/user/unionmessage')]")).shouldBe(Condition.visible);
        buttonTaskSavedOK.click();
        return this;
    }


    /**
     * Установка типа задачи
     */
    public UnionMessageNewPage setTaskType(TasksTypes tasktype) {
        if (tasktype == null) {
            return this;
        } else {
            $(fieldTaskType).shouldBe(Condition.visible);
        }
        fieldTaskType.click();
        editorField.click();
        getWebDriver().findElement
                (By.xpath("//*[contains (@class, 'combo-list')][contains (@style, 'visible')]//*[contains (text(), '" + tasktype.getTaskTypeName() + "')]"))
                .click();
        waitForTaskMask();
        return this;

    }

    /**
     * Создание нового проекта
     */
    public UnionMessageNewPage createProject(Project project) {
        if (project == null) {
            return this;
        } else {
            newProject.click();
            getWebDriver().switchTo().frame(projectFrame);
            $(projectField).shouldBe(Condition.present);
            projectField.click();
            editorFieldProject.setValue(project.getNameProject());
            projectDescription.click();
            editorTextProject.setValue(project.getDescription());
            projectClient.click();
            editorFieldProject.setValue(project.getСlient());
            projectEnd.click();
            editorFieldProject.setValue(project.getEndDate());
            projectSave.click();
            waitForProjectMask();
            getWebDriver().switchTo().defaultContent();
            getWebDriver().switchTo().frame(Frame);
        }
        return this;
    }

    /**
     * Ввод описания
     */
    public UnionMessageNewPage setTaskDescription(String descript) {
        if (descript == null) {
            return this;
        } else {
            description.click();
            getWebDriver().switchTo().frame(descriptionFrame);
            ckeBody.setValue(descript);
            getWebDriver().switchTo().defaultContent();
            getWebDriver().switchTo().frame(Frame);
            buttonSaveDescription.click();
        }
        return this;
    }

    /**
     * Ввод даты начала
     */
    public UnionMessageNewPage setDataBegin(String begin) {
        if (begin == null) {
            return this;
        } else {
            beginField.click();
            editorField.setValue(begin);
        }
        return this;
    }

    /**
     * Ввод даты конца
     */
    public UnionMessageNewPage setEnd(String end) {
        if (end == null) {
            return this;
        } else {
            endField.click();
            editorField.setValue(end);

        }
        return this;
    }

    /**
     * Установка признака важности
     */
    public UnionMessageNewPage setImportance(boolean isImportant) {
        priority.click();
        editorField.click();
        if (isImportant) {
            importantTask.click();
        } else {
            simpleTask.click();
        }
        return this;
    }

    /**
     * Установка Контрольных точек
     */
    public UnionMessageNewPage setCheckpoints(Checkpoint[] checkpoints) {
        if (checkpoints == null) {
            return this;
        } else
            getWebDriver().switchTo().defaultContent();
        getWebDriver().switchTo().frame(Frame);
        $(planningTab).shouldBe(Condition.visible);
        planningTab.click(); // Выбор вкладки - Планирование
        waitForTaskMask(); // Ожидание маски
        outer:
        for (Checkpoint checkpoint : checkpoints) {
            buttonAddCheckpoint.click(); // Добавить КТ
            checkpointDateField.click();
            editorField.setValue(checkpoint.getDate());
            checkpointDescriptionField.click();
            getWebDriver().switchTo().frame(descriptionFrame);
            ckeBody.setValue(checkpoint.getDescription());
            getWebDriver().switchTo().defaultContent();
            getWebDriver().switchTo().frame(Frame);
            buttonSaveDescription.click();
            checkpointNameField.click();
            editorField.setValue(checkpoint.getName()); // Заполняем Название КТ
            if (checkpoint.getIsReady()) {
                checkboxReadyFirst.click();
            }

             /*
            TODO Создание КТ с привязкой к Дате окончания/Начала
                checkpointLinkedField.click();
			if (checkpoints[i].getLinkedTo() == LinkedTo.NULL)
				{continue outer;}

				if (checkpoints[i].getLinkedTo() == LinkedTo.BEGIN)
				{
					editorField.click();
					editorField.setValue(Keys.chord(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.RETURN));
					}
				if (checkpoints[i].getLinkedTo() == LinkedTo.END)
					{editorField.setValue(Keys.chord(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.RETURN));
					}
				checkpointPeriodField.click();
				editorField.click();
				if (checkpoints[i].getPeriod() == Period.WORK_MINUTE)
					{firstVisibleCombo.click();}
				if (checkpoints[i].getPeriod() == Period.WORK_HOUR)
					{secondVisibleCombo.click();}
				if (checkpoints[i].getPeriod() == Period.WORK_DAY)
					{thirdVisibleCombo.click();}
				if (checkpoints[i].getPeriod() == Period.CAL_HOUR)
					{fourthVisibleCombo.click();}
				if (checkpoints[i].getPeriod() == Period.CAL_DAY)
					{fifthVisibleCombo.click();}
				wait.until(ExpectedConditions.elementToBeClickable(checkpointOffsetField));
				checkpointOffsetField.click();
				editorField.setValue(checkpoints[i].getOffset());*/
        }
        return this;
    }

    /**
     * Установка признака с докладом
     */
    public UnionMessageNewPage setReport(boolean isWithReport) {
        additionalTab.click();
        if (!isWithReport) {
            checkboxWithReport.click();
        }
        return this;
    }

    /**
     * Установка признака секретная
     */
    public UnionMessageNewPage setSecret(boolean isSecret) {
        additionalTab.click();
        if (isSecret) {
            checkboxSecretTask.click();
        }
        return this;
    }

    /**
     * Установка признака только для ознакомления
     */
    public UnionMessageNewPage setReview(boolean isForReview) {
        additionalTab.click();
        if (isForReview) {
            checkboxOnlyForView.click();
        }
        return this;
    }

    /**
     * Установка признака - Системные действия в родительской задаче
     *
     * @param sysActionsInParentTask
     * @return
     */
    public UnionMessageNewPage iwgSysActionsInParentTask(boolean sysActionsInParentTask) {
        if (sysActionsInParentTask) {
            inputIwgSysActionsInParentTask.click();
        }
        return this;
    }

    /**
     * Добавление ИРГ из массива
     */
    public UnionMessageNewPage setIWG(IWG[] iwg) {
        if (iwg == null) {
            return this;
        } else {
            outer:
            // Общий массив ИРГ
            for (IWG anIwg : iwg) {
                buttonAddIWG.click(); // Добавить ИРГ
                gotoIWGFrame(); // переходим во Фрейм формы добавления - ИРГ
                $(buttonIwgSave).shouldBe(Condition.visible);
                inputIwgName.setValue(anIwg.getNameIWG()); // Название ИРГ
                inputIwgTaskType.click();
                getWebDriver().findElement(By.xpath("//*[text()='" + anIwg.getTasksTypes().getTaskTypeName() + "']")).click();
                iwgSysActionsInParentTask(anIwg.getIsSystemActionsInParentTask()); // Системные действия в родительской задаче
                // ОР ИРГ
                if (anIwg.getRespPersons() == null) {
                    cancelIWG(); // Отменить создание/редактирование ИРГ
                    continue outer;
                } else {
                    for (int riwg = 0; riwg < (anIwg.getRespPersons().length); riwg++) {
                        buttonIwgAddRespPerson.click(); // добавить ОР ИРГ
                        // Window PopUp
                        String parentWindowHandler = getWebDriver().getWindowHandle(); // Store your parent window
                        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.cssSelector("#SearchEdit"))));
                        $(userSearchField).shouldBe(Condition.visible);
                        userSearchField.clear();
                        userSearchField.setValue(anIwg.getRespPersons()[riwg].getLastName());
                        userSearchField.sendKeys(Keys.RETURN);
                        waitForTaskMask();
                        userAddButton.click(); // Добавить пользователя
                        userSaveButton.click(); // Сохранить выбранных пользователей
                        getWebDriver().switchTo().window(parentWindowHandler);  // Switch back to parent window
                        getWebDriver().switchTo().frame(Frame);
                        getWebDriver().switchTo().frame(iwgFrame);
                    }
                }
                // Исполнители ИРГ
                if (anIwg.getWorkers() == null) {
                    saveIWG(); // Сохранить текущую ИРГ
                    continue outer;
                } else {
                    for (int wiwg = 0; wiwg < (anIwg.getWorkers().length); wiwg++) {
                        buttonIwgAddWorker.click(); // добавить Исполнителей ИРГ
                        String parentWindowHandler = getWebDriver().getWindowHandle(); // Store your parent window
                        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.cssSelector("#SearchEdit"))));
                        $(userSearchField).shouldBe(Condition.visible);
                        userSearchField.clear();
                        userSearchField.setValue(anIwg.getWorkers()[wiwg].getLastName());
                        userSearchField.pressEnter();
                        waitForTaskMask();
                        userAddButton.click();
                        userSaveButton.click();
                        getWebDriver().switchTo().window(parentWindowHandler);  // Switch back to parent window
                        getWebDriver().switchTo().frame(Frame);
                        getWebDriver().switchTo().frame(iwgFrame);
                    }
                }
                // Контролеры ИРГ
                if (anIwg.getControllers() == null) {
                    saveIWG(); // Сохранить текущую ИРГ
                    continue outer;
                } else {
                    for (int сiwg = 0; сiwg < (anIwg.getControllers().length); сiwg++) {
                        buttonIwgAddController.click(); // добавить Контролеров ИРГ
                        String parentWindowHandler = getWebDriver().getWindowHandle(); // Store your parent window
                        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.cssSelector("#SearchEdit"))));
                        $(userSearchField).shouldBe(Condition.visible);
                        userSearchField.clear();
                        userSearchField.setValue(anIwg.getControllers()[сiwg].getLastName());
                        userSearchField.pressEnter();
                        waitForTaskMask();
                        userAddButton.click();
                        userSaveButton.click();
                        getWebDriver().switchTo().window(parentWindowHandler);  // Switch back to parent window
                        getWebDriver().switchTo().frame(Frame);
                        getWebDriver().switchTo().frame(iwgFrame);
                    }
                }


                saveIWG(); // Сохранить текущую ИРГ
                verifyCreateIWG(anIwg.getNameIWG()); // Проверяем отображение ИРГ в гриде
            }
            goToTopFrem(); // уходим в ТОП фрейм
            gotoFrame(); // возвращаемся в основной фрейм для дальнейшей работы в задаче
        }
        return this;
    }

    /**
     * Сохранить ИРГ
     *
     * @return
     */
    public UnionMessageNewPage saveIWG() {
        buttonIwgSave.click();
        return this;
    }

    /**
     * Отменить ИРГ
     *
     * @return
     */
    public UnionMessageNewPage cancelIWG() {
        buttonIwgCancel.click();
        return this;
    }

    /**
     * Проверяем отображение добавленной подзадачи ИРГ в гриде ИРГ (ДО сохранения!)
     *
     * @param nameIWG
     * @return
     */
    public UnionMessageNewPage verifyCreateIWG(String nameIWG) {
        getWebDriver().switchTo().defaultContent(); // уходим в ТОР фрейм
        gotoFrame(); // переходим в общий фрейм "flow"
        waitForPageUntilElementIsVisible(By.xpath("//div[@id='tab_iwg']//tbody//td[5]//div[text()='" + nameIWG + "']"), 5000);
        return this;
    }


    /**
     * Проверка Загрузки страницы - ожидание кнопки - Добавить новый проект
     */
    public UnionMessageNewPage ensurePageLoaded() {
        $(By.xpath("//a[contains (@href, 'newproject')]")).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Создание обычной задачи
     *
     * @param task
     */
    @Override
    public void creatingTask(Task task) {
        ensurePageLoaded();
        setEnd(task.getDateEnd())
                .createProject(task.getProject())
                .setTaskName(task.getTaskName())
                .setTaskDescription(task.getDescription())
                .setDataBegin(task.getDateBegin())
                .setImportance(task.getIsImportant());
        // выбор пользователя по ФИО - Авторы - через searchlive
        choiceUsersThroughTheSearchLiveSurname(task.getAuthors(), authorsField, editorField);
        // выбор пользователя - Контролер - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getControllers(), сontrollersField, editorField);
        // выбор пользователя - Исполнителей - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getWorkers(), workersField, editorField);
        // выбор пользователя - Ответственные руководители - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getExecutiveManagers(), executiveManagersField, editorField);
        setTaskType(task.getTasktype()) // выбор - Тип задачи
                .setReport(task.getIsWithReport())
                .setSecret(task.getIsSecret())
                .setReview(task.getIsForReview())
                .clickSaveTask()
                .assertWindowTaskCreated();

    }

    /**
     * Создание обычной задачи с задачей ИРГ
     *
     * @param task передаются все необходимые атрибуты задачи
     */
    @Override
    public void creatingTaskWithTheTaskOfIWG(Task task) {
        ensurePageLoaded();
        setEnd(task.getDateEnd())
                .createProject(task.getProject())
                .setTaskName(task.getTaskName())
                .setTaskDescription(task.getDescription())
                .setDataBegin(task.getDateBegin())
                .setImportance(task.getIsImportant());
        // выбор пользователя - Ответственные руководители - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getExecutiveManagers(), executiveManagersField, editorField);
        setTaskType(task.getTasktype())
                .setIWG(task.getIWG())
                .setReport(task.getIsWithReport())
                .setSecret(task.getIsSecret())
                .setReview(task.getIsForReview())
                .clickSaveTask()
                .assertWindowTaskCreated();
    }


    /**
     * Создание обычной задачи с КТ
     *
     * @param task передаются все необходимые атрибуты задачи
     */
    @Override
    public void creationOfATaskCheckpoints(Task task) {
        ensurePageLoaded();
        setTaskName(task.getTaskName())
                .setEnd(task.getDateEnd())
                .setCheckpoints(task.getCheckpoints()) // Контрольные точки
                .clickSaveTask()
                .assertWindowTaskCreated();
    }
}


