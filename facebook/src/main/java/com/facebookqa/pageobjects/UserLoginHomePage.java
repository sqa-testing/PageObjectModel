package com.facebookqa.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLoginHomePage extends PageHeader {
	@FindBy(xpath = ".//input[@id='email']")
	WebElement userName;
	@FindBy(xpath = ".//input[@id='pass']")
	WebElement userPassword;
	@FindBy(xpath = "//button[@name='login']")
	WebElement login;
	
	@FindBy(xpath = "//div[@id='pageFooterChildren']//a")
	List<WebElement> footerChildren;
	//List<WebElement> allLinks = footerChildren.findElements(By.tagName("a"));
	
	public UserLoginHomePage() {
		PageFactory.initElements(driver, this);
	}

	public UserProfilePageObject login(String userNameStr, String password) {
		userName.sendKeys(userNameStr);
		userPassword.sendKeys(password);
		login.click();
		return new UserProfilePageObject();
	}
	public SignUpPageObject signUp() {		
		footerChildren.get(0).click();
		driver.navigate().back();
		return new SignUpPageObject();
	}	
	public UserLoginHomePage messanger() {
		footerChildren.get(2).click();
		driver.navigate().back();
		return new UserLoginHomePage();
	}
	public CreateNewAccountPageObject createAccount() {
		
		footerChildren.get(0).click();
		return new CreateNewAccountPageObject();
	}
}