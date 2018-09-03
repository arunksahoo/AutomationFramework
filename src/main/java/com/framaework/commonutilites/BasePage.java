package com.framaework.commonutilites;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class BasePage extends ProjectSetup {

	public static Properties Config = null;

	// ================= General Methods ================== //

	// Scroll Page
	public void Scroll(String PageUp, String PageDown) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(PageUp,PageDown)", "");
	}

	// To Reload/Refresh The Page
	public void refresh() throws InterruptedException {
		driver.navigate().refresh();
	}


	// To close the browser
	public void close_browser() {
		try {
			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Page scrolling
	public void scrolloing() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");

	}

	// ImplicityWait

	public static void implicitywait(int time) {
		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// Fluent Wait to read element
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
