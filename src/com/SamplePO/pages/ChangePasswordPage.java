package com.edureka.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.DriverHelper;

public class ChangePasswordPage extends DriverHelper{

    private LocatorReader changePasswordLocator;
    public ChangePasswordPage(WebDriver driver) {
        super(driver);
        changePasswordLocator = new LocatorReader("ChangePassword.xml");
    }

    /**
     *  Verify Change Password Page
     * @return
     */
    public ChangePasswordPage verifyChangPasswordPage() {
        String pageName =changePasswordLocator.getLocator("PageHeader");
        WaitForElementPresent(pageName, getTimeOut());
        Assert.assertTrue(isElementPresent(pageName));
        return PageFactory.initElements(driver, ChangePasswordPage.class);
    }

    /**
     *  Change Password
     * @param existingPassword
     * @return
     */
    public ChangePasswordPage changePassword(String existingPassword, String newPassword) {
        String tbExisitngPassword =changePasswordLocator.getLocator("ChangePassword.TBExistingPassword");
        WaitForElementPresent(tbExisitngPassword, getTimeOut());
        sendKeys(tbExisitngPassword, existingPassword);

        String tbNewPassword =changePasswordLocator.getLocator("ChangePassword.TBNewPassword");
        WaitForElementPresent(tbNewPassword, getTimeOut());
        sendKeys(tbNewPassword, newPassword);

        String tbconfirmNewPassword =changePasswordLocator.getLocator("ChangePassword.TBConfirmNewPassword");
        WaitForElementPresent(tbconfirmNewPassword, getTimeOut());
        sendKeys(tbconfirmNewPassword, newPassword);

        String btnSubmit =changePasswordLocator.getLocator("ChangePassword.BTNSubmit");
        WaitForElementPresent(btnSubmit, getTimeOut());
        clickOn(btnSubmit);
        return PageFactory.initElements(driver, ChangePasswordPage.class);
    }

   /**
    * Verify Password has been changed succesfully
    * @param msg: Success Message
    * @return
    */
    public ChangePasswordPage verifyPasswordChanged(String msg) {
        String successMessage ="//div[contains(text(),'"+msg+"')]";
        WaitForElementPresent(successMessage, getTimeOut());
        Assert.assertTrue(isElementPresent(successMessage));
        return PageFactory.initElements(driver, ChangePasswordPage.class);
    }
    
    /**
     *  Verify Error for Wrong existing Password
     * @param msg
     * @return
     */
    public ChangePasswordPage verifyErrorForChangePassword(String error) {
        String successMessage ="//div[contains(text(),'"+error+"')]";
        WaitForElementPresent(successMessage, getTimeOut());
        Assert.assertTrue(isElementPresent(successMessage));
        return PageFactory.initElements(driver, ChangePasswordPage.class);
    }
    
    /**
     *  Click on Profile dropdown
     * @param userName
     * @return
     */
    public ChangePasswordPage clickOnProfileDropdown(String userName){
        String dropdown ="//span[contains(text(),'"+userName+"')]";
        WaitForElementPresent(dropdown, getTimeOut());
        clickOn(dropdown);
        return PageFactory.initElements(driver, ChangePasswordPage.class);
    }
    
    /**  Logout application
    * @return
    */
   public DashboardPage logout(){
       String logout=changePasswordLocator.getLocator("ProfileDropdown.LTLogout");;
       WaitForElementPresent(logout, getTimeOut());
       mouseOver(logout);
       clickOn(logout);
       return PageFactory.initElements(driver, DashboardPage.class);
   }

   /**
    *  Enter correct existing password and different New and confrim password
    * @param existingPassword
    * @param newPassword
    * @param confirmPassword
    * @return
    */
   public ChangePasswordPage enterDifferentNewAndConfirmPassword(String existingPassword, String newPassword, String confirmPassword) {
       String tbExisitngPassword =changePasswordLocator.getLocator("ChangePassword.TBExistingPassword");
       WaitForElementPresent(tbExisitngPassword, getTimeOut());
       sendKeys(tbExisitngPassword, existingPassword);

       String tbNewPassword =changePasswordLocator.getLocator("ChangePassword.TBNewPassword");
       WaitForElementPresent(tbNewPassword, getTimeOut());
       sendKeys(tbNewPassword, newPassword);

       String tbconfirmNewPassword =changePasswordLocator.getLocator("ChangePassword.TBConfirmNewPassword");
       WaitForElementPresent(tbconfirmNewPassword, getTimeOut());
       sendKeys(tbconfirmNewPassword, confirmPassword);

       String btnSubmit =changePasswordLocator.getLocator("ChangePassword.BTNSubmit");
       WaitForElementPresent(btnSubmit, getTimeOut());
       clickOn(btnSubmit);
       return PageFactory.initElements(driver, ChangePasswordPage.class);
   }

}
