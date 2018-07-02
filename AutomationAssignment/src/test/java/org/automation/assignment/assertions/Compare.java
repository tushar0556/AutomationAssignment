package org.automation.assignment.assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Compare {

	public static boolean validatePageURL(WebDriver driver, String expUrl) {

		boolean flag = false;
		if (driver.getCurrentUrl().equalsIgnoreCase(expUrl)) {
			flag = true;
		}

		return flag;
	}

	public static boolean validatePageTitle(WebDriver driver, String expTitle) {

		boolean flag = false;
		if (driver.getTitle().equalsIgnoreCase(expTitle)) {
			flag = true;
		}

		return flag;
	}

	public static boolean validateElementExists(WebDriver driver, String xpath) {
		boolean flag = false;
		try {
			driver.findElement(By.xpath(xpath));
			flag = true;
		} catch (Exception e) {
			System.err.println(e);
		}
		return flag;
	}
}
