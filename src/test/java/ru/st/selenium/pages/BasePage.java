package ru.st.selenium.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;


import static com.codeborne.selenide.Selenide.*;

public abstract class BasePage {

    public static final String WEB_PAGE_URL = "http://motiw";
    public static final String PDA_PAGE_URL = "http://pda.motiw";

    //==================================================================================== Фреймы

    /**
     * Уходим в ТОП фрейм для дальнейшего взаимодействия с Внутренней страницей (InternalPage)
     */
    public WebDriver getFrameTop() {
        return switchTo().defaultContent();
    }

    /**
     * Основной фрейм
     *
     * @return возвращаем основной фрейм
     */
    public WebDriver getFrameFlow() {
        return switchTo().frame($(By.id("flow")));
    }

    /**
     * Проверяем отображения текста в диалоге (alert) и взаиможействуем с объектом, если Сообщение истенно - взаимодействуем
     * -подтверждаем удаление, отменяем удаление, подтверждаем сохранение
     *
     * @param element             getText() из диалогового окна, собственно, сообщение к-е показывается
     * @param expectedMessageText проверяемое сообщение
     * @param webElementButton    подтверждение (взаимодействие над объектом)
     */
    public static String checkingMessagesConfirmationOfTheObject(SelenideElement element, String expectedMessageText, SelenideElement webElementButton) {
        String actualMessageText = element.shouldBe(Condition.exactText(expectedMessageText)).getText();
        if (expectedMessageText != null && expectedMessageText.equals(actualMessageText)) {
            webElementButton.click();
            return expectedMessageText;
        }
        return null;
    }

    /**
     * Проверяем кол-во вкладок в форме редактирования объекта и их имена (отображение)
     *
     * @param tabsLocator     locator element tabs
     * @param numberOfTabs    передаваемое кол-во вкладок в форме редактирования объекта
     * @param tabsNameLocator locator element name tabs
     * @param tabNames        передаваемое имя вкладок для проверки отображение в форме
     */
    public static void checkDisplayedTabsInTheShapeOfAnObject(By tabsLocator, int numberOfTabs, By tabsNameLocator, String[] tabNames) {
        $$(tabsLocator).shouldBe(CollectionCondition.size(numberOfTabs)); // проверка отображения вкладок в форме редактирования Типа таблицы
        $$(tabsNameLocator).shouldHave(CollectionCondition.exactTexts(tabNames));
    }

    /**
     * Метод обращается к ensurePageLoaded и возвращает булевское значение,
     * (false - не дождались загрузки стр.; true - дождались) ждет загрузки
     * страницы
     */
    public boolean isPageLoaded() {
        try {
            ensurePageLoaded();
            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }

    /**
     * Метод - проверяет, где мы находимся здесь и сейчас, возвращает данную
     * страницу И ждет загрузки страницы
     */
    public BasePage ensurePageLoaded() {
        return this;
    }


}
