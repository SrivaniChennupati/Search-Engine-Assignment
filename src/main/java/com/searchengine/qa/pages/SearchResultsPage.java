package com.searchengine.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.searchengine.qa.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	
	private ElementUtil elementutil;

	private  By firstResultLocator = By.cssSelector("div#search div.g:nth-child(1)");
	
	public  SearchResultsPage( WebDriver driver) {
		
		this.driver = driver;
		elementutil = new ElementUtil(driver);
	}
	
	
	 public String getFirstResult() {
		 
		return elementutil.waitForElementVisible(10, firstResultLocator).getText();
		
	    }

	
	
}
