package com.coreframework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyDataImpl implements ReadPropertyData {
	Properties prop;

	public ReadPropertyDataImpl(final String propertyFilePath) {
		InputStream fis = null;
		fis = this.getClass().getClassLoader().getResourceAsStream(propertyFilePath);
		prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String readProperty(final String key) {
		return prop.getProperty(key);
	}

	@Override
	public Boolean isRunningDebug() {
		return DataUtils.stringToBoolean(System.getProperty("debug", prop.getProperty(GenericConstants.RUN_IN_DEBUG)));
	}

	@Override
	public String getResultsFolderPath() {
		return getKeyFromProp(GenericConstants.RESULTS_FOLDER_PATH);
	}

	private String getKeyFromProp(String propString) {
		return System.getProperty(propString, prop.getProperty(propString));
	}

	@Override
	public String getPlatformVersion() {
		return getKeyFromProp(GenericConstants.PLATFORM_VERSION);
	}

	@Override
	public boolean isPrintLogsToConsole() {
		return Boolean.parseBoolean(getKeyFromProp(GenericConstants.PRINT_LOGS_TO_CONSOLE));

	}

	@Override
	public String getScreenShotPath() {
		return getKeyFromProp(GenericConstants.SCREENSHOT_FOLDER_PATH);
	}

}
