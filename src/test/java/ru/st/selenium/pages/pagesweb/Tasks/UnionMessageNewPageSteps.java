package ru.st.selenium.pages.pagesweb.Tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.st.selenium.logicinterface.WebLogic.Task.UnionMessageNewLogic;
import ru.st.selenium.model.Tasks.Checkpoint;
import ru.st.selenium.model.Tasks.IWG;
import ru.st.selenium.model.Tasks.Project;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.Tasks.Task;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pagesweb.Tasks.TaskElements.ProjectFormElements;
import ru.st.selenium.pages.pagesweb.Tasks.TaskElements.TaskFormElements;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.st.selenium.utils.WaitUtil.waitForPageUntilElementIsVisible;
import static ru.st.selenium.utils.WindowsUtil.newWindowForm;


/**
 * Шаги - форма - СОЗДАТЬ ЗАДАЧУ
 */
public class UnionMessageNewPageSteps extends BasePage implements UnionMessageNewLogic {


    private ProjectFormElements projectFormElements = page(ProjectFormElements.class);
    private TaskFormElements taskFormElements = page(TaskFormElements.class);


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
     * Вкладка Планирование
     */
    @FindBy(xpath = "//li[contains (@id, 'tab_description')]//span[contains (@class, 'strip')]")
    private SelenideElement planningDescription;

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
     * Переход в фрейм формы редактирования/создания ИРГ
     */
    public UnionMessageNewPageSteps gotoIWGFrame() {
        switchTo().frame(iwgFrame);
        return this;
    }

    /**
     * Ожидание маски быстрого поиска при вводе шаблона поиска
     */
    public UnionMessageNewPageSteps waitForLivesearchMask() {
        sleep(700);
        $(By.xpath("//*[contains (@class, 'loading-indicator')]")).shouldNotBe(Condition.visible);
        return this;
    }

    /**
     * Ожидание маски задачи
     */
    public UnionMessageNewPageSteps waitForTaskMask() {
        sleep(500);
        $(By.xpath("//*[contains (@class, 'ext-el-mask')]")).shouldNotBe(Condition.visible);
        return this;
    }

    /**
     * Ожидание маски проекта
     */
    public UnionMessageNewPageSteps waitForProjectMask() {
        sleep(300);
        $(By.xpath("//*[contains (@class, 'x-mask x-mask-fixed')]")).shouldNotBe(Condition.visible);
        return this;
    }

    /**
     * Добавление пользователей в роль задачи, через livesearch - Поиск по фамилии
     *
     * @param employees       массив передаваемых пользователей (Фамилия пользователя)
     * @param fieldCustomRole передаваемая выбираемая роль в задаче
     * @param valueField      заполнение поля (input) Фамилией пользователя
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
     * @param employees       массив передаваемых пользователей (Фамилия пользователя)
     * @param fieldCustomRole передаваемая выбираемая роль в задаче
     * @param valueField      заполнение поля (input) Фамилией пользователя
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
    public UnionMessageNewPageSteps setTaskName(String taskName) {
        taskFormElements.getClickTaskName().click();
        taskFormElements.getEditorField().setValue(taskName);
        return this;
    }

    /**
     * Клик сохранить задачу - ожидание маски
     */
    public UnionMessageNewPageSteps clickSaveTask() {
        planningDescription.click();
        taskFormElements.getButtonCreateTask().click();
        waitForTaskMask();
        return this;
    }

    /**
     * Проверка что появилось окно и ссылка на созданную задачу
     */
    public UnionMessageNewPageSteps assertWindowTaskCreated() {
        $(By.xpath("//span[@class='ext-mb-text'][contains(text(),'Создана задача №')]")).shouldBe(Condition.visible);
        taskFormElements.getOKButtonInConfirmationFormTaskCreation().click();
        return this;
    }

    /**
     * Установка типа задачи
     */
    public UnionMessageNewPageSteps setTaskType(TasksTypes tasktype) {
        if (tasktype == null) {
            return this;
        } else {
            $(taskFormElements.getFieldTaskType()).shouldBe(Condition.visible);
        }
        taskFormElements.getFieldTaskType().click();
        taskFormElements.getEditorField().click();
        $(By.xpath("//*[contains (@class, 'combo-list')][contains (@style, 'visible')]//*[contains (text(), '" + tasktype.getTaskTypeName() + "')]"))
                .click();
        waitForTaskMask();
        return this;

    }

    /**
     * Создание нового проекта
     */
    public UnionMessageNewPageSteps createProject(Project project) {
        if (project == null) {
            return this;
        } else {
            taskFormElements.getButtonNewProject().click();
            switchTo().frame(projectFrame);
            // выбор поля Проект
            projectFormElements.getProjectField().click();
            // заполняем поле Проект (Название проекта)
            projectFormElements.getEditorFieldProject().setValue(project.getNameProject());
            // выбор поля Описание
            projectFormElements.getProjectDescription().click();
            // заполняем поле Описание проекта
            projectFormElements.getEditorDescriptionProject().setValue(project.getDescription());
            projectFormElements.getProjectClient().click();
            projectFormElements.getEditorFieldProject().setValue(project.getСlient());
            projectFormElements.getProjectEnd().click();
            projectFormElements.getEditorFieldProject().setValue(project.getEndDate());
            projectFormElements.getProjectSave().click();
            waitForProjectMask();
            getFrameTop();
            getFrameFlow();
        }
        return this;
    }

    /**
     * Ввод описания
     */
    public UnionMessageNewPageSteps setTaskDescription(String descript) {
        if (descript == null) {
            return this;
        } else {
            taskFormElements.getDescriptionTask().click();
            switchTo().frame(descriptionFrame);
            taskFormElements.getCkeBody().setValue(descript);
            getFrameTop();
            getFrameFlow();
            taskFormElements.getButtonSaveDescription().click();
        }
        return this;
    }

    /**
     * Ввод даты начала
     */
    public UnionMessageNewPageSteps setDataBegin(String begin) {
        if (begin == null) {
            return this;
        } else {
            taskFormElements.getBeginField().click();
            taskFormElements.getEditorField().setValue(begin);
        }
        return this;
    }

    /**
     * Ввод даты конца
     */
    public UnionMessageNewPageSteps setEnd(String end) {
        if (end == null) {
            return this;
        } else {
            taskFormElements.getEndField().click();
            taskFormElements.getEditorField().setValue(end);

        }
        return this;
    }

    /**
     * Установка признака важности
     */
    public UnionMessageNewPageSteps setImportance(boolean isImportant) {
        taskFormElements.getPriority().click();
        taskFormElements.getEditorField().click();
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
    public UnionMessageNewPageSteps setCheckpoints(Checkpoint[] checkpoints) {
        if (checkpoints == null) {
            return this;
        } else
            getFrameTop();
        getFrameFlow();
        $(planningTab).shouldBe(Condition.visible);
        planningTab.click(); // Выбор вкладки - Планирование
        waitForTaskMask(); // Ожидание маски
        for (Checkpoint checkpoint : checkpoints) {
            buttonAddCheckpoint.click(); // Добавить КТ
            checkpointDateField.click();
            taskFormElements.getEditorField().setValue(checkpoint.getDate());
            checkpointDescriptionField.click();
            switchTo().frame(descriptionFrame);
            taskFormElements.getCkeBody().setValue(checkpoint.getDescription());
            getFrameTop();
            getFrameFlow();
            taskFormElements.getButtonSaveDescription().click();
            checkpointNameField.click();
            taskFormElements.getEditorField().setValue(checkpoint.getName()); // Заполняем Название КТ
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
    public UnionMessageNewPageSteps setReport(boolean isWithReport) {
        additionalTab.click();
        if (!isWithReport) {
            checkboxWithReport.click();
        }
        return this;
    }

    /**
     * Установка признака секретная
     */
    public UnionMessageNewPageSteps setSecret(boolean isSecret) {
        additionalTab.click();
        if (isSecret) {
            checkboxSecretTask.click();
        }
        return this;
    }

    /**
     * Установка признака только для ознакомления
     */
    public UnionMessageNewPageSteps setReview(boolean isForReview) {
        additionalTab.click();
        if (isForReview) {
            checkboxOnlyForView.click();
        }
        return this;
    }

    /**
     * Установка признака - Системные действия в родительской задаче
     *
     * @param sysActionsInParentTask передаваемое булево зн-ия, для установки соответстующей настройки
     */
    public UnionMessageNewPageSteps iwgSysActionsInParentTask(boolean sysActionsInParentTask) {
        if (sysActionsInParentTask) {
            inputIwgSysActionsInParentTask.click();
        }
        return this;
    }

    /**
     * Добавление ИРГ из массива
     */
    public UnionMessageNewPageSteps setIWG(IWG[] iwg) {
        if (iwg == null) {
            return this;
        } else {
            // Общий массив ИРГ
            for (IWG anIwg : iwg) {
                taskFormElements.getButtonAddIWG().click(); // Добавить ИРГ
                gotoIWGFrame(); // переходим во Фрейм формы добавления - ИРГ
                $(buttonIwgSave).shouldBe(Condition.visible);
                inputIwgName.setValue(anIwg.getNameIWG()); // Название ИРГ
                inputIwgTaskType.click();
                $(By.xpath("//*[text()='" + anIwg.getTasksTypes().getTaskTypeName() + "']")).click();
                iwgSysActionsInParentTask(anIwg.getIsSystemActionsInParentTask()); // Системные действия в родительской задаче
                // ОР ИРГ
                if (anIwg.getRespPersons() == null) {
                    cancelIWG(); // Отменить создание/редактирование ИРГ
                    continue;
                } else {
                    for (int riwg = 0; riwg < (anIwg.getRespPersons().length); riwg++) {
                        buttonIwgAddRespPerson.click(); // добавить ОР ИРГ
                        // Window PopUp
                        String parentWindowHandler = getWebDriver().getWindowHandle(); // Store your parent window
                        switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.cssSelector("#SearchEdit"))));
                        $(userSearchField).shouldBe(Condition.visible);
                        userSearchField.clear();
                        userSearchField.setValue(anIwg.getRespPersons()[riwg].getLastName());
                        userSearchField.sendKeys(Keys.RETURN);
                        waitForTaskMask();
                        userAddButton.click(); // Добавить пользователя
                        userSaveButton.click(); // Сохранить выбранных пользователей
                        switchTo().window(parentWindowHandler);  // Switch back to parent window
                        getFrameFlow();
                        switchTo().frame(iwgFrame);
                    }
                }
                // Исполнители ИРГ
                if (anIwg.getWorkers() == null) {
                    saveIWG(); // Сохранить текущую ИРГ
                    continue;
                } else {
                    for (int wiwg = 0; wiwg < (anIwg.getWorkers().length); wiwg++) {
                        buttonIwgAddWorker.click(); // добавить Исполнителей ИРГ
                        String parentWindowHandler = getWebDriver().getWindowHandle(); // Store your parent window
                        switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.cssSelector("#SearchEdit"))));
                        $(userSearchField).shouldBe(Condition.visible);
                        userSearchField.clear();
                        userSearchField.setValue(anIwg.getWorkers()[wiwg].getLastName());
                        userSearchField.pressEnter();
                        waitForTaskMask();
                        userAddButton.click();
                        userSaveButton.click();
                        switchTo().window(parentWindowHandler);  // Switch back to parent window
                        getFrameFlow();
                        switchTo().frame(iwgFrame);
                    }
                }
                // Контролеры ИРГ
                if (anIwg.getControllers() == null) {
                    saveIWG(); // Сохранить текущую ИРГ
                    continue;
                } else {
                    for (int сiwg = 0; сiwg < (anIwg.getControllers().length); сiwg++) {
                        buttonIwgAddController.click(); // добавить Контролеров ИРГ
                        String parentWindowHandler = getWebDriver().getWindowHandle(); // Store your parent window
                        switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.cssSelector("#SearchEdit"))));
                        $(userSearchField).shouldBe(Condition.visible);
                        userSearchField.clear();
                        userSearchField.setValue(anIwg.getControllers()[сiwg].getLastName());
                        userSearchField.pressEnter();
                        waitForTaskMask();
                        userAddButton.click();
                        userSaveButton.click();
                        switchTo().window(parentWindowHandler);  // Switch back to parent window
                        getFrameFlow();
                        switchTo().frame(iwgFrame);
                    }
                }
                saveIWG(); // Сохранить текущую ИРГ
                verifyCreateIWG(anIwg.getNameIWG()); // Проверяем отображение ИРГ в гриде
            }
            getFrameTop(); // уходим в ТОП фрейм
            getFrameFlow(); // возвращаемся в основной фрейм для дальнейшей работы в задаче

        }
        return this;
    }

    /**
     * Сохранить ИРГ
     */
    public UnionMessageNewPageSteps saveIWG() {
        buttonIwgSave.click();
        return this;
    }

    /**
     * Отменить ИРГ
     */
    public UnionMessageNewPageSteps cancelIWG() {
        buttonIwgCancel.click();
        return this;
    }

    /**
     * Проверяем отображение добавленной подзадачи ИРГ в гриде ИРГ (ДО сохранения!)
     *
     * @param nameIWG передаем название ИРГ
     */
    public UnionMessageNewPageSteps verifyCreateIWG(String nameIWG) {
        getFrameTop();
        getFrameFlow();
        waitForPageUntilElementIsVisible(By.xpath("//div[@id='tab_iwg']//tbody//td[5]//div[text()='" + nameIWG + "']"), 5000);
        return this;
    }


    /**
     * Проверка Загрузки страницы - ожидание кнопки - Добавить новый проект
     */
    public UnionMessageNewPageSteps ensurePageLoaded() {
        $(By.xpath("//a[contains (@href, 'newproject')]")).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Создание обычной задачи
     *
     * @param task передаваемые атрибуты задачи
     */
    @Override
    public void creatingTask(Task task) {
        ensurePageLoaded();
        setEnd(task.getDateEnd());
        createProject(task.getProject());
        setTaskName(task.getTaskName())
                .setTaskDescription(task.getDescription())
                .setDataBegin(task.getDateBegin())
                .setImportance(task.getIsImportant());
        // выбор пользователя по ФИО - Авторы - через searchlive
        choiceUsersThroughTheSearchLiveSurname(task.getAuthors(), taskFormElements.getAuthorsField(),
                taskFormElements.getEditorField());
        // выбор пользователя - Контролер - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getControllers(), taskFormElements.getСontrollersField(),
                taskFormElements.getEditorField());
        // выбор пользователя - Исполнителей - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getWorkers(), taskFormElements.getWorkersField(),
                taskFormElements.getEditorField());
        // выбор пользователя - Ответственные руководители - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getExecutiveManagers(), taskFormElements.getExecutiveManagersField(),
                taskFormElements.getEditorField());
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
                .setTaskName(task.getTaskName())
                .setTaskDescription(task.getDescription())
                .setDataBegin(task.getDateBegin())
                .setImportance(task.getIsImportant());
        // выбор пользователя - Ответственные руководители - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getExecutiveManagers(), taskFormElements.getExecutiveManagersField(),
                taskFormElements.getEditorField());
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








