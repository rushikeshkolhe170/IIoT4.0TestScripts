package LandingDashboard;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.BaseTest;
import Pages.LandingDashboardPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AllProductionData extends BaseTest {

	LandingDashboardPage ldp;
	
	@Test(description =  "Availability of All Production data table on the screen", priority = 1)
	@Feature("Availability of All Production data table on the screen")
	@Severity(SeverityLevel.CRITICAL)
	public void productionDataAvailability() throws Exception {
		
		// checking the availability of production data..
		try {
			lp.login(prop.getProperty("username"), prop.getProperty("password"));
			lp.toastMessage();
			Thread.sleep(1000);
			ldp = new LandingDashboardPage(driver);
		
			// checking if the all production data is available or not..
			ldp.availabilityOfAllProductionData();
		} catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
	
	@Test(description =  "Title and Columns header checking", priority = 2)
	@Feature("Title and Columns header checking")
	@Severity(SeverityLevel.NORMAL)
	public void titleAndColumnHeaders() throws Exception {
		
		// checking the title and column header text of production date table..
		try {
		
			// checking if the title and columns headers are correct are not..
			ldp.titleAndColumnWithTextVerification();
		} catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
	
	@Test(description =  "Opening production details", priority = 3)
	@Feature("Opening production details")
	@Severity(SeverityLevel.NORMAL)
	public void openingProductionDetails() throws Exception {
		
		// Opening the details to check click action on all Plant, Department and Line..
		try {
		
			// checking if the title and columns headers are correct are not..
			ldp.openingDetails();
		} catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
	
	@Test(description =  "Caculation and Production state verification with Plant, Department and Line", priority = 4)
	@Feature("Caculation and Production state verification with Plant, Department and Line")
	@Severity(SeverityLevel.CRITICAL)
	public void calculationDetails() throws Exception {
		
		// calculation of all the data on production table and production state..
		try {
		
			// verifying the calculation on the all production table..
			ldp.calculationOnTable();
		} catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
	
	@Test(description =  "Production data download and file download verification", priority = 5)
	@Feature("Production data download and file download verification")
	@Severity(SeverityLevel.CRITICAL)
	public void productionDataDownload() throws Exception {
		
		// downloading of excel data for the production table..
		try {
		
			// downloading the production data using download button..
			ldp.productionDataDownload();
		} catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
}
