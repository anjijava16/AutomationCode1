package com.edureka.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.locators.LocatorReader;
import com.edureka.util.DriverHelper;

public class ForumsPage extends DriverHelper{

    private LocatorReader forumsLocator;
    public ForumsPage(WebDriver driver) {
        super(driver);

        forumsLocator = new LocatorReader("Forums.xml");
    }

    /**
     *  Verify Fourms Page
     * @return
     */
    public ForumsPage verifyPage(){
        String pageHeader = forumsLocator.getLocator("PageHeader");
        WaitForElementPresent(pageHeader, getTimeOut());
        String fourmsPage = getAttribute(pageHeader, "placeholder");
        Assert.assertTrue(fourmsPage.contains("Forum"));
        return PageFactory.initElements(driver, ForumsPage.class);
    }

    /**
     *  Verify and Click on Post Question button
     * @return
     */

    public QuestionPage verifyAndClickOnPostQuestionButton(){
        String btnPostQuestion = forumsLocator.getLocator("BTNPostQuestion");
        WaitForElementPresent(btnPostQuestion, getTimeOut());
        Assert.assertTrue(isElementPresent(btnPostQuestion));
        clickOn(btnPostQuestion);
        return PageFactory.initElements(driver, QuestionPage.class);
    }

    /**
     *  Verify Question is successfully posted
     * @return
     */
    public ForumsPage verifySuccessMsgPostQuestion(){
        String successMessagePostQuestion = forumsLocator.getLocator("SuccessMsgPostQuestion");
        WaitForElementPresent(successMessagePostQuestion, getTimeOut());
        Assert.assertTrue(isElementPresent(successMessagePostQuestion));
        return PageFactory.initElements(driver, ForumsPage.class);
    }

    /**
     *  Verify Moderation Status 
     * @param title
     * @return
     */
    public ForumsPage verifyModerationStatusOfQuestion(){
        String statusModeration = forumsLocator.getLocator("UnderModeration");
        WaitForElementPresent(statusModeration, getTimeOut());
        Assert.assertTrue(isElementPresent(statusModeration));
        return PageFactory.initElements(driver, ForumsPage.class);
    }

    /**
     *  Close Success message pop up
     * @return
     */
    public ForumsPage closeSuccessMessage(){
        String btnClose = forumsLocator.getLocator("BTNClose");
        WaitForElementPresent(btnClose, getTimeOut());
        clickOn(btnClose);
        return PageFactory.initElements(driver, ForumsPage.class);
    }

    /**
     *  Verify Different Filters on Post Questions page
     * @return
     */
    public ForumsPage verifyFilters(){
        String filters = forumsLocator.getLocator("Filters");
        WaitForElementPresent(filters, getTimeOut());
        String filtersName= getText(filters);
        Assert.assertTrue(filtersName.contains("Recent"));
        Assert.assertTrue(filtersName.contains("Open"));
        Assert.assertTrue(filtersName.contains("Most Answered"));
        Assert.assertTrue(filtersName.contains("Most Viewed"));
        Assert.assertTrue(filtersName.contains("My Support Tickets"));
        return PageFactory.initElements(driver, ForumsPage.class);
    }

    /**
     *  Verify Searched Functionality for Forums
     * @param searchKeyword
     * @return
     */
    public ForumsPage verifySearchFunctionality(String searchKeyword){
        String tbSearchForum = forumsLocator.getLocator("TBSearchForum");
        WaitForElementPresent(tbSearchForum, getTimeOut());
        Assert.assertTrue(isElementPresent(tbSearchForum));
        sendKeys(tbSearchForum, searchKeyword);

        String imgSearch= forumsLocator.getLocator("ImgSearchData");
        WaitForElementPresent(imgSearch, getTimeOut());
        clickOn(imgSearch);

        String searchedResults = forumsLocator.getLocator("SearchedResult");
        String searchedResultText= getText(searchedResults);
        Assert.assertTrue(searchedResultText.contains(searchKeyword));
        return PageFactory.initElements(driver, ForumsPage.class);
    }

}
