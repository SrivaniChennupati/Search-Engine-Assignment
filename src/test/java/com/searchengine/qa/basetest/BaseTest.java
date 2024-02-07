package com.searchengine.qa.basetest;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

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
	 * initialization and configuration loading. and dynamically setting the search
	 * engine URL based on the provided search engine parameter.
	 */

	@BeforeMethod

	@Parameters("searchEngine")

	public void setUp(String searchEnginetype) {

		driverfactory = new DriverFactory();

		prop = driverfactory.initProp();

		String searchEngineUrl = null;

		switch (searchEnginetype.toLowerCase()) {
		case "google":
			searchEngineUrl = prop.getProperty("googleSearchEngineUrl");
			break;
		case "bing":
			searchEngineUrl = prop.getProperty("bingSearchEngineUrl");
			break;
		case "yahoo":
			searchEngineUrl = prop.getProperty("yahooSearchEngineUrl");
			break;
		default:
			// Default to Google if no valid search engine type is provided
			 LOG.error("Invalid search engine type provided: " + searchEnginetype);
			 break;
		}

		prop.setProperty("url", searchEngineUrl);

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
