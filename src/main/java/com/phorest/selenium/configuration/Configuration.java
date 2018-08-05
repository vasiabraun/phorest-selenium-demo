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

package com.phorest.selenium.configuration;

import com.phorest.selenium.configuration.properties.PropertiesLoader;
import com.phorest.selenium.configuration.properties.Property;
import com.phorest.selenium.configuration.properties.PropertyFile;
import com.phorest.selenium.driver.Browser;

/**
 * Class for base tests properties - urls for test, browser name and version
 */
@PropertyFile("config.properties")
public class Configuration {

    private static Configuration config;

    public static Configuration getConfig() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

    public Configuration() {
        PropertiesLoader.populate(this);
    }

    @Property("browser.name")
    private String browser = "";

    @Property("browser.version")
    private String version = "";

    public Browser getBrowser() {
        final Browser browserForTests = Browser.lookup(browser);
        if (browserForTests != null) {
            return browserForTests;
        } else {
            throw new IllegalStateException("Browser name '" + browser + "' is not valid");
        }
    }

}
