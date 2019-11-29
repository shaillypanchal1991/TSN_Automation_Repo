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
package org.tsn.pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.tsn.base.BaseSetup;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SettingsPage extends BaseSetup {

	private AndroidDriver<AndroidElement> AndroidDriver = null;
	private IOSDriver<IOSElement> IOSDriver = null;
	final Logger log = LogManager.getLogger(SettingsPage.class.getName());

	public SettingsPage(AndroidDriver<AndroidElement> driver) {
		this.AndroidDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	public SettingsPage(IOSDriver<IOSElement> driver) {
		this.IOSDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	@AndroidFindBy(xpath = "//*[@text='Log Out']")
	@iOSFindBy(xpath = "//*[@name='LogoutButton']")
	private WebElement logoutBtn;

	/**
	 * isLogoutBtnDisplayed method is used to verify the Logout button
	 */
	public boolean isLogoutBtnDisplayed() {
		log.debug("In logoutBtnDisplayed method");
		if (logoutBtn.isDisplayed() == true) {
			test.log(Status.PASS, MarkupHelper.createLabel("logoutBtnDisplayed method: Passed ", ExtentColor.BLUE));
			return true;
		} else {
			test.log(Status.FAIL, MarkupHelper.createLabel("logoutBtnDisplayed method: FAILS ", ExtentColor.RED));
			return false;
		}
	}

	/**
	 * selectLogoutBtn method is used to click on the Logout button
	 */
	public void selectLogoutBtn() {
		log.debug("In selectLogoutBtn method");
		logoutBtn.click();
		test.log(Status.PASS, MarkupHelper.createLabel("selectLogoutBtn method: Passed ", ExtentColor.BLUE));
	}

	/**
	 * logoutFlow method is used to verify and click on the Logout button
	 */
	public void logoutFlow() {
		log.debug("In logoutFlow method");
		isLogoutBtnDisplayed();
		try {
			Thread.sleep(1000);
			selectLogoutBtn();
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test.log(Status.PASS, MarkupHelper.createLabel("logoutFlow method: Passed ", ExtentColor.BLUE));
	}
}