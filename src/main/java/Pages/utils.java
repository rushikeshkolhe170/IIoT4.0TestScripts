package Pages;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utils {
	
	public WebDriver driver;
	
	@FindBy(xpath = "//div[contains (@class,'toast-message')]")
	private WebElement toastMessage;
	
	@FindBy(xpath = "//a[@mattooltip='Logout']")
	private WebElement logout;
	
	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement logoutConfirmYes;
	
	public void scrollToWebelement(WebElement element) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	public static void clearFolder(File folder) {		
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
	
	public boolean compareTwoStringHashMap(Map<String, String> one, Map<String, String> two) {
		return one.equals(two);
	}
	
	public void deleteFileByName(File folder, String fileName) {
	    File targetFile = new File(folder, fileName);
	    if (targetFile.exists()) {
	        boolean deleted = targetFile.delete();
	        if (deleted) {
	            System.out.println("File deleted successfully: " + fileName);
	        } else {
	            System.out.println("Failed to delete the file: " + fileName);
	        }
	    } else {
	        System.out.println("File not found: " + fileName);
	    }
	}
	
	public void visibilityOfElement(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}
	
	public void waitForElementToUpdate(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.stalenessOf(ele));
	}
	
	public void visibilityOfAllElements(List<WebElement> lineCellListDropdown) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(lineCellListDropdown));
	}
	
	public void elementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void clickByJavascriptMethod(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
	}
	
	public void clickByActionMethod(WebElement ele) {
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).click().perform();
	}
	
	public String toastMessage() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMessage));
		String msg = toastMessage.getText();
		wait.until(ExpectedConditions.invisibilityOf(toastMessage));
		return msg;
	}
	
	public void progressBarCheck(double percentage, int filledBlock) throws Exception {
		
		double percent = percentage;
		int Blocks = filledBlock;
		if(percent >= 1.0 && percent <= 10.0) {
			//System.out.println("block1");
			if(Blocks != 1) {
				throw new Exception("Progress bar doesn't match with the percentage");
			}
		}else if(percent >= 10.1 && percent <= 20.0) {
			//System.out.println("block2");
			if(Blocks != 2) {
				throw new Exception("Progress bar doesn't match with the percentage");
			}
		}else if(percent >= 20.1 && percent <= 30.0) {
			//System.out.println("block3");
			if(Blocks != 3) {
				throw new Exception("Progress bar doesn't match with the percentage");
			}
		}
		else if(percent >= 30.1 && percent <= 40.0) {
			//System.out.println("block4");
			if(Blocks != 4) {
				throw new Exception("Progress bar doesn't match with the percentage");
			}
		}else if(percent >= 40.1 && percent <= 50.0) {
			//System.out.println("block5");
			if(Blocks != 5) {
				throw new Exception("Progress bar doesn't match with the percentage");
			}
		}else if(percent >= 50.1 && percent <= 60.0) {
			//System.out.println("block6");
			if(Blocks != 6) {
				throw new Exception("Progress bar doesn't match with the percentage");
			}
		}else if(percent >= 60.1 && percent <= 70.0) {
			//System.out.println("block7");
			if(Blocks != 7) {
				throw new Exception("Progress bar doesn't match with the percentage");
			}
		}else if(percent >= 70.1 && percent <= 80.0) {
			//System.out.println("block8");
			if(Blocks != 8) {
				throw new Exception("Progress bar doesn't match with the percentage");
			}
		}else if(percent >= 80.1 && percent <= 90.0) {
			//System.out.println("block9");
			if(Blocks != 9) {
				throw new Exception("Progress bar doesn't match with the percentage");
			}
		}else if(percent >= 90.1 && percent <= 100) {
			//System.out.println("block10");
			if(Blocks != 10) {
				throw new Exception("Progress bar doesn't match with the percentage");
			}
		}
	}
	
	public void logout() {

		logout.click();
		logoutConfirmYes.click();
	}
}
