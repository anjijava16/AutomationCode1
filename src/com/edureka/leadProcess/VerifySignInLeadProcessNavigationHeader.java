package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifySignInLeadProcessNavigationHeader  extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SignInModalPage signInModalPage;
    private SelectedCoursePage selectedCoursePage;
    
    static String campaignSource;
    static String campaignName;
    static String campaignMedium;
    static String event_Type;

    @Test
    public void test_010VerifySignInLeadProcessNavigationHeader() throws Exception {

        try {
/*
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();
            
            // Login Application
            addLog(" Login Application");
            userHomePage= signInModalPage.loginApp();

            // Verify User Home Page
            addLog("User has logged in successfully and Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

            // Click on Sign in button
            addLog("Click on Sign in button");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Sign in user
            String email = propertyReader.readRunTimeData("Test_Email_Id");
            String password = propertyReader.readRunTimeData("Test_Password");
            addLog("Sign in user");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);
            
            // Click on Start Learning button
            addLog("Click on Start Learning button");
            userHomePage = signInModalPage.clickStartLearningButton(UserHomePage.class);
        */
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
            String getUserID = userHomePage.getUserID(email);
            
            // Verify Data in user Table
            addLog("Verify Data in user Table");
            userHomePage=userHomePage.dataVerificationInUserTable("1",email,username,phoneNum);

            // Verify Data in User Lead Table
            String course__Id = propertyReader.readTestData("HomePage_Signup_Course_ID");
            String webSiteAction = propertyReader.readTestData("HomePage_SignUp_WebSite_Action");
            String country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("LeadCampaignSource");
            campaignName= propertyReader.readTestData("LeadCampaignName");
            campaignMedium= propertyReader.readTestData("LeadCampaignMedium");
          
            addLog("Verify Data in User Lead Table");
            userHomePage= userHomePage.dataVerificationInUser_LeadsTable(course__Id,webSiteAction,country,campaignSource, campaignName, campaignMedium,email);
                                                           
            // Verify Data in User Course table
            String isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            addLog("Verify Data in User Course table");
            userHomePage= userHomePage.dataVerificationInUser_CoursedTable(course__Id,isPaidValue,course__Id,getUserID);

            // Verify Data in User Event Table
            event_Type=propertyReader.readTestData("EventType");
            String webSiteAction1 = propertyReader.readTestData("HomePage_SignIn_WebSite_Action");
            addLog("Verify Data in User Event Table");
            userHomePage= userHomePage.dataVerificationInUser_EventTable(course__Id,webSiteAction1,campaignName, event_Type,getUserID);

            // Veriy Data in Ambassadors table
            String level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Veriy Data in Ambassadors table");
            userHomePage= userHomePage.dataVerificationInUser_AmbassadorsTable(getUserID);
            // Verify Data in Completed Queue Jobs table
            String courseStatus = propertyReader.readTestData("Status");
            String courseProperty = propertyReader.readTestData("Priority");
            userHomePage = userHomePage.dataVerificationInCompleted_Queue_Jobs_Table(course__Id,email);
            

        }
        catch (final Error e) {
            captureScreenshot("test_010VerifySignInLeadProcessNavigationHeader");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_010VerifySignInLeadProcessNavigationHeader");
            throw e;
        }
    }
}