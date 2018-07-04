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
 * This class is used to defined methods required for login process.
 * 
 * @author Tushar
 *
 */
public class LoginPage extends InstantiateDriver {

	@FindBy(id = "username")
	static WebElement usernameTextbox;
	@FindBy(id = "password")
	static WebElement passwordTextbox;
	@FindBy(xpath = "//button[@type='submit']")
	static WebElement signInButton;

	private XSSFWorkbook workbook;

	public static void enterUserName(String name) {
		usernameTextbox.sendKeys(name);
	}

	public static void enterPassword(String password) {
		passwordTextbox.sendKeys(password);
	}

	public static void clickSignin() {
		signInButton.click();
		Assert.assertEquals(InstantiateDriver.driver.getCurrentUrl(), "https://www.surveymonkey.com/dashboard/",
				"Login Unsuccessful, Please check credentials...");
	}

	public static void checkexistance() {
		Assert.assertEquals(InstantiateDriver.driver.getCurrentUrl(), "https://www.surveymonkey.com/", "Check Url..");

		// Checked whether sign in button exist and enable or not

		boolean signIn = InstantiateDriver.driver
				.findElement(By.xpath("//a[@href='https://www.surveymonkey.com/user/sign-in/']")).isEnabled();
		if (signIn) {
			InstantiateDriver.driver.findElement(By.xpath("//a[@href='https://www.surveymonkey.com/user/sign-in/']"))
					.click();
		}
		Assert.assertEquals(InstantiateDriver.driver.getCurrentUrl(), "https://www.surveymonkey.com/user/sign-in/",
				"Check Sign in Url..");
	}

	/**
	 * Read data that is username and password from excel file to provide to
	 * test case Update test case with data driven : dynamic data from excel
	 * 
	 * @return TestData [][]
	 * @throws IOException
	 */
	@DataProvider(name = "Excel")
	public Object[][] testDataGenerator() throws IOException {
		FileInputStream fileInputStream = new FileInputStream("./src/main/resources/TestData/Sample.xlsx");
		workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int noOfData = sheet.getPhysicalNumberOfRows();
		Object testData[][] = new Object[noOfData][2];

		for (int i = 0; i < noOfData; i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell userName = row.getCell(0);
			XSSFCell password = row.getCell(1);
			testData[i][0] = userName.getStringCellValue();
			testData[i][1] = password.getStringCellValue();
		}
		return testData;
	}
}
