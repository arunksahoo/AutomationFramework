package com.framework.pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.framaework.commonutilites.BasePage;
import com.framaework.commonutilites.JSONReader;

public class Homepage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static String jsonfile;
	JSONReader getValue;

	public Homepage(WebDriver driver) {
		Homepage.driver = driver;
		jsonfile = System.getProperty("user.dir") + "/data_source/HomePage.json";
		getValue = new JSONReader();
	}

	// Click On SignButton 
		public WebElement SignInButton() throws IOException {
			return getElementfluent(By.cssSelector(getValue.readFile(jsonfile,"Homepage_SignIn_Button")));

		}
	// Search for product.
	public WebElement SearchProductTextField() throws IOException {
		return getElementfluent(By.cssSelector(getValue.readFile(jsonfile,"Product_Search_Button_TextField")));

	}
	// Search for product.
		public WebElement SearchProductButton() throws IOException {
			return getElementfluent(By.cssSelector(getValue.readFile(jsonfile,"Product_Search_Button")));

		}
}
