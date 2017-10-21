package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.util.DriverTestCase;

public class VerifySignupFromEnrollOnAnyCourse extends DriverTestCase {

    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;
    private OrderSummaryPage orderSummaryPage;

    static String email;
    static String password;
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
    public void test_005VerifySignupFromEnroll() throws Exception {

        try {
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Select Course From Trending Courses
            addLog("Select Course");
            selectedCoursePage= dashboardPage.selectCourse_NewAdd();
            String slug = dashboardPage.getSlug();

            //Click on Enroll Now Button
            addLog("Click on Enroll Now Button");
            signInModalPage=selectedCoursePage.clickOnEnrollButton(SignInModalPage.class);

            // Sign up user
            String domainName= propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            orderSummaryPage= signInModalPage.signUp(OrderSummaryPage.class, domainName);
            String email = signInModalPage.RuntimeUserEmail();
            String username = signInModalPage.RuntimeUserName();
            String getUserID = signInModalPage.getUserID(email);
            String phoneNum = signInModalPage.RuntimeUserPhNum();
            
            // Verify User Home Page
            addLog("Verify sign up successfully");
            orderSummaryPage=orderSummaryPage.verifySignUpSuccessfully();
            
            // Verify Data in User Table
            addLog("Verify Data in User Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUserTable("1",email,username,phoneNum);

            // Verify Data in User Lead Table
            String course_id = selectedCoursePage.getCourseID(slug);
            System.out.println(course_id);
            webSiteAction = propertyReader.readTestData("HomePage_Signup_WebSite_Action");
            country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("LeadCampaignSource");
            campaignName= propertyReader.readTestData("LeadCampaignName");
            campaignMedium= propertyReader.readTestData("LeadCampaignMedium");
            addLog("Verify Data in User Lead Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_LeadsTable(course_id,webSiteAction,country,campaignSource, campaignName, campaignMedium,email);

            // Verify Data in User Course table
            String course_group = selectedCoursePage.getCourseGroup(slug);
            isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            addLog("Verify Data in User Course table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_CoursedTable(course_id,isPaidValue,course_group,getUserID);

            // Verify Data in User Event Table
            event_Enroll= propertyReader.readTestData("Event_Enroll");
            event_Type=propertyReader.readTestData("Enorll_Event_Type");
            addLog("Verify Data in User Event Table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_EventTable(course_id,event_Enroll,campaignName, event_Type,getUserID);
           
            //Verify data in Pre-order table
            
            orderSummaryPage=orderSummaryPage.dataVerificationInUser_PreOrderTableForLeadProcess(course_id,slug,email);
            
            // Veriy Data in Ambassadors table
            level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Veriy Data in Ambassadors table");
            orderSummaryPage= orderSummaryPage.dataVerificationInUser_AmbassadorsTable(getUserID);

            // Verify Data in Completed Queue Jobs table
            String courseStatus = propertyReader.readTestData("Status");
            String courseProperty = propertyReader.readTestData("Priority");
            orderSummaryPage = orderSummaryPage.dataVerificationInCompleted_Queue_Jobs_Table(course_id,email);
            

        }
        catch (final Error e) {
            captureScreenshot("test_005VerifySignupFromEnroll");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_005VerifySignupFromEnroll");
            throw e;
        }
    }
}
