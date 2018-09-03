package com.framework.commonfunction;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.framaework.commonutilites.ProjectSetup;
import com.google.common.base.Function;

public class BasePage extends ProjectSetup {

	public static Properties Config = null;
	public static Properties OR = null;
	public static List<WebElement> DDval = null;
	static String element1;
	static WebElement element;
	WebDriverWait wait = new WebDriverWait(driver, 3000);

	// ================= General Methods ================== //

	// Edit field
	public void webEdit(String XpathKey, String Text) {
		try {
			driver.findElement(By.xpath(OR.getProperty(XpathKey))).sendKeys(Text);
		} catch (Exception e) {
			TakeScreenShot("webEditError.png");
			System.out.println("Inside webEdit : " + e.getMessage());
		}
	}

	// Element Click
	public void elementClick(String XpathKey) {
		try {
			driver.findElement(By.xpath(OR.getProperty(XpathKey))).click();
		} catch (Exception e) {
			TakeScreenShot("elementClickError.png");
			System.out.println("Inside elementClick : " + e.getMessage());
		}
	}

	// Element Clear
	public void elementClear(String XpathKey) {
		try {
			driver.findElement(By.xpath(OR.getProperty(XpathKey))).clear();
		} catch (Exception e) {
			TakeScreenShot("elementClearError.png");
			System.out.println("Inside elementClear : " + e.getMessage());
		}
	}

	public static void elementWait(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			System.out.println("Inside elementWait : " + e.getMessage());
		}
	}

	public void TakeScreenShot(String FileName) {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/Screenshots/" + FileName));

		} catch (Exception e) {
			System.out.println("Inside TakeScreenShot : " + e.getMessage());
		}
	}

	public boolean IsElementEnabled(String XpathKey) {
		try {
			driver.findElement(By.xpath(OR.getProperty(XpathKey))).isEnabled();
		} catch (Exception e) {
			System.out.println("Inside IsElementEnabled : " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean IsElementPresent(String XpathKey) {
		try {
			driver.findElement(By.xpath(OR.getProperty(XpathKey)));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String WebElementText(String XpathKey) {
		try {
			String Text = driver.findElement(By.xpath(OR.getProperty(XpathKey))).getText();
			return Text;
		} catch (Exception e) {
			System.out.println("Inside TakeScreenShot : " + e.getMessage());
		}
		return null;
	}

	// ================= AUT Methods ================== //

	public String verifyWebEdit(String XpathKey) {
		String currval;
		try {
			WebElement val = driver.findElement(By.xpath(OR.getProperty(XpathKey)));
			currval = val.getAttribute("value");
			return currval;
		} catch (Exception e) {
			System.out.println("Not able to take value form the webEdit : " + e.getMessage());
		}
		return null;
	}

	public void verifyEditBox(String insertedval, String XpathKey, String errorName) {
		try {
			Thread.sleep(2000L);
			String actualVal = verifyWebEdit(XpathKey);
			System.out.println("Value of the web edit is : " + actualVal);
			if (!actualVal.equals(insertedval)) {
				TakeScreenShot(errorName);
			}
			Assert.assertEquals(actualVal, insertedval);
		} catch (Exception e) {
			System.out.println("WebEdit : Actual Value ! Expected value : " + e.getMessage());
		}
	}

	public void dropDownSelection(String XpathKey, String ddval) {
		try {
			DDval = driver.findElements(By.xpath(OR.getProperty(XpathKey)));
			for (int i = 0; i < DDval.size(); i++) {
				if (DDval.get(i).getText().equals(ddval)) {
					DDval.get(i).click();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("DropDown : Not able to select : " + e.getMessage());
		}
	}

	// For File Uploading

	public void FileUpload(String XpathKey, String RelativeLocOfTheFile) {
		try {
			driver.findElement(By.xpath(OR.getProperty(XpathKey))).sendKeys(RelativeLocOfTheFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// manual qc all checkbox

	public void clickcheckBoxes(String xpathkey) {
		try {
			List<WebElement> checkboxes = driver.findElements(By.xpath(OR.getProperty(xpathkey)));
			System.out.println(checkboxes.size());
			for (int i = 0; i < checkboxes.size(); i++) {
				checkboxes.get(i).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// section from drop down
	public void selectionfromdropdown(String XpathKey, String VisibleText) {
		try {
			Select selectByIndex = new Select(driver.findElement(By.xpath(OR.getProperty(XpathKey))));
			selectByIndex.selectByVisibleText(VisibleText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void DynamicElementClick(String clicktype, int clickIndex) {
		try {
			if (clicktype.equals("Verify")) {
				List<WebElement> allVerify = driver.findElements(By.xpath("//*[starts-with(@id,'show_verify_')]"));
				for (int i = 0; i < allVerify.size(); i++) {
					allVerify.get(clickIndex).click();
				}
			} else if (clicktype.equals("Accept")) {
				List<WebElement> allAccept = driver.findElements(By.xpath("//*[contains(@id,'accept_')]"));

				for (int i = 0; i < allAccept.size(); i++) {
					allAccept.get(clickIndex).click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void EnteringFrameHandling(String FrameID) {

		driver.switchTo().frame(driver.findElement(By.id(OR.getProperty(FrameID))));

	}

	public void OutOFrameHandling() {

		driver.switchTo().defaultContent();
	}

	public void Scroll(String PageUp, String PageDown) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(PageUp,PageDown)", "");
	}

	// Loan ID code

	public String loanID() {
		String loan_ID = WebElementText("loan_ID");
		return loan_ID;
	}

	// click on tab
	public void doubleClick(String xpathkey) throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath(OR.getProperty(xpathkey)));

		Thread.sleep(50000);

		Actions action = new Actions(driver);
		// action.doubleClick(ele);
		// action.perform();
		action.moveToElement(ele).doubleClick().build().perform();

	}

	// To Reload/Refresh The Page
	public void refresh() throws InterruptedException {
		driver.navigate().refresh();
	}

	// wait till click
	public void clickable(String xpathkey) {

		WebDriverWait wait = new WebDriverWait(driver, 2000);
		WebElement Button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(xpathkey))));
		Button.click();
	}

	// To close the borrower
	public void close_browser() {
		try {
			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void action_webedit(String xpathkey, String value) {
		WebElement ele = driver.findElement(By.xpath(OR.getProperty(xpathkey)));
		ele.sendKeys(value);
	}

	// Dropdown selection by Index
	public void select_dropdown_by_index(String xpathkey) {
		WebElement by_index = driver.findElement(By.xpath(OR.getProperty(xpathkey)));
		Select sel = new Select(by_index);
		Random rand = new Random();
		int x = rand.nextInt(1000);
		if (x == 0) {
			x = x + 1;
		} else {
			x = x;
		}
		sel.selectByIndex(x);
	}

	// download pdf file directly
	public void direct_pdf_download(String XpathKey) {
		driver.findElement(By.xpath(OR.getProperty(XpathKey))).click();
	}

	public void element_click_action(String xpathkey) {
		WebElement element = driver.findElement(By.xpath(OR.getProperty(xpathkey)));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	public int counting(String xpathkey) {
		List<WebElement> checkboxes = driver.findElements(By.xpath(xpathkey));
		int count = checkboxes.size();
		System.out.println(count);
		return count;
	}

	// Page scrolling
	public void scrolloing() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");

	}

	public void AlertAccept() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Wait Elements Webelememt
	public WebElement getElementeExplicit(By by) {
		element = null;
		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(by));

		} catch (Exception e) {
			Reporter.log("Fail: Web Element Not Found");
		}
		return element;
	}

	// ImplicityWait

	public static void implicitywait(int time) {
		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Wait
	public static WebElement getElementfluent(final By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(1, TimeUnit.MINUTES)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});
		return element;
	}

}


