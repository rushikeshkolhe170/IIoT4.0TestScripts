package LoginValidation;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageValidation extends BaseTest {

	@Test(description =  "Required validation for Email and Password", priority = 1)
	@Feature("Required validation for Email and Password")
	@Severity(SeverityLevel.CRITICAL)
	public void requiredValidation() throws Exception {
		
		try {
			// Verifying the cards title and checking if count is available or not..
			lp.loginWithoutCredential();
			String msg = lp.toastMessage();
			Assert.assertEquals(msg, "Please ensure that the email and password entered are valid.");
			String emailVal = lp.emailrequiredValidations();
			Assert.assertEquals(emailVal, "Email ID is required!");
			String passVal = lp.passrequiredValidations();
			Assert.assertEquals(passVal, "Password is required!");
		}catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
	
	@Test(description =  "Invalid email validation", priority = 2)
	@Feature("Invalid email validation")
	@Severity(SeverityLevel.CRITICAL)
	public void incorrectEmailValidation() throws Exception {
		try {
			driver.navigate().refresh();
			String invalidMsg = lp.invalidEmailValidations();
			Assert.assertEquals(invalidMsg, "Invalid email format!");
		}catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
	
	@Test(description =  "Unauthorized user validation", priority = 3)
	@Feature("Unauthorized user validation")
	@Severity(SeverityLevel.CRITICAL)
	public void unauthorizedValidation() throws Exception {
		try {
			driver.navigate().refresh();
			lp.unauthorizedLogin("abc123@gmail.com", "Test@123");
			String toastMsg = lp.toastMessage();
			Assert.assertEquals(toastMsg, "You're not authenticated User");
		}catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
	
	@Test(description =  "Minimum password length validation", priority = 4)
	@Feature("Minimum password length validation")
	@Severity(SeverityLevel.CRITICAL)
	public void minimumLengthPassword() throws Exception {
		try {
			driver.navigate().refresh();
			String lengthVal = lp.minimulPassLengthValidations();
			Assert.assertEquals(lengthVal, "Password must be at least 6 characters long.");
		}catch (Exception e) {
			Assert.fail("Test failed due to: " + e.getMessage(), e);
		}
	}
}
