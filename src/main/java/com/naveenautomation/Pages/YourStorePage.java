package com.naveenautomation.Pages;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.naveenautomation.Utils.ProxyDriver;

public class YourStorePage extends Page {

	private static final String PAGE_URL = "common/home";

	public YourStorePage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	private By myAccountBtn = By.cssSelector("a[title='My Account']");
	private By loginBtn = By.cssSelector("#top-links ul.dropdown-menu li:last-of-type");
	private By regBtn = By.cssSelector("ul.list-inline li:nth-of-type(2) ul li:nth-of-type(1) a");

	public void clickMyAccountBtn() {
		((ProxyDriver) wd).click(myAccountBtn);
	}

	public RegisterAccountPage clickRegBtn() {
		((ProxyDriver) wd).click(regBtn);
		return new RegisterAccountPage(wd, true);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!urlContains(wd.getCurrentUrl())) {
			fail("Page didn't load");
		}
	}

	@Override
	public String getPageURL() {
		return String.format("%s%s", getDomain(), PAGE_URL);
	}
}
