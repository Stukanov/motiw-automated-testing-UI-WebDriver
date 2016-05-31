package ru.motiw.web.elements.elementspagespda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.elements.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.page;

/*
 * Страница авторизации - PDA
 */
public class LoginPagePDA extends BasePage {

    /*
    Логин
    */
    @FindBy(css = "#login")
    private SelenideElement inputLogin;

    /*
    Пароль
     */
    @FindBy(css = "#pass")
    private SelenideElement inputPassword;

    /*
    Вход
     */
    @FindBy(css = "input[name='logon']")
    private SelenideElement loginButton;


    /**
     * вводим Логин и Пароль пользователя
     *
     * @param login input text login
     * @param passw input text password
     */
    @Step("вводим Логин и Пароль пользователя")
    public void loginAs(String login, String passw) {
        inputLogin.clear();
        inputLogin.setValue(login);
        inputPassword.clear();
        inputPassword.setValue(passw);
        loginButton.click();
    }

    /**
     * Вводим Login пользователя
     * @param login input text login
     * @return
     */
    public LoginPagePDA setLoginField(String login) {
        inputLogin.setValue(login);
        return this;
    }

    /**
     * Вводим пароль пользователя
     * @param password input text password
     * @return
     */
    public LoginPagePDA setPasswordField(String password) {
        inputPassword.setValue(password);
        return this;
    }

    /**
     * Авторизация под пользователем
     * @param user
     */
    public LoginPagePDA loginAsAdmin(Employee user) {
        setLoginField(user.getLoginName());
        setPasswordField(user.getPassword());
        return this;
    }

    /**
     * Проверяем отображение меню на внутренней странице
     *
     * @return results internal page instance
     */
    public InternalPagePDA goToInternalMenu() {
        loginButton.click();
        return page(InternalPagePDA.class);
    }

    /**
     * Проверяем то, что мы разлогинены - ПДА-интерфейс
     */
    public boolean isNotLoggedInPDA() {
        try {
            loginButton.shouldBe(Condition.visible);
            inputPassword.shouldBe(Condition.visible);
            inputLogin.shouldBe(Condition.visible);
            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }


}
