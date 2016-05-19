package ru.motiw.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Checks elements
 */
public abstract class ChecksUtil {

    /**
     * Метод проверки наличия элемента на странице
     *
     * @param locator передаваемый локатор элемента для представления
     */
    public static boolean isElementPresent(By locator) {
        try {
            sleep(800);
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
    public static boolean isElementVisible(By locator) throws InterruptedException {
        boolean value = false;
        sleep(800);
        if (getWebDriver().findElements(locator).size() > 0) {
            value = true;
        }
        return value;
    }


}
