package com.facebookqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoggedOutPageObject extends PageHeader {

	@FindBy(xpath = ".//div[@id='userNavigationLabel']")
	WebElement accountSettingsArrow;
	// @FindBy(xpath =
	// "//li[@id=\"BLUE_BAR_ID_DO_NOT_USE\"]/div/div/div/div/div/ul/li[9]")
	@FindBy(xpath = "//a//span[text()='Log Out']")
	WebElement logOut;

	public LoggedOutPageObject() {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		wait.until(ExpectedConditions.visibilityOf(accountSettingsArrow));
		accountSettingsArrow.click();
	}

	public void clickLoggedOut() {
		wait.until(ExpectedConditions.visibilityOf(logOut));
		logOut.click();
	}
}
