package com.swag.testcases;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase {
	WebDriver driver;
	
	ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	ExtentTest logger;
	String htmlReportPath = System.getProperty("user.dir")+"/Reports/Base_"+Helper.getCurrentTime()+".html";
	
	//BeforeSuite, BeforeTest, BeforeClass, BeforeMethod, Test, AfterMethod, AfterClass, AfterTest, AfterSuite
	
	@BeforeSuite
	public void setupSuite() {
		Reporter.log("Setting up reports and test is about to start", true);
		htmlReporter = new ExtentHtmlReporter(htmlReportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		logger = extent.createTest("Google test --> Test");
		Reporter.log("Setting up complete. Test may start.", true);
	}
	
	@BeforeClass
	public void setup() {
		Reporter.log("About to open the browser", true);
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		Reporter.log("The browser is launched", true);
	}
	
	@Test
	public void test1() {
		Reporter.log("About to open the url", true);
		driver.get("http://www.google.com/");
		assertEquals(driver.getTitle(), "Google");
		Reporter.log("The url is launched", true);
	}
	@Test
	public void test2() {
		Reporter.log("About to open the url", true);
		driver.get("http://www.google.com/");
		assertEquals(driver.getTitle(), "google");
		Reporter.log("Title verified", true);
	}
	@AfterMethod
	public void getResults(ITestResult result) {
		Reporter.log("About capture test result ", true);
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
	
	@AfterClass
	public void tearDown() {
		driver.close();
		Reporter.log("Url is closed", true);
	}
	
	@AfterTest
	public void endTest() {
		extent.flush();//if don't use flush no report will generate 
		Reporter.log("Report is generated", true);
	} 
}
