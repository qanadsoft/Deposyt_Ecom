package Master;

public class Public_Strings {

	//Modules
	protected String url		= "https://appdev.deposyt.com/";
	//protected String url		= "https://app.release.deposyt.com/";
	//protected String url		= "https://preapp.deposyt.com/";
	//protected String url		= "https://app.deposyt.com/";
	
	protected String Ecom_url   = "https://store.app.dev.deposyt.com/a/";
	//protected String Ecom_url = "https://store.app.release.deposyt.com/a/";
	//protected String Ecom_url = "https://store.app.deposyt.com/a/";

	//Module Url's
	protected String 
	Dashboard			= url+"index.php?m=dashboard",
	Messages 			= url+"index.php?m=conversation", 
	CallLog				= url+"index.php?m=customers&d=calllogs",
	Contacts		    = url+"index.php?m=customers",
	Pipelines			= url+"index.php?m=pipelinesv2&d=pipelinedashboard",
	Calendar			= url+"index.php?m=appointments&d=tableview", 
	Lists				= url+"index.php?m=contactlist",
	Notes 	 			= url+"index.php?m=notesmain&d=allnotes",
	Tasks 				= url+"index.php?m=notesmain&d=alltasks&tab=Incomplete",
	Campaigns			= url+"index.php?m=campaigns",
	Forms	 			= url+"index.php?m=addformtemplates",
	Funnels 			= url+"index.php?m=landingpage",
	Domains 			= url+"index.php?m=landingpage&d=manage_domain",
	Triggers			= url+"index.php?m=triggers",
	All_Media 			= url+"index.php?m=allmedia",
	Videos	 			= url+"index.php?m=ffmpeg",
	Support				= url+"index.php?m=help&d=list",
	Settings			= url+"index.php?m=settings&d=useractions",
	MyProfile			= url+"index.php?m=employeedetails&d=myprofile",
	UserManagment		= url+"index.php?m=usersmgmt&d=list",
	partnerManagement 	= url+"index.php?m=usersmgmt&d=partnerlist",
	affiliateManagement = url+"index.php?m=usersmgmt&d=sub-affilites-list",
	AccessLevelPage 	= url+"index.php?m=usersmgmt&d=access_level_list",
	AppointmentTypes	= url+"index.php?m=appointments&d=eventtypes",
	Services			= url+"index.php?m=services",
	Courses 			= url+"index.php?m=courses",
	Availibility		= url+"index.php?m=appointments&d=eventavailability",
	TagManager			= url+"index.php?m=tags",
	SellLive			= url+"index.php?m=employeedetails&d=videocallappointments",
	Partners 			= url+"index.php?m=partners",
	BussinessProfile	= url+"index.php?m=settings&d=businessprofile",
	Media 				= url+"index.php?m=allmedia",
	EditProfile 		= url+"index.php?m=employeedetails&d=myprofile";
	
	//E-com URL's
	protected String
	Sales 			 = Ecom_url + "sales",
	Orders 			 = Ecom_url + "orders",
	Products 		 = Ecom_url + "products?",
	Customers 		 = Ecom_url + "customers?",
	Discounts 		 = Ecom_url + "discounts?",
	Gift_Cards 		 = Ecom_url + "gift-cards",
	Pricing 		 = Ecom_url + "pricing?",
	Virtual_Terminal = Ecom_url + "virtual-terminal",
	settings 	     = Ecom_url + "settings",
	invoices		 = Ecom_url + "invoices",
	dual_Pricing	 = Ecom_url + "settings/dual-pricing",
	Customer_Hub	 = "https://smartstore.deposyt.store/";

	//CRM Logins
	protected String Test02Login = "nadsoft.test02@gmail.com";
	protected String Test02Pass	 =	"n2Nafcqm";

	protected String Test99Login = "nadsoft.test99@gmail.com";
	protected String Test99Pass	 = "Nadsoft@12345678";

	protected String MyAccLogin	 = "rohan@nadsoftdesign.com";
	protected String MyAccPass 	 = "icruMOgk";

	protected String ATALogin 	 = "testself30@gmail.com";
	protected String ATAPass	 = "AutomationTest@123";

	//web mail login
	protected String WMURL		 = "https://gator3086.hostgator.com:2096/";
	protected String WMLogin 	 = "qa@nadsoftdesign.com";
	protected String WMPass		 = "Nadsoft@2024";

	//Local Paths
	//protected String Media_Path  		  = System.getProperty("user.dir")+"\\Sources\\Test Media\\" ; 	   //For windows
	protected static String Media_Path    = "/home/nadsoft/Deposyt_Ecom/Deposyt_Ecom/Sources/Test Media/"; //for Linux OS
	
}
