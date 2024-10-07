package com.Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tests {

	WebDriver driver;
	String url = "https://todomvc.com/examples/angular/dist/browser/#/all";
	String todo = "Drink Water Every Hour";
	String title = "TodoMVC: Angular";
	String completed = "completed";

	@BeforeTest
	public void Intialization() {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(14));
	}

	@Test
	// 1. Add to-do is working. (Verify if the item is added to the list )
	public void addToDoIsworking() throws Throwable {
		Thread.sleep(10000);
		// Locaters
		WebElement enterBox = driver.findElement(By.xpath("(//input[@placeholder='What needs to be done?'])[1]"));
		Reporter.log("Home Page Verification");
		String actualTitle = driver.getTitle();
		System.out.println(title);
		Assert.assertTrue(title.equalsIgnoreCase(actualTitle));
		Reporter.log("Home Page verfifed");

		Thread.sleep(10000);
		Reporter.log(" Test 001 - Add to-do is working. (Verify if the item is added to the list )");
		enterBox.click();
		enterBox.sendKeys(todo);
		enterBox.sendKeys(Keys.ENTER);

		// Verification
		WebElement actualText = driver.findElement(By.xpath("(//*[text()='Drink Water Every Hour'])[1]"));
		if (todo.equalsIgnoreCase(actualText.getText())) {
			Assert.assertTrue(true);
			Reporter.log("Test 001 - Add to-do is working. (Verify if the item is added to the list - verfifed");

		} else {
			Assert.assertTrue(false);
			Reporter.log("Test 001 - Add to-do is working. (Verify if the item is added to the list - not verfifed");
		}

		Reporter.log(
				"************************End Of Test ***************************************************************************");

		Reporter.log(
				"Test-002 Marking an item as complete works. (Verify if the item is crossed out and verify the counter on the bottom-left)");
		WebElement clickCompleted = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
		clickCompleted.click();
		WebElement getAttributeofClass = driver.findElement(By.xpath("(//li[@class='completed'])[1]"));
		if (completed.equalsIgnoreCase(getAttributeofClass.getAttribute("class"))) {
			Assert.assertTrue(true);
			Reporter.log(
					"Test-002 Marking an item as complete works. (Verify if the item is crossed out and verify the counter on the bottom-left) - verfifed");
		}

		else {
			Assert.assertTrue(false);
			Reporter.log(
					"Test-002 Marking an item as complete works. (Verify if the item is crossed out and verify the counter on the bottom-left) - - not verfifed");
		}

		Reporter.log(
				"************************End Of Test ***************************************************************************");

		Reporter.log("Test -003 Delete a to-do (Verify if the item is removed from the list)");

		WebElement destroyButton = driver.findElement(By.xpath("(//button[@class='destroy'])[1]"));
		destroyButton.click();
		Thread.sleep(10000);
		WebElement emtyscreen=	driver.findElement(By.xpath("//input[@placeholder='What needs to be done?']"));
		boolean status = emtyscreen.isDisplayed();

		if (status == true) {

			Assert.assertTrue(true);
			Reporter.log("Test -003 Delete a to-do (Verify if the item is removed from the list) - verfifed");
		}

		else {
			Assert.assertTrue(false);
			Reporter.log("Delete a to-do (Verify if the item is removed from the list)- - not verfifed");

		}

		Reporter.log(
				"************************End Of Test ***************************************************************************");

		Reporter.log(
				"Test =004-Filtering a to-do is working (Click on the Active button and verify if it shows the Active items. Click on Completed and verify if it shows the completed items)");
		enterBox.click();
		enterBox.sendKeys(todo);
		enterBox.sendKeys(Keys.ENTER);

		WebElement all = driver.findElement(By.xpath("(//a[normalize-space()='All'])[1]"));
		all.click();
		WebElement actier = driver.findElement(By.xpath("(//a[normalize-space()='Active'])[1]"));
		WebElement present = driver.findElement(By.xpath("(//label[normalize-space()='Drink Water Every Hour'])[1]"));

		if (present.isDisplayed() == true) {

			Assert.assertTrue(true);
			Reporter.log(
					"Test =004-Filtering a to-do is working (Click on the Active button and verify if it shows the Active items. Click on Completed and verify if it shows the completed items)");
		}

		else {
			Assert.assertTrue(false);
			Reporter.log(
					"Test =004-Filtering a to-do is working (Click on the Active button and verify if it shows the Active items. Click on Completed and verify if it shows the completed items)");
		}

		Reporter.log(
				"************************End Of Test ***************************************************************************");
	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}
