package com.edureka.paymentProcess;

import org.testng.annotations.Test;

import com.edureka.pages.AdminAddOfferPage;
import com.edureka.pages.AdminDashboard;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.RazorPayPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class MakePaymentThroughCCAvenue extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;
    private AdminDashboard adminDashboard;
    private UserHomePage userHomePage;
    private AdminAddOfferPage adminAddOfferPage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private RazorPayPage razorPayPage;
   

    @Test
    public void test_005MakePaymentThroughCCAvenue() throws Exception {

        try {
       // CHANGE THE URL To MAKE ADMIN URL WHICH IS CHANGES NOW.
 // Change the url for the 
        	

        	// Navigate to app url
        	addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup_AdminPanel();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage =  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();
            
            signInModalPage = dashboardPage.clickSignInHeader();
           /* // Verify Login is selected as default
            addLog("Verify Login is selected as default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();
           */
            // Login Application through Admin credentials
            String email = propertyReader.readApplicationFile("Admin_UserName");
            String password = propertyReader.readApplicationFile("Admin_Password");
            addLog("Login Application through Admin credentials");
            adminDashboard= signInModalPage.Login_AdminPanel(AdminDashboard.class,email, password);
            
            //Navigate to Enable CCAvenue Button
            adminDashboard = adminDashboard.enablepaymentforINR("CC");
        	 
        	// Logout from the Admin Panel
        	dashboardPage = adminDashboard.logout(DashboardPage.class);
        	
//////////////////////////////////////////////////////////////////////////        	
      
        	// Navigate to app url
        	addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();
             
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
            String useremail = signInModalPage.RuntimeUserEmail();
            String username = signInModalPage.RuntimeUserName();
            String userpassword = signInModalPage.RuntimeUserPassword();
            String phn = signInModalPage.RuntimeUserPhNum();
            
            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyRandUserLoggedIn(username);

            // Select Course
 // String courseName = propertyReader.readTestData("Course_DevOps_Certification");
            addLog("Select Course from Popular Heading");
            selectedCoursePage=dashboardPage.selectCourse_Popular();
            String courseName = dashboardPage.getCourseName_PopularCOurse();
            System.out.println("Course which is selected: " +courseName);
            String slug = dashboardPage.getSlug();
             
            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);
             
            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();
            String courseDate = orderSummaryPage.getCourseDate();
            String courseDuration = orderSummaryPage.getCourseDuration();
            String courseTime = orderSummaryPage.getCourseTimeDuration();
            String courseAmount = orderSummaryPage.getCourseTotalCost();
            
            // Change Currency
            String currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();
           
            //Verify elements on CC Avenue page
            razorPayPage=razorPayPage.verifyCCAvenuePage(username, useremail, phn, courseName, courseDate, courseAmount,"");
            
            //Navigate to url
            String url=propertyReader.readApplicationFile("Edureka-URL");
            userHomePage=razorPayPage.navigateToUrl(url);
            
            
            // Click on Profile dropdown
            addLog("Click on Profile dropdown");
  //userName=propertyReader.readRunTimeData("UserName");
            userHomePage= userHomePage.clickOnProfileDropdown();

            // Logout Application
            addLog("Logout Application");
            dashboardPage=userHomePage.logout();
            
            
    // Click on Signin Navigation Header
    addLog("Click on SignIn Navigation header");
    signInModalPage = dashboardPage.clickSignInHeader();

    // Verify Login is selected as default
    addLog("Verify Login is selected as default");
    signInModalPage = signInModalPage.verifyLoginIsDefault();

    // Login Application through Admin credentials
    email = propertyReader.readApplicationFile("Admin_UserName");
    password = propertyReader.readApplicationFile("Admin_Password");
    addLog("Login Application through Admin credentials");
    signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

    // Click on Start Learning Course button
    addLog("Click on Start Learning Course button");
    userHomePage=signInModalPage.clickStartLearningButton(UserHomePage.class);

    // Click on Profile dropdown
    addLog("Click on Profile dropdown");
    userHomePage= userHomePage.clickOnProfileDropdown();

    // Logout Application
    addLog("Logout Application");
    dashboardPage=userHomePage.logout();

           
        }
        catch (Error e) {
            captureScreenshot("test_005MakePaymentThroughCCAvenue");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_005MakePaymentThroughCCAvenue");
            throw e;
        }
    }

}