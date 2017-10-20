package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class SignInFromAddToWishListInListViewOnAllCoursesPage extends DriverTestCase{
    
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;
    private AllCoursesPage allCoursesPage;
    private SelectedCoursePage selectedCoursePage;
    private UserHomePage userHomePage;
    static String coursename;
    static String email;
    static String password;
    static String course__Id ;
    static String webSiteAction;
    static String course_Group;
    static String isPaidValue;
    static String campaign_Values;
    static String level_id;
    static String event;
    static String country;
    static String campaignSource;
    static String campaignName;
    static String campaignMedium;
    static String event_Type;


    @Test
    public void test_015SignInFromAddToWishListInListViewOnAllCoursesPage() throws Exception {

        try {

            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Course Tab
            addLog("Click on Course Tab");
            allCoursesPage= dashboardPage.clickOnCoursesTab();

            // Click on List view Icon
            addLog("Click on List view Icon");
            allCoursesPage=allCoursesPage.clickOnListViewIcon();
           
           //addLog("Click on Add To Wishlist Icon");
           //selectedCoursePage = dashboardPage.selectcourse_AllCoursePage(); 
           course__Id = propertyReader.readTestData("Course_Id_Testing_SeleniumWebdriver");
            //signInModalPage= allCoursesPage.clickOnAddToWishListFromListCourses(SignInModalPage.class, coursename);
           signInModalPage= allCoursesPage.clickOnAddToWishListFromListCoursesByCourseId(SignInModalPage.class, course__Id);

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
            allCoursesPage=allCoursesPage.verifySignUpSuccessfully();
            
             // Verify Data in user table
            addLog("Verify Data in user table");
            allCoursesPage = allCoursesPage.dataVerificationInUserTable("1",email,username,phoneNum);

            // Verify Data in User Lead Table
            course__Id = propertyReader.readTestData("Course_Id_Testing_SeleniumWebdriver");
            webSiteAction = propertyReader.readTestData("HomePage_Signup_WebSite_Action");
            country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("LeadCampaignSource");
            campaignName= propertyReader.readTestData("LeadCampaignName");
            campaignMedium= propertyReader.readTestData("LeadCampaignMedium");
            addLog("Verify Data in User Lead Table");
            allCoursesPage= allCoursesPage.dataVerificationInUser_LeadsTable(course__Id,webSiteAction,country,campaignSource, campaignName, campaignMedium,email);

            // Verify Data in User Course table
            course_Group = propertyReader.readTestData("Course_Group_Testing_SeleniumWebdriver");
            isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            //String course_group = selectedCoursePage.getCourseGroup(slug);
            String getUserID = allCoursesPage.getUserID(email);
            addLog("Verify Data in User Course table");
            allCoursesPage= allCoursesPage.dataVerificationInUser_CoursedTable(course__Id,isPaidValue,course_Group,getUserID);

            // Verify Data in User Event Table
            campaign_Values= propertyReader.readTestData("LeadCampaignName");
            event_Type=propertyReader.readTestData("EventType");
            String event_context= propertyReader.readTestData("HomePage_SignIn_WebSite_Action");
            addLog("Verify Data in User Event Table");
            allCoursesPage= allCoursesPage.dataVerificationInUser_EventTable(course__Id,event_context,campaign_Values,event_Type,getUserID);

            // Veriy Data in Ambassadors table
            level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Veriy Data in Ambassadors table");
            allCoursesPage= allCoursesPage.dataVerificationInUser_AmbassadorsTable(getUserID);

          
         // Verify Data in Completed Queue Jobs table
            this.allCoursesPage = this.allCoursesPage.dataVerificationInCompleted_Queue_Jobs_Table(course__Id,email);

            // Click on Profile Dropdown
            String userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            allCoursesPage=allCoursesPage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = allCoursesPage.logout();  

        }
        catch (final Error e) {
            captureScreenshot("test_015SignInFromAddToWishListInListViewOnAllCoursesPage");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_015SignInFromAddToWishListInListViewOnAllCoursesPage");
            throw e;
        }
    }

}
