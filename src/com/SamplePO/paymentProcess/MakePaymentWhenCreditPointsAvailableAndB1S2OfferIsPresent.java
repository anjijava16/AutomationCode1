package com.edureka.paymentProcess;

import org.testng.annotations.Test;

import com.edureka.pages.AdminAddOfferPage;
import com.edureka.pages.AdminDashboard;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.MyProfilePage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.PayPalPage;
import com.edureka.pages.RazorPayPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class MakePaymentWhenCreditPointsAvailableAndB1S2OfferIsPresent extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private RazorPayPage razorPayPage;
    private PayPalPage payPalPage;
    private MyProfilePage myProfilePage;
    private AdminDashboard adminDashboard;
    private AdminAddOfferPage adminAddOfferPage;
    static String email;
    static String password;
    static String campaignSource;
    static String campaignName;
    static String campaignMedium;
    String TestCase_18_URL = " http://adminqa.edureka.in/admin/offers/offer_edit/5810";

    @Test 
   public void test_018MakePaymentWhenB1S2OfferIsPresent_RP() throws Exception {

    try {

    // Change in the Admin Panel: 
            	
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
               
                // Login Application through Admin credentials
                String email = propertyReader.readApplicationFile("Admin_UserName");
                String password = propertyReader.readApplicationFile("Admin_Password");
                addLog("Login Application through Admin credentials");
                adminDashboard= signInModalPage.Login_AdminPanel(AdminDashboard.class,email, password);
                
                //Navigate to offers URL : 
                adminDashboard = adminDashboard.enablepaymentforINR("RP");
          // ENALE OFFER       
                // Navigate to offer URL 
                adminDashboard = adminDashboard.navigateToOfferURL(AdminDashboard.class,TestCase_18_URL);
                
                // Land on Admin URL page.
                adminDashboard = adminDashboard.activateTheOffer("Active");
                		
            	// Logout from the Admin Panel
            	dashboardPage = adminDashboard.logout(DashboardPage.class);
        			
    			
    ////////////////////////////////////////////////////////////////////////////////////////////
    			
            	// Navigate to app url
                addLog("Navigate to the Edureka application url");
                dashboardPage = applicationSetupForLead();
                
    			// Click on Signin Navigation Header
    			addLog("Click on SignIn Navigation header");
    			signInModalPage = dashboardPage.clickSignInHeader();
    			
    /// Changes the USER, Login with User of different credentials 
    			
    			// Verify Login is selected as default
    			addLog("Verify Login is selected as default");
    			signInModalPage = signInModalPage.verifyLoginIsDefault();

    			// click on Sign up link
    			addLog("click on Sign up link");
    			signInModalPage=signInModalPage.clickSignUp();

    /*	// Verify Data
    	addLog("Verify Data");
    	discountTitle= propertyReader.readTestData("Title_Discount");
    	dashboardPage=dashboardPage.verifyDataForOfferFramework(discountTitle);
    */
    			addLog("Sign up user");
				String useremail = "creditpointtest@tech.edureka.in";
				String pass = "123456789";
				String name = "TEST";
				String phone = "123456789";
			    userHomePage = signInModalPage.RandUserLogin(useremail, pass);
			    
				// Verify User Home Page
				addLog("Verify User Home Page");
				userHomePage=userHomePage.verifyRandUserLoggedIn(name);
			    
    /*// Verify Banner is appearing
    String banner = propertyReader.readTestData("Banner_T20_Image");
    addLog("Verify "+banner+" Banner is appearing");
    userHomePage=userHomePage.verifyBanner(banner);*/

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

    			//On click of Enroll when none of the offer is present

    			// Changes the Currency
    			String currency= propertyReader.readTestData("INRCurrency");
    			orderSummaryPage=orderSummaryPage.changeCurrency(currency);


    			// Verify Order Summary Page
    			addLog("Verify Order Summary Page");
    			orderSummaryPage=orderSummaryPage.verifyPage();
    			String Savings = selectedCoursePage.getCourse_Savings();
    			System.out.println("SAVINGS : " + Savings);
    			String totalcost = selectedCoursePage.getCourse_TotalAmount();
    			System.out.println("TotalCost : " + totalcost);
            	/*String netPrice = selectedCoursePage.getCourse_netPrice();
    	        System.out.println("NetPrice : " + netPrice);
    			String discount = selectedCoursePage.getCourse_discountedPrice();
    			System.out.println("DiscountPrice : " + discount);
    			*/
    			String Tax = selectedCoursePage.getCourse_ServiceTax();
    			System.out.println("ServiceTax : " + Tax);
    			
    			//Assert B1S2 Title. 
    			orderSummaryPage.verifyB1S2Title();
    			

    			// Verify Data from PreOrder Table
    			String course_Id= propertyReader.readTestData("Course_ID_BigData");
    			String course_Title= propertyReader.readTestData("BigData_Hadoop_Title");
    			orderSummaryPage=orderSummaryPage.dataVerificationInUser_PreOrderTable(course_Id, course_Title, "",currency,"MakePaymentWhenAnyTypeOfDiscountNotB1S2","pre_orders","",totalcost,"","",useremail);


    			// On click of Pay securely(Razorpay) when none of the offer is present:

    			// Click on Razor pay securely button
    			addLog("Click on Razor pay securely button");
    			razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

    			// Verify Razor page
    			addLog("Verify Razor page");
    			razorPayPage=razorPayPage.verifyPage(useremail);

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
    			Thread.sleep(5000);
    			// Switch to Parent Window and verify my Profile page
    			addLog("Switch to Parent Window and verify my Profile page");
    			switchParentWindow(parentWidnow);
    			myProfilePage= myProfilePage.verifyPage();

    			// Verify Payment success message
    			addLog("Verify payment success message 'Thank you for making payment !'");
    			myProfilePage= myProfilePage.verifySuccessPaymentMessage();

    			// Verify Data in User table
    			addLog("Verify Data in User table");
    			myProfilePage=myProfilePage.dataVerificationInUserTable("2",useremail,name,phone);

    			// Verify Data in Pre-Order table
    			addLog("Verify Data in Pre-Order table");
                String paymentGateway = propertyReader.readTestData("PaymentGatewayRazor");
    			myProfilePage=myProfilePage.dataVerificationInUser_PreOrderTable(course_Id, course_Title, "",currency,"MakePaymentWhenAnyTypeOfDiscountNotB1S2","pre_orders_RazorPayment","",totalcost,"","",useremail);
                        //
    			// Verify Data in User Course Table
    			String course_Group = propertyReader.readTestData("Course_Group_BigData");
    			String isPaidValue= propertyReader.readTestData("PaidCourses_Is_Paid_Value");
    			String getUserID = signInModalPage.getUserID(useremail);
    			addLog("Verify Data in User Course Table");
    			myProfilePage=myProfilePage.dataVerificationInUser_CoursedTable(course_Id, isPaidValue, course_Group,getUserID);

    			// Database verification in Post order table
    			addLog("Database verification in Post order table");
    			myProfilePage=myProfilePage.dataVerificationInUser_PostOrderTable(course_Id,paymentGateway,currency,getUserID);

    			//Data Verification in event table for payment type Razorpay
    			String eventType=propertyReader.readTestData("EventTypeRazorpay");;
    			myProfilePage=myProfilePage.dataVerificationInUser_Events_Table_PaymentType(eventType,getUserID);

    			//Verify the Url  on my profile page after payment
    			myProfilePage=myProfilePage.verifyCourseIdInUrl(course_Id);
    			
    			// Click on Profile Dropdown
            	//userName = propertyReader.readRunTimeData("UserName");
    			addLog("Click on Profile Dropdown");
    			myProfilePage=myProfilePage.clickOnProfileDropdown(name);            

    			// Logout from the application
    			addLog("Logout from the application.");
    			dashboardPage = myProfilePage.logout(); 
    			
    	// DISABLE THE OFFER 
    		    addLog("Navigate to the Edureka application url");
                dashboardPage = applicationSetup_AdminPanel();

                // Verify Edureka Dashboard Page
                addLog("Verify Edureka Dashboard Page");
                dashboardPage =  dashboardPage.verifyDashboard();

                // Click on Signin Navigation Header
                addLog("Click on SignIn Navigation header");
                signInModalPage = dashboardPage.clickSignInHeader();
                signInModalPage = dashboardPage.clickSignInHeader();
               
                // Login Application through Admin credentials
                email = propertyReader.readApplicationFile("Admin_UserName");
                password = propertyReader.readApplicationFile("Admin_Password");
                addLog("Login Application through Admin credentials");
                adminDashboard= signInModalPage.Login_AdminPanel(AdminDashboard.class,email, password);

                adminDashboard = adminDashboard.navigateToOfferURL(AdminDashboard.class,TestCase_18_URL);
                
                // Land on Admin URL page.
                adminDashboard = adminDashboard.activateTheOffer("Inactive");
                		
            	// Logout from the Admin Panel
            	dashboardPage = adminDashboard.logout(DashboardPage.class);

    }
    catch (final Error e) {
    	captureScreenshot("test_016MakePaymentWhenB1S2OfferIsPresent()_RP");
    	throw e;
    } catch (final Exception e) {
    	captureScreenshot("test_016MakePaymentWhenB1S2OfferIsPresent()_RP");
    	throw e;
       }
    	}
    	/// EOD RP		
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // On click of Pay securely(Paypal) when none of the offer is present
    @Test (dependsOnMethods= "test_018MakePaymentWhenB1S2OfferIsPresent_RP")
    public void test_018MakePaymentWhenB1S2OfferIsPresent_Paypal() throws Exception{
    try 
            {
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
               
                // Login Application through Admin credentials
                String email = propertyReader.readApplicationFile("Admin_UserName");
                String password = propertyReader.readApplicationFile("Admin_Password");
                addLog("Login Application through Admin credentials");
                adminDashboard= signInModalPage.Login_AdminPanel(AdminDashboard.class,email, password);
                
                //Navigate to offers URL : 
                adminDashboard = adminDashboard.enablepaymentforINR("RP");
                
                // Navigate to offer URL 
                adminDashboard = adminDashboard.navigateToOfferURL(AdminDashboard.class,TestCase_18_URL);
                
                // Land on Admin URL page.
                adminDashboard = adminDashboard.activateTheOffer("Active");
                		
            	// Logout from the Admin Panel
            	dashboardPage = adminDashboard.logout(DashboardPage.class);
        			
    			
    ////////////////////////////////////////////////////////////////////////////////////////////
    			
            	// Navigate to app url
                addLog("Navigate to the Edureka application url");
                dashboardPage = applicationSetupForLead();
                
    			// Click on Signin Navigation Header
    			addLog("Click on SignIn Navigation header");
    			signInModalPage = dashboardPage.clickSignInHeader();
    			
    /// Changes the USER, Login with User of different credentials 
    			
    			// Verify Login is selected as default
    			addLog("Verify Login is selected as default");
    			signInModalPage = signInModalPage.verifyLoginIsDefault();

    			// click on Sign up link
    			addLog("click on Sign up link");
    			signInModalPage=signInModalPage.clickSignUp();

    /*			// Verify Data
    			addLog("Verify Data");
    			discountTitle= propertyReader.readTestData("Title_Discount");
    			dashboardPage=dashboardPage.verifyDataForOfferFramework(discountTitle);
    */
    			addLog("Sign up user");
				String useremail = "creditpointtest@tech.edureka.in";
				String pass = "123456789";
				String name = "TEST";
				String phone = "123456789";
			    userHomePage = signInModalPage.RandUserLogin(useremail, pass);
			    
				// Verify User Home Page
				addLog("Verify User Home Page");
				userHomePage=userHomePage.verifyRandUserLoggedIn(name);
    /*
    // Verify Banner is appearing
    String banner = propertyReader.readTestData("Banner_T20_Image");
    addLog("Verify "+banner+" Banner is appearing");
    userHomePage=userHomePage.verifyBanner(banner);

    */			// Select Course
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

    			
    			// Changes the Currency
    			String currency= propertyReader.readTestData("INRCurrency");
    			orderSummaryPage=orderSummaryPage.changeCurrency(currency);

    /*			// Verify total Amount for USD is equal to Net price
    			addLog("Verify total Amount for USD is equal to Net price");
    			orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);
    */
    			
    			// Verify Order Summary Page
    						addLog("Verify Order Summary Page");
    						orderSummaryPage=orderSummaryPage.verifyPage();
    						String Savings = selectedCoursePage.getCourse_Savings();
    						System.out.println("SAVINGS : " + Savings);
    						String totalcost = selectedCoursePage.getCourse_TotalAmount();
    						System.out.println("TotalCost : " + totalcost);
    						/*String netPrice = selectedCoursePage.getCourse_netPrice();
    						System.out.println("NetPrice : " + netPrice);
    						*//*String discount = selectedCoursePage.getCourse_discountedPrice();
    						System.out.println("DiscountPrice : " + discount);
    						String Tax = selectedCoursePage.getCourse_ServiceTax();
    						System.out.println("ServiceTax : " + Tax);*/
    		               	
    			// Click on Paypal link
    			addLog("Click on Paypal link");
    			payPalPage= orderSummaryPage.clickOnPaypalLink_New();

    			// Verify Paypal Page
    			addLog("Verify Paypal Page");
    			payPalPage=payPalPage.verifyPage();

    			// Enter Email, Password and Login paypal account
    			addLog("Enter Email, Password and Login paypal account");
    			payPalPage=payPalPage.enterEmailAndPassword();

    			// Make payment by clicking on Continue button
    			addLog("Make payment by clicking on Continue button");
    			myProfilePage=payPalPage.clickOnContinueButton();

    			// Switch to Parent Window and verify my Profile page
    			addLog("Switch to Parent Window and verify my Profile page");
    			myProfilePage= myProfilePage.verifyPage();

    			// Verify Payment success message
    			addLog("Verify payment success message 'Thank you for making payment !'");
    			myProfilePage= myProfilePage.verifySuccessPaymentMessage();


    			// Verify Data in User table
    			addLog("Verify Data in User table");
    			myProfilePage=myProfilePage.dataVerificationInUserTable("2",useremail,name,phone);

    			// Verify Data in Pre-Order table
    			addLog("Verify Data in Pre-Order table");
    			String paymentGateway = propertyReader.readTestData("PaymentGatewayPaypal");
    			String course_Id= propertyReader.readTestData("Course_ID_BigData");
    			String course_Title= propertyReader.readTestData("BigData_Hadoop_Title");

    			myProfilePage=myProfilePage.dataVerificationInUser_PreOrderTable(course_Id,course_Title,paymentGateway,currency,"MakePaymentWhenAnyTypeOfDiscountNotB1S2","pre_orders_PapalPayment","",totalcost,"","",useremail);

    			// Verify Data in User Course Table
             	String course_Group = propertyReader.readTestData("Course_Group_BigData");
    			String isPaidValue= propertyReader.readTestData("PaidCourses_Is_Paid_Value");
    			String getUserID = signInModalPage.getUserID(useremail);
    			addLog("Verify Data in User Course Table");
    			myProfilePage=myProfilePage.dataVerificationInUser_CoursedTable(course_Id, isPaidValue, course_Group,getUserID);

    			// Database verification in Post order table
    			addLog("Database verification in Post order table");
    			myProfilePage=myProfilePage.dataVerificationInUser_PostOrderTable(course_Id,paymentGateway,currency,getUserID);

    			//Data Verification in event table for payment type Paypal
    			String eventType=propertyReader.readTestData("EventTypePaypal");
    			myProfilePage=myProfilePage.dataVerificationInUser_Events_Table_PaymentType(eventType,getUserID);
    			
    			// DISABLE THE OFFER 
    		    addLog("Navigate to the Edureka application url");
                dashboardPage = applicationSetup_AdminPanel();

                // Verify Edureka Dashboard Page
                addLog("Verify Edureka Dashboard Page");
                dashboardPage =  dashboardPage.verifyDashboard();

                // Click on Signin Navigation Header
                addLog("Click on SignIn Navigation header");
                signInModalPage = dashboardPage.clickSignInHeader();
                signInModalPage = dashboardPage.clickSignInHeader();
               
                // Login Application through Admin credentials
                email = propertyReader.readApplicationFile("Admin_UserName");
                password = propertyReader.readApplicationFile("Admin_Password");
                addLog("Login Application through Admin credentials");
                adminDashboard= signInModalPage.Login_AdminPanel(AdminDashboard.class,email, password);

                
                adminDashboard = adminDashboard.navigateToOfferURL(AdminDashboard.class,TestCase_18_URL);
                
                // Land on Admin URL page.
                adminDashboard = adminDashboard.activateTheOffer("Inactive");
                		
            	// Logout from the Admin Panel
            	dashboardPage = adminDashboard.logout(DashboardPage.class);

           }
    catch (final Error e) 
        {
         captureScreenshot("test_016MakePaymentWhenB1S2OfferIsPresent_Paypal");
         throw e;
        } 
    catch (final Exception e) {
        captureScreenshot("test_016MakePaymentWhenB1S2OfferIsPresent_Paypal");
        throw e;
           }
         }
    			

    @Test(dependsOnMethods ="test_018MakePaymentWhenB1S2OfferIsPresent_Paypal")


    public void test_018MakePaymentWhenB1S2OfferIsPresent_CCAvenue() throws Exception{

    try {
    	// CC Avenue Payment.
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

        // Login Application through Admin credentials
        String email = propertyReader.readApplicationFile("Admin_UserName");
        String password = propertyReader.readApplicationFile("Admin_Password");
        addLog("Login Application through Admin credentials");
        adminDashboard= signInModalPage.Login_AdminPanel(AdminDashboard.class,email, password);
        
        //Navigate to Enable CCAvenue Button
        adminDashboard = adminDashboard.enablepaymentforINR("CC");
    	
        // Navigate to offer URL 
        adminDashboard = adminDashboard.navigateToOfferURL(AdminDashboard.class,TestCase_18_URL);
       
        // Edit The OFFER
        adminDashboard = adminDashboard.activateTheOffer("Active");
        
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

        addLog("Sign up user");
		String useremail = "creditpointtest@tech.edureka.in";
		String pass = "123456789";
		String name = "TEST";
		String phone = "123456789";
	    userHomePage = signInModalPage.RandUserLogin(useremail, pass);
	    
		// Verify User Home Page
		addLog("Verify User Home Page");
		userHomePage=userHomePage.verifyRandUserLoggedIn(name);

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
    	// Verify Order Summary Page
    	addLog("Verify Order Summary Page");
    	orderSummaryPage=orderSummaryPage.verifyPage();
    	String Savings = selectedCoursePage.getCourse_Savings();
    	System.out.println("SAVINGS : " + Savings);
    	String totalcost = selectedCoursePage.getCourse_TotalAmount();
    	System.out.println("TotalCost : " + totalcost);
    	String Tax = selectedCoursePage.getCourse_ServiceTax();
    	System.out.println("ServiceTax : " + Tax);
    
        // Change Currency
        String currency= propertyReader.readTestData("INRCurrency");
        addLog("Select currecnt" +currency +" from currnecy table");
        orderSummaryPage=orderSummaryPage.changeCurrency(currency);

        // Pre Order Table Assertion
    	addLog("Verify Data in Pre-Order table");
    	String paymentGateway = propertyReader.readTestData("PaymentGatewayPaypal");
    	String course_Id= propertyReader.readTestData("Course_ID_BigData");
    	String course_Title= propertyReader.readTestData("BigData_Hadoop_Title");
    	orderSummaryPage=orderSummaryPage.dataVerificationInUser_PreOrderTable(course_Id,course_Title,paymentGateway,currency,"MakePaymentWhenAnyTypeOfDiscountNotB1S2","pre_orders_PapalPayment","",totalcost,"","",useremail);


        // Click on Razor pay securely button
        addLog("Click on Razor pay securely button");
        razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();
        
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
       
     // DISABLE THE OFFER 
        addLog("Navigate to the Edureka application url");
        dashboardPage = applicationSetup_AdminPanel();

        // Verify Edureka Dashboard Page
        addLog("Verify Edureka Dashboard Page");
        dashboardPage =  dashboardPage.verifyDashboard();

        // Click on Signin Navigation Header
        addLog("Click on SignIn Navigation header");
        signInModalPage = dashboardPage.clickSignInHeader();
        signInModalPage = dashboardPage.clickSignInHeader();
       
        // Login Application through Admin credentials
        email = propertyReader.readApplicationFile("Admin_UserName");
        password = propertyReader.readApplicationFile("Admin_Password");
        addLog("Login Application through Admin credentials");
        adminDashboard= signInModalPage.Login_AdminPanel(AdminDashboard.class,email, password);

        adminDashboard = adminDashboard.navigateToOfferURL(AdminDashboard.class,TestCase_18_URL);
        
        // Land on Admin URL page.
        adminDashboard = adminDashboard.activateTheOffer("Inactive");
        		
    	// Logout from the Admin Panel
    	dashboardPage = adminDashboard.logout(DashboardPage.class);

        
      

    }

    		catch (final Error e) {
    			captureScreenshot("test_018_CCAvenue");
    			throw e;
    		} catch (final Exception e) {
    			captureScreenshot("test_018__CCAvenue");
    			throw e;
    		}
    	}
}
