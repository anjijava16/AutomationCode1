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

public class VerifyFriendsReferredInsideReferralTab extends DriverTestCase{
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
    static String referralLink;
    static String courseName;
    static String currency;
    static String bankName;
    static String  referralEmail_2;
    static String  referralEmail_3;
    static String  referralEmail_4;
    static String  referralEmail_5;
    static String enorlled_Count;

    @Test
    public void test_013VerifyFriendsReferredInsideReferralTab() throws Exception {

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
            String name = signInModalPage.RuntimeUserName();
            String email = signInModalPage.RuntimeUserEmail();
            
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

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Enter Email
            referralEmail_1= randomString(3)+"@tech.edureka.in";
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail_1);
            addLog("Enter email "+referralEmail_1);
            myProfilePage=myProfilePage.referFriend(referralEmail_1);

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            // Verify Close refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Refresh Browser
            addLog("Refresh Browser");
            myProfilePage=myProfilePage.refreshBrowser();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Verify Refer friends points
            addLog("Verify Refer friends points");
            myProfilePage=myProfilePage.verifyReferredFriend();

            // Verify Referral count 
            email= propertyReader.readRunTimeData("Email_Id");
            String referral_Count =propertyReader.readRunTimeData("ReferredFriendCount");
            myProfilePage=myProfilePage.verifyReferralCountForRefferalProcess(email, referral_Count);

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Enter Email
            referralEmail_2= randomString(3)+"@tech.edureka.in";
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail_2);
            addLog("Enter email "+referralEmail_2);
            myProfilePage=myProfilePage.referFriend(referralEmail_2);

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralEmail_2);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            // Refresh Browser
            addLog("Refresh Browser");
            myProfilePage=myProfilePage.refreshBrowser();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Verify Refer friends points
            addLog("Verify Refer friends points");
            myProfilePage=myProfilePage.verifyReferredFriend();

            // Verify Referral count 
            referral_Count =propertyReader.readRunTimeData("ReferredFriendCount");
            myProfilePage=myProfilePage.verifyReferralCountForRefferalProcess(email, referral_Count);

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Enter Email
            referralEmail_3= randomString(3)+"@tech.edureka.in";
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail_2);
            addLog("Enter email "+referralEmail_2);
            myProfilePage=myProfilePage.referFriend(referralEmail_2);

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralEmail_2);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            // Refresh Browser
            addLog("Refresh Browser");
            myProfilePage=myProfilePage.refreshBrowser();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Verify Refer friends points
            addLog("Verify Refer friends points");
            myProfilePage=myProfilePage.verifyReferredFriend();

            // Verify Referral count 
            referral_Count =propertyReader.readRunTimeData("ReferredFriendCount");
            myProfilePage=myProfilePage.verifyReferralCountForRefferalProcess(email, referral_Count);

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Enter Email
            referralEmail_4= randomString(3)+"@tech.edureka.in";
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail_4);
            addLog("Enter email "+referralEmail_2);
            myProfilePage=myProfilePage.referFriend(referralEmail_4);

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralEmail_2);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            // Refresh Browser
            addLog("Refresh Browser");
            myProfilePage=myProfilePage.refreshBrowser();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Verify Refer friends points
            addLog("Verify Refer friends points");
            myProfilePage=myProfilePage.verifyReferredFriend();

            // Verify Referral count 
            referral_Count =propertyReader.readRunTimeData("ReferredFriendCount");
            myProfilePage=myProfilePage.verifyReferralCountForRefferalProcess(email, referral_Count);

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Enter Email
            referralEmail_5= randomString(3)+"@tech.edureka.in";
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail_2);
            addLog("Enter email "+referralEmail_2);
            myProfilePage=myProfilePage.referFriend(referralEmail_2);

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralEmail_2);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            // Close to the referral popup
            addLog("Close to the referral popup");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Verify Refer friends points
            addLog("Verify Refer friends points");
            myProfilePage=myProfilePage.verifyReferredFriend();

            // Verify Referral count 
            referral_Count =propertyReader.readRunTimeData("ReferredFriendCount");
            myProfilePage=myProfilePage.verifyReferralCountForRefferalProcess(email, referral_Count);

        }
        catch (Error e) {
            captureScreenshot("test_013VerifyFriendsReferredInsideReferralTab");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_013VerifyFriendsReferredInsideReferralTab");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_013VerifyFriendsReferredInsideReferralTab"})
    public void test_014VerifyValueOfEnrolmentsNumberInsideReferral() throws Exception {
        try {

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

            //Sign up user_1 who is refferrad by A in TC_001
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

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Click on Profile Dropdown
            Thread.sleep(5000);
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();

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

            // Verify value of enrolments numbers
            addLog("Verify value of enrolments numbers");
            myProfilePage=myProfilePage.verifyEnorlNumbers();

            // Verify enrolled count in Ambassador Transaction table
            enorlled_Count= propertyReader.readRunTimeData("Enroll_Count");
            addLog("Verify enrolled count in Ambassador Transaction table");
            myProfilePage=myProfilePage.verifyDataIn_AmbassadorTransactionTable(email,enorlled_Count);


            //*************************User_2*******************************************

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

            //Sign up user_2 who is refferrad by A in TC_001
            userName = randomString(5);
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail_2);
            addLog("Sign up user_2 who is refferrad by A in TC_001");
            signInModalPage=signInModalPage.enterUserNameEmailAndPhoneNumnber(userName,referralEmail_2,phNumber);
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

            // Click on Profile Dropdown
            Thread.sleep(5000);
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();

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

            // Verify value of enrolments numbers
            addLog("Verify value of enrolments numbers");
            myProfilePage=myProfilePage.verifyEnorlNumbers();

            // Verify enrolled count in Ambassador Transaction table
            enorlled_Count= propertyReader.readRunTimeData("Enroll_Count");
            addLog("Verify enrolled count in Ambassador Transaction table");
            myProfilePage=myProfilePage.verifyDataIn_AmbassadorTransactionTable(email,enorlled_Count);

            //********************** User_3 ************************************************************
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

            //Sign up user_3 who is refferrad by A in TC_001
            userName = randomString(5);
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail_3);
            addLog("Sign up user_3 who is refferrad by A in TC_001");
            signInModalPage=signInModalPage.enterUserNameEmailAndPhoneNumnber(userName,referralEmail_3,phNumber);
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

            // Click on Profile Dropdown
            Thread.sleep(5000);
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();

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

            // Verify value of enrolments numbers
            addLog("Verify value of enrolments numbers");
            myProfilePage=myProfilePage.verifyEnorlNumbers();

            // Verify enrolled count in Ambassador Transaction table
            enorlled_Count= propertyReader.readRunTimeData("Enroll_Count");
            addLog("Verify enrolled count in Ambassador Transaction table");
            myProfilePage=myProfilePage.verifyDataIn_AmbassadorTransactionTable(email,enorlled_Count);
        }
        catch (Error e) {
            captureScreenshot("test_014VerifyValueOfEnrolmentsNumberInsideReferral");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_014VerifyValueOfEnrolmentsNumberInsideReferral");
            throw e;
        }
    }
}