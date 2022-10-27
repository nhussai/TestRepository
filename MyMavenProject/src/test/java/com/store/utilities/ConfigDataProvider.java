package com.store.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
	Properties pro;//global variable

	public ConfigDataProvider() {/// we created a constructor

		File src = new File(System.getProperty("user.dir") + "/Configuration/Config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
				pro = new Properties();
				pro.load(fis);
		} catch (Exception e) {
			System.out.println("Unable to read config file " + e.getMessage());		
		}

	}
	//Return you the Browser from Config file
	public String getBrowser() {
		return pro.getProperty("Browser");
	}
	//Return you the url from Config file
	public String getUrl() {
		return pro.getProperty("Url");
	}
	
}
