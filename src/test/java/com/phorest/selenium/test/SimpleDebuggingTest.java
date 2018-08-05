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

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import com.phorest.selenium.driver.WebDriverFactory;
import com.phorest.selenium.test.listeners.ScreenShotOnFailListener;
import com.phorest.selenium.util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static org.junit.Assert.assertTrue;

/**
 * Simple test using no structure - for the testing purpose only! You should NOT write such a bad test
 */
@Listeners({ScreenShotOnFailListener.class})
public class SimpleDebuggingTest {

    @BeforeMethod
    public void beforeTest() {
        WebDriverFactory.startBrowser();
    }

    @Test
    public void testSignUp() {

        Fairy fairy = Fairy.create();
        Person person = fairy.person();

        WebDriverFactory.getDriver().get("https://phorest.com/book/salons/demo-uk#/sign-in");
        WebElement dontHaveAccButton = WebDriverFactory.getDriver().findElement(By.xpath("//button[text()=\"I don't have an account\"]"));
        dontHaveAccButton.click();

        // fill in form
        WebElement fullName = WebDriverFactory.getDriver().findElement(By.cssSelector("input[placeholder='Full Name']"));
        fullName.sendKeys(person.getFullName());


        WebElement email = WebDriverFactory.getDriver().findElement(By.cssSelector("input[placeholder='Email']"));
        email.sendKeys(person.getEmail());

        WebElement mobile = WebDriverFactory.getDriver().findElement(By.cssSelector("input[placeholder='Mobile Phone Number']"));
        mobile.sendKeys(person.getTelephoneNumber());

        WebElement password = WebDriverFactory.getDriver().findElement(By.cssSelector("input[placeholder='Password']"));
        password.sendKeys(person.getPassword());

        WebElement submitButton = WebDriverFactory.getDriver().findElement(By.xpath("//button[text()=\"Sign up\"]"));

        Utils.waitForSeconds(5);

        submitButton.click();

        Utils.waitForSeconds(5);
    }


    @AfterMethod
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }

}
