package org.automation.assignment.pages;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.automation.assignment.base.InstantiateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

/**
 * This class is used to define methods required for registration process.
 * 
 * @author Tushar
 *
 */
public class RegistrationPage extends InstantiateDriver {

	@FindBy(id = "username")
	static WebElement usernameTextbox;
	@FindBy(id = "password")
	static WebElement passwordTextbox;
	@FindBy(id = "email")
	static WebElement emailTextbox;
	@FindBy(id = "first_name")
	static WebElement firstNameTextbox;
	@FindBy(id = "last_name")
	static WebElement lastNameTextbox;
	@FindBy(xpath = "//button[@id='submitform']")
	static WebElement createAccountButton;

	private XSSFWorkbook workbook;

	public static void enterUserName(String name) {
		usernameTextbox.sendKeys(name);
	}

	public static void enterPassword(String password) {
		passwordTextbox.sendKeys(password);
	}

	public static void enterEmail(String email) {
		emailTextbox.sendKeys(email);
	}

	public static void enterFirstName(String fname) {
		firstNameTextbox.sendKeys(fname);
	}

	public static void enterLastName(String lname) {
		lastNameTextbox.sendKeys(lname);
	}

	public static void clickCreateAccount() {
		createAccountButton.click();
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
