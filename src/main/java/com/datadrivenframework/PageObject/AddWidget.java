package com.datadrivenframework.PageObject;

import com.datadrivenframework.testbase.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class AddWidget {

    WebDriver driver;
    private final Logger log = TestBase.LoggerHelper.getLogger(LoginPage.class);

    @FindBy(xpath="//input[@name='j_username']")
    WebElement txt_username;

    @FindBy(xpath="//input[@name='j_password']")
    WebElement txt_password;

    @FindBy(xpath="//button[contains(text(),'Log In')]")
    WebElement btn_login;

    @FindBy(xpath="//div[@class='dash-icon']")
    WebElement icon_minidashboard;

    @FindBy(xpath="//*[@id='jazz_ui_toolbar_TwoStateButton_1']/a/img")
    WebElement icon_add_widget;

    @FindBy(xpath="//*[@id='jazz_ensemble_internal_catalog_WidgetCatalog_0']/div[2]/div[2]/div[4]/a")
    WebElement lnk_add_opensocial_gadget;

    @FindBy(xpath="//*[@id='jazz_ensemble_internal_catalog_AddExternalWidgetPane_0']/div[5]/div[1]/input")
    WebElement txt_url_to_opensocial_gadget;

    @FindBy(xpath="//*[@id='jazz_ui_toolbar_Button_47']/a/span")
    WebElement btn_add_widget;

    @FindBy(xpath="//*[@id='com_ibm_team_dashboard_web_ui_internal_catalog_WidgetCatalogDialog_0']/div[2]/div[2]/div[3]/div/div[2]/input")
    WebElement btn_close_widget_window;

    @FindBy(xpath="//h3[@id='wel-test']")
    WebElement msg_widget_added;


    /* Functions/Mothods prresent on page */


    public AddWidget(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    public void click_minidashborad_icon(){
        icon_minidashboard.click();
        log.info("Clicked on mini dashboard icon....");
    }

    public void click_addwidget_icon(){
        icon_add_widget.click();
        log.info("Clicked on Add Widget icon....");
    }

    public void link_addopensocialgadget(){
        lnk_add_opensocial_gadget.click();
        log.info("Clicked on open social gadget link....");
    }

    public void enter_urltoopensocialgadget(String url){
        txt_url_to_opensocial_gadget.sendKeys(url);
        log.info("Entered url to open social gadget....");
    }

    public void click_addwidgetbutton(){
        btn_add_widget.click();
        log.info("Clicked on add widget button....");
    }

    public void click_closewidgetwindow(){
        btn_close_widget_window.click();
        log.info("Clicked on close widget window button....");
    }

    public void verify_welcomemsg() throws InterruptedException {
        msg_widget_added.getText().trim();
        Thread.sleep(2000);
        Assert.assertEquals(msg_widget_added.getText(),"Welcome Watson to Your Requirements Team");
        Thread.sleep(1000);
        log.info("Widget is added successfully....");
    }



}
