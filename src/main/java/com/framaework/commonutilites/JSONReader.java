package com.framaework.commonutilites;

import java.io.File;
import java.io.IOException;
import com.jayway.jsonpath.JsonPath;


public class JSONReader {
	static File jsonfile;
	
	//File read value from JSON file 
	public String readFile(String filename,String parametersvalue) {
		String jsonValue = null;
		jsonfile = new File(filename);
		try {
			jsonValue = JsonPath.read(jsonfile,"$."+""+parametersvalue+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonValue;
		
	}
}
