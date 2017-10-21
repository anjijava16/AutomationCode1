package com.edureka.leadProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.util.DriverTestCase;

public class InquiryFromCourseLandingPage extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SelectedCoursePage selectedCoursePage1;    
    @Test
    public void test_020InquiryFromCourseLandingPage() throws Exception {

        try {
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            //Select a course
            addLog("Select course");
            selectedCoursePage= dashboardPage.selectCourse_Popular();

            // Verify Selected Course Page Is Open
            addLog("Verify Selected Course Page Is Open");
            String coursename = dashboardPage.getCourseName_PopularCOurse();
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(coursename);
            String slug = dashboardPage.getSlug();

            // Click on Query Box
            addLog("Click on Query Box");
            selectedCoursePage=selectedCoursePage.clickOnQueryBox();

            // Submit inquiry in drop query box
            addLog("Submit inquiry in drop query box");
            selectedCoursePage1=selectedCoursePage.sendQuery(SelectedCoursePage.class);
            String username = selectedCoursePage.Inquiry_UserName();
            String email = selectedCoursePage.Inquiry_Email();
            String phoneNum = selectedCoursePage.Inquiry_Phone();
            System.out.println("UserName is : " + username);
            System.out.println("Email is : " + email);
            
            // Verify Inquiry has been submitted
            addLog("Verify Inquiry has been submitted");
            selectedCoursePage=selectedCoursePage.verifySumbitInquiry();

            // Database verification in user table
            addLog("Database verification in user table");
            selectedCoursePage=selectedCoursePage.dataVerificationInUserTable("1",email,username,phoneNum);
            
            // Database verification in User Lead Table
            String course_id = selectedCoursePage.getCourseID(slug);
            String course_group = selectedCoursePage.getCourseGroup(slug);
  // String course__Id = propertyReader.readTestData("Course_Id_SplunkDev_Admin");
            String webSiteAction = propertyReader.readTestData("InquiryWebSiteActionOnCourseLadningPage");
            String  campaignName= propertyReader.readTestData("LeadCampaignName");

            // Verify Data in User Course table
   //String  course_Group = propertyReader.readTestData("Course_Group_SplunkDev_Admin");
            // Verify Data for Inquiry
            String event = propertyReader.readTestData("Inquiry_Event");
            String event_Type=propertyReader.readTestData("CountryIndia");
            String level_id = propertyReader.readTestData("HomePage_Signup_level_id");
            addLog("Verify Data for Inquiry");
            selectedCoursePage=selectedCoursePage.verifyDataForInquiry(course_id,webSiteAction,event,campaignName,course_group,event_Type,level_id,email,username);

            // Verify Data in User Event Table
            //String event_Type=propertyReader.readTestData("EventType");
            
        }
        catch (final Error e) {
            captureScreenshot("test_020InquiryFromCourseLandingPage");
            throw e;
        } 
        catch (final Exception e) {
            captureScreenshot("test_020InquiryFromCourseLandingPage");
            throw e;
        }
    }
}