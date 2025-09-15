package com.mockmart.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mockmart.base.DriverFactory;
import com.mockmart.utils.ExtentReporter;
import com.mockmart.utils.TestUtils;

public class TestListener implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " started execution...");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + " got successfully executed!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		WebDriver driver = DriverFactory.getDriver();
		String takeScreenshot = new TestUtils().takeScreenshot(driver, testName);
		extentTest.addScreenCaptureFromPath(takeScreenshot, testName);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + " got failed!!!");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " got skipped!");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		File extentReportFile = new File(
				System.getProperty("user.dir") + "\\Reports\\ExtentReports\\extentReport.html");
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
