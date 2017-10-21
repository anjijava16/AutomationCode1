package com.edureka.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.DriverHelper;

public class Mailnator extends DriverHelper{

    private LocatorReader mailnator;

    public Mailnator(WebDriver driver) {
        super(driver);
        mailnator = new LocatorReader("Mailnator.xml");
    }

    /**
     * Veify mailnator
     * @return
     */
    public Mailnator verifyMailnator() {
        String pageHeader =mailnator.getLocator("PageHeader");
        _waitForJStoLoad();
        Assert.assertTrue(isElementPresent(pageHeader));
        return PageFactory.initElements(driver, Mailnator.class);
    }

    /**
     *  Enter email for search
     * @return
     */
    public Mailnator enterEmail(String email) {
        String tbEmail =mailnator.getLocator("Mail.TBMail");
        _waitForJStoLoad();
        sendKeys(tbEmail, email);

        String btnSearch =mailnator.getLocator("Mail.BTNSearch");
        _waitForJStoLoad();
        clickOn(btnSearch);
        return PageFactory.initElements(driver, Mailnator.class);
    }

    /**
     *  Open mail
     * @return
     */
    public Mailnator openMail() {
        String mail =mailnator.getLocator("Mail.MailLink");
        _waitForJStoLoad();
        clickOn(mail);
        return PageFactory.initElements(driver, Mailnator.class);
    }

    /**
     *  Verify Mail subject
     * @param subject
     * @return
     */
    public Mailnator verifyMailSubject(String subject){
        String mailSubject ="//td[contains(text(),'Subject:')]//following-sibling::td[contains(text(),'"+subject+"')]";
        _waitForJStoLoad();
        if(!(isElementPresent(mailSubject))){
            this.refreshBrowser();
        }
        Assert.assertTrue(isElementPresent(mailSubject));
        return PageFactory.initElements(driver, Mailnator.class);
    }

    /**
     *  Delete mail
     * @return
     */
    public Mailnator deleteMail() {
        String btnDelete =mailnator.getLocator("Mail.BTNDelete");
        _waitForJStoLoad();
        clickOn(btnDelete);
        return PageFactory.initElements(driver, Mailnator.class);
    }

    /**
     *  Get Verification Code
     * @return
     */
    public String getVerificationCode(){
        getWebDriver().switchTo().frame("publicshowmaildivcontent");
        String code= mailnator.getLocator("Mail.VerificationCode");
        String verificationCode= getText(code);
        return verificationCode;
    }

    /**
     *  Navigate To Edureka
     * @return
     */
    public SignInModalPage navigateToEdureka() {
        switchToTab();
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Verify multiple mails in inbox
     * @return
     */
    public Mailnator verifyMultipleMailsInInbox() {
        String inboxMails =mailnator.getLocator("Mail.InboxMails");
        List<WebElement> mailList= driver.findElements(By.xpath(inboxMails));
        int mailCount= mailList.size();
        Assert.assertTrue(mailCount>1);
        return PageFactory.initElements(driver, Mailnator.class);
    }

    /**
     *  Navigate to the Forget Password Link
     * @return
     */
    public SignInModalPage navigateToForgetPasswordLink() {
        getWebDriver().switchTo().frame("publicshowmaildivcontent");
        String resetPasswordLink= mailnator.getLocator("Mail.ResetPasswordLink");
        _waitForJStoLoad();
        String resetPasswordLinkText= getText(resetPasswordLink);
        resetPasswordLinkText="http://"+resetPasswordLinkText;
        getWebDriver().navigate().to(resetPasswordLinkText);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }


    /**
     *  Open Referral Mail
     * @return
     */
    public Mailnator openReferralMail() {
        String mail =mailnator.getLocator("Mail.ReferralMail");
        WaitForElementPresent(mail, getTimeOut());
        clickOn(mail);
        return PageFactory.initElements(driver, Mailnator.class);
    }

    /**
     *  Refresh the browser
     * @return
     */
    public Mailnator refreshBrowser() {
        getWebDriver().navigate().refresh();
        return PageFactory.initElements(driver, Mailnator.class);
    }
    /**
     *  Open Referral Mail
     * @return
     * @throws InterruptedException 
     */
    public Mailnator verifyInvoiceEmailData() throws InterruptedException {
        getWebDriver().switchTo().frame(getWebDriver().findElement(By.id("publicshowmaildivcontent")));
        String retailInvoice =mailnator.getLocator("RetailInvoice");
       _waitForJStoLoad();
        Assert.assertTrue(isElementPresent(retailInvoice));
        String orderId =mailnator.getLocator("OrderId");
        Assert.assertTrue(isElementPresent(orderId));
        String cin =mailnator.getLocator("CIN");
        Assert.assertTrue(isElementPresent(cin));
        String name =mailnator.getLocator("Name");
        Assert.assertTrue(isElementPresent(name));
        String email =mailnator.getLocator("EmailId");
        Assert.assertTrue(isElementPresent(email));

        return PageFactory.initElements(driver, Mailnator.class);
    }
    /**
     *  Open Invoice Mail
     * @return
     * @throws InterruptedException 
     */
    public Mailnator openInvoiceMail() throws InterruptedException {
        String mail =mailnator.getLocator("Mail.InvoiceMail");
        _waitForJStoLoad();
        clickOn(mail);
        waitForElement();
        return PageFactory.initElements(driver, Mailnator.class);
    }
}
