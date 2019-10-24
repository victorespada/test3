package com.mycorp.impl;

import com.mycorp.BrowserDriverHelper;
import com.mycorp.BrowserManagerEnum;
import com.mycorp.IBrowserManager;
import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import org.openqa.selenium.WebDriver;

public class BrowserManagerEdge implements IBrowserManager {

    public BrowserManager getBrowserManager() {
        return EdgeDriverManager.getInstance();
    }

    public BrowserManager getBrowserManager(final String version) {
        return getBrowserManager().version(version);
    }

    public WebDriver getDriver() {
        return BrowserDriverHelper.selectDriver(BrowserManagerEnum.EDGE);

    }
}
