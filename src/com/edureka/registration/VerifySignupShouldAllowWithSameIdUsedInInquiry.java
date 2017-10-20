package com.edureka.registration;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifySignupShouldAllowWithSameIdUsedInInquiry extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;

    @Test
    public void test_033VerifySignupShouldAllowWithSameIdUsedInInquiry() throws Exception {

        try {
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();
            
            
            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Select Course
            addLog("Select Course");
            selectedCoursePage=dashboardPage.selectCourse_Trending();
            // Click on Query Box
            addLog("Click on Query Box");
            selectedCoursePage=selectedCoursePage.clickOnQueryBox();

            // Submit inquiry in drop query box
            addLog("Submit inquiry in drop query box");
            selectedCoursePage=selectedCoursePage.sendQuery(SelectedCoursePage.class);

            // Verify Inquiry has been submitted
            addLog("Verify Inquiry has been submitted");
            selectedCoursePage=selectedCoursePage.verifySumbitInquiry();

            // Click on Sign in header
            signInModalPage=selectedCoursePage.clickSignInHeader();
            addLog(" Click on Sign in header");

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();
            
            
            // Singup application with same od which is used in inquiry
            addLog("Singup application with same od which is used in inquiry");
            userHomePage=signInModalPage.signUpForSubmittedQuery();

        /*    // Verify that when an inquiry with fresh/new email id is entered, signUp should be allowed with the same email id
            addLog("Verify that when an inquiry with fresh/new email id is entered, signUp should be allowed with the same email id");
            String a = selectedCoursePage.Random_UserName();
        */}
        catch (final Error e) {
            captureScreenshot("test_033VerifySignupShouldAllowWithSameIdUsedInInquiry");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_033VerifySignupShouldAllowWithSameIdUsedInInquiry");
            throw e;
        }
    }
}
