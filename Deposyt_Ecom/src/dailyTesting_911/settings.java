package dailyTesting_911;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class settings extends Data {
	
	java.util.Random r = new java.util.Random();

	String Price = "2", Value = "1",susbcriptionPrice = "3",Fileone = "file1.jpg", Filetwo = "file2.png", Filethree = "file3.jpeg", Filefour = "file4.png", Filefive = "file5.png",
			Filesix = "file6.jpg", Fileseven = "file7.jpg",Fileeight = "file8.jpg", Filenine = "file9.jpg", Attachment = "Jira_Guide.pdf",

			Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
			SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),Country_Add = "Boardman, Oregon, 97818",
			Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(), ContactPhone1 = "(775) 986-5200",
			F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", country = "United States", ContactPhone2 = "(539) 321-3502",
			Account_Holder_Name = "DeposytCert", Routing_Number = "490000018", Account_Number = "000123456789", EXP = "12/44", CVV = "123", Card_No = "4242424242424242";

	String chars = "abcdefghijklmnopqrstuvwxyz",	    
			firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
			lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
			email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com",
			phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
	
	@Test(priority = 1)
	public void Settings() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		int code = 1000 + new Random().nextInt(9000); 
		
		String Product_Name1 = "Suscription Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),Payment_Mode = "PayPal", Region_Name = "Europe";
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
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name1);	
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
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name1);		
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

		//driver.findElement(By.xpath("//button[@role='checkbox']")).click();
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
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
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

		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("1");
		Thread.sleep(1000);

		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#result-item-0>a")).click();
		
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
		//driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
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
		//driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
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
		
		//driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
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
		
		/*List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[role='checkbox']")));
		for (WebElement checkbox : checkboxes) {
		    if (checkbox.isDisplayed() && checkbox.isEnabled()) {
		        checkbox.click();
		    }
		}*/
		
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

		//driver.findElement(By.xpath("//button[@role='checkbox']")).click(); //Clicking on checkbox				
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
	
	@Test(priority = 2)
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
		
		String orderconvenienceFee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(6)>div:nth-of-type(3)>div:nth-of-type(1)>span"))).getText().trim();
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

		//driver.findElement(By.xpath("//button[@role='checkbox']")).click();
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
		//Assert.assertEquals(Double.parseDouble(TotalOrderAmount.replace("$", "")), 11.00, "Total order amount mismatch in order summary");
		
		//Surcharge
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 
		
		Thread.sleep(2000);
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[3]")).click();
			String SurchargeFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter percentage\"]")).getAttribute("value").trim();
			System.out.println("Surcharge Fee In Settings : " + SurchargeFee);
			Thread.sleep(2000);
			driver.findElement(By.id("dialog-checkbox")).click();	
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();
			Thread.sleep(3000);
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
		
		String SurchargeFees = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(6)>div:nth-of-type(3)>div:nth-of-type(1)>span"))).getText().trim();
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

		//driver.findElement(By.xpath("//button[@role='checkbox']")).click();
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
		String ordersurgeFee1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#overflow-main-div>div:nth-of-type(2)>div>div:nth-of-type(3)>div>div:nth-of-type(2)>div>div:first-of-type>div:nth-of-type(5)>div>span:first-of-type"))).getText().trim();
		System.out.println("surge Fee In Order Details : " + ordersurgeFee1);
		String extractedsurgeFee1 = ordersurgeFee1.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedsurgeFee1.replaceAll("[^0-9]", "").replaceAll("00$", ""),ordersurgeFee1.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Convenience Fee mismatch in order summary");
		
		String TotalOrderAmount1 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(8)>div:nth-of-type(2)>span:first-of-type"))).getText().trim().replaceAll("[^0-9.]", "");
		System.out.println("Total Order Amount In Order Details : " + TotalOrderAmount1);
		//Assert.assertEquals(Double.parseDouble(TotalOrderAmount1.replace("$", "")), 1.03,"Total order amount mismatch in order summary");
		
		//Service Fee
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 
		
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[4]")).click();
			String ServiceFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter percentage\"]")).getAttribute("value").trim();
			System.out.println("Service Fee In Settings : " + ServiceFee);
			Thread.sleep(2000);
			driver.findElement(By.id("dialog-checkbox")).click();		
			Thread.sleep(3000);
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

		String ServiceFees = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(2)>div:nth-of-type(6)>div:nth-of-type(3)>div:nth-of-type(1)>span"))).getText().trim();
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

		//driver.findElement(By.xpath("//button[@role='checkbox']")).click();
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
		String orderserviceee1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#overflow-main-div>div:nth-of-type(2)>div>div:nth-of-type(3)>div>div:nth-of-type(2)>div>div:first-of-type>div:nth-of-type(5)>div>span:first-of-type"))).getText().trim();
		System.out.println("Service Fee In Order Details : " + orderserviceee1);
		String extractedserviceFee1 = orderserviceee1.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedserviceFee1.replaceAll("[^0-9]", "").replaceAll("00$", ""),orderserviceee1.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Convenience Fee mismatch in order summary");

		String TotalOrderAmount11 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(8)>div:nth-of-type(2)>span:first-of-type"))).getText().trim().replaceAll("[^0-9.]", "");
		System.out.println("Total Order Amount In Order Details : " + TotalOrderAmount11);
		//Assert.assertEquals(Double.parseDouble(TotalOrderAmount11.replace("$", "")), 1.14,"Total order amount mismatch in order summary");
		
		//Cash/ACH Discount (Cash Discount)
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 
		
		try {
			Thread.sleep(3000);
			WebElement feeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@name,'fee_option')])[5]")));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'});", feeOption);
			feeOption.click();
			Thread.sleep(2000);
			driver.findElement(By.id("displayStyleCashDiscount")).click();
			Thread.sleep(3000);
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
		String ordersdual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#overflow-main-div>div:nth-of-type(2)>div>div:nth-of-type(3)>div>div:nth-of-type(2)>div>div:first-of-type>div:nth-of-type(5)>div>span:first-of-type"))).getText().trim();
		System.out.println("Cash Discount Fee In Order Details : " + ordersdual);
		String extracteddualprice1 = ordersdual.replaceAll("[^0-9]", "");
		Assert.assertEquals(extracteddualprice1.replaceAll("[^0-9]", "").replaceAll("00$", ""),CashDiscountFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Convenience Fee mismatch in order summary");

		String TotalOrderAmounts1 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.order-details-section-class>div>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(8)>div:nth-of-type(2)>span:first-of-type"))).getText().trim();
		System.out.println("Total Order Amount In Order Details : " + TotalOrderAmounts1);
		//Assert.assertEquals(Double.parseDouble(TotalOrderAmounts1.replace("$", "")), 2.00,"Total order amount mismatch in order summary");
				
		//Cash/ACH Discount (dual pricet)
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 
		
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[5]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("displayStyleDualPricing")).click();
			Thread.sleep(3000);
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
		//Assert.assertEquals(Double.parseDouble(TotalOrderAmounts11.replace("$", "")), 2.08, "Total order amount mismatch in order summary");		
	}
		
	@Test(priority = 1)
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

		//driver.findElement(By.xpath("//button[@role='checkbox']")).click();
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

		//driver.findElement(By.xpath("//button[@role='checkbox']")).click();
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

}
