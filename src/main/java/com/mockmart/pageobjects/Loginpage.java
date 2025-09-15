package com.mockmart.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mockmart.utils.configReader;

public class Loginpage {

	public WebDriver driver;

	public Loginpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By username = By.xpath(new configReader().getProperty("user.name.xpath"));

	@FindBy(xpath = "//input[@placeholder='Enter Password']")
	WebElement Passwd;

	@FindBy(xpath = "//button[@class='button is-primary']")
	WebElement loginButton;
	
	@FindBy(css = ".mat-mdc-snack-bar-label")
	WebElement invalidLogintext;

	public void enterLoginCreds(String uname, String pwd) {
		driver.findElement(username).sendKeys(uname);
		Passwd.sendKeys(pwd);
	}

	public Productspage clickLogin() {
		loginButton.click();
		Productspage pp = new Productspage(driver);
		return pp;
	}
	
	public boolean validateInvalidLoginErrorMessage() {
		
		String errorMsg = invalidLogintext.getText();
		System.out.println(errorMsg);
		if(invalidLogintext.isDisplayed()) {
			return true;
		}
		return false;

	}

}
