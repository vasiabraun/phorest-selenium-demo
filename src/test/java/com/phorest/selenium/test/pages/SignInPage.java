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

import com.devskiller.jfairy.producer.person.Person;
import com.phorest.selenium.test.BasePage;
import com.phorest.selenium.util.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {

    private static final String PAGE_URL = "https://phorest.com/book/salons/demo-uk#/sign-in";

    @FindBy(css = "input[placeholder='Phone or email']")
    private WebElement phoneOrEmail;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement password;

    @FindBy(xpath = "//button[text()=\"Login\"]")
    private WebElement login;

    private Person person;


    public SignInPage() {
        super(true, false);
    }

    @Override
    protected void openPage() {
        getDriver().get(PAGE_URL);
    }

    @Override
    public boolean isPageOpened() {
        return login.isDisplayed();
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setFields() {
        phoneOrEmail.sendKeys(person.getEmail());
        password.sendKeys(person.getPassword());
    }

    public LandingPage doLogin() {
        setFields();
        login.click();
        Utils.waitForSeconds(3);
        return new LandingPage();
    }
}
