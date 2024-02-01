package com.searchengine.qa.basetest;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.searchengine.qa.driverfactory.DriverFactory;
import com.searchengine.qa.pages.SearchEngineHomePage;
import com.searchengine.qa.pages.SearchResultsPage;

public class BaseTest {

	private WebDriver driver;
	public DriverFactory driverfactory;
	public Properties prop;
	public SearchEngineHomePage searchenginehomepage;
	public SearchResultsPage searchresultspage;
	public static final Logger LOG = Logger.getLogger(BaseTest.class);

	/**
	 * Setup method to initialize the WebDriver and configuration properties before
	 * each test method. It uses the DriverFactory class to handle WebDriver
	 * initialization and configuration loading.
	 */
   
	@BeforeMethod 
	public void setUp() {

		driverfactory = new DriverFactory();

		prop = driverfactory.initProp();

		driver = driverfactory.initDriver(prop);
		
		driver.get(prop.getProperty("url").trim());
		
		driver.manage().deleteAllCookies();
		
		driver.manage().window().maximize();
		
		searchenginehomepage = new SearchEngineHomePage(driver);
	

	}

	/**
	 * Teardown method to perform cleanup after each test method. It quits the
	 * WebDriver
	 */

	@AfterMethod

	public void tearDown() {

		try {

			if (driver != null) {
				driver.quit();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
