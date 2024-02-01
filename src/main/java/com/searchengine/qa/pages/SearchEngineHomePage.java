package com.searchengine.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.searchengine.qa.utils.ElementUtil;

public class SearchEngineHomePage {
	
	
	private WebDriver driver;
	
	private  ElementUtil elementutil;
	
	private By searchbox = By.name("q");
	
	
	
	
	public SearchEngineHomePage(WebDriver driver) {
        this.driver = driver;
        elementutil = new ElementUtil(driver);
    }
	
	
	public SearchResultsPage search(String searchTerm) {
		
		elementutil.doSendKeys(searchbox, searchTerm, Keys.ENTER);
		
		return new SearchResultsPage(driver);
		
    }

}
