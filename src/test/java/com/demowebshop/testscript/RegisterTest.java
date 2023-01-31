package com.demowebshop.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.ErrorMessages;
import com.demowebshop.listners.TestListener;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.LoginPage;
import com.demowebshop.pages.RegisterPage;
import com.demowebshop.pages.UserAccountPage;
import com.demowebshop.utilities.ExcelUtility;
import com.demowebshop.utilities.RandomUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RegisterTest extends Base {
    HomePage home;
    RegisterPage register;
    UserAccountPage useraccountpage;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 1,description = "TC001 Verify Register",groups = {"Regression"})
    public void TC001_verifyRegister(){
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> data = ExcelUtility.excelDataReader("RegisterPage");
        String gender=data.get(1).get(0);
        String fName=RandomUtility.getfName();
        String lName=RandomUtility.getlName();
        String email=RandomUtility.getRandomEmail();
        String password=fName+"@123";
        String cPassword=password;
        home=new HomePage(driver);
        register=home.clickOnRegisterMenu();
        register.selectGender(gender);
        register.enterFirstName(fName);
        register.enterLastName(lName);
        register.enterEmail(email);
        register.enterPassword(password);
        register.enterConformPassword(cPassword);
        useraccountpage =register.clickOnRegisterButton();
        String actEmail=useraccountpage.getUserAccountEmailId();
        Assert.assertEquals(email,actEmail, ErrorMessages.INVALID_EMAIL_ID);
        extentTest.get().log(Status.PASS,"User registration completed successfully");
    }
}
