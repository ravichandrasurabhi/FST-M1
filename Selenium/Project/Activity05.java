package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity05 {
	WebDriver driver;

	@BeforeClass
	public void login() {
		driver = new FirefoxDriver();
		driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("bigbutton")).click();

	}

	@Test
	public void gettingColors() {

		WebElement navigationMenu = driver.findElement(By.id("toolbar"));
		System.out.println("The Navigation Menu color is : " + navigationMenu.getCssValue("color"));
	}

	@AfterClass

	public void closeBrowser() {
		driver.quit();
	}

}
