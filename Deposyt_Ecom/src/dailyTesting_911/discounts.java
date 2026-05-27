package dailyTesting_911;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class discounts extends Data {
	java.util.Random r = new java.util.Random();

	String Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
			SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
			Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(),
			
			ContactPhone1 = "(775) 986-5200",F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", country = "United States", ContactPhone2 = "(539) 321-3502",
			Account_Holder_Name = "DeposytCert", Routing_Number = "490000018", Account_Number = "000123456789", EXP = "12/44", CVV = "123", Card_No = "4242424242424242",
			chars = "abcdefghijklmnopqrstuvwxyz",Price = "2", Value = "1",susbcriptionPrice = "3",Fileone = "file1.jpg", Attachment = "Jira_Guide.pdf",Country_Add = "Boardman, Oregon, 97818",
					
			firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
			lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
			email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com", phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));

	@Test(priority = 1)
	public void Create_Discount() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		String Region_Name = "Europe";
		String DiscountAmount = "10";		
		String coupon = "";
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String nums  = "0123456789";

		for (int i = 0; i < 6; i++)
			coupon += chars.charAt((int)(Math.random() * chars.length()));

		for (int i = 0; i < 3; i++)
			coupon += nums.charAt((int)(Math.random() * nums.length()));
		
		//Check fixed amount discount can be created
		driver.navigate().to(Discounts);
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='+ Add Discount']"))).click(); // click on new discount button
		driver.findElement(By.cssSelector("button#fixed")).click();
		driver.findElement(By.cssSelector("div[data-orientation=\"vertical\"]:nth-of-type(2)>h3>div:first-of-type")).click();

		driver.findElement(By.cssSelector("#regions>div")).click(); //click on region drop down
		driver.findElement(By.id("react-select-2-option-0")).click(); //Select Default as region
		Thread.sleep(2000);
		driver.findElement(By.name("code")).sendKeys(coupon,Keys.TAB); //input code
		driver.switchTo().activeElement().sendKeys("10");
		driver.findElement(By.name("rule.description")).sendKeys("This discount is created for 911 Automation Test");

		//Check we can add Configurations ON DiScount 
		//A1. We Can Add Discount has a start date & Expire Date 
		//A2 We Can Add Discount has a Limit the number of redemtions
		driver.findElement(By.cssSelector("div[data-orientation=\"vertical\"]:nth-of-type(3)>h3>div:first-of-type")).click();
		driver.findElement(By.xpath("(//button[@value='on'])[1]")).click(); //Click on first toggle button
		driver.findElement(By.xpath("(//button[@value='on'])[2]")).click(); //Click on second toggle button
		driver.findElement(By.xpath("(//button[@value='on'])[3]")).click(); //Click on Third toggle button
		Thread.sleep(2000);
		driver.findElement(By.name("usage_limit")).sendKeys(susbcriptionPrice); //Click on Fourth toggle button

		//A3 We can add the Conditions on discount
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[data-orientation=\"vertical\"]:nth-of-type(4)>h3>div:first-of-type")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Add Condition']/parent::span/parent::button")).click(); //Click on add condition button
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[normalize-space()='Only for specific products'])[1]/parent::div/parent::button")).click(); //Select only for specific product condition
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("th[role='columnheader']:first-of-type")).click(); //Click on select product in condition
		driver.findElement(By.xpath("(//span[normalize-space()='Save and add more'])[1]")).click(); //Select the product in condition

		driver.findElement(By.xpath("(//div[normalize-space()='Only for specific product types'])[1]/parent::div/parent::button")).click(); //Click on apply button in types
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#unselectableTableProductType>thead>tr>th:first-of-type")).click(); //Click on select type in condition
		driver.findElement(By.xpath("(//span[normalize-space()='Save and close'])[1]")).click(); 

		Thread.sleep(3000);
		WebElement publishButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.custom-setting-region-tab > div > button:nth-of-type(2)")));
		jse.executeScript("arguments[0].scrollIntoView(true);", publishButton);
		wait.until(ExpectedConditions.elementToBeClickable(publishButton));
		jse.executeScript("arguments[0].click();", publishButton);
		Thread.sleep(3000);

		//Check discount is getting applied on checkout
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);

		String originalTab = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(10000);		
		driver.findElement(By.id("couponCode1")).sendKeys(coupon); //Input gift card code in cart
		driver.findElement(By.id("applyDiscountCoupon")).click(); //Click on apply button

		Thread.sleep(2000);
		String discountapplied = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#appliedDiscountCoupon"))).getText().trim();
		System.out.println("Discount applied : " + discountapplied);
		Assert.assertTrue(discountapplied.contains(coupon), "Discount code is not applied successfully");
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Regions']/parent::div/parent::button"))).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div:nth-of-type(2)>div:last-of-type>div>div>div>div>div>button")).click(); //Click on Add region button
		Thread.sleep(2000);
		driver.findElement(By.name("details.name")).sendKeys(Region_Name); //Enter region name
		driver.findElement(By.id("details.currency_code")).click();
		driver.findElement(By.id("react-select-2-option-0")).click();
		driver.findElement(By.id("details.countries")).click(); //Click on country drop down
		WebElement countryInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-3-option-0")));
		countryInput.click();	
		jse.executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[normalize-space()='Create Region']//parent::button")));
		Thread.sleep(2000);

		//Check region of account and then apply discount on another region to check if it gets applied on another region chechout link or not
		driver.navigate().to(Discounts);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:last-of-type>div>button")).click(); //Click on edit button of product
		driver.findElement(By.xpath("(//div[contains(@role,'menuitem')])[1]/button")).click(); //click on edit
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div div:nth-of-type(2) button")).click();
		driver.findElement(By.xpath("//span[contains(text(),\"Edit general information\")]")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.discount-mobile-class")).click(); //click on region drop
		driver.findElement(By.id("react-select-2-option-0")).click(); //Select another region
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save and close\"]")).click(); //Click on publish button

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}	

		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.id("couponCode1")).sendKeys(coupon); //Input gift
		driver.findElement(By.id("applyDiscountCoupon")).click(); //Click on apply button
		Thread.sleep(2000);
		String discountapplied1 = driver.findElement(By.cssSelector("div.animate-enter>div:nth-of-type(2)>span:last-of-type")).getText().trim();
		System.out.println("Discount applied on another region checkout : " + discountapplied1);
		Assert.assertEquals(discountapplied1,"Discount cannot be used in the selected region.","Wrong error message displayed for region restriction");

		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Discounts);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:last-of-type>div>button")).click(); //Click on edit button of product
		driver.findElement(By.xpath("(//div[contains(@role,'menuitem')])[1]/button")).click(); //click on edit
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div div:nth-of-type(2) button")).click();
		driver.findElement(By.xpath("//span[contains(text(),\"Edit general information\")]")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.discount-mobile-class")).click(); //click on region drop
		driver.findElement(By.id("react-select-2-option-1")).click(); //Select another region
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save and close\"]")).click(); //Click on publish button

		//Check discount is getting applied in vt
		Thread.sleep(5000);
		driver.navigate().to(Virtual_Terminal);

		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search Customer\"]")).sendKeys(ContactPhone2); //Select first customer from the list
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,'overflow-y-auto')]/div[contains(@class,'mb-[5px]')][1]")).click(); //Select first customer from the list
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]")).click(); //Clicking on add line items button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); //Clicking on item name field
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[text()='Create New Item']]/parent::div/following-sibling::div[1]")).click(); //Clicking on first product from the dropdown
		Thread.sleep(1000);

		WebElement discountTab = driver.findElement(By.xpath("(//span[normalize-space(text())='Discounts'])[1]"));
		jse.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", discountTab);
		Thread.sleep(2000);
		jse.executeScript("arguments[0].click();",driver.findElement(By.xpath("//p[text()='Add Discount']//parent::span/parent::button")));

		driver.findElement(By.xpath("//div[@id=\"discounts.0.code\"]")).click(); //Select discount
		Thread.sleep(3000);
		jse.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("div.new-product-popup>div:nth-of-type(2)>div:first-of-type")));
		Thread.sleep(2000);
		String discountappliedvt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.discount-item-total-class > div"))).getText().replaceAll(".*?(\\d+)(?:\\.\\d+)?", "$1");
		System.out.println("Discount applied in VT : " + discountappliedvt);
		Assert.assertEquals(discountappliedvt, DiscountAmount);
		System.out.println("Discount is applied successfully in VT\n");
		
		try {
			driver.navigate().to(settings);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Regions']/parent::div/parent::button"))).click(); //Click on Region button
			WebElement europeHeading = driver.findElement(By.xpath("//h1[normalize-space()='Europe']"));
			jse.executeScript("arguments[0].scrollIntoView(true);", europeHeading);
			Assert.assertTrue(europeHeading.isDisplayed(),"Europe heading is not displayed");
			
			driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[1]")).click(); 
			driver.findElement(By.xpath("//span[contains(text(),\"Delete Region\")]")).click();
			driver.findElement(By.cssSelector("input.remove-number-spinner")).sendKeys(Region_Name);
			driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();	
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			Assert.fail("Region not found: " + e.getMessage());
		}
	}
}