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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class ChromeBrowser extends BrowserAbstract {

    private static final String CHROMEDRIVER_PATH_FORMAT = "ChromeDriver/chromedriver_%s";
    private static final String CHROMEDRIVER_PATH_MAC = String.format(CHROMEDRIVER_PATH_FORMAT, "mac32/chromedriver");
    private static final String CHROMEDRIVER_PATH_LINUX = String.format(CHROMEDRIVER_PATH_FORMAT, "linux64/chromedriver");
    private static final String CHROMEDRIVER_PATH_WINDOWS = String.format(CHROMEDRIVER_PATH_FORMAT, "win32/chromedriver.exe");

    private ChromeOptions chromeOptions = new ChromeOptions();

    @Override
    public void setOptions() {
        String chromeBinaryPath = "";
        String osName = System.getProperty("os.name").toUpperCase();

        if (osName.contains("WINDOWS")) {
            chromeBinaryPath = CHROMEDRIVER_PATH_WINDOWS;
        } else if (osName.contains("MAC")) {
            chromeBinaryPath = CHROMEDRIVER_PATH_MAC;
        } else if (osName.contains("LINUX")) {
            chromeBinaryPath = CHROMEDRIVER_PATH_LINUX;
        }

        File chromedriver = new File(ClassLoader.getSystemResource(chromeBinaryPath).getPath());

        // set application user permissions to 455
        chromedriver.setExecutable(true);

        System.setProperty("webdriver.chrome.driver", chromedriver.getPath());

        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-notifications");
        chromeOptions.addArguments("process-per-site");
        chromeOptions.addArguments("dns-prefetch-disable");
    }

    @Override
    public WebDriver create() {
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return new ChromeDriver(caps);
    }

}
