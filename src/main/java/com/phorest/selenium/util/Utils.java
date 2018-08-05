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

package com.phorest.selenium.util;


import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.payment.CreditCard;
import com.devskiller.jfairy.producer.person.Person;

public class Utils {

    public static void waitForSeconds(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Person randomPerson() {
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        return person;
    }

    public static CreditCard randomCardDetails(){
        Fairy fairy = Fairy.create();
        CreditCard creditCard = fairy.creditCard();
        return creditCard;
    }
}
