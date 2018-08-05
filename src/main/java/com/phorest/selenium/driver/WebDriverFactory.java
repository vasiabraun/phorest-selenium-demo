/*
 * Copyright (c) 2018  [Vasylyna Mosiievych]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package com.phorest.selenium.driver;

import com.phorest.selenium.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Base class for web tests. It contains web driver {@link org.openqa.selenium.WebDriver} instance, used in all tests.
 * All communications with driver should be done through this class
 */
public class WebDriverFactory {

    private static final long IMPLICIT_WAIT_TIMEOUT = 10;
    private static WebDriver driver;

    /**
     * Getting of pre-configured {@link org.openqa.selenium.WebDriver} instance.
     * Please use this method only after call {@link #startBrowser() startBrowser} method
     *
     * @return webdriver object, or throw IllegalStateException, if driver has not been initialized
     */
    public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        } else {
            throw new IllegalStateException("Driver has not been initialized. " +
                    "Please call WebDriverFactory.startBrowser() before use this method");
        }
    }

    /**
     * Getting of pre-configured {@link org.openqa.selenium.support.ui.WebDriverWait} instance.
     * Please use this method only after call {@link #startBrowser() startBrowser} method
     *
     * @return webdriver object, or throw IllegalStateException, if driver has not been initialized
     */
    public static WebDriverWait getWaitingDriver(long timeOutWaiting) {
        if (driver != null) {
            return new WebDriverWait(driver, timeOutWaiting);
        } else {
            throw new IllegalStateException("Driver has not been initialized. " +
                    "Please call WebDriverFactory.startBrowser() before use this method");
        }
    }

    /**
     * Main method of class - it initialize driver and starts browser.
     *
     */
    public static void startBrowser() {
        if (driver == null) {
            final Browser browser = Configuration.getConfig().getBrowser();
            try {
                driver = browser.getBrowserClass().newInstance().getInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException("Unsupported browser type");
            }
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        } else {
            throw new IllegalStateException("Driver has already been initialized. Quit it before using this method");
        }
    }

    /**
     * Finishes browser
     */
    public static void finishBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Method for screenshot taking. It is empty now, because you could save your screenshot as you want.
     * This method calls in tests listeners on test fail
     */
    public static void takeScreenShot() {
        System.out.println("ScreenShot method called");
    }

}
