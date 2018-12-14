package com.datadrivenframework.login;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;

import com.datadrivenframework.PageObject.LoginPage;
import com.datadrivenframework.excelReader.Excel_reader;
import com.datadrivenframework.testbase.Config;
import com.datadrivenframework.testbase.TestBase;
import org.testng.annotations.Test;


import java.util.List;

//import com.datadrivenframework.helper.Logger.LoggerHelper;

// my project


public class LoginTest extends TestBase{
    private final Logger log = LoggerHelper.getLogger(LoginTest.class);

/*
    @DataProvider(name="testData")
    public Object[][] dataSource(){
        return getData("TestData.xlsx", "Data");
    }  */

//    @Test(dataProvider="testData")

    @Test
    public void testLoginToApplication() throws InterruptedException {
        log.info(LoginTest.class.getName()+" started");

        Config config = new Config(OR);
        driver.get(config.getWebsite());

        List<String> testData = Excel_reader.eData;

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enter_username(testData.get(0));
        test.log(LogStatus.INFO, "Username Entered");

        loginPage.enter_password(testData.get(1));
        test.log(LogStatus.INFO, "Password Entered");

        loginPage.click_login();
        test.log(LogStatus.INFO, "Clicked on Login Button");
        Thread.sleep(5000);

 //       loginPage.loginToApplication(Username, Password);


/*        boolean status = loginPage.verifySuccessLoginMsg();
        if(status){
            log.info("login is successful");
        }
        else{
            Assert.assertTrue(false, "login is not sucessful");
        }
*/

    }
}
