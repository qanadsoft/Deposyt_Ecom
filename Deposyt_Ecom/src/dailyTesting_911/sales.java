package dailyTesting_911;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class sales extends Data {
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
	
	@Test(priority = 2)
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

	@Test(priority = 3)
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
	
	@Test(priority = 4)
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
	
	@Test(priority = 5)
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
	
	@Test(priority = 6)
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
	
	@Test(priority = 7)
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
	
	@Test(priority = 8)
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
	
	@Test(priority = 9)
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

}
