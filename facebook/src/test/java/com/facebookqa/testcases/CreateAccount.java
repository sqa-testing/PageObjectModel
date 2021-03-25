package com.facebookqa.testcases;

import java.util.Iterator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.coreframework.exceldtos.FacebookLogin;
import com.coreframework.selenium.BaseDriverInitilization;
import com.coreframework.testngdataprovider.ExcelReadDataProvider;
import com.facebookqa.pageobjects.CreateNewAccountPageObject;
import com.facebookqa.pageobjects.SignUpPageObject;
import com.facebookqa.pageobjects.UserLoginHomePage;
import com.facebookqa.pageobjects.UserProfilePageObject;

public class CreateAccount extends BaseDriverInitilization {
	UserLoginHomePage homePageObject;

	@BeforeMethod
	public void SetUp() {
		homePageObject = new UserLoginHomePage();
	}

	@Test(dataProvider = "data-source")
	public void createNewFacebookAccount(FacebookLogin _facebookLogin) {
		loadBaseUrl();		
		
		// Pulok will show us demo after completing Create account testcase
		CreateNewAccountPageObject createAccount=homePageObject.createAccount();	
		UserLoginHomePage againHomePage=createAccount.createNewAccount(_facebookLogin.getUserLogin());
		
		
	}

	@DataProvider(name = "data-source")
	public Iterator<Object[]> dataOneByOne() {
		System.out.println("in data One by One");
		return new ExcelReadDataProvider("facebook.xlsx", "Sheet2", "CreateAccount");
	}

}
