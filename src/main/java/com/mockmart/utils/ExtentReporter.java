package com.mockmart.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {

		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(
				System.getProperty("user.dir") + "\\Reports\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK); // Dark theme
		sparkReporter.config().setDocumentTitle("MockMart Automation Report");
		sparkReporter.config().setReportName("Regression / Smoke Test Results");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReport.attachReporter(sparkReporter);

		configReader cf = new configReader();

		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("URL", cf.getProperty("url"));
		extentReport.setSystemInfo("Browser", cf.getProperty("browser"));
		extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReport.setSystemInfo("Version", System.getProperty("java.version"));
		
		return extentReport;

	}

}
