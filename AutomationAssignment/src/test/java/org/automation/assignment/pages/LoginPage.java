package org.automation.assignment.pages;

import java.io.IOException;
import java.sql.Driver;

import org.automation.assignment.utility.Utilty;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class is used to defined methods required for login process.
 * @author Tushar
 *
 */
public class LoginPage {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUserName(String name) throws IOException {
		WebElement element = driver.findElement(By.id(Utilty.fetchElementLocatorValue("login_username_id")));
		if (element != null) {
			element.sendKeys(name);
		}
		//element.sendKeys(Keys.TAB);
	}

	public void enterPassword(String password) throws IOException {
		driver.findElement(By.id(Utilty.fetchElementLocatorValue("login_password_id"))).sendKeys(password);
	}

	public void clickSignin() throws IOException {
		driver.findElement(By.xpath(Utilty.fetchElementLocatorValue("login_signin_xpath"))).click();
		if (driver.getCurrentUrl().equalsIgnoreCase("https://www.surveymonkey.com/dashboard/")) {
			System.out.println("Login Successful....--- Passed..");
		} else {
			System.out.println("Loging Unsuccessful...Please check credentials..--- Failed..");
		}

	}
}
