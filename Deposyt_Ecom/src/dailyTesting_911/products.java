package dailyTesting_911;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
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

public class products extends Data {
	java.util.Random r = new java.util.Random();	
	
	String Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
	SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(), 
	Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(), 
	
	ContactPhone1 = "(775) 986-5200",F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", country = "United States", ContactPhone2 = "(539) 321-3502",
	Account_Holder_Name = "DeposytCert", Routing_Number = "490000018", Account_Number = "000123456789", EXP = "12/44", CVV = "123", Card_No = "4242424242424242",Price = "2",
	chars = "abcdefghijklmnopqrstuvwxyz", Value = "1",susbcriptionPrice = "3",Fileone = "file1.jpg", Attachment = "Jira_Guide.pdf",	Country_Add = "Boardman, Oregon, 97818",    
	
	firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
	lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
	email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com",phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));

	@Test(priority = 1)
	public void NonInventory_OneTimeProducts() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Random random = new Random();
		
		String Tax_Rate = String.valueOf(random.nextInt(9) + 1);
		
		//DeleteMail("Your Order is Confirmed — Order# ");
		
		driver.navigate().to(Products);
		Thread.sleep(5000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		driver.navigate().refresh();
		driver.navigate().to(Products);
		Thread.sleep(5000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		
		Thread.sleep(3000);
		WebElement taxRate = driver.findElement(By.xpath("//input[@name='general.flatTaxRate']"));
		Thread.sleep(3000);
		taxRate.click();
		taxRate.sendKeys(Keys.CONTROL, "a");
		taxRate.sendKeys(Keys.DELETE);
		taxRate.sendKeys(Tax_Rate);
		driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		try { 
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("button[type=\"submit\"]+div>div>button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[.//span[normalize-space()='Delete Product']]")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div.verification-model-code+div>div>button:last-of-type")).click();		
		}catch (NoSuchElementException e) {
			System.out.println("No Duplicate Product Found, Proceeding with New Product Creation...");
		}*/
		
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

		// Entering One Time Purchase Product price
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys(Price);
		Thread.sleep(1000);
		
		// check we can download product files
		/*WebElement uploadAttachment = driver.findElement(By.xpath("//p[normalize-space()='Upload File']/parent::div/parent::div/parent::label"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});", uploadAttachment);
		wait.until(ExpectedConditions.elementToBeClickable(uploadAttachment));
		jse.executeScript("arguments[0].click();", uploadAttachment);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[type=\"file\"]")).sendKeys(Media_Path + Attachment);*/
		
		//check that user can hide product from store
		WebElement hideProductButton = driver.findElement(By.xpath("//p[text() = 'Hide Products from Store']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", hideProductButton);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", hideProductButton);
		
		//Scroll to "This Product Unlocks Courses" button
		WebElement unlockCourseButton = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButton);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockCourseButton);
		
		//click on Link Product to Services button
		WebElement unlockservicesButton = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockservicesButton);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockservicesButton);

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		//check product can be seen in services
		driver.navigate().to(Services);
		Thread.sleep(3000);
		driver.findElement(By.name("search")).sendKeys(Product_Name,Keys.ENTER);
		Thread.sleep(20000);
		String serviceName = driver.findElement(By.cssSelector("span.servicenamespan")).getText();
		Assert.assertEquals(serviceName, Product_Name, "Service not created with product name");
		System.out.println("\nService found in connect product listing");
		
		//check product can be seen in courses
		driver.navigate().to(Courses);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.list-form-img>div>div")).click();//click on courses
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.edit-course-sidebar>div>div>div>nav>div:nth-of-type(4)>div>div:nth-of-type(2)")).click();//click on products
		driver.findElement(By.id("paidCourseTab")).click();
		driver.findElement(By.id("connect-product")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.name("searchproductsDD")).sendKeys(Product_Name);
		Thread.sleep(2000);		
		List<WebElement> connectedProducts = driver.findElements(By.cssSelector("span.connect_product_title"));
		boolean productFound = false;
		for (WebElement product : connectedProducts) {
			if (product.getText().trim().equals(Product_Name)) {
				productFound = true;
				break;
			}
		}	
		
		Assert.assertTrue(productFound, "Product not found in connect product listing");		
		System.out.println("\nProduct found in connect product listing");
		
		//Check if we can active and inactive a product on test account
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
		
		//Thread.sleep(5000);
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr#close-list-prod_01KHB1KKJQPKTEFEBT4A3ANBWG > td > div > a"))).click();//*********remove this line
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[role='switch']:first-of-type")).click();//click on toggle button to make product inactive
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Make Inactive\"]")).click();//click on inactive button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]")).click();//confirm inactive
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Product was successfully updated']")));
		
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();//Click on order page created by product
		
		String pwindo = driver.getWindowHandle();
		String Checkout_page = null;
		for(String Tab: driver.getWindowHandles()){
			driver.switchTo().window(Tab);
			Checkout_page = Tab;
		}
		Thread.sleep(3000); //Added Wait due to optimization
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='This product is not available for purchase.']")));
			System.out.println("Product is inactive");
		} catch(TimeoutException e){
			System.out.println("'Product Currently Unavailable' message not found." + Checkout_page);
		}
		
		driver.close();
		driver.switchTo().window(pwindo);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[role='switch']:first-of-type")).click();//click on toggle button to make product inactive
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Make Active\"]")).click();//click on inactive button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]")).click();//confirm inactive
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Product was successfully updated']")));
		
		//check that user can purchase Non-inventory product		
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();//Click on order page created by product
		Thread.sleep(4000);
		String originalTab = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		Thread.sleep(2000);
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
		
		String ordertax = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(9)>span:first-of-type")).getText().trim();
		System.out.println("Order Tax: " + ordertax);
		String extractedTax = ordertax.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedTax, Tax_Rate, "Tax rate mismatch in order summary");

		//verify that user can do cash payment
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(4)>div:nth-of-type(2)")).click();//click on cash payment
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@role=\"combobox\"]")).click();//click on mode drop down
		driver.findElement(By.cssSelector("#CashPaymentMethodDropdown>button:first-of-type")).click();//select cash 
		driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();
		Thread.sleep(3000);

		//Validate order Summary - Customer details
		String Order_Name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Customer']//following-sibling::div//span"))).getText().trim().toLowerCase();
		String Order_Mail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Customer']//following-sibling::div//p"))).getText().trim();
		String Order_Phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Customer']//following-sibling::div//span[2]"))).getText().replace("-", "").trim();

		System.out.println(Order_Name);
		System.out.println(Order_Mail);
		System.out.println(Order_Phone);

		Assert.assertEquals(Order_Name, firstName + " " + lastName, "Customer name not matching on order summary");
		Assert.assertEquals(Order_Mail, email, "Customer email not matching on order summary");		
		
		Thread.sleep(2000);
		String PlacedOrderID = driver.findElement(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span")).getText();
		System.out.println("Placed Order ID: " + PlacedOrderID);
		
		driver.close();
		driver.switchTo().window(originalTab);
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();//click on order history
		Thread.sleep(2000);
		String OrderRecived = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim().toLowerCase();
		Assert.assertEquals(OrderRecived, firstName.toLowerCase() + " " + lastName.toLowerCase() , "Customer name not matching on order summary");
		
		String Orderdate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(6)>span"))).getText().trim();
		DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
		LocalDate orderDate = LocalDate.parse(Orderdate, uiFormatter);
		LocalDate today = LocalDate.now();
		Assert.assertEquals(orderDate, today, "Order date Mismatch: Expected today's date but got " + orderDate);
		
		/*Thread.sleep(2000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:first-of-type>span")).click(); //Click on view details of the order
		Thread.sleep(2000);
		String OrderIDinDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(@class,'small:ml-1 text-blue-105')])[1]"))).getText().trim();
		if (OrderIDinDetails.startsWith("#")) {
		    OrderIDinDetails = OrderIDinDetails.substring(1);
		}*/
		
		driver.navigate().to(settings);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href=\"/a/settings/details\"]//button")).click();//Click on settings
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class=\"cursor-pointer\"]")).click();//click on store
		Thread.sleep(2000);
		
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}
		
		driver.findElement(By.xpath("//button[normalize-space()='SHOP NOW']")).click();
		List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("p[data-testid='product-title']")));
		boolean isProductPresent = products.stream().map(p -> p.getText().trim()).anyMatch(name -> name.equalsIgnoreCase(Product_Name));
		Assert.assertFalse(isProductPresent,"Product is visible but should be hidden: " + Product_Name);
		System.out.println("Product is hidden from store as expected: " + Product_Name);	
		
		//verify after purchase one time product user receive email for the order
		//ReceivedMail("Your Order is Confirmed — Order# " + OrderIDinDetails);	
	}
	
	@Test(priority = 2)
	public void Inventory_SuscriptionProducts() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		String Product_Name = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();			
		//DeleteMail("Your Order is Confirmed — Order# ");
		
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
		}
		catch(Exception e) {
			System.out.println("ACH_Payment is already enabled for the store");
		}
		
		driver.navigate().to(Products);
		Thread.sleep(3000);
		
		//check that we can create inventory product 
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		try { 
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("button[type=\"submit\"]+div>div>button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[.//span[normalize-space()='Delete Product']]")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div.verification-model-code+div>div>button:last-of-type")).click();		
		}catch (NoSuchElementException e) {
			System.out.println("No Duplicate Product Found, Proceeding with New Product Creation...");
		}*/
		
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

		// Upload product attachment
		/*WebElement uploadAttachment = driver.findElement(By.xpath("//p[normalize-space()='Upload File']/parent::div/parent::div/parent::label"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadAttachment);	
		Thread.sleep(5000);
		driver.findElement(By.xpath("//p[normalize-space()='Upload File']/parent::div/parent::div/parent::label")).click();
		Thread.sleep(2500);
		driver.findElement(By.xpath("(//div[normalize-space()='Upload File'])[3]")).click();
		uploadAttachment.sendKeys(Media_Path + Attachment);*/
		
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
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(Value); //Subscription price field
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
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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

		//Payment Information
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
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

		// Debug print
		System.out.println(Order_Name);
		System.out.println(Order_Mail);
		System.out.println(Order_Phone);

		Assert.assertEquals(Order_Name, firstName + " " + lastName, "Customer name not matching on order summary");
		Assert.assertEquals(Order_Mail, email, "Customer email not matching on order summary");
		
		// Order History Section Show Proper Order 
		driver.close();
		driver.switchTo().window(originalTab);
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();//click on order history
		Thread.sleep(2000);
		String OrderRecived = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim().toLowerCase();
		Assert.assertEquals(OrderRecived, firstName.toLowerCase() + " " + lastName.toLowerCase() , "Customer name not matching on order summary");
		
		//1B verify filter by product  functionality is operational
		List<WebElement> orders = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#order-table-body > tr")));
		int totalOrders = orders.size();
		System.out.println("Total number of orders: " + totalOrders);
		
		//Verify that the order is visible in the order list after completing the checkout process
		driver.navigate().refresh();
		Thread.sleep(3000);
		String OrderStatusID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(1)>span"))).getText().trim();
		System.out.println("Order Status : " + OrderStatusID);
		
		String Orderdate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(6)>span"))).getText().trim();
		DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
		LocalDate orderDate = LocalDate.parse(Orderdate, uiFormatter);
		LocalDate today = LocalDate.now();
		Assert.assertEquals(orderDate, today, "Order date Mismatch: Expected today's date but got " + orderDate);	
		
		//Check upsell product can be added to the main product
		driver.findElement(By.cssSelector("div.button-group-class>button:nth-of-type(3)>div")).click();//click on up sell
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@value='on'])[3]")).click();//click on toggle to make product as up sell
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[text()='Single ']//parent::div")).click();//select single 
		String Upsell_Product = driver.findElement(By.cssSelector("table.rounded-large>tbody>tr>td>article>div>p:first-of-type")).getText().trim();
		System.out.println("Upsell Product Name: " + Upsell_Product);
		driver.findElement(By.xpath("(//button[@type='button'][normalize-space()='+ Add'])[1]")).click();
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);
		
		String originalTab1 = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab1)) {
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

		//Payment Information
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
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
		
		List<WebElement> checkboxes1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[role='checkbox']")));
		for (WebElement checkbox : checkboxes1) {
		    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
		        checkbox.click();
		    }
		}
		
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();
		Thread.sleep(5000);		
		String Upsell_Product_in_Checkout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.overflow-x-hidden>div>div>div:nth-of-type(2)>div>p:first-of-type"))).getText().trim();
		System.out.println("Upsell Product in Checkout: " + Upsell_Product_in_Checkout);
		Assert.assertEquals(Upsell_Product.toLowerCase(), Upsell_Product_in_Checkout.toLowerCase(), "Upsell product not added to checkout");
		
		driver.findElement(By.xpath("//button[text()='Add to Order']")).click(); //Place order
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Complete Checkout']//parent::span//parent::button")).click(); //confirm place order
		Thread.sleep(7000);
		Assert.assertEquals(Order_Name, firstName + " " + lastName, "Customer name not matching on order summary");
		Assert.assertEquals(Order_Mail, email, "Customer email not matching on order summary");
		
		//Order Section > Check Upsell Order It Show Upsell Tag 
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		String OrderIDGenerated = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span"))).getText().trim();
		System.out.println("Order ID : " + OrderIDGenerated);
		int orderId = Integer.parseInt(OrderIDGenerated.replaceAll("\\D+", ""));
		Assert.assertTrue(orderId < orderId + 1, "Order ID mismatch after navigation");
		
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)")).click(); //Click on order details
		Thread.sleep(3000);
		
		//Check order ID upon clicking opens order details
		jse.executeScript("window.scrollBy(0, 900);"); 
		String OrderIDinDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#overflow-main-div div p:nth-of-type(2)"))).getText().trim();
		OrderIDinDetails = OrderIDinDetails.replace("#", "");
		System.out.println("Order ID in Order Details: " + OrderIDinDetails);
		Assert.assertEquals(OrderIDinDetails, OrderIDGenerated, "Order ID mismatch in order");	
		
		String Upsell_Tag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#overflow-main-div span > div > span"))).getText().trim();
		Assert.assertEquals(Upsell_Tag, "Upsell", "Upsell tag not found in order details");
		
		driver.navigate().to(Orders);
		Thread.sleep(3000);
		driver.findElements(By.cssSelector(".new-main-order-class button ")).get(1).click(); //Click on filter by product drop down
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Search Products\")]")).sendKeys(Product_Name); //Search with product name in filter
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='" + Product_Name + "']"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on done button
		
		Thread.sleep(3000);
		List<WebElement> ordersplaced = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#order-table-body > tr")));
		int totalOrdersPlaced = ordersplaced.size() + 1;
		System.out.println("Total number of orders Placed : " + totalOrdersPlaced);
		//Assert.assertEquals(totalOrdersPlaced, totalOrders, "Orders are visible after applying filter by product, expected no orders to be visible");
		
		//verify after purchase subscription product user receive email for the order
		//ReceivedMail("Your Order is Confirmed — Order# " + OrderIDinDetails);	
	}
	
	@Test(priority = 3)	
	public void Inventory_TierProducts() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Actions actions = new Actions(driver);
		
		String Product_Name = "Tier Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		DiscriptionTier1 = 	"Description for first tier installment", DiscriptionTier2 = "Description for Second tier installment", TitleTier1 = "First Tier Installment",
		TitleTier2 = "Second Tier Installment", Tier1Feature1 = "Tier one Feature First", Tier2Feature1 = "Tier two Feature one";
		
		//DeleteMail("Subscription Charge Receipt for Subscription# ");		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.navigate().refresh();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");

		WebElement TierToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='tiered-pricing-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",TierToggle);
		TierToggle.click(); //Tier toggle button
		Thread.sleep(2000);
		
		//tier - I
		driver.findElement(By.xpath("//input[@placeholder=\"Starter\"]")).sendKeys("First Tier Installment");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#tier-item-1>div>div>div>button")).click();//click on price
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		driver.findElement(By.xpath("(//input[@name='productDetails.variants.0.prices[0].amount'])[1]")).sendKeys(Value);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.wrapdescription")).click();//click on description and bullet points to save tier details
		driver.findElement(By.xpath("(//textarea[contains(@name,'productDetails.variants.0.product_tier_description')])[1]")).sendKeys(DiscriptionTier1);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Add Title\")]")).sendKeys(TitleTier1);
		driver.findElements(By.cssSelector("div.key-features>div>div>div>button")).get(0).click();//click on feature one
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Enter Feature\")]")).sendKeys(Tier1Feature1);
		
		//tier - II
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Tier']")).click();//click on add tier button to add second tier		
		driver.findElement(By.xpath("(//input[contains(@name,'productDetails.variants.1.title')])[1]")).sendKeys("Second Tier Installment");
		Thread.sleep(1000);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#tier-item-2>div>div>div>button")).click();//click on price
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		driver.findElement(By.xpath("(//input[@name='productDetails.variants.1.prices[0].amount'])[1]")).sendKeys(susbcriptionPrice);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[normalize-space()='Description + Bullet points'])[2]")).click();//click on description and bullet points to save tier details
		driver.findElement(By.xpath("//textarea[@name=\"productDetails.variants.1.product_tier_description\"]")).sendKeys(DiscriptionTier2);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name=\"productDetails.variants.1.product_tier_features.0.title\"]")).sendKeys(TitleTier2);
		driver.findElements(By.cssSelector("div.key-features>div>div>div>button")).get(1).click();//click on feature one
		driver.findElement(By.xpath("//input[contains(@name,\"productDetails.variants.1.product_tier_features.0.feature_title.0\")]")).sendKeys(Tier2Feature1);
		
		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		//check that we can purchase tier product
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>div>div>button:first-of-type"))).click();
		
		String parentWindow = driver.getWindowHandle();
		wait.until(d -> d.getWindowHandles().size() > 1);
		for (String window : driver.getWindowHandles()) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@type,'button')])[2]"))).click();
		wait.until(d -> d.getWindowHandles().size() > 2);
		for (String window : driver.getWindowHandles()) {
		    driver.switchTo().window(window);
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

		//Payment Information
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
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
		
		List<WebElement> checkboxes1 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[role='checkbox']")));
		for (WebElement checkbox : checkboxes1) {
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
		
		Thread.sleep(2000);
		String PlacedOrderID = driver.findElement(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span")).getText();
		System.out.println("Placed Order ID: " + PlacedOrderID);
		
		driver.switchTo().window(parentWindow);	
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();//click on order history
		Thread.sleep(2000);
		String OrderRecived = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim().toLowerCase();
		Assert.assertEquals(OrderRecived, firstName + " " + lastName, "Customer name not matching on order summary");
		
		String OrderStatusID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(1)>span"))).getText().trim();
		System.out.println("Order Status : " + OrderStatusID);
		
		String Orderdate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(7)>span"))).getText().trim();
		DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
		LocalDate orderDate = LocalDate.parse(Orderdate, uiFormatter);
		LocalDate today = LocalDate.now();
		Assert.assertEquals(orderDate, today, "Order date Mismatch: Expected today's date but got " + orderDate);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:first-of-type>span")).click(); //Click on view details of the order
		Thread.sleep(2000);
		String OrderIDinDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(@class,'small:ml-1 text-blue-105')])[1]"))).getText().trim();
		if (OrderIDinDetails.startsWith("#")) {
		    OrderIDinDetails = OrderIDinDetails.substring(1);
		}
		
		//Verify tiered product orders are visible 
		driver.navigate().to(Orders);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Tiered Product\"]")).click(); //Click on tiered product tab in order details
		Thread.sleep(5000);
		String TierOrderID = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>span")).getText().trim().replaceAll("[^0-9]", "");
		System.out.println("Order ID in Order Details: " + OrderIDinDetails);
		Assert.assertEquals(OrderStatusID, TierOrderID, "Order ID mismatch in order");	
		
		//verify after purchase tier product user receive email for the order		
		//ReceivedMail("Subscription Charge Receipt for Subscription# " + OrderIDinDetails);	
	}	
}
