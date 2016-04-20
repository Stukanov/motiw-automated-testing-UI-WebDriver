package ru.st.selenium.pages.pagesweb.Tasks.TaskFormElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - Форма выбора пользователей в роли задачи
 */
public class UsersSelectTheFormElements {


    @FindBy(css = "#SearchEdit")
    private SelenideElement userSearchField;

    @FindBy(xpath = "//img[contains (@onclick, 'check')]")
    private SelenideElement userAddButton;

    @FindBy(xpath = "//*[@id='btn_save']//button")
    private SelenideElement userSaveButton;

    /**
     * Поиск пользователя в гриде
     */
    public SelenideElement getUserSearchField() {
        return userSearchField;
    }

    /**
     * Добавить пользователя
     */
    public SelenideElement getUserAddButton() {
        return userAddButton;
    }

    /**
     * Сохранить
     */
    public SelenideElement getUserSaveButton() {
        return userSaveButton;
    }
}
