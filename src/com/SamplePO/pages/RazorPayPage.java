package com.edureka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.DriverHelper;

public class RazorPayPage extends DriverHelper{

    private LocatorReader razorPay;
    public RazorPayPage(WebDriver driver) {
        super(driver);
        razorPay = new LocatorReader("RazorPay.xml");
    }

    /**
     *  Verify Razor Page
     * @return
     * @throws InterruptedException 
     */
    public RazorPayPage verifyPage(String Email) throws InterruptedException{
        Thread.sleep(5000);
//        String loginEmail = propertyReader.readRunTimeData("Email_Id");
        String iframe = razorPay.getLocator("Razor.Frame");
        WaitForElementClickable(iframe, getTimeOut());
        WebElement frame = getWebDriver().findElement(By.xpath(iframe));
        getWebDriver().switchTo().defaultContent();
        getWebDriver().switchTo().frame(frame);
        String email= razorPay.getLocator("Razor.TBMail");
        WaitForElementPresent(email, getTimeOut());
        String emailId= getAttribute(email, "value");
        Assert.assertTrue(emailId.equalsIgnoreCase(Email));
        return PageFactory.initElements(driver, RazorPayPage.class);

    }
    
    /**
     *  Click on Net Banking Tab
     * @return
     */
    public RazorPayPage clickOnNetBankingTab(){
        String tabNetBanking= razorPay.getLocator("Razor.TabNetBanking");
        WaitForElementPresent(tabNetBanking, getTimeOut());
        clickOn(tabNetBanking);
        return PageFactory.initElements(driver, RazorPayPage.class);

    }
    
    /**
     *  Select Bank
     * @param bankName
     * @return
     */
    public RazorPayPage selectBank(String bankName){
        String bank = "//div[@id='netb-banks']/div/label/div/div[contains(text(),'"+bankName+"')]";
        WaitForElementPresent(bank, getTimeOut());
        clickOn(bank);
        return PageFactory.initElements(driver, RazorPayPage.class);

    }
    
    /**
     *  Click on Pay button
     * @return
     */
    public RazorPayPage clickOnPayButton(){
        String btnPay= razorPay.getLocator("Razor.ButtonPay");
        WaitForElementPresent(btnPay, getTimeOut());
        clickOn(btnPay);
        return PageFactory.initElements(driver, RazorPayPage.class);

    }
    
    /**
     *  Click on success button
     * @return
     */
    public MyProfilePage clickOnSuccessButton(){
        String headerRazorBank= razorPay.getLocator("Razor.HeadingRazor");
        WaitForElementPresent(headerRazorBank, getTimeOut());
        String razorBankWelcomeMessage = getText(headerRazorBank);
        Assert.assertTrue(razorBankWelcomeMessage.contains("Welcome to Razorpay Bank"));
        
        String btnSuccess= razorPay.getLocator("Razor.BTNSuccess");
        WaitForElementPresent(btnSuccess, getTimeOut());
        clickOn(btnSuccess);
        return PageFactory.initElements(driver, MyProfilePage.class);

    }
    
    /**
     *  Verify Razor Page
     * @return
     */
    public RazorPayPage verifyRazorForReferral(){
        String loginEmail = propertyReader.readRunTimeData("RefferralEmail");
        String iframe = razorPay.getLocator("Razor.Frame");
        WebElement frame = getWebDriver().findElement(By.xpath(iframe));
        driver.switchTo().frame(frame);
        String email= razorPay.getLocator("Razor.TBMail");
        WaitForElementPresent(email, getTimeOut());
        String emailId= getAttribute(email, "value");
        Assert.assertTrue(emailId.contains(loginEmail));
        return PageFactory.initElements(driver, RazorPayPage.class);

    }
    /**
     *  Verify Need Assistance
     * @param timeZone
     * @return
     * @throws InterruptedException
     */
  @FindBy(id = "orderBillName")WebElement username1 ;
  @FindBy(id = "mobileno")WebElement courseName_courseStartDate_timezone ;
  @FindBy(id = "orderBillTel")WebElement mob ;
  @FindBy(id = "orderBillEmail")WebElement email1 ;
  @FindBy(css = ".span12.innerpanel-text.order-no")WebElement OrderID ;
  @FindBy(css = "imageHeader")WebElement edurekaLogo ;
  @FindBy(id = "finalTotal")WebElement courseAmount ;
  
   public RazorPayPage verifyCCAvenuePage(String usernname1, String email,String mobile,String coursename,String courseDate,String courseAmount,String orderID ) throws InterruptedException {
    	waitForElement();
    	waitForLoading();
    	String currentTitle=getWebDriver().getTitle();
    	WaitForElementPresent(currentTitle, getTimeOut());
    	System.out.println("Current URl :"+currentTitle);
    	Assert.assertTrue(getWebDriver().getTitle().contains("CCAvenue: Billing Shipping"));
    // Verify the following Info from CC	
    	
    	String name = username1.getAttribute("value");
    	Assert.assertTrue(name.equalsIgnoreCase(usernname1));

    	String courserDetails = courseName_courseStartDate_timezone.getText();
      /*  Assert.assertTrue(courserDetails.contains(coursename));
        Assert.assertTrue(courserDetails.contains(courseDate));
      */  
        String phone = mob.getAttribute("value");
        Assert.assertTrue(phone.equals(mobile));
        
    	String ORDERID = OrderID.getText();
    	//Assert.assertTrue(orderID.equals(ORDERID));
        
    	String Amount = this.courseAmount.getText();
    	//Assert.assertTrue(Amount.equals(courseAmount));
        
    	String mail = email1.getAttribute("value");
    	//Assert.assertTrue(mail.equals(email));
        
        return PageFactory.initElements(driver, RazorPayPage.class);
    }
    /**
     * Navigate to URL
     * @return
     * @throws InterruptedException 
     */
    public UserHomePage navigateToUrl(String url) throws InterruptedException {
    	getWebDriver().navigate().to(url);
    	waitForElement();
        return PageFactory.initElements(driver, UserHomePage.class);
    }
}