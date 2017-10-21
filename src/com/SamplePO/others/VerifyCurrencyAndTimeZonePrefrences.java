package com.edureka.others;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyCurrencyAndTimeZonePrefrences extends DriverTestCase {

	private DashboardPage dashboardPage;
	private SelectedCoursePage selectedCoursePage;
	private SignInModalPage signInModalPage;
	private OrderSummaryPage orderSummaryPage;
	private UserHomePage userHomePage;

	static String email;
	static String password;
	static String coursename;

	@Test
	public void test_001VerifyCurrencyAndTimeZonePrefrences() throws Exception {

		try {
			// Navigate to app url

			// Navigate to app url
			addLog("Navigate to the Edureka application url");
			dashboardPage = applicationSetup();

			// Verify Edureka Dashboard Page
			addLog("Verify Edureka Dashboard Page");
			dashboardPage=  dashboardPage.verifyDashboard();

			// Click on Signin Navigation Header
			addLog("Click on SignIn Navigation header");
			signInModalPage = dashboardPage.clickSignInHeader();

			// Verify LogIn Is Default
			addLog("Verify LogIn Is Default");
			signInModalPage = signInModalPage.verifyLoginIsDefault();

			// click on Sign up link
			addLog("click on Sign up link");
			signInModalPage=signInModalPage.clickSignUp();

			String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
			addLog("Sign up user");
			userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);

			// Verify User Home Page
			addLog("Verify User Home Page");
			userHomePage=userHomePage.verifyUserPage();

			// Select Course
			String courseName = propertyReader.readTestData("BigData&Hadoop");
			addLog("Select Course");
			selectedCoursePage=dashboardPage.selectCourse(courseName);

			// Verify Selected course should be open.
			addLog("Verify Selected course should be open");
			selectedCoursePage=selectedCoursePage.verifySelectedCoursePage(courseName);

			// Click on Enroll Button
			addLog("Click on Enroll Button");
			orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

			// Verify Order Summary Page
			addLog("Verify Order Summary Page");
			orderSummaryPage=orderSummaryPage.verifyPage();

			// Change Country
			String country_Australia = propertyReader.readTestData("CountryName");
			String currencySign = propertyReader.readTestData("AustralianCurrencySign");
			addLog("Change Country "+country_Australia + " from country dropdown");
			orderSummaryPage=orderSummaryPage.changeCountry(country_Australia,currencySign);

			// Verify Change Time Zone
			String changeAustralianTimeZone= propertyReader.readTestData("AustralianTimeZone");
			addLog("Verify change Time Zone" +changeAustralianTimeZone );
			orderSummaryPage = orderSummaryPage.verifyChangedTimeZoneWithCountry(changeAustralianTimeZone);

		}
		catch (final Error e) {
			captureScreenshot("test_001VerifyCurrencyAndTimeZonePrefrences");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_001VerifyCurrencyAndTimeZonePrefrences");
			throw e;
		}
	}
	@Test(dependsOnMethods={"test_001VerifyCurrencyAndTimeZonePrefrences"})
	public void test_002VerifyPagesLinks() throws Exception {
		try {

			// Verify Url Link
			addLog("Verify Url Link");
			orderSummaryPage = orderSummaryPage.verifyUrlLink();
		}
		catch (final Error e) {
			captureScreenshot("test_002VerifyPagesLinks");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_002VerifyPagesLinks");
			throw e;
		}
	}

}

