package com.demowebshop.testscript;

import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.ErrorMessages;
import com.demowebshop.pages.HomePage;
import com.demowebshop.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeTest extends Base {
    HomePage home;
    @Test(priority = 1,description = "TC001_verify Home page title")
    public void TC001_verifyHomePageTitle() {
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("HomePage");
        String expHomePageTitle=data.get(1).get(0);
        home=new HomePage(driver);
        String actualHomePageTitle=home.getHomePageTitle();
        Assert.assertEquals(actualHomePageTitle,expHomePageTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);
    }
    @Test(priority = 1, description = "TC002 verify subscribe email message")
    public  void TC_002_verifySubscribeEmailMessage(){
        List<ArrayList<String>> data =ExcelUtility.excelDataReader("HomePage");
        String expectedSubscribeMessage=data.get(1).get(1);
        home=new HomePage(driver);
        home.enterSubscribeEmailId();
        home.clickOnSubscribeButton();
        String actualSubscribeMessage= home.getSubscribeMessage();
        Assert.assertEquals(actualSubscribeMessage,expectedSubscribeMessage,ErrorMessages.SUBSCRIBE_FAILURE_MESSAGE);
    }
}
