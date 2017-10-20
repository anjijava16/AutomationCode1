package com.edureka.paymentProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.MyProfilePage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class ValidateAllElementsPresentOnOrderSummaryPage extends DriverTestCase{
    private DashboardPage dashboardPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private SignInModalPage signInModalPage;
    private MyProfilePage myProfilePage;
    @Test
    public void test_008ValidateAllElementsPresentOnOrderSummaryPage() throws Exception {

        try {

            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetupForLead();

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
            String username = signInModalPage.RuntimeUserName();
            String email = signInModalPage.RuntimeUserEmail();
            
            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyRandUserLoggedIn(username);

            // Select Course
            String courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course" +courseName);
            selectedCoursePage=dashboardPage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();
            
            // Get the CoursePrice
            String course_price = selectedCoursePage.getCourse_originalPrice();	
            String course_servicetax = selectedCoursePage.getCourse_ServiceTax();
            String getUserID = selectedCoursePage.getUserID(email);
            
            
            //Change Country
            String currency= propertyReader.readTestData("INRCurrency");
            String country=propertyReader.readTestData("Country_India");
            orderSummaryPage=orderSummaryPage.changeCountry(country, currency);

            // Change Currency
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);
            
    /*        //Verify total amount
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmountWithoutNetPrice(currency);
    */      
            // Verify Total Amount
            addLog("Verify Data in Pre-Order table");
            String course_Id= propertyReader.readTestData("Course_ID_BigData");
            String course_Title= propertyReader.readTestData("BigData_Hadoop_Title");
            orderSummaryPage=orderSummaryPage.dataVerificationInUser_PreOrderTableForINR(course_Id,course_price,course_servicetax,getUserID);

            //Verify chnaged time zone
            String timeZone=propertyReader.readTestData("IndianTimeZone");
            orderSummaryPage=orderSummaryPage.verifyChangedTimeZoneWithCountry(timeZone);
            
            //Verify Edureka Logo
            orderSummaryPage=orderSummaryPage.verifyEdurekaLogoOnOrdersummaryPage();
            
            //Verify Course Name
            orderSummaryPage=orderSummaryPage.verifyCourseName();
            
            //Verify the elements with Currency INR selection
            orderSummaryPage=orderSummaryPage.verifytheElementsWithINRSelection();
           
            /*//Reload the page and check the position of country comes first
            orderSummaryPage=orderSummaryPage.refreshPageAndCheckCountry(country);           
            */           
            // Change Country
            String countryName= propertyReader.readTestData("CountryName");
            String currencySign = propertyReader.readTestData("AustralianCurrencySign");
            addLog("Change Country from country dropdown");
            orderSummaryPage=orderSummaryPage.changeCountry(countryName,currencySign);
            
            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();
            
            // Change Currency            
            addLog("Select currecnt" +currencySign +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currencySign);
            
            //Verify the elements with Currency USSD selection
            orderSummaryPage=orderSummaryPage.verifytheElementsWithUSSDSelection();

            // Verify total Amount for USD is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmountWithoutNetPrice(currencySign);

           //Verify Need Assistance
            orderSummaryPage=orderSummaryPage.verifyNeedAssitanceAndMobileNos();
            
            //Reload the page and check the position of country comes first
            orderSummaryPage=orderSummaryPage.refreshPageAndCheckCountry(countryName);
        }
        catch (Error e) {
            captureScreenshot("test_008ValidateAllElementsPresentOnOrderSummaryPage");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_008ValidateAllElementsPresentOnOrderSummaryPage");
            throw e;
        }
    }
}