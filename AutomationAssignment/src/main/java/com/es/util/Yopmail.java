package com.es.util;

import java.util.concurrent.TimeUnit;

import org.automation.assignment.base.InstantiateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Yopmail extends InstantiateDriver {

	private static String otp;
	private static String mail = "iasinfobeans";
	private static WebDriver driver;

	public static String getOTP() throws InterruptedException {
		driver = InstantiateDriver.startBrowser();
		driver.get("http://www.yopmail.com");
		driver.findElement(By.id("login")).sendKeys(mail);
		driver.findElement(By.className("sbut")).click();
		driver.findElement(By.className("slientext")).click();
		driver.switchTo().frame("ifinbox");
		// driver.switchTo().frame((WebElement)
		// By.xpath("//*[@id='ifinbox']"));//lcoatio

		//WebElement emailSelection = driver.findElement(By.xpath("//span[text()='Evaluation Services Support']"));
		//WebElement emailSelection = driver.findElement(By.xpath("//*[text()='ICC-ES: One Time Password (OTP)']"));
	//	WebElement emailSelection = driver.findElement(By.xpath("//*[text()='ICC-ES: Account Registration Initiated']"));
		WebElement emailSelection = null;
		
		try {
			//emailSelection = driver.findElement(By.xpath("//*[text()='ICC-ES: New Account Registration']"));
		//	emailSelection = driver.findElement(By.xpath("//*[text()='ICC-ES: Account Registration Approved']"));
		//	emailSelection = driver.findElement(By.xpath("//*[contains(text(),'ICC-ES: Quotation Raised By')]"));
			emailSelection = driver.findElement(By.xpath("//*[contains(text(),'ICC-ES: Your Request For Profile Update has been')]"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Seraching email Subject on second page.");
			driver.findElement(By.xpath("//a[@class='igif next']")).click();
			//emailSelection = driver.findElement(By.xpath("//*[text()='ICC-ES: Account Registration Approved']"));
			//emailSelection = driver.findElement(By.xpath("//*[contains(text(),'ICC-ES: Quotation Raised By')]"));
			emailSelection = driver.findElement(By.xpath("//*[contains(text(),'ICC-ES: Your Request For Profile Update has been')]"));
		}
	//	WebElement emailSelection = driver.findElement(By.xpath("//*[text()='ICC-ES: Password Reset']"));
	//	WebElement emailSelection = driver.findElement(By.xpath("//*[text()='ICC-ES: Request For Profile Update']"));
	//	WebElement emailSelection = driver.findElement(By.xpath("//*[contains(text(),'ICC-ES: Request For Profile Update by')]"));
		emailSelection.click();
		driver.switchTo().defaultContent();
		/*driver.switchTo().frame("ifmail");

		WebElement element = driver.findElement(By.xpath("//div[@id='mailmillieu']//p/strong"));
		// div[@id='mailmillieu']/div[2]/p[2]/strong
		otp = element.getText();

		driver.switchTo().defaultContent();*/

		return otp;

	}

	public static void verifyOTPEmailBody() {
		driver.switchTo().frame("ifmail");
		String hiText = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
//		driver.findElement(By.xpath("//*[contains(text(),'Your one time password is: ')]"));
		String otpFirstLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Your one time password is: ')]")).getText();
		String otpThirdLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'For security reasons please do not share your one time password with any one.')]")).getText();
		String otpFourthLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		Assert.assertEquals(hiText, "Hi,", "Hi is not contain in email body");
		Assert.assertEquals(otpFirstLine.contains("Your one time password is: "), true,
				"Text 'Your one time password is: ' is not contain in email body");
		Assert.assertEquals(otpFirstLine.contains(" and would automatically expire in 5 minutes"), true,
				"Text ' and would automatically expire in 5 minutes' is not contain in email body");
		Assert.assertEquals(otpThirdLine,
				"For security reasons please do not share your one time password with any one.",
				"Text 'For security reasons please do not share your one time password with any one.' is not contain in email body");
		Assert.assertEquals(otpFourthLine.contains("Thank you,"), true,
				"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(otpFourthLine.contains("ICC Evaluation Service, LLC"), true,
				"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();

	}
	
	public static void verifyRegistrationMailBody() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you for your interest')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Your account details')]")).getText();
		String thirdRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),' Your registration request')]")).getText();
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine,"Thank you for your interest in ICC Evaluation Service, LLC.","Text 'Thank you for your interest in ICC Evaluation Service, LLC.' is not contain in email body");
		Assert.assertEquals(secondRgistrationLine,"Your account details are being reviewed by a member of our staff and more features would be available on to the portal once your account has been approved.","Text 'Your account details are being reviewed by a member of our staff and more features would be available on to the portal once your account has been approved.' is not contain in email body");
		Assert.assertEquals(thirdRgistrationLine,"Note: Your registration request will be reviewed within one business day.","Text 'Note: Your registration request will be reviewed within one business day.' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
	}

	public static void verifyNewAccRegMailBody() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'A new account has been registered')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Kindly log into the CRM')]")).getText();
	//	String thirdRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),' Your registration request')]")).getText();
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine.contains("A new account has been registered by"),true,"Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine,"Kindly log into the CRM to view and qualify the lead for approving account registration.","Text is not contain in email body");
		//Assert.assertEquals(thirdRgistrationLine,"Note: Your registration request will be reviewed within one business day.","Text 'Note: Your registration request will be reviewed within one business day.' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
	}
	
	public static void verifyPasswordMailBody() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'request to reset your password')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Please')]")).getText();
		String thirdRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'If you did not make this request')]")).getText();
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		WebElement passResetLink = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Please')]/a"));
		System.out.println(passResetLink.getAttribute("href"));
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine,"We have received a request to reset your password on the ICC-ES Customer Portal.","Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine,"Please click here to reset your password, and log into your ICC-ES account.","Text is not contain in email body");
		Assert.assertEquals(thirdRgistrationLine,"If you did not make this request, please ignore this e-mail.","Text 'If you did not make this request, please ignore this e-mail.' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
		
	}
	
	public static void verifyReqForProfileUpdate() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'update the profile has')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'representative will review the changes')]")).getText();
		String thirdRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'begin to reflect post approval')]")).getText();
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine.contains("Your request to update the profile has been submitted successfully"),true,"Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine.contains("An ICC-ES staff representative will review the changes and get in touch with you."),true,"Text is not contain in email body");
		Assert.assertEquals(thirdRgistrationLine.contains("Your changes will only begin to reflect post approval by ICC-ES team."),true,"Text is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
	}
	
	public static void verifyProfileUpdatedMailBody() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Profile has been updated by')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'To review and approve/reject the request, please ')]")).getText();
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine.contains("Profile has been updated by"),true,"Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine.contains("To review and approve/reject the request, please "),true,"Text is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
	}
	
	public static void verifyNewAccRegBody() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'A new account has been registered by email id:')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Kindly log into the CRM to view and qualify')]")).getText();
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine.contains("A new account has been registered by email id:"),true,"Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine.contains("Kindly log into the CRM to view and qualify"),true,"Text is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
	}
	
	public static void verifyAccRegAprrovedBody() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Your account has been approved by ICC Evaluation Service, LLC')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'More features have been added to your account on the portal.')]")).getText();
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine.contains("Your account has been approved by ICC Evaluation Service, LLC"),true,"Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine.contains("More features have been added to your account on the portal."),true,"Text is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
	}
	
	public static WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				element);
		return ele;
	}

	public static void verifyPMGApplication() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Your PMG application has been submitted')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'The application ID for the same is')]")).getText();
		String thirdRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Once all the signatures have been received,')]")).getText();
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine.contains("Your PMG application has been submitted"),true,"Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine.contains("The application ID for the same is"),true,"Text is not contain in email body");
		Assert.assertEquals(thirdRgistrationLine.contains("Once all the signatures have been received,"),true,"Text is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
		
	}
	
	public static void verifyESRApplication() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Your ESR application has been submitted successfully')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'The application ID for the same is')]")).getText();
		String thirdRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Once all the signatures have been received,')]")).getText();
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine.contains("Your ESR application has been submitted successfully"),true,"Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine.contains("The application ID for the same is"),true,"Text is not contain in email body");
		Assert.assertEquals(thirdRgistrationLine.contains("Once all the signatures have been received,"),true,"Text is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
	}
	
	public static void verifyQuatSubByCustomer() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Your quotation request')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'An ICC-ES staff representative is reviewing your request')]")).getText();
		
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine.contains("Your quotation request"),true,"Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine.contains("An ICC-ES staff representative is reviewing your request"),true,"Text is not contain in email body");
		
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
	}
	
	public static void verifyQuatRecByStaff() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'A new quotation request QOT')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'To view details please ')]")).getText();
	
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine.contains("A new quotation request QOT"),true,"Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine.contains("To view details please "),true,"Text is not contain in email body");
		
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
	}
	
	public static void verifyApprovedOrDeclineProfileChanges() {
		driver.switchTo().frame("ifmail");
		String hi = driver.findElement(By.xpath("//*[@id='mailmillieu']//p")).getText();
		String firstRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Your request to update profile information has been')]")).getText();
		String secondRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'To review your profile, please ')]")).getText();
		String thirdRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'In case you have any questions, please get in touch with the ')]")).getText();
		String forthRgistrationLine = driver.findElement(By.xpath("//*[@id='mailmillieu']//p[contains(text(),'Thank you,')]")).getText();
		Assert.assertEquals(hi.contains("Hi"),true,"Hi is not contain in email body");
		Assert.assertEquals(firstRgistrationLine.contains("Your request to update profile information has been"),true,"Text is not contain in email body");
		Assert.assertEquals(secondRgistrationLine.contains("To review your profile, please "),true,"Text is not contain in email body");
		Assert.assertEquals(thirdRgistrationLine.contains("In case you have any questions, please get in touch with the "),true,"Text is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("Thank you,"),true,"Text 'Thank you,' is not contain in email body");
		Assert.assertEquals(forthRgistrationLine.contains("ICC Evaluation Service, LLC"),true,"Text 'ICC Evaluation Service, LLC' is not contain in email body");
		driver.switchTo().defaultContent();
	}
}
