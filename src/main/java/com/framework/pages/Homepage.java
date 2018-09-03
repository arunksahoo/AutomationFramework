package com.framework.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.framaework.commonutilites.GenericsMethods;
import com.framework.commonfunction.BasePage;

public class Homepage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static GenericsMethods genericMethods;

	public Homepage(WebDriver driver) {
		Homepage.driver = driver;
		genericMethods = new GenericsMethods();
	}

	// Reading elements for home page
	public static String ReadingFilePagePath(String Value) {
		File file = new File("src/main/java/com/framework/uipageselements/HomePageElements.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();
		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String Data = prop.getProperty(Value);

		return Data;
	}

	// Patient discount percentage text in patients details page
	public WebElement SignInButton() {
		return getElementfluent(By.cssSelector(ReadingFilePagePath("Homepage_SignIn_Button")));

	}
}
