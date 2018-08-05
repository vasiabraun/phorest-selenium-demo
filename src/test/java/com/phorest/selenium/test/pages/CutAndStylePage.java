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
import com.phorest.selenium.util.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class CutAndStylePage extends BasePage {

    @FindBy(xpath = "//a[@class='btn btn-success']")
    private List<WebElement> subServices;

    public CutAndStylePage() {
        super(true, false);
    }

    @Override
    protected void openPage() {
    }

    public BookDialogPage doSubService() {
        subServices.get(0).click();
        Utils.waitForSeconds(2);

        return new BookDialogPage();
    }

    @Override
    public boolean isPageOpened() {
        try {
            return !subServices.isEmpty();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean hasResults() {
        return !subServices.isEmpty();
    }

}
