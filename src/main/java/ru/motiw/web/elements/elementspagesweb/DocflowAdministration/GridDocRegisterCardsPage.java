package ru.motiw.web.elements.elementspagesweb.DocflowAdministration;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.logicinterface.WebLogic.DocRegisterCardsLogic;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.elements.BasePage;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * грид Регистрационных карточек - Администрирование ДО/Регистрационные карточки документов (.../user/tab/user/DocRegisterCards)
 */
public class GridDocRegisterCardsPage extends BasePage implements DocRegisterCardsLogic {

    /*
     * Добавить Регистрационную карточку документа
     */
    @FindBy(xpath = "//div[count(a)=3]/a[1]//span")
    private SelenideElement clickAddOnRegCards;

    /*
     * Редактировать Регистрационную карточку документа
     */
    @FindBy(xpath = "//div[count(a)=3]/a[2]//span")
    private SelenideElement clickEditOnRegCards;

    /*
     * Удалить Регистрационную карточку документа
     */
    @FindBy(xpath = "//div[count(a)=3]/a[3]//span")
    private SelenideElement clickDelOnRegCards;

    /*
     * iФрейм
     */
    @FindBy(id = "flow")
    private SelenideElement Frem;

    /**
     * Уходим во фрейм - Регистрационные карточки документов
     */
    public GridDocRegisterCardsPage gotoFremRegisterCardsPage() {
        getWebDriver().switchTo().frame(Frem);
        return this;
    }

    /**
     * Ожидание созданного объекта в гриде DocRegisterCards
     *
     * @return GridDocRegisterCardsPage
     */
    public GridDocRegisterCardsPage verifyDocRegisterCards(
            String ObjectDocRegisterCards) {
        $(By.xpath("//div[@class='x-grid-item-container']//span[contains(text(),'"
                + ObjectDocRegisterCards + "')]")).shouldBe(visible);
        return this;
    }


    /**
     * Добавить объект - Регистрационная карточка документа
     */
    @Override
    public void addDocRegisterCards() {
        ensurePageLoaded();
        $(clickAddOnRegCards).shouldBe(present).click();
    }

    /**
     * Инициализация формы редактирования - Типы таблиц
     */
    public FormDocRegisterCardsEditPage goToDocRegisterCardsEditPage(){
        return page(FormDocRegisterCardsEditPage.class);
    }

    /**
     * Редактировать объект - РКД
     *
     * @param directories
     */
    @Override
    public void editDocRegisterCards(DocRegisterCards directories) {

    }

    /**
     * Удалить объект - РКД
     *
     * @param directories
     */
    @Override
    public void removeAnDocRegisterCards(DocRegisterCards directories) {

    }


    /**
     * Ожидание появления элемента(\ов) в гриде - Регистрационные карточки
     * документов: Кнопка Добавить РКД И отображение элемента - Регистрационная
     * карточка документа
     */
    public GridDocRegisterCardsPage ensurePageLoaded() {
        $(By.xpath("//div[count(a)=3]/a[1]//span")).shouldBe(visible);
        $(By.xpath("//div[count(*)=3]/span")).shouldBe(visible);
        return this;
    }

}
