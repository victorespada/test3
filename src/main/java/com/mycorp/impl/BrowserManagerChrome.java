package com.mycorp.impl;

import com.mycorp.BrowserDriverHelper;
import com.mycorp.BrowserManagerEnum;
import com.mycorp.IBrowserManager;
import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;


//chrome ok
public class BrowserManagerChrome implements IBrowserManager {

    public BrowserManager getBrowserManager() {
        return ChromeDriverManager.getInstance().version("2.24");
    }

    public BrowserManager getBrowserManager(final String version) {
        return getBrowserManager().version(version);
    }

    public WebDriver getDriver() {
        return BrowserDriverHelper.getDriver(BrowserManagerEnum.CHROME);
    }
}
