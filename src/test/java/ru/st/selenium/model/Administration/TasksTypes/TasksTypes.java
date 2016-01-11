package ru.st.selenium.model.Administration.TasksTypes;

import ru.st.selenium.model.CorrectionMethod;
import ru.st.selenium.model.OpenFilesForEdit;
import ru.st.selenium.model.ShiftDirection;

/**
 * Модель объекта системы - Тип задачи (Администрирование/Типы задач)
 */
public class TasksTypes {

    private String nameTaskType;
    private ShiftDirection shiftDirection;
    private CorrectionMethod correctionMethod;
    private boolean disableTaskTypeChange;
    private boolean onlyTheSameTypeIWG;
    private boolean disableCloseTaskWithNotReadyCheckpoints;
    private OpenFilesForEdit openFilesForEdit;
    private boolean attachFilesToActionLine;
    private boolean attachFilesToDecription;
    private TasksTypesField addTableRecordTo;
    private boolean useECP;
    private TasksTypesField[] tasksTypesFields;


    public TasksTypes(String nameTaskType) {
        this.nameTaskType = nameTaskType;
    }

    /**
     * Название - Типы задач
     */
    public String getTaskTypeName() {
        return nameTaskType;
    }

    public TasksTypes setTaskTypeName(String nameTaskType) {
        this.nameTaskType = nameTaskType;
        return this;
    }

    /**
     * Направление смещения при попадании на нерабочее время
     */
    public ShiftDirection getTaskTypeShiftDirection() {
        return shiftDirection;
    }

    public TasksTypes setTaskTypeShiftDirection(ShiftDirection shiftDirection) {
        this.shiftDirection = shiftDirection;
        return this;
    }

    /**
     * Использовать ЭЦП
     */
    public boolean getUseECP() {
        return useECP;
    }

    public TasksTypes setUseECP(boolean useECP) {
        this.useECP = useECP;
        return this;
    }

    /**
     * Корректировка даты
     */
    public CorrectionMethod getTaskTypeCorrectionMethod() {
        return correctionMethod;
    }

    public TasksTypes setTaskTypeCorrectionMethod(CorrectionMethod correctionMethod) {
        this.correctionMethod = correctionMethod;
        return this;
    }

    /**
     * Запретить изменение типа для созданной задачи
     */
    public boolean getIsTaskTypeChangeDisabled() {
        return disableTaskTypeChange;
    }

    public TasksTypes setIsTaskTypeChangeDisabled(boolean disableTaskTypeChange) {
        this.disableTaskTypeChange = disableTaskTypeChange;
        return this;
    }

    /**
     * Создавать подзадачи ИРГ только родительского типа
     */
    public boolean getOnlyTheSameTypeIWG() {
        return onlyTheSameTypeIWG;
    }

    public TasksTypes setOnlyTheSameTypeIWG(boolean onlyTheSameTypeIWG) {
        this.onlyTheSameTypeIWG = onlyTheSameTypeIWG;
        return this;
    }

    /**
     * Запретить закрытие задач с неготовыми контрольными точками
     */
    public boolean getIsCloseTaskWithNotReadyCheckpointsDisabled() {
        return disableCloseTaskWithNotReadyCheckpoints;
    }

    public TasksTypes setIsCloseTaskWithNotReadyCheckpointsDisabled(boolean disableCloseTaskWithNotReadyCheckpoints) {
        this.disableCloseTaskWithNotReadyCheckpoints = disableCloseTaskWithNotReadyCheckpoints;
        return this;
    }

    /**
     * Открывать файлы для редактирования
     */
    public OpenFilesForEdit getOpenFilesForEdit() {
        return openFilesForEdit;
    }

    public TasksTypes setOpenFilesForEdit(OpenFilesForEdit openFilesForEdit) {
        this.openFilesForEdit = openFilesForEdit;
        return this;
    }

    /**
     * Добавлять файлы в ленту действий
     */
    public boolean getIsAttachFilesToActionLine() {
        return attachFilesToActionLine;
    }

    public TasksTypes setIsAttachFilesToActionLine(boolean attachFilesToActionLine) {
        this.attachFilesToActionLine = attachFilesToActionLine;
        return this;
    }

    /**
     * Добавлять файлы в описание
     */
    public boolean getIsAttachFilesToDecription() {
        return attachFilesToDecription;
    }

    public TasksTypes setIsAttachFilesToDecription(boolean attachFilesToDecription) {
        this.attachFilesToDecription = attachFilesToDecription;
        return this;
    }

    /**
     * Вместо добавления файла добавлять запись в таблицу
     */
    public TasksTypesField getAddTableRecordTo() {
        return addTableRecordTo;
    }

    public TasksTypes setAddTableRecordTo(TasksTypesField addTableRecordTo) {
        this.addTableRecordTo = addTableRecordTo;
        return this;
    }

    /**
     * Поля массив объектов - объекта "Типы задач"
     */
    public TasksTypesField[] getTasksTypesFields() {
        return tasksTypesFields;
    }

    public TasksTypes setTasksTypesFields(TasksTypesField[] tasksTypesFields) {
        this.tasksTypesFields = tasksTypesFields;
        return this;
    }
}
