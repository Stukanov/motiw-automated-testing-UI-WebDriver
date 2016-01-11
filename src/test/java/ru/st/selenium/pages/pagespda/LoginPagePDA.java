package ru.st.selenium.pages.pagespda;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.Page;

import static com.codeborne.selenide.Selenide.page;

/*
 * Страница авторизации - PDA
 */
public class LoginPagePDA extends Page {

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
     * вводим Логин пользователя
     *
     * @param login input text login
     * @param passw input text password
     */
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
        return page(LoginPagePDA.class).isPageLoaded();
    }


}
