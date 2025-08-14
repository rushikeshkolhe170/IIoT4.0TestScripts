package LandingDashboard;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.BaseTest;
import Pages.LandingDashboardPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class CardsandValues extends BaseTest{

	LandingDashboardPage ldp;
	
	@Test(description =  "Machine, Gateways and Maintenance Cards and count", priority = 1)
	@Feature("Landing Dashboard Machine, Gateways and Maintenance Cards")
	@Severity(SeverityLevel.CRITICAL)
	public void cardsAndCounts() throws Exception {
		
		// Verifying the cards title and checking if count is available or not..
		//lp.login("priya.sharma@technosofteng.com", "Test@123");
		try {
			lp.login(prop.getProperty("username"), prop.getProperty("password"));
			lp.toastMessage();
			ldp = new LandingDashboardPage(driver);
			ldp.cardsAndValues();
		}catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
}
