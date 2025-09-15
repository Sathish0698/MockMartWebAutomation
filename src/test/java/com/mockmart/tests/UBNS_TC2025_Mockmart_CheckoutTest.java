package com.mockmart.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UBNS_TC2025_Mockmart_CheckoutTest {

	@Test
	public void tC0001_E2E() throws Exception {

		String prodName = "Solid Gold Petite Micropave ...";

		EdgeOptions options = new EdgeOptions();
		options.addArguments("--inprivate");

		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver(options);
		driver.get("https://letcode.in/home");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement LoginPageButton = driver.findElement(By.xpath("//i[@class='fas fa-user']"));
		LoginPageButton.click();

		WebElement enterUserName = driver.findElement(By.xpath("//input[@placeholder='Enter Username']"));
		enterUserName.sendKeys("mor_2314");

		WebElement enterPasswd = driver.findElement(By.xpath("//input[@placeholder='Enter Password']"));
		enterPasswd.sendKeys("83r5^_");

		WebElement loginButton = driver.findElement(By.xpath("//button[@class='button is-primary']"));
		loginButton.click();

		Thread.sleep(2000l);

		boolean displayed = driver.findElement(By.xpath("//i[@class='fas fa-sign-out-alt']")).isDisplayed();
		Assert.assertEquals(displayed, true, "Login button is no t displayed!");

		List<WebElement> prodElements = driver.findElements(
				By.xpath("//div[@class='column is-12-mobile is-6-tablet is-one-quarter-desktop ng-star-inserted']"));

		WebElement expectedProd = prodElements.stream()
				.filter(s -> s.findElement(By.cssSelector(".card-header")).getText().equalsIgnoreCase(prodName))
				.findFirst().orElse(null);

		WebElement ap = expectedProd.findElement(By.cssSelector(".card-footer"));

		js.executeScript("arguments[0].click();", ap);

		String prodTitleText = driver.findElement(By.xpath("//p[@class='title']")).getText();

		driver.findElement(By.xpath("//span[contains(text(),'Add to Cart')]")).click();

		System.out.println(prodTitleText);

		Assert.assertEquals(prodTitleText, "Solid Gold Petite Micropave");

		WebElement addToCartButton = driver
				.findElement(By.xpath("//button[contains(@class,'mt-4')]//span[contains(text(),'Add to Cart')]"));

		addToCartButton.click();

		WebElement cartButton = driver.findElement(
				By.xpath("//button[contains(@class,'is-pulled-right')]//i[contains(@class,'fa-cart-shopping')]"));

		cartButton.click();

		// viewing cart section and vaidation the cart title and the product in cart
		// table

		String cartTitleTxt = driver.findElement(By.xpath("//h2[@class='title has-text-centered']")).getText();
		Assert.assertEquals(cartTitleTxt, "Shopping Cart");

		// div[contains(@class, 'table-container')]//tbody/tr[1]/td[1]

		String productNameInCartTable = driver
				.findElement(By.xpath("//div[contains(@class, 'table-container')]//tbody/tr[1]/td[1]")).getText();

		String quantityString = driver
				.findElement(By.xpath("//div[contains(@class, 'table-container')]//tbody/tr[1]/td[2]//span")).getText();

		Assert.assertEquals(productNameInCartTable, "Solid Gold Petite Micropave");

		String productPriceInCartTable = driver
				.findElement(By.xpath("//div[contains(@class, 'table-container')]//tbody/tr[1]/td[3]")).getText();

		String pureProdPrice = "";
		for (int i = 0; i < productPriceInCartTable.length(); i++) {
			char c = productPriceInCartTable.charAt(i);
			if (Character.isDigit(c)) {
				pureProdPrice += c;
			}
		}

		System.out.println(pureProdPrice);

		String TotalAmountString = driver
				.findElement(By.xpath("//div[contains(@class, 'table-container')]//tbody/tr[1]/td[4]")).getText();

		String pureTotPrice = "";
		for (int i = 0; i < TotalAmountString.length(); i++) {
			char c = TotalAmountString.charAt(i);
			if (Character.isDigit(c)) {
				pureTotPrice += c;
			}
		}

		int quantity = Integer.parseInt(quantityString);
		int productPrice = Integer.parseInt(pureProdPrice);
		int totalPrice = Integer.parseInt(pureTotPrice);

		if (quantity * productPrice == totalPrice) {
			// click the checkout button
			driver.findElement(By.xpath("//button[contains(@class,'is-success')]")).click();
		} else {
			System.out.println("calculation mismatch!");
		}

		Alert alert = driver.switchTo().alert();
		alert.accept();

		String finalTextAfterCheckout = driver.findElement(By.xpath("//p[contains(@class,'is-4')]")).getText();
		Assert.assertEquals(finalTextAfterCheckout, "Your cart is empty");

		WebElement productsButtonOnFinalPage = driver
				.findElement(By.xpath("//div[@class='column']//span[text()='Products']"));
		productsButtonOnFinalPage.click();

		System.out.println(driver.getCurrentUrl());

		WebElement logoutButton = driver.findElement(By.xpath("//i[contains(@class,'fa-sign-out-alt')]"));
		logoutButton.click();

		Thread.sleep(2000l);

		driver.quit();

	}

}
