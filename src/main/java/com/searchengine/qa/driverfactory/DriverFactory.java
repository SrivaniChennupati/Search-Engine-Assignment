package com.searchengine.qa.driverfactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;

	public Properties prop;
	
	public static final Logger LOG = Logger.getLogger(DriverFactory.class);

	/**
	 * This method will help us to initialize a WebDriver based on the specified
	 * browser type
	 * 
	 * @param prop
	 * @return driver
	 */

	public WebDriver initDriver(Properties prop) {
		
		String browser = prop.getProperty("browser").trim();

		switch (browser.toLowerCase()) {
        case "chrome":
        	LOG.info("Running the test cases locally on...."+browser);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            break;
        case "firefox":
        	LOG.info("Running the test cases locally on...."+browser);
            WebDriverManager.firefoxdriver().setup();
            driver = new  FirefoxDriver();
            break;
        case "edge":
        	LOG.info("Running the test cases locally on...."+browser);
            WebDriverManager.edgedriver().setup();
            driver= new  EdgeDriver();
            break;
        default:
        	LOG.error("Unsupported browser...." + browser);
            
		}
		
		return driver;

	}

	/**
	 * This method initializes a Properties object by loading properties from a
	 * configuration file
	 * 
	 * @return prop
	 */

	public Properties initProp() {

		prop = new Properties();

		FileInputStream ip;
		try {
			ip = new FileInputStream("./src/test/resources/config/configuration.properties");

			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}

}
