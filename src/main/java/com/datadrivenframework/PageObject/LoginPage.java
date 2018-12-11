package com.datadrivenframework.PageObject;

import com.datadrivenframework.testbase.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class LoginPage{

    WebDriver driver;
    private final Logger log = TestBase.LoggerHelper.getLogger(LoginPage.class);
//    WaitHelper waitHelper;

    @FindBy(xpath="//input[@name='j_username']")
    WebElement txt_username;

    @FindBy(xpath="//input[@name='j_password']")
    WebElement txt_password;

    @FindBy(xpath="//button[contains(text(),'Log In')]")
    WebElement btn_login;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
//        waitHelper = new WaitHelper(driver);
//        waitHelper.waitForElement(driver, signin,new Config(TestBase.OR).getExplicitWait());
    }

    public void enter_username(String u){
        txt_username.sendKeys(u);
        log.info("username entered...."+u);
    }

    public void enter_password(String p){
        txt_password.sendKeys(p);
        log.info("password entered...."+p);
    }

    public void click_login(){
        btn_login.click();
        log.info("Clicked on login button....");
    }

//    public void loginToApplication(String u, String p){
//
//        enter_username(u);
//        enter_password(p);
//        click_login();
//      }

}
