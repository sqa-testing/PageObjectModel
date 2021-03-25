package com.coreframework.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
	public static String getRandomNumber() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_SS");
		return ft.format(new Date());
	}

	private static String getRandomCaptureFileNamePath(final String testName) {
		String fileName = getRandomNumber();
		StringBuffer stringTotalName = new StringBuffer();
		stringTotalName.append(GenericConstants.SCREEN_SHOT_FOLDER + File.separator);
		stringTotalName.append(testName);
		stringTotalName.append("-");
		stringTotalName.append(fileName);
		return stringTotalName.toString();

	}

	public static String getRandomCaptureFileName(final String testName) {
		StringBuffer stringTotalName = new StringBuffer();
		stringTotalName.append(getRandomCaptureFileNamePath(testName));
		stringTotalName.append(".png");
		return stringTotalName.toString();

	}

	public static boolean stringToBoolean(final String property) {
		if (property == null)
			return false;
		else if (property.equalsIgnoreCase("true"))
			return true;
		else
			return false;
	}

	public static String getRandomNumberWithReadableFormat() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		return ft.format(new Date());
	}
}
