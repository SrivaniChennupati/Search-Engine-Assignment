package com.searchengine.qa.pages;

import java.net.URI;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


import com.searchengine.qa.utils.ElementUtil;

public class SearchEngineHomePage {

	private WebDriver driver;

	private ElementUtil elementutil;

	private By searchbox = By.name("q");
	
    private SearchEngineType currentSearchEngineType;
    
    public static final Logger LOG = Logger.getLogger(SearchEngineHomePage.class);
    

	public SearchEngineHomePage(WebDriver driver) {
		this.driver = driver;
		elementutil = new ElementUtil(driver);
	}

	public SearchResultsPage search(String searchTerm , SearchEngineType searchEngineType) {

		setSearchBoxByEngine(searchEngineType);
		elementutil.doSendKeys(searchbox, searchTerm, Keys.ENTER);
      	return new SearchResultsPage(driver, searchEngineType);

	}
	
	// Method to set the search engine based on the URL
	
	 public void setSearchEngineByUrl() {
		
		String url=elementutil.doGetCurrentURL();
		currentSearchEngineType= getSearchEngineTypeFromUrl(url);
		setSearchBoxByEngine(currentSearchEngineType);
	}
	
	public SearchEngineType getCurrentSearchEngineType() {
        return currentSearchEngineType;
    }

	// method to extract search engine type from the URL
	private SearchEngineType getSearchEngineTypeFromUrl(String url) {
		try {
			URI uri = new URI(url);
			String host = uri.getHost();

			if (host.contains("google")) {
				return SearchEngineType.GOOGLE;
			} else if (host.contains("bing")) {
				return SearchEngineType.BING;
			} else if (host.contains("yahoo")) {
				return SearchEngineType.YAHOO;
			} else {
				LOG.error("No matching search engine found");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// method to set the search box based on the search engine type as locators change search engine to search engine 
	
	private void setSearchBoxByEngine(SearchEngineType engineType) {
		
		switch (engineType) {
		case GOOGLE:
			searchbox = By.name("q");
			break;
		case BING:
			searchbox = By.name("q");
			break;
		case YAHOO:
			searchbox = By.name("p");
			break;
		default:
            LOG.error("Unsupported search engine type: " + engineType);
            break;
			
		}
	}

	// Enum to define supported search engines
	public enum SearchEngineType {
		GOOGLE, BING, YAHOO
	}

}
