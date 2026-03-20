package dailyTesting_911;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Master.Data;

public class access_level extends Data {
	java.util.Random r = new java.util.Random();
	
	String it, username, adminID, userLoginNavigation, userID, secoundUserID, personalAccessPage,
	Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
	SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),Country_Add = "Boardman, Oregon, 97818",
	Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(), ContactPhone1 = "(775) 986-5200",
	Fileone = "file1.jpg", chars = "abcdefghijklmnopqrstuvwxyz", Value = "1",	
	firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
	lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
	email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com",phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000)), EXP = "12/44", CVV = "123", 
	Card_No = "4242424242424242",F_Name = "NineEleven", L_Name = "Contact", Street_Add = "Allen Court", country = "United States";
					
					
	@BeforeMethod
	@Parameters("loginTypeMainAccess")
	public void userNavigationMainAcess(@Optional("none") String loginTypeMainAcess) throws Exception {  
	    if (!loginTypeMainAcess.equalsIgnoreCase("none")) {
	        
	        driver.navigate().to(Dashboard);
	        Thread.sleep(2000);
	        adminID = driver.findElement(By.cssSelector("input#accountLoginID")).getAttribute("value");
	        System.out.println("Admin ID = " + adminID);
	        Thread.sleep(2000);
 
	        driver.navigate().to(AccessLevelPage);
	        Thread.sleep(3000);

	        it = driver.findElement(By.xpath("//div[@id='accesslevel_section']/div[@class='list-accesslevel'][6]/descendant::a[contains(@class,'btn-edit')]")).getAttribute("href");
	        String ID = driver.findElement(By.xpath("//div[@id='accesslevel_section']/div[@class='list-accesslevel'][6]/descendant::a[contains(@class,'btn-edit')]")).getAttribute("href").replace(url + "/index.php?m=usersmgmt&d=edit_access_level&id=", "").replaceAll("[^0-9]", "");
	        System.out.println(ID);

	        if (loginTypeMainAcess.equalsIgnoreCase("user")) { // For User role	            
	            driver.navigate().to(UserManagment);
	            Thread.sleep(4000);
	            userID = driver.findElement(By.cssSelector("table#cul_table>tbody>tr:nth-of-type(1)")).getAttribute("data-id").trim();
	            System.out.println("User ID - " + userID);
	            username = driver.findElement(By.cssSelector("tr.admintable_row:first-of-type")).getAttribute("data-access-title").trim();
	            System.out.println("User name - " + username);
	            secoundUserID = driver.findElement(By.cssSelector("table#cul_table>tbody>tr:nth-of-type(2)")).getAttribute("data-id").trim();
	            System.out.println("Secound User ID" + secoundUserID);
	            userLoginNavigation = driver.findElement(By.cssSelector("a[onclick^=\"admintable_loginmsgbox\"]")).getAttribute("onclick").replaceAll(".*'(index\\.php[^\']*)'.*", "$1");
	            System.out.println(userLoginNavigation);
	        } else if (loginTypeMainAcess.equalsIgnoreCase("partner")) { // For Partner role	            
	            driver.navigate().to(url + "index.php?m=usersmgmt&d=partnerlist");
	            Thread.sleep(4000);
	            userLoginNavigation = driver.findElement(By.cssSelector("a[onclick*='index.php?m=employeeregister&d=autologin']")).getAttribute("onclick").replaceAll(".*'(index\\.php[^\']*)'.*", "$1");
	            System.out.println("Partner ID - " + userLoginNavigation);
	        } else if (loginTypeMainAcess.equalsIgnoreCase("affiliate")) { // For Affiliate role	            
	            driver.navigate().to(url + "index.php?m=usersmgmt&d=sub-affilites-list");
	            Thread.sleep(4000);
	            userLoginNavigation = driver.findElement(By.cssSelector("a[onclick*='index.php?m=employeeregister&d=autologin']")).getAttribute("onclick").replaceAll(".*'(index\\.php[^\']*)'.*", "$1");
	            System.out.println("Affiliate ID - " + userLoginNavigation);
	        }

	        //Common Actions (Selecting the first check box and updating access level)
	        driver.findElement(By.cssSelector("input.usercheckbox+span")).click(); // select the first row
	        Thread.sleep(1000);
	        driver.findElement(By.xpath("(//a[@id = 'usertableoptions'])[1]")).click();  // Custom access levels
	        Thread.sleep(1000);
	        driver.findElement(By.cssSelector("div.dropdown-menu.usertableoptions>a+a")).click(); // Change access level
	        Thread.sleep(1000);
	        driver.findElement(By.id("assignUsersSelectAccess")).click();
	        Thread.sleep(1000);
	        driver.findElement(By.xpath("(//label[@data-value = '" + ID + "'])[2]")).click(); // Select User from dropdown
	        driver.findElement(By.cssSelector("button.btn-updateaccesslevel")).click(); // Update button
	        Thread.sleep(4000);
	        driver.navigate().to(it);
	        Thread.sleep(1000);
	        
	        DefaultAccess();
	    }
	}
	
	public void DefaultAccess() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver); 
		JavascriptExecutor jse = (JavascriptExecutor)driver;  
		 
		WebElement allAccess = driver.findElement(By.cssSelector("div.content-access"));
		action.moveToElement(allAccess).build().perform();
		Thread.sleep(500);
		 
		//For sales Module  
		WebElement accessLevelsModify = driver.findElement(By.id("section-sales"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", accessLevelsModify);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.ac_sales_grant_modify")).click(); 
		driver.findElement(By.cssSelector("button.ac_sales_external_notify_on")).click();
		
		//For orders Module
		WebElement customerModifyAccess = driver.findElement(By.id("section-orders"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", customerModifyAccess);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_orders_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_orders_all_on")).click();		
		
		//for products Module
		WebElement createContact = driver.findElement(By.id("section-products"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createContact);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_product_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_product_others_grant_modify")).click();
				
		//For invoices Module
		WebElement messagesModify = driver.findElement(By.id("section-invoices"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", messagesModify);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_invoices_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_invoices_create_on")).click();
		driver.findElement(By.cssSelector("button.ac_invoices_delete_on")).click();
		driver.findElement(By.cssSelector("button.ac_invoices_settings_grant_modify")).click();
				
		//For Discount Module
		WebElement triggersModify = driver.findElement(By.id("section-discounts"));
		jse.executeScript("arguments[0].scrollIntoView(true);", triggersModify);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_discount_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_discount_create_on")).click();
		driver.findElement(By.cssSelector("button.ac_giftcard_delete_on")).click();

		// For Gift Cards Module
		WebElement FormModifyAccess = driver.findElement(By.id("section-giftcards"));
		jse.executeScript("arguments[0].scrollIntoView(true);", FormModifyAccess);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.ac_giftcard_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_giftcard_create_on")).click();
		driver.findElement(By.cssSelector("button.ac_giftcard_delete_on")).click();
			    
		//For pricing Module
		WebElement listsModify = driver.findElement(By.id("section-pricing"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listsModify);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_pricing_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_pricing_create_on")).click();
		driver.findElement(By.cssSelector("button.ac_pricing_delete_on")).click();
		
		//For virtual terminal Module
		WebElement reportsModify = driver.findElement(By.id("section-virtual-terminal"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reportsModify);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_access_virtual_terminal_modify")).click();
		
		//for settings Module
		WebElement settingsModify = driver.findElement(By.id("section-store-settings"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", settingsModify);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_store_setting_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_store_payments_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_store_2fa_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_store_currencies_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_store_taxsettings_modify")).click();
		driver.findElement(By.cssSelector("button.ac_store_region_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_store_returnreason_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_store_dualpricing_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_store_subscriptionsetting_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_store_cardexpiry_grant_modify")).click();
		driver.findElement(By.cssSelector("button.ac_store_fulfillment_grant_modify")).click();				
	}
	
	@Test(priority = 1)
	public void NoneAccess() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		
		WebElement allAccess = driver.findElement(By.cssSelector("div.content-access"));
		action.moveToElement(allAccess).build().perform();
		Thread.sleep(500);
	     
		//For sales Module  
		WebElement salesOff = driver.findElement(By.id("section-sales"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", salesOff);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.ac_sales_grant_none")).click();
		
		//For orders Module
		WebElement ordersOff = driver.findElement(By.id("section-orders"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ordersOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_orders_grant_none")).click();
		
		//for products Module
		WebElement productsOff = driver.findElement(By.id("section-products"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_product_grant_none")).click();
		
		//For invoices Module
		WebElement invoicesOff = driver.findElement(By.id("section-invoices"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", invoicesOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_invoices_grant_none")).click();
		driver.findElement(By.cssSelector("button.ac_invoices_settings_grant_none")).click();
		
		//For virtual terminal Module
		WebElement virtualTerminalOff = driver.findElement(By.id("section-virtual-terminal"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", virtualTerminalOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_access_virtual_terminal_none")).click();
		
		//for settings Module
		WebElement settingsOff = driver.findElement(By.id("section-store-settings"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true	);", settingsOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_store_setting_grant_none")).click();
		driver.findElement(By.cssSelector("button.ac_store_payments_grant_none")).click();
		driver.findElement(By.cssSelector("button.ac_store_2fa_grant_none")).click();
		driver.findElement(By.cssSelector("button.ac_store_currencies_grant_none")).click();
		driver.findElement(By.cssSelector("button.ac_store_taxsettings_none")).click();
		driver.findElement(By.cssSelector("button.ac_store_region_grant_none")).click();
		driver.findElement(By.cssSelector("button.ac_store_returnreason_grant_none")).click();
		driver.findElement(By.cssSelector("button.ac_store_dualpricing_grant_none")).click();
		driver.findElement(By.cssSelector("button.ac_store_subscriptionsetting_grant_none")).click();
		driver.findElement(By.cssSelector("button.ac_store_cardexpiry_grant_none")).click();
		driver.findElement(By.cssSelector("button.ac_store_fulfillment_grant_none")).click();
		driver.findElement(By.cssSelector("button.saveaccesslevel")).click();
		Thread.sleep(4000);

		//login to user account
		driver.navigate().to(UserManagment);//user profile page 
		Thread.sleep(3000); 
		driver.findElement(By.cssSelector("input.usercheckbox+span")).click(); // select first user
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(11)>div>a")).click();
	    driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(11)>div>div>a:first-of-type")).click();
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector("form#formloginactionmodaldiv>div>button")).click();		
		Thread.sleep(2000);
		 
		//Check Sales module  in "None" condition
		try { 
			driver.findElement(By.xpath("//a[@title=\"Sales\"]")).click();
			Assert.assertTrue(false, "Test Failed: Sales Tab is Displayed.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Sales Tab is hidden.");
		} 	 
		
		/*driver.navigate().to(Sales);
		String SalesURL = driver.getCurrentUrl();
		Assert.assertEquals(SalesURL, Dashboard);*/
		
		//Orders module  in "None" condition
		try { 
			driver.findElement(By.xpath("//a[@title=\"Orders\"]")).click();
			Assert.assertTrue(false, "Test Failed: Orders Tab is Displayed.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Orders Tab is hidden.");
		}
		
		/*driver.navigate().to(Orders);
		String OrdersURL = driver.getCurrentUrl();
		Assert.assertEquals(OrdersURL, Dashboard);*/
		
		//Products module  in "None" condition
		try {
			driver.findElement(By.xpath("//a[@title=\"Products\"]")).click();
			Assert.assertTrue(false, "Test Failed: Products Tab is Displayed.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Products Tab is hidden.");
		}
		
		/*driver.navigate().to(Products);
		String ProductsURL = driver.getCurrentUrl();
		Assert.assertEquals(ProductsURL, Dashboard);*/
		
		//Invoices module  in "None" condition
		try {
			driver.findElement(By.xpath("//a[@title=\"Invoices\"]")).click();
			Assert.assertTrue(false, "Test Failed: Invoices Tab is Displayed.");
		} catch(Exception noSuchEelementException){
			System.out.println("Invoices Tab is hidden.");
		}

		/*driver.navigate().to(Invoices);
		String InvoicesURL = driver.getCurrentUrl();
		Assert.assertEquals(InvoicesURL, Dashboard);*/
		
		//Virtual Terminal  module  in "None" condition
		try {
			driver.findElement(By.xpath("//a[@title=\"Virtual Terminal\"]")).click();
			Assert.assertTrue(false, "Test Failed: Virtual Terminal Tab is Displayed.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Virtual Terminal Tab is hidden.");
		}
				
		/*driver.navigate().to(Virtual_Terminal);
		String Virtual_TerminalURL = driver.getCurrentUrl();
		Assert.assertEquals(Virtual_TerminalURL, Dashboard);*/
		
		//Store Settings module's tabs  in "None" condition
		try {
			driver.findElement(By.xpath("//a[@title=\"Store Settings\"]")).click();
			Assert.assertTrue(false, "Test Failed: Store Settings Tab is Displayed.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Store Settings Tab is hidden.");
		}
		
		/*driver.navigate().to(settings);
		String settingsURL = driver.getCurrentUrl();
		Assert.assertEquals(settingsURL, Dashboard);*/		
	}
	
	@Test(priority = 2)
	public void ViewAccess() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		
		WebElement allAccess = driver.findElement(By.cssSelector("div.content-access"));
		action.moveToElement(allAccess).build().perform();
		Thread.sleep(500);
		
		//for orders Module
		WebElement ordersOff = driver.findElement(By.id("section-orders"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ordersOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_orders_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_orders_all_on")).click();
		
		//for products Module
		WebElement productsOff = driver.findElement(By.id("section-products"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_product_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_product_others_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_product_create_on")).click();
		driver.findElement(By.cssSelector("button.ac_product_delete_on")).click();
				
		//for invoices Module
		WebElement invoicesOff = driver.findElement(By.id("section-invoices"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", invoicesOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_invoices_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_invoices_settings_grant_view")).click();
		
		//For virtual terminal Module
		WebElement virtualTerminalOff = driver.findElement(By.id("section-virtual-terminal"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", virtualTerminalOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_access_virtual_terminal_view")).click();
		
		//for settings Module
		WebElement settingsOff = driver.findElement(By.id("section-store-settings"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true	);", settingsOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_store_setting_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_store_payments_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_store_2fa_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_store_currencies_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_store_taxsettings_view")).click();
		driver.findElement(By.cssSelector("button.ac_store_region_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_store_returnreason_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_store_dualpricing_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_store_subscriptionsetting_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_store_cardexpiry_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_store_fulfillment_grant_view")).click();
		driver.findElement(By.cssSelector("button.saveaccesslevel")).click();
		Thread.sleep(4000);

		//login to user account
		driver.navigate().to(UserManagment);//user profile page 
		Thread.sleep(3000); 
		driver.findElement(By.cssSelector("input.usercheckbox+span")).click(); // select first user
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(11)>div>a")).click();
	    driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(11)>div>div>a:first-of-type")).click();
	    Thread.sleep(500);
	    driver.findElement(By.cssSelector("form#formloginactionmodaldiv>div>button")).click();		
		Thread.sleep(2000);
		
//		driver.navigate().to(Products);
//		Thread.sleep(5000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
//		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);	
//		driver.findElement(By.name("productDetails.privateName")).sendKeys(Private_Name);
//		driver.findElement(By.name("productDetails.productDescription")).sendKeys("test-Nadsoft");
//		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);
//
//		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
//		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
//		
//		Thread.sleep(3000);
//		String[] files1 = {Media_Path + Fileone};
//		String allFiles1 = String.join("\n", files1);
//		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();
//
//		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("1");
//		Thread.sleep(1000);
//		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
//		jse.executeScript("arguments[0].click();", confirmSaveBtn);
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
//		Thread.sleep(3000);
//		
//		driver.navigate().to(Products);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
//		Thread.sleep(4000);
//		driver.findElement(By.cssSelector("#result-item-0>a")).click();
//		
//		Thread.sleep(2000);
//		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
//		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();//Click on order page created by product
//		
//		String originalTab = driver.getWindowHandle();
//		for (String handle : driver.getWindowHandles()) {
//			if (!handle.equals(originalTab)) {
//				driver.switchTo().window(handle);
//					break;
//			}
//		}	
//		
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("input#email")).sendKeys(email);
//		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
//		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
//		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
//		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); //select country as USA
//		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
//		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
//		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
//		
//		Thread.sleep(5000);
//		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[normalize-space()='Payment Information']")));
//		Thread.sleep(2000);
//
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure card number input frame']")));
//		wait.until(ExpectedConditions.elementToBeClickable(By.name("cardnumber"))).sendKeys(Card_No);
//		driver.switchTo().defaultContent();
//
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure expiration date input frame']")));
//		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);
//		driver.switchTo().defaultContent();
//
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
//		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
//		driver.switchTo().defaultContent();
//
//		WebElement cardHolder = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
//		cardHolder.clear();
//		cardHolder.sendKeys(F_Name + " " + L_Name);
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("button[type='submit']")).click();
//		Thread.sleep(7000);
//		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
//		System.out.println("Placed Order ID: " + PlacedOrderID11);
//		driver.close();
//		driver.switchTo().window(originalTab);
//		
//		//Products module in "View Only" condition
//		driver.navigate().to(Products);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
//		Thread.sleep(4000);
//		driver.findElement(By.cssSelector("#result-item-0>a")).click();		
//		Thread.sleep(2000);
//		
//		try {
//			driver.findElement(By.xpath("(//button[@type=\"submit\"])[1]")).click();
//			Assert.assertTrue(false, "Test Failed: Save button is Displayed for product.");
//		} catch(Exception noSuchEelementException){
//		    System.out.println("Save button is not Displayed for product.");
//		}
//		
//		try {
//			driver.findElement(By.cssSelector("button[role='switch']:first-of-type")).click();//click on toggle button to make product inactive
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//button[normalize-space()=\"Make Inactive\"]")).click();//click on inactive button
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]")).click();//confirm inactive
//			Assert.assertTrue(false, "Test Failed: Toggle Active/ InActive button is Displayed for product.");
//		} catch(Exception noSuchEelementException){
//		    System.out.println("Toggle button is In-Active for product.");			
//		}
//		
//		Thread.sleep(2000);
//		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
//		
//		/*try {
//			driver.findElement(By.xpath("(//button[@aria-label='Page Settings'])[1]")).click();//Click on order page created by product
//			Assert.assertTrue(false, "Test Failed: Edit Page is clicakable for product.");
//		} catch(Exception noSuchEelementException){
//		    System.out.println("Edit Page button is not clicakable for product.");
//		}*/
//		
//		//Orders module in "View Only" condition
//		driver.navigate().to(Orders);
//		Thread.sleep(5000);
//		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)")).click(); //Click on order details
//		Thread.sleep(3000);
//		
//		try {
//			driver.findElement(By.xpath("((//span[normalize-space()=\"Edit Order\"])[1]//parent::button)[1]")).click();
//			Assert.assertTrue(false, "Test Failed: Edit Order button is clicakable for order.");
//		} catch(Exception noSuchEelementException){
//		    System.out.println("Edit Order button is not clicakable for order.");
//		}
//		
//		try {
//			driver.findElement(By.xpath("((//span[normalize-space()='Refund'])[1]//parent::button)[1]")).click();
//			Assert.assertTrue(false, "Test Failed: Refund button is clicakable for order.");
//		} catch(Exception noSuchEelementException){
//		    System.out.println("Refund button is not clicakable for order.");
//		}
//		
//		//Virtual Terminal  module in "View Only" condition
//		driver.navigate().to(Virtual_Terminal);
//		Thread.sleep(5000);
//		
//		try {
//			driver.navigate().to(Virtual_Terminal);
//			Thread.sleep(20000);
//			driver.findElement(By.xpath("//button[normalize-space(.)='Cash']")).click(); //Select cash payment method
//			Thread.sleep(1000);
//			driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//span[@class=\"truncate font-sans\"]//parent::span//parent::button")).click(); 
//			Thread.sleep(1000);
//			driver.findElement(By.id("field_first_name")).sendKeys("Ecom"); 
//			driver.findElement(By.id("field_last_name")).sendKeys("Contact"); 
//			driver.findElement(By.id("field_email_id")).sendKeys(WMLogin); 
//			driver.findElement(By.name("phone_no")).sendKeys(ContactPhone1); 
//			driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add);
//			driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//span[normalize-space()=\"Done\"]")).click(); 
//
//			Thread.sleep(5000);
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]"))).click(); //Clicking on add line items button
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); //Clicking on item name field
//			Thread.sleep(1000);
//			driver.findElement(By.xpath("//button[.//span[text()='Create New Item']]/parent::div/following-sibling::div[1]")).click(); //Clicking on first product from the dropdown
//			Thread.sleep(1000);
//
//			jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Payment Type']")));
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//div[contains(@class,'custom-class-for-aaply-the-css')]//div[contains(@class,'flex flex-1 items-center') and .//div[contains(@id,'placeholder')]]")).click(); //Clicking on payment type dropdown
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//div[@id='react-select-2-listbox']//div[@role='option'][1]")).click(); //Select payment type cash
//			Thread.sleep(1000);
//			WebElement button = driver.findElement(By.xpath("//button[.//p[contains(text(),'Record Cash Sale')]]"));
//			Assert.assertFalse(button.isEnabled(), "Button should be disabled");
//			Assert.assertTrue(button.getAttribute("class").contains("cursor-not-allowed"),"Virtual Terminal Record Cash Sale button Disabled.");
//			System.out.println("Virtual Terminal Record Cash Sale button is Disabled.");
//		} catch(Exception noSuchEelementException){
//		    System.out.println("Record Cash Sale button is not clicakable in virtual terminal Module.");
//		}
//		
//		//Invoices module in "View Only" condition
//		driver.navigate().to(invoices);
//		Thread.sleep(5000);
//		driver.navigate().to(invoices);
//		Thread.sleep(7000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button
//		
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
//		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
//		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
//		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
//		driver.findElement(By.id("field_first_name")).sendKeys(firstName); //Input first name
//		driver.findElement(By.id("field_last_name")).sendKeys(lastName); //Input last name
//		driver.findElement(By.id("field_email_id")).sendKeys(email); //Input email
//		driver.findElement(By.name("phone_no")).sendKeys(phone); //Input phone number
//		driver.findElement(By.xpath("(//input[@id='field_'])[3]")).sendKeys(Street_Add); //Input street address
//		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
//		
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); 
//		
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//span[normalize-space()='+ Add Line Items']")).click(); //Click on add item button
//		driver.findElement(By.xpath("//div[contains(@class,'truncate flex justify-start')]")).click(); //Click on select product drop down
//		
//		driver.findElement(By.xpath("(//input[@placeholder='Enter Line Item Name'])[1]")).sendKeys("Product 1"); //Select product
//		driver.findElement(By.xpath("(//input[contains(@placeholder,'—')])[1]")).sendKeys(Value); //Input quantity
//		driver.findElement(By.id("unit-price")).sendKeys(Value); //input value in unit price field
//		driver.findElement(By.xpath("//textarea[@placeholder=\"Custom line item description\"]")).sendKeys("Add New Product Discription"); 
//		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); //Click on save button to save line item
//		
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("(//span[text()='Save And Send'])[1]//parent::span//parent::button")).click(); //Click on send invoice button
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector("div.vf-code-frame > div:nth-of-type(3) > div > button:first-of-type")); //Click on send now button in pop up
//		Thread.sleep(2000);
//		WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='dialog'] > div > div:nth-of-type(3) > div > button:first-of-type")));
//		jse.executeScript("arguments[0].click();", button);
//		
//		Thread.sleep(5000);
//		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:last-of-type>button")).click();
//		Thread.sleep(2000);
//		
//		try {
//			driver.findElement(By.xpath("//p[normalize-space()=\"Edit Invoice\"]//parent::div")).click(); 
//			Assert.assertTrue(button.getAttribute("class").contains("cursor-not-allowed"),"Disabled styling not applied");
//			System.out.println("Edit Invoice button is Disabled for invoice.");
//		} catch(Exception noSuchEelementException){
//		    System.out.println("Edit Invoice button is clicakable for invoice.");
//		}
//		
//		try {
//			driver.findElement(By.xpath("//p[normalize-space()=\"Make Payment\"]//parent::div")).click(); 
//			Assert.assertTrue(button.getAttribute("class").contains("cursor-not-allowed"),"Disabled styling not applied");
//			System.out.println("Make Payment button is Disabled for invoice.");
//		} catch(Exception noSuchEelementException){
//		    System.out.println("Make Payment button is clicakable for invoice.");
//		}
//		
//		try {
//			driver.findElement(By.xpath("//p[normalize-space()=\"Archive Invoice\"]//parent::div")).click(); 
//			Assert.assertTrue(button.getAttribute("class").contains("cursor-not-allowed"),"Disabled styling not applied");
//			System.out.println("Archive Invoice button is Disabled for invoice.");
//		} catch(Exception noSuchEelementException){
//		    System.out.println("Archive Invoice button is clicakable for invoice.");
//		}
//		
//		try {
//			driver.findElement(By.xpath("//p[normalize-space()=\"Stop Auto Reminder\"]//parent::div")).click(); 
//			Assert.assertTrue(button.getAttribute("class").contains("cursor-not-allowed"),"Disabled styling not applied");
//			System.out.println("Stop Auto Reminder button is Disabled for invoice.");
//		} catch(Exception noSuchEelementException){
//		    System.out.println("Stop Auto Reminder button is clicakable for invoice.");
//		}
		
		//Store Settings module in "View Only" condition
		driver.navigate().to(settings);
		Thread.sleep(15000);
		driver.findElement(By.xpath("//h3[normalize-space()='Store Details']/parent::div/parent::button")).click(); 
		Thread.sleep(2000);
		
		try {			
			List<WebElement> elements = driver.findElements(By.xpath("//span[normalize-space()='Edit Store Settings']//parent::button"));
			Assert.assertTrue(elements.isEmpty(), "Test Failed: Edit Store Settings button is clickable for store settings.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Edit Store Settings button is Disabled for store settings.");
		}
		
		try {
		    WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Change Image']//parent::button"));
		    jse.executeScript("arguments[0].click();", element);
		    Assert.assertFalse(element.isEnabled(), "Button should be disabled");
		    System.out.println("Change Image button is Disabled for store settings.");
		} catch (Exception e) {
		    System.out.println("Edit Payments Settings button is Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(2)")).click(); 
		Thread.sleep(2000);
		
		try{
			WebElement button = driver.findElement(By.xpath("//button[normalize-space()=\"+ Add Another Offline Payment Method\"]"));
			Assert.assertFalse(button.isEnabled(), "Button should be disabled");
			System.out.println("Add Another Offline Payment Method button is Disabled for store settings.");
		} catch (Exception e) {
		    System.out.println("Add Another Offline Payment Method button is Disabled for store settings.");
		}
		
		try {
			WebElement button = driver.findElement(By.xpath("//button[normalize-space()=\"Manage Templates\"]"));
			Assert.assertFalse(button.isEnabled(), "Button should be disabled");
			System.out.println("Manage Templates button is Disabled for store settings.");
		} catch (Exception e) {
		    System.out.println("Manage Templates button is Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(3)")).click(); 
		Thread.sleep(2000);
		
		try {
			WebElement button = driver.findElement(By.xpath("//button[@value=\"on\"]"));
			Assert.assertEquals(button.getAttribute("aria-checked"), "true");
		} catch (Exception e) {
		    System.out.println("2FA Settings button is Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(5)")).click(); 
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.cssSelector("div[role='radiogroup']>label>div>button")).click();
			Assert.assertTrue(false, "Test Failed: Tax Settings button Disabled for store settings.");
		} catch (Exception e) {
		    System.out.println("Tax Settings button is Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(6)")).click(); 
		Thread.sleep(2000);
		
		try {
			WebElement button = driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div:nth-of-type(2)>div:last-of-type>div>div>div>div>div>button")); //Click on Add region button
			Assert.assertFalse(button.isEnabled(), "Button should be disabled");
			System.out.println("Add Region button is Disabled for store settings.");
		} catch (Exception e) {
		    System.out.println("Regions button is not Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(7)")).click(); 
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.cssSelector("button[aria-label='Add return reason']")).click(); //Click on Add return reason button
			Assert.assertTrue(false, "Test Failed: Return Reason button is clickable for store settings.");
			System.out.println("Return Reason button is Disabled for store settings.");
		} catch (Exception e) {
		    System.out.println("Return Reason button is not Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(8)")).click();
		Thread.sleep(5000);
		
		try {
			WebElement feeOption = driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[2]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'});", feeOption);
			Assert.assertFalse(feeOption.isEnabled(), "Button should be disabled");
			System.out.println("Price Adjustment Settings button is Disabled for store settings.");
		} catch (Exception e) {
		    System.out.println("Price Adjustment Settings button is not Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(9)")).click();
		Thread.sleep(2000);
		
		try {
			WebElement button = driver.findElement(By.xpath("(//span[normalize-space()='Save'])[1]"));
			Boolean isDisabled = (Boolean) jse.executeScript("return arguments[0].disabled;", button);
			Assert.assertTrue(isDisabled, "Button should be disabled");
		} catch (Exception e) {
			System.out.println("Subscription Settings Save button is Disabled for store settings.");
		}
		
		try {
			WebElement button = driver.findElement(By.xpath("(//span[normalize-space()='Save'])[2]"));
			Boolean isDisabled = (Boolean) jse.executeScript("return arguments[0].disabled;", button);
			Assert.assertTrue(isDisabled, "Button should be disabled");
		} catch (Exception e) {
			System.out.println("Subscription Settings Save button is Disabled for store settings.");
		}
		
		try {
			WebElement button = driver.findElement(By.xpath("(//span[normalize-space()='Save'])[3]"));
			Boolean isDisabled = (Boolean) jse.executeScript("return arguments[0].disabled;", button);
			Assert.assertTrue(isDisabled, "Button should be disabled");			
		} catch (Exception e) {
			System.out.println("Subscription Settings Save button is Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(10)")).click();
		Thread.sleep(2000);
		
		try {
			WebElement button = driver.findElement(By.xpath("(//span[normalize-space()='Save'])[1]//parent::button")); 
			Assert.assertFalse(button.isEnabled(), "Button should be disabled");
			System.out.println("Card Expiration Settings Save button is Disabled for store settings.");
		} catch (Exception e) {
		    System.out.println("Card Expiration Settings button is not Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(11)")).click();
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[1]")).click(); //Click on Add fulfillment option button
			Assert.assertTrue(false, "Test Failed: Shipping Options button is clickable for store settings.");
		} catch (Exception e) {
		    System.out.println("Shipping Options button is Disabled for store settings.");
		}
		
		try {
			driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[2]")).click(); //Click on save button
			Assert.assertTrue(false, "Test Failed: Return Shipping Options button is clickable for store settings.");
		} catch (Exception e) {
		    System.out.println("Return Shipping Options button is Disabled for store settings.");
		}	
	}
	
	@Test(priority = 3)
	public void ModifyAccess() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		
		WebElement allAccess = driver.findElement(By.cssSelector("div.content-access"));
		action.moveToElement(allAccess).build().perform();
		Thread.sleep(500);
		
		//for orders Module
		WebElement ordersOff = driver.findElement(By.id("section-orders"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ordersOff);
		Thread.sleep(500);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	

}
