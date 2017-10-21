package com.edureka.leadProcess;

import net.sf.saxon.OutputURIResolver;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifySignupFromHeaderOnAnyCourseLandingPage extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SignInModalPage signInModalPage;

    private SelectedCoursePage selectedCoursePage;
    
    static String email;
    static String password;
    static String campaignSource;
    static String campaignName;
    static String campaignMedium;
    static String event_Type;
    
  @Test
    public void test_011SignInFromHeaderOnAnyCourseLandingPage() throws Exception {

        try {
        	
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();
            
            //Select a course
            addLog("Select Course");
            selectedCoursePage= dashboardPage.selectCourse_Popular();
            String coursename = dashboardPage.getCourseName_PopularCOurse();
            String slug = dashboardPage.getSlug();
           
            // Click on Signin 
            addLog("Click on Signin");
           /*  signInModalPage = selectedCoursePage.clickSignInHeader();
            
            // Login Application
            String email = propertyReader.readRunTimeData("Email_Id");
            String password = propertyReader.readTestData("Password");
            addLog("Sign in user");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);
            String getUserID = signInModalPage.getUserID(email);      
              
            addLog("Click on Start Learning Course button");
            userHomePage=signInModalPage.clickStartLearningButton(UserHomePage.class);
             */           
            signInModalPage = dashboardPage.clickSignInHeader();
            
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();
            
            String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);
            String username = signInModalPage.RuntimeUserName();
            String email = signInModalPage.RuntimeUserEmail();
            String password = signInModalPage.RuntimeUserPassword();
            String phoneNumber = signInModalPage.RuntimeUserPhNum();
            
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

            /*email = propertyReader.readRunTimeData("Test_Email_Id");
            password = propertyReader.readRunTimeData("Test_Password");
            */
            addLog("Sign in user");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);
            
            // Click on Start Learning button
            addLog("Click on Start Learning button");
            userHomePage = signInModalPage.clickStartLearningButton(UserHomePage.class);

            
            // Verify Data in User Table
            addLog("Verify Data in User Table");
            userHomePage= userHomePage.dataVerificationInUserTable("1",email,username,phoneNumber);

            String getUserID = selectedCoursePage.getUserID(email);
            
            
            // Verify Data in User Lead Table
   // String course__Id = propertyReader.readTestData("CoursesJava_Id");
            String course_id = selectedCoursePage.getCourseID(slug);
            System.out.println(course_id);
            String webSiteAction = propertyReader.readTestData("HomePage_SignUp_WebSite_Action");
            String country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("LeadCampaignSource");
            campaignName= propertyReader.readTestData("LeadCampaignName");
            campaignMedium= propertyReader.readTestData("LeadCampaignMedium");
            addLog("Verify Data in User Lead Table");
            userHomePage= userHomePage.dataVerificationInUser_LeadsTable(course_id,webSiteAction,country,campaignSource, campaignName, campaignMedium,email);

            // Verify Data in User Course table
            String isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            addLog("Verify Data in User Course table");
            userHomePage= userHomePage.dataVerificationInUser_CoursedTable(course_id,isPaidValue,course_id,getUserID);
            
            //
            
            // Verify Data in User Event Table
            event_Type=propertyReader.readTestData("EventType");
            addLog("Verify Data in User Event Table");
            userHomePage= userHomePage.dataVerificationInUser_EventTable(course_id,webSiteAction,campaignName, event_Type,getUserID);

            // Veriy Data in Ambassadors table
     //String level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Veriy Data in Ambassadors table");
            userHomePage= userHomePage.dataVerificationInUser_AmbassadorsTable(getUserID);

         // Verify Data in Completed Queue Jobs table
            String courseStatus = this.propertyReader.readTestData("Status");
            String courseProperty = this.propertyReader.readTestData("Priority");
            userHomePage = userHomePage.dataVerificationInCompleted_Queue_Jobs_Table(course_id,email);
            
            
        }
        catch (final Error e) {
            captureScreenshot("test_011SignInFromHeaderOnAnyCourseLandingPage");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_011SignInFromHeaderOnAnyCourseLandingPage");
            throw e;
        }
    }
}