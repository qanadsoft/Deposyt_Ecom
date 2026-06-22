package Modules;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Master.Data;

public class Invoices extends Data {	
	
	@Test(priority = 1)
	public void Invoice_AllTab () throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.navigate().to(invoices);
		Thread.sleep(7000);
		
		//Check setting button and its navigation
		driver.findElement(By.xpath("//span[normalize-space()=\"Settings\"]//parent::span//parent::button")).click();
		String setting = driver.findElement(By.xpath("//h1[text()='Invoice Settings']")).getText();
		Assert.assertEquals(setting, "Invoice Settings","after click on Setting button it not redirected to Invoice Settings page successful");
		System.out.println("After click on Setting button it navigation to Invoice Settings page successfully\n");
		driver.findElement(By.cssSelector("div.custom-setting-region-tab>div>button")).click();	
		
		//Check template button and its navigation
		driver.findElement(By.xpath("//span[normalize-space()=\"Templates\"]//parent::span//parent::button")).click(); 
		String template = driver.findElement(By.xpath("//h1[text()='Invoice Templates']")).getText();
		Assert.assertEquals(template, "Invoice Templates","after click on Template button it not redirected to Invoice Templates page successful");
		System.out.println("After click on Template button it navigation to Invoice Templates page successfully\n");
		driver.findElement(By.cssSelector("div.breadcrumb.sticky>button")).click();
			
		//Check create invoices button and its navigation
		driver.findElement(By.xpath("(//span[normalize-space()=\"+ New Invoice\"]//parent::span//parent::button)[2]")).click();
		String createInvoice = driver.findElement(By.xpath("(//p[text()='Invoice Number #'])[1]")).getText();
		Assert.assertEquals(createInvoice, "Invoice Number #","after click on Create Invoice button it not redirected to Create Invoice page successful");
		System.out.println("After click on Create Invoice button it navigation to Create Invoice page successfully\n");
		driver.findElement(By.cssSelector("div.popup-container>div>div>div>button")).click();
		
		//Check Status (AZ) filtered  correct data
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
