package dailyTesting_911;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class orders extends Data {
	java.util.Random r = new java.util.Random();	
	
	String Price = "2", Value = "1",susbcriptionPrice = "3",Fileone = "file1.jpg", Attachment = "Jira_Guide.pdf",
	Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
	SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
	Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(), 
	
	ContactPhone1 = "(775) 986-5200",Country_Add = "Boardman United States, 97818",F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", 
	country = "United States", ContactPhone2 = "(539) 321-3502",Account_Holder_Name = "DeposytCert", Routing_Number = "490000018", Account_Number = "000123456789", EXP = "12/44", 
	CVV = "123", Card_No = "4242424242424242",	chars = "abcdefghijklmnopqrstuvwxyz", Card_No1 = "5411111111111115",
	
	firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
	lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
	email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com", phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
	
	@Test(priority = 1)
	public void Product_Actions() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Actions actions = new Actions(driver);
		
		StringBuilder giftCard = new StringBuilder();
		String giftCardName = "GiftCard" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),		
		Product_Name = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),GiftCard = "0.20", Discounts_Price = "1";		

		for (int i = 0; i < 10; i++) {
		    giftCard.append(java.util.concurrent.ThreadLocalRandom.current().nextBoolean()? (char) ('A' + java.util.concurrent.ThreadLocalRandom.current().nextInt(26)): (char) ('0' + java.util.concurrent.ThreadLocalRandom.current().nextInt(10)));
		}
		
		try {
			driver.navigate().to(settings);
			Thread.sleep(7000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 	
			Thread.sleep(3000);
			WebElement feeOption = driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[5]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'});", feeOption);
			feeOption.click();
			Thread.sleep(2000);
			driver.findElement(By.id("dialog-checkbox")).click();		
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();	
		} catch(Exception e) {
			System.out.println("ACH_Payment is already enabled for the store");
		}
		
		//6A Verify is giftcard is visible
		driver.navigate().to(Gift_Cards);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='New Gift Card'])[3]"))).click(); // click on New Gift Card button
		driver.findElement(By.xpath("(//div[@id='region.region_id'])[1]")).click(); //click on region drop down
		driver.findElement(By.id("react-select-2-option-0")).click(); //Select Default as region
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@placeholder='-'])[1]")).sendKeys(GiftCard);
		driver.findElement(By.name("receiver.email")).sendKeys(WMLogin);
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //click on create gift card button
		Thread.sleep(5000);
		String GiftCardID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.customer-table-second-class>table>tbody>tr>td>p:first-of-type"))).getText().trim();
		System.out.println("Created Gift Card ID: " + GiftCardID);
		
		//6B Verify is discount is visible
		driver.navigate().to(Discounts);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='+ Add Discount']"))).click(); // click on new discount button
		driver.findElement(By.cssSelector("div[data-orientation=\"vertical\"]:nth-of-type(2)>h3>div:first-of-type")).click();
		
		driver.findElement(By.cssSelector("#regions>div")).click();//click on region drop down
		driver.findElement(By.id("react-select-2-option-0")).click(); //Select Default as region
		Thread.sleep(2000);
		driver.findElement(By.name("rule.description")).sendKeys("This discount is created for 911 Automation Test");
		driver.findElement(By.name("code")).sendKeys(giftCardName); //input code
		driver.findElement(By.name("rule.value")).sendKeys(Discounts_Price); //input discount value		
		Thread.sleep(3000);
		WebElement publishButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.custom-setting-region-tab > div > button:nth-of-type(2)")));
		jse.executeScript("arguments[0].scrollIntoView(true);", publishButton);
		wait.until(ExpectedConditions.elementToBeClickable(publishButton));
		jse.executeScript("arguments[0].click();", publishButton);
        Thread.sleep(3000);

		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("test-Nadsoft");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};
		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();
		
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(1000);
		WebElement subscriptionToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",subscriptionToggle);
		subscriptionToggle.click(); //Subscription toggle button
		Thread.sleep(2000);
		
		WebElement scscprise = driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]"));
		scscprise.clear();
		Thread.sleep(1000);
		scscprise.sendKeys(Value); //Subscription price field
		Thread.sleep(1000);
	    driver.findElement(By.cssSelector("div.frequency-select-container")).click(); //Frequency drop down
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[2]")).click(); //Select frequency as monthly
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='Make the first payment different']/following-sibling::button[@role=\"switch\"]")).click(); //First payment different toggle button
		WebElement suscprise1 = driver.findElement(By.name("productPricing.regularPrice"));
		suscprise1.clear();
		suscprise1.sendKeys(susbcriptionPrice);
		driver.findElement(By.xpath("//span[normalize-space()='Make this a limited subscription']/following-sibling::button[@role=\"switch\"]")).click(); //Limited subscription toggle button
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button' and @tabindex='-1']/*[name()='svg'])[2]")).click(); //plus button to add subscription limit
		
		//Scroll to "This Product Unlocks Courses" button
		WebElement unlockCourseButton = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButton);
		Thread.sleep(500);
		jse.executeScript("arguments[0].click();", unlockCourseButton);

		//click on Link Product to Services button
		WebElement unlockservicesButton = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockservicesButton);
		Thread.sleep(500);
		jse.executeScript("arguments[0].click();", unlockservicesButton);

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		
		//Use Giftcard for Subscription product Checkout
		Thread.sleep(5000);
		driver.findElement(By.id("Giftcardcode")).sendKeys(GiftCardID); //Input discount code in cart
		driver.findElement(By.id("applyGiftCard")).click(); //Click on apply button for discount code
		Thread.sleep(2000);
		driver.findElement(By.id("couponCode1")).sendKeys(giftCardName); //Input gift card code in cart
		driver.findElement(By.id("applyDiscountCoupon")).click(); //Click on apply button for
				
		driver.findElement(By.cssSelector("input#email")).sendKeys(email);  
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); //select country as USA
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		Thread.sleep(2000);		

		//Payment Information
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space(text())='Payment Information']")));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(3)>div:nth-of-type(2)")).click();//click on ACH cash payment
		Thread.sleep(2000);
		driver.findElement(By.id("achAccountHolderName")).sendKeys(Account_Holder_Name);
		driver.findElement(By.id("achRoutingNumber")).sendKeys(Routing_Number);
		driver.findElement(By.id("achAccountNumber")).sendKeys(Account_Number);
		driver.findElement(By.xpath("(//button[@role='combobox'])[1]")).click();//click on mode drop down
		driver.findElement(By.cssSelector("#AchAccTypeDropdown>button")).click();//select checking
		driver.findElement(By.xpath("(//button[contains(@role,'combobox')])[2]")).click(); //click on account category drop down
		driver.findElement(By.cssSelector("#AchAccTypeDropdown>button")).click(); //select personal
		
		List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[role='checkbox']")));
		for (WebElement checkbox : checkboxes) {
		    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
		        checkbox.click();
		    }
		}
		
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();
		Thread.sleep(3000);

		//Validate order Summary - Customer details
		String Order_Name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Customer']//following-sibling::div//span"))).getText().trim();
		String Order_Mail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Customer']//following-sibling::div//p"))).getText().trim();
		String Order_Phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Customer']//following-sibling::div//span[2]"))).getText().replace("-", "").trim();

		System.out.println(Order_Name);
		System.out.println(Order_Mail);
		System.out.println(Order_Phone);

		Assert.assertEquals(Order_Name, firstName + " " + lastName, "Customer name not matching on order summary");
		Assert.assertEquals(Order_Mail, email, "Customer email not matching on order summary");		
		String Billing_Address1 = driver.findElement(By.cssSelector("div.print-container>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>p:first-of-type")).getText().trim();
		String Billing_Address2 = driver.findElement(By.cssSelector("div.print-container>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>p:nth-of-type(4)")).getText().trim();
		
		// Order History Section Show Proper Order 
		driver.close();
		driver.switchTo().window(originalTab);
		Thread.sleep(2000);
		
		//check that default checkout , checkout and default product page created and links are working
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages	
		driver.findElements(By.cssSelector("td.tracking-tighter>div>div>p")).get(0).click();//Click on default product page
		
		String pwindo1 = driver.getWindowHandle();
		String Checkout_page1 = null;
		for(String Tab: driver.getWindowHandles()){
			driver.switchTo().window(Tab);
			Checkout_page1 = Tab;
		}
		Thread.sleep(3000); //Added Wait due to optimization
		try{
			String heading2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("HK7a7HRDgW-subheading"))).getText().trim();
			Assert.assertEquals(heading2, Product_Name, "Heading not matching on checkout page");
		} catch(TimeoutException e){
			System.out.println("'Product Currently Unavailable' message not found." + Checkout_page1);
		}
		
		driver.close();
		driver.switchTo().window(pwindo1);
		
		driver.findElements(By.cssSelector("td.tracking-tighter>div>div>p")).get(1).click();//Click on default product page	
		String pwindo11 = driver.getWindowHandle();
		String Checkout_page11 = null;
		for(String Tab: driver.getWindowHandles()){
			driver.switchTo().window(Tab);
			Checkout_page11 = Tab;
		}
		Thread.sleep(3000);
		try{
			String heading3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkout-template>div>section>div>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>span:first-of-type"))).getText().trim();
			Assert.assertEquals(heading3, Product_Name, "Heading not matching on checkout page");
		} catch(TimeoutException e){
			System.out.println("'Product Currently Unavailable' message not found." + Checkout_page11);
		}
		
		driver.close();
		driver.switchTo().window(pwindo11);
		
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)")).click(); //Click on order details
			
		//2C check customer details/billing/ shipping address is correct
		Thread.sleep(5000);
		String fullName = firstName + " " + lastName;
		String OrderName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@data-state,'closed') and normalize-space()='" + fullName + "']"))).getText().trim().toLowerCase();
		String OrderEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@data-state,'closed') and normalize-space()='" + email + "']"))).getText().trim().toLowerCase();
		
		Assert.assertEquals(OrderName, fullName, "Customer name not matching in order details");
		Assert.assertEquals(OrderEmail, email, "Customer email not matching in order details");
		
		String OrderBillingAddress1 = driver.findElement(By.xpath("(//p[contains(text(),'Allen Court')])[1]")).getText().trim();
		String OrderBillingAddress2 = driver.findElement(By.xpath("(//p[contains(text(),'United States')])[1]")).getText().trim();
		String OrderBillingAddress3 = driver.findElement(By.xpath("(//p[contains(text(),'Boardman, Oregon, 97818')])[1]")).getText().trim();
		
		Assert.assertEquals(OrderBillingAddress1, Billing_Address1, "Street address not matching in order details");
		Assert.assertEquals(OrderBillingAddress2, country, "Country not matching in order details");
		Assert.assertEquals(OrderBillingAddress3, Billing_Address2, "City, State and Zip code not matching in order details");
		
		/*String GiftcardValid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[normalize-space()='" + GiftCardID + "'])[1]"))).getText().trim();
		String DiscountValid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[text()='" + giftCardName + "'])[1]"))).getText().trim();
		
		Assert.assertEquals(GiftcardValid, GiftCardID, "Gift card code not applied properly");
		Assert.assertEquals(DiscountValid, giftCardName, "Discount code not applied properly");*/			
	}
	
	@Test(priority = 2)
	public void Order_status() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;			
			
		//Verify order is visible in order list after complete order from invoice
		driver.navigate().to(invoices);
		Thread.sleep(15000);
		driver.navigate().refresh();
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
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
		Thread.sleep(3000);
		String InvoiceID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='dialog'] > div > div:nth-of-type(2)>div>div>div>table>thead>tr>td:nth-of-type(2)"))).getText().trim();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type"));//Click on send now button in pop up
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
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); //select country as USA
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.id("street-address")).sendKeys(Street_Add);
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
		WebElement cardHolder = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder.clear();
		cardHolder.sendKeys(F_Name + " " + L_Name);
		
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        driver.navigate().to(Orders);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Invoices'])[1]"))).click();   // Click on view details of the order
		Thread.sleep(2000);	
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click(); //Click on order details of the latest order
		Thread.sleep(2000);
		String OrderIDinDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#overflow-main-div button span:nth-of-type(2) span"))).getText().trim();
		OrderIDinDetails = OrderIDinDetails.replaceAll("\\D", "");
		Assert.assertEquals(OrderIDinDetails, InvoiceID, "Order ID in order details is not matching with placed order ID");
		
		//Verify order is visible in order list after complete order from virtual terminal
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[normalize-space(.)='Cash']")).click(); //Select cash payment method
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,'overflow-y-auto')]/div[contains(@class,'mb-[5px]')][1]")).click(); //Select first customer from the list
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]")).click(); //Clicking on add line items button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); //Clicking on item name field
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[text()='Create New Item']]/parent::div/following-sibling::div[1]")).click(); //Clicking on first product from the dropdown
		Thread.sleep(1000);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Cash Payment Method']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class,'custom-class-for-aaply-the-css')]//div[contains(@class,'flex flex-1 items-center') and .//div[contains(@id,'placeholder')]]")).click(); //Clicking on payment type dropdown
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='react-select-2-listbox']//div[@role='option'][1]")).click(); //Select payment type cash
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space(.//p/text())='Record Cash Sale']")).click(); //Clicking on record cash sale button
		Thread.sleep(15000);
		String orderId = driver.findElement(By.xpath("//div[h2[normalize-space(text())='Transaction Summary']]/p")).getText().trim().replace("#", ""); //Getting order id
		System.out.println("Order ID: " + orderId);
		
		driver.get(Orders);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[normalize-space(text())='Virtual Terminal']")).click(); //Clicking on virtual terminal filter
		Thread.sleep(2000);
		String orderIdInVirtualTerminal = driver.findElement(By.xpath("//tbody[@id='order-table-body']/tr[1]/td[2]/span")).getText().trim().replace("#", "");
		System.out.println("Order ID in Virtual Terminal: " + orderIdInVirtualTerminal);
		Assert.assertEquals(orderId, orderIdInVirtualTerminal, "Order ID in virtual terminal does not match the recorded order ID.");		
	}
	
	@Test(priority = 3)
	public void Place_Order() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		Date cDate = new Date();
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String giftCardName = "GiftCard " + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
		
		//Verify services orders are visible
		driver.navigate().to(Services);
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("div.fb-setting-icon-wrapper>a:first-of-type")).click(); //Click on service setting
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.assign-payment-type-dropdownsetting")).click(); //Click on assign payment type dropdown
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("li[data-paymenttype='paidbooking']")).click(); //Select cash payment type
		
		//Check the checkout process in the services
		String Pwindow = driver.getWindowHandle();
		driver.findElement(By.cssSelector("a.bookingwidgetborder")).click();
		driver.findElement(By.cssSelector("a[target=\"_blank\"]")).click();//preview button

		for (String handle:driver.getWindowHandles()){
			driver.switchTo().window(handle);
		}
		
		String servicename = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.allservicesviewdiv>div>div>div>div:first-of-type>a>div>h2"))).getText().trim();
		driver.findElement(By.id("searchservicefield")).sendKeys(servicename);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='professional-content']//h2[contains(text(),'"+servicename+"')]//ancestor::a"))).click();
		driver.findElement(By.cssSelector("div.nextchoosenbtnmaindiv a[data-action=\"datetime\"]")).click();
		Thread.sleep(3000);

		//Booking Free service
		driver.findElement(By.cssSelector("div[id='"+cSimpleDateFormat.format(cDate.getTime())+"']+div")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.booking_timeslots>p.timeslot")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a.closemaintippopuplater")).click();
		driver.findElement(By.cssSelector("a.floatingbtnfornextaction")).click();
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email);  
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); //select country as USA
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
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
		
		driver.findElement(By.xpath("//button[@role='checkbox']")).click(); //Clicking on checkbox
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		Thread.sleep(5000);
		String PlacedOrderID1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.order_details_info_orderID"))).getText().trim().toLowerCase();
		PlacedOrderID1 = PlacedOrderID1.replace("order #", "").trim();
		System.out.println("Placed Order ID: " + PlacedOrderID1);
		driver.close();		
		driver.switchTo().window(Pwindow);
		
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@aria-label='Services Toggle Button'])[1]"))).click(); 		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click(); //Click on view details of the order
		Thread.sleep(4000);
		String currentUrl = driver.getCurrentUrl().toLowerCase();	
		System.out.println("Current URL : " + currentUrl);
		Assert.assertTrue(currentUrl.contains(PlacedOrderID1), "Order ID not found in URL");
		
		//Verify premade - giftcard orders are visible
		driver.navigate().to(Gift_Cards);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Premade Gift Cards'])[1]"))).click();//Click on new gift card button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.gap-y-xsmall>div>div>div>div>button:first-of-type")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@placeholder='Premade Gift Card Name'])[1]"))).sendKeys(giftCardName);//Input gift card name
		
		driver.findElement(By.xpath("(//textarea[@placeholder='Premade Gift Card Description'])[1]")).sendKeys("911 Gift card Discription"); //Input gift card value
		driver.findElement(By.xpath("(//input[@placeholder='0.00'])[1]")).sendKeys("10"); //Input gift card value
		driver.findElement(By.xpath("(//input[contains(@placeholder,'0.00')])[2]")).sendKeys("9"); //Click on create gift card button
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Create & Publish'])[1]")).click();//click on publisg button
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("tbody[role='rowgroup']>tr:last-of-type>td:nth-of-type(2)>div>span>span>div")).click(); //Click on share
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[contains(@href,'/checkout/?id=prod_')])[1]")).click(); //Click on copy link
		
		String originalTab = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}		
		
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email);  
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); //select country as USA
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		Thread.sleep(2000);
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
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
		
		driver.findElement(By.xpath("(//button[@role='checkbox'])[2]")).click(); //Clicking on checkbox				
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Gift Cards'])[1]"))).click(); 		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click(); //Click on view details of the order
		Thread.sleep(4000);
		Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(PlacedOrderID11),"Order ID not found in URL");		
	}
	
	@Test(priority = 4)
	public void Edit_order() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Product_Name = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();
		String firstName1 = "sh" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26));
		String lastName1 = "ri" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26));
		String email1 = firstName1 + "." + lastName1 + r.nextInt(1000) + "2202@yopmail.com";
		String phone1 = "43" + (100000000 + r.nextInt(900000000));
		
		//DeleteMail("Your Voided Order Receipt");
		
		try {
			driver.navigate().to(Products);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//img[@alt=\"stripe\"]//parent::div//parent::div)[1]")).click(); //Select stripe from payment gateway list
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();
		} catch (Exception e) {
			System.out.println("Payment gateway not updated to stripe");
		}
		
		//Verify course orders are visible
		driver.navigate().to(Courses);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.list-form-img>div>div")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.edit-course-sidebar>div>div>div>nav>div:nth-of-type(4)>div>div:nth-of-type(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("paidCourseTab")).click();
		driver.findElement(By.id("connect-product")).click();
		Thread.sleep(3000);

		String prodname = driver.findElement(By.cssSelector("div.connect-product-listing>div:first-of-type>div>span")).getText();
		driver.findElement(By.name("searchproductsDD")).sendKeys(prodname);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.connect-product-listing>div:first-of-type")).click();
		driver.findElement(By.cssSelector("button.connect-productbtn")).click();

		Thread.sleep(2000);
		WebElement openEditProduct =driver.findElement(By.xpath("(//a[normalize-space()='Open/Edit Product'])[last()]"));
		jse.executeScript("arguments[0].click();", openEditProduct);
		String parentWindow = driver.getWindowHandle();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter>div>a:first-of-type"))).click();
		Thread.sleep(3000);

		driver.close();
		driver.switchTo().window(parentWindow);
		
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(7000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); //select country as USA
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
		Thread.sleep(2000);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();

		WebElement cardHolder = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder.clear();
		cardHolder.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(5000);

		String PlacedOrderID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		driver.close();
		driver.switchTo().window(parentWindow);
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//img[@alt=\"nmi\"]//parent::div//parent::div)[1]")).click(); //Select nmi from payment gateway list
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();
		
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='courses'])[1]"))).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);
		Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(PlacedOrderID),"Order ID not found in URL");
		
		//2A check  edit order functinality by adding products
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='One-Time'])[1]"))).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//button[normalize-space()='Edit Order'])[1]")).click(); //Click on edit order button
		Thread.sleep(2000);
		List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[role='dialog'] .edit-order-model-content span > div")));
		int totalProductsAfter = products.size();
		System.out.println("Total products before adding item: " + totalProductsAfter);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Add items']")).click(); //Click on add items button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("tbody[role='rowgroup']>tr>td:first-of-type")).click();

		driver.findElement(By.xpath("(//span[normalize-space()='Save and go back'])[1]")).click(); //Click on save and go back button
		Thread.sleep(2000);

		List<WebElement> products1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[role='dialog'] .edit-order-model-content span > div")));
		int totalProductsBefore = products1.size();
		System.out.println("Total products after adding item: " + totalProductsBefore);
		Assert.assertTrue(totalProductsAfter < totalProductsBefore,"Product count did NOT increase after adding item");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@role='dialog']//button[@aria-haspopup='menu']")).click(); //Click on edit order button
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[contains(text(),'Remove item')])[1]")).click(); //Click on delete button in dropdown		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Add items']")).click(); //Click on add items button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("tbody[role='rowgroup']>tr>td:first-of-type")).click();	
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Save and go back'])[1]")).click(); //Click on save and go back button		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[normalize-space()='Save and close'])[1]")).click(); //Click on edit order button

		Thread.sleep(3000);
		//driver.findElement(By.xpath("(//span[normalize-space()='Copy Confirmation-Request Link'])[1]")).click();

		jse.executeScript("window.__copiedText = '';" +"navigator.clipboard.writeText = function(text) {" +"   window.__copiedText = text;" +"};");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='Copy Confirmation-Request Link'])[1]"))).click();

		String copiedUrl = (String) jse.executeScript("return window.__copiedText;");
		System.out.println("Copied URL: " + copiedUrl);
		Thread.sleep(10000);

		String mainWindow = driver.getWindowHandle();
		jse.executeScript("window.open(arguments[0]);", copiedUrl);
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(mainWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email1);
		Thread.sleep(10000);
		
		WebElement CardName = driver.findElement(By.id("cardHolderName"));
		jse.executeScript("arguments[0].scrollIntoView(true);", CardName);
		Thread.sleep(1000);
		CardName.sendKeys(F_Name+" "+L_Name);
		driver.switchTo().frame("CollectJSInlineccnumber");
		driver.findElement(By.id("ccnumber")).sendKeys(Card_No1);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlinecvv");
		driver.findElement(By.id("cvv")).sendKeys(CVV);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlineccexp");
		driver.findElement(By.id("ccexp")).sendKeys(EXP);
		driver.switchTo().defaultContent();	
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();
		Thread.sleep(5000);
		
		String PlacedOrderID1 = driver.findElement(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span")).getText();
		System.out.println("Placed Order ID: " + PlacedOrderID1);		
		driver.close();
		driver.switchTo().window(mainWindow);

		driver.navigate().refresh();
		Thread.sleep(7000);

		//2B Check if timeline is updated after order is edited
        String timelineText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(text(),'Order Edit force confirmed')])[1]"))).getText().trim().toLowerCase();
        Assert.assertTrue(timelineText.contains("order edit force confirmed"),"Timeline is not updated with order edit force confirmed activity");
        
        String timelineText1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(text(),'Order Edit requested')])[1]"))).getText().trim().toLowerCase();
        Assert.assertTrue(timelineText1.contains("order edit requested"),"Timeline is not updated with order edit requested activity");
		
		//Check that when we do void then its amount is getting update correctly in sales module
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String totalRefund = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Voided Statistics Card']>div>div>p"))).getText().trim();
		String totalRefundValue = totalRefund.replaceAll("[^0-9.]", ""); 
		double refundBefore = Double.parseDouble(totalRefundValue);
		System.out.println("Total Refund Before: " + refundBefore);
		
		//Verify void functionality is operational
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("test-Nadsoft");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		driver.findElement(By.xpath("//div[@id='one-time-purchase-div']/descendant::button[@role=\"switch\"][1]")).click(); //One time purchase toggle button
		Thread.sleep(1000);
		WebElement subscriptionToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",subscriptionToggle);
		subscriptionToggle.click(); //Subscription toggle button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys("1"); //Subscription price field
	    driver.findElement(By.cssSelector("div.frequency-select-container")).click(); //Frequency drop down
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[2]")).click(); //Select frequency as monthly
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='Make the first payment different']/following-sibling::button[@role=\"switch\"]")).click(); //First payment different toggle button
		WebElement suscprise = driver.findElement(By.name("productPricing.regularPrice"));
		suscprise.clear();
		suscprise.sendKeys(susbcriptionPrice);
		driver.findElement(By.xpath("//span[normalize-space()='Make this a limited subscription']/following-sibling::button[@role=\"switch\"]")).click(); //Limited subscription toggle button
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button' and @tabindex='-1']/*[name()='svg'])[2]")).click(); //plus button to add subscription limit

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		driver.navigate().to(Courses);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.list-form-img>div>div")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.edit-course-sidebar>div>div>div>nav>div:nth-of-type(4)>div>div:nth-of-type(2)")).click();
		driver.findElement(By.id("paidCourseTab")).click();
		driver.findElement(By.id("connect-product")).click();
		Thread.sleep(3000);

		String prodname1 = driver.findElement(By.cssSelector("div.connect-product-listing>div:first-of-type>div>span")).getText();
		driver.findElement(By.name("searchproductsDD")).sendKeys(prodname1);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.connect-product-listing>div:first-of-type")).click();
		driver.findElement(By.cssSelector("button.connect-productbtn")).click();

		Thread.sleep(2000);
		WebElement openEditProduct1 = driver.findElement(By.xpath("(//a[normalize-space()='Open/Edit Product'])[last()]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", openEditProduct1);
		openEditProduct1.click();

		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter>div>a:first-of-type"))).click();
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(parentWindow);

		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email1);
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName1);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName1);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone1);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); //select country as USA
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
		Thread.sleep(2000);
		
		WebElement CardName1 = driver.findElement(By.id("cardHolderName"));
		jse.executeScript("arguments[0].scrollIntoView(true);", CardName1);
		Thread.sleep(1000);
		CardName1.sendKeys(F_Name+" "+L_Name);
		driver.switchTo().frame("CollectJSInlineccnumber");
		driver.findElement(By.id("ccnumber")).sendKeys(Card_No1);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlinecvv");
		driver.findElement(By.id("cvv")).sendKeys(CVV);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlineccexp");
		driver.findElement(By.id("ccexp")).sendKeys(EXP);
		driver.switchTo().defaultContent();	
		
		driver.findElement(By.cssSelector("button[role='checkbox']")).click(); //Clicking on checkbox
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();
		Thread.sleep(5000);
		
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(mainWindow);
		
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='courses'])[1]"))).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='Void'])[1]"))).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='Complete'])[1]"))).click();
		Thread.sleep(3000);
		driver.navigate().refresh();
		
		Thread.sleep(4000);
		By voidedText = By.xpath("(//span[normalize-space()='Voided'])[1]");
		WebElement statusText = wait.until(ExpectedConditions.visibilityOfElementLocated(voidedText));
		Assert.assertEquals(statusText.getText().trim(), "Voided");
		
		/*String totalRefundAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#overflow-main-div>div:nth-of-type(2)>div>div:nth-of-type(3)>div>div:nth-of-type(2)>div>div>div>div:nth-of-type(2)>div>span"))).getText().trim();
		double refundAdded = Double.parseDouble(totalRefundAfter.replaceAll("[^0-9.]", ""));
		System.out.println("Refund Added: " + refundAdded);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String finalRefundText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Voided Statistics Card']>div>div>p"))).getText().trim();
		double actualFinalRefund = Double.parseDouble(finalRefundText.replaceAll("[^0-9.]", ""));
		System.out.println("Actual Final Refund: " + actualFinalRefund);
		Assert.assertTrue(actualFinalRefund > refundBefore,"Final refund value mismatch");
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//img[@alt=\"stripe\"]//parent::div//parent::div)[1]")).click(); //Select nmi from payment gateway list
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();*/
		
		//13A check if refund email is received
		//ReceivedMail("Your Voided Order Receipt");
	}
	
	@Test(priority = 5)
	public void refund_order() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		try {
			driver.navigate().to(Products);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//img[@alt=\"stripe\"]//parent::div//parent::div)[1]")).click(); //Select stripe from payment gateway list
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();
		} catch (Exception e) {
			System.out.println("Payment gateway not updated to stripe");
		}

		//Check refund functionality is workable
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("test-Nadsoft");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};
		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();

		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("100");
		Thread.sleep(1000);
		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();//Click on order page created by product
		
		String originalTab = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
		Thread.sleep(2000);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();

		WebElement cardHolder = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder.clear();
		cardHolder.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(originalTab);

		driver.navigate().to(Orders);
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Refund']//parent::span//parent::button)[1]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id=\"Partially Refund\"]")).click();
		
		Thread.sleep(2000);
		WebElement refundAmountField = driver.findElement(By.xpath("//input[contains(@placeholder,\"0.00\")]"));
		Thread.sleep(3000);
		refundAmountField.click();
		refundAmountField.sendKeys(Keys.CONTROL, "a");
		refundAmountField.sendKeys(Keys.DELETE);
		refundAmountField.sendKeys("10");
		refundAmountField.getText().trim();
		System.out.println("Refund Amount Entered: " + refundAmountField);
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Complete']//parent::button"));
		jse.executeScript("arguments[0].click();", element);
		
		Thread.sleep(2000);
		String PartialRefund_Tag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#overflow-main-div span > div > span"))).getText().trim();
		Assert.assertEquals(PartialRefund_Tag, "Partially Refunded", "Refunded tag not found in order details");
		
		Thread.sleep(2000);
		String PartialRefund_Status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.custom-class-add-for-the-status-css-second"))).getText().trim();
		Assert.assertEquals(PartialRefund_Status, "Partial Refund", "Refunded Status not found in order details");
		System.out.println("Partial refund is successful and status is updated to Refunded");
		
		Thread.sleep(4000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Refund']//parent::span//parent::button)[1]"))).click();
		Thread.sleep(2000);
		WebElement element1 = driver.findElement(By.xpath("//span[normalize-space()='Complete']//parent::button"));
		jse.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
		String FullRefund_Tag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#overflow-main-div span > div > span"))).getText().trim();
		Assert.assertEquals(FullRefund_Tag, "Refunded", "Refunded tag not found in order details");
		
		Thread.sleep(2000);
		String FullRefund_Status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.custom-class-add-for-the-status-css-second"))).getText().trim();
		Assert.assertEquals(FullRefund_Status, "Refunded", "Refunded Status not found in order details");
		System.out.println("Partial refund is successful and status is updated to Refunded");	
	}
}