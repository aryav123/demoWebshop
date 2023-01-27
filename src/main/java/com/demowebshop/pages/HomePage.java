package com.demowebshop.pages;

import com.demowebshop.utilities.RandomUtility;
import com.demowebshop.utilities.TestHelper;
import com.demowebshop.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestHelper {
    public WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    /**
     * Page Elements
     **/
    private final String _subscribeEmailField = "newsletter-email";
    @FindBy(id = _subscribeEmailField)
    private WebElement subscribeEmailField;

    private final String _subscribeButton = "newsletter-subscribe-button";
    @FindBy(id = _subscribeButton)
    private WebElement subscribeButton;
    /** Element for newsletter block **/
    private final String _subscribeMessage = "newsletter-result-block";
    @FindBy(id = _subscribeMessage)
    private WebElement subscribeMessage;

    private final String _loginMenu ="//a[@class='ico-login']";
    @FindBy(xpath = _loginMenu) private WebElement loginMenu;

    /**
     * User Action Methods
     **/
    public String getHomePageTitle() {
        String title = page.getPageTitle(driver);
        return title;
    }

    public void enterSubscribeEmailId() {
        String emailId = RandomUtility.getRandomEmail();
        page.enterText(subscribeEmailField, emailId);
    }

    public void clickOnSubscribeButton() {
        page.clickOnElement(subscribeButton);
    }

    public String getSubscribeMessage() {
        //wait.waitForElementToBeVisible(driver,_subscribeMessage, WaitUtility.LocatorType.Id);
          wait.setHardWait();
        wait.waitForElementToBeVisible(driver, _subscribeMessage, WaitUtility.LocatorType.Id);
        String message = page.getElementText(subscribeMessage);
        return message;
    }
    public LoginPage clickOnLoginMenu() {
        page.clickOnElement(loginMenu);
        return new LoginPage(driver);
    }

}
