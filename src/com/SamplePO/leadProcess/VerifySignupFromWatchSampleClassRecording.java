package com.edureka.leadProcess;

import org.testng.annotations.Test;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.util.DriverTestCase;

public class VerifySignupFromWatchSampleClassRecording extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;

    static String userName;
    static String email;
    static String password;
    static String phoneNum;
    static String course;
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
    public void test_004VerifySignUpFromWatchSampleClassRecording() throws Exception {

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
            String slug = dashboardPage.getSlug();
           
            // Play class recording video
            addLog("Play class recording video");
            signInModalPage= selectedCoursePage.playClassRecording(SignInModalPage.class);

            // Verify default SignUp is selected
            addLog("Verify default SignUp is selected");
            signInModalPage= signInModalPage.verifySignUpIsDefault();

            // Sign up user
            String domainName= propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            selectedCoursePage= signInModalPage.signUp(SelectedCoursePage.class, domainName);
             email = signInModalPage.RuntimeUserEmail();
             userName = signInModalPage.RuntimeUserName();
             password = signInModalPage.RuntimeUserPassword();
             phoneNum = signInModalPage.RuntimeUserPhNum();
             String getUserID = signInModalPage.getUserID(email);
             
            // Verify User Home Page
            addLog("Verify sign up successfully");
            selectedCoursePage=selectedCoursePage.verifySignUpSuccessfully();

            // Verify Data in user Table
            addLog("Verify Data in user Table");
            selectedCoursePage=selectedCoursePage.dataVerificationInUserTable("1",email,userName,phoneNum);

            // Verify Data in User Lead Table
   // course__Id = propertyReader.readTestData("Course_Id_HadoopAdmin");
            String course_id = selectedCoursePage.getCourseID(slug);
            System.out.println(course_id);
            webSiteAction = propertyReader.readTestData("ClassRecording_Website_Action");
            country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("LeadCampaignSource");
            campaignName= propertyReader.readTestData("LeadCampaignName");
            campaignMedium= propertyReader.readTestData("LeadCampaignMedium");
            addLog("Verify Data in User Lead Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_LeadsTable(course_id,webSiteAction,country,campaignSource, campaignName, campaignMedium,email);

            // Verify Data in User Course table
            isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            String course_group = selectedCoursePage.getCourseGroup(slug);
            System.out.println("User ID : "+ getUserID);
  //  course_Group = propertyReader.readTestData("Course_Group_HadoopAdmin");
            addLog("Verify Data in User Course table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_CoursedTable(course_id,isPaidValue,course_group,getUserID);

            // Verify Data in User Event Table
            campaign_Values= propertyReader.readTestData("Event_UTM_campaign");
            event_Type=propertyReader.readTestData("EventType");
            addLog("Verify Data in User Event Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_EventTable(course_id,webSiteAction,campaign_Values, event_Type,getUserID);

            // Veriy Data in Ambassadors table
            level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Veriy Data in Ambassadors table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_AmbassadorsTable(getUserID);
         
            // Verify Data in Completed Queue Jobs table
            String courseStatus = propertyReader.readTestData("Status");
            String courseProperty = propertyReader.readTestData("Priority");
            selectedCoursePage = selectedCoursePage.dataVerificationInCompleted_Queue_Jobs_Table(course_id,email);

            // Click on Profile Dropdown
            addLog("Click on Profile Dropdown");
            selectedCoursePage=selectedCoursePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = selectedCoursePage.logout();            

        }
        catch (final Error e) {
            captureScreenshot("test_004VerifySignUpFromWatchSampleClassRecording");
            throw e;
        } 
        catch (final Exception e) {
            captureScreenshot("test_004VerifySignUpFromWatchSampleClassRecording");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_004VerifySignUpFromWatchSampleClassRecording"})
    public void test_013SignInFromVerifyWatchSimpleRecording() throws Exception {

        try {
            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            //Select a course
            addLog("Select course");
            selectedCoursePage= dashboardPage.selectCourse_Trending();
            String coursename = dashboardPage.getCourseName_TrendingCourse();
            System.out.println("Course which is selected: " +coursename);
            String slug = dashboardPage.getSlug();
           
            // Click on Play Button of Video
            addLog("Click on Play Button of Video");
            signInModalPage=selectedCoursePage.playClassRecording(SignInModalPage.class);

            // Verify default SignUp is selected
            addLog("Verify default SignUp is selected");
            signInModalPage= signInModalPage.clickOnLoginTab();

            // Sign in user
          /*  String email = propertyReader.readRunTimeData("Email_Id");
            String password = propertyReader.readTestData("Password");
          */  
            addLog("Sign in user");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);
            
            // Click on Start Learning Course button
            addLog("Click on Start Learning Course button");
            selectedCoursePage=signInModalPage.clickStartLearningButton(SelectedCoursePage.class);


            // Verify Data in User Lead Table
            String course_id = selectedCoursePage.getCourseID(slug);
            System.out.println(course_id);
            String Action = "Watch Sample Class Recording-Sign In";
            addLog("Verify Data in User Lead Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_LeadsTable(course_id,Action,country,campaignSource, campaignName, campaignMedium,email);

            // Verify Data in User Course table
            String course_group = selectedCoursePage.getCourseGroup(slug);
            String getUserID = selectedCoursePage.getUserID(email);
            System.out.println("User ID : "+ getUserID);
            addLog("Verify Data in User Course table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_CoursedTable(course_id,isPaidValue,course_group,getUserID);

            // Verify Data in User Event Table
            campaign_Values= propertyReader.readTestData("UTM_campaign");
            event = propertyReader.readTestData("Event_For_Rrcording_Video_Singin");
            addLog("Verify Data in User Event Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_EventTable(course_id,event,campaign_Values, event_Type,getUserID);

            // Veriy Data in Ambassadors table
            addLog("Veriy Data in Ambassadors table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_AmbassadorsTable(getUserID);
            
         // Verify Data in Completed Queue Jobs table
            String courseStatus = propertyReader.readTestData("Status");
            String courseProperty = propertyReader.readTestData("Priority");
            selectedCoursePage = selectedCoursePage.dataVerificationInCompleted_Queue_Jobs_Table(course_id,email);


        }

        catch (final Error e) {
            captureScreenshot("test_013SignInFromVerifyWatchSimpleRecording");
            throw e;
        } 
        catch (final Exception e) {
            captureScreenshot("test_013SignInFromVerifyWatchSimpleRecording");
            throw e;
        }
    }
}
