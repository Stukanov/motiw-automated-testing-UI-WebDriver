package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;


import ru.motiw.web.model.DocflowAdministration.AutoCalculationOfNumeratorFields;
import ru.motiw.web.model.AccessRights;
import ru.motiw.web.model.CorrectionMethod;
import ru.motiw.web.model.OpenFilesForEdit;
import ru.motiw.web.model.ShiftDirection;

/**
 * Модель объекта - "Регистрационная карточка документа"
 */
public class DocRegisterCards {

    private String docRegisterCardsName;

    private String documentStatesOnReview;
    private String documentStatesReviewed;
    private String documentStatesOnApproval;
    private String documentStatesOnExecution;
    private String documentStatesInArchive;

    private String displayNameTemplate;

    private ShiftDirection shiftDirectionDoc;

    private CorrectionMethod correctionMethodDoc;

    private boolean atFirstRevisionScheme;
    private boolean forCompletionInTighterPoint;
    private boolean onCompletionTheNewScheme;

    private OpenFilesForEdit openFilesForEditDoc;

    private String copyingFieldsWhenCreatingTask;

    private String authorsObjectives;
    private String controllersOfTasks;
    private String decisionMakersOfTasks;
    private String executorsOfTasks;

    private AccessRights accessDoc;

    private SettingsFinalVersion documentAuthorFinalVersionFile;
    private SettingsFinalVersion userWithEditRightFinalVersionFiles;
    private SettingsFinalVersion docTypeControllerFinalVersionFiles;

    private EditionOwnDocuments editionOwnDocumentsOnReview;
    private EditionOwnDocuments editionOwnDocumentsOnExecution;
    private EditionOwnDocuments editionOwnDocumentsInArchive;

    private boolean accessToSectionsDocumentRoute;
    private boolean accessToSectionsDocumentFiles;
    private boolean accessToSectionsDocumentResolution;
    private boolean accessToSectionsDocumentLog;

    private CreationOfLinkedDocuments creationOfLinkedDocuments;

    private boolean checkBoxUseAllPossibleRoutes;

    private AutoCalculationOfNumeratorFields autoСalculationNumeratorFields;

    private DocRegisterCardsField[] docRegisterCardsFields;

    /**
     * Конструктор класса - Регистрационные карточки документов
     * @param docRegisterCardsName
     */
    public DocRegisterCards(String docRegisterCardsName) {
        this.docRegisterCardsName = docRegisterCardsName;
    }

    /**
     * ------------------------------------------------------Вкладка - ОБЩЕЕ---------------------------------------------
     */

    /**
     * Название регистрационной карточки документа
     *
     * @return DocRegisterCards
     */
    public String getDocRegisterCardsName() {
        return docRegisterCardsName;
    }

    public DocRegisterCards setDocRegisterCardsName(
            String docRegisterCardsName) {
        this.docRegisterCardsName = docRegisterCardsName;
        return this;
    }


    /**
     * Статус документа - На рассмотрении
     *
     * @return DocRegisterCards
     */
    public String getDocumentStatesOnReview() {
        return documentStatesOnReview;
    }

    public DocRegisterCards setDocumentStatesOnReview(
            String documentStatesOnReview) {
        this.documentStatesOnReview = documentStatesOnReview;
        return this;
    }

    /**
     * Статус документа - Рассмотрен
     *
     * @return DocRegisterCards
     */
    public String getDocumentStatesReviewed() {
        return documentStatesReviewed;
    }

    public DocRegisterCards setDocumentStatesReviewed(
            String documentStatesReviewed) {
        this.documentStatesReviewed = documentStatesReviewed;
        return this;
    }

    /**
     * Статус документа - На подписании
     *
     * @return DocRegisterCards
     */
    public String getDocumentStatesOnApproval() {
        return documentStatesOnApproval;
    }

    public DocRegisterCards setDocumentStatesOnApproval(
            String documentStatesOnApproval) {
        this.documentStatesOnApproval = documentStatesOnApproval;
        return this;
    }

    /**
     * Статус документа - На исполнении
     *
     * @return DocRegisterCards
     */
    public String getDocumentStatesOnExecution() {
        return documentStatesOnExecution;
    }

    public DocRegisterCards setDocumentStatesOnExecution(
            String documentStatesOnExecution) {
        this.documentStatesOnExecution = documentStatesOnExecution;
        return this;
    }

    /**
     * Статус документа - В архиве
     *
     * @return DocRegisterCards
     */
    public String getDocumentStatesInArchive() {
        return documentStatesInArchive;
    }

    public DocRegisterCards setDocumentStatesInArchive(
            String documentStatesInArchive) {
        this.documentStatesInArchive = documentStatesInArchive;
        return this;
    }

    /**
     * Шаблон отображения
     *
     * @return DocRegisterCards
     */
    public String getDisplayNameTemplate() {
        return displayNameTemplate;
    }

    public DocRegisterCards setDisplayNameTemplate(
            String displayNameTemplate) {
        this.displayNameTemplate = displayNameTemplate;
        return this;
    }

    /**
     * Направление смещения при попадании на нерабочее время
     *
     * @return DocRegisterCards
     */
    public ShiftDirection getDocRegisterCardsShiftDirection() {
        return shiftDirectionDoc;
    }

    public DocRegisterCards setDocRegisterCardsShiftDirection(ShiftDirection shiftDirectionDoc) {
        this.shiftDirectionDoc = shiftDirectionDoc;
        return this;
    }

    /**
     * Способ корректировки
     *
     * @return DocRegisterCards
     */
    public CorrectionMethod getDocRegisterCardsCorrectionMethod() {
        return correctionMethodDoc;
    }

    public DocRegisterCards setDocRegisterCardsCorrectionMethod(CorrectionMethod correctionMethodDoc) {
        this.correctionMethodDoc = correctionMethodDoc;
        return this;
    }

    /**
     * Настройки по умолчанию при отправке документа на доработку:
     * Возврат на доработку с начала текущей схемы
     *
     * @return DocRegisterCards
     */
    public boolean getAtFirstRevisionScheme() {
        return atFirstRevisionScheme;
    }

    public DocRegisterCards setAtFirstRevisionScheme(boolean atFirstRevisionScheme) {
        this.atFirstRevisionScheme = atFirstRevisionScheme;
        return this;
    }


    /**
     * Настройки по умолчанию при отправке документа на доработку:
     * Возврат на доработку в ту же точку рассмотрения
     *
     * @return DocRegisterCards
     */
    public boolean getForCompletionInTighterPoint() {
        return forCompletionInTighterPoint;
    }

    public DocRegisterCards setForCompletionInTighterPoint(boolean forCompletionInTighterPoint) {
        this.forCompletionInTighterPoint = forCompletionInTighterPoint;
        return this;
    }


    /**
     * Настройки по умолчанию при отправке документа на доработку:
     * Возврат на доработку с новой схемой
     *
     * @return DocRegisterCards
     */
    public boolean getOnCompletionTheNewScheme() {
        return onCompletionTheNewScheme;
    }

    public DocRegisterCards setOnCompletionTheNewScheme(boolean onCompletionTheNewScheme) {
        this.onCompletionTheNewScheme = onCompletionTheNewScheme;
        return this;
    }

    /**
     * Открывать файлы документа для редактирования
     *
     * @return DocRegisterCards
     */
    public OpenFilesForEdit getOpenFilesForEditDoc() {
        return openFilesForEditDoc;
    }

    public DocRegisterCards setOpenFilesForEditDoc(OpenFilesForEdit openFilesForEditDoc) {
        this.openFilesForEditDoc = openFilesForEditDoc;
        return this;
    }

    /**
     * Автоматическое вычисление полей-нумераторов
     *
     * @return DocRegisterCards
     */
    public AutoCalculationOfNumeratorFields getAutoСalculationNumeratorFields() {
        return autoСalculationNumeratorFields;
    }

    public DocRegisterCards setAutoСalculationNumeratorFields(AutoCalculationOfNumeratorFields autoСalculationNumeratorFields) {
        this.autoСalculationNumeratorFields = autoСalculationNumeratorFields;
        return this;
    }


    //-------------------------------------------------------------------Вкладка - ПРАВА-----------------------------------------------------------------------------------


    /**
     * Доступ
     *
     * @return DocRegisterCards
     */
    public AccessRights getAccessDoc() {
        return accessDoc;
    }

    public DocRegisterCards setAccessDoc(AccessRights accessDoc) {
        this.accessDoc = accessDoc;
        return this;
    }

    /**
     * Изменение признака "Окончательная версия"
     * Автор документа:
     *
     * @return DocRegisterCards
     */
    public SettingsFinalVersion getDocAuthorFinalVersionFiles() {
        return documentAuthorFinalVersionFile;
    }

    public DocRegisterCards setDocAuthorFinalVersionFiles(SettingsFinalVersion documentAuthorFinalVersionFile) {
        this.documentAuthorFinalVersionFile = documentAuthorFinalVersionFile;
        return this;
    }

    /**
     * Изменение признака "Окончательная версия"
     * Пользователь с правами редактирования:
     *
     * @return DocRegisterCards
     */
    public SettingsFinalVersion getUserWithEditRightFinalVersionFiles() {
        return userWithEditRightFinalVersionFiles;
    }

    public DocRegisterCards setUserWithEditRightFinalVersionFiles(SettingsFinalVersion userWithEditRightFinalVersionFiles) {
        this.userWithEditRightFinalVersionFiles = userWithEditRightFinalVersionFiles;
        return this;
    }

    /**
     * Изменение признака "Окончательная версия"
     * Контролер типа документа:
     *
     * @return DocRegisterCards
     */
    public SettingsFinalVersion getDocTypeControllerFinalVersionFiles() {
        return docTypeControllerFinalVersionFiles;
    }

    public DocRegisterCards setDocTypeControllerFinalVersionFiles(SettingsFinalVersion docTypeControllerFinalVersionFiles) {
        this.docTypeControllerFinalVersionFiles = docTypeControllerFinalVersionFiles;
        return this;
    }

    /**
     * Редактирование своих документов
     * - На рассмотрении
     *
     * @return DocRegisterCards
     */
    public EditionOwnDocuments getEditionOwnDocumentsOnReview() {
        return editionOwnDocumentsOnReview;

    }

    public DocRegisterCards setEditionOwnDocumentsOnReview(EditionOwnDocuments editionOwnDocumentsOnReview) {
        this.editionOwnDocumentsOnReview = editionOwnDocumentsOnReview;
        return this;

    }

    /**
     * Редактирование своих документов
     * - На исполнении
     *
     * @return DocRegisterCards
     */
    public EditionOwnDocuments getEditionOwnDocumentOnExecution() {
        return editionOwnDocumentsOnExecution;

    }

    public DocRegisterCards setEditionOwnDocumentsOnExecution(EditionOwnDocuments editionOwnDocumentsOnExecution) {
        this.editionOwnDocumentsOnExecution = editionOwnDocumentsOnExecution;
        return this;

    }

    /**
     * Редактирование своих документов
     * - В архиве
     *
     * @return DocRegisterCards
     */
    public EditionOwnDocuments getEditionOwnDocumentInArchive() {
        return editionOwnDocumentsInArchive;

    }

    public DocRegisterCards setEditionOwnDocumentsInArchive(EditionOwnDocuments editionOwnDocumentsInArchive) {
        this.editionOwnDocumentsInArchive = editionOwnDocumentsInArchive;
        return this;

    }

    /**
     * Доступ к разделам документа при просмотре/редактировании
     * -Маршрут
     *
     * @return DocRegisterCards
     */
    public boolean getAccessToSectionsDocumentRoute() {
        return accessToSectionsDocumentRoute;
    }

    public DocRegisterCards setAccessToSectionsDocumentRoute(boolean accessToSectionsDocumentRoute) {
        this.accessToSectionsDocumentRoute = accessToSectionsDocumentRoute;
        return this;
    }

    /**
     * Доступ к разделам документа при просмотре/редактировании
     * -Файлы
     *
     * @return DocRegisterCards
     */
    public boolean getAccessToSectionsDocumentFiles() {
        return accessToSectionsDocumentFiles;
    }

    public DocRegisterCards setAccessToSectionsDocumentFiles(boolean accessToSectionsDocumentFiles) {
        this.accessToSectionsDocumentFiles = accessToSectionsDocumentFiles;
        return this;
    }

    /**
     * Доступ к разделам документа при просмотре/редактировании
     * -Резолюции
     *
     * @return DocRegisterCards
     */
    public boolean getAccessToSectionsDocumentResolution() {
        return accessToSectionsDocumentResolution;
    }

    public DocRegisterCards setAccessToSectionsDocumentResolution(boolean accessToSectionsDocumentResolution) {
        this.accessToSectionsDocumentResolution = accessToSectionsDocumentResolution;
        return this;
    }

    /**
     * Доступ к разделам документа при просмотре/редактировании
     * -Журнал
     *
     * @return DocRegisterCards
     */
    public boolean getAccessToSectionsDocumentLog() {
        return accessToSectionsDocumentLog;
    }

    public DocRegisterCards setAccessToSectionsDocumentLog(boolean accessToSectionsDocumentLog) {
        this.accessToSectionsDocumentLog = accessToSectionsDocumentLog;
        return this;
    }

    /**
     * Создание связанных документов
     *
     * @return DocRegisterCards
     */
    public CreationOfLinkedDocuments getCreationOfLinkedDocuments() {
        return creationOfLinkedDocuments;
    }

    public DocRegisterCards setCreationOfLinkedDocuments(CreationOfLinkedDocuments creationOfLinkedDocuments) {
        this.creationOfLinkedDocuments = creationOfLinkedDocuments;
        return this;

    }


    //---------------------------------------------------------------------------Вкладка - МАРШРУТЫ СОГЛАСОВАНИЯ------------------------------------------------------------------


    /**
     * Производим выбор настройки - Использовать все МС
     *
     * @return DocRegisterCards
     */
    public boolean getCheckBoxUseAllPossibleRoutes() {
        return checkBoxUseAllPossibleRoutes;
    }

    public DocRegisterCards setCheckBoxUseAllPossibleRoutes(boolean checkBoxUseAllPossibleRoutes) {
        this.checkBoxUseAllPossibleRoutes = checkBoxUseAllPossibleRoutes;
        return this;

    }

    /**
     * Поля - массив объектов - типа полей объекта - Документ
     *
     * @return
     */
    public DocRegisterCardsField[] getDocRegisterCardsFields() {
        return docRegisterCardsFields;
    }

    public DocRegisterCards setDocRegisterCardsFields(DocRegisterCardsField[] docRegisterCardsFields) {
        this.docRegisterCardsFields = docRegisterCardsFields;
        return this;
    }

    //---------------------------------------------------------------------------Вкладка - ЗАДАЧИ------------------------------------------------------------------

    /**
     * Копирование полей при создании задачи
     *
     * @return DocRegisterCards
     */
    public String getCopyingFieldsWhenCreatingATask() {
        return copyingFieldsWhenCreatingTask;
    }

    public DocRegisterCards setCopyingFieldsWhenCreatingATask(
            String copyingFieldSwhenCreatingTask) {
        this.copyingFieldsWhenCreatingTask = copyingFieldSwhenCreatingTask;
        return this;
    }

    /**
     * Поля документа, содержащие...
     * Авторов задач
     *
     * @return DocRegisterCards
     */
    public String getAuthorsObjectives() {
        return authorsObjectives;
    }

    public DocRegisterCards setAuthorsObjectives(
            String authorsObjectives) {
        this.authorsObjectives = authorsObjectives;
        return this;
    }

    /**
     * Поля документа, содержащие...
     * Контролеров задач
     *
     * @return DocRegisterCards
     */
    public String getControllersOfTasks() {
        return controllersOfTasks;
    }

    public DocRegisterCards setControllersOfTasks(
            String controllersoftasks) {
        this.controllersOfTasks = controllersoftasks;
        return this;
    }

    /**
     * Поля документа, содержащие...
     * Ответственных руководителей задач
     *
     * @return DocRegisterCards
     */
    public String getDecisionMakersOfTasks() {
        return decisionMakersOfTasks;
    }

    public DocRegisterCards setDecisionMakersOfTasks(
            String decisionmakersoftasks) {
        this.decisionMakersOfTasks = decisionmakersoftasks;
        return this;
    }

    /**
     * Поля документа, содержащие...
     * Исполнителей задач
     *
     * @return DocRegisterCards
     */
    public String getExecutorsOfTasks() {
        return executorsOfTasks;
    }

    public DocRegisterCards setExecutorsOfTasks(
            String executorsoftasks) {
        this.executorsOfTasks = executorsoftasks;
        return this;
    }


}
