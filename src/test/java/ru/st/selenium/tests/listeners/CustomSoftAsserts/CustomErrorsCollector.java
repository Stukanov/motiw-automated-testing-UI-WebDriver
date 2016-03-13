package ru.st.selenium.tests.listeners.CustomSoftAsserts;

import com.codeborne.selenide.logevents.ErrorsCollector;
import com.codeborne.selenide.logevents.LogEvent;

import java.util.ArrayList;
import java.util.List;

import static ru.st.selenium.utils.AllureReportsUtils.makeScreenshot;


public class CustomErrorsCollector extends ErrorsCollector {
    private final List<Throwable> errors = new ArrayList();

    @Override
    public void onEvent(LogEvent event) {
        if (event.getStatus() == LogEvent.EventStatus.FAIL) {
            this.errors.add(event.getError());
            makeScreenshot();
        }
    }
}
