package ru.st.selenium.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class Page {

    public static final String WEB_PAGE_URL = "http://motiw";
    public static final String PDA_PAGE_URL = "http://pda.motiw";

    /**
     * Уходим в ТОП фрейм для дальнейшего взаимодействия с Внутренней страницей (InternalPage)
     */
    public Page goToTopFrem() {
        getWebDriver().switchTo().defaultContent();
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
     * Имитации нажатия правой кнопки мыши. Клик осуществляется в центр элемента
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
        waitMillisecond(0.3);
        element.click();
    }


    //-----------------------------------------------------------------------------Переключение между - WINDOWS

    /**
     * Метод появление новго окна - переключение, т.е. взаимодействие с данным окном
     * <p>
     * пример использования,
     * driver.switchTo().window(new WebDriverWait(driver, 10).until(newWindowForm(By.cssSelector("#searchField"))));
     *
     * @param locator element that should contain the new page
     */
    public ExpectedCondition<String> newWindowForm(final By locator) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver d) {
                String initialWindowHandle = d.getWindowHandle(); // Запоминаем в начале, в каком окне мы находились
                String found = null;
                Set<String> windowHandles = d.getWindowHandles(); // возвращает множ-во идентификаторов окон И далее проходим в цикле в каждое окно и проверяем
                // имеется ли необходимый элемент в новом окне, нет - тогда переключаемся в следующее, если совпадает, то true
                for (String handle : windowHandles) {
                    try {
                        d.switchTo().window(handle);
                        if (d.findElement((locator)).isDisplayed()) {
                            found = handle;
                            break;
                        }
                    } catch (WebDriverException e) { // игнорируем все исключения
                    }
                }
                d.switchTo().window(initialWindowHandle);
                return found;
            }
        };
    }

    //-----------------------------------------------Waiting--------------------------------------------

    /**
     * Подождать в течение определенного количества времени
     * Пример, использования метода - waitTime(0.5 OR 1);
     *
     * @param seconds timeout in seconds for wait
     */
    public static void waitMillisecond(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Подождать пока отобразится элемент на странице
     * <p>
     * Пример, использования метода - waitForPageUntilElementIsVisible(By.xpath("//*[@id='bAddRec-btnIconEl']"), 5000);
     *
     * @param locator    элемент, с к-м взаимодействуем
     * @param maxSeconds время в сек. для ожидания
     */
    public WebElement waitForPageUntilElementIsVisible(By locator,
                                                       int maxSeconds) {
        return (new WebDriverWait(getWebDriver(), maxSeconds)).until(ExpectedConditions
                .visibilityOfElementLocated(locator));
    }

    //----------------------------------------------------Проверки------------------------------------------------

    /**
     * Метод проверки наличия элемента на странице
     *
     * @param locator передаваемый локатор элемента для представления
     */
    public boolean isElementPresent(By locator) {
        try {
            waitMillisecond(0.5);
            getWebDriver().findElement(locator);
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    /**
     * Метод проверки Видимости элемента
     *
     * @param locator передаваемый локатор элемента для представления
     */
    public boolean isElementVisible(By locator) throws InterruptedException {
        boolean value = false;
        waitMillisecond(0.5);
        if (getWebDriver().findElements(locator).size() > 0) {
            value = true;
        }
        return value;
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
    public Page ensurePageLoaded() {
        return this;
    }


}
