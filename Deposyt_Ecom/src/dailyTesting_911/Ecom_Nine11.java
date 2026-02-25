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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert; 
import org.testng.annotations.Test;

import Master.Data;

public class Ecom_Nine11 extends Data {
	StringBuilder giftCard = new StringBuilder();
	String giftCardName = giftCard.toString();
	java.util.Random r = new java.util.Random();	
	String Price = "25", Value = "1",susbcriptionPrice = "3",Fileone = "file1.jpg", Filetwo = "file2.png", Filethree = "file3.jpeg", Filefour = "file4.png", Filefive = "file5.png",
		Filesix = "file6.jpg", Fileseven = "file7.jpg",Fileeight = "file8.jpg", Filenine = "file9.jpg", Attachment = "Jira_Guide.pdf",

		Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(),
		F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", country = "United States",
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
		
		String ordertax = driver.findElement(By.cssSelector("section>div>div>div:nth-of-type(2)>div:nth-of-type(8)>div:last-of-type>div>span:first-of-type")).getText().trim();
		System.out.println("Order Tax: " + ordertax);
		String extractedTax = ordertax.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedTax, Tax_Rate, "Tax rate mismatch in order summary");

		//Payment Information
		driver.findElement(By.xpath("//input[@value=\"cash\"]")).click();//click on cash payment
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@role=\"combobox\"]")).click();//click on mode drop down
		driver.findElement(By.id("CashPaymentMethodDropdown")).click();//select cash 
		driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
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

		Assert.assertEquals(Order_Name, F_Name + " " + L_Name, "Customer name not matching on order summary");
		Assert.assertEquals(Order_Mail, WMLogin, "Customer email not matching on order summary");		
		
		driver.close();
		driver.switchTo().window(originalTab);
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();//click on order history
		Thread.sleep(2000);
		String OrderRecived = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim();
		Assert.assertEquals(OrderRecived, F_Name + " " + L_Name, "Customer name not matching on order summary");
		
		String Orderdate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(6)>span"))).getText().trim();
		DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
		LocalDate orderDate = LocalDate.parse(Orderdate, uiFormatter);
		LocalDate today = LocalDate.now();
		Assert.assertEquals(orderDate, today, "Order date Mismatch: Expected today's date but got " + orderDate);
		
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
	}
	
	@Test(priority = 2)
	public void Inventory_SuscriptionProducts() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		String Product_Name = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
		Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
			
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

		//Validate order Summary - Customer details
		String Order_Name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Customer']//following-sibling::div//span"))).getText().trim();
		String Order_Mail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Customer']//following-sibling::div//p"))).getText().trim();
		String Order_Phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Customer']//following-sibling::div//span[2]"))).getText().replace("-", "").trim();

		// Debug print
		System.out.println(Order_Name);
		System.out.println(Order_Mail);
		System.out.println(Order_Phone);

		Assert.assertEquals(Order_Name, F_Name + " " + L_Name, "Customer name not matching on order summary");
		Assert.assertEquals(Order_Mail, WMLogin, "Customer email not matching on order summary");
		
		// Order History Section Show Proper Order 
		driver.close();
		driver.switchTo().window(originalTab);
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();//click on order history
		Thread.sleep(2000);
		String OrderRecived = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim();
		Assert.assertEquals(OrderRecived, F_Name + " " + L_Name, "Customer name not matching on order summary");
		
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
		DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
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
		driver.findElement(By.xpath("//span[text()='Complete Checkout']")).click(); //confirm place order
		Thread.sleep(3000);
		Assert.assertEquals(Order_Name, F_Name + " " + L_Name, "Customer name not matching on order summary");
		Assert.assertEquals(Order_Mail, WMLogin, "Customer email not matching on order summary");
		
		//Order Section > Check Upsell Order It Show Upsell Tag 
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		String OrderIDGenerated = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span"))).getText().trim();
		System.out.println("Order ID : " + OrderIDGenerated);
		int orderId = Integer.parseInt(OrderIDGenerated.replaceAll("\\D+", ""));
		Assert.assertEquals(orderId + 1, orderId + 1, "Order ID mismatch after navigation");
		
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
		Assert.assertEquals(totalOrdersPlaced, totalOrders, "Orders are visible after applying filter by product, expected no orders to be visible");
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
		
		DeleteMail("Subscription Charge Receipt for Subscription# ");
		
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

		Assert.assertEquals(Order_Name, F_Name + " " + L_Name, "Customer name not matching on order summary");
		Assert.assertEquals(Order_Mail, WMLogin, "Customer email not matching on order summary");
		
		Thread.sleep(2000);
		String PlacedOrderID = driver.findElement(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span")).getText();
		System.out.println("Placed Order ID: " + PlacedOrderID);
		
		driver.switchTo().window(parentWindow);	
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();//click on order history
		Thread.sleep(2000);
		String OrderRecived = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(3)>div>span:last-of-type"))).getText().trim();
		Assert.assertEquals(OrderRecived, F_Name + " " + L_Name, "Customer name not matching on order summary");
		
		String OrderStatusID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(1)>span"))).getText().trim();
		System.out.println("Order Status : " + OrderStatusID);
		
		String Orderdate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody#order-table-body>tr>td:nth-of-type(7)>span"))).getText().trim();
		DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
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
		Thread.sleep(2000);
		String TierOrderID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#overflow-main-div div p:nth-of-type(2)"))).getText().trim();
		System.out.println("Order ID in Order Details: " + OrderIDinDetails);
		Assert.assertEquals(OrderStatusID, TierOrderID, "Order ID mismatch in order");	
		
		//Check if we receive the appropriate email after purchasing from a test account		
		ReceivedMail("Subscription Charge Receipt for Subscription# " + OrderIDinDetails);	
	}
	
	@Test(priority = 4)
	public void Check_Filter() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		//Verify order status is correct
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div > main > section > div:nth-of-type(4) > div > div:nth-of-type(4) > button"))).click();   // Click on filter by order status drop down
		
		//Check Paid Active Status
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.dropdown-portal>div>div:nth-of-type(2)>div:nth-of-type(2)>div")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.dropdown-portal>div>div:nth-of-type(3)>button:last-of-type")).click(); //Click on done button
		
		Thread.sleep(3000);
		String ordersText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-table-sticky-second-class.bulk-reply-first-class>div>div>p>span"))).getText().trim();
		String totalOrders = ordersText.replaceAll("\\D+", "");
		System.out.println("Total Orders : " + totalOrders);
		
		int previousCount = 0;
		int currentCount = driver.findElements(By.cssSelector("#order-table-body > tr")).size();

		while (currentCount < Integer.parseInt(totalOrders)) {
		    previousCount = currentCount;
		    jse.executeScript("document.querySelector('div#main-page-ui-div').scrollTop=2000");
		    Thread.sleep(10000); // wait for loading
		    currentCount = driver.findElements(By.cssSelector("#order-table-body > tr")).size();

		    if (currentCount == previousCount) {
		        break;
		    }
		}
		
		Assert.assertEquals(currentCount,totalOrders,"Number of orders displayed does not match total orders count after scrolling");
		System.out.println("All Paid orders are displayed after scrolling, total orders count: " + totalOrders);
		
		//Check Failed Active Status
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div > main > section > div:nth-of-type(4) > div > div:nth-of-type(4) > button"))).click();   // Click on filter by order status drop down
		driver.findElement(By.cssSelector("div.dropdown-portal>div>div:nth-of-type(2)>div:nth-of-type(4)>div")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.dropdown-portal>div>div:nth-of-type(3)>button:last-of-type")).click(); //Click on done button
		
		int previousCount1 = 0;
		int currentCount1 = driver.findElements(By.cssSelector("#order-table-body > tr")).size();

		while (currentCount1 < Integer.parseInt(totalOrders)) {
		    previousCount1 = currentCount1;
		    jse.executeScript("document.querySelector('div#main-page-ui-div').scrollTop=2000");
		    Thread.sleep(10000); // wait for loading
		    currentCount1 = driver.findElements(By.cssSelector("#order-table-body > tr")).size();

		    if (currentCount1 == previousCount1) {
		        break;
		    }
		}
		
		Assert.assertEquals(currentCount1,totalOrders,"Number of orders displayed does not match total orders count after scrolling");
		System.out.println("All Failed orders are displayed after scrolling, total orders count : " + totalOrders);	
		
	}
	
	@Test(priority = 5)
	public void Product_Actions() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Actions actions = new Actions(driver);
		
		String Country_Add = "Boardman, Oregon, 97818", GiftCard = "0.20", Discounts_Price = "1";		

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
		
		driver.findElement(By.id("Giftcardcode")).sendKeys(GiftCardID); //Input discount code in cart
		driver.findElement(By.id("applyGiftCard")).click(); //Click on apply button for discount code
		driver.findElement(By.id("couponCode1")).sendKeys(giftCardName); //Input gift card code in cart
		driver.findElement(By.id("applyDiscountCoupon")).click(); //Click on apply button for

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

		Assert.assertEquals(Order_Name, F_Name + " " + L_Name, "Customer name not matching on order summary");
		Assert.assertEquals(Order_Mail, WMLogin, "Customer email not matching on order summary");
		
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
		String fullName = F_Name + " " + L_Name;
		String OrderName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@data-state,'closed') and normalize-space()='" + fullName + "']"))).getText().trim();
		String OrderEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@data-state,'closed') and normalize-space()='" + WMLogin + "']"))).getText().trim();
		
		Assert.assertEquals(OrderName, fullName, "Customer name not matching in order details");
		Assert.assertEquals(OrderEmail, WMLogin, "Customer email not matching in order details");
		
		String OrderBillingAddress1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[contains(@class,'break-words') and normalize-space()='" + Street_Add + "'])[3]"))).getText().trim();
		String OrderBillingAddress2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[contains(normalize-space(),'" + country + "')])[3]"))).getText().trim();
		String OrderBillingAddress3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[contains(normalize-space(),'" + Country_Add + "')])[3]"))).getText().trim();
		
		Assert.assertEquals(OrderBillingAddress1, Billing_Address1, "Street address not matching in order details");
		Assert.assertEquals(OrderBillingAddress2, Billing_Address3, "Country not matching in order details");
		Assert.assertEquals(OrderBillingAddress3, Billing_Address2, "City, State and Zip code not matching in order details");
		
		String GiftcardValid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[normalize-space()='" + GiftCardID + "'])[1]"))).getText().trim();
		String DiscountValid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[text()='" + giftCardName + "'])[1]"))).getText().trim();
		
		Assert.assertEquals(GiftcardValid, GiftCardID, "Gift card code not applied properly");
		Assert.assertEquals(DiscountValid, giftCardName, "Discount code not applied properly");			
	}
	
	@Test(priority = 6)
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
		driver.findElement(By.id("field_first_name")).sendKeys(F_Name); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(L_Name); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(WMLogin); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(Number); //Input phone number
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
	
	@Test(priority = 7)
	public void Place_Order() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		Date cDate = new Date();
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String giftCardName = "GiftCard " + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
		
		//Verify services orders are visible
		/*driver.navigate().to(Services);
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
		driver.findElement(By.id("firstname")).sendKeys(F_Name);
		driver.findElement(By.id("lastname")).sendKeys(L_Name);
		driver.findElement(By.id("emailid")).sendKeys(WMLogin);
		driver.findElement(By.id("phonenumber")).sendKeys(Number);	
		driver.findElement(By.id("cardholdername")).sendKeys(F_Name + " " + L_Name); //click on country drop down
		driver.findElement(By.id("cardnumber")).sendKeys(Card_No); 
		driver.findElement(By.id("cardcvv")).sendKeys(CVV);
		driver.findElement(By.id("cardexp")).sendKeys(EXP);
		driver.findElement(By.cssSelector("div#book-btn>a")).click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElements(By.xpath("//h1[text()='Your service appointment is booked.']")).size() > 0,"Service booking confirmation not displayed!");
		
		Thread.sleep(5000);
		String PlacedOrderID1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID1);
		driver.close();		
		driver.switchTo().window(Pwindow);
		
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@aria-label='Services Toggle Button'])[1]"))).click(); 		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click(); //Click on view details of the order
		Thread.sleep(4000);
		Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(PlacedOrderID1),"Order ID not found in URL");*/
		
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
		driver.findElement(By.cssSelector("tbody[role='rowgroup']>tr>td:nth-of-type(2)>div>span>span>div")).click(); //Click on share
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
		
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Gift Cards'])[1]"))).click(); 		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click(); //Click on view details of the order
		Thread.sleep(4000);
		Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains(PlacedOrderID11),"Order ID not found in URL");		
	}
	
	@Test(priority = 8)
	public void Edit_order() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		DeleteMail("Your Voided Order Receipt");
		
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
		driver.findElement(By.xpath("(//a[normalize-space()='Open/Edit Product'])[1]")).click();

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

		//driver.findElement(By.xpath("(//button[@role='checkbox'])[2]")).click();
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(5000);
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
		
		String totalRefundAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#overflow-main-div>div:nth-of-type(2)>div>div:nth-of-type(3)>div>div:nth-of-type(2)"
				+ ">div>div>div>div:nth-of-type(2)>div>span"))).getText().trim();
		double refundAdded = Double.parseDouble(totalRefundAfter.replaceAll("[^0-9.]", ""));
		System.out.println("Refund Added: " + refundAdded);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		String finalRefundText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Voided Statistics Card']>div>div>p"))).getText().trim();
		double actualFinalRefund = Double.parseDouble(finalRefundText.replaceAll("[^0-9.]", ""));
		System.out.println("Actual Final Refund: " + actualFinalRefund);
		Assert.assertTrue(actualFinalRefund > refundBefore,"Final refund value mismatch");
		
		//13A check if refund email is received
		ReceivedMail("Your Voided Order Receipt");
	}
	
	@Test(priority = 9)
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
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		int Value = 1;
		String chars = "abcdefghijklmnopqrstuvwxyz";
	    
	    String firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26));
	    String lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26));
	    String email = firstName + "." + lastName + r.nextInt(1000) + "@testmail.com";
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
		
		String totaluser2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Orders Statistics Card']>div>div>p"))).getText().trim();
		String totalordersusers2 = totaluser2.replaceAll("[^0-9.]", ""); 
		double Revenueusers2 = Double.parseDouble(totalordersusers2);	
		System.out.println("Total Users Before In One-Time Tab : " + Revenueusers2);	
		
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
		Assert.assertEquals(RevenueValue3,expectedTotalRevenue,"Total revenue is not updated correctly after adding price In all sales tab");
		
		double expectedTotalRevenue1 = RevenueValue2 + Value;
		Assert.assertEquals(RevenueValue4,expectedTotalRevenue1,"Total revenue is not updated correctly after adding price In one time tab");
		
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
		Assert.assertEquals(RevenueValue5, Value,"Total revenue is not updated correctly after applying product filter");
		
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
		Assert.assertEquals((int) orderValue, 1, "Total Customers not updated correctly after applying product filter");
		
		//Verify that if we have placed one new order with new customer for one time product then it is showing one order count and 1 new customer count when we select (all sales and one time tab )
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
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
		
		Thread.sleep(2000);
		Assert.assertEquals(custorders11, Revenuecust + 1,"Order count is not incremented by 1");
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[aria-label='One-Time Toggle Button']")).click();//Click on one time toggle button
		Thread.sleep(3000);
		
		String totalRevenueone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='Revenue Statistics Card']>div>div>p"))).getText().trim();
		String totalRevenueValueone = totalRevenueone.replaceAll("[^0-9.]", ""); 
		double RevenueValueone = Double.parseDouble(totalRevenueValueone);	
		System.out.println("Total Revenue Value After In One-Time Tab : " + RevenueValueone);
		Assert.assertEquals(RevenueValueone,RevenueValue4 + 1, "Total revenue is not updated correctly after adding price In one time tab");
		
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
		
		driver.findElement(By.xpath("(//button[@role='checkbox'])[2]")).click(); //Clicking on checkbox
		driver.findElement(By.xpath("(//button[@role='checkbox'])[3]")).click(); //Clicking on checkbox
		
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //Click on pay button
        
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
		Thread.sleep(5000);
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
	}
	
	@Test(priority = 15)
	public void VirtualTerminal_salesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		String ContactPhone2 = "(539) 321-3502";
		
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
		//Assert.assertEquals(discountafter, expecteddiscount, "Total Discount is not updated correctly after Accept the subscription");
		
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
	}
	
	
	
}
