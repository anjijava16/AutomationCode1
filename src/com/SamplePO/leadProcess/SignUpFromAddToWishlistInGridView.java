package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;
import com.thoughtworks.selenium.webdriven.commands.Refresh;

public class SignUpFromAddToWishlistInGridView extends DriverTestCase {

	private DashboardPage dashboardPage;
	private SignInModalPage signInModalPage;
	private AllCoursesPage allCoursesPage;
	private UserHomePage userHomePage;
	private SelectedCoursePage selectedCoursePage;

	static String email;
	static String username;
	static String password;
	static String userName;
	static String PhoneNum;
	static String coursename;
	static String course__Id;
	static String webSiteAction;
	static String course_Group;
	static String isPaidValue;
	static String campaign_Values;
	static String level_id;
	static String event;
	static String allCourse;
	static String country;
	static String campaignSource;
	static String campaignName;
	static String campaignMedium;
	static String event_Type;
	static String getUserID;
	private static String courseStatus;
	private static String courseProperty;

	@Test
	public void test_007SignUpFromAddToWishlistInGridView() throws Exception {
		try {

			// Navigate to app url
			addLog("Navigate to the Edureka application url");
			dashboardPage = applicationSetupForLead();

			// Verify Edureka Dashboard Page
			addLog("Verify Edureka Dashboard Page");
			dashboardPage = dashboardPage.verifyDashboard();

			// Click on Course Tab
	        addLog("Click on Course Tab");
			allCoursesPage = dashboardPage.clickOnCoursesTab();

			
			// Verify All Courses page
			addLog("Verify All Courses page");
			allCoursesPage = allCoursesPage.verifyAllCoursesPage();

			// Click on Grid view Icon
			addLog("Click on Grid view Icon");
			allCoursesPage = allCoursesPage.clickOnGridViewIcon();

			// Click on Add To Wishlist Icon
			addLog("Click on Add To Wishlist Icon");
			SignUpFromAddToWishlistInGridView.coursename = propertyReader.readTestData("DataScienceCourse");
			SignUpFromAddToWishlistInGridView.course__Id = propertyReader.readTestData("Course_ID_DataScience");
//signInModalPage = allCoursesPage.clickOnAddToWishList(SignInModalPage.class, SignUpFromAddToWishlistInGridView.coursename);
            signInModalPage = allCoursesPage.clickOnAddToWishListNew(SignInModalPage.class, SignUpFromAddToWishlistInGridView.course__Id);
			
            // Sign up user
			String domainName = propertyReader.readApplicationFile("EdurekaDomain");
			addLog("Sign up user");
			allCoursesPage = signInModalPage.signUp(AllCoursesPage.class, domainName);
			email = signInModalPage.RuntimeUserEmail();
			password = signInModalPage.RuntimeUserPassword();
            username = signInModalPage.RuntimeUserName();
            PhoneNum = signInModalPage.RuntimeUserPhNum();
            
			// Verify User Home Page
			addLog("Verify sign up successfully");
			allCoursesPage = allCoursesPage.verifySignUpSuccessfully();

			// Verify Data in user table
			addLog("Verify Data in user table");
	        allCoursesPage = allCoursesPage.dataVerificationInUserTable("1",email,username,PhoneNum);

			// Verify Data in User Lead Table
			SignUpFromAddToWishlistInGridView.course__Id = propertyReader.readTestData("Course_ID_DataScience");
			SignUpFromAddToWishlistInGridView.webSiteAction = propertyReader.readTestData("HomePage_Signup_WebSite_Action");
			SignUpFromAddToWishlistInGridView.country = propertyReader.readTestData("CountryIndia");
			SignUpFromAddToWishlistInGridView.campaignSource = propertyReader.readTestData("LeadCampaignSource");
			SignUpFromAddToWishlistInGridView.campaignName = propertyReader.readTestData("LeadCampaignName");
			SignUpFromAddToWishlistInGridView.campaignMedium = propertyReader.readTestData("LeadCampaignMedium");
			addLog("Verify Data in User Lead Table");
			allCoursesPage = allCoursesPage.dataVerificationInUser_LeadsTable(SignUpFromAddToWishlistInGridView.course__Id, SignUpFromAddToWishlistInGridView.webSiteAction, SignUpFromAddToWishlistInGridView.country, SignUpFromAddToWishlistInGridView.campaignSource, SignUpFromAddToWishlistInGridView.campaignName, SignUpFromAddToWishlistInGridView.campaignMedium,email);

			// Verify Data in User Course table
			SignUpFromAddToWishlistInGridView.course_Group = propertyReader.readTestData("CourseGroup_DataScience");
			SignUpFromAddToWishlistInGridView.isPaidValue = propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
			getUserID = allCoursesPage.getUserID(email);
			addLog("Verify Data in User Course table");
	        allCoursesPage = allCoursesPage.dataVerificationInUser_CoursedTable(SignUpFromAddToWishlistInGridView.course__Id, SignUpFromAddToWishlistInGridView.isPaidValue, SignUpFromAddToWishlistInGridView.course_Group,getUserID);

			// Verify Data in User Event Table
			SignUpFromAddToWishlistInGridView.campaign_Values = propertyReader.readTestData("LeadCampaignName");
			SignUpFromAddToWishlistInGridView.event_Type = propertyReader.readTestData("EventType");
			addLog("Verify Data in User Event Table");
         	allCoursesPage = allCoursesPage.dataVerificationInUser_EventTable(SignUpFromAddToWishlistInGridView.course__Id, SignUpFromAddToWishlistInGridView.webSiteAction, SignUpFromAddToWishlistInGridView.campaign_Values, SignUpFromAddToWishlistInGridView.event_Type,getUserID);

			// Veriy Data in Ambassadors table
			SignUpFromAddToWishlistInGridView.level_id = propertyReader.readTestData("HomePage_Signup_level_id");
			addLog("Veriy Data in Ambassadors table");
			allCoursesPage = allCoursesPage.dataVerificationInUser_AmbassadorsTable(getUserID);

			// Verify Data in Completed Queue Jobs table
			SignUpFromAddToWishlistInGridView.courseStatus = propertyReader.readTestData("Status");
			SignUpFromAddToWishlistInGridView.courseProperty = propertyReader.readTestData("Priority");
	        allCoursesPage = allCoursesPage.dataVerificationInCompleted_Queue_Jobs_Table(SignUpFromAddToWishlistInGridView.course__Id,email);

			// Click on Profile Dropdown
			//SignUpFromAddToWishlistInGridView.userName = propertyReader.readRunTimeData("UserName");
			addLog("Click on Profile Dropdown");
			allCoursesPage = allCoursesPage.clickOnProfileDropdown(SignUpFromAddToWishlistInGridView.username);

			// Logout from the application
			addLog("Logout from the application.");
			dashboardPage = allCoursesPage.logout();

		} catch (final Error e) {
			captureScreenshot("test_007SignUpFromAddToWishlistInGridView");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_007SignUpFromAddToWishlistInGridView");
			throw e;
		}
	}

	@Test(dependsOnMethods = { "test_007SignUpFromAddToWishlistInGridView" })
	public void test_016SignInAddToWishlistInGridView() throws Exception {

		try {
			
			// Navigate to app url
			addLog("Navigate to the Edureka application url");
			dashboardPage = applicationSetupForLead();
			
			// Verify Edureka Dashboard Page
			addLog("Verify Edureka Dashboard Page");
			dashboardPage = dashboardPage.verifyDashboard();

			// Click on Course Tab
			addLog("Click on Course Tab");
			allCoursesPage = dashboardPage.clickOnCoursesTab();

			// Click on Grid view Icon
			addLog("Click on Grid view Icon");
			allCoursesPage = allCoursesPage.clickOnGridViewIcon();

			// Click on Add To Wishlist Icon
			SignUpFromAddToWishlistInGridView.coursename = propertyReader.readTestData("Course_RealTimeAnalytics");
			String course__Id1 = propertyReader.readTestData("Course_RealTimeAnalytics_Id");
			addLog("Click on Add To Wishlist Icon");
			signInModalPage = allCoursesPage.clickOnAddToWishListNew(SignInModalPage.class, course__Id1);

			// Verify default SignUp is selected
			Thread.sleep(10000);
			addLog("Verify default SignUp is selected");
			signInModalPage = signInModalPage.clickOnLoginTab();

			// Sign in user
	        addLog("Sign in user");
			signInModalPage = signInModalPage.enterEmailAndPassword(SignUpFromAddToWishlistInGridView.email, SignUpFromAddToWishlistInGridView.password);

			// Click on Start Learning Course button
			addLog("Click on Start Learning Course button");
			allCoursesPage = signInModalPage.clickStartLearningButton(AllCoursesPage.class);

			// Verify User Home Page
			addLog("Verify User Home Page");
			allCoursesPage = allCoursesPage.verifySignUpSuccessfully();

			// Verify Data in user table
			addLog("Verify Data in user table");
            allCoursesPage = allCoursesPage.dataVerificationInUserTable("1",email,username,PhoneNum);

			// Verify Data in User Lead Table
			addLog("Verify Data in User Lead Table");
			String webSiteAction1 = "like-Sign In";
			SignUpFromAddToWishlistInGridView.course_Group = propertyReader.readTestData("Course_RealTimeAnalytics_Group");
            allCoursesPage = allCoursesPage.dataVerificationInUser_LeadsTable(course__Id1, webSiteAction1, SignUpFromAddToWishlistInGridView.country, SignUpFromAddToWishlistInGridView.campaignSource, SignUpFromAddToWishlistInGridView.campaignName, SignUpFromAddToWishlistInGridView.campaignMedium,email);

			// Verify Data in User Course table
			addLog("Verify Data in User Course table");
			String getUserID = allCoursesPage.getUserID(email);
         	allCoursesPage = allCoursesPage.dataVerificationInUser_CoursedTable(course__Id1, SignUpFromAddToWishlistInGridView.isPaidValue, SignUpFromAddToWishlistInGridView.course_Group,getUserID);

			// Verify Data in User Event Table
			SignUpFromAddToWishlistInGridView.event = propertyReader.readTestData("Event_AddToWishList");
			addLog("Verify Data in User Event Table");
     		allCoursesPage = allCoursesPage.dataVerificationInUser_EventTable(course__Id1, SignUpFromAddToWishlistInGridView.event, SignUpFromAddToWishlistInGridView.campaign_Values, SignUpFromAddToWishlistInGridView.event_Type,getUserID);

			// Veriy Data in Ambassadors table
			addLog("Veriy Data in Ambassadors table");
			allCoursesPage = allCoursesPage.dataVerificationInUser_AmbassadorsTable(getUserID);
			
			// Verify Data in Completed Queue Jobs table
			SignUpFromAddToWishlistInGridView.courseStatus = propertyReader.readTestData("Status");
			SignUpFromAddToWishlistInGridView.courseProperty = propertyReader.readTestData("Priority");
     		allCoursesPage = allCoursesPage.dataVerificationInCompleted_Queue_Jobs_Table(course__Id1,email);

			// Click on Profile Dropdown
			addLog("Click on Profile Dropdown");
			allCoursesPage = allCoursesPage.clickOnProfileDropdown(username);

			// Logout from the application
			addLog("Logout from the application.");
			dashboardPage = allCoursesPage.logout();
		}

		catch (final Error e) {
	 		captureScreenshot("test_016SignInAddToWishlistInGridView");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_016SignInAddToWishlistInGridView");
			throw e;
		}
	}

	@Test(dependsOnMethods = { "test_016SignInAddToWishlistInGridView" })
	public void test_024WishListInGridViewAllCoursePage() throws Exception {

		try {
			// Verify Edureka Dashboard Page
			addLog("Verify Edureka Dashboard Page");
			dashboardPage = dashboardPage.verifyDashboard();

			// Click on Sign in button
			addLog("Click on Sign in button");
			signInModalPage = dashboardPage.clickSignInHeader();

			// Sign up user
			addLog("Sign up user");
			signInModalPage = signInModalPage.enterEmailAndPassword(SignUpFromAddToWishlistInGridView.email, SignUpFromAddToWishlistInGridView.password);

			// Click on Start Learning button
			addLog("Click on Start Learning button");
			userHomePage = signInModalPage.clickStartLearningButton(UserHomePage.class);

			// Verify User Page
			addLog("Verify User Page");
			allCoursesPage = allCoursesPage.verifySignUpSuccessfully();

			// Click on Course Tab
			SignUpFromAddToWishlistInGridView.allCourse = propertyReader.readTestData("AllCourse");
			addLog("Click on Course Tab");
			allCoursesPage = userHomePage.selectCourseOption(AllCoursesPage.class, SignUpFromAddToWishlistInGridView.allCourse);

			// Verify All Courses page
			addLog("Verify All Courses page");
			allCoursesPage = allCoursesPage.verifyAllCoursesPage();

			// Click on Add To Wishlist Icon
			addLog("Click on Add To Wishlist Icon");
			SignUpFromAddToWishlistInGridView.coursename=propertyReader.readTestData("Course_LinuxAdministration");
			String course__Id2 = propertyReader.readTestData("Course_LinuxAdministration_Id");
			allCoursesPage = allCoursesPage.clickOnAddToWishListNew(AllCoursesPage.class, course__Id2);

			// Verify Data in user table
			addLog("Verify Data in user table");		
			allCoursesPage = allCoursesPage.dataVerificationInUserTable("1",email,username,PhoneNum);

			// Verify Data in User Lead Table
			SignUpFromAddToWishlistInGridView.webSiteAction = propertyReader.readTestData("WebSite_Action_AddToWishList");
			SignUpFromAddToWishlistInGridView.course_Group = propertyReader.readTestData("Course_LinuxAdministration_Group");
			addLog("Verify Data in User Lead Table");
     		allCoursesPage = allCoursesPage.dataVerificationInUser_LeadsTable(course__Id2, SignUpFromAddToWishlistInGridView.webSiteAction, SignUpFromAddToWishlistInGridView.country, SignUpFromAddToWishlistInGridView.campaignSource, SignUpFromAddToWishlistInGridView.campaignName, SignUpFromAddToWishlistInGridView.campaignMedium,email);

			// Verify Data in User Course table
			addLog("Verify Data in User Course table");
			String getUserID = allCoursesPage.getUserID(email);
     		allCoursesPage = allCoursesPage.dataVerificationInUser_CoursedTable(course__Id2, SignUpFromAddToWishlistInGridView.isPaidValue, SignUpFromAddToWishlistInGridView.course_Group,getUserID);

			// Verify Data in User Event Table
			addLog("Verify Data in User Event Table");
    		allCoursesPage = allCoursesPage.dataVerificationInUser_EventTable(course__Id2, SignUpFromAddToWishlistInGridView.webSiteAction, SignUpFromAddToWishlistInGridView.campaign_Values, SignUpFromAddToWishlistInGridView.event_Type,getUserID);

			// Veriy Data in Ambassadors table
			addLog("Veriy Data in Ambassadors table");
			allCoursesPage = allCoursesPage.dataVerificationInUser_AmbassadorsTable(getUserID);
			

			// Verify Data in Completed Queue Jobs table
			SignUpFromAddToWishlistInGridView.courseStatus = propertyReader.readTestData("Status");
			SignUpFromAddToWishlistInGridView.courseProperty = propertyReader.readTestData("Priority");
    		allCoursesPage = allCoursesPage.dataVerificationInCompleted_Queue_Jobs_Table(course__Id2,email);
			
			// Click on Profile Dropdown
			addLog("Click on Profile Dropdown");
			allCoursesPage = allCoursesPage.clickOnProfileDropdown(SignUpFromAddToWishlistInGridView.userName);

			// Logout from the application
			addLog("Logout from the application.");
			dashboardPage = allCoursesPage.logout();
		}

		catch (final Error e) {
			captureScreenshot("test_024WishListInGridViewAllCoursePage");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_024WishListInGridViewAllCoursePage");
			throw e;
		}
	}
}
