package dailyTesting_911;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class virtualTerminal extends Data {
	java.util.Random r = new java.util.Random();

	String Product_Name = "OneTime Product - 911 " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
			SKU = "PrivateNo" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),	
			Private_Name = "TestProduct" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(),

			Country_Add = "Boardman, Oregon, 97818",ContactPhone1 = "(775) 986-5200",Price = "2", Value = "1",susbcriptionPrice = "3",Fileone = "file1.jpg",
			Attachment = "Jira_Guide.pdf",F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", country = "United States", 
			ContactPhone2 = "(539) 321-3502",Account_Holder_Name = "DeposytCert", Routing_Number = "490000018", Account_Number = "000123456789", EXP = "12/44", CVV = "123", 
			Card_No = "4242424242424242", chars = "abcdefghijklmnopqrstuvwxyz",	

			firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
			lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
			email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com",phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));

	@Test(priority = 1)
	public void Virtual_Terminal () throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;

		//Verify that after cash payment user will receive email for the order
		//DeleteMail("Your Order is Confirmed — Order# ");		
		//DeleteContact(ContactPhone1,WMLogin);

		//verify that user can create new customer and select the existing customer
		//verify that user can purchase product using cash payment
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[normalize-space(.)='Cash']")).click(); //Select cash payment method
		Thread.sleep(1000);
		driver.navigate().refresh();
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[normalize-space(.)='Cash']")).click(); //Select cash payment method

		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
		Thread.sleep(4000);
		driver.findElement(By.xpath("//span[@class=\"truncate font-sans\"]//parent::span//parent::button")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.id("field_first_name")).sendKeys(firstName); 
		driver.findElement(By.id("field_last_name")).sendKeys(lastName); 
		driver.findElement(By.id("field_email_id")).sendKeys(email); 
		driver.findElement(By.name("phone_no")).sendKeys(phone); 
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

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Cash Payment Method']")));
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
		//ReceivedMail("Your Order is Confirmed — Order# ");

		//Verify that after card payment user will receive email for the order
		//DeleteMail("Subscription Charge Receipt for Subscription# ");

		//verify that user can purchase product using card payment
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(10000);
		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search Customer\"]")).sendKeys(Number); //Select first customer from the list
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
		Thread.sleep(3000);
		driver.findElement(By.name("filters")).sendKeys(Number,Keys.ENTER);
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

		//ReceivedMail("Subscription Charge Receipt for Subscription# ");

		//Verify that after ACH payment user will receive email for the order
		//DeleteMail("Your Order is Confirmed — Order# ");

		try {
			driver.navigate().to(settings);
			Thread.sleep(7000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Price Adjustment Settings']/parent::div/parent::button"))).click(); 	
			Thread.sleep(3000);
			WebElement feeOption = driver.findElement(By.xpath("(//input[contains(@name,'fee_option')])[5]"));
			jse.executeScript("arguments[0].scrollIntoView({block:'center'});", feeOption);
			feeOption.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='displayStyleCashDiscount']")).click();
			driver.findElement(By.id("dialog-checkbox")).click();		
			driver.findElement(By.xpath("//span[normalize-space()=\"I agree & enable\"]")).click();	
		} catch(Exception e) {
			System.out.println("ACH_Payment is already enabled for the store");
		}

		//verify that user can purchase product using ACH payment
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(7000);
		driver.findElement(By.xpath("//span[normalize-space(.)='ACH']//parent::button")).click(); //Select card payment method
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); //Clicking on add customer button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search Customer\"]")).sendKeys(Number); //Select first customer from the list
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
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("test-Nadsoft");
		driver.findElement(By.name("productDetails.sku")).sendKeys(SKU);
		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);

		Thread.sleep(3000);
		String[] files1 = {Media_Path + Fileone};
		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();
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

		//ReceivedMail("Your Order is Confirmed — Order# ");		
	}
}