package org.automation.assignment.base;

import java.io.IOException;

import org.automation.assignment.utility.Utilty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * This class will initialized web driver and navigate to application url.
 * @author Tushar
 *
 */
public class InstantiateDriver {
	public WebDriver driver;

	@BeforeMethod
	public void startBrowser() throws InterruptedException {
		try {
			if (Utilty.fetchProprtyValue("browserName").toString().equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (Utilty.fetchProprtyValue("browserName").toString().equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
				driver = new FirefoxDriver();
			} /*else if (Utilty.fetchProprtyValue("browserName").toString().equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
				driver = new InternetExplorerDriver();
			} */else {
				System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
				driver = new ChromeDriver();
			}
		//	driver.manage().window().maximize();
			driver.get(Utilty.fetchProprtyValue("applicationURL").toString());
			
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}

	}

	@AfterMethod
	public void closeBrowser() {
		// driver.close();
//		driver.quit();
	}

}
