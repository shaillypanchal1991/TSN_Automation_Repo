/* 
Apache License 

Copyright [2019] [Tech Mahindra Ltd.]

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

package org.tsn.test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.tsn.base.BaseSetup;
import org.tsn.pages.LandingPage;
import org.tsn.pages.LoginPage;
import org.tsn.pages.ProfilePage;
import org.tsn.pages.SettingsPage;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class IOSAutomation extends BaseSetup {

	public IOSDriver<IOSElement> driver = null;
	public LoginPage loginObj = null;
	public ProfilePage profileObj = null;
	public SettingsPage settingsObj = null;
	public LandingPage landingObj = null;
	final Logger log = LogManager.getLogger(IOSAutomation.class.getName());

	String deviceName = "null";

	/**
	 * setup method is used for opening driver connections used for clearing out
	 * cache of used apps used for for object initialization
	 */
	@BeforeMethod(alwaysRun = true)
	public void setup() throws InterruptedException, IOException {
		log.debug("In setup method");
		driver = getIOSDriver();
		loginObj = new LoginPage(driver);
		profileObj = new ProfilePage(driver);
		settingsObj = new SettingsPage(driver);
		landingObj = new LandingPage(driver);
	}

	/**
	 * TSN_iOS_Automation_Suite method is used for automate TSN iOS app priority is
	 * 1 invocationCount is 1
	 */
	@Test(priority = 1, invocationCount = 1, enabled = true)
	public void TSN_iOS_Automation_Suite() throws InterruptedException, IOException {

		// Logger notifying start of TC_1
		log.debug("In TSN_iOS_Automation_Suite method: Start");
		test = extent.createTest("TSN_iOS_Automation_Suite method");
		loginObj.enterCredentials();
		loginObj.enterLogin();
		profileObj.ProfileSelection();
		landingObj.selectSettingsFlow();
		settingsObj.logoutFlow();
		log.debug("TTSN_iOS_Automation_Suite");
		test.log(Status.PASS, MarkupHelper.createLabel("TSN_iOS_Automation_Suite method: Passed ", ExtentColor.BLUE));
	}

	/**
	 * tearDown method is used for closing all the open connections used for
	 * clearing out cache of used apps used for taking screenshot for failure cases
	 * used for uninstalling required apps
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws Exception {

		if (driver != null) {
			driver.quit();
		}
		System.gc();
	}

}
