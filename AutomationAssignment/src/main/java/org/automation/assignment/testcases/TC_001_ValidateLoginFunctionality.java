package org.automation.assignment.testcases;

import org.automation.assignment.pages.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Test case for Login Functionality.
 * 
 * @author Tushar
 *
 */
public class TC_001_ValidateLoginFunctionality extends LoginPage {

	@Test(dataProvider = "Excel")
	public void tc_001_login_functionality(String uname, String pass) {
		PageFactory.initElements(driver, LoginPage.class);
		
		LoginPage.checkexistance();
		LoginPage.enterUserName(uname);
		LoginPage.enterPassword(pass);
		LoginPage.clickSignin();
	}

}
