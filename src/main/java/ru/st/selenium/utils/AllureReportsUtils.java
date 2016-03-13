package ru.st.selenium.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

/**
 * Статик методы для AllureReports
 */
public class AllureReportsUtils {

    /**
     * Для вставки скриншота в AllureReports
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] makeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Для вставки лога прохождения тестов в отчет AllureReports
     *
     * @param text непосредстванно передаваемый лог
     * @return возвращает переданный лог (текст)
     */
    @Attachment(value = "Test log")
    public static String attachText(String text) {
        return text;
    }


}
