package ru.st.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Waiting
 */
public class WaitUtil {

    /**
     * Подождать пока отобразится элемент на странице
     * <p>
     * Пример, использования метода - waitForPageUntilElementIsVisible(By.xpath("//*[@id='bAddRec-btnIconEl']"), 5000);
     *
     * @param locator    элемент, с к-м взаимодействуем
     * @param maxSeconds время в сек. для ожидания
     */
    public static WebElement waitForPageUntilElementIsVisible(By locator,
                                                              int maxSeconds) {
        return (new WebDriverWait(getWebDriver(), maxSeconds)).until(ExpectedConditions
                .visibilityOfElementLocated(locator));
    }


}
