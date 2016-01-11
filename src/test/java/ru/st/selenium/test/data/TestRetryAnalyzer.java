package ru.st.selenium.test.data;

import org.testng.ITestResult;
import ru.st.selenium.test.listeners.alluretestng.retrylistener.AbstractAllureRetryAnalyzer;

/**
 * TestNG-retry
 */
public class TestRetryAnalyzer extends AbstractAllureRetryAnalyzer {

    private int retryCount;
    private final int MAX_RETRY_COUNT = 2; // кол-во прогонов при падении теста


    /* Below method returns 'true' if the test method has to be retried else 'false'
    * and it takes the 'Result' as parameter of the test method that just ran
    */
    @Override
    public boolean retry(ITestResult result, boolean getRetryAbilityOnly) {
        if (retryCount < MAX_RETRY_COUNT) {
            System.out.println("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
            if (!getRetryAbilityOnly)
                retryCount++;
            return true;
        }

        return false;
    }

    protected String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }
}
