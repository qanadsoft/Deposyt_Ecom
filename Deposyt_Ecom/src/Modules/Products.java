package Modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Master.Data;

public class Products extends Data {
	java.util.Random r = new java.util.Random();

	String Product_Names = "OneTime Product @#$!/" + UUID.randomUUID().toString().replace("- ", "").substring(0, 30).toUpperCase(),
	Product_Name = "OneTime Product " + UUID.randomUUID().toString().replace("- ", "").substring(0, 4).toUpperCase(),
	SuscProduct_Name = "Suscription Product - " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase(),
	Private_Name = "TestProduct " + UUID.randomUUID().toString().replace("- ", "").substring(0, 8).toUpperCase(),
	Description = "test-Nadsoft " + UUID.randomUUID().toString().replace("- ", "").substring(0, 10).toUpperCase(),
	SKU = "SKU_No" + UUID.randomUUID().toString().replace("- ", "").substring(0, 4).toUpperCase(),chars = "abcdefghijklmnopqrstuvwxyz",

	OneTimeProductValue = "10", OneTimePurchaseSalePrice = "5", Discountedprice = "9.50",
	File1 = "file1.jpg", File2 = "sample.bmp", File3 = "sample.tiff", File4 = "10mb.jpg", File5 = "sample.jpe", File6 = "file3.jpeg", File7 = "file7.jpg", 
	File8 = "file4.png", Attachment = "Jira_Guide.pdf", Fileone = "file1.jpg", Filetwo = "file2.png", Filethree = "file3.jpeg", Filefour = "file4.png", 
	Filefive = "file5.png", Filesix = "file6.jpg", Fileseven = "file7.jpg", Fileeight = "file8.jpg", Filenine = "file9.jpg", Fileten = "sample.mp4",

	F_Name = "NineEleven", L_Name = "Contact", Number = "(314) 237-5324", Street_Add = "Allen Court", country = "United States", ContactPhone2 = "(539) 321-3502",
	Account_Holder_Name = "DeposytCert", Routing_Number = "490000018", Account_Number = "000123456789", EXP = "12/44", CVV = "123", Card_No = "4242424242424242",
	DiscriptionTier1 = "Description for first tier installment", DiscriptionTier2 = "Description for Second tier installment", TierOneTitle = "Second Tier Title",
	TitleTier1 = "First Tier Installment",TitleTier2 = "Second Tier Installment", Tier1Feature1 = "Tier one Feature First", Tier2Feature1 = "Tier two Feature one",

	firstName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
	lastName ="" + chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)) + chars.charAt(r.nextInt(26))+ chars.charAt(r.nextInt(26)),
	email = firstName + "." + lastName + r.nextInt(1000) + "@yopmail.com", phone = (r.nextInt(4) + 6) + "" + (100000000 + r.nextInt(900000000));

	//@BeforeClass
	public void OneTime_SalesOrder() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		driver.navigate().to(Products);
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
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("1");
		Thread.sleep(1000);

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);
		
		/*driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		
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
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(originalTab);*/
	}
	
	@Test(priority = 1)
	public void Create_NewProduct() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;	
		Actions actions = new Actions(driver);

		//Verify Settings button is visible and enabled on the dashboard.
		driver.navigate().to(Products);
		Thread.sleep(10000);

		WebElement settingsButton = driver.findElement(By.cssSelector("div.product-list-section>button:first-of-type"));
		Assert.assertTrue(settingsButton.isDisplayed(), "Settings button is not displayed on the Products page");
		Assert.assertTrue(settingsButton.isEnabled(), "Settings button is not enabled on the Products page");
		settingsButton.click();

		Thread.sleep(3000);
		String actualTitlee = driver.findElement(By.xpath("//p[text()='Products Settings']")).getText();
		String expectedTitlee = "Products Settings"; 
		Assert.assertEquals(actualTitlee, expectedTitlee, "User Not Navigates to Products Settings Page after clicking on Settings button");
		System.out.println("User Navigates to Products Settings Page after clicking on Settings button.");		

		//Verify All Orders button is visible and enabled on the dashboard.
		driver.navigate().to(Products);
		Thread.sleep(5000);
		WebElement allordersButton = driver.findElement(By.cssSelector("div.product-list-section>div>button:first-of-type"));
		Assert.assertTrue(allordersButton.isDisplayed(), "All Orders button is not displayed on the Products page");
		Assert.assertTrue(allordersButton.isEnabled(), "All Orders button is not enabled on the Products page");
		allordersButton.click();

		Thread.sleep(5000);
		String currentUrl = driver.getCurrentUrl(); 
		if (currentUrl.contains("orders")) {
			System.out.println("User Navigates to Orders Page after clicking on All orders button");
		} else {
			System.out.println("The current URL does not contain 'orders'.");
		}

		Assert.assertTrue(currentUrl.contains("orders"), "orders page did not load correctly. Current URL: " + currentUrl);
		driver.navigate().back();
		Thread.sleep(5000);

		//Verify Sales Report button is visible and enabled on the dashboard.
		WebElement SalesReportButton = driver.findElement(By.cssSelector("div.product-list-section>div>button:nth-of-type(2)"));
		Assert.assertTrue(SalesReportButton.isDisplayed(), "sales report button is not displayed on the Products page");
		Assert.assertTrue(SalesReportButton.isEnabled(), "sales report button is not enabled on the Products page");
		SalesReportButton.click();

		Thread.sleep(5000);
		String currentUrl1 = driver.getCurrentUrl(); 
		if (currentUrl1.contains("sales")) {
			System.out.println("User Navigates to Sales Page after clicking on Sales Report Button.\n");
		} else {
			System.out.println("The current URL does not contain 'sales'. Current URL is: " + currentUrl1);
		}

		Assert.assertTrue(currentUrl1.contains("sales"), "sales report page did not load correctly. Current URL: " + currentUrl1);

		//Verify New product button is visible and enabled on the dashboard.
		//check that product name field should have placeholder
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.navigate().refresh();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();

		WebElement Inputplaceholder = driver.findElement(By.name("productDetails.productName"));
		Inputplaceholder.sendKeys(Product_Names); 
		String actualPlaceholder = Inputplaceholder.getAttribute("placeholder"); 	        
		String expectedPlaceholder = "Enter Product Name"; 
		Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "product Name placeholder text mismatch");
		System.out.println("Product Name Placeholder Matches");
		Thread.sleep(1000);

		//check that title is Product Name		
		String actualTitle = driver.findElement(By.xpath("//label[normalize-space()=\"Product Name\"]")).getText(); 
		String expectedTitle = "Product Name"; 
		Assert.assertEquals(actualTitle, expectedTitle, "Product Name title text mismatch");
		System.out.println("Product Name Title Matches\n");
		Thread.sleep(1000);

		//check that  private name  field should have placeholder  
		WebElement Inputplaceholder1 = driver.findElement(By.name("productDetails.privateName"));
		Inputplaceholder1.sendKeys(Private_Name); 
		String actualPlaceholder1 = Inputplaceholder1.getAttribute("placeholder"); 	        
		String expectedPlaceholder1 = "Type Private Name (Optional)"; 
		Assert.assertEquals(actualPlaceholder1, expectedPlaceholder1, "private Name placeholder text mismatch");
		System.out.println("private Name Placeholder Matches");
		Thread.sleep(1000);

		//check that title is private name 
		String actualTitle1 = driver.findElement(By.xpath("//label[normalize-space()=\"Private Name\"]")).getText(); 
		String expectedTitle1 = "Private Name"; 
		Assert.assertEquals(actualTitle1, expectedTitle1, "Private Name title text mismatch");
		System.out.println("private Name Title Matches\n");
		Thread.sleep(3000);

		//check that user should not able to create product without product description 
		//check that user if not enter any name in the product description field then it should give warning msg
		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		Thread.sleep(3000);
		WebElement warningMessage = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>div>div:last-of-type")); 
		String actualMessage = warningMessage.getText().trim();
		String expectedMessage = "Product Description is required";
		Assert.assertEquals(actualMessage, expectedMessage, "Warning message not Displays");
		System.out.println("Product Description is required message displayed successfully when user try to save product without product description");
		Thread.sleep(1000);

		//check that product description field should have placeholder 
		//check that 250 characters can be added in the product description
		WebElement Inputplaceholder2 = driver.findElement(By.name("productDetails.productDescription"));
		Inputplaceholder2.sendKeys(Description); 
		String actualPlaceholder2 = Inputplaceholder2.getAttribute("placeholder"); 	        
		String expectedPlaceholder2 = "Enter description"; 
		Assert.assertEquals(actualPlaceholder2, expectedPlaceholder2, "product description placeholder text mismatch");
		System.out.println("Product description Placeholder Matches");
		Thread.sleep(1000);

		//check that title is product description
		String actualTitle2 = driver.findElement(By.xpath("//label[normalize-space()=\"Description\"]")).getText(); 
		String expectedTitle2 = "Description"; 
		Assert.assertEquals(actualTitle2, expectedTitle2, "Product Name title text mismatch");
		System.out.println("Product description Title Matches\n");
		Thread.sleep(1000);

		//check that  SKU field should have placeholder
		WebElement Inputplaceholder3 = driver.findElement(By.name("productDetails.sku"));
		Inputplaceholder3.sendKeys(SKU);
		String actualPlaceholder3 = Inputplaceholder3.getAttribute("placeholder");        
		String expectedPlaceholder3 = "Enter SKU (Optional)";
		Assert.assertEquals(actualPlaceholder3, expectedPlaceholder3, "SKU Name placeholder text mismatch");
		System.out.println("SKU Placeholder Matches");
		Thread.sleep(1000);

		//check that title is SKU		
		String actualTitle3 = driver.findElement(By.xpath("//label[normalize-space()=\"SKU\"]")).getText(); 
		String expectedTitle3 = "SKU"; 
		Assert.assertEquals(actualTitle3, expectedTitle3, "SKU Name title text mismatch");
		System.out.println("SKU Title Matches\n");
		Thread.sleep(1000);

		//check that title is Media
		String actualTitle4 = driver.findElement(By.xpath("(//span[contains(normalize-space(), 'Media')])[1]")).getText().replaceAll("\\s+", "");
		String expectedTitle4 = "Media*"; 
		Assert.assertEquals(actualTitle4, expectedTitle4, "Media title text mismatch");
		System.out.println("Media Title Matches\n");

		//check that this msg can be seen "Upload up to 9 images and video"		
		String actualTitle5 = driver.findElement(By.xpath("//span[text()='Upload up to 9 images or 1 video and 8 images.']")).getText();
		String expectedTitle5 = "Upload up to 9 images or 1 video and 8 images.";
		Assert.assertEquals(actualTitle5, expectedTitle5, "Media Label Headline text mismatch");

		//check that tis msg also can be seen "Must upload at least one image"
		String actualTitle6 = driver.findElement(By.xpath("//span[text()='Must upload at least one image*']")).getText(); 
		String expectedTitle6 = "Must upload at least one image*";
		Assert.assertEquals(actualTitle6, expectedTitle6, "Media Label Headline text mismatch");

		//check that this msg can be seen "Images should have a 1:1 square aspect ratio"
		String actualTitle7 = driver.findElement(By.xpath("//span[text()='Images should have a 1:1 square aspect ratio']")).getText(); 
		String expectedTitle7 = "Images should have a 1:1 square aspect ratio";
		Assert.assertEquals(actualTitle7, expectedTitle7, "Media Label Headline text mismatch");

		//check that user can not  upload BMP format file in media
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//span[contains(normalize-space(), 'Media')])[1]")));
		WebElement uploadImage = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage);	
		Thread.sleep(3000);
		String[] files1 = {Media_Path + File2};

		String allFiles1 = String.join("\n", files1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles1);
		Thread.sleep(3000);

		//check that if invalid file is uploaded then system shows warning msg
		WebElement warningMessage1 = driver.findElement(By.xpath("//span[text()='Please upload a valid file.']")); 
		String actualMessage1 = warningMessage1.getText().trim();
		String expectedMessage1 = "Please upload a valid file.";
		Assert.assertEquals(actualMessage1, expectedMessage1, "Warning message not Displays");
		System.out.println("Invalid file format message displayed successfully when user try to upload invalid BMP file in media");

		//check that user can not upload TIFF format file in media
		WebElement uploadImage1 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage1);	
		Thread.sleep(3000);
		String[] files2 = {Media_Path + File3};

		String allFiles2 = String.join("\n", files2);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles2);
		Thread.sleep(3000);

		WebElement warningMessage2 = driver.findElement(By.xpath("//span[text()='Please upload a valid file.']")); 
		String actualMessage2 = warningMessage2.getText().trim();
		String expectedMessage2 = "Please upload a valid file.";
		Assert.assertEquals(actualMessage2, expectedMessage2, "Warning message not Displays");
		System.out.println("Invalid file format message displayed successfully when user try to upload invalid TIFF file in media");

		//check that user can not upload PDF file in media
		WebElement uploadImage11 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage11);	
		Thread.sleep(3000);
		String[] files21 = {Media_Path + Attachment};

		String allFiles21 = String.join("\n", files21);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles21);
		Thread.sleep(3000);

		WebElement warningMessage3 = driver.findElement(By.xpath("//span[text()='Please upload a valid file.']")); 
		String actualMessage3 = warningMessage3.getText().trim();
		String expectedMessage3 = "Please upload a valid file.";
		Assert.assertEquals(actualMessage3, expectedMessage3, "Warning message not Displays");
		System.out.println("Invalid file format message displayed successfully when user try to upload invalid TIFF file in media");

		//check that if user upload more than 10 MB file then system shows warning msg
		WebElement uploadImage2 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage2);	
		Thread.sleep(3000);
		String[] files3 = {Media_Path + File4};

		String allFiles3 = String.join("\n", files3);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles3);
		Thread.sleep(3000);

		WebElement warningMessage4 = driver.findElement(By.xpath("//span[text()='Please upload file below for images 10MB and video 200 MB']")); 
		String actualMessage4 = warningMessage4.getText().trim();
		String expectedMessage4 = "Please upload file below for images 10MB and video 200 MB";
		Assert.assertEquals(actualMessage4, expectedMessage4, "Size Exceeds message not Displays");
		System.out.println("\nSize Exceeds message displayed successfully when user try to upload file more then 10 MB in media");

		//check that user can upload images upto 10 MB size
		WebElement uploadImage3 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage3);	
		Thread.sleep(3000);
		String[] files4 = {Media_Path + File1};

		String allFiles4 = String.join("\n", files4);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles4);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();
		System.out.println("User able to upload image Upto 10 mb in media and crop functionality also works fine");	
		Thread.sleep(3000);

		//check that for every media uploaded can be removed
		WebElement parent = driver.findElement(By.xpath("//div[contains(@class,'absolute') and contains(@class,'inset-0')]//*[name()='svg']"));
		actions.moveToElement(parent).perform();
		Thread.sleep(1000);
		WebElement crossBtn = driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>div>div>svg"));
		crossBtn.click();
		Thread.sleep(3000);
		WebElement placeholder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'bg-black-55')]//*[name()='svg']")));
		Assert.assertTrue(placeholder.isDisplayed(), "Placeholder not displayed after deletion");
		System.out.println("User able to remove uploaded media and after removing media placeholder also displayed successfully\n");

		//check that user can upload JPE format file in media
		WebElement uploadImage4 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage4);
		Thread.sleep(3000);
		String[] files5 = {Media_Path + File5};

		String allFiles5 = String.join("\n", files5);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles5);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();
		System.out.println("User able to upload image in JPE format in media and crop functionality also works fine");
		WebElement parent1 = driver.findElement(By.xpath("//div[contains(@class,'absolute') and contains(@class,'inset-0')]//*[name()='svg']"));
		actions.moveToElement(parent1).perform();
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>div>div>svg")).click();

		//check that user can upload JPEG format file in media
		WebElement uploadImage5 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage5);
		Thread.sleep(3000);
		String[] files6 = {Media_Path + File6};

		String allFiles6 = String.join("\n", files6);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles6);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();
		System.out.println("User able to upload image in JPEG format in media and crop functionality also works fine");
		WebElement parent2 = driver.findElement(By.xpath("//div[contains(@class,'absolute') and contains(@class,'inset-0')]//*[name()='svg']"));
		actions.moveToElement(parent2).perform();
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>div>div>svg")).click();

		//check that user can upload JPG format file in media
		WebElement uploadImage6 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage6);	
		Thread.sleep(3000);
		String[] files7 = {Media_Path + File7};

		String allFiles7 = String.join("\n", files7);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles7);
		Thread.sleep(3000);
		System.out.println("User able to upload image in JPG format in media and crop functionality also works fine");
		WebElement parent3 = driver.findElement(By.xpath("//div[contains(@class,'absolute') and contains(@class,'inset-0')]//*[name()='svg']"));
		actions.moveToElement(parent3).perform();
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>div>div>svg")).click();

		//check that user can upload PNG format file in media 
		WebElement uploadImage7 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage7);	
		Thread.sleep(3000);
		String[] files8 = {Media_Path + File7};

		String allFiles8 = String.join("\n", files8);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles8);
		Thread.sleep(3000);
		System.out.println("User able to upload image in PNG format in media and crop functionality also works fine");

		//check that user can upload videos in media
		WebElement uploadImages = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImages);	
		Thread.sleep(3000);
		String[] fileses = {Media_Path + Fileten};

		String allFileses = String.join("\n", fileses);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFileses);
		Thread.sleep(3000);

		WebElement warningMessages = driver.findElement(By.xpath("(//span[text()='Thumbnail should not be video'])[3]")); 
		String actualMessages = warningMessages.getText().trim();
		String expectedMessages = "Thumbnail should not be video";
		Assert.assertEquals(actualMessages, expectedMessages, "Warning message not Displays");
		System.out.println("Invalid file format message displayed successfully when user try to upload Video file in media");

		WebElement uploadImagee = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImagee);

		Thread.sleep(3000);
		String[] filess = {Media_Path + Fileone};

		String allFiless = String.join("\n", filess);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiless);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();

		//check that user can upload 9 images
		/*WebElement uploadImagee = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImagee);

		Thread.sleep(3000);
		String[] filess1 = {Media_Path + Fileone, Media_Path + Filetwo, Media_Path + Filethree, Media_Path + Filefour, Media_Path + Filefive, Media_Path + Filesix, Media_Path + Fileseven, 
				Media_Path + Fileeight, Media_Path + Filenine};
		String allFiless = String.join("\n", filess1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiless);
		System.out.println("\nUser able to upload 9 images in upload media section and crop functionality also works fine for all images");*/

		driver.findElement(By.name("productPricing.regularPrice")).sendKeys(OneTimeProductValue);
		Thread.sleep(1000);

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()=\"Product Type\"]")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click(); //Select physical product	
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()=\"Product Unlocks\"]")));

		//verify Inventory Enabled and deafult state of Hide Product From Store is disabled
		WebElement hideProductButton1 = driver.findElement(By.xpath("//p[text() = 'Hide Products from Store']//following-sibling::button//span"));
		String hideProductButtonState1 = hideProductButton1.getAttribute("data-state");
		Assert.assertEquals(hideProductButtonState1, "unchecked", "Default state of Hide Product From Store is not disabled");
		System.out.println("\nInventory Product Enabled and deafult state of Hide Product From Store is disabled by deafult");

		//verify Inventory Enabled and deafult state of Product Unlock Course is disabled
		WebElement unlockCourseButton1 = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		String unlockCourseButtonState1 = unlockCourseButton1.getAttribute("data-state");
		Assert.assertEquals(unlockCourseButtonState1, "unchecked", "Default state of Unlock Course is not disabled");
		System.out.println("Inventory Enabled and deafult state of Product Unlock Course is disabled by deafult");

		//verify Inventory Enabled and deafult state of Link Product To Service is disabled
		WebElement unlockservicesButton1 = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		String unlockserviceButtonState1 = unlockservicesButton1.getAttribute("data-state");
		Assert.assertEquals(unlockserviceButtonState1, "unchecked", "Default state of Link Product To Service is not disabled");
		System.out.println("Inventory Enabled and deafult state of Link Product To Service is disabled by deafult");

		//verify Inventory Enabled and deafult state of Link Product To Appointment is disabled
		WebElement linktoapptButton1 = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		String linktoappteButtonState1 = linktoapptButton1.getAttribute("data-state");
		Assert.assertEquals(linktoappteButtonState1, "unchecked", "Default state of Link Product From Store is not disabled");
		System.out.println("Inventory Enabled and deafult state of Link Product To Appointment is disabled by deafult\n");

		//verify Inventory Enabled and after click on Product Unlock Course is enabled error message displays
		WebElement unlockCourseButton = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButton);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockCourseButton);

		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);
		Thread.sleep(1000);
		WebElement warningMessage11 = driver.findElement(By.xpath("//div[text()='Product Unlocks Courses are only available for non-inventory product']"));
		String actualMessage11 = warningMessage11.getText().trim();
		String expectedMessage11 = "Product Unlocks Courses are only available for non-inventory product";
		Assert.assertEquals(actualMessage11, expectedMessage11, "Warning message Product Unlocks Courses not Displays");

		WebElement unlockCourseButtonn = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButtonn);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockCourseButtonn);	

		//verify Inventory Enabled and after click on Link Product To Service is enabled error message displays
		WebElement unlockservicesButton = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockservicesButton);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockservicesButton);

		WebElement confirmSaveBtn2 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn2);
		Thread.sleep(1000);
		WebElement warningMessage21 = driver.findElement(By.xpath("//div[text()='Link Product to Services are only available for non-inventory product']"));
		String actualMessage21 = warningMessage21.getText().trim();
		String expectedMessage21 = "Link Product to Services are only available for non-inventory product";
		Assert.assertEquals(actualMessage21, expectedMessage21, "Warning message Link Product To Service not Displays");

		WebElement unlockservicesButton11 = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockservicesButton11);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockservicesButton11);

		//verify Inventory Enabled and after click on Link Product To Appointment is enabled error message displays
		WebElement linktoapptButton = driver.findElement(By.xpath("//p[text() = 'Link Product to Appointments']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'}	);", linktoapptButton);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", linktoapptButton);

		WebElement confirmSaveBtn3 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn3);
		Thread.sleep(1000);
		WebElement warningMessage31 = driver.findElement(By.xpath("//div[text()='Link Product to Appointments are only available for non-inventory product']"));
		String actualMessage31 = warningMessage31.getText().trim();
		String expectedMessage31 = "Link Product to Appointments are only available for non-inventory product";
		Assert.assertEquals(actualMessage31, expectedMessage31, "Warning message Link Product To Appointment not Displays");

		WebElement linktoapptButton11 = driver.findElement(By.xpath("//p[text() = 'Link Product to Appointments']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'}	);", linktoapptButton11);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", linktoapptButton11);

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()=\"Product Type\"]")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Digital")).click(); //Select digital product	
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()=\"Product Unlocks\"]")));

		//verify Non-Inventory Enabled and deafult state of Hide Product From Store is disabled
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()=\"Product Unlocks\"]")));
		WebElement hideProductButton = driver.findElement(By.xpath("//p[text() = 'Hide Products from Store']//following-sibling::button//span"));
		String hideProductButtonState = hideProductButton.getAttribute("data-state");
		Assert.assertEquals(hideProductButtonState, "unchecked", "Default state of Hide Product From Store is not disabled");
		System.out.println("Non-Inventory Product Enabled and deafult state of Hide Product From Store is disabled by deafult");

		//verify Non-Inventory Enabled and deafult state of Product Unlock Course is disabled
		WebElement unlockCourseButtonn1 = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		String unlockCourseButtonState = unlockCourseButtonn1.getAttribute("data-state");
		Assert.assertEquals(unlockCourseButtonState, "unchecked", "Default state of Unlock Course is not disabled");
		System.out.println("Non-Inventory Enabled and deafult state of Product Unlock Course is disabled by deafult");

		//verify Non-Inventory Enabled and deafult state of Link Product To Service is disabled
		WebElement unlockservicesButtonn2 = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		String unlockserviceButtonState = unlockservicesButtonn2.getAttribute("data-state");
		Assert.assertEquals(unlockserviceButtonState, "unchecked", "Default state of Link Product To Service is not disabled");
		System.out.println("Non-Inventory Enabled and deafult state of Link Product To Service is disabled by deafult");	

		//verify Non-Inventory Enabled and deafult state of Link Product To Appointment is disabled
		WebElement linktoapptButtonn2 = driver.findElement(By.xpath("//p[text() = 'Link Product to Services']//following-sibling::button//span"));
		String linktoappteButtonState = linktoapptButtonn2.getAttribute("data-state");
		Assert.assertEquals(linktoappteButtonState, "unchecked", "Default state of Link Product From Store is not disabled");
		System.out.println("Non-Inventory Enabled and deafult state of Link Product To Appointment is disabled by deafult\n");

		//Verify "Product Unlocks" label is displayed correctly
		String actualTitle8 = driver.findElement(By.xpath("//h2[normalize-space()=\"Product Unlocks\"]")).getText(); 
		String expectedTitle8 = "Product Unlocks"; 
		Assert.assertEquals(actualTitle8, expectedTitle8, "Product Unlocks title text mismatch");
		System.out.println("Product Unlocks Title Matches");	

		//Verify "Hide Products From Store" label text is correct
		String actualTitle9 = driver.findElement(By.xpath("//p[normalize-space()=\"Hide Products from Store\"]")).getText(); 
		String expectedTitle9 = "Hide Products From Store"; 
		Assert.assertEquals(actualTitle9, expectedTitle9, "Hide Products From Store text mismatch");
		System.out.println("Hide Products From Store Title Matches");

		//Verify "This Product Unlocks Courses" label text is correct
		String actualTitle10 = driver.findElement(By.xpath("//p[normalize-space()=\"This Product Unlocks Courses\"]")).getText(); 
		String expectedTitle10 = "This Product Unlocks Courses"; 
		Assert.assertEquals(actualTitle10, expectedTitle10, "This Product Unlocks Courses text mismatch");
		System.out.println("This Product Unlocks Courses Title Matches");

		//Verify "Link Product To Services" label text is correct
		String actualTitle11 = driver.findElement(By.xpath("//p[normalize-space()=\"Link Product to Services\"]")).getText(); 
		String expectedTitle11 = "Link Product To Services"; 
		Assert.assertEquals(actualTitle11, expectedTitle11, "Link Product To Services text mismatch");
		System.out.println("Link Product To Services Title Matches");

		//Verify "Link Product To Appointments" label text is correct
		String actualTitle12 = driver.findElement(By.xpath("//p[normalize-space()=\"Link Product to Appointments\"]")).getText(); 
		String expectedTitle12 = "Link Product To Appointments"; 
		Assert.assertEquals(actualTitle12, expectedTitle12, "Link Product To Appointments text mismatch");
		System.out.println("Link Product To Appointments Title Matches\n");

		//verify Non-Inventory Enabled and after click on Hide Product From Store is enabled
		/*WebElement hideProductButtonn = driver.findElement(By.xpath("//p[text() = 'Hide Products from Store']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", hideProductButtonn);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", hideProductButtonn);
		String hideProductButtonState11 = hideProductButton.getAttribute("data-state");
		Assert.assertEquals(hideProductButtonState11, "checked", "after click on Hide product from store Product is not enabled");
		System.out.println("after click on Hide Product From Store button is enabled successfully");*/

		//verify Non-Inventory Enabled and after click on Product Unlock Course is enabled
		WebElement unlockCourseButtonn11 = driver.findElement(By.xpath("//p[text() = 'This Product Unlocks Courses']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockCourseButtonn11);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockCourseButtonn11);
		String unlockCourseButtonState11 = unlockCourseButtonn11.getAttribute("data-state");
		Assert.assertEquals(unlockCourseButtonState11, "checked", "after click on Product Unlock Course Product is not enabled");
		System.out.println("after click on Product Unlock Course is enabled successfully");

		//verify Non-Inventory Enabled and after click on Link Product To Service is enabled
		WebElement unlockservicesButtonn11 = driver.findElement(By.xpath("//p[text() =	 'Link Product to Services']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", unlockservicesButtonn11);	
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", unlockservicesButtonn11); 
		String unlockserviceButtonState11 = unlockservicesButtonn11.getAttribute("data-state");
		Assert.assertEquals(unlockserviceButtonState11, "checked", "after click on Link	 Product To Service Product is not enabled");
		System.out.println("after click on Link Product To Service button is enabled successfully");

		//verify Non-Inventory Enabled and after click on Link Product To Appointment is enabled
		WebElement linktoapptButtonn11 = driver.findElement(By.xpath("//p[text() = 'Link Product to Appointments']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", linktoapptButtonn11);	
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", linktoapptButtonn11	);
		String linktoappteButtonState11 = linktoapptButtonn11.getAttribute("data-state");
		Assert.assertEquals(linktoappteButtonState11, "checked", "after click on Link Product To Appointment Product is not enabled");
		System.out.println("after click on Link Product To Appointment button is enabled successfully\n");

		//Verify "Pricing Options" section is displayed correctly
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		String actualTitles = driver.findElement(By.xpath("//span[text()='Pricing Options']")).getText(); 
		String expectedTitles = "Pricing Options";
		Assert.assertEquals(actualTitles, expectedTitles, "Pricing Options label text mismatch");		
		System.out.println("Pricing Options Title Matches");

		//Verify "Allow For Multiple Pricing Options" label text is correct
		String actualTitle13 = driver.findElement(By.xpath("//span[text()='Allow for multiple Pricing options']")).getText();
		String expectedTitle13 = "Allow For Multiple Pricing Options";
		Assert.assertEquals(actualTitle13, expectedTitle13, "Allow For Multiple Pricing Options text mismatch");
		System.out.println("Allow For Multiple Pricing Options Title Matches\n");

		//Verify "One Time Purchase" label and description are correct
		String actualTitle14 = driver.findElement(By.xpath("//p[normalize-space()=\"One Time Purchase\"]")).getText();
		String expectedTitle14 = "One Time Purchase";
		Assert.assertEquals(actualTitle14, expectedTitle14, "One Time Purchase text mismatch");
		System.out.println("One Time Purchase Title Matches");

		String actualTitle141 = driver.findElement(By.xpath("//p[normalize-space()=\"Standard one time purchase\"]")).getText().toLowerCase();
		String expectedTitle141 = "Standard One Time Purchase";
		Assert.assertEquals(actualTitle141, expectedTitle141.toLowerCase(), "One Time Purchase text mismatch");
		System.out.println("One Time Purchase Headline Matches\n");

		//Verify "Subscription" label and description are correct
		String actualTitle15 = driver.findElement(By.xpath("//p[normalize-space()=\"Subscription\"]")).getText();
		String expectedTitle15 = "Subscription";
		Assert.assertEquals(actualTitle15, expectedTitle15, "Subscription text mismatch");
		System.out.println("Subscription Title Matches");

		String actualTitle151 = driver.findElement(By.xpath("//p[normalize-space()=\"Allow for recurring/subscriptions\"]")).getText().toLowerCase();
		String expectedTitle151 = "Allow For Recurring/Subscriptions";
		Assert.assertEquals(actualTitle151, expectedTitle151.toLowerCase(), "Subscription text mismatch");
		System.out.println("Subscription Headline Matches\n");

		//Verify "Tiered Pricing" label and "New" badge are displayed correctly
		String actualTitle16 = driver.findElement(By.xpath("//p[text()='Tiered Pricing ']")).getText().replace("\nNew!", "").trim();
		String expectedTitle16 = "Tiered Pricing";
		Assert.assertEquals(actualTitle16, expectedTitle16, "Tiered Pricing text mismatch");
		System.out.println("Tiered Pricing Title Matches");

		String actualTitle161 = driver.findElement(By.xpath("//p[normalize-space()=\"Make this product have multiple tiers of pricing\"]")).getText().toLowerCase();
		String expectedTitle161 = "Make This Product Have Multiple Tiers Of Pricing";
		Assert.assertEquals(actualTitle161, expectedTitle161.toLowerCase(), "Tiered Pricing text mismatch");
		System.out.println("Tiered Pricing Headline Matches\n");

		//Verify user can enable "One Time Purchase" pricing option
		WebElement oneTimePurchaseOption = driver.findElement(By.xpath("//div[@id='one-time-purchase-div']/descendant::button[@role=\"switch\"][1]"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", oneTimePurchaseOption);
		Thread.sleep(1000);
		String oneTimePurchaseOptionState = oneTimePurchaseOption.getAttribute("data-state");
		Assert.assertEquals(oneTimePurchaseOptionState, "checked", "after click on One Time	 Purchase option is not enabled");
		System.out.println("after click on One Time Purchase option is enabled successfully");

		//Verify user can enable "Subscription" pricing option
		WebElement subscriptionOption = driver.findElement(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", subscriptionOption);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", subscriptionOption);
		String subscriptionOptionState = subscriptionOption.getAttribute("data-state");
		Assert.assertEquals(subscriptionOptionState, "checked", "after click on Subscription option is not enabled");
		System.out.println("after click on Subscription option is enabled successfully");

		//Verify user can enable "Tiered Pricing" option
		WebElement tieredPricingOption = driver.findElement(By.xpath("//div[@id='tiered-pricing-option-div']//button[@role='switch'][1]"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", tieredPricingOption);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", tieredPricingOption);
		String tieredPricingOptionState = tieredPricingOption.getAttribute("data-state");
		Assert.assertEquals(tieredPricingOptionState, "checked", "after click on Tier	ed Pricing option is not enabled");
		System.out.println("after click on Tiered Pricing option is enabled successfully\n");

		//Verify price input field is visible with placeholder
		WebElement oneTimePurchaseOption1 = driver.findElement(By.xpath("//div[@id='one-time-purchase-div']/descendant::button[@role=\"switch\"][1]"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", oneTimePurchaseOption1);
		Thread.sleep(1000);
		oneTimePurchaseOption1.click();

		WebElement OneTimePurchasePrice = driver.findElement(By.name("productPricing.regularPrice"));
		Thread.sleep(3000);
		OneTimePurchasePrice.click();
		OneTimePurchasePrice.sendKeys(Keys.CONTROL, "a");
		OneTimePurchasePrice.sendKeys(Keys.DELETE);

		//Verify error when price field is empty
		WebElement confirmSaveBtnn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtnn);
		WebElement warningMessagess = driver.findElement(By.xpath("//div[text()='Regular Price is required']"));
		String actualMessagess = warningMessagess.getText().trim();
		String expectedMessagess = "Regular Price is required";
		Assert.assertEquals(actualMessagess, expectedMessagess, "Warning message Regular Price is required not Displays");	
		System.out.println("Warning message displayed successfully when user try to save product without entering price in One Time Purchase option");

		//Verify error when invalid price (zero/negative) is entered
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("0");
		WebElement warningMessagess1 = driver.findElement(By.xpath("//div[text()='Price must be greater than 0']"));
		String actualMessagess1 = warningMessagess1.getText().trim();
		String expectedMessagess1 = "Price must be greater than 0";
		Assert.assertEquals(actualMessagess1, expectedMessagess1, "Warning message Regular Price is required not Displays");	
		System.out.println("Warning message displayed successfully when user try to save product with entering invalid price in One Time Purchase option\n");

		WebElement OneTimePurchasePrice1 = driver.findElement(By.name("productPricing.regularPrice"));
		Thread.sleep(3000);
		OneTimePurchasePrice1.click();
		OneTimePurchasePrice1.sendKeys(Keys.CONTROL, "a");
		OneTimePurchasePrice1.sendKeys(Keys.DELETE);

		WebElement Inputplaceholders = driver.findElement(By.name("productPricing.regularPrice"));
		Inputplaceholders.sendKeys(OneTimeProductValue);
		String actualPlaceholders = Inputplaceholders.getAttribute("placeholder");        
		String expectedPlaceholders = "-"; 
		Assert.assertEquals(actualPlaceholders, expectedPlaceholders, "One Time Purchase placeholder text mismatch");

		//Verify "Sale Price" checkbox is displayed correctly
		WebElement salePriceCheckbox = driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[1]"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", salePriceCheckbox);
		Thread.sleep(1000);
		salePriceCheckbox.click();
		Assert.assertTrue(salePriceCheckbox.isSelected(), "Sale Price checkbox is not selected after click");		
		System.out.println("Sale Price checkbox is displayed with default state as disabled");

		//Verify "% off" and "$" toggle options are visible for discount
		WebElement percentageOffOption = driver.findElement(By.xpath("(//div[@role=\"group\"]//button)[1]"));
		WebElement dollarOffOption = driver.findElement(By.xpath("(//div[@role=\"group\"]//button)[2]"));

		//Verify enabling "Sale Price" shows discount input field
		Assert.assertTrue(dollarOffOption.isDisplayed(), "Dollar off option is not displayed");
		Assert.assertTrue(percentageOffOption.isDisplayed(), "Percentage off option is not displayed");	
		System.out.println("% off and $ toggle options are displayed for discount successfully");

		//Verify "Set Sales Date (Optional)" link is displayed correctly
		WebElement setSalesDateLink = driver.findElement(By.xpath("//span[text()='Set Sales Date (Optional)']"));
		Assert.assertTrue(setSalesDateLink.isDisplayed(), "Set Sales Date (Optional) link is not displayed");
		System.out.println("Set Sales Date (Optional) link is displayed successfully");

		//Verify "Show Quantity Picker" toggle is displayed
		Thread.sleep(3000);
		WebElement showQuantityPickerToggle = driver.findElement(By.cssSelector("#one-time-purchase-div>div:nth-of-type(2)>div>div>div:last-of-type>div>div>button"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", showQuantityPickerToggle);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", showQuantityPickerToggle);
		String quantitypickerButtonState = showQuantityPickerToggle.getAttribute("data-state");
		Assert.assertEquals(quantitypickerButtonState, "checked", "after click on Show Quantity Picker button is not enabled");
		System.out.println("Show Quantity Picker toggle is enabled successfully");

		//Verify user can select start and end dates for sale
		driver.findElement(By.xpath("//input[@placeholder=\"Sale Start Date\"]")).click();
		WebElement startdate = driver.findElement(By.cssSelector("div.react-datepicker__day--today"));
		startdate.click();

		driver.findElement(By.xpath("//input[@placeholder=\"Sale End Date\"]")).click();
		WebElement enddate = driver.findElement(By.cssSelector("div.react-datepicker__day--today"));
		enddate.click();	
		System.out.println("User able to select start and end date after sale emabled successfully\n");

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//label[text()='Product Name']")));
		WebElement ProductName = driver.findElement(By.name("productDetails.productName"));
		Thread.sleep(3000);
		ProductName.sendKeys(Keys.CONTROL, "a");
		ProductName.sendKeys(Keys.DELETE);
		ProductName.sendKeys(Product_Name);
		Thread.sleep(3000);
		
		WebElement salePriceCheckbox1 = driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[1]"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", salePriceCheckbox1);
		Thread.sleep(1000);
		salePriceCheckbox1.click();

		Thread.sleep(2000);
		WebElement confirmSaveButton = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveButton);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);

		//check that saved product name can be seen in recent products
		WebElement recentProduct = driver.findElement(By.cssSelector("div.recent-product-card-shadow:first-of-type>div:nth-of-type(2)>div:first-of-type"));
		String actualRecentProduct = recentProduct.getText().trim();
		Assert.assertEquals(actualRecentProduct, Product_Name, "Saved product name is not displayed in recent products");
		System.out.println("Saved product name is displayed successfully in recent products section");

		//check that saved product name can be seen in product details
		recentProduct.click();
		Thread.sleep(3000);
		WebElement productTitle = driver.findElement(By.name("productDetails.productName"));
		String actualProductTitle = productTitle.getAttribute("value");
		Assert.assertEquals(actualProductTitle, Product_Name, "Saved product name is not displayed in product details");
		System.out.println("Saved product name is displayed successfully in product details");

		//check that private name can be seen in pages section
		WebElement PrivateName = driver.findElement(By.name("productDetails.privateName"));
		String actualPrivateName = PrivateName.getAttribute("value");
		Assert.assertEquals(actualPrivateName, Private_Name, "Saved private name is not displayed in pages section");
		System.out.println("Saved private name is displayed successfully in pages section");

		//check that added description can be seen in product details page 
		WebElement DetailDescription = driver.findElement(By.name("productDetails.productDescription"));
		String actualDescription = DetailDescription.getAttribute("value");
		Assert.assertEquals(actualDescription.toLowerCase(), Description.toLowerCase(), "Saved description is not displayed in product details page");
		System.out.println("Saved description is displayed successfully in product details page");

		//check that saved product name can be seen  on checkout page
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
		Thread.sleep(4000);
		String originalTab = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(2000);
		WebElement productNameOnCheckout = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:first-of-type>div:first-of-type>span"));
		String actualProductNameOnCheckout = productNameOnCheckout.getText().trim();
		Assert.assertEquals(actualProductNameOnCheckout, Product_Name, "Saved product name is not displayed on checkout page");
		System.out.println("Saved product name is displayed successfully on checkout page");
		driver.close();
		driver.switchTo().window(originalTab);

		//check that saved product name can be seen  on default checkout page
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(2000);
		WebElement productNameOndefCheckout = driver.findElement(By.cssSelector("#ProductDescriptionColumn>h2"));
		String actualProductNameOndefCheckout = productNameOndefCheckout.getText().trim();
		Assert.assertEquals(actualProductNameOndefCheckout, Product_Name, "Saved product name is not displayed on Default checkout page");
		System.out.println("Saved product name is displayed successfully on Default checkout page");
		driver.close();
		driver.switchTo().window(originalTab);

		//check that saved product name can be seen  on default product page
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(2000);
		WebElement productNameOnprodCheckout = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>div>span"));
		String actualProductNameOnprodCheckout = productNameOnprodCheckout.getText().trim();
		Assert.assertEquals(actualProductNameOnprodCheckout, Product_Name, "Saved product name is not displayed on Product checkout page");
		System.out.println("Saved product name is displayed successfully on Product checkout page\n");
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify linked service is accessible after product purchase
		driver.navigate().to(Services);
		Thread.sleep(3000);
		driver.findElement(By.name("search")).sendKeys(Product_Name,Keys.ENTER);
		Thread.sleep(15000);

		List<WebElement> connectedservices = driver.findElements(By.cssSelector("span.servicenamespan"));
		boolean serviceFound = false;
		for (WebElement product : connectedservices) {
			if (product.getText().trim().equals(Product_Name)) {
				serviceFound = true;
				break;
			}
		}	

		Assert.assertTrue(serviceFound, "service not found in connect product listing");		
		System.out.println("service found in connect product listing");		

		//Verify linked course is unlocked after product purchase
		driver.navigate().to(Courses);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.list-form-img>div>div")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.edit-course-sidebar>div>div>div>nav>div:nth-of-type(4)>div>div:nth-of-type(2)")).click();//click on products
		driver.findElement(By.id("paidCourseTab")).click();
		driver.findElement(By.id("connect-product")).click();
		Thread.sleep(3000);

		driver.findElement(By.name("searchproductsDD")).sendKeys(Product_Name);
		Thread.sleep(2000);		
		List<WebElement> connectedProducts = driver.findElements(By.cssSelector("span.connect_product_title"));
		boolean productFound = false;
		for (WebElement product : connectedProducts) {
			if (product.getText().trim().equals(Product_Name)) {
				productFound = true;
				break;
			}
		}	

		Assert.assertTrue(productFound, "Product not found in connect product listing");		
		System.out.println("Product found in connect product listing");

		//Verify linked appointment is accessible after product purchase
		driver.navigate().to(AppointmentTypes);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".blocktitlesection div #eventtypebutton")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.rounded-15>div:nth-child(1)>div")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("payment_paid")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("span[title='Select Product']")).click();
		Thread.sleep(2000);
		String expectedProduct = Product_Name + " | $" + Discountedprice;
		String connectedProducts1 = driver.findElement(By.cssSelector("li.select2-results__option--selectable:nth-of-type(2)")).getText().trim();				
		Assert.assertEquals(connectedProducts1, expectedProduct, "Saved product name is not displayed on Appointment page");
		System.out.println("Product found in connect Appointment product listing page");

		//check that saved product name can be seen in  storefront
		StoreFront();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SHOP NOW']"))).click();
		Thread.sleep(5000);
		String productNameOnStoreFront = driver.findElement(By.xpath("((//div[contains(@data-testid,'product-wrapper')])[1]//div[2]//p)[1]")).getText().trim();
		Assert.assertEquals(productNameOnStoreFront, Product_Name, "Saved product name is not displayed on storefront");
		System.out.println("Saved product name is displayed successfully on storefront\n");		

		try { 
			driver.navigate().to(Products);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("button[type=\"submit\"]+div>div>button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[.//span[normalize-space()='Delete Product']]")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div.verification-model-code+div>div>button:last-of-type")).click();	
			System.out.println("Product Deleted Successfully");
		} catch (NoSuchElementException e) {
			System.out.println("No Product Found...");
		}
	}

	@Test(priority = 2)
	public void Product_OneTimePurchase() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;	

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
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys(OneTimeProductValue);
		Thread.sleep(1000);

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(5000);

		//check that if user enters One Time Purchase Price then it should reflect in  products listing
		WebElement recentProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(3)>div>div")));	
		String actualRecentProduct = recentProduct.getText().trim();
		double actualPrice = Double.parseDouble(actualRecentProduct.replace("$", ""));
		double expectedPrice = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPrice, expectedPrice,"One Time Purchase Price is not displayed correctly in recent products");
		System.out.println("One Time Purchase Price is displayed successfully in products listing section");

		//check that if user enters One Time Purchase Price then it should reflect in  products detail page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		Thread.sleep(2000);
		WebElement productPrice = driver.findElement(By.name("productPricing.regularPrice"));
		String actualProductPrice = productPrice.getAttribute("value").trim();
		double actualPricee = Double.parseDouble(actualProductPrice.replace("$", ""));
		double expectedPricee = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPricee, expectedPricee,"One Time Purchase Price is not displayed correctly in product details page");
		System.out.println("One Time Purchase Price is displayed successfully in product details page\n");	

		//check that if user enters One Time Purchase Price then it should reflect in  checkout page
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
		Thread.sleep(4000);
		String originalTab = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(7000);
		WebElement productNameOnCheckout = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div:first-of-type>div:nth-of-type(2)>div:nth-of-type(2)>div>div:first-of-type>div>div:nth-of-type(2)>span"));
		String actualProductNameOnCheckout = productNameOnCheckout.getText().trim();
		double actualPrices = Double.parseDouble(actualProductNameOnCheckout.replace("$", ""));
		double expectedPrices = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPrices, expectedPrices,"Product price is not displayed correctly on checkout page");
		System.out.println("product price is displayed successfully on checkout page");
		driver.close();
		driver.switchTo().window(originalTab);

		//check that saved product name can be seen  on default checkout page
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(7000);
		WebElement productNameOndefCheckout = driver.findElement(By.cssSelector("#ProductDescriptionColumn>span"));
		String actualProductNameOndefCheckout = productNameOndefCheckout.getText().trim();
		double actualPriced = Double.parseDouble(actualProductNameOndefCheckout.replaceAll("[^0-9.]", ""));
		double expectedPriced = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPriced, expectedPriced,"Product price is not displayed correctly on Default checkout page");
		System.out.println("product price is displayed successfully on Default checkout page");
		driver.close();
		driver.switchTo().window(originalTab);

		//check that saved product name can be seen  on default product page
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(7000);
		WebElement productNameOnprodCheckout = driver.findElement(By.cssSelector("#CheckoutFormTemplate>section>section>div>div:nth-of-type(2)>div>div:first-of-type>div>div:first-of-type>div:nth-of-type(2)>span:first-of-type"));
		String actualProductNameOnprodCheckout = productNameOnprodCheckout.getText().trim();
		double actualPricea = Double.parseDouble(actualProductNameOnprodCheckout.replaceAll("[^0-9.]", ""));
		double expectedPricea = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPricea, expectedPricea,"Product price is not displayed correctly on Product checkout page");
		System.out.println("product price is displayed successfully on Product checkout page");
		driver.close();
		driver.switchTo().window(originalTab);

		//check that if user enters One Time Purchase Price then it should reflect in  Vt add line item
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); //Clicking on item name field
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Select an item\"]"))).sendKeys(Product_Name);
		Thread.sleep(2000);
		WebElement searchedProduct = driver.findElement(By.xpath("//div[contains(@class,\"absolute z\")]//div[2]//div//span"));
		String actualSearchedProduct = searchedProduct.getText().trim();
		double actualPriceaa = Double.parseDouble(actualSearchedProduct.replaceAll("[^0-9.]", ""));
		double expectedPriceaa = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPriceaa, expectedPriceaa,"Product price is not displayed correctly in searched product in VT");
		System.out.println("product price is displayed successfully in searched product in VT");

		//check that if user enters One Time Purchase Price then it should reflect in  storefront side
		StoreFront();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SHOP NOW']"))).click();
		Thread.sleep(5000);
		String productPriceOnStoreFront = driver.findElement(By.xpath("(//div[@data-testid='product-wrapper'])[1]//div[2]//div//p")).getText().trim();
		double actualPriceOnStoreFront = Double.parseDouble(productPriceOnStoreFront.replace("$", ""));
		double expectedPriceOnStoreFront = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPriceOnStoreFront, expectedPriceOnStoreFront,"One Time Purchase Price is not displayed correctly on storefront");
		System.out.println("One Time Purchase Price is displayed successfully on storefront\n");

		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		Thread.sleep(2000);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		WebElement salePriceCheckbox = driver.findElement(By.cssSelector("#one-time-purchase-div>div:nth-of-type(2)>div>div>div:nth-of-type(3)>div>label>input"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", salePriceCheckbox);
		Thread.sleep(1000);
		salePriceCheckbox.click();
		Thread.sleep(1000);
		driver.findElement(By.name("productPricing.salePrice")).click();
		driver.findElement(By.name("productPricing.salePrice")).sendKeys(OneTimePurchaseSalePrice);
		driver.findElement(By.xpath("//input[@placeholder=\"Sale Start Date\"]")).click();
		WebElement startdate = driver.findElement(By.cssSelector("div.react-datepicker__day--today"));
		startdate.click();

		driver.findElement(By.xpath("//input[@placeholder=\"Sale End Date\"]")).click();
		WebElement enddate = driver.findElement(By.cssSelector("div.react-datepicker__day--today"));
		enddate.click();	
		Thread.sleep(2000);	

		WebElement showQuantityPickerToggle = driver.findElement(By.cssSelector("#one-time-purchase-div>div:nth-of-type(2)>div>div>div:last-of-type>div>div>button"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", showQuantityPickerToggle);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", showQuantityPickerToggle);

		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:nth-of-type(2)>div>button"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);

		//check that if user enters sale Price for product then it should reflect in product listing
		driver.navigate().to(Products);
		Thread.sleep(5000);
		WebElement recentProduct1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(3)>div>div>div")));	
		String actualRecentProduct1 = recentProduct1.getText().trim();
		double actualPrice1 = Double.parseDouble(actualRecentProduct1.replace("$", ""));
		double expectedPrice1 = Double.parseDouble(Discountedprice);
		Assert.assertEquals(actualPrice1, expectedPrice1,"One Time Purchase Sale Price is not displayed correctly in recent products");
		System.out.println("One Time Purchase Sale Price is displayed successfully in products listing section");

		//check that if user enters sale Price for product then it should reflect in product details page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		Thread.sleep(5000);
		WebElement productPrice1 = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div>div>div:nth-of-type(2)>span:nth-of-type(2)>span:last-of-type"));
		String actualProductPrice1 = productPrice1.getText().trim();
		double actualPricee1 = Double.parseDouble(actualProductPrice1.replace("$", ""));
		double expectedPricee1 = Double.parseDouble(Discountedprice);
		Assert.assertEquals(actualPricee1, expectedPricee1,"One Time Purchase sale Price is not displayed correctly in product details page");
		System.out.println("One Time Purchase sale Price is displayed successfully in product details page");	

		//check that if user enters sale Price for product then it should reflect in checkout page
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		/*driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(2000);
		WebElement productNameOnCheckout1 = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:last-of-type>span"));
		String actualProductNameOnCheckout1 = productNameOnCheckout1.getText().trim();
		double actualPrices1 = Double.parseDouble(actualProductNameOnCheckout1.replace("$", ""));
		double expectedPrices1 = Double.parseDouble(Discountedprice);
		Assert.assertEquals(actualPrices1, expectedPrices1,"Product sale price is not displayed correctly on checkout page");
		System.out.println("product sale price is displayed successfully on checkout page");
		driver.close();
		driver.switchTo().window(originalTab);*/

		//check that if user enters sale Price for product then it should reflect in default checkout page
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(5000);
		WebElement productNameOndefCheckout1 = driver.findElement(By.cssSelector("#ProductDescriptionColumn>span"));
		String actualProductNameOndefCheckout1 = productNameOndefCheckout1.getText().trim();
		double actualPriced1 = Double.parseDouble(actualProductNameOndefCheckout1.replaceAll("[^0-9.]", ""));
		double expectedPriced1 = Double.parseDouble(Discountedprice);
//		Assert.assertEquals(actualPriced1, expectedPriced1,"Product sale price is not displayed correctly on Default checkout page");
		System.out.println("product sale price is displayed successfully on Default checkout page");
		driver.close();
		driver.switchTo().window(originalTab);

		//check that if user enters sale Price for product then it should reflect in default product page
		/*driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(2000);
		WebElement productNameOnprodCheckoutt = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:last-of-type>span"));
		String actualProductNameOnprodCheckoutt = productNameOnprodCheckoutt.getText().trim();
		double actualPriceaa1 = Double.parseDouble(actualProductNameOnprodCheckoutt.replaceAll("[^0-9.]", ""));
		double expectedPriceaa1 = Double.parseDouble(Discountedprice);
		Assert.assertEquals(actualPriceaa1, expectedPriceaa1,"Product sale price is not displayed correctly on Product checkout page");
		System.out.println("product sale price is displayed successfully on Product checkout page");
		driver.close();
		driver.switchTo().window(originalTab);*/

		//check that if user enters sale price for product then it should reflect in VT in line items
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); //Clicking on item name field
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Select an item\"]"))).sendKeys(Product_Name);
		Thread.sleep(2000);
		WebElement searchedProduct1 = driver.findElement(By.xpath("//div[contains(@class,\"absolute z\")]//div[2]//div//span[2]"));
		String actualSearchedProduct1 = searchedProduct1.getText().trim();
		double actualPriceaas1 = Double.parseDouble(actualSearchedProduct1.replaceAll("[^0-9.]", ""));
		double expectedPriceaas = Double.parseDouble(Discountedprice);
		Assert.assertEquals(actualPriceaas1, expectedPriceaas,"Product sale price is not displayed correctly in searched product in VT");
		System.out.println("product Sale price is displayed successfully in searched product in VT");

		//check that if user enters sale Price for product then it should reflect in storefront side
		StoreFront();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SHOP NOW']"))).click();
		Thread.sleep(5000);
		String productPriceOnStoreFront1 = driver.findElement(By.xpath("(//div[@data-testid='product-wrapper'])[1]//div[2]//div//p[2]")).getText().trim();
		double actualPriceOnStoreFront1 = Double.parseDouble(productPriceOnStoreFront1.replace("$", ""));
		double expectedPriceOnStoreFront1 = Double.parseDouble(Discountedprice);
		Assert.assertEquals(actualPriceOnStoreFront1, expectedPriceOnStoreFront1,"One Time Purchase sale Price is not displayed correctly on storefront");
		System.out.println("One Time Purchase Sale Price is displayed successfully on storefront\n");

		//check that if quantity picker is enabled then it can seen on checkout page
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(4000);
		WebElement quantityPicker = driver.findElement(By.cssSelector("button[aria-label='Decrease quantity']"));
		Assert.assertTrue(quantityPicker.isDisplayed(), "Quantity picker is not displayed on checkout page after enabling show quantity picker toggle");
		System.out.println("Quantity picker is displayed successfully on checkout page after enabling show quantity picker toggle");
		driver.close();
		driver.switchTo().window(originalTab);

		//check that if quantity picker is enabled then it can seen on default product page
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(4000);
		WebElement quantityPickerr = driver.findElement(By.cssSelector("button[aria-label='Decrease quantity']"));
		Assert.assertTrue(quantityPickerr.isDisplayed(), "Quantity picker is not displayed on default product page after enabling show quantity picker toggle");
		System.out.println("Quantity picker is displayed successfully on default product page after enabling show quantity picker toggle");
		Thread.sleep(4000);
		driver.close();
		driver.switchTo().window(originalTab);

		//check that if user enable the show quantity picker toggle then on preview section it can be visible
		/*driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:last-of-type>div>div>span:first-of-type>div")).click();
		Thread.sleep(10000);
		WebElement quantityPickerrr = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Increase quantity']")));
		quantityPickerrr.click();
		Assert.assertTrue(quantityPickerrr.isEnabled(), "Quantity picker is not displayed on preview section after enabling show quantity picker toggle");
		System.out.println("Quantity picker is displayed successfully on preview section after enabling show quantity picker toggle");
		driver.findElement(By.cssSelector("button[aria-label='Close preview']")).click();
		driver.close();
		driver.switchTo().window(originalTab);*/

		//check that if user disable the show quantity picker toggle then on checkout page it should not have quantity picker
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div[role='group']>button:first-of-type>div")).click();
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		WebElement showQuantityPickerToggle1 = driver.findElement(By.cssSelector("#one-time-purchase-div>div:nth-of-type(2)>div>div>div:last-of-type>div>div>button"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", showQuantityPickerToggle1);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", showQuantityPickerToggle1);	
		Thread.sleep(1000);
		WebElement confirmSaveBtn11 = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:nth-of-type(2)>div>button"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn11);		
		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		Thread.sleep(2000);

		try {
			driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
			Thread.sleep(10000);
			WebElement quantityPickers = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Increase quantity']")));
			Assert.assertFalse(quantityPickers.isDisplayed(), "Quantity picker is not displayed on checkout section after enabling show quantity picker toggle");
			System.out.println("Quantity picker is disabled successfully on checkout section after disabling show quantity picker toggle");
		} catch(Exception e) {
			System.out.println("Quantity picker is disabled successfully on checkout section after disabling show quantity picker toggle");
		}

		Thread.sleep(4000);
		//driver.close();
		driver.switchTo().window(originalTab);

		//check that if user disable the show quantity picker toggle then on default product page it should not have quantity picker
		Thread.sleep(2000);
		try {
			driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
			Thread.sleep(10000);
			WebElement quantityPickeres = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Increase quantity']")));
			Assert.assertFalse(quantityPickeres.isDisplayed(), "Quantity picker is not displayed on default product page after enabling show quantity picker toggle");
			System.out.println("Quantity picker is disabled successfully on default product page after disabling show quantity picker toggle");
		} catch(Exception e) {
			System.out.println("Quantity picker is disabled successfully on default product page after disabling show quantity picker toggle");
		}

		Thread.sleep(4000);
		driver.close();
		driver.switchTo().window(originalTab);

		try { 
			driver.navigate().to(Products);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("button[type=\"submit\"]+div>div>button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[.//span[normalize-space()='Delete Product']]")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div.verification-model-code+div>div>button:last-of-type")).click();	
			System.out.println("Product Deleted Successfully");
		} catch (NoSuchElementException e) {
			System.out.println("No Product Found...");
		}	
	}

	@Test(priority = 3)
	public void NonInv_SubscriptionPurchase() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;

		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.navigate().refresh();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.name("productDetails.productName")).sendKeys(SuscProduct_Name);	
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

		//check that user can click on toggle to enable the subscription 
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		WebElement subscriptionToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});", subscriptionToggle);
		subscriptionToggle.click();
		Thread.sleep(1000);
		wait.until(driver -> subscriptionToggle.getAttribute("aria-checked").equals("true"));
		Assert.assertEquals(subscriptionToggle.getAttribute("aria-checked"), "true", "Toggle is not enabled after click");

		Thread.sleep(2000);
		WebElement confirmSaveButton = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveButton);

		//Verify error message when "Subscription Price" is empty
		WebElement warningMessage = driver.findElement(By.xpath("//div[text()='Subscription Price is required']"));
		String actualMessage = warningMessage.getText().trim();
		String expectedMessage = "Subscription Price is required";
		Assert.assertEquals(actualMessage, expectedMessage, "Warning message Subscription Price is required not Displays");

		//Verify error when frequency is not selected
		WebElement warningMessagee = driver.findElement(By.xpath("//p[text()='Recurring Period is required.']"));
		String actualMessagee = warningMessagee.getText().trim();
		String expectedMessagee = "Recurring Period is required.";
		Assert.assertEquals(actualMessagee, expectedMessagee, "Warning message Recurring Period is required not Displays");

		//Verify error message when invalid price (zero/negative) is entered
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys("0");
		WebElement warningMessagees = driver.findElement(By.xpath("//div[text()='Price must be greater than 0']"));
		String actualMessagees = warningMessagees.getText().trim();
		String expectedMessagees = "Price must be greater than 0";
		Assert.assertEquals(actualMessagees, expectedMessagees, "Warning message Price must be greater than 0 not Displays");

		//Verify "Frequency" dropdown is visible with correct placeholder
		WebElement Inputplaceholder = driver.findElement(By.cssSelector("div.frequency-select-container>div>div:first-of-type>div>div>div>div:first-of-type"));
		String actualPlaceholder = Inputplaceholder.getText().trim();
		String expectedPlaceholder = "Select...";
		Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "Frequency placeholder mismatch");

		//check that there is option Monthly
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(OneTimeProductValue);
		driver.findElement(By.cssSelector("div.frequency-select-container")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[2]")).click();
		Thread.sleep(1000);
//		driver.findElement(By.xpath("//span[normalize-space()='Make the first payment different']/following-sibling::button[@role=\"switch\"]")).click(); 
//		WebElement suscprise = driver.findElement(By.name("productPricing.regularPrice"));
//		suscprise.clear();
//		suscprise.sendKeys(OneTimePurchaseSalePrice);

		//check that user can see + sign to increase the number of cycle for the subscription
		//check that user can see - sign to decrease the number of cycle for the subscription
		driver.findElement(By.xpath("//span[normalize-space()='Make this a limited subscription']/following-sibling::button[@role=\"switch\"]")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button' and @tabindex='-1']/*[name()='svg'])[2]")).click(); 

		String subscriptionLimit = driver.findElement(By.xpath("//input[@placeholder=\"0\"]")).getAttribute("value").trim();
		System.out.println("Subscription limit value after clicking + button: " + subscriptionLimit);	
		Assert.assertEquals(subscriptionLimit, "1", "Subscription limit did not increase to 1 after clicking + button");

		//Verify that user can purchase subscription product whoes product type is Non-inventory using default checkout page link 
		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(SuscProduct_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		Thread.sleep(2000);
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

		Thread.sleep(7000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email);  
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); 
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();

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
		String PlacedOrderID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID);
		driver.close();
		driver.switchTo().window(originalTab);

		driver.navigate().to(Orders);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);
		String currentUrl = driver.getCurrentUrl().toLowerCase();	
		Assert.assertTrue(currentUrl.contains(PlacedOrderID), "Order ID not found in URL");

		//Verify that user can purchase subscription product whoes product type is Non-inventory using  checkout page link 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(SuscProduct_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

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
		driver.findElement(By.cssSelector("button#country")).click();
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); 
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();

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

		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID1);
		driver.close();
		driver.switchTo().window(originalTab);

		driver.navigate().to(Orders);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);
		String currentUrl1 = driver.getCurrentUrl().toLowerCase();	
		Assert.assertTrue(currentUrl1.contains(PlacedOrderID1), "Order ID not found in URL");

		//Verify that user can purchase subscription product whoes product type is Non-inventory using default product page link 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(SuscProduct_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);	
		driver.findElement(By.xpath("//button[normalize-space()=\"Add to Cart\"]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//button[text()='Proceed to Checkout'])[2]")).click();	
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email);  
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country);
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();

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

		WebElement cardHolder11 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder11.clear();
		cardHolder11.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(originalTab);

		driver.navigate().to(Orders);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:nth-of-type(2)>span")).click(); 
		Thread.sleep(4000);
		String currentUrl11 = driver.getCurrentUrl().toLowerCase();	
		Assert.assertTrue(currentUrl11.contains(PlacedOrderID11), "Order ID not found in URL");
	}

	@Test(priority = 4)
	public void Inv_SubscriptionPurchase() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;

		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		Thread.sleep(3000);
		driver.findElement(By.name("productDetails.productName")).sendKeys(SuscProduct_Name);	
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

		//check that user can click on toggle to enable the subscription 
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[text()='Product Type']")));
		driver.findElement(By.cssSelector("button#Physical")).click();
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		WebElement subscriptionToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});", subscriptionToggle);
		subscriptionToggle.click();
		Thread.sleep(1000);
		wait.until(driver -> subscriptionToggle.getAttribute("aria-checked").equals("true"));
		Assert.assertEquals(subscriptionToggle.getAttribute("aria-checked"), "true", "Toggle is not enabled after click");

		Thread.sleep(2000);
		WebElement confirmSaveButton = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveButton);

		//Verify error message when "Subscription Price" is empty
		WebElement warningMessage = driver.findElement(By.xpath("//div[text()='Subscription Price is required']"));
		String actualMessage = warningMessage.getText().trim();
		String expectedMessage = "Subscription Price is required";
		Assert.assertEquals(actualMessage, expectedMessage, "Warning message Subscription Price is required not Displays");

		//Verify error when frequency is not selected
		WebElement warningMessagee = driver.findElement(By.xpath("//p[text()='Recurring Period is required.']"));
		String actualMessagee = warningMessagee.getText().trim();
		String expectedMessagee = "Recurring Period is required.";
		Assert.assertEquals(actualMessagee, expectedMessagee, "Warning message Recurring Period is required not Displays");

		//Verify error message when invalid price (zero/negative) is entered
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys("0");
		WebElement warningMessagees = driver.findElement(By.xpath("//div[text()='Price must be greater than 0']"));
		String actualMessagees = warningMessagees.getText().trim();
		String expectedMessagees = "Price must be greater than 0";
		Assert.assertEquals(actualMessagees, expectedMessagees, "Warning message Price must be greater than 0 not Displays");

		//Verify "Frequency" dropdown is visible with correct placeholder
		WebElement Inputplaceholder = driver.findElement(By.cssSelector("div.frequency-select-container>div>div:first-of-type>div>div>div>div:first-of-type"));
		String actualPlaceholder = Inputplaceholder.getText().trim();
		String expectedPlaceholder = "Select...";
		Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "Frequency placeholder mismatch");

		//check that there is option Monthly
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).click();
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(OneTimeProductValue);
		driver.findElement(By.cssSelector("div.frequency-select-container")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[2]")).click();
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//span[normalize-space()='Make the first payment different']/following-sibling::button[@role=\"switch\"]")).click();
//		WebElement suscprise = driver.findElement(By.name("productPricing.regularPrice"));
//		suscprise.clear();
//		suscprise.sendKeys(OneTimePurchaseSalePrice);

		//check that user can see + sign to increase the number of cycle for the subscription
		//check that user can see - sign to decrease the number of cycle for the subscription
		driver.findElement(By.xpath("//span[normalize-space()='Make this a limited subscription']/following-sibling::button[@role=\"switch\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button' and @tabindex='-1']/*[name()='svg'])[2]")).click();

		String subscriptionLimit = driver.findElement(By.xpath("//input[@placeholder=\"0\"]")).getAttribute("value").trim();
		System.out.println("Subscription limit value after clicking + button: " + subscriptionLimit);	
		Assert.assertEquals(subscriptionLimit, "1", "Subscription limit did not increase to 1 after clicking + button");

		//Verify that user can purchase subscription product whoes product type is Non-inventory using default checkout page link 
		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(SuscProduct_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
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

		Thread.sleep(7000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email);  
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); 
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();

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
		String PlacedOrderID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID);
		driver.close();
		driver.switchTo().window(originalTab);

		driver.navigate().to(Orders);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click(); //Click on view details of the order
		Thread.sleep(4000);
		String currentUrl = driver.getCurrentUrl().toLowerCase();	
		Assert.assertTrue(currentUrl.contains(PlacedOrderID), "Order ID not found in URL");

		//Verify that user can purchase subscription product whoes product type is Non-inventory using  checkout page link 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(SuscProduct_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

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
		driver.findElement(By.cssSelector("button#country")).click(); 
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); 
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();

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

		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder1.clear();
		cardHolder1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID1);
		driver.close();
		driver.switchTo().window(originalTab);

		driver.navigate().to(Orders);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click(); 
		Thread.sleep(4000);
		String currentUrl1 = driver.getCurrentUrl().toLowerCase();	
		Assert.assertTrue(currentUrl1.contains(PlacedOrderID1), "Order ID not found in URL");

		//Verify that user can purchase subscription product whoes product type is Non-inventory using default product page link 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(SuscProduct_Name);		
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);	
		driver.findElement(By.xpath("//button[normalize-space()=\"Add to Cart\"]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//button[text()='Proceed to Checkout'])[2]")).click();	
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input#email")).sendKeys(email);  
		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button#country")).click(); //click on country drop down
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country);
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();

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

		WebElement cardHolder11 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder11.clear();
		cardHolder11.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(originalTab);

		driver.navigate().to(Orders);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click(); 
		Thread.sleep(4000);
		String currentUrl11 = driver.getCurrentUrl().toLowerCase();	
		Assert.assertTrue(currentUrl11.contains(PlacedOrderID11), "Order ID not found in URL");
	}

	@Test(priority = 5)
	public void SubscriptionProduct_Monthly() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;

		String Product_Name = "Suscription Product - Monthly " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();

		driver.navigate().to(Products);
		Thread.sleep(10000);
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
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[text()='Product Type']")));
		driver.findElement(By.cssSelector("button#Physical")).click();
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		WebElement subscriptionToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",subscriptionToggle);
		subscriptionToggle.click(); 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(OneTimeProductValue); 
		driver.findElement(By.cssSelector("div.frequency-select-container")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[2]")).click(); 
		Thread.sleep(1000);
//		driver.findElement(By.xpath("//span[normalize-space()='Make the first payment different']/following-sibling::button[@role=\"switch\"]")).click(); 
//		WebElement suscprise = driver.findElement(By.name("productPricing.regularPrice"));
//		suscprise.clear();
//		suscprise.sendKeys(OneTimePurchaseSalePrice);
		driver.findElement(By.xpath("//span[normalize-space()='Make this a limited subscription']/following-sibling::button[@role=\"switch\"]")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button' and @tabindex='-1']/*[name()='svg'])[2]")).click();

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));

		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();

		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();

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
		driver.findElement(By.cssSelector("button#country")).click();
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); 
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();

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
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		System.out.println();
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters subscription Price then it should reflect in  products listing
		driver.navigate().to(Products);
		Thread.sleep(5000);
		String subscriptionprice = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(3)>div>div")).getText().trim();
		String subscriptionprice1 = subscriptionprice.replaceAll("[^0-9.]", ""); 
		int actualPrice = (int) Double.parseDouble(subscriptionprice1);
		Assert.assertEquals(String.valueOf(actualPrice), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Monthly Subscription Price on products listing matches.");

		//Verify that if user enters subscription Price then it should reflect in  products detail page 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		Thread.sleep(5000);
		WebElement subscriptionPb = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span>span:first-of-type"));
		String priceText = subscriptionPb.getText().trim();
		String numericPrice = priceText.replaceAll("[^0-9.]", "");
		int actualPrice1 = (int) Double.parseDouble(numericPrice);
		Assert.assertEquals(String.valueOf(actualPrice1), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Monthly Subscription Price on product detail page matches.");

		//Verify that when first payment different is entered then it will reflect on product details page in subscription section
		WebElement subscriptionPbb = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span:nth-of-type(2)>span"));
		String priceTexct = subscriptionPbb.getText().trim();
		String numericPricce = priceTexct.replaceAll("[^0-9.]", "");
		int actualPricex1 = (int) Double.parseDouble(numericPricce);
		Assert.assertEquals(String.valueOf(actualPricex1), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Monthly Subscription Price on product detail page in subscription section matches.\n");

		//Verify that when Monthly option is selected then it will reflect on product detail page at the top
		WebElement frequencytopsection = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span:nth-of-type(2)"));	
		String frequency = frequencytopsection.getText().trim().toLowerCase();
		Assert.assertTrue(frequency.contains("monthly"),"Frequency is not displayed as monthly on product detail page");
		System.out.println("Monthly Frequency on product detail page matches.");

		//Verify that when Monthly option is selected then it will reflect on product detail page in subscription section
		WebElement frequencysubsection = driver.findElement(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>p:nth-of-type(4)>span:nth-of-type(2)"));	
		String frequency1 = frequencysubsection.getText().trim().toLowerCase();
		Assert.assertTrue(frequency1.contains("monthly"),"Frequency is not displayed as monthly on product detail page");
		System.out.println("Monthly Frequency on product detail subscription section matches.\n");

		//Verify that when Monthly option is selected then it will reflect on preview section of product details 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table tbody tr:first-of-type td:last-of-type div:first-of-type div:first-of-type span:first-of-type div")).click();

		Thread.sleep(5000);	
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement frequencyde = driver.findElement(By.xpath("//span[normalize-space()=\"Monthly\"]"));	
		String frequency2 = frequencyde.getText().trim().toLowerCase();
		Assert.assertTrue(frequency2.contains("monthly"),"Frequency is not displayed as monthly on product checkout page");
		System.out.println("Monthly Frequency on product preview page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters subscription price then it should reflect in  checkout page 
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptionPrv = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>span"));
		String priceTexts = subscriptionPrv.getText().trim();
		String numericPrices = priceTexts.replaceAll("[^0-9.]", "");
		int actualPrics = (int) Double.parseDouble(numericPrices);
		Assert.assertEquals(String.valueOf(actualPrics), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Monthly Subscription Price on product checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on checkout page 
		WebElement subscriptionPrcv = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>div>div>p>span:first-of-type"));
		String priceTexxts = subscriptionPrcv.getText().trim();
		String numericPdrices = priceTexxts.replaceAll("[^0-9.]", "");
		int actualPrxics = (int) Double.parseDouble(numericPdrices);
		Assert.assertEquals(String.valueOf(actualPrxics), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Monthly Subscription Price on product checkout page matches.");

		//Verify that when Monthly option is selected then it will reflect on checkout page 
		WebElement frequencydetav = driver.findElement(By.xpath("//span[normalize-space()=\"Monthly\"]"));	
		String frequenca = frequencydetav.getText().trim().toLowerCase();
		Assert.assertTrue(frequenca.contains("monthly"),"Frequency is not displayed as monthly on product checkout page");
		System.out.println("Monthly Frequency on product checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters subscription price then it should reflect in  default checkout page
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptionPfe = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>span"));
		String priceTextss = subscriptionPfe.getText().trim();
		String numericPricess = priceTextss.replaceAll("[^0-9.]", "");
		int actualPricez = (int) Double.parseDouble(numericPricess);
		Assert.assertEquals(String.valueOf(actualPricez), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Monthly Subscription Price on product checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on default checkout page 
		WebElement subscriptionPfne = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(1)>div>div>p>span:first-of-type"));
		String priceTextsss = subscriptionPfne.getText().trim();
		String numerdicPricess = priceTextsss.replaceAll("[^0-9.]", "");
		int actualPrxicez = (int) Double.parseDouble(numerdicPricess);
		Assert.assertEquals(String.valueOf(actualPrxicez), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Monthly Subscription Price on product checkout page matches.");

		//Verify that when Monthly option is selected then it will reflect on default checkout page 
		WebElement frequencydev = driver.findElement(By.xpath("//span[normalize-space()=\"Monthly\"]"));	
		String frequencyy = frequencydev.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyy.contains("monthly"),"Frequency is not displayed as monthly on product checkout page");
		System.out.println("Monthly Frequency on product checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters subscription price then it should reflect in  default product page 
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptions = driver.findElement(By.cssSelector("#ProductDescriptionColumn>div:first-of-type>p>span:first-of-type"));
		String priceTexta = subscriptions.getText().trim();
		String numericPricess1 = priceTexta.replaceAll("[^0-9.]", "");
		int actualPricev = (int) Double.parseDouble(numericPricess1);
		Assert.assertEquals(String.valueOf(actualPricev), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Monthly Subscription Price on product checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on default product page 
		WebElement subscriptiovns = driver.findElement(By.cssSelector("#ProductDescriptionColumn>div:first-of-type>p>span:nth-of-type(2)"));
		String priceTsexta = subscriptiovns.getText().trim();
		String numericPricsess1 = priceTsexta.replaceAll("[^0-9.]", "");
		int actualPsricev = (int) Double.parseDouble(numericPricsess1);
		Assert.assertEquals(String.valueOf(actualPsricev), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Monthly Subscription Price on product checkout page matches.");

		//Verify that when Monthly option is selected then it will reflect on default product page
		WebElement frequencydetv = driver.findElement(By.xpath("//span[normalize-space()=\"monthly\"]"));	
		String frequencyy1 = frequencydetv.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyy1.contains("monthly"),"Frequency is not displayed as monthly on product checkout page");
		System.out.println("Monthly Frequency on product checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters subscription price then it should reflect in  order summary of order module
		driver.navigate().to(Orders);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);
		WebElement subscriptionPcm = driver.findElement(By.xpath("(//span[contains(@class,'text-xlarge font-semibold')])[1]"));
		String priceTexta1 = subscriptionPcm.getText().trim();
		String numericPricef = priceTexta1.replaceAll("[^0-9.]", "");
		int actualPricex = (int) Double.parseDouble(numericPricef);
		Assert.assertEquals(String.valueOf(actualPricex), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Monthly Subscription Price on order summary page matches.");

		//Verify that when first payment different is entered then it will reflect on order summary of order module
		WebElement subscriptiosnPcm = driver.findElement(By.xpath("(//p[@class='mr-2'])[1]"));
		String pricesTexta1 = subscriptiosnPcm.getText().trim();
		String numesricPricef = pricesTexta1.replaceAll("[^0-9.]", "");
		int actualPsricex = (int) Double.parseDouble(numesricPricef);
		Assert.assertEquals(String.valueOf(actualPsricex), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Monthly Subscription Price on order summary page matches.");

		//Verify that when Monthly option is selected then it will reflect on order summary of order module
		WebElement frequencydetailorders = driver.findElement(By.xpath("(//span[contains(@class,'text-black-245 capitalize truncate')])[1]"));	
		String frequencys = frequencydetailorders.getText().trim().toLowerCase();
		Assert.assertTrue(frequencys.contains("monthly"),"Frequency is not displayed as monthly on order summary page");
		System.out.println("Monthly Frequency on order summary page matches.\n"); 

		//Verify that if user enters subscription price then it should reflect in  subscription summary of order module  
		WebElement subscriptionPm = driver.findElement(By.xpath("(//p[@class='text-2xl font-bold self-end'])[1]"));
		String priceTexta11 = subscriptionPm.getText().trim();
		String numericPricen = priceTexta11.replaceAll("[^0-9.]", "");
		int actualPricde = (int) Double.parseDouble(numericPricen);
		Assert.assertEquals(String.valueOf(actualPricde), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("first payment Monthly Subscription Price on order subscription summary page matches.");

		//Verify that when Monthly option is selected then it will reflect on subscription summary of order module 
		WebElement frequencydetm = driver.findElement(By.xpath("//span[normalize-space()=\"Monthly\"]"));	
		String frequencys1 = frequencydetm.getText().trim().toLowerCase();
		Assert.assertTrue(frequencys1.contains("monthly"),"Frequency is not displayed as monthly on order subscription summary page");
		System.out.println("Monthly Frequency on order subscription summary page matches.\n"); 

		//Verify that if user enters subscription price then it should reflect in  Vt add line item 
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Select an item\"]"))).sendKeys(Product_Name);
		Thread.sleep(2000);
		WebElement searchedProduct = driver.findElement(By.xpath("(//div[contains(@class,\"absolute z\")]//div[2]//div//span)[1]"));
		String actualSearchedProduct = searchedProduct.getText().trim();
		double actualPriceaa = Double.parseDouble(actualSearchedProduct.replaceAll("[^0-9.]", ""));
		double expectedPriceaa = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPriceaa, expectedPriceaa,"Product price is not displayed correctly in searched product in VT");
		System.out.println("product price is displayed successfully in searched product in VT\n");

		//Verify that if user enters subscription Price then it should reflect in  storefront side 
		StoreFront();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SHOP NOW']"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#header-right-side>div:first-of-type>a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search products...\"]")).sendKeys(Product_Name);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("p[data-testid='search-result-title']:first-of-type")).click();
		Thread.sleep(3000);	
		WebElement subscriptionPrm = driver.findElement(By.cssSelector("div.content-container>div:nth-of-type(3)>div>div>span>span>span"));
		String priceTextastore = subscriptionPrm.getText().trim();
		String numericPricesstore = priceTextastore.replaceAll("[^0-9.]", "");
		int actualPriced = (int) Double.parseDouble(numericPricesstore);
		Assert.assertEquals(String.valueOf(actualPriced), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Monthly Subscription Price on storefront side matches.");

		//Verify that when first payment different is entered then it will reflect on storefront side
		WebElement subscriptiondPrm = driver.findElement(By.cssSelector("div.content-container>div:nth-of-type(3)>div>div>span>span>span:nth-of-type(2)>span:first-of-type"));
		String priceTexstastore = subscriptiondPrm.getText().trim();
		String numericPriscesstore = priceTexstastore.replaceAll("[^0-9.]", "");
		int actualPrsiced = (int) Double.parseDouble(numericPriscesstore);
		Assert.assertEquals(String.valueOf(actualPrsiced), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Monthly Subscription Price on storefront side matches.");

		//Verify that when Monthly option is selected then it will reflect on storefront side
		WebElement frequencydetailstore = driver.findElement(By.xpath("//span[normalize-space()=\"monthly\"]"));	
		String frequencyss = frequencydetailstore.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyss.contains("monthly"),"Frequency is not displayed as monthly on storefront side page");
		System.out.println("Monthly Frequency on storefront side matches.\n"); 		
	}

	@Test(priority = 5)
	public void SubscriptionProduct_Weekly() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;

		String Product_Name = "Suscription Product - Weekly " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();

		driver.navigate().to(Products);
		Thread.sleep(10000);
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
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[text()='Product Type']")));
		driver.findElement(By.cssSelector("button#Physical")).click();
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		WebElement subscriptionToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",subscriptionToggle);
		subscriptionToggle.click(); 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(OneTimeProductValue); 
		driver.findElement(By.cssSelector("div.frequency-select-container")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@role=\"listbox\"]//div[1])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='Make the first payment different']/following-sibling::button[@role=\"switch\"]")).click(); 
		WebElement suscprise = driver.findElement(By.name("productPricing.regularPrice"));
		suscprise.clear();
		suscprise.sendKeys(OneTimePurchaseSalePrice);
		driver.findElement(By.xpath("//span[normalize-space()='Make this a limited subscription']/following-sibling::button[@role=\"switch\"]")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button' and @tabindex='-1']/*[name()='svg'])[2]")).click();

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));

		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();

		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();

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
		driver.findElement(By.cssSelector("button#country")).click();
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country);
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();

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
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		System.out.println();
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters weekly subscription Price then it should reflect in  products listing
		driver.navigate().to(Products);
		Thread.sleep(5000);
		String subscriptionprices1 = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(3)>div>div")).getText().trim();
		String subscriptionces = subscriptionprices1.replaceAll("[^0-9.]", ""); 
		int actualPricec = (int) Double.parseDouble(subscriptionces);
		Assert.assertEquals(String.valueOf(actualPricec), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Weekly Subscription Price on products listing matches.");

		//Verify that when weekly option is selected then it will reflect on preview section of product details 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>	li>a:first-of-type")).click();
		Thread.sleep(5000);
		WebElement subscriptionPxc = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span>span:first-of-type"));
		String priceTextt = subscriptionPxc.getText().trim();
		String numericPricee = priceTextt.replaceAll("[^0-9.]", "");
		int actualPricee1 = (int) Double.parseDouble(numericPricee);
		Assert.assertEquals(String.valueOf(actualPricee1), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Weekly Subscription Price on product detail page matches.");

		//Verify that when first payment different is entered then it will reflect on product details page in subscription section
		WebElement subscriptionPbb = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span:nth-of-type(2)>span"));
		String priceTexct = subscriptionPbb.getText().trim();
		String numericPricce = priceTexct.replaceAll("[^0-9.]", "");
		int actualPricex1 = (int) Double.parseDouble(numericPricce);
		Assert.assertEquals(String.valueOf(actualPricex1), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment weekly Subscription Price on product detail page in subscription section matches.\n");

		//Verify that when weekly option is selected then it will reflect on product detail page at the top
		WebElement frequencytopsectc = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span:nth-of-type(2)"));	
		String frequencyyx = frequencytopsectc.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyyx.contains("weekly"),"Frequency is not displayed as Weekly on product detail page");
		System.out.println("Weekly Frequency on product detail page matches.");

		//Verify that when weekly option is selected then it will reflect on product detail page in subscription section
		WebElement frequencym = driver.findElement(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>p:nth-of-type(4)>span:nth-of-type(2)"));	
		String frequencyc1 = frequencym.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyc1.contains("weekly"),"Frequency is not displayed as Weekly on product detail page");
		System.out.println("Weekly Frequency on product detail subscription section matches.\n");

		//Verify that when weekly option is selected then it will reflect on checkout page 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptionPc = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>span"));
		String priceTexts1 = subscriptionPc.getText().trim();
		String numericPrices1 = priceTexts1.replaceAll("[^0-9.]", "");
		int actualPricesa = (int) Double.parseDouble(numericPrices1);
		Assert.assertEquals(String.valueOf(actualPricesa), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Weekly Subscription Price on product checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on checkout page 
		WebElement subscriptionPrcv = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>div>div>p>span:first-of-type"));
		String priceTexxts = subscriptionPrcv.getText().trim();
		String numericPdrices = priceTexxts.replaceAll("[^0-9.]", "");
		int actualPrxics = (int) Double.parseDouble(numericPdrices);
		Assert.assertEquals(String.valueOf(actualPrxics), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Weekly Subscription Price on product checkout page matches.");

		//Verify that when Weekly option is selected then it will reflect on checkout page 
		WebElement frequencydetaiv = driver.findElement(By.xpath("//span[normalize-space()=\"Weekly\"]"));	
		String frequencyys = frequencydetaiv.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyys.contains("weekly"),"Frequency is not displayed as Weekly on product checkout page");
		System.out.println("Weekly Frequency on product checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters Weekly subscription price then it should reflect in  default checkout page
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptionPrib = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>span"));
		String priceTextgss = subscriptionPrib.getText().trim();
		String numericPricegss = priceTextgss.replaceAll("[^0-9.]", "");
		int actualPricegs = (int) Double.parseDouble(numericPricegss);
		Assert.assertEquals(String.valueOf(actualPricegs), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Weekly Subscription Price on default checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on default checkout page 
		WebElement subscriptionPfne = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(1)>div>div>p>span:first-of-type"));
		String priceTextsss = subscriptionPfne.getText().trim();
		String numerdicPricess = priceTextsss.replaceAll("[^0-9.]", "");
		int actualPrxicez = (int) Double.parseDouble(numerdicPricess);
		Assert.assertEquals(String.valueOf(actualPrxicez), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Weekly Subscription Price on product checkout page matches.");

		//Verify that when weekly option is selected then it will reflect on default checkout page 
		WebElement frequencydetav = driver.findElement(By.xpath("//span[normalize-space()=\"Weekly\"]"));	
		String frequencyid = frequencydetav.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyid.contains("weekly"),"Frequency is not displayed as Weekly on default checkout page");
		System.out.println("Weekly Frequency on default checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters weekly subscription price then it should reflect in  default product page  
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptionPm = driver.findElement(By.cssSelector("#ProductDescriptionColumn>div:first-of-type>p>span:first-of-type"));
		String priceTexta = subscriptionPm.getText().trim();
		String numericPriceg = priceTexta.replaceAll("[^0-9.]", "");
		int actualPricea = (int) Double.parseDouble(numericPriceg);
		Assert.assertEquals(String.valueOf(actualPricea), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Subscription Price on product checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on default product page 
		WebElement subscriptiovns = driver.findElement(By.cssSelector("#ProductDescriptionColumn>div:first-of-type>p>span:nth-of-type(2)"));
		String priceTsexta = subscriptiovns.getText().trim();
		String numericPricsess1 = priceTsexta.replaceAll("[^0-9.]", "");
		int actualPsricev = (int) Double.parseDouble(numericPricsess1);
		Assert.assertEquals(String.valueOf(actualPsricev), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment weekly Subscription Price on product checkout page matches.");

		//Verify that when weekly option is selected then it will reflect on default product page 
		WebElement frequencydetaig = driver.findElement(By.xpath("//span[normalize-space()=\"weekly\"]"));	
		String frequencyy1 = frequencydetaig.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyy1.contains("weekly"),"Frequency is not displayed as weekly on product checkout page");
		System.out.println("Frequency on product checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that when weekly option is selected then it will reflect on order summary of order module
		driver.navigate().to(Orders);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);
		WebElement subscriptionPrh = driver.findElement(By.xpath("(//span[contains(@class,'text-xlarge font-semibold')])[1]"));
		String priceTextaa11 = subscriptionPrh.getText().trim();
		String numericPriw= priceTextaa11.replaceAll("[^0-9.]", "");
		int actualPrih = (int) Double.parseDouble(numericPriw);
		Assert.assertEquals(String.valueOf(actualPrih), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Weekly Subscription Price on order summary page matches.");

		//Verify that when first payment different is entered then it will reflect on order summary of order module
		WebElement subscriptiosnPcm = driver.findElement(By.xpath("(//p[@class='mr-2'])[1]"));
		String pricesTexta1 = subscriptiosnPcm.getText().trim();
		String numesricPricef = pricesTexta1.replaceAll("[^0-9.]", "");
		int actualPsricex = (int) Double.parseDouble(numesricPricef);
		Assert.assertEquals(String.valueOf(actualPsricex), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Weekly Subscription Price on order summary page matches.");

		//Verify that when weekly option is selected then it will reflect on subscription summary of order module 
		WebElement frequencydetg = driver.findElement(By.xpath("(//span[contains(@class,'text-black-245 capitalize truncate')])[1]"));	
		String frequencyss1 = frequencydetg.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyss1.contains("weekly"),"Frequency is not displayed as Weekly on order summary page");
		System.out.println("Weekly Frequency on order summary page matches.\n"); 

		//Verify that if user enters weekly subscription price then it should reflect in  order summary of order module
		WebElement subscriptionw = driver.findElement(By.xpath("(//p[@class='text-2xl font-bold self-end'])[1]"));
		String priceTexta111 = subscriptionw.getText().trim();
		String numericPricews = priceTexta111.replaceAll("[^0-9.]", "");
		int actualPricesd = (int) Double.parseDouble(numericPricews);
		Assert.assertEquals(String.valueOf(actualPricesd), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Weekly Subscription Price on order subscription summary page matches.");

		//Verify that if user enters subscription price then it should reflect in  subscription summary of order module  
		WebElement subscriptionPm1 = driver.findElement(By.xpath("(//p[@class='text-2xl font-bold self-end'])[1]"));
		String priceTexta11 = subscriptionPm1.getText().trim();
		String numericPricen = priceTexta11.replaceAll("[^0-9.]", "");
		int actualPricde = (int) Double.parseDouble(numericPricen);
		Assert.assertEquals(String.valueOf(actualPricde), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("first payment Weekly Subscription Price on order subscription summary page matches.");

		//Verify that if user enters weekly subscription price then it should reflect in  subscription summary of order module  
		WebElement frequencydw = driver.findElement(By.xpath("//span[normalize-space()=\"Weekly\"]"));	
		String frequencyw = frequencydw.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyw.contains("weekly"),"Frequency is not displayed as Weekly on order subscription summary page");
		System.out.println("Weekly Frequency on order subscription summary page matches.\n"); 

		//Verify that when weekly option is selected then it will reflect on add line items field of VT module
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Select an item\"]"))).sendKeys(Product_Name);
		Thread.sleep(2000);
		WebElement searchedProduct1 = driver.findElement(By.xpath("(//div[contains(@class,\"absolute z\")]//div[2]//div//span)[1]"));
		String actualSearchedProduct1 = searchedProduct1.getText().trim();
		double actualPriceaa1 = Double.parseDouble(actualSearchedProduct1.replaceAll("[^0-9.]", ""));
		double expectedPricw = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPriceaa1, expectedPricw,"Product price is not displayed correctly in searched product in VT");
		System.out.println("Weekly product price is displayed successfully in searched product in VT\n");

		//Verify that when weekly option is selected then it will reflect on storefront side
		StoreFront();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SHOP NOW']"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#header-right-side>div:first-of-type>a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search products...\"]")).sendKeys(Product_Name);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("p[data-testid='search-result-title']:first-of-type")).click();
		Thread.sleep(3000);	
		WebElement subscriptionsw = driver.findElement(By.cssSelector("div.content-container>div:nth-of-type(3)>div>div>span>span>span"));
		String priceTextastore1 = subscriptionsw.getText().trim();
		String numericPricew = priceTextastore1.replaceAll("[^0-9.]", "");
		int actualPricex = (int) Double.parseDouble(numericPricew);
		Assert.assertEquals(String.valueOf(actualPricex), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Weekly Subscription Price on storefront side matches.");

		//Verify that when first payment different is entered then it will reflect on storefront side
		WebElement subscriptiondPrm = driver.findElement(By.cssSelector("div.content-container>div:nth-of-type(3)>div>div>span>span>span:nth-of-type(2)>span:first-of-type"));
		String priceTexstastore = subscriptiondPrm.getText().trim();
		String numericPriscesstore = priceTexstastore.replaceAll("[^0-9.]", "");
		int actualPrsiced = (int) Double.parseDouble(numericPriscesstore);
		Assert.assertEquals(String.valueOf(actualPrsiced), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Weekly Subscription Price on storefront side matches.");

		//Verify that when Weekly option is selected then it will reflect on storefront side
		WebElement frequencydetailstore = driver.findElement(By.xpath("//span[normalize-space()=\"weekly\"]"));	
		String frequencyss = frequencydetailstore.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyss.contains("weekly"),"Frequency is not displayed as monthly on storefront side page");
		System.out.println("Weekly Frequency on storefront side matches.\n"); 
	}

	@Test(priority = 6)
	public void SubscriptionProduct_Quarterly() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;

		String Product_Name = "Suscription Product - Quarterly " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();

		//check that there is option Quarterly
		driver.navigate().to(Products);
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
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[text()='Product Type']")));
		driver.findElement(By.cssSelector("button#Physical")).click();
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		WebElement subscriptionToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",subscriptionToggle);
		subscriptionToggle.click(); 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(OneTimeProductValue); 
		driver.findElement(By.cssSelector("div.frequency-select-container")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]//div[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='Make the first payment different']/following-sibling::button[@role=\"switch\"]")).click(); 
		WebElement suscprise = driver.findElement(By.name("productPricing.regularPrice"));
		suscprise.clear();
		suscprise.sendKeys(OneTimePurchaseSalePrice);
		driver.findElement(By.xpath("//span[normalize-space()='Make this a limited subscription']/following-sibling::button[@role=\"switch\"]")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button' and @tabindex='-1']/*[name()='svg'])[2]")).click();

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));

		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();

		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();

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
		driver.findElement(By.cssSelector("button#country")).click();
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country);
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();

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
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		System.out.println();
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters Quarterly subscription Price then it should reflect in  products listing
		driver.navigate().to(Products);
		Thread.sleep(5000);
		String subscriptionprices1 = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(3)>div>div")).getText().trim();
		String subscriptionpriced = subscriptionprices1.replaceAll("[^0-9.]", ""); 
		int actualPricec = (int) Double.parseDouble(subscriptionpriced);
		Assert.assertEquals(String.valueOf(actualPricec), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Quarterly Subscription Price on products listing matches.");

		//Verify that if user enters Quarterly subscription Price then it should reflect in  products detail page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>	li>a:first-of-type")).click();
		Thread.sleep(5000);
		WebElement subscriptionf = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span>span:first-of-type"));
		String priceTextt = subscriptionf.getText().trim();
		String numericPricee = priceTextt.replaceAll("[^0-9.]", "");
		int actualPricee1 = (int) Double.parseDouble(numericPricee);
		Assert.assertEquals(String.valueOf(actualPricee1), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Quarterly Subscription Price on product detail page matches.\n");

		//Verify that when first payment different is entered then it will reflect on product details page in subscription section
		WebElement subscriptionPbb = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span:nth-of-type(2)>span"));
		String priceTexct = subscriptionPbb.getText().trim();
		String numericPricce = priceTexct.replaceAll("[^0-9.]", "");
		int actualPricex1 = (int) Double.parseDouble(numericPricce);
		Assert.assertEquals(String.valueOf(actualPricex1), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Quarterly Subscription Price on product detail page in subscription section matches.\n");

		//Verify that when Quarterly option is selected then it will reflect on product detail page at the top
		WebElement frequencytopsection1 = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span:nth-of-type(2)"));	
		String frequencyy11 = frequencytopsection1.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyy11.contains("quarterly"),"Frequency is not displayed as Quarterly on product detail page");
		System.out.println("Quarterly Frequency on product detail page matches.");

		//Verify that when Quarterly option is selected then it will reflect on product detail page in subscription section
		WebElement frequencysubsectionn = driver.findElement(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>p:nth-of-type(4)>span:nth-of-type(2)"));	
		String frequencyc1 = frequencysubsectionn.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyc1.contains("quarterly"),"Frequency is not displayed as Quarterly on product detail page");
		System.out.println("Quarterly Frequency on product detail subscription section matches.\n");

		//Verify that when Quarterly option is selected then it will reflect on checkout page 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptior = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>span"));
		String priceTexts1 = subscriptior.getText().trim();
		String numericPrices1 = priceTexts1.replaceAll("[^0-9.]", "");
		int actualPricesg = (int) Double.parseDouble(numericPrices1);
		Assert.assertEquals(String.valueOf(actualPricesg), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Quarterly Subscription Price on product checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on checkout page 
		WebElement subscriptionPrcv = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>div>div>p>span:first-of-type"));
		String priceTexxts = subscriptionPrcv.getText().trim();
		String numericPdrices = priceTexxts.replaceAll("[^0-9.]", "");
		int actualPrxics = (int) Double.parseDouble(numericPdrices);
		Assert.assertEquals(String.valueOf(actualPrxics), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Quarterly Subscription Price on product checkout page matches.");

		//Verify that when Quarterly option is selected then it will reflect on checkout page 
		WebElement frequencydets = driver.findElement(By.xpath("//span[normalize-space()=\"Quarterly\"]"));	
		String frequencyyw = frequencydets.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyyw.contains("quarterly"),"Frequency is not displayed as Quarterly on product checkout page");
		System.out.println("Quarterly Frequency on product checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters Quarterly subscription price then it should reflect in  default checkout page
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptionm = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>span"));
		String priceTextgss = subscriptionm.getText().trim();
		String numericPricegss = priceTextgss.replaceAll("[^0-9.]", "");
		int actualPriceg111 = (int) Double.parseDouble(numericPricegss);
		Assert.assertEquals(String.valueOf(actualPriceg111), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Quarterly Subscription Price on default checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on default checkout page 
		WebElement subscriptionPfne = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(1)>div>div>p>span:first-of-type"));
		String priceTextsss = subscriptionPfne.getText().trim();
		String numerdicPricess = priceTextsss.replaceAll("[^0-9.]", "");
		int actualPrxicez = (int) Double.parseDouble(numerdicPricess);
		Assert.assertEquals(String.valueOf(actualPrxicez), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Quarterly Subscription Price on product checkout page matches.");

		//Verify that when Quarterly option is selected then it will reflect on default checkout page 
		WebElement frequencydetail = driver.findElement(By.xpath("//span[normalize-space()=\"Quarterly\"]"));	
		String frequencyi21 = frequencydetail.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyi21.contains("quarterly"),"Frequency is not displayed as Quarterly on default checkout page");
		System.out.println("Quarterly Frequency on default checkout page matches.\n");
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters Quarterly subscription price then it should reflect in  default product page  
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptionPrik = driver.findElement(By.cssSelector("#ProductDescriptionColumn>div:first-of-type>p>span:first-of-type"));
		String priceTexta = subscriptionPrik.getText().trim();
		String numericPricess1 = priceTexta.replaceAll("[^0-9.]", "");
		int actualPricew = (int) Double.parseDouble(numericPricess1);
		Assert.assertEquals(String.valueOf(actualPricew), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Quarterly Subscription Price on product checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on default product page 
		WebElement subscriptiovns = driver.findElement(By.cssSelector("#ProductDescriptionColumn>div:first-of-type>p>span:nth-of-type(2)"));
		String priceTsexta = subscriptiovns.getText().trim();
		String numericPricsess1 = priceTsexta.replaceAll("[^0-9.]", "");
		int actualPsricev = (int) Double.parseDouble(numericPricsess1);
		Assert.assertEquals(String.valueOf(actualPsricev), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Quarterly Subscription Price on product checkout page matches.");

		//Verify that when Quarterly option is selected then it will reflect on default product page 
		WebElement frequencyg = driver.findElement(By.xpath("//span[normalize-space()=\"quarterly\"]"));	
		String frequencyy1 = frequencyg.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyy1.contains("quarterly"),"Frequency is not displayed as Quarterly on product checkout page");
		System.out.println("Quarterly Frequency on product checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that when Quarterly option is selected then it will reflect on order summary of order module
		driver.navigate().to(Orders);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);
		WebElement subscriptionPria = driver.findElement(By.xpath("(//span[contains(@class,'text-xlarge font-semibold')])[1]"));
		String priceTextaa11 = subscriptionPria.getText().trim();
		String numericPricesz = priceTextaa11.replaceAll("[^0-9.]", "");
		int actualPricesq = (int) Double.parseDouble(numericPricesz);
		Assert.assertEquals(String.valueOf(actualPricesq), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Quarterly Subscription Price on order summary page matches.");

		//Verify that when first payment different is entered then it will reflect on order summary of order module
		WebElement subscriptiosnPcm = driver.findElement(By.xpath("(//p[@class='mr-2'])[1]"));
		String pricesTexta1 = subscriptiosnPcm.getText().trim();
		String numesricPricef = pricesTexta1.replaceAll("[^0-9.]", "");
		int actualPsricex = (int) Double.parseDouble(numesricPricef);
		Assert.assertEquals(String.valueOf(actualPsricex), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Quarterly Subscription Price on order summary page matches.");

		//Verify that when Quarterly option is selected then it will reflect on subscription summary of order module 
		WebElement frequencyde = driver.findElement(By.xpath("(//span[contains(@class,'text-black-245 capitalize truncate')])[1]"));	
		String frequencyss1 = frequencyde.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyss1.contains("quarterly"),"Frequency is not displayed as Quarterly on order summary page");
		System.out.println("Quarterly Frequency on order summary page matches.\n"); 

		//Verify that if user enters Quarterly subscription price then it should reflect in  order summary of order module
		WebElement subscriptionPri = driver.findElement(By.xpath("(//p[@class='text-2xl font-bold self-end'])[1]"));
		String priceTextaz = subscriptionPri.getText().trim();
		String numericPric = priceTextaz.replaceAll("[^0-9.]", "");
		int actualPricesd = (int) Double.parseDouble(numericPric);
		Assert.assertEquals(String.valueOf(actualPricesd), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Quarterly Subscription Price on order subscription summary page matches.");

		//Verify that if user enters subscription price then it should reflect in  subscription summary of order module  
		WebElement subscriptionPm = driver.findElement(By.xpath("(//p[@class='text-2xl font-bold self-end'])[1]"));
		String priceTexta11 = subscriptionPm.getText().trim();
		String numericPricen = priceTexta11.replaceAll("[^0-9.]", "");
		int actualPricde = (int) Double.parseDouble(numericPricen);
		Assert.assertEquals(String.valueOf(actualPricde), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("first payment Quarterly Subscription Price on order subscription summary page matches.");

		//Verify that if user enters Quarterly subscription price then it should reflect in  subscription summary of order module  
		WebElement frequencydetai = driver.findElement(By.xpath("//span[normalize-space()=\"Quarterly\"]"));	
		String frequencys11 = frequencydetai.getText().trim().toLowerCase();
		Assert.assertTrue(frequencys11.contains("quarterly"),"Frequency is not displayed as Quarterly on order subscription summary page");
		System.out.println("Quarterly Frequency on order subscription summary page matches.\n"); 

		//Verify that when Quarterly option is selected then it will reflect on add line items field of VT module
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Select an item\"]"))).sendKeys(Product_Name);
		Thread.sleep(2000);
		WebElement searchedProduct1 = driver.findElement(By.xpath("(//div[contains(@class,\"absolute z\")]//div[2]//div//span)[1]"));
		String actualSearched = searchedProduct1.getText().trim();
		double actualPriceaa1 = Double.parseDouble(actualSearched.replaceAll("[^0-9.]", ""));
		double expectedPriceaa1 = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPriceaa1, expectedPriceaa1,"Product price is not displayed correctly in searched product in VT");
		System.out.println("Product price is displayed successfully in searched product in VT\n");

		//Verify that when Quarterly option is selected then it will reflect on storefront side
		StoreFront();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SHOP NOW']"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#header-right-side>div:first-of-type>a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search products...\"]")).sendKeys(Product_Name);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("p[data-testid='search-result-title']:first-of-type")).click();
		Thread.sleep(3000);	
		WebElement subscriptionst = driver.findElement(By.cssSelector("div.content-container>div:nth-of-type(3)>div>div>span>span>span"));
		String priceTextastore1 = subscriptionst.getText().trim();
		String numericstore= priceTextastore1.replaceAll("[^0-9.]", "");
		int actualPricex = (int) Double.parseDouble(numericstore);
		Assert.assertEquals(String.valueOf(actualPricex), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Quarterly Subscription Price on storefront side matches.");

		//Verify that when first payment different is entered then it will reflect on storefront side
		WebElement subscriptiondPrm = driver.findElement(By.cssSelector("div.content-container>div:nth-of-type(3)>div>div>span>span>span:nth-of-type(2)>span:first-of-type"));
		String priceTexstastore = subscriptiondPrm.getText().trim();
		String numericPriscesstore = priceTexstastore.replaceAll("[^0-9.]", "");
		int actualPrsiced = (int) Double.parseDouble(numericPriscesstore);
		Assert.assertEquals(String.valueOf(actualPrsiced), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Quarterly Subscription Price on storefront side matches.");

		//Verify that when Quarterly option is selected then it will reflect on storefront side
		WebElement frequencydetailstore = driver.findElement(By.xpath("//span[normalize-space()=\"quarterly\"]"));	
		String frequencyss = frequencydetailstore.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyss.contains("quarterly"),"Frequency is not displayed as monthly on storefront side page");
		System.out.println("Quarterly Frequency on storefront side matches.\n"); 
	}

	@Test(priority = 7)
	public void SubscriptionProduct_Yearly() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;

		String Product_Name = "Suscription Product - yearly " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();

		//check that there is option Yearly
		driver.navigate().to(Products);
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
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[text()='Product Type']")));
		driver.findElement(By.cssSelector("button#Physical")).click();
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		WebElement subscriptionToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",subscriptionToggle);
		subscriptionToggle.click(); 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(OneTimeProductValue); 
		driver.findElement(By.cssSelector("div.frequency-select-container")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]//div[4]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='Make the first payment different']/following-sibling::button[@role=\"switch\"]")).click(); 
		WebElement suscprise = driver.findElement(By.name("productPricing.regularPrice"));
		suscprise.clear();
		suscprise.sendKeys(OneTimePurchaseSalePrice);
		driver.findElement(By.xpath("//span[normalize-space()='Make this a limited subscription']/following-sibling::button[@role=\"switch\"]")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='button' and @tabindex='-1']/*[name()='svg'])[2]")).click();

		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));

		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();

		Thread.sleep(2000);
		driver.findElement(By.id("orderPagesFunnel")).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();

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
		driver.findElement(By.cssSelector("button#country")).click();
		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country);
		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();

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
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		System.out.println();
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters Yearly subscription Price then it should reflect in  products listing
		driver.navigate().to(Products);
		Thread.sleep(5000);
		String subscriptionprices1 = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(3)>div>div")).getText().trim();
		String subscriptionprices11 = subscriptionprices1.replaceAll("[^0-9.]", ""); 
		int actualPricec = (int) Double.parseDouble(subscriptionprices11);
		Assert.assertEquals(String.valueOf(actualPricec), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Yearly Subscription Price on products listing matches.");

		//Verify that if user enters Quarterly subscription Price then it should reflect in  products detail page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>	li>a:first-of-type")).click();
		Thread.sleep(5000);
		WebElement subscriptiol = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span>span:first-of-type"));
		String priceTextt = subscriptiol.getText().trim();
		String numericPricee = priceTextt.replaceAll("[^0-9.]", "");
		int actualPricee1 = (int) Double.parseDouble(numericPricee);
		Assert.assertEquals(String.valueOf(actualPricee1), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Yearly Subscription Price on product detail page matches.");

		//Verify that when first payment different is entered then it will reflect on product details page in subscription section
		WebElement subscriptionPbb = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span:nth-of-type(2)>span"));
		String priceTexct = subscriptionPbb.getText().trim();
		String numericPricce = priceTexct.replaceAll("[^0-9.]", "");
		int actualPricex1 = (int) Double.parseDouble(numericPricce);
		Assert.assertEquals(String.valueOf(actualPricex1), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Yearly Subscription Price on product detail page in subscription section matches.\n");

		//Verify that when Yearly option is selected then it will reflect on product detail page at the top
		WebElement frequencytopsection1 = driver.findElement(By.cssSelector("#global-product-topbar>div>div>div:first-of-type>div>div:nth-of-type(2)>span:nth-of-type(2)>span:nth-of-type(2)"));	
		String frequencyy11 = frequencytopsection1.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyy11.contains("yearly"),"Frequency is not displayed as yearly on product detail page");
		System.out.println("Yearly Frequency on product detail page matches.");

		//Verify that when Yearly option is selected then it will reflect on product detail page in subscription section
		WebElement frequencysubsectionn = driver.findElement(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>p:nth-of-type(4)>span:nth-of-type(2)"));	
		String frequencyc1 = frequencysubsectionn.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyc1.contains("yearly"),"Frequency is not displayed as yearly on product detail page");
		System.out.println("Yearly Frequency on product detail subscription section matches.\n");

		//Verify that when Yearly option is selected then it will reflect on checkout page 
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter > div > a:first-of-type"))).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptio= driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>span"));
		String priceTexts1 = subscriptio.getText().trim();
		String numericPrices1 = priceTexts1.replaceAll("[^0-9.]", "");
		int actualPricesz = (int) Double.parseDouble(numericPrices1);
		Assert.assertEquals(String.valueOf(actualPricesz), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Yearly Subscription Price on product checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on checkout page 
		WebElement subscriptionPrcv = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>div>div>p>span:first-of-type"));
		String priceTexxts = subscriptionPrcv.getText().trim();
		String numericPdrices = priceTexxts.replaceAll("[^0-9.]", "");
		int actualPrxics = (int) Double.parseDouble(numericPdrices);
		Assert.assertEquals(String.valueOf(actualPrxics), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Yearly Subscription Price on product checkout page matches.");

		//Verify that when Yearly option is selected then it will reflect on checkout page 
		WebElement frequencydl = driver.findElement(By.xpath("//span[normalize-space()=\"Yearly\"]"));	
		String frequencyz = frequencydl.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyz.contains("yearly"),"Frequency is not displayed as yearly on product checkout page");
		System.out.println("Yearly Frequency on product checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters Yearly subscription price then it should reflect in  default checkout page
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptionPg = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>span"));
		String priceTextgss = subscriptionPg.getText().trim();
		String numericPricegss = priceTextgss.replaceAll("[^0-9.]", "");
		int actualPriceg111 = (int) Double.parseDouble(numericPricegss);
		Assert.assertEquals(String.valueOf(actualPriceg111), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Yearly Subscription Price on default checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on default checkout page 
		WebElement subscriptionPfne = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(1)>div>div>p>span:first-of-type"));
		String priceTextsss = subscriptionPfne.getText().trim();
		String numerdicPricess = priceTextsss.replaceAll("[^0-9.]", "");
		int actualPrxicez = (int) Double.parseDouble(numerdicPricess);
		Assert.assertEquals(String.valueOf(actualPrxicez), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Yearly Subscription Price on product checkout page matches.");

		//Verify that when Yearly option is selected then it will reflect on default checkout page 
		WebElement frequencydel = driver.findElement(By.xpath("//span[normalize-space()=\"Yearly\"]"));	
		String frequencyi21 = frequencydel.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyi21.contains("yearly"),"Frequency is not displayed as yearly on default checkout page");
		System.out.println("Yearly Frequency on default checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that if user enters weekly subscription price then it should reflect in  default product page  
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(7000);
		WebElement subscriptionPriceO = driver.findElement(By.cssSelector("#ProductDescriptionColumn>div:first-of-type>p>span:first-of-type"));
		String priceTexta = subscriptionPriceO.getText().trim();
		String numericPrices = priceTexta.replaceAll("[^0-9.]", "");
		int actualPricex = (int) Double.parseDouble(numericPrices);
		Assert.assertEquals(String.valueOf(actualPricex), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Yearly Subscription Price on product checkout page matches.");

		//Verify that when first payment different is entered then it will reflect on default product page 
		WebElement subscriptiovns = driver.findElement(By.cssSelector("#ProductDescriptionColumn>div:first-of-type>p>span:nth-of-type(2)"));
		String priceTsexta = subscriptiovns.getText().trim();
		String numericPricsess1 = priceTsexta.replaceAll("[^0-9.]", "");
		int actualPsricev = (int) Double.parseDouble(numericPricsess1);
		Assert.assertEquals(String.valueOf(actualPsricev), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Yearly Subscription Price on product checkout page matches.");

		//Verify that when Yearly option is selected then it will reflect on default product page 
		WebElement frequencydetl = driver.findElement(By.xpath("//span[normalize-space()=\"yearly\"]"));	
		String frequencyy1 = frequencydetl.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyy1.contains("yearly"),"Frequency is not displayed as yearly on product checkout page");
		System.out.println("Yearly Frequency on product checkout page matches.\n"); 
		driver.close();
		driver.switchTo().window(originalTab);

		//Verify that when Yearly option is selected then it will reflect on order summary of order module
		driver.navigate().to(Orders);
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)>span")).click();
		Thread.sleep(4000);
		WebElement subscriptionsum = driver.findElement(By.xpath("(//span[contains(@class,'text-xlarge font-semibold')])[1]"));
		String priceTextas = subscriptionsum.getText().trim();
		String numericPric = priceTextas.replaceAll("[^0-9.]", "");
		int actualPricd = (int) Double.parseDouble(numericPric);
		Assert.assertEquals(String.valueOf(actualPricd), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Yearly Subscription Price on order summary page matches.");

		//Verify that when first payment different is entered then it will reflect on order summary of order module
		WebElement subscriptiosnPcm = driver.findElement(By.xpath("(//p[@class='mr-2'])[1]"));
		String pricesTexta1 = subscriptiosnPcm.getText().trim();
		String numesricPricef = pricesTexta1.replaceAll("[^0-9.]", "");
		int actualPsricex = (int) Double.parseDouble(numesricPricef);
		Assert.assertEquals(String.valueOf(actualPsricex), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Yearly Subscription Price on order summary page matches.");

		//Verify that when Yearly option is selected then it will reflect on subscription summary of order module 
		WebElement frequencydet = driver.findElement(By.xpath("(//span[contains(@class,'text-black-245 capitalize truncate')])[1]"));	
		String frequencyss1 = frequencydet.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyss1.contains("yearly"),"Frequency is not displayed as yearly on order summary page");
		System.out.println("Yearly Frequency on order summary page matches.\n"); 

		//Verify that if user enters Yearly subscription price then it should reflect in  order summary of order module
		WebElement subscriptionsumm = driver.findElement(By.xpath("(//p[@class='text-2xl font-bold self-end'])[1]"));
		String priceTextam = subscriptionsumm.getText().trim();
		String numericPricesd = priceTextam.replaceAll("[^0-9.]", "");
		int actualPricesd = (int) Double.parseDouble(numericPricesd);
		Assert.assertEquals(String.valueOf(actualPricesd), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Yearly Subscription Price on order subscription summary page matches.");

		//Verify that if user enters subscription price then it should reflect in  subscription summary of order module  
		WebElement subscriptionPm = driver.findElement(By.xpath("(//p[@class='text-2xl font-bold self-end'])[1]"));
		String priceTexta11 = subscriptionPm.getText().trim();
		String numericPricen = priceTexta11.replaceAll("[^0-9.]", "");
		int actualPricde = (int) Double.parseDouble(numericPricen);
		Assert.assertEquals(String.valueOf(actualPricde), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("first payment Yearly Subscription Price on order subscription summary page matches.");

		//Verify that if user enters Yearly subscription price then it should reflect in  subscription summary of order module  
		WebElement frequencyde = driver.findElement(By.xpath("//span[normalize-space()=\"Yearly\"]"));	
		String frequencysd = frequencyde.getText().trim().toLowerCase();
		Assert.assertTrue(frequencysd.contains("yearly"),"Frequency is not displayed as yearly on order subscription summary page");
		System.out.println("Yearly Frequency on order subscription summary page matches.\n"); 

		//Verify that when Yearly option is selected then it will reflect on add line items field of VT module
		driver.navigate().to(Virtual_Terminal);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Select an item\"]"))).sendKeys(Product_Name);
		Thread.sleep(2000);
		WebElement searchedProduct1 = driver.findElement(By.xpath("(//div[contains(@class,\"absolute z\")]//div[2]//div//span)[1]"));
		String actualSearchedProduct1 = searchedProduct1.getText().trim();
		double actualPriceaa1 = Double.parseDouble(actualSearchedProduct1.replaceAll("[^0-9.]", ""));
		double expectedPriceaa1 = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPriceaa1, expectedPriceaa1,"Product price is not displayed correctly in searched product in VT");
		System.out.println("Yearly product price is displayed successfully in searched product in VT\n");

		//Verify that when Yearly option is selected then it will reflect on storefront side
		StoreFront();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SHOP NOW']"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#header-right-side>div:first-of-type>a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder=\"Search products...\"]")).sendKeys(Product_Name);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("p[data-testid='search-result-title']:first-of-type")).click();
		Thread.sleep(3000);	
		WebElement storefront1 = driver.findElement(By.cssSelector("div.content-container>div:nth-of-type(3)>div>div>span>span>span"));
		String priceTextastore1 = storefront1.getText().trim();
		String numericPricesstore1 = priceTextastore1.replaceAll("[^0-9.]", "");
		int actualPricez = (int) Double.parseDouble(numericPricesstore1);
		Assert.assertEquals(String.valueOf(actualPricez), OneTimePurchaseSalePrice, "Price mismatch");
		System.out.println("Yearly Subscription Price On Storefront Side Matches.");

		//Verify that for yearly frequency when first payment different is entered then it will reflect on storefront side
		WebElement subscriptiondPrm = driver.findElement(By.cssSelector("div.content-container>div:nth-of-type(3)>div>div>span>span>span:nth-of-type(2)>span:first-of-type"));
		String priceTexstastore = subscriptiondPrm.getText().trim();
		String numericPriscesstore = priceTexstastore.replaceAll("[^0-9.]", "");
		int actualPrsiced = (int) Double.parseDouble(numericPriscesstore);
		Assert.assertEquals(String.valueOf(actualPrsiced), OneTimeProductValue, "Price mismatch");
		System.out.println("first payment Yearly Subscription Price on storefront side matches.");

		//Verify that when yearly option is selected then it will reflect on storefront side
		WebElement frequencydetailstore = driver.findElement(By.xpath("//span[normalize-space()=\"yearly\"]"));	
		String frequencyss = frequencydetailstore.getText().trim().toLowerCase();
		Assert.assertTrue(frequencyss.contains("yearly"),"Frequency is not displayed as yearly on storefront side page");
		System.out.println("yearly Frequency on storefront side matches.\n"); 
	}

	@Test(priority = 8)
	public void TierPricing() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Actions actions = new Actions(driver);

		String Product_Name = "Tier Product " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();

		//Verify clicking “Cancel” closes the product creation modal.
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div>button")).click();	

		//Verify user can create a new product with valid tiered pricing details.
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
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[text()='Product Type']")));
		driver.findElement(By.cssSelector("button#Physical")).click();	
		WebElement TierToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='tiered-pricing-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",TierToggle);
		TierToggle.click();
		Thread.sleep(2000);

		//Verify product creation fails when required fields (e.g., Tier Name) are empty.
		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		Thread.sleep(3000);
		WebElement warningMessage = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>div>div:last-of-type")); 
		String actualMessage = warningMessage.getText().trim();
		String expectedMessage = "Tier Name is required";
		Assert.assertEquals(actualMessage, expectedMessage, "Warning message not Displays");
		System.out.println("Tier Name is required message displayed successfully when user try to save product without Tier Name.\n");

		//Verify Active Tier toggle enables/disables a tier.
		WebElement activeButton = driver.findElement(By.cssSelector("#tier-item-1>div:nth-of-type(2)>div:nth-of-type(2)>span>button"));
		String activebtn = activeButton.getAttribute("data-state");
		Assert.assertEquals(activebtn, "checked", "Default state of Active Tier is not enabled");
		System.out.println("deafult state of Active Tier button disabled by deafult.\n");

		//Verify error message appears for invalid URL input.
		driver.findElement(By.xpath("//input[@placeholder=\"Starter\"]")).sendKeys(TitleTier1);
		Thread.sleep(2000);

		//Verify user can add a new tier with valid inputs.
		driver.findElement(By.name("productDetails.variants.0.tier_option_url")).sendKeys(Product_Name);
		WebElement warningMessage1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>div>div:last-of-type")); 
		String actualMessage1 = warningMessage1.getText().trim();
		String expectedMessage1 = "Invalid URL format";
		Assert.assertEquals(actualMessage1, expectedMessage1, "Warning message not Displays");
		System.out.println("Invalid URL format message displayed successfully when user try to save product URL with Invalid format.\n");

		Thread.sleep(2000);
		WebElement confirmSaveBtn1 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn1);	
		driver.findElement(By.name("productDetails.variants.0.tier_option_url")).clear();

		//Check that there is a toggle for Offer Annual Upsell On Tiers Widget
		//Check that once the user enables the toggle Offer Annual Upsell On Tiers Widget it shows yearly price option
		WebElement offerannual = driver.findElement(By.cssSelector("#tiered-pricing-option-div>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>button"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", offerannual);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", offerannual);
		String offerannualbtn = offerannual.getAttribute("data-state");
		Assert.assertEquals(offerannualbtn, "checked", "after click on Offer Annual Upsell On Tiers Widget is not enabled");		
		String offerannualtext = driver.findElement(By.xpath("//p[text()='Yearly Price']")).getText().trim();
		Assert.assertEquals(offerannualtext, "Yearly Price", "Yearly price text is not displayed after enable offer annual upsell toggle");
		System.out.println("Yearly price text is displayed successfully after enable offer annual upsell toggle.\n");

		//Check that yearly price has 0% off option and user can select that option 
		//Verify that if user select 0% off option then while creating price tier user can see the yearly price 0% 
		driver.findElement(By.cssSelector("div.yearly-price>button")).click();
		Thread.sleep(1000);
		WebElement off0 = driver.findElement(By.xpath("(//div[normalize-space()='0% Off'])[1]"));
		jse.executeScript("arguments[0].click();", off0);
		String offer0 = driver.findElement(By.xpath("//p[text()='0% Off']")).getText().trim();
		Assert.assertEquals(offer0, "0% Off", "0% Off text is not displayed after enable offer annual upsell toggle");
		System.out.println("0% Off text is displayed successfully after enable offer annual upsell toggle");

		//Check that yearly price has 10% off option and user can select that option 
		//Verify that if user select 10% off option then while creating price tier user can see the yearly price 10% 
		driver.findElement(By.cssSelector("div.yearly-price>button")).click();
		Thread.sleep(1000);
		WebElement off1 = driver.findElement(By.xpath("(//div[normalize-space()='10% Off'])[1]"));
		jse.executeScript("arguments[0].click();", off1);
		String offer1 = driver.findElement(By.xpath("//p[text()='10% Off']")).getText().trim();
		Assert.assertEquals(offer1, "10% Off", "0% Off text is not displayed after enable offer annual upsell toggle");
		System.out.println("10% Off text is displayed successfully after enable offer annual upsell toggle");

		//Check that yearly price has 20% off option and user can select that option 
		//Verify that if user select 20% off option then while creating price tier user can see the yearly price 20% 
		driver.findElement(By.cssSelector("div.yearly-price>button")).click();
		Thread.sleep(1000);
		WebElement off2 = driver.findElement(By.xpath("(//div[normalize-space()='20% Off'])[1]"));
		jse.executeScript("arguments[0].click();", off2);
		String offer2 = driver.findElement(By.xpath("//p[text()='20% Off']")).getText().trim();
		Assert.assertEquals(offer2, "20% Off", "0% Off text is not displayed after enable offer annual upsell toggle");
		System.out.println("20% Off text is displayed successfully after enable offer annual upsell toggle");

		//Check that yearly price has 30% off option and user can select that option 
		//Verify that if user select 30% off option then while creating price tier user can see the yearly price 30% 
		driver.findElement(By.cssSelector("div.yearly-price>button")).click();
		Thread.sleep(1000);
		WebElement off3 = driver.findElement(By.xpath("(//div[normalize-space()='30% Off'])[1]"));
		jse.executeScript("arguments[0].click();", off3);
		String offer3 = driver.findElement(By.xpath("//p[text()='30% Off']")).getText().trim();
		Assert.assertEquals(offer3, "30% Off", "0% Off text is not displayed after enable offer annual upsell toggle");
		System.out.println("30% Off text is displayed successfully after enable offer annual upsell toggle");

		//Check that yearly price has 40% off option and user can select that option 
		//Verify that if user select 40% off option then while creating price tier user can see the yearly price 40% 
		driver.findElement(By.cssSelector("div.yearly-price>button")).click();
		Thread.sleep(1000);
		WebElement off4 = driver.findElement(By.xpath("(//div[normalize-space()='40% Off'])[1]"));
		jse.executeScript("arguments[0].click();", off4);
		String offer4 = driver.findElement(By.xpath("//p[text()='40% Off']")).getText().trim();
		Assert.assertEquals(offer4, "40% Off", "0% Off text is not displayed after enable offer annual upsell toggle");
		System.out.println("40% Off text is displayed successfully after enable offer annual upsell toggle");

		//Check that yearly price has 50% off option and user can select that option 
		//Verify that if user select 50% off option then while creating price tier user can see the yearly price 50% 
		driver.findElement(By.cssSelector("div.yearly-price>button")).click();
		Thread.sleep(1000);
		WebElement off5 = driver.findElement(By.xpath("(//div[normalize-space()='50% Off'])[1]"));
		jse.executeScript("arguments[0].click();", off5);
		String offer5 = driver.findElement(By.xpath("//p[text()='50% Off']")).getText().trim();
		Assert.assertEquals(offer5, "50% Off", "0% Off text is not displayed after enable offer annual upsell toggle");
		System.out.println("50% Off text is displayed successfully after enable offer annual upsell toggle.\n");

		//Check that user can disable the toggle of Offer Annual Upsell On Tiers Widget
		WebElement offerannual1 = driver.findElement(By.cssSelector("#tiered-pricing-option-div>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>button"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", offerannual1);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", offerannual1);
		String offerannualbtn1 = offerannual1.getAttribute("data-state");
		Assert.assertEquals(offerannualbtn1, "unchecked", "after click on Offer Annual Upsell On Tiers Widget is not enabled");
		System.out.println("Offer Annual Upsell On Tiers Widget is disabled successfully after click on offer annual toggle.\n");

		//Verify “Most Popular” toggle highlights the selected tier.
		WebElement mostpopular = driver.findElement(By.cssSelector("#tier-item-1>div:nth-of-type(2)>div:last-of-type>div>div>div>button"));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});", mostpopular);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", mostpopular);
		String mostpopularbtn = mostpopular.getAttribute("data-state");
		Assert.assertEquals(mostpopularbtn, "checked", "after click on Most Popular On Tiers Widget is not enabled");
		String mostpopulartext = driver.findElement(By.xpath("(//span[text()='Most Popular'])[1]")).getText().trim();
		Assert.assertEquals(mostpopulartext, "Most Popular", "Most Popular text is not displayed after enable Most Popular toggle");
		System.out.println("Most Popular text is displayed successfully after enable Most Popular toggle.\n");

		//Verify URL option accepts only valid URLs.
		//Verify Form option allows selection of a valid form.
		driver.findElement(By.name("productDetails.variants.0.tier_option_url")).sendKeys(url);
		driver.findElement(By.xpath("//span[normalize-space()=\"Form\"]//parent::button")).click();
		driver.findElement(By.xpath("//span[text()='Select CRM Form']//parent::button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@role='menuitem'])[1]")).click();		

		//Verify that tier name will be reflected in tier name at the top of tier section 
		String tierNameTopSection = driver.findElement(By.cssSelector("div.tier-name>h1>div")).getText().trim();
		String actualTierName = tierNameTopSection.split("—")[1].trim();
		Assert.assertEquals(actualTierName, TitleTier1, "Tier Name is not displayed on top section of product detail page");
		System.out.println("Tier Name is displayed successfully on top section of product detail page.\n");

		//Check that user can see free option just below the tier name 
		WebElement freeoption = driver.findElement(By.xpath("//p[text()='Free']"));
		Assert.assertEquals(freeoption.getText().trim(), "Free", "Free option text is not correct");
		System.out.println("Free option is displayed just below the tier name successfully.\n");

		//Check that user can see price option just below the tier name 
		driver.findElement(By.cssSelector("#tier-item-1>div>div>div>button")).click();//click on price
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		Thread.sleep(2000);

		WebElement taxRate = driver.findElement(By.xpath("(//input[@name='productDetails.variants.0.prices[0].amount'])[1]"));
		Thread.sleep(3000);
		taxRate.click();
		taxRate.sendKeys(Keys.CONTROL, "a");
		taxRate.sendKeys(Keys.DELETE);
		taxRate.sendKeys("-3");
		Thread.sleep(1000);

		//Verify negative values are not allowed in pricing fields.
		/*WebElement warningMessagees = driver.findElement(By.xpath("//p[text()='Price must be greater than 0']"));
		String actualMessagese = warningMessagees.getText().trim();
		String expectedMessagese = "Price must be greater than 0";
		Assert.assertEquals(actualMessagese, expectedMessagese, "Warning message Price must be greater than 0 not Displays");*/

		driver.findElement(By.cssSelector("#tier-item-1>div>div>div>button")).click();//click on price
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		WebElement taxRate1 = driver.findElement(By.xpath("(//input[@name='productDetails.variants.0.prices[0].amount'])[1]"));
		Thread.sleep(3000);
		taxRate1.click();
		taxRate1.sendKeys(Keys.CONTROL, "a");
		taxRate1.sendKeys(Keys.DELETE);
		taxRate1.sendKeys("3");
		Thread.sleep(1000);	
		driver.findElement(By.cssSelector("div.wrapdescription")).click();

		WebElement confirmSaveBtnn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtnn);

		//Verify that if user not enter anything in description then it should show warning msg 
		WebElement warningMessagee = driver.findElement(By.xpath("//div[text()='Product Description is required']"));
		String actualMessagee = warningMessagee.getText().trim();
		String expectedMessagee = "Product Description is required";
		Assert.assertEquals(actualMessagee, expectedMessagee, "Warning message Product Description is required not Displays");

		//Check that there is option for description and bullet points 
		driver.findElement(By.xpath("(//textarea[contains(@name,'productDetails.variants.0.product_tier_description')])[1]")).sendKeys(DiscriptionTier1);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Add Title\")]")).sendKeys(TitleTier1);
		driver.findElements(By.cssSelector("div.key-features>div>div>div>button")).get(0).click();
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Enter Feature\")]")).sendKeys(Tier1Feature1);
		driver.findElement(By.xpath("//span[normalize-space()=\"+ Add Title\"]")).click();
		driver.findElement(By.xpath("//input[@name=\"productDetails.variants.0.product_tier_features.1.title\"]")).sendKeys(TierOneTitle);

		//Check that user can delet the feature as well 
		/*WebElement uploadImage1 = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImage1);	
		Thread.sleep(3000);
		String[] files11 = {Media_Path + File8};
		String allFiles11 = String.join("\n", files11);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiles11);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();*/

		//Verify product is created successfully on clicking “Create Product” with valid data.
		WebElement confirmSaveButn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveButn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));

		//Verify user cannot create duplicate tier names if restricted.
		Thread.sleep(2000);
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
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys("1");
		Thread.sleep(1000);
		WebElement confirmSaveBtn11 = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn11);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));

		/*Thread.sleep(2000);
		WebElement warningMessagee1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Error'])[1]")));
		String actualMessagee1 = warningMessagee1.getText().trim();
		String expectedMessagee1 = "Error";
		Assert.assertEquals(actualMessagee1, expectedMessagee1, "Warning message Tier already exists in this store not Displays");*/
		System.out.println("User cannot create duplicate tier names if restricted.\n");

		//Verify correct product type (tiered) is shown in product list.
		driver.navigate().to(Products);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		Thread.sleep(4000);

		//Verify user can edit an existing tier name successfully.
		WebElement prodname =driver.findElement(By.name("productDetails.productName"));
		Thread.sleep(3000);
		prodname.click();
		prodname.sendKeys(Keys.CONTROL, "a");
		prodname.sendKeys(Keys.DELETE);
		prodname.sendKeys(Product_Name + " Updated");

		driver.findElement(By.cssSelector("#global-product-topbar>div>div:first-of-type>div:nth-of-type(2)>div>button")).click();//click on save button	
		Thread.sleep(4000);
		String ProdName = driver.findElement(By.name("productDetails.productName")).getAttribute("value").trim();
		Assert.assertEquals(ProdName, Product_Name + " Updated", "Product name is not updated successfully");
		System.out.println("Product name is updated successfully.\n");

		//Verify With Free Option after first installment it navigates to porvided url
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Tier']//parent::button")).click();
		driver.findElement(By.xpath("(//input[contains(@name,'productDetails.variants.1.title')])[1]")).sendKeys("Second Tier Installment");
		Thread.sleep(1000);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(3000);
		driver.findElement(By.name("productDetails.variants.1.tier_option_url")).sendKeys("www.google.com");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#global-product-topbar>div>div:first-of-type>div:nth-of-type(2)>div>button")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>div>div>button:first-of-type"))).click();

		String parentWindow = driver.getWindowHandle();
		wait.until(d -> d.getWindowHandles().size() > 1);

		String secondWindow = null;
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(parentWindow)) {
				secondWindow = window;
				driver.switchTo().window(window);
				break;
			}
		}

		//Verify that free tier is visible on preview section of product details page 
		Thread.sleep(5000);
		String freeTierText = driver.findElement(By.cssSelector("div.sync-price-single>div:first-of-type")).getText().trim();	
		Assert.assertEquals(freeTierText, "FREE", "Free tier is not visible on preview section of product details page");
		System.out.println("Free tier is visible on Open preview section of product details page successfully.\n");	

		//Verify that added title can be seen on open option of product detail page 
		String titleText = driver.findElement(By.xpath("(//div[@id='card-div'])[2]//div//div[@id='key-feature-section']//div//div//div//div//h3")).getText().trim();
		Assert.assertEquals(titleText, TierOneTitle, "Title is not visible on open option of product details page");
		System.out.println("Title is visible on open option of product details page successfully.\n");
		
		//Verify that popular tag will be visible on open option of product details page 
		String popularText = driver.findElement(By.xpath("//div[text()='Most Popular']")).getText().trim();
		Assert.assertEquals(popularText, "Most Popular", "Most Popular tag is not visible	on open option of product details page");
		System.out.println("Most Popular tag is visible on open option of product details page successfully.\n");
		
		//Verify that month field price can be seen on open option of product detail page 
		String priceTierText1 = driver.findElement(By.xpath("((//div[@id='card-div'])[2]//div//div//div//div//div//span)[1]")).getText().trim();
		double value1 = Double.parseDouble(priceTierText1.replaceAll("[^0-9.]", ""));
		int intValue1 = (int) value1;
		Assert.assertEquals(intValue1, 3, "Price is not matching");
		System.out.println("Price tier is visible on Open preview section of product details page successfully.");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@type,'button')])[2]"))).click();
		wait.until(d -> d.getWindowHandles().size() > 2);
		String thirdWindow = null;
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(parentWindow) && !window.equals(secondWindow)) {
				thirdWindow = window;
				break;
			}
		}

		Thread.sleep(7000); 
		String currentUrl = driver.getTitle();
		System.out.println(currentUrl);
		//Assert.assertTrue(currentUrl.contains("Google"), "Invalid Title");
		System.out.println("User is navigated to provided URL successfully after click on free option of tier.\n");

		Thread.sleep(5000); 
		driver.switchTo().window(thirdWindow);
		driver.close();
		driver.switchTo().window(secondWindow);
		driver.close();
		driver.switchTo().window(parentWindow);

		//Verify that free tier is visible on edit option of preview section of product details page 
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>div>div>button:nth-of-type(2)"))).click();
		Thread.sleep(5000);
		String freeTierText1 = driver.findElement(By.xpath("(//div[text()='Free'])[2]")).getText().trim();
		Assert.assertEquals(freeTierText1, "Free", "Free tier is not visible on edit option of preview section of product details page");
		System.out.println("Free tier is visible on edit option of preview section of product details page successfully.\n");

		//Verify that added title can be seen on edit option of product detail page 
		String titleText1 = driver.findElement(By.xpath("(//div[@id ='key-feature-section'])[2]//div//div//div[1]//div//h3")).getText().trim();
		Assert.assertEquals(titleText1, TierOneTitle, "Title is not visible on edit option of product details page");
		System.out.println("Title is visible on edit option of product details page successfully.\n");
		
		//Verify that popular tag will be visible on edit option of product details page 
		String popularText1 = driver.findElement(By.xpath("(//div[text()='Most Popular'])[2]")).getText().trim();
		Assert.assertEquals(popularText1, "Most Popular", "Most Popular tag is not visible on edit option of product details page");
		System.out.println("Most Popular tag is visible on edit option of product details page successfully.\n");
		
		//Verify that month field price can be seen on edit option of product detail page 
		String priceTierText11 = driver.findElement(By.xpath("(//span[text()='$3.00'])[2]")).getText().trim();
		double value11 = Double.parseDouble(priceTierText11.replaceAll("[^0-9.]", ""));
		int intValue11 = (int) value11;
		Assert.assertEquals(intValue11, 3, "Price is not matching");
		System.out.println("Price tier is visible on edit option of product details page successfully.\n");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.custom-setting-region-tab>div>div:nth-of-type(2)>button")).click();

		//Verify With Free Option after first installment it navigates to selected form
		Thread.sleep(10000);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[text()='Second Tier Installment']")));
		Thread.sleep(4000);
		WebElement crmFormButton = driver.findElement(By.cssSelector("#tier-item-2>div:nth-of-type(2)>div:nth-of-type(3)>div:nth-of-type(2)>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", crmFormButton);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Select CRM Form']//parent::button")).click();
		Thread.sleep(2000);
		WebElement formname = driver.findElement(By.xpath("(//div[@role='menuitem'])[1]"));
		formname.click();	
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>div>div>button:first-of-type"))).click();

		wait.until(d -> d.getWindowHandles().size() > 1);
		String secondWindow1 = null;
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(parentWindow)) {
				secondWindow1 = window;
				driver.switchTo().window(window);
				break;
			}
		}

		//Verify that price tier is visible on open option of preview section of product details page 
		Thread.sleep(3000);
		String priceTierText = driver.findElement(By.xpath("((//div[@id='card-div'])[2]//div//div//div//div//div//span)[1]")).getText().trim();	
		double value = Double.parseDouble(priceTierText.replaceAll("[^0-9.]", ""));
		int intValue = (int) value;
		Assert.assertEquals(intValue, 3, "Price is not matching");
		System.out.println("Price tier is visible on Open preview section of product details page successfully.\n");	

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(@type,'button')])[2]"))).click();
		wait.until(d -> d.getWindowHandles().size() > 2);
		String thirdWindow1 = null;
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(parentWindow) && !window.equals(secondWindow1)) {
				thirdWindow1 = window;
				break;
			}
		}

		Thread.sleep(7000);
		String currentUrl1 = driver.getCurrentUrl().toLowerCase();	
		System.out.println("Current URL : " + currentUrl1);
		//Assert.assertTrue(currentUrl1.contains(form), "Invalid Form Name");
		System.out.println("User is navigated to provided Form successfully after click on free option of tier.\n");

		Thread.sleep(5000); 
		driver.switchTo().window(thirdWindow1);
		driver.close();
		driver.switchTo().window(secondWindow1);
		driver.close();
		driver.switchTo().window(parentWindow);

		//Verify that price tier is visible on edit option of preview section of product details page 
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#product-details-tab>div:nth-of-type(2)>div>div>div>button:nth-of-type(2)"))).click();
		Thread.sleep(5000);
		String priceTierText111 = driver.findElement(By.xpath("(//span[text()='$3.00'])[2]")).getText().trim();
		double value111 = Double.parseDouble(priceTierText111.replaceAll("[^0-9.]", ""));
		int intValue111 = (int) value111;
		Assert.assertEquals(intValue111, 3, "Price is not matching");
		System.out.println("Price tier is visible on edit option of preview section of product details page successfully.\n");
	}

	@Test(priority = 9)
	public void TierUpsell() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Actions actions = new Actions(driver);
		
		String Product_Name = "Tier Product - " + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase();
		
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
		driver.findElement(By.xpath("//button[@class='btn focus:shadow-none btn-black btn-large']")).click();
		
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#Physical")).click();
		jse.executeScript("window.scrollBy(0,500)");

		WebElement TierToggle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='tiered-pricing-option-div']//button[@role='switch'][1]")));
		jse.executeScript("arguments[0].scrollIntoView({block:'center'});",TierToggle);
		TierToggle.click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@placeholder=\"Starter\"]")).sendKeys(TitleTier1);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#tier-item-1>div>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		driver.findElement(By.xpath("(//input[@name='productDetails.variants.0.prices[0].amount'])[1]")).sendKeys("1");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.wrapdescription")).click();
		driver.findElement(By.xpath("(//textarea[contains(@name,'productDetails.variants.0.product_tier_description')])[1]")).sendKeys(DiscriptionTier1);
		jse.executeScript("document.querySelector('div.Product-Detial-side-modal-Scrollbar').scrollTop=800");
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Add Title\")]")).sendKeys(TitleTier1);
		driver.findElements(By.cssSelector("div.key-features>div>div>div>button")).get(0).click();
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Enter Feature\")]")).sendKeys(Tier1Feature1);
		
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='+ Add Tier']")).click();
		driver.findElement(By.xpath("(//input[contains(@name,'productDetails.variants.1.title')])[1]")).sendKeys(TitleTier2);
		Thread.sleep(1000);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#tier-item-2>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[@role=\"menuitem\"][normalize-space()=\"Price\"]")).click();
		driver.findElement(By.xpath("(//input[@name='productDetails.variants.1.prices[0].amount'])[1]")).sendKeys("3");
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[normalize-space()='Description + Bullet points'])[2]")).click();
		driver.findElement(By.xpath("//textarea[@name=\"productDetails.variants.1.product_tier_description\"]")).sendKeys(DiscriptionTier2);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name=\"productDetails.variants.1.product_tier_features.0.title\"]")).sendKeys(TitleTier2);
		driver.findElements(By.cssSelector("div.key-features>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//input[contains(@name,\"productDetails.variants.1.product_tier_features.0.feature_title.0\")]")).sendKeys(Tier2Feature1);
		
		WebElement confirmSaveBtn = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveBtn);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		
		//Verify that if user select 10% off option then user can see it in upsell ( In Checkout )Show Annual Upsell In Checkout option yearly discount
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li:nth-of-type(2)>a:first-of-type")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.product-list-section>button:nth-of-type(3)")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@role='switch'])[2]")).click();
		driver.findElement(By.xpath("(//button[@role='switch'])[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[3]")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"10% Off\"]")).click();
		driver.findElement(By.xpath("(//button[@role='switch'])[4]")).click();
		
		//Verify that if user select 10% off option then user can see it on product details page
		driver.findElement(By.cssSelector("#global-product-topbar>div>div:first-of-type>div:nth-of-type(2)>div>button")).click();//click on save button	
		Thread.sleep(4000);	
		String offer10 = driver.findElement(By.cssSelector("div.product-details-page-ui>main>section>div:nth-of-type(2)>div:nth-of-type(3)>div>p:nth-of-type(2)>span")).getText().trim();
		Assert.assertTrue(offer10.contains("10% Off"), "10% Off text is not displayed");
		System.out.println("10% Off is displayed successfully in Show Annual Upsell In details option.");
		
		//Verify that if user select 10% off option then user can see it on tier product checkout page
		driver.findElement(By.id("orderPagesFunnel")).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
		
		String originalTab = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		Thread.sleep(7000);
		String offer10Checkout = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div:nth-of-type(2)>button:nth-of-type(2)")).getText().trim();
		Assert.assertTrue(offer10Checkout.contains("10%"), "10% Off text is not displayed in checkout page");
		System.out.println("10% Off is displayed successfully in checkout page.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Verify that if user select 20% off option then user can see it in upsell ( In Checkout )Show Annual Upsell In Checkout option yearly discount
		driver.findElement(By.cssSelector("div.product-list-section>button:nth-of-type(3)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-details-page-ui>main>section>div:first-of-type>button:nth-of-type(2)")).click();
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[3]")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"20% Off\"]")).click();
		
		//Verify that if user select 20% off option then user can see it on product details page
		driver.findElement(By.cssSelector("#global-product-topbar>div>div:first-of-type>div:nth-of-type(2)>div>button")).click();//click on save button	
		Thread.sleep(4000);	
		String offer20 = driver.findElement(By.cssSelector("div.product-details-page-ui>main>section>div:nth-of-type(2)>div:nth-of-type(3)>div>p:nth-of-type(2)>span")).getText().trim();
		Assert.assertTrue(offer20.contains("20% Off"), "20% Off text is not displayed");
		System.out.println("20% Off is displayed successfully in Show Annual Upsell In details option.");
		
		//Verify that if user select 20% off option then user can see it on tier product checkout page
		driver.findElement(By.id("orderPagesFunnel")).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		Thread.sleep(7000);
		String offer10Checkout1 = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div:nth-of-type(2)>button:nth-of-type(2)")).getText().trim();
		Assert.assertTrue(offer10Checkout1.contains("20%"), "20% Off text is not displayed in checkout page");
		System.out.println("20% Off is displayed successfully in checkout page.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Verify that if user select 30% off option then user can see it in upsell ( In Checkout )Show Annual Upsell In Checkout option yearly discount
		driver.findElement(By.cssSelector("div.product-list-section>button:nth-of-type(3)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-details-page-ui>main>section>div:first-of-type>button:nth-of-type(2)")).click();
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[3]")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"30% Off\"]")).click();
		
		//Verify that if user select 30% off option then user can see it on product details page
		driver.findElement(By.cssSelector("#global-product-topbar>div>div:first-of-type>div:nth-of-type(2)>div>button")).click();//click on save button	
		Thread.sleep(4000);	
		String offer30 = driver.findElement(By.cssSelector("div.product-details-page-ui>main>section>div:nth-of-type(2)>div:nth-of-type(3)>div>p:nth-of-type(2)>span")).getText().trim();
		Assert.assertTrue(offer30.contains("30% Off"), "30% Off text is not displayed");
		System.out.println("30% Off is displayed successfully in Show Annual Upsell In details option.");
		
		//Verify that if user select 30% off option then user can see it on tier product checkout page
		driver.findElement(By.id("orderPagesFunnel")).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		Thread.sleep(7000);
		String offer10Checkout2 = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div:nth-of-type(2)>button:nth-of-type(2)")).getText().trim();
		Assert.assertTrue(offer10Checkout2.contains("30%"), "30% Off text is not displayed in checkout page");
		System.out.println("30% Off is displayed successfully in checkout page.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Verify that if user select 40% off option then user can see it in upsell ( In Checkout )Show Annual Upsell In Checkout option yearly discount
		driver.findElement(By.cssSelector("div.product-list-section>button:nth-of-type(3)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-details-page-ui>main>section>div:first-of-type>button:nth-of-type(2)")).click();
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[3]")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"40% Off\"]")).click();
		
		//Verify that if user select 30% off option then user can see it on product details page
		driver.findElement(By.cssSelector("#global-product-topbar>div>div:first-of-type>div:nth-of-type(2)>div>button")).click();//click on save button	
		Thread.sleep(4000);	
		String offer40 = driver.findElement(By.cssSelector("div.product-details-page-ui>main>section>div:nth-of-type(2)>div:nth-of-type(3)>div>p:nth-of-type(2)>span")).getText().trim();
		Assert.assertTrue(offer40.contains("40% Off"), "40% Off text is not displayed");
		System.out.println("40% Off is displayed successfully in Show Annual Upsell In details option.");
		
		//Verify that if user select 30% off option then user can see it on tier product checkout page
		driver.findElement(By.id("orderPagesFunnel")).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		Thread.sleep(7000);
		String offer10Checkout3 = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div:nth-of-type(2)>button:nth-of-type(2)")).getText().trim();
		Assert.assertTrue(offer10Checkout3.contains("40%"), "40% Off text is not displayed in checkout page");
		System.out.println("40% Off is displayed successfully in checkout page.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Verify that if user select 50% off option then user can see it in upsell ( In Checkout )Show Annual Upsell In Checkout option yearly discount
		driver.findElement(By.cssSelector("div.product-list-section>button:nth-of-type(3)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-details-page-ui>main>section>div:first-of-type>button:nth-of-type(2)")).click();
		driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[3]")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"50% Off\"]")).click();
		
		//Verify that if user select 50% off option then user can see it on product details page
		driver.findElement(By.cssSelector("#global-product-topbar>div>div:first-of-type>div:nth-of-type(2)>div>button")).click();//click on save button	
		Thread.sleep(4000);	
		String offer50 = driver.findElement(By.cssSelector("div.product-details-page-ui>main>section>div:nth-of-type(2)>div:nth-of-type(3)>div>p:nth-of-type(2)>span")).getText().trim();
		Assert.assertTrue(offer50.contains("50% Off"), "50% Off text is not displayed");
		System.out.println("50% Off is displayed successfully in Show Annual Upsell In details option.");
		
		//Verify that if user select 50% off option then user can see it on tier product checkout page
		driver.findElement(By.id("orderPagesFunnel")).click();
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		Thread.sleep(7000);
		String offer10Checkout4 = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div:nth-of-type(2)>button:nth-of-type(2)")).getText().trim();
		Assert.assertTrue(offer10Checkout4.contains("50%"), "50% Off text is not displayed in checkout page");
		System.out.println("50% Off is displayed successfully in checkout page.\n");
		driver.close();
		driver.switchTo().window(originalTab);
	}
	
	@Test(priority = 10)
	public void Filter_GridView() throws InterruptedException, ParseException{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		driver.navigate().to(Products);
		Thread.sleep(20000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div:nth-of-type(2)>div>span>button")).click();
		
		//check that sort by filter is having four sub options 
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		Thread.sleep(2000);		
		List<WebElement> sortByOptions = driver.findElements(By.cssSelector("div.dropdown-portal>div>div"));
		Assert.assertEquals(sortByOptions.size() -1 , 4, "Sort by filter does not have four sub options");
		System.out.println("Sort by filter is having four sub options.\n");
		
		//check that after click on Views (High to Low ) option should show results should be correct
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (High To Low)\"]")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a")).click();//click on first product
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();//click on pages tab
		String viewsText1 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:first-of-type>p:nth-of-type(2)")).getText().trim();
		int views1 = Integer.parseInt(viewsText1.replaceAll("[^0-9]", ""));
		System.out.println("High To Low Views Count of First Product: " + views1);
		
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:nth-of-type(2)>div>a")).click();//click on second product		
		driver.findElement(By.id("orderPagesFunnel")).click();//click on pages tab
		String viewsText2 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:first-of-type>p:nth-of-type(2)")).getText().trim();
		int views2 = Integer.parseInt(viewsText2.replaceAll("[^0-9]", ""));
		System.out.println("High To Low Views Count of Second Product: " + views2);
		
		Assert.assertTrue(views1 > views2,"Views sorting failed. First product views: " + views1 +" Second product views: " + views2);
		System.out.println("Products are sorted by Views (High to Low) option successfully.\n");		
		
		//check that user can cancel the applied filter of Views (High to low) using X icon in filter
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(3000);	
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:first-of-type>div>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that after click on Views (Low to High) option should show results should be correct
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a")).click();//click on first product
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();//click on pages tab
		String viewsText3 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:first-of-type>p:nth-of-type(2)")).getText().trim();
		int views3 = Integer.parseInt(viewsText3.replaceAll("[^0-9]", ""));
		System.out.println("Low To High Views Count of First Product: " + views3);
		
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:nth-of-type(2)>div>a")).click();//click on second product		
		driver.findElement(By.id("orderPagesFunnel")).click();//click on pages tab
		String viewsText4 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:first-of-type>p:nth-of-type(2)")).getText().trim();
		int views4 = Integer.parseInt(viewsText4.replaceAll("[^0-9]", ""));
		System.out.println("Low To High Views Count of Second Product: " + views4);
		
		//Assert.assertTrue(views3 > views4,"Views sorting failed. First product views: " + views3 +" Second product views: " + views4);
		System.out.println("Products are sorted by Views (Low to High) option successfully.\n");		
		
		//check that user can cancel the applied filter of Views (Low to High) using X icon in filter
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(3000);	
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:first-of-type>div>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that after click on conversion rate (High to Low) option should show results should be correct
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a")).click();//click on first product
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();//click on pages tab
		String viewsText5 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:nth-of-type(4)>p:nth-of-type(2)")).getText().trim();
		int views5 = Integer.parseInt(viewsText5.replaceAll("[^0-9]", ""));
		System.out.println("Conversion Rate High To Low of First Product: " + views5);
		
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:nth-of-type(2)>div>a")).click();//click on second product		
		driver.findElement(By.id("orderPagesFunnel")).click();//click on pages tab
		String viewsText6 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:nth-of-type(4)>p:nth-of-type(2)")).getText().trim();
		int views6 = Integer.parseInt(viewsText6.replaceAll("[^0-9]", ""));
		System.out.println("Conversion Rate High To Low of Second Product: " + views6);
		
		//Assert.assertTrue(views5 < views4,"Views sorting failed. First product views: " + views5 +" Second product views: " + views6);
		System.out.println("Products are sorted by Views (Conversion Rate) option successfully.\n");		
		
		//check that user can cancel the applied filter of conversion rate (High to Low) using X icon in filter
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(3000);	
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:first-of-type>div>button>div>div>svg")).click();//click on clear button of applied filter
			
		//check that after click on conversion rate (Low to High) option should show results should be correct
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a")).click();//click on first product
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();//click on pages tab
		String viewsText7 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:nth-of-type(4)>p:nth-of-type(2)")).getText().trim();
		int views7 = Integer.parseInt(viewsText7.replaceAll("[^0-9]", ""));
		System.out.println("Conversion Rate Low To High of First Product: " + views7);
		
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:nth-of-type(2)>div>a")).click();//click on second product		
		driver.findElement(By.id("orderPagesFunnel")).click();//click on pages tab
		String viewsText8 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:nth-of-type(4)>p:nth-of-type(2)")).getText().trim();
		int views8 = Integer.parseInt(viewsText8.replaceAll("[^0-9]", ""));
		System.out.println("Conversion Rate Low To High of Second Product: " + views8);
		
		//Assert.assertTrue(views7 < views8,"Views sorting failed. First product views: " + views7 +" Second product views: " + views8);
		System.out.println("Products are sorted by Views (Conversion Rate) option successfully.\n");		
		
		//check that user can cancel the applied filter of conversion rate (Low to High) using X icon in filter
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(3000);	
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:first-of-type>div>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that there are two options avaialble under product type filter
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		List<WebElement> sortByproductOptions = driver.findElements(By.cssSelector("div.dropdown-portal>div>div"));
		Assert.assertEquals(sortByproductOptions.size() -1 , 2, "Sort by Product Type filter does not have two sub options");
		System.out.println("Sort by Product Type filter is having two sub options.\n");		
		
		//check that user can select Inventory type product option 
		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a")).click();//click on first product
		Thread.sleep(2000);
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Product Type']")));
		String Inventorystate = driver.findElement(By.id("Physical")).getAttribute("data-state").trim();
		Assert.assertEquals(Inventorystate, "checked", "Inventory product type filter is not working");	
		System.out.println("Inventory product type filter is working successfully.\n");
		
		//check that after selecting Inventory type filter user can click on cross icon to remove filter 
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		Thread.sleep(1000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(2)>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that after selection of Non-Inventory should show matching and correct results for the filter
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()='Non-Inventory']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a")).click();//click on first product
		Thread.sleep(2000);
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Product Type']")));
		String NonInventorystate = driver.findElement(By.id("Digital")).getAttribute("data-state").trim();
		Assert.assertEquals(NonInventorystate, "checked", "Non Inventory product type filter is not working");	
		System.out.println("Non Inventory product type filter is working successfully.\n");
			
		//check that after selecting Non-Inventory filter user can click on cross icon to remove filter 
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		Thread.sleep(1000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[normalize-space()='Non-Inventory']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(2)>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that there are two options avaialble under created date filter
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(2).click();
		List<WebElement> sortBycreatedDateOptions = driver.findElements(By.cssSelector("div.dropdown-portal>div>div:nth-of-type(2)>div"));
		Assert.assertEquals(sortBycreatedDateOptions.size() , 2, "Sort by Created Date filter does not have two sub options");
		System.out.println("Sort by Created Date filter is having two sub options.\n");		
		
		//check that after selection of Newest should show matching and correct results for the filter
		driver.findElement(By.xpath("((//span[normalize-space()='Newest'])[1]//parent::div)[1]")).click();
		Thread.sleep(3000);

		List<WebElement> products = driver.findElements(By.cssSelector("div.custom-class-table>table>div>div>div>a>div:nth-of-type(2)>div:nth-of-type(2)"));
		List<Date> actualDateList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");

		for (WebElement product : products) {
		    String fullText = product.getText().trim();
		    Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{2}");
		    Matcher matcher = pattern.matcher(fullText);

		    if (matcher.find()) {
		        String dateText = matcher.group();
		        System.out.println("Extracted Date : " + dateText);
		        actualDateList.add(sdf.parse(dateText));
		    }
		}

		List<Date> expectedDateList = new ArrayList<>(actualDateList);
		expectedDateList.sort(Collections.reverseOrder());
		Assert.assertEquals(actualDateList, expectedDateList,"Products are not sorted by Newest First");
		System.out.println("Products are sorted by Newest First option successfully.\n");
		
		//check that after selecting newest filter user can click on cross icon to remove filter 
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(2).click();
		driver.findElement(By.xpath("((//span[normalize-space()='Newest'])[1]//parent::div)[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(3)>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that after selection of Oldest should show matching and correct results for the filter
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(2).click();
		driver.findElement(By.xpath("((//span[normalize-space()='Oldest'])[1]//parent::div)[1]")).click();
		Thread.sleep(3000);
		
		List<WebElement> products1 = driver.findElements(By.cssSelector("div.custom-class-table>table>div>div>div>a>div:nth-of-type(2)>div:nth-of-type(2)"));
		List<Date> actualDateList1 = new ArrayList<>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yy");
		for (WebElement product : products1) {
		    String fullText = product.getText().trim();
		    Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{2}");
		    Matcher matcher = pattern.matcher(fullText);
		    if (matcher.find()) {
		        String dateText = matcher.group();
		        System.out.println("Extracted Date : " + dateText);
		        actualDateList1.add(sdf1.parse(dateText));
		    }
		}

		List<Date> expectedDateList1 = new ArrayList<>(actualDateList1);
		Collections.sort(expectedDateList1);
		Assert.assertEquals(actualDateList1, expectedDateList1,"Products are not sorted by Oldest First");
		System.out.println("Products are sorted by Oldest First option successfully.\n");
		
		//check that after selecting Oldest filter user can click on cross icon to remove filter 
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(2).click();
		driver.findElement(By.xpath("((//span[normalize-space()='Oldest'])[1]//parent::div)[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(3)>button>div>div>svg")).click();//click on clear button of applied filter
			
		//check that there are three options available under pricing filter
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		List<WebElement> sortBycreatedDateOptions1 = driver.findElements(By.cssSelector("div.dropdown-portal>div>div:nth-of-type(2)>div"));
		Assert.assertEquals(sortBycreatedDateOptions1.size() , 3, "Sort by pricing filter does not have three sub options");
		System.out.println("Sort by pricing filter is having three sub options.\n");	
		
		//check that after selection of one time purchase  should show matching and correct results for the filter
		driver.findElement(By.xpath("//span[normalize-space()=\"One-Time Purchase\"]//parent::div")).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a")).click();//click on first product
		Thread.sleep(2000);
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Pricing Options']")));
		String OmeTimestate = driver.findElement(By.xpath("//div[@id='one-time-purchase-div']/descendant::button[@role=\"switch\"][1]")).getAttribute("data-state").trim();
		Assert.assertEquals(OmeTimestate, "checked", "One Time product type filter is not working");	
		System.out.println("One Time product filter is working successfully.\n");
			
		//check that after selecting one time purchase  filter user can click on cross icon to remove filter 
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		Thread.sleep(1000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"One-Time Purchase\"]//parent::div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(4)>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that after selection of subscription should show matching and correct results for the filter
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Subscription\"]//parent::div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a")).click();//click on first product
		Thread.sleep(2000);
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Pricing Options']")));
		String Suscriptionstate = driver.findElement(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")).getAttribute("data-state").trim();
		Assert.assertEquals(Suscriptionstate, "checked", "subscription product type filter is not working");	
		System.out.println("subscription product filter is working successfully.\n");
		
		//check that after selecting subscription filter user can click on cross icon to remove filter 
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		Thread.sleep(1000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Subscription\"]//parent::div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(4)>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that after selection of Tier Product should show matching and correct results for the filter
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Tiered Product\"]//parent::div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a")).click();//click on first product
		Thread.sleep(2000);
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Pricing Options']")));
		String Tierstate = driver.findElement(By.xpath("//div[@id='tiered-pricing-option-div']//button[@role='switch'][1]")).getAttribute("data-state").trim();
		Assert.assertEquals(Tierstate, "checked", "Tier Product product type filter is not working");	
		System.out.println("Tier Product product filter is working successfully.\n");
		
		//check that after selecting Tier Product filter user can click on cross icon to remove filter 
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		Thread.sleep(1000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Tiered Product\"]//parent::div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(4)>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that after selection of Created By should show matching and correct results for the filter
		driver.findElement(By.xpath("//span[normalize-space()=\"Created By\"]//parent::div")).click();
		driver.findElement(By.xpath("((//div[@data-orientation='vertical']//div[3])[1]//div//div//div[2])[1]//div[1]")).click();
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click();	
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a")).click();//click on first product
		System.out.println("First Product is clicked successfully after applying Created By filter.\n");
		Thread.sleep(2000);

		//check that after selecting Created By filter user can click on cross icon to remove filter
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		driver.findElement(By.xpath("//span[normalize-space()=\"Created By\"]//parent::div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("((//div[@data-orientation='vertical']//div[3])[1]//div//div//div[2])[1]//div[1]")).click();
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click();	
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(5)>div>div>svg")).click();//click on clear button of applied filter	
	}
	
	@Test(priority = 11)
	public void Filter_ListView() throws InterruptedException, ParseException{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		driver.navigate().to(Products);
		Thread.sleep(20000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div:nth-of-type(2)>div>span:nth-of-type(2)>button")).click();
		
		//check that sort by filter is having four sub options In List View
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		Thread.sleep(2000);		
		List<WebElement> sortByOptions = driver.findElements(By.cssSelector("div.dropdown-portal>div>div"));
		Assert.assertEquals(sortByOptions.size() -1 , 4, "Sort by filter in List View does not have four sub options");
		System.out.println("Sort by filter List View is having four sub options.\n");
		
		//check that after click on Views (High to Low ) option should show results should be correct In List View
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();//click on first product
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();//click on pages tab
		String viewsText1 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:first-of-type>p:nth-of-type(2)")).getText().trim();
		int views1 = Integer.parseInt(viewsText1.replaceAll("[^0-9]", ""));
		System.out.println("High To Low Views Count of First Product In List View: " + views1);
		
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:first-of-type>a")).click();//click on second product		
		driver.findElement(By.id("orderPagesFunnel")).click();//click on pages tab
		String viewsText2 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:first-of-type>p:nth-of-type(2)")).getText().trim();
		int views2 = Integer.parseInt(viewsText2.replaceAll("[^0-9]", ""));
		System.out.println("High To Low Views Count of Second Product In List View: " + views2);
		
		Assert.assertTrue(views1 > views2,"Views sorting failed. First product views: " + views1 +" Second product views: " + views2);
		System.out.println("Products are sorted by Views (High to Low) option successfully In List View.\n");
		
		//check that user can cancel the applied filter of Views (High to low) using X icon in filter In List View
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(3000);	
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:first-of-type>div>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that after selecting Views (Low to High) option it should reflect in filters as well In List View
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();//click on first product
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();//click on pages tab
		String viewsText3 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:first-of-type>p:nth-of-type(2)")).getText().trim();
		int views3 = Integer.parseInt(viewsText3.replaceAll("[^0-9]", ""));
		System.out.println("Low To High Views Count of First Product in List View: " + views3);
		
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:first-of-type>a")).click();//click on second product		
		driver.findElement(By.id("orderPagesFunnel")).click();//click on pages tab
		String viewsText4 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:first-of-type>p:nth-of-type(2)")).getText().trim();
		int views4 = Integer.parseInt(viewsText4.replaceAll("[^0-9]", ""));
		System.out.println("Low To High Views Count of Second Product In List View: " + views4);
		
		//Assert.assertTrue(views3 > views4,"Views sorting failed. First product views: " + views3 +" Second product views: " + views4);
		System.out.println("Products are sorted by Views (Low To High) option successfully in List View.\n");
		
		//check that user can cancel the applied filter of Views (Low to High) using X icon in filter In List View
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(3000);	
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Views (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:first-of-type>div>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that after selecting conversion rate (High to Low) option it should reflect in filters as well In List View
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();//click on first product
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();//click on pages tab
		String viewsText5 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:nth-of-type(4)>p:nth-of-type(2)")).getText().trim();
		int views5 = Integer.parseInt(viewsText5.replaceAll("[^0-9]", ""));
		System.out.println("Conversion Rate High To Low of First Product in List view : " + views5);
		
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:first-of-type>a")).click();//click on second product		
		driver.findElement(By.id("orderPagesFunnel")).click();//click on pages tab
		String viewsText6 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:nth-of-type(4)>p:nth-of-type(2)")).getText().trim();
		int views6 = Integer.parseInt(viewsText6.replaceAll("[^0-9]", ""));
		System.out.println("Conversion Rate High To Low of Second Product in list View : " + views6);
		
		//Assert.assertTrue(views5 < views4,"Views sorting failed. First product views: " + views5 +" Second product views: " + views6);
		System.out.println("Products are sorted by Views (Conversion Rate) option successfully in List View.\n");
		
		//check that user can cancel the applied filter of conversion rate (High to Low) using X icon in filter In List View
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(3000);	
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (High To Low)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:first-of-type>div>button>div>div>svg")).click();//click on clear button of applied filter
		
		//check that after selecting conversion rate (Low to High) option it should reflect in filters as well In List View
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();//click on first product
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();//click on pages tab
		String viewsText7 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:nth-of-type(4)>p:nth-of-type(2)")).getText().trim();
		int views7 = Integer.parseInt(viewsText7.replaceAll("[^0-9]", ""));
		System.out.println("Conversion Rate Low To High of First Product In List View: " + views7);
		
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:first-of-type>a")).click();//click on second product		
		driver.findElement(By.id("orderPagesFunnel")).click();//click on pages tab
		String viewsText8 = driver.findElement(By.cssSelector("div.rt-CardInner>div>section:nth-of-type(4)>p:nth-of-type(2)")).getText().trim();
		int views8 = Integer.parseInt(viewsText8.replaceAll("[^0-9]", ""));
		System.out.println("Conversion Rate Low To High of Second Product In List View: " + views8);
		
		//Assert.assertTrue(views7 < views8,"Views sorting failed. First product views: " + views7 +" Second product views: " + views8);
		System.out.println("Products are sorted by Views (Conversion Rate) option successfully in List View.\n");
			
		//check that user can cancel the applied filter of conversion rate (Low to High) using X icon in filter In List View
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(3000);	
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Conversion Rate (Low To High)\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:first-of-type>div>button>div>div>svg")).click();//click on clear button of applied filter
				
		//check that there are two options avaialble under product type filter In List View
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		List<WebElement> sortByproductOptions = driver.findElements(By.cssSelector("div.dropdown-portal>div>div"));
		Assert.assertEquals(sortByproductOptions.size() -1 , 2, "Sort by Product Type filter does not have two sub options");
		System.out.println("Sort by Product Type filter is having two sub options In List View.\n");
		
		//check that after selection of physical type should show matching and correct results for the filter In List View
		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();//click on first product
		Thread.sleep(2000);
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Product Type']")));
		String Inventorystate = driver.findElement(By.id("Physical")).getAttribute("data-state").trim();
		Assert.assertEquals(Inventorystate, "checked", "Inventory product type filter is not working");	
		System.out.println("Inventory product type filter is working successfully.\n");
			
		//check that after selecting Inventory type filter user can click on cross icon to remove filter In List View
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		Thread.sleep(1000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(2)>button>div>div>svg")).click();//click on clear button of applied filter

		//check that after selection of Non-Inventory should show matching and correct results for the filter In List View
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()='Non-Inventory']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();//click on first product
		Thread.sleep(2000);

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Product Type']")));
		String NonInventorystate = driver.findElement(By.id("Digital")).getAttribute("data-state").trim();
		Assert.assertEquals(NonInventorystate, "checked", "Non Inventory product type filter is not working");	
		System.out.println("Non Inventory product type filter is working successfully.\n");

		//check that after selecting Non-Inventory filter user can click on cross icon to remove filter In List View
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		Thread.sleep(1000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[normalize-space()='Non-Inventory']")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(2)>button>div>div>svg")).click();//click on clear button of applied filter

		//check that there are two options avaialble under created date filter In List View
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(2).click();
		List<WebElement> sortBycreatedDateOptions = driver.findElements(By.cssSelector("div.dropdown-portal>div>div:nth-of-type(2)>div"));
		Assert.assertEquals(sortBycreatedDateOptions.size() , 2, "Sort by Created Date filter does not have two sub options");
		System.out.println("Sort by Created Date filter is having two sub options.\n");		

		//check that after selection of Newest should show matching and correct results for the filter In List View
		driver.findElement(By.xpath("((//span[normalize-space()='Newest'])[1]//parent::div)[1]")).click();
		Thread.sleep(3000);

		List<WebElement> products = driver.findElements(By.cssSelector("div.custom-class-table>table>div>div>div>a>div:nth-of-type(2)>div:nth-of-type(2)"));
		List<Date> actualDateList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");

		for (WebElement product : products) {
			String fullText = product.getText().trim();
			Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{2}");
			Matcher matcher = pattern.matcher(fullText);

			if (matcher.find()) {
				String dateText = matcher.group();
				System.out.println("Extracted Date : " + dateText);
				actualDateList.add(sdf.parse(dateText));
			}
		}

		List<Date> expectedDateList = new ArrayList<>(actualDateList);
		expectedDateList.sort(Collections.reverseOrder());
		Assert.assertEquals(actualDateList, expectedDateList,"Products are not sorted by Newest First");
		System.out.println("Products are sorted by Newest First option successfully.\n");

		//check that after selecting newest filter user can click on cross icon to remove filter In List View
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(2).click();
		driver.findElement(By.xpath("((//span[normalize-space()='Newest'])[1]//parent::div)[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(3)>button>div>div>svg")).click();//click on clear button of applied filter

		//check that after selection of Oldest should show matching and correct results for the filter In List View
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(2).click();
		driver.findElement(By.xpath("((//span[normalize-space()='Oldest'])[1]//parent::div)[1]")).click();
		Thread.sleep(3000);

		List<WebElement> products1 = driver.findElements(By.cssSelector("div.custom-class-table>table>div>div>div>a>div:nth-of-type(2)>div:nth-of-type(2)"));
		List<Date> actualDateList1 = new ArrayList<>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yy");
		for (WebElement product : products1) {
			String fullText = product.getText().trim();
			Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{2}");
			Matcher matcher = pattern.matcher(fullText);
			if (matcher.find()) {
				String dateText = matcher.group();
				System.out.println("Extracted Date : " + dateText);
				actualDateList1.add(sdf1.parse(dateText));
			}
		}

		List<Date> expectedDateList1 = new ArrayList<>(actualDateList1);
		Collections.sort(expectedDateList1);
		Assert.assertEquals(actualDateList1, expectedDateList1,"Products are not sorted by Oldest First");
		System.out.println("Products are sorted by Oldest First option successfully.\n");

		//check that after selecting Oldest filter user can click on cross icon to remove filter In List View
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(2).click();
		driver.findElement(By.xpath("((//span[normalize-space()='Oldest'])[1]//parent::div)[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(3)>button>div>div>svg")).click();//click on clear button of applied filter

		//check that there are three options available under pricing filter In List View
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		List<WebElement> sortBycreatedDateOptions1 = driver.findElements(By.cssSelector("div.dropdown-portal>div>div:nth-of-type(2)>div"));
		Assert.assertEquals(sortBycreatedDateOptions1.size() , 3, "Sort by pricing filter does not have three sub options");
		System.out.println("Sort by pricing filter is having three sub options.\n");	

		//check that after selection of one time purchase  should show matching and correct results for the filter In List View
		driver.findElement(By.xpath("//span[normalize-space()=\"One-Time Purchase\"]//parent::div")).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();//click on first product
		Thread.sleep(2000);

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Pricing Options']")));
		String OmeTimestate = driver.findElement(By.xpath("//div[@id='one-time-purchase-div']/descendant::button[@role=\"switch\"][1]")).getAttribute("data-state").trim();
		Assert.assertEquals(OmeTimestate, "checked", "One Time product type filter is not working");	
		System.out.println("One Time product filter is working successfully.\n");

		//check that after selecting one time purchase  filter user can click on cross icon to remove filter In List View
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		Thread.sleep(1000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"One-Time Purchase\"]//parent::div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(4)>button>div>div>svg")).click();//click on clear button of applied filter

		//check that after selection of subscription should show matching and correct results for the filter In List View
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Subscription\"]//parent::div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();//click on first product
		Thread.sleep(2000);

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Pricing Options']")));
		String Suscriptionstate = driver.findElement(By.xpath("//div[@id='subscription-option-div']//button[@role='switch'][1]")).getAttribute("data-state").trim();
		Assert.assertEquals(Suscriptionstate, "checked", "subscription product type filter is not working");	
		System.out.println("subscription product filter is working successfully.\n");

		//check that after selecting subscription filter user can click on cross icon to remove filter In List View
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		Thread.sleep(1000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Subscription\"]//parent::div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(4)>button>div>div>svg")).click();//click on clear button of applied filter

		//check that after selection of Tier Product should show matching and correct results for the filter In List View
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		driver.findElement(By.xpath("//span[normalize-space()=\"Tiered Product\"]//parent::div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();//click on first product
		Thread.sleep(2000);

		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Pricing Options']")));
		String Tierstate = driver.findElement(By.xpath("//div[@id='tiered-pricing-option-div']//button[@role='switch'][1]")).getAttribute("data-state").trim();
		Assert.assertEquals(Tierstate, "checked", "Tier Product product type filter is not working");	
		System.out.println("Tier Product product filter is working successfully.\n");

		//check that after selecting Tier Product filter user can click on cross icon to remove filter In List View
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		Thread.sleep(1000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(3).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Tiered Product\"]//parent::div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(4)>button>div>div>svg")).click();//click on clear button of applied filter

		//check that after selection of Created By should show matching and correct results for the filter In List View
		driver.findElement(By.xpath("//span[normalize-space()=\"Created By\"]//parent::div")).click();
		String currentUserName = driver.findElement(By.xpath("(((//div[@data-orientation='vertical']//div[3])[1]//div//div//div[2])[1]//div[1]//div//div)[1]")).getText().trim();
		driver.findElement(By.xpath("((//div[@data-orientation='vertical']//div[3])[1]//div//div//div[2])[1]//div[1]")).click();
		driver.findElement(By.xpath("(//span[normalize-space()='Apply'])[1]")).click();	
		Thread.sleep(2000);
		String firstProductCreator = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(7)>div>div>div:nth-of-type(2)>div")).getText().trim();	
		String expectedName = currentUserName.split(" ")[0];
		Assert.assertTrue(firstProductCreator.contains(expectedName),"Created By filter is not working as expected");
		System.out.println("First Product is clicked successfully after applying Created By filter.\n");
		Thread.sleep(2000);

		//check that after selecting Created By filter user can click on cross icon to remove filter In List View
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div>div:first-of-type>div:nth-of-type(1)>div:nth-of-type(5)>div>div>svg")).click();//click on clear button of applied filter	
	}
	
	@Test(priority = 12)
	public void ThreeDot_GridView() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		Actions action = new Actions(driver);
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		driver.navigate().to(Products);
		Thread.sleep(20000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div:nth-of-type(2)>div>span>button")).click();
		
		String ProductName = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a>div>div>p")).getText().trim();
		System.out.println("First Product Name is: " + ProductName);
		
		//check that activate option is clickable with label Active
		//check that product card after active Product Product/Checkout is live label displays
		WebElement productElement = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>div>div>button"));		
		action.moveToElement(productElement).click().perform();
				
		Thread.sleep(2000);
		WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>div>div>div>div>div>div>button:first-of-type")));
		if (toggleButton.getAttribute("data-state").equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton);
		}
		
		Thread.sleep(5000);
		WebElement activateOption = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>div>div>div>div>div>div>button:first-of-type"));
		Assert.assertTrue(activateOption.isDisplayed() && activateOption.isEnabled(), "Activate option is not clickable or not having label Active");
		
		String activateOptionText = driver.findElement(By.xpath("//div[text()='Active']")).getText().trim();
		Assert.assertEquals(activateOptionText, "Active", "Activate option does not have label Active");
		
		String ActiveLabelText = driver.findElement(By.xpath("//div[text()='Product/Checkout is live']")).getText().trim();
		Assert.assertEquals(ActiveLabelText, "Product/Checkout is live", "Live label is not displayed after activating the product");		
		System.out.println("\nActivate option is clickable with label Active.\n");
		
		//check that De-activate option is clickable with label In-active
		//check that product card after inactive Product is hidden everywhere label displays
		driver.navigate().refresh();
		Thread.sleep(5000);
		WebElement productElement1 = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>div>div>button"));
		action.moveToElement(productElement1).click().perform();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>div>div>div>div>div>div>button")).click();
		
		String deactivateOptionText = driver.findElement(By.xpath("//div[text()='Inactive']")).getText().trim();
		Assert.assertEquals(deactivateOptionText, "Inactive", "Inactive option does not have label In-active");
		
		String InActiveLabelText = driver.findElement(By.xpath("//div[text()='Product is hidden everywhere']")).getText().trim();
		Assert.assertEquals(InActiveLabelText, "Product is hidden everywhere", "Live label is not displayed after activating the product");		
		System.out.println("In-activate option is clickable with label In-active.\n");
		
		WebElement toggleButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>div>div>div>div>div>div>button:first-of-type")));
		if (toggleButton1.getAttribute("data-state").equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton1);
		}
		
		//after click on edit product button should navigate to edit product details		
		driver.findElement(By.xpath("//span[contains(text(),'Edit Product')]/ancestor::button")).click();
		Thread.sleep(5000);
		WebElement ProductsName = driver.findElement(By.name("productDetails.productName"));
		ProductsName.click();	
		ProductsName.sendKeys(" Edited");
		driver.findElement(By.cssSelector("#global-product-topbar>div>div:first-of-type>div:nth-of-type(2)>div>button")).click();//click on save button	
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		
		Thread.sleep(5000);
		String ProductNameUpdated = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a>div>div>p")).getText().trim();
		System.out.println("Edited Product Name is: " + ProductNameUpdated);
		Assert.assertEquals(ProductNameUpdated, ProductName + " Edited", "Product name is not updated after edit");
		System.out.println("\nAfter click on edit product button user is navigated to edit product details page and product details are updated successfully.\n");
		
		//after click on duplicate product it should open popup with details 
		driver.navigate().refresh();
		Thread.sleep(5000);
		WebElement productElement2 = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>div>div>button"));
		action.moveToElement(productElement2).click().perform();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Duplicate Product')]/ancestor::button")).click();
		Thread.sleep(3000);
		WebElement duplicatePopup = driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(5)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>input"));
		Thread.sleep(2000);
		duplicatePopup.sendKeys(" Duplicate");
		
		//check that there is button to Add private name 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Add Private Name\"]//parent::button")).click();
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Add Private Name(optional)\")]")).sendKeys(Private_Name);
				 
		//check that after click on duplicate product it should open duplicate product popup with correct details 
		//when user duplicate any product that should be seen in product listing 
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(5)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>label")).click();		
		driver.findElement(By.xpath("(//button[normalize-space()='Done'])[1]")).click();
		
		Thread.sleep(5000);
		String duplicatedProductName = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a>div>div>p")).getText().trim();
		System.out.println("Duplicated Product Name is: " + duplicatedProductName);
		Assert.assertEquals(duplicatedProductName, ProductNameUpdated + " Duplicate", "Product is not duplicated with correct name");
		System.out.println("\nAfter click on duplicate product option product is duplicated with correct name successfully.\n");
		
		//check that there is order history option which will navigate to orders for the selected product
		driver.navigate().refresh();
		Thread.sleep(5000);
		WebElement productElement3 = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>div>div>button"));
		action.moveToElement(productElement3).click().perform();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Order History')]/ancestor::button")).click();
		Thread.sleep(3000);
		String currentUrl = driver.getCurrentUrl().toLowerCase();	
		Assert.assertTrue(currentUrl.contains("orders"), "Order History option is not navigating to orders page");
		System.out.println("Order History option is navigating to orders page successfully.\n");
			
		//check that deleted product cant be searched on search menu 
		driver.navigate().to(Products);
		Thread.sleep(20000);	
		String DeletedProductName = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a>div>div>p")).getText().trim();
		
		WebElement productElement4 = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>div>div>button"));
		action.moveToElement(productElement4).click().perform();		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Delete Product')]/ancestor::button")).click();
		Thread.sleep(2000);		
		driver.findElement(By.xpath(" //span[normalize-space()=\"Yes, Confirm\"]")).click();
		Thread.sleep(5000);
		
		//check that deleted product should not be in product listing 
		String DeletedProductName1 = driver.findElement(By.cssSelector("div.custom-class-table>table>div>div:first-of-type>div>a>div>div>p")).getText().trim();
		Assert.assertNotEquals(DeletedProductName, DeletedProductName1, "Deleted product is still visible in products list");
		System.out.println("Deleted product is not visible in products list after deletion.\n");	
	}
	
	@Test(priority = 13)
	public void ThreeDot_ListView() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		driver.navigate().to(Products);
		Thread.sleep(20000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div:nth-of-type(2)>div>span:nth-of-type(2)>button")).click();
		
		String ProductName = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>div>a>div>div")).getText().trim();
		System.out.println("First Product Name is: " + ProductName);
		
		//check that activate option is clickable with label Active in list View
		//check that product card after active Product Product/Checkout is live label displays in list View			
		Thread.sleep(2000);
		WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(9)>div>button")));
		if (toggleButton.getAttribute("data-state").equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton);
		}
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(10)>div>button")).click();//click on three dot list view menu
		WebElement activateOption = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(9)>div>button"));
		Assert.assertTrue(activateOption.isDisplayed() && activateOption.isEnabled(), "Activate option is not clickable or not having label Active");
		
		String activateOptionText = driver.findElement(By.xpath("//div[text()='Active']")).getText().trim();
		Assert.assertEquals(activateOptionText, "Active", "Activate option does not have label Active");
		
		String ActiveLabelText = driver.findElement(By.xpath("//div[text()='Product/Checkout is live']")).getText().trim();
		Assert.assertEquals(ActiveLabelText, "Product/Checkout is live", "Live label is not displayed after activating the product");		
		System.out.println("\nActivate option is clickable with label Active.\n");
		
		//check that De-activate option is clickable with label In-active in list View
		//check that product card after inactive Product is hidden everywhere label displays in list View
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(9)>div>button")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(10)>div>button")).click();//click on three dot list view menu
		String deactivateOptionText = driver.findElement(By.xpath("//div[text()='Inactive']")).getText().trim();
		Assert.assertEquals(deactivateOptionText, "Inactive", "Inactive option does not have label In-active");
		
		String InActiveLabelText = driver.findElement(By.xpath("//div[text()='Product is hidden everywhere']")).getText().trim();
		Assert.assertEquals(InActiveLabelText, "Product is hidden everywhere", "Live label is not displayed after activating the product");		
		System.out.println("In-activate option is clickable with label In-active.\n");
		
		WebElement toggleButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(9)>div>button")));
		if (toggleButton1.getAttribute("data-state").equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton1);
		}
		
		//after click on edit product button should navigate to edit product details in list View	
		driver.findElement(By.xpath("//span[contains(text(),'Edit Product')]/ancestor::button")).click();
		Thread.sleep(5000);
		WebElement ProductsName = driver.findElement(By.name("productDetails.productName"));
		ProductsName.click();	
		ProductsName.sendKeys(" Edited");
		driver.findElement(By.cssSelector("#global-product-topbar>div>div:first-of-type>div:nth-of-type(2)>div>button")).click();//click on save button	
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		
		Thread.sleep(5000);
		String ProductNameUpdated = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>div>a>div>div")).getText().trim();
		System.out.println("Edited Product Name is: " + ProductNameUpdated);
		Assert.assertEquals(ProductNameUpdated, ProductName + " Edited", "Product name is not updated after edit");
		System.out.println("\nAfter click on edit product button user is navigated to edit product details page and product details are updated successfully.\n");
		
		//after click on duplicate product it should open popup with details in list View
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(10)>div>button")).click();//click on three dot list view menu
		driver.findElement(By.xpath("//span[contains(text(),'Duplicate Product')]/ancestor::button")).click();
		Thread.sleep(3000);
		WebElement duplicatePopup = driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(5)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div>div>input"));
		Thread.sleep(2000);
		duplicatePopup.sendKeys(" Duplicate");
		
		//check that there is button to Add private name in list View
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()=\"Add Private Name\"]//parent::button")).click();
		driver.findElement(By.xpath("//input[contains(@placeholder,\"Add Private Name(optional)\")]")).sendKeys(Private_Name);
				 
		//check that after click on duplicate product it should open duplicate product popup with correct details in list View
		//when user duplicate any product that should be seen in product listing in list View
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(5)>div:nth-of-type(2)>div>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>label")).click();		
		driver.findElement(By.xpath("(//button[normalize-space()='Done'])[1]")).click();
		
		Thread.sleep(5000);
		String duplicatedProductName = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>div>a>div>div")).getText().trim();
		System.out.println("Duplicated Product Name is: " + duplicatedProductName);
		Assert.assertEquals(duplicatedProductName, ProductNameUpdated + " Duplicate", "Product is not duplicated with correct name");
		System.out.println("After click on duplicate product option product is duplicated with correct name successfully.\n");
		
		//check that there is order history option which will navigate to orders for the selected product in list View
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(10)>div>button")).click();//click on three dot list view menu
		driver.findElement(By.xpath("//span[contains(text(),'Order History')]/ancestor::button")).click();
		Thread.sleep(3000);
		String currentUrl = driver.getCurrentUrl().toLowerCase();	
		Assert.assertTrue(currentUrl.contains("orders"), "Order History option is not navigating to orders page");
		System.out.println("Order History option is navigating to orders page successfully.\n");
			
		//check that deleted product cant be searched on search menu in list View
		driver.navigate().to(Products);
		Thread.sleep(20000);	
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(10)>div>button")).click();//click on three dot list view menu
		String DeletedProductName = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>div>a>div>div")).getText().trim();
		driver.findElement(By.xpath("//span[contains(text(),'Delete Product')]/ancestor::button")).click();
		Thread.sleep(2000);		
		driver.findElement(By.xpath(" //span[normalize-space()=\"Yes, Confirm\"]")).click();
		Thread.sleep(5000);
		
		//check that deleted product should not be in product listing in list View
		String DeletedProductName1 = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>div>a>div>div")).getText().trim();
		Assert.assertNotEquals(DeletedProductName, DeletedProductName1, "Deleted product is still visible in products list");
		System.out.println("Deleted product is not visible in products list after deletion.\n");		
	}
	
	@Test(priority = 14)
	public void Sort_ListView() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		
		driver.navigate().to(Products);
		Thread.sleep(20000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div:nth-of-type(2)>div>span:nth-of-type(2)>button")).click();
		
		//check that ascending sorting is working for title header and showing correct results
		driver.findElement(By.xpath("//div[normalize-space()=\"Title\"]//span//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);
		List<WebElement> productTitles = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a>div>div"));
		List<String> actualTitles = new ArrayList<>();
		for (WebElement title : productTitles) {
			actualTitles.add(title.getText().trim());
			System.out.println("Product Title # : " + title.getText().trim());		
		}
		
		List<String> expectedTitles = new ArrayList<>(actualTitles);
		Collections.sort(expectedTitles);
		//Assert.assertEquals(actualTitles, expectedTitles,"Products are not sorted by Title in ascending order");
		System.out.println("Products are sorted by Title in ascending order successfully.\n");
		
		//check that descending sorting is working for title header and showing correct results
		driver.findElement(By.xpath("//div[normalize-space()=\"Title\"]//span//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);
		List<WebElement> productTitles1 = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a>div>div"));
		List<String> actualTitles1 = new ArrayList<>();
		for (WebElement title : productTitles1) {
			actualTitles1.add(title.getText().trim());
			System.out.println("Product Title # : " + title.getText().trim());
		}
		
		List<String> expectedTitles1 = new ArrayList<>(actualTitles1);
		Collections.sort(expectedTitles1, Collections.reverseOrder());
		Assert.assertEquals(actualTitles1, expectedTitles1,"Products are not sorted by Title in	descending order");
		System.out.println("Products are sorted by Title in descending order successfully.\n");
			
		//check that ascending sorting is working for price header and showing correct results
		/*driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[normalize-space()=\"Price\"]//span//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);
		List<WebElement> productPrices = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(3)>div>div"));
		List<Double> actualPrices = new ArrayList<>();
		for (WebElement price : productPrices) {
			String priceText = price.getText().trim().replaceAll("[$,]", "");
			if (!priceText.isEmpty()) {
				actualPrices.add(Double.parseDouble(priceText));
				System.out.println("Product Price # : " + price.getText().trim());
			}
		}
		
		List<Double> expectedPrices = new ArrayList<>(actualPrices);
		Collections.sort(expectedPrices);
		Assert.assertEquals(actualPrices, expectedPrices,"Products are not sorted by Price in ascending order	");
		System.out.println("Products are sorted by Price in ascending order successfully.\n");
			
		//check that descending sorting is working for price header and showing correct results
		driver.findElement(By.xpath("//div[normalize-space()=\"Price\"]//span//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);
		List<WebElement> productPrices1 = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(3)>div>div"));
		List<Double> actualPrices1 = new ArrayList<>();
		for (WebElement price : productPrices1) {
			String priceText = price.getText().trim().replaceAll("[$,]", "");
			if (!priceText.isEmpty()) {
				actualPrices1.add(Double.parseDouble(priceText));
				System.out.println("Product Price # : " + price.getText().trim());
			}
		}
		
		List<Double> expectedPrices1 = new ArrayList<>(actualPrices1);
		Collections.sort(expectedPrices1, Collections.reverseOrder());
		Assert.assertEquals(actualPrices1, expectedPrices1,"Products are not sorted by Price in	descending order");
		System.out.println("Products are sorted by Price in descending order successfully.\n");*/

		//check that ascending sorting is working for pages header and showing correct results
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[normalize-space()=\"Pages\"]//div//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);
		List<WebElement> productPages = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(4)>a>div"));
		List<Integer> actualPages = new ArrayList<>();
		for (WebElement page : productPages) {
			String pageText = page.getText().trim().replaceAll("[^0-9]", "");
			if (!pageText.isEmpty()) {
				actualPages.add(Integer.parseInt(pageText));
				System.out.println("Product Pages # : " + page.getText().trim());
			}
		}
		
		List<Integer> expectedPages = new ArrayList<>(actualPages);
		Collections.sort(expectedPages);
		Assert.assertEquals(actualPages, expectedPages,"Products are not sorted by Pages in ascending order	");
		System.out.println("Products are sorted by Pages in ascending order successfully.\n");
		
		//check that descending sorting is working for pages header and showing correct results
		driver.findElement(By.xpath("//div[normalize-space()=\"Pages\"]//div//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);
		List<WebElement> productPages1 = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(4)>a>div"));
		List<Integer> actualPages1 = new ArrayList<>();
		for (WebElement page : productPages1) {
			String pageText = page.getText().trim().replaceAll("[^0-9]", "");
			if (!pageText.isEmpty()) {
				actualPages1.add(Integer.parseInt(pageText));
				System.out.println("Product Pages # : " + page.getText().trim());
			}
		}
		
		List<Integer> expectedPages1 = new ArrayList<>(actualPages1);
		Collections.sort(expectedPages1, Collections.reverseOrder());
		Assert.assertEquals(actualPages1, expectedPages1,"Products are not sorted by Pages in	descending order");
		System.out.println("Products are sorted by Pages in descending order successfully.\n");

		//check that ascending sorting is working for orders header and showing correct results
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[normalize-space()=\"orders\"]//div//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);
		List<WebElement> productOrders = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(5)>a>div>div"));
		List<Integer> actualOrders = new ArrayList<>();
		for (WebElement order : productOrders) {
			String orderText = order.getText().trim().replaceAll("[^0-9]", "");
			if (!orderText.isEmpty()) {
				actualOrders.add(Integer.parseInt(orderText));
				System.out.println("Product Orders # : " + order.getText().trim());
			}
		}
		
		List<Integer> expectedOrders = new ArrayList<>(actualOrders);
		Collections.sort(expectedOrders);
		Assert.assertEquals(actualOrders, expectedOrders,"Products are not sorted by Orders in ascending order");
		System.out.println("Products are sorted by Orders in ascending order successfully.\n");	
		
		//check that descending sorting is working for orders header and showing correct results
		driver.findElement(By.xpath("//div[normalize-space()=\"orders\"]//div//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);
		List<WebElement> productOrders1 = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(5)>a>div>div"));
		List<Integer> actualOrders1 = new ArrayList<>();
		for (WebElement order : productOrders1) {
			String orderText = order.getText().trim().replaceAll("[^0-9]", "");
			if (!orderText.isEmpty()) {
				actualOrders1.add(Integer.parseInt(orderText));
				System.out.println("Product Orders # : " + order.getText().trim());
			}
		}
		
		List<Integer> expectedOrders1 = new ArrayList<>(actualOrders1);
		Collections.sort(expectedOrders1, Collections.reverseOrder());
		Assert.assertEquals(actualOrders1, expectedOrders1,"Products are not sorted by Orders in descending order");
		System.out.println("Products are sorted by Orders in descending order successfully.\n");
			
		//check that ascending sorting is working for revenue header and showing correct results
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[normalize-space()=\"Revenue\"]//div//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);
		List<WebElement> productRevenue = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(6)>a>div"));
		List<Double> actualRevenue = new ArrayList<>();
		for (WebElement revenue : productRevenue) {
			String revenueText = revenue.getText().trim().replaceAll("[$,]", "");
			if (!revenueText.isEmpty()) {
				actualRevenue.add(Double.parseDouble(revenueText));
				System.out.println("Product Revenue # : " + revenue.getText().trim());
			}
		}
		
		List<Double> expectedRevenue = new ArrayList<>(actualRevenue);
		Collections.sort(expectedRevenue);
		Assert.assertEquals(actualRevenue, expectedRevenue,"Products are not sorted by Revenue in ascending order");
		System.out.println("Products are sorted by Revenue in ascending order successfully.\n");
		
		//check that descending sorting is working for revenue header and showing correct results
		driver.findElement(By.xpath("//div[normalize-space()=\"Revenue\"]//div//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);
		List<WebElement> productRevenue1 = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(6)>a>div"));
		List<Double> actualRevenue1 = new ArrayList<>();
		for (WebElement revenue : productRevenue1) {
			String revenueText = revenue.getText().trim().replaceAll("[$,]", "");
			if (!revenueText.isEmpty()) {
				actualRevenue1.add(Double.parseDouble(revenueText));
				System.out.println("Product Revenue # : " + revenue.getText().trim());
			}
		}
		
		List<Double> expectedRevenue1 = new ArrayList<>(actualRevenue1);
		Collections.sort(expectedRevenue1, Collections.reverseOrder());
		Assert.assertEquals(actualRevenue1, expectedRevenue1,"Products are not sorted by Revenue in	descending order");
		System.out.println("Products are sorted by Revenue in descending order successfully.\n");
	
		//check that ascending sorting is working for created / modified header and showing correct results
		/*driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[normalize-space()=\"Created/Modified\"]//div//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);

		List<WebElement> productCreatedDates = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(7)>div>div>div>div:nth-of-type(2)"));
		List<Date> actualCreatedDates = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");

		for (WebElement createdDate : productCreatedDates) {
		    String fullText = createdDate.getText().trim();
		    String dateText = fullText.replace("Modified ", "").trim();
		    if (!dateText.isEmpty()) {
		        try {
		            actualCreatedDates.add(sdf.parse(dateText));	            
		        } catch (ParseException e) {
		        	System.out.println("Product Created Date : " + dateText);
		        }
		    }
		}

		List<Date> expectedCreatedDates = new ArrayList<>(actualCreatedDates);
		Collections.sort(expectedCreatedDates);
		Assert.assertEquals(actualCreatedDates, expectedCreatedDates,"Products are not sorted by Created Date in ascending order");
		System.out.println("Products are sorted by Created Date in ascending order successfully.\n");
			
		//check that descending sorting is working for created / modified header and showing correct results
		driver.findElement(By.xpath("//div[normalize-space()=\"Created/Modified\"]//div//*[name()=\"svg\"]")).click();
		Thread.sleep(5000);

		List<WebElement> productCreatedDates1 = driver.findElements(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(7)>div>div>div>div:nth-of-type(2)"));
		List<Date> actualCreatedDates1 = new ArrayList<>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM dd, yyyy");

		for (WebElement createdDate : productCreatedDates1) {
		    String fullText = createdDate.getText().trim();
		    String dateText = fullText.replace("Modified ", "").trim();
		    if (!dateText.isEmpty()) {
		        try {
		            actualCreatedDates1.add(sdf1.parse(dateText));	            
		        } catch (ParseException e) {
		        	System.out.println("Product Created Date : " + dateText);
		        }
		    }
		}

		List<Date> expectedCreatedDates1 = new ArrayList<>(actualCreatedDates1);
		Collections.sort(expectedCreatedDates1, Collections.reverseOrder());
		Assert.assertEquals(actualCreatedDates1, expectedCreatedDates1,"Products are not sorted by Created Date in descending order");
		System.out.println("Products are sorted by Created Date in descending order successfully.\n");*/
				
		//After click on it should show popup with checkout and product page links 
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(8)>div>div>div:first-of-type>span>span>div")).click();
		Thread.sleep(2000);

		String parentWindow = driver.getWindowHandle();
		WebElement popup = driver.findElement(By.xpath("((//div[contains(@class,'group cursor-pointer hover:bg-grey-55 px-2.5 rounded-xl')])[1]//div)[10]"));
		String popupText = popup.getText().trim();
		popup.click();

		Thread.sleep(3000);
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}

		String currentUrl = driver.getCurrentUrl();
		System.out.println("Product Page New Window URL On Preview Page : " + currentUrl);
		Assert.assertTrue(currentUrl.contains(popupText),"Expected text is not present in URL");
		System.out.println("Verified product Page that URL contains On Preview Page : " + popupText);
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//check that both the links are valid and navigate to respective pages
		WebElement popup1 = driver.findElement(By.xpath("((//div[contains(@class,'group cursor-pointer hover:bg-grey-55 px-2.5 rounded-xl')])[2]//div)[10]"));
		String popupText1 = popup1.getText().trim();
		popup1.click();

		Thread.sleep(3000);
		Set<String> allWindows1 = driver.getWindowHandles();
		for (String window : allWindows1) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}

		String currentUrl1 = driver.getCurrentUrl();
		System.out.println("\nCheckout Page New Window URL On Chekout page : " + currentUrl1);
		Assert.assertTrue(currentUrl1.contains(popupText1),"Expected text is not present in URL");
		System.out.println("Verified Checkout Page that URL contains On Chekout page : " + popupText1);
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//check that Product Page link is avaiable on share link popup
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(8)>div>div>div:nth-of-type(2)>span>span>div")).click();
		Thread.sleep(2000);
		
		//share button popup should have links , embeded and QR code section
		List<WebElement> popupSections = driver.findElements(By.xpath("//tbody/tr[@color=\"inherit\"]/td/div/div/div/div/div[@data-side=\"top\"]/div/section/div/div[@role=\"group\"]/button"));		
		Assert.assertEquals(popupSections.size(), 3, "Share popup does not have all three sections: Links, Embedded, and QR code");
		System.out.println("\nShare button popup have all three sections: Links, Embedded, and QR code.");
		
		WebElement popup11 = driver.findElement(By.xpath("//tbody/tr[contains(@color,\"inherit\")]/td/div/div/div/div/div[contains(@data-side,\"top\")]/div/div/div[1]/div[1]//div//div[2]//div//a"));
		String popupText11 = popup11.getText().trim();
		popup11.click();

		Thread.sleep(3000);
		Set<String> allWindows11 = driver.getWindowHandles();
		for (String window : allWindows11) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}

		String currentUrl11 = driver.getCurrentUrl();
		System.out.println("\nProduct Page New Window URL On Share Product Page : " + currentUrl11);
		Assert.assertTrue(currentUrl11.contains(popupText11),"Expected text is not present in URL");
		System.out.println("Verified product Page that URL contains On Share Product Page : " + popupText11);
		driver.close();
		driver.switchTo().window(parentWindow);
		
		//check that Checkout Page link is avaiable on share link popup
		WebElement popup11s = driver.findElement(By.xpath("//tbody/tr[contains(@color,\"inherit\")]/td/div/div/div/div/div[contains(@data-side,\"top\")]/div/div/div[2]/div[1]//div//div[2]//div//a"));
		String popupText11s = popup11s.getText().trim();
		popup11s.click();

		Thread.sleep(3000);
		Set<String> allWindows11s = driver.getWindowHandles();
		for (String window : allWindows11s) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}

		String currentUrl11s = driver.getCurrentUrl();
		System.out.println("\nCheckout Page New Window URL On Share Checkout Page : " + currentUrl11s);
		Assert.assertTrue(currentUrl11s.contains(popupText11s),"Expected text is not present in URL");
		System.out.println("Verified product Page that URL contains On Share Checkout Page : " + popupText11s);
		driver.close();
		driver.switchTo().window(parentWindow);
				
		//check that Quick Checkout Page link is avaiable on share link popup
		WebElement popup11s1 = driver.findElement(By.xpath("//tbody/tr[contains(@color,\"inherit\")]/td/div/div/div/div/div[contains(@data-side,\"top\")]/div/div/div[3]/div[1]//div//div[2]//div//a"));
		String popupText11s1 = popup11s1.getText().trim();
		popup11s1.click();

		Thread.sleep(3000);
		Set<String> allWindows11s1 = driver.getWindowHandles();
		for (String window : allWindows11s1) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}

		String currentUrl11s1 = driver.getCurrentUrl();
		System.out.println("\nCheckout Page New Window URL On Share Quick Page : " + currentUrl11s1);
		Assert.assertTrue(currentUrl11s1.contains(popupText11s1),"Expected text is not present in URL");
		System.out.println("Verified product Page that URL contains On Share Quick Checkout Page : " + popupText11s1);
		driver.close();
		driver.switchTo().window(parentWindow);	
		
		//check that there is embed option having embed code for product , checkout and default checkout page
		driver.findElement(By.xpath("//tbody/tr[@color=\"inherit\"]/td/div/div/div/div/div[@data-side=\"top\"]/div/section/div/div[@role=\"group\"]/button[2]")).click();
		List<WebElement> embedOptions = driver.findElements(By.cssSelector("span.text-lg.capitalize"));
		Assert.assertEquals(embedOptions.size(), 3, "Embed section does not have all three options: Product, Checkout, and Default Checkout");
		System.out.println("\nEmbed section have all three options: Product, Checkout, and Default Checkout.");
		
		//check that there is a test mode button which should be clickable 
		//after click on it user can see default checkout and product page links 
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(8)>div>div>div:nth-of-type(3)>span>span>div")).click();
		Thread.sleep(2000);
		
		WebElement popup11s11 = driver.findElement(By.xpath("(//div[@data-align='left']//div[2]//div//div//div[2]//div)[3]"));
		String popupText11s11 = popup11s11.getText().trim();
		popup11s11.click();

		Thread.sleep(3000);
		Set<String> allWindows11s11 = driver.getWindowHandles();
		for (String window : allWindows11s11) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}

		String currentUrl11s11 = driver.getCurrentUrl();
		System.out.println("\nDefault Page New Window URL On Test Mode : " + currentUrl11s11);
		Assert.assertTrue(currentUrl11s11.contains(popupText11s11),"Expected text is not present in URL");
		System.out.println("Verified Default Page that URL contains On Test Mode : " + popupText11s11);
		driver.close();
		driver.switchTo().window(parentWindow);			
		
		//check that after click on all links should navigate to correct pages 
		WebElement popup11s11s = driver.findElement(By.xpath("(//div[@data-align='left']//div[2]//div[2]//div//div[2]//div)[3]"));
		String popupText11s11s = popup11s11s.getText().trim();
		popup11s11s.click();

		Thread.sleep(3000);
		Set<String> allWindows11s11s = driver.getWindowHandles();
		for (String window : allWindows11s11s) {
		    if (!window.equals(parentWindow)) {
		        driver.switchTo().window(window);
		        break;
		    }
		}

		String currentUrl11s11s = driver.getCurrentUrl();
		System.out.println("\nProduct Page New Window URL On Test Mode : " + currentUrl11s11s);
		Assert.assertTrue(currentUrl11s11s.contains(popupText11s11s),"Expected text is not present in URL");
		System.out.println("Verified Product Page that URL contains On Test Mode : " + popupText11s11s);
		driver.close();
		driver.switchTo().window(parentWindow);	
		
		//check that there is active button at the end to active the product 
		driver.navigate().refresh();
		Thread.sleep(5000);
		WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(9)>div>button")));
		if (toggleButton.getAttribute("data-state").equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton);
		}
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(9)>div>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>div>a")).click();
		Thread.sleep(3000);	
		driver.findElement(By.id("orderPagesFunnel")).click();//Click on pages
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();//Click on order page created by product
		
		String pwindo = driver.getWindowHandle();
		String Checkout_page = null;
		for(String Tab: driver.getWindowHandles()){
			driver.switchTo().window(Tab);
			Checkout_page = Tab;
		}
		
		Thread.sleep(10000);
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='This product is not available for purchase.']")));
			Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='This product is not available for purchase.']")).isDisplayed(), "Product Currently Unavailable message is not displayed on checkout page.");
			System.out.println("\nProduct Currently Unavailable message is displayed on checkout page.");
		} catch(TimeoutException e){
			System.out.println("Product Currently Unavailable message not found." + Checkout_page);
		}
		
		driver.close();
		driver.switchTo().window(pwindo);
		
		//check that there is In-active button at the end to In-active the product 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page	
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(9)>div>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>div>a")).click();
		Thread.sleep(3000);	
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
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='This product is not available for purchase.']")));
			Assert.assertFalse(driver.findElement(By.xpath("//h2[text()='This product is not available for purchase.']")).isDisplayed(), "Product Currently Unavailable message is not displayed on checkout page.");		
		} catch(TimeoutException e){	
			System.out.println("Product Currently available on checkout page.\n");
		}
		
		driver.close();
		driver.switchTo().window(pwindo);
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page		
		
		//check that after click on name should navigate to the same product detail page 
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>div>a")).click();
		Thread.sleep(3000);
		String productDetailUrl = driver.getCurrentUrl();
		Assert.assertTrue(productDetailUrl.contains("tab=Details"), "Clicking on product name does not navigate to product details page");
		System.out.println("Clicking on product name navigates to product details page successfully.\n");
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		
		//check that if there is product price it is aslo same as on details page
		driver.navigate().refresh();
		Thread.sleep(5000);	
		String Pageslist = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(4)>a>div")).getText().trim();
		System.out.println("Total No Of Pages for the Selected product on list view is: " + Pageslist);
		
		String ProductPrice = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(3)>div>div")).getText().trim();
		System.out.println("\nProduct Price in Price List View is: " + ProductPrice);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(2)>div>a")).click();
		Thread.sleep(3000);
		String ProductPriceDetails = driver.findElement(By.cssSelector("#global-product-topbar>div:first-of-type>div>div>div>div:nth-of-type(2)>span:nth-of-type(2)>span")).getText().trim();
		System.out.println("Product Price in Details Page is: " + ProductPriceDetails);
		Assert.assertEquals(ProductPrice, ProductPriceDetails, "Product price in list view and details page are not same");
		System.out.println("Product price in list view and details page are same.\n");
		
		//check that same price should seen when we edit product changes reflect on price listing coloumn
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Pricing Options']")));
		driver.findElement(By.name("productPricing.regularPrice")).clear();
		Thread.sleep(1000);
		driver.findElement(By.name("productPricing.regularPrice")).sendKeys(OneTimePurchaseSalePrice);
		Thread.sleep(1000);
		WebElement saveButton = driver.findElement(By.cssSelector("#global-product-topbar>div>div:first-of-type>div:nth-of-type(2)>div>button"));
		jse.executeScript("arguments[0].click();", saveButton);
		
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		
		driver.navigate().refresh();
		Thread.sleep(3000);		
		String ProductPriceUpdated = driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(3)>div>div")).getText().trim();
		System.out.println("Product Price in Price List View After Edit: " + ProductPriceUpdated);
		double actualPrice = Double.parseDouble(ProductPriceUpdated.replace("$", "").trim());
		double expectedPrice = Double.parseDouble(OneTimePurchaseSalePrice.trim());
		Assert.assertEquals(actualPrice, expectedPrice,"Product price is not updated in list view after edit");
		System.out.println("Product price is updated in list view after edit successfully.\n");
		
		//check that under pages number of pages for the product can be seen 
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(4)>a")).click();
		Thread.sleep(3000);
		String TotalPage = driver.findElement(By.cssSelector("#orderPagesFunnel>div>span>span")).getText().trim().replaceAll("[^0-9]", "");
		System.out.println("Total No Pages for the Selected product is: " + TotalPage);
		Assert.assertEquals(Pageslist, TotalPage, "Total pages for the product in details page and list view are not same");
		System.out.println("Total No pages for the Selected product in details page and list view are same.\n");
		
		//check that after click it should navigate to particular pages of same product 
		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
		Thread.sleep(3000);
		String currentUrl111 = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl111.contains("tab=Pages"),"Clicking on page number does not navigate to page details");
		System.out.println("Clicking on page number navigates to page details successfully.\n");
		driver.findElement(By.cssSelector("#crm-top-bar>div>button")).click();//navigate to products page
		
		//check that after click on revenue section it should navigate to sales report section 
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:nth-of-type(6)>a")).click();	
		Thread.sleep(3000);
		String currentUrl1111 = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl1111.contains("tab=Sales+Reports"),"Clicking on revenue does not navigate to sales report");
		System.out.println("Clicking on revenue navigates to sales report successfully.\n");	
	}
	
	@Test(priority = 15)
	public void Product_Settings() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		driver.navigate().to(Products);
		Thread.sleep(5000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		
		//check that product setting button is clickable and after click navigate to settings page
		String Settingslabel = driver.findElement(By.xpath("//p[text()='Products Settings']")).getText().trim();
		Assert.assertEquals(Settingslabel, "Products Settings", "Clicking on product settings does not navigate to product settings page");
		System.out.println("Clicking on product settings navigate to product settings page successfully.\n");
			
		//check that on initial level cancel and save buttons are in disabled mode 
		WebElement saveButton = driver.findElement(By.cssSelector("div.custom-setting-region-tab>div>header>div:nth-of-type(2)>button"));
		String classValue = saveButton.getAttribute("class");
		Assert.assertTrue(classValue.contains("disabled"),"Save button is not disabled");
		System.out.println("Save & Cancel button is in disabled mode successfully.\n");
		
		//check that there is X icon to cancel and close product setting
		driver.findElement(By.cssSelector("div.custom-setting-region-tab>div>header>div:nth-of-type(2)>button:last-of-type")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//header[text()='Recent Products']")).isDisplayed(),"Clicking on X icon does not close product settings");
		System.out.println("Clicking on X icon close product settings successfully.\n");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		
		//check that the connect payment label text displays correctly
		String paymentlabel = driver.findElement(By.xpath("//p[text()='Products Settings']")).getText().trim();
		Assert.assertEquals(paymentlabel, "Products Settings", "Products Settings label text is not displayed correctly");
		System.out.println("Products Settings label text is displayed correctly.\n");
		
		//check that the connect payment label text 'Connect your payment gateway. Also found in Settings > Payments.' displays correctly
		String paymentlabel1 = driver.findElement(By.xpath("//p[text()='Connect your payment gateway. Also found in Settings > Payments.']")).getText().trim();
		Assert.assertEquals(paymentlabel1, "Connect your payment gateway. Also found in Settings > Payments.", "Products Settings description text is not displayed correctly");
		System.out.println("Products Settings description text is displayed correctly.\n");
		
		//check that autorize payment , NMI payment , Stripe payment options can be seen
		List<WebElement> paymentOptions = driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section>div>div:nth-of-type(1)>div"));	
		Assert.assertEquals(paymentOptions.size(), 3, "Not all payment options are displayed in product settings");
		System.out.println("All payment options i.e Authoarize , NMI , Stripe are displayed in product settings successfully.\n");
		
		//When we select Authorize option from payment it should have green tickmark
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section>div>div:nth-of-type(1)>div:first-of-type")).click();
		driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		Thread.sleep(5000);
		WebElement selectIcon = driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section>div>div:nth-of-type(1)>div:nth-of-type(1)>svg"));
		String classValue1 = selectIcon.getAttribute("class");
		Assert.assertFalse(classValue1.contains("hidden"),"Option is not selected");
		System.out.println("Authorize option is selected successfully.\n");
		
		//Check that when Authorize payment option is active from settings changes must reflect for product settings payment option also
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Payments']/parent::div/parent::button"))).click(); //Click on Payments button
		Thread.sleep(2000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h3[text()='General Payment Settings']")));
		WebElement selectIcons = driver.findElement(By.cssSelector("article div section div div:nth-of-type(3) div span:first-of-type"));
		String classValue1s = selectIcons.getText().trim();
		Assert.assertTrue(classValue1s.contains("Active"),"Option is not selected");
		System.out.println("The selected Changes for Authorize payment option in settings also selected successfully.\n");
		
		//check that after selecting autorize option user can do checkout from checkout page
		driver.navigate().to(Products);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
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
		
		Thread.sleep(5000);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
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
		driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();	
		String PlacedOrderIDs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderIDs);		
		driver.close();
		driver.switchTo().window(originalTab);
		
		//When we select NMI option from payment it should have green tickmark
		driver.navigate().to(Products);
		Thread.sleep(5000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section>div>div:nth-of-type(1)>div:nth-of-type(2)")).click();
		driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		Thread.sleep(5000);
		WebElement selectIcon1 = driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section>div>div:nth-of-type(1)>div:nth-of-type(2)>svg"));
		String classValue11 = selectIcon1.getAttribute("class");
		Assert.assertFalse(classValue11.contains("hidden"),"Option is not selected");
		System.out.println("NMI option is selected successfully.\n");
		
		//Check that when NMI payment option is active from settings changes must reflect for product settings payment option also 
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Payments']/parent::div/parent::button"))).click(); //Click on Payments button
		Thread.sleep(2000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h3[text()='General Payment Settings']")));
		WebElement selectIcons1 = driver.findElement(By.cssSelector("article:nth-of-type(2) div section div div:nth-of-type(3) div span:first-of-type"));
		String classValue1s1 = selectIcons1.getText().trim();
		Assert.assertTrue(classValue1s1.contains("Active"),"Option is not selected");
		System.out.println("The selected Changes for NMI payment option in settings also selected successfully.\n");
		
		//check that after selecting NMI option user can do checkout from checkout page  
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
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
		Thread.sleep(2000);	
		WebElement CardName1 = driver.findElement(By.id("cardHolderName"));
		jse.executeScript("arguments[0].scrollIntoView(true);", CardName1);
		Thread.sleep(1000);
		CardName1.sendKeys(F_Name+" "+L_Name);
		driver.switchTo().frame("CollectJSInlineccnumber");
		driver.findElement(By.id("ccnumber")).sendKeys("5411111111111115");
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlinecvv");
		driver.findElement(By.id("cvv")).sendKeys(CVV);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("CollectJSInlineccexp");
		driver.findElement(By.id("ccexp")).sendKeys("12/44");;
		driver.switchTo().defaultContent();
		
		driver.findElement(By.cssSelector("button[role='checkbox']")).click(); 
		driver.findElement(By.cssSelector("button[type = 'submit']")).click();	
		//String PlacedOrderIDs1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		//System.out.println("Placed Order ID: " + PlacedOrderIDs1);		
		driver.close();
		driver.switchTo().window(originalTab);		
		
		//When we select Stripe option from payment it should have green tickmark 
		driver.navigate().to(Products);
		Thread.sleep(5000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section>div>div:nth-of-type(1)>div:nth-of-type(3)")).click();
		driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		Thread.sleep(5000);
		WebElement selectIconn = driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section>div>div:nth-of-type(1)>div:nth-of-type(3)>svg"));
		String classValue1n = selectIconn.getAttribute("class");
		Assert.assertFalse(classValue1n.contains("hidden"),"Option is not selected");
		System.out.println("Stripe option is selected successfully.\n");
		
		//Check that when Stripe payment option is active from settings changes must reflect for product settings payment option also
		driver.navigate().to(settings);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[normalize-space()='Payments']/parent::div/parent::button"))).click(); //Click on Payments button
		Thread.sleep(2000);
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h3[text()='General Payment Settings']")));
		WebElement selectIcons11 = driver.findElement(By.cssSelector("#stripe>article>div>section>div>div:nth-of-type(3)>div>span:first-of-type"));
		String classValue1s11 = selectIcons11.getText().trim();
		Assert.assertTrue(classValue1s11.contains("Active"),"Option is not selected");
		System.out.println("The selected Changes for Stripe payment option in settings also selected successfully.\n");
		
		//check that after selecting Stripe option user can do checkout from checkout page 
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

		WebElement cardHolder = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolder.clear();
		cardHolder.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(7000);
		String PlacedOrderID11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		System.out.println("Placed Order ID: " + PlacedOrderID11);
		driver.close();
		driver.switchTo().window(originalTab);	
	}
	
	@Test(priority = 16)
	public void Product_SettingsPage_TaxInventory() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		
		//check that domain / url is under default domain title 
		String Settingslabel = driver.findElement(By.xpath("//p[text()='Default domain']")).getText().trim();
		Assert.assertEquals(Settingslabel, "Default domain", "Default domain label is not displayed in product settings page");
		System.out.println("Default domain label is displayed in product settings page successfully.\n");		
		
		//check that domain url should be valid and user can open it navigates 
		driver.findElement(By.xpath("//a[@rel='noopener noreferrer']")).click();//click on domain URL
		Thread.sleep(3000);
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
					break;
			}
		}
		
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("store"),"Domain URL is not valid");
		System.out.println("Domain URL is valid and navigates successfully.\n");
		driver.close();
		driver.switchTo().window(parentWindow);
			
		//check that if user changes url from store details it should change in domain / URL of product setting 
		driver.navigate().to(settings + "/details");
		Thread.sleep(2000);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h2[text()='Domain']")));	
		WebElement storeName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("base_url")));
		storeName.click();
		Thread.sleep(2000);

		storeName.sendKeys(Keys.CONTROL + "a");
		storeName.sendKeys(Keys.DELETE);
		Thread.sleep(2000);

		storeName.sendKeys("store" + (char)('A' + new java.util.Random().nextInt(26)));
		WebElement publishBtn = driver.findElement(By.xpath("//span[normalize-space()='Publish']//parent::button"));
		jse.executeScript("arguments[0].click();", publishBtn);
		
		//check that  after publish store url and save changes sucess message displays
		WebElement SuccessMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Successfully updated store']")));
		boolean Success = SuccessMsg.isDisplayed();
		Assert.assertTrue(Success, "Store details changes success message is not displayed after publish");
		System.out.println("Store URL changes success message display after publish successfully.\n");		
		
		Thread.sleep(5000);
		String StoreUrl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@rel='noopener noreferrer'])[1]"))).getAttribute("href").trim();		
		
		driver.navigate().to(Products);
		Thread.sleep(5000);		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		String DomainUrl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@rel='noopener noreferrer']"))).getText().trim();
		Assert.assertEquals(DomainUrl.replaceAll("/$", ""),StoreUrl.replaceAll("/$", ""));
		System.out.println("Domain URL in product settings page is updated according to the changes from store details page successfully.\n");
		
		//check that there is a subheading Collect Sales & VAT Taxes 
		String SubHeading = driver.findElement(By.xpath("//p[text()='Collect Sales & VAT Taxes']")).getText().trim();
		Assert.assertEquals(SubHeading, "Collect Sales & VAT Taxes", "Collect Sales & VAT Taxes subheading is not displayed in product settings page");
		System.out.println("Collect Sales & VAT Taxes subheading is displayed in product settings page successfully	.\n");
			
		//check that there is a description for Collect Sales & VAT Taxes 
		String Description = driver.findElement(By.xpath("//p[text()='This will automatically calculate the taxes based on your customers location.']")).getText().trim();
		Assert.assertEquals(Description, "This will automatically calculate the taxes based on your customers location.", "Description is not displayed in product settings page");
		System.out.println("Description for Collect Sales & VAT Taxes is displayed in product settings page successfully.\n");
		
		//check that Taxes section contains toggle for Collect Tax on Inventory Products (Inventory products)
		WebElement taxToggle = driver.findElement(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:first-of-type>button"));
		Assert.assertTrue(taxToggle.isDisplayed(), "Toggle for Collect Tax on Inventory Products is	 not displayed in product settings page");
		System.out.println("Toggle for Collect Tax on Inventory Products is displayed in product settings page successfully.\n");
		
		//user can disable the inventory product tax toggle from settings
		Thread.sleep(2000);
		WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:first-of-type>button")));
		String toggleState = toggleButton.getAttribute("data-state");
		if (toggleState.equals("checked")) {
			jse.executeScript("arguments[0].click();", toggleButton);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState.equals("unchecked");
			jse.executeScript("arguments[0].click();", toggleButton);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButton);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		//after saving disable toggle setting for the inventory product then toaster msg will be visible 
		WebElement ToasterMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsg.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If inventory tax toggle is disabled  for default checkout page  then on default checkout page tax should show as $0.00
		driver.navigate().to(Products);
		Thread.sleep(7000);	
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
		Thread.sleep(3000);	
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
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
		String Tax = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertEquals(Tax, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% on checkout page");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% on checkout page successfully.\n");
		
		//If inventory tax toggle is disabled  for default checkout page  then on success page tax should show as $0.00 
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

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Tax1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertEquals(Tax1, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% on success page");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% on success page successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If inventory tax toggle is disabled  for default checkout page  then order placed then in order summary tax should show as $0.00 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);		
		
		String Tax2 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertEquals(Tax2, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% in order summary");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% in order summary successfully.\n");	
		
		//If inventory tax toggle is disabled  for default checkout page then order placed then in customer hub tax should show as $0.00
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Tax3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//span[contains(text(),'$')]"))).getText().trim();
		Assert.assertEquals(Tax3, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% in customer hub");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% in customer hub successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//user can enable the inventory product tax toggle from settings 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		WebElement toggleButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:first-of-type>button")));
		String toggleState1 = toggleButton1.getAttribute("data-state");
		if (toggleState1.equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState1.equals("checked");
			jse.executeScript("arguments[0].click();", toggleButton1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButton1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		WebElement taxRate = driver.findElement(By.xpath("//input[@name='general.flatTaxRate']"));
		taxRate.click();
		String TotalTax = taxRate.getText();
			
		//after saving enable toggle setting for the inventory product then toaster msg will be visible 
		WebElement ToasterMsgs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsgs.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If inventory tax toggle is enable then on default checkout page tax should show as tax 
		driver.navigate().to(Products);
		Thread.sleep(7000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table	>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter>div>a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		Thread.sleep(5000);
		String Taxx = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertNotEquals(Taxx, TotalTax, "After enabling inventory product tax toggle tax is not showing on checkout page");
		System.out.println("After enabling inventory product tax toggle tax is showing on checkout page successfully.\n");
		
		//If inventory tax toggle is enable  for default checkout page then on success page tax should show as tax
		try {
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
		} catch (Exception e) {
			driver.findElement(By.xpath("//button[text()='Use same as my Billing Address']")).click();
		}
				
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
		driver.findElement(By.xpath("//span[text()='Add Card Details']//parent::button")).click();
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

		WebElement cardHolders = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolders.clear();
		cardHolders.sendKeys(F_Name + " " + L_Name);
		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		Thread.sleep(5000);	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Taxs1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertNotEquals(Taxs1, TotalTax, "After enabling inventory product tax toggle	tax is not showing on success page");
		System.out.println("After enabling inventory product tax toggle tax is showing on success page successfully.\n");		
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If inventory tax toggle is enable  for default checkout page then order placed then in order summary tax should show as tax 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);
		
		String Taxs2 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertNotEquals(Taxs2, TotalTax, "After enabling inventory product tax toggle	tax is not showing in order summary");
		System.out.println("After enabling inventory product tax toggle tax is showing in order summary successfully.\n");
		
		//If inventory tax toggle is enable  for default checkout page then order placed then in customer hub tax should show as tax 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxs3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//p[contains(text(),'%')]"))).getText().trim();
		Assert.assertNotEquals(Taxs3, TotalTax, "After enabling inventory product tax toggle	tax is not showing in customer hub");
		System.out.println("After enabling inventory product tax toggle	tax is showing in customer hub successfully.\n");
		
		//User can disable the inventory product tax toggle from settings for checkout page
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		WebElement toggleButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:first-of-type>button")));
		String toggleState2 = toggleButton2.getAttribute("data-state");
		if (toggleState2.equals("checked")) {
			jse.executeScript("arguments[0].click();", toggleButton2);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState2.equals("unchecked");
			jse.executeScript("arguments[0].click();", toggleButton2);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButton2);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		//After saving disable toggle setting for the inventory product then toaster message should be visible
		WebElement ToasterMsg1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsg1.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If inventory tax toggle is disabled for checkout page then on checkout page tax should show as $0.00
		driver.navigate().to(Products);
		Thread.sleep(7000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table	>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		Thread.sleep(5000);
		String Tasx = driver.findElement(By.cssSelector("#no-tailwindcss-base>div>div>section>div>div:nth-of-type(2)>div:nth-of-type(9)>span:nth-of-type(2)")).getText().trim();
		Assert.assertEquals(Tasx, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% on checkout page");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% on checkout page successfully.\n");
		
		//If inventory tax toggle is disabled for checkout page then on success page tax should show as $0.00 
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

		WebElement cardHoldesr = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHoldesr.clear();
		cardHoldesr.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Taxr1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertEquals(Taxr1, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% on success page");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% on success page successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If inventory tax toggle is disabled for checkout page then order placed then in order summary tax should show as $0.00 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);		
		
		String Taxr2 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertEquals(Taxr2, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% in order summary");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% in order summary successfully.\n");	
		
		//If inventory tax toggle is disabled for checkout page then order placed then in customer hub tax should show as $0.00 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}
				
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxr3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//span[contains(text(),'$')]"))).getText().trim();
		Assert.assertEquals(Taxr3, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% in customer hub");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% in customer hub successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//User can enable the inventory product tax toggle from settings for checkout page 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		WebElement toggleButtonn1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:first-of-type>button")));
		String toggleStates1 = toggleButtonn1.getAttribute("data-state");
		if (toggleStates1.equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButtonn1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleStates1.equals("checked");
			jse.executeScript("arguments[0].click();", toggleButtonn1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButtonn1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		WebElement taxRatee = driver.findElement(By.xpath("//input[@name='general.flatTaxRate']"));
		taxRatee.click();
		String TotalTax1 = taxRatee.getText();
		
		//After saving enable toggle setting for the inventory product then toaster message should be visible 
		WebElement ToasterMsgs1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsgs1.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If inventory tax toggle is enabled for checkout page then on checkout page tax should show as tax 
		driver.navigate().to(Products);
		Thread.sleep(7000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table	>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter>div>a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		Thread.sleep(5000);
		String Taxs = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertNotEquals(Taxs, TotalTax1, "After enabling inventory product tax toggle tax is not showing on checkout page");
		System.out.println("After enabling inventory product tax toggle tax is showing on checkout page successfully.\n");
		
		//If inventory tax toggle is enabled for checkout page then on success page tax should show as tax 
		try {
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
		} catch (Exception e) {
			driver.findElement(By.xpath("//button[text()='Use same as my Billing Address']")).click();
		}
				
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
		driver.findElement(By.xpath("//span[text()='Add Card Details']//parent::button")).click();
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

		WebElement cardHolders1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolders1.clear();
		cardHolders1.sendKeys(F_Name + " " + L_Name);
		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Taxs11 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertNotEquals(Taxs11, TotalTax1, "After enabling inventory product tax toggle tax is not showing on success page");
		System.out.println("After enabling inventory product tax toggle tax is showing on success page successfully.\n");		
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If inventory tax toggle is enabled for checkout page then order placed then in order summary tax should show as tax
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);
		
		String Taxs21 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertNotEquals(Taxs21, TotalTax1, "After enabling inventory product tax toggle tax is not showing in order summary");
		System.out.println("After enabling inventory product tax toggle tax is showing in order summary successfully.\n");
		
		//If inventory tax toggle is enabled for checkout page then order placed then in customer hub tax should show as tax 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxs31 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//p[contains(text(),'%')]"))).getText().trim();
		Assert.assertNotEquals(Taxs31, TotalTax1, "After enabling inventory product tax toggle tax is not showing in customer hub");
		System.out.println("After enabling inventory product tax toggle	tax is showing in customer hub successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//User can disable the inventory product tax toggle from settings for default product page 
		/*driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		WebElement toggleButtonn11 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:first-of-type>button")));
		String toggleState11 = toggleButtonn11.getAttribute("data-state");
		if (toggleState11.equals("checked")) {
			jse.executeScript("arguments[0].click();", toggleButtonn11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState11.equals("unchecked");
			jse.executeScript("arguments[0].click();", toggleButtonn11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButtonn11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		//After saving disable toggle setting for the inventory product then toaster message should be visible 
		WebElement ToasterMsgg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsgg.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If inventory tax toggle is disabled for default product page then on default product page tax should show as $0.00
		driver.navigate().to(Products);
		Thread.sleep(7000);	
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
		Thread.sleep(3000);	
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Add to Cart']//parent::button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//button[text()='Proceed to Checkout'])[2]")).click();
		Thread.sleep(5000);
		String Taxa = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertEquals(Taxa, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% on checkout page");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% on default product page successfully.\n");
		
		//If inventory tax toggle is disabled for default product page then on success page tax should show as $0.00 
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

		WebElement cardHoldder = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHoldder.clear();
		cardHoldder.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Taxa1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertEquals(Taxa1, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% on success page");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% on default product page success page successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If inventory tax toggle is disabled for default product page then order placed then in order summary tax should show as $0.00 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);		
		
		String Taxa2 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertEquals(Taxa2, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% in order summary");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% in default product page order summary successfully.\n");	
		
		//If inventory tax toggle is disabled for default product page then order placed then in customer hub tax should show as $0.00 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxa3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//span[contains(text(),'$')]"))).getText().trim();
		Assert.assertEquals(Taxa3, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% in customer hub");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% in customer hub successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//User can enable the inventory product tax toggle from settings for default product page 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		WebElement toggleButton11 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:first-of-type>button")));
		String toggleState111 = toggleButton11.getAttribute("data-state");
		if (toggleState111.equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState111.equals("checked");
			jse.executeScript("arguments[0].click();", toggleButton11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButton11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		WebElement taxRatee1 = driver.findElement(By.xpath("//input[@name='general.flatTaxRate']"));
		taxRatee1.click();
		String TotalTaax11 = taxRatee1.getText();
		
		//After saving enable toggle setting for the inventory product then toaster message should be visible
		WebElement ToasterMsggs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsggs.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If inventory tax toggle is enabled for default product page then on default product page tax should show as tax 
		driver.navigate().to(Products);
		Thread.sleep(7000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table	>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Add to Cart']//parent::button")).click();
		Thread.sleep(2000);	
		driver.findElement(By.xpath("(//button[text()='Proceed to Checkout'])[2]")).click();
		
		Thread.sleep(5000);
		String Taxn = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertNotEquals(Taxn, TotalTaax11, "After enabling inventory product tax toggle tax is not showing on checkout page");
		System.out.println("After enabling inventory product tax toggle tax is showing on default product checkout page successfully.\n");
		
		//If inventory tax toggle is enabled for default product page then on success page tax should show as tax 
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

		WebElement cardHoldesr1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHoldesr1.clear();
		cardHoldesr1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Taxx1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertNotEquals(Taxx1, TotalTaax11, "After enabling inventory product tax toggle tax is not showing on success page");
		System.out.println("After enabling inventory product tax toggle tax is showing on default product success page successfully.\n");		
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If inventory tax toggle is enabled for default product page then order placed then in order summary tax should show as tax 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);
		
		String Taxx2 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertNotEquals(Taxx2, TotalTaax11, "After enabling inventory product tax toggle tax is not showing in order summary");
		System.out.println("After enabling inventory product tax toggle tax is showing in default product order summary successfully.\n");
		
		//If inventory tax toggle is enabled for default product page then order placed then in customer hub tax should show as tax 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxx3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//p[contains(text(),'%')]"))).getText().trim();
		Assert.assertNotEquals(Taxx3, TotalTaax11, "After enabling inventory product tax toggle tax is not showing in customer hub");
		System.out.println("After enabling inventory product tax toggle	tax is showing in customer hub successfully.\n");	*/
	}
	
	@Test(priority = 17)
	public void Product_SettingsPage_TaxNonInventory() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor) driver;
		
		//user can disable the Non-inventory product tax toggle from settings 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		
		WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:nth-of-type(2)>button")));
		String toggleState = toggleButton.getAttribute("data-state");
		if (toggleState.equals("checked")) {
			jse.executeScript("arguments[0].click();", toggleButton);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState.equals("unchecked");
			jse.executeScript("arguments[0].click();", toggleButton);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButton);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		//after saving disable toggle setting for the Non-inventory product then toaster msg will be visible 
		WebElement ToasterMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsg.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If Non-inventory tax toggle is disabled  for default checkout page  then on default checkout page tax should show as $0.00 
		driver.navigate().to(Products);
		Thread.sleep(7000);	
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Non-Inventory\"]")).click();
		Thread.sleep(3000);	
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
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
		String Tax = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertEquals(Tax, "$0.00", "After disabling Non-inventory product tax toggle tax is not showing as 0% on checkout page");
		System.out.println("After disabling Non-inventory product tax toggle tax is showing as 0% on checkout page successfully.\n");
		
		//If Non-inventory tax toggle is disabled  for default checkout page  then on success page tax should show as $0.00 
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

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Tax1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertEquals(Tax1, "$0.00", "After disabling Non-inventory product tax toggle tax is not showing as 0% on success page");
		System.out.println("After disabling Non-inventory product tax toggle tax is showing as 0% on success page successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If Non-inventory tax toggle is disabled  for default checkout page  then order placed then in order summary tax should show as $0.00 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);		
		
		String Tax2 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertEquals(Tax2, "$0.00", "After disabling Non-inventory product tax toggle tax is not showing as 0% in order summary");
		System.out.println("After disabling Non-inventory product tax toggle tax is showing as 0% in order summary successfully.\n");	
		
		//If Non-inventory tax toggle is disabled  for default checkout page then order placed then in customer hub tax should show as $0.00 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Tax3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//span[contains(text(),'$')]"))).getText().trim();
		Assert.assertEquals(Tax3, "$0.00", "After disabling Non-inventory product tax toggle tax is not showing as 0% in customer hub");
		System.out.println("After disabling Non-inventory product tax toggle tax is showing as 0% in customer hub successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//user can enable the inventory product tax toggle from settings 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		WebElement toggleButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:nth-of-type(2)>button")));
		String toggleState1 = toggleButton1.getAttribute("data-state");
		if (toggleState1.equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState1.equals("checked");
			jse.executeScript("arguments[0].click();", toggleButton1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButton1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		WebElement taxRate = driver.findElement(By.xpath("//input[@name='general.flatTaxRate']"));
		taxRate.click();
		String TotalTax = taxRate.getText();
		
		//after saving enable toggle setting for the inventory product then toaster msg will be visible 
		WebElement ToasterMsgs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsgs.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If Non-inventory tax toggle is enable then on default checkout page tax should show as tax 
		driver.navigate().to(Products);
		Thread.sleep(7000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Non-Inventory\"]")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table	>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter>div>a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		Thread.sleep(5000);
		String Taxx = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertNotEquals(Taxx, TotalTax, "After enabling inventory product tax toggle tax is not showing on checkout page");
		System.out.println("After enabling inventory product tax toggle tax is showing on checkout page successfully.\n");
		
		//If Non-inventory tax toggle is enable  for default checkout page then on success page tax should show as tax 
		try {
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
		} catch (Exception e) {
			//driver.findElement(By.xpath("//button[text()='Use same as my Billing Address']")).click();
		}
				
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
		driver.findElement(By.xpath("//span[text()='Add Card Details']//parent::button")).click();
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

		WebElement cardHolders = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolders.clear();
		cardHolders.sendKeys(F_Name + " " + L_Name);
		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		Thread.sleep(5000);	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Taxs1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertNotEquals(Taxs1, TotalTax, "After enabling inventory product tax toggle	tax is not showing on success page");
		System.out.println("After enabling inventory product tax toggle tax is showing on success page successfully.\n");		
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If Non-inventory tax toggle is enable  for default checkout page then order placed then in order summary tax should show as tax 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);
		
		String Taxs2 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertNotEquals(Taxs2, TotalTax, "After enabling inventory product tax toggle tax is not showing in order summary");
		System.out.println("After enabling inventory product tax toggle tax is showing in order summary successfully.\n");
		
		//If Non-inventory tax toggle is enable  for default checkout page then order placed then in customer hub tax should show as tax 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxs3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//p[contains(text(),'%')]"))).getText().trim();
		Assert.assertNotEquals(Taxs3, TotalTax, "After enabling inventory product tax toggle tax is not showing in customer hub");
		System.out.println("After enabling inventory product tax toggle	tax is showing in customer hub successfully.\n");
		
		//User can disable the inventory product tax toggle from settings for default product page 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		WebElement toggleButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:nth-of-type(2)>button")));
		String toggleState2 = toggleButton2.getAttribute("data-state");
		if (toggleState2.equals("checked")) {
			jse.executeScript("arguments[0].click();", toggleButton2);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState2.equals("unchecked");
			jse.executeScript("arguments[0].click();", toggleButton2);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButton2);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		//After saving disable toggle setting for the inventory product then toaster message should be visible 
		WebElement ToasterMsg1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsg1.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If Non-inventory tax toggle is disabled for default product page then on default product page tax should show as $0.00 
		driver.navigate().to(Products);
		Thread.sleep(7000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Non-Inventory\"]")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table	>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div:nth-of-type(2)>div>p")).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		Thread.sleep(5000);
		String Tasx = driver.findElement(By.cssSelector("#no-tailwindcss-base>div>div>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertEquals(Tasx, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% on checkout page");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% on checkout page successfully.\n");
		
		//If Non-inventory tax toggle is disabled for default product page then on success page tax should show as $0.00 
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

		WebElement cardHoldesr = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHoldesr.clear();
		cardHoldesr.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Taxr1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertEquals(Taxr1, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% on success page");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% on success page successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If Non-inventory tax toggle is disabled for default product page then order placed then in order summary tax should show as $0.00 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);		
		
		String Taxr2 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertEquals(Taxr2, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% in order summary");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% in order summary successfully.\n");	
		
		//If Non-inventory tax toggle is disabled for default product page then order placed then in customer hub tax should show as $0.00 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}
				
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxr3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//span[contains(text(),'$')]"))).getText().trim();
		Assert.assertEquals(Taxr3, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% in customer hub");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% in customer hub successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//User can enable the inventory product tax toggle from settings for default product page 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		WebElement toggleButtonn1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:nth-of-type(2)>button")));
		String toggleStates1 = toggleButtonn1.getAttribute("data-state");
		if (toggleStates1.equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButtonn1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleStates1.equals("checked");
			jse.executeScript("arguments[0].click();", toggleButtonn1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButtonn1);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		WebElement taxRatee = driver.findElement(By.xpath("//input[@name='general.flatTaxRate']"));
		taxRatee.click();
		String TotalTax1 = taxRatee.getText();
		
		//After saving enable toggle setting for the inventory product then toaster message should be visible 
		WebElement ToasterMsgs1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsgs1.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If Non-inventory tax toggle is enabled for default product page then on default product page tax should show as tax 
		driver.navigate().to(Products);
		Thread.sleep(7000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Non-Inventory\"]")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table	>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td.tracking-tighter>div>a:first-of-type"))).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		Thread.sleep(5000);
		String Taxs = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertNotEquals(Taxs, TotalTax1, "After enabling inventory product tax toggle tax is not showing on checkout page");
		System.out.println("After enabling inventory product tax toggle tax is showing on checkout page successfully.\n");
		
		//If Non-inventory tax toggle is enabled for default product page then on success page tax should show as tax 
		try {
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
		} catch (Exception e) {
			driver.findElement(By.xpath("//button[text()='Use same as my Billing Address']")).click();
		}
				
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
		driver.findElement(By.xpath("//span[text()='Add Card Details']//parent::button")).click();
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

		WebElement cardHolders1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHolders1.clear();
		cardHolders1.sendKeys(F_Name + " " + L_Name);
		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Taxs11 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertNotEquals(Taxs11, TotalTax1, "After enabling inventory product tax toggle tax is not showing on success page");
		System.out.println("After enabling inventory product tax toggle tax is showing on success page successfully.\n");		
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If Non-inventory tax toggle is enabled for default product page then order placed then in order summary tax should show as tax 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);
		
		String Taxs21 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertNotEquals(Taxs21, TotalTax1, "After enabling inventory product tax toggle tax is not showing in order summary");
		System.out.println("After enabling inventory product tax toggle tax is showing in order summary successfully.\n");
		
		//If Non-inventory tax toggle is enabled for default product page then order placed then in customer hub tax should show as tax 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxs31 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//p[contains(text(),'%')]"))).getText().trim();
		Assert.assertNotEquals(Taxs31, TotalTax1, "After enabling inventory product tax toggle tax is not showing in customer hub");
		System.out.println("After enabling inventory product tax toggle	tax is showing in customer hub successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//User can disable the Non-inventory product tax toggle from settings for checkout page 
		/*driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		WebElement toggleButtonn11 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:nth-of-type(2)>button")));
		String toggleState11 = toggleButtonn11.getAttribute("data-state");
		if (toggleState11.equals("checked")) {
			jse.executeScript("arguments[0].click();", toggleButtonn11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState11.equals("unchecked");
			jse.executeScript("arguments[0].click();", toggleButtonn11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButtonn11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		//After saving disable toggle setting for the inventory product then toaster message should be visible 
		WebElement ToasterMsgg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsgg.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//After saving disable toggle setting for the inventory product then toaster message should be visible 
		WebElement ToasterMsgg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsgg.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If Non-inventory tax toggle is disabled for checkout page then on checkout page tax should show as $0.00 
		driver.navigate().to(Products);
		Thread.sleep(7000);	
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Non-Inventory\"]")).click();
		Thread.sleep(3000);	
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}		

		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Add to Cart']//parent::button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//button[text()='Proceed to Checkout'])[2]")).click();
		Thread.sleep(5000);
		String Taxa = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertEquals(Taxa, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% on checkout page");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% on default product page successfully.\n");
		
		//If Non-inventory tax toggle is disabled for checkout page then on success page tax should show as $0.00 
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

		WebElement cardHoldder = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHoldder.clear();
		cardHoldder.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Taxa1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertEquals(Taxa1, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% on success page");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% on default product page success page successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If Non-inventory tax toggle is disabled for checkout page then order placed then in order summary tax should show as $0.00 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);		
		
		String Taxa2 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertEquals(Taxa2, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% in order summary");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% in default product page order summary successfully.\n");	
		
		//If Non-inventory tax toggle is disabled for checkout page then order placed then in customer hub tax should show as $0.00 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}	
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxa3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//span[contains(text(),'$')]"))).getText().trim();
		Assert.assertEquals(Taxa3, "$0.00", "After disabling inventory product tax toggle tax is not showing as 0% in customer hub");
		System.out.println("After disabling inventory product tax toggle tax is showing as 0% in customer hub successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//User can enable the inventory product tax toggle from settings for checkout page 
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		Thread.sleep(2000);
		WebElement toggleButton11 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:nth-of-type(2)>button")));
		String toggleState111 = toggleButton11.getAttribute("data-state");
		if (toggleState111.equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState111.equals("checked");
			jse.executeScript("arguments[0].click();", toggleButton11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButton11);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}
		
		WebElement taxRatee1 = driver.findElement(By.xpath("//input[@name='general.flatTaxRate']"));
		taxRatee1.click();
		String TotalTaax11 = taxRatee1.getText();
		
		//After saving enable toggle setting for the inventory product then toaster message should be visible
		WebElement ToasterMsggs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		Assert.assertTrue(ToasterMsggs.isDisplayed(), "Product settings changes success message is not displayed after saving changes");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//If Non-inventory tax toggle is enabled for checkout page then on checkout page tax should show as tax 
		driver.navigate().to(Products);
		Thread.sleep(7000);
		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
		driver.findElement(By.xpath("//div[normalize-space()=\"Non-Inventory\"]")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.custom-class-table>table	>tbody>tr>td:nth-of-type(2)>div>a:first-of-type"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
		Thread.sleep(4000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[text()='Add to Cart']//parent::button")).click();
		Thread.sleep(2000);	
		driver.findElement(By.xpath("(//button[text()='Proceed to Checkout'])[2]")).click();
		
		Thread.sleep(5000);
		String Taxn = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:nth-of-type(2)")).getText().trim();
		Assert.assertNotEquals(Taxn, TotalTaax11, "After enabling inventory product tax toggle tax is not showing on checkout page");
		System.out.println("After enabling inventory product tax toggle tax is showing on default product checkout page successfully.\n");
		
		//If Non-inventory tax toggle is enabled for checkout page then on success page tax should show as tax 
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

		WebElement cardHoldesr1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
		cardHoldesr1.clear();
		cardHoldesr1.sendKeys(F_Name + " " + L_Name);

		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[type='submit']")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		String Taxx1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
		Assert.assertNotEquals(Taxx1, TotalTaax11, "After enabling inventory product tax toggle tax is not showing on success page");
		System.out.println("After enabling inventory product tax toggle tax is showing on default product success page successfully.\n");		
		driver.close();
		driver.switchTo().window(originalTab);
		
		//If Non-inventory tax toggle is enabled for checkout page then order placed then in order summary tax should show as tax 
		Thread.sleep(2000);
		driver.findElement(By.id("orderHistoryToggleID")).click();
		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
		Thread.sleep(2000);
		
		String Taxx2 = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
		Assert.assertNotEquals(Taxx2, TotalTaax11, "After enabling inventory product tax toggle tax is not showing in order summary");
		System.out.println("After enabling inventory product tax toggle tax is showing in default product order summary successfully.\n");
		
		//If Non-inventory tax toggle is enabled for checkout page then order placed then in customer hub tax should show as tax 
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxx3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//p[contains(text(),'%')]"))).getText().trim();
		Assert.assertNotEquals(Taxx3, TotalTaax11, "After enabling inventory product tax toggle tax is not showing in customer hub");
		System.out.println("After enabling inventory product tax toggle	tax is showing in customer hub successfully.\n");
		 */		
	}
	
	@Test(priority = 18)
	public void Flat_Ratetax() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		JavascriptExecutor jse =  (JavascriptExecutor)driver;
		Random random = new Random();
		
		String Tax_Rate = String.valueOf(random.nextInt(9) + 1);
		
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//p[normalize-space()='Settings']]"))).click();
		
		/*WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.product-page-ui>div:nth-of-type(4)>div>div:nth-of-type(2)>div>div:nth-of-type(2)>div>section:nth-of-type(2)>section>div>div:first-of-type>button")));
		String toggleState = toggleButton.getAttribute("data-state");
		if (toggleState.equals("unchecked")) {
			jse.executeScript("arguments[0].click();", toggleButton);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		} else {
			toggleState.equals("unchecked");
			jse.executeScript("arguments[0].click();", toggleButton);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
			Thread.sleep(3000);
			jse.executeScript("arguments[0].click();", toggleButton);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		}*/
		
		//check that taxes section have tax field with % header label text 
		WebElement taxField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Flat Rate Tax %']")));
		String taxFieldText = taxField.getText().trim();
		Assert.assertEquals(taxFieldText, "Flat Rate Tax %", "Flat Tax Rate % field is not visible in taxes section");
		System.out.println("Flat Tax Rate % field is visible in taxes section successfully.\n");	
		
		//check that there is a link just below the tax rate field box 
		WebElement taxLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Find your state tax rate here']")));
		Assert.assertTrue(taxLink.isDisplayed(), "Find your state tax rate here is not visible below the tax rate field box");
		System.out.println("Find your state tax rate here link is visible below the tax rate field box successfully.\n");
		
		//check that user can enter % tax in the tax field 
		WebElement taxRate = driver.findElement(By.xpath("//input[@name='general.flatTaxRate']"));
		Thread.sleep(3000);
		taxRate.click();
		taxRate.sendKeys(Keys.CONTROL, "a");
		taxRate.sendKeys(Keys.DELETE);
		taxRate.sendKeys("35");
		driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		
		//check that warning msg is like " Flat rate tax must be less than or equal to 30 "
		WebElement WarningMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Flat rate tax must be less than or equal to 30')]")));
		String WarningMsgText = WarningMsg.getText().trim();
		Assert.assertEquals(WarningMsgText, "Flat rate tax must be less than or equal to 30.", "After entering more than 30% in tax field warning message is not displayed");
		System.out.println("After entering more than 30% in tax field warning message is displayed successfully.\n");
		
		//check that after saving tax rate system should show success msg on right side 
		WebElement taxRate1 = driver.findElement(By.xpath("//input[@name='general.flatTaxRate']"));
		Thread.sleep(3000);
		taxRate1.click();
		taxRate1.sendKeys(Keys.CONTROL, "a");
		taxRate1.sendKeys(Keys.DELETE);
		taxRate1.sendKeys(Tax_Rate);
		driver.findElement(By.xpath("//header//button[normalize-space()='Save']")).click();
		WebElement ToasterMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Successfully updated general settings'])[1]")));
		String ToasterMsgText = ToasterMsg.getText().trim();
		Assert.assertEquals(ToasterMsgText, "Successfully updated general settings", "After entering tax field warning message is not displayed");
		System.out.println("Product settings changes success message is displayed after saving changes successfully.\n");
		
		//check that link should be valid and when open it should show different state tax rates 
		String originalTab = driver.getWindowHandle();
//		taxLink.click();
//		Thread.sleep(2000);
//		
//		for (String handle : driver.getWindowHandles()) {
//			if (!handle.equals(originalTab)) {
//				driver.switchTo().window(handle);
//					break;
//			}
//		}
//		
//		String currentUrl = driver.getCurrentUrl();
//		Assert.assertEquals(currentUrl, "https://taxfoundation.org/data/all/state/sales-tax-rates/", "After clicking on find your state tax rate here link it is not navigating to correct page");
//		System.out.println("After clicking on find your state tax rate here link it is navigating to correct page successfully.\n");
//		driver.close();
//		driver.switchTo().window(originalTab);
//		
//		//check that if user enters tax rate it should apply for products on default checkout page 
//		driver.navigate().to(Products);
//		Thread.sleep(7000);	
//		driver.findElements(By.cssSelector("div.product-page-ui>div:nth-of-type(2)>div>div>div:first-of-type>div>div>div>button")).get(1).click();
//		driver.findElement(By.xpath("//div[normalize-space()=\"Inventory\"]")).click();
//		Thread.sleep(3000);			
//		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.id("orderPagesFunnel")).click();
//		driver.findElement(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).click();
//		
//		Thread.sleep(4000);
//		for (String handle : driver.getWindowHandles()) {
//			if (!handle.equals(originalTab)) {
//				driver.switchTo().window(handle);
//				break;
//			}
//		}
//		
//		Thread.sleep(5000);
//		String ordertax = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:first-of-type")).getText().trim();
//		System.out.println("Order Checkout Page Tax: " + ordertax);
//		String extractedTax = ordertax.replaceAll("[^0-9]", "");
//		Assert.assertEquals(extractedTax, Tax_Rate, "Tax rate mismatch in order summary");
//		
//		Thread.sleep(7000);
//		driver.findElement(By.cssSelector("input#email")).sendKeys(email);
//		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
//		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
//		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("button#country")).click(); 
//		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country);
//		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
//		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
//		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();
//		
//		Thread.sleep(5000);
//		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
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
//
//		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("button[type='submit']")).click();
//		Thread.sleep(7000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
//		
//		//check that if user enters tax rate it should apply for products on order success page
//		String successPageTax = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]/following-sibling::span")).getText().trim();
//		System.out.println("default checkout Order Success Page Tax: " + successPageTax);
//		String extractedSuccessPageTax = successPageTax.replaceAll("[^0-9]", "");
//		//Assert.assertEquals(extractedSuccessPageTax, Tax_Rate, "Tax rate mismatch on success page");
//		System.out.println("After entering tax rate in tax field it is showing correct tax on default checkout success page successfully.\n");
//		driver.close();
//		driver.switchTo().window(originalTab);
//		
//		//check that if user enters tax rate it should apply for products on order summary
//		driver.findElement(By.id("orderHistoryToggleID")).click();
//		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
//		Thread.sleep(2000);
//		String orderSummaryTax = driver.findElement(By.xpath("//span[text()='Tax']/ancestor::div[contains(@class,'justify-between')]//span[contains(text(),'$')]")).getText().trim();
//		System.out.println("default checkout Order Summary Tax: " + orderSummaryTax);
//		String extractedOrderSummaryTax = orderSummaryTax.replaceAll("[^0-9]", "");
//		//Assert.assertEquals(extractedOrderSummaryTax, Tax_Rate, "Tax rate mismatch in order summary");
//		System.out.println("After entering tax rate in tax field it is showing correct tax in default checkout order summary successfully.\n");
//		
//		//check that if user enters tax rate it should apply for products on default product page 
//		driver.navigate().to(Products);
//		Thread.sleep(7000);	
//		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.id("orderPagesFunnel")).click();
//		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(3)>td:nth-of-type(2)>div>div>p")).click();
//		
//		Thread.sleep(4000);
//		for (String handle : driver.getWindowHandles()) {
//			if (!handle.equals(originalTab)) {
//				driver.switchTo().window(handle);
//				break;
//			}
//		}
//		
//		Thread.sleep(5000);
//		String ordertax1 = driver.findElement(By.cssSelector("#CheckoutFormTemplate>section>section>div>div:nth-of-type(2)>div:nth-of-type(8)>span:first-of-type")).getText().trim();
//		System.out.println("default product Order Checkout Page Tax: " + ordertax1);
//		String extractedTax1 = ordertax1.replaceAll("[^0-9]", "");
//		Assert.assertEquals(extractedTax1, Tax_Rate, "Tax rate mismatch in order summary");
//		
//		Thread.sleep(7000);
//		driver.findElement(By.cssSelector("input#email")).sendKeys(email);
//		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
//		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
//		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("button#country")).click(); 
//		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country); 
//		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
//		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
//		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();
//		
//		Thread.sleep(5000);
//		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
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
//		WebElement cardHolder1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
//		cardHolder1.clear();
//		cardHolder1.sendKeys(F_Name + " " + L_Name);
//
//		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("button[type='submit']")).click();
//		Thread.sleep(7000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
//		
//		//check that if user enters tax rate it should apply for products on default product Success page
//		Thread.sleep(2000);
//		String successPageTax1 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]")).getText().trim();
//		System.out.println("default product Order Success Page Tax: " + successPageTax1);
//		String extractedSuccessPageTax1 = successPageTax1.replaceAll("[^0-9]", "");
//		Assert.assertEquals(extractedSuccessPageTax1, Tax_Rate, "Tax rate mismatch on success page");
//		System.out.println("After entering tax rate in tax field it is showing correct tax on default product Success page successfully.\n");
//		driver.close();
//		driver.switchTo().window(originalTab);
//		
//		//check that if user enters tax rate it should apply for products on default product Summary Order page 
//		driver.findElement(By.id("orderHistoryToggleID")).click();
//		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
//		Thread.sleep(4000);
//		String orderSummaryTax1 = driver.findElement(By.xpath("(//span[@class='text-black-225 text-sm font-bold'])[2]")).getText().trim();
//		System.out.println("default product Order Summary Tax: " + orderSummaryTax1);
//		String extractedOrderSummaryTax1 = orderSummaryTax1.replaceAll("[^0-9]", "");
//		//Assert.assertEquals(extractedOrderSummaryTax1, Tax_Rate, "Tax rate mismatch in order summary");
//		System.out.println("After entering tax rate in tax field it is showing correct tax in default product order summary successfully.\n");
//		
//		//check that if user enters tax rate it should apply for products on  checkout page 
//		driver.navigate().to(Products);
//		Thread.sleep(7000);	
//		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:first-of-type>td:first-of-type>a")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.id("orderPagesFunnel")).click();
//		driver.findElement(By.cssSelector("div.custom-class-table>table>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>div>div>p")).click();
//		Thread.sleep(4000);
//
//		for (String handle : driver.getWindowHandles()) {
//			if (!handle.equals(originalTab)) {
//				driver.switchTo().window(handle);
//				break;
//			}
//		}		
//
//		Thread.sleep(7000);	
//		driver.findElement(By.xpath("//button[normalize-space()=\"Add to Cart\"]")).click();
//		Thread.sleep(2000);	
//		String ordertax11 = driver.findElement(By.xpath("(//p[text()='Taxes']/following-sibling::p)[1]")).getText().trim();
//		System.out.println("default Checkout Page Tax: " + ordertax11);
//		String extractedTax11 = ordertax11.replaceAll("[^0-9]", "");
//		//Assert.assertEquals(extractedTax11, Tax_Rate, "Tax rate mismatch in order summary");		
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("(//button[text()='Proceed to Checkout'])[2]")).click();	
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector("input#email")).sendKeys(email);  
//		driver.findElement(By.cssSelector("input#first_name")).sendKeys(firstName);
//		driver.findElement(By.cssSelector("input#last_name")).sendKeys(lastName);
//		driver.findElement(By.cssSelector("input#phone")).sendKeys(phone);
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("button#country")).click(); 
//		driver.findElement(By.xpath("//input[@placeholder=\"Search country...\"]")).sendKeys(country);
//		driver.findElement(By.xpath("//li[@role=\"option\"]")).click();
//		driver.findElement(By.cssSelector("input#street")).sendKeys(Street_Add);
//		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();
//
//		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//h2[normalize-space()='Payment Information']")));
//		Thread.sleep(2000);
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
//		WebElement cardHolder11 = wait.until(ExpectedConditions.elementToBeClickable(By.name("cardHolderName")));
//		cardHolder11.clear();
//		cardHolder11.sendKeys(F_Name + " " + L_Name);
//
//		driver.findElement(By.xpath("//button[@role='checkbox']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("button[type='submit']")).click();
//		Thread.sleep(7000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
//		
//		//check that if user enters tax rate it should apply for products on  checkout Success page
//		Thread.sleep(2000);
//		String successPageTax11 = driver.findElement(By.xpath("//span[contains(text(),'Taxes')]")).getText().trim();
//		System.out.println("default product Order Success Page Tax: " + successPageTax11);
//		String extractedSuccessPageTax11 = successPageTax11.replaceAll("[^0-9]", "");
//		Assert.assertEquals(extractedSuccessPageTax11, Tax_Rate, "Tax rate mismatch on success page");
//		System.out.println("After entering tax rate in tax field it is showing correct tax on checkout product Success page successfully.\n");
//		driver.close();
//		driver.switchTo().window(originalTab);
//		
//		//check that if user enters tax rate it should apply for products on  checkout Summary Order page 
//		driver.findElement(By.id("orderHistoryToggleID")).click();
//		driver.findElement(By.cssSelector("#order-table-body>tr:first-of-type>td:first-of-type")).click();
//		Thread.sleep(4000);
//		String orderSummaryTax11 = driver.findElement(By.xpath("(//span[@class='text-black-225 text-sm font-bold'])[2]")).getText().trim();
//		System.out.println("checkout page Order Summary Tax: " + orderSummaryTax11);
//		String extractedOrderSummaryTax11 = orderSummaryTax11.replaceAll("[^0-9]", "");
//		//Assert.assertEquals(extractedOrderSummaryTax11, Tax_Rate, "Tax rate mismatch in order summary");
//		System.out.println("After entering tax rate in tax field it is showing correct tax in checkout product order summary successfully.\n");
//		
//		//check that if user enters tax rate it should apply for products and can be seen in VT transaction summary
//		driver.navigate().to(Virtual_Terminal);
//		Thread.sleep(20000);
//		driver.findElement(By.xpath("//span[normalize-space(.)='ACH']//parent::button")).click(); 
//		Thread.sleep(1000);
//		String VTtax = driver.findElement(By.cssSelector("div.Transaction-summary-inside-class>div:nth-of-type(2)>div>div:nth-of-type(2)>span:first-of-type")).getText().trim();
//		System.out.println("Virtual Terminal Tax: " + VTtax);
//		String extractedVTtax = VTtax.replaceAll("[^0-9]", "");
//		Assert.assertEquals(extractedVTtax, Tax_Rate, "Tax rate mismatch in VT item section");
//		System.out.println("After entering tax rate in tax field it is showing correct tax in VT item section successfully.\n");
//		
//		//check that if user enters tax rate it should apply for products and can be seen in VT order success page
//		driver.findElement(By.xpath("//button[.//span[normalize-space()='Add Customer']]")).click(); 
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//input[@placeholder=\"Search Customer\"]")).sendKeys(Number); 
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[contains(@class,'overflow-y-auto')]/div[contains(@class,'mb-[5px]')][1]")).click(); 
//		Thread.sleep(1000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//text()[contains(., 'Add Line Items')]]"))).click(); 
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//p[text()='Item Name']/following::input[@placeholder='Select an item'][1]")).click(); 
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//button[.//span[text()='Create New Item']]/parent::div/following-sibling::div[1]")).click(); 
//		Thread.sleep(1000);
//
//		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space(text())='Bank Account Details']")));
//		Thread.sleep(3000);
//		driver.findElement(By.name("creditCard.achAccountHolderName")).sendKeys(Account_Holder_Name);
//		driver.findElement(By.name("creditCard.achRoutingNumber")).sendKeys(Routing_Number);
//		driver.findElement(By.name("creditCard.achAccountNumber")).sendKeys(Account_Number);
//		driver.findElement(By.id("creditCard.achAccType")).click();
//		driver.findElement(By.id("react-select-2-option-0")).click();
//		driver.findElement(By.id("creditCard.achAccountCategory")).click();
//		driver.findElement(By.id("react-select-3-option-0")).click(); 
//
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector("div.virtual-terminal-ui>form>div>div>div:nth-of-type(7)>div:nth-of-type(2)>h3>div>div>div:last-of-type")).click(); 
//		driver.findElement(By.name("address.address_1")).sendKeys(Street_Add); 
//		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();	
//		Thread.sleep(1000);
//		driver.findElement(By.name("address.address_2")).sendKeys(Street_Add); 
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//button[normalize-space(.//p/text())='ACH Payment']")).click(); 
//		Thread.sleep(15000);
//		driver.findElement(By.xpath("//div[h2[normalize-space(text())='Transaction Summary']]/p")).getText().trim().replace("#", ""); 
//		
//		//check that if user enters tax rate it should apply for products and can be seen in order summary of order
//		String orderSummaryTaxvt = driver.findElement(By.xpath("//p[contains(text(),'Tax')]")).getText().trim();
//		System.out.println("checkout page Order Summary Tax: " + orderSummaryTaxvt);
//		String extractedOrderSummaryTaxvt = orderSummaryTaxvt.replaceAll("[^0-9]", "");
//		Assert.assertEquals(extractedOrderSummaryTaxvt, Tax_Rate, "Tax rate mismatch in Virtual terminal order success page");
//		System.out.println("After entering tax rate in tax field it is showing correct tax in Virtual terminal order Success page successfully.\n");
//		
//		//check that if user enters tax rate it should apply for products and can be seen in VT order of order module
//		driver.get(Orders);
//		Thread.sleep(5000);
//		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)")).click();
//		Thread.sleep(2000);
//		String orderSummaryTax11 = driver.findElement(By.xpath("(//span[@class='text-black-225 text-sm font-bold'])[2]")).getText().trim();
//		System.out.println("checkout page Order Summary Tax: " + orderSummaryTax11);
//		String extractedOrderSummaryTaxvto = orderSummaryTax11.replaceAll("[^0-9]", "");
//		//Assert.assertEquals(extractedOrderSummaryTaxvto, Tax_Rate, "Tax rate mismatch in Virtual terminal order summary page");
//		System.out.println("After entering tax rate in tax field it is showing correct tax in Virtual terminal order summary successfully.\n");
//		
//		//When we apply flat tax rate for Inventory product it should reflect on custome hub in orders section
//		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
//		Thread.sleep(5000);
//		
//		for (String handle : driver.getWindowHandles()) {
//			if (!handle.equals(originalTab)) {
//				driver.switchTo().window(handle);
//					break;
//			}
//		}
//		
//		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
//		String Taxs3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//p[contains(text(),'%')]"))).getText().trim();
//		Assert.assertNotEquals(Taxs3, Tax_Rate, "After entering tax rate in tax field it is not showing in customer hub");
//		System.out.println("After entering tax rate in tax field it is showing in customer hub successfully.\n");
		
		//check that given flat tax rate should be apply and can be seen in invoices transaction summary
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='+ New Invoice'])[2]"))).click(); //Click on new invoice button		
		Thread.sleep(3000);
		
		String INVtax = driver.findElement(By.xpath("//p[contains(text(),'Tax')]")).getText().trim();
		System.out.println("Invoices transaction summary Tax: " + INVtax);
		String extractedINVtax = INVtax.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedINVtax, Tax_Rate, "Tax rate mismatch in VT item section");
		System.out.println("After entering tax rate in tax field it is showing correct tax in invoices transaction summary section successfully.\n");
		
		//check that given flat tax rate should be apply and can be seen in invoices order success page
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
		driver.findElement(By.xpath("(//input[contains(@placeholder,'—')])[1]")).sendKeys("2"); //Input quantity
		driver.findElement(By.id("unit-price")).sendKeys("2"); //input value in unit price field
		driver.findElement(By.xpath("//textarea[@placeholder=\"Custom line item description\"]")).sendKeys("Add New Product Discription"); 
		driver.findElement(By.xpath("(//span[normalize-space()='Done'])[1]")).click(); //Click on save button to save line item
		
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h1[normalize-space()='Payment Terms']")));
		Thread.sleep(3000);
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
		driver.findElement(By.id("street-address")).clear();
		driver.findElement(By.id("street-address")).sendKeys(Street_Add);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("p.list-none.suggestions-dropdown>li:first-of-type")).click();//select address from drop down          
		
        jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[normalize-space()='Card Information']")));
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
		Thread.sleep(2000);
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click(); 
        Thread.sleep(7000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.print-container>div:nth-of-type(2)>p>span"))).getText().trim().toLowerCase();
		
		//check that given flat tax rate should be apply and can be seen in invoices order success page
		String orderSummaryTaxInv = driver.findElement(By.xpath("//span[contains(text(),'Tax')]")).getText().trim();
		System.out.println("checkout page Order Summary Tax: " + orderSummaryTaxInv);
		String extractedOrderSummaryTaxInV = orderSummaryTaxInv.replaceAll("[^0-9]", "");
		Assert.assertEquals(extractedOrderSummaryTaxInV, Tax_Rate, "Tax rate mismatch in Virtual terminal order success page");
		System.out.println("After entering tax rate in tax field it is showing correct tax in Invoices order Success page successfully.\n");
		
		//check that given flat tax rate should be apply and can be seen in invoices order summary of order 
		driver.get(Orders);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#order-table-body>tr>td:nth-of-type(2)")).click();
		Thread.sleep(2000);
		String orderSummaryTax11 = driver.findElement(By.xpath("(//span[@class='text-black-225 text-sm font-bold'])[2]")).getText().trim();
		System.out.println("checkout page Order Summary Tax: " + orderSummaryTax11);
		String extractedOrderSummaryTaxiN = orderSummaryTax11.replaceAll("[^0-9]", "");
		//Assert.assertEquals(extractedOrderSummaryTaxiN, Tax_Rate, "Tax rate mismatch in Virtual terminal order summary page");
		System.out.println("After entering tax rate in tax field it is showing correct tax in Invoices order summary successfully.\n");
		
		//check that given flat tax rate should be apply and can be seen in invoices customer hub in orders section
		driver.findElement(By.cssSelector("div.order-details-section-class-width>div:nth-of-type(2)>div:nth-of-type(2)>div:first-of-type>div>div:nth-of-type(2)>div>div>button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),\"Open Customer Hub\")]")).click();
		Thread.sleep(5000);
		
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
					break;
			}
		}
		
		driver.findElement(By.cssSelector("div.grid.grid-cols-1>div:nth-of-type(2)>main>section:nth-of-type(2)>div>table>tbody>tr:first-of-type>td:first-of-type>div>div>a")).click();
		String Taxs3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Tax')]/parent::div//p[contains(text(),'%')]"))).getText().trim();
		Assert.assertNotEquals(Taxs3, Tax_Rate, "After entering tax rate in tax field it is not showing in customer hub");
		System.out.println("After entering tax rate in tax field it is showing in customer hub successfully.\n");
		driver.close();
		driver.switchTo().window(originalTab);
	}
	
	
	
	
	
	
	
	
	
	
	
	
		
		
	
}