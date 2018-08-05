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

package com.phorest.selenium.driver.browser;

import com.phorest.selenium.driver.BrowserAbstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;

public class FirefoxBrowser extends BrowserAbstract {

    private FirefoxProfile firefoxProfile;

    @Override
    public void setOptions() {
        // Windows 8 requires to set webdriver.firefox.bin system variable
        // to path where executive file of FF is placed
        if ("WINDOWS 8".equalsIgnoreCase(System.getProperty("os.name"))) {
            System.setProperty("webdriver.firefox.bin", "c:" + File.separator + "Program Files (x86)"
                    + File.separator + "Mozilla Firefox" + File.separator + "Firefox.exe");
        }

        // Check if user who is running tests have write access in ~/.mozilla dir and home dir
        if ("LINUX".equalsIgnoreCase(System.getProperty("os.name"))) {
            File homePath = new File(System.getenv("HOME") + File.separator);
            File mozillaPath = new File(homePath + File.separator + ".mozilla");
            File tmpFile;
            if (mozillaPath.exists()) {
                try {
                    tmpFile = File.createTempFile("webdriver", null, mozillaPath);
                } catch (IOException ex) {
                    throw new WebDriverException(
                            "Can't create file in path: %s".replace("%s", mozillaPath.getAbsolutePath()));
                }
            } else {
                try {
                    tmpFile = File.createTempFile("webdriver", null, homePath);
                } catch (IOException ex) {
                    throw new WebDriverException(
                            "Can't create file in path: %s".replace("%s", homePath.getAbsolutePath()));
                }
            }
            tmpFile.delete();
        }

        if (ClassLoader.getSystemResource("FirefoxProfiles/Default") != null) {
            firefoxProfile = new FirefoxProfile(
                    new File(ClassLoader.getSystemResource("FirefoxProfiles/Default").getPath()));
        } else {
            firefoxProfile = new FirefoxProfile();
        }

    }

    @Override
    public WebDriver create() {
        caps.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        caps.setCapability("marionette", true);

        return new FirefoxDriver(caps);
    }

}
