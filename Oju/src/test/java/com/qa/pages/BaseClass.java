package com.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.utilities.BrowserFactory;
import com.qa.utilities.ConfigDataProvider;
import com.qa.utilities.ExcelDataProvider;
import com.qa.utilities.Helper;

public class BaseClass {
	public WebDriver driver;
	public ConfigDataProvider config;
	public ExcelDataProvider excel;
	public Helper help;
	public ExtentReports report; 
	public ExtentTest logger; 
	
	
	@BeforeSuite
	public void setupSuite() {
		config = new ConfigDataProvider();
		excel = new ExcelDataProvider();
		help = new Helper();
		ExtentHtmlReporter reporter =
		new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/Bank_"+help.getCurrentTime()+".html");
		report = new ExtentReports();
		report.attachReporter(reporter);
	
	}
	
	@BeforeTest
	public void setUp() {
		driver = BrowserFactory.openApplication(driver, config.getBrowser(), 
		config.getUrl());
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		report.flush();
		driver.close();
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.pass("Test passed", MediaEntityBuilder.createScreenCaptureFromPath(help.captureScreen(driver)).build());
		}
	}	
}
