package com.edureka.registration;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.util.DriverTestCase;

public class LoginWithEmailAndWithoutPassword extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;

    static String email;
    static String password;
    static String errorMessage;

    @Test
    public void test_014LoginWithEmailAndWithoutPassword() throws Exception {

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

            // Enter email 
            email = "test"+randomString(3)+"@tech.edureka.in";
            password = "";
            addLog("Enter email");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

            //  Verify user is not able to Logged in if user login without password
            errorMessage = propertyReader.readTestData("ErrorMessage");
            addLog("Verify user is not able to Logged in if user login without password");
            signInModalPage=  signInModalPage.verifyErrorForLogIn(errorMessage);

        }
        catch (final Error e) {
            captureScreenshot("test_014LoginWithEmailAndWithoutPassword");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_014LoginWithEmailAndWithoutPassword");
            throw e;
        }
    }

    @Test(dependsOnMethods={"test_014LoginWithEmailAndWithoutPassword"})
    public void test_015LoginWithoutEmail() throws Exception {

        try {

            // Close sign in module
            addLog("Close sign in module");
            dashboardPage=  signInModalPage.closeSignInModule();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // Enter Password 
            email = "";
            password = propertyReader.readTestData("Password");
            addLog("Enter password");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

            //  Verify user is not able to Logged in if user login without email
            addLog("Verify user is not able to Logged in if user login without email");
            signInModalPage=  signInModalPage.verifyErrorForLogIn(errorMessage);

        }
        catch (final Error e) {
            captureScreenshot("test_015LoginWithoutEmail");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_015LoginWithoutEmail");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_015LoginWithoutEmail"})
    public void test_016LoginWithoutEmailAndWithoutPassword ()throws Exception{

        try {

            // Close sign in module
            addLog("Close sign in module");
            dashboardPage=  signInModalPage.closeSignInModule();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // Enterl null in email and password fields
            email = "";
            password ="";
            addLog("Enterl null in email and password fields");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

//***** Correct behaviour is not mentioned in the TestCase.            
/*            //  Verify user is not able to Logged in if user login without email and without password because Start Learning button is disbaled 
            addLog("Verify user is not able to Logged in if user login without email and without password  beacuse Start Learning button is disbaled");
            signInModalPage=  signInModalPage.verifyStartLearningButtnDisabled();
*/
        }
        catch (final Error e) {
            captureScreenshot("test_016LoginWithoutEmailAndWithoutPassword");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_016LoginWithoutEmailAndWithoutPassword");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_015LoginWithoutEmail"})
    public void test_017LoginWithCorrectEmailAndIncorrectPassword ()throws Exception{

        try {

            // Close sign in module
            addLog("Close sign in module");
            dashboardPage=  signInModalPage.closeSignInModule();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // Enter correct email and incorrect password
            email = propertyReader.readRunTimeData("Email_Id");
            password = propertyReader.readTestData("InCorrectPassword");
            addLog("Enter correct email and incorrect password");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

            //  Verify user is not able to Logged in if user login with correct email and incorrect password
            addLog("Verify user is not able to Logged in if user login with correct email and incorrect password");
            signInModalPage=  signInModalPage.verifyErrorForLogIn(errorMessage);

        }
        catch (final Error e) {
            captureScreenshot("test_017LoginWithCorrectEmailAndIncorrectPassword");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_017LoginWithCorrectEmailAndIncorrectPassword");
            throw e;
        }
    }
    @Test(dependsOnMethods={"test_017LoginWithCorrectEmailAndIncorrectPassword"})
    public void test_018LoginWithIncorrectEmailAndIncorrectPassword ()throws Exception{

        try {

            // Close sign in module
            addLog("Close sign in module");
            dashboardPage=  signInModalPage.closeSignInModule();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // Enter incorrect email and incorrect password
            email = propertyReader.readTestData("InCorrectEmail");
            password = propertyReader.readTestData("InCorrectPassword");
            addLog("Enter correct email and incorrect password");
            signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

            //  Verify user is not able to Logged in if user login with incorrect email and incorrect password
            addLog("Verify user is not able to Logged in if user login with incorrect email and incorrect password");
            signInModalPage=  signInModalPage.verifyErrorForLogIn(errorMessage);

        }
        catch (final Error e) {
            captureScreenshot("test_017LoginWithCorrectEmailAndIncorrectPassword");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_017LoginWithCorrectEmailAndIncorrectPassword");
            throw e;
        }
    }
}