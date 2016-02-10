package ru.st.selenium.pages.pagespda;


import org.openqa.selenium.By;
import ru.st.selenium.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

/*
 * Страница - Сегодня
 */
public class TodayPagePDA extends BasePage {


    /**
     * Проверяем отображения изменений в разделе Сегодня
     *
     * @param textAction input text for feed action tasks
     * @return
     */
    public TodayPagePDA verifyInformationDisplaySectionToday(String textAction) {
        $(By.xpath("//p[contains(text(),'" + textAction + "')]")).shouldBe(visible);
        return this;
    }


}
