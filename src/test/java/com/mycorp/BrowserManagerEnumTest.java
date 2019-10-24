package com.mycorp;

import com.mycorp.impl.BrowserManagerNone;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.VoidDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;


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



//    @Test
//    @Ignore
//    public void shouldCheckOfChrome() throws Exception {
//        BrowserManagerEnum of = BrowserManagerEnum.of("test");
//        Assert.assertEquals(BrowserManagerEnum.NONE, of);
//        of = BrowserManagerEnum.of("perry");
//        Assert.assertEquals(BrowserManagerEnum.NONE, of);
//        of = BrowserManagerEnum.of("chrome");
//        Assert.assertEquals(BrowserManagerEnum.CHROME, of);
//        Assert.assertTrue(of.getBrowserManager() instanceof ChromeDriverManager);
//        Assert.assertTrue(of.getBrowserManager("1") instanceof ChromeDriverManager);
//        Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
//    }

}
