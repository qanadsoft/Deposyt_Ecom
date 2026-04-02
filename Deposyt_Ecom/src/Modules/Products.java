package Modules;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

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

public class Products extends Data {
	
	String Product_Names = "OneTime Product @#$!/" + UUID.randomUUID().toString().replace("- ", "").substring(0, 30).toUpperCase(),
	Product_Name = "OneTime Product " + UUID.randomUUID().toString().replace("- ", "").substring(0, 4).toUpperCase(),
	Private_Name = "TestProduct " + UUID.randomUUID().toString().replace("- ", "").substring(0, 8).toUpperCase(),
	Description = "test-Nadsoft " + UUID.randomUUID().toString().replace("- ", "").substring(0, 10).toUpperCase() + "@#$!/",
	SKU = "SKU_No" + UUID.randomUUID().toString().replace("- ", "").substring(0, 4).toUpperCase() + "@#$!/", OneTimeProductValue = "10", OneTimePurchaseSalePrice = "5",
	File1 = "file1.jpg", File2 = "sample.bmp", File3 = "sample.tiff", File4 = "10mb.jpg", File5 = "sample.jpe", File6 = "file3.jpeg", File7 = "file7.jpg", File8 = "file4.png", Attachment = "Jira_Guide.pdf",
	Fileone = "file1.jpg", Filetwo = "file2.png", Filethree = "file3.jpeg", Filefour = "file4.png", Filefive = "file5.png",
	Filesix = "file6.jpg", Fileseven = "file7.jpg",Fileeight = "file8.jpg", Filenine = "file9.jpg", Fileten = "sample.mp4";
	
	@Test(priority = 1)
	public void addProduct() throws InterruptedException {
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
			System.out.println("The current URL does not contain 'orders'. Current URL is: " + currentUrl);
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
		WebElement hideProductButtonn = driver.findElement(By.xpath("//p[text() = 'Hide Products from Store']//following-sibling::button//span"));
		jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", hideProductButtonn);
		Thread.sleep(1000);
		jse.executeScript("arguments[0].click();", hideProductButtonn);
		String hideProductButtonState11 = hideProductButton.getAttribute("data-state");
		Assert.assertEquals(hideProductButtonState11, "checked", "after click on Hide product from store Product is not enabled");
		System.out.println("after click on Hide Product From Store button is enabled successfully");
		
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
		WebElement Description = driver.findElement(By.name("productDetails.productDescription"));
		String actualDescription = Description.getAttribute("value");
		Assert.assertEquals(actualDescription, Description, "Saved description is not displayed in product details page");
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
		WebElement productNameOndefCheckout = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>span"));
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
		WebElement productNameOnprodCheckout = driver.findElement(By.cssSelector("#no-tailwindcss-base>section>div>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div>span"));
		String actualProductNameOnprodCheckout = productNameOnprodCheckout.getText().trim();
		Assert.assertEquals(actualProductNameOnprodCheckout, Product_Name, "Saved product name is not displayed on Product checkout page");
		System.out.println("Saved product name is displayed successfully on Product checkout page");
		driver.close();
		driver.switchTo().window(originalTab);
		
		//Verify linked service is accessible after product purchase
		driver.navigate().to(Services);
		Thread.sleep(3000);
		driver.findElement(By.name("search")).sendKeys(Product_Name,Keys.ENTER);
		Thread.sleep(15000);
		String serviceName = driver.findElement(By.cssSelector("span.servicenamespan")).getText();
		Assert.assertEquals(serviceName, Product_Name, "Service not created with product name");
		System.out.println("\nService found in connect product listing");
		
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
		System.out.println("\nProduct found in connect product listing");
		
		//Verify linked appointment is accessible after product purchase
		driver.navigate().to(AppointmentTypes);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".blocktitlesection div #eventtypebutton")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.rounded-15>div:nth-child(1)>div")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("payment_paid")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("select2-state-iy-container")).click();
		driver.findElement(By.name("//input[@placeholder=\"Search Product ...\"]")).sendKeys(Product_Name);
		Thread.sleep(2000);
		List<WebElement> connectedProducts1 = driver.findElements(By.cssSelector("li.select2-results__option--highlighted"));
		boolean productFound1 = false;
		for (WebElement product : connectedProducts1) {
			if (product.getText().trim().equals(Product_Name)) {
				productFound1 = true;
				break;
			}		
		}
		
		Assert.assertTrue(productFound1, "Product not found in connect product listing");
		System.out.println("Product found in connect Appointment product listing page");
		
		//check that saved product name can be seen in  storefront
		StoreFront();
		String productNameOnStoreFront = driver.findElement(By.xpath("((//div[contains(@data-testid,'product-wrapper')])[1]//div[2]//p)[1]")).getText().trim();
		Assert.assertEquals(productNameOnStoreFront, Product_Name, "Saved product name is not displayed on storefront");
		System.out.println("Saved product name is displayed successfully on storefront");		
	}
	
	
}
