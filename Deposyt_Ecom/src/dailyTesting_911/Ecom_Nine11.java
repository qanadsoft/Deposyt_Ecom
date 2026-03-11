package dailyTesting_911;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert; 
import org.testng.annotations.Test;

import Master.Data;

public class Ecom_Nine11 extends Data {

	java.util.Random r = new java.util.Random();	
	
	String Price = "2", Value = "1",susbcriptionPrice = "3",Fileone = "file1.jpg", Filetwo = "file2.png", Filethree = "file3.jpeg", Filefour = "file4.png", Filefive = "file5.png",
		Filesix = "file6.jpg", Fileseven = "file7.jpg",Fileeight = "file8.jpg", Filenine = "file9.jpg", Attachment = "Jira_Guide.pdf",

		Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),Country_Add = "Boardman, Oregon, 97818",
		Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(), ContactPhone1 = "(775) 986-5200",
		F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", country = "United States", ContactPhone2 = "(539) 321-3502",
		Account_Holder_Name = "DeposytCert", Routing_Number = "490000018", Account_Number = "000123456789", EXP = "12/44", CVV = "123", Card_No = "4242424242424242";

		String chars = "abcdefghijklmnopqrstuvwxyz";	    
		String firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26));
		String lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26));
		String email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com";
		String phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
	
	@Test(priority = 1)
	public void NonInventory_OneTimeProducts() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Random random = new Random();
		
		String Tax_Rate = String.valueOf(random.nextInt(9) + 1);
		
		//DeleteMail("Your Order is Confirmed — Order# ");
		
		driver.navigate().to(Products);
		Thread.sleep(10000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
		Thread.sleep(3000);
		WebElement taxRate = driver.findElement(By.xpath("//input[@name='general.flatTaxRate']"));
		taxRate.click();
		taxRate.sendKeys(Keys.CONTROL, "a");
		taxRate.sendKeys(Keys.DELETE);
		taxRate.sendKeys(Tax_Rate);
		driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		try { 
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone, Media_Path + Filetwo, Media_Path + Filethree, Media_Path + Filefour, Media_Path + Filefive, Media_Path + Filesix,
			Media_Path + Fileseven, Media_Path + Fileeight, Media_Path + Filenine};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);

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
		Thread.sleep(7000);
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
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Product Currently Unavailable']")));
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
		
		String ordertax = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div.flex-nowrap>div:nth-of-type(3)>div>span:first-of-type")).getText().trim();
		System.out.println("Order Tax: " + ordertax);
		String extractedTax = ordertax.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedTax, Tax_Rate, "Tax rate mismatch in order summary");

		//verify that user can do cash payment
		driver.findElement(By.xpath("//input[@value=\"cash\"]")).click();//click on cash payment
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@role=\"combobox\"]")).click();//click on mode drop down
		driver.findElement(By.id("CashPaymentMethodDropdown")).click();//select cash 
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
		
		String Product_Name = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
			
		//DeleteMail("Your Order is Confirmed — Order# ");
		
		driver.navigate().to(Products);
		Thread.sleep(3000);
		
		//check that we can create inventory product 
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		try { 
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone, Media_Path + Filetwo, Media_Path + Filethree, Media_Path + Filefour, Media_Path + Filefive, Media_Path + Filesix,
			Media_Path + Fileseven, Media_Path + Fileeight, Media_Path + Filenine};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);

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
		jse.executeScript("window.scrollBy(0,500)");
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
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		
		Thread.sleep(5000);
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
		driver.findElement(By.xpath("//input[@value=\"ach-cash\"]")).click();//click on ACH cash payment
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
		String Upsell_Product = driver.findElement(By.cssSelector("table.rounded-large>tbody>tr>td>article>div>p:first-of-type")).getText().trim();
		System.out.println("Upsell Product Name: " + Upsell_Product);
		driver.findElement(By.xpath("(//button[@type='button'][normalize-space()='+ Add'])[1]")).click(); //Add
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click(); //Save
		
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
		
		Thread.sleep(5000);
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
		driver.findElement(By.xpath("//input[@value=\"ach-cash\"]")).click();//click on ACH cash payment
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
		String Upsell_Product_in_Checkout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.overflow-x-hidden p p:first-of-type"))).getText().trim();
		System.out.println("Upsell Product in Checkout: " + Upsell_Product_in_Checkout);
		Assert.assertEquals(Upsell_Product, Upsell_Product_in_Checkout, "Upsell product not added to checkout");
		
		driver.findElement(By.xpath("//button[text()='Add To Order']")).click(); //Place order
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Complete Checkout']")).click(); //confirm place order
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
		SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(),
		DiscriptionTier1 = 	"Description for first tier installment", DiscriptionTier2 = "Description for Second tier installment", TitleTier1 = "First Tier Installment",
		TitleTier2 = "Second Tier Installment", Tier1Feature1 = "Tier one Feature First", Tier2Feature1 = "Tier two Feature one";
		
		//DeleteMail("Subscription Charge Receipt for Subscription# ");
		
		driver.navigate().to(Products);
		Thread.sleep(3000);
		
		//check that we can create inventory product 
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		try { 
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone, Media_Path + Filetwo, Media_Path + Filethree, Media_Path + Filefour, Media_Path + Filefive, Media_Path + Filesix,
			Media_Path + Fileseven, Media_Path + Fileeight, Media_Path + Filenine};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);

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
		jse.executeScript("window.scrollBy(0,500)");

		WebElement TierToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='tiered-pricing-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",TierToggle);
		TierToggle.click(); //Tier toggle button
		Thread.sleep(2000);
		
		//tier - I
		driver.findElement(By.xpath("//input[@placeholder=\"Starter\"]")).sendKeys("First Tier Installment");
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
		driver.findElement(By.cssSelector("#tier-item-2>div>div>div>button")).click();//click on price
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		driver.findElement(By.xpath("(//input[@name='productDetails.variants.1.prices[0].amount'])[1]")).sendKeys(susbcriptionPrice);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[normalize-space()='Description + Bullet points'])[2]")).click();//click on description and bullet points to save tier details
		driver.findElement(By.xpath("//textarea[@name=\"productDetails.variants.1.product_tier_description\"]")).sendKeys(DiscriptionTier2);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		driver.findElement(By.xpath("//input[@name=\"productDetails.variants.1.product_tier_features.0.title\"]")).sendKeys(TitleTier2);
		driver.findElements(By.cssSelector("div.key-features>div>div>div>button")).get(1).click();//click on feature one
		driver.findElement(By.xpath("//input[contains(@name,\"productDetails.variants.1.product_tier_features.0.feature_title.0\")]")).sendKeys(Tier2Feature1);
		
		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		//check that we can purchase tier product
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		Thread.sleep(3000);
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

		//Payment Information
		driver.findElement(By.xpath("//input[@value=\"ach-cash\"]")).click();//click on ACH cash payment
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
	
	@Test(priority = 4)
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
		
		//6A Verify is giftcard is visible
		driver.navigate().to(Gift_Cards);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='New Gift Card'])[3]"))).click(); // click on New Gift Card button
		driver.findElement(By.xpath("(//div[@id='region.region_id'])[1]")).click(); //click on region drop down
		driver.findElement(By.id("react-select-2-option-1")).click(); //Select Default as region
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
		driver.findElement(By.id("react-select-2-option-1")).click(); //Select Default as region
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
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone, Media_Path + Filetwo, Media_Path + Filethree, Media_Path + Filefour, Media_Path + Filefive, Media_Path + Filesix,
			Media_Path + Fileseven, Media_Path + Fileeight, Media_Path + Filenine};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		
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
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		driver.findElement(By.xpath("//input[@value=\"ach-cash\"]")).click();//click on ACH cash payment
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
		
		String Billing_Address1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div>div:nth-of-type(2)>div>div>div>p:nth-of-type(2)"))).getText().trim();
		String Billing_Address2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div>div:nth-of-type(2)>div>div>div>p:nth-of-type(3)"))).getText().trim();
		String Billing_Address3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div>div:nth-of-type(2)>div>div>div>p:nth-of-type(4)"))).getText().trim();
		
		// Order History Section Show Proper Order 
		driver.close();
		driver.switchTo().window(originalTab);
		Thread.sleep(2000);
		
		//check that default checkout , checkout and default product page created and links are working
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages	
		driver.findElements(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).get(1).click();//Click on default product page
		
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
		
		driver.findElements(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).get(2).click();//Click on default product page	
		String pwindo11 = driver.getWindowHandle();
		String Checkout_page11 = null;
		for(String Tab: driver.getWindowHandles()){
			driver.switchTo().window(Tab);
			Checkout_page11 = Tab;
		}
		Thread.sleep(3000); //Added Wait due to optimization
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
		String fullName = firstName + " " + lastName;
		String OrderName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@data-state,'closed') and normalize-space()='" + fullName + "']"))).getText().trim().toLowerCase();
		String OrderEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@data-state,'closed') and normalize-space()='" + email + "']"))).getText().trim().toLowerCase();
		
		Assert.assertEquals(OrderName, fullName, "Customer name not matching in order details");
		Assert.assertEquals(OrderEmail, email, "Customer email not matching in order details");
		
		String OrderBillingAddress1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[contains(@class,'break-words') and normalize-space()='" + Street_Add + "'])[3]"))).getText().trim();
		String OrderBillingAddress2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[contains(normalize-space(),'" + country + "')])[3]"))).getText().trim();
		String OrderBillingAddress3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[contains(normalize-space(),'" + Country_Add + "')])[3]"))).getText().trim();
		
		Assert.assertEquals(OrderBillingAddress1, Billing_Address1, "Street address not matching in order details");
		Assert.assertEquals(OrderBillingAddress2, Billing_Address3, "Country not matching in order details");
		Assert.assertEquals(OrderBillingAddress3, Billing_Address2, "City, State and Zip code not matching in order details");
		
		/*String GiftcardValid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[normalize-space()='" + GiftCardID + "'])[1]"))).getText().trim();
		String DiscountValid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[text()='" + giftCardName + "'])[1]"))).getText().trim();
		
		Assert.assertEquals(GiftcardValid, GiftCardID, "Gift card code not applied properly");
		Assert.assertEquals(DiscountValid, giftCardName, "Discount code not applied properly");*/			
	}
	
	@Test(priority = 5)
	public void Order_status() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;			
			
		//Verify order is visible in order list after complete order from invoice
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
		
		driver.findElement(By.xpath("(//button[@role='checkbox'])[2]")).click(); //Clicking on checkbox
		driver.findElement(By.xpath("(//button[@role='checkbox'])[3]")).click(); //Clicking on checkbox
		
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
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Payment Type']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class,'custom-class-for-aaply-the-css')]//div[contains(@class,'flex flex-1 items-center') and .//div[contains(@id,'placeholder')]]")).click(); //Clicking on payment type dropdown
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='react-select-2-listbox']//div[@role='option'][1]")).click(); //Select payment type cash
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space(.//p/text())='Record Cash Sale']")).click(); //Clicking on record cash sale button
		Thread.sleep(15000);
		String orderId = driver.findElement(By.xpath("//div[h2[normalize-space(text())='Transaction Summary']]/p")).getText().trim().replace("#", ""); //Getting order id
		System.out.println("Order ID: " + orderId);
		driver.get("https://store.app.deposyt.com/a/orders?tab=all-orders");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[normalize-space(text())='Virtual Terminal']")).click(); //Clicking on virtual terminal filter
		Thread.sleep(2000);
		String orderIdInVirtualTerminal = driver.findElement(By.xpath("//tbody[@id='order-table-body']/tr[1]/td[2]/span")).getText().trim().replace("#", "");
		System.out.println("Order ID in Virtual Terminal: " + orderIdInVirtualTerminal);
		Assert.assertEquals(orderId, orderIdInVirtualTerminal, "Order ID in virtual terminal does not match the recorded order ID.");		
	}
	
	@Test(priority = 6)
	public void Place_Order() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		Date cDate = new Date();
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String giftCardName = "GiftCard " + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
		
		//Verify services orders are visible
		driver.navigate().to(Services);
		Thread.sleep(3000);
		driver.findElement(By.xpath(" (//div[@class='mr-4 fb-setting-icon-wrapper'])[2]")).click(); //Click on service setting
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
		Thread.sleep(5000);
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
		
		Thread.sleep(5000);
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
	
	@Test(priority = 7)
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
		
		//Verify course orders are visible
		driver.navigate().to(Courses);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.list-form-img>div>div")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.edit-course-sidebar>div>div>div>nav>div:nth-of-type(4)>div>div:nth-of-type(2)")).click();
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

		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter>div>a:first-of-type"))).click();
		Thread.sleep(3000);

		driver.close();
		driver.switchTo().window(parentWindow);

		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Switch to checkout window
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(parentWindow)) {
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
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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
		Thread.sleep(3000);
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
		driver.findElements(By.xpath("//div[@role='dialog']//button[@aria-haspopup='menu']")).get(1).click(); //Click on edit order button
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
		driver.findElement(By.xpath("(//span[normalize-space()='Copy Confirmation-Request Link'])[1]")).click();

		jse.executeScript("window.__copiedText = '';" +"navigator.clipboard.writeText = function(text) {" +"   window.__copiedText = text;" +"};");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='Copy Confirmation-Request Link'])[1]"))).click();

		String copiedUrl = (String) jse.executeScript("return window.__copiedText;");
		System.out.println("Copied URL: " + copiedUrl);

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
		driver.findElement(By.cssSelector("input#email")).sendKeys(WMLogin);
		Thread.sleep(10000);
		
		WebElement CardName = driver.findElement(By.id("cardHolderName"));
		jse.executeScript("arguments[0].scrollIntoView(true);", CardName);
		Thread.sleep(1000);
		CardName.sendKeys(F_Name+" "+L_Name);
		driver.switchTo().frame("CollectJSInlineccnumber");
		driver.findElement(By.id("ccnumber")).sendKeys(Card_No);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlinecvv");
		driver.findElement(By.id("cvv")).sendKeys(CVV);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlineccexp");
		driver.findElement(By.id("ccexp")).sendKeys(EXP);;
		driver.switchTo().defaultContent();	
		
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();
		Thread.sleep(5000);
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
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");
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
		
		WebElement unlockCourseButton = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButton);
		Thread.sleep(500);
		jse.executeScript("arguments[0].click();", unlockCourseButton);

		WebElement unlockservicesButton = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockservicesButton);
		Thread.sleep(500);
		jse.executeScript("arguments[0].click();", unlockservicesButton);

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
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
		Thread.sleep(2000);
		
		WebElement CardName1 = driver.findElement(By.id("cardHolderName"));
		jse.executeScript("arguments[0].scrollIntoView(true);", CardName1);
		Thread.sleep(1000);
		CardName1.sendKeys(F_Name+" "+L_Name);
		driver.switchTo().frame("CollectJSInlineccnumber");
		driver.findElement(By.id("ccnumber")).sendKeys("4111111111111111");
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlinecvv");
		driver.findElement(By.id("cvv")).sendKeys(CVV);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlineccexp");
		driver.findElement(By.id("ccexp")).sendKeys(EXP);;
		driver.switchTo().defaultContent();	
		
		driver.findElement(By.cssSelector("button[role='checkbox']")).click(); //Clicking on checkbox
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();
		Thread.sleep(5000);
		
		String PlacedOrderID1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID1);
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
	
	@Test(priority = 8)
	public void refund_order() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	

		DeleteMail("Your Voided Order Receipt");

		//Verify Refund functionality is operational ( Full and partial)
		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
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

		Thread.sleep(5000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(WMLogin);  
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(F_Name);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(L_Name);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(Number);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); //select country as USA
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		Thread.sleep(2000);

		//Payment Information
		driver.findElement(By.xpath("//input[@value=\"ach-cash\"]")).click();//click on ACH cash payment
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

		driver.navigate().to(Orders);
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click();
		
		
	}
	
	@Test(priority = 10)
	public void OneTime_SalesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		String CreatePage = "Demo Checkout Page";
		String Pageslug = "";
		int Value = 1;
		String chars = "abcdefghijklmnopqrstuvwxyz";
	    
	    String firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26));
	    String lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26));
	    String email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com";
	    String phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String totalRevenue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue1 = totalRevenue1.replaceAll("[^0-9.]", ""); 
		double RevenueValue1 = Double.parseDouble(totalRevenueValue1);	
		System.out.println("Total Revenue Value Before In Sales Tab : " + RevenueValue1);
		
		String totalorders1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue1 = totalorders1.replaceAll("[^0-9.]", ""); 
		double Revenueorders1 = Double.parseDouble(totalordersValue1);	
		System.out.println("Total order Value Before In Sales Tab : " + Revenueorders1);
		
		String totalcust1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordercust1 = totalcust1.replaceAll("[^0-9.]", ""); 
		double Revenuecust = Double.parseDouble(totalordercust1);	
		System.out.println("Total Customers Before In Sales Tab : " + Revenuecust);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='One-Time Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenue2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue2 = totalRevenue2.replaceAll("[^0-9.]", ""); 
		double RevenueValue2 = Double.parseDouble(totalRevenueValue2);	
		System.out.println("Total Revenue Value Before In One-Time Tab : " + RevenueValue2);
		
		String totalorders2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue2 = totalorders2.replaceAll("[^0-9.]", ""); 
		double Revenueorders2 = Double.parseDouble(totalordersValue2);	
		System.out.println("Total orders Before In One-Time Tab : " + Revenueorders2);	
		
		String totaluser2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusers2 = totaluser2.replaceAll("[^0-9.]", ""); 
		double Revenueusers2 = Double.parseDouble(totalordersusers2);	
		System.out.println("Total Users Before In One-Time Tab : " + Revenueusers2);
		System.out.println();
		
		//Check By Creating New one time  order and sales revenue data increases Accordingly (all sales and one time tab )
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};
		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);

		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("1");
		Thread.sleep(1000);
		
		WebElement unlockCourseButton = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButton);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockCourseButton);
		
		WebElement unlockservicesButton = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockservicesButton);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockservicesButton);

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		
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
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String totalRevenue3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue3 = totalRevenue3.replaceAll("[^0-9.]", ""); 
		double RevenueValue3 = Double.parseDouble(totalRevenueValue3);	
		System.out.println("Total Revenue Value After In Sales Tab : " + RevenueValue3);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='One-Time Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenue4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue4 = totalRevenue4.replaceAll("[^0-9.]", ""); 
		double RevenueValue4 = Double.parseDouble(totalRevenueValue4);	
		System.out.println("Total Revenue Value After In One-Time Tab : " + RevenueValue4);
		
		double expectedTotalRevenue = RevenueValue1 + Value;
		Assert.assertEquals( RevenueValue4,expectedTotalRevenue, 0.1, "Total revenue is not updated correctly after adding price In all sales tab");
		
		double expectedTotalRevenue1 = RevenueValue2 + Value;
		Assert.assertEquals(RevenueValue4,expectedTotalRevenue1, 0.1, "Total revenue is not updated correctly after adding price In one time tab");
		
		//Check after placing one time order Sales data showing correct when we select specific products using productc filters  (All Sales and one time atb)
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Filter By Product Filter Button']")).click(); //Click on product filter
		driver.findElement(By.cssSelector("div#product-dropdown>div:nth-of-type(2)>div>div:first-of-type")).click(); //Search with product name in filter
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click(); //Click on apply button in filter
		
		String totalRevenue5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue5 = totalRevenue5.replaceAll("[^0-9.]","");
		double RevenueValue5 = Double.parseDouble(totalRevenueValue5);
		System.out.println("Total Revenue Value After Applying Product Filter In Sales Tab : " + RevenueValue5);		
		Thread.sleep(2000);
		Assert.assertEquals(RevenueValue5, Value, 0.1, "Total revenue is not updated correctly after applying product filter");
		
		String totalorders = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueorders = totalorders.replaceAll("[^0-9.]","");
		double orderValue = Double.parseDouble(totalRevenueorders);
		System.out.println("Total Order Value After Applying Product Filter In Sales Tab : " + orderValue);		
		Thread.sleep(2000);
		Assert.assertEquals((int) orderValue, 1, "Total Order Values is not updated correctly after applying product filter");
		
		String totalcustomers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordercustomers = totalcustomers.replaceAll("[^0-9.]","");
		double customersValue = Double.parseDouble(totalordercustomers);
		System.out.println("Total Customers After Applying Product Filter In Sales Tab : " + customersValue);		
		Thread.sleep(2000);
		Assert.assertEquals((int) customersValue, 1, "Total Customers not updated correctly after applying product filter");
		System.out.println();
		
		//Verify that if we have placed one new order with new customer for one time product then it is showing one order count and 1 new customer count when we select (all sales and one time tab )
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		
		//verify that user can create manually product and checkout pages
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Add Page']//parent::div//parent::button")).click();//Click on order page created by product
		driver.findElement(By.cssSelector("div.product-card>div:first-of-type")).click(); //Select first product from list
		Thread.sleep(2000);
		driver.findElement(By.name("page_name")).sendKeys(CreatePage); //Click on create page button
		driver.findElement(By.name("url_slug")).sendKeys(Pageslug); //Click on create page button
		driver.findElement(By.xpath("//span[normalize-space()=\"Create Page\"]")).click(); //Click on create page button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("button[aria-label='Save Action Button']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("button[aria-label='Close Editor']")).click(); 
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Exit\"]")).click();//Click on pages
		
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		String CreadtedPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.custom-class-table>table>tbody>tr:last-of-type>td:nth-of-type(2)>p:first-of-type"))).getText().trim();
		Assert.assertEquals(CreadtedPage, CreatePage,"Created page name is not showing in funnel table");
		
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();//Click on order page created by product		
		String originalTab1 = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab1)) {
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
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(originalTab1);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);		
		String totalorders11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue11 = totalorders11.replaceAll("[^0-9.]", ""); 
		double Revenueorders11 = Double.parseDouble(totalordersValue11);	
		System.out.println("Total order Value After In Sales Tab : " + Revenueorders11);
		
		Thread.sleep(2000);
		Assert.assertEquals(Revenueorders11, Revenueorders1 + 2,"Order count is not incremented by 1");
		
		String totalcust11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalcustomers11 = totalcust11.replaceAll("[^0-9.]", ""); 
		double custorders11 = Double.parseDouble(totalcustomers11);	
		System.out.println("Total Customers After In Sales Tab : " + custorders11);
		System.out.println();
		
		Thread.sleep(2000);
		Assert.assertEquals(custorders11, Revenuecust + 1,"Order count is not incremented by 1");
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='One-Time Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenueone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValueone = totalRevenueone.replaceAll("[^0-9.]", ""); 
		double RevenueValueone = Double.parseDouble(totalRevenueValueone);	
		System.out.println("Total Revenue Value After In One-Time Tab : " + RevenueValueone);
		Assert.assertEquals(RevenueValueone,RevenueValue4 + 1, 0.1, "Total revenue is not updated correctly after adding price In one time tab");
		
		String totalordersone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValueone = totalordersone.replaceAll("[^0-9.]", ""); 
		double Revenueordersone = Double.parseDouble(totalordersValueone);	
		System.out.println("Total orders After In One-Time Tab : " + Revenueordersone);	
		
		String totaluserone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusersone = totaluserone.replaceAll("[^0-9.]", ""); 
		double Revenueusersone = Double.parseDouble(totalordersusersone);	
		System.out.println("Total Users After In One-Time Tab : " + Revenueusersone);		
		
		Assert.assertEquals(Revenueordersone, Revenueorders2 + 2, "Order count is not incremented in one time tab");
		Assert.assertEquals(Revenueusersone, Revenueusers2, "Customer count is not incremented in one time tab");	
	}
	
	@Test(priority = 11)
	public void subscription_SalesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		String Product_Name = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
	    	
	    //Verify that after placing the subscription order if we select subscription tab then it is shwing all stats  correct (Like order count, customer count, repate count, page view, conversion rate )
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String totalRevenue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue1 = totalRevenue1.replaceAll("[^0-9.]", ""); 
		double RevenueValue1 = Double.parseDouble(totalRevenueValue1);	
		System.out.println("Total Revenue Value Before In All Sales Tab : " + RevenueValue1);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Subscriptions Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenue2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue2 = totalRevenue2.replaceAll("[^0-9.]", ""); 
		double RevenueValue2 = Double.parseDouble(totalRevenueValue2);	
		System.out.println("Total Revenue Value Before In Subscriptions Tab : " + RevenueValue2);
		
		String totalorders2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue2 = totalorders2.replaceAll("[^0-9.]", ""); 
		double Revenueorders2 = Double.parseDouble(totalordersValue2);	
		System.out.println("Total orders Before In Subscriptions Tab : " + Revenueorders2);	
		
		String totaluser2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusers2 = totaluser2.replaceAll("[^0-9.]", ""); 
		double Revenueusers2 = Double.parseDouble(totalordersusers2);	
		System.out.println("Total Users Before In Subscriptions Tab : " + Revenueusers2);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Filter By Product Filter Button']")).click(); //Click on product filter
		driver.findElement(By.cssSelector("div#product-dropdown>div:nth-of-type(2)>div>div:first-of-type")).click(); //Search with product name in filter
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click(); //Click on apply button in filter
		
		String totalRevenue5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue5 = totalRevenue5.replaceAll("[^0-9.]","");
		double RevenueValue5 = Double.parseDouble(totalRevenueValue5);
		System.out.println("Total Revenue Value Before Applying Product Filter In subscription Tab : " + RevenueValue5);		
				
		String totalorders = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueorders = totalorders.replaceAll("[^0-9.]","");
		double orderValue = Double.parseDouble(totalRevenueorders);
		System.out.println("Total Order Value Before Applying Product Filter In subscription Tab : " + orderValue);		
				
		String totalcustomers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordercustomers = totalcustomers.replaceAll("[^0-9.]","");
		double customersValue = Double.parseDouble(totalordercustomers);
		System.out.println("Total Customers Before Applying Product Filter In subscription Tab : " + customersValue);	
				
		//Verify that when we place the order for subscription product then its revenue is getting update in (all sales and subscription tab)
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");
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
		
		WebElement unlockCourseButton = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButton);
		Thread.sleep(500);
		jse.executeScript("arguments[0].click();", unlockCourseButton);

		WebElement unlockservicesButton = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockservicesButton);
		Thread.sleep(500);
		jse.executeScript("arguments[0].click();", unlockservicesButton);

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		
		Thread.sleep(5000);
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
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String totalRevenue11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue11 = totalRevenue11.replaceAll("[^0-9.]", ""); 
		double RevenueValue11 = Double.parseDouble(totalRevenueValue11);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + RevenueValue11);
		
		double expectedTotalRevenue = RevenueValue1 + Double.parseDouble(susbcriptionPrice);
		Assert.assertEquals(RevenueValue11,expectedTotalRevenue,"Total Revenue value mismatch after calculation.");
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Subscriptions Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenue21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue21 = totalRevenue21.replaceAll("[^0-9.]", ""); 
		double RevenueValue21 = Double.parseDouble(totalRevenueValue21);	
		System.out.println("Total Revenue Value After In Subscriptions Tab : " + RevenueValue21);
		
		double expectedTotalRevenue1 = RevenueValue2 + Double.parseDouble(susbcriptionPrice);
		Assert.assertEquals(RevenueValue21, expectedTotalRevenue1, "Total revenue is not updated in subscription tab after placing subscription order");
		
		String totalorders21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue21 = totalorders21.replaceAll("[^0-9.]", ""); 
		double Revenueorders21 = Double.parseDouble(totalordersValue21);	
		System.out.println("Total orders After In Subscriptions Tab : " + Revenueorders21);	
		
		String totaluser21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusers21 = totaluser21.replaceAll("[^0-9.]", ""); 
		double Revenueusers21 = Double.parseDouble(totalordersusers21);	
		System.out.println("Total Users After In Subscriptions Tab : " + Revenueusers21);
		
		Assert.assertEquals(Revenueorders21, Revenueorders2 + 1, "Order count is not incremented in subscription tab");
		Assert.assertEquals(Revenueusers21, Revenueusers2 + 1, "Customer count is not incremented in subscription tab");
				
		//Check that if we have selected only subscription product from drop-down and then if we select all sales or subscription tab then only it should show all the stats 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Filter By Product Filter Button']")).click(); //Click on product filter
		driver.findElement(By.cssSelector("div#product-dropdown>div:nth-of-type(2)>div>div:first-of-type")).click(); //Search with product name in filter
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click(); //Click on apply button in filter
		
		String totalRevenue51 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue51 = totalRevenue51.replaceAll("[^0-9.]","");
		double RevenueValue51 = Double.parseDouble(totalRevenueValue51);
		System.out.println("Total Revenue Value Before Applying Product Filter In subscription Tab : " + RevenueValue51);		
				
		String totalorders1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueorders1 = totalorders1.replaceAll("[^0-9.]","");
		double orderValue1 = Double.parseDouble(totalRevenueorders1);
		System.out.println("Total Order Value Before Applying Product Filter In subscription Tab : " + orderValue1);		
				
		String totalcustomers1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordercustomers1 = totalcustomers1.replaceAll("[^0-9.]","");
		double customersValue1 = Double.parseDouble(totalordercustomers1);
		System.out.println("Total Customers Before Applying Product Filter In subscription Tab : " + customersValue1);	
		
		Assert.assertEquals(RevenueValue51,Double.parseDouble(susbcriptionPrice),0.01,"Total revenue is not updated correctly Before applying product filter");
		Assert.assertEquals((int) orderValue1, 1, "Total Order Values is not updated correctly After applying product filter");
		Assert.assertEquals((int) customersValue1, 1, "Total Customers not updated correctly After applying product filter");
		
		//Verify that if we select upsell product from product drop-down then its data showing corrctly 
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();	
		Thread.sleep(2000);	
		driver.findElement(By.cssSelector("div.button-group-class>button:nth-of-type(3)>div")).click();//click on up sell
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@value='on'])[3]")).click();//click on toggle to make product as up sell
		Thread.sleep(3000);
		String Upsell_Product = driver.findElement(By.cssSelector("table.rounded-large>tbody>tr>td>article>div>p:first-of-type")).getText().trim();
		System.out.println("Upsell Product Name: " + Upsell_Product);
		driver.findElement(By.xpath("(//button[@type='button'][normalize-space()='+ Add'])[1]")).click(); //Add
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click(); //Save

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

		Thread.sleep(5000);
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
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolder11 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder11.clear();
		cardHolder11.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		driver.findElement(By.cssSelector("button[type = 'submit']")).click();
		Thread.sleep(5000);		
		String Upsell_Product_in_Checkout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.overflow-x-hidden p p:first-of-type"))).getText().trim();
		System.out.println("Upsell Product in Checkout: " + Upsell_Product_in_Checkout);
		Assert.assertEquals(Upsell_Product, Upsell_Product_in_Checkout, "Upsell product not added to checkout");

		driver.findElement(By.xpath("//button[text()='Add To Order']")).click(); //Place order
		driver.findElement(By.xpath("//span[text()='Complete Checkout']")).click(); //confirm place order
		Thread.sleep(3000);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String totalRevenue111 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue111 = totalRevenue111.replaceAll("[^0-9.]", ""); 
		double RevenueValue111 = Double.parseDouble(totalRevenueValue111);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + RevenueValue111);
		
		double expectedTotalRevenue11 = RevenueValue1 + Double.parseDouble(susbcriptionPrice);
		Assert.assertEquals(RevenueValue111,expectedTotalRevenue11,"Total Revenue value mismatch after calculation.");
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Upsells Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenue211 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue211 = totalRevenue211.replaceAll("[^0-9.]", ""); 
		double RevenueValue211 = Double.parseDouble(totalRevenueValue211);	
		System.out.println("Total Revenue Value After In upsell Tab : " + RevenueValue211);
		
		double expectedTotalRevenue111 = RevenueValue2 + Double.parseDouble(susbcriptionPrice);
		Assert.assertEquals(RevenueValue211, expectedTotalRevenue111, "Total revenue is not updated in upsell tab after placing upsell order");
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Filter By Product Filter Button']")).click(); //Click on product filter
		driver.findElement(By.cssSelector("div#product-dropdown>div:nth-of-type(2)>div>div:first-of-type")).click(); //Search with product name in filter
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click(); //Click on apply button in filter
		
		String totalRevenue511 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue511 = totalRevenue511.replaceAll("[^0-9.]","");
		double RevenueValue511 = Double.parseDouble(totalRevenueValue511);
		System.out.println("Total Revenue Value After Applying Product Filter In upsell Tab : " + RevenueValue511);		
				
		String totalorders11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueorders11 = totalorders11.replaceAll("[^0-9.]","");
		double orderValue11 = Double.parseDouble(totalRevenueorders11);
		System.out.println("Total Order Value After Applying Product Filter In upsell Tab : " + orderValue11);		
				
		String totalcustomers11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordercustomers11 = totalcustomers11.replaceAll("[^0-9.]","");
		double customersValue11 = Double.parseDouble(totalordercustomers11);
		System.out.println("Total Customers After Applying Product Filter In upsell Tab : " + customersValue11);	
		
		Assert.assertEquals(RevenueValue511,Double.parseDouble(susbcriptionPrice),0.01,"Total revenue is not updated correctly Before applying product filter");
		Assert.assertEquals((int) orderValue11, 1, "Total Order Values is not updated correctly After applying product filter");
		Assert.assertEquals((int) customersValue11, 1, "Total Customers not updated correctly After applying product filter");
	}

	@Test(priority = 12)
	public void Tier_SalesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);

		String Product_Name1 = "Tier Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
			DiscriptionTier1 = 	"Description for first tier installment", DiscriptionTier2 = "Description for Second tier installment", TitleTier1 = "First Tier Installment",
			TitleTier2 = "Second Tier Installment", Tier1Feature1 = "Tier one Feature First", Tier2Feature1 = "Tier two Feature one";

		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String totalRevenue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue1 = totalRevenue1.replaceAll("[^0-9.]", ""); 
		double RevenueValue1 = Double.parseDouble(totalRevenueValue1);	
		System.out.println("Total Revenue Value Before In All Sales Tab : " + RevenueValue1);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Tiered Product']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenue2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue2 = totalRevenue2.replaceAll("[^0-9.]", ""); 
		double RevenueValue2 = Double.parseDouble(totalRevenueValue2);	
		System.out.println("Total Revenue Value Before In Tiered Product Tab : " + RevenueValue2);
		
		String totalorders2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue2 = totalorders2.replaceAll("[^0-9.]", ""); 
		double Revenueorders2 = Double.parseDouble(totalordersValue2);	
		System.out.println("Total orders Before In Tiered Product Tab : " + Revenueorders2);	
		
		String totaluser2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusers2 = totaluser2.replaceAll("[^0-9.]", ""); 
		double Revenueusers2 = Double.parseDouble(totalordersusers2);	
		System.out.println("Total Users Before In Tiered Product Tab : " + Revenueusers2);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Filter By Product Filter Button']")).click(); //Click on product filter
		driver.findElement(By.cssSelector("div#product-dropdown>div:nth-of-type(2)>div>div:first-of-type")).click(); //Search with product name in filter
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click(); //Click on apply button in filter
		
		String totalRevenue5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue5 = totalRevenue5.replaceAll("[^0-9.]","");
		double RevenueValue5 = Double.parseDouble(totalRevenueValue5);
		System.out.println("Total Revenue Value Before Applying Product Filter In Tiered Product Tab : " + RevenueValue5);		
				
		String totalorders = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueorders = totalorders.replaceAll("[^0-9.]","");
		double orderValue = Double.parseDouble(totalRevenueorders);
		System.out.println("Total Order Value Before Applying Product Filter In Tiered Product Tab : " + orderValue);		
				
		String totalcustomers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordercustomers = totalcustomers.replaceAll("[^0-9.]","");
		double customersValue = Double.parseDouble(totalordercustomers);
		System.out.println("Total Customers Before Applying Product Filter In Tiered Product Tab : " + customersValue);
		
		//Check that if we have placed order for tier product then it is showing its revenue correct in all sales and tier product tab of the sales module
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name1);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
		WebElement uploadImage1 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage1);

		Thread.sleep(3000);
		String[] files11 = {Media_Path + Fileone};
		String allFiles11 = String.join("\n", files11);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles11);

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
		driver.findElement(By.cssSelector("#tier-item-2>div>div>div>button")).click();//click on price
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		driver.findElement(By.xpath("(//input[@name='productDetails.variants.1.prices[0].amount'])[1]")).sendKeys(susbcriptionPrice);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[normalize-space()='Description + Bullet points'])[2]")).click();//click on description and bullet points to save tier details
		driver.findElement(By.xpath("//textarea[@name=\"productDetails.variants.1.product_tier_description\"]")).sendKeys(DiscriptionTier2);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		driver.findElement(By.xpath("//input[@name=\"productDetails.variants.1.product_tier_features.0.title\"]")).sendKeys(TitleTier2);
		driver.findElements(By.cssSelector("div.key-features>div>div>div>button")).get(1).click();//click on feature one
		driver.findElement(By.xpath("//input[contains(@name,\"productDetails.variants.1.product_tier_features.0.feature_title.0\")]")).sendKeys(Tier2Feature1);

		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));

		//check that we can purchase tier product
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name1);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		Thread.sleep(3000);
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

		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(parentWindow);	

		//Check that if we have selected tier product from the filter and then if we select all sales or tier product tab then it is showing correct data (Like Order count , customer , repeat count , page view , conversion rate )
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String totalRevenueaftersus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueaftersusc = totalRevenueaftersus.replaceAll("[^0-9.]", ""); 
		double RevenueValueaftersusc = Double.parseDouble(totalRevenueaftersusc);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + RevenueValueaftersusc);

		double expectedTotalRevenueaf = RevenueValue1 + Double.parseDouble(Value);
		Assert.assertEquals(RevenueValueaftersusc,expectedTotalRevenueaf,"Total Revenue value mismatch after calculation.");

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Tiered Product']")).click();//Click on one time toggle button
		Thread.sleep(3000);

		String totalRevenue211 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue211 = totalRevenue211.replaceAll("[^0-9.]", ""); 
		double RevenueValue211 = Double.parseDouble(totalRevenueValue211);	
		System.out.println("Total Revenue Value After In Tiered Product Tab : " + RevenueValue211);

		double expectedTotalRevenue11 = RevenueValue2 + Double.parseDouble(Value);
		Assert.assertEquals(RevenueValue211, expectedTotalRevenue11, "Total revenue is not updated in Tiered Product tab after placing subscription order");

		String totalorders211 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue211 = totalorders211.replaceAll("[^0-9.]", ""); 
		double Revenueorders211 = Double.parseDouble(totalordersValue211);	
		System.out.println("Total orders After In Tiered Product Tab : " + Revenueorders211);	

		String totaluser211 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusers211 = totaluser211.replaceAll("[^0-9.]", ""); 
		double Revenueusers211 = Double.parseDouble(totalordersusers211);	
		System.out.println("Total Users After In Tiered Product Tab : " + Revenueusers211);

		Assert.assertEquals(Revenueorders211, Revenueorders2 + 1, "Order count is not incremented in Tiered Product tab");
		Assert.assertEquals(Revenueusers211, Revenueusers2 + 1, "Customer count is not incremented in Tiered Product tab");

		//Check that if we have selected only subscription product from drop-down and then if we select all sales or subscription tab then only it should show all the stats 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Filter By Product Filter Button']")).click(); //Click on product filter
		driver.findElement(By.cssSelector("div#product-dropdown>div:nth-of-type(2)>div>div:first-of-type")).click(); //Search with product name in filter
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click(); //Click on apply button in filter

		String totalRevenue511 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue511 = totalRevenue511.replaceAll("[^0-9.]","");
		double RevenueValue511 = Double.parseDouble(totalRevenueValue511);
		System.out.println("Total Revenue Value Before Applying Product Filter In Tiered Product Tab : " + RevenueValue511);		

		String totalorders11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueorders11 = totalorders11.replaceAll("[^0-9.]","");
		double orderValue11 = Double.parseDouble(totalRevenueorders11);
		System.out.println("Total Order Value Before Applying Product Filter In Tiered Product Tab : " + orderValue11);		

		String totalcustomers11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordercustomers11 = totalcustomers11.replaceAll("[^0-9.]","");
		double customersValue11 = Double.parseDouble(totalordercustomers11);
		System.out.println("Total Customers Before Applying Product Filter In Tiered Product Tab : " + customersValue11);	

		Assert.assertEquals((int) RevenueValue511, Double.parseDouble(Value),0.01, "Total revenue is not updated correctly Before applying product filter");
		Assert.assertEquals((int) orderValue11, 1, "Total Order Values is not updated correctly After applying product filter");
		Assert.assertEquals((int) customersValue11, 1, "Total Customers not updated correctly After applying product filter");	
	}
	
	@Test(priority = 13)
	public void Invoice_SalesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		//Check that when we place the order from invocie then its revenue is getting update in invocie and all sales tab 
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String totalRevenue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue1 = totalRevenue1.replaceAll("[^0-9.]", ""); 
		double RevenueValue1 = Double.parseDouble(totalRevenueValue1);	
		System.out.println("Total Revenue Value Before In All Sales Tab : " + RevenueValue1);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Invoices Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenue2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue2 = totalRevenue2.replaceAll("[^0-9.]", ""); 
		double RevenueValue2 = Double.parseDouble(totalRevenueValue2);	
		System.out.println("Total Revenue Value Before In Invoice Tab : " + RevenueValue2);
		
		String totalorders2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue2 = totalorders2.replaceAll("[^0-9.]", ""); 
		double Revenueorders2 = Double.parseDouble(totalordersValue2);	
		System.out.println("Total orders Before In Invoice Tab : " + Revenueorders2);	
		
		String totaluser2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusers2 = totaluser2.replaceAll("[^0-9.]", ""); 
		double Revenueusers2 = Double.parseDouble(totalordersusers2);	
		System.out.println("Total Users Before In Invoice Tab : " + Revenueusers2);

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
		
		driver.findElement(By.xpath("//button[@role='checkbox']")).click(); //Clicking on checkbox		
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
        Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
        
        //Check that if we have selected invocie product then it is showing its data correctly in invocie and all sales tab 
        driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String totalRevenue11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue11 = totalRevenue11.replaceAll("[^0-9.]", ""); 
		double RevenueValue11 = Double.parseDouble(totalRevenueValue11);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + RevenueValue11);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Invoices Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenue21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue21 = totalRevenue21.replaceAll("[^0-9.]", ""); 
		double RevenueValue21 = Double.parseDouble(totalRevenueValue21);	
		System.out.println("Total Revenue Value After In Invoice Tab : " + RevenueValue21);
		
		String totalorders21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue21 = totalorders21.replaceAll("[^0-9.]", ""); 
		double Revenueorders21 = Double.parseDouble(totalordersValue21);	
		System.out.println("Total orders After In Invoice Tab : " + Revenueorders21);	
		
		String totaluser21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusers21 = totaluser21.replaceAll("[^0-9.]", ""); 
		double Revenueusers21 = Double.parseDouble(totalordersusers21);	
		System.out.println("Total Users After In Invoice Tab : " + Revenueusers21);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Filter By Product Filter Button']")).click(); //Click on product filter
		driver.findElement(By.cssSelector("div#product-dropdown>div:nth-of-type(2)>div>div:first-of-type")).click(); //Search with product name in filter
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click(); //Click on apply button in filter
		
		String totalRevenue5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue5 = totalRevenue5.replaceAll("[^0-9.]","");
		double RevenueValue5 = Double.parseDouble(totalRevenueValue5);
		System.out.println("Total Revenue Value After Applying Product Filter In Invoice Tab : " + RevenueValue5);		
				
		String totalorders = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueorders = totalorders.replaceAll("[^0-9.]","");
		double orderValue = Double.parseDouble(totalRevenueorders);
		System.out.println("Total Order Value After Applying Product Filter In Invoice Tab : " + orderValue);		
				
		String totalcustomers = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordercustomers = totalcustomers.replaceAll("[^0-9.]","");
		double customersValue = Double.parseDouble(totalordercustomers);
		System.out.println("Total Customers After Applying Product Filter In Invoice Tab : " + customersValue);
		
		Assert.assertEquals((int) RevenueValue5, Double.parseDouble(Value), 0.01, "Total revenue is not updated correctly After applying product filter");
		Assert.assertEquals((int) orderValue, 1, "Total Order Values is not	updated correctly After applying product filter");
		Assert.assertEquals((int) customersValue, 1, "Total Customers not updated correctly	After applying product filter");
		
		//3D-Check Invoices  order in recent order popup
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div>section>div:nth-of-type(3)>div:nth-of-type(2)>button")).click();//Click on recent order popup eye button
		Thread.sleep(2000);
		String recentorderdisplayed = driver.findElement(By.cssSelector("div[aria-label='Recent Orders Drawer'] table tbody tr:first-child td a div div a")).getText().trim();
		System.out.println("Recent order displayed in recent order popup: " + recentorderdisplayed);
		Assert.assertEquals(recentorderdisplayed, email, "Placed order is not displayed in recent order popup");		
	}
	
	@Test(priority = 14)
	public void Cources_SalesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		int Value = 1;
		String Product_Name1 = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();
		String Product_Name2 = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();
		
		//Check that when we place the order from courses then the revenue is getting update in courses and all sales tab 
		//Check that if we purchase subscription product from courses then its revenue is getting update in all sales, subscription, and courses tab
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String totalRevenue1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue1 = totalRevenue1.replaceAll("[^0-9.]", ""); 
		double RevenueValue1 = Double.parseDouble(totalRevenueValue1);	
		System.out.println("Total Revenue Value Before In All Sales Tab : " + RevenueValue1);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Courses Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenuecource = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueVcource = totalRevenuecource.replaceAll("[^0-9.]", ""); 
		double RevenueValuecource = Double.parseDouble(totalRevenueVcource);	
		System.out.println("Total Revenue Value Before In Courses Tab : " + RevenueValuecource);
		
		String totalorderscource = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderscources = totalorderscource.replaceAll("[^0-9.]", ""); 
		double Revenueorderscource = Double.parseDouble(totalorderscources);	
		System.out.println("Total orders Before In Courses Tab : " + Revenueorderscource);	
		
		String totalusercources = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordercources = totalusercources.replaceAll("[^0-9.]", ""); 
		double Revenueordercources = Double.parseDouble(totalordercources);	
		System.out.println("Total Users Before In Courses Tab : " + Revenueordercources);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Subscriptions Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenuesuscr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenuesusc = totalRevenuesuscr.replaceAll("[^0-9.]", ""); 
		double Revenuesuscr = Double.parseDouble(totalRevenuesusc);	
		System.out.println("Total Revenue Value Before In Subscriptions Tab : " + Revenuesuscr);
		
		String totalorderssuscr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderssusc = totalorderssuscr.replaceAll("[^0-9.]", ""); 
		double Revenueorderssusctab = Double.parseDouble(totalorderssusc);	
		System.out.println("Total orders Before In Subscriptions Tab : " + Revenueorderssusctab);	
		
		String totalusersusctab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusc = totalusersusctab.replaceAll("[^0-9.]", ""); 
		double Revenueuserssusctab = Double.parseDouble(totalordersusc);	
		System.out.println("Total Users Before In Subscriptions Tab : " + Revenueuserssusctab);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='One-Time Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenueonetime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValonetime = totalRevenueonetime.replaceAll("[^0-9.]", ""); 
		double RevenueValueonetime = Double.parseDouble(totalRevenueValonetime);	
		System.out.println("Total Revenue Value In One-Time Tab : " + RevenueValueonetime);
		
		String totalordersonetime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersonetime1 = totalordersonetime.replaceAll("[^0-9.]", ""); 
		double Revenueordersonetime = Double.parseDouble(totalordersonetime1);	
		System.out.println("Total orders In One-Time Tab : " + Revenueordersonetime);	
		
		String totalusersonetime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersonetimes = totalusersonetime.replaceAll("[^0-9.]", ""); 
		double Revenueusersonetime = Double.parseDouble(totalordersonetimes);	
		System.out.println("Total Users In One-Time Tab : " + Revenueusersonetime);
		System.out.println();
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name2);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");
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
		
		WebElement unlockCourseButton = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButton);
		Thread.sleep(500);
		jse.executeScript("arguments[0].click();", unlockCourseButton);

		WebElement unlockservicesButton = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockservicesButton);
		Thread.sleep(500);
		jse.executeScript("arguments[0].click();", unlockservicesButton);

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

		String prodname = driver.findElement(By.cssSelector("div.connect-product-listing>div:first-of-type>div>span")).getText();
		driver.findElement(By.name("searchproductsDD")).sendKeys(prodname);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.connect-product-listing>div:first-of-type")).click();
		driver.findElement(By.cssSelector("button.connect-productbtn")).click();

		Thread.sleep(2000);
		WebElement openEditProduct = driver.findElement(By.xpath("(//a[normalize-space()='Open/Edit Product'])[1]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", openEditProduct);
		openEditProduct.click();

		String parentWindow = driver.getWindowHandle();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}

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
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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
		
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//verify that if we place the order from cpurse then it is showing all the stats correctly (order count, customer, repeate count, page view, conversion rate)
		driver.navigate().to(Sales);
		Thread.sleep(7000);

		String totalRevenue11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue11 = totalRevenue11.replaceAll("[^0-9.]", ""); 
		double RevenueValue11 = Double.parseDouble(totalRevenueValue11);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + RevenueValue11);
		double expectedTotalRevenueaf = RevenueValue1 + Double.parseDouble(susbcriptionPrice);
		Assert.assertEquals(RevenueValue11, expectedTotalRevenueaf, "Total revenue is not updated in Courses tab after placing course order");
		System.out.println();

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Courses Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);

		String totalRevenue21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue21 = totalRevenue21.replaceAll("[^0-9.]", ""); 
		double RevenueValue21 = Double.parseDouble(totalRevenueValue21);	
		System.out.println("Total Revenue Value After In Courses Tab : " + RevenueValue21);

		String totalorders21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue21 = totalorders21.replaceAll("[^0-9.]", ""); 
		double Revenueorders21 = Double.parseDouble(totalordersValue21);	
		System.out.println("Total orders After In Courses Tab : " + Revenueorders21);	

		String totaluser21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusers21 = totaluser21.replaceAll("[^0-9.]", ""); 
		double Revenueusers21 = Double.parseDouble(totalordersusers21);	
		System.out.println("Total Users After In Courses Tab : " + Revenueusers21);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Subscriptions Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenuesuscr1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenuesusc1 = totalRevenuesuscr1.replaceAll("[^0-9.]", ""); 
		double Revenuesuscr1 = Double.parseDouble(totalRevenuesusc1);	
		System.out.println("Total Revenue Value Before In Subscriptions Tab : " + Revenuesuscr1);
		
		String totalorderssuscr1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderssusc1 = totalorderssuscr1.replaceAll("[^0-9.]", ""); 
		double Revenueorderssusctab1 = Double.parseDouble(totalorderssusc1);	
		System.out.println("Total orders Before In Subscriptions Tab : " + Revenueorderssusctab1);	
		
		String totalusersusctab1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusc1 = totalusersusctab1.replaceAll("[^0-9.]", ""); 
		double Revenueuserssusctab1 = Double.parseDouble(totalordersusc1);	
		System.out.println("Total Users Before In Subscriptions Tab : " + Revenueuserssusctab1);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='One-Time Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenueonetime1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValonetime1 = totalRevenueonetime1.replaceAll("[^0-9.]", ""); 
		double RevenueValueonetime1 = Double.parseDouble(totalRevenueValonetime1);	
		System.out.println("Total Revenue Value In One-Time Tab : " + RevenueValueonetime1);
		
		String totalordersonetime11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersonetime111 = totalordersonetime11.replaceAll("[^0-9.]", ""); 
		double Revenueordersonetime1 = Double.parseDouble(totalordersonetime111);	
		System.out.println("Total orders In One-Time Tab : " + Revenueordersonetime1);	
		
		String totalusersonetime1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersonetimes1 = totalusersonetime1.replaceAll("[^0-9.]", ""); 
		double Revenueusersonetime1 = Double.parseDouble(totalordersonetimes1);	
		System.out.println("Total Users In One-Time Tab : " + Revenueusersonetime1);
		System.out.println();
		
		Assert.assertEquals(RevenueValue21, RevenueValuecource + Double.parseDouble(susbcriptionPrice), "Total revenue is not updated in Courses tab after placing course order");
		Assert.assertEquals(Revenueorders21, Revenueorderscource + 1, "Order count is not incremented in Courses tab");
		Assert.assertEquals(Revenueusers21, Revenueordercources + 1, "Customer count is not incremented in Courses tab");
		
//		Assert.assertEquals(RevenueValueonetime1, RevenueValueonetime + Double.parseDouble(susbcriptionPrice), "Total revenue is not updated in Subscriptions tab after placing course order");
//		Assert.assertEquals(Revenueordersonetime1, Revenueordersonetime + 1, "Order count is not incremented in Subscriptions tab");
//		Assert.assertEquals(Revenueusersonetime1, Revenueusersonetime +	1, "Customer count is not incremented in Subscriptions tab");	
		
		//Check that if we purchase one time product from couese then its revenue is getting update in all sales, one time and courses tab 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name1);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage1 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage1);
		
		Thread.sleep(3000);
		String[] files11 = {Media_Path + Fileone};
		String allFiles11 = String.join("\n", files11);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles11);

		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("1");
		Thread.sleep(1000);
		
		WebElement unlockCourseButton1 = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButton1);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockCourseButton1);
		
		WebElement unlockservicesButton1 = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockservicesButton1);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockservicesButton1);

		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name1);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		
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
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String totalRevenue3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue3 = totalRevenue3.replaceAll("[^0-9.]", ""); 
		double RevenueValue3 = Double.parseDouble(totalRevenueValue3);	
		System.out.println("Total Revenue Value After In Sales Tab : " + RevenueValue3);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='One-Time Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenue4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue4 = totalRevenue4.replaceAll("[^0-9.]", ""); 
		double RevenueValue4 = Double.parseDouble(totalRevenueValue4);	
		System.out.println("Total Revenue Value After In One-Time Tab : " + RevenueValue4);
		System.out.println();
		
		double expectedTotalRevenue = RevenueValue11 + Value;
		Assert.assertEquals(RevenueValue3,expectedTotalRevenue,"Total revenue is not updated correctly after adding price In all sales tab");
		
		double expectedTotalRevenue1 = RevenueValueonetime1 + Value;
		Assert.assertEquals(RevenueValue4,expectedTotalRevenue1,"Total revenue is not updated correctly after adding price In one time tab");
		
		String totalRevenuesuscr11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenuesusc11 = totalRevenuesuscr11.replaceAll("[^0-9.]", ""); 
		double Revenuesuscr11 = Double.parseDouble(totalRevenuesusc11);	
		System.out.println("Total Revenue Value Before In One-Time Tab : " + Revenuesuscr11);
		
		String totalorderssuscr11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderssusc11 = totalorderssuscr11.replaceAll("[^0-9.]", ""); 
		double Revenueorderssusctab11 = Double.parseDouble(totalorderssusc11);	
		System.out.println("Total orders Before In One-Time Tab : " + Revenueorderssusctab11);	
		
		String totalusersusctab11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusc11 = totalusersusctab11.replaceAll("[^0-9.]", ""); 
		double Revenueuserssusctab11 = Double.parseDouble(totalordersusc11);	
		System.out.println("Total Users Before In One-Time Tab : " + Revenueuserssusctab11);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Courses Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);

		String totalRevenue211 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValue211 = totalRevenue211.replaceAll("[^0-9.]", ""); 
		double RevenueValue211 = Double.parseDouble(totalRevenueValue211);	
		System.out.println("Total Revenue Value After In Courses Tab : " + RevenueValue211);

		String totalorders211 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersValue211 = totalorders211.replaceAll("[^0-9.]", ""); 
		double Revenueorders211 = Double.parseDouble(totalordersValue211);	
		System.out.println("Total orders After In Courses Tab : " + Revenueorders211);	

		String totaluser211 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusers211 = totaluser211.replaceAll("[^0-9.]", ""); 
		double Revenueusers211 = Double.parseDouble(totalordersusers211);	
		System.out.println("Total Users After In Courses Tab : " + Revenueusers211);	
		System.out.println();
		
		Assert.assertEquals(Revenueorders211, Revenueorderscource + 2, "Order count is not incremented in Courses tab");
		Assert.assertEquals(Revenueusers211, Revenueordercources + 1, "Customer count is not incremented in Courses tab");
		
		Assert.assertEquals(Revenueorderssusctab11, Revenueordersonetime1 + 1, "Order count is not incremented in One-Time tab");
		Assert.assertEquals(Revenueuserssusctab11, Revenueusersonetime1 + 1, "Customer count is not incremented in One-Time tab");	
		
		//3D-Check Courses order in recent order popup
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div>section>div:nth-of-type(3)>div:nth-of-type(2)>button")).click();//Click on recent order popup eye button
		Thread.sleep(2000);
		String recentorderdisplayed = driver.findElement(By.cssSelector("div[aria-label='Recent Orders Drawer'] table tbody tr:first-child td a div div a")).getText().trim();
		System.out.println("Recent order displayed in recent order popup: " + recentorderdisplayed);
		Assert.assertEquals(recentorderdisplayed, email, "Placed order is not displayed in recent order popup");
		
		//Check that when we click on order id from recent order tab then it is navigating to order module
		driver.findElement(By.cssSelector("div[aria-label='Recent Orders Drawer'] table tbody tr:first-child td a")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("orders") && driver.getCurrentUrl().toLowerCase().contains(PlacedOrderID11.toLowerCase()),"URL does not contain 'orders' or Order ID. Current URL");
	}
	
	@Test(priority = 15)
	public void VirtualTerminal_salesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		//Check that when we place the order from VT card option the its revenue is getting update correctly in all sales and VT tab correclty 
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String beforerevsalestab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String revbeforesalestab = beforerevsalestab.replaceAll("[^0-9.]", ""); 
		double beforerevvalsalestab = Double.parseDouble(revbeforesalestab);	
		System.out.println("Total Revenue Value Before In All Sales Tab by Cash : " + beforerevvalsalestab);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Virtual Terminal Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div[role='menu']:nth-of-type(1)>div:nth-of-type(3)")).click(); //Select cash payment method
		
		String totalRevenuebeforevttab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenuebreforevt = totalRevenuebeforevttab.replaceAll("[^0-9.]", ""); 
		double RevenueValuebeforevt = Double.parseDouble(totalRevenuebreforevt);	
		System.out.println("Total Revenue Value Before In Virtual Terminal Tab by Cash : " + RevenueValuebeforevt);
		
		String totalorderbeforevt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderbeforevttab = totalorderbeforevt.replaceAll("[^0-9.]", ""); 
		double Revenueorderbeforevt = Double.parseDouble(totalorderbeforevttab);	
		System.out.println("Total orders Before In Virtual Terminal Tab by Cash : " + Revenueorderbeforevt);	
		
		String totaluserbeforevt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalusersbeforevt = totaluserbeforevt.replaceAll("[^0-9.]", ""); 
		double Revenueusersbeforevt = Double.parseDouble(totalusersbeforevt);	
		System.out.println("Total Users Before In Virtual Terminal Tab by Cash : " + Revenueusersbeforevt);
		System.out.println();
			
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
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath("//input[@name='lineItems.0.unitPrice']")).getAttribute("value").replace("$", "").trim());
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Payment Type']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class,'custom-class-for-aaply-the-css')]//div[contains(@class,'flex flex-1 items-center') and .//div[contains(@id,'placeholder')]]")).click(); //Clicking on payment type dropdown
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='react-select-2-listbox']//div[@role='option'][1]")).click(); //Select payment type cash
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space(.//p/text())='Record Cash Sale']")).click(); //Clicking on record cash sale button
		Thread.sleep(15000);
		String orderId = driver.findElement(By.xpath("//div[h2[normalize-space(text())='Transaction Summary']]/p")).getText().trim().replace("#", ""); //Getting order id
		System.out.println("Order ID: " + orderId);
		driver.get("https://store.app.deposyt.com/a/orders?tab=all-orders");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[normalize-space(text())='Virtual Terminal']")).click(); //Clicking on virtual terminal filter
		Thread.sleep(2000);
		String orderIdInVirtualTerminal = driver.findElement(By.xpath("//tbody[@id='order-table-body']/tr[1]/td[2]/span")).getText().trim().replace("#", "");
		System.out.println("Order ID in Virtual Terminal: " + orderIdInVirtualTerminal);
		Assert.assertEquals(orderId, orderIdInVirtualTerminal, "Order ID in virtual terminal does not match the recorded order ID.");	
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String afterrevsalestab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String revaftersalestab = afterrevsalestab.replaceAll("[^0-9.]", ""); 
		double afterrevvalsalestab = Double.parseDouble(revaftersalestab);	
		System.out.println("Total Revenue Value After In All Sales Tab by Cash : " + afterrevvalsalestab);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Virtual Terminal Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div[role='menu']:nth-of-type(1)>div:nth-of-type(3)")).click(); //Select cash payment method
		
		String totalRevenueaftervttab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenuaftervt = totalRevenueaftervttab.replaceAll("[^0-9.]", ""); 
		double RevenueValueaftervt = Double.parseDouble(totalRevenuaftervt);	
		System.out.println("Total Revenue Value After In Virtual Terminal Tab by Cash : " + RevenueValueaftervt);
		
		String totalorderaftervt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderaftervttab = totalorderaftervt.replaceAll("[^0-9.]", ""); 
		double Revenueorderaftervt = Double.parseDouble(totalorderaftervttab);	
		System.out.println("Total orders After In Virtual Terminal Tab by Cash : " + Revenueorderaftervt);	
		
		String totaluseraftervt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalusersaftervt = totaluseraftervt.replaceAll("[^0-9.]", ""); 
		double Revenueusersaftervt = Double.parseDouble(totalusersaftervt);	
		System.out.println("Total Users After In Virtual Terminal Tab by Cash : " + Revenueusersaftervt);
		System.out.println();
		
		double expectedRevenueaftervt = RevenueValuebeforevt + unitPrice;
		Assert.assertEquals(RevenueValueaftervt, expectedRevenueaftervt, "Total revenue is not updated correctly after placing order from VT in VT tab");
		 
		double expectedRevenueaftersalestab = beforerevvalsalestab + unitPrice;
		Assert.assertEquals(afterrevvalsalestab, expectedRevenueaftersalestab, "Total revenue is not updated correctly after placing order from VT in all sales tab");
		 
		Assert.assertEquals(Revenueorderaftervt, Revenueorderbeforevt + 1, "Total orders is not updated correctly after placing order from VT in VT tab");
		Assert.assertEquals(Revenueusersaftervt, Revenueusersbeforevt, "Total users is not updated correctly after placing order from VT in VT tab");
		
		//Check that when we place the order from VT card option the its revenue is getting update correctly in all sales and VT tab correclty 
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String beforerevsalestabcard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String revbeforesalestabcard = beforerevsalestabcard.replaceAll("[^0-9.]", ""); 
		double beforerevvalsalestabcard = Double.parseDouble(revbeforesalestabcard);	
		System.out.println("Total Revenue Value Before In All Sales Tab by Card : " + beforerevvalsalestabcard);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Virtual Terminal Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div[role='menu']:nth-of-type(1)>div:nth-of-type(2)")).click(); //Select card payment method
		
		String totalRevenuebeforevttabcard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenuebreforevtcard = totalRevenuebeforevttabcard.replaceAll("[^0-9.]", ""); 
		double RevenueValuebeforevtcard = Double.parseDouble(totalRevenuebreforevtcard);	
		System.out.println("Total Revenue Value Before In Virtual Terminal Tab by Card : " + RevenueValuebeforevtcard);
		
		String totalorderbeforevtcard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderbeforevttabcard = totalorderbeforevtcard.replaceAll("[^0-9.]", ""); 
		double Revenueorderbeforevtcard = Double.parseDouble(totalorderbeforevttabcard);	
		System.out.println("Total orders Before In Virtual Terminal Tab by Card : " + Revenueorderbeforevtcard);	
		
		String totaluserbeforevtcard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalusersbeforevtcard = totaluserbeforevtcard.replaceAll("[^0-9.]", ""); 
		double Revenueusersbeforevtcard = Double.parseDouble(totalusersbeforevtcard);	
		System.out.println("Total Users Before In Virtual Terminal Tab by Card : " + Revenueusersbeforevtcard);
		System.out.println();
		
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[normalize-space(.)='Credit Card']")).click(); //Select card payment method
		Thread.sleep(1000);
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
		double unitPrice1 = Double.parseDouble(driver.findElement(By.xpath("//input[@name='lineItems.0.unitPrice']")).getAttribute("value").replace("$", "").trim());
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Credit Card Details']")));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);// MMYY format
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();
		WebElement cardHolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Name On Card\"]")));
		cardHolder.clear();
		cardHolder.sendKeys(F_Name + " " + L_Name);
		Thread.sleep(2000);
		
		driver.findElement(By.name("address.address_1")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder=\"Address 2\"]")).sendKeys(Street_Add);
		
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Clicking on charge button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='sms']")).click(); //Clicking on pay by sms button
		String parentWindow = driver.getWindowHandle();

		jse.executeScript("window.open();");
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}

		driver.navigate().to(Messages);
		driver.findElement(By.name("filters")).sendKeys(ContactPhone2,Keys.ENTER);
		Thread.sleep(4000);
		WebElement lastSms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")));
		String smsText = lastSms.getText().trim();

		String otp = null;
		Pattern pattern = Pattern.compile("code\\s(\\d{4})");
		Matcher matcher = pattern.matcher(smsText);

		if (matcher.find()) {
		    otp = matcher.group(1);
		}
		System.out.println("Extracted OTP: " + otp);
		
		driver.close();
		driver.switchTo().window(parentWindow);
		
		Thread.sleep(2000);
		WebElement otpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Confirm Verification Code']/ancestor::div[contains(@class,'bg-white')]")));
		List<WebElement> otpInputs = otpModal.findElements(By.cssSelector("#main-page-ui-div>main>div>div>div>div:nth-of-type(3)>input"));
		if (otpInputs.size() != 4) {
		    throw new RuntimeException("Expected 4 OTP inputs, found " + otpInputs.size());
		}

		for (int i = 0; i < otp.length(); i++) {
		    otpInputs.get(i).sendKeys(String.valueOf(otp.charAt(i)));
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Verify And Place Order']"))).click();

		Thread.sleep(15000);
		String orderId1 = driver.findElement(By.xpath("//div[h2[normalize-space(text())='Transaction Summary']]/p")).getText().trim().replace("#", ""); //Getting order id
		System.out.println("Order ID: " + orderId1);
		driver.get("https://store.app.deposyt.com/a/orders?tab=all-orders");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[normalize-space(text())='Virtual Terminal']")).click(); //Clicking on virtual terminal filter
		Thread.sleep(2000);
		String orderIdInVirtualTerminal1 = driver.findElement(By.xpath("//tbody[@id='order-table-body']/tr[1]/td[2]/span")).getText().trim().replace("#", "");
		System.out.println("Order ID in Virtual Terminal: " + orderIdInVirtualTerminal1);
		Assert.assertEquals(orderId1, orderIdInVirtualTerminal1, "Order ID in virtual terminal does not match the recorded order ID.");
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String afterrevsalestab1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String revaftersalestab1 = afterrevsalestab1.replaceAll("[^0-9.]", ""); 
		double afterrevvalsalestab1 = Double.parseDouble(revaftersalestab1);	
		System.out.println("Total Revenue Value After In All Sales Tab by Cash : " + afterrevvalsalestab1);
		System.out.println();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Virtual Terminal Toggle Button']")).click(); //Click on one time toggle button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div[role='menu']:nth-of-type(1)>div:nth-of-type(2)")).click(); //Select cash payment method
		
		String totalRevenueaftervttabcard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenuaftervtcard = totalRevenueaftervttabcard.replaceAll("[^0-9.]", ""); 
		double RevenueValueaftervtcard = Double.parseDouble(totalRevenuaftervtcard);	
		System.out.println("Total Revenue Value After In Virtual Terminal Tab by Cash : " + RevenueValueaftervtcard);
		
		String totalorderaftervtcard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderaftervttabcard = totalorderaftervtcard.replaceAll("[^0-9.]", ""); 
		double Revenueorderaftervtcard = Double.parseDouble(totalorderaftervttabcard);	
		System.out.println("Total orders After In Virtual Terminal Tab by Cash : " + Revenueorderaftervtcard);	
		
		String totaluseraftervtcard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalusersaftervtcard = totaluseraftervtcard.replaceAll("[^0-9.]", ""); 
		double Revenueusersaftervtcard = Double.parseDouble(totalusersaftervtcard);	
		System.out.println("Total Users After In Virtual Terminal Tab by Cash : " + Revenueusersaftervtcard);
		System.out.println();
		
		double expectedRevenueaftervt1 = RevenueValuebeforevtcard + unitPrice1;
		Assert.assertEquals(RevenueValueaftervtcard, expectedRevenueaftervt1, "Total revenue is not updated correctly after placing order from VT in VT tab");
		 
		double expectedRevenueaftersalestab1 = beforerevvalsalestabcard + unitPrice1;
		Assert.assertEquals(afterrevvalsalestab1, expectedRevenueaftersalestab1, "Total revenue is not updated correctly after placing order from VT in all sales tab");
		 
		Assert.assertEquals(Revenueorderaftervtcard, Revenueorderbeforevtcard + 1, "Total orders is not updated correctly after placing order from VT in VT tab");
		Assert.assertEquals(Revenueusersaftervtcard, Revenueusersbeforevtcard, "Total users is not updated correctly after placing order from VT in VT tab");
	}
	
	@Test(priority = 16)
	public void PremadeGift_SalesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		String giftCardName = "GiftCard " + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
		
		int GiftCradValue1 = 10;
		int GiftCradValue2 = 9;
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String beforerevsalestab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String revbeforesales = beforerevsalestab.replaceAll("[^0-9.]", ""); 
		double beforerevvalsaletab = Double.parseDouble(revbeforesales);	
		System.out.println("Total Revenue Value Before In All Sales Tab : " + beforerevvalsaletab);
		System.out.println();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@aria-label='Courses Toggle Button'])[2]")).click(); 
		
		String totalRevenuebeforegifttab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenuebeforegifttabs = totalRevenuebeforegifttab.replaceAll("[^0-9.]", ""); 
		double RevenueValuebeforegifttab = Double.parseDouble(totalRevenuebeforegifttabs);	
		System.out.println("Total Revenue Value Before In Premade Gift Cards Tab : " + RevenueValuebeforegifttab);
		
		String totalorderbeforegifttab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderbeforegifttabs = totalorderbeforegifttab.replaceAll("[^0-9.]", ""); 
		double Revenueorderbeforegifttab = Double.parseDouble(totalorderbeforegifttabs);	
		System.out.println("Total orders Before In Premade Gift Cards Tab : " + Revenueorderbeforegifttab);	
		
		String totaluserbeforegifttab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalusersbeforegifttab = totaluserbeforegifttab.replaceAll("[^0-9.]", ""); 
		double Revenueusersbeforegifttab = Double.parseDouble(totalusersbeforegifttab);	
		System.out.println("Total Users Before In Premade Gift Cards Tab : " + Revenueusersbeforegifttab);
		System.out.println();
		
		//Verify that when we place the order for premade gift card then its revnue is getting update in all sales , one time and premade tab
		driver.navigate().to(Gift_Cards);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Premade Gift Cards'])[1]"))).click();//Click on new gift card button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.gap-y-xsmall>div>div>div>div>button:first-of-type")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'giftcard-container')]/descendant::input[@name=\"title\"]"))).sendKeys(giftCardName);
		
		driver.findElement(By.xpath("(//textarea[@placeholder='Premade Gift Card Description'])[1]")).sendKeys("911 Gift card Discription"); //Input gift card value
		driver.findElement(By.xpath("(//input[@placeholder='0.00'])[1]")).sendKeys(String.valueOf(GiftCradValue1)); //Input gift card value
		driver.findElement(By.xpath("(//input[contains(@placeholder,'0.00')])[2]")).sendKeys(String.valueOf(GiftCradValue2)); //Click on create gift card button
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
		
		Thread.sleep(5000);
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
		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);
		
		driver.findElement(By.xpath("(//button[@role='checkbox'])[2]")).click(); //Clicking on checkbox				
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String afterrevsalestab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String revaftersales = afterrevsalestab.replaceAll("[^0-9.]", ""); 
		double afterrevvalsaletab = Double.parseDouble(revaftersales);	
		System.out.println("Total Revenue Value After In All Sales Tab : " + afterrevvalsaletab);
		System.out.println();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@aria-label='Courses Toggle Button'])[2]")).click(); 
		
		String totalRevenueaftergifttab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueaftergifttabs = totalRevenueaftergifttab.replaceAll("[^0-9.]", ""); 
		double RevenueValueaftergifttab = Double.parseDouble(totalRevenueaftergifttabs);	
		System.out.println("Total Revenue Value After In Premade Gift Cards Tab : " + RevenueValueaftergifttab);
		
		String totalorderaftergifttab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderaftergifttabs = totalorderaftergifttab.replaceAll("[^0-9.]", ""); 
		double Revenueorderaftergifttab = Double.parseDouble(totalorderaftergifttabs);	
		System.out.println("Total orders After In Premade Gift Cards Tab : " + Revenueorderaftergifttab);	
		
		String totaluseraftergifttab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalusersaftergifttab = totaluseraftergifttab.replaceAll("[^0-9.]", ""); 
		double Revenueusersaftergifttab = Double.parseDouble(totalusersaftergifttab);	
		System.out.println("Total Users After In Premade Gift Cards Tab : " + Revenueusersaftergifttab);
		System.out.println();
		
		double expectedRevenueaftergifttab = RevenueValuebeforegifttab + GiftCradValue2; 
		Assert.assertEquals(RevenueValueaftergifttab, expectedRevenueaftergifttab, "Total revenue is not updated correctly after placing order for premade gift card tab");
		
		double expectedRevenueaftersalestab = beforerevvalsaletab + GiftCradValue2; 
		Assert.assertEquals(afterrevvalsaletab, expectedRevenueaftersalestab, "Total revenue is not updated correctly after placing order for premade gift card in all sales tab");
		
		Assert.assertEquals(Revenueorderaftergifttab, Revenueorderbeforegifttab + 1, "Total orders is not updated correctly after placing order for premade gift card tab");
		Assert.assertEquals(Revenueusersaftergifttab, Revenueusersbeforegifttab + 1, "Total users is not updated correctly after placing order for premade gift card tab");		
	}
	
	@Test(priority = 17)
	public void SubscriptionAnalytics_SalesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;	
		
		String Product_Name = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		driver.findElement(By.cssSelector("div.sales-list-section>div>div:first-of-type")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@aria-label='Subscription Analytics']")).click(); 
		
		String totalPausedSuscbefore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Paused Subs. Statistics Card']>div>div>p"))).getText().trim();
		String totalPausedSuscbefor = totalPausedSuscbefore.replaceAll("[^0-9.]", ""); 
		double pausedsuscbefore = Double.parseDouble(totalPausedSuscbefor);	
		System.out.println("Total paused Suscriptions Before In Subscription Analytics Tab : " + pausedsuscbefore);
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");
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
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		
		Thread.sleep(5000);
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
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Check that when we pause the subscription then its count in showing pause tab 
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Subscriptions Toggle Button']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table table tbody tr td:nth-of-type(2)"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'radix')]"))).click();
		
		String parentWindow = driver.getWindowHandle();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Open Customer Hub')]"))).click();

		for (String windowHandle : driver.getWindowHandles()) {
		    if (!windowHandle.equals(parentWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[normalize-space()='Details'])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Pause Subscription\"]"))).click(); 
		Thread.sleep(3000);
		
		driver.close();
		driver.switchTo().window(parentWindow);
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		driver.findElement(By.cssSelector("div.sales-list-section>div>div:first-of-type")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@aria-label='Subscription Analytics']")).click(); 
		
		String totalPausedSuscafter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Paused Subs. Statistics Card']>div>div>p"))).getText().trim();
		String totalPausedSuscaftere = totalPausedSuscafter.replaceAll("[^0-9.]", ""); 
		double pausedsuscafter = Double.parseDouble(totalPausedSuscaftere);	
		System.out.println("Total paused Suscriptions After In Subscription Analytics Tab : " + pausedsuscafter);
		System.out.println();
		
		double expectedPausedSusc = pausedsuscbefore + 1;
		Assert.assertEquals(pausedsuscafter, expectedPausedSusc, "Total paused subscription is not updated correctly after pausing the subscription");
		
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Subscriptions Toggle Button']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table table tbody tr td:nth-of-type(2)"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'radix')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Open Customer Hub')]"))).click();

		for (String windowHandle : driver.getWindowHandles()) {
		    if (!windowHandle.equals(parentWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[normalize-space()='Details'])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()=\"Resume Subscription\"]"))).click(); 
		Thread.sleep(3000);
		
		driver.close();
		driver.switchTo().window(parentWindow);
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		driver.findElement(By.cssSelector("div.sales-list-section>div>div:first-of-type")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@aria-label='Subscription Analytics']")).click(); 
		
		String totalPausedSuscafter1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Paused Subs. Statistics Card']>div>div>p"))).getText().trim();
		String totalPausedSuscaftere1 = totalPausedSuscafter1.replaceAll("[^0-9.]", ""); 
		double pausedsuscafter1 = Double.parseDouble(totalPausedSuscaftere1);	
		System.out.println("Total Suscriptions After Resumed In Subscription Analytics Tab : " + pausedsuscafter1);
		
		double expectedPausedSusc1 = pausedsuscafter - 1;
		Assert.assertEquals(pausedsuscafter1, expectedPausedSusc1, "Total paused subscription is not updated correctly after resuming the subscription");
		
		//Check that if we have saved subscription with extra discount then it is showing discount amount in saved by discount tab 
		String totaldiscountbefore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Total Discount Statistics Card']>div>div>p"))).getText().trim();
		String totalbeforediscount = totaldiscountbefore.replaceAll("[^0-9.]", ""); 
		double discountbefore = Double.parseDouble(totalbeforediscount);	
		System.out.println("Before Discount In Subscription Analytics Tab : " + discountbefore);
		
		String totalsavedsuscbefore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Saved Subs. Statistics Card']>div>div>p"))).getText().trim();
		String totalbeforesavedsusc = totalsavedsuscbefore.replaceAll("[^0-9.]", ""); 
		double savedsuscbefore = Double.parseDouble(totalbeforesavedsusc);	
		System.out.println("Before Saved Subscriptions In Subscription Analytics Tab : " + savedsuscbefore);
		System.out.println();
			
		driver.navigate().to(Orders);
		Thread.sleep(7000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Subscriptions Toggle Button']"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table table tbody tr td:nth-of-type(2)"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'radix')]"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Open Customer Hub')]"))).click();

		for (String windowHandle : driver.getWindowHandles()) {
		    if (!windowHandle.equals(parentWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[normalize-space()='Details'])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.manage-sub-wrapper>div:nth-of-type(2)>div>div:first-of-type"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()=\"Cancel Subscription\"]"))).click();
		driver.findElement(By.xpath("//button[normalize-space()=\"Accept\"]")).click();
		Thread.sleep(3000);
		
		driver.close();
		driver.switchTo().window(parentWindow);
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		driver.findElement(By.cssSelector("div.sales-list-section>div>div:first-of-type")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@aria-label='Subscription Analytics']")).click(); 
		
		String totaldiscountafter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Total Discount Statistics Card']>div>div>p"))).getText().trim();
		String totalafterdiscount = totaldiscountafter.replaceAll("[^0-9.]", ""); 
		double discountafter = Double.parseDouble(totalafterdiscount);	
		System.out.println("After Discount In Subscription Analytics Tab : " + discountafter);
		
		double expecteddiscount = discountbefore + 0.08;
		Assert.assertEquals(discountafter, expecteddiscount, "Total Discount is not updated correctly after Accept the subscription");
		
		//Verify that when we save subscription while canceling then its count is showing in saved tab of the subscription analytics tab 
		String totalsavedsuscafter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Saved Subs. Statistics Card']>div>div>p"))).getText().trim();
		String totalaftersavedsusc = totalsavedsuscafter.replaceAll("[^0-9.]", ""); 
		double savedsuscafter = Double.parseDouble(totalaftersavedsusc);	
		System.out.println("Before Saved Subscriptions In Subscription Analytics Tab : " + savedsuscafter);
		
		double expectedsavedsusc = savedsuscbefore + 1;
		Assert.assertEquals(savedsuscafter, expectedsavedsusc, "Total saved	subscription is not updated correctly after saving the subscription while canceling the subscription");
		
		String totalcancelledbefore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Canceled Statistics Card']>div>div>p"))).getText().trim();
		String totalcancelbefore = totalcancelledbefore.replaceAll("[^0-9.]", ""); 
		double cancelledbefore = Double.parseDouble(totalcancelbefore);	
		System.out.println("Before Cancelled Subscriptions In Subscription Analytics Tab : " + cancelledbefore);
		System.out.println();
		
		//Check that when we cancel the subscription then it is showing 1 count on cancel tab in subscription analytics 
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Subscriptions Toggle Button']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table table tbody tr td:nth-of-type(2)"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'radix')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Open Customer Hub')]"))).click();

		for (String windowHandle : driver.getWindowHandles()) {
		    if (!windowHandle.equals(parentWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[normalize-space()='Details'])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.manage-sub-wrapper>div:nth-of-type(2)>div>div:first-of-type"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()=\"Cancel Subscription\"]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class=\"truncate\"]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("(//div[contains(text(),' Pricing & Affordability')])[1]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[normalize-space()=\"Select a reason\"]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[normalize-space()=\"Too expensive\"]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[normalize-space()=\"Next\"]")).click();	
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[normalize-space()=\"Confirm\"]")).click();
		Thread.sleep(3000);
		
		driver.close();
		driver.switchTo().window(parentWindow);
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		driver.findElement(By.cssSelector("div.sales-list-section>div>div:first-of-type")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@aria-label='Subscription Analytics']")).click(); 
		
		String totalcancelledafter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Canceled Statistics Card']>div>div>p"))).getText().trim();
		String totalcancelafter = totalcancelledafter.replaceAll("[^0-9.]", ""); 
		double cancelledafter = Double.parseDouble(totalcancelafter);	
		System.out.println("After Cancelled Subscriptions In Subscription Analytics Tab : " + cancelledafter);
		
		double expectedcancelled = cancelledbefore + 1;
		Assert.assertEquals(cancelledafter, expectedcancelled, "Total cancelled subscription is not updated	correctly after canceling the subscription");	
		
		//Check that the stats like revenue , page view , conversion rate is showing correctly on top product section 
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("(//h3[normalize-space()='Top Products'])[2]")));
		Thread.sleep(5000);
		String Productname = driver.findElement(By.cssSelector("section[aria-label='Top Products Table'] table tbody tr th p:first-child")).getText().trim();
		String Sales = driver.findElement(By.cssSelector("section[aria-label='Top Products Table'] table tbody tr:first-child td:nth-child(4)")).getText().trim();
		String Ravenue = driver.findElement(By.cssSelector("section[aria-label='Top Products Table'] table tbody tr:first-child td:nth-child(5)")).getText().trim();
		String Views = driver.findElement(By.cssSelector("section[aria-label='Top Products Table'] table tbody tr:first-child td:nth-child(6)")).getText().trim();
		String Conv_Rate = driver.findElement(By.cssSelector("section[aria-label='Top Products Table'] table tbody tr:first-child td:nth-child(7)")).getText().trim();
		
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("(//span[normalize-space()='Sales Reports'])[2]")));
		Thread.sleep(2000);
				
		driver.findElement(By.cssSelector("button[aria-label='Filter By Product Filter Button']")).click(); //Click on product filter
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Search Products\")]")).sendKeys(Productname); //Search with product name in filter
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div#product-dropdown>div:nth-of-type(2)>div>div:first-of-type")).click(); //Select the product in filter
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click(); //Click on apply button in filter
		
		String totalsales = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalsale = totalsales.replaceAll("[^0-9.]", ""); 
		double saleamount = Double.parseDouble(totalsale);	
		System.out.println("Total Sales In Top Product Section : " + saleamount);
		int saleamountInt = (int) saleamount;
		
		String totalrevenue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalrevenu = totalrevenue.replaceAll("[^0-9.]", ""); 
		double Revenue = Double.parseDouble(totalrevenu);	
		System.out.println("Total Revenue In Top Product Section : " + Revenue);
		String expectedFirstDigit = Ravenue.replaceAll("[^0-9]", "").substring(0, 1);
		String actualFirstDigit   = String.valueOf(Revenue).replaceAll("[^0-9]", "").substring(0, 1);
		
		String totalviews = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Page Views Statistics Card']>div>div>p"))).getText().trim();
		String totalview = totalviews.replaceAll("[^0-9.]", ""); 
		double PageViews = Double.parseDouble(totalview);	
		System.out.println("Total Views In Top Product Section : " + PageViews);
		int PageViewsInt = (int) PageViews;
		
		String totalConv_Rate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Conv. Rate Statistics Card']>div>div>p"))).getText().trim();
		String atotalConv_Rate = totalConv_Rate.replaceAll("[^0-9.]", ""); 
		double Conv_RateTotal = Double.parseDouble(atotalConv_Rate);	
		System.out.println("Total Conv Rate In Top Product Section : " + Conv_RateTotal);	
		String Conv_Rate_UI = Conv_Rate.replace("%", "").trim();
		String Conv_Rate_Card = String.valueOf(Conv_RateTotal);

		Assert.assertEquals(String.valueOf(saleamountInt),Sales,"Total Sales is not matching in top product section after applying product filter");
		Assert.assertEquals(String.valueOf(PageViewsInt),Views,"Total Views is not matching in top product section after applying product filter");
		Assert.assertEquals(Conv_Rate_Card,Conv_Rate_UI,"Total conversion rate is not matching in top product section after applying product filter");		
		Assert.assertEquals(actualFirstDigit,expectedFirstDigit,"Total revenue first digit is not matching in top product section after applying product filter");
	}
	
	@Test(priority = 18)
	public void Services_SalesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;			
		Date cDate = new Date();
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		
		//Verify that when we place the one time order from services then its revenue is getting update in all sales, one time and services tab 
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		
		String totalrevbeforesales = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalbeforerevsales = totalrevbeforesales.replaceAll("[^0-9.]", ""); 
		double revbeforesales = Double.parseDouble(totalbeforerevsales);	
		System.out.println("Total Revenue Before In All Sales Tab : " + revbeforesales);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@aria-label='Services Toggle Button']")).click(); 		
		String totalrevbefore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalbeforerev = totalrevbefore.replaceAll("[^0-9.]", ""); 
		double revbefore = Double.parseDouble(totalbeforerev);	
		System.out.println("Total Revenue Before In Services Tab : " + revbefore);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@aria-label='One-Time Toggle Button']")).click(); 		
		String totalrevbeforeonetime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalbeforerevonetime = totalrevbeforeonetime.replaceAll("[^0-9.]", ""); 
		double revbeforeonetime = Double.parseDouble(totalbeforerevonetime);	
		System.out.println("Total Revenue Before In One Time Tab : " + revbeforeonetime);
		System.out.println();
		
		driver.navigate().to(Services);
		Thread.sleep(3000);
		driver.findElement(By.xpath(" (//div[@class='mr-4 fb-setting-icon-wrapper'])[2]")).click(); //Click on service setting
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
		Thread.sleep(2000);
		String ServicePrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.serviceinfocardprice.serviceinfochild2"))).getAttribute("data-original-title");
		String ServicePriceValue = ServicePrice.replaceAll("[^0-9.]", "");
		double serviceAmount = Double.parseDouble(ServicePriceValue);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='professional-content']//h2[contains(text(),'"+servicename+"')]//ancestor::a"))).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.nextchoosenbtnmaindiv a[data-action=\"datetime\"]")).click();
		Thread.sleep(3000);

		//Booking Free service
		driver.findElement(By.cssSelector("div[id='"+cSimpleDateFormat.format(cDate.getTime())+"']+div")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.booking_timeslots>p.timeslot")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a.closemaintippopuplater")).click();
		driver.findElement(By.cssSelector("a.floatingbtnfornextaction")).click();
		Thread.sleep(5000);
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
		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);
		
		driver.findElement(By.xpath("//button[@role='checkbox']")).click(); //Clicking on checkbox
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		Thread.sleep(5000);
		String PlacedOrderID1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.order_details_info_orderID"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID1);
		driver.close();		
		driver.switchTo().window(Pwindow);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String totalrevenueaftersales = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalafterevsales = totalrevenueaftersales.replaceAll("[^0-9.]", ""); 
		double afterrevsales = Double.parseDouble(totalafterevsales);	
		System.out.println("Total Revenue After In All Sales Tab : " + afterrevsales);
		double expectedRevenue = revbeforesales + serviceAmount;
		Assert.assertEquals(afterrevsales,expectedRevenue, 0.01,"Revenue after sale does not match expected value");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@aria-label='One-Time Toggle Button']")).click(); 		
		String totalrevafteronetime = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalaftervonetime = totalrevafteronetime.replaceAll("[^0-9.]", ""); 
		double revafteronetime = Double.parseDouble(totalaftervonetime);	
		System.out.println("Total Revenue After In One Time Tab : " + revafteronetime);
		double expectedRevenue11 = revbeforeonetime + serviceAmount;
		Assert.assertEquals(revafteronetime,expectedRevenue11, 0.01,"Revenue after sale does not match expected value");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@aria-label='Services Toggle Button']")).click(); 	
		String totalrevenueafter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalafterev= totalrevenueafter.replaceAll("[^0-9.]", ""); 
		double afterrev = Double.parseDouble(totalafterev);	
		System.out.println("Total Revenue After In Services Tab : " + afterrev);
		System.out.println();
		double expectedRevenue1 = revbefore + serviceAmount;
		Assert.assertEquals(afterrev,expectedRevenue1, 0.01,"Revenue after sale does not match expected value");
			
		//Verify that when we place the subscription  order from services then its revenue is getting update in all sales, subscription  and services tab 
		//verify that if we place the order from services  then it is showing all the stats correctly (order count, customer, repeate count, page view, conversion rate)
		String totalorderbefore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderbefor = totalorderbefore.replaceAll("[^0-9.]", ""); 
		double Revenueorderbefore = Double.parseDouble(totalorderbefor);	
		System.out.println("Total orders Before In Services Tab : " + Revenueorderbefore);	
		
		String totaluserbefore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalusersbefore = totaluserbefore.replaceAll("[^0-9.]", ""); 
		double Revenueusersbefore = Double.parseDouble(totalusersbefore);	
		System.out.println("Total Users Before In Services Tab : " + Revenueusersbefore);
		System.out.println();
		
		driver.navigate().to(Services);
		Thread.sleep(3000);
		driver.findElement(By.xpath(" (//div[@class='mr-4 fb-setting-icon-wrapper'])[2]")).click(); //Click on service setting
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.assign-payment-type-dropdownsetting")).click(); //Click on assign payment type dropdown
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("li[data-paymenttype='paidbooking']")).click(); //Select cash payment type
		
		//Check the checkout process in the services
		driver.findElement(By.cssSelector("a.bookingwidgetborder")).click();
		driver.findElement(By.cssSelector("a[target=\"_blank\"]")).click();//preview button

		for (String handle:driver.getWindowHandles()){
			driver.switchTo().window(handle);
		}
		
		driver.findElement(By.id("searchservicefield")).sendKeys("Suscription Product");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".allservicesviewdiv .masterservicediv:not([style])>a"))).click();
		driver.findElement(By.cssSelector("div.nextchoosenbtnmaindiv a[data-action=\"datetime\"]")).click();
		Thread.sleep(3000);

		//Booking Free service
		driver.findElement(By.cssSelector("div[id='"+cSimpleDateFormat.format(cDate.getTime())+"']+div")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.booking_timeslots>p.timeslot")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a.closemaintippopuplater")).click();
		driver.findElement(By.cssSelector("a.floatingbtnfornextaction")).click();
		Thread.sleep(5000);
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
		WebElement cardHolder11 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder11.clear();
		cardHolder11.sendKeys(F_Name + " " + L_Name);
		
		driver.findElement(By.xpath("//button[@role='checkbox']")).click(); //Clicking on checkbox
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		Thread.sleep(5000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.order_details_info_orderID"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();		
		driver.switchTo().window(Pwindow);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		driver.findElement(By.xpath("//button[@aria-label='Services Toggle Button']")).click(); 
		
		String totalorderafter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalorderafters = totalorderafter.replaceAll("[^0-9.]", ""); 
		double Revenueorderafter = Double.parseDouble(totalorderafters);	
		System.out.println("Total orders After In Services Tab : " + Revenueorderafter);	
		
		String totaluserafter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New Cust. Statistics Card']>div>div>p"))).getText().trim();
		String totalusersafter = totaluserafter.replaceAll("[^0-9.]", ""); 
		double Revenueusersafter = Double.parseDouble(totalusersafter);	
		System.out.println("Total Users After In Services Tab : " + Revenueusersafter);
		
		double expectedRevenueorder = Revenueorderbefore + 1;	
		Assert.assertEquals(Revenueorderafter,expectedRevenueorder, "Total order count is not updated correctly after placing the order from services");
		Assert.assertEquals(Revenueusersafter,Revenueusersbefore, "Total user count is not updated correctly after placing the order from services");
		
		//3D-Check services order in recent order popup
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div>section>div:nth-of-type(3)>div:nth-of-type(2)>button")).click();//Click on recent order popup eye button
		Thread.sleep(2000);
		String recentorderdisplayed = driver.findElement(By.cssSelector("div[aria-label='Recent Orders Drawer'] table tbody tr:first-child td a div div a")).getText().trim();
		System.out.println("Recent order displayed in recent order popup: " + recentorderdisplayed);
		Assert.assertEquals(recentorderdisplayed, email, "Placed order is not displayed in recent order popup");
		
		//Check that when we click on order id from recent order tab then it is navigating to order module
		driver.findElement(By.cssSelector("div[aria-label='Recent Orders Drawer'] table tbody tr:first-child td a")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("orders") && driver.getCurrentUrl().toLowerCase().contains(PlacedOrderID11.toLowerCase()),"URL does not contain 'orders' or Order ID. Current URL");
	}
	
	@Test(priority = 19)
	public void Create_Discount() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
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
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='+ Add Discount']"))).click(); // click on new discount button
		driver.findElement(By.cssSelector("button#fixed")).click();
		driver.findElement(By.cssSelector("div[data-orientation=\"vertical\"]:nth-of-type(2)>h3>div:first-of-type")).click();
		
		driver.findElement(By.cssSelector("#regions>div")).click(); //click on region drop down
		driver.findElement(By.id("react-select-2-option-1")).click(); //Select Default as region
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
		
		Thread.sleep(5000);		
		driver.findElement(By.id("couponCode1")).sendKeys(coupon); //Input gift card code in cart
		driver.findElement(By.id("applyDiscountCoupon")).click(); //Click on apply button
		
		Thread.sleep(2000);
		String discountapplied = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#appliedDiscountCoupon"))).getText().trim();
		System.out.println("Discount applied : " + discountapplied);
		Assert.assertTrue(discountapplied.contains(coupon), "Discount code is not applied successfully");
		driver.switchTo().window(originalTab);
		
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
		Assert.assertEquals(discountapplied1,"This discount code cannot be used in the selected region!","Wrong error message displayed for region restriction");
		
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
		driver.findElement(By.xpath("//button[normalize-space(.)='Credit Card']")).click(); //Select card payment method
		Thread.sleep(1000);
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
		System.out.println("Discount is applied successfully in VT");
	}
	
	@Test(priority = 20)
	public void Create_GiftCard() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Actions actions = new Actions(driver);
		
		String GiftCard = "10";
		String giftCardName = "GiftCard " + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
		Product_Name = "Tier Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		DiscriptionTier1 = 	"Description for first tier installment", DiscriptionTier2 = "Description for Second tier installment", TitleTier1 = "First Tier Installment",
		TitleTier2 = "Second Tier Installment", Tier1Feature1 = "Tier one Feature First", Tier2Feature1 = "Tier two Feature one";
		
		//Check Normal giftcard is created 
		driver.navigate().to(Gift_Cards);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='New Gift Card'])[3]"))).click(); // click on New Gift Card button
		driver.findElement(By.xpath("(//div[@id='region.region_id'])[1]")).click(); //click on region drop down
		driver.findElement(By.id("react-select-2-option-1")).click(); //Select Default as region
		driver.findElement(By.xpath("(//input[@placeholder='-'])[1]")).sendKeys(GiftCard);
		driver.findElement(By.name("receiver.email")).sendKeys(WMLogin);
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //click on create gift card button
		Thread.sleep(5000);
		String GiftCardID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.customer-table-second-class>table>tbody>tr>td>p:first-of-type"))).getText().trim();
		System.out.println("Created Gift Card ID: " + GiftCardID);
		
		//Use Giftcard for One time product Checkout
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};
		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);

		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("100");
		Thread.sleep(1000);

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		
		Thread.sleep(5000);
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
		
		driver.findElement(By.id("Giftcardcode")).sendKeys(GiftCardID); //Input discount code in cart
		driver.findElement(By.id("applyGiftCard")).click(); //Click on apply button for discount code
		Thread.sleep(2000);
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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
		
		//Check if giftcard is used then it is getting disabled
		driver.navigate().to(Gift_Cards);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr>td:first-of-type>p:first-of-type")).click();
		Thread.sleep(2000);
		String GiftCardStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Disable']//parent::p//parent::div/parent::span/parent::button"))).getText().trim();
		Assert.assertEquals(GiftCardStatus, "Disable", "Gift card status is not updated to used after using the gift card in checkout");
		
		//Check if giftcard is expired then it is showing error on checkout page
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(5000);	
		driver.findElement(By.id("Giftcardcode")).sendKeys(GiftCardID); //Input discount code in cart
		driver.findElement(By.id("applyGiftCard")).click(); //Click on apply button for discount code
		
		String discountapplied1 = driver.findElement(By.cssSelector("div.animate-enter>div:nth-of-type(2)>span:last-of-type")).getText().trim();
		System.out.println("Discount applied on another region checkout : " + discountapplied1);
		Assert.assertEquals(discountapplied1, "Giftcard code is not valid", "Wrong error message displayed for region restriction");
		
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Check Premade giftcard is created 
		//Check Premade Giftcard is getting purchased
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
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}		
		
		Thread.sleep(5000);
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
		WebElement cardHolder11 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder11.clear();
		cardHolder11.sendKeys(F_Name + " " + L_Name);
		
		driver.findElement(By.xpath("(//button[@role='checkbox'])[2]")).click(); //Clicking on checkbox				
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		Thread.sleep(7000);
		String PlacedOrderID111 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID111);
		driver.close();
		driver.switchTo().window(originalTab);
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='Overview Toggle Button']")).click();
		String GiftCardID1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.customer-table-second-class>table>tbody>tr>td>p:first-of-type"))).getText().trim();
		System.out.println("Created Gift Card ID: " + GiftCardID1);
		
		//Use Giftcard for Tier product Checkout
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
		WebElement uploadImage1 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage1);
		
		Thread.sleep(3000);
		String[] files11 = {Media_Path + Fileone};

		String allFiles11 = String.join("\n", files11);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles11);
		
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
		driver.findElement(By.cssSelector("#tier-item-2>div>div>div>button")).click();//click on price
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		driver.findElement(By.xpath("(//input[@name='productDetails.variants.1.prices[0].amount'])[1]")).sendKeys(susbcriptionPrice);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[normalize-space()='Description + Bullet points'])[2]")).click();//click on description and bullet points to save tier details
		driver.findElement(By.xpath("//textarea[@name=\"productDetails.variants.1.product_tier_description\"]")).sendKeys(DiscriptionTier2);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		driver.findElement(By.xpath("//input[@name=\"productDetails.variants.1.product_tier_features.0.title\"]")).sendKeys(TitleTier2);
		driver.findElements(By.cssSelector("div.key-features>div>div>div>button")).get(1).click();//click on feature one
		driver.findElement(By.xpath("//input[contains(@name,\"productDetails.variants.1.product_tier_features.0.feature_title.0\")]")).sendKeys(Tier2Feature1);
		
		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		//check that we can purchase tier product
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		Thread.sleep(3000);
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
		
		driver.findElement(By.id("Giftcardcode")).sendKeys(GiftCardID1); //Input discount code in cart
		driver.findElement(By.id("applyGiftCard")).click(); //Click on apply button for discount code
		Thread.sleep(2000);
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrders = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID : " + PlacedOrders);
	}
	
	@Test(priority = 21)
	public void Pricing() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		String Product_Name1 = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		Product_Name2 = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),Price1 = "5", Price2 = "25";
		
		try {
			driver.navigate().to(dual_Pricing);
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//input[@name='fee_option'])[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();
		} catch (Exception e) {
			 System.out.println("None pricing already enabled");
		}
		
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name2);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);

		// Entering One Time Purchase Product price
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("25");
		Thread.sleep(1000);

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name1);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
		WebElement uploadImage1 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage1);
		
		Thread.sleep(3000);
		String[] files11 = {Media_Path + Fileone};

		String allFiles11 = String.join("\n", files11);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles11);
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.xpath("//div[@id='one-time-purchase-div']/descendant::button[@role=\"switch\"][1]")).click(); //One time purchase toggle button
		Thread.sleep(1000);
		WebElement subscriptionToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",subscriptionToggle);
		subscriptionToggle.click(); //Subscription toggle button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys("75"); //Subscription price field
	    driver.findElement(By.cssSelector("div.frequency-select-container")).click(); //Frequency drop down
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[2]")).click(); //Select frequency as monthly
		Thread.sleep(1000);

		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		driver.navigate().to(Pricing);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//p[contains(text(),'Add Price List')])[2]")).click(); 
		
		driver.findElement(By.cssSelector("div[data-orientation=\"vertical\"]:nth-of-type(2)>h3>div:first-of-type")).click();
		driver.findElement(By.name("name")).sendKeys("New Sale Price List " + UUID.randomUUID().toString().substring(0, 5).toUpperCase(),Keys.TAB);
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys("Discription for New Sale Prise List");
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[data-orientation=\"vertical\"]>div:nth-of-type(4)>div>h3:first-of-type")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Add Products Manually\"]//parent::button")).click();
		
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>div:first-of-type")).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:first-of-type>div:first-of-type")).click();
		
		driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();
		
		driver.findElements(By.cssSelector("div[data-orientation=\"vertical\"] > div:nth-of-type(4) button")).get(0).click(); 
		driver.findElement(By.xpath("((//div[contains(@class,'w-10')])[1]//button)[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),\"Edit prices\")]")).click(); //Select option as sale price
		Thread.sleep(2000);
		
		WebElement SalePrice1 = driver.findElement(By.xpath("//input[@placeholder=\"0.00\"]"));
		SalePrice1.click();
		SalePrice1.sendKeys(Keys.CONTROL, "a");
		SalePrice1.sendKeys(Keys.DELETE);
		SalePrice1.sendKeys(Price2);		
		driver.findElement(By.xpath("//span[normalize-space()=\"Save And Close\"]")).click();
		
		Thread.sleep(5000);
		driver.findElements(By.cssSelector("div[data-orientation=\"vertical\"] > div:nth-of-type(4) button")).get(2).click(); 
		driver.findElement(By.xpath("(//div[contains(@class,'w-10')])[2]//button[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),\"Edit prices\")]")).click(); //Select option as sale price
		Thread.sleep(2000);
		
		WebElement SalePrice2 = driver.findElement(By.xpath("//input[@placeholder=\"0.00\"]"));
		SalePrice2.click();
		SalePrice2.sendKeys(Keys.CONTROL, "a");
		SalePrice2.sendKeys(Keys.DELETE);
		SalePrice2.sendKeys(Price1);		
		driver.findElement(By.xpath("//span[normalize-space()=\"Save And Close\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Publish Price List\"]")).click();
		
		//Check sale prices are getting applied to One time & Subscription product on all pages
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name1);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		
		Thread.sleep(5000);
		WebElement salePriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#global-product-topbar span > span:last-child")));
        String rawText = salePriceElement.getText();
        String suscPrice1 = rawText.replaceAll(".*?(\\d+\\.\\d+).*", "$1");
        int actualPrice = (int) Double.parseDouble(suscPrice1);
        int expectedPrice = Integer.parseInt(Price2);
        Assert.assertEquals(actualPrice, expectedPrice, "Sale price is not applied on product details page");
		
		String suscPrice2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>p>span:last-of-type"))).getText().replaceAll("[^0-9.]", "");
		System.out.println("price on preview page: " + suscPrice2);
		Assert.assertEquals(suscPrice2, Price2, "Sale price is not applied on product preview page");
		
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
		
		String suscPurchasePrice = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div:nth-of-type(2)>div>span")).getText()
		.replaceAll("[^0-9.]", "").replaceAll("\\.00$", "");   
		Assert.assertEquals(suscPurchasePrice,Price2,"Sale price is not applied");
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name2);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		
		Thread.sleep(5000);	
		WebElement salePriceElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#global-product-topbar span > span:last-child")));
        String rawText1 = salePriceElement1.getText();
        String suscPrice11 = rawText1.replaceAll(".*?(\\d+\\.\\d+).*", "$1");
        int actualPrice1 = (int) Double.parseDouble(suscPrice11);
        int expectedPrice1 = Integer.parseInt(Price1);
        Assert.assertEquals(actualPrice1, expectedPrice1, "Sale price is not applied on product details page");
		
		String onetimePrice2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>p>span:last-of-type"))).getText().replaceAll("[^0-9.]", "");
		System.out.println("price on preview page: " + onetimePrice2);
		Assert.assertEquals(onetimePrice2, Price1, "Sale price is not applied on product preview page");
		
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();//Click on order page created by product
		
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
		
		String OnetimePrice = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div:nth-of-type(2)>div>span")).getText()
		.replaceAll("[^0-9.]", "").replaceAll("\\.00$", ""); 
		Assert.assertEquals(OnetimePrice,Price1,"Sale price is not applied");
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID111 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID111);
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name1);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();		

		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>a")).click();
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#AccountColumn>div>div:first-of-type")).click();
		By deleteBtnLocator = By.cssSelector("#checkout-template>nav>section>div>div>div>div>div:last-of-type>div>div>div>div>div:nth-of-type(3)>div>button:first-of-type");
		
		List<WebElement> deleteButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(deleteBtnLocator));
        int totalItems = deleteButtons.size();
        for (int i = totalItems; i > 1; i--) {
            List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(deleteBtnLocator));
            buttons.get(0).click();
            wait.withTimeout(Duration.ofSeconds(2)).until(ExpectedConditions.numberOfElementsToBeLessThan(deleteBtnLocator, i));
        }
        
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#checkout-template>nav>section>div>div>div>div>div:last-of-type>div>button")).click();
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//div[text()='Add to Cart']//parent::button")).click();
        driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name2);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();		

		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>a")).click();
		Thread.sleep(2000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		/*susc prise not updates as per pricing on checkout page*/
		Thread.sleep(5000);
        driver.findElement(By.xpath("//div[text()='Add to Cart']//parent::button")).click(); 
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//button[text()='Proceed to Checkout'])[2]")).click();
        Thread.sleep(5000);
        String suscPricee = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(3)>div:nth-of-type(2)>div>span")).getText().replaceAll("[^0-9.]", "")   .replaceAll("\\.00$", "");  
        System.out.println("Subscription price on Add to cart page: " + suscPricee);
        //Assert.assertEquals(suscPricee, Price2, "Sale price is not applied");
		
        String onetimePrice11 = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(2)>div:nth-of-type(2)>div>span")).getText().replaceAll("[^0-9.]", "")  .replaceAll("\\.00$", "");  
        System.out.println("One time purchase price on checkout page: " + onetimePrice11);
        Assert.assertEquals(onetimePrice11, Price1, "Sale price is not applied");
		
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
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolder11 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder11.clear();
		cardHolder11.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Create Override price list for One time & Subscription product
		driver.navigate().to(Pricing);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[contains(text(),'Add Price List')])[2]"))).click(); 
		Thread.sleep(2000);
		driver.findElement(By.id("override")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[data-orientation=\"vertical\"]:nth-of-type(2)>h3>div:first-of-type")).click();
		driver.findElement(By.name("name")).sendKeys("New Sale Price List " + UUID.randomUUID().toString().substring(0, 5).toUpperCase(),Keys.TAB);
		Thread.sleep(2000);
		driver.switchTo().activeElement().sendKeys("Discription for New Sale Prise List");
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[data-orientation=\"vertical\"]>div:nth-of-type(4)>div>h3:first-of-type")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Add Products Manually\"]//parent::button")).click();
		
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>div:first-of-type")).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:first-of-type>div:first-of-type")).click();	
		driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();
		
		driver.findElements(By.cssSelector("div[data-orientation=\"vertical\"] > div:nth-of-type(4) button")).get(0).click(); 
		driver.findElement(By.xpath("((//div[contains(@class,'w-10')])[1]//button)[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),\"Edit prices\")]")).click(); //Select option as sale price
		Thread.sleep(2000);
		
		WebElement SalePrice11 = driver.findElement(By.xpath("//input[@placeholder=\"0.00\"]"));
		SalePrice11.click();
		SalePrice11.sendKeys(Keys.CONTROL, "a");
		SalePrice11.sendKeys(Keys.DELETE);
		SalePrice11.sendKeys(Price2);		
		driver.findElement(By.xpath("//span[normalize-space()=\"Save And Close\"]")).click();
		
		Thread.sleep(5000);
		driver.findElements(By.cssSelector("div[data-orientation=\"vertical\"] > div:nth-of-type(4) button")).get(2).click(); 
		driver.findElement(By.xpath("(//div[contains(@class,'w-10')])[2]//button[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),\"Edit prices\")]")).click(); //Select option as sale price
		Thread.sleep(2000);
		
		WebElement SalePrice21 = driver.findElement(By.xpath("//input[@placeholder=\"0.00\"]"));
		SalePrice21.click();
		SalePrice21.sendKeys(Keys.CONTROL, "a");
		SalePrice21.sendKeys(Keys.DELETE);
		SalePrice21.sendKeys(Price1);		
		driver.findElement(By.xpath("//span[normalize-space()=\"Save And Close\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Publish Price List\"]")).click();
		
		//Check Override prices are getting applied to One time & Subscription product on all pages
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name1);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		
		Thread.sleep(5000);
		WebElement salePriceElement11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#global-product-topbar span > span:last-child")));
        String rawText11 = salePriceElement11.getText();
        String suscPrice111 = rawText11.replaceAll(".*?(\\d+\\.\\d+).*", "$1");
        int actualPrice11 = (int) Double.parseDouble(suscPrice111);
        int expectedPrice11 = Integer.parseInt(Price2);
        Assert.assertEquals(actualPrice11, expectedPrice11, "Sale price is not applied on product details page");
		
		String suscPrice21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>p:last-of-type"))).getText().replaceAll("[^0-9.]", "");
		System.out.println("price on preview page: " + suscPrice21);
		Assert.assertEquals(suscPrice21, Price2, "Sale price is not applied on product preview page");
		
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();//Click on order page created by product
		
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
		
		String suscPurchasePrice1 = driver.findElement(By.cssSelector("div[data-testid='checkout-container']>div>main>section>div>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(2)")).getText()
		.replaceAll("[^0-9.]", "").replaceAll("\\.00$", "");   
		Assert.assertEquals(suscPurchasePrice1,Price2,"Sale price is not applied");
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolderr = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolderr.clear();
		cardHolderr.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderIDs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderIDs);
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name2);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		
		Thread.sleep(5000);	
		WebElement salePriceElement111 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#global-product-topbar span > span:last-child")));
        String rawText111 = salePriceElement111.getText();
        String suscPrice1111 = rawText111.replaceAll(".*?(\\d+\\.\\d+).*", "$1");
        int actualPrice111 = (int) Double.parseDouble(suscPrice1111);
        int expectedPrice111 = Integer.parseInt(Price1);
        Assert.assertEquals(actualPrice111, expectedPrice111, "Sale price is not applied on product details page");
		
		String onetimePrice21 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>p>span:last-of-type"))).getText().replaceAll("[^0-9.]", "");
		System.out.println("price on preview page: " + onetimePrice21);
		Assert.assertEquals(onetimePrice21, Price1, "Sale price is not applied on product preview page");
		
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();//Click on order page created by product
		
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
		
		String OnetimePrice1 = driver.findElement(By.cssSelector("div[data-testid='checkout-container']>div>main>section>div>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(2)")).getText()
		.replaceAll("[^0-9.]", "").replaceAll("\\.00$", ""); 
		Assert.assertEquals(OnetimePrice1,Price1,"Sale price is not applied");
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolder111 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder111.clear();
		cardHolder111.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderIDr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderIDr);
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name1);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();		

		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>a")).click();
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#AccountColumn>div>div:first-of-type")).click();
		By deleteBtnLocator1 = By.cssSelector("#checkout-template>nav>section>div>div>div>div>div:last-of-type>div>div>div>div>div:nth-of-type(3)>div>button:first-of-type");
		
		List<WebElement> deleteButtons1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(deleteBtnLocator1));
        int totalItems1 = deleteButtons1.size();
        for (int i = totalItems1; i > 1; i--) {
            List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(deleteBtnLocator1));
            buttons.get(0).click();
            wait.withTimeout(Duration.ofSeconds(2)).until(ExpectedConditions.numberOfElementsToBeLessThan(deleteBtnLocator1, i));
        }
        
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#checkout-template>nav>section>div>div>div>div>div:last-of-type>div>button")).click();
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//div[text()='Add to Cart']//parent::button")).click();
        driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name2);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();		

		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>a")).click();
		Thread.sleep(2000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		/*susc prise not updates as per pricing on checkout page*/
		Thread.sleep(5000);
        driver.findElement(By.xpath("//div[text()='Add to Cart']//parent::button")).click(); 
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//button[text()='Proceed to Checkout'])[2]")).click();
        Thread.sleep(5000);
        String suscPricee1 = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(2)>div:nth-of-type(2)>span")).getText().replaceAll("[^0-9.]", "")   .replaceAll("\\.00$", "");  
        System.out.println("Subscription price on Add to cart page: " + suscPricee1);
        Assert.assertEquals(suscPricee1, Price1, "Sale price is not applied");
		
        String onetimePrice111 = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(3)>div:nth-of-type(2)>span")).getText().replaceAll("[^0-9.]", "")  .replaceAll("\\.00$", "");  
        System.out.println("One time purchase price on checkout page: " + onetimePrice111);
        //Assert.assertEquals(onetimePrice111, Price2, "Sale price is not applied");
		
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
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolderr1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolderr1.clear();
		cardHolderr1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.close();
		driver.switchTo().window(originalTab);	
		
		try {
			driver.navigate().to(dual_Pricing);
			Thread.sleep(5000);
			driver.findElement(By.xpath("(//input[@name='fee_option'])[5]")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#dialog-checkbox")).click();
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]//parent::button")).click();
		} catch (Exception e) {
			System.out.println("Dual pricing is enabled");
		}
		
	}
	
	@Test(priority = 22)
	public void VT () throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		//Verify that after cash payment user will receive email for the order
		DeleteMail("Your Order is Confirmed — Order# ");		
		DeleteContact(WMLogin, ContactPhone1);
		
		//verify that user can create new customer and select the existing customer
		//verify that user can purchase product using cash payment
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[normalize-space(.)='Cash']")).click(); //Select cash payment method
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class=\"truncate font-sans\"]//parent::span//parent::button")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.id("field_first_name")).sendKeys("Ecom"); 
		driver.findElement(By.id("field_last_name")).sendKeys("Contact"); 
		driver.findElement(By.id("field_email_id")).sendKeys(WMLogin); 
		driver.findElement(By.name("phone_no")).sendKeys(ContactPhone1); 
		driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Done\"]")).click(); 
		
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]"))).click(); //Clicking on add line items button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); //Clicking on item name field
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[text()='Create New Item']]/parent::div/following-sibling::div[1]")).click(); //Clicking on first product from the dropdown
		Thread.sleep(1000);

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Payment Type']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class,'custom-class-for-aaply-the-css')]//div[contains(@class,'flex flex-1 items-center') and .//div[contains(@id,'placeholder')]]")).click(); //Clicking on payment type dropdown
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='react-select-2-listbox']//div[@role='option'][1]")).click(); //Select payment type cash
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space(.//p/text())='Record Cash Sale']")).click(); //Clicking on record cash sale button
		Thread.sleep(15000);
		String orderId = driver.findElement(By.xpath("//div[h2[normalize-space(text())='Transaction Summary']]/p")).getText().trim().replace("#", ""); //Getting order id
		System.out.println("Order ID: " + orderId);		
		
		//verify that after cash payment success page for the order will be generated
		driver.get(Orders);
		Thread.sleep(5000);
		String orderIdInVirtualTerminal = driver.findElement(By.xpath("//tbody[@id='order-table-body']/tr[1]/td[2]/span")).getText().trim().replace("#", "");
		System.out.println("Order ID in Virtual Terminal: " + orderIdInVirtualTerminal);
		Assert.assertEquals(orderId, orderIdInVirtualTerminal, "Order ID in virtual terminal does not match the recorded order ID.");		
		ReceivedMail("Your Order is Confirmed — Order# ");
		
		//Verify that after card payment user will receive email for the order
		DeleteMail("Subscription Charge Receipt for Subscription# ");
		
		//verify that user can purchase product using card payment
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[normalize-space(.)='Credit Card']")).click(); //Select card payment method
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search Customer\"]")).sendKeys(ContactPhone1); //Select first customer from the list
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,'overflow-y-auto')]/div[contains(@class,'mb-[5px]')][1]")).click(); //Select first customer from the list
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]")).click(); //Clicking on add line items button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); //Clicking on item name field
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[text()='Create New Item']]/parent::div/following-sibling::div[1]")).click(); //Clicking on first product from the dropdown
		Thread.sleep(1000);

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Credit Card Details']")));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);// MMYY format
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();
		WebElement cardHolder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Name On Card\"]")));
		cardHolder.clear();
		cardHolder.sendKeys(F_Name + " " + L_Name);
		Thread.sleep(2000);
		
		driver.findElement(By.name("address.address_1")).sendKeys(Street_Add);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder=\"Address 2\"]")).sendKeys(Street_Add);
		
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Clicking on charge button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='sms']")).click(); //Clicking on pay by sms button
		String parentWindow = driver.getWindowHandle();

		jse.executeScript("window.open();");
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}
		
		//verify that user can send 2 FA via SMS for the card payment
		driver.navigate().to(Messages);
		driver.findElement(By.name("filters")).sendKeys(ContactPhone1,Keys.ENTER);
		Thread.sleep(7000);
		WebElement lastSms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")));
		String smsText = lastSms.getText().trim();

		String otp = null;
		Pattern pattern = Pattern.compile("code\\s(\\d{4})");
		Matcher matcher = pattern.matcher(smsText);

		if (matcher.find()) {
		    otp = matcher.group(1);
		}
		System.out.println("Extracted OTP: " + otp);
		
		driver.close();
		driver.switchTo().window(parentWindow);
		
		Thread.sleep(2000);
		WebElement otpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Confirm Verification Code']/ancestor::div[contains(@class,'bg-white')]")));
		List<WebElement> otpInputs = otpModal.findElements(By.cssSelector("#main-page-ui-div>main>div>div>div>div:nth-of-type(3)>input"));
		if (otpInputs.size() != 4) {
		    throw new RuntimeException("Expected 4 OTP inputs, found " + otpInputs.size());
		}

		for (int i = 0; i < otp.length(); i++) {
		    otpInputs.get(i).sendKeys(String.valueOf(otp.charAt(i)));
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Verify And Place Order']"))).click();

		Thread.sleep(15000);
		String orderId1 = driver.findElement(By.xpath("//div[h2[normalize-space(text())='Transaction Summary']]/p")).getText().trim().replace("#", ""); //Getting order id
		System.out.println("Order ID: " + orderId1);
		
		//verify that after card payment success page for the order will be generated
		driver.get(Orders);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[normalize-space(text())='Virtual Terminal']")).click(); //Clicking on virtual terminal filter
		Thread.sleep(2000);
		String orderIdInVirtualTerminal1 = driver.findElement(By.xpath("//tbody[@id='order-table-body']/tr[1]/td[2]/span")).getText().trim().replace("#", "");
		System.out.println("Order ID in Virtual Terminal: " + orderIdInVirtualTerminal1);
		Assert.assertEquals(orderId1, orderIdInVirtualTerminal1, "Order ID in virtual terminal does not match the recorded order ID.");
		
		ReceivedMail("Subscription Charge Receipt for Subscription# ");
		
		//Verify that after ACH payment user will receive email for the order
		DeleteMail("Your Order is Confirmed — Order# ");
		
		//verify that user can purchase product using ACH payment
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(7000);
		driver.findElement(By.xpath("//span[normalize-space(.)='ACH']//parent::button")).click(); //Select card payment method
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search Customer\"]")).sendKeys(ContactPhone1); //Select first customer from the list
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,'overflow-y-auto')]/div[contains(@class,'mb-[5px]')][1]")).click(); //Select first customer from the list
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]")).click(); //Clicking on add line items button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); //Clicking on item name field
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//span[text()='Create New Item']//parent::span//parent::button")).click(); //Clicking on first product from the dropdown
		Thread.sleep(1000);
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);
		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};
		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys(Price);
		Thread.sleep(1000);

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Bank Account Details']")));
		Thread.sleep(3000);
		
		driver.findElement(By.name("creditCard.achAccountHolderName")).sendKeys(Account_Holder_Name);
		driver.findElement(By.name("creditCard.achRoutingNumber")).sendKeys(Routing_Number);
		driver.findElement(By.name("creditCard.achAccountNumber")).sendKeys(Account_Number);
		driver.findElement(By.id("creditCard.achAccType")).click();//click on mode drop down
		driver.findElement(By.id("react-select-2-option-0")).click();//select checking
		driver.findElement(By.id("creditCard.achAccountCategory")).click(); //click on account category drop down
		driver.findElement(By.id("react-select-3-option-0")).click(); //select personal
		
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.virtual-terminal-ui>form>div>div>div:nth-of-type(7)>div:nth-of-type(2)>h3>div>div>div:last-of-type")).click(); 
		driver.findElement(By.name("address.address_1")).sendKeys(Street_Add); 
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();	
		Thread.sleep(1000);
		driver.findElement(By.name("address.address_2")).sendKeys(Street_Add); 
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space(.//p/text())='ACH Payment']")).click(); //Clicking on record cash sale button
		Thread.sleep(15000);
		String orderId11 = driver.findElement(By.xpath("//div[h2[normalize-space(text())='Transaction Summary']]/p")).getText().trim().replace("#", ""); //Getting order id
		System.out.println("Order ID: " + orderId11);
		
		driver.get(Orders);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[normalize-space(text())='Virtual Terminal']")).click(); //Clicking on virtual terminal filter
		Thread.sleep(2000);
		
		//verify that after ACH payment success page for the order will be generated
		String orderIdInVirtualTerminal11 = driver.findElement(By.xpath("//tbody[@id='order-table-body']/tr[1]/td[2]/span")).getText().trim().replace("#", "");
		System.out.println("Order ID in Virtual Terminal: " + orderIdInVirtualTerminal11);
		Assert.assertEquals(orderId11, orderIdInVirtualTerminal11, "Order ID in virtual terminal does not match the recorded order ID.");	
		
		ReceivedMail("Your Order is Confirmed — Order# ");		
	}
	
	@Test(priority = 23)
	public void Settings() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		int code = 1000 + new Random().nextInt(9000); 
		
		String Product_Name = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),Payment_Mode = "PayPal", Region_Name = "Europe";
		String MailSubject = "Trigger Mail Via Automation", EmailText = "This mail is sent by trigger Test", messageText = "This SMS is sent by trigger Test";		
		String Return_Reason = "Return_Order " + code , Updated_Return_Reason = "Updated_Return_Reason " + code, Shipping_Option = "Shipping_Option " + code, Shipping_Amount = "10",
		Updated_Shipping_Option = "Updated_Shipping_Option " + code, Return_Shipping_Option = "Return_Shipping_Option " + code, Updated_Return_Shipping_Option = "Updated_Return_Shipping_Option " + code;
		
		//Check card expiray notification is sent on email 
		//Check card expiry trigger is run successfully
		
		DeleteMail("Card Expiration Alert");
		driver.navigate().to(settings);
		Thread.sleep(7000);
		//driver.findElement(By.xpath("//button[@id=\"3\"]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Card Expiry']/parent::div/parent::button"))).click(); 				
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div>main>section>div:nth-of-type(2)>div>div>div>div>div:first-of-type")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[contains(@class,\"addingactionbtn\")]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.trigger-dropdown-class>div:nth-of-type(1)>button")).click();
		WebElement Emailsend = driver.findElement(By.xpath("//input[@placeholder=\"Subject\"]"));
		Emailsend.sendKeys(MailSubject);
		Thread.sleep(1000);
		Emailsend.sendKeys(Keys.TAB,EmailText);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(@class,\"addingactionbtn\")]")).click();
		driver.findElement(By.cssSelector("div.trigger-dropdown-class>div:nth-of-type(2)>button")).click();
		driver.findElement(By.cssSelector("textarea.desciption-input-class")).sendKeys(messageText);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(@class,\"addingactionbtn\")]")).click();
		driver.findElement(By.cssSelector("div.trigger-dropdown-class>div:nth-of-type(3)>button")).click();
		driver.findElement(By.cssSelector("input#tag-input")).click();
		driver.findElement(By.xpath("(//div[@data-state='open'])[2]//div[1]")).click();
		String selectedTagElement = driver.findElement(By.xpath("(//div[@data-state='open'])[1]//div//span")).getText().trim();	
		System.out.println("Selected tag: " + selectedTagElement);
		driver.findElement(By.xpath("(//div[@data-state='open'])[1]")).click();
		action.sendKeys(Keys.PAGE_DOWN).perform();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(@class,\"addingactionbtn\")]")).click();
		driver.findElement(By.cssSelector("div.trigger-dropdown-class>div:nth-of-type(5)>button")).click();
		driver.findElement(By.cssSelector("#assign_user_dropdown>div")).click();
		WebElement assignedUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#assign_user_dropdown>div:nth-of-type(2)>div>ul>li:nth-of-type(2)>div>div>span:nth-of-type(2)")));
		String selectedUser = assignedUser.getText().trim();
		System.out.println("Selected user: " + selectedUser);
		assignedUser.click();	
		driver.findElement(By.xpath("//button[normalize-space()=\"Apply\"]")).click(); //Click on save button		
		action.sendKeys(Keys.PAGE_DOWN).perform();		
		driver.findElement(By.xpath("//span[normalize-space()=\"Done\"]")).click(); //Click on Triggers button
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);	
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};
		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");
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
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(WMLogin);  
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
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));		

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys("03/26");
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
		String PlacedOrderd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderd);
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Check offline payment method is created and order is placed using this 
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Payments']/parent::div/parent::button"))).click(); //Click on Payments button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space()=\"+ Add Another Offline Payment Method\"]")).click(); //Click on AAdd Another Offline Payment Method button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Payment Method (e.g. payPal)\")]")).sendKeys(Payment_Mode,Keys.TAB); 
		Thread.sleep(1000);
		driver.switchTo().activeElement().sendKeys("This is paypal payment method",Keys.TAB);
		Thread.sleep(1000);
		driver.switchTo().activeElement().sendKeys("paypalpayment gateway");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click(); //Click on save button	
		
		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(5000);
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
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
		Thread.sleep(2000);		
		driver.findElement(By.xpath("//input[@value=\"cash\"]")).click();//click on cash payment
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@role=\"combobox\"]")).click();//click on mode drop down
		
		boolean isPresentAndClicked = false;
		List<WebElement> paymentMethods = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#CashPaymentMethodDropdown button span")));
		for (WebElement method : paymentMethods) {
		    String methodName = method.getText().trim();
		    System.out.println("Available Payment Method: " + methodName);
		    if (methodName.equalsIgnoreCase(Payment_Mode)) {
		        method.click();
		        isPresentAndClicked = true;
		        break;
		    }
		}

		Assert.assertTrue(isPresentAndClicked,"Payment mode '" + Payment_Mode + "' is not present in the list");		
		driver.findElement(By.cssSelector("[type=\"file\"]")).sendKeys(Media_Path + Fileone);
		driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();		
		
		Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Check 2FA Setting is working when toggle is on/off 
		//Check Order is placed Successfullly using this payment gatway ( NMI,Authorize,stripe,Paya)
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Payments']/parent::div/parent::button"))).click(); //Click on Payments button
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.cssSelector("#main-page-ui-div article:first-of-type section button")).click(); 
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()=\"Reset\"]//parent::button")).click();
			driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]//parent::button")).click();
			System.out.println("Authorize.net Payment gateway reset Successfully");
			
			driver.findElement(By.cssSelector("#main-page-ui-div article:first-of-type section button")).click(); 
			driver.findElement(By.name("api_key")).sendKeys("52e7yL6S2");
			driver.findElement(By.name("transaction_key")).sendKeys("24S74zf6p3L27FcL");
			driver.findElement(By.name("client_key")).sendKeys("498jbV729jKQ9tbLjhge8WaC8pMwy3ypNU9Ue2MprZ33NZnXpxB33BPT763xMTSY");
			driver.findElement(By.cssSelector("#mode>div>div:first-of-type")).click();
			driver.findElement(By.id("react-select-2-option-0")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#main-page-ui-div article:first-of-type section button")).click(); 
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id=\"radix-:r1s:\"]")).click();
			driver.findElement(By.xpath("//span[normalize-space()=\"Set as Active\"]//parent::button")).click();
			System.out.println("Authorize.net Payment gateway configured Successfully");
		} catch (Exception e) {
			driver.findElement(By.cssSelector("#main-page-ui-div article:first-of-type section button")).click(); 
			driver.findElement(By.name("api_key")).sendKeys("52e7yL6S2");
			driver.findElement(By.name("transaction_key")).sendKeys("24S74zf6p3L27FcL");
			driver.findElement(By.name("client_key")).sendKeys("498jbV729jKQ9tbLjhge8WaC8pMwy3ypNU9Ue2MprZ33NZnXpxB33BPT763xMTSY");
			driver.findElement(By.cssSelector("#mode>div>div:first-of-type")).click();
			driver.findElement(By.id("react-select-2-option-0")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#main-page-ui-div article:first-of-type section button")).click(); 
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#main-page-ui-div article:first-of-type section button:first-of-type")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()=\"Set as Active\"]//parent::button")).click();
			System.out.println("Authorize.net Payment gateway configured Successfully");
		}
		
		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(5000);
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
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
		Thread.sleep(2000);				
		WebElement CardName = driver.findElement(By.id("cardHolderName"));
		jse.executeScript("arguments[0].scrollIntoView(true);", CardName);
		Thread.sleep(1000);
		CardName.sendKeys(F_Name+" "+L_Name);
		driver.findElement(By.id("cardNumber")).sendKeys(Card_No);
		driver.findElement(By.id("cardCVV")).sendKeys(CVV);
		driver.findElement(By.xpath("//button[contains(@aria-controls,\"monthDropdown\")]")).click();
		driver.findElement(By.cssSelector("#monthDropdown>button:nth-of-type(12)")).click();
		driver.findElement(By.xpath("//button[contains(@aria-controls,\"yearDropdown\")]")).click();
		driver.findElement(By.cssSelector("#yearDropdown>button:first-of-type")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();	
		String PlacedOrderIDs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderIDs);		
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.findElement(By.id("orderHistoryToggleID")).click(); //click on order history
		Thread.sleep(2000);
		String OrderRecived = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim().toLowerCase();
		Assert.assertEquals(OrderRecived, firstName.toLowerCase() + " " + lastName.toLowerCase() , "Customer name not matching on order summary");
		
		String Orderdate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(6)>span"))).getText().trim();
		DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
		LocalDate orderDate = LocalDate.parse(Orderdate, uiFormatter);
		LocalDate today = LocalDate.now();
		Assert.assertEquals(orderDate, today, "Order date Mismatch: Expected today's date but got " + orderDate);
				
		//NMI	
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Payments']/parent::div/parent::button"))).click(); //Click on Payments button
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(2) section button")).click(); 
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()=\"Reset\"]//parent::button")).click();
			driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]//parent::button")).click();
			System.out.println("NMI Payment gateway reset Successfully");
			
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(2) section button")).click(); 
			driver.findElement(By.name("security_key")).sendKeys("H8T2x8y3y279uD3Mtqp5884V3rg82Fb3");
			driver.findElement(By.name("public_key")).sendKeys("Cz9x45-7C7kHK-hx4GDw-5q2fX7");			
			driver.findElement(By.cssSelector("#mode>div>div:first-of-type")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id^='react-select-'][id$='-listbox'] > div:first-child"))).click();
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(2) section button")).click(); 
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id=\"radix-:r1s:\"]")).click();
			driver.findElement(By.xpath("//span[normalize-space()=\"Set as Active\"]//parent::button")).click();
			System.out.println("NMI Payment gateway configured Successfully");
		} catch (Exception e) {
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(2) section button")).click(); 
			driver.findElement(By.name("security_key")).sendKeys("H8T2x8y3y279uD3Mtqp5884V3rg82Fb3");
			driver.findElement(By.name("public_key")).sendKeys("Cz9x45-7C7kHK-hx4GDw-5q2fX7");			
			driver.findElement(By.cssSelector("#mode>div>div:first-of-type")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id^='react-select-'][id$='-listbox'] > div:first-child"))).click();
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(2) section button")).click(); 
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(2) section button:first-of-type")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()=\"Set as Active\"]//parent::button")).click();
			System.out.println("NMI Payment gateway configured Successfully");
		}
		
		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(5000);
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
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
		Thread.sleep(2000);	
		WebElement CardName1 = driver.findElement(By.id("cardHolderName"));
		jse.executeScript("arguments[0].scrollIntoView(true);", CardName1);
		Thread.sleep(1000);
		CardName1.sendKeys(F_Name+" "+L_Name);
		driver.switchTo().frame("CollectJSInlineccnumber");
		driver.findElement(By.id("ccnumber")).sendKeys(Card_No);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlinecvv");
		driver.findElement(By.id("cvv")).sendKeys(CVV);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlineccexp");
		driver.findElement(By.id("ccexp")).sendKeys("12/2044");;
		driver.switchTo().defaultContent();
		
		driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();	
		String PlacedOrderIDs1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderIDs1);		
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.findElement(By.id("orderHistoryToggleID")).click(); //click on order history
		Thread.sleep(2000);
		String OrderRecived1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim().toLowerCase();
		Assert.assertEquals(OrderRecived1, firstName.toLowerCase() + " " + lastName.toLowerCase() , "Customer name not matching on order summary");
		
		String Orderdate1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(6)>span"))).getText().trim();
		DateTimeFormatter uiFormatter1 = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
		LocalDate orderDate1 = LocalDate.parse(Orderdate1, uiFormatter1);
		LocalDate today1 = LocalDate.now();
		Assert.assertEquals(orderDate1, today1, "Order date Mismatch: Expected today's date but got " + orderDate1);
		
		//Paya
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Payments']/parent::div/parent::button"))).click(); //Click on Payments button
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(5) section button")).click(); 
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()=\"Reset\"]//parent::button")).click();
			driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]//parent::button")).click();
			System.out.println("Paya Payment gateway reset Successfully");
			
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(5) section button")).click(); 
			driver.findElement(By.name("payaTerminal")).sendKeys("2310");		
			driver.findElement(By.cssSelector("#mode>div>div:first-of-type")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id^='react-select-'][id$='-listbox'] > div:first-child"))).click();
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(2) section button")).click(); 
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id=\"radix-:r1s:\"]")).click();
			System.out.println("Paya Payment gateway configured Successfully");
		} catch (Exception e) {
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(5) section button")).click(); 
			driver.findElement(By.name("payaTerminal")).sendKeys("2310");		
			driver.findElement(By.cssSelector("#mode>div>div:first-of-type")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id^='react-select-'][id$='-listbox'] > div:first-child"))).click();
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(5) section button")).click(); 
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(5) section button:first-of-type")).click();
			System.out.println("Paya Payment gateway configured Successfully");
		}
		
		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(5000);
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
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
		driver.findElement(By.xpath("//input[@value=\"ach-cash\"]")).click();//click on ACH cash payment
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
		
		String PlacedOrderIDs11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderIDs11);		
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.findElement(By.id("orderHistoryToggleID")).click(); //click on order history
		Thread.sleep(2000);
		String OrderRecived11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim().toLowerCase();
		Assert.assertEquals(OrderRecived11, firstName.toLowerCase() + " " + lastName.toLowerCase() , "Customer name not matching on order summary");
		
		String Orderdate11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(6)>span"))).getText().trim();
		DateTimeFormatter uiFormatter11 = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
		LocalDate orderDate11 = LocalDate.parse(Orderdate11, uiFormatter11);
		LocalDate today11 = LocalDate.now();
		Assert.assertEquals(orderDate11, today11, "Order date Mismatch: Expected today's date but got " + orderDate11);
		
		//Check New region is created Successfully and added countries are displayed on checkout page 
		//Check Tax is showing correct for newly created region in setting module and checkout page 
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Regions']/parent::div/parent::button"))).click(); //Click on Region button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div:nth-of-type(2)>div:last-of-type>div>div>div>div>div>button")).click(); //Click on Add region button
		Thread.sleep(2000);
		driver.findElement(By.name("details.name")).sendKeys(Region_Name); //Enter region name
		driver.findElement(By.id("details.currency_code")).click();
		driver.findElement(By.id("react-select-2-option-0")).click();
		driver.findElement(By.id("details.countries")).click(); //Click on country drop down
		WebElement countryInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-3-option-0")));
		String firstCountry = countryInput.getText();
		countryInput.click();	
		jse.executeScript("arguments[0].click();",driver.findElement(By.xpath("//span[normalize-space()='Create Region']//parent::button")));
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@role='radio'])[1]")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[normalize-space()='Tax Settings']//parent::a")).click();
		String taxRegion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(3)>div>div"))).getText().replaceAll("\\D", "");		
		System.out.println("Tax region : " + taxRegion);
		
		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		
		
		String ordertax = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div.flex-nowrap>div:nth-of-type(3)>div>span:first-of-type")).getText().trim();
		System.out.println("Order Tax: " + ordertax);
		String extractedTax = ordertax.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedTax, taxRegion, "Tax rate mismatch in order summary");

		Thread.sleep(5000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email);  
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
		Thread.sleep(2000);
		
		boolean isCountryFound = false;
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#country"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search country...']"))).sendKeys(firstCountry);
		List<WebElement> countryList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@role='option']")));

		for (WebElement country : countryList) {
		    String countryName = country.getText().trim();
		    System.out.println("Found country: " + countryName);

		    if (countryName.equalsIgnoreCase(firstCountry)) {
		        country.click();
		        isCountryFound = true;
		        break;
		    }
		}

		Assert.assertTrue(isCountryFound,"Region '" + firstCountry + "' not found in country list");
		driver.findElement(By.cssSelector("input#street")).sendKeys("Bulevardi i Ri");
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		Thread.sleep(2000);
		driver.findElement(By.id("postal_code")).sendKeys("45454");

		Thread.sleep(5000);
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
		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click(); //Clicking on checkbox				
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		Thread.sleep(7000);
		String PlacedOrderIDss11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderIDss11);
		driver.close();
		driver.switchTo().window(originalTab);
		
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
		}catch (NoSuchElementException e) {
			Assert.fail("Region not found: " + e.getMessage());
		}
		
		//Check Return reason created/updated/Deleted  Successfully 
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Return Reasons']/parent::div/parent::button"))).click(); //Click on Region button
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[1]")).click(); //Click on Add return reason button
		driver.findElement(By.xpath("//span[contains(text(),\"Add reason\")]")).click();
		driver.findElement(By.xpath("//input[@placeholder=\"Label\"]")).sendKeys(Return_Reason);
		driver.findElement(By.xpath("//input[@placeholder=\"Value\"]")).sendKeys(Return_Reason);
		driver.findElement(By.xpath("//textarea[@placeholder=\"Customer received the wrong size\"]")).sendKeys("Return order Discription");
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		
		Thread.sleep(2000);
		String reason = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + Return_Reason + "']"))).getText().trim();
		Assert.assertEquals(reason, Return_Reason, "Return reason not created successfully");
		System.out.println("Return reason created successfully");
		
		driver.findElement(By.xpath("//div[@id='main-page-ui-div']//main//*[normalize-space()='" + Return_Reason + "']//parent::div")).click(); //Click on edit button
		WebElement EditReason = driver.findElement(By.xpath("//input[@placeholder=\"Reason Name:...\"]"));
		EditReason.clear();
		EditReason.sendKeys(Updated_Return_Reason);
		driver.findElement(By.xpath("//input[@placeholder=\"unique_value_key\"]")).clear();
		driver.findElement(By.xpath("//input[@placeholder=\"unique_value_key\"]")).sendKeys(Updated_Return_Reason);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();
		
		Thread.sleep(2000);
		String Updatedreason = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + Updated_Return_Reason + "']"))).getText().trim();
		Assert.assertEquals(Updatedreason, Updated_Return_Reason, "Return reason not updated successfully");
		System.out.println("Return reason updated successfully");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[2]")).click();
		driver.findElement(By.xpath("//span[contains(text(),\"Delete reason\")]")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Yes, Confirm\"]")).click();
		
		String Deletedreason = driver.findElement(By.xpath("//div[text()='No return reasons created']")).getText().trim();
		Assert.assertEquals(Deletedreason, "No return reasons created", "Return reason not deleted successfully");
		System.out.println("Return reason deleted successfully\n");
		
		//Check shipping option created/Edited Successfully 
		//Check shipping option deleted Successfully 
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Fulfillment']/parent::div/parent::button"))).click(); //Click on Region button
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[1]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),\"Add Shipping Option\")])//parent::span//parent::button")).click();
		
		driver.findElement(By.xpath("//input[@placeholder=\"Title...\"]")).sendKeys(Shipping_Option);
		driver.findElement(By.id("price_type")).click();
		driver.findElement(By.id("react-select-2-option-0")).click();
		driver.findElement(By.xpath("(//input[@inputmode='decimal'])[1]")).sendKeys(Shipping_Amount);
		driver.findElement(By.id("react-select-3-placeholder")).click();
		driver.findElement(By.id("react-select-3-option-0")).click();
		driver.findElement(By.id("react-select-4-placeholder")).click();
		driver.findElement(By.id("react-select-4-option-0")).click();
		driver.findElement(By.xpath("(//input[@inputmode='decimal'])[2]")).sendKeys(Shipping_Amount);
		driver.findElement(By.xpath("(//input[@inputmode='decimal'])[3]")).sendKeys(Shipping_Amount);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save and close\"]")).click();
		
		Thread.sleep(2000);
		String ShippingOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='" + Shipping_Option + "']"))).getText().trim();
		Assert.assertEquals(ShippingOption, Shipping_Option, "Shipping option not created successfully");
		System.out.println("Shipping option created successfully");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[2]")).click(); //Click on edit button
		driver.findElement(By.xpath("//span[contains(text(),\"Edit\")]")).click();
		driver.findElement(By.xpath("//input[@placeholder=\"Title...\"]")).clear();
		driver.findElement(By.xpath("//input[@placeholder=\"Title...\"]")).sendKeys(Updated_Shipping_Option);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save and close\"]")).click();
		
		Thread.sleep(2000);
		String ShippingOption1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='" + Updated_Shipping_Option + "']"))).getText().trim();
		Assert.assertEquals(ShippingOption1, Updated_Shipping_Option, "Shipping option not created successfully");
		System.out.println("Shipping option updated successfully");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[2]")).click(); //Click on edit button
		driver.findElement(By.xpath("//span[contains(text(),\"Delete\")]")).click();
		System.out.println("Shipping option deleted successfully\n");

		//Check return shipping option created/Edited Successfully 
		//Check return shipping option deleted Successfully 
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Fulfillment']/parent::div/parent::button"))).click(); //Click on Region button
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[2]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),\"Add Return Shipping Option\")])//parent::span//parent::button")).click();
		
		driver.findElement(By.xpath("//input[@placeholder=\"Title...\"]")).sendKeys(Return_Shipping_Option);
		driver.findElement(By.id("price_type")).click();
		driver.findElement(By.id("react-select-2-option-0")).click();
		driver.findElement(By.xpath("(//input[@inputmode='decimal'])[1]")).sendKeys(Shipping_Amount);
		driver.findElement(By.id("react-select-3-placeholder")).click();
		driver.findElement(By.id("react-select-3-option-0")).click();
		driver.findElement(By.id("react-select-4-placeholder")).click();
		driver.findElement(By.id("react-select-4-option-0")).click();
		driver.findElement(By.xpath("(//input[@inputmode='decimal'])[2]")).sendKeys(Shipping_Amount);
		driver.findElement(By.xpath("(//input[@inputmode='decimal'])[3]")).sendKeys(Shipping_Amount);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save and close\"]")).click();
		
		Thread.sleep(2000);
		String ReturnShippingOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='" + Return_Shipping_Option + "']"))).getText().trim();
		Assert.assertEquals(ReturnShippingOption, Return_Shipping_Option, "Shipping option not created successfully");
		System.out.println("Return Shipping option created successfully");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[3]")).click(); //Click on edit button
		driver.findElement(By.xpath("//span[contains(text(),\"Edit\")]")).click();
		driver.findElement(By.xpath("//input[@placeholder=\"Title...\"]")).clear();
		driver.findElement(By.xpath("//input[@placeholder=\"Title...\"]")).sendKeys(Updated_Return_Shipping_Option);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save and close\"]")).click();
		
		Thread.sleep(2000);
		String returnShippingOption1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='" + Updated_Return_Shipping_Option + "']"))).getText().trim();
		Assert.assertEquals(returnShippingOption1, Updated_Return_Shipping_Option, "Shipping option not created successfully");
		System.out.println("Return Shipping option updated successfully");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[3]")).click(); //Click on edit button
		driver.findElement(By.xpath("//span[contains(text(),\"Delete\")]")).click();
		System.out.println("Return Shipping option deleted successfully");	
		
		ReceivedMail("Card Expiration Alert");	
	}
	
	@Test(priority = 24)
	public void PriceAdjustment() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		String Product_Name = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();
		
		//Check Price adjustment setting is applied correctly on checkout pages and displayed in order details (surcharge fee,service fee,conveninees fee,cash discount and dual price)
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 
		
		//Convenience Fees
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[2]")).click();
			String convenienceFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter amount\"]")).getAttribute("value").trim();
			System.out.println("Convenience Fee In Settings : " + convenienceFee);
			driver.findElement(By.id("dialog-checkbox")).click();		
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();
		}catch(Exception e) {
			System.out.println("Convenience fee is already enabled");
		}
		
		String convenienceFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter amount\"]")).getAttribute("value").trim();			
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);	
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};
		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");
		WebElement subscriptionToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",subscriptionToggle);
		subscriptionToggle.click(); //Subscription toggle button
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(Value); //Subscription price field
	    driver.findElement(By.cssSelector("div.frequency-select-container")).click(); //Frequency drop down
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[2]")).click(); //Select frequency as monthly

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		
		Thread.sleep(5000);
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
		
		String orderconvenienceFee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(9)>div:nth-of-type(3)>div:last-of-type"))).getText().trim();
		System.out.println("convenience Fee On Checkout Page : " + orderconvenienceFee);
		String extractedconvenienceFee = orderconvenienceFee.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedconvenienceFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),convenienceFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Convenience Fee mismatch in order summary");
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));		

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys("03/26");
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
		String PlacedOrderd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderd);
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.findElement(By.id("orderHistoryToggleID")).click(); //click on order history
		Thread.sleep(2000);
		String OrderRecived = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim().toLowerCase();
		Assert.assertEquals(OrderRecived, firstName.toLowerCase() + " " + lastName.toLowerCase(), "Customer name not matching on order summary");
		
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)")).click(); //Click on order details		
		Thread.sleep(2000);
		String orderconvenienceFee1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(5)>div:nth-of-type(2)>span:first-of-type"))).getText().trim();
		System.out.println("convenience Fee In Order Details : " + orderconvenienceFee1);
		String extractedconvenienceFee1 = orderconvenienceFee1.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedconvenienceFee1.replaceAll("[^0-9]", "").replaceAll("00$", ""),convenienceFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Convenience Fee mismatch in order summary");
		
		String TotalOrderAmount =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(8)>div:nth-of-type(2)>span:first-of-type"))).getText().trim();
		System.out.println("Total Order Amount In Order Details : " + TotalOrderAmount);
		Assert.assertEquals(TotalOrderAmount, 11.04, "Total order amount mismatch in order summary");
		
		//Surcharge
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 
		
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[3]")).click();
			String SurchargeFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter percentage\"]")).getAttribute("value").trim();
			System.out.println("Surcharge Fee In Settings : " + SurchargeFee);
			driver.findElement(By.id("dialog-checkbox")).click();		
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();
		}catch(Exception e) {
			System.out.println("Surcharge fee is already enabled");
		}
		
		String SurchargeFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter percentage\"]")).getAttribute("value").trim();
		
		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(5000);
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
		
		String SurchargeFees = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(9)>div:nth-of-type(3)>div:first-of-type"))).getText().trim();
		System.out.println("Surcharge Fee On Checkout Page : " + SurchargeFees);
		String extractedSurchargeFee = SurchargeFees.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedSurchargeFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),SurchargeFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Surcharge Fee mismatch in order summary");
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys("03/26");
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();

		WebElement cardHolderd = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolderd.clear();
		cardHolderd.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderd1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderd1);
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.findElement(By.id("orderHistoryToggleID")).click(); //click on order history
		Thread.sleep(2000);
		String OrderRecived1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim().toLowerCase();
		Assert.assertEquals(OrderRecived1, firstName.toLowerCase() + " " + lastName.toLowerCase(), "Customer name not matching on order summary");
		
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)")).click(); //Click on order details		
		Thread.sleep(2000);
		String ordersurgeFee1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(5)>div:nth-of-type(2)>span:first-of-type"))).getText().trim();
		System.out.println("surge Fee In Order Details : " + ordersurgeFee1);
		String extractedsurgeFee1 = ordersurgeFee1.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedsurgeFee1.replaceAll("[^0-9]", "").replaceAll("00$", ""),ordersurgeFee1.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Convenience Fee mismatch in order summary");
		
		String TotalOrderAmount1 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(8)>div:nth-of-type(2)>span:first-of-type"))).getText().trim();
		System.out.println("Total Order Amount In Order Details : " + TotalOrderAmount1);
		Assert.assertEquals(Double.parseDouble(TotalOrderAmount1.replace("$", "")), 1.14,"Total order amount mismatch in order summary");
		
		//Service Fee
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 

		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[4]")).click();
			String ServiceFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter percentage\"]")).getAttribute("value").trim();
			System.out.println("Service Fee In Settings : " + ServiceFee);
			driver.findElement(By.id("dialog-checkbox")).click();		
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();
		}catch(Exception e) {
			System.out.println("Service fee is already enabled");
		}

		String ServiceFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter percentage\"]")).getAttribute("value").trim();

		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(5000);
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

		String ServiceFees = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(9)>div:nth-of-type(3)>div:first-of-type"))).getText().trim();
		System.out.println("Service Fee On Checkout Page : " + ServiceFees);
		String extractedServiceFee = ServiceFees.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedServiceFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),ServiceFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Service Fee mismatch in order summary");

		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys("03/26");
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();

		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderd11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderd11);
		driver.close();
		driver.switchTo().window(originalTab);

		driver.findElement(By.id("orderHistoryToggleID")).click(); //click on order history
		Thread.sleep(2000);
		String OrderRecived11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim().toLowerCase();
		Assert.assertEquals(OrderRecived11, firstName.toLowerCase() + " " + lastName.toLowerCase(), "Customer name not matching on order summary");

		driver.navigate().to(Orders);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)")).click(); //Click on order details		
		Thread.sleep(2000);
		String orderserviceee1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(5)>div:nth-of-type(2)>span:first-of-type"))).getText().trim();
		System.out.println("Service Fee In Order Details : " + orderserviceee1);
		String extractedserviceFee1 = orderserviceee1.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedserviceFee1.replaceAll("[^0-9]", "").replaceAll("00$", ""),orderserviceee1.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Convenience Fee mismatch in order summary");

		String TotalOrderAmount11 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(8)>div:nth-of-type(2)>span:first-of-type"))).getText().trim();
		System.out.println("Total Order Amount In Order Details : " + TotalOrderAmount11);
		Assert.assertEquals(Double.parseDouble(TotalOrderAmount11.replace("$", "")), 1.14,"Total order amount mismatch in order summary");
		
		//Cash/ACH Discount (Cash Discount)
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 

		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[5]")).click();
			driver.findElement(By.id("displayStyleCashDiscount")).click();
			String CashDiscountFee = driver.findElement(By.xpath("//input[@placeholder='1.00']")).getAttribute("value").trim();
			System.out.println("Cash Discount Fee In Settings : " + CashDiscountFee);
		}catch(Exception e) {
			System.out.println("Cash Discount fee is already enabled");
		}

		String CashDiscountFee = driver.findElement(By.xpath("//input[@placeholder='1.00']")).getAttribute("value").trim();
		
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(7000);
		driver.findElement(By.xpath("//span[normalize-space(.)='ACH']//parent::button")).click(); //Select card payment method
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search Customer\"]")).sendKeys(ContactPhone1); //Select first customer from the list
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,'overflow-y-auto')]/div[contains(@class,'mb-[5px]')][1]")).click(); //Select first customer from the list
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]")).click(); //Clicking on add line items button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); //Clicking on item name field
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//span[text()='Create New Item']//parent::span//parent::button")).click(); //Clicking on first product from the dropdown
		Thread.sleep(1000);
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);
		WebElement uploadImage1 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage1);
		
		Thread.sleep(3000);
		String[] files11 = {Media_Path + Fileone};
		String allFiles11 = String.join("\n", files11);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles11);
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys(Price);
		Thread.sleep(1000);

		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Bank Account Details']")));
		Thread.sleep(3000);
		
		driver.findElement(By.name("creditCard.achAccountHolderName")).sendKeys(Account_Holder_Name);
		driver.findElement(By.name("creditCard.achRoutingNumber")).sendKeys(Routing_Number);
		driver.findElement(By.name("creditCard.achAccountNumber")).sendKeys(Account_Number);
		driver.findElement(By.id("creditCard.achAccType")).click();//click on mode drop down
		driver.findElement(By.id("react-select-2-option-0")).click();//select checking
		driver.findElement(By.id("creditCard.achAccountCategory")).click(); //click on account category drop down
		driver.findElement(By.id("react-select-3-option-0")).click(); //select personal
		
		String dualFees = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.Transaction-summary-inside-class>div:nth-of-type(2)>div>div:nth-of-type(2)>span"))).getText().trim();
		System.out.println("Cash Discount Fee On Checkout Page : " + dualFees);
		String extracteddualFee = dualFees.replaceAll("[^0-9]", "");
		Assert.assertEquals(extracteddualFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),CashDiscountFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),"dual Fee mismatch in order summary");
		
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.virtual-terminal-ui>form>div>div>div:nth-of-type(7)>div:nth-of-type(2)>h3>div>div>div:last-of-type")).click(); 
		driver.findElement(By.name("address.address_1")).sendKeys(Street_Add); 
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();	
		Thread.sleep(1000);
		driver.findElement(By.name("address.address_2")).sendKeys(Street_Add); 
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space(.//p/text())='ACH Payment']")).click(); //Clicking on record cash sale button
		Thread.sleep(15000);
		String orderIds = driver.findElement(By.xpath("//div[h2[normalize-space(text())='Transaction Summary']]/p")).getText().trim().replace("#", ""); //Getting order id
		System.out.println("Order ID: " + orderIds);
		
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)")).click(); //Click on order details		
		Thread.sleep(2000);
		String ordersdual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(5)>div:nth-of-type(1)>span:first-of-type"))).getText().trim();
		System.out.println("Cash Discount Fee In Order Details : " + ordersdual);
		String extracteddualprice1 = ordersdual.replaceAll("[^0-9]", "");
		Assert.assertEquals(extracteddualprice1.replaceAll("[^0-9]", "").replaceAll("00$", ""),CashDiscountFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Convenience Fee mismatch in order summary");

		String TotalOrderAmounts1 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(8)>div:nth-of-type(2)>span:first-of-type"))).getText().trim();
		System.out.println("Total Order Amount In Order Details : " + TotalOrderAmounts1);
		Assert.assertEquals(Double.parseDouble(TotalOrderAmounts1.replace("$", "")), 2.00,"Total order amount mismatch in order summary");
				
		//Cash/ACH Discount (dual pricet)
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 

		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[5]")).click();
			driver.findElement(By.id("displayStyleDualPricing")).click();
			String DualPriceFee1 = driver.findElement(By.xpath("//input[@placeholder='1.00']")).getAttribute("value").trim();
			System.out.println("Dual Price Fee In Settings : " + DualPriceFee1);
		}catch(Exception e) {
			System.out.println("Dual Price fee is already enabled");
		}

		String DualPriceFee1 = driver.findElement(By.xpath("//input[@placeholder='1.00']")).getAttribute("value").trim();
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(7000);
		driver.findElement(By.xpath("//span[normalize-space(.)='ACH']//parent::button")).click(); //Select card payment method
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search Customer\"]")).sendKeys(ContactPhone1); //Select first customer from the list
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@class,'overflow-y-auto')]/div[contains(@class,'mb-[5px]')][1]")).click(); //Select first customer from the list
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]")).click(); //Clicking on add line items button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); //Clicking on item name field
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//span[text()='Create New Item']//parent::span//parent::button")).click(); //Clicking on first product from the dropdown
		Thread.sleep(1000);
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage11 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage11);	
		Thread.sleep(3000);
		String[] files111 = {Media_Path + Fileone};
		String allFiles111 = String.join("\n", files111);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles111);
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.xpath("//div[@id='one-time-purchase-div']/descendant::button[@role=\"switch\"][1]")).click(); //One time purchase toggle button
		Thread.sleep(1000);
		WebElement subscriptionToggle1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",subscriptionToggle1);
		subscriptionToggle1.click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(Value); //Subscription price field
	    driver.findElement(By.cssSelector("div.frequency-select-container")).click(); //Frequency drop down
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[2]")).click(); //Select frequency as monthly
		Thread.sleep(1000);

		WebElement confirmSaveBtn11 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn11);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		Thread.sleep(3000);		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Bank Account Details']")));
		Thread.sleep(3000);		
		driver.findElement(By.name("creditCard.achAccountHolderName")).sendKeys(Account_Holder_Name);
		driver.findElement(By.name("creditCard.achRoutingNumber")).sendKeys(Routing_Number);
		driver.findElement(By.name("creditCard.achAccountNumber")).sendKeys(Account_Number);
		driver.findElement(By.id("creditCard.achAccType")).click();//click on mode drop down
		driver.findElement(By.id("react-select-2-option-0")).click();//select checking
		driver.findElement(By.id("creditCard.achAccountCategory")).click(); //click on account category drop down
		driver.findElement(By.id("react-select-3-option-0")).click(); //select personal
		
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.virtual-terminal-ui>form>div>div>div:nth-of-type(7)>div:nth-of-type(2)>h3>div>div>div:last-of-type")).click(); 
		driver.findElement(By.name("address.address_1")).sendKeys(Street_Add); 
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();	
		Thread.sleep(1000);
		driver.findElement(By.name("address.address_2")).sendKeys(Street_Add); 
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space(.//p/text())='ACH Payment']")).click(); //Clicking on record cash sale button
		Thread.sleep(15000);
		
		String ordersdual1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("main.sub-main-ui-page>div>div>div:nth-of-type(2)>div:nth-of-type(4)>div:nth-of-type(2)>span:first-of-type"))).getText().trim();
		System.out.println("Dual Price Fee In Order Details : " + ordersdual1);
		String extracteddualprice11 = ordersdual1.replaceAll("[^0-9]", "");
		Assert.assertEquals(extracteddualprice11.replaceAll("[^0-9]", "").replaceAll("00$", ""),DualPriceFee1.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Convenience Fee mismatch in order summary");
		String orderIds1 = driver.findElement(By.xpath("//div[h2[normalize-space(text())='Transaction Summary']]/p")).getText().trim().replace("#", ""); //Getting order id
		System.out.println("Order ID: " + orderIds1);	
		String TotalOrderAmounts11 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("main.sub-main-ui-page>div>div>div:nth-of-type(2)>div:nth-of-type(4)>div:nth-of-type(6)>p>span"))).getText().trim();
		System.out.println("Total Order Amount In Order Details : " + TotalOrderAmounts11);
		Assert.assertEquals(Double.parseDouble(TotalOrderAmounts11.replace("$", "")), 2.08, "Total order amount mismatch in order summary");		
	}
		
	@Test(priority = 25)
	public void SubscriptionSettings() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);
		
		String Product_Name = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();
		String Product_Name1 = "Tier Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		DiscriptionTier1 = 	"Description for first tier installment", DiscriptionTier2 = "Description for Second tier installment", TitleTier1 = "First Tier Installment",
		TitleTier2 = "Second Tier Installment", Tier1Feature1 = "Tier one Feature First", Tier2Feature1 = "Tier two Feature one";
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");
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

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
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
		
		Thread.sleep(5000);
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
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys("03/26");
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
		String PlacedOrderd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderd);
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Check Allow members to self-cancel Subscriptions setting is working when toggle is on/off
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Subscription Settings']/parent::div/parent::button"))).click(); 

		driver.findElement(By.id("off")).click();
		driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[1]")).click();
		Thread.sleep(3000);
		
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table table tbody tr td:nth-of-type(2)"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'radix')]"))).click();
		
		String parentWindow = driver.getWindowHandle();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Open Customer Hub')]"))).click();

		for (String windowHandle : driver.getWindowHandles()) {
		    if (!windowHandle.equals(parentWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("table.table-auto>tbody>tr:first-of-type>td:first-of-type>div>div>a"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Subscription Summary\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();	
		List<WebElement> cancelSubscriptionBtn = driver.findElements(By.cssSelector("div.manage-sub-wrapper>div:first-of-type>div>div"));
		Assert.assertEquals(cancelSubscriptionBtn.size(), 1, "Cancel Subscription button should be hidden but is visible");	
		System.out.println("Cancel Subscription button is hidden when toggle is off");
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(parentWindow);
		
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Subscription Settings']/parent::div/parent::button"))).click(); 
		Thread.sleep(3000);
		driver.findElement(By.id("on")).click();
		driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[1]")).click();
		
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table table tbody tr td:nth-of-type(2)"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'radix')]"))).click();	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Open Customer Hub')]"))).click();

		for (String windowHandle : driver.getWindowHandles()) {
		    if (!windowHandle.equals(parentWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("table.table-auto>tbody>tr:first-of-type>td:first-of-type>div>div>a"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Subscription Summary\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();	
		List<WebElement> cancelSubscriptionBtn1 = driver.findElements(By.cssSelector("div.manage-sub-wrapper>div:first-of-type>div>div"));
		Assert.assertEquals(cancelSubscriptionBtn1.size(), 2, "Cancel Subscription button is still hidden");	
		System.out.println("Cancel Subscription button is visible when toggle is on");
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//Check Show Immediate Upgrade Option setting is working when toggle is on/off	
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name1);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage1 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage1);		
		Thread.sleep(3000);
		String[] files11 = {Media_Path + Fileone};
		String allFiles11 = String.join("\n", files11);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles11);
		
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
		driver.findElement(By.cssSelector("#tier-item-2>div>div>div>button")).click();//click on price
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		driver.findElement(By.xpath("(//input[@name='productDetails.variants.1.prices[0].amount'])[1]")).sendKeys(susbcriptionPrice);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[normalize-space()='Description + Bullet points'])[2]")).click();//click on description and bullet points to save tier details
		driver.findElement(By.xpath("//textarea[@name=\"productDetails.variants.1.product_tier_description\"]")).sendKeys(DiscriptionTier2);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		driver.findElement(By.xpath("//input[@name=\"productDetails.variants.1.product_tier_features.0.title\"]")).sendKeys(TitleTier2);
		driver.findElements(By.cssSelector("div.key-features>div>div>div>button")).get(1).click();//click on feature one
		driver.findElement(By.xpath("//input[contains(@name,\"productDetails.variants.1.product_tier_features.0.feature_title.0\")]")).sendKeys(Tier2Feature1);
		
		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name1);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>div>div>button:first-of-type"))).click();
				
		wait.until(d -> d.getWindowHandles().size() > 1);
		for (String window : driver.getWindowHandles()) {
		    if (!window.equals(originalTab)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@type,'button')])[2]"))).click();
		wait.until(d -> d.getWindowHandles().size() > 2);
		for (String window : driver.getWindowHandles()) {
		    driver.switchTo().window(window);
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
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys("03/26");
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();

		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderd1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderd1);
		driver.close();
		driver.switchTo().window(originalTab);		
		
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Subscription Settings']/parent::div/parent::button"))).click(); 
		Thread.sleep(3000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		String buttonon = driver.findElement(By.xpath("(//button[@role='switch'])[6]")).getAttribute("data-state");
		
		try {
			if(buttonon.equals("unchecked")) {
				driver.findElement(By.xpath("(//button[@role='switch'])[6]")).click();
				driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[1]")).click();
				System.out.println("Immediate Upgrade Option is turned on");
			}
		}catch(Exception e) {
			System.out.println("Immediate Upgrade Option is already turned on");
		}
		
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table table tbody tr td:nth-of-type(2)"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'radix')]"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Open Customer Hub')]"))).click();
		for (String windowHandle : driver.getWindowHandles()) {
		    if (!windowHandle.equals(parentWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("table.table-auto>tbody>tr:first-of-type>td:first-of-type>div>div>a"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Subscription Summary\"]"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();	
		Thread.sleep(1000);
		List<WebElement> cancelSubscriptionBtn11 = driver.findElements(By.cssSelector("div.manage-sub-wrapper>div:first-of-type>div>div"));
		Assert.assertEquals(cancelSubscriptionBtn11.size(), 3, "Immediate Upgrade button is still hidden");	
		System.out.println("Immediate Upgrade button is visible when toggle is on");
		
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.manage-sub-wrapper>div:first-of-type>div>div:last-of-type")).click(); //Click on immediate upgrade button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Upgrade ']//parent::button"))).click();
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("div.modal-scroll>div>ul>li>div>div>div>span>input")).click();
		List<WebElement> upgradeOption = driver.findElements(By.cssSelector("div.modal-scroll>div>div>div"));
		Assert.assertEquals(upgradeOption.size(), 2, "Upgrade option is not visible");			
		driver.close();
		driver.switchTo().window(parentWindow);
		
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Subscription Settings']/parent::div/parent::button"))).click(); 
		Thread.sleep(3000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		String buttonoff = driver.findElement(By.xpath("(//button[@role='switch'])[6]")).getAttribute("data-state");
		
		try {
			if(buttonoff.equals("checked")) {
				driver.findElement(By.xpath("(//button[@role='switch'])[6]")).click();
				driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[1]")).click();
				System.out.println("Immediate Upgrade Option is turned on");
			}
		}catch(Exception e) {
			System.out.println("Immediate Upgrade Option is already turned on");
		}
		
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table table tbody tr td:nth-of-type(2)"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'radix')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Open Customer Hub')]"))).click();

		for (String windowHandle : driver.getWindowHandles()) {
		    if (!windowHandle.equals(parentWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("table.table-auto>tbody>tr:first-of-type>td:first-of-type>div>div>a"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Subscription Summary\"]"))).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();
		Thread.sleep(1000);
		List<WebElement> cancelSubscriptionBtn111 = driver.findElements(By.cssSelector("div.manage-sub-wrapper>div:first-of-type>div>div"));
		Assert.assertEquals(cancelSubscriptionBtn111.size(), 3, "Immediate Upgrade button is still visible");	
		System.out.println("Immediate Upgrade button is visible when toggle is off");	
		
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.manage-sub-wrapper>div:first-of-type>div>div:last-of-type")).click(); //Click on immediate upgrade button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Upgrade ']//parent::button"))).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.modal-scroll>div>ul>li>div>div>div>span>input")).click();
		List<WebElement> upgradeOption1 = driver.findElements(By.cssSelector("div.modal-scroll>div>div>div"));
		Assert.assertEquals(upgradeOption1.size(), 1, "Upgrade option is not visible");	
	}
	
	@Test(priority = 26)
	public void Customer_Hub() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);
		
		String Password = java.util.UUID.randomUUID().toString().replaceAll("-", "").substring(0,10) + "A1@",
		Product_Name1 = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		Product_Name2 = "Tier Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		DiscriptionTier1 = 	"Description for first tier installment", DiscriptionTier2 = "Description for Second tier installment", TitleTier1 = "First Tier Installment",
		TitleTier2 = "Second Tier Installment", Tier1Feature1 = "Tier one Feature First", Tier2Feature1 = "Tier two Feature one";
		
		//Verify that we are able to add new customer and from join us link and that customer s getting added in contacts module as well
		//Verify that a newly created customer can log in to the Customer Hub using the Sign In option
		try {
			driver.navigate().to(Customer_Hub);
			driver.findElement(By.cssSelector("#header-right-side>div:nth-of-type(2)>div>button")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:first-of-type>div>div>div:nth-of-type(2)>div>div>ul>li:last-of-type")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#header-right-side>div:nth-of-type(2)>div>button")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[normalize-space()=\"Join us\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.name("first_name")).sendKeys(firstName);
			driver.findElement(By.name("last_name")).sendKeys(lastName);
			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("phone")).sendKeys(phone);
			driver.findElement(By.name("password")).sendKeys(Password);
			driver.findElement(By.xpath("//button[normalize-space()=\"Join\"]")).click();	
			System.out.println("Customer is already login in customer hub");
		}catch(Exception e) {
			driver.navigate().to(Customer_Hub);
			driver.findElement(By.cssSelector("#header-right-side>div:nth-of-type(2)>div>button")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[normalize-space()=\"Join us\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.name("first_name")).sendKeys(firstName);
			driver.findElement(By.name("last_name")).sendKeys(lastName);
			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("phone")).sendKeys(phone);
			driver.findElement(By.name("password")).sendKeys(Password);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[normalize-space()=\"Join\"]")).click();
			System.out.println("Customer is added from customer hub");
		}
		
		//Verify that when we purchase onetime product then its order getting listed in order tab
		Thread.sleep(3000);
		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		
		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};
		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("1");
		Thread.sleep(1000);

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();		
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
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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
		String PlacedOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrder);
		driver.close();
		driver.switchTo().window(originalTab);
			
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		WebElement onetimeorder = driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span"));
		String Orderidext = onetimeorder.getText();
		System.out.println("Order ID from order tab: " + Orderidext);
		onetimeorder.click();
		Thread.sleep(4000);
		String currentUrl = driver.getCurrentUrl().toLowerCase();	
		System.out.println("Current URL : " + currentUrl);
		Assert.assertTrue(currentUrl.contains(PlacedOrder), "Order ID not found in URL");
				
		//Verify that when we purchase subscription product then its order is getting listed in order tab
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name1);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage1 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage1);		
		Thread.sleep(3000);
		String[] files11 = {Media_Path + Fileone};
		String allFiles11 = String.join("\n", files11);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles11);
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("window.scrollBy(0,500)");
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

		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name1);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}		
		
		Thread.sleep(5000);
		driver.findElement(By.name("shippingOption")).click();
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrder1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrder1);
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		WebElement suscorder = driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span"));
		String Orderidext1 = suscorder.getText();
		System.out.println("Order ID from order tab: " + Orderidext1);
		suscorder.click();
		Thread.sleep(4000);
		String currentUrl1 = driver.getCurrentUrl().toLowerCase();	
		System.out.println("Current URL : " + currentUrl1);
		Assert.assertTrue(currentUrl1.contains(PlacedOrder1), "Order ID not found in URL");
		
		//Verify that when we purchase tier  product then its order is getting listed in order tab
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name2);	
		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created for 911 Automation product Test");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage11 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage11);	
		Thread.sleep(3000);
		String[] files111 = {Media_Path + Fileone};
		String allFiles111 = String.join("\n", files111);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles111);
		
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
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Tier']")).click(); //click on add tier button to add second tier		
		driver.findElement(By.xpath("(//input[contains(@name,'productDetails.variants.1.title')])[1]")).sendKeys("Second Tier Installment");
		Thread.sleep(1000);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		driver.findElement(By.cssSelector("#tier-item-2>div>div>div>button")).click(); //click on price
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		driver.findElement(By.xpath("(//input[@name='productDetails.variants.1.prices[0].amount'])[1]")).sendKeys(susbcriptionPrice);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[normalize-space()='Description + Bullet points'])[2]")).click();//click on description and bullet points to save tier details
		driver.findElement(By.xpath("//textarea[@name=\"productDetails.variants.1.product_tier_description\"]")).sendKeys(DiscriptionTier2);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		driver.findElement(By.xpath("//input[@name=\"productDetails.variants.1.product_tier_features.0.title\"]")).sendKeys(TitleTier2);
		driver.findElements(By.cssSelector("div.key-features>div>div>div>button")).get(1).click();//click on feature one
		driver.findElement(By.xpath("//input[contains(@name,\"productDetails.variants.1.product_tier_features.0.feature_title.0\")]")).sendKeys(Tier2Feature1);
		
		WebElement confirmSaveBtn11 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn11);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name2);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		Thread.sleep(3000);
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
		
		Thread.sleep(5000);
		driver.findElement(By.name("shippingOption")).click();	
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
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

		WebElement cardHolder11 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder11.clear();
		cardHolder11.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrder11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrder11);
		driver.close();
		driver.switchTo().window(originalTab);
		
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		WebElement tierorder = driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span"));
		String Orderidext2 = tierorder.getText();
		System.out.println("Order ID from order tab: " + Orderidext2);
		
		String OrderAmount = driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(4)>span:first-of-type")).getText().trim();
		System.out.println("Order Amount: " + OrderAmount);
		tierorder.click();
		Thread.sleep(4000);
		String currentUrl11 = driver.getCurrentUrl().toLowerCase();	
		System.out.println("Current URL : " + currentUrl11);
		Assert.assertTrue(currentUrl11.contains(PlacedOrder11), "Order ID not found in URL");
		
		//Verify that all cretaed invoice for respected customer is getting display in invoice tab
		Thread.sleep(3000);
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.newproductbutton:nth-of-type(3)"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()=\"+ Add Recipient\"]//parent::button")).click();
		driver.findElement(By.xpath("//input[@placeholder=\"Search Customers\"]")).sendKeys(email); //Select first customer from the list
		Thread.sleep(5000);
		driver.findElement(By.xpath("//ul[contains(@class,\"px-4 py-3 hover:bg-gray-50 cursor-pointer\")]")).click(); //Select first customer from the list
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]")).click(); //Clicking on add line items button
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id=\"root\"]//div[contains(@class,\"mtb:w-full\")]//ul[1]")).click(); //Clicking on item name field
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Save And Send']//parent::span//parent::button")).click();
		Thread.sleep(5000);
		String invoiceid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='dialog']>div>div:nth-of-type(2)>div>div>div>table>thead>tr>td:nth-of-type(2)"))).getText().trim();
		System.out.println("Created Invoice ID: " + invoiceid);
		Thread.sleep(5000);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@id,'radix')]//span[contains(@class,'mr')]//div[contains(@class,'flex')])[1]")));
		jse.executeScript("arguments[0].click();", element);
		
		Thread.sleep(5000);
		driver.navigate().to(Customer_Hub + "account/invoices");
		Thread.sleep(5000);
		String invoiceid1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:last-of-type>div>div>div>div>table>tbody>tr:first-of-type>td:first-of-type"))).getText().trim().replace("#","");
		System.out.println("Created Invoice ID In Customers Hub : " + invoiceid1);
		Assert.assertEquals(invoiceid, invoiceid1, "Created invoice is not visible in customer hub");
		
		//verify that we are able to open invoice details 
		//Verify that we are able to make payment for invoice
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:last-of-type>div>div>div>div>table>tbody>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);
		String invoiceID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>section>div>div>div>p>span:first-of-type"))).getText().trim().replace("#","");
		Assert.assertEquals(invoiceid, invoiceID, "Created invoice is not visible in customer hub");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>section>div:first-of-type>div>div:nth-of-type(2)>div>a:first-of-type")).click(); //Click on pay now button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Pay\"]")).click();
		Thread.sleep(3000);
		
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Card Information']")));
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

		WebElement cardHolder111 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder111.clear();
		cardHolder111.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("(//button[@role='checkbox'])[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		String PlacedOrder1s1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrder1s1);
		
		driver.navigate().to(Customer_Hub);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#header-right-side>div:nth-of-type(2)>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:first-of-type>div>div>div:nth-of-type(2)>div>div>ul>li:nth-of-type(3)")).click();
		Thread.sleep(1000);		
		String preouct1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'grid') and contains(@class,'grid-cols-1')]/div[2]/div/div[2]/div/div/article/div/div/div[2]/span[1])[1]"))).getText().trim();
		String expectedProduct = preouct1;

		List<WebElement> products = driver.findElements(By.xpath("(//div[contains(@class,'grid') and contains(@class,'grid-cols-1')]/div[2]/div/div[2]/div/div/article/div/div/div[2]/span[1])"));
		boolean productFound = false;
		for (WebElement product : products) {
		    String productName = product.getText().trim();
		    if (productName.equalsIgnoreCase(expectedProduct)) {
		        productFound = true;
		        break;
		    }
		}

		Assert.assertTrue(productFound, "Product is not present in the list");
		
		String preouct2s = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'grid') and contains(@class,'grid-cols-1')]/div[2]/div/div[2]/div/div/article/div/div/div[2]/span[1])[2]"))).getText().trim();
		List<WebElement> products1 = driver.findElements(By.xpath("(//div[contains(@class,'grid') and contains(@class,'grid-cols-1')]/div[2]/div/div[2]/div/div/article/div/div/div[2]/span[1])"));
		boolean product2Found = false;

		for (WebElement product : products1) {
		    String productName = product.getText().trim();
		    if (productName.equalsIgnoreCase(preouct2s)) {
		        product2Found = true;
		        break;
		    }
		}

		Assert.assertTrue(product2Found, "Product is not present in the list");
		
		String preouct3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'grid') and contains(@class,'grid-cols-1')]/div[2]/div/div[2]/div/div/article/div/div/div[2]/span[1])[3]"))).getText().trim();
		List<WebElement> products11 = driver.findElements(By.xpath("(//div[contains(@class,'grid') and contains(@class,'grid-cols-1')]/div[2]/div/div[2]/div/div/article/div/div/div[2]/span[1])"));
		boolean product2Found1 = false;

		for (WebElement product : products11) {
		    String productName = product.getText().trim();
		    if (productName.equalsIgnoreCase(preouct3)) {
		        product2Found1 = true;
		        break;
		    }
		}

		Assert.assertTrue(product2Found1, "Product is not present in the list");
		
		//verify that the card which we use to place the order is getting added in card on file section 
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:first-of-type>div>div>div:nth-of-type(2)>div>div>ul>li:nth-of-type(1)")).click();
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:last-of-type>header")));
		
		String cardNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:last-of-type>article>div>div>div:nth-of-type(2)>p>div>div>span:nth-of-type(2)"))).getText().trim();
		String ExpCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:last-of-type>article>div>div>div:nth-of-type(2)>p>div>div:nth-of-type(2)>span:first-of-type"))).getText().trim();
		String namecard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:last-of-type>article>div>div>div:nth-of-type(2)>p>div:nth-of-type(2)>div:first-of-type"))).getText().trim();
		
		Assert.assertTrue(cardNumber.endsWith(Card_No.substring(Card_No.length() - 4)), "Card number is not correct in card on file section");
		Assert.assertEquals(ExpCard, EXP, "Card Expiration date is not correct in card on file section");
		Assert.assertEquals(namecard, F_Name +" "+ L_Name, "Card holder name is not correct in card on file section");
		
		//verify that the address is getting correctly add which we have used while palcing the order
		String Streetadd = driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:first-of-type>div>div:nth-of-type(2)>div>p:nth-of-type(2)")).getText().trim();
		String cityadd = driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:first-of-type>div>div:nth-of-type(2)>div>p:nth-of-type(3)")).getText().trim();
		String countryadd = driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:first-of-type>div>div:nth-of-type(2)>div>p:nth-of-type(4)")).getText().trim();
		
		Assert.assertEquals(Streetadd,Street_Add, "Street address is not correct in customer hub");
		Assert.assertEquals(cityadd,Country_Add, "City is not correct in customer hub");
		Assert.assertEquals(countryadd, country, "Country is not correct in customer hub");
		
		//Verify that we are able to see the profile summary ,  orders , subscription, invoice ,and added cards in overview tab in customer hub 
		//verify that when we open order summary the  it is showing all details correct 
		String profilesummary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:first-of-type>div>div:nth-of-type(1)>div>div>p"))).getText().trim();
		Assert.assertEquals(profilesummary,email, "Profile email is not visible in customer hub overview tab");
		
		String profilephone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:first-of-type>div>div:nth-of-type(1)>p:last-of-type"))).getText().trim().replaceAll("[^0-9]", "");
		Assert.assertEquals(phone,profilephone, "Profile phone is not visible in customer hub overview tab");
		
		//Verify that we are able to add billing and shipping address 
		jse.executeScript("window.scrollTo(0,0);");
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:first-of-type>div>div>div:nth-of-type(2)>div>div>ul>li:nth-of-type(2)")).click();
		Thread.sleep(2000);
		driver.findElements(By.cssSelector("button.bill-add:first-of-type")).get(0).click(); 
		driver.findElement(By.name("first_name")).sendKeys(firstName);
		driver.findElement(By.name("last_name")).sendKeys(lastName);
		driver.findElement(By.name("address_1")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("ul.suggestions-dropdown>li:first-of-type")).click();
		driver.findElement(By.cssSelector("div.sticky.bottom-0>button:last-of-type")).click();
		
		driver.findElements(By.cssSelector("button.bill-add:first-of-type")).get(1).click(); 
		driver.findElement(By.name("first_name")).sendKeys(firstName);
		driver.findElement(By.name("last_name")).sendKeys(lastName);
		driver.findElement(By.name("address_1")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("ul.suggestions-dropdown>li:first-of-type")).click();
		driver.findElement(By.cssSelector("div.sticky.bottom-0>button:last-of-type")).click();
		
		//Verify that we are able to open order summary from order tab 
		Thread.sleep(2000);
		jse.executeScript("window.scrollTo(0,0);");
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:first-of-type>div>div>div:nth-of-type(2)>div>div>ul>li:nth-of-type(3)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div:first-of-type>article>header>div:last-of-type>a")).click();
		Thread.sleep(2000);
		String Tierorderid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.content-container>div>section>div>div>div>p>span:first-of-type"))).getText().trim().replace("#","");
		System.out.println("Tier Order ID: " + Tierorderid);
		//Assert.assertEquals(Tierorderid, Orderidext2, "Order ID is not correct in order summary page");
		
		String TierorderAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.content-container>div>section>div:last-of-type>div:nth-of-type(3)>div>span:first-of-type"))).getText().trim();
		System.out.println("Tier Order Amount: " + TierorderAmount);
		//Assert.assertEquals(TierorderAmount, OrderAmount, "Order Amount is not correct in order summary page");
		
		//Verify that when we purchase tier and subscription product then it is getting display in subscription tab 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:first-of-type>div>div>div:nth-of-type(2)>div>div>ul>li:nth-of-type(5)")).click();
		Thread.sleep(2000);
		String SubProduct1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.main-subscription>div>div:first-of-type>div>div:last-of-type>p:first-of-type"))).getText().trim();
		SubProduct1 = SubProduct1.split("\\|")[0].trim();
		Assert.assertEquals(SubProduct1, Product_Name2, "Tier product is not visible in customer hub subscription tab");
		
		String SubProduct2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div:nth-of-type(2)>p:first-of-type"))).getText().trim();
		Assert.assertEquals(SubProduct2, Product_Name1, "Subscription product is not visible in customer hub subscription tab");
			
		//Verify that we are able to open subscription summary from order summary 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.main-subscription>div>div:first-of-type>div:nth-of-type(2)>a")).click();
		Thread.sleep(2000);
		String SubID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.subscription-details>div>article>div:nth-of-type(2)>div>div>p>span"))).getText().trim().replace("#","");
		String orderIdFromUrl = currentUrl11.substring(currentUrl11.lastIndexOf("/") + 1);
		Assert.assertEquals(SubID.toLowerCase(),orderIdFromUrl.toLowerCase(),"Tier ID is not correct in subscription summary page");
		
		String OrderAmount1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.subscription-details>div>article>div:nth-of-type(1)>div:last-of-type>p"))).getText().trim().replace("USD","").trim();;
		Assert.assertEquals(OrderAmount1, TierorderAmount, "Order Amount is not correct in subscription summary page");
		
		//verify that we are able to pause the subscription 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Pause Subscription\"]"))).click(); 
		Thread.sleep(3000);		
		String SubscriptionStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>section>div>article>div>div>div:nth-of-type(2)>p>span"))).getText().trim();
		Assert.assertEquals(SubscriptionStatus, "Paused", "Subscription status is not getting updated to paused after pausing the subscription");
		
		//verify that we are able to Resume the subscription 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Resume Subscription\"]"))).click(); 
		Thread.sleep(3000);		
		String SubscriptionStatus1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>section>div>article>div>div>div:nth-of-type(2)>p>span"))).getText().trim();
		Assert.assertEquals(SubscriptionStatus1, "Active", "Subscription status is not getting updated to resumed after pausing the subscription");
		
		//Verify that we are able to cancel the subscription 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.manage-sub-wrapper>div:nth-of-type(2)>div>div:first-of-type"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()=\"Cancel Subscription\"]"))).click();
		driver.findElement(By.xpath("//button[normalize-space()=\"Decline\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class=\"truncate\"]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("(//div[contains(text(),' Pricing & Affordability')])[1]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[normalize-space()=\"Select a reason\"]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[normalize-space()=\"Too expensive\"]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[normalize-space()=\"Next\"]")).click();	
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[normalize-space()=\"Confirm\"]")).click();
		Thread.sleep(3000);
		String SubscriptionStatus11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>section>div>article>div>div>div:nth-of-type(2)>p>span"))).getText().trim();
		Assert.assertTrue(SubscriptionStatus11.toLowerCase().contains("canceled"),"Subscription cancel status is not displayed");
		
		//verify that we are able to Restart  the subscription 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Manage Subscription\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()=\"Restart Subscription\"]"))).click(); 
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("section.subscription-details>div>header>a")).click();
		String SubscriptionStatus111 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.main-subscription>div>div:first-of-type>div:nth-of-type(2)>p>span"))).getText().trim();
		Assert.assertEquals(SubscriptionStatus111, "Active", "Subscription status is not getting updated to Active after restart the subscription");	
	}
	
	@Test(priority = 27)
	public void PaymentMethods() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		String Password = java.util.UUID.randomUUID().toString().replaceAll("-", "").substring(0,10) + "A1@";

		try {
			driver.navigate().to(Customer_Hub);
			driver.findElement(By.cssSelector("#header-right-side>div:nth-of-type(2)>div>button")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:first-of-type>div>div>div:nth-of-type(2)>div>div>ul>li:last-of-type")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#header-right-side>div:nth-of-type(2)>div>button")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[normalize-space()=\"Join us\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.name("first_name")).sendKeys(firstName);
			driver.findElement(By.name("last_name")).sendKeys(lastName);
			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("phone")).sendKeys(phone);
			driver.findElement(By.name("password")).sendKeys(Password);
			driver.findElement(By.xpath("//button[normalize-space()=\"Join\"]")).click();	
			System.out.println("Customer is already login in customer hub");
		}catch(Exception e) {
			driver.navigate().to(Customer_Hub);
			driver.findElement(By.cssSelector("#header-right-side>div:nth-of-type(2)>div>button")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[normalize-space()=\"Join us\"]")).click();
			Thread.sleep(3000);
			driver.findElement(By.name("first_name")).sendKeys(firstName);
			driver.findElement(By.name("last_name")).sendKeys(lastName);
			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("phone")).sendKeys(phone);
			driver.findElement(By.name("password")).sendKeys(Password);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[normalize-space()=\"Join\"]")).click();
			System.out.println("Customer is added from customer hub");
		}

		//vrify that we are able to add new card for all getaways (authorize, NMI, Stripe)
		Thread.sleep(4000);
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div:last-of-type>div>div:last-of-type>div>div>div>section>div>div>div:nth-of-type(3)")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();
		
		driver.navigate().to(Customer_Hub + "account/card-on-file");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='+ Add New Card']//parent::button")).click();
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

		WebElement cardHolder111 = wait.until(ExpectedConditions.elementToBeClickable(By.name("name_on_card")));
		cardHolder111.clear();
		cardHolder111.sendKeys(F_Name + " " + L_Name);
		driver.findElement(By.xpath("//button[normalize-space()=\"Save\"]")).click();	
		
		String EXPDate = driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div>article:first-of-type>div:first-of-type>div>div:nth-of-type(2)>p>div>div>span:nth-of-type(2)")).getText().trim();
		Assert.assertEquals(EXPDate, "4242", "Card No is not correct in card on file section");
		
		String cvvno = driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div>article:first-of-type>div:first-of-type>div>div:nth-of-type(2)>p>div>div:nth-of-type(2)>span")).getText().trim();
		Assert.assertEquals(cvvno, EXP, "Card Expiration date is not correct in card on file section");
		
		String cardname = driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div>article:first-of-type>div:first-of-type>div>div:nth-of-type(2)>p>div:nth-of-type(2)>div")).getText().trim();
		Assert.assertEquals(cardname, F_Name + " " + L_Name, "Card Name is not correct in card on file section");
		System.out.println("New card is added successfully in card on file section");
		
		Thread.sleep(4000);
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div:last-of-type>div>div:last-of-type>div>div>div>section>div>div>div:first-of-type")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();
		
		driver.navigate().to(Customer_Hub + "account/card-on-file");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='+ Add New Card']//parent::button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.name("name_on_card")).sendKeys(F_Name + " " + L_Name);
		driver.findElement(By.name("card_number")).sendKeys(Card_No);
		driver.findElement(By.name("expiry_month")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("div.overflow-auto:nth-of-type(2)>div:first-of-type")).click();
		driver.findElement(By.name("expiry_year")).click();
		driver.findElement(By.cssSelector("div.overflow-auto:nth-of-type(2)>div:nth-of-type(6)")).click();
		driver.findElement(By.name("cvv")).sendKeys(CVV);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Save\"]")).click();	
		
		//verify that we are able to delete card
		Thread.sleep(3000);
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div>article>div:last-of-type>div>button"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(@id,'headlessui-popover-panel')]//div//div//p[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Delete\"]")).click();
		
		Thread.sleep(2000);
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div:last-of-type>div>div:last-of-type>div>div>div>section>div>div>div:nth-of-type(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();
		Thread.sleep(4000);
		
		driver.navigate().to(Customer_Hub + "account/card-on-file");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='+ Add New Card']//parent::button")).click();
		Thread.sleep(2000);
		
		WebElement CardName = driver.findElement(By.xpath("//input[@placeholder=\"Card Holder Name\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", CardName);
		Thread.sleep(1000);
		CardName.sendKeys(F_Name+" "+L_Name);
		driver.switchTo().frame("CollectJSInlineccnumber");
		driver.findElement(By.id("ccnumber")).sendKeys(Card_No);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlinecvv");
		driver.findElement(By.id("cvv")).sendKeys(CVV);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlineccexp");
		driver.findElement(By.id("ccexp")).sendKeys("12/2044");
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Save\"]")).click();		
		
		Thread.sleep(3000);
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div>article>div:last-of-type>div>button"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(@id,'headlessui-popover-panel')]//div//div//p[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Delete\"]")).click();
		Thread.sleep(4000);
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div:last-of-type>div>div:last-of-type>div>div>div>section>div>div>div:nth-of-type(3)")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Save\"]")).click();
				
		//verify that we are able to edit card 
		driver.navigate().to(Customer_Hub + "account/card-on-file");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div>article>div:last-of-type>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(@id,'headlessui-popover-panel')]//div//div//p[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[aria-controls='monthDropdown']")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("#monthDropdowncard>button:first-of-type")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button[aria-controls='yearDropdown']")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("#monthDropdowncard>button:nth-of-type(3)")).click();
		Thread.sleep(500);
		driver.findElement(By.name("cvv")).sendKeys("789");
		driver.findElement(By.xpath("//button[normalize-space()=\"Save\"]")).click();
		
		Thread.sleep(2000);
		String expdateupdated = driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div>article:first-of-type>div:first-of-type>div>div:nth-of-type(2)>p>div>div:nth-of-type(2)>span")).getText().trim();
		Assert.assertEquals(expdateupdated, "01/28", "Card Expiration date is not updated in card on file section");
		
		//Verify that we are able to set backup card
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div>article>div:last-of-type>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(@id,'headlessui-popover-panel')]//div//div//p[3]")).click();
		Thread.sleep(1000);
		WebElement backupIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='marked_backup']")));
		Assert.assertTrue(backupIcon.isDisplayed(), "Backup card icon is not displayed after marking backup card");
		System.out.println("Backup card is marked successfully");
		
		//verify that we are able to remove backup card
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>div>div>article>div:last-of-type>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(@id,'headlessui-popover-panel')]//div//div//p[3]")).click();
		Thread.sleep(1000);
		Assert.assertTrue(backupIcon.isDisplayed() == false, "Backup card icon is still displayed after unmarking backup card");
		System.out.println("Backup card is removed successfully");		
	}
		
		
		
		
		
	
		
}
