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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MasterClass {

	//Modules
	protected String url		= "https://app.deposyt.com/";
	//protected String url		= "https://app.release.deposyt.com/";
	//protected String url		= "https://preapp.deposyt.com/";
	protected String Ecom_url 	= "https://store.app.deposyt.com/a/";

	//Module Url's
	protected String 
	Dashboard	= url+"index.php?m=dashboard",
	Messages 	= url+"index.php?m=conversation",
	CallLog		= url+"index.php?m=customers&d=calllogs",
	Contacts	= url+"index.php?m=customers",
	Pipelines	= url+"index.php?m=pipelinesv2&d=pipelinedashboard",
	Calendar	= url+"index.php?m=appointments&d=tableview", 
	Lists		= url+"index.php?m=contactlist",
	Notes 	 	= url+"index.php?m=notesmain&d=allnotes",
	Tasks 		= url+"index.php?m=notesmain&d=alltasks&tab=Incomplete",
	Campaigns	= url+"index.php?m=campaigns",
	Forms	 	= url+"index.php?m=addformtemplates",
	Funnels 	= url+"index.php?m=landingpage",
	Domains 	= url+"index.php?m=landingpage&d=manage_domain",
	Triggers	= url+"index.php?m=triggers",
	Videos	 	= url+"index.php?m=ffmpeg",
	Support		= url+"index.php?m=help&d=list",
	Settings	= url+"index.php?m=settings&d=useractions",
	MyProfile	= url+"index.php?m=employeedetails&d=myprofile",
	UserManagment	 	= url+"index.php?m=usersmgmt&d=list",
	AccessLevelPage 	= url+"index.php?m=usersmgmt&d=access_level_list",
	AppointmentTypes 	= url+"index.php?m=appointments&d=eventtypes",
	Courses 	= url+"index.php?m=courses",
	Availibility		= url+"index.php?m=appointments&d=eventavailability",
	TagManager	= url+"index.php?m=tags",
	SellLive	= url+"index.php?m=employeedetails&d=videocallappointments",
	BussinessProfile = url+"index.php?m=settings&d=businessprofile";
	
	//Ecom URL's
	protected String
	Sales 		= Ecom_url+"sales",
	Orders 		= Ecom_url+"orders",
	Products 	= Ecom_url+"products?",
	Customers 	= Ecom_url+"customers?",
	Discounts 	= Ecom_url+"discounts?",
	Gift_Cards 	= Ecom_url+"gift-cards",
	Pricing 	= Ecom_url+"pricing?",
	Virtual_Terminal = Ecom_url+"virtual-terminal",
	settings 	= Ecom_url+"settings?";

	//Crons
	protected String TriggerExecutor = url+"index.php?m=triggerexecutor";
	protected String ListImportCron = url+"index.php?m=cronimportcustomers";

	//CRM Logins
	protected String Test02Login = "nadsoft.test02@gmail.com";
	protected String Test02Pass	 =	"n2Nafcqm";

	protected String Test99Login = "nadsoft.test99@gmail.com";
	protected String Test99Pass	 = "Nadsoft@12345678";

	protected String MyAccLogin	 = "rohan@nadsoftdesign.com";
	protected String MyAccPass 	 = "icruMOgk";

	protected String ATALogin 	 = "testself30@gmail.com";
	protected String ATAPass	 = "AutomationTest@123";

	protected String SALogin 	 = "admin";
	protected String SAPass		 = "LafC!TQgk2ghQv3WIi";
	protected String SATestAct	 = url+"index.php?m=companiesmgmt&d=switchcompany&id=1";

	//webmail login
	protected String WMURL		 = "https://gator3086.hostgator.com:2096/";
	protected String WMLogin 	 = "qa@nadsoftdesign.com";
	protected String WMPass		 = "Nadsoft@2024";

	//Local Paths
	protected String Media_Path = "C:\\Users\\Rohan kokare\\eclipse-workspace\\Deposyt_Ecom\\Sources\\Test Media\\" ;

	public WebDriver driver;


	@Parameters({"bname","uname","pass", "role"})
	@BeforeClass
	public void invokebrowser(String bname, String uname, String pass, String role) throws Exception 
	{

		switch(bname) 
		{
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

		if(title.equals("deposyt.com"))
		{
			driver.findElement(By.xpath("//h1[text() = 'Role: "+role+"']//parent::div//parent::a")).click();
		}
	}


	public void AddContact(String Name, String Number, String Mail) throws Exception {
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60)) ;
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));

		driver.navigate().to(Customers);

		//Checking for duplicate customers By number
		if(!Number.equals("")){
			driver.findElement(By.name("search")).sendKeys(Number);
			action.sendKeys(Keys.ENTER).build().perform();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));
			Thread.sleep(3000);
			action.sendKeys(Keys.ESCAPE).perform();

			try	{
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("#at0-cell-actions_col-0>div>a")).click();
				driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
				driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
				Thread.sleep(2000);
			}
			catch (Exception NoSuchElementExcepation) {
			}
		}

		//Checking for duplicate customers By number by Mail
		if(!Mail.equals("")) {
			driver.findElement(By.name("search")).clear();
			driver.findElement(By.name("search")).sendKeys(Mail);
			action.sendKeys(Keys.ENTER).build().perform();

			try {
				driver.findElement(By.cssSelector("#at0-cell-actions_col-0>div>a")).click();
				driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
				driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
				Thread.sleep(2000);
			}
			catch (Exception NoSuchElementExcepation) 
			{
			}
		}

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button
		Thread.sleep(2000);
		driver.findElement(By.id("first_name")).sendKeys(Name);	
		driver.findElement(By.id("cellphone")).sendKeys(Number);
		driver.findElement(By.id("email")).sendKeys(Mail);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();

		String success = null;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertify-logs")));
			success = driver.findElement(By.id("alertify-logs")).getText();
		}
		catch(Exception TimeOutException) {
		}
		System.out.println("	"+success);
	}

	public void DeleteContact(String Number, String Mail) throws Exception	{
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)) ;

		driver.navigate().to(Customers);
		//By number
		driver.findElement(By.name("search")).sendKeys(Number, Keys.ENTER);

		try	{
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
			Thread.sleep(2000);
		}
		catch (Exception NoSuchElementExcepation){
		}

		//by Mail
		driver.findElement(By.name("search")).clear();
		driver.findElement(By.name("search")).sendKeys(Mail);
		action.sendKeys(Keys.ENTER).build().perform();

		try {
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
			Thread.sleep(2000);
		}
		catch (Exception NoSuchElementExcepation) {
		}
	}

	public void AddTag(String TagName) throws Exception	{
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;

		//Adding New Tag
		driver.navigate().to(TagManager);
		driver.findElement(By.name("searchtag")).sendKeys(TagName);
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#selectAll+span")).click();

		try	{
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("button.deletebulktags ")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("deleteblkpopup")).click();
		}
		catch (Exception e) {
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

		//Login To anather test account
		WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(Uname);
		driver.findElement(By.id("password")).sendKeys(Pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();

		Thread.sleep(2000);
		String title = driver.getTitle();

		if(title.equals("deposyt.com"))
		{
			driver.findElement(By.cssSelector("#multiaccountpopup_closebtn+h1+a")).click();
		}
	}

	public Boolean ReceivedMail(String Subject) throws InterruptedException {
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
		System.out.println("Appointment Mail Confirmation Status ----->");
		System.out.println("	"+msgcount1);

		boolean MailStatus;
		try
		{
			Thread.sleep(1000);
			String subject = driver.findElement(By.cssSelector("tr.message.unread:first-of-type>td.subject>a>span")).getText();
			String recivedfrom = driver.findElement(By.cssSelector("tr.message.unread:first-of-type>td.fromto>span>span")).getText();
			String date = driver.findElement(By.cssSelector("tr.message.unread:first-of-type>td.date")).getText();

			System.out.println("	Mail Subject: " +subject);
			System.out.println("	Received From: "+recivedfrom);
			System.out.println("	Received On  : "+date);

			driver.findElement(By.id("listselectmenulink")).click();
			driver.findElement(By.id("rcmbtn143")).click();
			driver.findElement(By.xpath("//*[@id=\"rcmbtn115\"]")).click();
			MailStatus = true;
		}
		catch(Exception e) 
		{
			MailStatus = false;
		}

		driver.findElement(By.id("rcmbtn105")).click();
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window(pwindo);
		return(MailStatus);
	}

	public void DeleteMail(String Subject) throws InterruptedException
	{
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

		try
		{
			driver.findElement(By.id("listselectmenulink")).click();
			driver.findElement(By.id("rcmbtn143")).click();
			driver.findElement(By.xpath("//*[@id=\"rcmbtn115\"]")).click();
		}
		catch(Exception e) 
		{
		}

		driver.findElement(By.id("rcmbtn105")).click();
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window(pwindo);
	}
}
