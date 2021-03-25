package com.coreframework.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.coreframework.utils.DataUtils;
import com.coreframework.utils.GenericConstants;
import com.coreframework.utils.MyReporterUtils;
import com.coreframework.utils.ReadPropertyData;
import com.coreframework.utils.ReadPropertyDataImpl;
import com.coreframework.utils.SendEmail;

public class ExtentReporterNG implements IReporter {
	String resultPath;
	String screenShotPath;
	private ExtentReports extent = new ExtentReports();
	Logger logger = Logger.getLogger(ExtentReporterNG.class);
	List<Date> suiteTimingsList = new ArrayList<Date>();

	@Override
	public void generateReport(final List<XmlSuite> xmlSuites, final List<ISuite> suites,
			final String outputDirectory) {
		ReadPropertyData readprop = new ReadPropertyDataImpl(GenericConstants.STORE_DEMO_PROP_FILE_NAME);
		this.resultPath = readprop.getResultsFolderPath();
		this.screenShotPath = readprop.getScreenShotPath();
		String source = System.getProperty("user.dir") + File.separator + GenericConstants.SCREEN_SHOT_FOLDER;
		File srcDir = new File(source);

		File destDir = new File(screenShotPath);
		try {
			FileUtils.copyDirectory(srcDir, destDir);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int passedTest = 0;
		int failedTest = 0;
		String report_file_full_path = resultPath + File.separator + GenericConstants.EXTENT_REPORT_FILE_NAME + "_"
				+ DataUtils.getRandomNumber() + ".html";
		File file = new File(report_file_full_path);
		try {
			FileUtils.touch(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ExtentSparkReporter mainExtent = new ExtentSparkReporter(file);
		extent.attachReporter(mainExtent);

		for (ISuite suite : suites) {
			if ("suite of suites".equalsIgnoreCase(suite.getName()))
				continue;
			Map<String, ISuiteResult> result = suite.getResults();
			int suiteLevelPassedTests = 0;
			int suiteLevelFailedTests = 0;
			ExtentTest mainSuite = extent.createTest(suite.getName());
			for (ISuiteResult suiteResult : result.values()) {
				ITestContext context = suiteResult.getTestContext();
				ExtentTest extendSuite = mainSuite.createNode(context.getName());
				passedTest = passedTest + context.getPassedTests().size();
				failedTest = failedTest + context.getFailedTests().size();
				suiteLevelPassedTests = suiteLevelPassedTests + context.getPassedTests().size();
				suiteLevelFailedTests = suiteLevelFailedTests + context.getFailedTests().size();
				buildTestNodes(context.getPassedTests(), Status.PASS, extendSuite);
				buildTestNodes(context.getFailedTests(), Status.FAIL, extendSuite);
				buildTestNodes(context.getSkippedTests(), Status.FAIL, extendSuite);

				extendSuite.getModel()
						.setStatus(getStatus(context.getPassedTests().size(), context.getFailedTests().size()));
				suiteTimingsList.add(suiteResult.getTestContext().getStartDate());
				suiteTimingsList.add(suiteResult.getTestContext().getEndDate());
			}
			mainSuite.getModel().setStatus(getStatus(suiteLevelPassedTests, suiteLevelFailedTests));
			addTimingsToSuite(mainSuite, suiteTimingsList);
			suiteTimingsList = new ArrayList<Date>();

		}
		for (String s : Reporter.getOutput()) {
			extent.setTestRunnerOutput(s + "<br>");
		}

		extent.flush();
		try {
			SendEmail.sendEmailThis(report_file_full_path);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Status getStatus(int passed, int failed) {
		return failed == 0 && passed != 0 ? Status.PASS : passed > failed ? Status.WARNING : Status.FAIL;
	}

	private void addTimingsToSuite(final ExtentTest mainSuite, List<Date> timingList) {
		if (timingList.size() > 1) {
			Collections.sort(timingList);
			mainSuite.getModel().setStartTime(timingList.get(0));
			mainSuite.getModel().setEndTime(timingList.get(timingList.size() - 1));
		}
	}

	private void buildTestNodes(final IResultMap tests, final Status status, final ExtentTest extendSuite) {
		if (tests.size() > 0) {
			List<Date> timinglist = new ArrayList<Date>();
			for (ITestResult result : tests.getAllResults()) {
				timinglist.add(getTime(result.getStartMillis()));
				timinglist.add(getTime(result.getEndMillis()));
				generateExtentTest(status, extendSuite, result);
			}
			addTimingsToSuite(extendSuite, timinglist);
		}

	}

	private void generateExtentTest(final Status status, final ExtentTest extendSuite, final ITestResult result) {
		System.out.println(status);
		ExtentTest test = extendSuite.createNode(result.getMethod().getMethodName(),
				(String) result.getAttribute(GenericConstants.DESCRIPTION));
		test.getModel().setStartTime(getTime(result.getStartMillis()));
		test.getModel().setEndTime(getTime(result.getEndMillis()));
		for (String str : Reporter.getOutput(result)) {
			logger.debug(str);
			log(Status.INFO, str, test);
		}
		for (String group : result.getMethod().getGroups())
			test.assignCategory(group);
		if (result.getThrowable() != null) {
			test.log(status, result.getThrowable());
			test.getModel().getLogContext().getLast().setTimestamp(getTime(result.getEndMillis()));
		} else {
			test.log(status, "Test " + status.toString().toLowerCase() + "ed");
			test.getModel().getLogContext().getLast().setTimestamp(getTime(result.getEndMillis()));

		}
		String screenShotFileName = (String) result.getAttribute(GenericConstants.SCREEN_SHOT_REPORTER_ATTRIBUTE);
		logger.debug("the screen shot file name is " + screenShotFileName);
		System.out.println("the screen shot file name is " + screenShotFileName);

		try {
			addScreenShot(test, screenShotFileName, getTime(result.getEndMillis()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void log(Status st, String logStirng, ExtentTest test) {
		System.out.println("Log Str is " + logStirng);
		String[] split = logStirng.split(MyReporterUtils.LOG_SPLITTER);
		if (split.length > 1) {
			test.log(Status.INFO, split[0]);
			test.getModel().getLogContext().getLast().setTimestamp(getDate(split[1]));
		}
		test.log(Status.INFO, logStirng);
	}

	private Date getDate(String str) {
		System.out.println("Get data Str is " + str);
		if (!(MyReporterUtils.getDate(str) == null))
			return MyReporterUtils.getDate(str);
		else
			return new Date();
	}

	private void addScreenShot(final ExtentTest test, final String screenShotFileName, Date date) throws IOException {
		if (screenShotFileName != null) {
			test.log(Status.INFO, "Testcase Failed",
					MediaEntityBuilder
							.createScreenCaptureFromPath(
									screenShotPath + File.separator + screenShotFileName.replace("ScreenShots\\", ""))
							.build());
			test.getModel().getLogContext().getLast().setTimestamp(date);
		}
	}

	private Date getTime(final long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
