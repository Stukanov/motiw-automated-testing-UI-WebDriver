package ru.st.selenium.pagesteps.Tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.st.selenium.logicinterface.WebLogic.Task.UnionMessageNewLogic;
import ru.st.selenium.model.Tasks.Checkpoint;
import ru.st.selenium.model.Tasks.IWG;
import ru.st.selenium.model.Tasks.Project;
import ru.st.selenium.model.Administration.TasksTypes.TasksTypes;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.model.Tasks.Task;
import ru.st.selenium.pages.BasePage;
import ru.st.selenium.pages.pageselementsweb.TasksElements.TaskFormElements.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.st.selenium.utils.WaitUtil.waitForPageUntilElementIsVisible;
import static ru.st.selenium.utils.WindowsUtil.newWindowForm;

/**
 * форма - СОЗДАТЬ ЗАДАЧУ
 */
public class UnionMessageNewPageSteps extends BasePage implements UnionMessageNewLogic {


    private ProjectFormElements projectFormElements = page(ProjectFormElements.class);
    private InsetDescriptionTaskFormElements insetDescriptionTaskFormElements = page(InsetDescriptionTaskFormElements.class);
    private IWGFormElements iwgFormElements = page(IWGFormElements.class);
    private UsersSelectTheFormElements usersSelectTheFormElements = page(UsersSelectTheFormElements.class);
    private InsetPlanningTaskFormElements insetPlanningTaskFormElements = page(InsetPlanningTaskFormElements.class);
    private InsetDetailsTaskFormElements insetDetailsTaskFormElements = page(InsetDetailsTaskFormElements.class);


    /**
     * Ожидание маски быстрого поиска при вводе шаблона поиска
     */
    public UnionMessageNewPageSteps waitForLiveSearchMask() {
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
                waitForLiveSearchMask();
                $(By.xpath("//div[contains (@style, 'visible')]//*[contains (text(), '" + employee.getLastName() + "')]")).click();
                waitForTaskMask();
            }
        }
    }

    /**
     * Добавление названия задачи
     * @param taskName строковый параметр для передачи Названия задачи
     * @return
     */
    public UnionMessageNewPageSteps setTaskName(String taskName) {
        insetDescriptionTaskFormElements.getClickTaskName().click();
        insetDescriptionTaskFormElements.getEditorField().setValue(taskName);
        return this;
    }

    /**
     * Клик сохранить задачу - Ожидание маски
     */
    public UnionMessageNewPageSteps clickSaveTask() {
        insetDescriptionTaskFormElements.getPlanningDescription().click();
        insetDescriptionTaskFormElements.getButtonCreateTask().click();
        waitForTaskMask();
        return this;
    }

    /**
     * Проверка что появилось окно и ссылка на созданную задачу
     */
    public UnionMessageNewPageSteps assertWindowTaskCreated() {
        $(By.xpath("//span[@class='ext-mb-text'][contains(text(),'Создана задача №')]")).shouldBe(Condition.visible);
        insetDescriptionTaskFormElements.getOKButtonInConfirmationFormTaskCreation().click();
        return this;
    }

    /**
     * Установка типа задачи
     * @param taskType передаваемое имя типа задачи
     */
    public UnionMessageNewPageSteps setTaskType(TasksTypes taskType) {
        if (taskType == null) {
            return this;
        } else {
            $(insetDescriptionTaskFormElements.getFieldTaskType()).shouldBe(Condition.visible);
        }
        insetDescriptionTaskFormElements.getFieldTaskType().click();
        insetDescriptionTaskFormElements.getEditorField().click();
        $(By.xpath("//*[contains (@class, 'combo-list')][contains (@style, 'visible')]//*[contains (text(), '" + taskType.getTaskTypeName() + "')]"))
                .click();
        waitForTaskMask();
        return this;

    }

    /**
     * Создание нового проекта
     * @param project передаваемые параметры атрибуты проекта
     */
    public UnionMessageNewPageSteps createProject(Project project) {
        if (project == null) {
            return this;
        } else {
            insetDescriptionTaskFormElements.getButtonNewProject().click();
            getFrameFormProject();
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
            insetDescriptionTaskFormElements.getDescriptionTask().click();
            getFrameFormDescription();
            insetDescriptionTaskFormElements.getCkeBody().setValue(descript);
            getFrameTop();
            getFrameFlow();
            insetDescriptionTaskFormElements.getButtonSaveDescription().click();
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
            insetDescriptionTaskFormElements.getBeginField().click();
            insetDescriptionTaskFormElements.getEditorField().setValue(begin);
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
            insetDescriptionTaskFormElements.getEndField().click();
            insetDescriptionTaskFormElements.getEditorField().setValue(end);

        }
        return this;
    }

    /**
     * Установка признака важности
     */
    public UnionMessageNewPageSteps setImportance(boolean isImportant) {
        insetDescriptionTaskFormElements.getPriority().click();
        insetDescriptionTaskFormElements.getEditorField().click();
        if (isImportant) {
            insetDescriptionTaskFormElements.getImportantTask().click();
        } else {
            insetDescriptionTaskFormElements.getSimpleTask().click();
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
        insetPlanningTaskFormElements.getPlanningTab().click(); // Выбор вкладки - Планирование
        waitForTaskMask(); // Ожидание маски
        for (Checkpoint checkpoint : checkpoints) {
            insetPlanningTaskFormElements.getButtonAddCheckpoint().click(); // Добавить КТ
            insetPlanningTaskFormElements.getCheckpointDateField().click();
            insetDescriptionTaskFormElements.getEditorField().setValue(checkpoint.getDate());
            insetPlanningTaskFormElements.getCheckpointDescriptionField().click();
            getFrameFormDescription();
            insetDescriptionTaskFormElements.getCkeBody().setValue(checkpoint.getDescription());
            getFrameTop();
            getFrameFlow();
            insetDescriptionTaskFormElements.getButtonSaveDescription().click();
            insetPlanningTaskFormElements.getCheckpointNameField().click();
            insetDescriptionTaskFormElements.getEditorField().setValue(checkpoint.getName()); // Заполняем Название КТ
            if (checkpoint.getIsReady()) {
                insetPlanningTaskFormElements.getCheckboxReadyFirst().click();
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
        if (!isWithReport) {
            insetDetailsTaskFormElements.getAdditionalTab().click();
            insetDetailsTaskFormElements.getCheckboxWithReport().click();
        }
        return this;
    }

    /**
     * Установка признака секретная
     */
    public UnionMessageNewPageSteps setSecret(boolean isSecret) {
        if (isSecret) {
            insetDetailsTaskFormElements.getAdditionalTab().click();
            insetDetailsTaskFormElements.getCheckboxSecretTask().click();
        }
        return this;
    }

    /**
     * Установка признака только для ознакомления
     */
    public UnionMessageNewPageSteps setReview(boolean isForReview) {
        insetDetailsTaskFormElements.getAdditionalTab().click();
        if (isForReview) {
            insetDetailsTaskFormElements.getCheckboxOnlyForView().click();
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
            iwgFormElements.getInputIwgSysActionsInParentTask().click();
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
                insetDescriptionTaskFormElements.getButtonAddIWG().click(); // Добавить ИРГ
                getFrameIWG(); // переходим во Фрейм формы добавления - ИРГ
                $(iwgFormElements.getButtonIwgSave()).shouldBe(Condition.visible);
                iwgFormElements.getInputIwgName().setValue(anIwg.getNameIWG()); // Название ИРГ
                iwgFormElements.getInputIwgTaskType().click();
                $(By.xpath("//*[text()='" + anIwg.getTasksTypes().getTaskTypeName() + "']")).click();
                iwgSysActionsInParentTask(anIwg.getIsSystemActionsInParentTask()); // Системные действия в родительской задаче
                // ОР ИРГ
                if (anIwg.getRespPersons() == null) {
                    cancelIWG(); // Отменить создание/редактирование ИРГ
                    continue;
                } else {
                    for (int riwg = 0; riwg < (anIwg.getRespPersons().length); riwg++) {
                        iwgFormElements.getButtonIwgAddRespPerson().click(); // добавить ОР ИРГ
                        // Window PopUp
                        String parentWindowHandler = getWebDriver().getWindowHandle(); // Store your parent window
                        switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.cssSelector("#SearchEdit"))));
                        $(usersSelectTheFormElements.getUserSearchField()).shouldBe(Condition.visible);
                        usersSelectTheFormElements.getUserSearchField().clear();
                        usersSelectTheFormElements.getUserSearchField().setValue(anIwg.getRespPersons()[riwg].getLastName());
                        usersSelectTheFormElements.getUserSearchField().sendKeys(Keys.RETURN);
                        waitForTaskMask();
                        usersSelectTheFormElements.getUserAddButton().click(); // Добавить пользователя
                        usersSelectTheFormElements.getUserSaveButton().click(); // Сохранить выбранных пользователей
                        switchTo().window(parentWindowHandler);  // Switch back to parent window
                        getFrameFlow();
                        getFrameIWG();
                    }
                }
                // Исполнители ИРГ
                if (anIwg.getWorkers() == null) {
                    saveIWG(); // Сохранить текущую ИРГ
                    continue;
                } else {
                    for (int wiwg = 0; wiwg < (anIwg.getWorkers().length); wiwg++) {
                        iwgFormElements.getButtonIwgAddWorker().click(); // добавить Исполнителей ИРГ
                        String parentWindowHandler = getWebDriver().getWindowHandle(); // Store your parent window
                        switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.cssSelector("#SearchEdit"))));
                        $(usersSelectTheFormElements.getUserSearchField()).shouldBe(Condition.visible);
                        usersSelectTheFormElements.getUserSearchField().clear();
                        usersSelectTheFormElements.getUserSearchField().setValue(anIwg.getWorkers()[wiwg].getLastName());
                        usersSelectTheFormElements.getUserSearchField().pressEnter();
                        waitForTaskMask();
                        usersSelectTheFormElements.getUserAddButton().click(); // Добавить пользователя
                        usersSelectTheFormElements.getUserSaveButton().click(); // Сохранить выбранных пользователей
                        switchTo().window(parentWindowHandler);  // Switch back to parent window
                        getFrameFlow();
                        getFrameIWG();
                    }
                }
                // Контролеры ИРГ
                if (anIwg.getControllers() == null) {
                    saveIWG(); // Сохранить текущую ИРГ
                    continue;
                } else {
                    for (int сiwg = 0; сiwg < (anIwg.getControllers().length); сiwg++) {
                        iwgFormElements.getButtonIwgAddController().click(); // добавить Контролеров ИРГ
                        String parentWindowHandler = getWebDriver().getWindowHandle(); // Store your parent window
                        switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.cssSelector("#SearchEdit"))));
                        $(usersSelectTheFormElements.getUserSearchField()).shouldBe(Condition.visible);
                        usersSelectTheFormElements.getUserSearchField().clear();
                        usersSelectTheFormElements.getUserSearchField().setValue(anIwg.getControllers()[сiwg].getLastName());
                        usersSelectTheFormElements.getUserSearchField().pressEnter();
                        waitForTaskMask();
                        usersSelectTheFormElements.getUserAddButton().click(); // Добавить пользователя
                        usersSelectTheFormElements.getUserSaveButton().click(); // Сохранить выбранных пользователей
                        switchTo().window(parentWindowHandler);  // Switch back to parent window
                        getFrameFlow();
                        getFrameIWG();
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
        iwgFormElements.getButtonIwgSave().click();
        return this;
    }

    /**
     * Отменить ИРГ
     */
    public UnionMessageNewPageSteps cancelIWG() {
        iwgFormElements.getButtonIwgCancel().click();
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
        choiceUsersThroughTheSearchLiveSurname(task.getAuthors(), insetDescriptionTaskFormElements.getAuthorsField(),
                insetDescriptionTaskFormElements.getEditorField());
        // выбор пользователя - Контролер - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getControllers(), insetDescriptionTaskFormElements.getСontrollersField(),
                insetDescriptionTaskFormElements.getEditorField());
        // выбор пользователя - Исполнителей - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getWorkers(), insetDescriptionTaskFormElements.getWorkersField(),
                insetDescriptionTaskFormElements.getEditorField());
        // выбор пользователя - Ответственные руководители - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getExecutiveManagers(), insetDescriptionTaskFormElements.getExecutiveManagersField(),
                insetDescriptionTaskFormElements.getEditorField());
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
        choiceUsersThroughTheSearchLiveForSpace(task.getExecutiveManagers(), insetDescriptionTaskFormElements.getExecutiveManagersField(),
                insetDescriptionTaskFormElements.getEditorField());
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








