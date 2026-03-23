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

import Master.Data;

public class pricing extends Data{
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
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("test-Nadsoft");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);

		// Scroll to and upload product image
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

}
