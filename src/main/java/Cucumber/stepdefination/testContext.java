package Cucumber.stepdefination;

import org.openqa.selenium.WebDriver;

import Pages.loginPage;

// File use to initialize driver and interact with login page with no null driver..

public class testContext {

	private WebDriver driver;
	private loginPage lp;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public loginPage getLoginPage() {
		if(lp == null) {
			lp = new loginPage(driver);
		}
		return lp;
	}
}
