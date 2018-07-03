package org.automation.assignment.testcases;

import org.automation.assignment.pages.RegistrationPage;
import org.testng.annotations.Test;

/**
 * Test case for registration process.
 * 
 * @author Tushar
 *
 */
public class TC_002_RegistrationFunction extends RegistrationPage {

	@Test(dataProvider = "Excel")
	public void tc_002_registration_functionality(String uname, String pass, String email, String fname, String lname) {
		RegistrationPage.checkSignUpButton();
		try {
			RegistrationPage.enterUserName(uname);
			RegistrationPage.enterPassword(pass);
			RegistrationPage.enterEmail(email);
			RegistrationPage.enterFirstName(fname);
			RegistrationPage.enterLastName(lname);
			RegistrationPage.clickCreateAccount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
