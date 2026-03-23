package dailyTesting_911;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class invoices extends Data {
	java.util.Random r = new java.util.Random();
	
	String Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
			SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),Country_Add = "Boardman, Oregon, 97818",
			Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(), ContactPhone1 = "(775) 986-5200",
			F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", country = "United States", ContactPhone2 = "(539) 321-3502",
			Account_Holder_Name = "DeposytCert", Routing_Number = "490000018", Account_Number = "000123456789", EXP = "12/44", CVV = "123", Card_No = "4242424242424242",
			chars = "abcdefghijklmnopqrstuvwxyz", Value = "50",	    
			firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
			lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
			email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com",
			phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
	
	@Test(priority = 1)
	public void FullPayment_Invoice() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;	
		String Discounts_Price = "1";	
			
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
		driver.findElement(By.name("phone_no")).sendKeys(ContactPhone2); //Input phone number
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
		driver.findElement(By.xpath("//p[text()=' Add Discount']//parent::span//parent::button")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\" \"]")).sendKeys(Discounts_Price); //Input discount name
		driver.findElement(By.xpath("//span[normalize-space()=\"Add\"]")).click(); //Click on save button to save discount
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
		Thread.sleep(2000);
		
		String InvoiceAmount = driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(2) > div >div>div>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>p")).getText().trim();
		System.out.println("Invoice Amount: " + InvoiceAmount);
		
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
        driver.findElement(By.xpath("(//button[normalize-space()='Pay'])[1]")).click(); //Click on pay button in invoice checkout page
        driver.findElement(By.xpath("(//button[normalize-space()='Pay As Guest'])[1]")).click(); //Click on pay as guest button in invoice checkout page
        Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); //select country as USA
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.id("street-address")).sendKeys(Street_Add);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		Thread.sleep(2000);
        driver.findElement(By.name("tips_amount")).sendKeys(Value); //Input tip amount in invoice checkout page           
		
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
		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		
		//Check Full payments Invoices calculation showing correct after order by considering products,Tax, discount and dual prices.(sales, orders and thank emails, customer hub etc)
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		String orderAmount = driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(4)>span")).getText(); //Click on order details
		System.out.println("Order Amount in Orders Page: " + orderAmount);
		Assert.assertEquals(orderAmount, InvoiceAmount, "Order amount in orders page is not matching with invoice amount");
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);		
		String totalRevenue11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue11 = totalRevenue11.replaceAll("[^0-9.]", ""); 
		double RevenueValue11 = Double.parseDouble(totalRevenueValue11);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + RevenueValue11);
		Assert.assertEquals(RevenueValue + Double.parseDouble(InvoiceAmount) < RevenueValue11, "Total revenue value in sales page is not updated correctly after placing order from invoice");
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
