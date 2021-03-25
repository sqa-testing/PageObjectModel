package com.facebookqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProfilePageObject extends PageHeader {
	@FindBy(xpath = "//*[@id='mount_0_0_GA']/div/div[1]/div/div[2]/div[4]/div[1]/span/div/div[1]")
	WebElement buttonAccount;
	@FindBy(xpath = "//span[text()='Log Out']")
	WebElement logOut;
	@FindBy(xpath = "//div[text()='Log Out']")
	WebElement confirmLogOut;
	

	public UserProfilePageObject() {
		PageFactory.initElements(driver, this);
	}

	public UserLoginHomePage logOut() {
		buttonAccount.click();
		logOut.click();
		confirmLogOut.click();
		return new UserLoginHomePage();
	}

}
