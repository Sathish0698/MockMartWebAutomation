package com.mockmart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mockmart.utils.configReader;

public class ProductDetailspage {

	public WebDriver driver;

	public ProductDetailspage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[@class='title']")
	WebElement titleTxtElement;

	@FindBy(xpath = "//button[contains(@class,'mt-4')]//span[contains(text(),'Add to Cart')]")
	WebElement addToCartButton;

	@FindBy(xpath = "//button[contains(@class,'is-pulled-right')]//i[contains(@class,'fa-cart-shopping')]")
	WebElement goToCartButton;

	public void validateTheProductName() {
		String prodTitleText = titleTxtElement.getText();
		Assert.assertEquals(prodTitleText, new configReader().getProperty("productFullName"));
		System.out.println(prodTitleText);
	}

	public void clickAddToCart() {
		addToCartButton.click();
	}

	public Cartpage navigateToCartSection() {
		goToCartButton.click();
		Cartpage cartpage = new Cartpage(driver);
		return cartpage;
		
	}

}
