package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class loginPage extends BaseTest {

	public loginPage(WebDriver driver) {

		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@formcontrolname='userEmail']")
	private WebElement username;

	@FindBy(xpath = "//input[@formcontrolname='password']")
	private WebElement password;

	@FindBy(xpath = "//button/span[text()='Login']")
	private WebElement login;

	@FindBy(xpath = "//mat-error[@id='mat-mdc-error-0']")
	private WebElement emailRequired;

	@FindBy(xpath = "//mat-error[@id='mat-mdc-error-1']")
	private WebElement passwordRequired;

	@FindBy(xpath = "//mat-error[@id='mat-mdc-error-2']")
	private WebElement invalidEmail;

	public String minimulPassLengthValidations() {
		username.clear();
		password.clear();
		password.sendKeys("abc");
		login.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(invalidEmail));
		String minLength = invalidEmail.getText();
		return minLength;
	}
	
	public String invalidEmailValidations() {
		username.clear();
		password.clear();
		username.sendKeys("abc123");
		login.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(invalidEmail));
		String invalidmail = invalidEmail.getText();
		return invalidmail;
	}

	public String emailrequiredValidations() {
		String emailval = emailRequired.getText();
		return emailval;
	}

	public String passrequiredValidations() {
		String passval = passwordRequired.getText();
		return passval;
	}

	public void login(String user, String pass) {

		username.clear();
		password.clear();
		username.sendKeys(user);
		password.sendKeys(pass);
		login.click();
	}

	public void unauthorizedLogin(String user, String pass) {

		username.clear();
		password.clear();
		username.sendKeys(user);
		password.sendKeys(pass);
		login.click();
	}

	public void loginWithoutCredential() {
		login.click();
	}
}