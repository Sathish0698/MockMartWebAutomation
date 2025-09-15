package com.mockmart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//i[@class='fas fa-user']")
	WebElement loginLinkButtton;

	public Loginpage navigateToLoginPage() {
		loginLinkButtton.click();
		Loginpage loginPage = new Loginpage(driver);
		return loginPage;
	}

	public void OpenTheApplication() {
		driver.get("https://letcode.in/home");
	}

}
