package com.mycorp.impl;

import com.mycorp.BrowserDriverHelper;
import com.mycorp.BrowserManagerEnum;
import com.mycorp.IBrowserManager;
import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;

public class BrowserManagerFirefox implements IBrowserManager {

    public BrowserManager getBrowserManager() {
        return ChromeDriverManager.getInstance();
    }

    public BrowserManager getBrowserManager(final String version) {
        return getBrowserManager().version(version);
    }

    public WebDriver getDriver() {
        return BrowserDriverHelper.selectDriver(BrowserManagerEnum.FIREFOX);

    }
}
