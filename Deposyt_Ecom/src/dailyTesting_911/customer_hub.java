package dailyTesting_911;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class customer_hub extends Data {
	
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
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("test-Nadsoft");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

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
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("test-Nadsoft");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage1 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage1);		
		Thread.sleep(3000);
		String[] files11 = {Media_Path + Fileone};
		String allFiles11 = String.join("\n", files11);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles11);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();
		
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
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("test-Nadsoft");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		WebElement uploadImage11 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage11);	
		Thread.sleep(3000);
		String[] files111 = {Media_Path + Fileone};
		String allFiles111 = String.join("\n", files111);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles111);
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
		Thread.sleep(2000);
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

		//driver.findElement(By.xpath("(//button[@role='checkbox'])[3]")).click();
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
	
	@Test(priority = 26)
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
		
		Thread.sleep(5000);
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
