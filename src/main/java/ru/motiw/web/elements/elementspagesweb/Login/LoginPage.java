package ru.motiw.web.elements.elementspagesweb.Login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.elements.elementspagesweb.Internal.InternalPage;
import ru.motiw.web.logicinterface.WebLogic.AuthorizationLogic;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.elements.BasePage;
import ru.motiw.web.elements.elementspagesweb.Internal.LibraryPage;
import ru.motiw.web.elements.elementspagesweb.Options.PwdPage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Стараница авторизации - Web
 */
public class LoginPage extends BasePage implements AuthorizationLogic {

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
        return page(InternalPage.class).isPageLoadedInternal();
    }

    /**
     * Проверяем то, что мы разлогинены
     *
     * @return LoginPage
     */
    @Override
    public boolean isNotLoggedIn() {
        return page(LoginPage.class).isPageNotLoadedInternal();
    }

    /**
     * Метод обращается к ensurePageLoaded и возвращает булевское значение,
     * (false - не дождались загрузки стр.; true - дождались) ждет загрузки
     * страницы
     */
    public boolean isPageNotLoadedInternal() {
        try {
            submitButton.shouldBe(visible);
            passwordField.shouldBe(visible);
            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }

    /**
     * Проверяем, что мы не только залогинены, но залогинены под конкретным пользователем
     *
     * @param user атрибуты пользователя (в данном случае Логин пользователя)
     * @return возвращаяет истенность, если Логин пользователя (при авторизации) совпадает с логином указанный
     * в реквизитах пользователя
     */
    @Step("Проверяем, что используемый логин пользователя при входе в Систему " +
            "соответствует логину заданному в реквизитах пользователя в Мои реквизиты")
    @Override
    public boolean newUserIsLoggedInAs(Employee user) {
        return isLoggedIn()
                && getLoggedUser().getLoginName().equals(user.getLoginName());
    }

    /**
     * Проверяем отображение системной папки пользователя
     *
     * @param user атрибуты пользователя (в данном случае Логин пользователя) ФИО
     * @return возвращаяет истенность, если ФИО пользователя (при создании) совпадает с названием системной папки Библиотеки
     */
    @Step("Проверяем отображение созданной системной папки Библиотеки пользователя")
    @Override
    public boolean checkTheSystemFolderMappingUserLibrary(Employee user) {
        return isLoggedIn()
                && getNameOfTheSystemFolderUserLibrary().getFullName()
                .equals(user.getLastName() + " " + user.getName() + " " + user.getPatronymic());
    }

    /**
     * Проверяем отображение WN (What's new) на странице приветствия
     *
     * @return возвращаем коллекцию заголовков на стр. приветствия
     */
    public ElementsCollection getVerifyWNInfo() {
        getFrameFlow();
        $(By.xpath("//h2[text()]")).shouldBe(Condition.exactText("Связь"));
        return $$(By.xpath("//h2[text()]")).shouldHaveSize(16);
    }

    /*
     TODO 0006 Обязательно Добавить проверку отображения АК
     TODO 0007  проверка отображения системных библиотек при создании Подразделения
      */

    private Employee getNameOfTheSystemFolderUserLibrary() {

        LibraryPage userNameOfSystemLibraryFolder = initializedInsidePage().goToLibrary()
                .ensurePageLoaded();
        return new Employee().setFullName(userNameOfSystemLibraryFolder.getParentFullNameSystemBoxLibrary());

    }

    @Override
    public boolean hasMenuUserComplete() {
        return false;
    }

    private Employee getLoggedUser() {
        getVerifyWNInfo();
        PwdPage userProfile = initializedInsidePage().goToPwd()
                .ensurePageLoaded();
        return new Employee().setLoginName(userProfile.getLoginName());

    }


}
