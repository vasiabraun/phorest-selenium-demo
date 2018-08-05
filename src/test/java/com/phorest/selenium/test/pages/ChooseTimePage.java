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

package com.phorest.selenium.test.pages;

import com.phorest.selenium.test.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class ChooseTimePage extends BasePage {

    @FindBy(xpath = "//a[text()=\"9:00 AM\"]")
    private WebElement time_9_00;

    public ChooseTimePage(){
        super(true, false);
    }

    @Override
    protected void openPage() {
        //do nothing
    }

    public PayPage doSelectTime_9_00(){
        time_9_00.click();

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.urlContains("/appointment-reservation"));

        return new PayPage();
    }

    @Override
    public boolean isPageOpened() {
        try {
            return time_9_00.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

}
