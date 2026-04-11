package Master;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory extends Data{
	
	private static ThreadLocal <WebDriver> driver = new ThreadLocal <>();

	public static void setDriver(String browser) {
		WebDriver driverInstance = null;

		if (browser.equalsIgnoreCase("chrome")) {
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
			driverInstance = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driverInstance = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driverInstance = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}
		driver.set(driverInstance);
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void unload() {
		driver.remove();
	}
}
