package com.datadrivenframework.PageObject;

import com.datadrivenframework.testbase.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckScore {

    WebDriver driver;
    private final Logger log = TestBase.LoggerHelper.getLogger(CheckScore.class);

    @FindBy(xpath="//*[@id=\"com_ibm_rdm_web_grid_CachedVirtualGrid_0\"]/div/div[1]/div/div[1]/table[1]/tbody/tr[1]/td[1]/div/label")
    WebElement chk_reqid;

    

   // Methods starts from here

    public void click_chkbox(){
        chk_reqid.click();
    }


}
