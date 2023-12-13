package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.ControlActions;
import constants.ConstantPath;

public class LoginPage extends ControlActions {
	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement userEmailElement;

	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement userPasswordElement;

	@FindBy(xpath = "//input[@id='login']")
	WebElement clickOnLoginBtnElement;

	@FindBy(xpath = "//div[@aria-label='Login Successfully']")
	WebElement loginSuccessfullElement;

	@FindBy(xpath = "//div[text()='*Email is required']")
	WebElement emailIsRequiredElement;

	@FindBy(xpath = "//div[text()='*Password is required']")
	WebElement passwordIsRequiredElement;

	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement loginFailElement;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void logIn(String email, String password) {
		// System.out.println("Enter Email Address");
		// option 1: driver.findElement(By.id("userEmail")).sendKeys(email);
		// option 2: getElement("XPATH", "//input[@id='userEmail']", false);
		// so to find element there are 3 options. 3rd is using page factory design
		// pattern
		// userEmailElement.sendKeys(email);
		enterEmailOfUser(email);

		// System.out.println("Enter Password");
		// driver.findElement(By.id("userPassword")).sendKeys(password)
		// userPasswordElement.sendKeys(password);
		enterPasswordOfUser(password);

		// System.out.println("Click on login");
		// driver.findElement(By.id("login")).click();
		// clickOnLoginBtnElement.click();
		clickOnLoginBtn();
	}

	public void enterPasswordOfUser(String password) {
		System.out.println("Enter Password");
		userPasswordElement.sendKeys(password);

	}

	public void enterEmailOfUser(String email) {
		System.out.println("Enter Email Address");
		userEmailElement.sendKeys(email);
	}

	public void clickOnLoginBtn() {
		System.out.println("click on login button");
		clickOnLoginBtnElement.click();
	}

	public boolean isLoginSuccessFullyDisplayed() {
		// WebElement loginSuccessfullElement = getElement("XPATH",
		// "//div[@aria-label='Login Successfully']", true);
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 30);
		 * wait.until(ExpectedConditions.visibilityOf(loginSuccessfullElement));
		 */

		/*
		 * waitForElementToBeVisible(loginSuccessfullElement); return
		 * loginSuccessfullElement.isDisplayed();
		 */

		return isElementDisplayedWithWait(loginSuccessfullElement, ConstantPath.FASTWAIT);
	}

	public boolean isLoginUnsuccessFullDisplayed() {
		return isElementDisplayedWithWait(loginFailElement, ConstantPath.FASTWAIT);
	}

	public boolean isEmailRequiredErrorDisplayed() {
		return isElementDisplayed(emailIsRequiredElement);
	}

	public boolean isPasswordRequiredErrorDisplayed() {
		return isElementDisplayed(passwordIsRequiredElement);

	}

	public String getCurrentURL() {
		return super.getCurrentURL();
	}
}
