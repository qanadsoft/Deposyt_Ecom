package Master;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
			options.addArguments("use-fake-ui-for-media-stream");
			options.addArguments("use-fake-device-for-media-stream");
			options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
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

	public void AddContact(String Name, String Number, String Mail) throws Exception {
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));

		driver.navigate().to(Customers);

		//Checking for duplicate customers By number
		if(!Number.equals("")) {
			driver.findElement(By.name("search")).sendKeys(Number);
			action.sendKeys(Keys.ENTER).build().perform();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));
			Thread.sleep(3000);
			action.sendKeys(Keys.ESCAPE).perform();

			try {
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("#at0-cell-actions_col-30>div>a")).click();
				driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-30\"]/div/div/a[6]")).click();
				driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
				Thread.sleep(2000);
			} catch (Exception NoSuchElementExcepation) {
			}
		}

		//Checking for duplicate customers By number by Mail
		if(!Mail.equals("")){
			driver.findElement(By.name("search")).clear();
			driver.findElement(By.name("search")).sendKeys(Mail);
			action.sendKeys(Keys.ENTER).build().perform();

			try {
				driver.findElement(By.cssSelector("#at0-cell-actions_col-30>div>a")).click();
				driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-30\"]/div/div/a[6]")).click();
				driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
				Thread.sleep(2000);
			} catch (Exception NoSuchElementExcepation) {
			}
		}

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button
		Thread.sleep(2000);
		driver.findElement(By.id("first_name")).sendKeys(Name);	
		driver.findElement(By.id("cellphone")).sendKeys(Number);
		driver.findElement(By.id("email")).sendKeys(Mail);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertify-logs")));
		} catch(Exception TimeOutException) {
			Assert.assertTrue(false,"Contact Not Added");
		}
	}

	public void DeleteContact(String Number, String Mail) throws Exception {
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)) ;

		driver.navigate().to(Customers);

		//By number
		if(!Number.equals("")) {
			driver.findElement(By.name("search")).sendKeys(Number);
			action.sendKeys(Keys.ENTER).build().perform();

			try {
				driver.findElement(By.id("at0-cell-actions_col-30")).click();
				driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-30\"]/div/div/a[6]")).click();
				driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
				Thread.sleep(2000);
			} catch (Exception NoSuchElementExcepation) {
			}
		}

		//by Mail
		if(!Mail.equals("")) {
			driver.findElement(By.name("search")).clear();
			driver.findElement(By.name("search")).sendKeys(Mail);
			action.sendKeys(Keys.ENTER).build().perform();

			try
			{
				driver.findElement(By.id("at0-cell-actions_col-30")).click();
				driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-30\"]/div/div/a[6]")).click();
				driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
				Thread.sleep(2000);
			} catch (Exception NoSuchElementExcepation) {
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
