package com.edureka.searchFunctionality;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class SearchBasedOnBatchType extends DriverTestCase{
    private DashboardPage dashboardPage;
    private AllCoursesPage allCoursesPage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;

    private static String allCourse;

    @Test
    public void test_003SearchBasedOnBatchType() throws Exception {

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

            // Check Batch Type
            String batchType= propertyReader.readTestData("Batch_Type_Weekend");
            addLog("Check "+batchType+" Check box in Search Category");
            allCoursesPage = allCoursesPage.selectBatchType(batchType);

            // Verify Selected filter is appread in Searched Result
            String batchFilter = propertyReader.readTestData("Filter_Weekend");
            addLog("Verify Selected "+batchFilter+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(batchFilter);

            // Verify Dispalyed courses based on Weekend
            String queryForWeekend_Batches= propertyReader.readTestData("Query_WeekendBatch");
            addLog("Verify Dispalyed courses based on "+batchType);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForWeekend_Batches);

            // Check Batch Type
            batchType= propertyReader.readTestData("Batch_Type_WeekDays");
            addLog("Check "+batchType+" Check box in Search Category");
            allCoursesPage = allCoursesPage.selectBatchType(batchType);

            // Verify Selected filter is appread in Searched Result
            batchFilter = propertyReader.readTestData("Filter_WeekDays");
            addLog("Verify Selected "+batchFilter+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(batchFilter);

            // Verify Dispalyed courses based on Weekdays
            String queryForWeekDays_Batches= propertyReader.readTestData("Query_WeekDaysBatach");
            addLog("Verify Dispalyed courses based on "+batchType);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForWeekDays_Batches);


        }   catch (final Error e) {
            captureScreenshot("test_003SearchBasedOnBatchType");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_003SearchBasedOnBatchType");
            throw e;
        }
    }
}