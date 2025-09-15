package com.mockmart.tests;

import org.testng.annotations.Test;

import com.mockmart.base.Base;
import com.mockmart.base.DriverFactory;
import com.mockmart.pageobjects.Cartpage;
import com.mockmart.pageobjects.LandingPage;
import com.mockmart.pageobjects.Loginpage;
import com.mockmart.pageobjects.ProductDetailspage;
import com.mockmart.pageobjects.Productspage;
import com.mockmart.utils.configReader;

public class UBNS_TC2027_Mockmart_ProductTest extends Base {

	configReader cf;

	public UBNS_TC2027_Mockmart_ProductTest() {
		cf = new configReader();
	}

	public LandingPage landingPage;
	public Loginpage loginPage;
	public Productspage productsPage;
	public ProductDetailspage productDetailsPage;
	public Cartpage cartPage;

	@Test
	public void tC0001_E2E() throws Exception {

		String prodName = cf.getProperty("productName");

		landingPage = new LandingPage(DriverFactory.getDriver());

		loginPage = landingPage.navigateToLoginPage();
		loginPage.enterLoginCreds("mor_2314", "83r5^_");
		productsPage = loginPage.clickLogin();
		productsPage.validateTheLogoutButtonIsDisplayed();
		productsPage.findTheProd(prodName);
		productDetailsPage = productsPage.viewTheProd();
		productDetailsPage.validateTheProductName();
		productsPage.clickLogout();

	}

}
