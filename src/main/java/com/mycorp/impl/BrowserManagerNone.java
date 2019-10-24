package com.mycorp.impl;

import com.mycorp.IBrowserManager;
import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.VoidDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;

import java.util.Map;

public class BrowserManagerNone implements IBrowserManager {

    public BrowserManager getBrowserManager() {
        return VoidDriverManager.getInstance().version("1");
    }

    public BrowserManager getBrowserManager(final String version) {
        return getBrowserManager().version(version);
    }

    public WebDriver getDriver() {
        final DesiredCapabilities dc = new DesiredCapabilities(BrowserType.MOCK, "mock-version", Platform.ANY);
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

    public WebDriver getMockedDriver() {
        return getDriver();
    }
}
