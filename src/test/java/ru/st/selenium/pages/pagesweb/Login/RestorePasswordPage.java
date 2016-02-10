package ru.st.selenium.pages.pagesweb.Login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.BasePage;


import static com.codeborne.selenide.Selenide.$;

/**
 * Стараница сброса пароля пользователя
 */
public class RestorePasswordPage extends BasePage {


    /**
     * Поле ввода нового пароля
     */
    @FindBy(id = "newpass-inputEl")
    private SelenideElement newPasswordField;

    /**
     * Поле ввода подтверждения пароля
     */
    @FindBy(id = "newpass_confirm-inputEl")
    private SelenideElement newPasswordConfirmField;

    /**
     * Кнопка - Отправить
     */
    @FindBy(xpath = "//a[contains(@id,'button')]")
    private SelenideElement buttonToSend;

    /**
     * Кнопка - Стартовая страница
     */
    @FindBy(xpath = "//a[@href='/user/']")
    private SelenideElement clickHomePage;

    /**
     * Новый Пароль
     *
     * @param text
     * @return
     */
    public RestorePasswordPage selPasswordField(String text) {
        newPasswordField.setValue(text);
        return this;
    }

    /**
     * Подтверждение нового пароля
     *
     * @param text
     * @return
     */
    public RestorePasswordPage selPasswordConfirmField(String text) {
        newPasswordConfirmField.setValue(text);
        return this;
    }

    /**
     * Нажатия кнопки - Отправить
     *
     * @return RestorePasswordPage
     */
    public RestorePasswordPage clickButtonToSend() {
        buttonToSend.click();
        return this;
    }

    /**
     * Линк переход на Стартовую страницу
     *
     * @return RestorePasswordPage
     */
    public RestorePasswordPage linkHome() {
        clickHomePage.click();
        return this;
    }

    public RestorePasswordPage passwordChange(Employee user) {
        ensurePageLoaded()
                .selPasswordField(user.getNewPassword())
                .selPasswordConfirmField(user.getNewСonfirmationPassword())
                .clickButtonToSend().linkHome();
        return this;
    }

    /**
     * Ожидание элементов - текст - "Вам необходимо сменить" И newpass
     */
    public RestorePasswordPage ensurePageLoaded() {
        $(By.id("newpass_confirm-inputEl")).shouldBe(Condition.visible);
        $(By.xpath("//label[text()]"))
                .shouldHave(Condition.exactText("Вам необходимо сменить пароль"));
        return this;
    }

}
