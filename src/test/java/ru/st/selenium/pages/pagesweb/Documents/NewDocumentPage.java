package ru.st.selenium.pages.pagesweb.Documents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.st.selenium.logicinterface.WebLogic.DocumentLogic;
import ru.st.selenium.model.DocflowAdministration.DictionaryEditor.DictionaryEditorField;
import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCardsField;
import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.*;
import ru.st.selenium.model.Document.Document;
import ru.st.selenium.model.Tasks.Project;
import ru.st.selenium.model.Administration.Users.Department;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.BasePage;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.st.selenium.utils.WindowsUtil.newWindowForm;


public class NewDocumentPage extends BasePage implements DocumentLogic {

    /**
     * iФрейм
     */
    @FindBy(id = "flow")
    private SelenideElement frameFlow;

    /**
     * Фрейм CKE (расширенный текстовый редактор)
     */
    @FindBy(xpath = "//*[@class='cke_wysiwyg_frame cke_reset']")
    private SelenideElement descriptionFrame;

    /**
     * Фрейм создания проекта
     */
    @FindBy(xpath = "//*[contains (@src, 'project')]")
    private SelenideElement projectFrame;

    /**
     * Фрейм - МС
     */
    @FindBy(css = "#routeframe")
    private SelenideElement frameRoute;

    /**
     * Вкладка - Карточка документа
     */
    @FindBy(xpath = "(//span[contains(@class,'x-tab-strip')][ancestor::li[contains(@class,'x-tab-strip-active')]])[2]")
    private SelenideElement documentCartTab;

    /**
     * Вкладка - Маршрут
     */
    @FindBy(xpath = "(//span[contains(@class,'x-tab-strip')][ancestor::li[contains(@id,'documentRoutes')]])[2]")
    private SelenideElement routeTab;

    /**
     * Выбор списка - Тип документа
     */
    @FindBy(xpath = "//div[@id='div_docType'][ancestor::div[@id='doc_type_templID']]//input")
    private SelenideElement fieldDocumentType;

    /**
     * Выбор поля - Порядок рассмотрения
     */
    @FindBy(css = "#cb_route_name")
    private SelenideElement route;

    /**
     * Выбор списка - Маршруты
     */
    @FindBy(xpath = "//input[@id='cb_route_name']/../img")
    private SelenideElement listOfRoutes;


    //-----------------------------------------------------------------------------------------------Системные поля документа-------------------------------

    /**
     * Дата регистрации
     */
    @FindBy(xpath = "//div[contains(text(),'Дата регистрации')]/../../td[2]//div")
    private SelenideElement fieldRegistrationDate;

    /**
     * Создвть новый проект
     */
    @FindBy(xpath = "//div[count(img)=2]//img[2]")
    private SelenideElement newProject;

    /**
     * Выбрать существующий проект
     */
    @FindBy(xpath = "//div[count(img)=2]//img[1]")
    private SelenideElement selectExistentProject;

    /**
     * Поле проект
     */
    @FindBy(xpath = "//tr[1]/td[2]/div")
    private SelenideElement projectField;

    /**
     * Описание проекта
     *
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

    /**
     * Поле ввода для  поля проекта
     */
    @FindBy(xpath = "//*[contains (@class, 'x-editor')][not(contains (@style, 'none'))]//input")
    private SelenideElement editorFieldProject;

    /**
     * Поле текста для  проекта
     */
    @FindBy(xpath = "//textarea")
    private SelenideElement editorTextProject;


    //--------------------------------------------------------------------------------------------Поля ввода в форме документа---------------------------------

    /**
     * input поля
     */
    @FindBy(xpath = "//input[contains(@id,'ext-comp')][ancestor::div[contains(@style,'visibility: visible')]]")
    private SelenideElement inputField;

    /**
     * input поля 2 (Для полей типа Строка с предопределенным спр-ом)
     */
    @FindBy(xpath = "//input[contains(@id,'taSearchID')][ancestor::div[contains(@style,'visibility: visible')]]")
    private SelenideElement input2Field;

    /**
     * input поля 3 (Для полей типа Сотрудник)
     */
    @FindBy(xpath = "//input[contains(@id,'_name_ext')][ancestor::div[contains(@style,'visibility: visible')]]")
    private SelenideElement input3Field;


    //------------------------------------------------------------------------------------------Пользовательские поля документа-----------------------------

    /**
     * Область редактирования поля типа Текст
     *
     */
    @FindBy(css = "body")
    private SelenideElement ckeBody;

    /**
     * Кнопка сохранения в форме расширенного текстового редактора (CKE)
     *
     */
    @FindBy(xpath = "//*[contains (@class,'window-noborder')][contains (@style,'visible')]//td[contains (@class,'cell')][1]")
    private SelenideElement buttonSaveDescription;

    /**
     * Поле поиска в окне поля - типа "Подразделение"
     */
    @FindBy(css = "#searchField")
    private SelenideElement searchFieldDepartment;

    /**
     * Чебокс подсвеченного узла окна добавления псевдонима в подразделение
     */
    @FindBy(xpath = "//*[contains (@class, 'selected')][contains (@class, 'tree-node')]/*[@type='checkbox'] ")
    private SelenideElement selectedCheckBox;

    /**
     * Сохранить - в окне поля - типа "Подразделение"
     */
    @FindBy(xpath = "(//button[not(contains(@style,'url'))])[1]")
    private SelenideElement buttonSave;

    /**
     * Отменить - в окне поля - типа "Подразделение"
     */
    @FindBy(xpath = "(//button[not(contains(@style,'url'))])[2]")
    private SelenideElement buttonCancel;

    /**
     * Поле Поиск в форме выбора пользователей
     */
    @FindBy(css = "#SearchEdit")
    private SelenideElement searchEmployee;


    //--------------------------------------------------------------------------------------Основные объекты формы создания документа--------------------------

    /**
     * Сохранить
     */
    @FindBy(xpath = "//div[@id='saveButton']//button")
    private SelenideElement saveDocument;

    /**
     * Сохранить и создать новый документ
     */
    @FindBy(xpath = "//div[@id='saveAndNewButton']//button")
    private SelenideElement saveAndCreateNewDocument;

    /**
     * Создать резолюцию
     */
    @FindBy(xpath = "//div[@id='newStageButton']//button")
    private SelenideElement createWithResolution;

    /**
     * Поиск
     */
    @FindBy(xpath = "//div[@id='searchButton']//button")
    private SelenideElement searchButton;

    /**
     * Добавить файл
     */
    @FindBy(xpath = "//div[@id='addFileButtonDiv']//input")
    private SelenideElement addFileButtonDiv;

    /**
     * Сканировать
     */
    @FindBy(xpath = "//div[@id='btnScaner']//button")
    private SelenideElement btnScaner;

    //---------------------------------------------------------------------------------------------------------------Методы---------------------------------------------


    /**
     * Уходим во фрейм формы Создать документ
     */
    public NewDocumentPage gotoFrameFormNewDocument() {
        switchTo().frame(frameFlow);
        return this;
    }

    /**
     * Уходим во фрейм Маршруты
     */
    public NewDocumentPage gotoFrameFormRoute() {
        switchTo().frame(frameRoute);
        return this;
    }

    /**
     * Выбираем вкладку - Карточки документа
     */
    public NewDocumentPage selDocumentCartTab() {
        documentCartTab.click();
        return this;
    }

    /**
     * выбор поля - Тип документа
     *
     * @param typeNameDoc передаваемое название Типа документа - выбор в форме Создания документа
     */
    public NewDocumentPage selFieldDocumentType(DocRegisterCards typeNameDoc) {
        if (typeNameDoc == null) {
            return this;
        } else {
            $(fieldDocumentType).shouldBe(Condition.present);
            fieldDocumentType.click();
            $(By.xpath("//div[@id='treePanel']//li//a/*[text()]")).shouldBe(Condition.visible);
            $(By.xpath("//div[@id='treePanel']//li//a/*[text()='" + typeNameDoc.getDocRegisterCardsName() + "']")).click();
        }
        return this;
    }

    /**
     * Заполняем поле - Дата регистрации
     *
     * @param dateRegistration передаваемое значение Дата регистрации - используется для заполнения
     */
    public NewDocumentPage writeInRegistrationDate(String dateRegistration) {
        if (dateRegistration == null) {
            return this;
        } else {
            waitForFormNewDocumentMask();
            fieldRegistrationDate.click();
            inputField.clear();
            inputField.setValue(dateRegistration);
        }
        return this;
    }

    /**
     * Создание нового проекта для документа
     */
    public NewDocumentPage createProject(Project project) {
        if (project == null) {
            return this;
        } else {
            newProject.click();
            switchTo().frame(projectFrame);
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
            switchTo().defaultContent();
            switchTo().frame(frameFlow);
        }
        return this;
    }

    /*
     * Ожидание маски проекта
     */
    public NewDocumentPage waitForProjectMask() {
        sleep(300);
        $(By.xpath("//*[contains (@class, 'x-mask x-mask-fixed')]")).shouldBe(Condition.disappear);
        return this;
    }

    /*
     * Ожидание исчезновения маски из DOM в форме создания документа
     */
    public NewDocumentPage waitForFormNewDocumentMask() {
        sleep(300);
        $(By.xpath("//div[contains(@class,'ext-el-mask-msg x-mask-loading') or @class='ext-el-mask' and not(@style='display: none;')]")).shouldBe(Condition.disappear);
        return this;
    }

    /**
     * Ввод значения в поле типа "Текст"
     */
    public NewDocumentPage selEditText(String nameField, String text) {
        if (text == null) {
            return this;
        } else {
            $(By.xpath("//table//tr/td[1]/div[contains(text(),'" + nameField + "')]/../../td[2]/div/../../td[3]//img")).click();
            switchTo().frame(descriptionFrame);
            ckeBody.setValue(text);
            switchTo().defaultContent();
            switchTo().frame(frameFlow);
            buttonSaveDescription.click();
        }
        return this;
    }

    /**
     * Ввод значения в поле типа "Словарь"
     */
    public NewDocumentPage selValueDictionary(String nameField, DictionaryEditorField valueDictionary) {
        if (valueDictionary == null) {
            return this;
        } else {
            $(By.xpath("//table//tr/td[1]/div[contains(text(),'" + nameField + "')]/../../td[2]/div")).click();
            inputField.click();
            $(By.xpath("//div[contains(@class,'x-combo-list')]//*[contains(text(),'" + valueDictionary
                    .getDictionaryEditorElement() + "')][ancestor::div[contains(@style,'visibility: visible')]]")).shouldBe(Condition.present);
            $(By.xpath("//div[contains(@class,'x-combo-list')]//*[contains(text(),'" + valueDictionary
                    .getDictionaryEditorElement() + "')][ancestor::div[contains(@style,'visibility: visible')]]")).click();
        }
        return this;
    }

    /**
     * Общий метод заполнения полей документа
     *
     * @param nameField имя поля документа для заполнения
     * @param valueLine передаваемое значение для заполнения
     */
    public NewDocumentPage enterValueInField(String nameField, String valueLine) {
        if (valueLine == null) {
            return this;
        } else {
            $(By.xpath("//table//tr/td[1]/div[contains(text(),'" + nameField + "')]/../../td[2]/div")).click();
            try {
                $(By.xpath
                        ("//input[contains(@id,'ext-comp')][ancestor::div[contains(@style,'visibility: visible')]]")).shouldBe(Condition.visible);
                inputField.clear();
                inputField.setValue(valueLine);
            } catch (Exception e) {
                input2Field.clear();
                input2Field.setValue(valueLine);
            }
        }
        return this;
    }

    /**
     * Выбор значения в поле типа "Подразделение"
     */
    public NewDocumentPage selEditDepartment(String nameField, Department[] department) {
        if (department == null) {
            return this;
        } else {
            for (Department departments : department) {
                $(By.xpath("//table//tr/td[1]/div[contains(text(),'" + nameField + "')]/../../td[2]/div/../../td[3]//img")).click();
                String parentWindowHandler = getWebDriver().getWindowHandle(); // Store your parent window
                switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.cssSelector("#searchField"))));
                $(searchFieldDepartment).shouldBe(Condition.present);
                searchFieldDepartment.click();
                searchFieldDepartment.clear();
                searchFieldDepartment.setValue(departments.getDepName());
                searchFieldDepartment.pressEnter();
                selectedCheckBox.click();
                buttonSave.click();
                getWebDriver().switchTo().window(parentWindowHandler);  // Switch back to parent window
                gotoFrameFormNewDocument();
            }
        }
        return this;
    }

    /**
     * Добавление Сотрудник через livesearch - поиск по Фамилии
     *
     * @param nameStr заполняемое полей - передаваемое поле для заполнения
     * @param employee кол-во передаваемых пользователей
     */
    public NewDocumentPage selLiveSearchEmployee(String nameStr, Employee[] employee) {
        if (employee == null) {
            return this;
        } else {
            for (Employee employees : employee) {
                $(By.xpath("//table//tr/td[1]/div[contains(text(),'" + nameStr + "')]/../../td[2]/div")).click();
                input3Field.setValue(employees.getLastName());
                $(By.xpath("//div[contains(@class,'x-combo-list')]//*[contains(text(),'" + employees
                        .getLastName() + "')][ancestor::div[contains(@style,'visibility: visible')]]")).shouldBe(Condition.present);
                $(By.xpath("//div[contains(@class,'x-combo-list')]//*[contains(text(),'" + employees
                        .getLastName() + "')][ancestor::div[contains(@style,'visibility: visible')]]")).click();
            }
            return this;
        }
    }

    /**
     * TODO Выбор значения из формы выбора пользователей - поле типа "Сотрудник"
     *
     */
   /* public NewDocumentPage selEditEmployee(Employee[] employee) {
        if (employee == null) {
            return this;
        } else {
            for (Employee employees : employee) {
                editEmployee.click();
                String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
                driver.switchTo().window(new WebDriverWait(driver, 10).until(newWindowForm(By.cssSelector("#searchEmployee"))));
                wait.until(ExpectedConditions.elementToBeClickable(searchEmployee));
                searchEmployee.click();
                searchEmployee.clear();
                searchEmployee.setValue(employees.getLastName());
                searchFieldDepartment.setValue(Keys.RETURN);
                selectedCheckBox.click();
                buttonSave.click();
                driver.switchTo().window(parentWindowHandler);  // Switch back to parent window
            }
        }
        return this;
    } */

    /**
     * Заполняем пользовательские поля документа
     *
     * @param customField зн-ия полей для заполнения
     */
    public NewDocumentPage fillCustomFieldsDocument(DocRegisterCardsField[] customField) {
        if (customField == null) {
            return null;
        } else
        for (DocRegisterCardsField customsField : customField) {
            // 1. ЧИСЛО
            if (customsField.getFieldTypeDoc() instanceof FieldTypeNumberDoc) {
                FieldTypeNumberDoc fieldNumber = (FieldTypeNumberDoc) customsField.getFieldTypeDoc();
                enterValueInField(customsField.getFieldNameDoc(), customsField.getValueField());
                // 2. ДАТА
            } else if (customsField.getFieldTypeDoc() instanceof FieldTypeDateDoc) {
                FieldTypeDateDoc fieldDate = (FieldTypeDateDoc) customsField.getFieldTypeDoc();
                enterValueInField(customsField.getFieldNameDoc(), customsField.getValueField());
                // 3. СТРОКА
            } else if (customsField.getFieldTypeDoc() instanceof FieldTypeStringDoc && customsField.getUniqueField()) {
                FieldTypeStringDoc fieldString = (FieldTypeStringDoc) customsField.getFieldTypeDoc();
                enterValueInField(customsField.getFieldNameDoc(), customsField.getValueField());
                // 4. ТЕКСТ
            } else if (customsField.getFieldTypeDoc() instanceof FieldTypeTextDoc) {
                FieldTypeTextDoc fieldText = (FieldTypeTextDoc) customsField.getFieldTypeDoc();
                selEditText(customsField.getFieldNameDoc(), customsField.getValueField());
                // 5. СЛОВАРЬ
            } else if (customsField.getFieldTypeDoc() instanceof FieldTypeDictionaryDoc) {
                FieldTypeDictionaryDoc fieldDictionary = (FieldTypeDictionaryDoc) customsField.getFieldTypeDoc();
                selValueDictionary(customsField.getFieldNameDoc(), customsField.getValueDictionaryEditor());
                // 6. ПОДРАЗДЕЛЕНИЕ
            } else if (customsField.getFieldTypeDoc() instanceof FieldTypeDepartmentDoc) {
                FieldTypeDepartmentDoc fieldDepartment = (FieldTypeDepartmentDoc) customsField.getFieldTypeDoc();
                selEditDepartment(customsField.getFieldNameDoc(), customsField.getValueDepartment());
                // 7. СОТРУДНИК
            } else if (customsField.getFieldTypeDoc() instanceof FieldTypeEmployeeDoc) {
                FieldTypeEmployeeDoc fieldEmployee = (FieldTypeEmployeeDoc) customsField.getFieldTypeDoc();
                selLiveSearchEmployee(customsField.getFieldNameDoc(), customsField.getValueEmployee());
                // 8. ДОКУМЕНТ
            } else if (customsField.getFieldTypeDoc() instanceof FieldTypeDocumentDoc) {
                FieldTypeDocumentDoc fieldDocument = (FieldTypeDocumentDoc) customsField.getFieldTypeDoc();
                // 9. НУМЕРАТОР
            } else if (customsField.getFieldTypeDoc() instanceof FieldTypeNumeratorDoc) {
                FieldTypeNumeratorDoc fieldNumerator = (FieldTypeNumeratorDoc) customsField.getFieldTypeDoc();
                // 10. СПРАВОЧНИК
            } else if (customsField.getFieldTypeDoc() instanceof FieldTypeDirectoryDoc) {
                FieldTypeDirectoryDoc fieldDirectory = (FieldTypeDirectoryDoc) customsField.getFieldTypeDoc();

            }
        }
        return this;
    }

    /**
     * Выбираем вкладку - Маршруты
     *
     */
    public NewDocumentPage routeTab() {
        routeTab.click();
        $(By.cssSelector("#cb_route_name")).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Выбор маршрутной схемы документа (Порядок рассмотрения)
     *
     */
    public NewDocumentPage selRouteScheme(String routeScheme) {
        if (routeScheme == null) {
            return this;
        } else {
            listOfRoutes.click();
            $(By.xpath("//div[contains(@class,'x-combo-list')][ancestor::div[contains(@style,'visibility: visible')]]")).shouldBe(Condition.present);
            $(By.xpath("//div[contains(@class,'x-combo-list')]/*[text()='" + routeScheme + "'][ancestor::div[contains(@style,'visibility: visible')]]")).click();
            gotoFrameFormRoute(); // уходим во Фрейм МС
            $(By.xpath("//table/colgroup/col")).shouldBe(Condition.present); // ожидаем появление блоков МС
        }
        return this;
    }

    /**
     * Сохранить и создать новый документ
     *
     */
    public NewDocumentPage clickSaveAndCreateNewDocument() {
        getFrameTop();
        gotoFrameFormNewDocument();
        $(By.xpath("//div[@id='saveAndNewButton']//button")).shouldBe(Condition.present);
        saveAndCreateNewDocument.click();
        return this;
    }

    /**
     * Проверяем отображение надписи - Зарегистрировано, документ находится на рассмотрении - после сохранения документа
     *
     */
    public NewDocumentPage assertVerifyCreateDoc() {
        $(By.xpath("//a[@class='error_message' and @style='text-decoration:none']")).shouldBe(Condition.visible);
        return this;
    }


    /**
     * Создать документ
     *
     * @param document атрибуты  (значения) документа для заполнения в форме Создания документа
     */
    @Override
    public void createDocument(Document document) {
        selFieldDocumentType(document.getDocumentType()) // выбираем проинициализированный Тип документа
                .writeInRegistrationDate(document.getDateRegistration()) // Дата регистрации
                .createProject(document.getProject()) // добавляем новый проект
                .fillCustomFieldsDocument(document.getDocumentFields()) // заполнение пользовательских полей документа
                .routeTab() // Выбор вкладки - Маршруты
                .selRouteScheme(document.getRouteScheme())
                .clickSaveAndCreateNewDocument() // Сохранить и создать новый документ
                .assertVerifyCreateDoc(); // Проверяем создание документа

    }

    /**
     * Резолюции
     */
    @Override
    public void createWithResolution() {

    }
}

