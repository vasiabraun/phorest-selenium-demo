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

import com.devskiller.jfairy.producer.person.Person;
import com.phorest.selenium.driver.WebDriverFactory;
import com.phorest.selenium.test.listeners.ScreenShotOnFailListener;
import com.phorest.selenium.test.pages.*;
import com.phorest.selenium.util.Utils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

@Listeners({ScreenShotOnFailListener.class})
public class E2EScenariosDemo {

    private Person person;

    @BeforeMethod
    public void beforeTest() {
        WebDriverFactory.startBrowser();
    }

    @Test
    public void testSignUp() {
        person = Utils.randomPerson();

        SignUpPage signUpPage = new SignUpPage();
        signUpPage.setPerson(person);

        //Utils.waitForSeconds(3);
        LandingPage landingPage = signUpPage.doSubmit();

        assertTrue("No sign up success message detected", landingPage.isSignUpSuccessfully());
        //Utils.waitForSeconds(3);
    }

    @Test(dependsOnMethods = "testSignUp")
    public void testSignIn() {

        SignInPage signInPage = new SignInPage();
        signInPage.setPerson(person);

        //Utils.waitForSeconds(3);
        LandingPage landingPage = signInPage.doLogin();
        assertTrue("No services were found on results page", landingPage.isSignInSuccessfully());
    }

    @Test(dependsOnMethods = "testSignUp")
    public void testSignInAndCutStyleCheckOut() {

        SignInPage signInPage = new SignInPage();
        signInPage.setPerson(person);

        LandingPage landingPage = signInPage.doLogin();

        assertTrue("No services were found on results page", landingPage.hasResults());
        assertTrue("No cut and style service was found on results page", landingPage.hasCutPlusStyleService());

        CutAndStylePage cutAndStylePage = landingPage.doSelectCutAndStyle();
        assertTrue("No sub-services for cut and style were found on results page", cutAndStylePage.hasResults());

        BookDialogPage bookDialogPage = cutAndStylePage.doSubService();
        assertTrue("No booking dialog", bookDialogPage.isPageOpened());

        ChooseTimePage chooseTimePage = bookDialogPage.doBookNow();
        assertTrue("Can't find 9:00 AM time", chooseTimePage.isPageOpened());

        PayPage payPage = chooseTimePage.doSelectTime_9_00();
        payPage.setCreditCard(Utils.randomCardDetails());
        assertTrue("Can't find pay form", payPage.hasFormPay());

        payPage.doPay();
        //Utils.waitForSeconds(5);
    }

    @AfterMethod
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }

}
