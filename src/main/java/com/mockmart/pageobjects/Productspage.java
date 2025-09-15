package com.mockmart.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Productspage {

	public WebDriver driver;

	public Productspage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//i[@class='fas fa-sign-out-alt']")
	WebElement logoutButton;

	@FindBy(xpath = "//div[contains(@class, 'is-one-quarter-desktop')]")
	List<WebElement> prods;

	By prodNameHeader = By.cssSelector(".card-header");

	By prodFooterelementtoclick = By.cssSelector(".card-footer");

	WebElement expectedProd;

	public boolean validateTheLogoutButtonIsDisplayed() {
		boolean displayed = logoutButton.isDisplayed();
		if(displayed == true) {
			return true;
		}else {
			return false;
		}
	}

	public void findTheProd(String prodName) {

		expectedProd = prods.stream().filter(s -> s.findElement(prodNameHeader).getText().equalsIgnoreCase(prodName))
				.findFirst().orElse(null);
	}

	public void clickLogout() {
		logoutButton.click();
	}

	public ProductDetailspage viewTheProd() {
		WebElement ap = expectedProd.findElement(prodFooterelementtoclick);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ap);
		ProductDetailspage pdp = new ProductDetailspage(driver);
		return pdp;
	}

}
