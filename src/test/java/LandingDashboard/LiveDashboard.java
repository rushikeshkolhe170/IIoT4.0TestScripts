package LandingDashboard;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.BaseTest;
import Pages.LandingDashboardPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LiveDashboard extends BaseTest{

	LandingDashboardPage ldp;
	
	@Test(description =  "Live Dashboard with the attribute information", priority = 1)
	@Feature("Live Dashboard with the attribute information")
	@Severity(SeverityLevel.CRITICAL)
	public void liveDataAvalability() throws Exception {
		
		// Verifying if the live data is on or not..
		try {
			lp.login(prop.getProperty("username"), prop.getProperty("password"));
			lp.toastMessage();
			ldp = new LandingDashboardPage(driver);
			ldp.liveDataUpdate();
		}catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
}
