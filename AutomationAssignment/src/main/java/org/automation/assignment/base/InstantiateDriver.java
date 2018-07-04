package org.automation.assignment.base;

import java.util.concurrent.TimeUnit;

import org.automation.assignment.utility.Utilty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * This class will initialized web driver and navigate to application url.
 * 
 * @author Tushar
 *
 */
public class InstantiateDriver {
	public static WebDriver driver;

	@BeforeMethod
	public void startBrowser() throws InterruptedException {
		switch (Utilty.fetchProprtyValue("browserName")) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/Driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/Driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		case "ie":
			System.setProperty("webdriver.ie.driver", "./src/main/resources/Driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;

		case "Default":
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/Driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(Utilty.fetchProprtyValue("applicationURL").toString());

	}

	@AfterMethod
	public void closeBrowser() {
		// driver.quit();
	}

}
