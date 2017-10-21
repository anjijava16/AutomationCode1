package com.edureka.referralProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.MyProfilePage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.RazorPayPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class IfOneExistingUserRefersToAnotherExistingUser extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;
    private RazorPayPage razorPayPage;
    private MyProfilePage myProfilePage;

    static String email;
    static String userName;
    static String referralEmail_1;
    static String referralLink_1;
    static String referralLink_2;
    static String courseName;
    static String currency;
    static String bankName;
    static String  referralEmail_2;

    @Test
    public void test_027ValidateUserCPurchaseCourseThenUserBGetPointNotA() throws Exception {

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
            String username = signInModalPage.RuntimeUserName();
            String email = signInModalPage.RuntimeUserEmail();
            String phone = signInModalPage.RuntimeUserPhNum();

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Select Course
            courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course " + courseName);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

            // Verify Razor page
            addLog("Verify Razor page");
            razorPayPage=razorPayPage.verifyPage(email);

            // Click on Net Banking tab
            addLog("Click on Net Banking tab");
            razorPayPage=razorPayPage.clickOnNetBankingTab();

            // Select bank
            bankName = propertyReader.readTestData("Bank");
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

            // Click on Profile Dropdown
            userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            //Sign up user_1 who is refferrad by A in TC_001
            referralEmail_1= randomString(3)+"@tech.edureka.in";
            userName = propertyReader.readTestData("UserName");
            String phNumber = propertyReader.readTestData("PhoneNumber");
            String password = propertyReader.readTestData("Password");
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail_1);
            addLog("Sign up user_1 who is refferrad by A in TC_001");
            signInModalPage=signInModalPage.enterUserNameEmailAndPhoneNumnber(userName,referralEmail_1,phNumber);
            signInModalPage=signInModalPage.enterPassword(password);

            // Click on Sign create Account button
            addLog("Click on Sign create Account button");
            userHomePage=signInModalPage.clickOnCreateAccountButton();

            //Verify Edureka logo at at Home page
            addLog("Verify Edureka logo at at All Courses page");
            userHomePage=userHomePage.verifyEdurekaLogoOnHomePage();

            // Select Course
            courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course " +courseName);
            Thread.sleep(5000);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

            // Verify Razor page
            addLog("Verify Razor page");
            Thread.sleep(5000);
            razorPayPage=razorPayPage.verifyRazorForReferral();

            // Click on Net Banking tab
            addLog("Click on Net Banking tab");
            razorPayPage=razorPayPage.clickOnNetBankingTab();

            // Select bank
            addLog("Select Bank" +bankName +" from bank table ");
            razorPayPage=razorPayPage.selectBank(bankName);

            // Click on pay button
            addLog("Click on pay button");
            razorPayPage=razorPayPage.clickOnPayButton();

            // Get Parent window Id
            addLog("Get Parent Window ID");
            parentWidnow = getParentWindowHandle();

            // Switch Child Window and Click on Succss button
            addLog("Switch Child Window and Click on Succss button");
            switchPreviewWindow();
            myProfilePage=razorPayPage.clickOnSuccessButton();

            // Switch to Parent Window and verify my Profile page
            addLog("Switch to Parent Window and verify my Profile page");
            switchParentWindow(parentWidnow);

            // Verify My Profile Page
            addLog("Verify My Profile Page");
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Click on Profile Dropdown
            Thread.sleep(5000);
            addLog("Click on Profile Dropdown");
            userHomePage=userHomePage.clickOnProfileDropdown();  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = userHomePage.logout();  

            // Click on Sign Header
            addLog("Click on Sign Header");
            signInModalPage=dashboardPage.clickSignInHeader();

            // login user A
            addLog("Login user A");
            userHomePage= signInModalPage.loginApp();

            //Verify User Page
            addLog("Verify User Page");
            userHomePage = userHomePage.verifyUserPage();

            // Click on Profile Dropdown
            userName = propertyReader.readRunTimeData("UserName");
            addLog("Click profile dropdown");
            userHomePage= userHomePage.clickOnProfileDropdown();

            // Select My Profile
            addLog("Select My Profile");
            myProfilePage=userHomePage.clickOnMyProfile();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Click on Refer Button
            addLog("Click on Refer Button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Enter Email
            addLog("Enter email "+referralEmail_1);
            myProfilePage=myProfilePage.referFriend(referralEmail_1);

            // Get Referral Link
            referralLink_1 = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink_1);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            // Verify Close refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Get User A credit Points
            email= propertyReader.readRunTimeData("Email_Id");
            addLog("Get User A credit Points");
            myProfilePage=myProfilePage.getAvailablePointsOfUserAndUserB(email, referralEmail_1);

            // Click on Profile Dropdown
            userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // Navigate to the Referral Link
            addLog("Navigate to the "+referralLink_1);
            dashboardPage=dashboardPage.changeUrl(referralLink_1);

            // Click on Sign Header
            addLog("Click on Sign Header");
            signInModalPage=dashboardPage.clickSignInHeader();

            // Login Application through Customer B
            addLog("Login Application through Customer B");
            signInModalPage= signInModalPage.enterEmailAndPassword(referralEmail_1, password);

            // Click on Start Learning Course button
            addLog("Click on Start Learning Course button");
            userHomePage=signInModalPage.clickStartLearningButton(UserHomePage.class);

            // Select Course
            courseName = propertyReader.readTestData("ApacheSpark&Scala");
            addLog("Select Course " +courseName);
            Thread.sleep(5000);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
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
            Thread.sleep(5000);
            razorPayPage=razorPayPage.verifyRazorForReferral();

            // Click on Net Banking tab
            addLog("Click on Net Banking tab");
            razorPayPage=razorPayPage.clickOnNetBankingTab();

            // Select bank
            addLog("Select Bank" +bankName +" from bank table ");
            razorPayPage=razorPayPage.selectBank(bankName);

            // Click on pay button
            addLog("Click on pay button");
            razorPayPage=razorPayPage.clickOnPayButton();

            // Get Parent window Id
            addLog("Get Parent Window ID");
            parentWidnow = getParentWindowHandle();

            // Switch Child Window and Click on Succss button
            addLog("Switch Child Window and Click on Succss button");
            switchPreviewWindow();
            myProfilePage=razorPayPage.clickOnSuccessButton();

            // Switch to Parent Window and verify my Profile page
            addLog("Switch to Parent Window and verify my Profile page");
            switchParentWindow(parentWidnow);

            // Verify My Profile Page
            addLog("Verify My Profile Page");
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Click on Profile Dropdown
            Thread.sleep(5000);
            userName = propertyReader.readTestData("UserName");
            addLog("Click on Profile Dropdown");
            userHomePage=userHomePage.clickOnProfileDropdown();  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = userHomePage.logout();  

            // Click on Sign Header
            addLog("Click on Sign Header");
            signInModalPage=dashboardPage.clickSignInHeader();

            // login user A
            addLog("Login user A");
            userHomePage= signInModalPage.loginApp();

            //Verify User Page
            addLog("Verify User Page");
            userHomePage = userHomePage.verifyUserPage();

            // Click on Profile Dropdown
            userName = propertyReader.readRunTimeData("UserName");
            addLog("Click profile dropdown");
            userHomePage= userHomePage.clickOnProfileDropdown();

            // Select My Profile
            addLog("Select My Profile");
            myProfilePage=userHomePage.clickOnMyProfile();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            /* addLog("Verify when user C purchases the course then customer B gets the credit points");
            myProfilePage = myProfilePage.verifyEarningCreditPoints();*/

            myProfilePage=myProfilePage.compareCreditPointAfterReferExistingCustomer(email);
        }

        catch (final Error e) {
            captureScreenshot("test_027ValidateUserCPurchaseCourseThenUserBGetPointNotA");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_027ValidateUserCPurchaseCourseThenUserBGetPointNotA");
            throw e;
        }
    }
}
