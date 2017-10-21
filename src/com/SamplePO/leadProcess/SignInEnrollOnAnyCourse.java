package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class SignInEnrollOnAnyCourse extends DriverTestCase {

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
    static String username;
    static String phoneNum;
    static String password; 
    static String email;
    
    static String campaignSource;
    static String campaignName;
    static String campaignMedium;
    static String event_Type;


    @Test
    public void test_014SignInEnrollOnAnyCourse() throws Exception {

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
             username = signInModalPage.RuntimeUserName();
             email = signInModalPage.RuntimeUserEmail();
             password = signInModalPage.RuntimeUserPassword();
             phoneNum = signInModalPage.RuntimeUserPhNum();
            
            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyRandUserLoggedIn(username);
            
            addLog("Click on Profile Dropdown");
            userHomePage=userHomePage.clickOnProfileDropdown();  
               
            // Logout Application
            addLog("Logout Application");
            dashboardPage=userHomePage.logout();
            
            // Verify Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Select Course and Verify the Course Landing Page
            addLog("Select Course");
            selectedCoursePage=dashboardPage.selectCourse_Trending();
            String courseName = dashboardPage.getCourseName_TrendingCourse();
            System.out.println("Course which is selected: " +courseName);
            String slug = dashboardPage.getSlug();
            
            // Click on Play Button of Video
            addLog("Click on Play Button of Video");
            signInModalPage=selectedCoursePage.clickOnEnrollButton(SignInModalPage.class);
           
            // Verify default SignUp is selected
            addLog("Verify default SignUp is selected");
            signInModalPage= signInModalPage.clickOnLoginTab();
            
            // Sign in user
              //email = propertyReader.readRunTimeData("Email_Id");
              //password = propertyReader.readTestData("Password");
              //userName = "UserQI";
              //phoneNum = "9897989798";
            addLog("Sign in user");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);
            
            // Click on Start Learning button
            addLog("Click on Start Learning button");
            orderSummaryPage = signInModalPage.clickStartLearningButton(OrderSummaryPage.class);
 
            
            // Verify Data in User Table
            addLog("Verify Data in User Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUserTable("1",email,username,phoneNum);

            // Verify Data in User Lead Table
            String course_id = selectedCoursePage.getCourseID(slug);
            System.out.println(course_id);
            webSiteAction = propertyReader.readTestData("Enroll_Sign_In");
            country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("LeadCampaignSource");
            campaignName= propertyReader.readTestData("LeadCampaignName");
            campaignMedium= propertyReader.readTestData("LeadCampaignMedium");
            addLog("Verify Data in User Lead Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_LeadsTable(course_id,webSiteAction,country,campaignSource, campaignName, campaignMedium,email);

            // Verify Data in User Course table
            String course_group = selectedCoursePage.getCourseGroup(slug);
            String getUserID = selectedCoursePage.getUserID(email);
            System.out.println("User ID : "+ getUserID);
            isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_CoursedTable(course_id,isPaidValue,course_group,getUserID);

            // Verify Data in User Event Table
            event_Enroll= propertyReader.readTestData("Event_Enroll");
            event_Type=propertyReader.readTestData("Enorll_Event_Type");
            addLog("Verify Data in User Event Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_EventTable(course_id,webSiteAction,campaign_Values,event_Type,getUserID);

            // Veriy Data in Ambassadors table
            level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Veriy Data in Ambassadors table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_AmbassadorsTable(getUserID);

            // Verify Data in Completed Queue Jobs table
            /*String courseStatus = this.propertyReader.readTestData("Status");
            String courseProperty = this.propertyReader.readTestData("Priority");
            */
            orderSummaryPage = orderSummaryPage.dataVerificationInCompleted_Queue_Jobs_Table(course_id,email);

            
           /*// Click on Profile Dropdown
           // String userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            orderSummaryPage=orderSummaryPage.clickOnProfileDropdown("USERNR");            

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = orderSummaryPage.logout();          
        */   
        }
        catch (final Error e) {
            captureScreenshot("test_014SignInEnrollOnAnyCourse");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_014SignInEnrollOnAnyCourse");
            throw e;
        }
    }
 @Test(dependsOnMethods={"test_014SignInEnrollOnAnyCourse"})
    public void test_023EnrollOnAnyCourseLandingPage() throws Exception {

        try {
        	
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

        	
            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

/*            // Click on Sign in button
            addLog("Click on Sign in button");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Sign in user
            addLog("Sign in user");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

            // Click on Start Learning button
            addLog("Click on Start Learning button");
            userHomePage = signInModalPage.clickStartLearningButton(UserHomePage.class);
*/
/*            // Verify User Page
            addLog("Verify User Page");
            userHomePage=userHomePage.verifyUserPage();
*/            
         // Select Course and Verify the Course Landing Page
            addLog("Select Course");
            selectedCoursePage=dashboardPage.selectCourse_Popular();
            String courseName = dashboardPage.getCourseName_PopularCOurse();
            System.out.println("Course which is selected: " +courseName);
            String slug = dashboardPage.getSlug();
            
            // Click on Play Button of Video
            addLog("Click on Play Button of Video");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);
            
            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Verify Data in User Table
            addLog("Verify Data in User Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUserTable("1",email,username,phoneNum);

            // Verify Data in User Lead Table
            //course__Id = propertyReader.readTestData("Course_ID_BigData");
            
            String course_id = selectedCoursePage.getCourseID(slug);
            System.out.println(course_id);
            country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("LeadCampaignSource");
            campaignName= propertyReader.readTestData("LeadCampaignName");
            campaignMedium= propertyReader.readTestData("LeadCampaignMedium");
            webSiteAction = propertyReader.readTestData("Event_Enroll");
            addLog("Verify Data in User Lead Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_LeadsTable(course_id,webSiteAction,country,campaignSource, campaignName, campaignMedium,email);

            // Verify Data in User Course table
            addLog("Verify Data in User Course table");
            String course_group = selectedCoursePage.getCourseGroup(slug);
            String getUserID = selectedCoursePage.getUserID(email);
            System.out.println("User ID : "+ getUserID);
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_CoursedTable(course_id,isPaidValue,course_group,getUserID);

            // Verify Data in User Event Table
            addLog("Verify Data in User Event Table");
            campaign_Values=propertyReader.readTestData("Event_UTM_campaign");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_EventTable(course_id,event_Enroll,campaign_Values, event_Type,getUserID);

            // Veriy Data in Ambassadors table
            addLog("Veriy Data in Ambassadors table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_AmbassadorsTable(getUserID);
            
            // Verify Data in Completed Queue Jobs table
            /*String courseStatus = propertyReader.readTestData("Status");
            String courseProperty =propertyReader.readTestData("Priority");*/
            orderSummaryPage = orderSummaryPage.dataVerificationInCompleted_Queue_Jobs_Table(email,course_id);
        }

        catch (final Error e) {
            captureScreenshot("test_023EnrollOnAnyCourseLandingPage");
            throw e;
        } 
        catch (final Exception e) {
            captureScreenshot("test_023EnrollOnAnyCourseLandingPage");
            throw e;
        }
    }
}