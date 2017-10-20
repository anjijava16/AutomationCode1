package com.edureka.registration;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.util.DriverTestCase;

public class VerifyUserNotAbleToSignUpIfEnteredPhNumberInAlphabets extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;

    static  String  phNumber;
    static String userName;
    static String email;
    static String password;
    static String errorMessage;

    @Test
    public void test_007VerifyAllTextBoxesContainsrelevantPlaceholderText() throws Exception {

        try {

            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=	dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            // Verify all the text boxes should contains relevant placeholder text.
            addLog("Verify all the text boxes should contains relevant placeholder text.");
            signInModalPage=signInModalPage.verifyPlaceholderText();

        }
        catch (final Error e) {
            captureScreenshot("test_007VerifyAllTextBoxesContainsrelevantPlaceholderText");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_007VerifyAllTextBoxesContainsrelevantPlaceholderText");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_007VerifyAllTextBoxesContainsrelevantPlaceholderText"})
    public void test_005VerifyUserNotAbleToSignUpIfEnteredPhNumberInAlphabets() throws Exception {

        try {
            //Enter username, email and PhoneNumber
            userName = propertyReader.readTestData("UserName");
            email = "test"+randomString(3)+"@tech.edureka.in";
            phNumber = propertyReader.readTestData("PhNumberInAlphabets");
            addLog("Enter username, email and PhoneNumber");
            signInModalPage=signInModalPage.enterUserNameEmailAndPhoneNumnber(userName,email,phNumber);

            // Enter password
            password = propertyReader.readTestData("Password");
            addLog("Enter Password");
            signInModalPage=signInModalPage.enterPassword(password);

            //Verify User is not able to signup if mobile number is in alphabets
            addLog("Verify User is not able to signup if mobile number is in alphabets");
            signInModalPage = signInModalPage.verify_MobileError_EnterAValidNumber();
        }
        catch (final Error e) {
            captureScreenshot("test_005VerifyUserNotAbleToSignUpIfEnteredPhNumberInAlphabets");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_005VerifyUserNotAbleToSignUpIfEnteredPhNumberInAlphabets");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_005VerifyUserNotAbleToSignUpIfEnteredPhNumberInAlphabets"})
    public void test_06VerifyUserNotAbleTOSignupWithExisitngID() throws Exception {

        try {

            // Close sign in module
            addLog("Close sign in module");
            dashboardPage=  signInModalPage.closeSignInModule();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            // Enter sign up details
            email = propertyReader.readRunTimeData("Email_Id");
            phNumber=propertyReader.readTestData("PhoneNumber");
            addLog("Enter user name, email and phone number in to the sing up field");
            signInModalPage= signInModalPage.enterUserNameEmailAndPhoneNumnber(userName, email, phNumber);
            signInModalPage=signInModalPage.enterPassword(password);

            //  Verify user is not able to sign up if entered email is incorrect
            errorMessage = propertyReader.readTestData("ErrorMessageForExistingEmailID");
            addLog("Verify user is not able to sign up if entered email is incorrect");
            signInModalPage=  signInModalPage.verifyError(errorMessage);

        }
        catch (final Error e) {
            captureScreenshot("test_06VerifyUserNotAbleTOSignupWithExisitngID");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_06VerifyUserNotAbleTOSignupWithExisitngID");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_06VerifyUserNotAbleTOSignupWithExisitngID"})
    public void test_011VerifyCrossClosedSignupPopop() throws Exception {

        try {

            // Click on cross button 
            addLog("Click on cross button");
            dashboardPage=signInModalPage.clickOnCrossButton();

            // Verify Signup has been closed 
            addLog("Verify Signup has been closed");
            dashboardPage=dashboardPage.verifySignUpNotPresent();
        } catch (final Error e) {
            captureScreenshot("test_011VerifyCrossClosedSignupPopop");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_011VerifyCrossClosedSignupPopop");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_011VerifyCrossClosedSignupPopop"})
    public void test_010VerifyClickingHyperlinkOnClickingRedirectToPrivacyPolicyPage() throws Exception {

        try {

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Click on Privacy Policy link 
            addLog("Click on Privacy Policy link");
            signInModalPage=signInModalPage.clickOPrivateAndPolicyLink();

            // Verify that ' Privacy Policy' text is hyperlink and on clicking redirects to ' Privacy Policy' page. 
            switchPreviewWindow();
            addLog("Verify that ' Privacy Policy' text is hyperlink and on clicking redirects to ' Privacy Policy' page.");
            signInModalPage=signInModalPage.verifyPrivateAndPolicyRedirectPrivateAndPolicyPage();
        }
        catch (final Error e) {
            captureScreenshot("test_010VerifyClickingHyperlinkOnClickingRedirectToPrivacyPolicyPage");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_010VerifyClickingHyperlinkOnClickingRedirectToPrivacyPolicyPage");
            throw e;
        }
    }
}
