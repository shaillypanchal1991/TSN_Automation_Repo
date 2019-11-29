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

public class LoginPage extends BaseSetup {

	private AndroidDriver<AndroidElement> AndroidDriver = null;
	private IOSDriver<IOSElement> IOSDriver = null;
	final Logger log = LogManager.getLogger(LoginPage.class.getName());

	/**
	 * Initializing android Driver
	 */
	public LoginPage(AndroidDriver<AndroidElement> driver) {
		log.debug("AndroidDriver initialized");
		this.AndroidDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}

	/**
	 * Initializing iOS Driver
	 */
	public LoginPage(IOSDriver<IOSElement> driver) {
		log.debug("IOSDriver initialized");
		this.IOSDriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
	}


	/**
	 * Objects Locators
	 */
	@AndroidFindBy(xpath = "//*[@text='Email ID']")
	@iOSFindBy(xpath = "//*[@name='EmailIdTextField']")
	private WebElement username;

	@AndroidFindBy(xpath = "//*[@text='Password']")
	@iOSFindBy(xpath = "//*[@name='PasswordTextField']")
	private WebElement password;

	@AndroidFindBy(xpath = "//*[@text='SIGN IN']")
	@iOSFindBy(xpath = "//*[@name='LoginButton']")
	private WebElement loginBtn;

	@AndroidFindBy(id = "com.sample.poc:id/imgLogo")
	@iOSFindBy(xpath = "//XCUIElementTypeImage[@name='LogoImageView']")
	private WebElement tsnLogo;

	@AndroidFindBy(xpath = "//*[@text='Remember Me']")
	@iOSFindBy(xpath = "//*[@name='RememberMeLabel']")
	private WebElement rememberMe;

	@AndroidFindBy(id = "com.sample.poc:id/snackbar_text")
	@iOSFindBy(xpath = "//*[@name='Verification Failed']")
	private WebElement verificationFailedMsg;

	@AndroidFindBy(xpath = "//*[@text='Ok']")
	@iOSFindBy(xpath = "//*[@name='Ok']")
	private WebElement okBtn;

	/**
	 * isUserNameDisplayed method is used to verify if Username field getting
	 * displayed
	 */
	public boolean isUserNameDisplayed() {
		if (username.isDisplayed() == true) {
			log.debug("isUserNameDisplayed : Success");
			test.log(Status.PASS, MarkupHelper.createLabel("userNameDisplayed method: Passed ", ExtentColor.BLUE));
			return true;
		} else {
			log.debug("isUserNameDisplayed : Fails");
			test.log(Status.FAIL, MarkupHelper.createLabel("userNameDisplayed method: FAIL ", ExtentColor.RED));
			return false;
		}
	}

	/**
	 * isPasswordDisplayed method is used to verify if Password field getting
	 * displayed
	 */
	public boolean isPasswordDisplayed() {
		if (password.isDisplayed() == true) {
			log.debug("isPasswordDisplayed : Success");
			test.log(Status.PASS, MarkupHelper.createLabel("passwordDisplayed method: Passed ", ExtentColor.BLUE));
			return true;
		} else {
			log.debug("isPasswordDisplayed : Fails");
			test.log(Status.FAIL, MarkupHelper.createLabel("passwordDisplayed method: FAIL ", ExtentColor.RED));
			return false;
		}
	}

	/**
	 * isLoginBtnDisplayed method is used to verify if Login button getting
	 * displayed
	 */
	public boolean isLoginBtnDisplayed() {
		if (loginBtn.isDisplayed() == true) {
			log.debug("isLoginBtnDisplayed : Success");
			test.log(Status.PASS, MarkupHelper.createLabel("loginBtnDisplayed method: Passed ", ExtentColor.BLUE));
			return true;
		} else {
			log.debug("isLoginBtnDisplayed : Fails");
			test.log(Status.FAIL, MarkupHelper.createLabel("loginBtnDisplayed method: FAIL ", ExtentColor.RED));
			return false;
		}
	}

	/**
	 * isRememberMeDisplayed method is used to verify if Remember Me checkbox
	 * getting displayed
	 */
	public boolean isRememberMeDisplayed() {
		if (rememberMe.isDisplayed() == true) {
			log.debug("isRememberMeDisplayed : Success");
			test.log(Status.PASS, MarkupHelper.createLabel("rememberMeDisplayed method: Passed ", ExtentColor.BLUE));
			return true;
		} else {
			log.debug("isRememberMeDisplayed : Fails");
			test.log(Status.FAIL, MarkupHelper.createLabel("rememberMeDisplayed method: FAIL ", ExtentColor.RED));
			return false;
		}
	}

	/**
	 * verifyLoginScreen method is used to verify all the elements getting displayed
	 * on the Login screen
	 */
	public boolean verifyLoginScreen() {
		log.debug("In verifyLoginScreen method");
		if (isUserNameDisplayed() && isPasswordDisplayed() && isLoginBtnDisplayed() && isRememberMeDisplayed()) {
			log.debug("Login screen verified");
			test.log(Status.PASS, MarkupHelper.createLabel("verifyLoginScreen method: Passed ", ExtentColor.BLUE));
			return true;
		} else {
			log.error("Login screen verification : Fails");
			test.log(Status.FAIL, MarkupHelper.createLabel("verifyLoginScreen method: FAIL ", ExtentColor.RED));
			return false;
		}
	}

	/**
	 * enterUserName method is used to enter value in Username field
	 */
	public void enterUserName() {
		log.debug("In enterUserName method");
		username.click();
		username.sendKeys(configProp.getProperty("username"));
		test.log(Status.PASS, MarkupHelper.createLabel("enterUserName method: Passed ", ExtentColor.BLUE));
	}

	/**
	 * enterPassword method is used to enter value in Password field
	 */
	public void enterPassword() {
		log.debug("In enterPassword method");
		password.click();
		password.sendKeys(configProp.getProperty("password"));
		test.log(Status.PASS, MarkupHelper.createLabel("enterPassword method: Passed ", ExtentColor.BLUE));
	}

	/**
	 * enterLogin method is used to click on the Login button
	 */
	public void enterLogin() {
		log.debug("In enterLogin method");
		try {
			Thread.sleep(2000);
			loginBtn.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test.log(Status.PASS, MarkupHelper.createLabel("enterLogin method: Passed ", ExtentColor.BLUE));
	}

	/**
	 * enterCredentials method is used to enter values in Username and Password
	 * field
	 */
	public void enterCredentials() {
		log.debug("In enterCredentials method");
		verifyLoginScreen();
		enterUserName();
		enterPassword();
		test.log(Status.PASS, MarkupHelper.createLabel("enterCredentials method: Passed ", ExtentColor.BLUE));
	}

	

	

	

}