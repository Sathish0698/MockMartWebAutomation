package com.mockmart.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.mockmart.pageobjects.LandingPage;
import com.mockmart.utils.configReader;

public class Base {

	public LandingPage landingPage;

	@BeforeMethod
	public void launchApplication() throws Exception {

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: new configReader().getProperty("browser");

		// String browserName = new configReader().getProperty("browser");
		DriverFactory.setWebDriver(browserName);
		landingPage = new LandingPage(DriverFactory.getDriver());
		landingPage.OpenTheApplication();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		DriverFactory.quitDriver();
	}

}
