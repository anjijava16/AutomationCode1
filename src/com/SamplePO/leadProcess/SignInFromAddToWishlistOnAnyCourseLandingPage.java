package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class SignInFromAddToWishlistOnAnyCourseLandingPage extends DriverTestCase {

    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;
    private AllCoursesPage allCoursePage;
    private UserHomePage userHomePage;
    
    static String course__Id ;
    static String webSiteAction;
    static String course_Group;
    static String isPaidValue;
    static String campaign_Values;
    static String level_id;
    static String event ;
    static String country;
    static String campaignSource;
    static String campaignName;
    static String campaignMedium;

    @Test
    public void test_012SignInFromAddToWishlistOnAnyCourseLandingPage() throws Exception {

        try {
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();
            
            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            //Select a course
           // String course= propertyReader.readTestData("CoursesJava");
            selectedCoursePage= dashboardPage.selectCourse_Popular();
            String courseName = dashboardPage.getCourseName_PopularCOurse();
            System.out.println("Course which is selected: " +courseName);
            String slug = dashboardPage.getSlug();   

            // Click on Add To Wishlist Icon
            addLog("Click on Add To Wishlist Icon");
            signInModalPage= selectedCoursePage.clickOnAddToWishlistIcon(SignInModalPage.class);

            // Verify default SignUp is selected
            addLog("Verify default SignUp is selected");
            signInModalPage= signInModalPage.clickOnLoginTab();
 
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
            
            // Verify Sign Up Header
            addLog("Click on Sign in button");
            signInModalPage = dashboardPage.clickSignInHeader();
            
            addLog("Sign in user");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);
            
            // Click on Start Learning button
            addLog("Click on Start Learning button");
            userHomePage = signInModalPage.clickStartLearningButton(UserHomePage.class);


            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage = userHomePage.verifyRandUserLoggedIn(username);
             
            
            // Verify Data in user table
            addLog("Verify Data in user table");
            selectedCoursePage = selectedCoursePage.dataVerificationInUserTable("1",email,username,phoneNum);

            // Verify Data in User Lead Table
           // course__Id = propertyReader.readTestData("CoursesJava_Id");
            String course_id = selectedCoursePage.getCourseID(slug);
            webSiteAction = propertyReader.readTestData("HomePage_Signup_WebSite_Action");
            campaignSource= propertyReader.readTestData("LeadCampaignSource");
            campaignName= propertyReader.readTestData("LeadCampaignName");
            campaignMedium= propertyReader.readTestData("LeadCampaignMedium");
            country= propertyReader.readTestData("CountryIndia");
            addLog("Verify Data in User Lead Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_LeadsTable(course_id,webSiteAction, country,campaignSource,campaignName,campaignMedium,email);

            // Verify Data in User Course table
            //course_Group = propertyReader.readTestData("CoursesJava_Group");
            String course_group = selectedCoursePage.getCourseGroup(slug);
            String getUserID = selectedCoursePage.getUserID(email);
            isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            addLog("Verify Data in User Course table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_CoursedTable(course_id,isPaidValue,course_group,getUserID);

            // Verify Data in User Event Table
            campaign_Values= propertyReader.readTestData("LeadCampaignName");
            String event_Type=propertyReader.readTestData("EventType");
            String event_context = propertyReader.readTestData("HomePage_SignIn_WebSite_Action");
            addLog("Verify Data in User Event Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_EventTable(course_id,event_context,campaign_Values,event_Type,getUserID);

            // Veriy Data in Ambassadors table
            level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Veriy Data in Ambassadors table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_AmbassadorsTable(getUserID);
            
         // Verify Data in Completed Queue Jobs table
            String courseStatus = propertyReader.readTestData("Status");
            String courseProperty = propertyReader.readTestData("Priority");
           selectedCoursePage = selectedCoursePage.dataVerificationInCompleted_Queue_Jobs_Table(course_id,email);



        }
        catch (final Error e) {
            captureScreenshot("test_012SignInFromAddToWishlistOnAnyCourseLandingPage");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_012SignInFromAddToWishlistOnAnyCourseLandingPage");
            throw e;
        }
    }
}