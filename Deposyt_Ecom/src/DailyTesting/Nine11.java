package DailyTesting;

import java.time.Duration;
import java.time.Instant;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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

		String Product_name = "Nine Eleven Test Product"+now.getEpochSecond(), Price = "25",
				F_Name = "NineEleven", L_Name = "Contact", Number = "2345678910",
				Street_Add = "D.P. Road", State = "Maharastra", City = "Pune", Pincode = "411001",
				Card_Number = "4242424242424242", CVV = "123",
				Mail_Subject = "Your Order is Confirmed";
		
		//Deleting Already recived order Mails
		DeleteMail(Mail_Subject);

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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.items-center.capitalize.newproductbutton"))).click();
		//driver.findElement(By.cssSelector("button.items-center.capitalize.newproductbutton")).click();
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
		
		//Check if we can active and inactive a product on test account
		driver.findElement(By.cssSelector("button[role=\"switch\"]>span")).click();
		driver.findElement(By.cssSelector("div.rt-CardInner>div>button:nth-of-type(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn-small[type=\"submit\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.shadow-toaster")));
		driver.findElement(By.cssSelector("div.shadow-toaster>div>button")).click();
		
		//validate Product is inactive
		driver.findElement(By.id("orderPagesFunnel")).click();
		
		String pwindo = driver.getWindowHandle();
		driver.findElement(By.xpath("//p[text() = 'Default Checkout Page']//following-sibling::div[2]//a")).click();
		
		String Checkout_page = null;
		for(String Tab: driver.getWindowHandles())
		{
			driver.switchTo().window(Tab);
			Checkout_page = Tab;
		}
		
		Thread.sleep(3000);//Added Wait due to optimization issue
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Product Currently Unavailable']")));
		}
		catch(TimeoutException e)
		{
			try
			{
				driver.findElement(By.xpath("//span[text()[normalize-space() = 'Order Summary']]"));
				Assert.assertTrue(false, "Product Not Get Set as inactive");
			}
			catch(NoSuchElementException e1)
			{
				Assert.assertTrue(false, "Check Locater for 'product not available' Line'");
			}
			
		}
		
		driver.switchTo().window(pwindo);
		
		//Making Product active 
		driver.findElement(By.cssSelector("button[role=\"switch\"]>span")).click();
		driver.findElement(By.cssSelector("div.rt-CardInner>div>button:nth-of-type(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn-small[type=\"submit\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.shadow-toaster")));
		driver.findElement(By.cssSelector("div.shadow-toaster>div>button")).click();
		
		//Check by test purchasing that the product can be purchased in test account
		driver.switchTo().window(Checkout_page);
		driver.navigate().refresh();
		
		try
		{
			driver.findElement(By.xpath("//span[text()[normalize-space() = 'Order Summary']]"));
		}
		catch(TimeoutException e)
		{
			Assert.assertTrue(false, "Product Not Get Set as active");
		}
		
		//Filling Contact information
		driver.findElement(By.id("email")).sendKeys(WMLogin);
		driver.findElement(By.id("first_name")).sendKeys(F_Name);
		driver.findElement(By.id("last_name")).sendKeys(L_Name);
		driver.findElement(By.id("phone")).sendKeys(Number);
		driver.findElement(By.id("street")).sendKeys(Street_Add);
		driver.findElement(By.id("state")).sendKeys(State);
		driver.findElement(By.id("city")).sendKeys(City);
		driver.findElement(By.id("postal_code")).sendKeys(Pincode);
		
		//Payment Information
		driver.findElement(By.id("cardHolderName")).sendKeys(F_Name+" "+L_Name);
		driver.findElement(By.id("cardNumber")).sendKeys(Card_Number);
		driver.findElement(By.id("cardCVV")).sendKeys(CVV);
		
		driver.findElement(By.cssSelector("button[aria-controls=\"monthDropdown\"]>div")).click();
		driver.findElement(By.cssSelector("#monthDropdown>button")).click();
		driver.findElement(By.cssSelector("button[aria-controls=\"yearDropdown\"]>div")).click();
		driver.findElement(By.cssSelector("#yearDropdown>button:nth-of-type(2)")).click();

		driver.findElement(By.cssSelector("button[type = 'submit']")).click();
		Thread.sleep(3000);
		
		//Validate order Summery - Customer details
		String Order_Name = driver.findElement(By.xpath("//span[text() = 'Customer']//following-sibling::div//span")).getText().trim();
		String Order_Mail = driver.findElement(By.xpath("//span[text() = 'Customer']//following-sibling::div//p")).getText().trim();
		String Order_Phone = driver.findElement(By.xpath("//span[text() = 'Customer']//following-sibling::div//span[2]")).getText().replace("-", "");
		
		System.out.println(Order_Name);
		System.out.println(Order_Mail);
		System.out.println(Order_Phone);
		
		//Validate order Summery - Billing details
		
		//Check if while creating product toggle on course is working and product is getting shared in courses 
		
	}
}