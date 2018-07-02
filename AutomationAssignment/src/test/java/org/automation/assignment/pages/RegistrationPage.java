package org.automation.assignment.pages;

import java.io.IOException;

import org.automation.assignment.utility.Utilty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to define methods required for registration process.
 * @author Tushar
 *
 */
public class RegistrationPage {

	WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUserName(String name) throws IOException {
		driver.findElement(By.id(Utilty.fetchElementLocatorValue("registration_username_id"))).sendKeys(name);
	}

	public void enterPassword(String password) throws IOException {
		driver.findElement(By.id(Utilty.fetchElementLocatorValue("registration_password_id"))).sendKeys(password);
	}

	public void enterEmail(String email) throws IOException {
		driver.findElement(By.id(Utilty.fetchElementLocatorValue("registration_email_id"))).sendKeys(email);
	}

	public void enterFirstName(String fname) throws IOException {
		driver.findElement(By.id(Utilty.fetchElementLocatorValue("registration_first_name_id"))).sendKeys(fname);
	}

	public void enterLastName(String lname) throws IOException {
		driver.findElement(By.id(Utilty.fetchElementLocatorValue("registration_last_name_id"))).sendKeys(lname);
	}

	public void clickCreateAccount() throws IOException {
		driver.findElement(By.xpath(Utilty.fetchElementLocatorValue("registration_create_account_xpath"))).click();
		if (driver.getCurrentUrl().equalsIgnoreCase("https://www.surveymonkey.com/profile/default/?joined=1")) {
			System.out.println("Registration Successful....");
		} else {
			System.out.println("Registration Unsuccessful...");
		}

	}
}
