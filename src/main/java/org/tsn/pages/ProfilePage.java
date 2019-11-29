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

public class ProfilePage extends BaseSetup {

	private AndroidDriver<AndroidElement> AndroidDriver = null;
	private IOSDriver<IOSElement> IOSDriver = null;
	final Logger log = LogManager.getLogger(ProfilePage.class.getName());

	public ProfilePage(AndroidDriver<AndroidElement> driver) {
		this.AndroidDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	public ProfilePage(IOSDriver<IOSElement> driver) {
		this.IOSDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ImageView")
	@iOSFindBy(xpath = "(//XCUIElementTypeImage[@name='ProfileAvatarLogoImageView'])[1]")
	private WebElement profileTester;

	/**
	 * isProfileTesterDisplayed method is used to verify if Tester Avatar getting
	 * displayed
	 */
	public boolean isProfileTesterDisplayed() {
		log.debug("In isProfileTesterDisplayed method");
		if (profileTester.isDisplayed() == true) {
			test.log(Status.PASS,
					MarkupHelper.createLabel("isProfileTesterDisplayed method: Passed ", ExtentColor.BLUE));
			return true;
		} else {
			test.log(Status.FAIL,
					MarkupHelper.createLabel("isProfileTesterDisplayed method: Passed ", ExtentColor.RED));
			return false;
		}

	}

	/**
	 * selectTesterProfileAvatar method is used to click on the Tester Avatar
	 */
	public void selectTesterProfileAvatar() {
		log.debug("In selectTesterProfileAvatar method");
		profileTester.click();
		test.log(Status.PASS, MarkupHelper.createLabel("selectTesterProfileAvatar method: Passed ", ExtentColor.BLUE));
	}

	/**
	 * ProfileSelection method is used to verify and click on the Tester Avatar
	 */
	public void ProfileSelection() {
		log.debug("In ProfileSelection method");
		try {
		Thread.sleep(5000);
		isProfileTesterDisplayed();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selectTesterProfileAvatar();
		test.log(Status.PASS, MarkupHelper.createLabel("ProfileSelection method: Passed ", ExtentColor.BLUE));
	}
}