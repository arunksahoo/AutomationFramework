package com.framaework.commonutilites;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONReader {

	// Reading json file
	public static String readFile(String filename) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Reading JSON Object as String  
	public static JSONObject JSONFileAsObjects(String filename) {
		JSONObject jobj = null;
		try {
			String jsonData = JSONReader.readFile(filename);
			JSONParser parser = new JSONParser();
			jobj = (JSONObject) parser.parse(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobj;
	}
}
