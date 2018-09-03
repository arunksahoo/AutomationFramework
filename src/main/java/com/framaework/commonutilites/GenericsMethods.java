package com.framaework.commonutilites;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.openqa.selenium.WebDriver;

public class GenericsMethods {
	
	WebDriver driver;

	// Config data file data
	public static String ConfigFile(String Value) {
		File file = new File("src/main/java/com/framework/configuration/ProfileSetUp.properties");
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
	
	public ArrayList<String> api(File file) {
		ArrayList<String> arr = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				arr.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr;

	}

	// FilePath properties to read local files
	public String filereader(String Value) {
		File file = new File("data_source/HomePage.json");
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

	// File upload using Robot class
	
	public void fileUpload(String path) {

		try {
			StringSelection ss = new StringSelection(path);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			// imitate mouse events like ENTER, CTRL+C, CTRL+V
			Robot robot = new Robot();
			robot.delay(25000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(5000);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// color hexValue
	public String color(String color) {
		String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");

		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);
		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		return actualColor;
	}
}
