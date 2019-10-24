package com.mycorp;

import com.mycorp.impl.BrowserManagerChrome;
import com.mycorp.impl.BrowserManagerNone;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.VoidDriverManager;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Map;

import static com.mycorp.BrowserManagerEnum.CHROME;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;


/**
 * Unit test for simple BrowserManagerEnumTest.
 */

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest({
        BrowserManagerFactory.class,
        BrowserDriverHelper.class,
})
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

        mockStatic(BrowserManagerFactory.class);
        mockStatic(BrowserDriverHelper.class);

        when(BrowserManagerFactory.manager(CHROME.name())).thenReturn(new BrowserManagerChrome());
        when(BrowserDriverHelper.selectDriver(CHROME)).thenReturn(getMockedDriver("chrome","2"));


        Assert.assertEquals(BrowserManagerChrome.class.isInstance(of), Boolean.TRUE);
        Assert.assertTrue(of.getBrowserManager() instanceof ChromeDriverManager);
        Assert.assertTrue(of.getBrowserManager("1") instanceof ChromeDriverManager);
        Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
    }

    private WebDriver getMockedDriver(String browserType, String version) {
        final DesiredCapabilities dc = new DesiredCapabilities(browserType, version, Platform.ANY);
        final RemoteWebDriver mock = new RemoteWebDriver(dc) {
            /**
             * {@inheritDoc}
             *
             * @see RemoteWebDriver#execute(String, Map)
             */
            @Override
            protected Response execute(final String driverCommand, final Map<String, ?> parameters) {
                return new Response();
            }

            /**
             * {@inheritDoc}
             *
             * @see RemoteWebDriver#startSession(Capabilities, Capabilities)
             */
            @Override
            protected void startSession(final Capabilities desiredCapabilities,
                                        final Capabilities requiredCapabilities) {
                setSessionId("mock");
            }
        };
        return mock;
    }

}
