package com.edureka.poc;

import org.testng.annotations.Test;
import com.edureka.util.DriverTestCase;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;

public class VerifySignUpFromClassRecording extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;

    @Test
    public void test_03VerifySignUpFromClassRecording() throws Exception {

        try {

            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            //Select a course
            String course= propertyReader.readTestData("DataScienceCourse");
            selectedCoursePage= dashboardPage.selectCourse(course);

            // Verify Selected Course Page Is Open
            addLog("Verify Selected Course Page Is Open");
            selectedCoursePage.verifySelectedCoursePage(course);

            // Click on Play Button of Video
            addLog("Click on Play Button of Video");
            signInModalPage=selectedCoursePage.playClassRecording(SignInModalPage.class);

            // Verify default SignUp is selected
            addLog("Verify default SignUp is selected");
            signInModalPage= signInModalPage.verifySignUpIsDefault();

            // Sign up user
            String domainName= propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            selectedCoursePage= signInModalPage.signUp(SelectedCoursePage.class, domainName);

            // Verify User Home Page
            addLog("Verify User Home Page");
            selectedCoursePage=selectedCoursePage.verifySignUpSuccessfully();
            
        }

        catch (final Error e) {
            captureScreenshot("test_03VerifySignUpFromClassRecording");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_03VerifySignUpFromClassRecording");
            throw e
            ;
        }
    }


}
