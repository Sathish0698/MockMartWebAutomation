package com.mockmart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mockmart.base.Base;
import com.mockmart.base.DriverFactory;
import com.mockmart.pageobjects.Cartpage;
import com.mockmart.pageobjects.LandingPage;
import com.mockmart.pageobjects.Loginpage;
import com.mockmart.pageobjects.ProductDetailspage;
import com.mockmart.pageobjects.Productspage;
import com.mockmart.utils.configReader;

public class UBNS_TC2026_Mockmart_LoginTest extends Base {

	configReader cf;

	public UBNS_TC2026_Mockmart_LoginTest() {
		cf = new configReader();
	}

	public LandingPage landingPage;
	public Loginpage loginPage;
	public Productspage productsPage;
	public ProductDetailspage productDetailsPage;
	public Cartpage cartPage;

	@Test(priority = 2)
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
		productDetailsPage.clickAddToCart();
		cartPage = productDetailsPage.navigateToCartSection();
		cartPage.validateTheTitleOfTheCart();
		cartPage.validateTheProductNameInCartTable();
		cartPage.extractTotalAmountAndProductPrice();
		cartPage.validateTheTotalPriceOfTheProduct();
		cartPage.validateAlertMessageAndClickOk("Checkout Successful!");
		cartPage.validateFinalTextAfterCheckout("Your cart is empty");
		cartPage.clickProductsAndVerifyItNavigatesToProductspage("https://letcode.in/home");
		productsPage.clickLogout();
	}

	@Test(priority = 1)
	public void tC0002_validateLoginWithValidCreds() {

		landingPage = new LandingPage(DriverFactory.getDriver());
		loginPage = landingPage.navigateToLoginPage();
		loginPage.enterLoginCreds(cf.getProperty("user.name"), cf.getProperty("password"));
		productsPage = loginPage.clickLogin();
		boolean validateTheLogoutButtonIsDisplayed = productsPage.validateTheLogoutButtonIsDisplayed();
		if (!validateTheLogoutButtonIsDisplayed) {
			System.out.println("Unable to login");
		} else {
			productsPage.clickLogout();
		}

	}

	@Test(priority = 3)
	public void tC0003_validateLoginWithInValidCreds() {	

		landingPage = new LandingPage(DriverFactory.getDriver());
		loginPage = landingPage.navigateToLoginPage();
		loginPage.enterLoginCreds(cf.getProperty("invalid.user.name"), cf.getProperty("invalid.password"));
		loginPage.clickLogin();
		boolean validateInvalidLoginErrorMessage = loginPage.validateInvalidLoginErrorMessage();
		Assert.assertEquals(validateInvalidLoginErrorMessage, true);

	}

}
