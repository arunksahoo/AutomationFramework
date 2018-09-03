package com.framaework.commonutilites;

import org.testng.annotations.DataProvider;


public class Data_Provider {
	ExcelReaderExpected EDR = new ExcelReaderExpected();

	// Reading Excel sheet Data Using Data Provider
	@DataProvider(name = "ValidSignupData")
	public Object[][] loginData01() {
		Object[][] arrayObject = EDR.getExcelData("/data_source/Validation.xls", "Credential");
		return arrayObject;
	}

}
