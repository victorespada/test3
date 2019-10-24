package com.mycorp.impl;

import com.mycorp.BrowserDriverHelper;
import com.mycorp.BrowserManagerEnum;
import com.mycorp.IBrowserManager;
import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;

public class BrowserManagerIE implements IBrowserManager {

    public BrowserManager getBrowserManager() {
        return InternetExplorerDriverManager.getInstance();
    }

    public BrowserManager getBrowserManager(final String version) {
        return getBrowserManager().version(version);
    }

    public WebDriver getDriver() {
        return BrowserDriverHelper.getDriver(BrowserManagerEnum.IE);

    }
}
