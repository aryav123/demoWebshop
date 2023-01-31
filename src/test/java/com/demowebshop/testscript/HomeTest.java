package com.demowebshop.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.ErrorMessages;
import com.demowebshop.listners.TestListener;
import com.demowebshop.pages.HomePage;
import com.demowebshop.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeTest extends Base {
    HomePage home;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 1,enabled = true,description = "TC001_verify Home page title",groups = {"Smoke"})
    public void TC001_verifyHomePageTitle() {
        extentTest.get().assignCategory("Smoke");
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("HomePage");
        String expHomePageTitle=data.get(1).get(0);
        home=new HomePage(driver);
        String actualHomePageTitle=home.getHomePageTitle();
        extentTest.get().log(Status.PASS,"Home page title received");
        Assert.assertEquals(actualHomePageTitle,expHomePageTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS,"Expected home page title match with actual home page title");
    }
    @Test(priority = 1, enabled = true,description = "TC002 verify subscribe email message",groups = {"Sanity"})
    public  void TC_002_verifySubscribeEmailMessage(){
        extentTest.get().assignCategory("Sanity");
        List<ArrayList<String>> data =ExcelUtility.excelDataReader("HomePage");
        String expectedSubscribeMessage=data.get(1).get(1);
        home=new HomePage(driver);
        home.enterSubscribeEmailId();
        home.clickOnSubscribeButton();
        String actualSubscribeMessage= home.getSubscribeMessage();
        Assert.assertEquals(actualSubscribeMessage,expectedSubscribeMessage,ErrorMessages.SUBSCRIBE_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS,"Expected subscription message match with actual subscription message");
    }
}
