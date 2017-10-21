package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class EnrollOnAnyCourseLandingPage extends DriverTestCase {

    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;
    private OrderSummaryPage orderSummaryPage;
    private UserHomePage userHomePage;

    static String coursename;
    static String course__Id ;
    static String webSiteAction;
    static String course_Group;
    static String isPaidValue;
    static String campaign_Values;
    static String level_id;
    static String event_Enroll;
    static String country;

    static String campaignSource;
    static String campaignName;
    static String campaignMedium;
    static String event_Type;


    @Test
    public void test_023EnrollOnAnyCourseLandingPage() throws Exception {

        try {
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

            // Click on Sign in button
            addLog("Click on Sign in button");
            signInModalPage = dashboardPage.clickSignInHeader();
            
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();
            
            String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);
            String username = signInModalPage.RuntimeUserName();
            String email = signInModalPage.RuntimeUserEmail();
            String password = signInModalPage.RuntimeUserPassword();
            String phoneNum = signInModalPage.RuntimeUserPhNum();
            
            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyRandUserLoggedIn(username);
            
            addLog("Click on Profile Dropdown");
            userHomePage=userHomePage.clickOnProfileDropdown();  
               
            // Logout Application
            addLog("Logout Application");
            dashboardPage=userHomePage.logout();
            
            addLog("Click on Sign in button");
            signInModalPage = dashboardPage.clickSignInHeader();
         
            
            // Sign in user
            addLog("Sign in user");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);
            
            // Click on Start Learning button
            addLog("Click on Start Learning button");
            userHomePage = signInModalPage.clickStartLearningButton(UserHomePage.class);

            // Verify User Page
            addLog("Verify User Page");
            userHomePage=userHomePage.verifyRandUserLoggedIn(username);

   
            // Select Course and Verify the Course Landing Page
            addLog("Select Course");
            selectedCoursePage=dashboardPage.selectCourse_Trending();
            String courseName = dashboardPage.getCourseName_TrendingCourse();
            System.out.println("Course which is selected: " +courseName);
            String slug = dashboardPage.getSlug();
            String course_id = selectedCoursePage.getCourseID(slug);
            String course_group = selectedCoursePage.getCourseGroup(slug);
            
            // Click on Play Button of Video
            addLog("Click on Play Button of Video");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();
            String getUserID = selectedCoursePage.getUserID(email);
          
            // Verify Data in User Table
            addLog("Verify Data in User Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUserTable("1",email,username,phoneNum);

            // Verify Data in User Lead Table
      //course__Id = propertyReader.readTestData("Course_ID_BigData");
            webSiteAction = propertyReader.readTestData("Event_Enroll");
            country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("LeadCampaignSource");
            campaignName= propertyReader.readTestData("LeadCampaignName");
            campaignMedium= propertyReader.readTestData("LeadCampaignMedium");
            addLog("Verify Data in User Lead Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_LeadsTable(course_id,webSiteAction,country,campaignSource, campaignName, campaignMedium,email);

            // Verify Data in User Course table
       //course_Group = propertyReader.readTestData("Course_Group_BigData");
            isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            addLog("Verify Data in User Course table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_CoursedTable(course_id,isPaidValue,course_group,getUserID);

            
            // Verify Data in User Event Table
            event_Enroll= propertyReader.readTestData("Event_Enroll");
            event_Type=propertyReader.readTestData("Enorll_Event_Type");
            addLog("Verify Data in User Event Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_EventTable(course_id,event_Enroll,campaign_Values, event_Type,getUserID);
            
            // Veriy Data in Ambassadors table
            level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Veriy Data in Ambassadors table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_AmbassadorsTable(getUserID);
            
         // Verify Data in Completed Queue Jobs table
            String courseStatus = propertyReader.readTestData("Status");
            String courseProperty =propertyReader.readTestData("Priority");
            orderSummaryPage = orderSummaryPage.dataVerificationInCompleted_Queue_Jobs_Table(course_id,email);



        }
        catch (final Error e) {
            captureScreenshot("test_023EnrollOnAnyCourseLandingPage");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_023EnrollOnAnyCourseLandingPage");
            throw e;
        }
    }
}