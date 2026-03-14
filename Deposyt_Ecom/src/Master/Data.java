package Master;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Data extends Public_Strings {

	public WebDriver driver;

	@Parameters({"bname","uname","pass", "role"})
	@BeforeClass
	public void invokebrowser(String bname, String uname, String pass, String role) throws Exception {

		switch(bname) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-save-password-bubble");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-password-manager-reauthentication");
			options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
			options.addArguments("use-fake-ui-for-media-stream");
			driver = new ChromeDriver(options);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		default:
			System.err.println("invalid browser name");
			break;
		}

		driver.manage().window().maximize();
		driver.get(url);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));

		WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();

		Thread.sleep(2000);
		String title = driver.getTitle();

		if(title.equals("deposyt.com")) {
			driver.findElement(By.xpath("//h1[text() = 'Role: "+role+"']//parent::div//parent::a")).click();
		}
	}

	public void AddContact(String Name,String lastName, String Number, String Mail) throws Exception 
	{
		WebDriver driver = DriverFactory.getDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		DeleteContact(Number, Mail);
 
		driver.findElement(By.cssSelector("div.contact-table-header>div>a.new-sml-insert")).click();// add customer button
		Thread.sleep(2000);
		driver.findElement(By.id("first_name")).sendKeys(Name);
		driver.findElement(By.id("last_name")).sendKeys(lastName);
		driver.findElement(By.id("email")).sendKeys(Mail);
		driver.findElement(By.id("cellphonefrommodal")).sendKeys(Number);
		Thread.sleep(1000);
		driver.findElement(By.id("addnewcustomernew")).click();

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		}
		catch(TimeoutException e) 
		{
			Assert.assertTrue(false,"Contact Not Added");
		}
	}


	public void DeleteContact(String Number, String Mail) throws InterruptedException	
	{
		WebDriver driver = DriverFactory.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	

 
		driver.navigate().to(Contacts);  

		//Checking for duplicate customers By number
		if(!Number.equals("")){
			driver.findElement(By.name("search")).sendKeys(Number);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@id='list']/descendant::p[@data-search-in=\"phone_number\"])[1]")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));

			int SearchedContacts = driver.findElements(By.cssSelector(".contacttablebody tr.admintable_row")).size();
			if(SearchedContacts>0)
			{
				driver.findElement(By.cssSelector("#at0-cell-actions_col-30>div>a")).click();
				driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-30\"]/div/div/a[6]")).click();
				driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
			}
		}

		//Checking for duplicate customers By number by Mail
		if(!Mail.equals("")){
			driver.findElement(By.name("search")).clear();
			Thread.sleep(1000);
			driver.findElement(By.name("search")).sendKeys(Mail);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@id='list']/descendant::p[@data-search-in= \"email\"])[1]")).click();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}

			int SearchedContacts = driver.findElements(By.cssSelector(".contacttablebody tr.admintable_row")).size();

			if(SearchedContacts>0)
			{
				driver.findElement(By.cssSelector("#at0-cell-actions_col-30>div>a")).click();
				driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-30\"]/div/div/a[6]")).click();
				driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
			}
		}
	}

	public void AddTag(String TagName) throws Exception 
	{
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;

		//Adding New Tag
		driver.navigate().to(TagManager);
		driver.findElement(By.name("searchtag")).sendKeys(TagName);
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#selectAll+span")).click();

		try {
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("button.deletebulktags ")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("deleteblkpopup")).click();
		} catch (Exception e) {
		}

		driver.findElement(By.cssSelector("div.selectize-control.custom-tag-input>div>input")).sendKeys(TagName);
		action.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.id("Add_tags")).click();
	}

	public void ReLogin(String Uname, String Pass) throws InterruptedException {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));

		driver.navigate().to(url);
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();

		//Login To another test account
		WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(Uname);
		driver.findElement(By.id("password")).sendKeys(Pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();

		Thread.sleep(2000);
		String title = driver.getTitle();

		if(title.equals("deposyt.com")){
			driver.findElement(By.cssSelector("#multiaccountpopup_closebtn+h1+a")).click();
		}
	}

	public Boolean ReceivedMail(String Subject) throws InterruptedException{
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		String pwindo = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://nadsoftdesign.com:2096/cpsess9421461281/3rdparty/roundcube/?_task=mail&_mbox=INBOX");
		driver.findElement(By.name("user")).sendKeys(WMLogin);
		action.sendKeys(Keys.TAB).sendKeys(WMPass).sendKeys(Keys.ENTER).build().perform();

		//Checking Mail-->
		driver.findElement(By.id("quicksearchbox")).sendKeys(Subject);
		action.sendKeys(Keys.ENTER).build().perform();

		Thread.sleep(2000);
		String msgcount1= driver.findElement(By.id("messagestack")).getText();
		System.out.println();
		System.out.println("Mail Confirmation Status ----->");
		System.out.println("	"+msgcount1);

		boolean MailStatus;
		try {
			Thread.sleep(1000);
			String subject = driver.findElement(By.cssSelector("tr.message.unread:first-of-type>td.subject>a>span")).getText();
			String recivedfrom =driver.findElement(By.cssSelector("tr.message.unread:first-of-type>td.fromto>span>span")).getText();
			String date = driver.findElement(By.cssSelector("tr.message.unread:first-of-type>td.date")).getText();

			System.out.println("	Mail Subject: " +subject);
			System.out.println("	Received From: "+recivedfrom);
			System.out.println("	Received On  : "+date);

			driver.findElement(By.id("listselectmenulink")).click();
			driver.findElement(By.id("rcmbtn144")).click();
			driver.findElement(By.id("rcmbtn116")).click();
			MailStatus = true;
		} catch(Exception e){
			MailStatus = false;
		}

		driver.findElement(By.id("rcmbtn106")).click();
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window(pwindo);
		return(MailStatus);
	}

	public void DeleteMail(String Subject) throws InterruptedException {
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		String pwindo = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(WMURL);
		driver.findElement(By.name("user")).sendKeys(WMLogin);
		action.sendKeys(Keys.TAB).sendKeys(WMPass).sendKeys(Keys.ENTER).build().perform();

		driver.findElement(By.id("quicksearchbox")).sendKeys(Subject);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);

		try {
			driver.findElement(By.id("listselectmenulink")).click();//Select All and Delete
			driver.findElement(By.id("rcmbtn144")).click();
			driver.findElement(By.id("rcmbtn116")).click();
		} catch(Exception e) {
		}

		driver.findElement(By.id("rcmbtn106")).click();
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window(pwindo);
	}

}
