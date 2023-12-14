package base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.ConstantPath;
import utility.PropertyOperations;

public abstract class ControlActions {
	private static PropertyOperations propOperations;
	protected static WebDriver driver;
	protected static WebDriverWait wait;

	public static void lounchBrowser() {
		PropertyOperations properations = new PropertyOperations(ConstantPath.EVN_FILEPATH);
		System.setProperty(ConstantPath.CHROME_DRIVER_KEY, ConstantPath.CHROME_DRIVER_VALUE);
		driver = new ChromeDriver();
		driver.get(properations.getValue("url"));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, ConstantPath.WAIT);
	}

	public static void closeBrowser() {
		driver.close();
	}

	protected WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement e = null;
		switch (locatorType.toUpperCase()) {
		case "XPATH":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			else
				e = driver.findElement(By.xpath(locatorValue));
			break;

		case "CSS":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			else
				e = driver.findElement(By.cssSelector(locatorValue));
			break;

		case "ID":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			else
				e = driver.findElement(By.id(locatorValue));
			break;

		case "NAME":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			else
				e = driver.findElement(By.name(locatorValue));
			break;

		case "LINKTEST":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			else
				e = driver.findElement(By.linkText(locatorValue));
			break;

		case "PARTIALLINKTEST":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			else
				e = driver.findElement(By.partialLinkText(locatorValue));
			break;

		case "CLASSNAME":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			else
				e = driver.findElement(By.className(locatorValue));
			break;

		case "TAGNAME":
			if (isWaitRequired)
				e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			else
				e = driver.findElement(By.tagName(locatorValue));
			break;

		default:
			System.out.println("Invalid locator");
		}
		return e;
	}

	protected void waitForElementToBeVisible(WebElement e) {
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	protected void waitForElementToBeclickable(WebElement e) {
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}

	protected void waitForElementToBeInvisible(WebElement e) {
		wait.until(ExpectedConditions.invisibilityOf(e));
	}

	protected Boolean isElementDisplayed(WebElement e) {
		try {
			return e.isDisplayed();
		} catch (NoSuchElementException ne) {
			return false;
		}
	}

	protected Boolean isElementDisplayedWithWait(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
			return true;
		} catch (Exception te) {
			return false;
		}
	}

	protected Boolean isElementDisplayedWithWait(WebElement e, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(e));
			return true;
		} catch (Exception te) {
			return false;
		}
	}

	protected String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public static void takeScreenshot(String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver; // takes screenshot
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);//outype will generate file and keep it in memory
		//coping the file from memory to folder
		try {
			FileUtils.copyFile(sourceFile, new File(".//screenshots/"+fileName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
