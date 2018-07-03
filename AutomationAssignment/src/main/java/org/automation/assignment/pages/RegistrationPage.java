package org.automation.assignment.pages;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.automation.assignment.base.InstantiateDriver;
import org.automation.assignment.utility.Utilty;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

/**
 * This class is used to define methods required for registration process.
 * 
 * @author Tushar
 *
 */
public class RegistrationPage extends InstantiateDriver {

	private XSSFWorkbook workbook;

	public static void enterUserName(String name) {
		try {
			InstantiateDriver.driver.findElement(By.id(Utilty.fetchElementLocatorValue("registration_username_id")))
					.sendKeys(name);
		} catch (IOException e) {
			System.err.println("Error while entering Username for registration" + e);
			e.printStackTrace();
		}
	}

	public static void enterPassword(String password) {
		try {
			InstantiateDriver.driver.findElement(By.id(Utilty.fetchElementLocatorValue("registration_password_id")))
					.sendKeys(password);
		} catch (IOException e) {
			System.err.println("Error while entering password for registraion" + e);
			e.printStackTrace();
		}
	}

	public static void enterEmail(String email) {
		try {
			InstantiateDriver.driver.findElement(By.id(Utilty.fetchElementLocatorValue("registration_email_id")))
					.sendKeys(email);
		} catch (IOException e) {
			System.err.println("Error for Registraion while entering email" + e);
			e.printStackTrace();
		}
	}

	public static void enterFirstName(String fname) {
		try {
			InstantiateDriver.driver.findElement(By.id(Utilty.fetchElementLocatorValue("registration_first_name_id")))
					.sendKeys(fname);
		} catch (IOException e) {
			System.err.println("Error for Registraion while entering first name" + e);
			e.printStackTrace();
		}
	}

	public static void enterLastName(String lname) {
		try {
			InstantiateDriver.driver.findElement(By.id(Utilty.fetchElementLocatorValue("registration_last_name_id")))
					.sendKeys(lname);
		} catch (IOException e) {
			System.err.println("Error for Registraion while entering last name" + e);
			e.printStackTrace();
		}
	}

	public static void clickCreateAccount() {
		try {
			InstantiateDriver.driver
					.findElement(By.xpath(Utilty.fetchElementLocatorValue("registration_create_account_xpath")))
					.click();
		} catch (IOException e) {
			System.err.println("Error for Registraion" + e);
			e.printStackTrace();
		}
		Assert.assertEquals(InstantiateDriver.driver.getCurrentUrl(),
				"https://www.surveymonkey.com/profile/default/?joined=1", "Registration Unsuccessful...");
	}

	public static void checkSignUpButton() {
		boolean signUp = InstantiateDriver.driver
				.findElement(By.xpath("//a[@href='https://www.surveymonkey.com/user/sign-up/']")).isEnabled();
		
		if (signUp) {
			InstantiateDriver.driver.findElement(By.xpath("//a[@href='https://www.surveymonkey.com/user/sign-up/']"))
					.click();
		}
		
		Assert.assertEquals(InstantiateDriver.driver.getCurrentUrl(), "https://www.surveymonkey.com/user/sign-up/",
				"Sign Up button not exists..");

	}

	/**
	 * Read the data require for registration from excel file.
	 * 
	 * @return testData
	 * @throws IOException
	 */
	@DataProvider(name = "Excel")
	public Object[][] testDataGenerator() throws IOException {
		FileInputStream fileInputStream = new FileInputStream("./src/main/resources/TestData/Sample.xlsx");
		workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheet("Sheet2");
		int noOfData = sheet.getPhysicalNumberOfRows();
		Object testData[][] = new Object[noOfData][5];

		for (int i = 0; i < noOfData; i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell userName = row.getCell(0);
			XSSFCell password = row.getCell(1);
			XSSFCell email = row.getCell(2);
			XSSFCell fname = row.getCell(3);
			XSSFCell lname = row.getCell(4);
			testData[i][0] = userName.getStringCellValue();
			testData[i][1] = password.getStringCellValue();
			testData[i][2] = email.getStringCellValue();
			testData[i][3] = fname.getStringCellValue();
			testData[i][4] = lname.getStringCellValue();
		}
		return testData;
	}
}
