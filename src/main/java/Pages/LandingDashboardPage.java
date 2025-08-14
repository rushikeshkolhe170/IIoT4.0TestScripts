package Pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LandingDashboardPage extends BaseTest {
	
	public LandingDashboardPage(WebDriver driver) {

		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h6[@class='card-title-main']")
	private List<WebElement> cardsTitle;

	@FindBy(xpath = "//p[@class='card-value']")
	private WebElement activeCardsValue;

	@FindBy(xpath = "//p[@class='card-value-inactive']")
	private WebElement inactiveCardsValue;

	@FindBy(xpath = "//h6[@class='card-title-main']/../p")
	private WebElement cardValues;

	@FindBy(xpath = "(//button[text()='No'])[2]")
	private WebElement logoutConfirmNo;

	@FindBy(css = ".main-head-active")
	private WebElement dashbordTitle;

	@FindBy(xpath = "(//p[@class='card-sub-head'])[1]")
	private WebElement availabilityPercentage;

	@FindBy(xpath = "(//p[@class='card-sub-head'])[2]")
	private WebElement performancePercentage;

	@FindBy(xpath = "(//p[@class='card-sub-head'])[3]")
	private WebElement qualityPercentage;

	@FindBy(xpath = "(//div[@class='range'])[1]")
	private WebElement availabilityProgressBar;

	@FindBy(xpath = "(//div[@class='range'])[2]")
	private WebElement performanceProgressBar;

	@FindBy(xpath = "(//div[@class='range'])[3]")
	private WebElement qualityProgressBar;

	@FindBy(css = "h5.card-title")
	private WebElement detailsCardTitles;

	@FindBy(xpath = "//h5[contains(@class,'responsive-title line-title')]")
	private List<WebElement> lineCardTitles;

	@FindBy(xpath = "//h5[contains(@class,'responsive-title line-title')]")
	private WebElement lCardTitles;

	@FindBy(css = "p.card-subtitle")
	private WebElement detailsCardSubTitles;

	@FindBy(xpath = "//div[@class='col-md-2 column-print']/div/div[1]/div/h5")
	private List<WebElement> departmentNames;

	@FindBy(xpath = "//p[@class='dept-card-head']")
	private List<WebElement> departmentAttributes;

	@FindBy(css = "p.dept-card-head")
	private WebElement deptAttributes;

	@FindBy(css = "p.dept-card-sub-head")
	private WebElement deptAttributesValues;

	@FindBy(xpath = "//div[@class='col-md-2 column-print']/div/div[1]/div/h5")
	private WebElement deptNames;
	
	@FindBy(css = ".table-responsive")
	private WebElement allProductionTable;
	
	@FindBy(xpath = "//p[contains(@class,'tblhead')]")
	private WebElement titleOfProductionTable;
	
	@FindBy(xpath = "//th[contains(@class,'table-cell')]")
	private List<WebElement> columnHeaders;
	
	@FindBy(xpath = "//img[@alt='Plant']")
	private List<WebElement> plantsInTables;
	
	@FindBy(xpath = "//img[@alt='Department']")
	private List<WebElement> departmentsInTables;
	
	@FindBy(xpath = "//img[@alt='Line']")
	private List<WebElement> linesInTables;
	
	@FindBy(xpath = "//td[@Style='padding-left: 80px;']")
	private List<WebElement> workcenterFromOpenLine;
	
	@FindBy(xpath = "//img[@class='img-cell']")
	private WebElement downloadIcon;
	
	@FindBy(css = ".mdc-checkbox__native-control")
	private List<WebElement> allSystemParameters;
	
	@FindBy(css = ".mdc-checkbox--selected")
	private List<WebElement> selectedSystemParameters;
	
	@FindBy(xpath = " //button[text()=' Submit '] ")
	private WebElement parameterSubmit;
	
	@FindBy(xpath = "//a[@routerlink='/productivity']")
	private WebElement productivityDashboard;
	
	public ProductivityDashboardPage moveToProductivityDashboard() {
		productivityDashboard.click();
		return new ProductivityDashboardPage(driver);
	}
	
	public void deptParamterChange() throws Exception {

		int count = 1;
		scrollToWebelement(deptAttributesValues);
		if (deptNames.isDisplayed()) {
			for (WebElement dept : departmentNames) {
				WebElement system = dept.findElement(By.xpath("../../../img"));
				clickByJavascriptMethod(system);
				for(int i = 0; i < allSystemParameters.size(); i++) {
					String className = allSystemParameters.get(i).getAttribute("class");
					if(!className.contains("mdc-checkbox--selected") && count <= 3) {
						allSystemParameters.get(i).click();
						count++;
					}else if (className.contains("mdc-checkbox--selected")) {
						allSystemParameters.get(i).click();
					}
				}
			}
			parameterSubmit.click();
		} else {
			throw new Exception("Department not available");
		}
	}
	
	public void productionDataDownload() throws Exception {
		boolean download;
		deleteFileByName(downloadDir, "Live_Dashboard_Data.csv");
		Thread.sleep(3000);
		scrollToWebelement(downloadIcon);
		elementToBeClickable(downloadIcon);
		clickByJavascriptMethod(downloadIcon);
		Thread.sleep(3000);
		// Check if file is downloaded..
        File[] files = downloadDir.listFiles((dir, name) -> name.equalsIgnoreCase("Live_Dashboard_Data.csv"));
        if (files != null && files.length > 0) {
            download = true;
        } else {
            download = false;
        }
        Assert.assertTrue(download);
	}
	
	public Map<String, String> departmentAttDetails() throws Exception {

		Map<String, String> deptatt = new HashMap<>();
		if (deptNames.isDisplayed()) {
			for (WebElement dept : departmentNames) {
				List<WebElement> attribute = dept.findElements(By.xpath("../../../../div/div/p"));
				for (int i = 0; i < attribute.size(); i++) {
					String attributeName = attribute.get(i).getText();
					String attValue = attribute.get(i).findElement(By.xpath("../div/div/p")).getText();
					deptatt.put(attributeName, attValue);
				}
			}
		} else {
			throw new Exception("Department not available");
		}
		return deptatt;
	}
	
	public void openingDetails() {
		for(WebElement plant : plantsInTables) {
			elementToBeClickable(plant);
			scrollToWebelement(plant);
			clickByJavascriptMethod(plant);
			for(WebElement dept : departmentsInTables) {
				elementToBeClickable(dept);
				scrollToWebelement(dept);
				clickByJavascriptMethod(dept);
				for(WebElement line : linesInTables) {
					elementToBeClickable(line);
					scrollToWebelement(line);
					clickByJavascriptMethod(line);
				}
			}
		}
	}
	
	int targetQuantity, Incountquantity, goodcountquantity = 0;
	int targetQuantity1, Incountquantity1, goodcountquantity1 = 0;
	double deptOEE1, lineOEE2 = 0.0;
	int lineCount, deptCount = 0;
	String lineProductionState, deptProductionState, plantProductionState;
	
	public void calculationOnTable() {
		for(WebElement plant : plantsInTables) {
			clickByJavascriptMethod(plant);
			elementToBeClickable(plant);
			scrollToWebelement(plant);
			clickByJavascriptMethod(plant);
			plantProductionState = plant.findElement(By.xpath("../../../td[2]")).getText();
			int plantTargetedQuantity = Integer.parseInt(plant.findElement(By.xpath("../../../td[4]")).getText());
			double plantOEE = Double.parseDouble(plant.findElement(By.xpath("../../../td[5]")).getText());
			int plantInCountQuantity = Integer.parseInt(plant.findElement(By.xpath("../../../td[6]")).getText());
			int plantGoodCountQuantity = Integer.parseInt(plant.findElement(By.xpath("../../../td[7]")).getText());
			for(WebElement dept : departmentsInTables) {
				elementToBeClickable(dept);
				scrollToWebelement(dept);
				clickByJavascriptMethod(dept);
				deptProductionState = dept.findElement(By.xpath("../../../td[2]")).getText();
				int deptTargetedQuantity = Integer.parseInt(dept.findElement(By.xpath("../../../td[4]")).getText());
				double deptOEE = Double.parseDouble(dept.findElement(By.xpath("../../../td[5]")).getText());
				int deptInCountQuantity = Integer.parseInt(dept.findElement(By.xpath("../../../td[6]")).getText());
				int deptGoodCountQuantity = Integer.parseInt(dept.findElement(By.xpath("../../../td[7]")).getText());
				targetQuantity = targetQuantity + deptTargetedQuantity;
				Incountquantity = Incountquantity + deptInCountQuantity;
				goodcountquantity = goodcountquantity + deptGoodCountQuantity;
				deptOEE1 = deptOEE1 + deptOEE;
				deptCount++;
				for(WebElement line : linesInTables) {
					elementToBeClickable(line);
					scrollToWebelement(line);
					clickByJavascriptMethod(line);
					lineProductionState = line.findElement(By.xpath("../../../td[2]")).getText();
					int lineTargetedQuantity = Integer.parseInt(line.findElement(By.xpath("../../../td[4]")).getText());
					double lineOEE = Double.parseDouble(line.findElement(By.xpath("../../../td[5]")).getText());
					int lineInCountQuantity = Integer.parseInt(line.findElement(By.xpath("../../../td[6]")).getText());
					int lineGoodCountQuantity = Integer.parseInt(line.findElement(By.xpath("../../../td[7]")).getText());
					targetQuantity1 = targetQuantity1 + lineTargetedQuantity;
					Incountquantity1 = Incountquantity1 + lineInCountQuantity;
					goodcountquantity1 = goodcountquantity1 + lineGoodCountQuantity;
					lineOEE2 = lineOEE2 + lineOEE;
					lineCount++;
				}
				double averageLineOEE = lineOEE2/lineCount;
				Assert.assertEquals(targetQuantity1, deptTargetedQuantity);
				Assert.assertEquals(Incountquantity1, deptInCountQuantity);
				Assert.assertEquals(goodcountquantity1, deptGoodCountQuantity);
				Assert.assertEquals(lineProductionState, deptProductionState);
				Assert.assertEquals(deptOEE, averageLineOEE);
			}
			double averageDeptOEE = deptOEE1/deptCount;
			Assert.assertEquals(targetQuantity, plantTargetedQuantity);
			Assert.assertEquals(Incountquantity, plantInCountQuantity);
			Assert.assertEquals(goodcountquantity, plantGoodCountQuantity);
			Assert.assertEquals(deptProductionState, plantProductionState);
			Assert.assertEquals(plantOEE, averageDeptOEE);
		}
	}
	
	public void availabilityOfAllProductionData() {
		visibilityOfElement(allProductionTable);
		scrollToWebelement(titleOfProductionTable);
	}
	
	public void titleAndColumnWithTextVerification() throws Exception {
		visibilityOfElement(titleOfProductionTable);
		String title = titleOfProductionTable.getText();
		Assert.assertEquals(title, "All Production");
		List<String> columnTitles = List.of("Asset Name", "Production State", "Part", "Targeted Quantity", "OEE(%)", "In Count(unit)", "Good Count(unit)", "Run Time(hh:mm:ss)", "Unplanned Downtime(hh:mm:ss)", "Planned Stop Time(hh:mm:ss)");
		ArrayList<String> columnTitles1 = new ArrayList<String>();
		for(WebElement column : columnHeaders) {
			columnTitles1.add(column.getText());
		}
		if(columnTitles.size() == columnTitles1.size()) {
			Assert.assertTrue(columnTitles1.containsAll(columnTitles));
		}else {
			throw new Exception("Capture column title and list column title size does not match");
		}
	}

	public double availabilityPercentage() {
		String Percentage = availabilityPercentage.getText();
		String[] SplitData = Percentage.split("%");
		double availabilityPercentage = Double.parseDouble(SplitData[0]);
		return availabilityPercentage;
	}

	public double performancePercentage() {
		String Percentage = performancePercentage.getText();
		String[] SplitData = Percentage.split("%");
		double performancePercentage = Double.parseDouble(SplitData[0]);
		return performancePercentage;
	}

	public double qualityPercentage() {
		String Percentage = qualityPercentage.getText();
		String[] SplitData = Percentage.split("%");
		double qualityPercentage = Double.parseDouble(SplitData[0]);
		return qualityPercentage;
	}

	public int availabilityProgressBarCounts() throws Exception {
		// boolean available = availabilityProgressBar.isDisplayed();
		if (availabilityProgressBar.isDisplayed()) {
			int filledBlocks = availabilityProgressBar
					.findElements(By.xpath(".//div[@class='filled item ng-star-inserted']")).size();
			int toolBlocks = availabilityProgressBar
					.findElements(By.xpath(".//div[@class='filled item tool ng-star-inserted']")).size();
			int totalFilledBlocks = filledBlocks + toolBlocks;
			int emptyBlocks = availabilityProgressBar.findElements(By.xpath(".//div[@class='item ng-star-inserted']"))
					.size();
			int total = totalFilledBlocks + emptyBlocks;
			Assert.assertEquals(total, 10);
			return totalFilledBlocks;
		} else {
			throw new Exception("Progress Bar for Availability is not showing..");
		}
	}

	public int performanceProgressBarCounts() throws Exception {
		// boolean performance = performanceProgressBar.isDisplayed();
		if (performanceProgressBar.isDisplayed()) {
			int filledBlocks = performanceProgressBar
					.findElements(By.xpath(".//div[@class='filled item ng-star-inserted']")).size();
			int toolBlocks = performanceProgressBar
					.findElements(By.xpath(".//div[@class='filled item tool ng-star-inserted']")).size();
			int totalFilledBlocks = filledBlocks + toolBlocks;
			int emptyBlocks = performanceProgressBar.findElements(By.xpath(".//div[@class='item ng-star-inserted']"))
					.size();
			int total = totalFilledBlocks + emptyBlocks;
			Assert.assertEquals(total, 10);
			return totalFilledBlocks;
		} else {
			throw new Exception("Progress Bar for Performance is not showing..");
		}
	}

	public int qualityProgressBarCounts() throws Exception {
		// boolean quality = qualityProgressBar.isDisplayed();
		if (qualityProgressBar.isDisplayed()) {
			int filledBlocks = qualityProgressBar
					.findElements(By.xpath(".//div[@class='filled item ng-star-inserted']")).size();
			int toolBlocks = qualityProgressBar
					.findElements(By.xpath(".//div[@class='filled item tool ng-star-inserted']")).size();
			int totalFilledBlocks = filledBlocks + toolBlocks;
			int emptyBlocks = qualityProgressBar.findElements(By.xpath(".//div[@class='item ng-star-inserted']"))
					.size();
			int total = totalFilledBlocks + emptyBlocks;
			Assert.assertEquals(total, 10);
			return totalFilledBlocks;
		} else {
			throw new Exception("Progress Bar for Quality is not showing..");
		}
	}

	public void liveDataUpdate() throws Exception {

		Thread.sleep(3000);
		String percentage = availabilityPercentage.getText();
		if (percentage.equalsIgnoreCase("0.0%")) {
			throw new Exception("Live data is off or there might be some issue data is not receiving..");
		}
	}

	public String dashboardTitle() {
		String title = dashbordTitle.getText();
		return title;
	}

	public void cardsAndValues() throws Exception {

		// Adding all the cards title in the array..
		ArrayList<String> cards = new ArrayList<>();
		cards.add("Inactive Machines");
		cards.add("Inactive Gateways");
		cards.add("Total Machines");
		cards.add("Active Machines");
		cards.add("Maintenance Due");
		cards.add("Under Maintenance");
		cards.add("Total Gateways");
		cards.add("Active Gateways");

		// Verifying if cards arraylist size match with the cards title list size..
		Assert.assertEquals(cardsTitle.size(), cards.size());

		// Verifying if count shows 0 for total machines and if card is not available..
		for (WebElement cardName : cardsTitle) {
			String title = cardName.getText();
			if (cards.contains(title)) {
				// System.out.println(title + " is available in the cards");
				Thread.sleep(2000);
				int count = Integer.parseInt(cardName.findElement(By.xpath("../p")).getText());
				// System.out.println(title + " are " + count);
				if ("Total Machines".equals(title) && count == 0) {
					throw new Exception("Machine count is not loaded and showing 0");
				}
			} else {
				// System.out.println(title + " is missing from the cards");
				throw new Exception(title + " Card is not available");
			}
		}
	}

	public void detailsCardAvailability() throws Exception {

		if (!detailsCardTitles.isDisplayed()) {
			throw new Exception("Department and Line details cards are not available");
		}
	}

	public void deptLineDetailsInView() throws Exception {

		scrollToWebelement(detailsCardTitles);
	}

	public void departmentAttributesWithValues() throws Exception {

		if (deptNames.isDisplayed()) {
			for (WebElement dept : departmentNames) {
				String deptName = dept.getText();
				System.out.println(deptName);
				List<WebElement> attribute = dept.findElements(By.xpath("../../../../div/div/p"));
				for (int i = 0; i < attribute.size(); i++) {
					String attributeName = attribute.get(i).getText();
					// System.out.println(attributeName);
					String attValue = attribute.get(i).findElement(By.xpath("../div/div/p")).getText();
					// System.out.println(attValue);
					System.out.println(
							"In Department " + deptName + " attribute " + attributeName + " has value " + attValue);
				}
			}
		} else {
			throw new Exception("Department not available");
		}
	}

	public void lineAttributesWithValues() throws Exception {

		if (lCardTitles.isDisplayed()) {
			for (WebElement lineTitle : lineCardTitles) {
				String lineName = lineTitle.getText();
				System.out.println(lineName);
				List<WebElement> attribute = lineTitle.findElements(By.xpath("../../../../div[2]/div[2]/div/p[1]"));
				for (WebElement att : attribute) {
					String attributeName = att.getText();
					// System.out.println(attributeName);
					String attributevalue = att.findElement(By.xpath("../p[2]")).getText();
					// System.out.println(attributevalue);
					System.out.println(
							"In line " + lineName + " attribute " + attributeName + " has value " + attributevalue);
				}
			}
		} else {
			throw new Exception("Line Cards are not available");
		}
	}
}
