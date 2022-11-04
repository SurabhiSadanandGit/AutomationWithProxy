package com.naveenautomation.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.naveenautomation.Pages.RegisterAccountPage;
import com.naveenautomation.Pages.YourStorePage;
import com.naveenautomation.base.TestBase;

public class RegisterAccountPageTest extends TestBase {

	YourStorePage yp;
	RegisterAccountPage registerpage;

	@BeforeMethod
	public void startBrowserSession() {
		intialization();
		yp = new YourStorePage(webDriver, true);

	}

	@Test
	public void verifyErrorMsg() {

		yp.clickMyAccountBtn();
		registerpage = yp.clickRegBtn();
		registerpage = registerpage.clickSubmitBtn();

		String expMsg = "First Name must be between 1 and 32 characters!Last Name must be between 1 and 32 characters!E-Mail Address does not appear to be valid!Telephone must be between 3 and 32 characters!Password must be between 4 and 20 characters!";
		Assert.assertEquals(registerpage.getTextofErrorsMessage(), expMsg, "Text's not matching");

		String warningMsg = registerpage.clickSubmitBtn().getWarningMessage();
		Assert.assertEquals(warningMsg, "Warning: You must agree to the Privacy Policy!",
				"Warning Msg is not matching");

	}

	@AfterMethod
	public void quitBrowserSession() {
		quitBrowser();
	}
}
