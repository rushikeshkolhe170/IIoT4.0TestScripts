package LandingDashboard;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.BaseTest;
import Pages.LandingDashboardPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class SystemParameters extends BaseTest {

	LandingDashboardPage ldp;
	boolean data;
	
	@Test(description =  "Availability Progress bar and percentage Checking matching for live data", priority = 1)
	@Feature("Availability Progress bar and percentage Checking matching for live data")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyingSystemParameterChange() throws Exception {
		
		// Counting the filled progress bar as per the percentage..
		try {
			lp.login(prop.getProperty("username"), prop.getProperty("password"));
			Thread.sleep(3000);
			ldp = new LandingDashboardPage(driver);
		
			Map<String, String> detailBefore = ldp.departmentAttDetails();
			ldp.deptParamterChange();
			String msg = ldp.toastMessage();
			Assert.assertEquals(msg, "Manufacturing Parameters Updated Successfully");
			Thread.sleep(3000);
			Map<String, String> detailAfter = ldp.departmentAttDetails();
			boolean data = ldp.compareTwoStringHashMap(detailBefore, detailAfter);
			Assert.assertFalse(data);
		} catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
}
