package com.edureka.others;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class CourseOrderingOnAllCoursesPages extends DriverTestCase{
    private DashboardPage dashboardPage;
    private AllCoursesPage allCoursesPage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;

    private static String allCourse;
    @Test
    public void test_004VerifyClassRecordingOnCLP() throws Exception {

        try {
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            // Sign up User
            String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            //  Select AllCourse Tab from Courses dropdown
            allCourse = propertyReader.readTestData("AllCourse");
            addLog("Select "+allCourse+" Tab from Courses dropdown");
            allCoursesPage= userHomePage.selectCourseOption(AllCoursesPage.class, allCourse);

            // Verify All Courses page
            addLog("Verify All Courses page");
            allCoursesPage= allCoursesPage.verifyAllCoursesPage();

            // Click on Grid view Icon
            addLog("Click on Grid view Icon");
            allCoursesPage=allCoursesPage.clickOnGridViewIcon();

            // Verify Courses are appearing in Grid view
            addLog("Verify Courses are appearing in Grid view");
            allCoursesPage=allCoursesPage.verifyCourseInGridView();

            // Click on List view Icon
            addLog("Click on List view Icon");
            allCoursesPage=allCoursesPage.clickOnListViewIcon();

            // Verify Courses are appearing in List view
            addLog("Verify Courses are appearing in List view");
            allCoursesPage=allCoursesPage.verifyCourseInListView();

        }   catch (final Error e) {
            captureScreenshot("test_004VerifyClassRecordingOnCLP");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_004VerifyClassRecordingOnCLP");
            throw e;
        }
    }
}
