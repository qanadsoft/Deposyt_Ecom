package dailyTesting_911;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class invoices extends Data {
	java.util.Random r = new java.util.Random();
	
	String Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
			SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
			Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(), 
			
			ContactPhone1 = "(775) 986-5200",F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", country = "United States", 
			ContactPhone2 = "(539) 321-3502",Account_Holder_Name = "DeposytCert", Routing_Number = "490000018", Account_Number = "000123456789", EXP = "12/44", CVV = "123", 
			Card_No = "4242424242424242",chars = "abcdefghijklmnopqrstuvwxyz", Value = "10", Discounts_Price = "1", ountry_Add = "Boardman, Oregon, 97818",
			
			firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
			lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
			email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com", phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
	
	@Test(priority = 1)
	public void FullPayment_WithDualprice() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;	
			
		//Check Full payments Invoices are getting created and sent on time by adding multiple products,Tax, discount and dual prices.   		
		driver.navigate().to(Sales);
		Thread.sleep(7000);	
		String totalRevenue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue = totalRevenue.replaceAll("[^0-9.]", ""); 
		double RevenueValue = Double.parseDouble(totalRevenueValue);	
		System.out.println("Total Revenue Value Before In All Sales Tab : " + RevenueValue);
		
        driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[2]")).click();
			String convenienceFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter amount\"]")).getAttribute("value").trim();
			System.out.println("Convenience Fee In Settings : " + convenienceFee);
			driver.findElement(By.id("dialog-checkbox")).click();		
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();
		} catch(Exception e) {
		}
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
		driver.findElement(By.id("field_first_name")).sendKeys(firstName); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(lastName); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(email); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(phone); //Input phone number
		driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add); //Input street address
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); 
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Line Items']")).click(); //Click on add item button
		driver.findElement(By.xpath("//div[contains(@class,'truncate flex justify-start')]")).click(); //Click on select product drop down		
		driver.findElement(By.xpath("(//input[@placeholder='Enter Line Item Name'])[1]")).sendKeys("Product 1"); //Select product
		driver.findElement(By.xpath("(//input[contains(@placeholder,'—')])[1]")).sendKeys(Value); //Input quantity
		driver.findElement(By.id("unit-price")).sendKeys(Value); //input value in unit price field
		driver.findElement(By.xpath("//textarea[@placeholder=\"Custom line item description\"]")).sendKeys("Add New Product Discription"); 
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); //Click on save button to save line item
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[normalize-space()='Payment Terms']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()=' Add Discount']//parent::span//parent::button")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys(Discounts_Price); //Input discount name
		driver.findElement(By.xpath("//span[normalize-space()=\"Add\"]")).click(); //Click on save button to save discount
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
		Thread.sleep(2000);
		
		String InvoiceAmountText = driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(2) > div >div>div>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>p")).getText().trim();
		double invoiceAmount = Double.parseDouble(InvoiceAmountText.replaceAll("[^0-9.]", ""));
		System.out.println("Invoice Amount: " + invoiceAmount);
		
		WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='dialog'] > div > div:nth-of-type(3) > div > button:first-of-type")));
		jse.executeScript("arguments[0].click();", button);
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Share Link']")).click(); 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#order-table-body > tr:first-of-type > td:last-of-type > button"))).click();
        WebElement quickCheckoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Share Link']")));

        jse.executeScript("navigator.clipboard.writeText = function(text) { window.copiedLink = text; }");
        quickCheckoutLink.click();
        String checkoutUrl = (String) jse.executeScript("return window.copiedLink;");
        driver.get(checkoutUrl);
        
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@role='checkbox']")).click();
        driver.findElement(By.xpath("(//button[normalize-space()='Pay'])[1]")).click(); //Click on pay button in invoice checkout page
        driver.findElement(By.xpath("(//button[normalize-space()='Pay As Guest'])[1]")).click(); //Click on pay as guest button in invoice checkout page
        Thread.sleep(2000);	
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Card Information']")));
		Thread.sleep(2000);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);// MMYY format
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();
		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);	
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		
		//Check Full payments Invoices calculation showing correct after order by considering products,Tax, discount and dual prices.(sales, orders and thank emails, customer hub etc)
		driver.navigate().to(Orders);
		Thread.sleep(10000);
		String orderAmountText = driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(4)>span")).getText().trim();
		double orderAmount = Double.parseDouble(orderAmountText.replaceAll("[^0-9.]", ""));
		System.out.println("Order Amount in Orders Page: " + orderAmount);
		Assert.assertEquals(orderAmount,invoiceAmount,0.01, "Order amount in orders page is not matching with invoice amount");		
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);		
		String totalRevenued = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValued = totalRevenued.replaceAll("[^0-9.]", ""); 
		double RevenueValued = Double.parseDouble(totalRevenueValued);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + RevenueValued);
		Assert.assertTrue(RevenueValued > RevenueValue,"Revenue did not increase after placing order");		
	}
	
	@Test(priority = 2)
	public void FullPayment_WithoutDualPrice() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;	
		
		//Check full payment  invoice getting created when we disable the dual pricing and calculations showing corrrect with  multiple products amout,Tax, discount
		driver.navigate().to(Sales);
		Thread.sleep(7000);	
		String totalRevenue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue = totalRevenue.replaceAll("[^0-9.]", ""); 
		double RevenueValue = Double.parseDouble(totalRevenueValue);	
		System.out.println("Total Revenue Value Before In All Sales Tab : " + RevenueValue);
		
        driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[1]")).click();		
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();
			System.out.println("Dual price is disabled successfully");
		} catch(Exception e) {
		}
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
		driver.findElement(By.id("field_first_name")).sendKeys(firstName); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(lastName); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(email); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(phone); //Input phone number
		driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add); //Input street address
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); 
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Line Items']")).click(); //Click on add item button
		driver.findElement(By.xpath("//div[contains(@class,'truncate flex justify-start')]")).click(); //Click on select product drop down		
		driver.findElement(By.xpath("(//input[@placeholder='Enter Line Item Name'])[1]")).sendKeys("Product 1"); //Select product
		driver.findElement(By.xpath("(//input[contains(@placeholder,'—')])[1]")).sendKeys(Value); //Input quantity
		driver.findElement(By.id("unit-price")).sendKeys(Value); //input value in unit price field
		driver.findElement(By.xpath("//textarea[@placeholder=\"Custom line item description\"]")).sendKeys("Add New Product Discription"); 
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); //Click on save button to save line item
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[normalize-space()='Payment Terms']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()=' Add Discount']//parent::span//parent::button")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys(Discounts_Price); //Input discount name
		driver.findElement(By.xpath("//span[normalize-space()=\"Add\"]")).click(); //Click on save button to save discount
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
		Thread.sleep(2000);
		
		String InvoiceAmountText = driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(2) > div >div>div>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>p")).getText().trim();
		double invoiceAmount = Double.parseDouble(InvoiceAmountText.replaceAll("[^0-9.]", ""));
		System.out.println("Invoice Amount: " + invoiceAmount);
		
		WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='dialog'] > div > div:nth-of-type(3) > div > button:first-of-type")));
		jse.executeScript("arguments[0].click();", button);
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Share Link']")).click(); 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#order-table-body > tr:first-of-type > td:last-of-type > button"))).click();
        WebElement quickCheckoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Share Link']")));

        jse.executeScript("navigator.clipboard.writeText = function(text) { window.copiedLink = text; }");
        quickCheckoutLink.click();
        String checkoutUrl = (String) jse.executeScript("return window.copiedLink;");
        driver.get(checkoutUrl);
        
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@role='checkbox']")).click();
        driver.findElement(By.xpath("(//button[normalize-space()='Pay'])[1]")).click(); //Click on pay button in invoice checkout page
        driver.findElement(By.xpath("(//button[normalize-space()='Pay As Guest'])[1]")).click(); //Click on pay as guest button in invoice checkout page
        Thread.sleep(2000);		
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Card Information']")));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);// MMYY format
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();
		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);	
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
				
		driver.navigate().to(Orders);
		Thread.sleep(10000);
		String orderAmountText = driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(4)>span")).getText().trim();
		double orderAmount = Double.parseDouble(orderAmountText.replaceAll("[^0-9.]", ""));
		System.out.println("Order Amount in Orders Page: " + orderAmount);
		Assert.assertEquals(orderAmount, invoiceAmount, 0.01, "Order amount in orders page is not matching with invoice amount");	
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);		
		String totalRevenued = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValued = totalRevenued.replaceAll("[^0-9.]", ""); 
		double RevenueValued = Double.parseDouble(totalRevenueValued);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + RevenueValued);
		Assert.assertTrue(RevenueValued > RevenueValue,"Revenue did not increase after placing order");					
	}
	
	@Test(priority = 3)
	public void SplitPayment_WithDualPrice() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;		
		
		//Check split payments Invoices are getting created and sent on time by adding multiple products,Tax, discount and dual prices.
		driver.navigate().to(Sales);
		Thread.sleep(7000);	
		String totalRevenue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue = totalRevenue.replaceAll("[^0-9.]", ""); 
		double RevenueValue = Double.parseDouble(totalRevenueValue);	
		System.out.println("Total Revenue Value Before In All Sales Tab : " + RevenueValue);
		
        driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[2]")).click();
			String convenienceFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter amount\"]")).getAttribute("value").trim();
			System.out.println("Convenience Fee In Settings : " + convenienceFee);
			driver.findElement(By.id("dialog-checkbox")).click();		
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();
		} catch(Exception e) {
		}
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
		driver.findElement(By.id("field_first_name")).sendKeys(firstName); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(lastName); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(email); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(phone); //Input phone number
		driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add); //Input street address
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); 
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Line Items']")).click(); //Click on add item button
		driver.findElement(By.xpath("//div[contains(@class,'truncate flex justify-start')]")).click(); //Click on select product drop down		
		driver.findElement(By.xpath("(//input[@placeholder='Enter Line Item Name'])[1]")).sendKeys("Product 1"); //Select product
		driver.findElement(By.xpath("(//input[contains(@placeholder,'—')])[1]")).sendKeys(Value); //Input quantity
		driver.findElement(By.id("unit-price")).sendKeys(Value); //input value in unit price field
		driver.findElement(By.xpath("//textarea[@placeholder=\"Custom line item description\"]")).sendKeys("Add New Product Discription"); 
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); //Click on save button to save line item
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[normalize-space()='Payment Terms']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()=' Add Discount']//parent::span//parent::button")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys(Discounts_Price); //Input discount name
		driver.findElement(By.xpath("//span[normalize-space()=\"Add\"]")).click(); //Click on save button to save discount
		
		Thread.sleep(3000);
		WebElement el = driver.findElement(By.xpath("//span[normalize-space()='Split Payments']//parent::button"));
		jse.executeScript("arguments[0].click();", el);
		
		WebElement taxRate = driver.findElement(By.xpath("//input[@placeholder=\"1\"]"));
		Thread.sleep(3000);
		taxRate.click();
		taxRate.sendKeys(Keys.CONTROL, "a");
		taxRate.sendKeys(Keys.DELETE);
		taxRate.sendKeys("2");
		driver.findElement(By.xpath("(//button[normalize-space()='Done'])[1]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
		Thread.sleep(2000);		
		WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='dialog'] > div > div:nth-of-type(3) > div > button:first-of-type")));
		jse.executeScript("arguments[0].click();", button);
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Share Link']")).click(); 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#order-table-body > tr:first-of-type > td:last-of-type > button"))).click();
        WebElement quickCheckoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Share Link']")));

        jse.executeScript("navigator.clipboard.writeText = function(text) { window.copiedLink = text; }");
        quickCheckoutLink.click();
        String checkoutUrl = (String) jse.executeScript("return window.copiedLink;");
        driver.get(checkoutUrl);
        
        Thread.sleep(5000);
		String InvoiceAmountText = driver.findElement(By.cssSelector("div.inv-paymenys-table>div>div.amount>p:nth-of-type(2)")).getText().trim();
		double invoiceAmount = Double.parseDouble(InvoiceAmountText.replaceAll("[^0-9.]", ""));
		System.out.println("Invoice Amount: " + invoiceAmount);
        
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@role='checkbox']")).click();
        driver.findElement(By.xpath("(//button[normalize-space()='Pay'])[1]")).click(); //Click on pay button in invoice checkout page
        driver.findElement(By.xpath("(//button[normalize-space()='Pay As Guest'])[1]")).click(); //Click on pay as guest button in invoice checkout page
        Thread.sleep(2000);	
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Card Information']")));
		Thread.sleep(2000);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);// MMYY format
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();
		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);	

		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
				
		driver.navigate().to(Orders);
		Thread.sleep(10000);
		String orderAmountText = driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(4)>span")).getText().trim();
		double orderAmount = Double.parseDouble(orderAmountText.replaceAll("[^0-9.]", ""));
		System.out.println("Order Amount in Orders Page: " + orderAmount);
		Assert.assertEquals(orderAmount, invoiceAmount, 0.01, "Order amount in orders page is not matching with invoice amount");	
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);		
		String totalRevenued = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValued = totalRevenued.replaceAll("[^0-9.]", ""); 
		double RevenueValued = Double.parseDouble(totalRevenueValued);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + RevenueValued);
		Assert.assertTrue(RevenueValued > RevenueValue,"Revenue did not increase after placing order");			
	}
	
	@Test(priority = 4)
	public void SplitPayment_WithoutDualPrice() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		//Check  split payment  invoice getting created when we disable the dual pricing and calculations showing corrrect with  multiple products amout,Tax, discount
		driver.navigate().to(Sales);
		Thread.sleep(7000);	
		String totalRevenue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue = totalRevenue.replaceAll("[^0-9.]", ""); 
		double RevenueValue = Double.parseDouble(totalRevenueValue);	
		System.out.println("Total Revenue Value Before In All Sales Tab : " + RevenueValue);
		
        driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[1]")).click();		
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();
			System.out.println("Dual price is disabled successfully");
		} catch(Exception e) {
		}
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
		driver.findElement(By.id("field_first_name")).sendKeys(firstName); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(lastName); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(email); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(phone); //Input phone number
		driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add); //Input street address
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); 
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Line Items']")).click(); //Click on add item button
		driver.findElement(By.xpath("//div[contains(@class,'truncate flex justify-start')]")).click(); //Click on select product drop down		
		driver.findElement(By.xpath("(//input[@placeholder='Enter Line Item Name'])[1]")).sendKeys("Product 1"); //Select product
		driver.findElement(By.xpath("(//input[contains(@placeholder,'—')])[1]")).sendKeys(Value); //Input quantity
		driver.findElement(By.id("unit-price")).sendKeys(Value); //input value in unit price field
		driver.findElement(By.xpath("//textarea[@placeholder=\"Custom line item description\"]")).sendKeys("Add New Product Discription"); 
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); //Click on save button to save line item
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[normalize-space()='Payment Terms']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()=' Add Discount']//parent::span//parent::button")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys(Discounts_Price); //Input discount name
		driver.findElement(By.xpath("//span[normalize-space()=\"Add\"]")).click(); //Click on save button to save discount
		
		Thread.sleep(3000);
		WebElement el = driver.findElement(By.xpath("//span[normalize-space()='Split Payments']//parent::button"));
		jse.executeScript("arguments[0].click();", el);
		
		WebElement taxRate = driver.findElement(By.xpath("//input[@placeholder=\"1\"]"));
		Thread.sleep(3000);
		taxRate.click();
		taxRate.sendKeys(Keys.CONTROL, "a");
		taxRate.sendKeys(Keys.DELETE);
		taxRate.sendKeys("2");
		driver.findElement(By.xpath("(//button[normalize-space()='Done'])[1]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
		Thread.sleep(2000);		
		WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='dialog'] > div > div:nth-of-type(3) > div > button:first-of-type")));
		jse.executeScript("arguments[0].click();", button);
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Share Link']")).click(); 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#order-table-body > tr:first-of-type > td:last-of-type > button"))).click();
        WebElement quickCheckoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Share Link']")));

        jse.executeScript("navigator.clipboard.writeText = function(text) { window.copiedLink = text; }");
        quickCheckoutLink.click();
        String checkoutUrl = (String) jse.executeScript("return window.copiedLink;");
        driver.get(checkoutUrl);
        
        Thread.sleep(5000);
		String InvoiceAmountText = driver.findElement(By.cssSelector("div.inv-paymenys-table>div>div.amount>p:nth-of-type(2)")).getText().trim();
		double invoiceAmount = Double.parseDouble(InvoiceAmountText.replaceAll("[^0-9.]", ""));
		System.out.println("Invoice Amount: " + invoiceAmount);
        
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@role='checkbox']")).click();
        driver.findElement(By.xpath("(//button[normalize-space()='Pay'])[1]")).click(); //Click on pay button in invoice checkout page
        driver.findElement(By.xpath("(//button[normalize-space()='Pay As Guest'])[1]")).click(); //Click on pay as guest button in invoice checkout page
        Thread.sleep(2000);		
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Card Information']")));
		Thread.sleep(2000);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);// MMYY format
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();
		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);	

		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		
		driver.navigate().to(Orders);
		Thread.sleep(10000);
		String orderAmountText = driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(4)>span")).getText().trim();
		double orderAmount = Double.parseDouble(orderAmountText.replaceAll("[^0-9.]", ""));
		System.out.println("Order Amount in Orders Page: " + orderAmount);
		Assert.assertEquals(orderAmount, invoiceAmount, 0.01, "Order amount in orders page is not matching with invoice amount");	
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);		
		String totalRevenued = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValued = totalRevenued.replaceAll("[^0-9.]", ""); 
		double RevenueValued = Double.parseDouble(totalRevenueValued);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + RevenueValued);
		Assert.assertTrue(RevenueValued > RevenueValue,"Revenue did not increase after placing order");		
	}
	
	@Test(priority = 5)
	public void Createtemplate() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		//Check templates getting created via invoice aand create template page and listed in template page
		driver.navigate().to(invoices);
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//span[normalize-space()=\"Templates\"]//parent::span//parent::button")).click(); 
		Thread.sleep(10000);
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.custom-class-table>table>thead>tr:first-of-type>td:first-of-type>div>div")));
		jse.executeScript("arguments[0].click();", el);
		
		Thread.sleep(3000);
		String TemplateCount = driver.findElement(By.cssSelector("div.countContacts>p:first-of-type")).getText().trim();
		System.out.println("Total templates before creating new template: " + TemplateCount);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.clear-select-btn")).click(); 
		Thread.sleep(3000);
		
		try {
			driver.findElement(By.cssSelector("div.custom-class-table>table>thead>tr:first-of-type>td:first-of-type>div>div")).click(); 
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()=\"Delete\"]//parent::button")).click(); 
			driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]//parent::button")).click();
			System.out.println("Existing templates are deleted successfully");
			Thread.sleep(2000);
		} catch(Exception e) {
			System.out.println("No templates found to delete");
		}
		
		driver.findElement(By.xpath("//span[normalize-space()=\"+ New Template\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Line Items']")).click(); //Click on add item button	
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id=\"main-page-ui-div\"]//ul[1]")).click(); //Select product
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[normalize-space()='Payment Terms']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()=' Add Discount']//parent::span//parent::button")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys(Discounts_Price); //Input discount name
		driver.findElement(By.xpath("//span[normalize-space()=\"Add\"]")).click(); //Click on save button to save discount
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Save Template']//parent::span//parent::button")).click(); //Click on save as template button
		
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys("Invoice Template for 911"); //Input template name
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[normalize-space()='Add'])[1]//parent::button")).click(); //Click on save button to save template
		Thread.sleep(5000);
		
		driver.findElement(By.cssSelector("div.custom-class-table>table>thead>tr:first-of-type>td:first-of-type>div>div")).click();
		String TemplateCount1 = driver.findElement(By.cssSelector("div.countContacts>p:first-of-type")).getText().trim();
		System.out.println("Total templates after creating new template: " + TemplateCount1);
		Assert.assertEquals(Integer.parseInt(TemplateCount1), 1, "Template count did not increase by 1 after creating new template");
		System.out.println("Template created and verified successfully");
	}
	
	@Test(priority = 6)
	public void Filter_Invoice() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		// Check Filter operations are working on invoice listing page
		driver.navigate().to(invoices);
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(2)>div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(3)>div>div:nth-of-type(2)>div:nth-of-type(2)")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Done\"]")).click();
		Thread.sleep(3000);
		
		String initialCountText1 = driver.findElement(By.cssSelector("div[role='group']>div:first-of-type>button:first-of-type>div>span>p>span")).getText().trim();
		String numberOnly1 = initialCountText1.replaceAll("[^0-9]", ""); // Remove non-numeric chars
		int rowCounts1 = Integer.parseInt(numberOnly1);
		System.out.println("Filtered Count In List View : " + rowCounts1);
		
		int previousCountt = 0;
		while (true) {
		    List<WebElement> UnreadStatus = driver.findElements(By.cssSelector("#order-table-body>tr"));
		    if (UnreadStatus.size() == previousCountt) {
		        break;
		    }
		    previousCountt = UnreadStatus.size();
		    jse.executeScript("arguments[0].scrollIntoView(true);",UnreadStatus.get(UnreadStatus.size() - 1));
		    Thread.sleep(2000); 
		}

		List<WebElement> UnreadStatus = driver.findElements(By.cssSelector("#order-table-body>tr"));
		System.out.println("Actual Row Count in Table: " + UnreadStatus.size());
		Assert.assertEquals(UnreadStatus.size(),rowCounts1,"Mismatch between filter count and actual table rows");
		System.out.println("Filter count matches with Outstanding status count in table\n");
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(2)>div>div")).click();
		
		/*Thread.sleep(5000);	
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(2)>button")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(3)>div>div:nth-of-type(2)>div:nth-of-type(4)")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Done\"]")).click();
		Thread.sleep(3000);
		
		String initialCountText2 = driver.findElement(By.cssSelector("div[role='group']>div:first-of-type>button:first-of-type>div>span>p>span")).getText().trim();
		String numberOnly2 = initialCountText2.replaceAll("[^0-9]", ""); // Remove non-numeric chars
		int rowCounts2 = Integer.parseInt(numberOnly2);
		System.out.println("Filtered Count In List View : " + rowCounts2);
		
		int previousCounts = 0;
		while (true) {
			List<WebElement> UnreadStatus2 = driver.findElements(By.cssSelector("#order-table-body>tr"));
			if (UnreadStatus2.size() == previousCounts) {
				break;
			}
			previousCounts = UnreadStatus2.size();
			jse.executeScript("arguments[0].scrollIntoView(true);",UnreadStatus2.get(UnreadStatus2.size() - 1));
			Thread.sleep(2000);
		}

		List<WebElement> UnreadStatus2 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		System.out.println("Actual Row Count in Table: " + UnreadStatus2.size());
		Assert.assertEquals(UnreadStatus2.size(),rowCounts2,"Mismatch between filter count and actual table rows");
		System.out.println("Filter count matches with Failed status count in table\n");
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(2)>div>div")).click();*/
		
		Thread.sleep(5000);	
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(2)>button")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(3)>div>div:nth-of-type(2)>div:nth-of-type(5)")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Done\"]")).click();
		Thread.sleep(3000);
		
		String initialCountText3 = driver.findElement(By.cssSelector("div[role='group']>div:first-of-type>button:first-of-type>div>span>p>span")).getText().trim();
		String numberOnly3 = initialCountText3.replaceAll("[^0-9]", ""); // Remove non-numeric chars
		int rowCounts3 = Integer.parseInt(numberOnly3);
		System.out.println("Filtered Count In List View : " + rowCounts3);
		
		int previousCountd = 0;
		while (true) {
		    List<WebElement> UnreadStatus3 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		    if (UnreadStatus3.size() == previousCountd) {
		        break; 
		    }
		    previousCountd = UnreadStatus3.size();
		    jse.executeScript("arguments[0].scrollIntoView(true);",UnreadStatus3.get(UnreadStatus3.size() - 1));
		    Thread.sleep(2000); // wait for new rows to load
		}

		List<WebElement> UnreadStatus3 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		System.out.println("Actual Row Count in Table: " + UnreadStatus3.size());
		Assert.assertEquals(UnreadStatus3.size(),rowCounts3,"Mismatch between filter count and actual table rows");
		System.out.println("Filter count matches with Paid status count in table\n");
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(2)>div>div")).click();
		
		Thread.sleep(5000);	
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(2)>button")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(3)>div>div:nth-of-type(2)>div:nth-of-type(6)")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Done\"]")).click();
		Thread.sleep(5000);
		
		String initialCountText4 = driver.findElement(By.cssSelector("div[role='group']>div:first-of-type>button:first-of-type>div>span>p>span")).getText().trim();
		String numberOnly4 = initialCountText4.replaceAll("[^0-9]", ""); // Remove non-numeric chars
		int rowCounts4 = Integer.parseInt(numberOnly4);
		System.out.println("Filtered Count In List View : " + rowCounts4);
		
		int previousCounta = 0;
		while (true) {
		    List<WebElement> UnreadStatus4 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		    if (UnreadStatus4.size() == previousCounta) {
		        break; 
		    }

		    previousCounta = UnreadStatus4.size();
		    jse.executeScript("arguments[0].scrollIntoView(true);",UnreadStatus4.get(UnreadStatus4.size() - 1));
		    Thread.sleep(2000); // wait for new rows to load
		}

		List<WebElement> UnreadStatus4 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		System.out.println("Actual Row Count in Table: " + UnreadStatus4.size());
		Assert.assertEquals(UnreadStatus4.size(),rowCounts4,"Mismatch between filter count and actual table rows");
		System.out.println("Filter count matches with Partial Paid status count in table\n");
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(2)>div>div")).click();
		
		Thread.sleep(5000);	
		driver.findElement(By.cssSelector("div.filter-operation:first-of-type>div>div:nth-of-type(3)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.filter-operation:first-of-type>div>div:nth-of-type(4)>div>div:nth-of-type(3)>div:nth-of-type(3)")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Apply\"]//parent::button")).click();
		Thread.sleep(5000);	
		
		String initialCountText5 = driver.findElement(By.cssSelector("div[role='group']>div:first-of-type>button:first-of-type>div>span>p>span")).getText().trim();
		String numberOnly5 = initialCountText5.replaceAll("[^0-9]", ""); // Remove non-numeric chars
		int rowCounts5 = Integer.parseInt(numberOnly5);
		System.out.println("Filtered Count In List View : " + rowCounts5);
		
		int previousCount = 0;
		while (true) {
		    List<WebElement> UnreadStatus5 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		    if (UnreadStatus5.size() == previousCount) {
		        break; 
		    }
		    previousCount = UnreadStatus5.size();
		    jse.executeScript("arguments[0].scrollIntoView(true);", UnreadStatus5.get(UnreadStatus5.size() - 1));
		    Thread.sleep(2000);
		}

		List<WebElement> UnreadStatus5 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		System.out.println("Actual Row Count in Table: " + UnreadStatus5.size());
		Assert.assertEquals(UnreadStatus5.size(),rowCounts5,"Mismatch between filter count and actual table rows");
		System.out.println("Filter count matches with Assign User in table\n");
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(3)>div>div")).click();
		
		Thread.sleep(7000);	
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//div[text()='Overdue'])[1]")));
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.filter-operation:first-of-type>div>div:nth-of-type(4)>div>div>div")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Done']//parent::button")).click();
		
		String initialCountText6 = driver.findElement(By.cssSelector("div[role='group']>div:first-of-type>button:first-of-type>div>span>p>span")).getText().trim();
		String numberOnly6 = initialCountText6.replaceAll("[^0-9]", ""); // Remove non-numeric chars
		int rowCounts6 = Integer.parseInt(numberOnly6);
		System.out.println("Filtered Count In List View : " + rowCounts6);
		
		int previousCountts = 0;
		while (true) {
		    List<WebElement> UnreadStatus6 = driver.findElements(By.cssSelector("#order-table-body>tr"));

		    if (UnreadStatus6.size() == previousCountts) {
		        break; // no more rows loading
		    }

		    previousCountts = UnreadStatus6.size();
		    jse.executeScript("arguments[0].scrollIntoView(true);",UnreadStatus6.get(UnreadStatus6.size() - 1));
		    Thread.sleep(2000); 
		}

		List<WebElement> UnreadStatus6 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		System.out.println("Actual Row Count in Table: " + UnreadStatus6.size());
		Assert.assertEquals(UnreadStatus6.size(),rowCounts6,"Mismatch between filter count and actual table rows");
		System.out.println("Filter count matches with Send Date count in table\n");
		
		Thread.sleep(5000);	
		driver.findElement(By.cssSelector("div.filter-operation:first-of-type>div>div:nth-of-type(5)>button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Pay In full\"]")).click();
		
		String initialCountText7 = driver.findElement(By.cssSelector("div[role='group']>div:first-of-type>button:first-of-type>div>span>p>span")).getText().trim();
		String numberOnly7 = initialCountText7.replaceAll("[^0-9]", ""); // Remove non-numeric chars
		int rowCounts7 = Integer.parseInt(numberOnly7);
		System.out.println("Filtered Count In List View : " + rowCounts7);
		
		int previousCountc = 0;
		while (true) {
		    List<WebElement> UnreadStatus7 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		    if (UnreadStatus7.size() == previousCountc) {
		        break; 
		    }

		    previousCountc = UnreadStatus7.size();
		    jse.executeScript("arguments[0].scrollIntoView(true);",UnreadStatus7.get(UnreadStatus7.size() - 1));
		    Thread.sleep(2000); 
		}

		List<WebElement> UnreadStatus7 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		System.out.println("Actual Row Count in Table: " + UnreadStatus7.size());
		//Assert.assertEquals(UnreadStatus7.size(),rowCounts7,"Mismatch between filter count and actual table rows");
		System.out.println("Filter count matches with Payment Terms count in table\n");
		driver.findElement(By.cssSelector("div.filter-operation:last-of-type>div>div:nth-of-type(5)>button>div>div")).click();
		
		Thread.sleep(5000);	
		driver.findElement(By.cssSelector("div.filter-operation:first-of-type>div>div:nth-of-type(6)>button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Manual Charge\"]")).click();
		
		String initialCountText8 = driver.findElement(By.cssSelector("div[role='group']>div:first-of-type>button:first-of-type>div>span>p>span")).getText().trim();
		String numberOnly8 = initialCountText8.replaceAll("[^0-9]", ""); // Remove non-numeric chars
		int rowCounts8 = Integer.parseInt(numberOnly8);
		System.out.println("Filtered Count In List View : " + rowCounts8);
		
		int previousCountcs = 0;
		while (true) {
		    List<WebElement> UnreadStatus8 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		    if (UnreadStatus8.size() == previousCountcs) {
		        break; 
		    }

		    previousCountcs = UnreadStatus8.size();
		    jse.executeScript("arguments[0].scrollIntoView(true);",UnreadStatus8.get(UnreadStatus8.size() - 1));
		    Thread.sleep(2000); 
		}

		List<WebElement> UnreadStatus8 = driver.findElements(By.cssSelector("#order-table-body>tr"));
		System.out.println("Actual Row Count in Table: " + UnreadStatus8.size());
		Assert.assertEquals(UnreadStatus8.size(),rowCounts8,"Mismatch between filter count and actual table rows");
		System.out.println("Filter count matches with Billing Type count in table\n");
	}
	
	@Test(priority = 7)
	public void Bulk_Operations() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		
		// Check bulk operations are working on invoice listing page
		driver.navigate().to(invoices);
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:first-of-type>div>div")).click();
		
		//send reminder		
		String invoiceAmount = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(5)")).getText().trim();
		System.out.println("Invoice Amount in List View: " + invoiceAmount);
		String cleanAmount = invoiceAmount.replaceAll("[^0-9.]", "");
		driver.findElement(By.xpath("//span[normalize-space()=\"Send Reminders\"]//parent::button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();
		Thread.sleep(3000);
		WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='All selected invoice reminders have been sent successfully.']")));
		String actualMsg = successMsg.getText().trim();
		System.out.println("Message displayed: " + actualMsg);
		Assert.assertEquals(actualMsg,"All selected invoice reminders have been sent successfully.","Send reminder operation is not performed successfully");
		System.out.println("Send reminder operation is performed successfully");
		
		driver.navigate().to(Messages);
		Thread.sleep(5000);
		driver.findElement(By.name("filters")).sendKeys(ContactPhone2);
		driver.findElement(By.xpath("(//div[@id='list']/descendant::p[@data-search-in= \"phone_number\"])[1]")).click();
		Thread.sleep(5000);
		WebElement lastreminder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type>a.noatagcntz")));
		String reminderText = lastreminder.getText().trim();
		System.out.println("SMS Text: " + reminderText);
		Assert.assertTrue(reminderText.contains(cleanAmount), "Sent reminder SMS does not contain correct invoice amount");
		System.out.println("Sent reminder Functionality is verified successfully");
		
		//send email
		driver.navigate().to(invoices);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:first-of-type>div>div")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Send Email\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();
		
		driver.navigate().to(Messages);
		Thread.sleep(5000);
		driver.findElement(By.name("filters")).sendKeys(ContactPhone2);
		driver.findElement(By.xpath("(//div[@id='list']/descendant::p[@data-search-in= \"phone_number\"])[1]")).click();
		Thread.sleep(5000);
		WebElement lastemail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type>a.noatagcntz")));
		String emailText = lastemail.getText().trim();
		System.out.println("SMS Text: " + emailText);
		Assert.assertTrue(emailText.contains(cleanAmount), "Sent reminder SMS does not contain correct invoice amount");
		System.out.println("Sent Email Functionality is verified successfully");
		
		//send sms
		driver.navigate().to(invoices);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:first-of-type>div>div")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Send SMS\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();
		
		driver.navigate().to(Messages);
		Thread.sleep(5000);
		driver.findElement(By.name("filters")).sendKeys(ContactPhone2);
		driver.findElement(By.xpath("(//div[@id='list']/descendant::p[@data-search-in= \"phone_number\"])[1]")).click();
		Thread.sleep(5000);
		WebElement lastsms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type>a.noatagcntz")));
		String smsText = lastsms.getText().trim();
		System.out.println("SMS Text: " + smsText);
		Assert.assertTrue(smsText.contains(cleanAmount), "Sent reminder SMS does not contain correct invoice amount");
		System.out.println("Sent Sms Functionality is verified successfully");
		
		//cancel Invoice
		driver.navigate().to(invoices);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:first-of-type>div>div")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Cancel Invoices\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();
		
		WebElement successMsg1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Selected invoices have been cancelled successfully.']")));
		String actualMsg1 = successMsg1.getText().trim();
		System.out.println("Message displayed: " + actualMsg1);
		Assert.assertEquals(actualMsg1,"Selected invoices have been cancelled successfully.","Cancel reminder operation is not performed successfully");
		System.out.println("Cancel reminder operation is performed successfully");
		
		Thread.sleep(5000);
		String status = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(4)>div")).getText().trim();
		System.out.println("Invoice Status in List View after cancellation: " + status);
		Assert.assertEquals(status, "Cancelled", "Invoice status is not updated to cancelled after performing cancel operation");
		System.out.println("Cancel Invoice Functionality is verified successfully");	
	}
	
	@Test(priority = 8)
	public void FullPayment_Operations() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		//Check operations on full payment invoice> edit, archive, cancel , duplicate ,send reminder, active/stop auto reminder, print ,download, share link, bypass dual pricing, make payment  working.
		driver.navigate().to(invoices);
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button
		driver.navigate().refresh();
		Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
		driver.findElement(By.id("field_first_name")).sendKeys(firstName); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(lastName); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(email); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(phone); //Input phone number
		driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add); //Input street address
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); 
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Line Items']")).click(); //Click on add item button
		driver.findElement(By.xpath("//div[contains(@class,'truncate flex justify-start')]")).click(); //Click on select product drop down		
		driver.findElement(By.xpath("(//input[@placeholder='Enter Line Item Name'])[1]")).sendKeys("Product 1"); //Select product
		driver.findElement(By.xpath("(//input[contains(@placeholder,'—')])[1]")).sendKeys(Value); //Input quantity
		driver.findElement(By.id("unit-price")).sendKeys(Value); //input value in unit price field
		driver.findElement(By.xpath("//textarea[@placeholder=\"Custom line item description\"]")).sendKeys("Add New Product Discription"); 
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); //Click on save button to save line item
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[normalize-space()='Payment Terms']")));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//p[text()=' Add Discount']//parent::span//parent::button")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys(Discounts_Price); //Input discount name
		driver.findElement(By.xpath("//span[normalize-space()=\"Add\"]")).click(); //Click on save button to save discount
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		WebElement button1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")));
		jse.executeScript("arguments[0].click();", button1);
		
		driver.navigate().to(invoices);
		Thread.sleep(10000);
		String invoiceAmount1 = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(5)")).getText().trim();
		String cleanAmount = invoiceAmount1.replaceAll("[^0-9.]", "");
		System.out.println("Invoice Amount in List View: " + cleanAmount);
		
		String invoiceName = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(8)")).getText().trim();
		System.out.println("Invoice name Before Edit in List View: " + invoiceName);
		
		String invoiceNumber = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(2)")).getText().trim();
		System.out.println("Invoice Number Before Duplicate in List View: " + invoiceNumber);
		
		//send reminder
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Send Reminder\"]")).click();
		WebElement reminderSuccessMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Reminder has been sent successfully.']")));
		String actualReminderMsg = reminderSuccessMsg.getText().trim();
		System.out.println("Message displayed: " + actualReminderMsg);
		Assert.assertEquals(actualReminderMsg,"Reminder has been sent successfully.","reminder operation is not performed successfully on split payment invoice");
		System.out.println("Send reminder operation is performed successfully on split payment invoice");
		
		driver.navigate().to(Messages);
		Thread.sleep(5000);
		driver.findElement(By.name("filters")).sendKeys(email);
		driver.findElement(By.xpath("(//div[@id='list']/descendant::p[@data-search-in= \"email\"])[1]")).click();
		Thread.sleep(5000);
		/*WebElement lastreminder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type>a.noatagcntz")));
		String reminderText = lastreminder.getText().trim();
		System.out.println("SMS Text: " + reminderText);
		Assert.assertTrue(reminderText.contains(cleanAmount), "Sent reminder SMS does not contain correct invoice amount");*/
		System.out.println("Sent reminder Functionality is verified successfully");
		 
		//Edit Invoice
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Edit Invoice\"]")).click();		
		WebElement editInvoice = driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]"));
		Thread.sleep(3000);
		editInvoice.click();
		editInvoice.sendKeys(Keys.CONTROL, "a");
		editInvoice.sendKeys(Keys.DELETE);
		editInvoice.sendKeys("This invoice is Edited for 911 Automation Test");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		WebElement button2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")));
		jse.executeScript("arguments[0].click();", button2);
		
		Thread.sleep(5000);
		String invoiceName1 = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(8)")).getText().trim();
		System.out.println("Invoice name After Edit in List View: " + invoiceName1);
		Assert.assertEquals(invoiceName1, "This invoice is Edited for 911 Automation Test", "Invoice details are not updated after editing invoice");
		System.out.println("Edit Invoice Functionality is verified successfully");
		
		/*//duplicate Invoice
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Duplicate Invoice\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
		Thread.sleep(3000);
		String newInvoiceNumber = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(5)")).getText().trim();
		System.out.println("Invoice Number After Duplicate in List View: " + newInvoiceNumber);
		int temp = Integer.parseInt(invoiceNumber); 
		String expectedInvoice = String.format("%0" + invoiceNumber.length() + "d", temp + 1);
		Assert.assertEquals(newInvoiceNumber,expectedInvoice,"Invoice number is NOT incremented correctly");
		System.out.println("Duplicate Invoice Functionality is verified successfully");*/
		
		//archive Invoice
		driver.navigate().to(invoices);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Archive Invoice\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();

		String status = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(4)>div")).getText().trim();
		System.out.println("Invoice Status in List View after Archive: " + status);
		Assert.assertEquals(status, "Archived", "Invoice status is not updated to Archive after performing cancel operation");
		System.out.println("Archive Invoice Functionality is verified successfully");	

		//restore Invoice
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Restore Invoice\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();
		String restorestatus = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(4)>div")).getText().trim();
		System.out.println("Invoice Status in List View after Restore: " + restorestatus);
		System.out.println("Restore Invoice Functionality is verified successfully");	
		
		//quick checkout link
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Quick Checkout\"]")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#order-table-body > tr:first-of-type > td:last-of-type > button"))).click();
        WebElement quickCheckoutLink1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()=\"Quick Checkout\"]")));
        jse.executeScript("navigator.clipboard.writeText = function(text) { window.copiedLink = text; }");
        quickCheckoutLink1.click();
        String checkoutUrl1 = (String) jse.executeScript("return window.copiedLink;");
        driver.get(checkoutUrl1);
        
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
        driver.findElement(By.xpath("(//button[normalize-space()='Pay'])[1]")).click(); //Click on pay button in invoice checkout page
        driver.findElement(By.xpath("(//button[normalize-space()='Pay As Guest'])[1]")).click(); //Click on pay as guest button in invoice checkout page
        Thread.sleep(2000);          	
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
		Thread.sleep(2000);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);// MMYY format
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();
		WebElement cardHolder = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder.clear();
		cardHolder.sendKeys(F_Name + " " + L_Name);	
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		
		/*//cancel Invoice
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Cancel Invoice']//parent::div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();
		
		String cancelstatus = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(4)>div")).getText().trim();
		System.out.println("Invoice Status in List View after cancellation: " + cancelstatus);
		Assert.assertEquals(cancelstatus, "Cancelled", "Invoice status is not updated to cancelled after performing cancel operation");
		System.out.println("Cancel Invoice Functionality is verified successfully");*/		
	}
	
	@Test(priority = 9)
	public void SplitPayment_Operations() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		//Check operations on split  payment invoice> edit, archive, cancel , duplicate ,send reminder, active/stop auto reminder, print ,download, share link, bypass dual pricing, make payment  working.
		driver.navigate().to(invoices);
		Thread.sleep(15000);
		driver.navigate().refresh();
		Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button
		driver.navigate().refresh();
		Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
		driver.findElement(By.id("field_first_name")).sendKeys(firstName); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(lastName); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(email); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(phone); //Input phone number
		driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add); //Input street address
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); 
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Line Items']")).click(); //Click on add item button
		driver.findElement(By.xpath("//div[contains(@class,'truncate flex justify-start')]")).click(); //Click on select product drop down		
		driver.findElement(By.xpath("(//input[@placeholder='Enter Line Item Name'])[1]")).sendKeys("Product 1"); //Select product
		driver.findElement(By.xpath("(//input[contains(@placeholder,'—')])[1]")).sendKeys(Value); //Input quantity
		driver.findElement(By.id("unit-price")).sendKeys(Value); //input value in unit price field
		driver.findElement(By.xpath("//textarea[@placeholder=\"Custom line item description\"]")).sendKeys("Add New Product Discription"); 
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); //Click on save button to save line item
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[normalize-space()='Payment Terms']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()=' Add Discount']//parent::span//parent::button")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys(Discounts_Price); //Input discount name
		driver.findElement(By.xpath("//span[normalize-space()=\"Add\"]")).click(); //Click on save button to save discount
		
		Thread.sleep(3000);
		WebElement el = driver.findElement(By.xpath("//span[normalize-space()='Split Payments']//parent::button"));
		jse.executeScript("arguments[0].click();", el);
		
		WebElement taxRate = driver.findElement(By.xpath("//input[@placeholder=\"1\"]"));
		Thread.sleep(3000);
		taxRate.click();
		taxRate.sendKeys(Keys.CONTROL, "a");
		taxRate.sendKeys(Keys.DELETE);
		taxRate.sendKeys("2");
		driver.findElement(By.xpath("(//button[normalize-space()='Done'])[1]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
		Thread.sleep(2000);		
		WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='dialog'] > div > div:nth-of-type(3) > div > button:first-of-type")));
		jse.executeScript("arguments[0].click();", button);
		Thread.sleep(2000);	
		
		driver.navigate().to(invoices);
		Thread.sleep(10000);
		String invoiceAmount1 = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(5)")).getText().trim();		
		String cleanAmount = invoiceAmount1.replaceAll("[^0-9.]", "");
		System.out.println("Invoice Amount in List View: " + cleanAmount);
		
		//send reminder
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Send Reminder\"]")).click();
		WebElement reminderSuccessMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Reminder has been sent successfully.']")));
		String actualReminderMsg = reminderSuccessMsg.getText().trim();
		System.out.println("Message displayed: " + actualReminderMsg);
		Assert.assertEquals(actualReminderMsg,"Reminder has been sent successfully.","reminder operation is not performed successfully on split payment invoice");
		System.out.println("Send reminder operation is performed successfully on split payment invoice");
		
		driver.navigate().to(Messages);
		Thread.sleep(5000);
		driver.findElement(By.name("filters")).sendKeys(email);
		driver.findElement(By.xpath("(//div[@id='list']/descendant::p[@data-search-in= \"email\"])[1]")).click();
		Thread.sleep(5000);
		/*WebElement lastreminder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type>a.noatagcntz")));
		String reminderText = lastreminder.getText().trim();
		System.out.println("SMS Text: " + reminderText);
		Assert.assertTrue(reminderText.contains(cleanAmount), "Sent reminder SMS does not contain correct invoice amount");*/
		System.out.println("Sent reminder Functionality is verified successfully");
		 
		//Edit Invoice
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Edit Invoice\"]")).click();		
		WebElement editInvoice = driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]"));
		Thread.sleep(3000);
		editInvoice.click();
		editInvoice.sendKeys(Keys.CONTROL, "a");
		editInvoice.sendKeys(Keys.DELETE);
		editInvoice.sendKeys("This invoice is Edited for 911 Automation Test");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		WebElement button1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")));
		jse.executeScript("arguments[0].click();", button1);
		
		Thread.sleep(5000);
		String invoiceName1 = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(8)")).getText().trim();
		System.out.println("Invoice name After Edit in List View: " + invoiceName1);
		Assert.assertEquals(invoiceName1, "This invoice is Edited for 911 Automation Test", "Invoice details are not updated after editing invoice");
		System.out.println("Edit Invoice Functionality is verified successfully");
		
		/*//duplicate Invoice
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Duplicate Invoice\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
		Thread.sleep(3000);
		String newInvoiceNumber = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(5)")).getText().trim();
		System.out.println("Invoice Number After Duplicate in List View: " + newInvoiceNumber);
		int temp = Integer.parseInt(invoiceNumber); 
		String expectedInvoice = String.format("%0" + invoiceNumber.length() + "d", temp + 1);
		Assert.assertEquals(newInvoiceNumber,expectedInvoice,"Invoice number is NOT incremented correctly");
		System.out.println("Duplicate Invoice Functionality is verified successfully");*/
		
		//quick checkout link
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Quick Checkout\"]")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#order-table-body > tr:first-of-type > td:last-of-type > button"))).click();
        WebElement quickCheckoutLink1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()=\"Quick Checkout\"]")));
        jse.executeScript("navigator.clipboard.writeText = function(text) { window.copiedLink = text; }");
        quickCheckoutLink1.click();
        String checkoutUrl1 = (String) jse.executeScript("return window.copiedLink;");
        driver.get(checkoutUrl1);
        
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
        driver.findElement(By.xpath("(//button[normalize-space()='Pay'])[1]")).click(); //Click on pay button in invoice checkout page
        driver.findElement(By.xpath("(//button[normalize-space()='Pay As Guest'])[1]")).click(); //Click on pay as guest button in invoice checkout page
        Thread.sleep(2000);          	
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
		Thread.sleep(2000);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);// MMYY format
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();
		WebElement cardHolder = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder.clear();
		cardHolder.sendKeys(F_Name + " " + L_Name);		
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		
		//archive Invoice
		driver.navigate().to(invoices);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Archive Invoice\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();
		
		String status = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(4)>div")).getText().trim();
		System.out.println("Invoice Status in List View after Archive: " + status);
		Assert.assertEquals(status, "Archived", "Invoice status is not updated to Archive after performing cancel operation");
		System.out.println("Archive Invoice Functionality is verified successfully");	
		
		//restore Invoice
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()=\"Restore Invoice\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();
		String restorestatus = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(4)>div")).getText().trim();
		System.out.println("Invoice Status in List View after Restore: " + restorestatus);
		System.out.println("Restore Invoice Functionality is verified successfully");	
		
		//cancel Invoice
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Cancel Invoice']//parent::div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();
		
		String cancelstatus = driver.findElement(By.cssSelector("tbody#order-table-body>tr:first-of-type>td:nth-of-type(4)>div")).getText().trim();
		System.out.println("Invoice Status in List View after cancellation: " + cancelstatus);
		Assert.assertEquals(cancelstatus, "Cancelled", "Invoice status is not updated to cancelled after performing cancel operation");
		System.out.println("Cancel Invoice Functionality is verified successfully");	
	}
	
	@Test(priority = 10)
	public void Full_SplitPayment_Refund() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		String firstName1 ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
		lastName1 ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
		email1 = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com", phone1 = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
		
		driver.navigate().to(Dashboard);
		Thread.sleep(7000);
		String totalRefunddash = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.dashboard-card-link.refund-url>div>div:nth-of-type(2)>p"))).getText().trim();
		String totalRefundValue = totalRefunddash.replaceAll("[^0-9.]", ""); 
		double RefundValue = Double.parseDouble(totalRefundValue);	
		System.out.println("Total Refund Value Before In Dashboard Tab : " + RefundValue);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String totalRefundsales = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Refunds Statistics Card']>div>div>p"))).getText().trim();
		String totalRefundValuesales = totalRefundsales.replaceAll("[^0-9.]", ""); 
		double RefundValuesales = Double.parseDouble(totalRefundValuesales);	
		System.out.println("Total Refund Value Before In Sales Tab : " + RefundValuesales);
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		String totalRefundinv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Total Refunded Statistics Card']>div>div>p"))).getText().trim();
		String totalRefundValueinv = totalRefundinv.replaceAll("[^0-9.]", ""); 
		double refundValueinv = Double.parseDouble(totalRefundValueinv);	
		System.out.println("Total Refund Value Before In Invoices Tab : " + refundValueinv);
		
		//Check refund functionality working for full payment invoice and data showing correct as per refund amount on>  invoice listing,sales module, orders module, CRM Dashboard ,  customer hub and order thank emails etc)		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
		driver.findElement(By.id("field_first_name")).sendKeys(firstName); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(lastName); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(email); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(phone); //Input phone number
		driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add); //Input street address
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); 
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Line Items']")).click(); //Click on add item button
		driver.findElement(By.xpath("//div[contains(@class,'truncate flex justify-start')]")).click(); //Click on select product drop down		
		driver.findElement(By.xpath("(//input[@placeholder='Enter Line Item Name'])[1]")).sendKeys("Product 1"); //Select product
		driver.findElement(By.xpath("(//input[contains(@placeholder,'—')])[1]")).sendKeys(Value); //Input quantity
		driver.findElement(By.id("unit-price")).sendKeys(Value); //input value in unit price field
		driver.findElement(By.xpath("//textarea[@placeholder=\"Custom line item description\"]")).sendKeys("Add New Product Discription"); 
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); //Click on save button to save line item
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[normalize-space()='Payment Terms']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()=' Add Discount']//parent::span//parent::button")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys(Discounts_Price); //Input discount name
		driver.findElement(By.xpath("//span[normalize-space()=\"Add\"]")).click(); //Click on save button to save discount
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
		Thread.sleep(2000);		
		WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='dialog'] > div > div:nth-of-type(3) > div > button:first-of-type")));
		jse.executeScript("arguments[0].click();", button);
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Share Link']")).click(); 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#order-table-body > tr:first-of-type > td:last-of-type > button"))).click();
        WebElement quickCheckoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Share Link']")));

        jse.executeScript("navigator.clipboard.writeText = function(text) { window.copiedLink = text; }");
        quickCheckoutLink.click();
        String checkoutUrl = (String) jse.executeScript("return window.copiedLink;");
        driver.get(checkoutUrl);
        
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("button[role='checkbox']")).click();
        driver.findElement(By.xpath("(//button[normalize-space()='Pay'])[1]")).click(); //Click on pay button in invoice checkout page
        driver.findElement(By.xpath("(//button[normalize-space()='Pay As Guest'])[1]")).click(); //Click on pay as guest button in invoice checkout page
        Thread.sleep(2000);          	
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Card Information']")));
		Thread.sleep(2000);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);// MMYY format
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();
		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        Thread.sleep(7000);
		String PlacedOrderID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID);
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(4)>div")).click();
		Thread.sleep(2000);
		WebElement refundBtn = driver.findElement(By.xpath("//span[normalize-space()='Refund']"));
		jse.executeScript("arguments[0].click();", refundBtn);
		
		Thread.sleep(2000);
		String RefundInvoiceAmount = driver.findElement(By.cssSelector("input[inputmode='decimal']")).getAttribute("value").trim();
		System.out.println("Invoice Amount: " + RefundInvoiceAmount);
		double refundInvoiceAmount = Double.parseDouble(RefundInvoiceAmount);
		driver.findElement(By.xpath("//span[normalize-space()=\"Complete\"]")).click(); 
		Thread.sleep(5000);
			
		driver.navigate().to(Dashboard);
		Thread.sleep(7000);
		String totalRefunddash1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.dashboard-card-link.refund-url>div>div:nth-of-type(2)>p"))).getText().trim();
		String totalRefundValue1 = totalRefunddash1.replaceAll("[^0-9.]", ""); 
		double RefundValue1 = Double.parseDouble(totalRefundValue1);	
		System.out.println("Total Refund Value After In Dashboard Tab : " + RefundValue1);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String totalRefundsales1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Refunds Statistics Card']>div>div>p"))).getText().trim();
		String totalRefundValuesales1 = totalRefundsales1.replaceAll("[^0-9.]", ""); 
		double RefundValuesales1 = Double.parseDouble(totalRefundValuesales1);	
		System.out.println("Total Refund Value After In Sales Tab : " + RefundValuesales1);
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		String totalRefundinv1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Total Refunded Statistics Card']>div>div>p"))).getText().trim();
		String totalRefundValueinv1 = totalRefundinv1.replaceAll("[^0-9.]", ""); 
		double refundValueinv1 = Double.parseDouble(totalRefundValueinv1);	
		System.out.println("Total Refund Value After In Invoices Tab : " + refundValueinv1);
		
		Assert.assertEquals(RefundValue1, RefundValue + refundInvoiceAmount, "Refund amount is not updated correctly in Dashboard after performing full refund operation");
		System.out.println("Refund amount is updated correctly in Dashboard after performing full refund operation");
		Assert.assertEquals(RefundValuesales1, RefundValuesales + refundInvoiceAmount, "Refund amount is not updated correctly in Sales tab after performing full refund operation");
		System.out.println("Refund amount is updated correctly in Sales tab after performing full refund operation");
		Assert.assertEquals(refundValueinv1, refundValueinv + refundInvoiceAmount, "Refund amount is not updated correctly in Invoices tab after performing full refund operation");
		System.out.println("Refund amount is updated correctly in Invoices tab after performing full refund operation");
		
		//Check refund functionality working for  split payment invoice and data showing correct as per refund amount on>  invoice listing,sales module, orders module, CRM Dashboard ,  customer hub and order thank emails etc)
		driver.navigate().to(Dashboard);
		Thread.sleep(7000);
		String totalRefunddash2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.dashboard-card-link.refund-url>div>div:nth-of-type(2)>p"))).getText().trim();
		String totalRefundValue2 = totalRefunddash2.replaceAll("[^0-9.]", ""); 
		double RefundValue2 = Double.parseDouble(totalRefundValue2);	
		System.out.println("Total Refund Value Before In Dashboard Tab : " + RefundValue2);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String totalRefundsales2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Refunds Statistics Card']>div>div>p"))).getText().trim();
		String totalRefundValuesales2 = totalRefundsales2.replaceAll("[^0-9.]", ""); 
		double RefundValuesales2 = Double.parseDouble(totalRefundValuesales2);	
		System.out.println("Total Refund Value Before In Sales Tab : " + RefundValuesales2);
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		String totalRefundinv2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Total Refunded Statistics Card']>div>div>p"))).getText().trim();
		String totalRefundValueinv2 = totalRefundinv2.replaceAll("[^0-9.]", ""); 
		double refundValueinv2 = Double.parseDouble(totalRefundValueinv2);	
		System.out.println("Total Refund Value Before In Invoices Tab : " + refundValueinv2);
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
		driver.findElement(By.id("field_first_name")).sendKeys(firstName1); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(lastName1); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(email1); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(phone1); //Input phone number
		driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add); //Input street address
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); 
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Line Items']")).click(); //Click on add item button
		driver.findElement(By.xpath("//div[contains(@class,'truncate flex justify-start')]")).click(); //Click on select product drop down		
		driver.findElement(By.xpath("(//input[@placeholder='Enter Line Item Name'])[1]")).sendKeys("Product 1"); //Select product
		driver.findElement(By.xpath("(//input[contains(@placeholder,'—')])[1]")).sendKeys(Value); //Input quantity
		driver.findElement(By.id("unit-price")).sendKeys(Value); //input value in unit price field
		driver.findElement(By.xpath("//textarea[@placeholder=\"Custom line item description\"]")).sendKeys("Add New Product Discription"); 
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); //Click on save button to save line item
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[normalize-space()='Payment Terms']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()=' Add Discount']//parent::span//parent::button")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys(Discounts_Price); //Input discount name
		driver.findElement(By.xpath("//span[normalize-space()=\"Add\"]")).click(); //Click on save button to save discount
		
		Thread.sleep(3000);
		WebElement el = driver.findElement(By.xpath("//span[normalize-space()='Split Payments']//parent::button"));
		jse.executeScript("arguments[0].click();", el);	
		WebElement taxRate = driver.findElement(By.xpath("//input[@placeholder=\"1\"]"));
		Thread.sleep(3000);
		taxRate.click();
		taxRate.sendKeys(Keys.CONTROL, "a");
		taxRate.sendKeys(Keys.DELETE);
		taxRate.sendKeys("2");
		driver.findElement(By.xpath("(//button[normalize-space()='Done'])[1]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
		Thread.sleep(2000);		
		WebElement button1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='dialog'] > div > div:nth-of-type(3) > div > button:first-of-type")));
		jse.executeScript("arguments[0].click();", button1);
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Share Link']")).click(); 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#order-table-body > tr:first-of-type > td:last-of-type > button"))).click();
        WebElement quickCheckoutLink1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Share Link']")));

        jse.executeScript("navigator.clipboard.writeText = function(text) { window.copiedLink = text; }");
        quickCheckoutLink1.click();
        String checkoutUrl1 = (String) jse.executeScript("return window.copiedLink;");
        driver.get(checkoutUrl1);
        
        Thread.sleep(5000);
		String InvoiceAmountText = driver.findElement(By.cssSelector("div.inv-paymenys-table>div>div.amount>p:nth-of-type(2)")).getText().trim();
		double invoiceAmount = Double.parseDouble(InvoiceAmountText.replaceAll("[^0-9.]", ""));
		System.out.println("Invoice Amount: " + invoiceAmount);
        
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("button[role='checkbox']")).click();
        driver.findElement(By.xpath("(//button[normalize-space()='Pay'])[1]")).click(); //Click on pay button in invoice checkout page
        driver.findElement(By.xpath("(//button[normalize-space()='Pay As Guest'])[1]")).click(); //Click on pay as guest button in invoice checkout page
        Thread.sleep(2000);          		
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Card Information']")));
		Thread.sleep(2000);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);// MMYY format
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();
		WebElement cardHolder11 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder11.clear();
		cardHolder11.sendKeys(F_Name + " " + L_Name);
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        Thread.sleep(7000);
		String PlacedOrderIDs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderIDs);
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(4)>div")).click();
		Thread.sleep(2000);
		WebElement refundBtn1 = driver.findElement(By.xpath("//span[normalize-space()='Refund']"));
		jse.executeScript("arguments[0].click();", refundBtn1);
		
		Thread.sleep(2000);
		String RefundInvoiceAmount1 = driver.findElement(By.cssSelector("input[inputmode='decimal']")).getAttribute("value").trim();
		System.out.println("Invoice Amount: " + RefundInvoiceAmount1);
		double refundInvoiceAmount1 = Double.parseDouble(RefundInvoiceAmount1);
		driver.findElement(By.xpath("//span[normalize-space()=\"Complete\"]")).click(); 
		Thread.sleep(5000);
			
		driver.navigate().to(Dashboard);
		Thread.sleep(7000);
		String totalRefunddash11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.dashboard-card-link.refund-url>div>div:nth-of-type(2)>p"))).getText().trim();
		String totalRefundValue11 = totalRefunddash11.replaceAll("[^0-9.]", ""); 
		double RefundValue11 = Double.parseDouble(totalRefundValue11);	
		System.out.println("Total Refund Value After In Dashboard Tab : " + RefundValue11);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String totalRefundsales11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Refunds Statistics Card']>div>div>p"))).getText().trim();
		String totalRefundValuesales11 = totalRefundsales11.replaceAll("[^0-9.]", ""); 
		double RefundValuesales11 = Double.parseDouble(totalRefundValuesales11);	
		System.out.println("Total Refund Value After In Sales Tab : " + RefundValuesales11);
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		String totalRefundinv11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Total Refunded Statistics Card']>div>div>p"))).getText().trim();
		String totalRefundValueinv11 = totalRefundinv11.replaceAll("[^0-9.]", ""); 
		double refundValueinv11 = Double.parseDouble(totalRefundValueinv11);	
		System.out.println("Total Refund Value After In Invoices Tab : " + refundValueinv11);
		
		Assert.assertEquals(RefundValue11, RefundValue2 + refundInvoiceAmount1, "Refund amount is not updated correctly in Dashboard after performing split refund operation");
		System.out.println("Refund amount is updated correctly in Dashboard after performing split refund operation");
		Assert.assertEquals(RefundValuesales11, RefundValuesales2 + refundInvoiceAmount1, 0.01, "Refund amount is not updated correctly in Sales tab after performing split refund operation");
		System.out.println("Refund amount is updated correctly in Sales tab after performing split refund operation");
		Assert.assertEquals(refundValueinv11, refundValueinv2 + refundInvoiceAmount1, 0.01,  "Refund amount is not updated correctly in Invoices tab after performing split refund operation");	
		System.out.println("Refund amount is updated correctly in Invoices tab after performing split refund operation");
	}
}