package com.qa.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {

	//screenshot, dateTime, frames, popup, 
	
	
	public String captureScreen(WebDriver driver) {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/Bank_"+getCurrentTime()+".png");

		//converting relative path to absolute path
		String fullPath = dest.getAbsolutePath();
		try {
			FileHandler.copy(src, dest);
			System.out.println("Screenshot captured!!");
		} catch (Exception e) {
			System.out.println("Unable to capture screenshot");
		}
		
		return fullPath;	
	}
	
	public String getCurrentTime() {
		//Specifying the format
		DateFormat myFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		//Current time
		Date currentDate = new Date();
		return myFormat.format(currentDate);
		
		
	}
	
	
}
