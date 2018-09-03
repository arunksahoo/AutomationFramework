package com.framework.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.framaework.commonutilites.BasePage;
import com.framaework.commonutilites.Data_Provider;
import com.framaework.commonutilites.ExcelReaderExpected;
import com.framaework.commonutilites.GenericsMethods;
import com.framaework.commonutilites.ProjectSetup;
import com.framaework.commonutilites.SoftAssertions;
import com.framework.pages.Homepage;

import jxl.read.biff.BiffException;

public class TestAmzon extends Data_Provider {

	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	GenericsMethods genericMethods;
	JavascriptExecutor jse;
	String orderIDFromApplication;
	BasePage basepage;
	String jsonData;
	JSONObject jobj ;
	Homepage homepage;
	

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {

		projectSetup.setupEnvironment("Url");
		driver = ProjectSetup.getDriver();
		genericMethods = new GenericsMethods();
		ExcelReaderExpected.connectExcel();
		jse = (JavascriptExecutor) driver;
		basepage = new BasePage();
		homepage = new Homepage(driver);
		
		
	}

	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {
		homepage.SignInButton().click();
		SoftAssertions.verifyEquals(homepage.SignInButton().getText(), "Sign In", "Text is Matching", "Text is Not Matching");
		basepage.implicitywait(5);
		
	}
	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
