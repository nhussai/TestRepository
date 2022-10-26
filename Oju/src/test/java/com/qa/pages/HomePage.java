package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;//global variable
	
	
	public HomePage(WebDriver driver) {//we are creating a constructor
		
		this.driver = driver;
		
	PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(xpath="//button[@ng-click='manager()']") 
	 WebElement managerLoginBtn;
	
	//click on add customer 
	//driver.findElement(By.xpath("//button[@ng-class='btnClass1']")).click();
	//add customer details 
	//adding customer details
	//driver.findElement(By.xpath("//input[@ng-model='fName']")).sendKeys("Abc");
	//driver.findElement(By.xpath("//input[@ng-model='lName']")).sendKeys("Xyz");
	//driver.findElement(By.xpath("//input[@ng-model='postCd']")).sendKeys("10008");
	//clicking on add customer button
	//driver.findElement(By.xpath("//button[@type='submit']")).click();
	// handling alert 
	//driver.switchTo().alert().accept();
	
	//code of page object model-->in this code we join the code
	@FindBy(xpath="//button[@ng-class='btnClass1']") 
	WebElement addCustomer;
	
	@FindBy(xpath="//input[@ng-model='fName']") 
	WebElement fName;
	
	@FindBy(xpath="//input[@ng-model='lName']") 
	WebElement lName;
	
	@FindBy(xpath="//input[@ng-model='postCd']") 
	WebElement zip;
	
	@FindBy(xpath="//button[@type='submit']") 
	WebElement addBtn;
	
	
	//login as manager
	public void loginAsManager() {
		managerLoginBtn.click();
	}
	
	public void addCustomer(String firstName, String lastName, String postCode) {
		addCustomer.click();
		fName.sendKeys(firstName);
		lName.sendKeys(lastName);
		zip.sendKeys(postCode);
		addBtn.click();
		driver.switchTo().alert().accept();
	}
	
}
