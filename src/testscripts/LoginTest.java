package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ExcelOperations;

public class LoginTest extends Testbase {

	@Test
	public void verifyLogin() {
		loginPage.logIn("swatikudale92@gmail.com", "Swati@1592321");
		Boolean loginFlag = loginPage.isLoginSuccessFullyDisplayed();
		Assert.assertTrue(loginFlag);
	}

	@Test
	public void verifyLoginPageErrorMessage() {
		System.out.println("Click on Login button");
		loginPage.clickOnLoginBtn();

		System.out.println("Verify user Email required error displayed");
		boolean emailRequiredErrorMessage = loginPage.isEmailRequiredErrorDisplayed();
		Assert.assertTrue(emailRequiredErrorMessage);

		System.out.println("Verify password required error displayed");
		boolean passwordRequiredErrorMessage = loginPage.isPasswordRequiredErrorDisplayed();
		Assert.assertTrue(passwordRequiredErrorMessage);

	}

	@Test
	public void verifyEmailErrorMessage() {
		System.out.println("Enter valid Password");
		loginPage.enterPasswordOfUser("Swati@1592");

		System.out.println("Click on Login button");
		loginPage.clickOnLoginBtn();

		System.out.println("Verify user Email error message displayed");
		boolean emailrequiredError = loginPage.isEmailRequiredErrorDisplayed();
		Assert.assertTrue(emailrequiredError);

		System.out.println("Verify password is provided so no error displayed");
		boolean passwordPassed = loginPage.isPasswordRequiredErrorDisplayed();
		Assert.assertFalse(passwordPassed);
	}

	@Test
	public void verifyPasswordErrorMessage() {
		System.out.println("Enter valid user Email");
		loginPage.enterEmailOfUser("swatikudale92@gmail.com");

		System.out.println("Click on Login button");
		loginPage.clickOnLoginBtn();

		System.out.println("Verify user Email is provided so no error message displayed");
		boolean emailPassed = loginPage.isEmailRequiredErrorDisplayed();
		// it will return false meaning NoSuchElementDsiplayed as we have given
		// correctEmail
		Assert.assertFalse(emailPassed);

		System.out.println("Verify password required error displayed");
		boolean passwordRequiredErrorMessage = loginPage.isPasswordRequiredErrorDisplayed();
		Assert.assertTrue(passwordRequiredErrorMessage);
	}

	@Test(dataProvider = "readExceldata")
	public void verifyLoginUsingDDT(String userName, String passWord, String expectedMsg) {
		System.out.println("Login with provided credintials");
		loginPage.logIn(userName, passWord);
		String currentURL = "";

		boolean loginFlag;
		if (expectedMsg.equals("pass")) {
			System.out.println("Login Successfully toast message displayed");
			loginFlag = loginPage.isLoginSuccessFullyDisplayed();
			Assert.assertTrue(loginFlag, "Login Successfully");

			System.out.println("Login Successfull hence redirecting to dashboard page");
			currentURL = loginPage.getCurrentURL();
			Assert.assertTrue(currentURL.endsWith("dashboard/dash"));

			System.out.println("Incorrect emailID or password message displayed");
			loginFlag = loginPage.isLoginUnsuccessFullDisplayed();
			Assert.assertFalse(loginFlag, "Login Unsuccessfull");

		} else {
			System.out.println("Login Unsuccessfull toast message displayed");
			loginFlag = loginPage.isLoginUnsuccessFullDisplayed();
			Assert.assertTrue(loginFlag, "Login Unsuccessfull");

			currentURL = loginPage.getCurrentURL();
			System.out.println("Login UnSuccessfull hence stayed on same login page");
			Assert.assertTrue(currentURL.endsWith("auth/login"));

			System.out.println("Incorrect emailID or password message displayed");
			loginFlag = loginPage.isLoginSuccessFullyDisplayed();
			Assert.assertFalse(loginFlag, "Login Successfully");
		}
	}

	@DataProvider
	public Object[][] readExceldata() throws IOException {
		return ExcelOperations.readExcel(".//testData/LoginData.xlsx", "Login");
	}
}
