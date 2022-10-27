package com.store.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.store.utilities.BrowserFactory;
import com.store.utilities.ConfigDataProvider;
import com.store.utilities.ExcelDataProvider;
import com.store.utilities.Helper;

public class BaseClass {
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	
	public ExtentReports extent;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest logger;
	String htmlReportPath = System.getProperty("user.dir")+"/Reports/Base_"+Helper.getCurrentTime()+".html";
	
	
	@BeforeSuite
	public void setupSuite() {
		excel = new ExcelDataProvider();	
		config = new ConfigDataProvider();
		Reporter.log("Setting up reports and test is about to start", true);
		htmlReporter = new ExtentHtmlReporter(htmlReportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		logger = extent.createTest("Google test --> Test");
		Reporter.log("Setting up complete. Test may start.", true);
	}
	
	@BeforeClass
	public void setUP() {
		Reporter.log("About to open the browser", true);
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver = BrowserFactory.openApplication(driver, config.getBrowser(), config.getUrl());
		Reporter.log("The browser is launched", true);
	}
		
	@AfterClass
	public void tearDown() {
		driver.close();
		Reporter.log("Url is closed", true);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		Reporter.log("About capture test result ", true);
		if(result.getStatus() == ITestResult.FAILURE) {
			Helper.captureScreenshot(driver);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			Helper.captureScreenshot(driver);	
		}
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " >> FAILED due to following reason: ", ExtentColor.RED));

			try {
				logger.log(Status.FAIL, result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " >> Test passed. ", ExtentColor.GREEN));
			try {
				logger.log(Status.PASS, result.getName() , MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Reporter.log("Test result is captured", true);
	}
	
	@AfterTest
	public void endTest() {
		extent.flush();//if don't use flush no report will generate 
		Reporter.log("Report is generated", true);
	} 
}
