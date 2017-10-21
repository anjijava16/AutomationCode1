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

public class VerifyReferralProcessWhenNoneOfOfferIsPresent extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;
    private RazorPayPage razorPayPage;
    private MyProfilePage myProfilePage;
    private AllCoursesPage allCoursesPage;

    static String email;
    static String userName;
    static String referralEmail;
    static String referralLink;
    static String courseName;
    static String currency;
    static String bankName;

    @Test
    public void test_022VerifyReferralProcessWhenNoneOfOfferIsPresent() throws Exception {

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
            String email = signInModalPage.RuntimeUserEmail();
            String username = signInModalPage.RuntimeUserName();
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
            Thread.sleep(5000);
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
            Thread.sleep(5000);
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

            // Verify Addtional Referred Discount
            addLog("Verify Addtional Referred Discount");
            dashboardPage = dashboardPage.verifyAdditonalDiscountBanner();

            // Verify final price on Dashboard after offer discount and referral discount value
            String percentageValue="0";
            String courseName = propertyReader.readTestData("BigData&Hadoop");
            String referral_Discount_Value=propertyReader.readTestData("Referral_Discount_Vaule");
            addLog("Verify final price on Dashboard after offer discount and referral discount value");
            dashboardPage=dashboardPage.verifyCoursePriceAfterReferralAndOfferDiscount(courseName,percentageValue, referral_Discount_Value);

            // Click on Course Tab
            addLog("Click on Course Tab");
            allCoursesPage= dashboardPage.clickOnCoursesTab();

            // Click on List view Icon
            addLog("Click on List view Icon");
            allCoursesPage=allCoursesPage.clickOnListViewIcon();

            // Verify Tool Tip message 
            String course_Id = propertyReader.readTestData("Course_ID_BigData");
            String tool_Tip_Message = propertyReader.readTestData("Tool_Tip_Referral_Message");
            addLog("Verify ToolTip message " +tool_Tip_Message+ " and select course Id " +course_Id);
            allCoursesPage=allCoursesPage.verifyToolTipMessage(course_Id, tool_Tip_Message);

            // Verify final price on All courses page after offer discount and referral discount
            addLog("Verify final price on All courses page after offer discount and referral discount");
            allCoursesPage=allCoursesPage.verifyDiscountPriceValue(course_Id, percentageValue, referral_Discount_Value);

            // Change Currency
            String currency= propertyReader.readTestData("USDCurrency");
            addLog("Select "+currency+" from currency dropdown");
            allCoursesPage= allCoursesPage.changeCurrency(currency);

            // Verify final price after offer discount and referral discount
            addLog("Verify final price on All courses page after offer discount and referral discount");
            allCoursesPage=allCoursesPage.verifyDiscountPriceValue(course_Id, percentageValue, referral_Discount_Value);
            /*
            // Change Currency
            currency= propertyReader.readTestData("Currency_GBP");
            addLog("Select "+currency+" from currency dropdown");
            allCoursesPage= allCoursesPage.changeCurrency(currency);

            // Verify final price after offer discount and referral discount
            addLog("Verify final price on All courses page after offer discount and referral discount");
            allCoursesPage=allCoursesPage.verifyDiscountPriceValue(course_Id, percentageValue, referral_Discount_Value);

            // Change Currency
            currency= propertyReader.readTestData("Currency_CD");
            addLog("Select "+currency+" from currency dropdown");
            allCoursesPage= allCoursesPage.changeCurrency(currency);

            // Verify final price after offer discount and referral discount
            addLog("Verify final price on All courses page after offer discount and referral discount");
            allCoursesPage=allCoursesPage.verifyDiscountPriceValue(course_Id, percentageValue, referral_Discount_Value);

            // Change Currency
            currency= propertyReader.readTestData("Currency_SG");
            addLog("Select "+currency+" from currency dropdown");
            allCoursesPage= allCoursesPage.changeCurrency(currency);

            // Verify final price after offer discount and referral discount
            addLog("Verify final price on All courses page after offer discount and referral discount");
            allCoursesPage=allCoursesPage.verifyDiscountPriceValue(course_Id, percentageValue, referral_Discount_Value);


            currency= propertyReader.readTestData("Currency_AU");
            addLog("Select "+currency+" from currency dropdown");
            allCoursesPage= allCoursesPage.changeCurrency(currency);

            // Verify final price after offer discount and referral discount
            addLog("Verify final price on All courses page after offer discount and referral discount");
            allCoursesPage=allCoursesPage.verifyDiscountPriceValue(course_Id, percentageValue, referral_Discount_Value);

             */
            currency= propertyReader.readTestData("Currency_EU");
            addLog("Select "+currency+" from currency dropdown");
            allCoursesPage= allCoursesPage.changeCurrency(currency);

            // Verify final price after offer discount and referral discount
            addLog("Verify final price on All courses page after offer discount and referral discount");
            allCoursesPage=allCoursesPage.verifyDiscountPriceValue(course_Id, percentageValue, referral_Discount_Value);

            // Select Course
            courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course " +courseName);
            selectedCoursePage=allCoursesPage.selectCourseFromListView(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Verify Additonal Referral and Offer Disocunt Message
            addLog("Verify Additonal Referral and Offer Disocunt Message");
            selectedCoursePage =selectedCoursePage.verifyAdditonalDiscountBanner();

            //Click on Enroll Now Button
            addLog("Click on Enroll Now Button");
            signInModalPage=selectedCoursePage.clickOnEnrollButton(SignInModalPage.class);

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
            orderSummaryPage=signInModalPage.createAccountButton();

            //Verify Order Summary page
            addLog("Verify Order Summary page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Verify Price after Referral Discount and Offer Discount
            addLog("Verify Price after Referral Discount and Offer Discount");
            orderSummaryPage=orderSummaryPage.verifyPriceAfterOfferAndReferralDiscount();

            // Change Country
            String countryName= propertyReader.readTestData("CountryName");
            String currencySign = propertyReader.readTestData("AustralianCurrencySign");
            addLog("Change Country from country dropdown");
            orderSummaryPage=orderSummaryPage.changeCountry(countryName,currencySign);

            // Verify Price after Referral Discount and Offer Discount
            addLog("Verify Price after Referral Discount and Offer Discount");
            orderSummaryPage=orderSummaryPage.verifyPriceAfterOfferAndReferralDiscount();

            // Change Currency
            currency= propertyReader.readTestData("USDCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify Price after Referral Discount and Offer Discount
            addLog("Verify Price after Referral Discount and Offer Discount");
            orderSummaryPage=orderSummaryPage.verifyPriceAfterOfferAndReferralDiscount();

            // Change Currency
            currency= propertyReader.readTestData("Currency_GBP");
            addLog("Select "+currency+" from currency dropdown");
            orderSummaryPage= orderSummaryPage.changeCurrency(currency);

            // Verify Price after Referral Discount and Offer Discount
            addLog("Verify Price after Referral Discount and Offer Discount");
            orderSummaryPage=orderSummaryPage.verifyPriceAfterOfferAndReferralDiscount();

            // Change Currency
            currency= propertyReader.readTestData("Currency_CD");
            addLog("Select "+currency+" from currency dropdown");
            orderSummaryPage= orderSummaryPage.changeCurrency(currency);

            // Verify Price after Referral Discount and Offer Discount
            addLog("Verify Price after Referral Discount and Offer Discount");
            orderSummaryPage=orderSummaryPage.verifyPriceAfterOfferAndReferralDiscount();

            // Change Currency
            currency= propertyReader.readTestData("Currency_SG");
            addLog("Select "+currency+" from currency dropdown");
            orderSummaryPage= orderSummaryPage.changeCurrency(currency);

            // Verify Price after Referral Discount and Offer Discount
            addLog("Verify Price after Referral Discount and Offer Discount");
            orderSummaryPage=orderSummaryPage.verifyPriceAfterOfferAndReferralDiscount();

            currency= propertyReader.readTestData("Currency_AU");
            addLog("Select "+currency+" from currency dropdown");
            orderSummaryPage= orderSummaryPage.changeCurrency(currency);

            // Verify Price after Referral Discount and Offer Discount
            addLog("Verify Price after Referral Discount and Offer Discount");
            orderSummaryPage=orderSummaryPage.verifyPriceAfterOfferAndReferralDiscount();

            currency= propertyReader.readTestData("Currency_EU");
            addLog("Select "+currency+" from currency dropdown");
            orderSummaryPage= orderSummaryPage.changeCurrency(currency);

            // Verify Price after Referral Discount and Offer Discount
            addLog("Verify Price after Referral Discount and Offer Discount");
            orderSummaryPage=orderSummaryPage.verifyPriceAfterOfferAndReferralDiscount();

            // Click on Profile Dropdown
            addLog("Click on Profile Dropdown");
            orderSummaryPage=orderSummaryPage.clickOnProfileDropdown(userName);            

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = orderSummaryPage.logout();       

        }
        catch (Error e) {
            captureScreenshot("test_022VerifyReferralProcessWhenNoneOfOfferIsPresent");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_022VerifyReferralProcessWhenNoneOfOfferIsPresent");
            throw e;
        }
    }
}