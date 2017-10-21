package com.edureka.searchFunctionality;

import org.testng.annotations.Test;

import com.edureka.pages.AllCoursesPage;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class SearchBasedOnCategory extends DriverTestCase{
    private DashboardPage dashboardPage;
    private AllCoursesPage allCoursesPage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;

    private static String allCourse;
    private static String category;

    @Test
    public void test_001SearchBasedOnCategory() throws Exception {

        try {
            // Navigate to app url
            addLog("Navigate to the Edureka application url");
            dashboardPage = applicationSetup();

            // Verify Edureka Dashboard Page
            addLog("Verify Edureka Dashboard Page");
            dashboardPage=  dashboardPage.verifyDashboard();

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            // Sign up User
            String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            //  Select AllCourse Tab from Courses dropdown
            allCourse = propertyReader.readTestData("AllCourse");
            addLog("Select "+allCourse+" Tab from Courses dropdown");
            allCoursesPage= userHomePage.selectCourseOption(AllCoursesPage.class, allCourse);

            // Verify All Courses page
            addLog("Verify All Courses page");
            allCoursesPage= allCoursesPage.verifyAllCoursesPage();

            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Big_Data_Analytics");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            String queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);

            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Category_Cloud_Computing");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);

            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Category_Cloud_Computing");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);

            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Category_Business_Intelligence");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);

            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Category_DevOps");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);

            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Category_Finance_Marketing");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);
            ////////////////



            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Category_Program_And_Develpment");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);

            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Category_Software_Testing");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);

            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Category_Project_Management");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);

            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Category_Mobile_App_Development");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);

            // Click on View all Link in Search Category
            addLog("Click on View all Link in Search Category");
            allCoursesPage= allCoursesPage.clickOnViewAllButton();

            // Verify All Search Categories are visible
            addLog("Verify All Search Categories are visible");
            allCoursesPage= allCoursesPage.verifyAllSearchCategoriesVisible();

            // Check Course Check box in Search Category
            category = propertyReader.readTestData("Category_Others");
            addLog("Check "+category+" Check box in Search Category");
            allCoursesPage = allCoursesPage.checkCourseCheckBoxinSearch(category);

            // Verify Selected course is appread in Searched Result
            addLog("Verify Selected "+category+" is appread in Searched Result");
            allCoursesPage = allCoursesPage.verifySelectFilterAppear(category);

            // Verify Dispalyed courses based on selected Category
            queryForCategory= propertyReader.readTestData("Query_For_Category");
            queryForCategory= queryForCategory.replace("#", category);
            addLog("Verify Dispalyed courses based on "+category);
            allCoursesPage = allCoursesPage.verifyDisplayedCourses(queryForCategory);


        }   catch (final Error e) {
            captureScreenshot("test_001SearchBasedOnCategory");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_001SearchBasedOnCategory");
            throw e;
        }
    }
}