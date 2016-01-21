package ru.st.selenium.utils;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;

import java.io.File;

/**
 * Created by Sidelnikov Mikhail on 30.07.15.
 * Util class for taking screenshots.
 */
public class ScreenShotUtil {

    /**
     * Method for taking ScreenShot. You should use {@link com.codeborne.selenide.Screenshots} class for this.
     */
    public static void takeScreenShot() {
        // You could to something like this and save file as you want :
        File screenShotFile = Screenshots.takeScreenShotAsFile();
        System.out.println("ScreenShot method called");

    }
}
