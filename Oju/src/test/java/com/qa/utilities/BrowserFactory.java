package com.qa.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	WebDriver driver;
	
	
	public static WebDriver openApplication(WebDriver driver, String browser, String url) {
	
		if(browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", ".\\Driver\\geckodriver.exe");
			driver = new FirefoxDriver();	
		}else if(browser.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", ".\\Driver\\msedgedriver.exe");
			driver = new EdgeDriver();	
		}
		else
		{
			System.out.println("This browser is not supported");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(url);
		return driver;
		
	}
	

}
