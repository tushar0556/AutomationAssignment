package org.automation.assignment.testcases;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.automation.assignment.assertions.Compare;
import org.automation.assignment.base.InstantiateDriver;
import org.automation.assignment.pages.RegistrationPage;

/**
 * Test case for registration process.
 * 
 * @author Tushar
 *
 */
public class TC_002_RegistrationFunction extends InstantiateDriver {

	private XSSFWorkbook workbook;

	@Test(dataProvider = "Excel")
	public void tc_002_registration_functionality(String uname, String pass, String email, String fname, String lname)
			throws IOException {
		//driver.manage().window().maximize();
		Assert.assertTrue(Compare.validateElementExists(driver, "//a[@href='https://www.surveymonkey.com/user/sign-up/']"), "Sign Up button not exists..");
		driver.findElement(By.xpath("//a[@href='https://www.surveymonkey.com/user/sign-up/']")).click();
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.enterUserName(uname);
		registrationPage.enterPassword(pass);
		registrationPage.enterEmail(email);
		registrationPage.enterFirstName(fname);
		registrationPage.enterLastName(lname);
		registrationPage.clickCreateAccount();
	}

	/**
	 * Read the data require for registration from excel file. 
	 * @return testData
	 * @throws IOException
	 */
	@DataProvider(name = "Excel")
	public Object[][] testDataGenerator() throws IOException {
		FileInputStream fileInputStream = new FileInputStream("./TestData/Sample.xlsx");
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
