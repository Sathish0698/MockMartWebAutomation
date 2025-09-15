package com.mockmart.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	@DataProvider(name = "getData")
	public void getDataFromxl() throws EncryptedDocumentException, IOException {

		String filelocation = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "test-data" + File.separator + "MockmartE2EData.xlsx";
		FileInputStream fis = new FileInputStream(new File(filelocation));
		Workbook wbook = WorkbookFactory.create(fis);
		Sheet sheet = wbook.getSheetAt(0);
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		String stringCellValue = cell.getStringCellValue();
		System.out.println(stringCellValue);
		wbook.close();

	}

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\ekartrh\\data\\purchaseOrder.json"),
				StandardCharsets.UTF_8);

		// String to hashmap - jackson databind
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

}
