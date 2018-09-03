package com.framaework.commonutilites;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public   void takeScreenshot( WebDriver driver,String methodname,String dateFormatted ) throws IOException
	{
		File snapshot=  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String location="source/snapshot/"+methodname+"/";
	 	File savesnap	=new File(location + methodname + "_" +dateFormatted+ ".png");
	 	FileUtils.copyFile(snapshot, savesnap);
	}
	
}
