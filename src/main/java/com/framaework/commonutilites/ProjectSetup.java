package com.framaework.commonutilites;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class ProjectSetup {

	public static WebDriver driver;
	public String browser;
	GenericsMethods genaricsmethods = new GenericsMethods();

	// Method: Setup environment
	@SuppressWarnings({ "unused", "deprecation" })
	@BeforeSuite
	@Parameters("browser")
	public void setupEnvironment(String Url) throws IOException, InterruptedException {
		String URL = null;

		// From excel you can handle which browser you want to run

		if (GenericsMethods.ConfigFile("browser").equalsIgnoreCase("FIREFOX")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/broswersetup/geckodriver");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			openUrl(Url);
		} else if (GenericsMethods.ConfigFile("browser").equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/broswersetup/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			openUrl(Url);
			System.out.println("Browser - Chrome.");

		} else if (GenericsMethods.ConfigFile("browser").equals("IEEXPLORE")) {
			driver = new InternetExplorerDriver();
			System.out.println("Browser - IE.");
			ClearBrowserCache();
		}

	}

	// TO OPEN ANY URL RELATED TO APPLICATION

	public void openUrl(String Url) {
		try {

			driver.get(GenericsMethods.ConfigFile(Url));
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println("Not able to Open Base Url : " + e.getMessage());
		}
	}
	// TO OPEN ANY URL RELATED TO APPLICATION

	public void openUrl1(String Url) {
		try {

			driver.get(Url);
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Not able to Open Base Url : " + e.getMessage());
		}
	}

	// Method: Return driver object
	// Driver object return
	public static WebDriver getDriver() {
		return driver;
	}

	public void ClearBrowserCache() {
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

	}

	public void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 1000000);
		wait.until(pageLoadCondition);
	}

	public void waitForPageLoaded() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 1000000);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#login-dialog")));
		} catch (Throwable error) {
			error.printStackTrace();
			System.out.println("LOP");
		}
	}
}
