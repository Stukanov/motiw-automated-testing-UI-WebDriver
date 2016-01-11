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

/**
 * Абстрактное представление класса страницы в пользовательском интерфейсе
 */
public abstract class Page {

    public static final String PDA_PAGE_URL = "http://pda.johni/";
    public static final String WEB_PAGE_URL = "http://johni/";

    /**
     * Уходим в ТОП фрейм для дальнейшего взаимодействия с Внутренней страницей (InternalPage)
     */
    public Page goToTopFrem() {
        getWebDriver().switchTo().defaultContent();
        return this;
    }

    /**
     * Проверяем отображения текста в диалоге (alert) и взаиможействуем с объектом, если Сообщение истенно - взаимодействуем
     * -подтверждаем удаление, отменяем удаление, подтверждаем сохранение
     *
     * @param element             getText() из диалогового окна, собственно, сообщение к-е показывается
     * @param webElementButton    подтверждение (взаимодействие над объектом)
     * @param expectedMessageText проверяемое сообщение
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
     * Пользователяская API для эмуляции сложных пользовательских действий
     * (эмуляция клавиатуры и мыши)
     */
    Actions action = new Actions(getWebDriver());

    /**
     * Метод имитирующий нажатие клавиши - Enter
     */
    public void pressEnter() {
        action = action.sendKeys(Keys.chord(Keys.ENTER));
        action.build().perform();
    }

    /**
     * Метод клавиатурного выбора настроек, смещение на ОДНУ позицию вниз,
     * например, Скрывать...; Изменяемое при редактировании и etc., полей значение полей, выбирает значение == Да
     */
    public void selectingSecondAdjustmentPosition() {
        action = action.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
        action.build().perform();
    }

    /**
     * Метод клавиатурного выбора настроек, смещение на ДВЕ позиции вниз,
     */
    public void selectingThirdAdjustmentPosition() {
        action = action.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));
        action.build().perform();
    }

    /**
     * Метод клавиатурного выбора настроек, смещение на ТРИ позиции вниз,
     */
    public void selectingFourthlyAdjustmentPosition() {
        action = action.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER));
        action.build().perform();
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


    //-----------------------------------------------------------------------------Переключение между - WINDOWS

    /**
     * Метод появление новго окна
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

        /* пример,
         driver.switchTo().window(new WebDriverWait(driver, 10).until(newWindowForm(By.cssSelector("#searchField"))));
         */

    }

    //-----------------------------------------------Waiting--------------------------------------------

    /**
     * Подождать в течение определенного количества времени
     *
     * @param seconds timeout in seconds for wait
     */
    public void waitMillisecond(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * Пример, использования метода - waitTime(0.5 OR 1);
         */
    }

    /**
     * Подождать пока отобразится элемент на странице
     *
     * @param locator
     * @param maxSeconds
     */
    public WebElement waitForPageUntilElementIsVisible(By locator,
                                                       int maxSeconds) {
        return (new WebDriverWait(getWebDriver(), maxSeconds)).until(ExpectedConditions
                .visibilityOfElementLocated(locator));
        /**
         * Пример использования метода
         * - waitForPageUntilElementIsVisible(By.xpath("//*[@id='bAddRec-btnIconEl']"), 5000);
         */
    }

    //----------------------------------------------------Проверки------------------------------------------------

    /**
     * Метод проверки наличия элемента на странице
     *
     * @param locator
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
     */
    public boolean isElementVisible(By locator) {
        boolean value = false;
        if ($(locator).shouldBe(Condition.visible).isDisplayed())
            value = true;
        return value;
    }


}
