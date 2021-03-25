package com.coreframework.webdriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.coreframework.utils.FilePathUtils;

public enum DriverType implements DriverSetup {
	FIREFOX {

		@Override
		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			System.setProperty("webdriver.gecko.driver", FilePathUtils.getDriverFullPath("geckodriver.exe"));
			return capabilities;
		}

		@Override
		public WebDriver getWebDriverObject(final DesiredCapabilities capabilities) {
			FirefoxOptions firefoxOptions = new FirefoxOptions(capabilities);
			return new FirefoxDriver(firefoxOptions);
		}

	},
	CHROME {

		@Override
		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
			HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
			chromePreferences.put("profile.password_manager_enabled", "false");
			chromePreferences.put("profile.default_content_setting_values.notifications", 2);
			capabilities.setCapability("chrome.prefs", chromePreferences);
			System.setProperty("webdriver.chrome.driver", FilePathUtils.getDriverFullPath("chromedriver.exe"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-notifications");
			options.setExperimentalOption("prefs", chromePreferences);
			capabilities.setCapability("chrome.binary", FilePathUtils.getDriverFullPath("chromedriver.exe"));
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			return capabilities;
		}

		@Override
		public WebDriver getWebDriverObject(final DesiredCapabilities capabilities) {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("test-type");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.merge(capabilities);
			return new ChromeDriver(chromeOptions);
		}

	};

	public static DriverType getRandomBrowser() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

}
