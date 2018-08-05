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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    private static final String PAGE_URL = "https://phorest.com/book/salons/demo-uk#/registration";

    @FindBy(xpath = "//button[text()=\"I don't have an account\"]")
    private WebElement noAccButton;

    @FindBy(css = "input[placeholder='Full Name']")
    private WebElement fullName;

    @FindBy(css = "input[placeholder='Email']")
    private WebElement email;

    @FindBy(css = "input[placeholder='Mobile Phone Number']")
    private WebElement mobile;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement password;

    @FindBy(xpath = "//button[text()=\"Sign up\"]")
    private WebElement submitButton;

    private Person person;


    public SignUpPage() {
        super(true, false);
    }

    @Override
    protected void openPage() {
        getDriver().get(PAGE_URL);
    }

    @Override
    public boolean isPageOpened() {
        return submitButton.isDisplayed();
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setFields() {
        fullName.sendKeys(person.getFullName());
        email.sendKeys(person.getEmail());
        mobile.sendKeys("35" + person.getTelephoneNumber().replace("-", ""));
        password.sendKeys(person.getPassword());
    }

    public LandingPage doSubmit() {
        setFields();
        submitButton.click();
        return new LandingPage();
    }
}
