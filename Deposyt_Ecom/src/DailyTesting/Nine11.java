package DailyTesting;

import java.time.Duration;
import java.time.Instant;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.MasterClass;

public class Nine11 extends MasterClass {

	@Test
	public void Products() throws Exception	{

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		Actions action = new Actions(driver);
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Instant now = Instant.now();

		String Product_name = "Nine Eleven Test Product"+now.getEpochSecond(), Price = "25";

		driver.navigate().to(Products);
		Thread.sleep(3000);
		
		//Commenting Below code due to isse in delete product functionality
		/*//Deleting Duplicate Prooduct  
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_name);
		
		try 
		{ 
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("#result-item-0>a")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("button[type=\"submit\"]+div>div>button")).click();
			driver.findElement(By.cssSelector("div.shadow-product-card-hover>button")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div.verification-model-code+div>div>button:last-of-type")).click();
			Thread.sleep(2000);
		}
		catch (NoSuchElementException e) {
		}
		
		
		driver.findElement(By.cssSelector("input[placeholder=\"Search Products\"]")).clear();
		*/
		//Create a product in test account
		driver.findElement(By.cssSelector("button.items-center.capitalize.newproductbutton")).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created by Automation for 911 Test");
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop = 500");
		driver.findElement(By.cssSelector("input[type=\"file\"]")).sendKeys(Media_Path+"JPEG TEST.jpg");
		driver.findElement(By.cssSelector("[name=\"productType.digital.attachment\"]+div>div>input")).sendKeys(Media_Path+"Jira Guide.pdf");
		
		//Entering Product price
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop = 500");
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys(Price);
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop = 1500");
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span")).click();
		driver.findElement(By.cssSelector("div.sticky.bottom-0")).click();
		driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Product-Detial-side-modal-Scrollbar")));
		
		//validate product listing 
		driver.findElement(By.cssSelector("input[placeholder=\"Search Products\"]")).sendKeys(Product_name);
		
		try 
		{ 
			driver.findElement(By.cssSelector("#result-item-0>a")).click();
		}
		catch (NoSuchElementException e) 
		{
			Assert.assertTrue(false, "Product Listing Not Found");
		}
	}
}