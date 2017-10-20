package com.edureka.pages;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.DriverHelper;


public class AdminDashboard extends DriverHelper{

    private LocatorReader adminDashboardLocator;

    public AdminDashboard(WebDriver driver) {
        super(driver);
        adminDashboardLocator = new LocatorReader("AdminDashboard.xml");
    }
    
     /**
     *  Verify Admin Dashboard Page
     * @return
     */
    public AdminDashboard verifyAdminDashboard() {
        String pageheader=adminDashboardLocator.getLocator("PageHeader");
        WaitForElementPresent(pageheader, getTimeOut());
        Assert.assertTrue(isElementPresent(pageheader));
        return PageFactory.initElements(driver, AdminDashboard.class);
    }
    
  @FindBy (css =".custom-nav.main-nav-admin>ul>li:nth-child(1)") WebElement Dashboard_Button ;
  @FindBy (css =".custom-nav.main-nav-admin>ul>li:nth-child(1)>div>ul>li:nth-child(5)") WebElement ChangeSiteSetting_Button ;
  @FindBy (css ="#ui-id-1>span") WebElement Site_Button ;
  @FindBy (css ="#SettingEditForm>div:nth-child(17)>input:nth-child(2)") WebElement CC_CheckBox ;
  @FindBy (css =".submit>input") WebElement Update_Button ;
   
    public AdminDashboard enablepaymentforINR(String paymentMode) throws InterruptedException{
        waitForLoading(); 
    	waitForWebElementPresent(Dashboard_Button);
    	WaitForElementClickable("css= .custom-nav.main-nav-admin>ul>li:nth-child(1)",getTimeOut());
        Actions ac = new Actions(driver);
        ac.moveToElement(Dashboard_Button);
        ac.click(ChangeSiteSetting_Button).build().perform();
        
        //Click on the Site Button
        waitForLoading();
        _waitForJStoLoad();
        WaitForElementVisible("css= #ui-id-1>span", getTimeOut());
        WaitForElementClickable("css= #ui-id-1>span", getTimeOut());
        Site_Button.click();
        
        //Drop-Down for checking the payment module.
        waitForLoading();
        //_waitForJStoLoad();
        WaitForElementVisible("css= #ui-id-1>span", getTimeOut());
        WaitForElementVisible("css= #Setting308Name",getTimeOut());
        WaitForElementClickable("css= #Setting308Name",getTimeOut());
        Thread.sleep(5000);
        Select s = new Select(driver.findElement(By.cssSelector("#Setting308Name")));
        
        if (paymentMode.equalsIgnoreCase("cc"))
        {
        waitForWebElementPresent(CC_CheckBox);
        WaitForElementClickable("css= #SettingEditForm>div:nth-child(17)>input:nth-child(2)", getTimeOut()); 	
        CC_CheckBox.click(); 	
        s.selectByValue("CC");
        }
        else if (paymentMode.equalsIgnoreCase("rp")){
        s.selectByValue("RP");
        }
        WaitForElementClickable("css=.submit>input", getTimeOut());
        Update_Button.click();
        Thread.sleep(3000);
        return PageFactory.initElements(driver, AdminDashboard.class);
    }
    
  @FindBy (css = ".logout")WebElement LogOut_Button;  
    public <T> T logout (@SuppressWarnings("rawtypes") Class className){
    	
    	LogOut_Button.click();
    	return (T) PageFactory.initElements(driver, className);
    }

    /**
     *  Select Menu tab
     * @param tabName
     * @return
     */
    public AdminDashboard selectMenuTab(String tabName) {
        String tab="//a[contains(text(),'"+tabName+"')]";
        WaitForElementPresent(tab, getTimeOut());
        mouseOver(tab);
        clickOn(tab);
        return PageFactory.initElements(driver, AdminDashboard.class);
    }
    
    /**
     *  Select option from Admin Offer Dropdown
     * @param subMenu
     * @return
     */
    public AdminAddOfferPage selectOptionFromAdminOfferTab(String subMenu) {
        String dropdownOption="//a[contains(text(),'"+subMenu+"')]";
        WaitForElementPresent(dropdownOption, getTimeOut());
        mouseOver(dropdownOption);
        clickOn(dropdownOption);
        return PageFactory.initElements(driver, AdminAddOfferPage.class);
    }
    /**
     *  Select option from Admin Offer Dropdown
     * @param subMenu
     * @return
     */
    public AdminDashboard selectSubMenu(String subMenu) {
        String dropdownOption="//a[contains(text(),'"+subMenu+"')]";
        WaitForElementPresent(dropdownOption, getTimeOut());
        mouseOver(dropdownOption);
        clickOn(dropdownOption);
        return PageFactory.initElements(driver, AdminDashboard.class);
    }
    /**
     *  Select option from Admin Offer Dropdown
     * @param subMenu
     * @return
     */
    public AdminAddOfferPage changePaymentGateway(String paymentGateway) {
        String Gateway=adminDashboardLocator.getLocator("Gateway");
        WaitForElementPresent(paymentGateway, getTimeOut());
        selectDropDown(Gateway, paymentGateway);
        String gatewayButton=adminDashboardLocator.getLocator("ChangePaymentGatewayButton");
        clickOn(gatewayButton);
        return PageFactory.initElements(driver, AdminAddOfferPage.class);
    }
    
    /**
     *  Select submenu from Dashboard
     * @param tabName
     * @return
     */
    public AdminDashboard selectDashoardMenu(String SubmenuName) {
        String tab="//a[contains(text(),'"+SubmenuName+"')]";
        WaitForElementPresent(tab, getTimeOut());
        mouseOver(tab);
        clickOn(tab);
        return PageFactory.initElements(driver, AdminDashboard.class);
    }
    
    /**
     *  Change Offer Coupan Settings
     * @param value
     * @return
     */
    public AdminDashboard changeOfferCouponSettings(String value) {
        String meta="//a[text()='Meta']";
        WaitForElementPresent(meta, getTimeOut());
        clickOn(meta);
        
        String tbOrderSummary="//label[text()='order page']/following::input[1]";
        WaitForElementPresent(tbOrderSummary, getTimeOut());
        sendKeys(tbOrderSummary, value);
        
        String btnUpdate="//input[@value='Update']";
        WaitForElementPresent(btnUpdate, getTimeOut());
        clickOn(btnUpdate);
        
        return PageFactory.initElements(driver, AdminDashboard.class);
    }

    /**
     *  Select option from Admin Offer Dropdown
     * @param subMenu
     * @return
     */
    public AdminAddOfferPage selectOptionFromAdminOfferTab1(String subMenu) {
        String dropdownOption="//a[contains(text(),'"+subMenu+"')]";
        WaitForElementPresent(dropdownOption, getTimeOut());
        mouseOver(dropdownOption);
        clickOn(dropdownOption);
        return PageFactory.initElements(driver, AdminAddOfferPage.class);
    }

    
	public <T> T navigateToOfferURL(@SuppressWarnings("rawtypes") Class className,String URL) throws InterruptedException {
		pageLoading();
		_waitForJStoLoad();
		driver.navigate().to(URL);
        return (T) PageFactory.initElements(driver,className);
    }

@FindBy(id = "OfferStatus") WebElement status;
@FindBy(id = "OfferStartDate") WebElement startTime;
@FindBy(id = "OfferEndDate") WebElement endTime;
@FindBy(id = "OfferPriority") WebElement OfferPriority;
@FindBy(css = "#OfferAdminOfferEditForm>table>tbody>tr:nth-child(6)>td:nth-child(4)>.upper") WebElement MaxPriority_Offer;
@FindBy(css = ".submit>input") WebElement submit;
@FindBy(css = "#OfferIsFreeAllowed") WebElement B1S1_Checkbox;


public AdminDashboard enableB1S1Offer(){
	_waitForJStoLoad();
	waitForWebElementPresent(status);
    B1S1_Checkbox.click();
    return PageFactory.initElements(driver,AdminDashboard.class);
}

	public AdminDashboard activateTheOffer(String offerStatus) {
		_waitForJStoLoad();
		waitForWebElementPresent(status);
		Select l = new Select(status);
		if (offerStatus.equals("Active"))
	{
	    l.selectByVisibleText("Active");	
	    
		Calendar date = Calendar.getInstance();
	    date.setTime(new Date());
	    Format f = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	    System.out.println(f.format(date.getTime()));
	    String StartDate = f.format(date.getTime());
	    startTime.clear();
	    startTime.sendKeys(StartDate);
   	    date.add(Calendar.YEAR,10);
	    String endDate = f.format(date.getTime());
   	    endTime.clear();
	    endTime.sendKeys(endDate);
   	    
   	   String priorityvalue = MaxPriority_Offer.getText(); 
   	   int a = Integer.parseInt(priorityvalue);
   	   String value = String.valueOf(a+1);
   	   System.out.println(value);
   	   OfferPriority.clear();
   	   OfferPriority.sendKeys(value);
   	   
   	   
	}
		else if (offerStatus.equals("Inactive")){
		
			l.selectByVisibleText("Inactive");	
		    
		}
		submit.click();
        return PageFactory.initElements(driver, AdminDashboard.class);
 	
	}
    
}
