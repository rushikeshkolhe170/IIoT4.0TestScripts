package ProductivityDashboard;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.BaseTest;
import Pages.LandingDashboardPage;
import Pages.ProductivityDashboardPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class CardsAvailability extends BaseTest {

	LandingDashboardPage ldp;
	ProductivityDashboardPage pdp;
	
	@Test(description =  "Productivity dashboard Data Verification with landing Dashboard", priority = 1)
	@Feature("Productivity dashboard Data Verification with landing Dashboard")
	@Severity(SeverityLevel.CRITICAL)
	public void productionDataAvailability() throws Exception {
		
		// checking the data and correctness of of it..
		try {
			lp.login(prop.getProperty("username"), prop.getProperty("password"));
			lp.toastMessage();
			Thread.sleep(1000);
			ldp = new LandingDashboardPage(driver);
		
			pdp = ldp.moveToProductivityDashboard();
			Thread.sleep(2000);
			pdp.lineWiseCardName();
			
		} catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
	
}
