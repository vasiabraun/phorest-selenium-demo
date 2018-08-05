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

package com.phorest.selenium.test;

import com.phorest.selenium.driver.WebDriverFactory;
import com.phorest.selenium.util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is the main class for test. When you create your page - you must extend your class from this
 */
public abstract class BasePage {
    protected static final int WAIT_FOR_PAGE_LOAD_IN_SECONDS = 5;
    /**
     * In subclasses  should be used for page opening
     */
    protected abstract void openPage();

    /**
     * checks is page opened
     * @return true if opened
     */
    public abstract boolean isPageOpened();

    public boolean acceptIFrame(WebElement iframe){
        return true;
    }

    public BasePage(boolean openPageByUrl, boolean findIFrame){
        if(openPageByUrl){
            openPage();
        }

        if(findIFrame){
            getDriver().switchTo().defaultContent();
            for(WebElement element: getDriver().findElements(By.tagName("iframe"))){
                if(acceptIFrame(element)) {
                    getDriver().switchTo().frame(element);
                    break;
                }
            }
        }

        PageFactory.initElements(getDriver(), this);
        waitForOpen();
    }

    /**
     * Waiting for page opening
     */
    protected void waitForOpen(){
        int secondsCount = 0;
        boolean isPageOpenedIndicator = isPageOpened();
        while (!isPageOpenedIndicator && secondsCount < WAIT_FOR_PAGE_LOAD_IN_SECONDS) {
            Utils.waitForSeconds(1);
            secondsCount++;
            isPageOpenedIndicator = isPageOpened();
        }
        if(!isPageOpenedIndicator) {
            throw new AssertionError("Page was not opened");
        }
    }

    /**
     * getting webdriver instance
     * @return initialized in tests webdriver instance
     */
    protected WebDriver getDriver(){
        return WebDriverFactory.getDriver();
    }

    protected WebDriverWait getDriverWait(long timeOutWaiting){
        return WebDriverFactory.getWaitingDriver(timeOutWaiting);
    }

}
