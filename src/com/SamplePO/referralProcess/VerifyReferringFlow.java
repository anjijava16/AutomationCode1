package com.edureka.referralProcess;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.MyProfilePage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.RazorPayPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class VerifyReferringFlow extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;
    private RazorPayPage razorPayPage;
    private MyProfilePage myProfilePage;
    private AllCoursesPage allCoursesPage;

    static String email_A;
    static String username_A;
    static String password_A;
    static String phone_A;
    static String referralEmail;
    static String referralLink;
    static String levelId;
    static String welcomemail;
    static String ambassador_Campaign_Id;
    static String ambassador_Level_Id;
    static String source;
    static String campaign_id ;

    @Test
    public void test_001VerifyRefferringFlow() throws Exception {

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
            username_A = signInModalPage.RuntimeUserName();
            email_A = signInModalPage.RuntimeUserEmail();
            phone_A = signInModalPage.RuntimeUserPhNum();
            
            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyRandUserLoggedIn(username_A);

            // Select Course
            String courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course " + courseName);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Data in User table
            addLog("Verify Data in User table");
            orderSummaryPage=orderSummaryPage.dataVerificationInUserTable(email_A,username_A,phone_A);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            String currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

            // Verify Razor page
            addLog("Verify Razor page");
            razorPayPage=razorPayPage.verifyPage(email_A);

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
            Thread.sleep(3000);
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

            // Get page url
            String page_Url = myProfilePage.getUrl();
            addLog("Get Page url: "+page_Url);

            // Database verification for Referral Process
            String email = propertyReader.readRunTimeData("Email_Id");
            levelId = propertyReader.readTestData("Referral_Level_Id");
            welcomemail = propertyReader.readTestData("Referral_Welcome_Mail");
            ambassador_Campaign_Id = propertyReader.readTestData("Referral_Campaign_Id");
            ambassador_Level_Id = propertyReader.readTestData("Referral_ambassador_level_id");
            source = propertyReader.readTestData("Email_Refer_Source");
            campaign_id = propertyReader.readTestData("Referral_Campaign_Id");
            addLog("Database verification for Referral Process");
            myProfilePage=myProfilePage.dataVerificationFor_ReferralProcess(email,referralLink, levelId,welcomemail, ambassador_Campaign_Id, ambassador_Level_Id,source,page_Url,campaign_id);

            // Click on Profile Dropdown
 //           userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(username_A);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();

            // Navigate to the Referral Link
            addLog("Navigate to the "+referralLink);
            dashboardPage=dashboardPage.changeUrl(referralLink);


        }
        catch (Error e) {
            captureScreenshot("test_001VerifyRefferringFlow");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_001VerifyRefferringFlow");
            throw e;
        }
    }

    @Test(dependsOnMethods={"test_001VerifyRefferringFlow"})
    public void test_002RefferralActivities() throws Exception {

        try {

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
          String  userName = propertyReader.readTestData("UserName");
            referralEmail = propertyReader.readRunTimeData("RefferralEmail");
            String phNumber = propertyReader.readTestData("PhoneNumber");
            String password = propertyReader.readTestData("Password");
            addLog("Sign up user B who is refferrad by A in TC_001");
            signInModalPage=signInModalPage.enterUserNameEmailAndPhoneNumnber(userName,referralEmail,phNumber);
            signInModalPage=signInModalPage.enterPassword(password);

            // Click on Sign create Account button
            addLog("Click on Sign create Account button");
            userHomePage=signInModalPage.clickOnCreateAccountButton();

/*    //Verify Edureka logo at at Home page
    addLog("Verify Edureka logo at at All Courses page");
    userHomePage=userHomePage.verifyEdurekaLogoOnHomePage();
*/
            // Select Course
            String courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course " +courseName);
            Thread.sleep(5000);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Verify Data in User table
            addLog("Verify Data in User table");
            orderSummaryPage=orderSummaryPage.dataVerificationForReferredUserInUserTable("3",email_A);

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

//// Verify total Amount for USD is equal to Net price
//addLog("verify Total amount");
//orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

            // Verify Razor page
            Thread.sleep(5000);
            addLog("Verify Razor page");
            razorPayPage=razorPayPage.verifyRazorForReferral();

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
            Thread.sleep(3000);
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Database verification for referres user
            source = propertyReader.readTestData("Activity_Type_Referred_User");
            addLog("Database verification for referres user");
            myProfilePage=myProfilePage.dataVerificationForReferredUser(referralEmail, levelId, welcomemail, ambassador_Campaign_Id, ambassador_Level_Id,source,campaign_id);

            // Click on Profile Dropdown
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();
        }
        catch (Error e) {
            captureScreenshot("test_002RefferralActivities");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_002RefferralActivities");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_002RefferralActivities"})
    public void test_003VerifyEarningCredtiPoints() throws Exception {
        try {


            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // login user A
            addLog("Login user A");
            signInModalPage= signInModalPage.enterEmailAndPassword(email_A, password_A);
            userHomePage = signInModalPage.clickStartLearningButton(UserHomePage.class);
            		
            		
            //Verify User Page
            addLog("Verify User Page");
            userHomePage = userHomePage.verifyUserPage();

            // Click on Profile Dropdown
           // userName = propertyReader.readRunTimeData("UserName");
            addLog("Click profile dropdown");
            userHomePage= userHomePage.clickOnProfileDropdown();

            // Select My Profile
            addLog("Select My Profile");
            myProfilePage=userHomePage.clickOnMyProfile();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Verify Earing Credit Points
            addLog("Verify Earing Credit Points");
            myProfilePage=myProfilePage.verifyEarningCreditPoints();

            // Click on Refer Friend button
            Thread.sleep(3000);
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Get page url
            String page_Url = myProfilePage.getUrl();
            addLog("Get Page url: "+page_Url);

            // Database verification for Referral Process
            levelId = propertyReader.readTestData("Referral_Level_Id");
            welcomemail = propertyReader.readTestData("Referral_Welcome_Mail");
            ambassador_Campaign_Id = propertyReader.readTestData("Referral_Campaign_Id");
            ambassador_Level_Id = propertyReader.readTestData("Referral_ambassador_level_id");
            campaign_id = propertyReader.readTestData("Referral_Campaign_Id");
            source = propertyReader.readTestData("Email_Refer_Source");
            String email = propertyReader.readRunTimeData("Email_Id");
            addLog("Database verification for Referral Process");
            myProfilePage=myProfilePage.dataVerificationFor_ReferralProcess(email,referralLink, levelId,welcomemail, ambassador_Campaign_Id, ambassador_Level_Id,source,page_Url,campaign_id);

            // Get Credit Points
            addLog("Get Credit Points");
            String points = myProfilePage.getCreditPoints();

            // Verify Data for referral 
            email = propertyReader.readRunTimeData("Email_Id");
            double creditPoints = Integer.parseInt(points);
            double points_1 = creditPoints/60;
            String earning_Credit_Points= Double.toString(points_1);
            addLog("Verify Data for referral ");
   //  myProfilePage=myProfilePage.VerificationCreditPointsInAmbassadorsTableReferralProcess(email, earning_Credit_Points);

            // Verify AMBASSADOR_TRANSACTIONS User_id refferer_id level_id and campaign_id (taken from AMBASSADOR) , order_id(taken fron post_orders), revenue, value
            email = propertyReader.readRunTimeData("RefferralEmail");
            addLog("Verify AMBASSADOR_TRANSACTIONS User_id refferer_id level_id and campaign_id (taken from AMBASSADOR) , order_id(taken fron post_orders), revenue, value");
            myProfilePage=myProfilePage.verifyData_InAmassador_Transaction_ForReferral(email, "166");

        }
        catch (Error e) {
            captureScreenshot("test_003VerifyEarningCredtiPoints");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_003VerifyEarningCredtiPoints");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_003VerifyEarningCredtiPoints"})
    public void test_005VrifyRedemptionOFCreditPoints() throws Exception {
        try {

            // Verify Redeemption 
            addLog("Verify Redeemption");
            allCoursesPage = myProfilePage.clickOnRedeemButton();

            // Verify All Course Page
            addLog("Verify All Course Page");
            allCoursesPage=allCoursesPage.verifyAllCoursesPage();

            // Select Course
            String courseName = propertyReader.readTestData("ApacheSpark&Scala");
            addLog("Select Course "+courseName);
            selectedCoursePage=allCoursesPage.selectCourseFromGridView(courseName);

            // Verify Selected course should be open.
            addLog("Verify "+courseName+" course should be open");
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

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

            // Verify Razor page
            addLog("Verify Razor page");
            razorPayPage=razorPayPage.verifyPage(email_A);

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

            // Verify Redeemption Credit Points
            addLog("Verify Redeemption Credit Points");
            myProfilePage=myProfilePage.verifyRedeemptionCreditPoints();

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Redemption of credit points
            String email = propertyReader.readRunTimeData("Email_Id");
            String course_Id = propertyReader.readTestData("Course_ID_ApacheSpark&Scala");
            addLog("Redemption of credit points");
            myProfilePage=myProfilePage.verifyData_For_redemptions_In_ReferralProcess(email, course_Id);
        }
        catch (Error e) {
            captureScreenshot("test_005VrifyRedemptionOFCreditPoints");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_005VrifyRedemptionOFCreditPoints");
            throw e;
        }
    }
}
