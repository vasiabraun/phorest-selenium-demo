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

package com.phorest.selenium.driver;

import com.phorest.selenium.driver.browser.ChromeBrowser;
import com.phorest.selenium.driver.browser.FirefoxBrowser;
import com.phorest.selenium.driver.browser.HtmlUnitBrowser;
import com.phorest.selenium.driver.browser.IEBrowser;

public enum Browser {

    CHROME(ChromeBrowser.class, "chrome"),
    FIREFOX(FirefoxBrowser.class, "firefox"),
    HTML(HtmlUnitBrowser.class, "html"),
    IE(IEBrowser.class, "ie");

    private Class<? extends BrowserAbstract> browserClass;
    private String name;

    Browser(Class<? extends BrowserAbstract> browserClass, String name) {
        this.name = name;
        this.browserClass = browserClass;
    }

    public static Browser lookup(String browserName) {
        for (Browser name : Browser.values()) {
            if (name.getName().equalsIgnoreCase(browserName)) {
                return name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Class<? extends BrowserAbstract> getBrowserClass() {
        return browserClass;
    }
}
