package com.coreframework.utils;

import org.apache.log4j.Logger;

public class FilePathUtils {
	private static Logger logger = Logger.getLogger(FilePathUtils.class);

	public static String getFullPathOfFileFromClassPath(final String browserImplExeFileName) {
		String filePath = FilePathUtils.class.getClassLoader().getResource(browserImplExeFileName).getFile();
		return filePath;
	}

	public static String getDriverFullPath(final String browserName) {
		String filePath = FilePathUtils.class.getClassLoader().getResource(browserName).getFile();
		// filePath = filePath.substring(6, filePath.length());
		logger.info("driver path returned is " + filePath);
		return filePath;

	}

	public static String getScreenShotFullPath(final String fileName) {
		StringBuffer stringTotalName = new StringBuffer();
		stringTotalName.append(System.getProperty("user.dir"));
		stringTotalName.append("/" + fileName);
		return stringTotalName.toString();

	}

}
