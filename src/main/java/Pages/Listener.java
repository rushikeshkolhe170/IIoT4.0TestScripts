package Pages;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;

public class Listener extends utils implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(result.getName()+ " is Started...");
		//System.out.println("Test instance: " + result.getInstance());
	    //System.out.println("Driver reference: " + ((BaseTest)result.getInstance()).driver);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(result.getName()+ " is successfully Pass...");
	}
	
	private void saveScreenshotToDisk(byte[] screenshotBytes, String testName) {
		try {
			File dir = new File("Screenshots");
			if(!dir.exists()) {
				dir.mkdir();
			}
			File screenshotFile = new File(dir, testName + ".png");
			Files.write(screenshotFile.toPath(), screenshotBytes);
			//System.out.println("Screenshot saved to: " + screenshotFile.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getBrowserName(WebDriver driver) {
	    if (driver instanceof ChromeDriver) return "chrome";
	    if (driver instanceof FirefoxDriver) return "firefox";
	    if (driver instanceof EdgeDriver) return "edge";
	    if (driver instanceof SafariDriver) return "safari";
	    return "unknown_browser";
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println(result.getName()+ " is Failed...");
		WebDriver driver = null;
		
		try {
			Object testInstance = result.getInstance();
	        if (testInstance instanceof BaseTest) {
	            driver = ((BaseTest) testInstance).driver;
	        }
	        
	        if (driver == null) {
	            ITestContext context = result.getTestContext();
	            driver = (WebDriver) context.getAttribute("webDriver");
	        }
		
	        if(driver!=null) {
				String browsername = getBrowserName(driver);
				String screenshotName = "Failure_" + browsername + "_" + result.getName();
				byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				Allure.addAttachment(screenshotName, "image/png", new ByteArrayInputStream(screenshotBytes),".png");
				saveScreenshotToDisk(screenshotBytes, screenshotName);
	        }else {
	            System.err.println("Driver was null - cannot take screenshot");
	        }
		} catch (Exception e) {
			System.err.println("Failed to capture screenshot: " + e.getMessage());
	        e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(result.getName()+ " is Skipped...");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Test is Started...");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Test is Finish...");
	}

	
}
