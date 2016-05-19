package ru.motiw.web.elements.elementsweb.Options;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.elements.BasePage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Стараница - Мои реквизиты
 */
public class PwdPage extends BasePage {

    /*
    Фамилия
     */
    @FindBy(xpath = "//div[@class='x-box-inner']/div[1]//tbody/tr[1]/td[2]/input [ancestor::div[@class='x-window-mc']]")
    private SelenideElement lastNameField;

    /*
    Логин
     */
    @FindBy(xpath = "//div[text()='Имя пользователя']/parent::td/parent::tr/parent::tbody/parent::table/parent::div/parent::div/following-sibling::div/input")
    private SelenideElement loginNameField;

    @FindBy(xpath = "//div[text()='Имя пользователя']/parent::td/following-sibling::td[1]/div")
    private SelenideElement clickLoginNameField;


    public String getLastName() {
        return lastNameField.getAttribute("value");
    }

    public PwdPage setUsernameField(String text) {
        lastNameField.setValue(text);
        return this;
    }

    public String getLoginName() {
        clickLoginNameField.click();
        return loginNameField.getAttribute("value");
    }

    public PwdPage setLoginField(String login) {
        loginNameField.setValue(login);
        return this;
    }


    // Проверяем и ожидаем, что на странице имеется соответствующий элемент
    public PwdPage ensurePageLoaded() {
        $(By.xpath("//tbody/tr//label[text()='Дата рождения:']")).shouldBe(Condition.visible);
        $(By.xpath("//tbody/tr//label[text()='Должность:']")).shouldBe(Condition.visible);
        return this;
    }
}
