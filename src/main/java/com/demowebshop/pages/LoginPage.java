package com.demowebshop.pages;

import com.demowebshop.utilities.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestHelper {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private final String _userEmailField = "Email";
    @FindBy(id=_userEmailField)
    private WebElement userEmailField;

    private final String _passwordField = "Password";
    @FindBy(id=_passwordField)
    private WebElement passwordField;

    private final String _loginButton = "//input[@class='button-1 login-button']";
    @FindBy(xpath=_loginButton)
    private WebElement loginButton;


    public String getLoginPageTitle() {
        String title=page.getPageTitle(driver);
        return title;
    }
    public void enterUserEmailId(String userName){
        page.enterText(userEmailField,userName);
    }
    public void enterUserPassword(String password){
        page.enterText(passwordField,password);
    }
    public UserAccountPage clickOnLoginButton(){
        page.clickOnElement(loginButton);
        return new UserAccountPage(driver);
    }
}
