package com.edureka.registration;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.util.DriverTestCase;

public class VerifyUserNotAbleToSignupIfMandatoryFieldIsEmpty  extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;

    static  String  phNumber;
    static String userName;
    static String email;
    static String errorMessage;

    @Test
    public void test_002VerifyUserNotAbleToSignupIfMandatoryFieldISEmpty() throws Exception {

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

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            // Sign up user
            phNumber = propertyReader.readTestData("PhoneNumber");
            userName= "User"+randomString(2);
            email = "test" + randomString(3) + "@edureka.in";
            addLog("Enter user name, email and phone number in to the sing up field");
            signInModalPage= signInModalPage.enterUserNameEmailAndPhoneNumnber(userName, email, phNumber);

            // Verify manadatory fields are required
            errorMessage = propertyReader.readTestData("ErrorMessage");
            addLog(" Verify manadatory fields are required");
            //signInModalPage=  signInModalPage.verifyError(errorMessage);
            signInModalPage = signInModalPage.verify_PasswordError_EnterAValidPassword(); 
        }
        catch (final Error e) {
            captureScreenshot("test_002VerifyUserNotAbleToSignupIfMandatoryFieldISEmpty");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_002VerifyUserNotAbleToSignupIfMandatoryFieldISEmpty");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_002VerifyUserNotAbleToSignupIfMandatoryFieldISEmpty"})
    public void test_03VerifyUserNotAbleTOSignupEnteredEmailIsIncorrect() throws Exception {

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

            // Enter sing up details
            email = "test" + randomString(3) + "Mail";
            String password = propertyReader.readTestData("Password");
            addLog("Enter user name, email and phone number in to the sing up field");
            signInModalPage= signInModalPage.enterUserNameEmailAndPhoneNumnber(userName, email, phNumber);
            signInModalPage=signInModalPage.enterPassword(password);

            //  Verify user is not able to sign up if entered email is incorrect
            addLog("Verify user is not able to sign up if entered email is incorrect");
            signInModalPage=  signInModalPage.verify_EMailError_EnterAValidEmail();

        }
        catch (final Error e) {
            captureScreenshot("test_03VerifyUserNotAbleTOSignupEnteredEmailIsIncorrect");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_03VerifyUserNotAbleTOSignupEnteredEmailIsIncorrect");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_03VerifyUserNotAbleTOSignupEnteredEmailIsIncorrect"})
    public void test_04VerifyUserNotAbleTOSignupEnteredPasswordIsLessThan8Characters() throws Exception {

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

            // Enter sing up details
            email = "test" + randomString(3);
            String password = propertyReader.readTestData("InCorrectPassword");
            addLog("Enter user name, email and phone number in to the sing up field");
            signInModalPage= signInModalPage.enterUserNameEmailAndPhoneNumnber(userName, email, phNumber);
            signInModalPage=signInModalPage.enterPassword(password);

            //  Verify user is not able to sign up if entered email is incorrect
            addLog("Verify user is not able to sign up if entered email is incorrect");
//            signInModalPage=  signInModalPage.verifyError(errorMessage);
            signInModalPage = signInModalPage.verify_EMailError_EnterAValidEmail();
        }
        catch (final Error e) {
            captureScreenshot("test_04VerifyUserNotAbleTOSignupEnteredPasswordIsLessThan8Characters");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_04VerifyUserNotAbleTOSignupEnteredPasswordIsLessThan8Characters");
            throw e;
        }
    }

    @Test(dependsOnMethods={"test_04VerifyUserNotAbleTOSignupEnteredPasswordIsLessThan8Characters"})
    public void test_008VerifyClickingOnShowTextPasswordBecomeUnmasked() throws Exception {

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

            // Enter Password
            String password = propertyReader.readTestData("Password");
            addLog("Enter Password");
            signInModalPage=signInModalPage.enterPassword(password);

            //Verify that on clicking 'show' text in password text box the password characters become unmasked.
            addLog("Verify that on clicking 'show' text in password text box the password characters become unmasked.");
            signInModalPage=signInModalPage.verifyPasswordBecomeUnmaskedByClickOnShow("SignUp");

        }
        catch (final Error e) {
            captureScreenshot("test_008VerifyClickingOnShowTextPasswordBecomeUnmasked");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_008VerifyClickingOnShowTextPasswordBecomeUnmasked");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_008VerifyClickingOnShowTextPasswordBecomeUnmasked"})
    public void test_009VerifyClickingTCRedirectTermsAndConditionsPage() throws Exception {

        try {

            // Click on T&C link 
            addLog("Click on T&C link");
            signInModalPage=signInModalPage.clickOnTCLink();

            // Verify that 'T&C' text is hyperlink and on clicking redirects to 'Terms and Conditions' page. 
            switchPreviewWindow();
            addLog("Verify that 'T&C' text is hyperlink and on clicking redirects to 'Terms and Conditions' page. ");
            signInModalPage=signInModalPage.verifyTAndCLinkRedirectTCPage();
        }
        catch (final Error e) {
            captureScreenshot("test_009VerifyClickingTCRedirectTermsAndConditionsPage");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_009VerifyClickingTCRedirectTermsAndConditionsPage");
            throw e;
        }
    }
}