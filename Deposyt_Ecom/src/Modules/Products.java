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
	SKU = "SKU_No" + UUID.randomUUID().toString().replace("- ", "").substring(0, 4).toUpperCase() + "@#$!/", 
	File1 = "file1.jpg", File2 = "sample.bmp", File3 = "sample.tiff", File4 = "10mb.jpg", File5 = "sample.jpe", File6 = "file3.jpeg", File7 = "file7.jpg", File8 = "file4.png", Attachment = "Jira_Guide.pdf",
	Fileone = "file1.jpg", Filetwo = "file2.png", Filethree = "file3.jpeg", Filefour = "file4.png", Filefive = "file5.png",
	Filesix = "file6.jpg", Fileseven = "file7.jpg",Fileeight = "file8.jpg", Filenine = "file9.jpg", Fileten = "sample.mp4";
	
	@Test(priority = 1)
	public void addProduct() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;	
		Actions actions = new Actions(driver);
		
		//check that product name field should have placeholder
		driver.navigate().to(Products);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		driver.navigate().refresh();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-page-ui-div button.newproductbutton:nth-of-type(2)"))).click();
		
		WebElement Inputplaceholder = driver.findElement(By.name("productDetails.productName"));
		Inputplaceholder.sendKeys(Product_Name); // Enter the product name in the input field 
		String actualPlaceholder = Inputplaceholder.getAttribute("placeholder"); // Get the placeholder attribute		        
		String expectedPlaceholder = "Enter Product Name"; // Expected placeholder text
		Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "product Name placeholder text mismatch");
		System.out.println("Product Name Placeholder Matches");
		Thread.sleep(1000);
		
		//check that title is Product Name		
		String actualTitle = driver.findElement(By.xpath("//label[normalize-space()=\"Product Name\"]")).getText(); // Get the title text
		String expectedTitle = "Product Name"; // Expected title text
		Assert.assertEquals(actualTitle, expectedTitle, "Product Name title text mismatch");
		System.out.println("Product Name Title Matches\n");
		Thread.sleep(1000);
		
		//check that  private name  field should have placeholder  
		WebElement Inputplaceholder1 = driver.findElement(By.name("productDetails.privateName"));
		Inputplaceholder1.sendKeys(Private_Name); // Enter the private name in the input field 
		String actualPlaceholder1 = Inputplaceholder1.getAttribute("placeholder"); // Get the placeholder attribute		        
		String expectedPlaceholder1 = "Type Private Name (Optional)"; // Expected placeholder text
		Assert.assertEquals(actualPlaceholder1, expectedPlaceholder1, "private Name placeholder text mismatch");
		System.out.println("private Name Placeholder Matches");
		Thread.sleep(1000);
				
		//check that title is private name 
		String actualTitle1 = driver.findElement(By.xpath("//label[normalize-space()=\"Private Name\"]")).getText(); // Get the title text
		String expectedTitle1 = "Private Name"; // Expected title text
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
		Inputplaceholder2.sendKeys(Description); // Enter the product name in the input field 
		String actualPlaceholder2 = Inputplaceholder2.getAttribute("placeholder"); // Get the placeholder attribute		        
		String expectedPlaceholder2 = "Enter description"; // Expected placeholder text
		Assert.assertEquals(actualPlaceholder2, expectedPlaceholder2, "product description placeholder text mismatch");
		System.out.println("Product description Placeholder Matches");
		Thread.sleep(1000);
				
		//check that title is product description
		String actualTitle2 = driver.findElement(By.xpath("//label[normalize-space()=\"Description\"]")).getText(); // Get the title text
		String expectedTitle2 = "Description"; // Expected title text
		Assert.assertEquals(actualTitle2, expectedTitle2, "Product Name title text mismatch");
		System.out.println("Product description Title Matches\n");
		Thread.sleep(1000);
				
		//check that  SKU field should have placeholder
		WebElement Inputplaceholder3 = driver.findElement(By.name("productDetails.sku"));
		Inputplaceholder3.sendKeys(SKU); // Enter the SKU name in the input field 
		String actualPlaceholder3 = Inputplaceholder3.getAttribute("placeholder"); // Get the placeholder attribute		        
		String expectedPlaceholder3 = "Enter SKU (Optional)"; // Expected placeholder text
		Assert.assertEquals(actualPlaceholder3, expectedPlaceholder3, "SKU Name placeholder text mismatch");
		System.out.println("SKU Placeholder Matches");
		Thread.sleep(1000);
		
		//check that title is SKU		
		String actualTitle3 = driver.findElement(By.xpath("//label[normalize-space()=\"SKU\"]")).getText(); // Get the title text
		String expectedTitle3 = "SKU"; // Expected title text
		Assert.assertEquals(actualTitle3, expectedTitle3, "SKU Name title text mismatch");
		System.out.println("SKU Title Matches\n");
		Thread.sleep(1000);
		
		//check that title is Media
		String actualTitle4 = driver.findElement(By.xpath("(//span[contains(normalize-space(), 'Media')])[1]")).getText().replaceAll("\\s+", "");
		String expectedTitle4 = "Media*"; // Expected title text
		Assert.assertEquals(actualTitle4, expectedTitle4, "Media title text mismatch");
		System.out.println("Media Title Matches\n");
		
		//check that this msg can be seen "Upload up to 9 images and video"		
		String actualTitle5 = driver.findElement(By.xpath("//span[text()='Upload up to 9 images or 1 video and 8 images.']")).getText(); // Get the title text
		String expectedTitle5 = "Upload up to 9 images or 1 video and 8 images.";
		Assert.assertEquals(actualTitle5, expectedTitle5, "Media Label Headline text mismatch");
		
		//check that tis msg also can be seen "Must upload at least one image"
		String actualTitle6 = driver.findElement(By.xpath("//span[text()='Must upload at least one image*']")).getText(); // Get the title text
		String expectedTitle6 = "Must upload at least one image*";
		Assert.assertEquals(actualTitle6, expectedTitle6, "Media Label Headline text mismatch");
		
		//check that this msg can be seen "Images should have a 1:1 square aspect ratio"
		String actualTitle7 = driver.findElement(By.xpath("//span[text()='Images should have a 1:1 square aspect ratio']")).getText(); // Get the title text
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
		WebElement uploadImagee = driver.findElement(By.cssSelector("input[type=\"file\"]"));
		jse.executeScript("arguments[0].scrollIntoView(true);", uploadImagee);
		
		Thread.sleep(3000);
		String[] filess1 = {Media_Path + Fileone, Media_Path + Filetwo, Media_Path + Filethree, Media_Path + Filefour, Media_Path + Filefive, Media_Path + Filesix, Media_Path + Fileseven, 
				Media_Path + Fileeight, Media_Path + Filenine};
		String allFiless = String.join("\n", filess1);
		driver.findElement(By.cssSelector("[type='file']")).sendKeys(allFiless);
		System.out.println("\nUser able to upload 9 images in upload media section and crop functionality also works fine for all images");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
