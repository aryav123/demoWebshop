package com.demowebshop.testscript;

import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.ErrorMessages;
import com.demowebshop.dataprovider.DataProviders;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.LoginPage;
import com.demowebshop.pages.UserAccountPage;
import com.demowebshop.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LoginTest extends Base {
    HomePage home;
    LoginPage login;
    UserAccountPage useraccountpage;
    @Test(priority = 1,description = "TC001 Verify Login Page Title")
    public void TC001_verifyLoginPageTitle(){
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String expLoginPageTitle=data.get(1).get(0);
        home=new HomePage(driver);
        login=home.clickOnLoginMenu();
        String actLogingPageTitle=login.getLoginPageTitle();
        Assert.assertEquals(actLogingPageTitle,expLoginPageTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);
    }
    @Test(priority = 1,description = "TC002 Verify Valid Login")
    public void TC002_verifyValidLogin(){
        home=new HomePage(driver);
        login=home.clickOnLoginMenu();
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String userEmailId=data.get(1).get(1);
        login.enterUserEmailId(userEmailId);
        String userPassword=data.get(1).get(2);
        login.enterUserPassword(userPassword);
        useraccountpage = login.clickOnLoginButton();
        String actEmail=useraccountpage.getUserAccountEmailId();
        Assert.assertEquals(userEmailId,actEmail,ErrorMessages.INVALID_EMAIL_ID);
    }
    @Test(priority = 2,description = "TC003 Verify Invalid login error message",dataProvider ="InvalidUserCredentials",dataProviderClass = DataProviders.class)
    public void TC003_verifyInvalidLoginErrorMessage(String username,String password){
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
        String expectedErrorMessage=data.get(1).get(3);
        home=new HomePage(driver);
        login=home.clickOnLoginMenu();
        login.enterUserEmailId(username);
        login.enterUserPassword(password);
        login.clickOnLoginButton();
        String actErrorMessage=login.getLoginErrorMessage();
        Assert.assertEquals(actErrorMessage,expectedErrorMessage,ErrorMessages.INVALID_ERROR_MESSAGE);
    }
}
