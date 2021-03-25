package com.coreframework.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.coreframework.selenium.SeleniumActions;
import com.coreframework.utils.BaseFrameWorkInitializer;

public class MyWebDriverListerner implements WebDriverEventListener {

	@Override
	public void afterClickOn(final WebElement arg0, final WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterFindBy(final By arg0, final WebElement arg1, final WebDriver arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(final WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(final WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateTo(final String arg0, final WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(final String arg0, final WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeClickOn(final WebElement arg0, final WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeFindBy(final By arg0, final WebElement arg1, final WebDriver driver) {
		if (BaseFrameWorkInitializer.getInstance().isRunInDebugMode()) {
			SeleniumActions.captureScreenshot(driver, "fileName");
		}
	}

	@Override
	public void beforeNavigateBack(final WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(final WebDriver arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateTo(final String arg0, final WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeScript(final String arg0, final WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(final Throwable arg0, final WebDriver driver) {
		SeleniumActions.captureScreenshot(driver, "fileName");
	}

	@Override
	public void beforeNavigateRefresh(final WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateRefresh(final WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(final WebElement arg0, final WebDriver arg1, final CharSequence[] arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeChangeValueOf(final WebElement arg0, final WebDriver arg1, final CharSequence[] arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub

	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub

	}

}
