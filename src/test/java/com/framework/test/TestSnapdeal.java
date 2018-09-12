package com.framework.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import com.framaework.commonutilites.BasePage;
import com.framaework.commonutilites.Data_Provider;
import com.framaework.commonutilites.ProjectSetup;
import com.framaework.commonutilites.SoftAssertions;
import com.framework.pages.Homepage;
import jxl.read.biff.BiffException;

@Listeners(com.framaework.commonutilites.Listener.class)
public class TestSnapdeal extends Data_Provider {

	WebDriver driver;
	ProjectSetup projectSetup = new ProjectSetup();
	BasePage basepage;
	Homepage homepage;

	@BeforeClass
	public void beforeTestEditProfile() throws BiffException, IOException, InterruptedException {
		projectSetup.setupEnvironment("Url");
		driver = ProjectSetup.getDriver();
		basepage = new BasePage();
		homepage = new Homepage(driver);
	}

	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {
		homepage.SignInButton().click();
		SoftAssertions.verifyEquals(homepage.SignInButton().getText(), "Sign In", "Text is Matching",
				"Text is Not Matching");
		BasePage.implicitywait(5);
		homepage.SearchProductTextField().sendKeys("Mobile");
		BasePage.implicitywait(5);
		homepage.SearchProductTextField().click();
		BasePage.implicitywait(20);
	}

	@AfterClass
	public void closeAPP() throws InterruptedException {
		driver.quit();
	}
}
