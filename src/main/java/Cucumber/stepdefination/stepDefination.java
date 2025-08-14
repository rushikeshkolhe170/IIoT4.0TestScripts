package Cucumber.stepdefination;

import org.testng.Assert;

import Pages.loginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefination {

	private final loginPage lp;
	
	public stepDefination(testContext context) {
		this.lp = context.getLoginPage();
	}
	
	@Given("I landed on the login page of IIoT")
	public void loginpage() {
		
		System.out.println("Login Page Open");
	}
	
	@When("I click on login without filling any details")
	public void loginWithoutCred() {
		
		lp.loginWithoutCredential();
	}
	
	@Then("{string} toast message is displayed")
	public void toastMessage(String string) {
		
		String msg = lp.toastMessage();
		//Assert.assertTrue(msg.equalsIgnoreCase(string));
		Assert.assertEquals(msg, string);
	}
	
	@And("{string} validation shows below the email field")
	public void emailRequiredValidation(String string) {
		
		String emailVal = lp.emailrequiredValidations();
		//Assert.assertTrue(emailVal.equalsIgnoreCase(string));
		Assert.assertEquals(emailVal, string);
	}
	
	@And("{string} validation shows below the password field")
	public void passRequiredValidation(String string) throws Exception {
		
		String passVal = lp.passrequiredValidations();
		//Assert.assertTrue(passVal.equalsIgnoreCase(string));
		Assert.assertEquals(passVal, string);
		Thread.sleep(2000);
	}
	
	@Given("Enter invalid email {string} and password {string} and verify {string} validation message")
	public void wrongEmail(String email, String pass, String string) throws Exception {
		
		String invalidMsg = lp.invalidEmailValidations();
		//Assert.assertTrue(invalidMsg.equalsIgnoreCase(string));
		Assert.assertEquals(invalidMsg, string);
		Thread.sleep(2000);
	}
}
