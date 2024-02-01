package com.searchengine.qa.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

	/**
	 * This Constructor takes webdriver instance as parameter and assign it to the
	 * class variable named driver. It follows the Single Responsibility Principle
	 * (SRP) by focusing on the responsibility of initializing the driver.
	 * 
	 * @param driver
	 */

	public ElementUtil(WebDriver driver) {

		this.driver = driver;

	}

	/**
	 * This method will help us to find or create the Web element
	 * 
	 * @param locator
	 * @return WebElement
	 */

	public WebElement getElement(By locator) {

		return driver.findElement(locator);

	}

	/**
	 * This method will help us to create WebElement and pass the Value
	 * 
	 * @param locator
	 * @param value
	 */

	public void doSendKeys(By locator, String value) {

		getElement(locator).sendKeys(value);

	}
	
	/**
	 *  Overloaded method with additional parameter for special key
	 * @param locator
	 * @param value
	 * @param specialKey
	 */
	
	public void doSendKeys(By locator, String value, Keys specialKey) {
        WebElement element = getElement(locator);
        element.sendKeys(value);
        element.sendKeys(specialKey);
    }
	
	

	/**
	 * This method will help us to click the WebElement
	 * 
	 * @param locator
	 */

	public void doClick(By locator) {

		getElement(locator).click();

	}

	/**
	 * This method will help us to get the Text of an element
	 * 
	 * @param locator
	 * @return String
	 */

	public String dogetText(By locator) {

		return getElement(locator).getText();

	}

	/**
	 * This method will help us to get the list of WebElemets
	 * 
	 * @param locator
	 * @return List<WebElement>
	 */

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);

	}
	
	
	/**
	 * This method will help us to checking the element present on the DOM of a page and visible 
	 * @param timeout
	 * @param locator
	 * @return
	 */
	

	public WebElement waitForElementVisible(int timeout, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeout);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	/**
	 * This method will help us to checking the element present on the DOM of a page and visible with polling time customization
	 * @param timeout
	 * @param locator
	 * @param pollingtime
	 * @return
	 */
	
	public WebElement waitForElementVisible(int timeout, By locator, int pollingtime) {

		WebDriverWait wait = new WebDriverWait(driver, timeout, pollingtime);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	
	
	

	/**
	 * This method will help us to pass the value using Actions class when we get
	 * ElementnotInteractable Exception
	 * 
	 * @param locator
	 * @param value
	 */

	public void doActionSendkeys(By locator, String value) {

		Actions ac = new Actions(driver);

		ac.sendKeys(getElement(locator), value).build().perform();

	}

	/**
	 * This method will help us to the click with Actions Class when we get
	 * ElementnotInteractable Exception
	 * 
	 * @param locator
	 */

	public void doActionsClick(By locator) {

		Actions ac = new Actions(driver);

		ac.click(getElement(locator)).build().perform();

	}

}
