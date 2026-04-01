package Modules;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class Products extends Data {
	
	String Product_Name = "OneTime Product @#$!/" + UUID.randomUUID().toString().replace("- ", "").substring(0, 30).toUpperCase(),
	Private_Name = "TestProduct " + UUID.randomUUID().toString().replace("- ", "").substring(0, 8).toUpperCase(),
	Description = "test-Nadsoft " + UUID.randomUUID().toString().replace("- ", "").substring(0, 10).toUpperCase() + "@#$!/",
	SKU = "SKU_No" + UUID.randomUUID().toString().replace("- ", "").substring(0, 4).toUpperCase() + "@#$!/", OneTimeProductValue = "1",
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
		Inputplaceholder.sendKeys(Product_Name); 
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
		System.out.println("after click on Link Product To Appointment button is enabled successfully");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
