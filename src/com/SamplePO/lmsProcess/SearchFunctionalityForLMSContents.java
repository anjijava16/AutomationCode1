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

public class SearchFunctionalityForLMSContents  extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;
    private RazorPayPage razorPayPage;
    private MyProfilePage myProfilePage;
    private MyCourses myCourses;

    static String course_Id;
    static String campaignSource;
    static String campaignName;
    static String campaignMedium;
    @Test
    public void test_011SearchFunctionalityForLMSContents() throws Exception {

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
          //  razorPayPage=razorPayPage.verifyPage();

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
          //  myCourses=myCourses.dataBaseVerificationInUserTable("1");

            // Verify Data in User Lead Table
            course_Id = propertyReader.readTestData("Course_ID_BigData");
            String webSiteAction= propertyReader.readTestData("Event_Enroll");
            String country= propertyReader.readTestData("CountryIndia");
            addLog("Verify Data in User Leads Table");
            campaignSource= propertyReader.readTestData("CampaignSource");
            campaignName= propertyReader.readTestData("CampaignName");
            campaignMedium= propertyReader.readTestData("CampaignMedium");
            addLog("Verify Data in User Lead Table");
        //    myCourses= myCourses.dataVerificationInUser_LeadsTable(course_Id, webSiteAction, country,campaignSource,campaignName,campaignMedium);

            // Verify Batch Id
            addLog("Verify Batch ID");
            myCourses=myCourses.dataBaseVerificationForLMS();

            // Click on Go To Course Tab
            addLog("Click on Go To Course Tab");
            myCourses = myCourses.clickOnGoToCourse();

            // Search Course 
            String search_Content = propertyReader.readTestData("SearchContent");
            addLog("Search content " +search_Content);
            myCourses=myCourses.searchContent(search_Content);

            // Verify User able to Search Course
            addLog("Verify User able to Search Course");
            myCourses=myCourses.verifySearchContent(search_Content);
            
            // Verify Data in User table
            addLog("Verify Data in User table");
        //    selectedCoursePage=selectedCoursePage.dataVerificationInUserTable("2");

        }
        catch (Error e) {
            captureScreenshot("test_011SearchFunctionalityForLMSContents");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_011SearchFunctionalityForLMSContents");
            throw e;
        }
    }
}