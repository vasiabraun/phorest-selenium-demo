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
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.NoSuchElementException;

public class LandingPage extends BasePage {

    @FindBy(xpath = "//a[text()=\"My Booking History\"]")
    private WebElement bookingHistory;

    @FindBy(xpath = "//div[text()=\"Thanks for signing up\"]")
    private WebElement signingUpText;

    @FindBy(xpath = "//div[text()=\"You're now signed in\"]")
    private WebElement signingInText;

    @FindBys(@FindBy(css = "div[class='list-group']"))
    private List<WebElement> services;

    @FindBy(xpath = "//a[text()=\"Cut + Style\"]")
    private WebElement cutPlusStyleService;

    public LandingPage(){
        super(true, false);
    }

    @Override
    protected void openPage() {
        //do nothing
    }

    public boolean isSignUpSuccessfully(){
        return signingUpText != null;
    }

    public boolean isSignInSuccessfully(){
        return signingInText != null;
    }

    public boolean hasCutPlusStyleService(){
        return cutPlusStyleService != null;
    }

    public CutAndStylePage doSelectCutAndStyle(){
        cutPlusStyleService.click();
        return new CutAndStylePage();
    }

    @Override
    public boolean isPageOpened() {
        try {
            return !services.isEmpty();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean hasResults() {
        return !services.isEmpty();
    }

}
