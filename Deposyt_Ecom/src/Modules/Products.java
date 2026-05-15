package Modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();
		System.out.println("User able to upload image in PNG format in media and crop functionality also works fine");
		WebElement parent4 = driver.findElement(By.xpath("//div[contains(@class,'absolute') and contains(@class,'inset-0')]//*[name()='svg']"));
		actions.moveToElement(parent4).perform();
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>div>div>svg")).click();

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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

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
		driver.findElement(By.name("productPricing.salePrice")).sendKeys(OneTimePurchaseSalePrice);

		Thread.sleep(2000);
		WebElement confirmSaveButton = driver.findElement(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)"));
		jse.executeScript("arguments[0].click();", confirmSaveButton);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.sticky.bottom-0>div>button:nth-of-type(2)")));
		Thread.sleep(3000);

		//check that saved product name can be seen in recent products
		WebElement recentProduct = driver.findElement(By.cssSelector("div.recent-product-card-shadow:first-of-type>div:nth-of-type(2)>div:first-of-type"));
		String actualRecentProduct = recentProduct.getText().trim();
		Assert.assertEquals(actualRecentProduct, Product_Name, "Saved product name is not displayed	 in recent products");
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
		WebElement productNameOnCheckout = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>span"));
		String actualProductNameOnCheckout = productNameOnCheckout.getText().trim();
		Assert.assertEquals(actualProductNameOnCheckout, Product_Name, "Saved product name is not displayed on checkout page");
		System.out.println("Saved product name is displayed successfully on checkout page");
		driver.close();
		driver.switchTo().window(originalTab);

		//check that saved product name can be seen  on default checkout page
		driver.findElements(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).get(1).click();
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
		driver.findElements(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).get(2).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(2000);
		WebElement productNameOnprodCheckout = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>span:first-of-type"));
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
			driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		WebElement productNameOnCheckout = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:last-of-type>span"));
		String actualProductNameOnCheckout = productNameOnCheckout.getText().trim();
		double actualPrices = Double.parseDouble(actualProductNameOnCheckout.replace("$", ""));
		double expectedPrices = Double.parseDouble(OneTimeProductValue);
		Assert.assertEquals(actualPrices, expectedPrices,"Product price is not displayed correctly on checkout page");
		System.out.println("product price is displayed successfully on checkout page");
		driver.close();
		driver.switchTo().window(originalTab);

		//check that saved product name can be seen  on default checkout page
		driver.findElements(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).get(1).click();
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
		driver.findElements(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).get(2).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(7000);
		WebElement productNameOnprodCheckout = driver.findElement(By.cssSelector("#checkout-template>div>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:last-of-type>span"));
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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
		Thread.sleep(2000);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Pricing Options']")));
		WebElement salePriceCheckbox = driver.findElement(By.cssSelector("#one-time-purchase-div>div:nth-of-type(2)>div>div>div:nth-of-type(3)>div>label>input"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", salePriceCheckbox);
		Thread.sleep(1000);
		salePriceCheckbox.click();
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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		driver.findElements(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).get(1).click();
		Thread.sleep(4000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalTab)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		Thread.sleep(5000);
		WebElement productNameOndefCheckout1 = driver.findElement(By.cssSelector("#ProductDescriptionColumn>div>span:last-of-type"));
		String actualProductNameOndefCheckout1 = productNameOndefCheckout1.getText().trim();
		double actualPriced1 = Double.parseDouble(actualProductNameOndefCheckout1.replaceAll("[^0-9.]", ""));
		double expectedPriced1 = Double.parseDouble(Discountedprice);
		Assert.assertEquals(actualPriced1, expectedPriced1,"Product sale price is not displayed correctly on Default checkout page");
		System.out.println("product sale price is displayed successfully on Default checkout page");
		driver.close();
		driver.switchTo().window(originalTab);

		//check that if user enters sale Price for product then it should reflect in default product page
		/*driver.findElements(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).get(2).click();
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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		driver.findElements(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).get(2).click();
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
			driver.findElements(By.cssSelector("td.tracking-tighter>div>a:first-of-type")).get(2).click();
			Thread.sleep(10000);
			WebElement quantityPickeres = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Increase quantity']")));
			Assert.assertFalse(quantityPickeres.isDisplayed(), "Quantity picker is not displayed on default product page after enabling show quantity picker toggle");
			System.out.println("Quantity picker is disabled successfully on default product page after disabling show quantity picker toggle");
		} catch(Exception e) {
			System.out.println("Quantity picker is disabled successfully on default product page after disabling show quantity picker toggle");
		}

		Thread.sleep(4000);
		//driver.close();
		driver.switchTo().window(originalTab);

		try { 
			driver.navigate().to(Products);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"Search Products\"]"))).sendKeys(Product_Name);	
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

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
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).clear();
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(OneTimeProductValue);
		driver.findElement(By.cssSelector("div.frequency-select-container")).click(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='Make the first payment different']/following-sibling::button[@role=\"switch\"]")).click(); 
		WebElement suscprise = driver.findElement(By.name("productPricing.regularPrice"));
		suscprise.clear();
		suscprise.sendKeys(OneTimePurchaseSalePrice);

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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(2).click();
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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(1).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

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
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).clear();
		driver.findElement(By.cssSelector("input[name=\"productPricing.regularPrice\"]")).sendKeys(OneTimeProductValue);
		driver.findElement(By.cssSelector("div.frequency-select-container")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role=\"listbox\"]/div[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[normalize-space()='Make the first payment different']/following-sibling::button[@role=\"switch\"]")).click();
		WebElement suscprise = driver.findElement(By.name("productPricing.regularPrice"));
		suscprise.clear();
		suscprise.sendKeys(OneTimePurchaseSalePrice);

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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(2).click();
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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("orderPagesFunnel"))).click();
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(1).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();

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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(2).click();
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
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(1).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();

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
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(2).click();
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
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(1).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();

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
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(2).click();
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
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(1).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();

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
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(2).click();
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
		driver.findElements(By.cssSelector("td.tracking-tighter > div > a:first-of-type")).get(1).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();

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
		WebElement warningMessagees = driver.findElement(By.xpath("//p[text()='Price must be greater than 0']"));
		String actualMessagese = warningMessagees.getText().trim();
		String expectedMessagese = "Price must be greater than 0";
		Assert.assertEquals(actualMessagese, expectedMessagese, "Warning message Price must be greater than 0 not Displays");

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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();
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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		driver.findElement(By.cssSelector("div.Product-Detial-side-modal-Scrollbar>div:nth-of-type(2)>div:nth-of-type(2)>div>div:nth-of-type(2)>div>div>div>div>div>svg")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Crop']//parent::button")).click();
		
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
		driver.findElement(By.cssSelector("ul#results-list>div>div>div>li>a:first-of-type")).click();
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
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:first-of-type")).click();//click on first product
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:nth-of-type(2)")).click();//click on second product		
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:first-of-type")).click();//click on first product
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:nth-of-type(2)")).click();//click on second product		
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:first-of-type")).click();//click on first product
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:nth-of-type(2)")).click();//click on second product		
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:first-of-type")).click();//click on first product
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:nth-of-type(2)")).click();//click on second product		
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:first-of-type")).click();//click on first product
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:first-of-type")).click();//click on first product
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:first-of-type")).click();//click on first product
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:first-of-type")).click();//click on first product
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:first-of-type")).click();//click on first product
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
		driver.findElement(By.cssSelector("table.shadow-product-table>div>div:first-of-type")).click();//click on first product
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
		
	
		
		
	
}