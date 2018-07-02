package org.automation.assignment.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.automation.assignment.assertions.Compare;
import org.automation.assignment.base.InstantiateDriver;
import org.automation.assignment.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test case for Login Functionality.
 * 
 * @author Tushar
 *
 */
public class TC_001_ValidateLoginFunctionality extends InstantiateDriver {

	private XSSFWorkbook workbook;

	@Test(dataProvider = "Excel")
	public void tc_001_login_functionality(String uname, String pass) throws IOException {
		Assert.assertTrue(Compare.validatePageURL(driver, "https://www.surveymonkey.com/"), "Check Url..");

		// Checked whether sign in button exist and enable or not
		Assert.assertTrue(
				Compare.validateElementExists(driver, "//a[@href='https://www.surveymonkey.com/user/sign-in/']"),
				"Sign in button not exists..");
		boolean signIn = driver.findElement(By.xpath("//a[@href='https://www.surveymonkey.com/user/sign-in/']"))
				.isEnabled();
		if (signIn) {
			driver.findElement(By.xpath("//a[@href='https://www.surveymonkey.com/user/sign-in/']")).click();
		}
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserName(uname);
		loginPage.enterPassword(pass);
		loginPage.clickSignin();
	}

	/*
	 * @DataProvider(name="Static") public Object[][] testDataGenerator() {
	 * Object [][] data =
	 * {{"uname1","pass1"},{"uname2","pass2"},{"uname3","pass3"}}; return data;
	 * }
	 */

	/**
	 * Read data that is username and password from excel file to provide to
	 * test case Update test case with data driven : dynamic data from excel
	 * 
	 * @return TestData [][]
	 * @throws IOException
	 */
	@DataProvider(name = "Excel")
	public Object[][] testDataGenerator() throws IOException {
		FileInputStream fileInputStream = new FileInputStream("./TestData/Sample.xlsx");
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
