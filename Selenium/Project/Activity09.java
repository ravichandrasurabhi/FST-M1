package project;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity09 {
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
	public void traversingTables2() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("grouptab_0")));
		WebElement salatesTab = driver.findElement(By.id("grouptab_0"));
		action.moveToElement(salatesTab).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moduleTab_9_Leads")));
		driver.findElement(By.id("moduleTab_9_Leads")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@class='list-view-rounded-corners']//tbody[1]/tr[contains(@class,'oddListRow')][1]")));
		List<WebElement> names = driver.findElements(By.xpath("//td[@type='name']//a"));
		List<WebElement> user = driver.findElements(By.xpath("//td[@type='relate']//a"));

		for (int i = 0; i < 10; i++) {
			System.out.println("Name = " + names.get(i).getText() + " , " + "User = " + user.get(i).getText());
		}
	}

	@AfterClass

	public void closeBrowser() {
		driver.quit();
	}
}
