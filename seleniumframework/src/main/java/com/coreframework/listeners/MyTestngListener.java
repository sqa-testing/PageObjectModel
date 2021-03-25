package com.coreframework.listeners;

import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

public class MyTestngListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

	Logger logger = Logger.getLogger(ExtentReporterNG.class);

	@Override
	public void onStart(final ISuite arg0) {

		Reporter.log("About to begin executing Suite " + arg0.getName(), true);

	}

	// This belongs to ISuiteListener and will execute, once the Suite is
	// finished

	@Override
	public void onFinish(final ISuite iSuite) {
//		Reporter.log("About to end executing Suite " + iSuite.getName(), true);
//		String filePath = File.separator + "target" + File.separator + "surefire-reports" + File.separator
//				+ "emailable-report.html";
//		if (!(new File(filePath)).exists()) {
//			filePath = File.separator + "test-output" + File.separator + "emailable-report.html";
//		}
//		try {
//			//SendEmail.sendEmailThis(filePath, iSuite.getName());
//		} catch (IOException | EmailException e) {
//			e.printStackTrace();
//		}
	}

	// This belongs to ITestListener and will execute before starting of Test
	// set/batch

	@Override
	public void onStart(final ITestContext testContext) {
		Reporter.log("About to begin executing Test " + testContext.getName(), true);
	}

	// This belongs to ITestListener and will execute, once the Test set/batch
	// is finished

	@Override
	public void onFinish(final ITestContext arg0) {
		Reporter.log("Completed executing test " + arg0.getName(), true);
	}

	// This belongs to ITestListener and will execute only when the test is pass

	@Override
	public void onTestSuccess(final ITestResult itestResult) {

		// This is calling the printTestResults method

		printTestResults(itestResult);

	}

	// This belongs to ITestListener and will execute only on the event of fail
	// test

	@Override
	public void onTestFailure(final ITestResult itestResult) {

		// This is calling the printTestResults method

		printTestResults(itestResult);

	}

	// This belongs to ITestListener and will execute before the main test start
	// (@Test)

	@Override
	public void onTestStart(final ITestResult testContext) {

		Reporter.log("started executing test " + testContext.getName(), true);

	}

	// This belongs to ITestListener and will execute only if any of the main
	// test(@Test) get skipped

	@Override
	public void onTestSkipped(final ITestResult itestResult) {

		printTestResults(itestResult);

	}

	// This is just a piece of shit, ignore this

	@Override
	public void onTestFailedButWithinSuccessPercentage(final ITestResult arg0) {

	}

	// This is the method which will be executed in case of test pass or fail

	// This will provide the information on the test

	private void printTestResults(final ITestResult result) {

		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			if (params != null) {
				Reporter.log("Test Method had the following parameters : " + params, true);
			}
		}
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
		}
		Reporter.log("Test Status: " + status, true);

	}

	// This belongs to IInvokedMethodListener and will execute before every
	// method including @Before @After @Test

	@Override
	public void beforeInvocation(final IInvokedMethod arg0, final ITestResult arg1) {

		String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());

		logger.debug(textMsg);

	}

	// This belongs to IInvokedMethodListener and will execute after every
	// method including @Before @After @Test

	@Override
	public void afterInvocation(final IInvokedMethod arg0, final ITestResult arg1) {

		String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());

		logger.debug(textMsg);

	}

	// This will return method names to the calling function

	private String returnMethodName(final ITestNGMethod method) {

		return method.getRealClass().getSimpleName() + "." + method.getMethodName();

	}

}
