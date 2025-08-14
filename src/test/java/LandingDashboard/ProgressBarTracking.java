package LandingDashboard;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.BaseTest;
import Pages.LandingDashboardPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ProgressBarTracking extends BaseTest {

	LandingDashboardPage ldp;
	
	@Test(description =  "Availability Progress bar and percentage Checking matching for live data", priority = 1)
	@Feature("Availability Progress bar and percentage Checking matching for live data")
	@Severity(SeverityLevel.CRITICAL)
	public void progressBarTracking() throws Exception {
		
		// Counting the filled progress bar as per the percentage..
		try {
			lp.login(prop.getProperty("username"), prop.getProperty("password"));
			lp.toastMessage();
			Thread.sleep(1000);
			ldp = new LandingDashboardPage(driver);
		
			// Availability percentage and progress bar match..
			int totalFilledBlocksAVl = ldp.availabilityProgressBarCounts();
			double availabilityPercent = ldp.availabilityPercentage();
			ldp.progressBarCheck(availabilityPercent, totalFilledBlocksAVl);
		
			// Performance percentage and progress bar match..
			int totalFilledBlocksPER = ldp.performanceProgressBarCounts();
			double performancePercent = ldp.performancePercentage();
			ldp.progressBarCheck(performancePercent, totalFilledBlocksPER);
		
			// Quality percentage and progress bar match..
			int totalFilledBlocksQUA = ldp.qualityProgressBarCounts();
			double qualityPercent = ldp.qualityPercentage();
			ldp.progressBarCheck(qualityPercent, totalFilledBlocksQUA);
		} catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
}
