package Modules;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class Products extends Data {
	
	String Product_Name = "OneTime Product @#$!/" + UUID.randomUUID().toString().replace("-", "").substring(0, 15).toUpperCase(),
	Private_Name = "TestProduct " + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase(),
	Description = "test-Nadsoft " + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase() + "@#$!/",
	SKU = "SKU_No" + UUID.randomUUID().toString().replace("-", "").substring(0, 4).toUpperCase() + "@#$!/", Fileone = "file1.jpg";

	@Test(priority = 1)
	public void addProduct() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		JavascriptExecutor jse =  (JavascriptExecutor)driver;		
		
		//check that product name field should have placeholder
		//check that product name filed should accept different characters
		//check that 50 characters can be added in the product name	
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
