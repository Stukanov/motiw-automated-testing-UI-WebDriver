package ru.motiw.web.elements.elementsweb.TasksElements.TaskFormElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - форма создания нового Проекта
 */
public class ProjectFormElements {

    @FindBy(xpath = "//*[contains (@class, 'x-editor')][not(contains (@style, 'none'))]//input")
    private SelenideElement editorFieldProject;

    @FindBy(xpath = "//textarea")
    private SelenideElement editorDescriptionProject;

    @FindBy(xpath = "(//table)[1]//td[2]/div")
    private SelenideElement projectField;

    @FindBy(xpath = "(//table)[2]//td[2]/div")
    private SelenideElement projectDescription;

    @FindBy(xpath = "(//table)[4]//td[2]/div")
    private SelenideElement projectClient;

    @FindBy(xpath = "(//table)[6]//td[2]/div")
    private SelenideElement projectEnd;

    @FindBy(xpath = "//*[contains (@class, 'footer')]//a[3]/../a[1]//span[2]")
    private SelenideElement projectSave;

    /**
     * поля ввода - в форме Проекта
     *
     * @return элемент поля ввода Название проекта
     */
    public SelenideElement getEditorFieldProject() {
        return editorFieldProject;
    }

    /**
     * поле ввода - Описание проекта
     *
     * @return элемент поля ввода Описание
     */
    public SelenideElement getEditorDescriptionProject() {
        return editorDescriptionProject;
    }

    /**
     * Выбор поля - Проект
     *
     * @return элемент поля - Проект
     */
    public SelenideElement getProjectField() {
        return projectField;
    }

    /**
     * Выбор поля - Описание
     *
     * @return элемент поля - Описание
     */
    public SelenideElement getProjectDescription() {
        return projectDescription;
    }

    /**
     * Выбор поля - Заказчик
     *
     * @return элемент поля - Заказчик
     */
    public SelenideElement getProjectClient() {
        return projectClient;
    }

    /**
     * Выбор поля - Дата окончания
     *
     * @return элемент поля - Дата окончания
     */
    public SelenideElement getProjectEnd() {
        return projectEnd;
    }

    /**
     * Сохранить проект
     *
     * @return элемент поля - Сохранить проект
     */
    public SelenideElement getProjectSave() {
        return projectSave;
    }


}
