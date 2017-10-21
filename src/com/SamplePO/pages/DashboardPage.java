package com.edureka.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.DriverHelper;


public class DashboardPage extends DriverHelper{

    private LocatorReader dashboardLocator;
    private DatabaseVerifications databaseVerification;
    public DashboardPage(WebDriver driver) {
        super(driver);
        dashboardLocator = new LocatorReader("Dashboard.xml");
        databaseVerification = new DatabaseVerifications(driver);
    }

    /**
     *  Click on Sign In Link
     * @return
     */
    public SignInModalPage clickSignInHeader() {
        String lTsignIn =dashboardLocator.getLocator("HomePage.LTsignIn");
        _waitForJStoLoad();
         
        Assert.assertTrue(isElementPresent(lTsignIn));
        clickOn(lTsignIn);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Verify Dashboard Page
     * @return
     */
    public DashboardPage verifyDashboard() {
        String pageheader=dashboardLocator.getLocator("HomePage.PageHeader");
        _waitForJStoLoad();
        Assert.assertTrue(isElementPresent(pageheader));
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Select Course 
     * @param course
     * @return
     */
    public SelectedCoursePage selectCourse(String course) {
    	
        List<WebElement> courses = driver.findElements(By.xpath(dashboardLocator.getLocator("HomePage.Courses")));
        int rows = courses.size();
        for (int i = 1; i < rows; i++) 
        {
          String coursesList = "//div[@class='owl-stage-outer']/div/div["+i+"]/div/ul/a/li/h4";
          _waitForJStoLoad();
          String coursesName= getText(coursesList);
          if (coursesName.contains(course)) 
           {
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
    String TrendingCourse_CourseName = "";
    public SelectedCoursePage selectCourse_Trending() {
        scrollWindow(0, 100);
        _waitForJStoLoad();
        
         
          // UpperLimit of the course as per the locator : 10;
          // LowerLimit of the course as per the locator : 6
          Random ran = new Random();
          int num = ran.nextInt(10-6+1)+6;
         
          /*String a = driver.findElement(By.cssSelector("#trending>.edureka-courses>.courses-slider>div:nth-child(1)>div>div:nth-child("+i+")>div>ul>a")).getText();      
          System.out.println(a);
          */
          WebElement btn = driver.findElement(By.cssSelector("#trending>.edureka-courses>.courses-slider>div:nth-child(1)>div>div:nth-child("+num+")>div>ul>a>li>div>div"));
    	  String course = driver.findElement(By.cssSelector("#trending>.edureka-courses>.courses-slider>div:nth-child(1)>div>div:nth-child("+num+")>div>ul>a>li>h4")).getText();
    	  TrendingCourse_CourseName = course;
    	  System.out.println("Logged in coursename :" + course);
          btn.click();
    	  
    	  /// VERIFY IS THE USER HAS LOGGED INTO THE COURSE PAGE
    	  String c1= "//h1[contains(text(),'"+course+"')]";
  		  WaitForElementPresent(c1, getTimeOut());
  		  Assert.assertTrue(isElementPresent(c1));
  		  
       return PageFactory.initElements(driver, SelectedCoursePage.class);
   }
    
    public String getSlug()
    {
     String url = driver.getCurrentUrl();
     String[] a = url.split("/");
     String name = a[3];
     System.out.println(name);
    return name;
    }
    public String getCourseName_TrendingCourse(){
    	return TrendingCourse_CourseName;
    }

    /**
     * Select Course 
     * @param course
     * @return
     */
    String PopularCourse_CourseName;
    public SelectedCoursePage selectCourse_Popular() {
        
      scrollWindow(0, 100);
      _waitForJStoLoad();
      // UpperLimit of the course as per the locator : 10;
      // LowerLimit of the course as per the locator : 6
      Random ran = new Random();
      int num = ran.nextInt(10-6+1)+6;
     
      /*String a = driver.findElement(By.cssSelector("#trending>.edureka-courses>.courses-slider>div:nth-child(1)>div>div:nth-child("+i+")>div>ul>a")).getText();      
      System.out.println(a);
      */
      WebElement btn = driver.findElement(By.cssSelector("#popular>.edureka-courses>.courses-slider>div:nth-child(1)>div>div:nth-child("+num+")>div>ul>a>li>div>div"));
      JavascriptExecutor jse = (JavascriptExecutor)driver;
	  jse.executeScript("window.scrollBy(0,250)", "");
	 
	  String course = driver.findElement(By.cssSelector("#popular>.edureka-courses>.courses-slider>div:nth-child(1)>div>div:nth-child("+num+")>div>ul>a>li>h4")).getText();
	  PopularCourse_CourseName= course;
	  System.out.println("Logged in coursename :" + course);
      WaitForElementClickable("#popular>.edureka-courses>.courses-slider>div:nth-child(1)>div>div:nth-child("+num+")>div>ul>a>li>h4",getTimeOut());
	  btn.click();
	  
	  
	  
	  /// VERIFY IS THE USER HAS LOGGED INTO THE COURSE PAGE
	  String c1= "//h1[contains(text(),'"+course+"')]";
	  WaitForElementPresent(c1, getTimeOut());
	  Assert.assertTrue(isElementPresent(c1));
      return PageFactory.initElements(driver, SelectedCoursePage.class);
    }
    
    public String getCourseName_PopularCOurse(){
    	return PopularCourse_CourseName;
    }
    
    /**
     * Select Course 
     * @return
     */
    String NewlyAdded_CourseName = "";
    public SelectedCoursePage selectCourse_NewAdd() {

        scrollWindow(0, 500);
        _waitForJStoLoad();
        
        // UpperLimit of the course as per the locator : 10;
        // LowerLimit of the course as per the locator : 6
        Random ran = new Random();
        int num = ran.nextInt(10-6+1)+6;
       
        WebElement btn = driver.findElement(By.cssSelector("#new-not>.edureka-courses>.courses-slider>div:nth-child(1)>div>div:nth-child("+num+")>div>ul>a>li>div>div"));
        String cour = driver.findElement(By.cssSelector("#new-not>.edureka-courses>.courses-slider>div:nth-child(1)>div>div:nth-child("+num+")>div>ul>a>li>h4")).getText();
        NewlyAdded_CourseName = cour;
        JavascriptExecutor jse = (JavascriptExecutor)driver;
  	    jse.executeScript("window.scrollBy(0,250)", "");
  	 
  	    System.out.println("Logged in coursename :" + cour);
        WaitForElementClickable("#new-not>.edureka-courses>.courses-slider>div:nth-child(1)>div>div:nth-child("+num+")>div>ul>a>li>h4",getTimeOut());
  	    btn.click();
  	  
        /// VERIFY IS THE USER HAS LOGGED INTO THE COURSE PAGE
  	    String c1= "//h1[contains(text(),'"+cour+"')]";
	    WaitForElementPresent(c1, getTimeOut());
	  //  Assert.assertTrue(isElementPresent(c1));
	
        
        return PageFactory.initElements(driver, SelectedCoursePage.class);

    }
    public String getCourseName_NewlyAdded(){
    	
    	return NewlyAdded_CourseName;
    }
    
    public SelectedCoursePage selectcourse_AllCoursePage(){
    	
    	 scrollWindow(0, 500);
         _waitForJStoLoad();
         
         // UpperLimit of the course as per the locator : 10;
         // LowerLimit of the course as per the locator : 6
         Random ran = new Random();
         int num = ran.nextInt(((5 - 1) + 1) + 1);
        
         if (num%2 == 0){
        	 num = num+1;
         }
        
        WebElement btn = driver.findElement(By.cssSelector(".no-pad-rit-xs.no-pad-grid>li:nth-child("+num+")>div:nth-child(3)>div>a>p"));
        String cour = driver.findElement(By.cssSelector(".no-pad-rit-xs.no-pad-grid>li:nth-child("+num+")>div:nth-child(1)>div>a>h5")).getText();
        NewlyAdded_CourseName = cour;
     /*   JavascriptExecutor jse = (JavascriptExecutor)driver;
  	    jse.executeScript("window.scrollBy(0,250)", "");
  	 */
  	    System.out.println("Logged in coursename :" + cour);
        WaitForElementClickable(".no-pad-rit-xs.no-pad-grid>li:nth-child("+num+")>div:nth-child(3)>div>a>h5",getTimeOut());
  	    btn.click();
  	  
    	
    	return PageFactory.initElements(driver,SelectedCoursePage.class);
    }
    /**
     *  Verify Sign up pop has been closed 
     * @return
     */
    public DashboardPage verifySignUpNotPresent() {
        String popUpSignup=dashboardLocator.getLocator("HomePage.PopupSignup");
        WaitForElementNotVisible(popUpSignup, 20);
        Assert.assertFalse(isElementPresent(popUpSignup));
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     *  Click on Course Tab
     * @return
     */
    public AllCoursesPage clickOnCoursesTab() {
        String tabCourses =dashboardLocator.getLocator("HomePage.TabCourses");
        WaitForElementPresent(tabCourses, getTimeOut());
        Assert.assertTrue(isElementPresent(tabCourses));
        clickOn(tabCourses);
        return PageFactory.initElements(driver, AllCoursesPage.class);
    }

    /**
     *  Click on Query box
     * @return
     * @throws InterruptedException
     */
    public DashboardPage clickOnQueryBox() throws InterruptedException{
        scrollWindow(0, 1000);
        String queryBox= dashboardLocator.getLocator("QueryBox.HeaderQueryBox");
        Thread.sleep(5000);
        mouseClickByLocator(queryBox, "Click on QuerBox");
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Drop query
     * @param query
     * @param phNumber
     * @return
     */
    public DashboardPage sendQuery(){
        String userName= "User"+randomString(2);
        String email = "test" + randomString(3)+ "@tech.edureka.in";
        propertyReader.updatePropertyTestData("QueryEmail_Id", email);
        propertyReader.updatePropertyTestData("QueryUserName", userName);
        String query = propertyReader.readTestData("Query");

        String phNumber= propertyReader.readTestData("PhoneNumber");
        String tbQueryName = dashboardLocator.getLocator("QueryBox.TBQueryName");
        WaitForElementVisible(tbQueryName, getTimeOut());
        sendKeys(tbQueryName, userName);

        String tbQueryEmail= dashboardLocator.getLocator("QueryBox.TBQueryEmail");
        WaitForElementVisible(tbQueryEmail, getTimeOut());
        sendKeys(tbQueryEmail, email);


        String tbQueryPhNumberString= dashboardLocator.getLocator("QueryBox.TBQueryPhNumber");
        WaitForElementVisible(tbQueryPhNumberString, getTimeOut());
        sendKeys(tbQueryPhNumberString, phNumber);

        String tbInquiry = dashboardLocator.getLocator("QueryBox.TBInquiry");
        WaitForElementVisible(tbInquiry, getTimeOut());
        sendKeys(tbInquiry, query);

        String btnSubmit= dashboardLocator.getLocator("QueryBox.BTNSubmit");
        WaitForElementVisible(btnSubmit, getTimeOut());
        clickOn(btnSubmit);
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Verify Submit Inquiry Message
     * @return
     */
    public DashboardPage verifySumbitInquiry(){
        String messageSubmitInquiry= dashboardLocator.getLocator("QueryBox.MsgSubmitInquiry");
        WaitForElementPresent(messageSubmitInquiry, 5);
        Assert.assertTrue(isElementPresent(messageSubmitInquiry));
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     *  Verify Banner
     * @return
     */
    public DashboardPage verifyBanner(String bannerImg){
        getWebDriver().navigate().refresh();
        String banner= "//img[contains(@src,'img/creative/"+bannerImg+".png')]";
        WaitForElementPresent(banner, getTimeOut());
        Assert.assertTrue(isElementPresent(banner));
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     *  Verify Data for Offer Framework
     * @param discount_Description
     * @return
     * @throws Exception
     */
    public DashboardPage verifyDataForOfferFramework(String discount_Description) throws Exception {
        DashboardPage dashboard;
        dashboard = (DashboardPage)databaseVerification.dataVerification_For_OfferFramework(DashboardPage.class, discount_Description);
        return dashboard;
    }

    /**
     *  Navigate to the Offer code URL
     * @param url
     * @return
     */
    public DashboardPage naviateToOfferCde(String url) {
        getWebDriver().navigate().to(url);
        return PageFactory.initElements(driver, DashboardPage.class);

    }

    /**
     *  Currency Verification in Offer framework
     * @return
     * @throws Exception
     */
    public DashboardPage currencyVerificationInDatabase_For_OfferFramework() throws Exception {
        DashboardPage dashboard;
        dashboard = (DashboardPage)databaseVerification.currencyVerificationInDatabase_For_OfferFramework(DashboardPage.class);
        return dashboard;
    }


    /**
     *  Verify Discount of offer code
     * @param course
     * @param discountValue
     * @return
     */
    public DashboardPage verifyDiscountOfOfferCode(String course, String discountValue){
        List<WebElement> courses = driver.findElements(By.xpath(dashboardLocator.getLocator("HomePage.Courses")));
        int rows = courses.size();
        for (int i = 1; i < rows; i++) {
            String coursesList = "//div[@class='owl-stage-outer']/div/div["+i+"]/div/ul/a/li/h4";
            _waitForJStoLoad();
            String coursesName= getText(coursesList);
            if (coursesName.contains(course)) {
                String actualCoursePrice = "//div[@class='owl-stage-outer']/div/div["+i+"]/div/ul/a/li/h5/span/b";
                String actualCoursePriceText = getText(actualCoursePrice);
                String actualCoursePriceText_1 = actualCoursePriceText.replace("\u20B9", "");
                String actualCoursePriceText_2 = actualCoursePriceText_1.replace(",", "");
                String actualCoursePriceText_3= actualCoursePriceText_2.trim();
                float actualCoursePriceValue = Integer.parseInt(actualCoursePriceText_3);

                String coursePrice = "//div[@class='owl-stage-outer']/div/div["+i+"]/div/ul/a/li/h5/span/small";
                String coursePriceText= getText(coursePrice);
                String coursePriceText_1 = coursePriceText.replace("\u20B9", "");
                String coursePriceText_2 = coursePriceText_1.replace(",", "");
                String coursePriceText_3 = coursePriceText_2.trim();
                int coursePriceValue = Integer.parseInt(coursePriceText_3);
                int discountPercentage = Integer.parseInt(discountValue);
                float discountAmount = (int)(coursePriceValue*(discountPercentage/100.0f));
                float priceAfterDiscount= coursePriceValue-discountAmount;
                Assert.assertEquals(priceAfterDiscount, actualCoursePriceValue);
                break;
            }
        }
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     *  Change Url
     * @param url
     * @return
     */
    public DashboardPage changeUrl(String url){
        getWebDriver().get(url);
        String currentUrl= driver.getCurrentUrl();
        String current_Url= currentUrl.replace("www", "ecomqa	");
        String current_Url_2= current_Url.replace(".co",".in");
        getWebDriver().get(current_Url_2);
        _waitForJStoLoad();
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     *  Verify Banner for Addtional Disocunt 
     * @return
     */
    public DashboardPage verifyAdditonalDiscountBanner(){
        String messageSubmitInquiry= dashboardLocator.getLocator("Banners.AddtionalDiscountBanner");
        _waitForJStoLoad();
        Assert.assertTrue(isElementPresent(messageSubmitInquiry));
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     *  Verify course price after discount value and referral value
     * @param course
     * @param discountValue
     * @param referral_Disocunt
     * @return
     */
    public DashboardPage verifyCoursePriceAfterReferralAndOfferDiscount(String course, String discountValue, String referral_Disocunt){
        List<WebElement> courses = driver.findElements(By.xpath(dashboardLocator.getLocator("HomePage.Courses")));
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

                String coursePrice = "//div[@class='owl-stage-outer']/div/div["+i+"]/div/ul/a/li/h5/span/small";
                String coursePriceText= getText(coursePrice);
                String coursePriceText_1 = coursePriceText.replace("\u20B9", "");
                String coursePriceText_2 = coursePriceText_1.replace(",", "");
                String coursePriceText_3 = coursePriceText_2.trim();
                int referralDisocunt = Integer.parseInt(referral_Disocunt);
                int coursePriceValue = Integer.parseInt(coursePriceText_3);
                int discountPercentage = Integer.parseInt(discountValue);
                double discountAmount =round((coursePriceValue*(discountPercentage/100.0f)),2);
                double discountAmount_1= coursePriceValue-discountAmount;
                double discountAmount_2=round((discountAmount_1*(referralDisocunt/100.0f)),2);
                int discountAmount_3 = (int)(discountAmount_1-discountAmount_2);
                Assert.assertEquals(discountAmount_3, actualCoursePriceValue);
                break;
            }

        }
        return PageFactory.initElements(driver, DashboardPage.class);
    }
    
}