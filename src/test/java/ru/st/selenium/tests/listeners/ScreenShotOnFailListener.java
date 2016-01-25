package ru.st.selenium.tests.listeners;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.st.selenium.utils.ScreenShotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

/**
 * Created by Sidelnikov Mikhail on 14.07.15.
 * This listener adds screenshot taking on test failure
 */
public class ScreenShotOnFailListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        /**
         * Автоматически делать скриншот, после каждого упавшего теста
         * Чтобы делать скриншоты после зелёных тестов, нужно вызвать такую команду перед запуском тестов: java ScreenShooter.captureSuccessfulTests = true;
         * Вы также можете сделать скриншот в любом месте теста одной строчкой - screenshot("my_file_name");
         * При этом Selenide создаст два файла: my_file_name.png и my_file_name.html
         */
        ScreenShotUtil.takeScreenShot();
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
    public void onFinish(ITestContext iTestContext) {

    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
