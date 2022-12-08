package com.samuel.utils;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

import com.samuel.users.models.UserList;

public class Excel2Json {

	public static Message<JSONObject> AdminData(String filePath, int index) {

		List<JSONObject> jsonObjects = new ArrayList<>();
		JSONObject json1 = ExcelConvert.excelConvert(filePath, 0);
		jsonObjects.add(json1);
		JSONObject json2 = ExcelConvert.excelConvert(filePath, 1);
		jsonObjects.add(json2);
		JSONObject json3 = ExcelConvert.excelConvert(filePath, 2);
		jsonObjects.add(json3);
		JSONObject tablesObject = new JSONObject();
		tablesObject.put("tables", jsonObjects);
		tablesObject.put("users", UserList.fetchUsers());
		return new Message<JSONObject>("Data retrieved successfully", tablesObject);
	}

	public static Message<JSONObject> usersData(String filePath, int index) {
		return new Message<JSONObject>("Data retrieved successfully", ExcelConvert.excelConvert(filePath, index));
	}

}