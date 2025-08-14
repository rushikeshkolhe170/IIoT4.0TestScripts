package Cucumber.stepdefination;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

// Created to get the context from testContext file..
// Created to start the browser using Before tag from cucumber which will get executed before every scenario gets execute..
// Created to terminate the browser using After tag from cucumber which will terminate the browser after executing scenario..

public class hook {

	private final testContext context;
	
	public hook(testContext context) {
		this.context = context;
	}
	
	@Before
	public void setup() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--ignore-certificate-errors");
		WebDriver driver = new ChromeDriver(op);
		driver.manage().window().maximize();
		driver.get("http://13.233.216.164/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		context.setDriver(driver);
	}
	
	@After
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			final byte[] file = ((TakesScreenshot)context.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(file, "image/png", "Screenshot on Failure");
		}
		context.getDriver().quit();
	}
}
