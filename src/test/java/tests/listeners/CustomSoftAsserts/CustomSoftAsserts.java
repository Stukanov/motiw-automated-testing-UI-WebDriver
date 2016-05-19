package tests.listeners.CustomSoftAsserts;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.SoftAsserts;

/**
 * Переделанный @Listeners({ CustomSoftAsserts.class}), к-й используется для верификации без падения тестов
 * - добавлена возможность снятия скриншота каждого FAIL теста
 */
public class CustomSoftAsserts extends SoftAsserts {
    private final CustomErrorsCollector errorsCollector = new CustomErrorsCollector();

    public CustomSoftAsserts() {
        SelenideLogger.addListener(this.errorsCollector);
    }
}
