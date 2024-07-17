package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity06 {
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
	public void menuChecking_06() {
		

		WebElement activitiesMenu = driver.findElement(By.id("grouptab_3"));
		Assert.assertTrue(activitiesMenu.isDisplayed());
		System.out.println("Activities menu is displayed : " + activitiesMenu.isDisplayed());

	

	}

	@AfterClass

	public void closeBrowser() {
		driver.quit();
	}

}
