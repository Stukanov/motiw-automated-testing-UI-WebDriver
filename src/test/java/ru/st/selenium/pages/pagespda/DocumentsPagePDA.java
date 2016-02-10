package ru.st.selenium.pages.pagespda;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.st.selenium.pages.BasePage;

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
     * @param docRegisterCards
     *
     * TODO добавить проверку на Название документа и Дату регистрации
     */
    public DocumentsPagePDA checkTheDisplayOfTheDocumentGrid(DocRegisterCards docRegisterCards) {
        controlled.click();
        $(By.xpath("//*[@id='mainblock']//tbody//td[3]//text[text()='" + docRegisterCards.getDocRegisterCardsName() + "']"))
                .shouldBe(Condition.visible);
        return this;
    }


}
