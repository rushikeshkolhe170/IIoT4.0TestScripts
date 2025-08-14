package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.google.common.collect.ImmutableMap;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class BaseTest extends utils{
	
	ChromeOptions options;
	FirefoxOptions fo;
	EdgeOptions eo;
	public static File downloadDir;
	protected Properties prop;
	public loginPage lp;
	
	public BaseTest() {
        PageFactory.initElements(driver, this); // driver is null here
    }
	
	@BeforeSuite
	public void setAllureEnvironment() {
		allureEnvironmentWriter(
				ImmutableMap.<String, String>builder()
				.put("Browser", "Chrome, Firefox & Edge")
				.put("URL", "http://13.233.216.164/login")
				.build(), System.getProperty("user.dir")+"/allure-results/");
	}
	
	@BeforeSuite
	public void clearAllureResult() {
		String folderPath = System.getProperty("user.dir") + File.separator + "allure-results";
		File folder = new File(folderPath);
		if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
                System.out.println("All files deleted from: " + folderPath);
            }
        } else {
            System.out.println("Folder does not exist: " + folderPath);
        }
    }

	@BeforeClass
	@Parameters("browserName")
	public void browser(String browserName) throws Exception {
	
		//Driver setting...
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Pages//globalData.properties");
		prop.load(fis);
		//String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		// File download location..
		String downloadFilepath = System.getProperty("user.dir")+ File.separator + "Downloads";
		//downloadDir = new File(downloadFilepath + "/Downloads");
		downloadDir = new File(downloadFilepath);
		if (!downloadDir.exists()) downloadDir.mkdir();
		
		if(browserName.contains("chrome"))
		{
			options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", downloadFilepath);
	        prefs.put("download.prompt_for_download", false);
	        prefs.put("profile.default_content_settings.popups", 0);
	        prefs.put("safebrowsing.enabled", true);
	        prefs.put("safebrowsing.disable_download_protection", true);
	        options.setExperimentalOption("prefs", prefs);
			options.addArguments("--remote-allow-origins=*");
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			fo = new FirefoxOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", downloadFilepath);
	        prefs.put("download.prompt_for_download", false);
	        prefs.put("profile.default_content_settings.popups", 0);
	        prefs.put("safebrowsing.enabled", true);
	        options.setExperimentalOption("prefs", prefs);
			fo.addArguments("--remote-allow-origin=*");
			driver = new FirefoxDriver(fo);
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			eo = new EdgeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", downloadFilepath);
	        prefs.put("download.prompt_for_download", false);
	        prefs.put("profile.default_content_settings.popups", 0);
	        prefs.put("safebrowsing.enabled", true);
	        options.setExperimentalOption("prefs", prefs);
			eo.addArguments("--remote-allow-origin=*");
			driver = new EdgeDriver(eo);
		}
		else if(browserName.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver();
		}else {
			throw new Exception("Unsupported Browser " + browserName);
		}
		//driver = tdriver.get();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://13.233.216.164/login");
		lp = new loginPage(driver);
	}
	
	// Below method used so that driver will not remain null during capturing the screenshot (JAVA + TESTNG)..
	@BeforeMethod
	public void setup(ITestContext context) {
		try {
			context.setAttribute("webDriver", driver);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void closing() {
		
		//Quitting all the process...
		try {
			Thread.sleep(1000);
			driver.quit();
		}catch (Exception e) {
			System.err.println("Error while closing driver: " + e.getMessage());
		}
		
	}
	
	public String getProperty(String key) {
		
		return prop.getProperty(key);
	}
}
