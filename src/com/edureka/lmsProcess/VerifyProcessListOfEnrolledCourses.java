package com.edureka.lmsProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.MyCourses;
import com.edureka.pages.MyProfilePage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.RazorPayPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyProcessListOfEnrolledCourses extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;
    private RazorPayPage razorPayPage;
    private MyProfilePage myProfilePage;
    private MyCourses myCourses;

    static String campaignSource;
    static String campaignName;
    static String campaignMedium;
    static String course_Id;
    @Test
    public void test_001VerifyProcessListOfEnrolledCourses() throws Exception {

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

            String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Select Course
            String courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course" +courseName);
            selectedCoursePage=dashboardPage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            String currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for USD is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

            // Verify Razor page
            addLog("Verify Razor page");
       //razorPayPage=razorPayPage.verifyPage();

            // Click on Net Banking tab
            addLog("Click on Net Banking tab");
            razorPayPage=razorPayPage.clickOnNetBankingTab();

            // Select bank
            String bankName = propertyReader.readTestData("Bank");
            addLog("Select Bank" +bankName +" from bank table ");
            razorPayPage=razorPayPage.selectBank(bankName);

            // Click on pay button
            addLog("Click on pay button");
            razorPayPage=razorPayPage.clickOnPayButton();

            // Get Parent window Id
            addLog("Get Parent Window ID");
            String parentWidnow = getParentWindowHandle();

            // Switch Child Window and Click on Succss button
            addLog("Switch Child Window and Click on Succss button");
            switchPreviewWindow();
            myProfilePage=razorPayPage.clickOnSuccessButton();

            // Switch to Parent Window and verify my Profile page
            addLog("Switch to Parent Window and verify my Profile page");
            switchParentWindow(parentWidnow);
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Select My Courses from Courses Menu drop down
            String myCourseTab = propertyReader.readTestData("MyCourses");
            addLog("Select My Courses from Courses Menu Dropdown");
            myCourses = myProfilePage.selectCourseOption(MyCourses.class, myCourseTab);

            // Verify My courses Page
            addLog("Verify "+courseName+" course Page");
            myCourses = myCourses.verifyPage(courseName);

            // Verify Data in User table
            addLog("Verify Data in User table");
       //myCourses=myCourses.dataBaseVerificationInUserTable("1");

            // Verify Data in User Lead Table
            course_Id = propertyReader.readTestData("Course_ID_BigData");
            String webSiteAction= propertyReader.readTestData("Event_Enroll");
            String country= propertyReader.readTestData("CountryIndia");
            campaignSource= propertyReader.readTestData("CampaignSource");
            campaignName= propertyReader.readTestData("CampaignName");
            campaignMedium= propertyReader.readTestData("CampaignMedium");
            addLog("Verify Data in User Lead Table");
       //myCourses= myCourses.dataVerificationInUser_LeadsTable(course_Id, webSiteAction, country,campaignSource,campaignName,campaignMedium);

            // Verify Batch Id
            addLog("Verify Batch ID");
            myCourses=myCourses.dataBaseVerificationForLMS();
        }
        catch (Error e) {
            captureScreenshot("test_001VerifyProcessListOfEnrolledCourses");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_001VerifyProcessListOfEnrolledCourses");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_001VerifyProcessListOfEnrolledCourses"})
    public void test_002VerifyRespectiveCourseContents() throws Exception {

        try {

            // Click on Go To Course Tab
            addLog("Click on Go To Course Tab");
            myCourses = myCourses.clickOnGoToCourse();


            // Verify Respective Course Contents
            addLog("Verify Respective Course Contents");
            myCourses = myCourses.verifyCourseContent();

        }
        catch (Error e) {
            captureScreenshot("test_002VerifyRespectiveCourseContents");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_002VerifyRespectiveCourseContents");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_002VerifyRespectiveCourseContents"})
    public void test_003VerifyPlayRecording() throws Exception {

        try {

            // Click on Go To Course Tab
            addLog("Click on Go To Course Tab");
            myCourses = myCourses.playClassRecording();

            // Verify Batch Id
            addLog("Verify Batch ID");
            myCourses=myCourses.dataBaseVerificationForLMS();

            // Database verificatiom for Class Recording
            addLog("Database verificatiom for Class Recording");
            myCourses=myCourses.dataVerification_For_ClassRecordingVideo(course_Id);

        }
        catch (Error e) {
            captureScreenshot("test_003VerifyPlayRecording");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_003VerifyPlayRecording");
            throw e;
        }
    }
}
