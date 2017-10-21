package com.edureka.notification;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.MyProfilePage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.RazorPayPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyWebNotificationFunationality extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;
    private RazorPayPage razorPayPage;
    private MyProfilePage myProfilePage;

    static String email;
    static String userName;
    static String referralEmail;
    static String referralLink;

    @Test
    public void test_001VerifyWebNotificationFunationality() throws Exception {

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
            addLog("Select Course " + courseName);
            selectedCoursePage=userHomePage.selectCourse(courseName);

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
         //   orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

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

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Enter Email
            referralEmail= randomString(3)+"@tech.edureka.in";
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail);
            addLog("Enter email "+referralEmail);
            myProfilePage=myProfilePage.referFriend(referralEmail);

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Click on Profile Dropdown
            userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Verify Data in User table
            addLog("Verify Data in User table");
       //myProfilePage=myProfilePage.dataVerificationInUserTable("1");

            // Database verification for Referral Process
            email = propertyReader.readRunTimeData("Email_Id");
            addLog("Database verification for Referral Process");
          //  myProfilePage=myProfilePage.dataVerificationFor_ReferralProcess(email, userName, referralLink);

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();

            // Navigate to the Referral Link
            addLog("Navigate to the "+referralLink);
            dashboardPage=dashboardPage.changeUrl(referralLink);

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            //Sign up
            userName = propertyReader.readTestData("UserName");
            email = propertyReader.readRunTimeData("RefferralEmail");
            String phNumber = propertyReader.readTestData("PhoneNumber");
            String password = propertyReader.readTestData("Password");
            addLog("Sign up user B who is refferrad by A in TC_001");
            signInModalPage=signInModalPage.enterUserNameEmailAndPhoneNumnber(userName,email,phNumber);
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
            selectedCoursePage=userHomePage.selectCourse(courseName);

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
            currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for USD is equal to Net price
            addLog("verify Total amount");
         //orderSummaryPage.verifyTotalAmount(currency);

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
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Verify Data in User table
            addLog("Verify Data in User table");
//     myProfilePage=myProfilePage.dataVerificationInUserTable("1");

            // Database verification for Referral Process
            addLog("Database verification for Referral Process");
      //      myProfilePage=myProfilePage.dataVerificationFor_ReferralProcess(email, userName, referralLink);

            // Click on Profile Dropdown
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

            // login user A
            addLog("Login user A");
            userHomePage= signInModalPage.loginApp();

            //Verify User Page
            addLog("Verify User Page");
            userHomePage = userHomePage.verifyUserPage();

            // Verify Notification
            addLog("Verify Notifications");
            userHomePage=userHomePage.verifyNotification();
        }
        catch (Error e) {
            captureScreenshot("test_001VerifyWebNotificationFunationality");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_001VerifyWebNotificationFunationality");
            throw e;
        }
    }
}