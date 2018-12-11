package com.datadrivenframework.Add_Widget;

import com.datadrivenframework.PageObject.AddWidget;
import com.datadrivenframework.excelReader.Excel_reader;
import com.datadrivenframework.testbase.Config;
import com.datadrivenframework.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

public class add_widget extends TestBase {
	private final Logger log = LoggerHelper.getLogger(add_widget.class);


	@Test
	public void addwidget() throws InterruptedException {
		log.info(add_widget.class.getName()+" started");

		Config config = new Config(OR);
		driver.get(config.getWebsite());

		List<String> testData = Excel_reader.eData;

		AddWidget addwidget = new AddWidget(driver);

		addwidget.enter_username(testData.get(0));
		test.log(LogStatus.INFO, "Username Entered");

		addwidget.enter_password(testData.get(1));
		test.log(LogStatus.INFO, "Password Entered");

		addwidget.click_login();
		test.log(LogStatus.INFO, "Clicked on Login Button");
        Thread.sleep(4000);

		addwidget.click_minidashborad_icon();
		test.log(LogStatus.INFO, "Clicked on mini dashboard icon");
        Thread.sleep(4000);

		addwidget.click_addwidget_icon();
		test.log(LogStatus.INFO, "Clicked on Add Widget icon");
        Thread.sleep(4000);

		addwidget.link_addopensocialgadget();
		test.log(LogStatus.INFO, "Clicked on open social gadget link");

		addwidget.enter_urltoopensocialgadget(testData.get(2));
		test.log(LogStatus.INFO, "Entered url to open social gadget");
		Thread.sleep(3000);

		addwidget.click_addwidgetbutton();
		test.log(LogStatus.INFO, "Clicked on add widget button");

		addwidget.click_closewidgetwindow();
		test.log(LogStatus.INFO, "Clicked on close widget window button");
		Thread.sleep(2000);

		driver.switchTo().frame("jazz_opensocial_Gadget_0");

		addwidget.verify_welcomemsg();
		test.log(LogStatus.INFO, "Widget is added successfully");



	}
}
