package ru.st.selenium.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Взаимодействие с элементами на стр.
 */
public abstract class ElementUtil {

    //------------------------------------------------------------------------------------Эмитация клавиш

    /**
     * Пользователяская API для эмуляции сложных пользовательских действий
     * (эмуляция клавиатуры и мыши)
     */

    private static Actions actions = new Actions(getWebDriver());

    /**
     * Метод клавиатурного выбора настроек, смещение на ОДНУ позицию вниз,
     * например, Скрывать...; Изменяемое при редактировании и etc., полей значение полей, выбирает значение == Да
     */
    public static void selectingSecondAdjustmentPosition() {
        actions = actions.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
        actions.build().perform();
    }

    /**
     * Метод клавиатурного выбора настроек, смещение на ДВЕ позиции вниз,
     */
    public static void selectingThirdAdjustmentPosition() {
        actions = actions.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));
        actions.build().perform();
    }

    /**
     * Метод клавиатурного выбора настроек, смещение на ТРИ позиции вниз,
     */
    public static void selectingFourthlyAdjustmentPosition() {
        actions = actions.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));
        actions.build().perform();
    }

    //------------------------------------------------------------------------------------Взаимодействие с элементом на стр.

    /**
     * Имитации нажатия правой кнопки мыши. Клик осуществляется в центр элемента и
     * ожидание появляющегося элемента
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

    /**
     * Attempts to click on an element multiple times (to avoid stale element
     * exceptions caused by rapid DOM refreshes)
     *
     * @param d  The WebDriver
     * @param by By element locator
     */
    public static void dependableClick(WebDriver d, By by) {
        final int MAXIMUM_WAIT_TIME = 10;
        final int MAX_STALE_ELEMENT_RETRIES = 5;

        WebDriverWait wait = new WebDriverWait(d, MAXIMUM_WAIT_TIME);
        int retries = 0;
        while (true) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(by)).click();

                return;
            } catch (StaleElementReferenceException e) {
                if (retries < MAX_STALE_ELEMENT_RETRIES) {
                    retries++;
                    continue;
                } else {
                    throw e;
                }
            }
        }
    }

    /**
     * Скроллинг к элементу вниз и выбор (сlick) данного элемента из списка
     * <p/>
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


}
