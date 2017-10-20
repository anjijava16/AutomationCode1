package com.edureka.others;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyAllImagesOnThePageAreValid extends DriverTestCase{
	private DashboardPage dashboardPage;
	private AllCoursesPage allCoursesPage;
	private SignInModalPage signInModalPage;
	private UserHomePage userHomePage;
	private SelectedCoursePage selectedCoursePage;
	private OrderSummaryPage orderSummaryPage;

	private static String allCourse;
	@Test
	public void test_003VerifyAllImagesOnThePageAreValid() throws Exception {

		try {
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

			// Sign up User
			String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
			addLog("Sign up user");
			userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);

			// Verify User Home Page
			addLog("Verify User Home Page");
			userHomePage=userHomePage.verifyUserPage();

			//Verify Edureka logo at at Home page
			addLog("Verify Edureka logo at at All Courses page");
			userHomePage=userHomePage.verifyEdurekaLogoOnHomePage();

			//  Select AllCourse Tab from Courses dropdown
			allCourse = propertyReader.readTestData("AllCourse");
			addLog("Select "+allCourse+" Tab from Courses dropdown");
			allCoursesPage= userHomePage.selectCourseOption(AllCoursesPage.class, allCourse);

			// Verify All Courses page
			addLog("Verify All Courses page");
			allCoursesPage= allCoursesPage.verifyAllCoursesPage();

			// Verify Edureka logo at at All Courses page
			addLog("Verify Edureka logo at at All Courses page");
			allCoursesPage= allCoursesPage.verifyEdurekaLogo();

			// Verify Country flag
			addLog("Verify Country flag");
			allCoursesPage= allCoursesPage.verifyCountryFlagImages();

			// Select Course
			String courseName = propertyReader.readTestData("BigData&Hadoop");
			addLog("Select Course");
			selectedCoursePage=allCoursesPage.selectCourseFromGridView(courseName);

			// Verify Selected course should be open.
			addLog("Verify Selected course should be open");
			selectedCoursePage=selectedCoursePage.verifySelectedCoursePage(courseName);

			// Verify Selected course image
			addLog("Verify "+courseName+" course image");
			selectedCoursePage=selectedCoursePage.bigDataHadoopImage();

			// Click on Enroll Button
			addLog("Click on Enroll Button");
			orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

			// Verify Order Summary Page
			addLog("Verify Order Summary Page");
			orderSummaryPage=orderSummaryPage.verifyPage();

			// Verify Edureka logo at at Order Summary page
			addLog("Verify Edureka logo at at Order Summary page");
			orderSummaryPage=orderSummaryPage.verifyEdurekaLogoOnOrdersummaryPage();

		}   catch (final Error e) {
			captureScreenshot("test_003VerifyAllImagesOnThePageAreValid");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_003VerifyAllImagesOnThePageAreValid");
			throw e;
		}
	}
}
