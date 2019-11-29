/* 
Apache License 

Copyright [2018] [Tech Mahindra Americas Inc.]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.tsn.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseSetup {

	public WebDriver webDriver = null;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test = null;
	public Properties configProp = null;
	public DesiredCapabilities capabilities = null;
	public String iOSAppPath = System.getProperty("user.dir") + "//src//main//java"
			+ "//org//tsn//utils//SampleApplication.ipa";

	public AndroidDriver<AndroidElement> Androiddriver = null;
	public IOSDriver<IOSElement> IOSdriver = null;
	public static AppiumDriverLocalService service = null;

	/**
	 * extentSetup method is used for setting up config parameters for Extent Report
	 * Generation
	 */
	@BeforeClass
	public void extentSetup() {
		String fileName = new SimpleDateFormat("yyyyMMddHHmm'.html'").format(new Date());

		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/TSN_AutomationTestReport" + fileName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Android/iOS Automation Test Report");
		htmlReporter.config().setReportName("Android/iOS Automation Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	/**
	 * BaseSetup constructor is used for initialize configuration prop file
	 */
	public BaseSetup() {
		configProp = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "//src//main//java" + "//org//tsn//config//config.properties");
			configProp.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * startAppium method is used to start appium server
	 */
	public static void startAppium() {
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
	}

	/**
	 * getAndroidDriver method is used to setup android driver connection used to
	 * set the desired driver capabilities for Android
	 */
	public AndroidDriver<AndroidElement> getAndroidDriver() throws MalformedURLException {
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", configProp.getProperty("android_DeviceName"));
		capabilities.setCapability("appPackage", configProp.getProperty("appPackage"));
		capabilities.setCapability("appActivity", configProp.getProperty("appActivity"));
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("noReset", false);
		capabilities.setCapability("autoGrantPermissions", true);
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),
				capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * getDriver method is used to setup driver connection used to set the desired
	 * driver capabilities for iOS
	 */
	public IOSDriver<IOSElement> getIOSDriver() throws MalformedURLException {
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", configProp.getProperty("iOS_deviceName"));
		capabilities.setCapability("platformName", configProp.getProperty("platformName"));
		capabilities.setCapability("platformVersion", configProp.getProperty("platformVersion"));
		capabilities.setCapability("AutomationName", "XCUITest");
		capabilities.setCapability("udid", configProp.getProperty("udid"));
		capabilities.setCapability("app", iOSAppPath);
		capabilities.setCapability("noReset", false);
		capabilities.setCapability("autoGrantPermissions", true);
		IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * sleep method is used to induce a desired wait time
	 */
	public void sleep(long sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * sleep method is used to stop appium server
	 */
	public static void stopAppium() {
		service.stop();
	}

	/**
	 * closeDriver method is used to close the driver
	 */
	public void closeDriver() {
		webDriver.close();
	}

	/**
	 * extentFlush method is used to clear the extent object used for reporting
	 */
	@AfterSuite
	public void extentFlush() {
		extent.flush();
	}
}