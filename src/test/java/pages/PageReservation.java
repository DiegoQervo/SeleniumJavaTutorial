package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.support.ui.*;




public class PageReservation {
	private By PassangersDrop;
	private WebDriver driver;
	private By FromDrop;
	private By ToDrop;
	private By TitleText;
	public PageReservation(WebDriver driver) {
		this.driver = driver;
		TitleText =  By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[1]/td");
		PassangersDrop = By.name("passCount");
		FromDrop = By.name("fromPort");
		ToDrop = By.name("toPort");
	}
	public void assertPage() {
		Assert.assertTrue(driver.findElement(TitleText).getText().contains("Login Successfully"));
	}
	
	public void SelectPassengers(int cant) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cantidadPasajeros = wait.until(ExpectedConditions.presenceOfElementLocated(PassangersDrop));
		Select selectPasajeros = new Select(cantidadPasajeros);
		selectPasajeros.selectByVisibleText(Integer.toString(cant));
	}
	public void selectFromDrop(int index) {
		Select selectFrom = new Select(driver.findElement(FromDrop));
		selectFrom.selectByIndex(index);
	}
	public void selectoToPort(String city) {
		Select selectTo = new Select(driver.findElement(ToDrop));
		selectTo.selectByValue(city);
	}
}
