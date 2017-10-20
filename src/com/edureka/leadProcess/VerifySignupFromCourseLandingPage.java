package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.util.DriverTestCase;

public class VerifySignupFromCourseLandingPage extends DriverTestCase{
    
	private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;
    
    static String email;
    static String password;
    static String userName;
    static String coursename;
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
    public void test_002VerifySignupFromCourseLandingPage() throws Exception {

        try {
        	
            String os_arch = System.getProperty("os.arch");
            System.out.println("OS Arch:" + os_arch);   

            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            //Select a course
            selectedCoursePage= dashboardPage.selectCourse_NewAdd();
            String courseName = dashboardPage.getCourseName_NewlyAdded();
            System.out.println("Course which is selected: " +courseName);
            String slug = dashboardPage.getSlug();
            
            // Click on Signin 
            addLog("Click on Signin");
            signInModalPage = selectedCoursePage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            // Sign up user
            String domainName= propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            selectedCoursePage= signInModalPage.signUp(SelectedCoursePage.class, domainName);
            String email = signInModalPage.RuntimeUserEmail();
            String username = signInModalPage.RuntimeUserName();
            String phoneNum = signInModalPage.RuntimeUserPhNum();
            
            // Verify User Home Page
            addLog("Verify User Home Page");
            selectedCoursePage=selectedCoursePage.verifySignUpSuccessfully();

            // Verify Data in User Table
            addLog("Verify Data in User Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUserTable("1",email,username,phoneNum);

            // Verify Data in User Lead Table
   //course__Id = propertyReader.readTestData("Course_ID_BigData");
            String course_id = selectedCoursePage.getCourseID(slug);
            System.out.println(course_id);
            webSiteAction = propertyReader.readTestData("HomePage_Signup_WebSite_Action");
            country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("CampaignSource");
            campaignName= propertyReader.readTestData("CampaignName");
            campaignMedium= propertyReader.readTestData("CampaignMedium");
            addLog("Verify Data in User Lead Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_LeadsTable(course_id,webSiteAction, country,campaignSource,campaignName,campaignMedium,email);

            // Verify Data in User Course table
   //course_Group = propertyReader.readTestData("Course_Group_BigData");
            String course_group = selectedCoursePage.getCourseGroup(slug);
            String getUserID = selectedCoursePage.getUserID(email);
            isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            addLog("Verify Data in User Course table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_CoursedTable(course_id,isPaidValue,course_group,getUserID);

            // Verify Data in User Event Table
            campaign_Values= propertyReader.readTestData("UTM_campaign");
            event_Type=propertyReader.readTestData("EventType");
            addLog("Verify Data in User Event Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_EventTable(course_id,webSiteAction,campaign_Values,event_Type,getUserID);

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
            captureScreenshot("test_002VerifySignupFromCourseLandingPage");
            throw e;
        } 
        catch (final Exception e) {
            captureScreenshot("test_002VerifySignupFromCourseLandingPage");
            throw e;
        }
    }
}