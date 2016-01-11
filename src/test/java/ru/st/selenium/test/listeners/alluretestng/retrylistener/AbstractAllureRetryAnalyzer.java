package ru.st.selenium.test.listeners.alluretestng.retrylistener;

import org.testng.ITestResult;

public abstract class AbstractAllureRetryAnalyzer implements IAllureRetryAnalyzer {

    public boolean retry(ITestResult result) {
        return retry(result, false);
    }
}
