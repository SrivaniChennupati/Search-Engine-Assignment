package com.searchengine.qa.tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.searchengine.qa.basetest.BaseTest;
import com.searchengine.qa.pages.SearchEngineHomePage;
public class SearchEngineTest extends BaseTest {
	
	
	@DataProvider
	public Object[][] getTestData() {
		
		return new Object[][] {

			      {"jobs-noreply@linkedin.com is legitimate?"},
				  { "SpaceX rocket launches schedule" }, 
				  {" Python 3.8 programming"},
				  {"2024 Science & Technology trends"},
		};
	}
    
	
	
	@Test(dataProvider = "getTestData")
	public void searchAndVerifyFirstResult(String searchterm) {

		try {
			
			searchenginehomepage.setSearchEngineByUrl();
						
			SearchEngineHomePage.SearchEngineType engineType = searchenginehomepage.getCurrentSearchEngineType();
				
			searchresultspage = searchenginehomepage.search(searchterm, engineType);

			String actualResult = searchresultspage.getFirstResult();
			
			String[] searchWords = searchterm.toLowerCase().split("\\s+");

			boolean isAtLeastOneWordPresent = false;
			for (String word : searchWords) {
				if (actualResult.toLowerCase().contains(word)) {
					isAtLeastOneWordPresent = true;
					break;
				}
			}

			Assert.assertTrue(isAtLeastOneWordPresent,"No word from the search term is present in the actual result: " + searchterm);

			LOG.info("Assertion Passed for Search Term: " + searchterm);

		} catch (Exception e) {

			LOG.error("An error occurred during the search and verification process: " + e.getMessage());
	
		}
	}

}
