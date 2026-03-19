package dailyTesting_911;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Master.Data;

public class access_level extends Data {
	
	String it, username, adminID, userLoginNavigation, userID, secoundUserID, personalAccessPage;
	
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
	    }
	}
	
	@Test(priority = 1)
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
		driver.findElement(By.cssSelector("button.saveaccesslevel")).click();
	}
	
	@Test(priority = 2)
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
	
	@Test(priority = 3)
	public void ViewAccess() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		
		WebElement allAccess = driver.findElement(By.cssSelector("div.content-access"));
		action.moveToElement(allAccess).build().perform();
		Thread.sleep(500);
		
		//For orders Module
		WebElement ordersOff = driver.findElement(By.id("section-orders"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ordersOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_orders_grant_none")).click();
		
		//for products Module
		WebElement productsOff = driver.findElement(By.id("section-products"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsOff);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.ac_product_grant_view")).click();
		driver.findElement(By.cssSelector("button.ac_product_others_grant_view")).click();
		
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
		
		
		
		
		
		
		
		
		
		
	}
	

}
