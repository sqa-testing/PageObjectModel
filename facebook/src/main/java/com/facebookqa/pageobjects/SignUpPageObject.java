package com.facebookqa.pageobjects;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPageObject extends PageHeader {
	
	@FindBy(xpath = "//div[@id='pageFooterChildren']//a")
	List<WebElement> footerChildren;
	
	public SignUpPageObject() {
		PageFactory.initElements(driver, this);
	}
	
	public UserLoginHomePage loginPage() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		footerChildren.get(1).click();
		driver.navigate().back();
		return new UserLoginHomePage();
	}

}
