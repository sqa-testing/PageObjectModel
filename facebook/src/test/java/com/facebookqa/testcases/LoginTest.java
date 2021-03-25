package com.facebookqa.testcases;

import java.util.Iterator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.coreframework.exceldtos.FacebookLogin;
import com.coreframework.selenium.BaseDriverInitilization;
import com.coreframework.testngdataprovider.ExcelReadDataProvider;
import com.facebookqa.pageobjects.UserProfilePageObject;
import com.facebookqa.pageobjects.SignUpPageObject;
import com.facebookqa.pageobjects.UserLoginHomePage;

public class LoginTest extends BaseDriverInitilization {
	UserLoginHomePage homePageObject;

	@BeforeMethod
	public void SetUp() {
		homePageObject = new UserLoginHomePage();
	}

	@Test(dataProvider = "data-source")
	public void testLogin(FacebookLogin _facebookLogin) {
		loadBaseUrl();
				
		SignUpPageObject signUp=homePageObject.signUp();
		UserLoginHomePage login = signUp.loginPage();
		UserLoginHomePage messanger =login.messanger();
		UserProfilePageObject userProfile = homePageObject.login(_facebookLogin.getUserLogin(),
				_facebookLogin.getUserPassword());
		
		//UserLoginHomePage logout = userProfile.logOut();
	}

	@DataProvider(name = "data-source")
	public Iterator<Object[]> dataOneByOne() {
		System.out.println("in data One by One");
		return new ExcelReadDataProvider("facebook.xlsx", "Sheet1", "Login");
	}
}
