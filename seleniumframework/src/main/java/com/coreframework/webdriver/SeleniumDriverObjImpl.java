package com.coreframework.webdriver;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.coreframework.listeners.MyWebDriverListerner;

public class SeleniumDriverObjImpl implements SeleniumDriverObj {
	WebDriver driver;
	EventFiringWebDriver eventFiringWebDriver;
	private static Logger logger = Logger.getLogger(SeleniumDriverObjImpl.class);

	@Override
	public WebDriver getDriver(final String browserName) {
		if (!(browserName.equalsIgnoreCase("Random"))) {
			getBrowerSpecificDriver(browserName);
		} else {
			getBrowerSpecificDriver(DriverType.getRandomBrowser().name());
		}
		driver.manage().window().maximize();
		this.eventFiringWebDriver = new EventFiringWebDriver(driver);
		MyWebDriverListerner handler = new MyWebDriverListerner();
		eventFiringWebDriver.register(handler);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	private void getBrowerSpecificDriver(final String browserName) {
		DriverType driverType = null;
		try {
			driverType = DriverType.valueOf(browserName.toUpperCase());
		} catch (IllegalArgumentException ignored) {
			logger.error(ignored.getMessage());
			System.err.println("Unknown driver specified, defaulting to '" + browserName + "'...");
		}
		driver = driverType.getWebDriverObject(driverType.getDesiredCapabilities());
	}
}
