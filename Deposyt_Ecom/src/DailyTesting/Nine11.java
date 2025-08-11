package DailyTesting;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert; 
import org.testng.annotations.Test;

import Master.MasterClass;

public class Nine11 extends MasterClass {

	@Test
	public void Products() throws Exception	{

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		Actions action = new Actions(driver);
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Instant now = Instant.now();

		String Product_name = "Rohith Nine Eleven Test Product"+now.getEpochSecond(), Price = "25",
				F_Name = "NineEleven", L_Name = "Contact", Number = "2345678910",
				Street_Add = "D.P. Road", State = "Maharastra", City = "Pune", Pincode = "411001",
				Card_Number = "4242424242424242", CVV = "123",
				Mail_Subject = "Your Order is Confirmed";
		
		//Deleting Already recived order Mails
		DeleteMail(Mail_Subject);

		driver.navigate().to(Products);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Search Products']")));

        // Search for the product (if exists delete it)
        WebElement searchBox = driver.findElement(By.cssSelector("input[placeholder='Search Products']"));
        searchBox.sendKeys(Product_name);
        Thread.sleep(3000);

        try {
            driver.findElement(By.cssSelector("#result-item-0>a")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']+div>div>button"))).click();
            driver.findElement(By.cssSelector("div.shadow-product-card-hover>button")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.verification-model-code+div>div>button:last-of-type"))).click();
        } catch (NoSuchElementException e) {
            // product not found to delete, continue
        }

        // Clear search box
        searchBox.click();
        searchBox.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		
		//Create a product in test account
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.items-center.capitalize.newproductbutton"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(Product_name);
		driver.findElement(By.name("productDetails.productDescription")).sendKeys("This product is created by Automation for 911 Test");

		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);
		uploadImage.sendKeys(Media_Path + "JPEG TEST.jpg");

		WebElement uploadAttachment = driver.findElement(By.cssSelector("[name=\"productType.digital.attachment\"]+div>div>input"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadAttachment);
		uploadAttachment.sendKeys(Media_Path + "Jira Guide.pdf");

		WebElement subscriptionButton = driver.findElement(By.xpath("//button[@id='Subscription']"));

		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", subscriptionButton);
		Thread.sleep(500);

		jse.executeScript("arguments[0].click();", subscriptionButton);

		driver.findElement(By.name("productPricing.regularPrice")).sendKeys(Price);
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("#react-select-2-placeholder")).click(); 
		
		driver.findElement(By.xpath("//span[contains(text(),'Weekly')]")).click();
		WebElement unlockCourseButton = driver.findElement(By.xpath("(//span[contains(@data-state,'unchecked')])[4]"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButton);
		Thread.sleep(500);
		jse.executeScript("arguments[0].click();", unlockCourseButton);

		// Save product
		WebElement saveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", saveBtn);
		Thread.sleep(500);
		saveBtn.click();
 
		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);

		Thread.sleep(3000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.Product-Detial-side-modal-Scrollbar")));

		driver.navigate().to(Courses);

		// Wait for the hamburger icons to be visible
		// Wait until the first course item is visible
		WebElement firstCourse = wait.until(ExpectedConditions.visibilityOfElementLocated(
		    By.cssSelector("div.list-form-img-wrapper.edit-list-icon")));

		// Hover over the first course to reveal the hamburger icon
	
		action.moveToElement(firstCourse).perform();

		// Wait until the hamburger icon inside the first course is visible & clickable
		WebElement hamburgerIcon = wait.until(ExpectedConditions.elementToBeClickable(
		    firstCourse.findElement(By.cssSelector("a#dropdownMenu2 > i"))));
		hamburgerIcon.click();

		// Wait until "Edit Course" icon appears and click it
		WebElement editCourseIcon = wait.until(ExpectedConditions.elementToBeClickable(
		    By.cssSelector("i.fa-regular.fa-pencil")));
		editCourseIcon.click();

		// Wait until the Pricing tab is clickable and click it
		List<WebElement> pricingTabs = driver.findElements(
			    By.xpath("//span[contains(@class,'menu-title') and normalize-space()='Pricing']")
			);

			for (WebElement tab : pricingTabs) {
			    if (tab.isDisplayed()) {
			        tab.click();
			        break;
			    }
			}
 


		driver.findElement(By.cssSelector("#paidCourseTab")).click();
		driver.findElement(By.cssSelector("#connect-product")).click();;
		
		WebElement searchBox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#searchproductsDD")));
		searchBox1.sendKeys(Product_name);
		
		Thread.sleep(3000);
		
		driver.navigate().to(Products);
		
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
		    By.cssSelector("tbody tr"), 1
		));
		
		WebElement hamburgerButton = driver.findElement(
		    By.cssSelector("tbody tr:nth-of-type(2) button[aria-haspopup='menu']")
		);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hamburgerButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", hamburgerButton);

		WebElement deleteOption = wait.until(ExpectedConditions.elementToBeClickable(
			    By.xpath("//button[.//span[contains(@class,'text-[#FF0000] capitalize')]]")
			));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteOption);

		WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//button[contains(@class,'btn-black') and .//span[normalize-space()='Yes, Confirm']]")
		));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
		
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0, 0);");

		WebElement settingsTab = wait.until(ExpectedConditions.elementToBeClickable(
		    By.cssSelector("button:has(svg[data-icon='gear'])") 
		));

		js.executeScript("arguments[0].scrollIntoView(true);", settingsTab);
		js.executeScript("arguments[0].click();", settingsTab);

		WebElement flatTaxInput = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//input[@type='number' and @placeholder='0']")
		    )
		);

		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", flatTaxInput);
		
		try {
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(
		        By.cssSelector(".custom-setting-region-tab")
		    ));
		} catch (TimeoutException e) {
		    System.out.println("Overlay didn't disappear — continuing anyway");
		}

		wait.until(ExpectedConditions.elementToBeClickable(flatTaxInput)).click();

		flatTaxInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		flatTaxInput.sendKeys(Keys.BACK_SPACE);
		flatTaxInput.sendKeys("15");

		WebElement saveBtn1 = wait.until(ExpectedConditions.elementToBeClickable(
		    By.cssSelector("header button:nth-child(2) span:nth-child(1)")
		));
		saveBtn1.click();

		WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(
		    By.cssSelector(".rotate-45")
		));
		closeBtn.click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("input[placeholder=\"Search Products\"]")).clear();
	
	    driver.findElement(By.cssSelector("input[placeholder=\"Search Products\"]")).sendKeys(Product_name);
	  	
		try {
		    By productLink = By.cssSelector("#result-item-0 > a");
		   
		    WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(productLink));
		    
		    element1.click();
		    
		} catch (Exception e) {
			
		    System.out.println("DEBUG: Product element not found or not clickable.");
		    System.out.println("Page Title: " + driver.getTitle());
		    System.out.println("Current URL: " + driver.getCurrentUrl());
		    
		    Assert.fail("Product Listing Not Found");
		}

		//Check if we can active and inactive a product on test account
		driver.findElement(By.cssSelector("button[role=\"switch\"]>span")).click();
		driver.findElement(By.cssSelector("div.rt-CardInner>div>button:nth-of-type(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn-small[type=\"submit\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.shadow-toaster")));
		driver.findElement(By.cssSelector("div.shadow-toaster>div>button")).click();
		
		//validate Product is inactive
		driver.findElement(By.id("orderPagesFunnel")).click();
		
		String pwindo = driver.getWindowHandle();
		
		driver.findElement(By.cssSelector("a[href*='deposyt.store/checkout?id=prod_']")).click();
	
		
		String Checkout_page = null;
		for(String Tab: driver.getWindowHandles())
		{
			driver.switchTo().window(Tab);
			Checkout_page = Tab;
		}
		
		Thread.sleep(3000);
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Product Currently Unavailable']")));
		}
		catch(TimeoutException e)
		{
			try
			{
				driver.findElement(By.xpath("//span[text()[normalize-space() = 'Order Summary']]"));
				Assert.assertTrue(false, "Product Not Get Set as inactive");
			}
			catch(NoSuchElementException e1)
			{
				Assert.assertTrue(false, "Check Locater for 'product not available' Line'");
			}
			
		}
		 
		driver.switchTo().window(pwindo);
		
		//Making Product active 
		driver.findElement(By.cssSelector("button[role=\"switch\"]>span")).click();
		driver.findElement(By.cssSelector("div.rt-CardInner>div>button:nth-of-type(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn-small[type=\"submit\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.shadow-toaster")));
		driver.findElement(By.cssSelector("div.shadow-toaster>div>button")).click();
		
		//Check by test purchasing that the product can be purchased in test account
		driver.switchTo().window(Checkout_page);
		driver.navigate().refresh();
		
		try
		{
			driver.findElement(By.xpath("//span[text()[normalize-space() = 'Order Summary']]"));
		}
		catch(TimeoutException e)
		{
			Assert.assertTrue(false, "Product Not Get Set as active");
		}
		
		//Filling Contact information
		
		
		WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emailInput.sendKeys(WMLogin);

		driver.findElement(By.id("first_name")).sendKeys(F_Name);
		driver.findElement(By.id("last_name")).sendKeys(L_Name);
		driver.findElement(By.id("phone")).sendKeys(Number);
		driver.findElement(By.id("street")).sendKeys(Street_Add);
		driver.findElement(By.id("state")).sendKeys(State);
		driver.findElement(By.id("city")).sendKeys(City);
		driver.findElement(By.id("postal_code")).sendKeys(Pincode);
		
		//Payment Information
		driver.findElement(By.id("cardHolderName")).sendKeys(F_Name+" "+L_Name);
		driver.findElement(By.id("cardNumber")).sendKeys(Card_Number);
		driver.findElement(By.id("cardCVV")).sendKeys(CVV);
		
		driver.findElement(By.cssSelector("button[aria-controls=\"monthDropdown\"]>div")).click();
		driver.findElement(By.cssSelector("#monthDropdown>button")).click();
		driver.findElement(By.cssSelector("button[aria-controls=\"yearDropdown\"]>div")).click();
		driver.findElement(By.cssSelector("#yearDropdown>button:nth-of-type(2)")).click();
		

		WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[role='checkbox']")));
		checkbox.click();

		// Small pause to let button enable
		Thread.sleep(2000);

		// Try locating make payment button with XPath by visible text
		WebElement makePaymentBtn = wait.until(ExpectedConditions.elementToBeClickable(
		    By.cssSelector("button[type = 'submit']")
		));

		makePaymentBtn.click();

		Thread.sleep(3000);

		 
		//Validate order Summery - Customer details
		// Wait for the Customer section to be present and visible
		

		String Order_Name = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//span[text() = 'Customer']//following-sibling::div//span"))).getText().trim();

		String Order_Mail = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//span[text() = 'Customer']//following-sibling::div//p"))).getText().trim();

		String Order_Phone = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("//span[text() = 'Customer']//following-sibling::div//span[2]")))
		        .getText().replace("-", "").trim();

		// Debug print
		System.out.println(Order_Name);
		System.out.println(Order_Mail);
		System.out.println(Order_Phone);

		// Assertions
		    Assert.assertEquals(Order_Name, F_Name + " " + L_Name, "Customer name not matching on order summary");
		    Assert.assertEquals(Order_Mail, WMLogin, "Customer email not matching on order summary");

		    String subjectStaticPart = "Subscription Charge Receipt";
		    boolean mailReceived = ReceivedMail(subjectStaticPart);
		    Assert.assertTrue(mailReceived, "Confirmation email was NOT received.");
		}

		// Helper method at class level (not inside any other method)
	public Boolean ReceivedMail(String subjectStaticPart) throws InterruptedException {
	    Actions action = new Actions(driver);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    String mainWindow = driver.getWindowHandle();
	    driver.switchTo().newWindow(WindowType.TAB);
	    driver.get(WMURL);

	    driver.findElement(By.name("user")).sendKeys(WMLogin);
	    action.sendKeys(Keys.TAB).sendKeys(WMPass).sendKeys(Keys.ENTER).build().perform();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("quicksearchbox")));

	    WebElement searchBox = driver.findElement(By.id("quicksearchbox"));
	    searchBox.clear();
	    searchBox.sendKeys(subjectStaticPart);
	    action.sendKeys(Keys.ENTER).build().perform();

	    Thread.sleep(3000);

	    List<WebElement> allSubjects = driver.findElements(By.cssSelector("tr.message > td.subject > a > span"));
	    WebElement matchedEmail = null;

	    for (WebElement subject : allSubjects) {
	        String text = subject.getText();
	        if (text.startsWith(subjectStaticPart) && text.contains("subs_")) {
	            matchedEmail = subject;
	            break;
	        }
	    }

	    if (matchedEmail == null) {
	        System.out.println("No matching email found with subject starting with: " + subjectStaticPart);
	        driver.close();
	        driver.switchTo().window(mainWindow);
	        return false;
	    }

	    action.doubleClick(matchedEmail).perform();

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#messagebody")));
	    WebElement messageBody = driver.findElement(By.cssSelector("div#messagebody"));
	    String messageText = messageBody.getText();

	    System.out.println("Message Body Preview: " + (messageText.length() > 200 ? messageText.substring(0, 200) + "..." : messageText));

	    // Basic checks for text presence
	    boolean isValidText = messageText.contains("Subscription ID") && messageText.contains("subs_");

	    // Check Customer Hub link presence
	    WebElement customerHubLink = null;
	    try {
	        customerHubLink = messageBody.findElement(By.xpath("//a[contains(text(),'My Customer Hub')]"));
	    } catch (NoSuchElementException e) {
	        System.out.println("Customer Hub link not found");
	    }

	    boolean isCustomerHubPresent = (customerHubLink != null);

	    // Check all links in email
	    List<WebElement> allLinks = messageBody.findElements(By.tagName("a"));
	    boolean allLinksValid = true;
	    for (WebElement link : allLinks) {
	        String href = link.getAttribute("href");
	        if (href == null || href.isEmpty()) {
	            System.out.println("Link with empty href found");
	            allLinksValid = false;
	            break;
	        }
	        // Open link in new tab and verify it loads (simplified)
	        String originalWindow = driver.getWindowHandle();
	        driver.switchTo().newWindow(WindowType.TAB);
	        driver.get(href);

	        Thread.sleep(3000);  // wait for page load (better to use wait for page load in real test)

	        // Simple check: title or URL should not contain '404' or 'Error'
	        String pageTitle = driver.getTitle().toLowerCase();
	        String currentUrl = driver.getCurrentUrl().toLowerCase();
	        if (pageTitle.contains("404") || pageTitle.contains("error") || currentUrl.contains("404") || currentUrl.contains("error")) {
	            System.out.println("Broken link found: " + href);
	            allLinksValid = false;
	        }
	        driver.close();
	        driver.switchTo().window(originalWindow);

	        if (!allLinksValid) break;
	    }

	    // Open and verify Customer Hub link separately (optional if already checked above)
	    boolean isCustomerHubWorking = false;
	    if (isCustomerHubPresent) {
	        String hubUrl = customerHubLink.getAttribute("href");
	        String originalWindow = driver.getWindowHandle();
	        driver.switchTo().newWindow(WindowType.TAB);
	        driver.get(hubUrl);

	        Thread.sleep(3000);

	        String pageTitle = driver.getTitle().toLowerCase();
	        String currentUrl = driver.getCurrentUrl().toLowerCase();
	        if (!pageTitle.contains("404") && !pageTitle.contains("error") && !currentUrl.contains("404") && !currentUrl.contains("error")) {
	            isCustomerHubWorking = true;
	        }
	        driver.close();
	        driver.switchTo().window(originalWindow);
	    }

	    driver.close(); 
	    driver.switchTo().window(mainWindow);

	    // Final result: all validations must pass
	    return isValidText && isCustomerHubPresent && allLinksValid && isCustomerHubWorking;
	}
}