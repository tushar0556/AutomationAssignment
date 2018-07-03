package org.automation.assignment.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.automation.assignment.utility.Utilty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		try {
			switch(Utilty.fetchProprtyValue("browserName")) {
			case "chrome" :
				System.setProperty("webdriver.chrome.driver", "./src/main/resources/Driver/chromedriver.exe");
				driver = new ChromeDriver();
				break;
				
			case "firefox"	:
				System.setProperty("webdriver.gecko.driver", "./src/main/resources/Driver/geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			}
			
			/*if (Utilty.fetchProprtyValue("browserName").toString().equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./src/main/resources/Driver/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (Utilty.fetchProprtyValue("browserName").toString().equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "./src/main/resources/Driver/geckodriver.exe");
				driver = new FirefoxDriver();
			} else {
				System.setProperty("webdriver.chrome.driver", "./src/main/resources/Driver/chromedriver.exe");
				driver = new ChromeDriver();
			}*/
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			driver.manage().window().maximize();
			driver.get(Utilty.fetchProprtyValue("applicationURL").toString());

		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}

	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
