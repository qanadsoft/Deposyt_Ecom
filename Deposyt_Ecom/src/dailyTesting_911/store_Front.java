package dailyTesting_911;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class store_Front extends Data {
	
	java.util.Random r = new java.util.Random();

	String Price = "2", Value = "1",susbcriptionPrice = "3",Fileone = "file1.jpg", Filetwo = "file2.png", Attachment = "Jira_Guide.pdf",
			Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
			SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),Country_Add = "Boardman, Oregon, 97818",
			Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(), ContactPhone1 = "(775) 986-5200",
			F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", country = "United States", ContactPhone2 = "(539) 321-3502",
			Account_Holder_Name = "DeposytCert", Routing_Number = "490000018", Account_Number = "000123456789", EXP = "12/44", CVV = "123", Card_No = "4242424242424242",
			chars = "abcdefghijklmnopqrstuvwxyz", GiftCard = "10",	    
			firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26)),
			lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26)),
			email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com",
			phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
	
	@Test(priority = 1)
	public void Store_Front() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		String Store_Name = "Smart Online Store " + java.util.UUID.randomUUID().toString().substring(0,2);
		String Store_Name_Updated = "Ebiz Online Store " + java.util.UUID.randomUUID().toString().substring(0,2);
		
		try {
			driver.navigate().to(settings);
			Thread.sleep(7000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 	
			Thread.sleep(3000);
			WebElement feeOption = driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[1]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'});", feeOption);
			feeOption.click();
			Thread.sleep(2000);	
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();	
		}catch(Exception e) {
			System.out.println("ACH_Payment is enabled for the store");
		}
						
		//Verify that the storefront URL is fetched from the Settings module and store name and url  is correct 
		driver.navigate().to(settings + "/details");
		Thread.sleep(15000);
		WebElement storeName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder=\"Store name\"]")));
		Thread.sleep(2000);
		storeName.clear();
		Thread.sleep(2000);
		storeName.sendKeys(Store_Name);		
		String StoreUrl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("base_url"))).getAttribute("value").trim();		
		String expectedUrl = "https://" + StoreUrl + ".app.deposyt.com/";
		System.out.println("Store URL: " + expectedUrl);
		
		//Verify that the logo is displayed correctly when the square logo is selected, and also when the rectangular logo is selected.
		driver.findElement(By.id("squareLogo")).click();
		driver.findElement(By.cssSelector("div.setting-ui-logo-custom-class>div>label:first-of-type>div>div>p>div>button:first-of-type")).click();
		Thread.sleep(2000);	
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(Media_Path + Fileone);				
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//span[normalize-space()=\"Crop\"]//parent::button")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Done\"]")).click();
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//span[normalize-space()=\"Publish\"]//parent::button")).click();
			
		String parentWindow = driver.getWindowHandle();
		WebElement url = driver.findElement(By.cssSelector("a[rel='noopener noreferrer']:first-of-type"));
		String storelink = url.getText();
		url.click();
		Thread.sleep(5000);
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}
		Thread.sleep(3000);		
		String storeNameOnUI = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//nav[contains(@class,'text-ui-fg-subtle')]//div)[8]//div//h1)[1]"))).getText().trim();
		Assert.assertEquals(storeNameOnUI, Store_Name, "Store name is not correct on store front");
		System.out.println("Store name is correct on store front page");
		
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl);
		Assert.assertTrue(currentUrl.contains(storelink), "Store URL is not correct");
		System.out.println("Store URL is correct and navigated to store front successfully");
		
		WebElement logo = driver.findElement(By.xpath("(//img[@alt='Logo'])[1]"));
		String width = logo.getAttribute("width");
		String height = logo.getAttribute("height");
		Assert.assertEquals(width, "50", "Logo width is incorrect");
		Assert.assertEquals(height, "50", "Logo height is incorrect");
		System.out.println("Square logo is displayed correctly.");	
		driver.close();
		driver.switchTo().window(parentWindow);
		
		driver.findElement(By.id("rectangleLogo")).click();
		driver.findElements(By.cssSelector("div.setting-ui-logo-custom-class>div>label:first-of-type>div>div>p>div>button:first-of-type")).get(1).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(Media_Path + Filetwo);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Crop\"]//parent::button")).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Done\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Publish\"]//parent::button	")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[rel='noopener noreferrer']:first-of-type")).click();
		Thread.sleep(5000);
		
		Set<String> allWindowss = driver.getWindowHandles();
		for (String window : allWindowss) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}
		
		WebElement logo1 = driver.findElement(By.xpath("(//img[@alt='Logo'])[1]"));
		String width1 = logo1.getAttribute("width");
		String height1 = logo1.getAttribute("height");
		Assert.assertEquals(width1, "150", "Logo width is incorrect");
		Assert.assertEquals(height1, "50", "Logo height is incorrect");
		System.out.println("Rectangle logo is displayed correctly.");		
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//Change the store name in Settings module and verify that the updated name is displayed correct on the store header and footer 
		WebElement storeName1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder=\"Store name\"]")));
		Thread.sleep(100);
		storeName1.clear();
		Thread.sleep(500);
		storeName1.sendKeys(Store_Name_Updated);	
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Publish\"]//parent::button	")).click();
		
		//Verify that after changing store Url, new url is working for existing store 	
		WebElement url1 = driver.findElement(By.cssSelector("a[rel='noopener noreferrer']:first-of-type"));
		String storelink1 = url1.getText();
		url1.click();
		
		Set<String> allWindows1 = driver.getWindowHandles();
		for (String window : allWindows1) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}
		
		String currentUrl1 = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentUrl1);
		Assert.assertTrue(currentUrl1.contains(storelink1), "Store URL is not correct");
		System.out.println("Store URL is Updated and navigated to store front successfully");
		
		Thread.sleep(3000);				
		String storeNameheader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//nav[contains(@class,'text-ui-fg-subtle')]//div)[8]//div//h1)[1]"))).getText().trim();
		Assert.assertEquals(storeNameheader, Store_Name_Updated, "Store name is not correct on store front header section");
		
		String storeNamefooter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.text-ui-fg-subtle"))).getText().trim();
		Assert.assertEquals(storeNamefooter, Store_Name_Updated, "Store name is not correct on store front footer section");
		System.out.println("Store name is correct on store front header and footer page");
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//Verify that the banner heading, subheading, and description are updated successfully and displayed correctly on the store.
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Banner Settings']")));		
		WebElement bannerHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("heading")));
		bannerHeading.clear();
		bannerHeading.sendKeys("welcome to our store");
		
		WebElement bannerSubHeading = driver.findElement(By.name("sub_heading"));
		bannerSubHeading.clear();
		bannerSubHeading.sendKeys("best products available here");
		
		WebElement bannerDescription = driver.findElement(By.name("banner_description"));
		bannerDescription.clear();
		bannerDescription.sendKeys("explore our wide range of products and enjoy shopping with us!");		
		Thread.sleep(2000);	
		
		//Verify that the store email, phone number, and address in the footer are displayed/hidden correctly when the toggle is turned ON/OFF.
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Contact Info']")));
		
		Thread.sleep(5000);
		WebElement toggleButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@value,'on')])[2]")));
		if (toggleButton1.getAttribute("data-state").equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton1);
		}
		/*Thread.sleep(10000);	
		WebElement toggleButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@value,'on')])[3]")));
		if (!toggleButton2.getAttribute("data-state").equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton2);
		}
		Thread.sleep(10000);	
		WebElement toggleButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@value,'on')])[4]")));
		if (!toggleButton3.getAttribute("data-state").equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton3);
		}*/
		
		Thread.sleep(2000);
		jse.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("a[rel='noopener noreferrer']:first-of-type")));		
		
		Set<String> allWindows2 = driver.getWindowHandles();
		for (String window : allWindows2) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		
		Thread.sleep(5000);
		String bannerHeadingOnUI = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.home_txt>p"))).getText().trim().toLowerCase();
		Assert.assertEquals(bannerHeadingOnUI, "welcome to our store", "Banner heading is not correct on store front");
		
		String bannerSubHeadingOnUI = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.home_txt>h2"))).getText().trim().toLowerCase();
		Assert.assertEquals(bannerSubHeadingOnUI, "best products available here", "Banner sub heading is not correct on store front");
		
		String bannerDescriptionOnUI = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.home_txt>div>p"))).getText().trim().toLowerCase();
		Assert.assertEquals(bannerDescriptionOnUI, "explore our wide range of products and enjoy shopping with us!", "Banner description is not correct on store front");		
		System.out.println("Banner heading, subheading and description are correct on store front page");
		
		WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer.border-ui-border-base>div>div>div>div:nth-of-type(2)>div>div:first-of-type>p")));
		String contactInfoEmail = emailElement.getText();
		Assert.assertTrue(emailElement.isDisplayed(), "Email is not displayed in footer");
		System.out.println("Email displayed: " + contactInfoEmail);

		/*WebElement mobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer.border-ui-border-base>div>div>div>div:nth-of-type(2)>div>div:nth-of-type(2)>p")));
		String contactInfoMobile = mobileElement.getText();
		Assert.assertTrue(mobileElement.isDisplayed(), "Mobile number is not displayed in footer");
		System.out.println("Mobile displayed: " + contactInfoMobile);

		WebElement addressElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer.border-ui-border-base>div>div>div>div:nth-of-type(2)>div>div:nth-of-type(3)>p")));
		String contactInfoAddress = addressElement.getText();
		Assert.assertTrue(addressElement.isDisplayed(), "Address is not displayed in footer");
		System.out.println("Address displayed: " + contactInfoAddress);*/
		
		driver.close();
		driver.switchTo().window(parentWindow);		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Contact Info']")));

		Thread.sleep(5000);
		WebElement toggleButton11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@value,'on')])[2]")));
		if (toggleButton11.getAttribute("data-state").equals("checked")) {
			jse.executeScript("arguments[0].click();", toggleButton11);
		}

		/*WebElement toggleButton22 =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@value,'on')])[3]")));
		if (!toggleButton22.getAttribute("data-state").equals("checked")) {
			toggleButton22.click();
		}

		WebElement toggleButton33 =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@value,'on')])[4]")));
		if (!toggleButton33.getAttribute("data-state").equals("checked")) {
			toggleButton33.click();
		}*/
		
		Thread.sleep(2000);
		jse.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("a[rel='noopener noreferrer']:first-of-type")));
		Set<String> allWindows3 = driver.getWindowHandles();
		for (String window : allWindows3) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		
		Thread.sleep(5000);
		Assert.assertFalse(contactInfoEmail.isEmpty(), "Email text is empty");
		/*Assert.assertTrue(contactInfoMobile.isEmpty(), "Mobile text is empty");
		Assert.assertTrue(contactInfoAddress.isEmpty(), "Address text is empty");*/
		
		//Verify that products are listed in ascending/descending order correctly when their order is configured accordingly.
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SHOP NOW']"))).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//label[normalize-space()='Price: Low -> High']")).click();
		Thread.sleep(5000);

		List<WebElement> priceElements = driver.findElements(By.cssSelector("div[data-testid='product-wrapper']>div:nth-of-type(2)>div"));
		List<Double> actualPrices = new ArrayList<>();

		for (WebElement price : priceElements) {
		    String priceText = price.getText().replaceAll("[^0-9.]", ""); 
		    actualPrices.add(Double.parseDouble(priceText));
		}
		System.out.println("Prices from UI: " + actualPrices);

		List<Double> sortedPrices = new ArrayList<>(actualPrices);
		Collections.sort(sortedPrices);
		System.out.println("Sorted Prices: " + sortedPrices);
		Assert.assertEquals(actualPrices, sortedPrices, "Prices are NOT sorted in ascending order");
		System.out.println("Products are correctly sorted in ascending order.\n");
		
		driver.findElement(By.xpath("//label[normalize-space()=\"Price: High -> Low\"]")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[data-testid='product-wrapper']>div:nth-of-type(2)>div")));

		List<WebElement> priceElements1 = driver.findElements(By.cssSelector("div[data-testid='product-wrapper']>div:nth-of-type(2)>div"));
		List<Double> actualPrices1 = new ArrayList<>();
		
		for (WebElement price : priceElements1) {
		    String priceText = price.getText().replaceAll("[^0-9.]", "");
		    actualPrices1.add(Double.parseDouble(priceText));
		}

		System.out.println("Prices from UI: " + actualPrices1);
		
		List<Double> sortedPrices1 = new ArrayList<>(actualPrices1);
		Collections.sort(sortedPrices1, Collections.reverseOrder());
		System.out.println("Sorted Prices (Expected Desc): " + sortedPrices1);
		Assert.assertEquals(actualPrices1, sortedPrices1, "Prices are NOT sorted in descending order");
		System.out.println("Products are correctly sorted in descending order.");
		
		//Verify that cash order is placed successfullly and payment method displayed in order table 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("p[data-testid='product-title']:first-of-type")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Add to Cart\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#header-right-side>div:last-of-type>div>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Go to checkout\"]")).click();
		
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

		driver.findElement(By.xpath("//input[@value=\"cash\"]")).click();//click on cash payment
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@role=\"combobox\"]")).click();//click on mode drop down
		driver.findElement(By.id("CashPaymentMethodDropdown")).click();//select cash 
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID);
			
		driver.navigate().to(Orders);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>span")).click();
		Thread.sleep(5000);
		String currentUrl11 = driver.getCurrentUrl().toLowerCase();	
		System.out.println("Current URL : " + currentUrl11);
		Assert.assertTrue(currentUrl11.contains(PlacedOrderID), "Order ID not found in URL");
	}
	
	@Test(priority = 2)
	public void ACH_order() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		//Verify that a flat order (No tax , no dual pricing) for a one-time purchase can be placed using the search functionality, and confirm that the order is listed in the Order table.
		driver.navigate().to(Products);
		Thread.sleep(10000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
		Thread.sleep(3000);
		WebElement toggleBtn = driver.findElement(By.xpath("//div[normalize-space()=\"Collect Tax on Inventory Products\"]//button[contains(@value,\"on\")]"));
		if (toggleBtn.getAttribute("data-state").equals("checked")) {
			   toggleBtn.click();
		    driver.findElement(By.cssSelector("div.custom-setting-region-tab>div>header>div>button:nth-of-type(2)")).click();
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
		}
		catch(Exception e) {
			System.out.println("ACH_Payment is enabled for the store");
		}
		
		driver.navigate().to(settings + "/details");
		Thread.sleep(15000);		
		String parentWindow = driver.getWindowHandle();
		Thread.sleep(2000);
		jse.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("a[rel='noopener noreferrer']:first-of-type")));		
		
		Set<String> allWindows2 = driver.getWindowHandles();
		for (String window : allWindows2) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SHOP NOW']"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#header-right-side>div:first-of-type>a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search products...\"]")).sendKeys("OneTime Product - 911");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("p[data-testid='search-result-title']:first-of-type")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Add to Cart\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#header-right-side>div:last-of-type>div>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Go to checkout\"]")).click();
		
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
		
		driver.findElement(By.xpath("//input[@value=\"ach-cash\"]")).click();//click on ACH cash payment
		Thread.sleep(2000);
		driver.findElement(By.id("achAccountHolderName")).sendKeys(Account_Holder_Name);
		driver.findElement(By.id("achRoutingNumber")).sendKeys(Routing_Number);
		driver.findElement(By.id("achAccountNumber")).sendKeys(Account_Number);
		driver.findElement(By.xpath("(//button[@role='combobox'])[1]")).click();//click on mode drop down
		driver.findElement(By.cssSelector("#AchAccTypeDropdown>button")).click();//select checking
		driver.findElement(By.xpath("(//button[contains(@role,'combobox')])[2]")).click(); //click on account category drop down
		driver.findElement(By.cssSelector("#AchAccTypeDropdown>button")).click(); //select personal		
		
		driver.findElement(By.cssSelector("button[role='checkbox']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Authorize & Make Payment\")]//parent::button")).click();
		Thread.sleep(7000);
		String PlacedOrderID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID);
		
		driver.navigate().to(Orders);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>span")).click(); 
		Thread.sleep(5000);
		String currentUrl = driver.getCurrentUrl().toLowerCase();	
		System.out.println("Current URL : " + currentUrl);
		Assert.assertTrue(currentUrl.contains(PlacedOrderID), "Order ID not found in URL");	
	}
	
	@Test(priority = 3)
	public void Onetimeorder_Storefront() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		//Verify that an order placed with gift card + dual pricing + tax (one-time purchase) is listed in the Recent Orders tab in the Sales module.
		driver.navigate().to(Gift_Cards);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='New Gift Card'])[3]"))).click(); // click on New Gift Card button
		driver.findElement(By.xpath("(//div[@id='region.region_id'])[1]")).click(); //click on region drop down
		driver.findElement(By.xpath("(((//span[text()='default'])[2]//parent::span)[1]//parent::span//parent::div//parent::div//parent::div)[1]")).click(); //Select Default as region
		driver.findElement(By.xpath("(//input[@placeholder='-'])[1]")).sendKeys(GiftCard);
		driver.findElement(By.name("receiver.email")).sendKeys(WMLogin);
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //click on create gift card button
		Thread.sleep(5000);
		String GiftCardID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.customer-table-second-class>table>tbody>tr>td>p:first-of-type"))).getText().trim();
		System.out.println("Created Gift Card ID: " + GiftCardID);
		
		driver.navigate().to(Products);
		Thread.sleep(10000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();//Click on settings
		Thread.sleep(3000);
		WebElement toggleBtn = driver.findElement(By.xpath("//div[normalize-space()=\"Collect Tax on Inventory Products\"]//button[contains(@value,\"on\")]"));
		if (toggleBtn.getAttribute("data-state").equals("unchecked")) {
			   toggleBtn.click();
		    driver.findElement(By.cssSelector("div.custom-setting-region-tab>div>header>div>button:nth-of-type(2)")).click();
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
		}
		catch(Exception e) {
			System.out.println("ACH_Payment is enabled for the store");
		}
		
		driver.navigate().to(settings + "/details");
		Thread.sleep(15000);		
		String parentWindow = driver.getWindowHandle();
		Thread.sleep(2000);
		jse.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("a[rel='noopener noreferrer']:first-of-type")));		
		
		Set<String> allWindows2 = driver.getWindowHandles();
		for (String window : allWindows2) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		
		//Dual price ON then card price/cash price on the store page and Add to cart 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SHOP NOW']"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#header-right-side>div:first-of-type>a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search products...\"]")).sendKeys("OneTime Product - 911");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("p[data-testid='search-result-title']:first-of-type")).click();
		Thread.sleep(3000);	
		String cashPrice = driver.findElement(By.cssSelector("span[data-testid='product-price']>div>div:first-of-type>p")).getText().trim();
		Assert.assertTrue(cashPrice.contains("Cash Price:"), "Cash Price label is not displayed correctly");
		String cardPrice = driver.findElement(By.cssSelector("span[data-testid='product-price']>div>div:nth-of-type(2)>p")).getText().trim();
		Assert.assertTrue(cardPrice.contains("Card Price:"), "Cash Price label is not displayed correctly");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Add to Cart\"]")).click();	
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#header-right-side>div:last-of-type>div>button")).click();	
		Thread.sleep(1000);		
		
		String CardPrice = driver.findElement(By.xpath("//span[normalize-space()=\"Card Total\"]")).getText().trim();
		Assert.assertTrue(CardPrice.contains("Card Total"), "Card Total label is not displayed correctly in checkout");
		
		String CashPrice = driver.findElement(By.xpath("//span[normalize-space()=\"Cash Total\"]")).getText().trim();
		Assert.assertTrue(CashPrice.contains("Cash Total"), "Cash Total label is not displayed	correctly in checkout");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()=\"Go to checkout\"]")).click();		
		
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
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID);
		
		driver.navigate().to(Sales);
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div>section>div:nth-of-type(3)>div:nth-of-type(2)>button")).click();//Click on recent order popup eye button
		Thread.sleep(2000);
		String recentorderdisplayed = driver.findElement(By.cssSelector("div[aria-label='Recent Orders Drawer'] table tbody tr:first-child td a div div a")).getText().trim();
		System.out.println("Recent order displayed in recent order popup: " + recentorderdisplayed);
		Assert.assertEquals(recentorderdisplayed, email, "Placed order is not displayed in recent order popup");
		
		driver.findElement(By.cssSelector("div[aria-label='Recent Orders Drawer'] table tbody tr:first-child td a")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("orders") && driver.getCurrentUrl().toLowerCase().contains(PlacedOrderID.toLowerCase()),"URL does not contain 'orders' or Order ID. Current URL");	
	}
}