package ru.st.selenium.tests.listeners.CustomTextReport;

import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;

/**
 * Annotate your test class with {@code @Listeners({ CustomTextReport.class})}
 * Эквивалентный TextReport - только за одним исключением, лог еще пишется в AllureReport
 */
public class CustomTextReport extends ExitCodeListener {
    protected CustomReport report = new CustomReport();

    public CustomTextReport() {

    }

    public void onTestStart(ITestResult result) {
        this.report.start();
    }

    public void onTestFailure(ITestResult result) {
        this.report.finish(result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        this.report.finish(result.getName());
    }


    public void onTestSuccess(ITestResult result) {
        this.report.finish(result.getName());
    }

}
