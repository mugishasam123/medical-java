package com.samuel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

public class ExcelConvert {

	public static JSONObject excelConvert(String filePath, int index,String role) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(filePath));
			XSSFWorkbook workBook = new XSSFWorkbook(excelFile);
			XSSFSheet workSheet = workBook.getSheetAt(index);
			String sheetName = workBook.getSheetName(index);
			List<JSONObject> dataList = new ArrayList<>();
			XSSFRow header = workSheet.getRow(1);
			for (int i = 2; i < workSheet.getPhysicalNumberOfRows() + 1; i++) {
				XSSFRow row = workSheet.getRow(i);
				JSONObject rowJsonObject = new JSONObject();
				for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
					String columnName = header.getCell(j).toString();
					String columnValue = row.getCell(j).toString();
					rowJsonObject.put(columnName, columnValue);
				}
				dataList.add(rowJsonObject);
			}
			workBook.close();
			JSONObject sheetObject = new JSONObject();
			sheetObject.put("role", role);
			sheetObject.put("sheetName", sheetName);
			sheetObject.put("data", dataList);

			return sheetObject;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
