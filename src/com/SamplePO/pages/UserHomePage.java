package com.edureka.pages;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.Database;
import com.edureka.util.DriverHelper;

public class UserHomePage extends DriverHelper {

    private final LocatorReader userHomePageLocator;
    private final DatabaseVerifications databaseVerification;

    public UserHomePage(WebDriver driver) {
        super(driver);
        databaseVerification = new DatabaseVerifications(driver);
        userHomePageLocator = new LocatorReader("UserHome.xml");

    }

    /**
     *  Verify User Page
     * @param user : User Name
     * @return
     */
    public UserHomePage verifyUserPage() {
        String pageName =userHomePageLocator.getLocator("HomePage.PageHeader");
        WaitForElementPresent(pageName,20);
        WebElement userNameHeader = driver.findElement(By.xpath(pageName));
        String tBuserName = propertyReader.readRunTimeData("Default_LoginUser");
        String pageheaderText = userNameHeader.getText();
        Assert.assertTrue(pageheaderText.equalsIgnoreCase(tBuserName));
        return PageFactory.initElements(driver, UserHomePage.class);
    }
   public UserHomePage verifyRandUserLoggedIn(String username){
	   String pageName =userHomePageLocator.getLocator("HomePage.PageHeader");
       WaitForElementPresent(pageName,20);
       WebElement userNameHeader = driver.findElement(By.xpath(pageName));
       String pageheaderText = userNameHeader.getText();
       Assert.assertTrue(pageheaderText.equalsIgnoreCase(username));
       return PageFactory.initElements(driver, UserHomePage.class);
    }
   
    /**
     * Select Course 
     * @param course
     * @return
     */
    public SelectedCoursePage selectCourse(String course) {
        List<WebElement> courses = driver.findElements(By.xpath(userHomePageLocator.getLocator("HomePage.Courses")));
        int rows = courses.size();
        for (int i = 1; i < rows; i++) {
            String coursesList = "//div[@class='owl-stage-outer']/div/div["+i+"]/div/ul/a/li/h4";
            WaitForElementPresent(coursesList, getTimeOut());
            String coursesName= getText(coursesList);
            if (coursesName.contains(course)) {
                String courseLink = "//div[@class='owl-stage-outer']/div/div["+i+"]/div/ul/a";
                String loc = getAttribute(courseLink, "href");
                driver.get(loc);
                break;
            }
        }
        return PageFactory.initElements(driver, SelectedCoursePage.class);
    }
    /**
     * Select Course 
     * @param course
     * @return
     */
    public SelectedCoursePage selectCourse_NewAddition(String course) {
        scrollWindow(0, 500);
        System.out.println("Course"+course);
        List<WebElement> courses = driver.findElements(By.xpath(userHomePageLocator.getLocator("HomePage.Courses_New")));
        int rows = courses.size();
        System.out.println("rows"+rows);
        for (int i = 1; i < rows; i++) {
            String coursesList = "//*[@id='new-not']/section/div/div[1]/div/div["+i+"]/div/ul/a/li/h4";
            WaitForElementPresent(coursesList, getTimeOut());
            String coursesName= getText(coursesList);
            System.out.println("New Course : "+coursesName);
            if (coursesName.contains(course)) {
                System.out.println("I am in If");
                String courseLink = "//*[@id='new-not']/section/div/div[1]/div/div["+i+"]/div/ul/a";
                String loc = getAttribute(courseLink, "href");
                driver.get(loc);
                break;
            }
        }
        return PageFactory.initElements(driver, SelectedCoursePage.class);
    }

    /**
     *  Refresh browser
     * @return
     */
    public DashboardPage refreshBrowser() {
        driver.navigate().refresh();
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     *  Click on Profile dropdown
     * @param userName
     * @return
     */
    public UserHomePage clickOnProfileDropdown(){
        _waitForJStoLoad();
        
    	WebElement r = driver.findElement(By.xpath("//*[@id='header-II']/section/nav/div/ul[2]/li[1]/a/span"));
    	//String dropdown =".dropdown-toggle.withripple>.webinar-profile-name";
        _waitForJStoLoad();
     //   WaitForElementPresent(dropdown, getTimeOut());
        /*WaitForElementClickable(dropdown,getTimeOut());
        clickOn(dropdown);
        */
        WaitForElementPresent("css= .dropdown-toggle.withripple>.webinar-profile-name", getTimeOut());
        r.click();
        return PageFactory.initElements(driver, UserHomePage.class);
    }

    /**
     *  Click on Change Password link
     * @return
     */
    public ChangePasswordPage clickOnchangePasswordLink(){
        String changePassword=userHomePageLocator.getLocator("ProfileDropdown.LTChangePassword");;
        WaitForElementPresent(changePassword, getTimeOut());
        mouseOver(changePassword);
        clickOn(changePassword);
        return PageFactory.initElements(driver, ChangePasswordPage.class);
    }

    /**
     *  Logout application
     * @return
     */
    public DashboardPage logout(){
        String logout=userHomePageLocator.getLocator("ProfileDropdown.LTLogout");;
        WaitForElementPresent(logout, getTimeOut());
        mouseOver(logout);
        clickOn(logout);
        return PageFactory.initElements(driver, DashboardPage.class);
    }


    /**
     *  Verify Data in user Table
     * @return
     * @throws Exception
     */
    public UserHomePage dataVerificationInUserTable(String custID,String EmailID, String Username,String PhoneNum) throws Exception {
        UserHomePage userHomePage;
        userHomePage = (UserHomePage)databaseVerification.dataVerificationInUserTable(UserHomePage.class,custID,EmailID,Username,PhoneNum);
        return userHomePage;
    }
    /**
     *  Verify Data in user Table
     * @return
     * @throws Exception
     */
    public UserHomePage dataVerificationInUserTable(String emailId,String userName,String phone) throws Exception {
        UserHomePage userHomePage;
        userHomePage = (UserHomePage)databaseVerification.dataVerificationInUserTable(UserHomePage.class,emailId,userName,phone);
        return userHomePage;
    }

    /**
     *  Verify Data in User_Leads Table
     * @param courseID
     * @param webSiteAction
     * @return
     * @throws Exception
     */
    public UserHomePage dataVerificationInUser_LeadsTable(String courseId, String webSiteAction, String country,String campaignSource, String campaignName, String campaignMedium,String email) throws Exception {
        UserHomePage userHomePage;
        userHomePage = (UserHomePage)databaseVerification.dataVerificationInUserLeadTable(UserHomePage.class, courseId, webSiteAction, country, campaignSource, campaignName,campaignMedium,email);
        return userHomePage;
    }

    /**
     *  Verify Data in User Courses Table
     * @param courseId
     * @param isPaidValue
     * @param courseGroup
     * @return
     * @throws Exception
     */
    public UserHomePage dataVerificationInUser_CoursedTable(String courseId, String isPaidValue, String courseGroup,String UserID) throws Exception {
        UserHomePage userHomePage;
        userHomePage = (UserHomePage)databaseVerification.dataVerificationInUser_Course_Table(UserHomePage.class, courseId,isPaidValue,courseGroup,UserID);
        return userHomePage;
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
    public UserHomePage dataVerificationInUser_EventTable(String courseId, String event_context,String utm_campaign, String event_Type,String UserID) throws Exception {
        UserHomePage userHomePage;
        userHomePage = (UserHomePage)databaseVerification.dataVerificationInUser_Events_Table(UserHomePage.class, courseId,event_context,utm_campaign,event_Type,UserID);
        return userHomePage;
    }

    /**
     *  Verify Data in Ambassadors Table
     * @param level_id
     * @return
     * @throws Exception
     */
    public UserHomePage dataVerificationInUser_AmbassadorsTable(String UserID) throws Exception {
        UserHomePage userHomePage;
        userHomePage = (UserHomePage)databaseVerification.dataVerificationInAmbassadors(UserHomePage.class,UserID);
        return userHomePage;
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
        String tabCourses =userHomePageLocator.getLocator("HomePage.TabCourses");
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
     *  Select Community
     * @param className
     * @param option
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> T selectOptionFromCommunity(Class className,String option) {
        String ddCommunity = userHomePageLocator.getLocator("HomePage.DDCommunity");
        WaitForElementPresent(ddCommunity, 30);
        clickOn(ddCommunity);

        String communityOption = "//ul[@class='dropdown-menu']//li/a[contains(text(),'"+option+"')]";
        WaitForElementPresent(communityOption, getTimeOut());
        clickOn(communityOption);
        return (T) PageFactory.initElements(driver, className);
    }

    /**
     *  Verify Admin User Page
     * @return
     */
    public UserHomePage verifyAdminUserPage() {
        String pageName =userHomePageLocator.getLocator("HomePage.PageHeader");
        WaitForElementPresent(pageName,20);
        WebElement userNameHeader = driver.findElement(By.xpath(pageName));
        String pageheaderText = userNameHeader.getText();
        Assert.assertTrue(pageheaderText.equalsIgnoreCase("Admin"));
        return PageFactory.initElements(driver, UserHomePage.class);
    }

    /**
     *  Logout application
     * @return
     */
    public AdminDashboard selectAdmin(){
        String admin=userHomePageLocator.getLocator("ProfileDropdown.LTAdmin");;
        WaitForElementPresent(admin, getTimeOut());
        mouseOver(admin);
        clickOn(admin);
        return PageFactory.initElements(driver, AdminDashboard.class);
    }

    /**
     *  Verify Banner
     * @return
     */
    public UserHomePage verifyBanner(String bannerImg){
        getWebDriver().navigate().refresh();
        String banner= "//img[contains(@src,'img/creative/"+bannerImg+".png')]";
        WaitForElementPresent(banner, getTimeOut());
        Assert.assertTrue(isElementPresent(banner));
        return PageFactory.initElements(driver, UserHomePage.class);
    }

    /**
     *  Currency Verification in Offer framework
     * @return
     * @throws Exception
     */
    public UserHomePage currencyVerificationInDatabase_For_OfferFramework() throws Exception {
        UserHomePage userHome;
        userHome = (UserHomePage)databaseVerification.currencyVerificationInDatabase_For_OfferFramework(UserHomePage.class);
        return userHome;
    }

    /**
     *  Verify Data for Offer Framework
     * @param discount_Description
     * @return
     * @throws Exception
     */
    public UserHomePage verifyDataForOfferFramework(String discount_Description) throws Exception {
        UserHomePage userHome;
        userHome = (UserHomePage)databaseVerification.dataVerification_For_OfferFramework(UserHomePage.class, discount_Description);
        return userHome;
    }

    /**
     *  Verify Edureka Logo
     * @return
     */
    public UserHomePage verifyEdurekaLogoOnHomePage(){
        String edurekaLogo = userHomePageLocator.getLocator("EdurekaLogo");
        WaitForElementPresent(edurekaLogo, getTimeOut());
        Assert.assertTrue(isElementPresent(edurekaLogo));
        return PageFactory.initElements(driver, UserHomePage.class);
    }

    /**
     *  Click on My Profile Link
     * @return
     */
    public MyProfilePage clickOnMyProfile(){
        String lTMyProfile=userHomePageLocator.getLocator("ProfileDropdown.LTProfile");
        WaitForElementPresent(lTMyProfile, getTimeOut());
        clickOn(lTMyProfile);
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    /**
     *  Verify Referral Home page
     * @param referralName
     * @return
     */
    public UserHomePage verifyReferralHomePage(String referralName) {
        String referralUserName= "//span[contains(text(),'"+referralName+"')]";
        WaitForElementPresent(referralUserName, getTimeOut());
        Assert.assertTrue(isElementPresent(referralUserName));
        return PageFactory.initElements(driver, UserHomePage.class);
    }

    /**
     *  Verify Notifications
     * @return
     */
    public UserHomePage verifyNotification(){
        String iconBell=userHomePageLocator.getLocator("HomePage.IconBell");;
        WaitForElementPresent(iconBell, getTimeOut());
        mouseClickByLocator(iconBell,"");

        String notifications = userHomePageLocator.getLocator("HomePage.Notifications");
        WaitForElementPresent(notifications, getTimeOut());
        Assert.assertTrue(isElementPresent(notifications));
        return PageFactory.initElements(driver, UserHomePage.class);
    }
    /**
     * Verify Data in user Completed_Queue_Jobs_Table
     * 
     * @param courseId
     * @param event_context
     * @param utm_source
     * @param utm_campaign
     * @return
     * @throws Exception
     */
    public  UserHomePage dataVerificationInCompleted_Queue_Jobs_Table(String courseID, String userEmail) throws Exception {
        UserHomePage userHomePage;
        userHomePage = ( UserHomePage)databaseVerification.dataVerificationInCompleted_Queue_Jobs_Table(UserHomePage.class,userEmail,courseID);
        return userHomePage;
    }

    /**
     *  Verify Banner for Addtional Disocunt 
     * @return
     */
    public UserHomePage verifyAdditonalDiscountBanner(){
        String bannerMessage= userHomePageLocator.getLocator("AddtionalDiscountBanner");
        WaitForElementPresent(bannerMessage, 5);
        Assert.assertTrue(isElementPresent(bannerMessage));
        return PageFactory.initElements(driver, UserHomePage.class);
    }
    /**
     * Select Training Type
     * @param type
     * @return
     */
    public AllCoursesPage selectTrainingType(String type) {
        String trainingType = "//h5[text()='Training Type']/following::label[contains(text(),'" + type + "')]";
        WaitForElementPresent(trainingType, getTimeOut());
        clickOn(trainingType);
        return PageFactory.initElements(DriverHelper.driver, AllCoursesPage.class);
    }

    /**
     *  Verify Existing Customer Discount And Credit points message 
     * @return
     */
    public UserHomePage verifyExistingCustomerDiscountAndCreditPoints() {
        String discountAndCredit =userHomePageLocator.getLocator("ExistingDiscountAndCreditMessage");
        String creditPoints = propertyReader.readRunTimeData("EarnedCredits");
        WaitForElementPresent(discountAndCredit,20);
        String discount_And_Credit_Points = getText(discountAndCredit);
        Assert.assertTrue(discount_And_Credit_Points.contains("Exclusive 20% discount for existing customers + "+creditPoints+" credits available"));
        return PageFactory.initElements(driver, UserHomePage.class);
    }

    /**
     *  Verify course price after discount value and referral value
     * @param course
     * @param discountValue
     * @param referral_Disocunt
     * @return
     */
    public UserHomePage verifyCoursePriceAfterReferralAndOfferDiscount(String course, String discountValue){
        List<WebElement> courses = driver.findElements(By.xpath(userHomePageLocator.getLocator("HomePage.Courses")));
        int rows = courses.size();
        for (int i = 1; i < rows; i++) {
            String coursesList = "//div[@class='owl-stage-outer']/div/div["+i+"]/div/ul/a/li/h4";
            WaitForElementPresent(coursesList, getTimeOut());
            String coursesName= getText(coursesList);
            if (coursesName.contains(course)) {
                String actualCoursePrice = "//div[@class='owl-stage-outer']/div/div["+i+"]/div/ul/a/li/h5/span/b";
                String actualCoursePriceText = getText(actualCoursePrice);
                String actualCoursePriceText_1 = actualCoursePriceText.replace("\u20B9", "");
                String actualCoursePriceText_2 = actualCoursePriceText_1.replace(",", "");
                String actualCoursePriceText_3= actualCoursePriceText_2.trim();
                int actualCoursePriceValue = Integer.parseInt(actualCoursePriceText_3);
                System.out.println("actualCoursePriceValue::::" +actualCoursePriceValue );

                String coursePrice = "//div[@class='owl-stage-outer']/div/div["+i+"]/div/ul/a/li/h5/span/small";
                String coursePriceText= getText(coursePrice);
                String coursePriceText_1 = coursePriceText.replace("\u20B9", "");
                String coursePriceText_2 = coursePriceText_1.replace(",", "");
                String coursePriceText_3 = coursePriceText_2.trim();
                int coursePriceValue = Integer.parseInt(coursePriceText_3);
                int discountPercentage = Integer.parseInt(discountValue);
                double discountAmount =round((coursePriceValue*(discountPercentage/100.0f)),2);
                int discountAmount_1= (int)(coursePriceValue-discountAmount);
                Assert.assertEquals(discountAmount_1, actualCoursePriceValue);
                break;
            }

        }
        return PageFactory.initElements(driver, UserHomePage.class);
    }

	public String getUserID(String email) throws Exception {
		Database d = new Database();
		String id = d.getRecord("users","id","email",email);
		System.out.println("User_id of "+email+" : "+id);
		return id;
		
	}
}
