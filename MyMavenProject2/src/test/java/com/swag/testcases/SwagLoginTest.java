package com.swag.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/*Create a simple maven project named MyMavenProject2 and carry out
login validation on the application https://www.saucedemo.com/ through
your maven project.*/


public class SwagLoginTest {

	@Test
	public static void loginTest() {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
		
		driver.get("https://www.saucedemo.com/");
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		driver.findElement(By.xpath("//span[text()='Products']"));
		
		
		String actual = driver.findElement(By.xpath("//span[text()='Products']")).getText();
		
		String expected = "PRODUCTS";
		
		Assert.assertEquals(actual, expected, "Login failed");
		driver.quit();
	}
}
