package com.framaework.commonutilites;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.Reporter;

import com.mchange.util.AssertException;

public class SoftAssertions {
	public static int isAnyAssertFail = 0;
	public static Throwable exp;
	private static String FOLDER_NAME = System.getProperty("user.dir") + "/Screenshots";

	// Verify the Actual and Expected Results.
	public static void verifyEquals(Object actual, Object expected, String successMessage, String failureMessage) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Assert.assertEquals(actual, expected);
			printLogs("<font color = \"#006400\">PASS : " + successMessage + "</font>");
		} catch (Throwable e) {
			isAnyAssertFail++;
			takeScreenShotOnFailure();
			System.out.println(
					"FAIL : " + failureMessage + " Expected Result : " + expected + " Actual Result : " + actual);
			printLogs("<font color = \"ff0000\">FAIL : " + failureMessage + " Expected Result : " + expected
					+ " Actual Result : " + actual + "</font>");
			e.printStackTrace();
			printLogs("Detailed:" + e);
			if (isAnyAssertFail == 1)
				exp = e;
		}
	}

	public static void throwAsserationOnFailure() {

		if (isAnyAssertFail > 0) {
			isAnyAssertFail = 0;
			exp.printStackTrace();
			throw new AssertException(exp.toString());
		}
	}

	public static void printInfoMSG(String msg) {
		printLogs("<font color = \"#FF892E\">PASS : " + msg + "</font>");
	}

	public static void printLogs(String msg) {
		Reporter.log(msg);
	}

	public static void drawLine() {
		printLogs("------------------------------------------");
	}

	public static void reporterLogLine() {
		Reporter.log("---------------------------------------------------------------");
	}
	// Take Screen Shot of Failure Elements
	public static String takeScreenShotOnFailure() {
		File snapshot = ((TakesScreenshot) ProjectSetup.getDriver()).getScreenshotAs(OutputType.FILE);
		String dateNow = getCurrentDate();
		String snapShotDirectory = "source/snapshots_" + FOLDER_NAME;
		String location = snapShotDirectory + "/failure_screenshot_" + dateNow + ".png";
		File file = new File(location);
		try {
			FileUtils.copyFile(snapshot, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String screenShotPath = "\nScreenshot Location :: " + file.getAbsolutePath() + "\n";
		printLogs(screenShotPath);
		System.out.println(screenShotPath);
		return screenShotPath;
	}

	public static String getCurrentDate() {
		String DATE_FORMAT_NOW = "yyyy/MMM/dd";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		FOLDER_NAME = sdf.format(date).replace("/", "_");
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		return formatter.format(currentDate.getTime()).replace("/", "_").replace(":", "_").replace(" ", "");
	}
}
