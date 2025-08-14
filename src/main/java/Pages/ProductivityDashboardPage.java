package Pages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductivityDashboardPage extends BaseTest {

	public ProductivityDashboardPage(WebDriver driver) {

		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//p[text()='Availability']/../div/div/p)[1]")
	private WebElement availabilityPercentage;
	
	@FindBy(xpath = "(//p[text()='Performance']/../div/div/p)[1]")
	private WebElement performancePercentage;
	
	@FindBy(xpath = "(//p[text()='Quality']/../div/div/p)[1]")
	private WebElement qualityPercentage;
	
	@FindBy(xpath = "(//p[text()='Overall Equipment Efficiency']/../div/div/p)[1]")
	private WebElement oeePercentage;
	
	@FindBy(xpath = "//a[@data-tooltip='Go']")
	private WebElement submitGo;
	
	@FindBy(xpath = "//mat-select[@placeholder='Select Line']")
	private WebElement lineCellDropdow;
	
	@FindBy(xpath = "//mat-option/span")
	private List<WebElement> lineCellListDropdown;
	
	@FindBy(xpath = "//p[@class='bar-text ng-star-inserted']")
	private WebElement previousDayPercentage;
	
	@FindBy(xpath = "//h2[@class='workcenter-text']")
	private List<WebElement> cardsTitles;
	
	@FindBy(xpath = "//div[contains(@class,'active')]//h2[@class='workcenter-text']")
	private List<WebElement> activeCardsTitles;
	
	@FindBy(xpath = "//div[contains(@class,'active')]")
	private WebElement activeCard;
	
	@FindBy(xpath = "//mat-icon[text()='arrow_forward']")
	private WebElement nextArrow;
	
	int times2 = 0;
	
	public void lineWiseCardName() throws Exception {
		
		Thread.sleep(1000);
		lineCellDropdow.click();
		Thread.sleep(1000);
		visibilityOfAllElements(lineCellListDropdown);
		List<WebElement> options = lineCellListDropdown;
		int totalOption = options.size();
		for(WebElement att : options) {
			elementToBeClickable(att);
			clickByJavascriptMethod(att);
			submitGo.click();
			Thread.sleep(2000);
			//System.out.println(lineCellDropdow.getText());
			//System.out.println(cardsName1());
			cardsName1();
			times2++;
			if(times < totalOption) {
				lineCellDropdow.click();
				Thread.sleep(1000);
				visibilityOfAllElements(lineCellListDropdown);
				options = lineCellListDropdown;
			}	
		}	
	}
	
	public void cardsName1() throws Exception {
		Thread.sleep(1000);
		Set<String> printedTitles = new HashSet<String>();
	    boolean moreCards = true;
	    int count = 0;
	    while (moreCards) {
	    	List<WebElement> test = driver.findElements(
	                By.xpath("//div[contains(@class,'active')]//h2[@class='workcenter-text']")
	    	        );
	        for (WebElement card : test) {
	            String title = card.getText().trim();
	            Assert.assertTrue(card.isDisplayed());
	            if (!title.isEmpty() && printedTitles.add(title)) {
	                count++;
	            }
	        }
	        
	        if(nextArrow.isEnabled()) {
	            clickByJavascriptMethod(nextArrow);
	            Thread.sleep(1000);
	            if(driver.findElements(By.xpath("//div[contains(@class,'active')]//h2[@class='workcenter-text']")).get(0).getText().equalsIgnoreCase(test.get(0).getText())) {
	            	test = driver.findElements(By.xpath("//div[contains(@class,'active')]//h2[@class='workcenter-text']"));
	            }

	            if(count == cardsTitles.size()) break;
	        } else {
	            moreCards = false;
	        }
	    }
	    //return printedTitles;
	}
	
	public Set<String> cardsAvailabilityByName() throws Exception {
		Thread.sleep(2000);
		Set<String> printedTitles = new HashSet<String>();
	    boolean moreCards = true;
	    int count = 0;
	    while (moreCards) {
	        for (WebElement card : activeCardsTitles) {
	            String title = card.getText().trim();
	            Assert.assertTrue(card.isDisplayed());
	            if (!title.isEmpty() && printedTitles.add(title)) {
	                count++;
	            }
	        }
	        
	        if(nextArrow.isEnabled()) {
	            clickByJavascriptMethod(nextArrow);
	            Thread.sleep(1000);
	            if(driver.findElements(By.xpath("//div[contains(@class,'active')]//h2[@class='workcenter-text']")).get(0).getText().equalsIgnoreCase(activeCardsTitles.get(0).getText())) {
	            	activeCardsTitles = driver.findElements(By.xpath("//div[contains(@class,'active')]//h2[@class='workcenter-text']"));
	            }

	            if(count == cardsTitles.size()) break;
	        } else {
	            moreCards = false;
	        }
	    }
	    return printedTitles;
	}
	
	//double aval1, perf1, qual1, oee1 = 0.0;
	int times1 = 0;
	
	public void oeeCaculation() throws Exception {
		Thread.sleep(1000);
		lineCellDropdow.click();
		Thread.sleep(1000);
		visibilityOfAllElements(lineCellListDropdown);
		List<WebElement> options = lineCellListDropdown;
		int totalOption = options.size();
		for(WebElement att : options) {
			elementToBeClickable(att);
			clickByJavascriptMethod(att);
			submitGo.click();
			Thread.sleep(2000);
			double oeePresent = oeePercentage();
			double oee1 = ((availabilityPercentage()/100)*(performancePercentage()/100)*(qualityPercentage()/100))*100;
			double oee = Math.round(oee1 * 10.0) / 10.0;
			times1++;
			if(times1 < totalOption) {
				lineCellDropdow.click();
				Thread.sleep(1000);
				visibilityOfAllElements(lineCellListDropdown);
				options = lineCellListDropdown;
			}
			Assert.assertEquals(oeePresent, oee);
		}
	}
	
	double aval, perf, qual = 0.0;
	int times = 0;
	
	public Map<String, Double> dataCalculationAllLine() throws Exception {
		Thread.sleep(1000);
		lineCellDropdow.click();
		Thread.sleep(1000);
		visibilityOfAllElements(lineCellListDropdown);
		List<WebElement> options = lineCellListDropdown;
		int totalOption = options.size();
		for(WebElement att : options) {
			elementToBeClickable(att);
			clickByJavascriptMethod(att);
			submitGo.click();
			Thread.sleep(2000);
			aval = aval + availabilityPercentage();
			perf = perf + performancePercentage();
			qual = qual + qualityPercentage();
			times++;
			if(times < totalOption) {
				lineCellDropdow.click();
				Thread.sleep(1000);
				visibilityOfAllElements(lineCellListDropdown);
				options = lineCellListDropdown;
			}
		}
		Map<String, Double> result = new HashMap<String, Double>();
		result.put("averageAval", aval/totalOption);
		result.put("averagePerf", perf/totalOption);
		result.put("averageQual", qual/totalOption);
		return result;
	}
	
	public double availabilityPercentage() {
		//scrollToWebelement(availabilityPercentage);
		String Percentage = availabilityPercentage.getText();
		String[] SplitData = Percentage.split("%");
		double availabilityPercentage = Double.parseDouble(SplitData[0]);
		return availabilityPercentage;
	}
	
	public double performancePercentage() {
		//scrollToWebelement(performancePercentage);
		String Percentage = performancePercentage.getText();
		String[] SplitData = Percentage.split("%");
		double performancePercentage = Double.parseDouble(SplitData[0]);
		return performancePercentage;
	}

	public double qualityPercentage() {
		//scrollToWebelement(qualityPercentage);
		String Percentage = qualityPercentage.getText();
		String[] SplitData = Percentage.split("%");
		double qualityPercentage = Double.parseDouble(SplitData[0]);
		return qualityPercentage;
	}
	
	public double oeePercentage() {
		//scrollToWebelement(oeePercentage);
		String Percentage = oeePercentage.getText();
		String[] SplitData = Percentage.split("%");
		double qualityPercentage = Double.parseDouble(SplitData[0]);
		return qualityPercentage;
	}

}
