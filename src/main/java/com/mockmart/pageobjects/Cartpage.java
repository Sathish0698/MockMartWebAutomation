package com.mockmart.pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mockmart.utils.configReader;

public class Cartpage {

	public WebDriver driver;

	public Cartpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	String cartTitle = "";
	String productNameInCart = "";
	String pureProdPrice = "";
	String pureTotPrice = "";
	String quantitytxt = "";
	String number1 ="";
	String number2 ="";

	@FindBy(xpath = "//h2[@class='title has-text-centered']")
	WebElement cartTitleText;

	@FindBy(xpath = "//div[contains(@class, 'table-container')]//tbody/tr[1]/td[1]")
	WebElement productNameInCartTable;

	@FindBy(xpath = "//div[contains(@class, 'table-container')]//tbody/tr[1]/td[2]//span")
	WebElement quantityString;

	@FindBy(xpath = "//div[contains(@class, 'table-container')]//tbody/tr[1]/td[3]")
	WebElement productPriceInCartTable;

	@FindBy(xpath = "//div[contains(@class, 'table-container')]//tbody/tr[1]/td[4]")
	WebElement TotalAmountString;

	@FindBy(xpath = "//button[contains(@class,'is-success')]")
	WebElement checkoutButton;

	@FindBy(xpath = "//p[contains(@class,'is-4')]")
	WebElement finalTextAfterchekot;

	@FindBy(xpath = "//div[@class='column']//span[text()='Products']")
	WebElement ProductsButton;

	public void validateTheTitleOfTheCart() {
		String cartTitle = cartTitleText.getText();
		Assert.assertEquals(cartTitle, "Shopping Cart");
	}

	public void validateTheProductNameInCartTable() {
		String productNameInCart = productNameInCartTable.getText();
		Assert.assertEquals(productNameInCart, new configReader().getProperty("productFullName"));
	}

	public void extractTotalAmountAndProductPrice() {

		String productPriceInCartTableString = productPriceInCartTable.getText();
		number1 = productPriceInCartTableString.replaceAll("[^0-9.]", "");
		System.out.println(pureProdPrice);
		String TotalAmountStringText = TotalAmountString.getText();
		number2 = TotalAmountStringText.replaceAll("[^0-9.]", "");

	}

	public void validateTheTotalPriceOfTheProduct() {

		quantitytxt = quantityString.getText();
		int quantity = Integer.parseInt(quantitytxt);
		double productPrice = Double.parseDouble(number1);
		double totalPrice = Double.parseDouble(number2);
		
		System.out.println(quantity * productPrice);

		if (quantity * productPrice == totalPrice) {
			// click the checkout button
			checkoutButton.click();
		} else {
			System.out.println("calculation mismatch!");
		}
	}

	public void validateAlertMessageAndClickOk(String expectedCheckoutAlertMessage) {
		Alert al = driver.switchTo().alert();
		String alertText = al.getText();
		System.out.println(alertText);
		Assert.assertEquals(alertText, expectedCheckoutAlertMessage);
		al.accept();
	}

	public void validateFinalTextAfterCheckout(String ExpectedFinalTextAfterCheckout) {
		String finalTextAfterCheckout = finalTextAfterchekot.getText();
		Assert.assertEquals(finalTextAfterCheckout, ExpectedFinalTextAfterCheckout);
	}

	public void clickProductsAndVerifyItNavigatesToProductspage(String expectedURl) {
		ProductsButton.click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, expectedURl);
	}

}
