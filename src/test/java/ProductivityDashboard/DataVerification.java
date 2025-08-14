package ProductivityDashboard;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.BaseTest;
import Pages.LandingDashboardPage;
import Pages.ProductivityDashboardPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class DataVerification extends BaseTest {

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
			Thread.sleep(3000);
			ldp = new LandingDashboardPage(driver);
		
			// checking if the all production data is available and correct..
			double availabiltyPercentage = ldp.availabilityPercentage();
			double performancePercentage = ldp.performancePercentage();
			double qualityPercentage = ldp.qualityPercentage();
			pdp = ldp.moveToProductivityDashboard();
			Thread.sleep(2000);
			Map<String, Double> metrics = pdp.dataCalculationAllLine();
			Assert.assertEquals(metrics.get("averageAval"), availabiltyPercentage);
			Assert.assertEquals(metrics.get("averagePerf"), performancePercentage);
			Assert.assertEquals(metrics.get("averageQual"), qualityPercentage);
		} catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
	
	@Test(description =  "OEE caculation with Availability, Performace and Quality", priority = 2)
	@Feature("OEE caculation with Availability, Performace and Quality")
	@Severity(SeverityLevel.CRITICAL)
	public void oeeCalculation() throws Exception {
		
		// checking the oee of production data..
		try {
			//ldp = new LandingDashboardPage(driver);
		
			pdp = ldp.moveToProductivityDashboard();
			Thread.sleep(2000);
			pdp.oeeCaculation();
		} catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
}
