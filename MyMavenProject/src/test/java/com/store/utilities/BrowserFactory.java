package com.store.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	
	//public WebDriver driver;
	
	
	public static WebDriver openApplication(WebDriver driver, String browser, String url) {
		
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			
		}else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();

			
		}else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/Drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
		
		driver.get(url);
		
		return driver;
		
	}
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();	
	}
	

}
