package com.naveenautomation.Utils;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProxyDriver implements WebDriver, JavascriptExecutor {
	private WebDriver driver;

	public ProxyDriver(WebDriver wd) {
		driver = wd;
	}

	public String getText(By Locator) {
		return this.getText(Locator, Duration.ofSeconds(5));
	}

	public void click(By locator) {
		this.waitForElementToBeClickable(locator, Duration.ofSeconds(5)).click();
	}

	public Object executeScript(String script, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object executeAsyncScript(String script, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	public void get(String url) {
		ProxyDriver.this.driver.get(url);

	}

	public String getCurrentUrl() {
		return ProxyDriver.this.driver.getCurrentUrl();
	}

	public String getTitle() {
		return ProxyDriver.this.driver.getTitle();
	}

	public List<WebElement> findElements(By by) {
		return ProxyDriver.this.driver.findElements(by);
	}

	public WebElement findElement(By by) {
		return ProxyDriver.this.driver.findElement(by);
	}

	public String getPageSource() {
		return ProxyDriver.this.driver.getPageSource();
	}

	public void close() {
		driver.close();

	}

	public void quit() {
		driver.quit();

	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public TargetLocator switchTo() {
		// TODO Auto-generated method stub
		return null;
	}

	public Navigation navigate() {
		return new Navigation() {

			public void to(URL url) {
				ProxyDriver.this.driver.navigate().to(url);

			}

			public void to(String url) {
				ProxyDriver.this.driver.navigate().to(url);

			}

			public void refresh() {
				ProxyDriver.this.driver.navigate().refresh();

			}

			public void forward() {
				ProxyDriver.this.driver.navigate().forward();

			}

			public void back() {
				ProxyDriver.this.driver.navigate().back();

			}
		};
	}

	public String getText(By locator, Duration waitforElementToBeVisibleInSeconds) {
		try {

			WebElement element = this.waitForElementToBeVisible(locator, waitforElementToBeVisibleInSeconds);
			return element.getText().trim();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Options manage() {
		return driver.manage();
	}

	public void sendKeys(By by, String keysInput) {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(driver.findElement(by)))
				.sendKeys(keysInput);
	}

	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by, Duration.ofSeconds(10));
			return this.findElement(by).isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public boolean isEnabled(By by) {
		try {
			waitForElementToBeVisible(by, Duration.ofSeconds(10));
			return this.findElement(by).isEnabled();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public void selectFromDropDown(WebElement element, String value) {
		waitForElementToBeSelectable(element);
		Select sc = new Select(element);
		try {
			sc.selectByValue(value);
		} catch (Exception e) {
			sc.selectByVisibleText(value);
		}
	}

	public void switchToNewTab(By element) {
		String parentHandle = this.getWindowHandle();
		waitForElementToBeClickable(element, Duration.ofSeconds(5));

		Set<String> allWindowHandles = this.getWindowHandles();
		for (String windowHandle : allWindowHandles) {
			if (!windowHandle.equals(parentHandle)) {
				this.switchTo().window(windowHandle);
			}
		}

	}

	public void acceptAlert() {
		new WebDriverWait(this, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent()).accept();
	}

	public void dismissAlert() {
		new WebDriverWait(this, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent()).dismiss();
	}

	public WebElement waitForElementToBeClickable(By locator, Duration i) {
		return new WebDriverWait(this, i).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement waitForElementToBeVisible(By Locator, Duration timeOutInSeconds) {
		return new WebDriverWait(this, timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}

	public void waitForElementToBeSelectable(WebElement element) {
		new WebDriverWait(this, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementSelectionStateToBe(element, true));
	}

}
