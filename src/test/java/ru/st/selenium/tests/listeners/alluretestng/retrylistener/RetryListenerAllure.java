package ru.st.selenium.tests.listeners.alluretestng.retrylistener;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.TestCaseFinishedEvent;
import ru.yandex.qatools.allure.events.TestCasePendingEvent;

import java.util.Set;


public class RetryListenerAllure implements ITestListener {

    Allure lifecycle = Allure.LIFECYCLE;

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        fireRetryTest("The test has been failed then retried", result);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext context) {
        Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
        for (ITestResult temp : failedTests) {
            ITestNGMethod method = temp.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                failedTests.remove(temp);
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    failedTests.remove(temp);
                }
            }
        }
    }

    protected void fireRetryTest(String message, ITestResult result) {
        if (((IAllureRetryAnalyzer) result.getMethod().getRetryAnalyzer()).retry(result, true)) {
            Throwable throwable = new RetryException(message, result.getThrowable());
            getLifecycle().fire(new TestCasePendingEvent().withThrowable(throwable));
            getLifecycle().fire(new TestCaseFinishedEvent());
        }
    }

    protected Allure getLifecycle() {
        return lifecycle;
    }
}
