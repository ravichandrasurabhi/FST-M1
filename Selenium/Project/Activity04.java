package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity04 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users");
	}

	@Test
	public void login() {

		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("bigbutton")).click();
		WebElement userMenu = driver.findElement(By.id("with-label"));
		Assert.assertTrue(userMenu.isDisplayed(), "Home page not loaded");

	}

	@AfterClass

	public void tearDown() {
		driver.quit();
	}

}
