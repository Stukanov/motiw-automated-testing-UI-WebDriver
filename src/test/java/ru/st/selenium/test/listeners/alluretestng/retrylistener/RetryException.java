package ru.st.selenium.test.listeners.alluretestng.retrylistener;


import org.testng.SkipException;

public class RetryException extends SkipException {


    public RetryException(String message, Throwable cause) {
        super(message, cause);
        reduceStackTrace();
    }
}
