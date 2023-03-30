package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import helpers.Helpers;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageReservation;
import org.openqa.selenium.JavascriptExecutor;
import helpers.ScreenShots;
import helpers.WebDriverManager;


public class Tests {
	private WebDriver driver;
	ArrayList<String> tabs;
	@BeforeMethod
	public void setUp(){
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
		driver.navigate().to("https://demo.guru99.com/test/newtours/index.php");
		JavascriptExecutor JavaScriptExecutor = (JavascriptExecutor)driver;
		String googleWindow = "window.open('http://www.google.com')";
		JavaScriptExecutor.executeScript(googleWindow);
		tabs = new ArrayList<String> (driver.getWindowHandles());
	}
	@Test 
	public void PruebaUno() {		
		WebDriverManager.SetWindowSize(driver, "maximized");
		driver.switchTo().window(tabs.get(1)).navigate().to("https://demo.guru99.com/test/newtours/reservation.php");
		driver.switchTo().window(tabs.get(0));
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		pageLogin.login("user", "user");
		pageReservation.assertPage();
		
		
	}
	@Test 
	public void PruebaDos() {
		WebDriverManager.SetWindowSize(driver, "fullscreen");
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.login("user", "no");
		pageLogon.assertLogonPage();
		
	
		
	}
	@Test
	public void PruebaTres() {
		WebDriverManager.SetWindowSize(driver, 500, 700);
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		pageLogin.login("user", "user");
		pageReservation.assertPage();
		driver.navigate().to("https://demo.guru99.com/test/newtours/reservation.php");
		pageReservation.SelectPassengers(2);
		pageReservation.selectFromDrop(3);
		pageReservation.selectoToPort("London");
	}
	@Test
	public void PruebaCuatro() {
		PageLogin pagelogin = new PageLogin(driver);
		pagelogin.verifyFields();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(!result.isSuccess()) {
			ScreenShots.takeScreenshot("Error", driver);
		}
			driver.switchTo().window(tabs.get(1)).close();
			driver.switchTo().window(tabs.get(0)).close();
	}
}	
