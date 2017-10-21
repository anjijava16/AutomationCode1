package com.edureka.searchFunctionality;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class SearchBasedOnTrainingType extends DriverTestCase{
    private DashboardPage dashboardPage;
    private AllCoursesPage allCoursesPage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;

    private static String allCourse;

    @Test
    public void test_004SearchBasedOnTrainingType() throws Exception {

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

            // Check Training Type
            String trainingType= propertyReader.readTestData("Training_Type_Live_Instructor");
            addLog("Check "+trainingType+" Check box in Search Category");
            allCoursesPage = allCoursesPage.selectTrainingType(trainingType);

            // Verify Selected filter is appread in Searched Result
            String trainingFilter = propertyReader.readTestData("Training_Filter_Instructor");
            addLog("Verify Selected "+trainingFilter+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(trainingFilter);

            // Verify Dispalyed courses based on Live Instructor Led
            String queryForLed_Instructor= propertyReader.readTestData("Query_ForLiveInstructor");
            addLog("Verify Dispalyed courses based on "+trainingType);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForLed_Instructor);

            // Check Training Type
            trainingType= propertyReader.readTestData("Training_Self_paced");
            addLog("Check "+trainingType+" Check box in Search Category");
            allCoursesPage = allCoursesPage.selectTrainingType(trainingType);

            // Verify Selected filter is appread in Searched Result
            trainingFilter= propertyReader.readTestData("Training_Filter__Selfpaced");
            addLog("Verify Selected "+trainingFilter+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(trainingFilter);

            // Verify Dispalyed courses based on Live Instructor Led
            String queryFor_Self_paced= propertyReader.readTestData("Query_For_Self_paced");
            addLog("Verify Dispalyed courses based on "+trainingType);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryFor_Self_paced);


        }   catch (final Error e) {
            captureScreenshot("test_004SearchBasedOnTrainingType");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_004SearchBasedOnTrainingType");
            throw e;
        }
    }
}