package com.naveenautomation.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.naveenautomation.Utils.ProxyDriver;

public class RegisterAccountPage extends Page {
	public RegisterAccountPage(WebDriver wd, boolean waitForPageToLoad) {
		super(wd, waitForPageToLoad);
	}

	List<WebElement> errorMsgs = wd.findElements(By.cssSelector("div.text-danger"));

	By subscribeBtn = By.cssSelector("div.buttons input:nth-of-type(2)");

	By warningMsg = By.cssSelector("div#account-register div.alert.alert-danger");

	public String getTextofErrorsMessage() {

		String text = "";
		for (WebElement errors : errorMsgs) {
			text += errors.getText();
		}
		return text;
	}

	public RegisterAccountPage clickSubmitBtn() {
		((ProxyDriver) wd).click(subscribeBtn);
		return new RegisterAccountPage(wd, true);
	}

	public String getWarningMessage() {
		return ((ProxyDriver) wd).getText(warningMsg);
	}
}
