package com.framework.test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//Need to run code FROM MultipleBrowser.xml
public class MultiBrowser {

	public WebDriver driver;

	@Parameters("browser")

	@BeforeClass

	// Passing Browser parameter from TestNG xml

	public void beforeTest(String browser) {

		// If the browser is Firefox, then do this

		if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/broswersetup/geckodriver");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/broswersetup/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

		}

		// Doesn't the browser type, lauch the Website

		driver.get("http://www.store.demoqa.com");

	}

	// Once Before method is completed, Test method will start

	@Test
	public void login() throws InterruptedException {

		driver.findElement(By.xpath(".//*[@id='account']/a")).click();

		driver.findElement(By.id("log")).sendKeys("testuser_1");

		driver.findElement(By.id("pwd")).sendKeys("Test@123");

		driver.findElement(By.id("login")).click();

	}

	@AfterClass
	public void afterTest() {

		driver.quit();

	}

}