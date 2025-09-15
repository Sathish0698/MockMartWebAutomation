package com.mockmart.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtils {

	public String takeScreenshot(WebDriver driver, String testName) {
		
		if (driver == null)
			return null;

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String destPath = System.getProperty("user.dir") + "\\Reports\\screenshots\\" + testName + "_" + timestamp + ".png";

		try {
			FileUtils.copyFile(srcFile, new File(destPath));
			System.out.println("Screenshot saved: " + destPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destPath;
	}

}
