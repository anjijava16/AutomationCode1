package com.edureka.poc;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.util.DriverTestCase;

public class VerifySignUpFromAddToWishlist extends DriverTestCase {

    private DashboardPage dashboardPage;
    private SelectedCoursePage selectedCoursePage;
    private SignInModalPage signInModalPage;

    @Test
    public void test_04VerifySignUpFromAddToWishlist() throws Exception {

        try {

            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();


            // Select Course From Trending Courses
            String coursename = propertyReader.readTestData("BigData&Haddop");
            addLog("Select Course From Trending Courses");
            selectedCoursePage=	dashboardPage.selectCourse(coursename);


            // Verify Selected Course Page Is Open
            addLog("Verify Selected Course Page Is Open");
            selectedCoursePage=	selectedCoursePage.verifySelectedCoursePage(coursename);

            // Click on Add To Wishlist Icon
            addLog("Click on Add To Wishlist Icon");
            signInModalPage= selectedCoursePage.clickOnAddToWishlistIcon(SignInModalPage.class);

            // Sign up user
            String domainName= propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            selectedCoursePage= signInModalPage.signUp(SelectedCoursePage.class, domainName);
            
            // Verify sign up successfully
            addLog(" Verify sign up successfully");
            selectedCoursePage= selectedCoursePage.verifySignUpSuccessfully();
            
        }
        catch (final Error e) {
            captureScreenshot("test_04VerifySignUpFromAddToWishlist");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_04VerifySignUpFromAddToWishlist");
            throw e;
        }
    }

}



