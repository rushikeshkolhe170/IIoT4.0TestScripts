package LandingDashboard;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.BaseTest;
import Pages.LandingDashboardPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class DepartmentLineDetails extends BaseTest{

	LandingDashboardPage ldp;
	
	@Test(description =  "Machine, Gateways and Maintenance Cards and count", priority = 1)
	@Feature("Landing Dashboard Machine, Gateways and Maintenance Cards")
	@Severity(SeverityLevel.CRITICAL)
	public void deptLineCardsDetails() throws Exception {
		
		try {
			lp.login(prop.getProperty("username"), prop.getProperty("password"));
			lp.toastMessage();
			ldp = new LandingDashboardPage(driver);
			// Verify if the Department and Line details cards are display on the screen or not
			ldp.detailsCardAvailability();
			// Bringing the department and line details in view
			ldp.deptLineDetailsInView();
			// Checking the attributes and there values for the department panel
			ldp.departmentAttributesWithValues();
			// Checking the attributes and there values for the line panels
			ldp.lineAttributesWithValues();
		}catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
}
