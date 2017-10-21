package com.edureka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.Database;
import com.edureka.util.DriverHelper;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

public class SignInModalPage extends DriverHelper{

    private LocatorReader signInLocator;

    public SignInModalPage(WebDriver driver) {
        super(driver);
        signInLocator = new LocatorReader("SignIn.xml");
    }

    /**
     *  Verify SignIn Page
     * @return
     * @throws InterruptedException 
     */
    public SignInModalPage verifySignInModal() throws InterruptedException {
        String pageName= signInLocator.getLocator("PageHeader");
        waitForElement();
        Assert.assertTrue(isElementPresent(pageName));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     * User Signup
     * @return
     */
    
    String name = "";
    String passwrd = "";
    String email = "";
    String phNumber="";
    @SuppressWarnings("unchecked")
    public <T> T signUp(@SuppressWarnings("rawtypes") Class className, String domainName){
        phNumber = propertyReader.readTestData("PhoneNumber");
        passwrd = propertyReader.readTestData("Password");
        name= "User"+randomString(2);
        email = "test" + randomString(6)+ domainName;
        System.out.println(email);
        propertyReader.updatePropertyTestData("Email_Id", email);
        propertyReader.updatePropertyTestData("UserName", name);

        String tbUserName = signInLocator.getLocator("SignIn.TBUserName");
        WaitForElementVisible(tbUserName, 20);
        sendKeys(tbUserName, name);

        String tbUserEmail = signInLocator.getLocator("SignIn.TBEmail");
        WaitForElementPresent(tbUserEmail, getTimeOut());
        sendKeys(tbUserEmail, email);

        String tbUserMob =signInLocator.getLocator("SignIn.TBPhNumber");
        WaitForElementPresent(tbUserMob, getTimeOut());
        sendKeys(tbUserMob, phNumber);

        String tbUserPwrd =signInLocator.getLocator("SignIn.TBUserPassword");
        WaitForElementPresent(tbUserPwrd, getTimeOut());
        sendKeys(tbUserPwrd, passwrd);

        String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);

        getPageLoadTimeOut();
        
        
        return (T) PageFactory.initElements(driver, className);
    }
    
    public String RuntimeUserPhNum(){
    	
    	return phNumber;
    }
    public String RuntimeUserName(){
    	
    	return name;
    }
    public String RuntimeUserPassword(){
    	
    	return passwrd;
    }
    public String RuntimeUserEmail(){
    	return email;
    }
    public String getUserID(String Email) throws Exception {
		Database d = new Database();
		String id = d.getRecord("users","id","email",Email);
		System.out.println("User_id of "+Email+" : "+id);
		return id;
	}
  /**
     *  Login Application
     * @return
     */
    public UserHomePage loginApp() {
        String email = propertyReader.readRunTimeData("Default_Login_UserName");
        String password = propertyReader.readRunTimeData("Default_Login_Password");

        String tbLoginEmail = signInLocator.getLocator("Login.TBLoginEmail");
        WaitForElementPresent(tbLoginEmail, getTimeOut());
        sendKeys(tbLoginEmail, email);

        String tbPassword = signInLocator.getLocator("Login.TBLoginPassword");
        WaitForElementPresent(tbPassword, getTimeOut());
        sendKeys(tbPassword, password);

        String btnStartLearning =signInLocator.getLocator("Login.BTNStartLearning");
        WaitForElementPresent(btnStartLearning, getTimeOut());
        clickOn(btnStartLearning);
        return PageFactory.initElements(driver, UserHomePage.class);
    }
    public UserHomePage RandUserLogin(String username,String passwrd){
   
    	String tbLoginEmail = signInLocator.getLocator("Login.TBLoginEmail");
        WaitForElementPresent(tbLoginEmail, getTimeOut());
        sendKeys(tbLoginEmail, username);

        String tbPassword = signInLocator.getLocator("Login.TBLoginPassword");
        WaitForElementPresent(tbPassword, getTimeOut());
        sendKeys(tbPassword, passwrd);

        String btnStartLearning =signInLocator.getLocator("Login.BTNStartLearning");
        WaitForElementPresent(btnStartLearning, getTimeOut());
        clickOn(btnStartLearning);
        
        return PageFactory.initElements(driver, UserHomePage.class);
   
    }
    
    

    /**
     *  Verify Login is selected in default 
     * @return
     */
    public SignInModalPage verifyLoginIsDefault() {
        String tbLoginEmail = signInLocator.getLocator("Login.TBLoginEmail");
        WaitForElementPresent(tbLoginEmail, getTimeOut());
        Assert.assertTrue(isElementPresent(tbLoginEmail));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Enter email
     * @return
     */
    public SignInModalPage enterEmail() {
        String email = propertyReader.readRunTimeData("Email_Id");
        String tbLoginEmail = signInLocator.getLocator("Login.TBLoginEmail");
        WaitForElementPresent(tbLoginEmail, getTimeOut());
        sendKeys(tbLoginEmail, email);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }
    
    public SignInModalPage enterMailnatorAccount(String useremail){
    	String tbLoginEmail = signInLocator.getLocator("Login.TBLoginEmail");
        WaitForElementPresent(tbLoginEmail, getTimeOut());
        sendKeys(tbLoginEmail, useremail);
        return PageFactory.initElements(driver,SignInModalPage.class);
    }

    /**
     *  Click on Signup link
     */
    public SignInModalPage clickSignUp() {
        String lTsignUp = signInLocator.getLocator("SignIn.LTSignUp");
        WaitForElementVisible(lTsignUp, getTimeOut());
        WaitForElementPresent(lTsignUp, getTimeOut());
        clickOn(lTsignUp);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Click on Login link
     */
    public SignInModalPage clickOnLoginTab() {
        String lTsignUp = signInLocator.getLocator("SignIn.LTSignIn");
        WaitForElementPresent(lTsignUp,getTimeOut());
        clickOn(lTsignUp);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }


    /**
     * Verify Sign Up is selected as default
     * @return
     */
    public SignInModalPage verifySignUpIsDefault() {
        String signUp = signInLocator.getLocator("SignIn.TBUserName");
        WaitForElementPresent(signUp, getTimeOut());
        Assert.assertTrue(isElementPresent(signUp));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     * Enter User name, Email and phone number
     * @param userName
     * @param email
     * @param phNumber
     * @return
     */
    public SignInModalPage enterUserNameEmailAndPhoneNumnber(String userName, String email, String phNumber){

        String tbUserName = signInLocator.getLocator("SignIn.TBUserName");
        WaitForElementPresent(tbUserName, getTimeOut());
        WaitForElementVisible(tbUserName, getTimeOut());
        sendKeys(tbUserName, userName);
           
        String tbUserEmail = signInLocator.getLocator("SignIn.TBEmail");
        WaitForElementPresent(tbUserEmail, getTimeOut());
        WaitForElementVisible(tbUserName, getTimeOut());
        sendKeys(tbUserEmail, email);

        String tbUserMob =signInLocator.getLocator("SignIn.TBPhNumber");
        WaitForElementPresent(tbUserMob, getTimeOut());
        WaitForElementVisible(tbUserName, getTimeOut());
        sendKeys(tbUserMob, phNumber);
        return PageFactory.initElements(driver, SignInModalPage.class);

    }

  public SignInModalPage signUpEmptyFieldsValidations(String userName, String email, String phNumber, String passwrd){
    	 
        String tbUserName = signInLocator.getLocator("SignIn.TBUserName");
        WaitForElementPresent(tbUserName, getTimeOut());
        WaitForElementVisible(tbUserName, getTimeOut());
        sendKeys(tbUserName, userName);
           
        String tbUserEmail = signInLocator.getLocator("SignIn.TBEmail");
        WaitForElementPresent(tbUserEmail, getTimeOut());
        WaitForElementVisible(tbUserName, getTimeOut());
        sendKeys(tbUserEmail, email);

        String tbUserMob =signInLocator.getLocator("SignIn.TBPhNumber");
        WaitForElementPresent(tbUserMob, getTimeOut());
        WaitForElementVisible(tbUserName, getTimeOut());
        sendKeys(tbUserMob, phNumber);
        
        String tbUserPwrd =signInLocator.getLocator("SignIn.TBUserPassword");
        WaitForElementPresent(tbUserPwrd, getTimeOut());
        sendKeys(tbUserPwrd, passwrd);
        return PageFactory.initElements(driver, SignInModalPage.class);

    }
  
    /**
     *  Verify Error
     * @return
     */
    public SignInModalPage verifyError(String errorMessage){
        String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);

        String msgError = "//span[contains(text(),'"+errorMessage+"')]";
        WaitForElementPresent(msgError, 20);
        Assert.assertTrue(isElementPresent(msgError));
        return PageFactory.initElements(driver, SignInModalPage.class);

    }
 //###################################################################################################   
    // ERROR MESSAGES ASSERTION
    
    public SignInModalPage verify_MobileError_EnterAValidNumber(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        String errormessage = driver.findElement(By.cssSelector("#signupForm>div:nth-child(6)>small:nth-child(5)")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Enter a valid mobile number",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    
    public SignInModalPage verify_MobileError_EnterMobileNumber(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        String errormessage = driver.findElement(By.cssSelector("#signupForm>div:nth-child(6)>small:nth-child(4)")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Enter mobile number",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    
    public SignInModalPage verify_MobileError_PleaseEnterAValidNumber(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        String errormessage = driver.findElement(By.cssSelector("#signupForm>div:nth-child(6)>small:nth-child(3)")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Please Enter a valid value",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    public SignInModalPage verify_NameError_PleaseEnterAValidValue(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        
        String errormessage = driver.findElement(By.xpath("//*[@id='signupForm']/div[1]/small[1]")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Please Enter a valid value",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    public SignInModalPage verify_NameError_EnterYourName(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        String errormessage = driver.findElement(By.xpath("//*[@id='signupForm']/div[1]/small[2]")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Enter your name",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    public SignInModalPage verify_NameError_EnterAValidName(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        String errormessage = driver.findElement(By.xpath("//*[@id='signupForm']/div[1]/small[3]")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Enter a valid name",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    public SignInModalPage verify_EMailError_PleaseEnterAValidValue(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        String errormessage = driver.findElement(By.xpath("//*[@id='signupForm']/div[2]/small[1]")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Please enter a valid number",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    public SignInModalPage verify_EMailError_EnterAEmail(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        String errormessage = driver.findElement(By.xpath("//*[@id='signupForm']/div[2]/small[2]")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Enter a email",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    public SignInModalPage verify_EMailError_EnterAValidEmail(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        
        WaitForElementPresent("//*[@id='signupForm']/div[2]/small[3]",getTimeOut());
        String errormessage = driver.findElement(By.xpath("//*[@id='signupForm']/div[2]/small[3]")).getText();
        WaitForElementVisible("//*[@id='signupForm']/div[2]/small[3]",getTimeOut());
        System.out.println(errormessage);
        Assert.assertEquals("Enter a valid email",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    public SignInModalPage verify_PasswordError_PleaseEnterAValidValue(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        String errormessage = driver.findElement(By.cssSelector("#signupForm>div:nth-child(7)>small:nth-child(4)")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Please enter a valid value",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }    
    public SignInModalPage verify_PasswordError_EnterAValidPassword(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        String errormessage = driver.findElement(By.cssSelector("#signupForm>div:nth-child(7)>small:nth-child(5)")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Enter a valid password",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    public SignInModalPage verify_PasswordError_PasswordMinimum8Character(){
    	String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        
        String errormessage = driver.findElement(By.cssSelector("#signupForm>div:nth-child(7)>small:nth-child(6)")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Password should be minimum of 8 characters",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    
    /**
     *  Close Sign in module
     * @throws InterruptedException 
     */
    public DashboardPage closeSignInModule() throws InterruptedException{
        String imgClose = signInLocator.getLocator("SignIn.CloseIcon");
        WaitForElementPresent(imgClose, getTimeOut());
        WaitForElementVisible(imgClose,getTimeOut());
        Thread.sleep(2000);
        clickOn(imgClose);
        driver.navigate().refresh();
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Enter password
     * @param password
     */
    public SignInModalPage enterPassword(String password) {
        String tbUserPwrd =signInLocator.getLocator("SignIn.TBUserPassword");
        WaitForElementPresent(tbUserPwrd, getTimeOut());
        sendKeys(tbUserPwrd, password);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     * Verify all the text boxes should contains relevant placeholder text
     */
    public SignInModalPage verifyPlaceholderText() {
    	WebElement eName = driver.findElement(By.id("signupname"));
    	eName.sendKeys("a");
        String placeHolderUserName =signInLocator.getLocator("SignIn.PlaceholderName");
        WaitForElementPresent(placeHolderUserName, getTimeOut());
        Assert.assertTrue(isElementPresent(placeHolderUserName));
        
    	WebElement eEmailID = driver.findElement(By.id("signupemail"));
    	eEmailID.sendKeys("A");
        String placeHolderEmail =signInLocator.getLocator("SignIn.PlaceholderEmail");
        WaitForElementPresent(placeHolderEmail, getTimeOut());
        Assert.assertTrue(isElementPresent(placeHolderEmail));
        
        WebElement ePhone = driver.findElement(By.id("signuptel"));
        ePhone.sendKeys("14");
        String placeHolderPhNumber =signInLocator.getLocator("SignIn.PlaceholderPhNumber");
        WaitForElementPresent(placeHolderPhNumber, getTimeOut());
        Assert.assertTrue(isElementPresent(placeHolderPhNumber));

        WebElement ePassword = driver.findElement(By.id("pwd3"));
        ePassword.sendKeys("qw");
        String placeHolderPassword =signInLocator.getLocator("SignIn.PlaceholderPassword");
        WaitForElementPresent(placeHolderPassword, getTimeOut());
        Assert.assertTrue(isElementPresent(placeHolderPassword));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Verify that on clicking 'show' text in password text box the password characters become unmasked.
     * @return
     */
    public SignInModalPage verifyPasswordBecomeUnmaskedByClickOnShow(String Login_OR_SignUp){
        String btnShow = "";
    	if (Login_OR_SignUp.equalsIgnoreCase("Login")){
    	 btnShow =signInLocator.getLocator("SignIn.BTNShow_Login");
        }
        else if (Login_OR_SignUp.equalsIgnoreCase("SignUp")) {
         btnShow =signInLocator.getLocator("SignIn.BTNShow_SignUp");
        }
        WaitForElementPresent(btnShow, getTimeOut());
        clickOn(btnShow);

        String unMaskedPassword =signInLocator.getLocator("SignIn.UnMaskedPassword");
        WaitForElementPresent(unMaskedPassword, getTimeOut());
        Assert.assertTrue(isElementPresent(unMaskedPassword));
        return PageFactory.initElements(driver, SignInModalPage.class);

    }

    /**
     *  Click on T&C condition
     * @return
     */
    public SignInModalPage clickOnTCLink(){
        String  ltTAndC=signInLocator.getLocator("SignIn.LTTAndC");
        WaitForElementPresent(ltTAndC, getTimeOut());
        clickOn(ltTAndC);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Verify that 'T&C' text is hyperlink and on clicking redirects to 'Terms and Conditions' page. 
     * @return
     */
    public SignInModalPage verifyTAndCLinkRedirectTCPage(){
        String termsAndConditonPage = signInLocator.getLocator("TermsAndConditions.PageHeader");
        WaitForElementPresent(termsAndConditonPage, getTimeOut());
        String pageHeader = getText(termsAndConditonPage);
        Assert.assertTrue(pageHeader.contains("Terms and Conditions"));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Click on Private and Policy condition
     * @return
     */
    public SignInModalPage clickOPrivateAndPolicyLink(){
        String  lTPrivateAndPolicy=signInLocator.getLocator("SignIn.LTPrivateAndPolicy");
        WaitForElementPresent(lTPrivateAndPolicy, getTimeOut());
        clickOn(lTPrivateAndPolicy);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     * Verify that ' Privacy Policy' text is hyperlink and on clicking redirects to ' Privacy Policy' page.
     * @return
     */
    public SignInModalPage verifyPrivateAndPolicyRedirectPrivateAndPolicyPage(){
        String termsAndConditonPage = signInLocator.getLocator("PrivateAndPolicy.PageHeader");
        WaitForElementPresent(termsAndConditonPage, getTimeOut());
        String pageHeader = getText(termsAndConditonPage);
        Assert.assertTrue(pageHeader.contains("Privacy Policy"));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    public DashboardPage clickOnCrossButton(){
        String btnCross=signInLocator.getLocator("SignIn.BTNClose");
        WaitForElementPresent(btnCross, getTimeOut());
        clickOn(btnCross);
        return PageFactory.initElements(driver, DashboardPage.class);
    }

    /**
     * Enter Email and Password in Login fields
     * @param email
     * @param password
     * @return
     */
    public SignInModalPage enterEmailAndPassword(String email, String password) {
        String tbLoginEmail = signInLocator.getLocator("Login.TBLoginEmail");
        WaitForElementPresent(tbLoginEmail, getTimeOut());
        sendKeys(tbLoginEmail, email);

        String tbPassword = signInLocator.getLocator("Login.TBLoginPassword");
        WaitForElementPresent(tbPassword, getTimeOut());
        sendKeys(tbPassword, password);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }
     
    
    public <T> T Login_AdminPanel (@SuppressWarnings("rawtypes") Class className,String email,String password){
         
        String tbLoginEmail = signInLocator.getLocator("Login.TBLoginEmail");
        WaitForElementPresent(tbLoginEmail, getTimeOut());
        sendKeys(tbLoginEmail, email);

        String tbPassword = signInLocator.getLocator("Login.AdminLoginPassword");
        WaitForElementPresent(tbPassword, getTimeOut());
        sendKeys(tbPassword, password);
        
        String submitbutton = signInLocator.getLocator("Login.AdminSubmitButton");
        WaitForElementPresent(submitbutton, getTimeOut());
        clickOn(submitbutton);
        
        return (T) PageFactory.initElements(driver, className);         
  }


    /**
     *  Verify Error for Log in application
     * @return
     */
    public SignInModalPage verifyErrorForLogIn(String errorMessage){
        String btnStartLearning =signInLocator.getLocator("Login.BTNStartLearning");
        clickOn(btnStartLearning);
        // changing the locator  
        String msgError = "//*[@id='signinForm']/div[3]/small[1]";
        WaitForElementPresent(msgError, 20);
        Assert.assertTrue(isElementPresent(msgError));
        return PageFactory.initElements(driver, SignInModalPage.class);

    }

    /**
     * Verify start learning button is disabled 
     * @return
     */
    public SignInModalPage verifyStartLearningButtnDisabled() {
        String btnStartLearning =signInLocator.getLocator("Login.BTNStartLearning");
        WaitForElementPresent(btnStartLearning, getTimeOut());
        String btnStartLearningAttribute = getAttribute(btnStartLearning, "disabled");
        Assert.assertTrue(btnStartLearningAttribute.contains("true"));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Verify Message
     * @return
     */
    public SignInModalPage verifyMessage(String message) throws InterruptedException {
        String msgText = "//span[contains(text(),'"+message+"')]";
        WaitForElementPresent(msgText, 20);
        Assert.assertTrue(isElementPresent(msgText)); 
        return PageFactory.initElements(driver, SignInModalPage.class);
    }
    
    public SignInModalPage verify_IncorrectVerificationCode_errorMessage() throws InterruptedException {
    	WaitForElementPresent("#loginerrortextpass",getTimeOut());
    	WaitForElementVisible("#loginerrortextpass", getTimeOut());
        Thread.sleep(2000);
    	String errormessage = driver.findElement(By.cssSelector("#loginerrortextpass")).getText();
        System.out.println(errormessage);
        Assert.assertEquals("Incorrect verification code",errormessage);
        
        return PageFactory.initElements(driver,SignInModalPage.class);
    }
    
    @FindBy(xpath = "//*[@id='signinForm']/div[3]/small[2]")WebElement PasswordIsRequired;
    @FindBy(css = ".loginerrortextemail")WebElement EmailNot_Verified_ErrorMessage; 
    public SignInModalPage verifyMessage_paramLocator(){
        String message = EmailNot_Verified_ErrorMessage.getText();
        System.out.println("Error Message: " + message);
        Assert.assertEquals("","");
    	return PageFactory.initElements(driver,SignInModalPage.class);
    }
    /**
     *  Click on Forgot Password
     * @return
     * @throws InterruptedException 
     */
    public SignInModalPage clickOnForgotPassword() throws InterruptedException {
      //  waitForLoading();
    	String ltForgotPassword =signInLocator.getLocator("ForgotPassword.ltForgotPassword");
        WaitForElementPresent(ltForgotPassword, getTimeOut());
        WaitForElementClickable(ltForgotPassword,getTimeOut());
        Thread.sleep(3000);
        clickOn(ltForgotPassword);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Verify Success message 'Verification code sent to given email'
     * @param emailID
     * @return
     */
    public SignInModalPage verifySuccessMsgForCode(String emailID) {
        String successMessage = "//span[contains(text(),'Verification code sent to "+emailID+"')]";
        WaitForElementPresent(successMessage, 20);
    //    Assert.assertTrue(isElementPresent(successMessage)); 
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  verify verification code and password fields should display.
     * @return
     */
    public SignInModalPage verifyVerificationCodeAndNewPasswordFields() {
        String verificationCodeField = signInLocator.getLocator("ForgotPassword.TBVerificationCode");
        WaitForElementPresent(verificationCodeField, getTimeOut());
        Assert.assertTrue(isElementPresent(verificationCodeField)); 

        String newPasswordField = signInLocator.getLocator("ForgotPassword.TBNewPassword");
        WaitForElementPresent(newPasswordField, getTimeOut());
        Assert.assertTrue(isElementPresent(newPasswordField)); 
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     * Email text field should disable
     * @return
     */
    public SignInModalPage verifyEmailFieldISDisabled() {
        String emailField = signInLocator.getLocator("ForgotPassword.TBEmail");
        WaitForElementPresent(emailField, getTimeOut());
        String disabledEmail = getAttribute(emailField, "disabled");
        Assert.assertTrue(disabledEmail.contains("true")); 
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     * Verify Email link 'support@edureka.co".
     * @return
     */
    public SignInModalPage verifysupportEmailLink() {
        String lTSuppportEmail = signInLocator.getLocator("ForgotPassword.SupportEmail");
        WaitForElementPresent(lTSuppportEmail, getTimeOut());
        Assert.assertTrue(isElementPresent(lTSuppportEmail)); 
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     * Click on Resend Password Link
     * @return
     */
    public SignInModalPage clickOnResendLink() {
        String lTResend = signInLocator.getLocator("ForgotPassword.LTResend");
        WaitForElementPresent(lTResend, getTimeOut());
        WaitForElementClickable(lTResend,getTimeOut());
        clickOn(lTResend);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     * Verify Create Account button is disabled 
     * @return
     */
    public SignInModalPage verifyCreateAccountButtnDisabled() {
        String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        String btnCreateAccountDisabled = getAttribute(btnCreateAccount, "disabled");
        Assert.assertTrue(btnCreateAccountDisabled.contains("true"));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }
    /**
     *  Verify Error Message is displayed in red
     * @return
     */
    public SignInModalPage verifyErrorMessageInRed() {
        String msgError = signInLocator.getLocator("Login.TxtError");
        WaitForElementPresent(msgError, getTimeOut());
        String errorMessage = getAttribute(msgError, "class");
        Assert.assertTrue(errorMessage.contains("red"));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Verify Error Message is displayed in green
     * @return
     * @throws InterruptedException 
     */
    public SignInModalPage verifySuccessMessageInGreen() throws InterruptedException {
        String msgSuccess = signInLocator.getLocator("ForgotPassword.MessageSuccess");
        WaitForElementPresent(msgSuccess, getTimeOut());
        String successMessage = getAttribute(msgSuccess, "class");
        Thread.sleep(3000);
        Assert.assertTrue(successMessage.contains("successmsgnonresp"));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Enter new Password
     * @param newPassword
     * @return
     */
    public SignInModalPage enterNewPassword(String newPassword) {
        String newPasswordField = signInLocator.getLocator("ForgotPassword.TBNewPassword");
        WaitForElementPresent(newPasswordField, 30);
        sendKeys(newPasswordField, newPassword);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Verify Show functionlity for new Password
     * @return
     */
    public SignInModalPage verifyShowFunctionlityForNewPassword() {
        String btnShow =signInLocator.getLocator("ForgotPassword.BTNShow");
        WaitForElementPresent(btnShow, getTimeOut());
        WaitForElementClickable(btnShow, getTimeOut());
        clickOn(btnShow);

        String unMaskedPassword =signInLocator.getLocator("ForgotPassword.UnMaskedPassword");
        WaitForElementPresent(unMaskedPassword, getTimeOut());
        Assert.assertTrue(isElementPresent(unMaskedPassword));
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  signUp should be allowed with the same email id when an inquiry with fresh/new email id is entered
     * @return
     */
    public UserHomePage signUpForSubmittedQuery(){
        String phNumber = propertyReader.readTestData("PhoneNumber");
        String password = propertyReader.readTestData("Password");
        String userName= propertyReader.readRunTimeData("QueryUserName");
        String email = propertyReader.readRunTimeData("QueryEmail_Id");

        String tbUserName = signInLocator.getLocator("SignIn.TBUserName");
        WaitForElementVisible(tbUserName, getTimeOut());
        sendKeys(tbUserName, userName);

        String tbUserEmail = signInLocator.getLocator("SignIn.TBEmail");
        WaitForElementPresent(tbUserEmail, getTimeOut());
        sendKeys(tbUserEmail, email);

        String tbUserMob =signInLocator.getLocator("SignIn.TBPhNumber");
        WaitForElementPresent(tbUserMob, getTimeOut());
        sendKeys(tbUserMob, phNumber);

        String tbUserPwrd =signInLocator.getLocator("SignIn.TBUserPassword");
        WaitForElementPresent(tbUserPwrd, getTimeOut());
        sendKeys(tbUserPwrd, password);

        String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);

        getPageLoadTimeOut();
        return PageFactory.initElements(driver, UserHomePage.class);
    }

    /**
     *  Navigate to Mailnaor
     * @return
     */
    public Mailnator navigateToMailnator() {
        getWebDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        String mailnatorurl = propertyReader.readApplicationFile("Mailnator-URL");
        getWebDriver().navigate().to(mailnatorurl);
        return PageFactory.initElements(driver, Mailnator.class);
    }

    /**
     * Enter verificaiton code, new password and click on Reset Password button
     * @param verificationCode
     * @return
     */
    public SignInModalPage enterVerificationCodeAndNewPassword(String verificationCode,String password) {
        String verificationCodeField = signInLocator.getLocator("ForgotPassword.TBVerificationCode");
        WaitForElementPresent(verificationCodeField, getTimeOut());
        sendKeys(verificationCodeField, verificationCode);

        String newPasswordField = signInLocator.getLocator("ForgotPassword.TBNewPassword");
        WaitForElementPresent(newPasswordField, getTimeOut());
        sendKeys(newPasswordField, password);

        String btnPasswordReset = signInLocator.getLocator("ForgotPassword.BTNReset");
        WaitForElementPresent(newPasswordField, getTimeOut());
        clickOn(btnPasswordReset);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     *  Click on Start Learning button
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T clickStartLearningButton(@SuppressWarnings("rawtypes") Class className){
        String btnStartLearning =signInLocator.getLocator("Login.BTNStartLearning");
        WaitForElementPresent(btnStartLearning, getTimeOut());
        clickOn(btnStartLearning);
        return (T) PageFactory.initElements(driver, className);
    }

    /**
     *  Click on Log In Link
     */
    public SignInModalPage clickOnLogInLink() {
        String lTlogIn = signInLocator.getLocator("Login.LTLogIn");
        WaitForElementPresent(lTlogIn, getTimeOut());
        clickOn(lTlogIn);
        return PageFactory.initElements(driver, SignInModalPage.class);
    }

    /**
     * Click on Create Account button
     * @return
     */
    public UserHomePage clickOnCreateAccountButton() {
        String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        getPageLoadTimeOut();
        return PageFactory.initElements(driver, UserHomePage.class);
    }
    
    /**
     * Click on Create Account button for Enorll
     * @return
     */
    public OrderSummaryPage createAccountButton() {
        String btnCreateAccount =signInLocator.getLocator("SignIn.BTNCreateAccount");
        WaitForElementPresent(btnCreateAccount, getTimeOut());
        clickOn(btnCreateAccount);
        getPageLoadTimeOut();
        return PageFactory.initElements(driver, OrderSummaryPage.class);
    }

}
