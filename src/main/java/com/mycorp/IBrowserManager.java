package com.mycorp;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.BrowserManager;

public interface IBrowserManager {
	
	BrowserManager getBrowserManager();

	BrowserManager getBrowserManager(final String version);

	WebDriver getDriver();
	
}
