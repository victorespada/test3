package com.mycorp;

import com.mycorp.impl.BrowserManagerChrome;
import com.mycorp.impl.BrowserManagerNone;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.VoidDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.mycorp.BrowserManagerEnum.CHROME;


/**
 * Unit test for simple BrowserManagerEnumTest.
 */
@Slf4j
public class BrowserManagerEnumTest {

    @Test
    public void shouldCheckOf() throws Exception {
        IBrowserManager of = BrowserManagerFactory.manager("test");
        Assert.assertEquals(of instanceof BrowserManagerNone, Boolean.TRUE);
        of = BrowserManagerFactory.manager("perry");
        Assert.assertEquals(of instanceof BrowserManagerNone, Boolean.TRUE);
        of = BrowserManagerFactory.manager(null);
        Assert.assertEquals(of instanceof BrowserManagerNone, Boolean.TRUE);
        Assert.assertTrue(of.getBrowserManager() instanceof VoidDriverManager);
        Assert.assertTrue(of.getBrowserManager("1") instanceof VoidDriverManager);
        Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
    }



    @Test
    public void shouldCheckOfChrome() throws Exception {
        IBrowserManager of = BrowserManagerFactory.manager(CHROME.name());

        Assert.assertEquals(BrowserManagerChrome.class.isInstance(of), Boolean.TRUE);
        Assert.assertTrue(of.getBrowserManager() instanceof ChromeDriverManager);
        Assert.assertTrue(of.getBrowserManager("1") instanceof ChromeDriverManager);
        Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
    }

}
