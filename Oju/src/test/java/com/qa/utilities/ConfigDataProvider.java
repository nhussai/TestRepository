package com.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	Properties prop;
	
	public ConfigDataProvider() {
		
		File src = new File("./Config/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();//creating an object of the reference of prop
			prop.load(fis);
		} catch (Exception e) {
			
			System.out.println("Unable to read config file");
		} 	
	}	
	
	public String getBrowser() {
		return prop.getProperty("browser");//this will return the browser 
	}
	
	public String getUrl() {
		return prop.getProperty("url");//this will return the url
	}
	
	
	
	
	
	
}
