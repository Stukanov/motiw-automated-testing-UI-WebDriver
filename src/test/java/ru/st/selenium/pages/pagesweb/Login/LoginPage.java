package ru.st.selenium.pages.pagesweb.Login;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.logicinterface.WebLogic.AuthorizationLogic;
import ru.st.selenium.model.Administration.Users.Employee;
import ru.st.selenium.pages.Page;
import ru.st.selenium.pages.pagesweb.Internal.InternalPage;
import ru.st.selenium.pages.pagesweb.Options.PwdPage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Стараница авторизации - Web
 */
public class LoginPage extends Page implements AuthorizationLogic {

    /*
     * Логин
     */
    @FindBy(css = "#login")
    private SelenideElement loginField;

    /*
     * Пароль
     */
    @FindBy(css = "#pass")
    private SelenideElement passwordField;

    /*
     * Вход
     */
    @FindBy(css = "#subm")
    private SelenideElement submitButton;

    /**
     * Вводим Login пользователя
     *
     * @param text
     * @return
     */
    public LoginPage setLoginField(String text) {
        loginField.clear();
        loginField.setValue(text);
        return this;
    }

    /**
     * Вводим пароль пользователя
     *
     * @param text
     * @return
     */
    public LoginPage setPasswordField(String text) {
        passwordField.clear();
        passwordField.setValue(text);
        return this;
    }


    /**
     * Инициализируем внутреннюю страницу
     *
     * @return results internal page instance
     */
    public InternalPage initializedInsidePage() {
        return page(InternalPage.class);
    }


    /**
     * Авторизация под указанным пользователем
     *
     * @param user
     */
    @Step("Authorization system under the specified user")
    @Override
    public LoginPage loginAs(Employee user) {
        getWebDriver().manage().window().maximize();
        setLoginField(user.getLoginName());
        setPasswordField(user.getPassword());
        submitButton.click();
        return this;
    }


    /**
     * Проверка истинности загрузки внутренней стр-цы
     *
     * @return InternalPage
     */
    @Override
    public boolean isLoggedIn() {
        return page(InternalPage.class).isPageLoaded();
    }

    /**
     * Проверяем то, что мы разлогинены
     *
     * @return LoginPage
     */
    @Override
    public boolean isNotLoggedIn() {
        return page(LoginPage.class).isPageLoaded();
    }

    /**
     * Проверяем, что мы не только залогинены, но залогинены под конкретным пользователем
     */
    @Override
    public boolean isLoggedInAs(Employee user) {
        return isLoggedIn()
                && getLoggedUser().getLoginName().equals(user.getLoginName());
    }
    

    @Override
    public boolean hasMenuUserComplete() {
        return false;
    }

    private Employee getLoggedUser() {

        PwdPage userProfile = initializedInsidePage().ensurePageLoaded().goToPwd()
                .ensurePageLoaded();
        return new Employee().setLastName(userProfile.getLastName())
                .setLoginName(userProfile.getLoginName());

    }




}
