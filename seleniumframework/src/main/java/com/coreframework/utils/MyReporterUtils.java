package com.coreframework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Reporter;

public class MyReporterUtils {
	public static final String LOG_DATE_FORMAT = "yyyyMMddhhmmssSS";
	public static final String LOG_SPLITTER = "::";

	public static void logEvent(String log, Date dt) {
		Reporter.log(appendDateTolog(log, dt),
				BaseFrameWorkInitializer.getInstance().getReadProp().isPrintLogsToConsole());
	}

	public static void logEvent(String str) {
		logEvent(str, new Date());
	}

	private static String appendDateTolog(String log, Date dt) {
		StringBuilder str = new StringBuilder();
		str.append(log);
		str.append(LOG_SPLITTER);
		str.append(convertDateToString(dt));
		return str.toString();
	}

	private static String convertDateToString(Date dt) {
		SimpleDateFormat ft = new SimpleDateFormat(LOG_DATE_FORMAT);
		return ft.format(dt);
	}

	public static void logEvent(String log, boolean b) {
		Reporter.log(appendDateTolog(log, new Date()), b);

	}

	public static String getpreformattedText(String text) {
		StringBuffer str = new StringBuffer();
		str.append("<span style='font-weight:bold;background-color: #ffffcc'>");
		str.append(text);
		str.append("</span> <br>");
		return str.toString();
	}

	public static String getLink(String link, String texttodisplay) {
		StringBuffer str = new StringBuffer();
		str.append("<a href=\"");
		str.append(link);
		str.append("\"  download>");
		str.append(getpreformattedText(texttodisplay));
		str.append("</a>");
		return str.toString();
	}

	public static void addDescription(String string) {
		Reporter.getCurrentTestResult().setAttribute(GenericConstants.DESCRIPTION, string);
		MyReporterUtils.logEvent("<Description>" + string, MyReporterUtils.printToConsole());
	}

	public static boolean printToConsole() {
		return BaseFrameWorkInitializer.getInstance().getReadProp().isPrintLogsToConsole();
	}

	public static String addCodeBlock(String string, String actualJsonData) {
		StringBuffer str = new StringBuffer();
		str.append(getpreformattedText(string));
		str.append("<textarea disabled=\"\" class=\"code-block\" style=\"height: 20px;\">");
		str.append(actualJsonData);
		str.append("</textarea>");
		return str.toString();
	}

	public static Date getDate(String dateString) {
		SimpleDateFormat ft = new SimpleDateFormat(LOG_DATE_FORMAT);
		try {
			Date dt = ft.parse(dateString);
			return dt;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void logEventAsError(String string) {
		logEvent(getErrorText(string), new Date());

	}

	private static String getErrorText(String string) {
		StringBuffer str = new StringBuffer();
		str.append("<span style='font-weight:bold;background-color: #ffffcc;color:red'>");
		str.append(string);
		str.append("</span> <br>");
		return str.toString();
	}
}