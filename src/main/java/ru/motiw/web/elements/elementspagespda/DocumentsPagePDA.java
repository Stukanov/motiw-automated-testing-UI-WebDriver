package ru.motiw.web.elements.elementspagespda;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.elements.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/*
 * Страница - Документы
 */
public class DocumentsPagePDA extends BasePage {

    /*
     * На рассмотрении
     */
    @FindBy(xpath = "//li[@class='ui-block-a']//a")
    private SelenideElement onReview;

    /*
     * На исполнении
     */
    @FindBy(xpath = "//li[@class='ui-block-b']//a")
    private SelenideElement toExecution;

    /*
     * На контроле
     */
    @FindBy(xpath = "//li[@class='ui-block-c']//a")
    private SelenideElement controlled;

    /**
     * Проверяем отображение гридов документа
     *
     * @return results instance page grid documents
     */
    public DocumentsPagePDA checkMapGridsDocuments() {
        onReview.click();
        toExecution.click();
        controlled.click();
        return this;
    }

    /**
     * Проверяем отображение созданного документа в гриде
     *
     * @param docRegisterCards тип документа (РКД)
     * @param document         атрибуты зн-ия поля документа
     */
    public DocumentsPagePDA checkTheDisplayOfTheDocumentGrid(DocRegisterCards docRegisterCards, Document document) {
        controlled.click();
        $(By.xpath("//*[@id='mainblock']//tbody//td[3]//text[text()='" + docRegisterCards.getDocRegisterCardsName() + "']"))
                .shouldBe(Condition.visible);
        $(By.xpath("//*[@id='mainblock']/table/tbody//td[4][text()='" + document.getDateRegistration() + "']"))
                .shouldBe(Condition.visible);
        return this;
    }


}
