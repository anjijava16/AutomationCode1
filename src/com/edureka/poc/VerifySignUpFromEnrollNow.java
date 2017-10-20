package com.edureka.poc;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.util.DriverTestCase;
import com.edureka.util.Timer;

public class VerifySignUpFromEnrollNow extends DriverTestCase {

    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;

    @Test
    public void test_02VerifySignUpFromEnrollNow() throws Exception {

        try {
            Timer timer = new Timer();
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            timer.start();
            dashboardPage = applicationSetup();
            timer.end();
            addLog("Totaltime: " + timer.getTotalTime() + " seconds"); 

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Select Course From Trending Courses
            String coursename = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course From Trending Courses");
            selectedCoursePage=	dashboardPage.selectCourse(coursename);

            // Verify Selected Course Page Is Open
            addLog("Verify Selected Course Page Is Open");
            selectedCoursePage=	selectedCoursePage.verifySelectedCoursePage(coursename);

            //Click on Enroll Now Button
            addLog("Click on Enroll Now Button");
            signInModalPage=selectedCoursePage.clickOnEnrollButton(SignInModalPage.class);
         
            // Sign up user
            String domainName= propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            selectedCoursePage= signInModalPage.signUp(SelectedCoursePage.class,domainName);

            // Verify Sign up successfully
            addLog("Verify Sign up successfully");
            selectedCoursePage=selectedCoursePage.verifySignUpSuccessfully();

        }
        catch (final Error e) {
            captureScreenshot("test_02VerifySignUpFromEnrollNow");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_02VerifySignUpFromEnrollNow");
            throw e;
        }
    }



}



