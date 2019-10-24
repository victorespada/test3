package com.mycorp;

import com.mycorp.impl.*;

public class BrowserManagerFactory {
	
	public static IBrowserManager manager(String browserType) {
		if(browserType == null){
			browserType = "null";
		}
		switch(browserType.toUpperCase()) {
			case "CHROME": return new BrowserManagerChrome();
			case "EDGE": return new BrowserManagerEdge();
			case "FIREFOX" :
			case "MARIONETTE":
				return new BrowserManagerFirefox();
			case "IE": return new BrowserManagerIE();

			case "OPERA": return new BrowserManagerOpera();
			case "PHANTOMJS": return new BrowserManagerPhantomJS();
			case "NONE": default: return new BrowserManagerNone();
		}
	}

}
