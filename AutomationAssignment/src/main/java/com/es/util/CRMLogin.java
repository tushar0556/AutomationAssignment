package com.es.util;

import java.security.Key;

import org.automation.assignment.base.InstantiateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CRMLogin {
	private static WebDriver driver;
	public static String crmPortalUrl = "https://esuat.iccsafe.org/";

	public static void crmLogin(String userName, String Password) {
		try {
			driver = InstantiateDriver.startBrowser();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get(crmPortalUrl);
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_UsernameTextBox']")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_PasswordTextBox']")).sendKeys(Password);
		driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceHolder1_SubmitButton']")).click();
		System.out.println("Logged in Successfully in CRM Portal.");
		try {
			driver.switchTo().frame("InlineDialog_Iframe");
			driver.findElement(By.xpath("//*[@id='dontShowAgainFirst']")).click();
			driver.findElement(By.xpath("//*[text()='No, thanks']")).click();
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void crmLogout(){
		try {
			driver.findElement(By.xpath("//*[text()='ES Portal']")).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='navTabButtonUserInfoSignOutId']")).click();
			System.out.println("Logout From CRM.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void qualifyLeadInCRM(String name, String email) {
		// String findLead = "//*[text()='"+name+"']";
		try {
			String findLead = "//*[text()='" + email + "']";
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[text()='Microsoft Dynamics CRM']"))).perform();
			Thread.sleep(10000);
			act.moveToElement(driver.findElement(By.xpath("//*[@id='SFA' and @title='Sales']"))).click().build()
					.perform();

			driver.switchTo().frame(0);
			Thread.sleep(10000);
			WebElement filterEle = driver.findElement(By.xpath("//*[@id='filterButtonImage']"));
			JavascriptExecutor jsfilter = (JavascriptExecutor) driver;
			jsfilter.executeScript("arguments[0].click();", filterEle);
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='datetimeFilterPopupcrmGridleadcreatedon']/div/a/div")).click();;
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[text()='Sort Newest to Oldest']")).click();
			Thread.sleep(10000);
//			driver.findElement(By.xpath("//*[@id='grid-filter-OkbuttondatetimeFilterPopupcrmGridleadcreatedon']")).click();
			
			
			WebElement element = driver.findElement(By.xpath("//*[@id='crmGrid_findCriteria']"));
			element.sendKeys(name);
			element.sendKeys(Keys.RETURN);
			Thread.sleep(10000);
			WebElement ele = driver.findElement(By.xpath(findLead));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ele);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver.switchTo().defaultContent();
		}
		driver.findElement(By.xpath("//*[text()=' Qualify ']")).click();

	}

	public static void qualifyLeadInCRMByAdvancedFind(String name, String email) {
		try {
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[text()='Microsoft Dynamics CRM']"))).perform();
			Thread.sleep(10000);
			act.moveToElement(driver.findElement(By.xpath("//*[@id='SFA' and @title='Sales']"))).click().build()
					.perform();
			Thread.sleep(20000);
			driver.findElement(By.xpath("//*[text()=' Advanced Find ']")).click();
			Thread.sleep(10000);
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
			driver.findElement(By.xpath("//*[@id='Mscrm.AdvancedFind.Groups.View.New-Large']/span/span/img")).click();
			Thread.sleep(20000);
			driver.switchTo().frame(0);
			Select menu = new Select(driver.findElement(By.xpath("//*[@class='ms-crm-SelectBox']")));
			menu.selectByVisibleText("Email");
			driver.findElement(By.xpath("//*[@id='advFindEFGRP0FFLD0CCVALCTL']")).sendKeys(email);
			driver.findElement(By.xpath("//*[@id='Mscrm.AdvancedFind.Groups.View.Save-Large']/span/span/img")).click();
			
			act.moveToElement(driver.findElement(By.xpath("//*[@class='ms-crm-SelectBox' or @id='advFindE_fieldListFLDCTL']"))).click().build().perform();
			Thread.sleep(20000);
			driver.findElement(By.xpath("//*[@class='ms-crm-SelectBox']/*[@id='fld']/option[@value='emailaddress1']")).click();
			Thread.sleep(20000);
			driver.findElement(By.xpath("//*[@id='advFindEFGRP0FFLD0CCVALCTL']")).sendKeys(email);
			driver.findElement(By.xpath("//*[@id='Mscrm.AdvancedFind.Groups.View.Save-Large']/span/span/img")).click();
			driver.switchTo().defaultContent();
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
