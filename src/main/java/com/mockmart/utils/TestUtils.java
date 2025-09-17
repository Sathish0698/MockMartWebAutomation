package com.mockmart.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {

	public WebDriverWait wait;

	public WebElement waitForElementToBePresent(WebDriver driver, By element) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.presenceOfElementLocated(element));

	}

	public void waitForVisibilityOfAllElementlocated(WebDriver driver,By locater3) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locater3));

	}
	
	public void waitForElementToBeVisible(WebDriver driver,By locater) {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locater));
		

	}

	public String takeScreenshot(WebDriver driver, String testName) {

		if (driver == null)
			return null;

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String destPath = System.getProperty("user.dir") + "\\Reports\\screenshots\\" + testName + "_" + timestamp
				+ ".png";

		try {
			FileUtils.copyFile(srcFile, new File(destPath));
			System.out.println("Screenshot saved: " + destPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destPath;
	}

}
