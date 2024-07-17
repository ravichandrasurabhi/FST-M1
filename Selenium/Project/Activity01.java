package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity01 {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users");
	}

	@Test
	public void homePageTitileTest() {

		System.out.println("The Tittle of page is  : " + driver.getTitle());
		Assert.assertEquals("SuiteCRM", driver.getTitle());
	}

	@AfterClass

	public void tearDown() {
		driver.quit();
	}

}
