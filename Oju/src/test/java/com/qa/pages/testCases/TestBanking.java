package com.qa.pages.testCases;
import org.testng.annotations.Test;
import com.qa.pages.BaseClass;
import com.qa.pages.HomePage;


public class TestBanking extends BaseClass {
	
	@Test
	public void openBankSite() {
		
		logger = report.createTest("Client creation test");
		
		
		//creating an object of your HomePage class
		HomePage home = new HomePage(driver);
		home.loginAsManager();
		logger.info("Logged in as a manager");
		
		//this coming from excel sheet
		home.addCustomer(excel.getStringData("Client", 1, 0), excel.getStringData("Client", 1, 1), excel.getStringData("Client", 1, 2));  
		logger.info("Added a client");
		
		
	}	
	
}
