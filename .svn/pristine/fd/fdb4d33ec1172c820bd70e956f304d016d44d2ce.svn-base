package com.edureka.registration;

import org.testng.annotations.Test;

import com.edureka.util.DriverTestCase;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;

public class VerifyLoginWithCorrectEmailAndCorrectPassword extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SignInModalPage signInModalPage;

    @Test
    public void test_012VerifyLoginWithCorrectEmailAndCorrectPassword() throws Exception {

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

            // Login Application
            addLog(" Login Application");
            userHomePage= signInModalPage.loginApp();

            // Verify User Home Page
            addLog("User has logged in successfully and Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

        }
        catch (final Error e) {
            captureScreenshot("test_012VerifyLoginWithCorrectEmailAndCorrectPassword");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_012VerifyLoginWithCorrectEmailAndCorrectPassword");
            throw e;
        }
    }
}
