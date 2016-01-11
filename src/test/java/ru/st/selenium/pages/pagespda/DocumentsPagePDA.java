package ru.st.selenium.pages.pagespda;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.st.selenium.pages.Page;

/*
 * Страница - Документы
 */
public class DocumentsPagePDA extends Page {

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


}
