package dailyTesting_911;

import java.time.Duration;
import java.util.List;
import java.util.Set;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Master.Data;

public class access_level extends Data {
	java.util.Random r = new java.util.Random();
	StringBuilder giftCard = new StringBuilder();
	
	String it, username, adminID, userLoginNavigation, userID, secoundUserID, personalAccessPage,
	Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
	SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
	Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(),
	
	ContactPhone1 = "(775) 986-5200",Fileone = "file1.jpg", chars = "abcdefghijklmnopqrstuvwxyz", Value = "1", ContactPhone2 = "(539) 321-3502",GiftCard = "0.20",
	Card_No = "4242424242424242",F_Name = "NineEleven", L_Name = "Contact", Street_Add = "Allen Court", country = "United States",Discounts_Price = "1",Country_Add = "Boardman, Oregon, 97818",
	
	giftCardName = "GiftCard" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
	firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
	lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
	email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com",phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000)), EXP = "12/44", CVV = "123";				
					
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
		
		String firstName1 ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
		lastName1 ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
		email1 = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com", phone1 = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
		
		String firstName2 ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
		lastName2 ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
		email2 = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com", phone2 = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
		
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
		wait.until(ExpectedConditions.elementToBeClickable(By.name("exp-date"))).sendKeys(EXP);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='Secure CVC input frame']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("cvc"))).sendKeys(CVV);
		driver.switchTo().defaultContent();

		WebElement cardHolder = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder.clear();
		cardHolder.sendKeys(F_Name + " " + L_Name);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Products module in "View Only" condition
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();		
		Thread.sleep(2000);
		
		try {
			driver.findElement(By.xpath("(//button[@type=\"submit\"])[1]")).click();
			Assert.assertTrue(false, "Test Failed: Save button is Displayed for product.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Save button is not Displayed for product.");
		}
		
		try {
			driver.findElement(By.cssSelector("button[role='switch']:first-of-type")).click();//click on toggle button to make product inactive
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[normalize-space()=\"Make Inactive\"]")).click();//click on inactive button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]")).click();//confirm inactive
			Assert.assertTrue(false, "Test Failed: Toggle Active/ InActive button is Displayed for product.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Toggle button is In-Active for product.");			
		}
		
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		
		/*try {
			driver.findElement(By.xpath("(//button[@aria-label='Page Settings'])[1]")).click();//Click on order page created by product
			Assert.assertTrue(false, "Test Failed: Edit Page is clicakable for product.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Edit Page button is not clicakable for product.");
		}*/
		
		//Orders module in "View Only" condition
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)")).click(); //Click on order details
		Thread.sleep(3000);
		
		try {
			driver.findElement(By.xpath("((//span[normalize-space()=\"Edit Order\"])[1]//parent::button)[1]")).click();
			Assert.assertTrue(false, "Test Failed: Edit Order button is clicakable for order.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Edit Order button is not clicakable for order.");
		}
		
		try {
			driver.findElement(By.xpath("((//span[normalize-space()='Refund'])[1]//parent::button)[1]")).click();
			Assert.assertTrue(false, "Test Failed: Refund button is clicakable for order.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Refund button is not clicakable for order.");
		}
		
		//Virtual Terminal  module in "View Only" condition
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(5000);
		
		try {
			driver.navigate().to(Virtual_Terminal);
			Thread.sleep(20000);
			driver.findElement(By.xpath("//button[normalize-space(.)='Cash']")).click(); //Select cash payment method
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@class=\"truncate font-sans\"]//parent::span//parent::button")).click(); 
			Thread.sleep(1000);
			
			driver.findElement(By.id("field_first_name")).sendKeys(firstName1); 
			driver.findElement(By.id("field_last_name")).sendKeys(lastName1); 
			driver.findElement(By.id("field_email_id")).sendKeys(email1); 
			driver.findElement(By.name("phone_no")).sendKeys(phone1); 
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
			WebElement button = driver.findElement(By.xpath("//button[.//p[contains(text(),'Record Cash Sale')]]"));
			Assert.assertFalse(button.isEnabled(), "Button should be disabled");
			Assert.assertTrue(button.getAttribute("class").contains("cursor-not-allowed"),"Virtual Terminal Record Cash Sale button Disabled.");
			System.out.println("Virtual Terminal Record Cash Sale button is Disabled.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Record Cash Sale button is not clicakable in virtual terminal Module.");
		}
		
		//Invoices module in "View Only" condition
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
		
		driver.findElement(By.id("field_first_name")).sendKeys(firstName2); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(lastName2); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(email2); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(phone2); //Input phone number
		Thread.sleep(2000);
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
		
		try {
			driver.findElement(By.xpath("//p[normalize-space()=\"Edit Invoice\"]//parent::div")).click(); 
			Assert.assertTrue(button.getAttribute("class").contains("cursor-not-allowed"),"Disabled styling not applied");
			System.out.println("Edit Invoice button is Disabled for invoice.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Edit Invoice button is clicakable for invoice.");
		}
		
		try {
			driver.findElement(By.xpath("//p[normalize-space()=\"Make Payment\"]//parent::div")).click(); 
			Assert.assertTrue(button.getAttribute("class").contains("cursor-not-allowed"),"Disabled styling not applied");
			System.out.println("Make Payment button is Disabled for invoice.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Make Payment button is clicakable for invoice.");
		}
		
		try {
			driver.findElement(By.xpath("//p[normalize-space()=\"Archive Invoice\"]//parent::div")).click(); 
			Assert.assertTrue(button.getAttribute("class").contains("cursor-not-allowed"),"Disabled styling not applied");
			System.out.println("Archive Invoice button is Disabled for invoice.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Archive Invoice button is clicakable for invoice.");
		}
		
		try {
			driver.findElement(By.xpath("//p[normalize-space()=\"Stop Auto Reminder\"]//parent::div")).click(); 
			Assert.assertTrue(button.getAttribute("class").contains("cursor-not-allowed"),"Disabled styling not applied");
			System.out.println("Stop Auto Reminder button is Disabled for invoice.");
		} catch(Exception noSuchEelementException){
		    System.out.println("Stop Auto Reminder button is clicakable for invoice.");
		}
		
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
			WebElement button1 = driver.findElement(By.xpath("//button[normalize-space()=\"+ Add Another Offline Payment Method\"]"));
			Assert.assertFalse(button1.isEnabled(), "Button should be disabled");
			System.out.println("Add Another Offline Payment Method button is Disabled for store settings.");
		} catch (Exception e) {
		    System.out.println("Add Another Offline Payment Method button is Disabled for store settings.");
		}
		
		try {
			WebElement button2 = driver.findElement(By.xpath("//button[normalize-space()=\"Manage Templates\"]"));
			Assert.assertFalse(button2.isEnabled(), "Button should be disabled");
			System.out.println("Manage Templates button is Disabled for store settings.");
		} catch (Exception e) { 
		    System.out.println("Manage Templates button is Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(3)")).click(); 
		Thread.sleep(2000);
		
		try {
			WebElement button3 = driver.findElement(By.xpath("//button[@value=\"on\"]"));
			Assert.assertEquals(button3.getAttribute("aria-checked"), "false");
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
			WebElement button4 = driver.findElement(By.cssSelector("#main-page-ui-div>main>div>div:nth-of-type(2)>div:last-of-type>div>div>div>div>div>button")); //Click on Add region button
			Assert.assertFalse(button4.isEnabled(), "Button should be disabled");
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
			WebElement button5 = driver.findElement(By.xpath("(//span[normalize-space()='Save'])[1]"));
			Boolean isDisabled = (Boolean) jse.executeScript("return arguments[0].disabled;", button5);
			Assert.assertTrue(isDisabled, "Button should be disabled");
		} catch (Exception e) {
			System.out.println("Subscription Settings Save button is Disabled for store settings.");
		}
		
		try {
			WebElement button6 = driver.findElement(By.xpath("(//span[normalize-space()='Save'])[2]"));
			Boolean isDisabled = (Boolean) jse.executeScript("return arguments[0].disabled;", button6);
			Assert.assertTrue(isDisabled, "Button should be disabled");
		} catch (Exception e) {
			System.out.println("Subscription Settings Save button is Disabled for store settings.");
		}
		
		try {
			WebElement button7 = driver.findElement(By.xpath("(//span[normalize-space()='Save'])[3]"));
			Boolean isDisabled = (Boolean) jse.executeScript("return arguments[0].disabled;", button7);
			Assert.assertTrue(isDisabled, "Button should be disabled");			
		} catch (Exception e) {
			System.out.println("Subscription Settings Save button is Disabled for store settings.");
		}
		
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(10)")).click();
		Thread.sleep(2000);
		
		try {
			WebElement button8 = driver.findElement(By.xpath("(//span[normalize-space()='Save'])[1]//parent::button")); 
			Assert.assertFalse(button8.isEnabled(), "Button should be disabled");
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
		Actions actions = new Actions (driver);
		String firstName1 ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
		lastName1 ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
		email1 = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com", phone1 = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));
				
		String Price1 = "5", Price2 = "25", Region_Name = "Europe";
		String Store_Name = "Smart Online Store " + java.util.UUID.randomUUID().toString().substring(0,2);
		
		WebElement allAccess = driver.findElement(By.cssSelector("div.content-access"));
		actions.moveToElement(allAccess).build().perform();
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
		driver.findElement(By.cssSelector("button.saveaccesslevel")).click(); 
		Thread.sleep(5000);
		
		//Products module in "modify" condition
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
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); //select country as USA
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down
		Thread.sleep(2000);
		
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
		String PlacedOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrder);
		driver.close();
		driver.switchTo().window(originalTab);
		System.out.println("Order placed successfully with Modify Access In Product Module.");
			
		//Orders module in "modify" condition
		driver.navigate().to(Orders);
		Thread.sleep(7000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);
		String currentUrl = driver.getCurrentUrl().toLowerCase();	
		System.out.println("Current URL : " + currentUrl);
		Assert.assertTrue(currentUrl.contains(PlacedOrder), "Order ID not found in URL");
		
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
		System.out.println("Order placed successfully with Modify Access in Order Module.");
		
		//Invoices module in "modify" condition
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Describe what this invoice is about')])[1]")).sendKeys("This invoice is created for 911 Automation Test");
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Recipient']")).click(); //Click on add recipient button
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search Customers')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Create new customer')])[1]")).click(); //Select create contact
		driver.findElement(By.id("field_first_name")).sendKeys(firstName1); //Input first name
		driver.findElement(By.id("field_last_name")).sendKeys(lastName1); //Input last name
		driver.findElement(By.id("field_email_id")).sendKeys(email1); //Input email
		driver.findElement(By.name("phone_no")).sendKeys(phone1); //Input phone number
		Thread.sleep(2000);
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
        driver.findElement(By.xpath("//button[@role='checkbox']")).click();
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
		
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
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
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
        
        Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		System.out.println("Order placed successfully with Modify Access in Invoice Module.");
		
		//Virtual Terminal  module in "modify" condition
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
		driver.navigate().to(Orders);
		Thread.sleep(5000);
		String orderIdInVirtualTerminal = driver.findElement(By.xpath("//tbody[@id='order-table-body']/tr[1]/td[2]/span")).getText().trim().replace("#", "");
		System.out.println("Order ID in Virtual Terminal: " + orderIdInVirtualTerminal);
		Assert.assertEquals(orderId, orderIdInVirtualTerminal, "Order ID in virtual terminal does not match the recorded order ID.");	
		System.out.println("Order placed successfully with Modify Access in Virtual Terminal Module.");
		
		//Discounts  module in "modify" condition
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
		System.out.println("Discount created successfully with Modify Access in Discounts Module.");
		
		//Gift Cards module in "modify" condition
		driver.navigate().to(Gift_Cards);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='New Gift Card'])[3]"))).click(); // click on New Gift Card button
		driver.findElement(By.xpath("(//div[@id='region.region_id'])[1]")).click(); //click on region drop down
		driver.findElement(By.id("react-select-2-option-0")).click(); //Select Default as region
		driver.findElement(By.xpath("(//input[@placeholder='-'])[1]")).sendKeys(GiftCard);
		driver.findElement(By.name("receiver.email")).sendKeys(WMLogin);
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); //click on create gift card button
		Thread.sleep(5000);
		String GiftCardID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.customer-table-second-class>table>tbody>tr>td>p:first-of-type"))).getText().trim();
		System.out.println("Created Gift Card ID: " + GiftCardID);
		System.out.println("Gift Card created successfully with Modify Access in Gift Cards Module.");
		
		//Pricing module in "modify" condition
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
		System.out.println("Price List created and published successfully with Modify Access in Pricing Module.");
		
		//Store Settings module's tabs in "modify" condition->Check Store Details Page
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Store Details']/parent::div/parent::button"))).click(); 
		Thread.sleep(2000);
		WebElement storeName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder=\"Store name\"]")));
		Thread.sleep(2000);
		storeName.clear();
		Thread.sleep(2000);
		storeName.sendKeys(Store_Name);	
		
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//span[normalize-space()=\"Publish\"]//parent::button")).click();
			
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.cssSelector("a[rel='noopener noreferrer']:first-of-type")).click();
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
		System.out.println("Store name is correct on store front page with Modify Access in Store Settings Module.");
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//Store Settings module's tabs in "modify" condition->Check Payments Page
		Thread.sleep(2000);	
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(2)")).click(); 
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector("#main-page-ui-div article:first-of-type section button:first-of-type")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Set as Active\"]//parent::button")).click();
		System.out.println("Payment gateway are working in modify access level in store settings module.");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#main-page-ui-div article:nth-of-type(2) section button")).click(); 
		Thread.sleep(2000);
		jse.executeScript("arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", driver.findElement(By.cssSelector("#stripe>article>div>section>div>div:last-of-type>div>button")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Set as Active\"]//parent::button")).click();
		
		//Store Settings module's tabs in "modify" condition->Check Regions Page
		driver.findElement(By.cssSelector("div.sticky.top-0.self-start>div>a:nth-of-type(6)")).click(); 
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
		driver.findElement(By.cssSelector("div[role='radiogroup']>label>div>button:first-of-type")).click();
		
		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(parentWindow)) {
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
		System.out.println("Region is found in country list while placing order with Modify Access in Store Settings Module.");
		
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
			Thread.sleep(2000);
		}catch (NoSuchElementException e) {
			Assert.fail("Region not found: " + e.getMessage());
		}		
		
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//Store Settings module's tabs in "modify" condition->Check Price Adjustment Settings Page
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
			System.out.println("Convenience fee is already enabled");
		}
		
		String convenienceFee = driver.findElement(By.xpath("//input[@placeholder=\"Enter amount\"]")).getAttribute("value").trim();			
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_Name);
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

		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("1");
		Thread.sleep(1000);
		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
					break;
			}
		}		
		
		Thread.sleep(9000);	
		String orderconvenienceFee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#no-tailwindcss-base>section>div>div>div:nth-of-type(9)>span"))).getText().trim();
		System.out.println("convenience Fee On Checkout Page : " + orderconvenienceFee);
		String extractedconvenienceFee = orderconvenienceFee.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedconvenienceFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),convenienceFee.replaceAll("[^0-9]", "").replaceAll("00$", ""),"Convenience Fee mismatch in order summary");
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//Store Settings module's tabs in "modify" condition->Check Subscription Settings Page	
		driver.navigate().to(settings);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Subscription Settings']/parent::div/parent::button"))).click(); 
		Thread.sleep(3000);
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		String buttonoff = driver.findElement(By.xpath("(//button[@role='switch'])[1]")).getAttribute("data-state");
		
		try {
			if(buttonoff.equals("unchecked")) {
				driver.findElement(By.xpath("(//button[@role='switch'])[1]")).click();
				driver.findElement(By.xpath("(//button[@role='switch'])[2]")).click();
				driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[2]")).click();
				System.out.println("Send Notification Option is turned on Under Modify Subscription Settings Module.");
			}
		} catch(Exception e) {
			System.out.println("Send Notification Option is already turned on");
			Assert.assertTrue(false, "Test Failed: Send Notification button is Not clicakable for order Under Modify Subscription Settings Module.");
		}		
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@role='switch'])[1]")).click();
		driver.findElement(By.xpath("(//button[@role='switch'])[2]")).click();
		driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[2]")).click();
	}
}