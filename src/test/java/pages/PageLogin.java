package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import helpers.Helpers;

public class PageLogin {
	private WebDriver driver;
	private By userField;
	private By passwordField;
	private By loginButton;
	private By fields;
	public PageLogin(WebDriver driver) {
	this.driver = driver;
	fields = By.tagName("input");
	userField = By.name("userName");
	passwordField = By.name("password");
	loginButton = By.name("submit");
	}
	public void login(String user, String pass) {
			driver.findElement(userField).sendKeys(user);
			driver.findElement(passwordField).sendKeys(pass);
			driver.findElement(loginButton).click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	public void verifyFields() {
		List<WebElement> loginFields = driver.findElements(fields);
		System.out.println(loginFields.size());
		Assert.assertTrue(loginFields.size() == 4);
	}
}
