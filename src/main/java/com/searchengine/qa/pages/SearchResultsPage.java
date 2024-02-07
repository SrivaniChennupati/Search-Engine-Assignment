package com.searchengine.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.searchengine.qa.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	
	private ElementUtil elementutil;

	private  By firstResultLocator = By.cssSelector("div#search div.g:nth-child(1)");
	
	 public static final Logger LOG = Logger.getLogger(SearchEngineHomePage.class);
	
	public  SearchResultsPage( WebDriver driver,SearchEngineHomePage.SearchEngineType searchEngineType) {
		
		this.driver = driver;
		elementutil = new ElementUtil(driver);
		setFirstResultLocator(searchEngineType);
	}
	
	
	 public String getFirstResult() {
		 
		return elementutil.waitForElementVisible(10, firstResultLocator).getText();
		
	    }
	 
	 
	 // method to set the first result locator based on the search engine type
	 
	 private void setFirstResultLocator(SearchEngineHomePage.SearchEngineType searchEngineType) {
	        switch (searchEngineType) {
	            case GOOGLE:
	                firstResultLocator = By.cssSelector("div#search div.g:nth-child(1)");
	                break;
	            case BING:
	            	firstResultLocator = By.cssSelector("div#b_tween+ol");
	                break;
	            case YAHOO:
	            	firstResultLocator = By.cssSelector("div.dd.fst.algo.algo-sr.relsrch");
	                break;                
	            default:	
	                LOG.error("No valid search engine type provided to set the first result locator.");
	                break;     
	            
	        }
	    }

	
	
}
