package com.edureka.pages;

import java.util.*;
import java.sql.SQLException;
import java.text.*;

import org.codehaus.groovy.runtime.ConvertedClosure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.Database;
import com.edureka.util.DriverHelper;

public class SelectedCoursePage extends DriverHelper{

	//Case 1
	String tableName = "users";
	String columnNameToBeFetched = "id";
	String columnNameToBeMatched = "email";


	//Case 2
	String tableName2 = "user_events";
	String columnNameToBeFetched2 = "event_type";
	String columnNameToBeMatched2 = "user_id";

	private final LocatorReader selectedCoursesLocator;
	private final DatabaseVerifications databaseVerification;

	public SelectedCoursePage(WebDriver driver) {
		super(driver);
		databaseVerification = new DatabaseVerifications(driver);
		selectedCoursesLocator = new LocatorReader("SelectedCourse.xml");
	}

	/**
	 * Verify Selected Course page is open
	 * @param selectCourse
	 * @return
	 */
	public SelectedCoursePage verifySelectedCoursePage(String selectCourse){
		String course= "//h1[contains(text(),'"+selectCourse+"')]";
		WaitForElementPresent(course, getTimeOut());
		Assert.assertTrue(isElementPresent(course));
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}
	/**
	 * Verify Selected Course page is open
	 * @param selectCourse
	 * @return
	 */
	public SelectedCoursePage verifySelectedPopularCoursePage(String selectCourse){
		String course= "//h1[contains(text(),'"+selectCourse+"')]";
		WaitForElementPresent(course, getTimeOut());
		Assert.assertTrue(isElementPresent(course));
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 *  Click on Query box
	 * @return
	 * @throws InterruptedException 
	 */
	public SelectedCoursePage clickOnQueryBox() throws InterruptedException{
		scrollWindow(0, 1000);
		String queryBox= selectedCoursesLocator.getLocator("QueryBox.HeaderQueryBox");
		Thread.sleep(5000);
		mouseClickByLocator(queryBox, "Click on QuerBox");
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 * Drop query
	 * @param query
	 * @param phNumber
	 * @return
	 */
 public	String userName = "";
 public	String email ="";
 public	String phNumber = "";
	@SuppressWarnings("unchecked")
    public <T> T sendQuery(@SuppressWarnings("rawtypes") Class className){
		userName= "User"+randomString(2);
		email = "test" + randomString(3)+ "@tech.edureka.in";
	//	propertyReader.updatePropertyTestData("QueryEmail_Id", email1);
//		propertyReader.updatePropertyTestData("QueryUserName", userName1);
		String query = propertyReader.readTestData("Query");

		phNumber= propertyReader.readTestData("PhoneNumber");
		String tbQueryName = selectedCoursesLocator.getLocator("QueryBox.TBQueryName");
		WaitForElementVisible(tbQueryName, getTimeOut());
		sendKeys(tbQueryName, userName);

		String tbQueryEmail= selectedCoursesLocator.getLocator("QueryBox.TBQueryEmail");
		WaitForElementVisible(tbQueryEmail, getTimeOut());
		sendKeys(tbQueryEmail, email);


		String tbQueryPhNumberString= selectedCoursesLocator.getLocator("QueryBox.TBQueryPhNumber");
		WaitForElementVisible(tbQueryPhNumberString, getTimeOut());
		sendKeys(tbQueryPhNumberString, phNumber);

		String tbInquiry = selectedCoursesLocator.getLocator("QueryBox.TBInquiry");
		WaitForElementVisible(tbInquiry, getTimeOut());
		sendKeys(tbInquiry, query);

		String btnSubmit= selectedCoursesLocator.getLocator("QueryBox.BTNSubmit");
		WaitForElementVisible(btnSubmit, getTimeOut());
		clickOn(btnSubmit);
		
		getPageLoadTimeOut();
        
		return (T) PageFactory.initElements(driver,className);
	}

 public String Inquiry_UserName(){
	 return userName;
	}	

 public String Inquiry_Email(){
	 return email;
	}
 public String Inquiry_Phone() {
		return phNumber;
	}
	/**
	 * Verify Submit Inquiry Message
	 * @return
	 */
	public SelectedCoursePage verifySumbitInquiry(){
		String messageSubmitInquiry= selectedCoursesLocator.getLocator("QueryBox.MsgSubmitInquiry");
		WaitForElementPresent(messageSubmitInquiry, 5);
		Assert.assertTrue(isElementPresent(messageSubmitInquiry));
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 * Select Currency
	 * @param currency
	 * @return
	 */
	public SelectedCoursePage selectCurrency(String currency){
		WebElement lTChange = driver.findElement(By.xpath(selectedCoursesLocator.getLocator("Currency.LTCurrency")));
		lTChange.click();
		List<WebElement> currencyList = driver.findElements(By.xpath(selectedCoursesLocator.getLocator("Currency.CurrencyList")));
		int totalCurrency= currencyList.size();
		for(int i=1;i<=totalCurrency;i++){
			String lTcurrency= "//ul[@id='currency-select-item']/li["+i+"]/a";
			String currencyName= getText(lTcurrency);
			if(currencyName.contains(currency)){
				clickOn(lTcurrency);
				break;
			}
		}
		WebElement btnApply = driver.findElement(By.xpath(selectedCoursesLocator.getLocator("Currency.BTNApply")));
		btnApply.click();
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 * Click on Enroll Button
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T clickOnEnrollButton(@SuppressWarnings("rawtypes") Class className){
		String currencyPopUp = selectedCoursesLocator.getLocator("Currency.CurrencyPopUp");
		WebElement btnEnroll = driver.findElement(By.xpath(selectedCoursesLocator.getLocator("BTNEnroll")));
		WaitForElementNotVisible(currencyPopUp, 3);
		waitForWebElementPresent(btnEnroll);
		btnEnroll.click();
		return (T) PageFactory.initElements(driver, className);
	}

	/**
	 * Click on Add To Wishlist Link
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T clickOnAddToWishlistIcon(@SuppressWarnings("rawtypes") Class className){
		WebElement lTwishlst = driver.findElement(By.xpath(selectedCoursesLocator.getLocator("LTWishlst")));
		waitForWebElementPresent(lTwishlst);
		lTwishlst.click();
		return (T) PageFactory.initElements(driver, className);
	}

	/**
	 * Click on Play Button of Video
	 * @return
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("unchecked")
	public <T> T playClassRecording(@SuppressWarnings("rawtypes") Class className) throws InterruptedException{
	//	WebElement btnPlayVideo = driver.findElement(By.cssSelector(selectedCoursesLocator.getLocator("BTNPlayVideo")));
		WebElement play = driver.findElement(By.cssSelector(".prevideopanelplay"));
		waitForWebElementPresent(play);
		play.click();
		/*javaScriptExecute("document.getElementById('overlayvideopanel').click()");
		Thread.sleep(10000);
		*/return (T) PageFactory.initElements(driver, className);
	}

	/**
	 * Verify Event Type is IN, when User Drop a Query.
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage verifyEventTypeViaDropQuery() throws Exception{
		String tBuserEmailId = propertyReader.readRunTimeData("Email_Id");
		String tEventType = propertyReader.readRunTimeData("Event_Type_DROP");
		Database dbObject = new Database();
		String userId = dbObject.getRecord(tableName, columnNameToBeFetched, columnNameToBeMatched, tBuserEmailId);
		String userEventType = dbObject.getRecord(tableName2, columnNameToBeFetched2, columnNameToBeMatched2, userId);
		if(tEventType.equals(userEventType)){
			System.out.println("Event Type Is IN For Sign Up Via Drop Us a Query");
		}
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}  

	/**
	 *  Click on Sign In Link
	 * @return
	 * @throws InterruptedException 
	 */
	public SignInModalPage clickSignInHeader() throws InterruptedException {
		String lTsignIn =selectedCoursesLocator.getLocator("LTsignIn");
		Thread.sleep(5000);
		Assert.assertTrue(isElementPresent(lTsignIn));
		clickOn(lTsignIn);
		return PageFactory.initElements(driver, SignInModalPage.class);
	}

	/**
	 *  Refresh browser
	 */
	public SelectedCoursePage referhBrowser(){
		driver.navigate().refresh();
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 *  Verify User name
	 * @return
	 */
	public SelectedCoursePage verifySignUpSuccessfully() {
		String pageName =selectedCoursesLocator.getLocator("ProfileDropdown.UserName");
		WaitForElementPresent(pageName,20);
		WebElement userNameHeader = driver.findElement(By.xpath(pageName));
		String tBuserName = propertyReader.readRunTimeData("UserName");
		String pageheaderText = userNameHeader.getText();
		Assert.assertTrue(pageheaderText.equalsIgnoreCase(tBuserName));
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 *  Verify Data in user Table
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage dataVerificationInUserTable(String custID, String Email, String Username,String PhoneNum) throws Exception {
		SelectedCoursePage selectedCourse;
		selectedCourse = (SelectedCoursePage)databaseVerification.dataVerificationInUserTable(SelectedCoursePage.class,"1", Email, Username,PhoneNum);
		return selectedCourse;
	}
	/**
	 *  Verify Data in user Table
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage dataVerificationInUserTable(String emailId,String userName,String phone) throws Exception {
		SelectedCoursePage selectedCourse;
		selectedCourse = (SelectedCoursePage)databaseVerification.dataVerificationInUserTable(SelectedCoursePage.class,emailId,userName,phone);
		return selectedCourse;
	}

	/**
	 *  Verify Data in User_Leads Table
	 * @param courseID
	 * @param webSiteAction
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage dataVerificationInUser_LeadsTable(String courseId, String webSiteAction, String country,String campaignSource, String campaignName, String campaignMedium,String email) throws Exception {
		SelectedCoursePage selectedCourse;
		selectedCourse = (SelectedCoursePage)databaseVerification.dataVerificationInUserLeadTable(SelectedCoursePage.class, courseId, webSiteAction, country, campaignSource, campaignName, campaignMedium,email);
		return selectedCourse;
	}

	/**
	 *  Verify Data in User Courses Table
	 * @param courseId
	 * @param isPaidValue
	 * @param courseGroup
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage dataVerificationInUser_CoursedTable(String courseId, String isPaidValue, String courseGroup,String UserID) throws Exception {
		SelectedCoursePage selectedCourse;
		selectedCourse = (SelectedCoursePage)databaseVerification.dataVerificationInUser_Course_Table(SelectedCoursePage.class, courseId, isPaidValue, courseGroup,UserID);
		return selectedCourse;
	}

	/**
	 *  Verify Data in user Events Table
	 * @param courseId
	 * @param event_context
	 * @param utm_source
	 * @param utm_campaign
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage dataVerificationInUser_EventTable(String courseId, String event_context,String utm_campaign, String event_Type,String UserID) throws Exception {
		SelectedCoursePage selectedCourse;
		selectedCourse = (SelectedCoursePage)databaseVerification.dataVerificationInUser_Events_Table(SelectedCoursePage.class, courseId, event_context, utm_campaign,event_Type,UserID);
		return selectedCourse;
	}

	/**
	 *  Verify Data in Ambassadors Table
	 * @param level_id
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage dataVerificationInUser_AmbassadorsTable(String UserID) throws Exception {
		SelectedCoursePage selectedCourse;
		selectedCourse = (SelectedCoursePage)databaseVerification.dataVerificationInAmbassadors(SelectedCoursePage.class,UserID);
		return selectedCourse;
	}

	/**
	 *  Click on Profile Dropdown
	 * @param userName
	 * @return
	 */
	public SelectedCoursePage clickOnProfileDropdown(String userName){
		String dropdown ="//span[contains(text(),'"+userName+"')]";
		WaitForElementPresent(dropdown, getTimeOut());
		clickOn(dropdown);
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 *  Logout application
	 * @return
	 */
	public DashboardPage logout(){
		String logout=selectedCoursesLocator.getLocator("ProfileDropdown.LTLogout");;
		WaitForElementPresent(logout, getTimeOut());
		mouseOver(logout);
		clickOn(logout);
		return PageFactory.initElements(driver, DashboardPage.class);
	}


	/**
	 *  Verify Data for inquiry
	 * @param courseId
	 * @param webSiteAction
	 * @param event_context
	 * @param utm_campaign
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage verifyDataForInquiry(String courseId, String webSiteAction,String event_context, String utm_campaign,String courseGroup,String eventType,String level_id,String email,String username) throws Exception {
		SelectedCoursePage selectCourse;
		selectCourse = (SelectedCoursePage)databaseVerification.dataVerificationFor_Inquiry(SelectedCoursePage.class, courseId, webSiteAction,event_context, utm_campaign,courseGroup,eventType,level_id,email,username);
		return selectCourse;
	}


	/**
	 * Select Course option from course Dropdown
	 * @param className
	 * @param courseOption : My Course
	 *                       All Course
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T selectCourseOption(@SuppressWarnings("rawtypes") Class className, String courseOption) {
		String tabCourses =selectedCoursesLocator.getLocator("TabCourses");
		WaitForElementPresent(tabCourses, getTimeOut());
		Assert.assertTrue(isElementPresent(tabCourses));
		clickOn(tabCourses);

		String option ="//ul[@class='dropdown-menu']//li/a[contains(text(),'"+courseOption+"')]";
		WaitForElementPresent(option, getTimeOut());
		Assert.assertTrue(isElementPresent(option));
		clickOn(option);
		return (T) PageFactory.initElements(driver, className);
	}

	/**
	 *  Verify Banner
	 * @return
	 */
	public SelectedCoursePage verifyBanner(String bannerImg){
		getWebDriver().navigate().refresh();
		String banner= "//img[contains(@src,'img/creative/"+bannerImg+".png')]";
		WaitForElementPresent(banner, getTimeOut());
		Assert.assertTrue(isElementPresent(banner));
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 * 
	 * @param discount_Description
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage verifyDataForOfferFramework(String discount_Description) throws Exception {
		SelectedCoursePage selectCourse;
		selectCourse = (SelectedCoursePage)databaseVerification.dataVerification_For_OfferFramework(SelectedCoursePage.class, discount_Description);
		return selectCourse;
	}

	/**
	 *  Search Course
	 * @param course
	 * @return
	 */
	public SelectedCoursePage searchCourse(String course) {
		String tbSearch =selectedCoursesLocator.getLocator("TBSearch");
		WaitForElementPresent(tbSearch, getTimeOut());
		sendKeys(tbSearch, course);

		String searchedCourse = selectedCoursesLocator.getLocator("SearchedData");
		WaitForElementPresent(searchedCourse, 20);
		clickOn(searchedCourse);
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 *  Verify Pink Message under Course
	 * @param message
	 * @return
	 */
	public SelectedCoursePage verifyPinkMessage(String message) {
		String pinkMessage ="//b[contains(text(),'"+message+"')]";
		WaitForElementPresent(pinkMessage, getTimeOut());
		Assert.assertTrue(isElementPresent(pinkMessage));
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 *  Currency Verification in Offer framework
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage currencyVerificationInDatabase_For_OfferFramework() throws Exception {
		SelectedCoursePage selectedCoursePage;
		selectedCoursePage = (SelectedCoursePage)databaseVerification.currencyVerificationInDatabase_For_OfferFramework(SelectedCoursePage.class);
		return selectedCoursePage;
	}

	/**
	 *  Verify Selected course image
	 * @return
	 */
	public SelectedCoursePage bigDataHadoopImage() {
		String imgBigDataAndHadoop =selectedCoursesLocator.getLocator("ImgBigDataHadoop");
		WaitForElementPresent(imgBigDataAndHadoop, getTimeOut());
		Assert.assertTrue(isElementPresent(imgBigDataAndHadoop));
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 * Verify Data in user Completed_Queue_Jobs Table
	 * 
	 * @param courseId
	 * @param event_context
	 * @param utm_source
	 * @param utm_campaign
	 * @return
	 * @throws Exception
	 */
	public  SelectedCoursePage dataVerificationInCompleted_Queue_Jobs_Table(String userEmail, String courseID) throws Exception {
		SelectedCoursePage selectedCoursePage;
		selectedCoursePage = ( SelectedCoursePage)databaseVerification.dataVerificationInCompleted_Queue_Jobs_Table(SelectedCoursePage.class,userEmail ,courseID);
		return selectedCoursePage;
	}
	
	/*public SelectedCoursePage dataVerificationInCompleted_Queue_JobsTable(String email, String courseid){
		
		
	}*/
	/**
	 *  Verify Need Assistance
	 * @return
	 * @throws Exception 
	 */
	public SelectedCoursePage verifyOnlineClassroomAndtimeZone(final Calendar calc,String timeZone) throws Exception {
		int hr,min;
		String onlineClassroom=selectedCoursesLocator.getLocator("OnlineClassroom");
		Assert.assertTrue(getText(onlineClassroom).contains("Online Classroom"));

		//Time zone
		String istGmt=selectedCoursesLocator.getLocator("ISTGMT");
		String timeZoneVal=getText(istGmt);
		Assert.assertTrue(timeZoneVal.contains(timeZone));

		//Batch details       
		timeZone=timeZoneVal.split("GMT ")[1];
		String hour=timeZone.split(":")[0];
		String minute=timeZone.split(":")[1].replace(")", "");

		hr=Integer.parseInt(hour);
		min=Integer.parseInt(minute);
		System.out.println(calc.getTime());
		Calendar cal1=new GregorianCalendar();
		cal1=calc;
		cal1.add(Calendar.HOUR_OF_DAY, hr);
		if (hour.contains("+")){
			cal1.add(Calendar.MINUTE, min);
		}
		else{
			cal1.add(Calendar.MINUTE, -min);
		}
		String daySpan=selectedCoursesLocator.getLocator("Day");
		String dayVal=getText(daySpan);
		String day=cal1.getTime().toString();
		Assert.assertTrue(day.contains(dayVal)); 
		if (hour.contains("+")){
			cal1.add(Calendar.HOUR_OF_DAY, -hr);
			cal1.add(Calendar.MINUTE, -min);
		}
		else{
			cal1.add(Calendar.HOUR_OF_DAY, -hr);
			cal1.add(Calendar.MINUTE, -min);
		}
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}
	/**
	 *  verify No Of Live Batches and enrollment
	 * @return
	 * @throws InterruptedException
	 */
	public SelectedCoursePage verifyNoOfLiveBatchesAndEnrollment() throws InterruptedException {
		List<WebElement> noOfCourses = driver.findElements(By.xpath(".//*[@id='web_view']/div[3]/div/article/div"));
		Log("No Of live batches clip"+noOfCourses.size());
		//Check for enroll button
		for(int i=1;i<noOfCourses.size()-1;i++){
			String enrollNow="//*[@id='web_view']/div[3]/div/article/div["+i+"]/div[5]/a/span";
			WaitForElementPresent(enrollNow,getTimeOut());
			WaitForElementVisible(enrollNow,getTimeOut());
			Assert.assertTrue(isElementPresent(enrollNow));			
		}
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}
	/**
	 *  verify No Of Live Batches and enrollment
	 * @return
	 * @throws Exception 
	 */
	public SelectedCoursePage selfPacedTrainingElementsAndCoursePrice(String selfCourseDec,String currency,String courseId) throws Exception {
		String onlineClassroom=selectedCoursesLocator.getLocator("OnlineSelfLearning");
		String online_SelfLearning=propertyReader.readTestData("Online_SelfLearning");
		System.out.println("Actual :"+getText(onlineClassroom));
		System.out.println("Expected :"+online_SelfLearning);
		Assert.assertTrue(getText(onlineClassroom).trim().contains(online_SelfLearning));

		String currencyVal=selectedCoursesLocator.getLocator("CurrencySpan");
		System.out.println("Actual Currency :"+getText(currencyVal));
		System.out.println("Expected Currency :"+currency);
		Assert.assertTrue(getText(currencyVal).contains(currency));

		String price=databaseVerification.getCoursePrice(courseId, currency);
		String priceValue=selectedCoursesLocator.getLocator("PriceValue");
		System.out.println("Actual price :"+price);
		System.out.println("priceValue :"+getText(priceValue));
		String text=getText(priceValue).replace(",", "");
		Assert.assertTrue(text.contains(price));

		String enrollNow=selectedCoursesLocator.getLocator("EnrollNow");

		Assert.assertTrue(getText(enrollNow).contains("ENROLL NOW"));

		String onlineSelfCourseDesc=selectedCoursesLocator.getLocator("OnlineSelfCourseDesc");
		Assert.assertTrue(getText(onlineSelfCourseDesc).contains(selfCourseDec));

		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 *  Verify Banner for Additional Discount 
	 * @return
	 */
	public SelectedCoursePage verifyAdditonalDiscountBanner(){
		String bannerMessage= selectedCoursesLocator.getLocator("AddtionalDiscountBanner");
		WaitForElementPresent(bannerMessage, 5);
		Assert.assertTrue(isElementPresent(bannerMessage));
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 *  Verify Referral Discount message 
	 * @return
	 */
	public SelectedCoursePage verifyAddtionalReferralAndOfferDiscountMessage(){
		String referralDisocunt= selectedCoursesLocator.getLocator("ReferralDiscountMessage");
		Assert.assertTrue(isElementPresent(referralDisocunt));
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 *  Verify Discount for Existing customer with credit points
	 * @return
	 */
	public SelectedCoursePage verifyDiscountMessageForExisingCustomer(){
		String existingCustomerDiscount= selectedCoursesLocator.getLocator("DiscountExistingCustomer");
		String creditPoints = propertyReader.readRunTimeData("EarnedCredits");
		_waitForJStoLoad();
		String msg = getText(existingCustomerDiscount);
		System.out.println("msg:::: " +msg);
		Assert.assertTrue(msg.contains("Exclusive 20% discount for existing customers + "+creditPoints+" credits available"));
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}

	/**
	 *  Change Country
	 * @param countryName
	 * @return
	 */
	public SelectedCoursePage changeCountryOnClpPopup(String countryName) throws InterruptedException{
		_waitForJStoLoad();
		String ddCountry = selectedCoursesLocator.getLocator("SelfDDCountry");
		WaitForElementEnabled(ddCountry, getTimeOut());
		WaitForElementPresent(ddCountry, getTimeOut());
		clickOn(ddCountry);
		Thread.sleep(2000);
		String tbCountry= selectedCoursesLocator.getLocator("SelfTBCountry");
		WaitForElementEnabled(tbCountry, getTimeOut());
		WaitForElementPresent(tbCountry, getTimeOut());
		sendKeysByKeyboard(tbCountry, countryName);
		Thread.sleep(2000);
		String country = "//a[contains(text(),'"+countryName+"')]";
        WaitForElementPresent(country, getTimeOut());
		clickOn(country);
        String applyBtn=selectedCoursesLocator.getLocator("Apply");
		clickOn(applyBtn);
		_waitForJStoLoad();
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}


	/**
	 *  Database verification in Pre_Order table
	 * @param course_Id
	 * @param courseTitle
	 * @return
	 * @throws Exception
	 */
	public SelectedCoursePage dataVerificationInUser_PreOrderTable(String course_Id, String courseTitle,String gateway,String currency,String testCaseName,String sheetName,String Discount,String TotalAmount,String price,String serviceTax,String emailId) throws Exception {
		SelectedCoursePage selectedCoursePage;
		selectedCoursePage = (SelectedCoursePage)databaseVerification.dataVerificationInPreOrderTable(MyProfilePage.class, course_Id, courseTitle,gateway,currency,testCaseName,sheetName,Discount,TotalAmount,price,serviceTax,emailId);
		return selectedCoursePage;
	}

	public SelectedCoursePage changeCountryOnInnerNavigation(String countryName) throws InterruptedException{
		_waitForJStoLoad();
		String ddCountry = selectedCoursesLocator.getLocator("InnerDDCountry");
		WaitForElementEnabled(ddCountry, getTimeOut());
		WaitForElementPresent(ddCountry, getTimeOut());
		clickOn(ddCountry);
		Thread.sleep(2000);
		String tbCountry= selectedCoursesLocator.getLocator("InnerTBCountry");
		WaitForElementPresent(tbCountry, getTimeOut());
		sendKeysByKeyboard(tbCountry, countryName);
		Thread.sleep(2000);
		String country = "//span[contains(text(),'"+countryName+"')]";

		WaitForElementPresent(country, getTimeOut());
		clickOn(country);

		_waitForJStoLoad();
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}


	/**
	 *  verify No Of Live Batches and enrollment
	 * @return
	 * @throws Exception 
	 */
	public SelectedCoursePage instructorLedTrainingElementsAndCoursePrice(String currency,String courseId) throws Exception {

		//Currency
		String currencySpan=selectedCoursesLocator.getLocator("Currency_Clp");
		System.out.println(getText(currencySpan));
		System.out.println(currency);
		Assert.assertTrue(getText(currencySpan).contains(currency));

		//Price
		String price=databaseVerification.getCoursePrice(courseId, currency);
		String priceValue=selectedCoursesLocator.getLocator("PriceValue");
		System.out.println("Actual price :"+price);
		System.out.println("priceValue :"+getText(priceValue));
		String text=getText(priceValue).replace(",", "");
		Assert.assertTrue(text.contains(price));

		//Batch Start date
		String batchStartDate=selectedCoursesLocator.getLocator("BatchStartDate");
		Assert.assertTrue(isElementPresent(batchStartDate));

/*		//Verify batch listing in ascending order
		Assert.assertTrue(verifyBatchListingInAscendingOrder(courseId));  
*/
		return PageFactory.initElements(driver, SelectedCoursePage.class);
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean verifyBatchListingInAscendingOrder(String courseId) throws Exception{
		List<WebElement> noOfCourses=driver.findElements(By.xpath("//*[@class='coursebatchdata']/article/div"));
		ArrayList<Date> list1 = new ArrayList<Date>();
		ArrayList<Date> list2=new ArrayList<Date>();
		int size=noOfCourses.size();
		for (int i = 1; i < size; i++) {
			String day=getText("//div[@class='coursebatchdata']/article/div["+i+"]/div/p[1]");
			String month=getText("//div[@class='coursebatchdata']/article/div["+i+"]/div/b[1]");
			String query=propertyReader.readTestData("Query_Year");
			query=query.replace("#", courseId);
			String year=databaseVerification.executeSignleQuery(query);
			year=year.split("-")[0];
			String date=year+"-"+month+"-"+day;
			DateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
			Date date_First=df.parse(date);
			list1.add(date_First);
			list2.add(date_First);
		}
		Collections.sort(list2);
		boolean isSorted=list1.equals(list2);
		return isSorted;
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Calendar setGMTDate(String courseId) throws Exception{
		String day=getText(selectedCoursesLocator.getLocator("DD"));
		String month=getText(selectedCoursesLocator.getLocator("Month"));
		String hourMinute=getText(selectedCoursesLocator.getLocator("HoursMinute"));
		String hm=hourMinute.split(" ")[0];
		String query=propertyReader.readTestData("Query_Year");
		query=query.replace("#", courseId);
		String year=databaseVerification.executeSignleQuery(query);
		year=year.split("-")[0];
		String date=hm+" "+day+"/"+month+"/"+year;
		DateFormat df = new SimpleDateFormat("HH:mm dd/MMM/yyyy");
		Calendar cal = new GregorianCalendar();
		Date myDateTime = df.parse(date);
		cal.setTime(myDateTime);
		if (hourMinute.contains("PM")) {
			cal.add(Calendar.HOUR_OF_DAY, 12);
		}
		cal.add(Calendar.HOUR_OF_DAY, -5);
		cal.add(Calendar.MINUTE, -30);
		return cal;
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDate(String courseId) throws Exception{
		String day=getText(selectedCoursesLocator.getLocator("DD"));
		String month=getText(selectedCoursesLocator.getLocator("Month"));
		String query=propertyReader.readTestData("Query_Year");
		query=query.replace("#", courseId);
		String year=databaseVerification.executeSignleQuery(query);
		year=year.split("-")[0];
		String date=day+"-"+month+"-"+year;
		return date;
	}

	public String getCourseID(String Slug) throws Exception {
       
	 Database d = new Database();
	 String a = d.getRecord("courses","id",new String[]{"slug","visibility"},new String[]{Slug,"1"});
	 System.out.println("Course ID of the for the Course "+Slug+" : "+ a);	
	 return a;
	}

	public String getCourseGroup(String slug) throws Exception {
		 Database d = new Database();
		 String a = d.getRecord("courses","course_group",new String[]{"slug","visibility"},new String[]{slug,"1"});
		 System.out.println("Course Group for the Course "+slug+" : "+ a);	
		 return a;
		
	}
	
	public String getCourseTitle(String slug) throws Exception{
		
		 Database d = new Database();
		 String a = d.getRecord("courses","analytics_title",new String[]{"slug","visibility"},new String[]{slug,"1"});
		 System.out.println("Course Group for the Course "+slug+" : "+ a);	
		 return a;
	}

	public String getUserID(String Email) throws Exception {
		Database d = new Database();
		String id = d.getRecord("users","id","email",Email);
		System.out.println("User_id of "+Email+" : "+id);
		return id;
	}

@FindBy(css = "#org_price") WebElement Original_Price;
	public String getCourse_originalPrice() {
		String courserprice = "";
       if (Original_Price.isDisplayed())
       {   
    	   courserprice = Original_Price.getText().replace(",","");
       }
       else {
    	   courserprice = "0";
       }
	    return courserprice;
	}

@FindBy(css = ".savingsecord>div:nth-child(2)>div>p:nth-child(1)>.taxvalue") WebElement serviceTax;
@FindBy(css = ".savingsecord>div:nth-child(2)>div>p:nth-child(2)>.taxvalue") WebElement KKcess;
@FindBy(css = ".savingsecord>div:nth-child(2)>div>p:nth-child(3)>.taxvalue") WebElement sbTax;

	public String getCourse_ServiceTax() {
		String servicetax = serviceTax.getText().replace(",","");
		String kkcess = KKcess.getText().replace(",","");
		String sbcess= sbTax.getText().replace(",","");
		String course_servicetax1 = "";
		if (serviceTax.isDisplayed())
		{
		int TotalTax = Integer.parseInt(servicetax.trim())+Integer.parseInt(kkcess.trim())+Integer.parseInt(sbcess.trim());
		 course_servicetax1 = String.valueOf(TotalTax);
		}
		else{
		   course_servicetax1 = "0";	
		}
		return course_servicetax1;
	}
@FindBy(css = "#disc_price") WebElement course_Discount;	
	public String getCourse_discountedPrice(){
		
		String course_discountedPrice = "";
		
		if (course_Discount.getLocation()!= null){
		   course_discountedPrice = course_Discount.getText().replace(",","");
		 }
		else {
			course_discountedPrice = "0";
		}
		return course_discountedPrice;
	}
@FindBy(css = "#net_price") WebElement NetPrice;	
	public String getCourse_netPrice(){
		String course_netPrice  = "";
	 if ((NetPrice.getSize() == null))	
	 {
		 course_netPrice = "0";
	}
	 else { 
		 course_netPrice = NetPrice.getText().replace(",","");
		 	 
	 }
	return course_netPrice;
	}
	
@FindBy(css = "#total_price") WebElement TotalAmount;	
	public String getCourse_TotalAmount(){
		
		String course_TotalAmount = "";
		if (TotalAmount.isDisplayed()){
		course_TotalAmount = TotalAmount.getText().replace(",","");
		}
		else {
			course_TotalAmount = "0";
		}
		 return course_TotalAmount;
	}
@FindBy(css = "#total-savings")WebElement Savings; 	
   public String getCourse_Savings(){
	  
	   String saving= "";
	   if (Savings.getSize() != null){  
	   saving = Savings.getAttribute("data-save").replace(",","");
	   }
	   else {
		   saving = "0";
	   }
	   return saving;
   }


	
}

