package com.searchengine.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtil {

	WebDriver driver;

	public JSUtil(WebDriver driver) {

		this.driver = driver;
	}

	/**
	 * This method is intended to perform a click operation on a web element using
	 * JavaScript
	 * 
	 * @param element
	 */

	public void clickElementByJS(WebElement element) {

		JavascriptExecutor Js = (JavascriptExecutor) driver;

		Js.executeScript("arguments[0].click();", element);

	}

}
