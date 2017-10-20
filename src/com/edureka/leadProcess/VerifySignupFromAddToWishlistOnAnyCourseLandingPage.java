package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.util.DriverTestCase;

public class VerifySignupFromAddToWishlistOnAnyCourseLandingPage extends DriverTestCase {

    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;

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
    public void test_003VerifySignupFromAddToWishlistOnAnyCourseLandingPage() throws Exception {

        try {
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Select Course From Trending Courses
            addLog("Select Course From Trending Courses");
            selectedCoursePage= dashboardPage.selectCourse_NewAdd();
            String coursename = dashboardPage.getCourseName_NewlyAdded();
            System.out.println("Course which is selected: " +coursename);
            String slug = dashboardPage.getSlug();
            

            // Click on Add To Wishlist Icon
            addLog("Click on Add To Wishlist Icon");
            signInModalPage= selectedCoursePage.clickOnAddToWishlistIcon(SignInModalPage.class);

            // Sign up user
            String domainName= propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            selectedCoursePage= signInModalPage.signUp(SelectedCoursePage.class, domainName);
            String email = signInModalPage.RuntimeUserEmail();
            String username = signInModalPage.RuntimeUserName();
            String phoneNum = signInModalPage.RuntimeUserPhNum();
            
            // Verify User Home Page
            addLog("Verify sign up successfully");
            selectedCoursePage=selectedCoursePage.verifySignUpSuccessfully();

            // Verify Data in user table
            addLog("Verify Data in user table");
            selectedCoursePage = selectedCoursePage.dataVerificationInUserTable("1",email,username,phoneNum);

            // Verify Data in User Lead Table
            String course_id = selectedCoursePage.getCourseID(slug);
            System.out.println(course_id);
            webSiteAction = propertyReader.readTestData("HomePage_Signup_WebSite_Action");
            country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("LeadCampaignSource");
            campaignName= propertyReader.readTestData("LeadCampaignName");
            campaignMedium= propertyReader.readTestData("LeadCampaignMedium");
            addLog("Verify Data in User Lead Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_LeadsTable(course_id,webSiteAction,country,campaignSource, campaignName, campaignMedium,email);

            // Verify Data in User Event Table
            campaign_Values= propertyReader.readTestData("LeadCampaignName");
            String event_Type=propertyReader.readTestData("EventType");
            String course_group = selectedCoursePage.getCourseGroup(slug);
            String getUserID = selectedCoursePage.getUserID(email);
            addLog("Verify Data in User Event Table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_EventTable(course_id,webSiteAction,campaign_Values,event_Type,getUserID);

            // Veriy Data in Ambassadors table
            level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Veriy Data in Ambassadors table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_AmbassadorsTable(getUserID);

         // Verify Data in Completed Queue Jobs table
            String courseStatus = this.propertyReader.readTestData("Status");
            String courseProperty = this.propertyReader.readTestData("Priority");
            selectedCoursePage = selectedCoursePage.dataVerificationInCompleted_Queue_Jobs_Table(email,course_id);

           // Verify Data in User Course table
            course_Group = propertyReader.readTestData("Course_Group_ApacheSpark&Scala");
            isPaidValue= propertyReader.readTestData("HomePage_Signup_Is_Paid_Value");
            addLog("Verify Data in User Course table");
            selectedCoursePage= selectedCoursePage.dataVerificationInUser_CoursedTable(course_id,isPaidValue,course_group,getUserID);
            


        }
        catch (final Error e) {
            captureScreenshot("test_003VerifySignupFromAddToWishlistOnAnyCourseLandingPage");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_003VerifySignupFromAddToWishlistOnAnyCourseLandingPage");
            throw e;
        }
    }
}
