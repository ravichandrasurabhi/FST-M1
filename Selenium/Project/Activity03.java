package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity03 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users");
	}

	@Test
	public void copyRightText_03() {

		String copyRightText = driver.findElement(By.id("admin_options")).getText();
		System.out.println("The copyright Text is : " + copyRightText);
	}

	@AfterClass

	public void tearDown() {
		driver.quit();
	}

}
