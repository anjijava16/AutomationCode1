package com.edureka.paymentProcess;

import org.testng.annotations.Test;

import com.edureka.pages.DashboardPage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class CopyOfValidateTotalAmountOnChangeOfCurrency extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    @Test
    public void test_001ValidateTotalAmountOnChangeOfCurrency() throws Exception {

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
            String username  =signInModalPage.RuntimeUserName();
            String email = signInModalPage.RuntimeUserEmail();
            String phoneNum = signInModalPage.RuntimeUserPhNum();
           
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

            // Change Currency INR
            String currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for INR is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

            orderSummaryPage=orderSummaryPage.verifytheElementsWithINRSelection();

            // Change Currency USD
            
            currency= propertyReader.readTestData("USDCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for USD is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);


            // Change Currency GBP
            currency= propertyReader.readTestData("Currency_GBP");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);


            // Verify total Amount for GBP is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

            // Change Currency CD
            currency= propertyReader.readTestData("Currency_CD");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for CD is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

            // Change Currency SG
            currency= propertyReader.readTestData("Currency_SG");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for SG is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

            // Change Currency AU
            currency= propertyReader.readTestData("Currency_AU");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Change Country Australia
            String country_Australia = propertyReader.readTestData("CountryName");
            String currencySign = propertyReader.readTestData("AustralianCurrencySign");
            addLog("Change Country "+country_Australia + " from country dropdown");
            orderSummaryPage=orderSummaryPage.changeCountry(country_Australia,currencySign);

            // Verify Change Time Zone
            String changeAustralianTimeZone= propertyReader.readTestData("AustralianTimeZone");
            addLog("Verify change Time Zone" +changeAustralianTimeZone );
            orderSummaryPage = orderSummaryPage.verifyChangedTimeZoneWithCountry(changeAustralianTimeZone);

            // Verify total Amount for AU is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);          

            // Change Currency EU
            currency= propertyReader.readTestData("Currency_EU");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for EU is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);


        }
        catch (Error e) {
            captureScreenshot("test_001ValidateTotalAmountOnChangeOfCurrency");
            throw e;
        } catch (Exception e) {
            captureScreenshot("test_001ValidateTotalAmountOnChangeOfCurrency");
            throw e;
        }
    }
}