package ru.motiw.web.elements.elementsweb.Administration;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import ru.motiw.web.logicinterface.WebLogic.SystemInformationLogic;
import ru.motiw.web.elements.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertFalse;
import static ru.motiw.utils.ChecksUtil.isElementPresent;

/**
 * Информация о системе
 */
public class SystemInformationPage extends BasePage implements SystemInformationLogic {


    /**
     * Проверка отсутствия красных значений
     */
    public SystemInformationPage assertNotRedElement() {
        assertFalse(isElementPresent(By
                .xpath("/*//*[contains (@style, '#F83838')]")));
        return this;

    }

    /**
     * Проверка Загрузки страницы - ожидание наличия кнопки чейнджллога
     */
    public SystemInformationPage ensurePageLoaded() {
        $(By.xpath("//input[@type='button' and @value='ChangeLog']")).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Проверяем отсутствия красных элеметов (Работоспособность служб)
     */
    @Override
    public boolean checkingOfSystemServices() {
        ensurePageLoaded();
        try {
            assertNotRedElement();
            return true;
        } catch (TimeoutException to) {
            return false;

        }
    }
}