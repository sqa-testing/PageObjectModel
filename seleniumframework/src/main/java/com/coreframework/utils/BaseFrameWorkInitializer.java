package com.coreframework.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class BaseFrameWorkInitializer {
	private static Logger logger = Logger.getLogger(BaseFrameWorkInitializer.class);
	private WebDriver driver;
	private Wait<WebDriver> webdriverWait;
	private ReadPropertyData readProp;
	private boolean runInDebugMode;
	public static ThreadLocal<BaseFrameWorkInitializer> baseFrameWorkInitializer = new ThreadLocal<BaseFrameWorkInitializer>() {
		@Override
		protected BaseFrameWorkInitializer initialValue() {
			BaseFrameWorkInitializer baseFrameWork = new BaseFrameWorkInitializer();
			return baseFrameWork;
		}
	};

	private BaseFrameWorkInitializer() {
	}

	public static BaseFrameWorkInitializer getInstance() {
		return baseFrameWorkInitializer.get();
	}

	public void setDriverWait(final Wait<WebDriver> webdriverWait) {
		this.webdriverWait = webdriverWait;
	}

	public Wait<WebDriver> getWebDriverWait() {
		assertingNotNull(webdriverWait);
		return webdriverWait;
	}

	public void setDriver(final WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		assertingNotNull(driver);
		return driver;
	}

	public ReadPropertyData getReadProp() {
		assertingNotNull(readProp);
		return readProp;
	}

	public void setReadProp(final ReadPropertyData readProp) {
		this.readProp = readProp;
	}

	public boolean isRunInDebugMode() {
		return runInDebugMode;
	}

	public void setRunInDebugMode(final boolean runInDebugMode) {
		this.runInDebugMode = runInDebugMode;
	}

	public void removeThreadVairable() {
		baseFrameWorkInitializer.remove();
	}

	private static void assertingNotNull(final Object object) {
		if (object == null) {
			logger.debug("the object being returned is null");
		}
	}
}
