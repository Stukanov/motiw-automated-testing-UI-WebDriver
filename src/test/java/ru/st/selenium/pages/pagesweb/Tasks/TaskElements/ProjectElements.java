package ru.st.selenium.pages.pagesweb.Tasks.TaskElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - форма создания нового Проекта
 */
public class ProjectElements {

    //------------------------------------------------------------------------------------------------------------Форма проекта

    /*
     * поля ввода - в форме Проекта
     */
    @FindBy(xpath = "//*[contains (@class, 'x-editor')][not(contains (@style, 'none'))]//input")
    private SelenideElement editorFieldProject;

    /*
     * поле ввода - Описание проекта
     */
    @FindBy(xpath = "//textarea")
    private SelenideElement editorDescriptionProject;

    /*
     * Проект - Название проекта (Проект)
     */
    @FindBy(xpath = "(//table)[1]//td[2]/div")
    private SelenideElement nameProjectField;

    /*
     * Проект - Описание
     */
    @FindBy(xpath = "(//table)[2]//td[2]/div")
    private SelenideElement projectDescription;

    /*
     * Проект - Заказчик
     */
    @FindBy(xpath = "(//table)[4]//td[2]/div")
    private SelenideElement projectClient;

    /*
     * Проект - Окончание проекта
     */
    @FindBy(xpath = "(//table)[6]//td[2]/div")
    private SelenideElement projectEnd;

    /*
     * Сохранить проект
     */
    @FindBy(xpath = "//*[contains (@class, 'footer')]//a[3]/../a[1]//span[2]")
    private SelenideElement projectSave;


    public SelenideElement getEditorFieldProject(){
        return editorFieldProject;
    }

    public SelenideElement getEditorDescriptionProject(){
        return editorDescriptionProject;
    }

    /**
     * Выбор поля - Проект
     * @return
     */
    public SelenideElement getNameProjectField(){
        return nameProjectField;
    }

    public SelenideElement getProjectDescription(){
        return projectDescription;
    }

    public SelenideElement getProjectClient(){
        return projectClient;
    }

    public SelenideElement getProjectEnd(){
        return projectEnd;
    }

    public SelenideElement getProjectSave(){
        return projectSave;
    }



}
