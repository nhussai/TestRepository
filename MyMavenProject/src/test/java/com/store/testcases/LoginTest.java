package com.store.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.store.pages.BaseClass;
import com.store.pages.LoginPage;



public class LoginTest extends BaseClass {
		
	@Test
	public void testLogin() {
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	    loginPage.loginToStore(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
	    loginPage.validateLandingPage();    
	}
		
}
//this is abstraction 