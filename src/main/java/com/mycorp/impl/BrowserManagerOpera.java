package com.mycorp.impl;

import com.mycorp.BrowserDriverHelper;
import com.mycorp.BrowserManagerEnum;
import com.mycorp.IBrowserManager;
import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.OperaDriverManager;
import org.openqa.selenium.WebDriver;

public class BrowserManagerOpera implements IBrowserManager {

    public BrowserManager getBrowserManager() {
        return OperaDriverManager.getInstance();
    }

    public BrowserManager getBrowserManager(final String version) {
        return getBrowserManager().version(version);
    }

    public WebDriver getDriver() {
        return BrowserDriverHelper.selectDriver(BrowserManagerEnum.OPERA);
    }
}
