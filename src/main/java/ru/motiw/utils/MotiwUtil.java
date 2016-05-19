package ru.motiw.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

/**
 * Общие методы для взаимодействия с элементами стр-ц системы Мотив
 */
public abstract class MotiwUtil {


    /**
     * Развернем все ветви объекта
     */
    public static void unwrapAllNodesFolder(SelenideElement plusSubsites) {
        try {
            while (ChecksUtil.isElementPresent(By
                    .xpath("//img[contains(@class,'x-tree-ec-icon') and contains(@class,'plus')]")))
                plusSubsites.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
