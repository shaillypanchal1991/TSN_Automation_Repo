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

public class LandingPage extends BaseSetup {

	private AndroidDriver<AndroidElement> AndroidDriver = null;
	private IOSDriver<IOSElement> IOSDriver = null;
	final Logger log = LogManager.getLogger(LandingPage.class.getName());

	public LandingPage(AndroidDriver<AndroidElement> driver) {
		this.AndroidDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	public LandingPage(IOSDriver<IOSElement> driver) {
		this.IOSDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Settings\"]/android.widget.ImageView")
	@iOSFindBy(accessibility = "Settings")
	private WebElement settingsBtn;

	/**
	 * settingsBtnDisplayed method is used to verify if settings icon getting
	 * displayed
	 */
	public boolean isSettingsBtnDisplayed() {
		log.debug("In settingsBtnDisplayed method");
		if (settingsBtn.isDisplayed() == true) {
			test.log(Status.PASS, MarkupHelper.createLabel("settingsBtnDisplayed method: Passed ", ExtentColor.BLUE));
			return true;
		} else {
			test.log(Status.FAIL, MarkupHelper.createLabel("settingsBtnDisplayed method: Passed ", ExtentColor.RED));
			return false;
		}
	}

	/**
	 * selectSettingsBtn method is used to click on Settings Icon
	 */
	public void selectSettingsBtn() {
		log.debug("In selectSettingsBtn method");
		settingsBtn.click();
		test.log(Status.PASS, MarkupHelper.createLabel("selectSettingsBtn method: Passed ", ExtentColor.BLUE));
	}

	/**
	 * selectSettingsFlow method is used to verify and click on Settings Icon
	 */
	public void selectSettingsFlow() {
		log.debug("In selectSettingsFlow method");
		isSettingsBtnDisplayed();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectSettingsBtn();
		test.log(Status.PASS, MarkupHelper.createLabel("selectSettingsFlow method: Passed ", ExtentColor.BLUE));
	}
}