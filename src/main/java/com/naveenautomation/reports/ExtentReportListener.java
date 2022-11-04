package com.naveenautomation.reports;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener extends TestListenerAdapter {

	private ExtentHtmlReporter htmlReporter;
	private ExtentReports extent;
	private ExtentTest test;

	@Override
	public void onStart(ITestContext testContext) {
		// Get the current date and Time
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		// Report Name
		String repName = "Extent Report" + "_" + timeStamp + ".html";

		htmlReporter = new ExtentHtmlReporter("./Extent Reports/" + repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Name of the host", "localhost");
		extent.setSystemInfo("Env", "Prod");
		extent.setSystemInfo("Tester Name", "Manvir");

		htmlReporter.config().setDocumentTitle("Naveen Automation Application Testing");
		htmlReporter.config().setReportName("Regression Suite Testing");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);

	}

	@Override
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		test = extent.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		test = extent.createTest(tr.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		test = extent.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

}
