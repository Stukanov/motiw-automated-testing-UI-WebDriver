package ru.st.selenium.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class BasePage {

    public static final String WEB_PAGE_URL = "http://motiw/";
    public static final String PDA_PAGE_URL = "http://pda.motiw";

    /**
     * Уходим в ТОП фрейм для дальнейшего взаимодействия с Внутренней страницей (InternalPage)
     */
    public BasePage goToTopFrem() {
        switchTo().defaultContent();
        return this;
    }

    //-----------------------------------------------------------------------------------Alert

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


    //------------------------------------------------------------------------------------Нажатие на элемент

    /**
     * Имитации нажатия правой кнопки мыши. Клик осуществляется в центр элемента и ожидание элемента
     *
     * @param element               передаваемая переменная для взаимодействия
     * @param elementWaitVisibility передаваемая переменная (элемент DOM) для взаимодействия, ожидание
     *                              появления данного элемента
     */
    public void contextClickAction(SelenideElement element, WebElement elementWaitVisibility) {
        actions.contextClick(element).perform();
        (new WebDriverWait(getWebDriver(), 10))
                .until(ExpectedConditions
                        .visibilityOfElementLocated((By) elementWaitVisibility));
    }

    /**
     * Кликнуть по невидимому элементу можно с помощью javascript
     *
     * @param element переменная для взаимодействия
     */
    public void clickOnInvisibleElement(SelenideElement element) {

        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);";

        ((JavascriptExecutor) getWebDriver()).executeScript(script, element);
    }

    //------------------------------------------------------------------------------------Эмитация клавиш

    /**
     * Пользователяская API для эмуляции сложных пользовательских действий
     * (эмуляция клавиатуры и мыши)
     */
    Actions actions = new Actions(getWebDriver());

    /**
     * Метод клавиатурного выбора настроек, смещение на ОДНУ позицию вниз,
     * например, Скрывать...; Изменяемое при редактировании и etc., полей значение полей, выбирает значение == Да
     */
    public void selectingSecondAdjustmentPosition() {
        actions = actions.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
        actions.build().perform();
    }

    /**
     * Метод клавиатурного выбора настроек, смещение на ДВЕ позиции вниз,
     */
    public void selectingThirdAdjustmentPosition() {
        actions = actions.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));
        actions.build().perform();
    }

    /**
     * Метод клавиатурного выбора настроек, смещение на ТРИ позиции вниз,
     */
    public void selectingFourthlyAdjustmentPosition() {
        actions = actions.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));
        actions.build().perform();
    }

    /**
     * Глобальная переменная - Сочетание клавиш Shift и Enter -
     * если послать это сочетание веб элементу открывающему новую страницу -
     * страница откроется в новом окне
     */
    public static String NewWindowOpen = Keys.chord(Keys.SHIFT, Keys.ENTER);

    /**
     * Открыть url в новом окне
     *
     * @param url - url страницы
     *            Пример - WebElement link = driver.findElement(By.tagName("a"));
     *            openInNewWindow(link.getAttribute("href"));
     */
    public static void openInNewWindow(String url) {
        executeJavaScript("window.open(arguments[0])", url);
    }

    /**
     * The code below will open the link in new Tab
     * <p>
     * пример - driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpenInNewTab);
     */
    public static String selectLinkOpenInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

    //--------------------------------------------------------------------------------Скроллинг

    /**
     * Скроллинг к элементу вниз и выбор (сlick) данного элемента из списка
     * <p>
     * пример - scrollToAndClick()
     *
     * @param locator элемент к к-му необходимо проскроллировать список
     */
    public static void scrollToAndClick(String locator) {
        SelenideElement element = $(By.xpath(locator));
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView();"
                , element);
        sleep(300);
        element.click();
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
