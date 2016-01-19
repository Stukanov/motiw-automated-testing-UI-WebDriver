package ru.st.selenium.tests.listeners.alluretestng.retrylistener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public interface IAllureRetryAnalyzer extends IRetryAnalyzer {

    boolean retry(ITestResult result, boolean getRetryAbilityOnly);

}
