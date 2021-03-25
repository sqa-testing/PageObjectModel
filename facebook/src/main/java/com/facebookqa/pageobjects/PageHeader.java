package com.facebookqa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.coreframework.utils.BaseFrameWorkInitializer;

public class PageHeader {
	WebDriver driver;
	Wait<WebDriver> wait;

	public PageHeader() {
		this.driver = BaseFrameWorkInitializer.getInstance().getDriver();
		this.wait = BaseFrameWorkInitializer.getInstance().getWebDriverWait();
	}
}
