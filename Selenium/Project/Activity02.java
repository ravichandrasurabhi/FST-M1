package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity02 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users");
	}

	@Test
	public void urlOfHeaderImage_02() {

		String url = driver.findElement(By.xpath("//div/img")).getAttribute("src");
		System.out.println("The URL of Header Image is  : ");
		System.out.println(url);
	}


	@AfterClass

	public void tearDown() {
		driver.quit();
	}

}
