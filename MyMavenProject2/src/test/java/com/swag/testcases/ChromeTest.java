package com.swag.testcases;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeTest {
	
	WebDriver driver;
	
	@BeforeTest
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeClass
	public void setupTest() {
		driver = new ChromeDriver();//will launch chrome 
		
	}
	@AfterTest
	public void tearDown() {
	driver.close();
	}
	
	@Test
	public void testOne() {
		driver.get("https://www.google.com/");
		assertEquals(driver.getTitle(), "Google", "Title did not match");
	}
}
