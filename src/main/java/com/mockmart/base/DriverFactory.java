package com.mockmart.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public static WebDriver setWebDriver(String browserName) throws Exception {

		if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--inprivate");
			WebDriverManager.edgedriver().setup();
			tldriver.set(new EdgeDriver(edgeOptions));
		} else if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver(chromeOptions));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions firefoxoptions = new FirefoxOptions();
			firefoxoptions.addArguments("-private");
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver(firefoxoptions));
		}

		getDriver().manage().window().maximize();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		return getDriver();

	}

	public static WebDriver getDriver() {
		return tldriver.get();

	}

	public static void quitDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			tldriver.remove();
		}
	}

}
