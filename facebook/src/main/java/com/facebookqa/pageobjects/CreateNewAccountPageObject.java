package com.facebookqa.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewAccountPageObject {
	
	@FindBy(xpath = ".//input[@name='firstname']")
	WebElement firstName;
	
	
	
	public UserLoginHomePage createNewAccount(String fName) {
		
		firstName.sendKeys(fName);
		return new UserLoginHomePage();
	}

}
