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

import com.devskiller.jfairy.producer.payment.CreditCard;
import com.phorest.selenium.test.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PayPage extends BasePage {

    private WebElement cardNumber;
    private WebElement expDate;
    private WebElement cvc;

    @FindBy(tagName = "button")
    private List<WebElement> btnSuccess;

    private CreditCard creditCard;

    public PayPage() {
        super(false, false);
    }


    @Override
    protected void openPage() {
        //do nothing
    }

    public boolean acceptIFrame(WebElement iframe) {
        return Boolean.parseBoolean(iframe.getAttribute("allowpaymentrequest"));
    }

    public void doPay() {
        findElementsIFrame();
        getPayButton().click();
    }

    public void findElementsIFrame() {
        for (WebElement element : getDriver().findElements(By.tagName("iframe"))) {
            if (acceptIFrame(element)) {
                getDriver().switchTo().frame(element);
                break;
            }
        }

        cardNumber = getDriver().findElement(By.cssSelector("input[placeholder='Card number']"));
        expDate = getDriver().findElement(By.cssSelector("input[placeholder='MM / YY']"));
        cvc = getDriver().findElement(By.cssSelector("input[placeholder='CVC']"));

        cardNumber.sendKeys(creditCard.getCardNumber());
        expDate.sendKeys(creditCard.getExpiryDateAsString());
        cvc.sendKeys(creditCard.getCvv());

        getDriver().switchTo().defaultContent();
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public WebElement getPayButton() {
        for (WebElement e : btnSuccess) {
            if (e.getText().contains("Pay"))
                return e;
        }
        return null;
    }

    @Override
    public boolean isPageOpened() {
        return getPayButton().isDisplayed();
    }

    public boolean hasFormPay() {
        return getPayButton() != null && getPayButton().isEnabled();
    }
}
