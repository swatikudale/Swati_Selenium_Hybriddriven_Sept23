package testscripts;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.ControlActions;
import pages.LoginPage;

public class Testbase {
	LoginPage loginPage;
	int count = 1;

	@BeforeMethod
	public void setUp() {
		ControlActions.lounchBrowser();
		loginPage = new LoginPage();
	}

	@AfterMethod
	public void closeBrowser(ITestResult result) {
		System.out.println(result.getStatus());
		if (ITestResult.FAILURE == result.getStatus()) {
			ControlActions.takeScreenshot(result.getName() + "_" + count++);
			ControlActions.closeBrowser();
		}
	}
}
