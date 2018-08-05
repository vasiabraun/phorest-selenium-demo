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

import java.util.List;
import java.util.NoSuchElementException;

public class BookDialogPage extends BasePage {

    @FindBy(xpath = "//div[@class='modal-dialog']")
    private WebElement dialog;

    @FindBy(css = "button + button")
    private List<WebElement> bookNow;

    public BookDialogPage(){
        super(true, false);
    }

    @Override
    protected void openPage() {
        //do nothing
    }

    public ChooseTimePage doBookNow(){
        getDisplayed().click();
        return new ChooseTimePage();
    }

    @Override
    public boolean isPageOpened() {
        try {
            WebElement element = getDisplayed();
            return element != null && element.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    private WebElement getDisplayed(){
        for(WebElement element: bookNow){
            if(element.isDisplayed())
                return element;
        }
        return null;
    }

}
