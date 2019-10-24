package com.mycorp;

import com.mycorp.impl.*;
import io.github.bonigarcia.wdm.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Map;

import static com.mycorp.BrowserManagerEnum.*;
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
        when(BrowserDriverHelper.getDriver(CHROME)).thenReturn(getMockedDriver("chrome", "2"));

        Assert.assertEquals(of instanceof BrowserManagerChrome, Boolean.TRUE);
        Assert.assertTrue(of.getBrowserManager() instanceof ChromeDriverManager);
        Assert.assertTrue(of.getBrowserManager("1") instanceof ChromeDriverManager);
        Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
    }

    @Test
    public void shouldCheckOfEdge() throws Exception {
        IBrowserManager of = BrowserManagerFactory.manager(EDGE.name());

        mockStatic(BrowserManagerFactory.class);
        mockStatic(BrowserDriverHelper.class);

        when(BrowserManagerFactory.manager(EDGE.name())).thenReturn(new BrowserManagerEdge());
        when(BrowserDriverHelper.getDriver(EDGE)).thenReturn(getMockedDriver("edge", "2"));

        Assert.assertEquals(of instanceof BrowserManagerEdge, Boolean.TRUE);
        Assert.assertTrue(of.getBrowserManager() instanceof EdgeDriverManager);
        Assert.assertTrue(of.getBrowserManager("1") instanceof EdgeDriverManager);
        Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
    }

    @Test
    public void shouldCheckOfFirefox() throws Exception {
        IBrowserManager of = BrowserManagerFactory.manager(FIREFOX.name());

        mockStatic(BrowserManagerFactory.class);
        mockStatic(BrowserDriverHelper.class);

        when(BrowserManagerFactory.manager(FIREFOX.name())).thenReturn(new BrowserManagerFirefox());
        when(BrowserDriverHelper.getDriver(FIREFOX)).thenReturn(getMockedDriver("firefox", "2"));

        Assert.assertEquals(of instanceof BrowserManagerFirefox, Boolean.TRUE);
        Assert.assertTrue(of.getBrowserManager() instanceof FirefoxDriverManager);
        Assert.assertTrue(of.getBrowserManager("1") instanceof FirefoxDriverManager);
        Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
    }

    @Test
    public void shouldCheckOfIE() throws Exception {
        IBrowserManager of = BrowserManagerFactory.manager(IE.name());

        mockStatic(BrowserManagerFactory.class);
        mockStatic(BrowserDriverHelper.class);

        when(BrowserManagerFactory.manager(IE.name())).thenReturn(new BrowserManagerIE());
        when(BrowserDriverHelper.getDriver(IE)).thenReturn(getMockedDriver("ie", "2"));

        Assert.assertEquals(of instanceof BrowserManagerIE, Boolean.TRUE);
        Assert.assertTrue(of.getBrowserManager() instanceof InternetExplorerDriverManager);
        Assert.assertTrue(of.getBrowserManager("1") instanceof InternetExplorerDriverManager);
        Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
    }

    @Test
    public void shouldCheckOfMarionette() throws Exception {
        IBrowserManager of = BrowserManagerFactory.manager(MARIONETTE.name());

        mockStatic(BrowserManagerFactory.class);
        mockStatic(BrowserDriverHelper.class);

        when(BrowserManagerFactory.manager(MARIONETTE.name())).thenReturn(new BrowserManagerFirefox());
        //Internamente, marionette llama al driver de firefox, debemos simular la llamada con FIREFOX
        when(BrowserDriverHelper.getDriver(FIREFOX)).thenReturn(getMockedDriver("firefox", "2"));

        Assert.assertEquals(of instanceof BrowserManagerFirefox, Boolean.TRUE);
        Assert.assertTrue(of.getBrowserManager() instanceof FirefoxDriverManager);
        Assert.assertTrue(of.getBrowserManager("1") instanceof FirefoxDriverManager);
        Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
    }

    @Test
    public void shouldCheckOfOpera() throws Exception {
        IBrowserManager of = BrowserManagerFactory.manager(OPERA.name());

        mockStatic(BrowserManagerFactory.class);
        mockStatic(BrowserDriverHelper.class);

        when(BrowserManagerFactory.manager(OPERA.name())).thenReturn(new BrowserManagerOpera());
        when(BrowserDriverHelper.getDriver(OPERA)).thenReturn(getMockedDriver("opera", "2"));

        Assert.assertEquals(of instanceof BrowserManagerOpera, Boolean.TRUE);
        Assert.assertTrue(of.getBrowserManager() instanceof OperaDriverManager);
        Assert.assertTrue(of.getBrowserManager("1") instanceof OperaDriverManager);
        Assert.assertTrue(of.getDriver() instanceof RemoteWebDriver);
    }

    @Test
    public void shouldCheckOfPhantomJS() throws Exception {
        IBrowserManager of = BrowserManagerFactory.manager(PHANTOMJS.name());

        mockStatic(BrowserManagerFactory.class);
        mockStatic(BrowserDriverHelper.class);

        when(BrowserManagerFactory.manager(PHANTOMJS.name())).thenReturn(new BrowserManagerPhantomJS());
        when(BrowserDriverHelper.getDriver(PHANTOMJS)).thenReturn(getMockedDriver("phantomjs", "2"));

        Assert.assertEquals(of instanceof BrowserManagerPhantomJS, Boolean.TRUE);
        Assert.assertTrue(of.getBrowserManager() instanceof PhantomJsDriverManager);
        Assert.assertTrue(of.getBrowserManager("1") instanceof PhantomJsDriverManager);
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
